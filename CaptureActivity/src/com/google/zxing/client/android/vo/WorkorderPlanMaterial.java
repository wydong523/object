/**   
 * @Title: WorkorderPlanMaterial.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import com.google.zxing.client.android.utils.SoapHelper;
import com.google.zxing.client.android.vo.WorkorderPlanTask.Fields;

/**
 * @Desctiption 物料实体对象
 * @author 汪渝栋
 * @date 2013-10-24 下午2:15:31
 */
public class WorkorderPlanMaterial extends Base implements Serializable,
		ParserInterface {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -6881527766751438246L;
	// id
	// 项目
	// 行类型
	// 数量
	// 单位
	// 单位成本
	// 行成本
	// 库房
	// 库房地点
	// 预留类型
	// 要求时间
	// 描述
	private String wpitemid;

	private String itemnum;

	private String linetype;

	private String itemqty;

	private String orderunit;

	private String unitcost;

	private String linecost;

	private String location;

	private String storelocsite;

	private String restype;

	private String requiredate;

	private String description;

	@Override
	public String toString() {
		return "WorkorderPlanMaterial [wpitemid=" + wpitemid + ", itemnum="
				+ itemnum + ", linetype=" + linetype + ", itemqty=" + itemqty
				+ ", orderunit=" + orderunit + ", unitcost=" + unitcost
				+ ", linecost=" + linecost + ", location=" + location
				+ ", storelocsite=" + storelocsite + ", restype=" + restype
				+ ", requiredate=" + requiredate + ", description="
				+ description + "]";
	}

	public String getItemnum() {
		return itemnum;
	}

	public void setItemnum(String itemnum) {
		this.itemnum = itemnum;
	}

	public String getLinetype() {
		return linetype;
	}

	public void setLinetype(String linetype) {
		this.linetype = linetype;
	}

	public String getItemqty() {
		return itemqty;
	}

	public void setItemqty(String itemqty) {
		this.itemqty = itemqty;
	}

	public String getOrderunit() {
		return orderunit;
	}

	public void setOrderunit(String orderunit) {
		this.orderunit = orderunit;
	}

	public String getUnitcost() {
		return unitcost;
	}

	public void setUnitcost(String unitcost) {
		this.unitcost = unitcost;
	}

	public String getLinecost() {
		return linecost;
	}

	public void setLinecost(String linecost) {
		this.linecost = linecost;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStorelocsite() {
		return storelocsite;
	}

	public void setStorelocsite(String storelocsite) {
		this.storelocsite = storelocsite;
	}

	public String getRestype() {
		return restype;
	}

	public void setRestype(String restype) {
		this.restype = restype;
	}

	public String getRequiredate() {
		return requiredate;
	}

	public void setRequiredate(String requiredate) {
		this.requiredate = requiredate;
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
		SoapObject materialsData = result;
		WorkorderPlanMaterial material = null;
		WorkorderPlanMaterialList lineList = new WorkorderPlanMaterialList();
		ArrayList<WorkorderPlanMaterial> materials = lineList.getMaterials();
		int resultCount = materialsData.getPropertyCount();
		for (int i = 0; i < resultCount; i++) {
			material = new WorkorderPlanMaterial();
			SoapObject subObj = (SoapObject) result.getProperty(i);
			material.setWpitemid(SoapHelper.getSoapObjectString(subObj,
					Fields.WPITEMID));
			material.setItemnum(SoapHelper.getSoapObjectString(subObj,
					Fields.ITEMNUM));
			material.setLinetype(SoapHelper.getSoapObjectString(subObj,
					Fields.LINETYPE));
			material.setItemqty(SoapHelper.getSoapObjectString(subObj,
					Fields.ITEMQTY));
			material.setOrderunit(SoapHelper.getSoapObjectString(subObj,
					Fields.ORDERUNIT));
			material.setUnitcost(SoapHelper.getSoapObjectString(subObj,
					Fields.UNITCOST));
			material.setLinecost(SoapHelper.getSoapObjectString(subObj,
					Fields.LINECOST));
			material.setLocation(SoapHelper.getSoapObjectString(subObj,
					Fields.LOCATION));
			material.setStorelocsite(SoapHelper.getSoapObjectString(subObj,
					Fields.STORELOCSITE));
			material.setRestype(SoapHelper.getSoapObjectString(subObj,
					Fields.RESTYPE));
			material.setRequiredate(SoapHelper.getSoapObjectString(subObj,
					Fields.REQUIREDATE));
			material.setDescription(SoapHelper.getSoapObjectString(subObj,
					Fields.DESCRIPTION));
			materials.add(material);
		}
		return lineList;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the wpitemid
	 */
	public String getWpitemid() {
		return wpitemid;
	}

	/**
	 * @param wpitemid
	 *            the wpitemid to set
	 */
	public void setWpitemid(String wpitemid) {
		this.wpitemid = wpitemid;
	}

	class Fields {
		public static final String WPITEMID = "WPITEMID";
		public static final String ITEMNUM = "ITEMNUM";
		public static final String LINETYPE = "LINETYPE";
		public static final String ITEMQTY = "ITEMQTY";
		public static final String ORDERUNIT = "ORDERUNIT";
		public static final String UNITCOST = "UNITCOST";
		public static final String LINECOST = "LINECOST";
		public static final String LOCATION = "LOCATION";
		public static final String STORELOCSITE = "STORELOCSITE";
		public static final String RESTYPE = "RESTYPE";
		public static final String REQUIREDATE = "REQUIREDATE";
		public static final String DESCRIPTION = "DESCRIPTION";
	}

}
