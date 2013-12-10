/**   
 * @Title: ClassstructureSelect.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import com.google.zxing.client.android.utils.SoapHelper;
import com.google.zxing.client.android.vo.HomeworkPlanSelect.Fields;

/**
 * @Desctiption 分类选择值实体
 * @author 汪渝栋
 * @date 2013-10-12 下午4:17:05
 */
public class ClassstructureSelect extends Base implements ParserInterface,
		Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 5872981872472006757L;
	private String classstructureid;
	private String classificationid;
	private String description;

	public String getClassificationid() {
		return classificationid;
	}

	public void setClassificationid(String classificationid) {
		this.classificationid = classificationid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ClassstructureSelect [classstructureid=" + classstructureid
				+ ", classificationid=" + classificationid + ", description="
				+ description + "]";
	}

	class Fields {

		public static final String CLASSSTRUCTUREID = "CLASSSTRUCTUREID";
		public static final String CLASSIFICATIONID = "CLASSIFICATIONID";
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
		SoapObject csData = result;
		ClassstructureSelect csSelect = null;
		ClassstructureSelectList lineList = new ClassstructureSelectList();
		ArrayList<ClassstructureSelect> classs = lineList.getClasss();
		int resultCount = csData.getPropertyCount();
		for (int i = 0; i < resultCount; i++) {
			csSelect = new ClassstructureSelect();
			SoapObject subObj = (SoapObject) result.getProperty(i);
			csSelect.setClassstructureid(SoapHelper.getSoapObjectString(subObj,
					Fields.CLASSSTRUCTUREID));
			csSelect.setClassificationid(SoapHelper.getSoapObjectString(subObj,
					Fields.CLASSIFICATIONID));
			csSelect.setDescription(SoapHelper.getSoapObjectString(subObj,
					Fields.DESCRIPTION));
			classs.add(csSelect);
		}
		return lineList;
	}

	/**
	 * @return the classstructureid
	 */
	public String getClassstructureid() {
		return classstructureid;
	}

	/**
	 * @param classstructureid
	 *            the classstructureid to set
	 */
	public void setClassstructureid(String classstructureid) {
		this.classstructureid = classstructureid;
	}

}
