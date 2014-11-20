/**
 * Project Name:AssetManagerSystem
 * File Name:EditText.java
 * Package Name:com.hxqh.assetmanagersystem.vo
 * Date:2014-1-2…œŒÁ11:08:51
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.hxqh.assetmanagersystem.vo;

import java.io.Serializable;

/**
 * ClassName:EditText <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-1-2 …œŒÁ11:08:51 <br/>
 * 
 * @author ÕÙ”Â∂∞
 * @version
 * @since JDK 1.6
 * @see
 */
public class EditTextAttr implements Serializable
{

	private int id;

	private int parentId;

	private String hint;

	private int inputType;

	private int style;
	
	private int layoutToRightOf;

	public int getStyle()
	{
		return style;
	}

	public void setStyle(int style)
	{
		this.style = style;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getParentId()
	{
		return parentId;
	}

	public void setParentId(int parentId)
	{
		this.parentId = parentId;
	}

	public String getHint()
	{
		return hint;
	}

	public void setHint(String hint)
	{
		this.hint = hint;
	}

	public int getInputType()
	{
		return inputType;
	}

	public void setInputType(int inputType)
	{
		this.inputType = inputType;
	}

	public int getLayoutToRightOf()
	{
		return layoutToRightOf;
	}

	public void setLayoutToRightOf(int layoutToRightOf)
	{
		this.layoutToRightOf = layoutToRightOf;
	}

}
