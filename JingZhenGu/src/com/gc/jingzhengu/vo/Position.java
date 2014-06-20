/**
 * Project Name:JingZhenGu
 * File Name:Position.java
 * Package Name:com.gc.jingzhengu.vo
 * Date:2014-6-5下午10:00:41
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.vo;

/**
 * ClassName:Position <br/>
 * Function: 列表位置实体. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-5 下午10:00:41 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class Position
{
	// 位置
	int position;

	// 是否选中
	boolean ischeckedflag;

	Position(int name, boolean flag)
	{
		position = name;
		ischeckedflag = flag;
	}

	public int getPosition()
	{
		return position;
	}

	public void setPosition(int position)
	{
		this.position = position;
	}

	public boolean isIscheckedflag()
	{
		return ischeckedflag;
	}

	public void setIscheckedflag(boolean ischeckedflag)
	{
		this.ischeckedflag = ischeckedflag;
	}

}
