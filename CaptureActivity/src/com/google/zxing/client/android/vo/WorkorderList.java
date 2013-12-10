package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 工单列表实体
 * 
 * @author 汪渝栋
 * 
 */
public class WorkorderList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8389064136973922834L;

	private int pagesize;

	private int workorderCount;

	private ArrayList<Workorder> workorders = new ArrayList<Workorder>();

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the workorderCount
	 */
	public int getWorkorderCount() {
		return workorderCount;
	}

	/**
	 * @param workorderCount
	 *            the workorderCount to set
	 */
	public void setWorkorderCount(int workorderCount) {
		this.workorderCount = workorderCount;
	}

	/**
	 * @return the workorders
	 */
	public ArrayList<Workorder> getWorkorders() {
		return workorders;
	}

	/**
	 * @param workorders the workorders to set
	 */
	public void setWorkorders(ArrayList<Workorder> workorders) {
		this.workorders = workorders;
	}

}
