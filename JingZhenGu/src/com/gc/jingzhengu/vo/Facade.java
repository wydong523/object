/**
 * Project Name:JingZhenGu
 * File Name:Facade.java
 * Package Name:com.gc.jingzhengu.vo
 * Date:2014-6-5下午5:28:44
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.vo;

/**
 * ClassName:Facade <br/>
 * Function: 外观实体. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-5 下午5:28:44 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class Facade
{
	// 当前位置
	private int position;

	// 可见或不可见标记
	private int flagPic;

	// 外观名
	private String facadeName;

	// 勾
	private int tick;

	public Facade(int position, int flagPic, String facadeName, int tick)
	{
		super();
		this.position = position;
		this.flagPic = flagPic;
		this.facadeName = facadeName;
		this.tick = tick;
	}

	public int getFlagPic()
	{
		return flagPic;
	}

	public void setFlagPic(int flagPic)
	{
		this.flagPic = flagPic;
	}

	public int getPosition()
	{
		return position;
	}

	public void setPosition(int position)
	{
		this.position = position;
	}

	public String getFacadeName()
	{
		return facadeName;
	}

	public void setFacadeName(String facadeName)
	{
		this.facadeName = facadeName;
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
