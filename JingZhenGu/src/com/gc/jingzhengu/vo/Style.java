/**
 * Project Name:JingZhenGu
 * File Name:Style.java
 * Package Name:com.gc.jingzhengu.vo
 * Date:2014-6-16上午10:21:03
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.vo;

import java.io.Serializable;

/**
 * ClassName:Style <br/>
 * Function: 车型实体. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-16 上午10:21:03 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class Style implements Serializable
{
	private int id;
	private String name;
	/**
	 * 字体颜色
	 */
	private int fontColor;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getFontColor()
	{
		return fontColor;
	}

	public void setFontColor(int fontColor)
	{
		this.fontColor = fontColor;
	}
}
