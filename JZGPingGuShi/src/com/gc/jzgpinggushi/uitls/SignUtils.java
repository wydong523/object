/**
 * Project Name:JZGPingGuShi
 * File Name:SignUtils.java
 * Package Name:com.gc.jzgpinggushi.uitls
 * Date:2014-9-1上午10:40:27
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.uitls;

/**
 * ClassName:SignUtils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-1 上午10:40:27 <br/>
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

	public static String signForQueryCarList(int styleId, int pageCount,
			String orderItem, String orderType, int pgsid)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?StyleId=");
		buffer.append(styleId);
		buffer.append("&PageCount=");
		buffer.append(pageCount);
		buffer.append("&OrderItem=");
		buffer.append(orderItem);
		buffer.append("&OrderType=");
		buffer.append(orderType);
		buffer.append("&pgsid=");
		buffer.append(pgsid);
		buffer.append(privateNum);

		return MD5Utils.MD5(buffer.toString());
	}

	public static String signForLogin(String username, String password)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?username=");
		buffer.append(username);
		buffer.append("&password=");
		buffer.append(password);
		buffer.append(privateNum);
		return MD5Utils.MD5(buffer.toString());
	}

	public static String signForOfferList(int pgsid, int pageCount)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?pgsid=");
		buffer.append(pgsid);
		buffer.append("&PageCount=");
		buffer.append(pageCount);
		buffer.append(privateNum);
		return MD5Utils.MD5(buffer.toString());
	}

	public static String signForOfferSuccessList(int pgsid, int pageCount)
	{
		return signForOfferList(pgsid, pageCount);
	}

	public static String signForNewCarList(int pageCount, int pgsid)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?PageCount=");
		buffer.append(pageCount);
		buffer.append("&pgsid=");
		buffer.append(pgsid);
		buffer.append(privateNum);
		return MD5Utils.MD5(buffer.toString());
	}

	public static String signForQueryByCarsourceid(int carsourceid)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?carsourceid=");
		buffer.append(carsourceid);
		buffer.append(privateNum);
		return MD5Utils.MD5(buffer.toString());
	}

	public static String signForUploadPrice(int pgsid, String myBidPrice,
			int carsourceid)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?pgsid=");
		buffer.append(pgsid);
		buffer.append("&myBidPrice=");
		buffer.append(myBidPrice);
		buffer.append("&carsourceid=");
		buffer.append(carsourceid);
		buffer.append(privateNum);
		return MD5Utils.MD5(buffer.toString());
	}

}
