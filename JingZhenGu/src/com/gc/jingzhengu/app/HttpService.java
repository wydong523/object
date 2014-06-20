/**
 * Project Name:JingZhenGu
 * File Name:HttpService.java
 * Package Name:com.gc.jingzhengu.app
 * Date:2014-6-12下午1:30:14
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.gc.jingzhengu.json.JsonObjectImpl;
import com.gc.jingzhengu.uitls.HttpClientUtils;
import com.gc.jingzhengu.uitls.SignUtils;
import com.gc.jingzhengu.vo.CityList;
import com.gc.jingzhengu.vo.MakeList;
import com.gc.jingzhengu.vo.ModelList;
import com.gc.jingzhengu.vo.PriceRange;
import com.gc.jingzhengu.vo.ProvinceList;
import com.gc.jingzhengu.vo.SimpleAssessment;
import com.gc.jingzhengu.vo.StyleList;

/**
 * ClassName:HttpService <br/>
 * Function: 网络操作业务层. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-12 下午1:30:14 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class HttpService
{
	private static final String ENCODING = "utf-8";

	// String path =
	// "http://api.jingzhengu.com/APP/Car/GetMakeByLetter.ashx?&sign=9468ADA326C0D6BAC2A66D6DFD4D3EC6";
	// Map<String, String> params = new HashMap<String, String>();//
	// 定义一个保存key-value的Map用于保存需要传输的数据
	// params.put("&sign", "9468ADA326C0D6BAC2A66D6DFD4D3EC6");//
	// 保存数据到map对象

	/**
	 * 获取汽车品牌列表 getMakeList: <br/>
	 * 
	 * @author wang
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static MakeList getMakeList() throws Exception
	{
		// System.out.println("SignUtils.signForMakeList()"
		// + SignUtils.signForMakeList());
		String path = "http://apitest.guchewang.com/APP/Car/GetMakeByLetter.ashx";
		// String path =
		// "http://api.jingzhengu.com/APP/Car/GetMakeByLetter.ashx";
		String result = HttpClientUtils.sendHttpClientPOSTRequest(path
				+ "?&sign=" + SignUtils.signForMakeList(), null, ENCODING);
		System.out.println(result);
		JsonObjectImpl jsonObjectImpl = new JsonObjectImpl();
		MakeList makeList = jsonObjectImpl.parserMakeList(result);
		return makeList;
	}

	/**
	 * 获取汽车车系列表 getModelList: <br/>
	 * 
	 * @author wang
	 * @param makeId
	 *            品牌id
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static ModelList getModelList(String makeid) throws Exception
	{
		ModelList modelList = null;
		// String path =
		// "http://apitest.guchewang.com/APP/Car/GetModelByMakeId.ashx";
		String path = "http://apitest.guchewang.com/APP/Car/GetGroupModelByManufacturerId.ashx";

		String url = path + "?MakeId=" + makeid + "&sign="
				+ SignUtils.signForModelList(makeid);

		String result = HttpClientUtils.sendHttpClientPOSTRequest(url, null,
				ENCODING);
		System.out.println("url is " + url);
		System.out.println("result is model " + result);
		JsonObjectImpl jsonObjectImpl = new JsonObjectImpl();
		modelList = jsonObjectImpl.parserModelList(result);
		return modelList;
	}

	/**
	 * 获取汽车类型数据 getStyleList: <br/>
	 * 
	 * @author wang
	 * @param modelid
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static StyleList getStyleList(String modelid) throws Exception
	{
		StyleList styleList = null;
		String path = "http://apitest.guchewang.com/app/car/GetGroupStyleByModelId.ashx";
		String url = path + "?ModelId=" + modelid + "&sign="
				+ SignUtils.signForStylelList(modelid);

		String result = HttpClientUtils.sendHttpClientPOSTRequest(url, null,
				ENCODING);
		System.out.println("getStyleList result is " + result);
		JsonObjectImpl jsonObjectImpl = new JsonObjectImpl();
		styleList = jsonObjectImpl.parserStyleList(result);
		return styleList;
	}

	/**
	 * 获取简单评估价格 getSimpleAssessmentPrice: <br/>
	 * 
	 * @author wang
	 * @param simpleAssessment
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static PriceRange getSimpleAssessmentPrice(
			SimpleAssessment simpleAssessment) throws Exception
	{
		String path = "http://apitest.guchewang.com/APP/Assess/Simple.ashx";
		JsonObjectImpl jsonObjectImpl = new JsonObjectImpl();
		String JsonBody = jsonObjectImpl.generateJson(simpleAssessment);
		System.out.println("Send json is " + JsonBody);
		Map<String, String> params = new HashMap<String, String>();
		params.put("JsonBody", JsonBody);
		String result = HttpClientUtils.sendHttpClientPOSTRequest(path
				+ "?&sign=" + SignUtils.signForMakeList(), params, ENCODING);
		System.out.println("result is " + result);
		return jsonObjectImpl.parserPriceRange(result);
	}

	/**
	 * 获取省数据 getProvinceList: <br/>
	 * 
	 * @author wang
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static ProvinceList getProvinceList() throws Exception
	{
		String path = "http://apitest.guchewang.com/app/area/GetProv.ashx";
		String result = HttpClientUtils.sendHttpClientPOSTRequest(path
				+ "?&sign=" + SignUtils.signForMakeList(), null, ENCODING);
		System.out.println(result);
		JsonObjectImpl jsonObjectImpl = new JsonObjectImpl();
		ProvinceList provinceList = jsonObjectImpl.parserProvinceList(result);
		return provinceList;
	}

	/**
	 * 获取市数据 getCityList: <br/>
	 * 
	 * @author wang
	 * @param provinceid
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static CityList getCityList(int provinceid) throws Exception
	{
		CityList cityList = null;
		String path = "http://apitest.guchewang.com/app/area/GetCityByProvId.ashx";
		String url = path + "?ProvId=" + provinceid + "&sign="
				+ SignUtils.signForCityList(String.valueOf(provinceid));

		
		String result = HttpClientUtils.sendHttpClientPOSTRequest(url, null,
				ENCODING);
		JsonObjectImpl jsonObjectImpl = new JsonObjectImpl();
		cityList = jsonObjectImpl.parserCityList(result);
		return cityList;
	}
}
