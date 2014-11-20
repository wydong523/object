/**
 * Project Name:AssetManagerSystem
 * File Name:ViewManager.java
 * Package Name:com.hxqh.assetmanagersystem.core
 * Date:2013-12-29下午7:35:07
 * Copyright (c) 2013, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.hxqh.assetmanagersystem.core;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.hxqh.assetmanagersystem.vo.EditTextAttr;
import com.hxqh.assetmanagersystem.vo.RelativeLayoutAttr;
import com.hxqh.assetmanagersystem.vo.TextViewAttr;

import android.content.Context;
import android.content.Entity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * ClassName:ViewManager <br/>
 * Function: 视图管理类(逻辑功能主控对象): 1、xml解析管理 2、封装解析数据 3、组装界面元素 4、下载/上传xml数据
 * 5、生成/保存xml数据 Reason: TODO ADD REASON. <br/>
 * Date: 2013-12-29 下午7:35:07 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class ViewManager implements IViewManager
{

	private Context context;

	private ArrayList<LinkedHashMap<String, String>> elements;

	private XmlManager xmlManager;

	private String filePath;

	private WidgetProducer producer;

	private LinkedHashMap<Integer, RelativeLayout> relativeLayouts;

	private LinkedHashMap<Integer, EditText> editTexts;

	private LinkedHashMap<Integer, TextView> textViews;

	private RelativeViewAttrManager relativeViewAttrManager;

	public ViewManager(Context context, String filePath)
	{
		super();
		this.context = context;
		this.filePath = filePath;
	}

	@Override
	public ArrayList<LinkedHashMap<String, String>> parserXmlByContent(
			String xmlContent)
	{
		return null;
	}

	@Override
	public ArrayList<LinkedHashMap<String, String>> parserXmlByPath(
			String filePath)
	{
		xmlManager = new XmlManager();
		return xmlManager.parserXmlByPath(context, filePath);
	}

	@Override
	public void generateXml(Serializable serializable)
	{

	}

	@Override
	public void saveXml(String path, String templateName, String fileName)
	{

	}

	@Override
	public void uploadXml(URL url, String filePath)
	{

	}

	@Override
	public void downloadXml(URL url, ArrayList<String> templateName)
	{

	}

	@Override
	public LinearLayout createLinearLayout(LinearLayout linearLayout)
	{
		relativeLayouts = new LinkedHashMap<Integer, RelativeLayout>();

		editTexts = new LinkedHashMap<Integer, EditText>();

		textViews = new LinkedHashMap<Integer, TextView>();

		relativeViewAttrManager = new RelativeViewAttrManager();

		elements = parserXmlByPath(filePath);

		boolean isAssembly = relativeViewAttrManager
				.assemblyAttributes(elements);

		producer = WidgetProducer.getInstance();

		// 根据属性创建控件，并添加到控件集合
		if (isAssembly
				&& (RelativeViewAttrManager.layoutAttrs.size() > 0
						|| RelativeViewAttrManager.editTextAttrs.size() > 0 || RelativeViewAttrManager.textViewAttrs
						.size() > 0))
		{
			for (Serializable layoutAttr : RelativeViewAttrManager.layoutAttrs)
			{
				RelativeLayoutAttr attr = (RelativeLayoutAttr) layoutAttr;
				RelativeLayout layout = producer.createRelativeLayout(context,
						attr);
				relativeLayouts.put(attr.getId(), layout);
			}

			for (Serializable editTextAttr : RelativeViewAttrManager.editTextAttrs)
			{
				EditTextAttr attr = (EditTextAttr) editTextAttr;
				EditText edit = producer.createEditText(context, attr);
				// editTexts.put(attr.getParentId(), edit);
				relativeLayouts.get(attr.getParentId()).addView(edit);
			}

			for (Serializable textViewAttr : RelativeViewAttrManager.textViewAttrs)
			{
				TextViewAttr attr = (TextViewAttr) textViewAttr;
				TextView textView = producer.createTextView(context, attr);
				// textViews.put(attr.getParentId(), textView);
				relativeLayouts.get(attr.getParentId()).addView(textView);
			}

			// Set<Entry<Integer, RelativeLayout>> relativeLayouts.entrySet();

			for (Entry<Integer, RelativeLayout> entrySetObject : relativeLayouts
					.entrySet())
			{
				System.out.println("entrySet key is " + entrySetObject.getKey()
						+ "**" + "entrySet value is"
						+ entrySetObject.getValue());
				linearLayout.addView(entrySetObject.getValue());
			}

		}

		return linearLayout;
	}
}
