package com.google.zxing.client.android.vo;

/**
 * ʵ�������
 * 
 * @author ���嶰
 * 
 */
public abstract class Base {
	public static final String ANY_TYPE = "anyType{}";

	public static final String QUOTATION_MARKS = "";
	
	protected String cacheKey;

	public String getCacheKey() {
		return cacheKey;
	}

	public void setCacheKey(String cacheKey) {
		this.cacheKey = cacheKey;
	}
}
