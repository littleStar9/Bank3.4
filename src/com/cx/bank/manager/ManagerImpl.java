
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
    * ManagerImpl业务层实现
    * 
    * @author admin
    * 
    */
   public class ManagerImpl implements ManagerInterface {
	
	//private static ManagerImpl instance=null;
	//MoneyBean moneyBean = MoneyBean.getInstance();//获取单例的模型层对象
	//BankDaoInterface dao=FiledaoImpl.getInstance();
	   
	   
		private static ManagerImpl instance = null;
		MoneyBean moneyBean = MoneyBean.getInstance();//获取单例的模型层对象
		private BankDaoInterface userDao = null;
		
		public ManagerImpl()throws Exception{
			UserDaoFactory DaoFactory = UserDaoFactory.getInstance();//获取单例的工厂类对象
			userDao = DaoFactory.createUserDao();//拿到持久层映射对象
		}
		//一加载类就会创建反射对象，此处给反射对象加锁
		//单例实现在线程里只能取得单一的实例，不能同时取得多个实例
		//加锁实现在多个线程同时运行只能有一个线程取得单一实例throws Exception
		public static synchronized ManagerImpl getInstance(){
			if(instance == null)
				try {
					instance = new ManagerImpl();
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			return instance;
		}
		
		
	/**
	 * 注册
	 * @param name 用户名
	 * @param psd 密码
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
	 * 登录方法的实现
	 * @param user 用户名
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
	
	/**实现存款功能
	 * @exception 不处理InvalidDepositException异常
	 */
	
	public void deposit(String smoney)throws InvalidDepositException,NumberFormatException {
		  
			double money=Double.parseDouble(smoney);
//			如果用户存钱金额小于0，则抛出InvalidDepositException异常
			if (money <= 0) 
//					throw new InvalidDepositException("存款金额需大于0");
				throw new ErrorCodeException("user.depmoney.error");
				 
		 double leave  = moneyBean.getMoney();
	      moneyBean.setMoney(leave+money);//	存入
		 
		 
	}

	/**实现取款功能
	 * @exception 不处理AccountOverDrawnException异常
	 */
	public void withdrawals(String smoney)throws AccountOverDrawnException,NumberFormatException {
		 
		double money=Double.parseDouble(smoney);
		
		double leave  = moneyBean.getMoney();
			
		 if (money > leave)  
			 //如果用户取款金额大于余额，则抛出AccountOverDrawnException异常 
//					throw new AccountOverDrawnException("余额不足");
			 throw new ErrorCodeException("user.dramoney.error");
			
			moneyBean.setMoney(leave - money);//存入
			 
		}
		

    /**实现查看余额功能*/
	public double inquiry() {
		 
		double money = moneyBean.getMoney();
		return money;
	}

	/**
	 * 转账
	 * @param outAccount 转出账号
	 * @param inAccount  转入账号
	 * @param money  转账金额
	 * @return boolean
	 * @throws NumberFormatException
	 */
	public boolean tranferMoney(String inAccount,String money)throws NumberFormatException,IOException
	{
			double dmoney=Double.parseDouble(money);//把转账金额转化为数字
			double moneyBalance= moneyBean.getMoney();
			
			if (dmoney<moneyBalance&&dmoney>0)//判断你的余额是否大于转账金额和转账金额是否大于零
			{
				userDao.transfer(inAccount,dmoney);//调用转账方法
				return true;
			}
			else{
//			   System.out.println("转账金额不足或金额不合法!");
				throw new ErrorCodeException("user.tramoney.error");
//				return false;
			}
			
	 }

	
	/**退出系统*/
	public void exitSystem(String logname){
		BankDaoInterface bif=FiledaoImpl.getInstance();
		try {
			bif.AddBank(logname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("系统已经退出");
		//System.exit(1);
	}
	 

  }
