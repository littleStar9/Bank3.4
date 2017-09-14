
   package com.cx.bank.dao;

   import java.io.IOException;

import com.cx.bank.user.model.User;

     /**
      * 
      * @author Administrator
      * �־ò�ӿ�
      */
      public interface BankDaoInterface {

      /**
       * �洢����
       * @throws Exception
       */
	   void AddBank(String logname)throws  Exception;

	  /**
	   * ע��
	   * @param name �û���
	   * @param psd ����
	   */
	   public boolean register(User bankUser);
	   /**
		 * ��¼
		 * @param user
		 *  
		 */
		public boolean login(User bakUser);
		 /**
		 * ȡ������
		 * @param ת���˺�
		 */
		public double getBalance(String outAccount);
		
		/**ת��
		@return void
		@param double dmoney ת�˽��
		@param outAccount ת���˺�
		@param inAccount ת���˺�
		@exception IOException
		*/
	    public void  transfer(String inAccount,double dmoney) throws IOException;
  }
