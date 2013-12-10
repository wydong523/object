package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Account列表实体类
 * 
 * @author 汪渝栋
 * 
 */
public class AccountList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4800813856228645131L;

	private int pagesize;

	private int inboxsCount;

	private ArrayList<Account> accounts = new ArrayList<Account>();

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getInboxsCount() {
		return inboxsCount;
	}

	public void setInboxsCount(int inboxsCount) {
		this.inboxsCount = inboxsCount;
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

}
