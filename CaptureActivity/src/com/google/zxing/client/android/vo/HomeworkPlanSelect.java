/**   
 * @Title: HomeworkPlanSelect.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import com.google.zxing.client.android.utils.SoapHelper;
import com.google.zxing.client.android.vo.PMSelect.Fields;

/**
 * @Desctiption 作业计划选择值实体
 * @author 汪渝栋
 * @date 2013-10-12 下午3:30:58
 */
public class HomeworkPlanSelect extends Base implements Serializable,
		ParserInterface {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 374863460605771289L;

	private String jpnum;

	private String description;

	@Override
	public String toString() {
		return "HomeworkPlanSelect [jpnum=" + jpnum + ", description="
				+ description + "]";
	}

	public String getJpnum() {
		return jpnum;
	}

	public void setJpnum(String jpnum) {
		this.jpnum = jpnum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	class Fields {
		public static final String JPNUM = "JPNUM";
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
		SoapObject hpData = result;
		HomeworkPlanSelect hpSelect = null;
		HomeworkPlanSelectList lineList = new HomeworkPlanSelectList();
		ArrayList<HomeworkPlanSelect> hps = lineList.getHomeworks();
		int resultCount = hpData.getPropertyCount();
		for (int i = 0; i < resultCount; i++) {
			hpSelect = new HomeworkPlanSelect();
			SoapObject subObj = (SoapObject) result.getProperty(i);
			hpSelect.setJpnum(SoapHelper.getSoapObjectString(subObj,
					Fields.JPNUM));
			hpSelect.setDescription(SoapHelper.getSoapObjectString(subObj,
					Fields.DESCRIPTION));
			hps.add(hpSelect);
		}
		return lineList;
	}

}
