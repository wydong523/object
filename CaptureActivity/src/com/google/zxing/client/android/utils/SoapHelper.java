package com.google.zxing.client.android.utils;

import org.ksoap2.serialization.SoapObject;

import com.google.zxing.client.android.vo.Base;

/**
 * Soap�����������Ҫ�Ĺ�������
 * 
 * @author ���嶰
 * 
 */
public final class SoapHelper {
	public static int getSoapObjectInt(SoapObject soapObject, String name) {
		String value = soapObject.getProperty(name).toString();
		if (value != null && !value.equals("anyType{}")) {
			return Integer.valueOf(value).intValue();
		}
		return -1;
	}

	/**
	 * ��ȡ����ֵ
	 * 
	 * @param soapObject
	 *            webservice���ݶ���
	 * @param name
	 *            ����name
	 * @return ����value
	 */
	public static String getSoapObjectString(SoapObject soapObject, String name) {
		String result = "";
		Object value = soapObject.getProperty(name);
		if (value != null) {
			String va = value.toString();
			if (!va.equals(Base.ANY_TYPE)) {
				return result = va;
			}
		}
		return result;
	}

	public static float getSoapObjectFloat(SoapObject soapObject, String name) {
		return Float.valueOf(soapObject.getProperty(name).toString())
				.floatValue();
	}
}
