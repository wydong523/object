/**   
 * @Title: PMSelect.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import com.google.zxing.client.android.utils.SoapHelper;
import com.google.zxing.client.android.vo.PersonSelect.Fields;

/**
 * @Desctiption pm选择值实体
 * @author 汪渝栋
 * @date 2013-10-12 上午10:31:54
 */
public class PMSelect extends Base implements Serializable, ParserInterface {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 7420076382894138137L;

	private String pmnum;

	private String description;

	public String getPmnum() {
		return pmnum;
	}

	public void setPmnum(String pmnum) {
		this.pmnum = pmnum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "PMSelect [pmnum=" + pmnum + ", description=" + description
				+ "]";
	}

	class Fields {
		public static final String PMNUM = "PMNUM";
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
		SoapObject pmData = result;
		PMSelect pmSelect = null;
		PMSelectList lineList = new PMSelectList();
		ArrayList<PMSelect> pms = lineList.getPms();
		int resultCount = pmData.getPropertyCount();
		for (int i = 0; i < resultCount; i++) {
			pmSelect = new PMSelect();
			SoapObject subObj = (SoapObject) result.getProperty(i);
			pmSelect.setPmnum(SoapHelper.getSoapObjectString(subObj,
					Fields.PMNUM));
			pmSelect.setDescription(SoapHelper.getSoapObjectString(subObj,
					Fields.DESCRIPTION));
			pms.add(pmSelect);
		}
		return lineList;
	}

}
