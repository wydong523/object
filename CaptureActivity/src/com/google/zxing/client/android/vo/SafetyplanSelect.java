/**   
 * @Title: SafetyplanSelect.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import com.google.zxing.client.android.utils.SoapHelper;

/**
 * @Desctiption 安全计划选择值实体
 * @author 汪渝栋
 * @date 2013-10-10 上午10:45:00
 */
public class SafetyplanSelect extends Base implements Serializable,
		ParserInterface {
	/**
	 * 安全计划
	 */
	private String safetyplanid;

	/**
	 * 描述
	 */
	private String description;

	public String getSafetyplanid() {
		return safetyplanid;
	}

	public void setSafetyplanid(String safetyplanid) {
		this.safetyplanid = safetyplanid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "SafetyplanSelect [safetyplanid=" + safetyplanid
				+ ", description=" + description + "]";
	}

	class Fields {
		public static final String SAFETYPLANID = "SAFETYPLANID";
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
		SoapObject locationsData = result;
		SafetyplanSelect safetyplan = null;
		SafetyplanSelectList lineList = new SafetyplanSelectList();
		ArrayList<SafetyplanSelect> safetyplans = lineList.getSafetyplans();
		int resultCount = locationsData.getPropertyCount();
		for (int i = 0; i < resultCount; i++) {
			safetyplan = new SafetyplanSelect();
			SoapObject subObj = (SoapObject) result.getProperty(i);
			safetyplan.setSafetyplanid(SoapHelper.getSoapObjectString(subObj,
					Fields.SAFETYPLANID));
			safetyplan.setDescription(SoapHelper.getSoapObjectString(subObj,
					Fields.DESCRIPTION));
			safetyplans.add(safetyplan);
		}
		return lineList;
	}
}
