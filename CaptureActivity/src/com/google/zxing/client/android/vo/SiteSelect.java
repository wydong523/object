/**   
 * @Title: SiteSelect.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import com.google.zxing.client.android.utils.SoapHelper;
import com.google.zxing.client.android.vo.MeasureunitSelect.Fields;

/**
 * @Desctiption 地点选择值实体
 * @author 汪渝栋
 * @date 2013-10-30 下午3:42:08
 */
public class SiteSelect extends Base implements ParserInterface, Serializable {

	/**
	 * 表：site 字段： siteid description
	 */
	private String siteid;
	private String description;

	@Override
	public String toString() {
		return "SiteSelect [siteid=" + siteid + ", description=" + description
				+ "]";
	}

	public String getSiteid() {
		return siteid;
	}

	public void setSiteid(String siteid) {
		this.siteid = siteid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	class Fields {
		public static final String SITEID = "SITEID";
		public static final String DESCRIPTION = "DESCRIPTION";
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
		SoapObject sitesData = result;
		SiteSelect site = null;
		SiteSelectList lineList = new SiteSelectList();
		ArrayList<SiteSelect> sites = lineList.getSites();
		int resultCount = sitesData.getPropertyCount();
		for (int i = 0; i < resultCount; i++) {
			site = new SiteSelect();
			SoapObject subObj = (SoapObject) result.getProperty(i);
			site.setSiteid(SoapHelper
					.getSoapObjectString(subObj, Fields.SITEID));
			site.setDescription(SoapHelper.getSoapObjectString(subObj,
					Fields.DESCRIPTION));
			sites.add(site);
		}
		return lineList;
	}

}
