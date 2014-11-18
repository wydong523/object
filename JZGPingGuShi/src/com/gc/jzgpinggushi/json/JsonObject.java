/**
 * Project Name:JZGPingGuShi
 * File Name:JsonObject.java
 * Package Name:com.gc.jzgpinggushi.json
 * Date:2014-9-1上午10:51:32
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.json;

import java.io.Serializable;

import com.gc.jzgpinggushi.vo.MakeList;
import com.gc.jzgpinggushi.vo.ModelList;
import com.gc.jzgpinggushi.vo.NewCarList;
import com.gc.jzgpinggushi.vo.OfferList;
import com.gc.jzgpinggushi.vo.OfferSuccessList;
import com.gc.jzgpinggushi.vo.QueryCarList;
import com.gc.jzgpinggushi.vo.StyleList;

/**
 * ClassName:JsonObject <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-1 上午10:51:32 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public interface JsonObject
{
	/**
	 * 解析品牌数据 parserJson: <br/>
	 * 
	 * @author wang
	 * @param jsonData
	 * @return
	 * @since JDK 1.6
	 */
	MakeList parserMakeList(String jsonData);

	/**
	 * 解析系列数据 parserModelList: <br/>
	 * 
	 * @author wang
	 * @param jsonData
	 * @return
	 * @since JDK 1.6
	 */
	ModelList parserModelList(String jsonData);

	/**
	 * 解析车型数据 parserStyleList: <br/>
	 * 
	 * @author wang
	 * @param modelid
	 * @return
	 * @since JDK 1.6
	 */
	StyleList parserStyleList(String jsonData);

	/**
	 * 解析查车 parserQueryCarList: <br/>
	 * 
	 * @author wang
	 * @param jsonData
	 * @return
	 * @since JDK 1.6
	 */
	QueryCarList parserQueryCarList(String jsonData);

	/**
	 * 生成json数据 generateJson: <br/>
	 * 
	 * @author wang
	 * @param object
	 * @return
	 * @since JDK 1.6
	 */
	String generateJson(Serializable object);

	/**
	 * 解析已出价车辆列表 parserOfferList: <br/>
	 * 
	 * @author wang
	 * @return
	 * @since JDK 1.6
	 */
	OfferList parserOfferList(String jsonData);

	/**
	 * 解析出价成功列表 parserOfferSuccessList: <br/>
	 * 
	 * @author wang
	 * @return
	 * @since JDK 1.6
	 */
	OfferSuccessList parserOfferSuccessList(String jsonData);

	/**
	 * 解析车源列表 parserNewCarList: <br/>
	 * 
	 * @author wang
	 * @return
	 * @since JDK 1.6
	 */
	NewCarList parserNewCarList(String jsonData);

}
