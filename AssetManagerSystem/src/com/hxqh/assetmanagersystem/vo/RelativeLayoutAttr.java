/**
 * Project Name:AssetManagerSystem
 * File Name:RelativeLayout.java
 * Package Name:com.hxqh.assetmanagersystem.vo
 * Date:2014-1-2ÏÂÎç4:29:43
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.hxqh.assetmanagersystem.vo;

import java.io.Serializable;

/**
 * ClassName:RelativeLayout <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-1-2 ÏÂÎç4:29:43 <br/>
 * 
 * @author ÍôÓå¶°
 * @version
 * @since JDK 1.6
 * @see
 */
public class RelativeLayoutAttr implements Serializable
{
	private int id;

	private String type;

	private int style;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public int getStyle()
	{
		return style;
	}

	public void setStyle(int style)
	{
		this.style = style;
	}

}
