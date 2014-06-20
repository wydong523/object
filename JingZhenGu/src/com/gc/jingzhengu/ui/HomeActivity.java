/**
 * Project Name:JingZhenGu
 * File Name:HomeActivity.java
 * Package Name:com.gc.jingzhengu.ui
 * Date:2014-5-22下午4:52:03
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.ui;

import com.gc.jingzhengu.R;
import com.gc.jingzhengu.app.ActivityHelp;
import com.gc.jingzhengu.uitls.DialogManager;
import com.gc.jingzhengu.view.YearMonthDialog;

import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * ClassName:HomeActivity <br/>
 * Function: 首页面 <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-5-22 下午4:52:03 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class HomeActivity extends BaseActivity implements OnClickListener
{
	private Button mStartEvaluatingBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		init();

	}

	@Override
	public void init()
	{
		mStartEvaluatingBtn = (Button) findViewById(R.id.start_evaluating_btn);
		mStartEvaluatingBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{

		switch (v.getId()) {
		case R.id.start_evaluating_btn:
			 ActivityHelp.startActivity(this, IndexCarActivity.class);
			break;
		default:
			break;
		}
	}
}
