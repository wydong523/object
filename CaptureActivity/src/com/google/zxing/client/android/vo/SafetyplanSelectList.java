/**   
 * @Title: SafetyplanSelectList.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Desctiption ��ȫ�ƻ�ѡ��ֵ�б�ʵ��
 * @author ���嶰
 * @date 2013-10-10 ����10:47:55
 */
public class SafetyplanSelectList implements Serializable {

	private int pagesize;

	private int inboxsCount;

	private ArrayList<SafetyplanSelect> safetyplans = new ArrayList<SafetyplanSelect>();

	@Override
	public String toString() {
		return "SafetyplanSelectList [pagesize=" + pagesize + ", inboxsCount="
				+ inboxsCount + ", safetyplans=" + safetyplans + "]";
	}

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

	public ArrayList<SafetyplanSelect> getSafetyplans() {
		return safetyplans;
	}

	public void setSafetyplans(ArrayList<SafetyplanSelect> safetyplans) {
		this.safetyplans = safetyplans;
	}
	
}
