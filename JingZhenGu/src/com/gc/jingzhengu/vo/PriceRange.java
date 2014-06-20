/**
 * Project Name:JingZhenGu
 * File Name:PriceRange.java
 * Package Name:com.gc.jingzhengu.vo
 * Date:2014-6-18上午11:34:47
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.vo;

import java.io.Serializable;

/**
 * ClassName:PriceRange <br/>
 * Function: 价格区间实体. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-18 上午11:34:47 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class PriceRange extends BaseObject implements Serializable
{
	private int appraiseReportId;

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

	@Override
	public String toString()
	{
		return "PriceRange [appraiseReportId=" + appraiseReportId
				+ ", priceRange=" + priceRange + "]";
	}

}
