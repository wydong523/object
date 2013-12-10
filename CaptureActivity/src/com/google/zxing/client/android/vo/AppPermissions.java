package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;
import org.ksoap2.serialization.SoapObject;

import com.google.zxing.client.android.app.AppContext;
import com.google.zxing.client.android.utils.SoapHelper;

/**
 * 应用程序权限实体
 * 
 * @author 汪渝栋
 * 
 */
public class AppPermissions extends Base implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5355446332835421039L;

	private String app;

	private String description;

	private String apptype;

	private String restrictions;

	private String orderby;

	private String originalapp;

	private String custapptype;

	private String maintbname;

	private int maxappsid;

	private String rowstamp;

	private String reportobject;

	private int ismobile;

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getApptype() {
		return apptype;
	}

	public void setApptype(String apptype) {
		this.apptype = apptype;
	}

	public String getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(String restrictions) {
		this.restrictions = restrictions;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getOriginalapp() {
		return originalapp;
	}

	public void setOriginalapp(String originalapp) {
		this.originalapp = originalapp;
	}

	public String getCustapptype() {
		return custapptype;
	}

	public void setCustapptype(String custapptype) {
		this.custapptype = custapptype;
	}

	public String getMaintbname() {
		return maintbname;
	}

	public void setMaintbname(String maintbname) {
		this.maintbname = maintbname;
	}

	public int getMaxappsid() {
		return maxappsid;
	}

	public void setMaxappsid(int maxappsid) {
		this.maxappsid = maxappsid;
	}

	public String getRowstamp() {
		return rowstamp;
	}

	public void setRowstamp(String rowstamp) {
		this.rowstamp = rowstamp;
	}

	public String getReportobject() {
		return reportobject;
	}

	public void setReportobject(String reportobject) {
		this.reportobject = reportobject;
	}

	public int getIsmobile() {
		return ismobile;
	}

	public void setIsmobile(int ismobile) {
		this.ismobile = ismobile;
	}

	public AppPermissionsList parse(SoapObject result) {
		AppPermissionsList apList = new AppPermissionsList();
		ArrayList<AppPermissions> aps = apList.getAppPermissions();
		AppPermissions ap = null;
		int objCount = result.getPropertyCount();
		System.out
		.println("用户appName个数为:" + objCount);
		for (int i = 0; i < objCount; i++) {
			SoapObject subObj = (SoapObject) result.getProperty(i);
			// 组装webservice返回数据
			ap = new AppPermissions();
			ap.setApp(SoapHelper.getSoapObjectString(subObj, Fields.APP));
			ap.setDescription(SoapHelper.getSoapObjectString(subObj,
					Fields.DESCRIPTION));
			
			ap.setApptype(SoapHelper
					.getSoapObjectString(subObj, Fields.APPTYPE));
			ap.setRestrictions(SoapHelper.getSoapObjectString(subObj,
					Fields.RESTRICTIONS));
			ap.setOrderby(SoapHelper
					.getSoapObjectString(subObj, Fields.ORDERBY));
			ap.setOriginalapp(SoapHelper.getSoapObjectString(subObj,
					Fields.ORIGINALAPP));
			ap.setCustapptype(SoapHelper.getSoapObjectString(subObj,
					Fields.CUSTAPPTYPE));
			ap.setMaintbname(SoapHelper.getSoapObjectString(subObj,
					Fields.MAINTBNAME));
			ap.setMaxappsid(SoapHelper.getSoapObjectInt(subObj,
					Fields.MAXAPPSID));
//			ap.setRowstamp(SoapHelper.getSoapObjectString(subObj,
//					Fields.ROWSTAMP));
//			ap.setReportobject(SoapHelper.getSoapObjectString(subObj,
//					Fields.REPORTOBJECT));
			ap.setIsmobile(SoapHelper.getSoapObjectInt(subObj, Fields.ISMOBILE));
			aps.add(ap);
		}
		return apList;
	}

	class Fields {
		public static final String APP = "APP";
		public static final String DESCRIPTION = "DESCRIPTION";
		public static final String APPTYPE = "APPTYPE";
		public static final String RESTRICTIONS = "RESTRICTIONS";
		public static final String ORDERBY = "ORDERBY";
		public static final String ORIGINALAPP = "ORIGINALAPP";
		public static final String CUSTAPPTYPE = "CUSTAPPTYPE";
		public static final String MAINTBNAME = "MAINTBNAME";
		public static final String MAXAPPSID = "MAXAPPSID";
		public static final String ROWSTAMP = "ROWSTAMP";
		public static final String REPORTOBJECT = "REPORTOBJECT";
		public static final String ISMOBILE = "ISMOBILE";
	}
}
