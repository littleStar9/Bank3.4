
 package com.cx.bank.manager;
 import java.io.IOException;

import com.cx.bank.model.UserBean;
 import com.cx.bank.util.AccountOverDrawnException;
import com.cx.bank.util.InvalidDepositException;

  /**
   * @date 2016.6.21
   * @author admin 业务类接口
   */
  public interface ManagerInterface {
	  
	  /**
		 * 注册
		 * @param user 封装用户名，密码
		 */
  public boolean register(String name, String psd); 
	
  /**
	 * 登录
	 * @param user 封装用户名，密码
	 */
	public boolean login(String name, String psd);
	/**
	 * 存款
	 * @param money
	 * @return
	 */
	void deposit(String money)throws InvalidDepositException,NumberFormatException;
	
	/**
	 * 取款
	 * @param money
	 * @return
	 */
	void withdrawals(String money)throws AccountOverDrawnException,NumberFormatException;
	
	/**
	 * 查询
	 * @return
	 */
	double inquiry();
	
	/**
	 * 转账
	 * @param yourAccount 转出账号
	 * @param hisAccount  转入账号
	 * @param money  转账金额
	 * @return boolean
	 * @throws NumberFormatException
	 */
	
	public boolean tranferMoney(String outAccount,String money)throws NumberFormatException,NumberFormatException,IOException;
	
	/**
	 * 退出系统
	 */
	 public void exitSystem(String logname);
}
