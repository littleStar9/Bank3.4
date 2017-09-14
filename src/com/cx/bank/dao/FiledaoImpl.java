
  package com.cx.bank.dao;

  import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.cx.bank.model.MoneyBean;
import com.cx.bank.model.UserBean;
import com.cx.bank.user.model.User;
import com.cx.bank.util.MD5;

import Exception.ErrorCodeException;
  /**
   * �־ò�ʵ����
   * @author Administrator
   *
   */
  public class FiledaoImpl implements BankDaoInterface {
	MoneyBean moneyBean = MoneyBean.getInstance();//��ȡ������ģ�Ͳ����
	private static FiledaoImpl dao ;
	//FiledaoImpl dao = new FiledaoImpl();
	MD5 md5=new MD5();
	
	public FiledaoImpl() {
   }
	/**
	 * ��ȡ��һʵ���ľ�̬����
	 * @return
	 */
	public static FiledaoImpl getInstance() {
		 if(dao==null){
			dao=new FiledaoImpl();
			}
		 return dao;
        }
	 /**
     * �洢����ʵ��
     * @throws Exception
     */
	public void AddBank(String logname) throws Exception {
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream(new File(logname+ ".properties"));
		props.load(fis);
		fis.close();
		props.setProperty("money", (new Double(MoneyBean.getInstance().getMoney())).toString());
		FileOutputStream fos = new FileOutputStream(logname + ".properties");
		props.store(fos, logname + ".properties");
		fos.close();
       }
	
	/**
	  * ע�᷽��ʵ��
	  * @param name �û���
	  * @param psd ����
	  */
	 public boolean register(UserBean user)
	 {   
		 String name=user.getUserName();
		 String psd=user.getPassword();
		 File f = new File( name + ".properties");
			if(f.exists()) {
				System.out.println("���û��Ѵ��ڣ�������ע��");
				throw new ErrorCodeException("user.has.exist", name);
//				return false;
			} else {
				if("".equals(name) || "".equals(psd)) {
					System.out.println("�û��������벻��Ϊ�գ�");
					throw new ErrorCodeException("user.password.isnull");
//					return false;
				} else {
					try {
						Properties props = new Properties();
						FileInputStream fis = new FileInputStream(new File("Bank.properties"));
						props.load(fis);
						fis.close();
						String upass=md5.encode(psd.getBytes());//��md5�������û�������м���
						props.setProperty("userName", name);
						props.setProperty("password", upass);
						props.setProperty("money", "10");
						FileOutputStream fos = new FileOutputStream(name + ".properties");
						props.store(fos, name + ".properties");
						fos.close();
						System.out.println("ע��ɹ���");
						return true;
					} catch(IOException e) {
						System.out.println("��ȡ�ļ�����");
						return false;
					}
				}
			}
		  }
	 /**
		 * ��¼������ʵ��
		 * @param user �û���
		 */
		public boolean login(UserBean user){
			 String name=user.getUserName();
			 String psd=user.getPassword();
			try {
				Properties props = new Properties();
				FileInputStream fis = new FileInputStream(new File(".\\" + name + ".properties"));
				props.load(fis);
				fis.close();
				String upass1=md5.encode(psd.getBytes());//��md5�������û�������м���
				String upass2=props.getProperty("password");//ȡ�����������
				if(name.equals(props.getProperty("userName")) && (upass1.equals(upass2))) {
					MoneyBean.getInstance().setMoney(Double.parseDouble(props.getProperty("money")));
					System.out.println("��¼�ɹ�");
					
					return true;
				} else {
					if(!name.equals(props.getProperty("userName"))){
						throw new ErrorCodeException("user.not.found", name);}
					else{
					throw new ErrorCodeException("user.password.error");
		
					}
				}
			} 
			catch (IOException e) {
				throw new ErrorCodeException("user.not.found", name);
				//System.out.println("�û�������!");
				//return false;
			}
		}
		 /**
		 * ȡ��������ʵ��
		 * @param ת���˺�
		 */
		public double getBalance(String outAccount){
			double money=0;
			try {
				Properties props = new Properties();
				FileInputStream fis = new FileInputStream(new File(outAccount + ".properties"));
				props.load(fis);
				fis.close();
				money=Double.parseDouble(props.getProperty("money"));
			} catch (IOException e) {
				throw new ErrorCodeException("user.not.found", outAccount);
//				System.out.println("�û�������!");
			}
			return money;
		}
		
		/**ת��
		@return void
		@param double dmoney ת�˽��
		@param outAccount ת���˺�
		@param inAccount ת���˺�
		@exception IOException
		*/
	    public void  transfer(String inAccount,double dmoney) throws IOException
		{
 	    	  Properties props = new Properties();
	    	  double leave  = moneyBean.getMoney(); 						  
			  double num = leave-dmoney;//�û�������ȥҪת�˵Ľ��
              
			  moneyBean.setMoney(num);
 
			  /*����Ҫת���û���֤����Ϣ*/
			  FileInputStream in1file=new FileInputStream(inAccount+".properties");
			  props.load(in1file);
			  in1file.close();//�ر��ļ���
              						  
			  double num1 = Double.parseDouble(props.getProperty("money"))+dmoney;//Ҫת���û���������Ҫת�˵Ľ��
			  String s1=String.valueOf(num1);//��double���ͱ���ת��Ϊ�ַ���
              
			  props.setProperty("money",s1);//����Ҫת���û������
               
              /*�Ѹı���Ҫת���û�����Ϣ���´����ļ�*/
			  FileOutputStream out=new FileOutputStream(inAccount+".properties");
			  props.store(out,inAccount+".properties");
			  out.close();//�ر��ļ���
		}
		@Override
		public boolean register(User bankUser) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean login(User bakUser) {
			// TODO Auto-generated method stub
			return false;
		}

  
  
  }
