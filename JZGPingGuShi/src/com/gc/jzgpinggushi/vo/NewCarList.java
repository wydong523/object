/**
 * Project Name:JZGPingGuShi
 * File Name:NewCarList.java
 * Package Name:com.gc.jzgpinggushi.vo
 * Date:2014-9-9下午3:33:04
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ClassName:NewCarList <br/>
 * Function: 最新车源列表实体. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-9 下午3:33:04 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class NewCarList extends BaseObject implements Serializable
{
	private ArrayList<NewCar> newCars = new ArrayList<NewCar>();

	public ArrayList<NewCar> getNewCars()
	{
		return newCars;
	}

	public void setNewCars(ArrayList<NewCar> newCars)
	{
		this.newCars = newCars;
	}

}
