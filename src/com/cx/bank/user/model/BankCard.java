package com.cx.bank.user.model;

import java.sql.Date;

/**
 * ���п���
 * @author Administrator
 *
 */
public class BankCard {
	
    //���п���
	private int bankId;
	//����
	private String psd;
	//���֤��
	//private int[] IDCredit = new int[18];
	//private String IDCredit;
	//�ֻ���
	private String telPhone;
	//������
	private String city;
	//����ʱ��
	private Date creatDate;
	//���ÿ�
	private CreditCard creCard;
	//���
	private DepositeCard depCard;
	//�������
	private double bankMoney;
	//�û�
	private User bankUser;

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}

	public CreditCard getCreCard() {
		return creCard;
	}

	public void setCreCard(CreditCard creCard) {
		this.creCard = creCard;
	}

	public DepositeCard getDepCard() {
		return depCard;
	}

	public void setDepCard(DepositeCard depCard) {
		this.depCard = depCard;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getPsd() {
		return psd;
	}

	public void setPsd(String psd) {
		this.psd = psd;
	}

	public double getBankMoney() {
		return bankMoney;
	}

	public void setBankMoney(double bankMoney) {
		this.bankMoney = bankMoney;
	}

	public User getBankUser() {
		return bankUser;
	}

	public void setBankUser(User bankUser) {
		this.bankUser = bankUser;
	}

//	public BankCard getBankCard() {
//		return bankCard;
//	}
//
//	public void setBankCard(BankCard bankCard) {
//		this.bankCard = bankCard;
//	}


	

}
