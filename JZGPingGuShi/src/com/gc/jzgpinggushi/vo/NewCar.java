/**
 * Project Name:JZGPingGuShi
 * File Name:NewCar.java
 * Package Name:com.gc.jzgpinggushi.vo
 * Date:2014-9-9下午3:32:22
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.vo;

import java.io.Serializable;

/**
 * ClassName:NewCar <br/>
 * Function: 最新车源实体. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-9 下午3:32:22 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class NewCar extends BaseObject implements Serializable
{
	// 汽车图片：
	private String imgUrl;

	// 汽车等级：
	private String carLevel;

	// 车名全称：
	private String fullName;

	// 行驶里程：
	private String mileage;

	// 上牌时间：
	private String registDate;

	// 市场价格：
	private String marketPrice;

	// 结果报告
	private String reportUrl;

	public String getImgUrl()
	{
		return imgUrl;
	}

	public void setImgUrl(String imgUrl)
	{
		this.imgUrl = imgUrl;
	}

	public String getCarLevel()
	{
		return carLevel;
	}

	public void setCarLevel(String carLevel)
	{
		this.carLevel = carLevel;
	}

	public String getFullName()
	{
		return fullName;
	}

	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	public String getMileage()
	{
		return mileage;
	}

	public void setMileage(String mileage)
	{
		this.mileage = mileage;
	}

	public String getRegistDate()
	{
		return registDate;
	}

	public void setRegistDate(String registDate)
	{
		this.registDate = registDate;
	}

	public String getMarketPrice()
	{
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice)
	{
		this.marketPrice = marketPrice;
	}

	public String getReportUrl()
	{
		return reportUrl;
	}

	public void setReportUrl(String reportUrl)
	{
		this.reportUrl = reportUrl;
	}

}
