package com.cx.bank.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 管理session
 * @author Administrator
 *
 */
public class HibernateFilter implements Filter {
	/**
	synchronized 是解决多个线程访问同一个变量
	 ThreadLocal 是每个线程维护自己专用的变量
    在ThreadLocal类中定义了一个线程安全（使用synchronized 使一段代码同时只能有一个线程来操作）
	的ThreadLocalMap， Map中元素的键为线程对象，而值对应线程的变量副本。
	也就是我们需要的hibernate.Session
	hreadLocal保证了每个线程都有自己的Session
	*/
	private static ThreadLocal hibernateHolder = new ThreadLocal();
	
	private static SessionFactory factory = null; 
	
	public void destroy() {
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		try {
			filterChain.doFilter(servletRequest, servletResponse);
		} finally {
			Session session = (Session)hibernateHolder.get();
			if (session != null) {
				if (session.isOpen()) {
					session.close();
				}
				hibernateHolder.remove();
			}
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		try {
			Configuration cfg = new Configuration().configure();
			factory = cfg.buildSessionFactory();
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}	
	}
	
	public static Session getSession() {
		//通过当前线程在ThreadLocal对象的ThreadLocalMap集合中取得session对象
		//该方法为（synchronized）同步操作
		Session session = (Session)hibernateHolder.get();
		if (session == null) {
			session = factory.openSession();
			hibernateHolder.set(session);
		}
		return session;
	}
}
