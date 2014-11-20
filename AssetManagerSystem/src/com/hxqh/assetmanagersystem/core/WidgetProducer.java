/**
 * Project Name:AssetManagerSystem
 * File Name:ViewProducer.java
 * Package Name:com.hxqh.assetmanagersystem.core
 * Date:2014-1-2上午10:00:37
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.hxqh.assetmanagersystem.core;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxqh.assetmanagersystem.R;
import com.hxqh.assetmanagersystem.vo.EditTextAttr;
import com.hxqh.assetmanagersystem.vo.TextViewAttr;

/**
 * ClassName:WidgetProducer <br/>
 * Function: 控件生成器类：根据解析的xml数据进行控件生成<br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-1-2 上午10:00:37 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class WidgetProducer
{
	private static WidgetProducer widgetProducer = null;

	private WidgetProducer()
	{
	}

	public static synchronized WidgetProducer getInstance()
	{
		if (widgetProducer == null)
		{
			widgetProducer = new WidgetProducer();
		}
		return widgetProducer;
	}

	public RelativeLayout createRelativeLayout(Context context,
			com.hxqh.assetmanagersystem.vo.RelativeLayoutAttr attr)
	{
		RelativeLayout layout = new RelativeLayout(context);
		layout.setId(attr.getId());
		// RelativeLayout layout = new RelativeLayout(context, null,
		// R.style.RelativeLayout);
		return layout;
	}

	public TextView createTextView(Context context,
			com.hxqh.assetmanagersystem.vo.TextViewAttr attr)
	{
		TextView view = new TextView(context);
		view.setId(attr.getId());
		view.setText(attr.getText());
		view.setTextSize(18);
		addRule(view, attr);
		// TextView view = new TextView(context, null, attr.getStyle());
		// view.setText("哈哈");
		// view.setTextSize(attr.getSize());
		// view.setTypeface(Typeface.DEFAULT, attr.getStyle());
		// view.setTypeface(Typeface.DEFAULT, R.style.TextView);
		return view;
	}

	public EditText createEditText(Context context,
			com.hxqh.assetmanagersystem.vo.EditTextAttr attr)
	{
		EditText view = new EditText(context);
		view.setBackgroundResource(R.drawable.edit_bg2);
		view.setHint(attr.getHint());
		view.setRawInputType(InputType.TYPE_CLASS_NUMBER);
		view.setId(attr.getId());
		view.setSingleLine();
		addRule(view, attr);
		// view.setRawInputType(attr.getInputType());
		// view.setTypeface(Typeface.DEFAULT, attr.getStyle());
		// view.setTypeface(Typeface.SERIF, R.style.EditView);
		return view;
	}

	private void addRule(TextView view, TextViewAttr attr)
	{
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		// params.addRule(RelativeLayout.ALIGN_BASELINE);
		// params.addRule(RelativeLayout.ALIGN_BOTTOM);
		// params.addRule(RelativeLayout.CENTER_VERTICAL);
		// params.setMargins(0, 4, 7, 0);
		int oneid = attr.getLayoutAlignBaseline();
		int twoid = attr.getLayoutAlignBottom();
		params.addRule(RelativeLayout.ALIGN_BASELINE, oneid);
		params.addRule(RelativeLayout.ALIGN_BOTTOM, twoid);
		view.setLayoutParams(params);
	}

	private void addRule(EditText view, EditTextAttr attr)
	{
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		int oneid = attr.getLayoutToRightOf();
		params.addRule(RelativeLayout.RIGHT_OF, oneid);
		view.setLayoutParams(params);
	}

}
