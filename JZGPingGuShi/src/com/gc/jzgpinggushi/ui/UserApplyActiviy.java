/**
 * Project Name:JZGPingGuShi
 * File Name:UserApplyActiviy.java
 * Package Name:com.gc.jzgpinggushi.ui
 * Date:2014-10-20下午7:22:22
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;

import com.example.jzgpinggushi.R;
import com.gc.jzgpinggushi.adapter.OfferSuccessAdapter;
import com.gc.jzgpinggushi.adapter.UserApplyAdapter;
import com.gc.jzgpinggushi.app.AppContext;
import com.gc.jzgpinggushi.app.HttpService;
import com.gc.jzgpinggushi.uitls.MessageUtils;
import com.gc.jzgpinggushi.view.pulllist.XListView;
import com.gc.jzgpinggushi.view.pulllist.XListView.IXListViewListener;
import com.gc.jzgpinggushi.vo.OfferSuccess;
import com.gc.jzgpinggushi.vo.UserApply;
import com.gc.jzgpinggushi.vo.UserApplyList;

/**
 * ClassName:UserApplyActiviy <br/>
 * Function: 用户申请列表视图. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-10-20 下午7:22:22 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class UserApplyActiviy extends BaseActivity implements OnClickListener,
		IXListViewListener, OnItemClickListener
{
	private ImageButton mReturnBtn;

	private Activity mActivity = this;

	private XListView mUserApplyListView;

	private UserApplyAdapter mAdapter;

	private Handler mHandler;

	/**
	 * 用户申请列表数据
	 */
	private ArrayList<UserApply> mCacheUserApplyList = new ArrayList<UserApply>();

	private UserApplyList mUserApplyList;

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
		setContentView(R.layout.user_apply_list);
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
					mUserApplyList = HttpService.getUserApplyList(
							mAppContext.getPgsid(), mPageCount);
					ArrayList<UserApply> userApplys = mUserApplyList
							.getUserApplys();
					if (mOldLoc >= userApplys.size())
					{
						// 当列表数据加载完毕、隐藏加载更多
						MessageUtils.sendMessage(mHandler, R.id.hide_footer,
								null);
					} else
					{
						mCacheUserApplyList.addAll(userApplys.subList(mOldLoc,
								userApplys.size()));
						mOldLoc += 10;
						System.out.println("mOldLoc is " + mOldLoc);
						MessageUtils.sendMessage(mHandler,
								R.id.user_apply_list, userApplys);
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
				ArrayList<UserApply> userApplys = (ArrayList<UserApply>) msg.obj;
				switch (msg.what) {
				case R.id.user_apply_list:
					showUserApplyList(userApplys);
					break;
				case R.id.hide_footer:
					if (mCacheUserApplyList.size() == 0)
					{
						showInfo("尊敬的用户，服务器没有查询到相关数据！！");
					}
					mUserApplyListView.setPullLoadEnable(false);
					break;
				default:
					break;
				}
			}

			private void showUserApplyList(ArrayList<UserApply> userApply)
			{
				if (mOldLoc > 10)
				{
					mAdapter.notifyDataSetChanged();
				} else
				{
					if (mCacheUserApplyList.size() < 5)
					{
						mUserApplyListView.getmFooterView().hide();
					} else
					{
						mUserApplyListView.setPullLoadEnable(true);
					}
					mAdapter = new UserApplyAdapter(mActivity, userApply);
					mUserApplyListView.setAdapter(mAdapter);
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

		mUserApplyListView = (XListView) findViewById(R.id.user_apply_list);
		mUserApplyListView.setPullLoadEnable(true);
		mUserApplyListView.setPullRefreshEnable(false);
		mUserApplyListView.setXListViewListener(this);
		mUserApplyListView.setOnItemClickListener(this);
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
		mUserApplyListView.stopRefresh();
		mUserApplyListView.stopLoadMore();
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
