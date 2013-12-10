package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * �û�Ӧ��Ȩ���б�
 * 
 * @author ���嶰
 * 
 */
public class AppPermissionsList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7717204155718302506L;

	private int pagesize;

	private int appPermissionsCount;

	private ArrayList<AppPermissions> appPermissions = new ArrayList<AppPermissions>();

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getAppPermissionsCount() {
		return appPermissionsCount;
	}

	public void setAppPermissionsCount(int appPermissionsCount) {
		this.appPermissionsCount = appPermissionsCount;
	}

	/**
	 * @return the appPermissions
	 */
	public ArrayList<AppPermissions> getAppPermissions() {
		return appPermissions;
	}

	/**
	 * @param appPermissions
	 *            the appPermissions to set
	 */
	public void setAppPermissions(ArrayList<AppPermissions> appPermissions) {
		this.appPermissions = appPermissions;
	}

}
