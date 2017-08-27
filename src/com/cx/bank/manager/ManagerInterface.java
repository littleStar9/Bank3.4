
 package com.cx.bank.manager;
 import java.io.IOException;

import com.cx.bank.model.UserBean;
 import com.cx.bank.util.AccountOverDrawnException;
import com.cx.bank.util.InvalidDepositException;

  /**
   * @date 2016.6.21
   * @author admin ҵ����ӿ�
   */
  public interface ManagerInterface {
	  
	  /**
		 * ע��
		 * @param user ��װ�û���������
		 */
  public boolean register(String name, String psd); 
	
  /**
	 * ��¼
	 * @param user ��װ�û���������
	 */
	public boolean login(String name, String psd);
	/**
	 * ���
	 * @param money
	 * @return
	 */
	void deposit(String money)throws InvalidDepositException,NumberFormatException;
	
	/**
	 * ȡ��
	 * @param money
	 * @return
	 */
	void withdrawals(String money)throws AccountOverDrawnException,NumberFormatException;
	
	/**
	 * ��ѯ
	 * @return
	 */
	double inquiry();
	
	/**
	 * ת��
	 * @param yourAccount ת���˺�
	 * @param hisAccount  ת���˺�
	 * @param money  ת�˽��
	 * @return boolean
	 * @throws NumberFormatException
	 */
	
	public boolean tranferMoney(String outAccount,String money)throws NumberFormatException,NumberFormatException,IOException;
	
	/**
	 * �˳�ϵͳ
	 */
	 public void exitSystem(String logname);
}
