/**
 * Project Name:InvoiceCADA
 * File Name:BaseActivity.java
 * Package Name:com.jzg.invoicecada.ui
 * Date:2014-4-16下午5:27:58
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.jzg.invoicecada.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.jzg.invoicecada.app.AppContext;
import com.jzg.invoicecada.app.AppManager;

/**
 * ClassName:BaseActivity <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-16 下午5:27:58 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class BaseActivity extends Activity
{
	public AppContext appContext;

	protected SharedPreferences configPres;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		appContext = (AppContext) getApplicationContext();
		configPres = getPreferences(MODE_PRIVATE);
		initConfigPres();
		// 添加Activity到堆栈
		AppManager.getAppManager().addActivity(this);
	}

	private void initConfigPres()
	{
		configPres.edit().putBoolean("flashOnOff", false).commit();
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		// 结束Activity&从堆栈中移除
		AppManager.getAppManager().finishActivity(this);
	}

	protected void showInfo(String string)
	{
		appContext.showInfo(string, this);
	}

	protected void showError(String info)
	{
		appContext.showError(info, this);
	}
}
