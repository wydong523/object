/**
 * Project Name:JingZhenGu
 * File Name:YearMonthDialog.java
 * Package Name:com.gc.jingzhengu.view
 * Date:2014-5-28上午10:55:51
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gc.jingzhengu.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * ClassName:YearMonthDialog <br/>
 * Function: 选择年月的Dialog. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-5-28 上午10:55:51 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class YearMonthDialog extends Dialog
{
	private Context context;

	private ListView yearList;

	private ListView monthList;

	private List<Map<String, String>> months = new ArrayList<Map<String, String>>();

	private List<Map<String, String>> years = new ArrayList<Map<String, String>>();

	public YearMonthDialog(Context context, int theme)
	{

		super(context, theme);
		this.context = context;
		for (int i = 0; i < 20; i++)
		{
			Map<String, String> aa = new HashMap<String, String>();
			aa.put("year", "2013");
			months.add(aa);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.year_month_list);
		setCanceledOnTouchOutside(true);
		yearList = (ListView) findViewById(R.id.year_list);
		monthList = (ListView) findViewById(R.id.month_list);
		yearList.setAdapter(new SimpleAdapter(context, months,
				R.layout.textview_content, new String[] { "year" },
				new int[] { R.id.content }));
		monthList.setAdapter(new SimpleAdapter(context, months,
				R.layout.textview_content, new String[] { "year" },
				new int[] { R.id.content }));
		// Calendar.getInstance();
		// mYear = c.get(Calendar.YEAR);
		// mMonth = c.get(Calendar.MONTH);
	}
}
