package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Inbox�б�ʵ����
 * 
 * @author ���嶰
 * 
 */
public class InboxList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2259438525364700933L;

	private int pagesize;

	private int inboxsCount;

	private ArrayList<Inbox> inboxs = new ArrayList<Inbox>();

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

	public ArrayList<Inbox> getInboxs() {
		return inboxs;
	}

	public void setInboxs(ArrayList<Inbox> inboxs) {
		this.inboxs = inboxs;
	}
}
