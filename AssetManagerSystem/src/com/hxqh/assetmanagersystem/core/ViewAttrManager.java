/**
 * Project Name:AssetManagerSystem
 * File Name:ViewAttrManager.java
 * Package Name:com.hxqh.assetmanagersystem.core
 * Date:2014-1-3上午11:02:01
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.hxqh.assetmanagersystem.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import com.hxqh.assetmanagersystem.R;

/**
 * ClassName:ViewAttrManager <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-1-3 上午11:02:01 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class ViewAttrManager implements IViewAttrManager
{
	static final String TYPE = "type";

	static final String RELATIVELAYOUT = "RelativeLayout";

	static final String STYLE = "style";

	static final String ID = "id";

	static final String EDITTEXT = "EditText";

	static final String EDITTEXT_INPUTTYPE = "inputType";

	static final String HINT = "hint";

	static final String PARENTID = "parentId";

	static final String TEXTVIEW = "TextView";

	static final String TEXT = "text";

	static final String LAYOUT_ALIGN_BASELINE = "layout_alignBaseline";

	static final String LAYOUT_ALIGN_BOTTOM = "layout_alignBottom";

	static final String LAYOUT_TO_RIGHT_OF = "layout_toRightOf";

	/**
	 * 控件样式
	 */
	static final LinkedHashMap<String, Integer> styles = new LinkedHashMap<String, Integer>();

	/**
	 * 存放当前xml文件中RelativeLayout属性对象
	 */
	public static LinkedList<Serializable> layoutAttrs = new LinkedList<Serializable>();

	/**
	 * 存放当前xml文件中EditText属性对象
	 */
	public static LinkedList<Serializable> editTextAttrs = new LinkedList<Serializable>();

	/**
	 * 存放当前xml文件中TextView属性对象
	 */
	public static LinkedList<Serializable> textViewAttrs = new LinkedList<Serializable>();

	static
	{
		styles.put("0x7f070005", R.style.RelativeLayout);
		styles.put("0x7f070003", R.style.EditView);
		styles.put("0x7f070002", R.style.TextView);
	}

	@Override
	public boolean assemblyAttributes(
			ArrayList<LinkedHashMap<String, String>> elements)
	{
		return false;
	}

}
