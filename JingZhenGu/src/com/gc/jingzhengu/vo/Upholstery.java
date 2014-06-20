/**
 * Project Name:JingZhenGu
 * File Name:Upholstery.java
 * Package Name:com.gc.jingzhengu.vo
 * Date:2014-6-6下午12:04:42
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.vo;

import java.io.Serializable;

/**
 * ClassName:Upholstery <br/>
 * Function: 内饰损伤实体. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-6 下午12:04:42 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class Upholstery implements Serializable
{
	// 当前位置
	private int position;

	// 可见或不可见标记
	private int flagPic;

	// 外观名
	private String upholsteryName;

	// 修改选项可见标记
	private int tick;

	public Upholstery(int position, int flagPic, String upholsteryName, int tick)
	{
		super();
		this.position = position;
		this.flagPic = flagPic;
		this.upholsteryName = upholsteryName;
		this.tick = tick;
	}

	public int getPosition()
	{
		return position;
	}

	public void setPosition(int position)
	{
		this.position = position;
	}

	public int getFlagPic()
	{
		return flagPic;
	}

	public void setFlagPic(int flagPic)
	{
		this.flagPic = flagPic;
	}

	public String getUpholsteryName()
	{
		return upholsteryName;
	}

	public void setUpholsteryName(String upholsteryName)
	{
		this.upholsteryName = upholsteryName;
	}

	public int getTick()
	{
		return tick;
	}

	public void setTick(int tick)
	{
		this.tick = tick;
	}

}
