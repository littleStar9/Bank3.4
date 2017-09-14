package com.cx.bank.util;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.cx.bank.user.model.BankCard;

public class ExportDB {

	public static void main(String[] args) {
		
		//¶ÁÈ¡hibernate.cfg.xmlÎÄ¼þ
		Configuration cfg = new Configuration().configure();
		//cfg.addClass(BankCard.class);
		SchemaExport export = new SchemaExport(cfg);
		export.create(true, true);
	}
}
