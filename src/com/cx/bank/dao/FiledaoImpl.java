
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
   * 持久层实现类
   * @author Administrator
   *
   */
  public class FiledaoImpl implements BankDaoInterface {
	MoneyBean moneyBean = MoneyBean.getInstance();//获取单例的模型层对象
	private static FiledaoImpl dao ;
	//FiledaoImpl dao = new FiledaoImpl();
	MD5 md5=new MD5();
	
	public FiledaoImpl() {
   }
	/**
	 * 获取单一实例的静态方法
	 * @return
	 */
	public static FiledaoImpl getInstance() {
		 if(dao==null){
			dao=new FiledaoImpl();
			}
		 return dao;
        }
	 /**
     * 存储方法实现
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
	  * 注册方法实现
	  * @param name 用户名
	  * @param psd 密码
	  */
	 public boolean register(UserBean user)
	 {   
		 String name=user.getUserName();
		 String psd=user.getPassword();
		 File f = new File( name + ".properties");
			if(f.exists()) {
				System.out.println("该用户已存在！请重新注册");
				throw new ErrorCodeException("user.has.exist", name);
//				return false;
			} else {
				if("".equals(name) || "".equals(psd)) {
					System.out.println("用户名或密码不能为空！");
					throw new ErrorCodeException("user.password.isnull");
//					return false;
				} else {
					try {
						Properties props = new Properties();
						FileInputStream fis = new FileInputStream(new File("Bank.properties"));
						props.load(fis);
						fis.close();
						String upass=md5.encode(psd.getBytes());//用md5技术对用户密码进行加密
						props.setProperty("userName", name);
						props.setProperty("password", upass);
						props.setProperty("money", "10");
						FileOutputStream fos = new FileOutputStream(name + ".properties");
						props.store(fos, name + ".properties");
						fos.close();
						System.out.println("注册成功！");
						return true;
					} catch(IOException e) {
						System.out.println("读取文件出错！");
						return false;
					}
				}
			}
		  }
	 /**
		 * 登录方法的实现
		 * @param user 用户名
		 */
		public boolean login(UserBean user){
			 String name=user.getUserName();
			 String psd=user.getPassword();
			try {
				Properties props = new Properties();
				FileInputStream fis = new FileInputStream(new File(".\\" + name + ".properties"));
				props.load(fis);
				fis.close();
				String upass1=md5.encode(psd.getBytes());//用md5技术对用户密码进行加密
				String upass2=props.getProperty("password");//取得密码的密文
				if(name.equals(props.getProperty("userName")) && (upass1.equals(upass2))) {
					MoneyBean.getInstance().setMoney(Double.parseDouble(props.getProperty("money")));
					System.out.println("登录成功");
					
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
				//System.out.println("用户不存在!");
				//return false;
			}
		}
		 /**
		 * 取得余额方法的实现
		 * @param 转出账号
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
//				System.out.println("用户不存在!");
			}
			return money;
		}
		
		/**转账
		@return void
		@param double dmoney 转账金额
		@param outAccount 转出账号
		@param inAccount 转出账号
		@exception IOException
		*/
	    public void  transfer(String inAccount,double dmoney) throws IOException
		{
 	    	  Properties props = new Properties();
	    	  double leave  = moneyBean.getMoney(); 						  
			  double num = leave-dmoney;//用户的余额减去要转账的金额
              
			  moneyBean.setMoney(num);
 
			  /*读入要转账用户的证书信息*/
			  FileInputStream in1file=new FileInputStream(inAccount+".properties");
			  props.load(in1file);
			  in1file.close();//关闭文件流
              						  
			  double num1 = Double.parseDouble(props.getProperty("money"))+dmoney;//要转账用户的余额加上要转账的金额
			  String s1=String.valueOf(num1);//把double类型变量转化为字符串
              
			  props.setProperty("money",s1);//设置要转账用户的余额
               
              /*把改变后的要转账用户的信息重新存入文件*/
			  FileOutputStream out=new FileOutputStream(inAccount+".properties");
			  props.store(out,inAccount+".properties");
			  out.close();//关闭文件流
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
