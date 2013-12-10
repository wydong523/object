/**   
 * @Title: HomeworkPlanSelectList.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Desctiption 作业计划选择值列表实体
 * @author 汪渝栋
 * @date 2013-10-12 下午3:34:35
 */
public class HomeworkPlanSelectList implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1339567844363947334L;

	private int pagesize;

	private int hpCount;

	private ArrayList<HomeworkPlanSelect> homeworks = new ArrayList<HomeworkPlanSelect>();

	@Override
	public String toString() {
		return "HomeworkPlanSelectList [pagesize=" + pagesize + ", hpCount="
				+ hpCount + ", homeworks=" + homeworks + "]";
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getHpCount() {
		return hpCount;
	}

	public void setHpCount(int hpCount) {
		this.hpCount = hpCount;
	}

	public ArrayList<HomeworkPlanSelect> getHomeworks() {
		return homeworks;
	}

	public void setHomeworks(ArrayList<HomeworkPlanSelect> homeworks) {
		this.homeworks = homeworks;
	}

}
