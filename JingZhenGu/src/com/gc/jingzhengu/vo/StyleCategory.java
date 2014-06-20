/**
 * Project Name:JingZhenGu
 * File Name:StyleCategory.java
 * Package Name:com.gc.jingzhengu.vo
 * Date:2014-6-19上午10:23:12
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:StyleCategory <br/>
 * Function: 车型分类实体. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-19 上午10:23:12 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class StyleCategory implements Serializable
{
	private String yearTitle;

	private List<Style> categoryItem = new ArrayList<Style>();

	public StyleCategory(String string)
	{
		yearTitle = string;
	}

	public String getYearTitle()
	{
		return yearTitle;
	}

	public void setYearTitle(String yearTitle)
	{
		this.yearTitle = yearTitle;
	}

	public List<Style> getCategoryItem()
	{
		return categoryItem;
	}

	public void setCategoryItem(List<Style> categoryItem)
	{
		this.categoryItem = categoryItem;
	}

	
	public void addItem(Style pItemName)
	{
		categoryItem.add(pItemName);
	}

	/**
	 * 获取Item内容
	 * 
	 * @param pPosition
	 * @return
	 */
	public String getItem(int pPosition)
	{
		// Category排在第一位
		if (pPosition == 0)
		{
			return yearTitle;
		} else
		{
			return categoryItem.get(pPosition - 1).getName();
		}
	}

	/**
	 * 获取当前位置字体颜色 getFontColor: <br/>
	 * 
	 * @author wang
	 * @param pPosition
	 * @return
	 * @since JDK 1.6
	 */
	public int getFontColor(int pPosition)
	{
		return categoryItem.get(pPosition - 1).getFontColor();
	}
	
	
	

	/**
	 * 当前类别Item总数。Category也需要占用一个Item
	 * 
	 * @return
	 */
	public int getItemCount()
	{
		return categoryItem.size() + 1;
	}
}
