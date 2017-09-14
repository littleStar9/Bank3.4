
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
    * ManagerImplҵ���ʵ��
    * 
    * @author admin
    * 
    */
   public class ManagerImpl implements ManagerInterface {   
		private static ManagerImpl instance = null;
		MoneyBean moneyBean = MoneyBean.getInstance();//��ȡ������ģ�Ͳ����
		
//		private BankDaoInterface userDao = null;
		
		public ManagerImpl()throws Exception{
//			UserDaoFactory DaoFactory = UserDaoFactory.getInstance();//��ȡ�����Ĺ��������
//			userDao = DaoFactory.createUserDao();//�õ��־ò�ӳ�����
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
				System.out.println("�û��������벻��Ϊ�գ�");
				throw new ErrorCodeException("user.password.isnull");

			} else {
			for(Iterator iter=user.iterator();iter.hasNext();){
				String obj = (String)iter.next();
					if(name.equals(obj))
					{
						System.out.println("���û��Ѵ��ڣ�������ע��");
						throw new ErrorCodeException("user.has.exist", name);
					}
				}
				 //�û���
				 User bankUser = new User();
				 bankUser.setUserName(name);
				 bankUser.setIDCredit(ID); 
				 //���п���
				 DepositeCard bk = new DepositeCard();
				 bk.setPsd(psd);
				 bk.setBankUser(bankUser);
				 Set BankCards = new HashSet();
				 BankCards.add(bk);
				 bankUser.setBankCards(BankCards);
				 
				session.save(bankUser);
				session.getTransaction().commit();
				System.out.println("ע��ɹ���");
			}
//		}
//		catch(Exception e) {
//			//��¼��־,log4j��......
//			e.printStackTrace();
//			session.getTransaction().rollback();
//			//throw new AppException("drp.database.item.error.findallitem");
//		}
	 	return true;
	}
	/**
	 * ��¼������ʵ��
	 * @param user �û���
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
				System.out.println("�û��������벻��Ϊ�գ�");
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
						System.out.println("��¼�ɹ�");
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
//			//��¼��־,log4j��......
//			e.printStackTrace();
//			session.getTransaction().rollback();
//			System.out.println("��¼ʧ��");
//		}
		return true;
		
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
			Session session = null;
			session = HibernateFilter.getSession();
			session.beginTransaction();
			
			List user= session.createQuery("select userName from User").list();
			if("".equals(inAccount)|| "".equals(money)) {
				System.out.println("�û��������벻��Ϊ�գ�");
				throw new ErrorCodeException("user.password.isnull");
			} else {			
			if (dmoney<moneyBalance&&dmoney>0)//�ж��������Ƿ����ת�˽���ת�˽���Ƿ������
			{
				for(Iterator iter=user.iterator();iter.hasNext();){
					String obj = (String)iter.next();
					if(inAccount.equals(obj))
					{    //ת��
						try {
							double num = moneyBalance-dmoney;//�û�������ȥҪת�˵Ľ��
							moneyBean.setMoney(num);
							//�Է����п�
							BankCard tu=(BankCard) session.createQuery("select bk from BankCard bk where bk.bankUser.userName like :un").setParameter("un",inAccount).uniqueResult();
							tu.setBankMoney(tu.getBankMoney()+dmoney);
							session.save(tu);
							session.getTransaction().commit();
						}catch(Exception e) {
							//��¼��־,log4j��......
							e.printStackTrace();
							session.getTransaction().rollback();
							System.out.println("ת��ʧ��");
						}
					}
				}
				//userDao.transfer(inAccount,dmoney);//����ת�˷���	
				return true;
			}
			else{
//			   System.out.println("ת�˽������Ϸ�!");
				throw new ErrorCodeException("user.tramoney.error");
			}
		}
	 }

	
	/**�˳�ϵͳ*/
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
		System.out.println("ϵͳ�Ѿ��˳�");
	}
	 

  }
