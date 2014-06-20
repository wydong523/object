/**
 * Project Name:JingZhenGu
 * File Name:JsonObject.java
 * Package Name:com.gc.jingzhengu.json
 * Date:2014-6-12下午1:37:18
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.json;

import java.io.Serializable;
import java.util.ArrayList;

import com.gc.jingzhengu.vo.CityList;
import com.gc.jingzhengu.vo.MakeList;
import com.gc.jingzhengu.vo.ModelList;
import com.gc.jingzhengu.vo.PriceRange;
import com.gc.jingzhengu.vo.ProvinceList;
import com.gc.jingzhengu.vo.StyleList;

/**
 * ClassName:JsonObject <br/>
 * Function: json数据操作接口. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-12 下午1:37:18 <br/>
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
	 * 解析省数据 parserProvinceList: <br/>
	 * 
	 * @author wang
	 * @param jsonData
	 * @return
	 * @since JDK 1.6
	 */
	ProvinceList parserProvinceList(String jsonData);

	/**
	 * 解析市数据 parserCityList: <br/>
	 * 
	 * @author wang
	 * @param jsonData
	 * @return
	 * @since JDK 1.6
	 */
	CityList parserCityList(String jsonData);

	/**
	 * 解析价格区间 parserPriceRange: <br/>
	 * 
	 * @author wang
	 * @param jsonData
	 * @return
	 * @since JDK 1.6
	 */
	PriceRange parserPriceRange(String jsonData);

	/**
	 * 生成json数据 generateJson: <br/>
	 * 
	 * @author wang
	 * @param object
	 * @return
	 * @since JDK 1.6
	 */
	String generateJson(Serializable object);

}
