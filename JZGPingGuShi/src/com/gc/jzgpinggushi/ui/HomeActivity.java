/**
 * Project Name:JZGPingGuShi
 * File Name:HomeActivity.java
 * Package Name:com.gc.jzgpinggushi.ui
 * Date:2014-9-3下午5:39:20
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.ui;

import java.util.ArrayList;

import com.example.jzgpinggushi.R;
import com.gc.jzgpinggushi.adapter.GridAdapter;
import com.gc.jzgpinggushi.app.ActivityHelp;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

/**
 * ClassName:HomeActivity <br/>
 * Function: 主页. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-3 下午5:39:20 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class HomeActivity extends BaseActivity implements OnItemClickListener
{
	private static ArrayList<String> mNames = new ArrayList<String>();

	private static ArrayList<Integer> mImgs = new ArrayList<Integer>();

	private GridView mGrid;

	private Activity mActivity = this;

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
		super.init();
		mGrid = (GridView) findViewById(R.id.operate_grid);
		mGrid.setSelector(new ColorDrawable(Color.TRANSPARENT));
		// mGrid.setEnabled(false);
		mGrid.setAdapter(new GridAdapter(this, mNames, mImgs));
		mGrid.setOnItemClickListener(this);
	}

	static
	{
		mNames.add("最新车源");
		mNames.add("已出价车辆");
		mNames.add("出价成功");
		mNames.add("按品牌查找");
		mNames.add("用户申请");

		mImgs.add(R.drawable.zuixin);
		mImgs.add(R.drawable.yichujia);
		mImgs.add(R.drawable.chenggong);
		mImgs.add(R.drawable.cheyuan);
		mImgs.add(R.drawable.shenqing);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{
		System.out.println("viewgird" + position);
		switch (mImgs.get(position)) {
		// 按品牌查找
		case R.drawable.cheyuan:
			ActivityHelp.startActivity(mActivity, IndexCarActivity.class);
			break;
		// 已出价车辆
		case R.drawable.yichujia:
			ActivityHelp.startActivity(mActivity, OfferListActivity.class);
			break;
		// 出价成功
		case R.drawable.chenggong:
			ActivityHelp.startActivity(mActivity,
					OfferSuccessListActivity.class);
			break;
		// 最新车源
		case R.drawable.zuixin:
			ActivityHelp.startActivity(mActivity, NewCarListActivity.class);
			break;
		// 用户申请
		case R.drawable.shenqing:
			ActivityHelp.startActivity(mActivity, UserApplyActiviy.class);
			break;
		}
	}
}
