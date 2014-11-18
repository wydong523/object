/**
 * Project Name:JZGPingGuShi
 * File Name:HttpService.java
 * Package Name:com.gc.jzgpinggushi.app
 * Date:2014-9-1上午10:36:02
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.app;

import com.gc.jzgpinggushi.json.JsonObjectImpl;
import com.gc.jzgpinggushi.uitls.HttpClientUtils;
import com.gc.jzgpinggushi.uitls.SignUtils;
import com.gc.jzgpinggushi.vo.CarSource;
import com.gc.jzgpinggushi.vo.MakeList;
import com.gc.jzgpinggushi.vo.ModelList;
import com.gc.jzgpinggushi.vo.NewCarList;
import com.gc.jzgpinggushi.vo.OfferList;
import com.gc.jzgpinggushi.vo.OfferSuccessList;
import com.gc.jzgpinggushi.vo.QueryCarList;
import com.gc.jzgpinggushi.vo.StyleList;
import com.gc.jzgpinggushi.vo.User;
import com.gc.jzgpinggushi.vo.UserApplyList;

/**
 * ClassName:HttpService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-1 上午10:36:02 <br/>
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

	// static final String RESULT =
	// "{\"status\":100,\"msg\":\"成功!\",\"flag\":0,\"ReportList\":[{\"insuranceExpireDate\":\"2014年9月\",\"inspectionExpireDate\":\"2014年9月\",\"bodyColor\":\"黑色\",\"priceRange\":\"20~30万\",\"myBidPrice\":\"39.9万\",\"imgUrl\":\"http://img1.bitautoimg.com/autoalbum/files/20130407/419/10520441996117_2521828_1.jpg\",\"carLevel\":1,\"fullName\":\"奔驰B级(进口) 2012款 B200 1.6T 双离合\",\"mileage\":35000,\"registDate\":\"2011年\",\"marketPrice\":\"18.19\",\"reportUrl\":\"http://m1.jingzhengu.com/gujia/reportDetailClient.aspx?reportId=363369\"},{\"insuranceExpireDate\":\"2014年9月\",\"inspectionExpireDate\":\"2014年9月\",\"bodyColor\":\"黑色\",\"priceRange\":\"20~30万\",\"myBidPrice\":\"39.9万\",\"imgUrl\":\"http://img4.bitautoimg.com/autoalbum/files/20090901/022/200909011513231838_943087_1.jpg\",\"carLevel\":1,\"fullName\":\"别克凯越 2008款 1.6 LX—MT\",\"mileage\":75000,\"registDate\":\"2009年\",\"marketPrice\":\"4.51\",\"reportUrl\":\"http://m1.jingzhengu.com/gujia/reportDetailClient.aspx?reportId=363286\"},{\"insuranceExpireDate\":\"2014年9月\",\"inspectionExpireDate\":\"2014年9月\",\"bodyColor\":\"黑色\",\"priceRange\":\"20~30万\",\"myBidPrice\":\"39.9万\",\"imgUrl\":\"http://img1.bitautoimg.com/autoalbum/files/20100208/001/201002080942593026_1149220_1.jpg\",\"carLevel\":1,\"fullName\":\"奥迪A6L 2011款 3.0T FSI quattro 豪华型\",\"mileage\":45000,\"registDate\":\"2010年\",\"marketPrice\":\"31.82\",\"reportUrl\":\"http://m1.jingzhengu.com/gujia/reportDetailClient.aspx?reportId=363285\"},{\"insuranceExpireDate\":\"2014年9月\",\"inspectionExpireDate\":\"2014年9月\",\"bodyColor\":\"黑色\",\"priceRange\":\"20~30万\",\"myBidPrice\":\"39.9万\",\"imgUrl\":\"http://img3.bitautoimg.com/autoalbum/files/20090901/037/200909011514000901_943102_1.jpg\",\"carLevel\":1,\"fullName\":\"别克凯越 2008款 1.6 LX—MT\",\"mileage\":75000,\"registDate\":\"2009年\",\"marketPrice\":\"4.34\",\"reportUrl\":\"http://m1.jingzhengu.com/gujia/reportDetailClient.aspx?reportId=363283\"},{\"insuranceExpireDate\":\"2014年9月\",\"inspectionExpireDate\":\"2014年9月\",\"bodyColor\":\"黑色\",\"priceRange\":\"20~30万\",\"myBidPrice\":\"39.9万\",\"imgUrl\":\"http://img1.bitautoimg.com/autoalbum/files/20100312/020/201003122204098707_1174380_1.jpg\",\"carLevel\":1,\"fullName\":\"大众捷达 2010款 前卫\",\"mileage\":55000,\"registDate\":\"2010年\",\"marketPrice\":\"4.88\",\"reportUrl\":\"http://m1.jingzhengu.com/gujia/reportDetailClient.aspx?reportId=363265\"},{\"insuranceExpireDate\":\"2014年9月\",\"inspectionExpireDate\":\"2014年9月\",\"bodyColor\":\"黑色\",\"priceRange\":\"20~30万\",\"myBidPrice\":\"39.9万\",\"imgUrl\":\"http://img2.bitautoimg.com/autoalbum/files/20090731/020/200907312028556228_908345_1.jpg\",\"carLevel\":1,\"fullName\":\"吉利汽车吉利帝豪EC7 2009款 1.8L MT豪华型\",\"mileage\":63000,\"registDate\":\"2010年\",\"marketPrice\":\"4.71\",\"reportUrl\":\"http://m1.jingzhengu.com/gujia/reportDetailClient.aspx?reportId=363125\"},{\"insuranceExpireDate\":\"2014年9月\",\"inspectionExpireDate\":\"2014年9月\",\"bodyColor\":\"黑色\",\"priceRange\":\"20~30万\",\"myBidPrice\":\"39.9万\",\"imgUrl\":\"http://img4.bitautoimg.com/autoalbum/files/20081020/031/200810201717427031_86887_1.jpg\",\"carLevel\":1,\"fullName\":\"哈飞赛马 2009款 新时代赛马 1.5L手动标准型\",\"mileage\":63000,\"registDate\":\"2011年\",\"marketPrice\":\"3.10\",\"reportUrl\":\"http://m1.jingzhengu.com/gujia/reportDetailClient.aspx?reportId=362804\"}]}";

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
		String path = "http://api.jingzhengu.com/APP/Car/GetMakeByLetter.ashx";
		String result = HttpClientUtils.sendHttpClientPOSTRequest(path
				+ "?&sign=" + SignUtils.signForMakeList(), null, ENCODING);
		System.out.println(result);

		System.out.println("path is" + path + "?&sign="
				+ SignUtils.signForMakeList());
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
		String path = "http://api.jingzhengu.com/APP/Car/GetGroupModelByManufacturerId.ashx";
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
		String path = "http://api.jingzhengu.com/app/car/GetGroupStyleByModelId.ashx";
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
	 * 查车 getQueryCarList: <br/>
	 * 
	 * @author wang
	 * @param styleId
	 * @param pageCount
	 * @param orderItem
	 * @param orderType
	 * @param pgsid
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static QueryCarList getQueryCarList(int styleId, int pageCount,
			String orderItem, String orderType, int pgsid) throws Exception
	{
		QueryCarList queryCarList = null;
		String path = "http://api.jingzhengu.com/app/pgs/carsourcelist.ashx";
		String url = path
				+ "?StyleId="
				+ styleId
				+ "&PageCount="
				+ pageCount
				+ "&OrderItem="
				+ orderItem
				+ "&OrderType="
				+ orderType
				+ "&pgsid="
				+ pgsid
				+ "&sign="
				+ SignUtils.signForQueryCarList(styleId, pageCount, orderItem,
						orderType, pgsid);
		// System.out.println("url is " + url);

		String result = HttpClientUtils.sendHttpClientPOSTRequest(url, null,
				ENCODING);
		System.out.println("queryCar is :" + result);
		JsonObjectImpl jsonObjectImpl = new JsonObjectImpl();
		queryCarList = jsonObjectImpl.parserQueryCarList(result);
		return queryCarList;
	}

	/**
	 * 已出价车辆列表查询 getQueryCarList: <br/>
	 * 
	 * @author wang
	 * @param styleId
	 * @param pageCount
	 * @param orderItem
	 * @param orderType
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static OfferList getOfferList(int pgsid, int pageCount)
			throws Exception
	{
		OfferList offerList = null;
		String path = "http://api.jingzhengu.com/app/pgs/appraisedlist.ashx";
		String url = path + "?pgsid=" + pgsid + "&PageCount=" + pageCount
				+ "&sign=" + SignUtils.signForOfferList(pgsid, pageCount);
		System.out.println("url is " + url);
		String result = HttpClientUtils.sendHttpClientGetRequest(url);
		System.out.println("getOfferList is :" + result);
		JsonObjectImpl jsonObjectImpl = new JsonObjectImpl();
		offerList = jsonObjectImpl.parserOfferList(result);
		return offerList;
	}

	/**
	 * 出价成功列表查询 getQueryCarList: <br/>
	 * 
	 * @author wang
	 * @param styleId
	 * @param pageCount
	 * @param orderItem
	 * @param orderType
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static OfferSuccessList getOfferSuccessList(int pgsid, int pageCount)
			throws Exception
	{
		OfferSuccessList offerSuccessList = null;
		String path = "http://api.jingzhengu.com/app/pgs/appraisesuccesslist.ashx";
		String url = path + "?pgsid=" + pgsid + "&PageCount=" + pageCount
				+ "&sign="
				+ SignUtils.signForOfferSuccessList(pgsid, pageCount);
		String result = HttpClientUtils.sendHttpClientGetRequest(url);
		System.out.println("getOfferSuccessList is :" + result);
		JsonObjectImpl jsonObjectImpl = new JsonObjectImpl();
		offerSuccessList = jsonObjectImpl.parserOfferSuccessList(result);
		return offerSuccessList;
	}

	/**
	 * 最新车源列表查询 getQueryCarList: <br/>
	 * 
	 * @author wang
	 * @param styleId
	 * @param pageCount
	 * @param orderItem
	 * @param orderType
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static NewCarList getNewCarList(int pageCount, int pgsid)
			throws Exception
	{
		NewCarList newCarList = null;
		String path = "http://api.jingzhengu.com/app/pgs/newcarsourcelist.ashx";
		String url = path + "?PageCount=" + pageCount + "&pgsid=" + pgsid
				+ "&sign=" + SignUtils.signForNewCarList(pageCount,pgsid);
		String result = HttpClientUtils.sendHttpClientGetRequest(url);
		System.out.println("getNewCarList is :" + result);
		JsonObjectImpl jsonObjectImpl = new JsonObjectImpl();
		newCarList = jsonObjectImpl.parserNewCarList(result);
		return newCarList;
	}

	/**
	 * 用户登录 login: <br/>
	 * 
	 * @author wang
	 * @param username
	 * @param password
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static User login(String username, String password) throws Exception
	{
		User user = null;
		String path = "http://api.jingzhengu.com/app/pgs/login.ashx";
		String url = path + "?username=" + username + "&password=" + password
				+ "&sign=" + SignUtils.signForLogin(username, password);

		String result = HttpClientUtils.sendHttpClientGetRequest(url);
		JsonObjectImpl jsonObjectImpl = new JsonObjectImpl();
		user = jsonObjectImpl.parserUser(result);
		return user;
	}

	/**
	 * 查询车源详细信息 QueryByCarsourceid: <br/>
	 * 
	 * @author wang
	 * @param carId
	 * @return
	 * @since JDK 1.6
	 */
	public static CarSource queryByCarsourceid(int carsourceid)
	{
		CarSource carSource = null;
		String path = "http://api.jingzhengu.com/app/pgs/getcardetail.ashx";
		String url = path + "?carsourceid=" + carsourceid + "&sign="
				+ SignUtils.signForQueryByCarsourceid(carsourceid);

		String result = HttpClientUtils.sendHttpClientGetRequest(url);
		JsonObjectImpl jsonObjectImpl = new JsonObjectImpl();
		carSource = jsonObjectImpl.parserCarSource(result);
		return carSource;
	}

	public static boolean uploadOffer(String myBidPrice, int pgsid,
			int carsourceid) throws Exception
	{
		String path = "http://api.jingzhengu.com/app/pgs/activeappraise.ashx";
		String url = path + "?pgsid=" + pgsid + "&myBidPrice=" + myBidPrice
				+ "&carsourceid=" + carsourceid + "&sign="
				+ SignUtils.signForUploadPrice(pgsid, myBidPrice, carsourceid);
		String result = HttpClientUtils.sendHttpClientGetRequest(url);
		JsonObjectImpl jsonObjectImpl = new JsonObjectImpl();
		return jsonObjectImpl.parserUploadResult(result);
	}

	public static UserApplyList getUserApplyList(int pgsid, int pageCount)
	{
		UserApplyList userApplyList = null;
		String path = "http://api.jingzhengu.com/app/pgs/applyappraiselist.ashx";
		String url = path + "?pgsid=" + pgsid + "&PageCount=" + pageCount
				+ "&sign="
				+ SignUtils.signForOfferSuccessList(pgsid, pageCount);
		String result = HttpClientUtils.sendHttpClientGetRequest(url);
		System.out.println("getUserApplyList is :" + result);
		JsonObjectImpl jsonObjectImpl = new JsonObjectImpl();
		userApplyList = jsonObjectImpl.parserUserApplyList(result);
		return userApplyList;
	}

}
