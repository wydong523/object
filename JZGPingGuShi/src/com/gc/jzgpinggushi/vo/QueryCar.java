/**
 * Project Name:JingZhenGu
 * File Name:QueryCar.java
 * Package Name:com.gc.jingzhengu.vo
 * Date:2014-8-5下午3:22:58
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.vo;

import java.io.Serializable;

/**
 * ClassName:QueryCar <br/>
 * Function: 查车实体. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-8-5 下午3:22:58 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class QueryCar implements Serializable
{
	/**
	 * 图片下载地址
	 */
	private String imgUrl;

	/**
	 * 车辆评级
	 */
	private int carLevel;

	/**
	 * 全名
	 */
	private String fullName;

	/**
	 * 行驶公里数
	 */
	private int mileage;

	/**
	 * 上牌时间
	 */
	private String registDate;

	/**
	 * 价格
	 */
	private String marketPrice;

	/**
	 * 结果报告页地址
	 */
	private String reportUrl;

	/**
	 * 车源Id
	 */
	private int carsourceid;

	public String getImgUrl()
	{
		return imgUrl;
	}

	public void setImgUrl(String imgUrl)
	{
		this.imgUrl = imgUrl;
	}

	public int getCarLevel()
	{
		return carLevel;
	}

	public void setCarLevel(int carLevel)
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

	public int getMileage()
	{
		return mileage;
	}

	public void setMileage(int mileage)
	{
		this.mileage = mileage;
	}

	public int getCarsourceid()
	{
		return carsourceid;
	}

	public void setCarsourceid(int carsourceid)
	{
		this.carsourceid = carsourceid;
	}

}
