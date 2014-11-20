/**
 * Project Name:AssetManagerSystem
 * File Name:TextView.java
 * Package Name:com.hxqh.assetmanagersystem.vo
 * Date:2014-1-2…œŒÁ10:59:40
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.hxqh.assetmanagersystem.vo;

import java.io.Serializable;

/**
 * ClassName:TextView <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-1-2 …œŒÁ10:59:40 <br/>
 * 
 * @author ÕÙ”Â∂∞
 * @version
 * @since JDK 1.6
 * @see
 */
public class TextViewAttr implements Serializable
{
	private int id;

	private int parentId;

	private String text;

	private int size;

	private int style;

	private int layoutAlignBaseline;

	private int layoutAlignBottom;

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

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	public int getStyle()
	{
		return style;
	}

	public void setStyle(int style)
	{
		this.style = style;
	}

	public int getLayoutAlignBaseline()
	{
		return layoutAlignBaseline;
	}

	public void setLayoutAlignBaseline(int layoutAlignBaseline)
	{
		this.layoutAlignBaseline = layoutAlignBaseline;
	}

	public int getLayoutAlignBottom()
	{
		return layoutAlignBottom;
	}

	public void setLayoutAlignBottom(int layoutAlignBottom)
	{
		this.layoutAlignBottom = layoutAlignBottom;
	}

	// TextView textView = new TextView(this);
	// textView.setId(R.id.text);
	// textView.setText("ÕÙ”Â∂∞");
	// textView.setTextSize(18);
	// textView.setTypeface(Typeface.DEFAULT, 0x7f070002);
}
