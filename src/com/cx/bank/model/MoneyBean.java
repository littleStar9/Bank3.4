 
  package com.cx.bank.model;

  /**
   *����ģʽ-�˻����ʵ��bean
   * @author admin
   * @date  2017.6 
   */
  public class MoneyBean {
	
	private double money;//�˻����
	private static MoneyBean moneyBean ;
	
	 
	private MoneyBean() {

	}

	/**
	 * ��ȡMoneyBeanʵ���ľ�̬����
	 * @return
	 */
	public static MoneyBean getInstance() {
		 if(moneyBean==null){
			 moneyBean=new MoneyBean();
			}
		 return  moneyBean;
	}
    
	/**�õ���� */
	public double getMoney() {

		return money;
	}

	  /**�洢Ǯ*/
	public void setMoney(double money) {
		this.money = money;
	}

 }
