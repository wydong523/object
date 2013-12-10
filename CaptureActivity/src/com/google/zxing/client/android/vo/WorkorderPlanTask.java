/**   
 * @Title: WorkorderPlanTask.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import com.google.zxing.client.android.utils.SoapHelper;
import com.google.zxing.client.android.vo.WorktypeSelect.Fields;

/**
 * @Desctiption 工单计划任务实体对象
 * @author 汪渝栋
 * @date 2013-10-19 下午12:03:59
 */
public class WorkorderPlanTask extends Base implements Serializable,
		ParserInterface {

	// 任务号
	// 描述
	// 持续时间

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -2526280723767170000L;

	private String taskid;

	private String description;

	private String estdur;
	
	private String workorderid;

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEstdur() {
		return estdur;
	}

	public void setEstdur(String estdur) {
		this.estdur = estdur;
	}

	
	@Override
	public String toString() {
		return "WorkorderPlanTask [taskid=" + taskid + ", description="
				+ description + ", estdur=" + estdur + ", workorderid="
				+ workorderid + "]";
	}


	class Fields {
		public static final String TASKID = "TASKID";
		public static final String DESCRIPTION = "DESCRIPTION";
		public static final String ESTDUR = "ESTDUR";
		public static final String WORKORDERID = "WORKORDERID";
	}

	/*
	 * (非 Javadoc) <p>Title: parse</p> <p>Description: </p>
	 * 
	 * @param result
	 * 
	 * @return
	 * 
	 * @see com.google.zxing.client.android.vo.ParserInterface#parse(org.ksoap2.
	 * serialization.SoapObject)
	 */
	@Override
	public Serializable parse(SoapObject result) {
		SoapObject taskData = result;
		WorkorderPlanTask planTask = null;
		WorkorderPlanTaskList lineList = new WorkorderPlanTaskList();
		ArrayList<WorkorderPlanTask> tasks = lineList.getPlanTasks();
		int resultCount = taskData.getPropertyCount();
		for (int i = 0; i < resultCount; i++) {
			planTask = new WorkorderPlanTask();
			SoapObject subObj = (SoapObject) result.getProperty(i);
			planTask.setTaskid(SoapHelper.getSoapObjectString(subObj,
					Fields.TASKID));
			planTask.setDescription(SoapHelper.getSoapObjectString(subObj,
					Fields.DESCRIPTION));
			planTask.setEstdur(SoapHelper.getSoapObjectString(subObj,
					Fields.ESTDUR));
			planTask.setWorkorderid(SoapHelper.getSoapObjectString(subObj,
					Fields.WORKORDERID));
			tasks.add(planTask);
		}
		return lineList;
	}

	/**
	 * @return the workorderid
	 */
	public String getWorkorderid() {
		return workorderid;
	}

	/**
	 * @param workorderid the workorderid to set
	 */
	public void setWorkorderid(String workorderid) {
		this.workorderid = workorderid;
	}

}
