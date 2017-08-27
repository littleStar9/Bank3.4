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
		Class c = Class.forName(className);//�����࣬��ȡӳ�����
		Object o = c.newInstance();//ͨ��ӳ����󴴽�����
		//��classInfo.properties�����ļ��ж�̬��ȡ�־ò�FiledaoImplʵ����
		userDao = (BankDaoInterface)o;
		
	} 
	public static synchronized UserDaoFactory getInstance()throws Exception{
		if (instance == null) {
			instance = new UserDaoFactory();
		}
		return instance;
	}
	//����userDao����
	public BankDaoInterface createUserDao() {
		return userDao;
	}
}
