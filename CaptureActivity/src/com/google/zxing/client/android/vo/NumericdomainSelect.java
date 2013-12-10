/**   
 * @Title: NumericdomainSelect.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import com.google.zxing.client.android.utils.SoapHelper;

/**
 * @Desctiption 优先级选择值实体
 * @author 汪渝栋
 * @date 2013-10-10 上午10:51:55
 */
public class NumericdomainSelect extends Base implements Serializable,
		ParserInterface {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -4841988828570813798L;

	/**
	 * 域
	 */
	private String domainid;

	/**
	 * 描述
	 */
	private String description;

	public String getDomainid() {
		return domainid;
	}

	public void setDomainid(String domainid) {
		this.domainid = domainid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "NumericdomainSelect [domainid=" + domainid + ", description="
				+ description + "]";
	}

	class Fields {
		public static final String DOMAINID = "DOMAINID";
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
		SoapObject numericdomainData = result;
		NumericdomainSelect numericdomainSelect = null;
		NumericdomainSelectList lineList = new NumericdomainSelectList();
		ArrayList<NumericdomainSelect> numericdomains = lineList
				.getNumericdomains();
		int resultCount = numericdomainData.getPropertyCount();
		for (int i = 0; i < resultCount; i++) {
			numericdomainSelect = new NumericdomainSelect();
			SoapObject subObj = (SoapObject) result.getProperty(i);
			numericdomainSelect.setDomainid(SoapHelper.getSoapObjectString(
					subObj, Fields.DOMAINID));
			numericdomainSelect.setDescription(SoapHelper.getSoapObjectString(
					subObj, Fields.DESCRIPTION));
			numericdomains.add(numericdomainSelect);
		}
		return lineList;
	}
}
