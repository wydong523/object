/**
 * Project Name:JZGPingGuShi
 * File Name:OfferListActivity.java
 * Package Name:com.gc.jzgpinggushi.ui
 * Date:2014-9-9上午10:58:21
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.ui;

import java.util.ArrayList;

import android.app.Activity;
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
import com.gc.jzgpinggushi.adapter.OfferCarAdapter;
import com.gc.jzgpinggushi.app.ActivityHelp;
import com.gc.jzgpinggushi.app.AppContext;
import com.gc.jzgpinggushi.app.HttpService;
import com.gc.jzgpinggushi.uitls.MessageUtils;
import com.gc.jzgpinggushi.view.pulllist.XListView;
import com.gc.jzgpinggushi.view.pulllist.XListView.IXListViewListener;
import com.gc.jzgpinggushi.vo.CarSource;
import com.gc.jzgpinggushi.vo.Offer;
import com.gc.jzgpinggushi.vo.OfferList;

/**
 * ClassName:OfferListActivity <br/>
 * Function: 已出价车辆列表. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-9 上午10:58:21 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class OfferListActivity extends BaseActivity implements
		IXListViewListener, OnItemClickListener, OnClickListener
{
	private String FILTER = "com.gc.jzgpinggushi.ui.upload";

	private UploadBroadCast mUploadBroadCast;

	private Activity mActivity = this;

	private XListView mOfferListView;

	private ImageButton mReturnBtn;

	private OfferCarAdapter mAdapter;

	private Handler mHandler;

	/**
	 * 缓存位置
	 */
	private int mOldLoc = 0;

	/**
	 * 每次刷新的数据
	 */
	private int mPageCount = 10;

	/**
	 * 缓存已出价车辆列表数据
	 */
	private ArrayList<Offer> mCacheOfferCarList = new ArrayList<Offer>();

	private ArrayList<Offer> mOfferList;

	private AppContext mAppContext;

	/**
	 * 更新出价广播接收器 ClassName: UploadBroadCast <br/>
	 * Function: TODO ADD FUNCTION. <br/>
	 * Reason: TODO ADD REASON. <br/>
	 * date: 2014-10-20 下午12:16:42 <br/>
	 * 
	 * @author wang
	 * @version OfferSuccessListActivity
	 * @since JDK 1.6
	 */
	class UploadBroadCast extends BroadcastReceiver
	{
		@Override
		public void onReceive(Context context, Intent intent)
		{
			System.out.println("intent is " + intent.getAction());
			String price = intent.getStringExtra("price");
			int position = intent.getIntExtra("position", 0);
			if (String.valueOf(price).contains("."))
			{
				mCacheOfferCarList.get(position).setMyBidPrice(price);
			} else
			{
				mCacheOfferCarList.get(position).setMyBidPrice(price + ".00");
			}

			mAdapter.notifyDataSetChanged();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.offer_list);
		mAppContext = (AppContext) getApplicationContext();
		mHandler = getHandler();
		init();
		mDialog = mDialogManager.show(this, this, R.drawable.bg_loading);
		startOfferCarThread();
	}

	private void startOfferCarThread()
	{
		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					int pgsid = mAppContext.getPgsid();
					OfferList mOfferCarList = HttpService.getOfferList(pgsid,
							mPageCount);
					ArrayList<Offer> queryOfferCar = mOfferCarList
							.getOfferCars();
					System.out.println("mOldLoc is " + mOldLoc);
					if (mOldLoc >= queryOfferCar.size())
					{
						// 当列表数据加载完毕、隐藏加载更多
						MessageUtils.sendMessage(mHandler, R.id.hide_footer,
								null);
					} else
					{
						mCacheOfferCarList.addAll(queryOfferCar.subList(
								mOldLoc, queryOfferCar.size()));
						mOldLoc += 10;
						MessageUtils.sendMessage(mHandler, R.id.offer_car_list,
								queryOfferCar);
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
				switch (msg.what) {
				case R.id.offer_car_list:
					mOfferList = (ArrayList<Offer>) msg.obj;
					showCarList();
					break;
				case R.id.hide_footer:
					if (mCacheOfferCarList.size() == 0)
					{
						showInfo("尊敬的用户，服务器没有查询到相关数据！！");
					}
					mOfferListView.setPullLoadEnable(false);
					break;
				case R.id.start_offer_activity:
					CarSource carSource = (CarSource) msg.obj;
					mAppContext.setModelFlag("ycjcl");// 已出价车辆模块标记
					startOfferActivity(carSource);
				default:
					break;
				}
			}

			private void showCarList()
			{
				if (mOldLoc > 10)
				{
					mAdapter.notifyDataSetChanged();
				} else
				{
					if (mCacheOfferCarList.size() < 5)
					{
						mOfferListView.getmFooterView().hide();
					} else
					{
						mOfferListView.setPullLoadEnable(true);
					}
					mAdapter = new OfferCarAdapter(mActivity, mOfferList,
							mHandler);
					mOfferListView.setAdapter(mAdapter);
				}
			}
		};
	}

	protected void startOfferActivity(CarSource carSource)
	{
		ActivityHelp.startActivity(this, OfferActivity.class, "carSource",
				carSource);
	}

	@Override
	public void init()
	{
		super.init();
		IntentFilter filter = new IntentFilter(FILTER);
		mUploadBroadCast = new UploadBroadCast();
		registerReceiver(mUploadBroadCast, filter);

		mOfferListView = (XListView) findViewById(R.id.offer_car_list);
		mOfferListView.setPullLoadEnable(true);
		mOfferListView.setPullRefreshEnable(false);
		mOfferListView.setXListViewListener(this);
		mOfferListView.setOnItemClickListener(this);

		mReturnBtn = (ImageButton) findViewById(R.id.return_btn);
		mReturnBtn.setOnClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{

	}

	@Override
	public void onRefresh()
	{

	}

	@Override
	public void onLoadMore()
	{
		mPageCount += 10;
		startOfferCarThread();
		mOfferListView.stopRefresh();
		mOfferListView.stopLoadMore();
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		mPageCount = 10;
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
