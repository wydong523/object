/**
 * Project Name:JingZhenGu
 * File Name:CarType.java
 * Package Name:com.gc.jingzhengu.vo
 * Date:2014-5-26上午11:59:38
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.vo;

import java.io.Serializable;

/**
 * ClassName:CarType <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-5-26 上午11:59:38 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class CarType implements Serializable
{
	private String mCarFactory;

	private String mCarType;

	public String getmCarFactory()
	{
		return mCarFactory;
	}

	public void setmCarFactory(String mCarFactory)
	{
		this.mCarFactory = mCarFactory;
	}

	public String getmCarType()
	{
		return mCarType;
	}

	public void setmCarType(String mCarType)
	{
		this.mCarType = mCarType;
	}

}
