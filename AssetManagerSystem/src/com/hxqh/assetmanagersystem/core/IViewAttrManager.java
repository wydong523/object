/**
 * Project Name:AssetManagerSystem
 * File Name:IViewAttrManager.java
 * Package Name:com.hxqh.assetmanagersystem.core
 * Date:2014-1-3上午10:54:32
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.hxqh.assetmanagersystem.core;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.hxqh.assetmanagersystem.R;

/**
 * ClassName:IViewAttrManager <br/>
 * Function: 封装xml文件解析后，文件中包含的视图控件属性，及对视图控件属性的组装<br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-1-3 上午10:54:32 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public interface IViewAttrManager
{
	/**
	 * 
	 * 组装属性到对应的集合列表中 assemblyAttributes: <br/>
	 * 
	 * @author wang
	 * @param elements
	 *            从xml解析的元素集合
	 * @return true(成功)/false(失败)
	 * @since JDK 1.6
	 */
	boolean assemblyAttributes(ArrayList<LinkedHashMap<String, String>> elements);
}
