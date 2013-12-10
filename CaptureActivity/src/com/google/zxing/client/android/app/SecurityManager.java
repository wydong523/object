package com.google.zxing.client.android.app;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Ӧ�ð�ȫ�������ڶ��û���ȫ��صĲ���
 * 
 * @author ���嶰
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
	 * ��ȡ��ǰ�ֻ�����
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
	 * ��ȡ��ǰ�豸Ψһ��ʶ(ע:���ֻ��豸���������̶���ϵͳ�޷����)
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
	 * У���û��绰����
	 * 
	 * @param phoneNum
	 * @return
	 */
	public boolean checkPhoneNum(String phoneNum) {
		return false;
	}
}
