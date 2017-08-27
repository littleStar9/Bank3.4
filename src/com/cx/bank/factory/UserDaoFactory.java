package com.cx.bank.factory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.cx.bank.dao.BankDaoInterface;

public class UserDaoFactory {
	private static UserDaoFactory instance;
	private BankDaoInterface userDao;

	private UserDaoFactory()throws Exception{
		Properties p = new Properties();
		File a = new File("ClassInfo.properties");
		if(!a.exists()){
			a.createNewFile();
		
		}System.out.println(a.getAbsolutePath());
		FileInputStream fis = new FileInputStream(new File("ClassInfo.properties"));
		p.load(fis);
		fis.close();
		String className = p.getProperty("className");
		System.out.println(className);
		Class c = Class.forName(className);//加载类，获取映射对象
		Object o = c.newInstance();//通过映射对象创建对象
		//从classInfo.properties配置文件中动态获取持久层FiledaoImpl实现类
		userDao = (BankDaoInterface)o;
		
	} 
	public static synchronized UserDaoFactory getInstance()throws Exception{
		if (instance == null) {
			instance = new UserDaoFactory();
		}
		return instance;
	}
	//创建userDao对象
	public BankDaoInterface createUserDao() {
		return userDao;
	}
}
