/**
 * Project Name:JingZhenGu
 * File Name:SimpleAssessment.java
 * Package Name:com.gc.jingzhengu.vo
 * Date:2014-6-16下午3:45:41
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.vo;

import java.io.Serializable;

/**
 * ClassName:SimpleAssessment <br/>
 * Function: 初级估价实体. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-16 下午3:45:41 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class SimpleAssessment implements Serializable
{
	/**
	 * 汽车品牌id
	 */
	private int makeId;

	/**
	 * 汽车车系id
	 */
	private int modelId;

	/**
	 * 汽车类型id
	 */
	private int styleId;

	/**
	 * 汽车类型
	 */
	private String style;

	/**
	 * 行驶公里数
	 */
	private String mileage;

	/**
	 * 上牌年份
	 */
	private String getLicenseDate;

	/**
	 * 车况id
	 */
	private int appraiseReportId;

	/**
	 * 价格区间
	 */
	private String priceRange;

	public int getAppraiseReportId()
	{
		return appraiseReportId;
	}

	public void setAppraiseReportId(int appraiseReportId)
	{
		this.appraiseReportId = appraiseReportId;
	}

	public String getPriceRange()
	{
		return priceRange;
	}

	public void setPriceRange(String priceRange)
	{
		this.priceRange = priceRange;
	}

	public int getMakeId()
	{
		return makeId;
	}

	public void setMakeId(int makeId)
	{
		this.makeId = makeId;
	}

	public int getModelId()
	{
		return modelId;
	}

	public void setModelId(int modelId)
	{
		this.modelId = modelId;
	}

	public int getStyleId()
	{
		return styleId;
	}

	public void setStyleId(int styleId)
	{
		this.styleId = styleId;
	}

	public String getMileage()
	{
		return mileage;
	}

	public void setMileage(String mileage)
	{
		this.mileage = mileage;
	}

	public String getGetLicenseDate()
	{
		return getLicenseDate;
	}

	public void setGetLicenseDate(String getLicenseDate)
	{
		this.getLicenseDate = getLicenseDate;
	}

	@Override
	public String toString()
	{
		return "SimpleAssessment [makeId=" + makeId + ", modelId=" + modelId
				+ ", styleId=" + styleId + ", mileage=" + mileage
				+ ", getLicenseDate=" + getLicenseDate + ", appraiseReportId="
				+ appraiseReportId + ", priceRange=" + priceRange + "]";
	}

	public String getStyle()
	{
		return style;
	}

	public void setStyle(String style)
	{
		this.style = style;
	}

}
