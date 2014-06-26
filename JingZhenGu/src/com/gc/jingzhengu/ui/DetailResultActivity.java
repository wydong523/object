/**
 * Project Name:JingZhenGu
 * File Name:DetailResultActivity.java
 * Package Name:com.gc.jingzhengu.ui
 * Date:2014-6-25上午9:46:24
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.ui;

import com.gc.jingzhengu.R;
import com.gc.jingzhengu.app.ActivityHelp;
import com.gc.jingzhengu.app.AppManager;
import com.gc.jingzhengu.vo.DetailResult;

import android.app.ActivityManager;
import android.database.MergeCursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * ClassName:DetailResultActivity <br/>
 * Function: 生成的详细结果界面. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-25 上午9:46:24 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class DetailResultActivity extends BaseActivity implements
		OnClickListener
{
	private DetailResult mDetailResult;

	private WebView mWebView;

	private ImageButton mReturnBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_result);
		mDetailResult = (DetailResult) getIntent().getSerializableExtra(
				"DetailResult");
		System.out.println(mDetailResult.getmURL());
		init();
	}

	@Override
	public void init()
	{
		super.init();
		// 实例化WebView对象
		mWebView = (WebView) findViewById(R.id.gen_web);
		// 设置WebView属性，能够执行Javascript脚本
		mWebView.getSettings().setJavaScriptEnabled(true);
		// 加载需要显示的网页
		mWebView.loadUrl(mDetailResult.getmURL());
		// mWebView.loadUrl("http://www.baidu.com");
		mReturnBtn = (ImageButton) findViewById(R.id.return_btn);
		mReturnBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId()) {
		case R.id.return_btn:
			AppManager appManager = AppManager.getAppManager();
			appManager.finishOtherActivity();
			break;
		default:
			break;
		}
	}
}
