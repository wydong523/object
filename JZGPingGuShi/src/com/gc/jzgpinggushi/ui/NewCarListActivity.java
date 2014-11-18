/**
 * Project Name:JZGPingGuShi
 * File Name:NewCarActivity.java
 * Package Name:com.gc.jzgpinggushi.ui
 * Date:2014-9-9下午12:14:52
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.ui;

import java.util.ArrayList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;

import com.example.jzgpinggushi.R;
import com.gc.jzgpinggushi.adapter.NewCarAdapter;
import com.gc.jzgpinggushi.adapter.OfferSuccessAdapter;
import com.gc.jzgpinggushi.adapter.QueryCarAdapter;
import com.gc.jzgpinggushi.app.ActivityHelp;
import com.gc.jzgpinggushi.app.HttpService;
import com.gc.jzgpinggushi.ui.QueryCarActivity.AddBroadCast;
import com.gc.jzgpinggushi.uitls.MessageUtils;
import com.gc.jzgpinggushi.view.pulllist.XListView;
import com.gc.jzgpinggushi.view.pulllist.XListView.IXListViewListener;
import com.gc.jzgpinggushi.vo.NewCar;
import com.gc.jzgpinggushi.vo.NewCarList;
import com.gc.jzgpinggushi.vo.Offer;
import com.gc.jzgpinggushi.vo.QueryCar;

/**
 * ClassName:NewCarActivity <br/>
 * Function: 最新车源列表界面. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-9 下午12:14:52 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class NewCarListActivity extends BaseActivity implements
		IXListViewListener, OnItemClickListener, OnClickListener
{
	private String FILTER = "com.gc.jzgpinggushi.ui.newCarPrice";

	private NewCarPriceBroadCast mNewCarPriceBroadCast;

	private XListView mNewCarListView;

	private ImageButton returnBtn;

	private NewCarAdapter mAdapter;

	private Handler mHandler;

	private int mOldLoc = 0;

	/**
	 * 缓存已出价车辆列表数据
	 */
	private ArrayList<NewCar> mCacheNewCarList = new ArrayList<NewCar>();

	private ArrayList<NewCar> mNewCarList;

	/**
	 * 每次刷新的数据
	 */
	private int mPageCount = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_car_list);
		mHandler = getHandler();
		init();
		mDialog = mDialogManager.show(this, this, R.drawable.bg_loading);
		startNewCarThread();
	}

	private void startNewCarThread()
	{
		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					NewCarList newCarList = HttpService.getNewCarList(
							mPageCount, appContext.getPgsid());
					ArrayList<NewCar> newCar = newCarList.getNewCars();
					if (mOldLoc >= newCar.size())
					{
						// 当列表数据加载完毕、隐藏加载更多
						MessageUtils.sendMessage(mHandler, R.id.hide_footer,
								null);
					} else
					{
						mCacheNewCarList.addAll(newCar.subList(mOldLoc,
								newCar.size()));
						mOldLoc += 10;
						System.out.println("mOldLoc is " + mOldLoc);
						MessageUtils.sendMessage(mHandler, R.id.new_car_list,
								newCar);
					}
				} catch (Exception e)
				{
					e.printStackTrace();
					System.out.println("startOfferCarThread Exception is" + e);
				}
			}
		}).start();
	}

	public Handler getHandler()
	{
		return new Handler()
		{

			@Override
			public void handleMessage(Message msg)
			{
				mDialog.dismiss();
				mNewCarList = (ArrayList<NewCar>) msg.obj;
				switch (msg.what) {
				case R.id.new_car_list:
					showCarList(mNewCarList);
					break;
				case R.id.hide_footer:
					if (mCacheNewCarList.size() == 0)
					{
						showInfo("尊敬的用户，服务器没有查询到相关数据！！");
					}
					mNewCarListView.setPullLoadEnable(false);
					break;
				default:
					break;
				}
			}

			private void showCarList(ArrayList<NewCar> newCars)
			{
				if (mOldLoc > 10)
				{
					mAdapter.notifyDataSetChanged();
				} else
				{
					if (newCars.size() < 5)
					{
						mNewCarListView.getmFooterView().hide();
					} else
					{
						mNewCarListView.setPullLoadEnable(true);
					}
					mAdapter = new NewCarAdapter(getApplicationContext(),
							mCacheNewCarList);
					mNewCarListView.setAdapter(mAdapter);
				}

			}
		};
	}

	@Override
	public void init()
	{
		super.init();
		IntentFilter filter = new IntentFilter(FILTER);
		mNewCarPriceBroadCast = new NewCarPriceBroadCast();
		registerReceiver(mNewCarPriceBroadCast, filter);

		mNewCarListView = (XListView) findViewById(R.id.new_car_list);
		mNewCarListView.setPullLoadEnable(true);
		mNewCarListView.setPullRefreshEnable(false);
		mNewCarListView.setXListViewListener(this);
		mNewCarListView.setOnItemClickListener(this);

		returnBtn = (ImageButton) findViewById(R.id.return_btn);
		returnBtn.setOnClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{
		appContext.setAddPosition(position);
		appContext.setDetailFlag("newCarPrice");
		appContext.setModelFlag("zxcy");// 最新车源模块标记
		NewCar newCar = (NewCar) mCacheNewCarList.get(position - 1);
		ActivityHelp.startActivity(getApplicationContext(),
				DetailResultActivity.class, "newCar", newCar,
				Intent.FLAG_ACTIVITY_NEW_TASK);
	}

	@Override
	public void onRefresh()
	{

	}

	@Override
	public void onLoadMore()
	{
		mPageCount += 10;
		startNewCarThread();
		mNewCarListView.stopRefresh();
		mNewCarListView.stopLoadMore();
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		mPageCount = 10;
	}

	/**
	 * 新增出价广播接收器 ClassName: UploadBroadCast <br/>
	 * Function: TODO ADD FUNCTION. <br/>
	 * Reason: TODO ADD REASON. <br/>
	 * date: 2014-10-20 下午12:16:42 <br/>
	 * 
	 * @author wang
	 * @version OfferSuccessListActivity
	 * @since JDK 1.6
	 */
	class NewCarPriceBroadCast extends BroadcastReceiver
	{
		@Override
		public void onReceive(Context context, Intent intent)
		{
			System.out.println("newcar " + "intent is " + intent.getAction());
			int position = appContext.getAddPosition();
			mCacheNewCarList.remove(position - 1);
			mAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId()) {
		case R.id.return_btn:
			finish();
			break;
		default:
			break;
		}
	}

}
