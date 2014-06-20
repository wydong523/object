/**
 * Project Name:JingZhenGu
 * File Name:Impetus.java
 * Package Name:com.gc.jingzhengu.vo
 * Date:2014-6-11下午4:46:17
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.vo;

import java.io.Serializable;

/**
 * ClassName:Impetus <br/>
 * Function: 动力实体对象. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-11 下午4:46:17 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class Impetus implements Serializable
{
	/**
	 * 当前点击位置
	 */
	private int position;

	/**
	 * 勾标记显示或不可见
	 */
	private int tickFlag;

	/**
	 * 圆记显示或不可见
	 */
	private int circleFlag;

	/**
	 * 数据名称
	 */
	private String name;

	public Impetus(int position, int tickFlag, int circleFlag, String name)
	{
		super();
		this.position = position;
		this.tickFlag = tickFlag;
		this.circleFlag = circleFlag;
		this.name = name;
	}

	public int getPosition()
	{
		return position;
	}

	public void setPosition(int position)
	{
		this.position = position;
	}

	public int getTickFlag()
	{
		return tickFlag;
	}

	public void setTickFlag(int tickFlag)
	{
		this.tickFlag = tickFlag;
	}

	public int getCircleFlag()
	{
		return circleFlag;
	}

	public void setCircleFlag(int circleFlag)
	{
		this.circleFlag = circleFlag;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

}
