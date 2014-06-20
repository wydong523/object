/**
 * Project Name:JingZhenGu
 * File Name:YearMonth.java
 * Package Name:com.gc.jingzhengu.vo
 * Date:2014-5-28上午9:42:15
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.vo;

import java.io.Serializable;

/**
 * ClassName:YearMonth <br/>
 * Function: 年月选择列表对象. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-5-28 上午9:42:15 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class YearMonth implements Serializable
{
	private String title;

	private String year;

	private String month;

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getYear()
	{
		return year;
	}

	public void setYear(String year)
	{
		this.year = year;
	}

	public String getMonth()
	{
		return month;
	}

	public void setMonth(String month)
	{
		this.month = month;
	}

}
