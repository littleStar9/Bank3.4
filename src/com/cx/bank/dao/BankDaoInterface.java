
   package com.cx.bank.dao;

   import java.io.IOException;

import com.cx.bank.model.UserBean;

     /**
      * 
      * @author Administrator
      * 持久层接口
      */
      public interface BankDaoInterface {

      /**
       * 存储方法
       * @throws Exception
       */
	   void AddBank(String logname)throws  Exception;

	  /**
	   * 注册
	   * @param name 用户名
	   * @param psd 密码
	   */
	   public boolean register(UserBean user);
	   /**
		 * 登录
		 * @param user
		 *  
		 */
		public boolean login(UserBean user);
		 /**
		 * 取得余额方法
		 * @param 转出账号
		 */
		public double getBalance(String outAccount);
		
		/**转账
		@return void
		@param double dmoney 转账金额
		@param outAccount 转出账号
		@param inAccount 转出账号
		@exception IOException
		*/
	    public void  transfer(String inAccount,double dmoney) throws IOException;
  }
