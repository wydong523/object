/**   
 * @Title: LocationsSelectList.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Desctiption λ��ֵ�б�ʵ��
 * @author ���嶰
 * @date 2013-10-10 ����10:20:26
 */
public class LocationsSelectList implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -7506921532315510013L;

	private int pagesize;

	private int inboxsCount;

	private ArrayList<LocationsSelect> locations = new ArrayList<LocationsSelect>();

	public ArrayList<LocationsSelect> getLocations() {
		return locations;
	}

	public void setLocations(ArrayList<LocationsSelect> locations) {
		this.locations = locations;
	}

	public int getPagesize() {
		return pagesize;
	}

	@Override
	public String toString() {
		return "LocationsSelectList [pagesize=" + pagesize + ", inboxsCount="
				+ inboxsCount + ", locations=" + locations + "]";
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

}
