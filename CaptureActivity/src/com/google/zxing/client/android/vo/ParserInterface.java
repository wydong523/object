/**   
 * @Title: SelectInterface.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;

import org.ksoap2.serialization.SoapObject;

/**
 * @Desctiption
 * @author ���嶰
 * @date 2013-10-10 ����2:18:49
 */
public interface ParserInterface {
	Serializable parse(SoapObject result);
	
}
