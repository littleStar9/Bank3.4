package com.cx.bank.user.model;

import java.util.Set;

/**
 * 用户类
 * @author Administrator
 *
 */
public class User {
	
    //用户名
	private String userName;
	//性别
	private boolean sex;
	//身份证号
	//private int[] IDCredit = new int[18];
	private String IDCredit;
	//手机号
	private String telphone;
	//户口所在地
	private String city;
	//银行卡
	//private BankCard[] bankCard = new BankCard[2];
	private Set bankCards;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public String getIDCredit() {
		return IDCredit;
	}

	public void setIDCredit(String iDCredit) {
		IDCredit = iDCredit;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Set getBankCards() {
		return bankCards;
	}

	public void setBankCards(Set bankCards) {
		this.bankCards = bankCards;
	}

}
