/**
 * Project Name:JingZhenGu
 * File Name:Model.java
 * Package Name:com.gc.jingzhengu.vo
 * Date:2014-6-13上午11:34:30
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.vo;

import java.io.Serializable;

/**
 * ClassName:Model <br/>
 * Function: 车系实体对象. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-13 上午11:34:30 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class Model implements Serializable
{
	/**
	 * 车系id
	 */
	private int id;

	/**
	 * 车系名称
	 */
	private String name;

	/**
	 * 厂商名称
	 */
	private String manufacturerName;

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

	public String getManufacturerName()
	{
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName)
	{
		this.manufacturerName = manufacturerName;
	}

	public int getFontColor()
	{
		return fontColor;
	}

	public void setFontColor(int fontColor)
	{
		this.fontColor = fontColor;
	}

	@Override
	public String toString()
	{
		return "Model [id=" + id + ", name=" + name + ", manufacturerName="
				+ manufacturerName + ", fontColor=" + fontColor + "]";
	}

	
}
