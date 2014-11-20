/**
 * Project Name:AssetManagerSystem
 * File Name:IXmlManager.java
 * Package Name:com.hxqh.assetmanagersystem.uitls
 * Date:2013-12-25����3:35:46
 * Copyright (c) 2013, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.hxqh.assetmanagersystem.core;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.widget.LinearLayout;

/**
 * 
 * ClassName: IXmlManager <br/>
 * Function: ���������ɡ��ϴ������ء�����xml��ʽ���ݡ�����View<br/>
 * Reason: TODO ADD REASON. <br/>
 * date: 2013-12-25 ����3:41:55 <br/>
 * 
 * @author wang
 * @version
 * @since JDK 1.6
 */
public interface IViewManager
{
	ArrayList<LinkedHashMap<String, String>> parserXmlByContent(String xmlContent);

	ArrayList<LinkedHashMap<String, String>> parserXmlByPath(String filePath);

	void generateXml(Serializable serializable);

	void saveXml(String path, String templateName, String fileName);
	
	void uploadXml(URL url ,String filePath);
	
	void downloadXml(URL url,ArrayList<String> templateName);
	
	LinearLayout createLinearLayout(LinearLayout linearLayout);
	
}
