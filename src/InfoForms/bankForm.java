package InfoForms;

import org.apache.struts.action.ActionForm;

public class bankForm extends ActionForm {
	//�����
	private String depMoney;
	
	//ȡ����
	private String wdrMoney;
	
	//ת�˽��
	private String traMoney;
	
	//ת���˷��û���
	private String TName;
	
	public String getDepMoney() {
		return depMoney;
	}

	public void setDepMoney(String depMoney) {
		this.depMoney = depMoney;
	}

	public String getWdrMoney() {
		return wdrMoney;
	}

	public void setWdrMoney(String wdrMoney) {
		this.wdrMoney = wdrMoney;
	}

	public String getTName() {
		return TName;
	}

	public void setTName(String tName) {
		TName = tName;
	}


	public String getTraMoney() {
		return traMoney;
	}

	public void setTraMoney(String traMoney) {
		this.traMoney = traMoney;
	}

}