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
 * ����session
 * @author Administrator
 *
 */
public class HibernateFilter implements Filter {
	/**
	synchronized �ǽ������̷߳���ͬһ������
	 ThreadLocal ��ÿ���߳�ά���Լ�ר�õı���
    ��ThreadLocal���ж�����һ���̰߳�ȫ��ʹ��synchronized ʹһ�δ���ͬʱֻ����һ���߳���������
	��ThreadLocalMap�� Map��Ԫ�صļ�Ϊ�̶߳��󣬶�ֵ��Ӧ�̵߳ı���������
	Ҳ����������Ҫ��hibernate.Session
	hreadLocal��֤��ÿ���̶߳����Լ���Session
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
		//ͨ����ǰ�߳���ThreadLocal�����ThreadLocalMap������ȡ��session����
		//�÷���Ϊ��synchronized��ͬ������
		Session session = (Session)hibernateHolder.get();
		if (session == null) {
			session = factory.openSession();
			hibernateHolder.set(session);
		}
		return session;
	}
}
