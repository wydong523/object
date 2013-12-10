/**   
 * @Title: NumericdomainSelectList.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Desctiption ���ȼ�ѡ��ֵ�б�ʵ��
 * @author ���嶰
 * @date 2013-10-10 ����10:55:44
 */
public class NumericdomainSelectList implements Serializable {

	private int pagesize;

	private int inboxsCount;

	private ArrayList<NumericdomainSelect> numericdomains = new ArrayList<NumericdomainSelect>();

	@Override
	public String toString() {
		return "NumericdomainSelectList [pagesize=" + pagesize
				+ ", inboxsCount=" + inboxsCount + ", numericdomains="
				+ numericdomains + "]";
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

	public ArrayList<NumericdomainSelect> getNumericdomains() {
		return numericdomains;
	}

	public void setNumericdomains(ArrayList<NumericdomainSelect> numericdomains) {
		this.numericdomains = numericdomains;
	}

}
