/**   
 * @Title: WorkorderPlanTaskList.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Desctiption 工单计划任务列表实体
 * @author 汪渝栋
 * @date 2013-10-19 下午12:09:46
 */
public class WorkorderPlanTaskList implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -5477919708281423420L;

	private int pagesize;

	private int planTaskCount;

	private ArrayList<WorkorderPlanTask> planTasks = new ArrayList<WorkorderPlanTask>();

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPlanTaskCount() {
		return planTaskCount;
	}

	public void setPlanTaskCount(int planTaskCount) {
		this.planTaskCount = planTaskCount;
	}

	public ArrayList<WorkorderPlanTask> getPlanTasks() {
		return planTasks;
	}

	public void setPlanTasks(ArrayList<WorkorderPlanTask> planTasks) {
		this.planTasks = planTasks;
	}

	@Override
	public String toString() {
		return "WorkorderPlanTaskList [pagesize=" + pagesize
				+ ", planTaskCount=" + planTaskCount + ", planTasks="
				+ planTasks + "]";
	}

}
