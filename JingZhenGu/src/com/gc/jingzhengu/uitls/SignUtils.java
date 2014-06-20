/**
 * Project Name:JingZhenGu
 * File Name:SignUtils.java
 * Package Name:com.gc.jingzhengu.uitls
 * Date:2014-6-12下午1:47:36
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.uitls;

/**
 * ClassName:SignUtils <br/>
 * Function: 生成对应的私钥. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-12 下午1:47:36 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class SignUtils
{
	private static final String privateNum = "2CB3147B-D93C-964B-47AE-EEE448C84E3C";

	public static String signForMakeList()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?");
		buffer.append(privateNum);
		System.out.println("signForMakeList  String is :" + buffer.toString());

		return MD5Utils.MD5(buffer.toString());
	}

	public static String signForModelList(String makeid)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?MakeId=");
		buffer.append(makeid);
		buffer.append(privateNum);
		System.out.println("signForModelList  String is :" + buffer.toString());

		return MD5Utils.MD5(buffer.toString());
	}

	public static String signForStylelList(String modelid)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?ModelId=");
		buffer.append(modelid);
		buffer.append(privateNum);
		System.out
				.println("signForStylelList  String is :" + buffer.toString());

		return MD5Utils.MD5(buffer.toString());
	}

	public static String signForCityList(String procinceid)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?ProvId=");
		buffer.append(procinceid);
		buffer.append(privateNum);
		System.out.println("signForCityList  String is :" + buffer.toString());

		return MD5Utils.MD5(buffer.toString());
	}

}
