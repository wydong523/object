/**
 * Project Name:JZGPingGuShi
 * File Name:UserApplyList.java
 * Package Name:com.gc.jzgpinggushi.vo
 * Date:2014-10-20下午7:41:52
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ClassName:UserApplyList <br/>
 * Function: 用户申请列表实体. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-10-20 下午7:41:52 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class UserApplyList extends BaseObject implements Serializable
{
	private ArrayList<UserApply> userApplys = new ArrayList<UserApply>();

	public ArrayList<UserApply> getUserApplys()
	{
		return userApplys;
	}

	public void setUserApplys(ArrayList<UserApply> userApplys)
	{
		this.userApplys = userApplys;
	}

	@Override
	public String toString()
	{
		return "UserApplyList [userApplys=" + userApplys + "]";
	}

}
