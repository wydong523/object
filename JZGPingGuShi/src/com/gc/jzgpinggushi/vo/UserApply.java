/**
 * Project Name:JZGPingGuShi
 * File Name:UserApply.java
 * Package Name:com.gc.jzgpinggushi.vo
 * Date:2014-10-20下午7:40:50
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.vo;

import java.io.Serializable;

/**
 * ClassName:UserApply <br/>
 * Function: 用户申请实体. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-10-20 下午7:40:50 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class UserApply extends BaseObject implements Serializable
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

	// 卖家电话：
	private String sellerPhoneNum;

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

	public String getSellerPhoneNum()
	{
		return sellerPhoneNum;
	}

	public void setSellerPhoneNum(String sellerPhoneNum)
	{
		this.sellerPhoneNum = sellerPhoneNum;
	}

	@Override
	public String toString()
	{
		return "UserApply [imgUrl=" + imgUrl + ", carLevel=" + carLevel
				+ ", fullName=" + fullName + ", mileage=" + mileage
				+ ", registDate=" + registDate + ", marketPrice=" + marketPrice
				+ ", sellerPhoneNum=" + sellerPhoneNum + "]";
	}

}
