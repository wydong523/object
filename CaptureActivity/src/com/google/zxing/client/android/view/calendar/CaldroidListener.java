package com.google.zxing.client.android.view.calendar;


import java.util.Date;

import android.view.View;

/**
 * 日历事件监听
 * @author 汪渝栋
 *
 */
public abstract class CaldroidListener {
	//日期选中事件
	public abstract void onSelectDate(Date date, View view);

	//日期改变事件
	public void onChangeMonth(int month, int year) {
		// Do nothing
	};
}
