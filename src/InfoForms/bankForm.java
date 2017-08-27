package InfoForms;

import org.apache.struts.action.ActionForm;

public class bankForm extends ActionForm {
	//存款金额
	private String depMoney;
	
	//取款金额
	private String wdrMoney;
	
	//转账金额
	private String traMoney;
	
	//转至账方用户名
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