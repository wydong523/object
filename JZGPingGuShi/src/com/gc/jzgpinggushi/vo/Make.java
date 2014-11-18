/**
 * Project Name:JingZhenGu
 * File Name:Make.java
 * Package Name:com.gc.jingzhengu.vo
 * Date:2014-6-12下午4:12:57
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.vo;

import java.io.Serializable;

/**
 * ClassName:Make <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: 品牌实体. <br/>
 * Date: 2014-6-12 下午4:12:57 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class Make implements Serializable
{
	private int makeId;

	private String makeName;

	private String makeLogo;

	private String groupName;
	
	private int fontColor;
	

	public int getFontColor()
	{
		return fontColor;
	}

	public void setFontColor(int fontColor)
	{
		this.fontColor = fontColor;
	}

	public int getMakeId()
	{
		return makeId;
	}

	public void setMakeId(int makeId)
	{
		this.makeId = makeId;
	}

	public String getMakeName()
	{
		return makeName;
	}

	public void setMakeName(String makeName)
	{
		this.makeName = makeName;
	}

	public String getMakeLogo()
	{
		return makeLogo;
	}

	public void setMakeLogo(String makeLogo)
	{
		this.makeLogo = makeLogo;
	}

	public String getGroupName()
	{
		return groupName;
	}

	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}
}
