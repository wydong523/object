/**
 * Project Name:JZGPingGuShi
 * File Name:User.java
 * Package Name:com.gc.jzgpinggushi.vo
 * Date:2014-10-14上午11:08:34
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
*/

package com.gc.jzgpinggushi.vo;

import java.io.Serializable;

/**
 * ClassName:User <br/>
 * Function: 用户实体对象. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-10-14 上午11:08:34 <br/>
 * @author   汪渝栋
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class User extends BaseObject implements Serializable
{
	/**
	 * 登录名
	 */
	private String username;
	
	/**
	 * 登录密码
	 */
	private String password;
	
	/**
	 * 评估师Id
	 */
	private int pgsid;

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public int getPgsid()
	{
		return pgsid;
	}

	public void setPgsid(int pgsid)
	{
		this.pgsid = pgsid;
	}

	@Override
	public String toString()
	{
		return "User [username=" + username + ", password=" + password
				+ ", pgsid=" + pgsid + "]";
	}
	
}

