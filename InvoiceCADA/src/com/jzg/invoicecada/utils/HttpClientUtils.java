/**
 * Project Name:InvoiceCADA
 * File Name:HttpClientUtils.java
 * Package Name:com.example.invoicecada
 * Date:2014-4-15下午12:14:12
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.jzg.invoicecada.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.util.Log;

/**
 * ClassName:HttpClientUtils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-15 下午12:14:12 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class HttpClientUtils
{

	public static String sendHttpClientGetRequest(String path)
	{
		HttpGet httpRequest = new HttpGet(path);
		// 取得HttpClient对象
		HttpClient httpclient = new DefaultHttpClient();
		// 请求HttpClient，取得HttpResponse
		HttpResponse httpResponse;
		String strResult = null;
		try
		{
			httpResponse = httpclient.execute(httpRequest);
			// 请求成功
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			{
				// 取得返回的字符串
				strResult = EntityUtils.toString(httpResponse.getEntity());
				System.out.println("login result is :" + strResult);
				return strResult;
			}

		} catch (ClientProtocolException e)
		{
			e.printStackTrace();

		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return strResult;
	}

	/**
	 * 采用HttpClient发送POST请求
	 * 
	 * @param path
	 *            请求路径
	 * @param params
	 *            请求参数
	 * @throws Exception
	 */
	public static boolean sendHttpClientPOSTRequest(String path,
			Map<String, String> params, String encoding) throws Exception
	{
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		if (params != null && !params.isEmpty())
		{
			for (Map.Entry<String, String> entry : params.entrySet())
			{
				param.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
			}
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(param, encoding);
		HttpPost post = new HttpPost(path);
		// HttpGet post = new HttpGet();
		post.setEntity(entity);
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(post);
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == HttpStatus.SC_OK)
		{
			InputStream is = response.getEntity().getContent();// 获取服务器返回的数据
			System.out.println("server result :" + readInStream(is));
			return true;
		} else if (statusCode == HttpStatus.SC_REQUEST_TIMEOUT)
		{
			return false;
		}
		return false;
	}

	private static String readInStream(InputStream inStream)
	{
		try
		{
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[512];
			int length = -1;
			while ((length = inStream.read(buffer)) != -1)
			{
				outStream.write(buffer, 0, length);
			}

			outStream.close();
			inStream.close();
			return outStream.toString();
		} catch (IOException e)
		{
			Log.i("FileTest", e.getMessage());
		}
		return null;
	}
}
