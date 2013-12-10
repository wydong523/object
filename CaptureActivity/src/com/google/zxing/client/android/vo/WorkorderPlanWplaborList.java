/**   
 * @Title: WorkorderPlanWplaborList.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Desctiption 工单计划人员列表实体
 * @author 汪渝栋
 * @date 2013-10-23 下午4:25:15
 */
public class WorkorderPlanWplaborList extends Base implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -6491652862791011842L;

	private int pagesize;

	private int wplaborCount;

	private ArrayList<WorkorderPlanWplabor> wplabors = new ArrayList<WorkorderPlanWplabor>();

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getWplaborCount() {
		return wplaborCount;
	}

	public void setWplaborCount(int wplaborCount) {
		this.wplaborCount = wplaborCount;
	}

	public ArrayList<WorkorderPlanWplabor> getWplabors() {
		return wplabors;
	}

	public void setWplabors(ArrayList<WorkorderPlanWplabor> wplabors) {
		this.wplabors = wplabors;
	}

	@Override
	public String toString() {
		return "WorkorderPlanWplaborList [pagesize=" + pagesize
				+ ", wplaborCount=" + wplaborCount + ", wplabors=" + wplabors
				+ "]";
	}

}
