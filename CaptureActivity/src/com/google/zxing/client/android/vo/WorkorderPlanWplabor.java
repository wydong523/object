/**   
 * @Title: WorkorderPlanW.java 
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
 * @Desctiption 工单计划人工实体对象
 * @author 汪渝栋
 * @date 2013-10-23 下午4:21:54
 */
public class WorkorderPlanWplabor extends Base implements Serializable,
		ParserInterface {
	// 员工
	// 数量
	// 常规小时
	// 唯一标识

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -2292892742439541522L;

	private String laborcode;

	private String quantity;

	private String laborhrs;

	private String wplaborid;

	@Override
	public String toString() {
		return "WorkorderPlanWplabor [laborcode=" + laborcode + ", quantity="
				+ quantity + ", laborhrs=" + laborhrs + ", wplaborid="
				+ wplaborid + "]";
	}

	public String getLaborcode() {
		return laborcode;
	}

	public void setLaborcode(String laborcode) {
		this.laborcode = laborcode;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getLaborhrs() {
		return laborhrs;
	}

	public void setLaborhrs(String laborhrs) {
		this.laborhrs = laborhrs;
	}

	class Fields {
		public static final String LABORCODE = "LABORCODE";
		public static final String QUANTITY = "QUANTITY";
		public static final String LABORHRS = "LABORHRS";
		public static final String WPLABORID = "WPLABORID";
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
		SoapObject wplaborData = result;
		WorkorderPlanWplabor wplabor = null;
		WorkorderPlanWplaborList lineList = new WorkorderPlanWplaborList();
		ArrayList<WorkorderPlanWplabor> wplabors = lineList.getWplabors();
		int resultCount = wplaborData.getPropertyCount();
		for (int i = 0; i < resultCount; i++) {
			wplabor = new WorkorderPlanWplabor();
			SoapObject subObj = (SoapObject) result.getProperty(i);
			wplabor.setLaborcode(SoapHelper.getSoapObjectString(subObj,
					Fields.LABORCODE));
			wplabor.setQuantity(SoapHelper.getSoapObjectString(subObj,
					Fields.QUANTITY));
			wplabor.setLaborhrs(SoapHelper.getSoapObjectString(subObj,
					Fields.LABORHRS));
			wplabor.setWplaborid(SoapHelper.getSoapObjectString(subObj,
					Fields.WPLABORID));
			wplabors.add(wplabor);
		}
		return lineList;
	}

	/**
	 * @return the wplaborid
	 */
	public String getWplaborid() {
		return wplaborid;
	}

	/**
	 * @param wplaborid
	 *            the wplaborid to set
	 */
	public void setWplaborid(String wplaborid) {
		this.wplaborid = wplaborid;
	}

}
