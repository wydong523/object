/**
 * Project Name:JingZhenGu
 * File Name:QueryCarList.java
 * Package Name:com.gc.jingzhengu.vo
 * Date:2014-8-5下午3:29:39
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ClassName:QueryCarList <br/>
 * Function: 查车实体列表. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-8-5 下午3:29:39 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class QueryCarList extends BaseObject implements Serializable
{
	private ArrayList<QueryCar> cars = new ArrayList<QueryCar>();

	public ArrayList<QueryCar> getCars()
	{
		return cars;
	}

	public void setCars(ArrayList<QueryCar> cars)
	{
		this.cars = cars;
	}

}
