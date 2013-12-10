/**   
 * @Title: Avgcost.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;

import org.ksoap2.serialization.SoapObject;

import com.google.zxing.client.android.utils.SoapHelper;

/**
 * @Desctiption 单位成本实体
 * @author 汪渝栋
 * @date 2013-10-30 下午5:22:18
 */
public class Avgcost extends Base implements Serializable, ParserInterface {
	/**
	 * 库存编码 库房 项目集 站点 标准成本 平均成本 最大成本 组织
	 */
	private String itemnum;
	private String location;
	private String itemsetid;
	private String siteid;
	private String stdcost;
	private String avgcost;
	private String lastcost;
	private String orgid;

	public String getItemnum() {
		return itemnum;
	}

	public void setItemnum(String itemnum) {
		this.itemnum = itemnum;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getItemsetid() {
		return itemsetid;
	}

	public void setItemsetid(String itemsetid) {
		this.itemsetid = itemsetid;
	}

	public String getSiteid() {
		return siteid;
	}

	public void setSiteid(String siteid) {
		this.siteid = siteid;
	}

	public String getStdcost() {
		return stdcost;
	}

	public void setStdcost(String stdcost) {
		this.stdcost = stdcost;
	}

	public String getAvgcost() {
		return avgcost;
	}

	public void setAvgcost(String avgcost) {
		this.avgcost = avgcost;
	}

	public String getLastcost() {
		return lastcost;
	}

	public void setLastcost(String lastcost) {
		this.lastcost = lastcost;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	@Override
	public String toString() {
		return "Avgcost [itemnum=" + itemnum + ", location=" + location
				+ ", itemsetid=" + itemsetid + ", siteid=" + siteid
				+ ", stdcost=" + stdcost + ", avgcost=" + avgcost
				+ ", lastcost=" + lastcost + ", orgid=" + orgid + "]";
	}

	class Fields {
		public static final String ITEMNUM = "ITEMNUM";
		public static final String LOCATION = "LOCATION";
		public static final String ITEMSETID = "ITEMSETID";
		public static final String SITEID = "SITEID";
		public static final String STDCOST = "STDCOST";
		public static final String AVGCOST = "AVGCOST";
		public static final String LASTCOST = "LASTCOST";
		public static final String ORGID = "ORGID";
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
		SoapObject locationsData = result;
		SoapObject subObj = (SoapObject) result.getProperty(0);
		Avgcost avgcost = new Avgcost();
		avgcost.setItemnum(SoapHelper.getSoapObjectString(subObj,
				Fields.ITEMNUM));
		avgcost.setLocation(SoapHelper.getSoapObjectString(subObj,
				Fields.LOCATION));
		avgcost.setItemsetid(SoapHelper.getSoapObjectString(subObj,
				Fields.ITEMSETID));
		avgcost.setSiteid(SoapHelper.getSoapObjectString(subObj, Fields.SITEID));
		avgcost.setStdcost(SoapHelper.getSoapObjectString(subObj,
				Fields.STDCOST));
		avgcost.setAvgcost(SoapHelper.getSoapObjectString(subObj,
				Fields.AVGCOST));
		avgcost.setLastcost(SoapHelper.getSoapObjectString(subObj,
				Fields.LASTCOST));
		avgcost.setOrgid(SoapHelper.getSoapObjectString(subObj, Fields.ORGID));
		return avgcost;
	}

}
