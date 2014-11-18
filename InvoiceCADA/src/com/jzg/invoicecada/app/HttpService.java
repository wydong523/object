/**
 * Project Name:InvoiceCADA
 * File Name:HttpService.java
 * Package Name:com.example.invoicecada
 * Date:2014-4-15下午12:08:56
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.jzg.invoicecada.app;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

import com.jzg.invoicecada.utils.HttpClientUtils;

/**
 * ClassName:HttpService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-15 下午12:08:56 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class HttpService
{
	private static final String ENCODING = "utf-8";

	public static String login(CharSequence name, CharSequence pass, String sign)
	{
		String result = null;
		// String url = "http://apptest.guchewang.com/Login.ashx";
		String url = "http://appservice.guchewang.com/Login.ashx";

		String path = url + "?username=" + name.toString() + "&pass="
				+ pass.toString() + "&sign=" + sign;
		System.out.println("path is :" + path);
		try
		{
			result = HttpClientUtils.sendHttpClientGetRequest(path);
			return result;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	public static boolean pictureUpload(String img, String lname)
	{
		// String path = "http://apptest.guchewang.com/uploadinvoicepic.ashx";
		String path = "http://appservice.guchewang.com/uploadinvoicepic.ashx";

		Map<String, String> params = new HashMap<String, String>();// 定义一个保存key-value的Map用于保存需要传输的数据
		params.put("imgs", img);// 保存数据到map对象
		params.put("lname", lname);// 用户标示
		try
		{
			return HttpClientUtils.sendHttpClientPOSTRequest(path, params,
					ENCODING);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

}
