/**
 * Project Name:AssetManagerSystem
 * File Name:ViewList.java
 * Package Name:com.hxqh.assetmanagersystem.vo
 * Date:2013-12-29下午4:24:19
 * Copyright (c) 2013, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.hxqh.assetmanagersystem.core;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.text.TextUtils;

import com.hxqh.assetmanagersystem.vo.EditTextAttr;
import com.hxqh.assetmanagersystem.vo.RelativeLayoutAttr;
import com.hxqh.assetmanagersystem.vo.TextViewAttr;

/**
 * ClassName:View <br/>
 * Function: 存储xml配置文件中的元素对象 <br/>
 * Date: 2013-12-29 下午4:24:19 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class RelativeViewAttrManager extends ViewAttrManager
{

	public boolean assemblyAttributes(
			ArrayList<LinkedHashMap<String, String>> elements)
	{
		for (LinkedHashMap<String, String> element : elements)
		{
			String type = element.get(TYPE);
			if (TextUtils.isEmpty(type))
			{
				// TODO 提示用户xml格式文件出错
				break;
			}
			if (type.equals(RELATIVELAYOUT))
			{
				RelativeLayoutAttr relativeLayoutAttr = setRelativeLayoutAttr(element);
				layoutAttrs.add(relativeLayoutAttr);
			} else if (type.equals(EDITTEXT))
			{
				EditTextAttr editTextAttr = setEditTextAttr(element);
				editTextAttrs.add(editTextAttr);
			} else if (type.equals(TEXTVIEW))
			{
				TextViewAttr textViewAttr = setTextViewAttr(element);
				textViewAttrs.add(textViewAttr);
			} else
			{
				return false;
			}
		}
		return true;
	}

	private TextViewAttr setTextViewAttr(LinkedHashMap<String, String> element)
	{
		TextViewAttr textViewAttr = new TextViewAttr();
		textViewAttr.setId(Integer.valueOf(element.get(ID)));
		textViewAttr.setParentId(Integer.valueOf(element.get(PARENTID)));
		// textViewAttr.setStyle(styles.get(element.get(STYLE)));
		textViewAttr.setText(element.get(TEXT));
		textViewAttr.setLayoutAlignBaseline(Integer.valueOf(element
				.get(LAYOUT_ALIGN_BASELINE)));
		textViewAttr.setLayoutAlignBottom(Integer.valueOf(element
				.get(LAYOUT_ALIGN_BOTTOM)));
		return textViewAttr;
	}

	private EditTextAttr setEditTextAttr(LinkedHashMap<String, String> element)
	{
		EditTextAttr editTextAttr = new EditTextAttr();
		editTextAttr.setId(Integer.valueOf(element.get(ID)));
		// editTextAttr.setStyle(styles.get(element.get(STYLE)));
		editTextAttr.setHint(element.get(HINT));
		editTextAttr.setParentId(Integer.valueOf(element.get(PARENTID)));
		editTextAttr.setLayoutToRightOf(Integer.valueOf(element
				.get(LAYOUT_TO_RIGHT_OF)));
		// editTextAttr.setInputType(Integer.valueOf(element
		// .get(EDITTEXT_INPUTTYPE)));
		return editTextAttr;
	}

	private RelativeLayoutAttr setRelativeLayoutAttr(
			LinkedHashMap<String, String> element)
	{
		RelativeLayoutAttr relativeLayoutAttr = new RelativeLayoutAttr();
		// relativeLayoutAttr.setStyle(styles.get(element.get(STYLE)));
		relativeLayoutAttr.setId(Integer.valueOf(element.get(ID)));
		return relativeLayoutAttr;
	}
}
