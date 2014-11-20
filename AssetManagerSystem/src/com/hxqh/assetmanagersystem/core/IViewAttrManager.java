/**
 * Project Name:AssetManagerSystem
 * File Name:IViewAttrManager.java
 * Package Name:com.hxqh.assetmanagersystem.core
 * Date:2014-1-3����10:54:32
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.hxqh.assetmanagersystem.core;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.hxqh.assetmanagersystem.R;

/**
 * ClassName:IViewAttrManager <br/>
 * Function: ��װxml�ļ��������ļ��а�������ͼ�ؼ����ԣ�������ͼ�ؼ����Ե���װ<br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-1-3 ����10:54:32 <br/>
 * 
 * @author ���嶰
 * @version
 * @since JDK 1.6
 * @see
 */
public interface IViewAttrManager
{
	/**
	 * 
	 * ��װ���Ե���Ӧ�ļ����б��� assemblyAttributes: <br/>
	 * 
	 * @author wang
	 * @param elements
	 *            ��xml������Ԫ�ؼ���
	 * @return true(�ɹ�)/false(ʧ��)
	 * @since JDK 1.6
	 */
	boolean assemblyAttributes(ArrayList<LinkedHashMap<String, String>> elements);
}
