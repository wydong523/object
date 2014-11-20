/**
 * Project Name:AssetManagerSystem
 * File Name:XmlParser.java
 * Package Name:com.hxqh.assetmanagersystem.core
 * Date:2013-12-29下午7:54:49
 * Copyright (c) 2013, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.hxqh.assetmanagersystem.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Xml;

/**
 * ClassName:XmlParser <br/>
 * Function: xml数据处理类 Date: 2013-12-29 下午7:54:49 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class XmlManager implements IXmlManager
{
	private XmlPullParser xmlPullParser;

	/**
	 * 视图元素集合(注：xml文件中所有元素)
	 */
	private static ArrayList<LinkedHashMap<String, String>> allElements = new ArrayList<LinkedHashMap<String, String>>();

	@Override
	public synchronized ArrayList<LinkedHashMap<String, String>> parserXmlByPath(
			Context activity, String path)
	{
		if (allElements.size() > 0)
		{
			allElements.clear();
		}

		try
		{
			InputStream inputStream = activity.getAssets().open(path);
			xmlPullParser = Xml.newPullParser();
			xmlPullParser.setInput(inputStream, "UTF-8");

			int eventType = xmlPullParser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT)
			{

				// if (eventType == XmlPullParser.START_DOCUMENT)
				// {
				// System.out.println("Start document"
				// + xmlPullParser.getName());
				// } else if (eventType == XmlPullParser.END_DOCUMENT)
				// {
				// System.out
				// .println("End document" + xmlPullParser.getName());
				// } else

				if (eventType == XmlPullParser.START_TAG)
				{
					System.out.println("start tag :" + xmlPullParser.getName());
					String startTagName = xmlPullParser.getName();
					parserByStartTag(startTagName);
				}
				// else if (eventType == XmlPullParser.END_TAG)
				// {
				// System.out.println("end tag" + xmlPullParser.getName());
				// }
				// else if (eventType == XmlPullParser.TEXT)
				// {
				// System.out.println(xmlPullParser.getText());
				// }
				eventType = xmlPullParser.next();
			}
			System.out.println("allElements is :" + allElements.toString());
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (XmlPullParserException e)
		{
			e.printStackTrace();
		}

		return allElements;
	}

	private void parserByStartTag(String tagName)
	{
		int count = xmlPullParser.getAttributeCount();
		LinkedHashMap<String, String> currentElement = new LinkedHashMap<String, String>();
		for (int i = 0; i < count; i++)
		{
			String attributeName = xmlPullParser.getAttributeName(i);

			String attributeValue = xmlPullParser.getAttributeValue(
					xmlPullParser.getNamespace(), attributeName);

			currentElement.put(attributeName, attributeValue);
		}
		allElements.add(currentElement);
		// System.out.println("currentElement is " + currentElement.toString());
	}

}
