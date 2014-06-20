/**
 * Project Name:JingZhenGu
 * File Name:Car.java
 * Package Name:com.gc.jingzhengu.vo
 * Date:2014-6-16下午12:29:04
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.vo;

import java.io.Serializable;

/**
 * ClassName:Car <br/>
 * Function: 汽车实体. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-16 下午12:29:04 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class Car implements Serializable
{
	/**
	 * 品牌名
	 */
	private String makeName;

	/**
	 * 品牌id
	 */
	private int makeid;

	/**
	 * 车系名
	 */
	private String modelName;

	/**
	 * 车系id
	 */
	private int modelid;

	/**
	 * 车型名
	 */
	private String styleName;

	/**
	 * 车型id
	 */
	private int styleid;

	public int getMakeid()
	{
		return makeid;
	}

	public void setMakeid(int makeid)
	{
		this.makeid = makeid;
	}

	public int getModelid()
	{
		return modelid;
	}

	public void setModelid(int modelid)
	{
		this.modelid = modelid;
	}

	public int getStyleid()
	{
		return styleid;
	}

	public void setStyleid(int styleid)
	{
		this.styleid = styleid;
	}

	public String getMakeName()
	{
		return makeName;
	}

	public void setMakeName(String makeName)
	{
		this.makeName = makeName;
	}

	public String getModelName()
	{
		return modelName;
	}

	public void setModelName(String modelName)
	{
		this.modelName = modelName;
	}

	public String getStyleName()
	{
		return styleName;
	}

	public void setStyleName(String styleName)
	{
		this.styleName = styleName;
	}

	@Override
	public String toString()
	{
		return "Car [makeName=" + makeName + ", makeid=" + makeid
				+ ", modelName=" + modelName + ", modelid=" + modelid
				+ ", styleName=" + styleName + ", styleid=" + styleid + "]";
	}

}
