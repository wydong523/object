package com.google.zxing.client.android.app;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * 应用安全管理：用于对用户安全相关的操作
 * 
 * @author 汪渝栋
 * 
 */
public class SecurityManager {

	private static SecurityManager securityManager = null;

	private SecurityManager() {
	}

	public static synchronized SecurityManager getSecurityManager() {
		if (securityManager == null) {
			securityManager = new SecurityManager();
		}
		return securityManager;
	}

	/**
	 * 获取当前手机号码
	 * 
	 * @return
	 */
	public String getCurPhoneNum(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String phoneNum = tm.getLine1Number();
		if (phoneNum == null) {
			return "";
		}
		return phoneNum;
	}

	/**
	 * 获取当前设备唯一标识(注:非手机设备和少数厂商定制系统无法获得)
	 * 
	 * @return
	 */
	public String getCurDeviceId(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String deviceId = tm.getDeviceId();
		if (deviceId == null) {
			return "";
		}
		return deviceId;
	}

	public String getSimNum(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getSimSerialNumber();
	}

	/**
	 * 校验用户电话号码
	 * 
	 * @param phoneNum
	 * @return
	 */
	public boolean checkPhoneNum(String phoneNum) {
		return false;
	}
}
