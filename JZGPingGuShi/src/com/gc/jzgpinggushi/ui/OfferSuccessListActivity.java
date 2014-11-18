/**
 * Project Name:JZGPingGuShi
 * File Name:OfferSuccessActivity.java
 * Package Name:com.gc.jzgpinggushi.ui
 * Date:2014-9-9上午11:45:39
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.AdapterView.OnItemClickListener;

import com.example.jzgpinggushi.R;
import com.gc.jzgpinggushi.adapter.OfferSuccessAdapter;
import com.gc.jzgpinggushi.app.AppContext;
import com.gc.jzgpinggushi.app.HttpService;
import com.gc.jzgpinggushi.uitls.MessageUtils;
import com.gc.jzgpinggushi.view.pulllist.XListView;
import com.gc.jzgpinggushi.view.pulllist.XListView.IXListViewListener;
import com.gc.jzgpinggushi.vo.OfferSuccess;
import com.gc.jzgpinggushi.vo.OfferSuccessList;

/**
 * ClassName:OfferSuccessActivity <br/>
 * Function: 出价成功列表. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-9 上午11:45:39 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class OfferSuccessListActivity extends BaseActivity implements
		IXListViewListener, OnItemClickListener, OnClickListener
{
	private ImageButton mReturnBtn;

	private Activity mActivity = this;

	private XListView mOfferSuccessListView;

	private OfferSuccessAdapter mAdapter;

	private Handler mHandler;

	/**
	 * 缓存已出价车辆列表数据
	 */
	private ArrayList<OfferSuccess> mCacheOfferSuccessCarList = new ArrayList<OfferSuccess>();

	private OfferSuccessList mOfferSuccessList;

	private AppContext mAppContext;

	/**
	 * 缓存位置
	 */
	private int mOldLoc = 0;

	/**
	 * 每次刷新的数据
	 */
	private int mPageCount = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.offer_success_list);
		mHandler = getHandler();
		mAppContext = (AppContext) getApplicationContext();
		init();
		mDialog = mDialogManager.show(this, this, R.drawable.bg_loading);
		startOfferSuccessCarThread();
	}

	private void startOfferSuccessCarThread()
	{
		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					mOfferSuccessList = HttpService.getOfferSuccessList(
							mAppContext.getPgsid(), mPageCount);
					ArrayList<OfferSuccess> offerSuccessCar = mOfferSuccessList
							.getOfferSuccesses();
					if (mOldLoc >= offerSuccessCar.size())
					{
						// 当列表数据加载完毕、隐藏加载更多
						MessageUtils.sendMessage(mHandler, R.id.hide_footer,
								null);
					} else
					{
						mCacheOfferSuccessCarList.addAll(offerSuccessCar
								.subList(mOldLoc, offerSuccessCar.size()));
						mOldLoc += 10;
						System.out.println("mOldLoc is " + mOldLoc);
						MessageUtils.sendMessage(mHandler,
								R.id.offer_success_list, offerSuccessCar);
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
				ArrayList<OfferSuccess> offerSuccessCar = (ArrayList<OfferSuccess>) msg.obj;
				switch (msg.what) {
				case R.id.offer_success_list:
					showCarList(offerSuccessCar);
					break;
				case R.id.hide_footer:
					if (mCacheOfferSuccessCarList.size() == 0)
					{
						showInfo("尊敬的用户，服务器没有查询到相关数据！！");
					}
					mOfferSuccessListView.setPullLoadEnable(false);
					break;
				default:
					break;
				}
			}

			private void showCarList(ArrayList<OfferSuccess> offerSuccessCar)
			{

				if (mOldLoc > 10)
				{
					mAdapter.notifyDataSetChanged();
				} else
				{
					if (mCacheOfferSuccessCarList.size() < 5)
					{
						mOfferSuccessListView.getmFooterView().hide();
					} else
					{
						mOfferSuccessListView.setPullLoadEnable(true);
					}
					mAdapter = new OfferSuccessAdapter(mActivity,
							offerSuccessCar);
					mOfferSuccessListView.setAdapter(mAdapter);
				}
			}
		};
	}

	@Override
	public void init()
	{
		super.init();
		mReturnBtn = (ImageButton) findViewById(R.id.return_btn);
		mReturnBtn.setOnClickListener(this);

		mOfferSuccessListView = (XListView) findViewById(R.id.offer_success_list);
		mOfferSuccessListView.setPullLoadEnable(true);
		mOfferSuccessListView.setPullRefreshEnable(false);
		mOfferSuccessListView.setXListViewListener(this);
		mOfferSuccessListView.setOnItemClickListener(this);
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
		startOfferSuccessCarThread();
		mOfferSuccessListView.stopRefresh();
		mOfferSuccessListView.stopLoadMore();
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
