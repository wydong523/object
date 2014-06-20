/**
 * Project Name:InvoiceCADA
 * File Name:MD5Utils.java
 * Package Name:com.example.invoicecada
 * Date:2014-4-15上午11:44:46
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.uitls;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * ClassName:MD5Utils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-15 上午11:44:46 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class MD5Utils
{

	public final static String MD5(String s)
	{
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try
		{
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++)
			{
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str).toUpperCase();
		} catch (Exception e)
		{
			return null;
		}
	}

	/**
	 * MD5加密 MD5Encrypt: <br/>
	 * 
	 * @author wang
	 * @param inStr
	 * @return
	 * @since JDK 1.6
	 */
	public static String MD5Encrypt(String inStr)
	{

		MessageDigest md = null;
		String outStr = null;
		try
		{

			md = MessageDigest.getInstance("MD5"); // 可以选中其他的算法如SHA
			byte[] digest = md.digest(inStr.getBytes());
			// 返回的是byet[]，要转化为String存储比较方便
			outStr = bytetoString(digest);
		} catch (NoSuchAlgorithmException nsae)
		{
			nsae.printStackTrace();
		}
		return outStr;
	}

	public static String bytetoString(byte[] digest)
	{

		String str = "";
		String tempStr = "";
		for (int i = 1; i < digest.length; i++)
		{
			tempStr = (Integer.toHexString(digest[i] & 0xff));
			if (tempStr.length() == 1)
			{
				str = str + "0" + tempStr;
			} else
			{
				str = str + tempStr;
			}
		}
		return str.toLowerCase();

	}
}
