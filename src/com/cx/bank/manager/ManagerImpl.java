
  package com.cx.bank.manager;

  import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import com.cx.bank.dao.BankDaoInterface;
import com.cx.bank.factory.UserDaoFactory;
import com.cx.bank.model.MoneyBean;
import com.cx.bank.model.UserBean;
import com.cx.bank.user.model.BankCard;
import com.cx.bank.user.model.DepositeCard;
import com.cx.bank.user.model.User;
import com.cx.bank.util.AccountOverDrawnException;
import com.cx.bank.util.HibernateFilter;
import com.cx.bank.util.InvalidDepositException;

import Exception.ErrorCodeException;
 

   /**
    * ManagerImpl业务层实现
    * 
    * @author admin
    * 
    */
   public class ManagerImpl implements ManagerInterface {   
		private static ManagerImpl instance = null;
		MoneyBean moneyBean = MoneyBean.getInstance();//获取单例的模型层对象
		
//		private BankDaoInterface userDao = null;
		
		public ManagerImpl()throws Exception{
//			UserDaoFactory DaoFactory = UserDaoFactory.getInstance();//获取单例的工厂类对象
//			userDao = DaoFactory.createUserDao();//拿到持久层映射对象
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
	public boolean register(String name,String psd,String ID){
//	 UserBean user=new UserBean();
//	 user.setUserName(name);
//	 user.setPassword(psd);
// 	 boolean flag=bif.register(user);
//	 BankDaoInterface bif=FiledaoImpl.getInstance();
		Session session = null;
//		try {
			//session = HibernateUtils.getSession();
			session = HibernateFilter.getSession();
			session.beginTransaction();
			
			List user= session.createQuery("select userName from User").list();
			if("".equals(name)|| "".equals(psd)) {
				System.out.println("用户名或密码不能为空！");
				throw new ErrorCodeException("user.password.isnull");

			} else {
			for(Iterator iter=user.iterator();iter.hasNext();){
				String obj = (String)iter.next();
					if(name.equals(obj))
					{
						System.out.println("该用户已存在！请重新注册");
						throw new ErrorCodeException("user.has.exist", name);
					}
				}
				 //用户类
				 User bankUser = new User();
				 bankUser.setUserName(name);
				 bankUser.setIDCredit(ID); 
				 //银行卡类
				 DepositeCard bk = new DepositeCard();
				 bk.setPsd(psd);
				 bk.setBankUser(bankUser);
				 Set BankCards = new HashSet();
				 BankCards.add(bk);
				 bankUser.setBankCards(BankCards);
				 
				session.save(bankUser);
				session.getTransaction().commit();
				System.out.println("注册成功！");
			}
//		}
//		catch(Exception e) {
//			//记录日志,log4j等......
//			e.printStackTrace();
//			session.getTransaction().rollback();
//			//throw new AppException("drp.database.item.error.findallitem");
//		}
	 	return true;
	}
	/**
	 * 登录方法的实现
	 * @param user 用户名
	 */
	 public boolean login(String unm, String psd){
		UserBean user=new UserBean();
		Session session = null;
//		try {
			session = HibernateFilter.getSession();
			session.beginTransaction();
			List u= session.createQuery("from User").list();
			boolean flag =false;
			if("".equals(unm)|| "".equals(psd)) {
				System.out.println("用户名或密码不能为空！");
				throw new ErrorCodeException("user.password.isnull");

			} else {
			for (Iterator iter=u.iterator(); iter.hasNext();) {
				User obj = (User)iter.next();
				
				if(unm.equals(obj.getUserName())) {
					String b= (String) session.createQuery("select bk.psd from BankCard bk where bk.bankUser.userName like :un").setParameter("un",unm).uniqueResult();
					flag = true;
					if(psd.equals(b)){
						Double m=(Double) session.createQuery("select bk.bankMoney from BankCard bk where bk.bankUser.userName like :un").setParameter("un",unm).uniqueResult();
						MoneyBean.getInstance().setMoney(m);
						System.out.println("登录成功");
						session.getTransaction().commit();
						return true;
					}
					else{
						throw new ErrorCodeException("user.password.error");
					}
				}
			}
			}
			if(!flag){
				throw new ErrorCodeException("user.not.found", unm);
			}
//		}
//		catch(Exception e) {
//			//记录日志,log4j等......
//			e.printStackTrace();
//			session.getTransaction().rollback();
//			System.out.println("登录失败");
//		}
		return true;
		
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
			Session session = null;
			session = HibernateFilter.getSession();
			session.beginTransaction();
			
			List user= session.createQuery("select userName from User").list();
			if("".equals(inAccount)|| "".equals(money)) {
				System.out.println("用户名或密码不能为空！");
				throw new ErrorCodeException("user.password.isnull");
			} else {			
			if (dmoney<moneyBalance&&dmoney>0)//判断你的余额是否大于转账金额和转账金额是否大于零
			{
				for(Iterator iter=user.iterator();iter.hasNext();){
					String obj = (String)iter.next();
					if(inAccount.equals(obj))
					{    //转账
						try {
							double num = moneyBalance-dmoney;//用户的余额减去要转账的金额
							moneyBean.setMoney(num);
							//对方银行卡
							BankCard tu=(BankCard) session.createQuery("select bk from BankCard bk where bk.bankUser.userName like :un").setParameter("un",inAccount).uniqueResult();
							tu.setBankMoney(tu.getBankMoney()+dmoney);
							session.save(tu);
							session.getTransaction().commit();
						}catch(Exception e) {
							//记录日志,log4j等......
							e.printStackTrace();
							session.getTransaction().rollback();
							System.out.println("转账失败");
						}
					}
				}
				//userDao.transfer(inAccount,dmoney);//调用转账方法	
				return true;
			}
			else{
//			   System.out.println("转账金额不足或金额不合法!");
				throw new ErrorCodeException("user.tramoney.error");
			}
		}
	 }

	
	/**退出系统*/
	public void exitSystem(String logname){
		//BankDaoInterface bif=FiledaoImpl.getInstance();
		Session session = null;
		try {
			//bif.AddBank(logname);
			session = HibernateFilter.getSession();
			session.beginTransaction();
			BankCard bkUser=(BankCard)session.createQuery("select bk from BankCard bk where bk.bankUser.userName like :un").setParameter("un",logname).uniqueResult();
			bkUser.setBankMoney(MoneyBean.getInstance().getMoney());
			session.save(bkUser);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("系统已经退出");
	}
	 

  }
