/**   
 * @Title: ProjectSelect.java 
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
 * @Desctiption 项目选择值实体
 * @author 汪渝栋
 * @date 2013-10-14 上午11:47:19
 */
public class ProjectSelect extends Base implements Serializable,
		ParserInterface {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 8133747538348364518L;

	private String itemnum;

	private String description;

	public String getItemnum() {
		return itemnum;
	}

	public void setItemnum(String itemnum) {
		this.itemnum = itemnum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ProjectSelect [itemnum=" + itemnum + ", description="
				+ description + "]";
	}

	class Fields {
		public static final String ITEMNUM = "ITEMNUM";
		public static final String DESCRIPTION = "DESCRIPTION";
	}

	/*
	 * (非 Javadoc) <p>Title: parse</p> <p>Description: </p>
	 * 
	 * @param result
	 * 
	 * @return
	 * 
	 * @see com.google.zxing.client.android.vo.SelectInterface#parse(org.ksoap2.
	 * serialization.SoapObject)
	 */
	@Override
	public Serializable parse(SoapObject result) {
		SoapObject projectsData = result;
		ProjectSelect projectsSelect = null;
		ProjectSelectList lineList = new ProjectSelectList();
		ArrayList<ProjectSelect> projects = lineList.getProjects();
		int resultCount = projectsData.getPropertyCount();
		for (int i = 0; i < resultCount; i++) {
			projectsSelect = new ProjectSelect();
			SoapObject subObj = (SoapObject) result.getProperty(i);
			projectsSelect.setItemnum(SoapHelper.getSoapObjectString(subObj,
					Fields.ITEMNUM));
			projectsSelect.setDescription(SoapHelper.getSoapObjectString(
					subObj, Fields.DESCRIPTION));
			projects.add(projectsSelect);
		}
		return lineList;
	}

}
