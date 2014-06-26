/**
 * Project Name:JingZhenGu
 * File Name:CarDamage.java
 * Package Name:com.gc.jingzhengu.vo
 * Date:2014-6-22下午10:11:00
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.vo;

import java.io.Serializable;

/**
 * ClassName:CarDamage <br/>
 * Function: 车损对象： 1:滑动页的id 2:滑动页选项位置 3:选中的车损id 4:是否被选中标记. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-22 下午10:11:00 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class CarDamage implements Serializable
{
	/**
	 * 车况报告ID
	 */
	private int appraiseReportId;

	/**
	 * 上牌省ID
	 */
	private int licenseProvId;

	/**
	 * 上牌市ID
	 */
	private int licenseCityId;

	/**
	 * 车牌号
	 */
	private String licenseNumber = "";

	/**
	 * 颜色
	 */
	private String bodyColor;

	/**
	 * 保险到期日期，格式：YYYY-MM-01
	 */
	private String insuranceExpireDate;

	/**
	 * 年检到期日期，格式：YYYY-MM-01
	 */
	private String inspectionExpireDate;

	/**
	 * 实际购车价，整数，单位：元
	 */
	private int purchasePrice;

	/**
	 * 车辆损伤，格式：以”,”间隔的一系列损伤编号，无损伤传空值
	 */
	private String carInjury;

	public int getAppraiseReportId()
	{
		return appraiseReportId;
	}

	public void setAppraiseReportId(int appraiseReportId)
	{
		this.appraiseReportId = appraiseReportId;
	}

	public int getLicenseProvId()
	{
		return licenseProvId;
	}

	public void setLicenseProvId(int licenseProvId)
	{
		this.licenseProvId = licenseProvId;
	}

	public int getLicenseCityId()
	{
		return licenseCityId;
	}

	public void setLicenseCityId(int licenseCityId)
	{
		this.licenseCityId = licenseCityId;
	}

	public String getLicenseNumber()
	{
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber)
	{
		this.licenseNumber = licenseNumber;
	}

	public String getBodyColor()
	{
		return bodyColor;
	}

	public void setBodyColor(String bodyColor)
	{
		this.bodyColor = bodyColor;
	}

	public String getInsuranceExpireDate()
	{
		return insuranceExpireDate;
	}

	public void setInsuranceExpireDate(String insuranceExpireDate)
	{
		this.insuranceExpireDate = insuranceExpireDate;
	}

	public String getInspectionExpireDate()
	{
		return inspectionExpireDate;
	}

	public void setInspectionExpireDate(String inspectionExpireDate)
	{
		this.inspectionExpireDate = inspectionExpireDate;
	}

	public int getPurchasePrice()
	{
		return purchasePrice;
	}

	public void setPurchasePrice(int purchasePrice)
	{
		this.purchasePrice = purchasePrice;
	}

	public String getCarInjury()
	{
		return carInjury;
	}

	public void setCarInjury(String carInjury)
	{
		this.carInjury = carInjury;
	}

	@Override
	public String toString()
	{
		return "CarDamage [appraiseReportId=" + appraiseReportId
				+ ", licenseProvId=" + licenseProvId + ", licenseCityId="
				+ licenseCityId + ", licenseNumber=" + licenseNumber
				+ ", bodyColor=" + bodyColor + ", insuranceExpireDate="
				+ insuranceExpireDate + ", inspectionExpireDate="
				+ inspectionExpireDate + ", purchasePrice=" + purchasePrice
				+ ", carInjury=" + carInjury + "]";
	}

}
