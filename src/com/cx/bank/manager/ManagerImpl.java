
  package com.cx.bank.manager;

  import java.io.IOException;

import com.cx.bank.dao.BankDaoInterface;
import com.cx.bank.dao.FiledaoImpl;
import com.cx.bank.factory.UserDaoFactory;
import com.cx.bank.model.MoneyBean;
import com.cx.bank.model.UserBean;
import com.cx.bank.util.AccountOverDrawnException;
import com.cx.bank.util.InvalidDepositException;

import Exception.ErrorCodeException;
 

   /**
    * ManagerImplҵ���ʵ��
    * 
    * @author admin
    * 
    */
   public class ManagerImpl implements ManagerInterface {
	
	//private static ManagerImpl instance=null;
	//MoneyBean moneyBean = MoneyBean.getInstance();//��ȡ������ģ�Ͳ����
	//BankDaoInterface dao=FiledaoImpl.getInstance();
	   
	   
		private static ManagerImpl instance = null;
		MoneyBean moneyBean = MoneyBean.getInstance();//��ȡ������ģ�Ͳ����
		private BankDaoInterface userDao = null;
		
		public ManagerImpl()throws Exception{
			UserDaoFactory DaoFactory = UserDaoFactory.getInstance();//��ȡ�����Ĺ��������
			userDao = DaoFactory.createUserDao();//�õ��־ò�ӳ�����
		}
		//һ������ͻᴴ��������󣬴˴�������������
		//����ʵ�����߳���ֻ��ȡ�õ�һ��ʵ��������ͬʱȡ�ö��ʵ��
		//����ʵ���ڶ���߳�ͬʱ����ֻ����һ���߳�ȡ�õ�һʵ��throws Exception
		public static synchronized ManagerImpl getInstance(){
			if(instance == null)
				try {
					instance = new ManagerImpl();
				} catch (Exception e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			return instance;
		}
		
		
	/**
	 * ע��
	 * @param name �û���
	 * @param psd ����
	 */
	public boolean register(String name, String psd) {
	 UserBean user=new UserBean();
	 user.setUserName(name);
	 user.setPassword(psd);
	 BankDaoInterface bif=FiledaoImpl.getInstance();
	 boolean flag=bif.register(user);
	 return flag;
	}
	/**
	 * ��¼������ʵ��
	 * @param user �û���
	 */
	public boolean login(String name, String psd){
		UserBean user=new UserBean();
// 		if (!"admin".equals(username)) {
// 			System.out.println("name fail");
// 			throw new ErrorCodeException("user.not.found", username);
//		}
//		if (!"admin".equals(password)) {
//			System.out.println("psde fail");
//			throw new ErrorCodeException("user.password.error");
//			}
		user.setUserName(name);
		user.setPassword(psd);
		BankDaoInterface bif=FiledaoImpl.getInstance();
		boolean flag=bif.login(user);
		return flag;
	}
	
	/**ʵ�ִ���
	 * @exception ������InvalidDepositException�쳣
	 */
	
	public void deposit(String smoney)throws InvalidDepositException,NumberFormatException {
		  
			double money=Double.parseDouble(smoney);
//			����û���Ǯ���С��0�����׳�InvalidDepositException�쳣
			if (money <= 0) 
//					throw new InvalidDepositException("����������0");
				throw new ErrorCodeException("user.depmoney.error");
				 
		 double leave  = moneyBean.getMoney();
	      moneyBean.setMoney(leave+money);//	����
		 
		 
	}

	/**ʵ��ȡ���
	 * @exception ������AccountOverDrawnException�쳣
	 */
	public void withdrawals(String smoney)throws AccountOverDrawnException,NumberFormatException {
		 
		double money=Double.parseDouble(smoney);
		
		double leave  = moneyBean.getMoney();
			
		 if (money > leave)  
			 //����û�ȡ������������׳�AccountOverDrawnException�쳣 
//					throw new AccountOverDrawnException("����");
			 throw new ErrorCodeException("user.dramoney.error");
			
			moneyBean.setMoney(leave - money);//����
			 
		}
		

    /**ʵ�ֲ鿴����*/
	public double inquiry() {
		 
		double money = moneyBean.getMoney();
		return money;
	}

	/**
	 * ת��
	 * @param outAccount ת���˺�
	 * @param inAccount  ת���˺�
	 * @param money  ת�˽��
	 * @return boolean
	 * @throws NumberFormatException
	 */
	public boolean tranferMoney(String inAccount,String money)throws NumberFormatException,IOException
	{
			double dmoney=Double.parseDouble(money);//��ת�˽��ת��Ϊ����
			double moneyBalance= moneyBean.getMoney();
			
			if (dmoney<moneyBalance&&dmoney>0)//�ж��������Ƿ����ת�˽���ת�˽���Ƿ������
			{
				userDao.transfer(inAccount,dmoney);//����ת�˷���
				return true;
			}
			else{
//			   System.out.println("ת�˽������Ϸ�!");
				throw new ErrorCodeException("user.tramoney.error");
//				return false;
			}
			
	 }

	
	/**�˳�ϵͳ*/
	public void exitSystem(String logname){
		BankDaoInterface bif=FiledaoImpl.getInstance();
		try {
			bif.AddBank(logname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("ϵͳ�Ѿ��˳�");
		//System.exit(1);
	}
	 

  }
