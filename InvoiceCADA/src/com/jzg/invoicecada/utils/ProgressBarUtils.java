/**
 * Project Name:InvoiceCADA
 * File Name:ProgressBarUtils.java
 * Package Name:com.example.invoicecada
 * Date:2014-4-16上午10:30:40
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.jzg.invoicecada.utils;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.jzg.invoicecada.R;

/**
 * ClassName:ProgressBarUtils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-16 上午10:30:40 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class ProgressBarUtils
{
	private static View view;

	/**
	 * 显示进度条
	 * 
	 * @param dataLoading
	 */
	public static synchronized void visibleProgressBar(Activity activity,
			int proID, int dataID, String dataText)
	{
		view = activity.getWindow().getDecorView();
		view.findViewById(proID).setVisibility(TextView.VISIBLE);
		view.findViewById(dataID).setVisibility(TextView.VISIBLE);
		((TextView) view.findViewById(dataID)).setText(dataText);
	}

	/**
	 * 隐藏进度条
	 */
	public synchronized void hideProgressBar()
	{
		view.findViewById(R.id.progress_bar).setVisibility(TextView.INVISIBLE);
		view.findViewById(R.id.data_is_loading).setVisibility(
				TextView.INVISIBLE);
	}
}
