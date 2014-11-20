/**
 * Project Name:AssetManagerSystem
 * File Name:IXmlParser.java
 * Package Name:com.hxqh.assetmanagersystem.core
 * Date:2013-12-29下午7:55:43
 * Copyright (c) 2013, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.hxqh.assetmanagersystem.core;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import android.app.Activity;
import android.content.Context;

/**
 * ClassName:IXmlParser <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2013-12-29 下午7:55:43 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public interface IXmlManager
{
	/**
	 * 根据xml文件的路径解析xml数据 parserXmlByPath: <br/>
	 * 
	 * @author wang
	 * @param path
	 *            xml文件路径
	 * @param context
	 *            当前context
	 * @return 返回true则解析数据成功，否则，解析数据失败
	 * @since JDK 1.6
	 */
	ArrayList<LinkedHashMap<String, String>> parserXmlByPath(Context context,
			String path);
}
