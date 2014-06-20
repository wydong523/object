/**
 * Project Name:JingZhenGu
 * File Name:DialogManager.java
 * Package Name:com.gc.jingzhengu.uitls
 * Date:2014-5-28上午11:14:39
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.uitls;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;

import com.gc.jingzhengu.R;
import com.gc.jingzhengu.view.YearMonthDialog;

/**
 * ClassName:DialogManager <br/>
 * Function: Dialog控制管理. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-5-28 上午11:14:39 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class DialogManager
{
	private Dialog showDialog(Context context)
	{
		YearMonthDialog dialog = new YearMonthDialog(context, R.style.dialog);
		dialog.show();
		return dialog;
	}

	/**
	 * @Title: show
	 * @Description: TODO
	 * @param @param dialogEntity
	 * @param @param dialogListBg
	 * @param @return
	 * @return PopDialog
	 * @throws
	 */
	public void show(Context context, Activity activity, int resId)
	{
		Dialog dialog = showDialog(context);
		setDialogScale(activity, dialog, resId);
	}

	/**
	 * 设置Dialog弹出窗口大小
	 * 
	 * @param context
	 * @param dialog
	 */
	public void setDialogScale(Activity activity, Dialog dialog, int resId)
	{
		Display display = activity.getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		Window window = dialog.getWindow();
		window.setLayout(width * 2 / 3, height / 2);
		window.setGravity(Gravity.CENTER);
		window.setBackgroundDrawableResource(resId);
	}
}
