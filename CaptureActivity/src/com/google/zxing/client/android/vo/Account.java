package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import com.google.zxing.client.android.utils.SoapHelper;

/**
 * �û�����
 * 
 * @author ���嶰
 * 
 */
public class Account extends Base implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2736372270309936769L;

	private int id;

	private String username;// �û���

	private String password;// �û�����

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * ���������û���������
	 * @param result
	 * @return
	 */
	public ArrayList<Account> parse(SoapObject result) {
		SoapObject accountData = result;
		Account account = null;
		AccountList accountList = new AccountList();
		ArrayList<Account> accounts = accountList.getAccounts();
		int objCount = accountData.getPropertyCount();
		for (int i = 0; i < objCount; i++) {
			SoapObject subObj = (SoapObject) result.getProperty(i);
			account = new Account();
			account.setUsername(SoapHelper.getSoapObjectString(subObj,
					"LOGINID"));
			account.setPassword(SoapHelper.getSoapObjectString(subObj,
					"PASSWORD"));
			accounts.add(account);
		}
		return accounts;
	}
}
