/**   
 * @Title: PersonSelectList.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Desctiption ��Աѡ��ֵ�б�ʵ��(�����ˡ������ˡ�Ա������)
 * @author ���嶰
 * @date 2013-10-10 ����10:59:39
 */
public class PersonSelectList implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 4630288241540021175L;

	private int pagesize;

	private int inboxsCount;

	private ArrayList<PersonSelect> persons = new ArrayList<PersonSelect>();

	@Override
	public String toString() {
		return "PersonSelectList [pagesize=" + pagesize + ", inboxsCount="
				+ inboxsCount + ", persons=" + persons + "]";
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

	public ArrayList<PersonSelect> getPersons() {
		return persons;
	}

	public void setPersons(ArrayList<PersonSelect> persons) {
		this.persons = persons;
	}

}
