/**
 * Project Name:JZGPingGuShi
 * File Name:CarSource.java
 * Package Name:com.gc.jzgpinggushi.vo
 * Date:2014-10-16上午10:27:31
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.vo;

import java.io.Serializable;

/**
 * ClassName:CarSource <br/>
 * Function: 车源实体对象. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-10-16 上午10:27:31 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class CarSource extends BaseObject implements Serializable
{

	/**
	 * 点击已出价车辆列表的位置
	 */
	private int clickPosition;

	/**
	 * 车况等级
	 */
	private int carLevel;

	/**
	 * 等级图片地址
	 */
	private String carLevelUrl;

	/**
	 * 车源图片地址
	 */
	private String imgUrl;

	/**
	 * 车型全称
	 */
	private String fullName;

	/**
	 * 首次上牌日期
	 */
	private String registDate;

	/**
	 * 保险到期日
	 */
	private String insuranceExpireDate;

	/**
	 * 行驶里程，单位公里
	 */
	private int mileage;

	/**
	 * 年检到期日
	 */
	private String inspectionExpireDate;

	/**
	 * 车身颜色
	 */
	private String bodyColor;

	/**
	 * 价格区间
	 */
	private String priceRange;

	/**
	 * 车源Id
	 */
	private int carsourceid;

	public int getCarLevel()
	{
		return carLevel;
	}

	public void setCarLevel(int carLevel)
	{
		this.carLevel = carLevel;
	}

	public String getCarLevelUrl()
	{
		return carLevelUrl;
	}

	public void setCarLevelUrl(String carLevelUrl)
	{
		this.carLevelUrl = carLevelUrl;
	}

	public String getImgUrl()
	{
		return imgUrl;
	}

	public void setImgUrl(String imgUrl)
	{
		this.imgUrl = imgUrl;
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

	public String getInsuranceExpireDate()
	{
		return insuranceExpireDate;
	}

	public void setInsuranceExpireDate(String insuranceExpireDate)
	{
		this.insuranceExpireDate = insuranceExpireDate;
	}

	public int getMileage()
	{
		return mileage;
	}

	public void setMileage(int mileage)
	{
		this.mileage = mileage;
	}

	public String getInspectionExpireDate()
	{
		return inspectionExpireDate;
	}

	public void setInspectionExpireDate(String inspectionExpireDate)
	{
		this.inspectionExpireDate = inspectionExpireDate;
	}

	public String getBodyColor()
	{
		return bodyColor;
	}

	public void setBodyColor(String bodyColor)
	{
		this.bodyColor = bodyColor;
	}

	public String getPriceRange()
	{
		return priceRange;
	}

	public void setPriceRange(String priceRange)
	{
		this.priceRange = priceRange;
	}

	public int getCarsourceid()
	{
		return carsourceid;
	}

	public void setCarsourceid(int carsourceid)
	{
		this.carsourceid = carsourceid;
	}

	@Override
	public String toString()
	{
		return "CarSource [carLevel=" + carLevel + ", imgUrl=" + imgUrl
				+ ", fullName=" + fullName + ", registDate=" + registDate
				+ ", insuranceExpireDate=" + insuranceExpireDate + ", mileage="
				+ mileage + ", inspectionExpireDate=" + inspectionExpireDate
				+ ", bodyColor=" + bodyColor + ", priceRange=" + priceRange
				+ ", carsourceid=" + carsourceid + "]";
	}

	public int getClickPosition()
	{
		return clickPosition;
	}

	public void setClickPosition(int clickPosition)
	{
		this.clickPosition = clickPosition;
	}

}
