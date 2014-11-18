/**
 * Project Name:JingZhenGu
 * File Name:QueryCarActivity.java
 * Package Name:com.gc.jingzhengu.ui
 * Date:2014-8-4上午11:42:46
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.ui;

import java.nio.MappedByteBuffer;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.jzgpinggushi.R;
import com.gc.jzgpinggushi.adapter.QueryCarAdapter;
import com.gc.jzgpinggushi.app.ActivityHelp;
import com.gc.jzgpinggushi.app.HttpService;
import com.gc.jzgpinggushi.constant.Constant;
import com.gc.jzgpinggushi.ui.OfferListActivity.UploadBroadCast;
import com.gc.jzgpinggushi.uitls.MessageUtils;
import com.gc.jzgpinggushi.view.pulllist.XListView;
import com.gc.jzgpinggushi.view.pulllist.XListView.IXListViewListener;
import com.gc.jzgpinggushi.vo.QueryCar;
import com.gc.jzgpinggushi.vo.QueryCarList;

/**
 * ClassName:QueryCarActivity <br/>
 * Function: 查车入口. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-8-4 上午11:42:46 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class QueryCarActivity extends BaseActivity implements OnClickListener,
		IXListViewListener, OnItemClickListener
{
	private String FILTER = "com.gc.jzgpinggushi.ui.addprice";

	private AddBroadCast mAddBroadCast;

	private ImageButton mReturnBtn;

	private RelativeLayout mPriceBtn;

	private RelativeLayout mYearBtn;

	private RelativeLayout mKmBtn;

	private RelativeLayout mRecommend;

	private ImageView mPriceImg;

	private ImageView mYearImg;

	private ImageView mKmImg;

	private XListView mCarListView;

	private Button mQueryOtherBtn;

	private Resources mResources;

	private QueryCarAdapter mAdapter;

	private Handler mHandler;

	private QueryCarList mQueryCarList;

	/**
	 * 价格排序标记 余=0则升序 否 降序
	 */
	private int mPriceFlag = 0;

	/**
	 * 车龄排序标记 余=0则升序 否 降序
	 */
	private int mYearFlag = 0;

	/**
	 * 里程排序标记 余=0则升序 否 降序
	 */
	private int mKmFlag = 0;

	/**
	 * 查车列表数据
	 */
	private ArrayList<QueryCar> cars;

	private int mStyleId;

	/**
	 * 每次刷新的数据
	 */
	private int mPageCount = 10;

	/**
	 * 排序的方式:价格、车龄、里程
	 */
	private String mOrderItem = "";

	/**
	 * 排序的类型:asc desc
	 */
	private String mOrderType = "";

	/**
	 * 缓存查车列表数据
	 */
	private ArrayList<QueryCar> mCacheQueryCarList = new ArrayList<QueryCar>();

	private int mOldAscPriceLoc = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.query_car);
		mStyleId = Integer.valueOf((String) getIntent().getSerializableExtra(
				"styleId"));
		mHandler = getHandler();
		init();
		mDialog = mDialogManager.show(this, this, R.drawable.bg_loading);
		startQueryCarThread();
	}

	public Handler getHandler()
	{
		return new Handler()
		{

			@Override
			public void handleMessage(Message msg)
			{
				mDialog.dismiss();
				mQueryCarList = (QueryCarList) msg.obj;
				switch (msg.what) {
				case R.id.car_list:
					showCarList();
					break;
				case R.id.hide_footer:
					if (mCacheQueryCarList.size() == 0)
					{
						showInfo("尊敬的用户，服务器没有查询到相关数据！！");
					}
					mCarListView.setPullLoadEnable(false);
					break;
				default:
					break;
				}
			}

			private void showCarList()
			{
				if (mQueryCarList.getFlag() == 0)
				{
					mRecommend.setVisibility(View.VISIBLE);
				}

				if (mOldAscPriceLoc > 10)
				{
					mAdapter.notifyDataSetChanged();
				} else
				{
					if (mCacheQueryCarList.size() < 5)
					{
						mCarListView.getmFooterView().hide();
					} else
					{
						mCarListView.setPullLoadEnable(true);
					}
					mAdapter = new QueryCarAdapter(getApplicationContext(),
							mCacheQueryCarList);
					mCarListView.setAdapter(mAdapter);
				}
			}
		};
	}

	@Override
	public void init()
	{
		super.init();

		IntentFilter filter = new IntentFilter(FILTER);
		mAddBroadCast = new AddBroadCast();
		registerReceiver(mAddBroadCast, filter);

		mResources = getResources();
		mReturnBtn = (ImageButton) findViewById(R.id.return_btn);
		mReturnBtn.setOnClickListener(this);

		mPriceBtn = (RelativeLayout) findViewById(R.id.with_price_layout);
		mPriceBtn.setOnClickListener(this);
		mYearBtn = (RelativeLayout) findViewById(R.id.with_year_layout);
		mYearBtn.setOnClickListener(this);
		mKmBtn = (RelativeLayout) findViewById(R.id.with_km_layout);
		mKmBtn.setOnClickListener(this);
		mRecommend = (RelativeLayout) findViewById(R.id.recommend_layout);

		mPriceImg = (ImageView) findViewById(R.id.price_img);
		mYearImg = (ImageView) findViewById(R.id.year_img);
		mKmImg = (ImageView) findViewById(R.id.km_img);

		mQueryOtherBtn = (Button) findViewById(R.id.query_other_btn);
		mQueryOtherBtn.setOnClickListener(this);

		mCarListView = (XListView) findViewById(R.id.car_list);
		mCarListView.setPullLoadEnable(true);
		mCarListView.setPullRefreshEnable(false);
		mCarListView.setXListViewListener(this);
		mCarListView.setOnItemClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId()) {
		case R.id.return_btn:
		case R.id.query_other_btn:
			finish();
			break;
		case R.id.with_price_layout:
			queryCarByPrice();
			break;
		case R.id.with_year_layout:
			queryCarByYear();
			break;
		case R.id.with_km_layout:
			queryCarByKm();
			break;
		default:
			break;
		}
	}

	private void queryCarByKm()
	{
		mOrderItem = Constant.ORDERITEM3;
		if (withKmUIOperation())
		{
			mOrderType = Constant.ASC;
		} else
		{
			mOrderType = Constant.DESC;
		}
		startQueryCarThread();
	}

	private void queryCarByYear()
	{
		mOrderItem = Constant.ORDERITEM2;
		if (withYearUIOperation())
		{
			mOrderType = Constant.ASC;
		} else
		{
			mOrderType = Constant.DESC;
		}
		startQueryCarThread();
	}

	private void queryCarByPrice()
	{
		mOrderItem = Constant.ORDERITEM1;
		if (withPriceUIOperation())
		{
			mOrderType = Constant.ASC;
		} else
		{
			mOrderType = Constant.DESC;
		}
		startQueryCarThread();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{
		int viewid = parent.getId();
		switch (viewid) {
		case R.id.car_list:
			appContext.setAddPosition(position);
			appContext.setDetailFlag("addprice");
			appContext.setModelFlag("ckcy");// 查看车源模块标记
			QueryCar queryCar = mCacheQueryCarList.get(position - 1);
			ActivityHelp.startActivity(getApplicationContext(),
					DetailResultActivity.class, "queryCar", queryCar,
					Intent.FLAG_ACTIVITY_NEW_TASK);
			break;
		default:
			break;
		}
	}

	private void startQueryCarThread()
	{
		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					mQueryCarList = HttpService.getQueryCarList(mStyleId,
							mPageCount, mOrderItem, mOrderType,
							appContext.getPgsid());
					System.out.println("mOrderItem = " + mOrderItem
							+ ",mOrderType=" + mOrderType);
					ArrayList<QueryCar> queryCar = mQueryCarList.getCars();
					if (mOldAscPriceLoc >= queryCar.size())
					{
						// 当列表数据加载完毕、隐藏加载更多
						MessageUtils.sendMessage(mHandler, R.id.hide_footer,
								null);
					} else
					{
						mCacheQueryCarList.addAll(queryCar.subList(
								mOldAscPriceLoc, queryCar.size()));
						mOldAscPriceLoc += 10;
						System.out.println("mOldAscPriceLoc is "
								+ mOldAscPriceLoc);
						MessageUtils.sendMessage(mHandler, R.id.car_list,
								mQueryCarList);
					}
				} catch (Exception e)
				{
					e.printStackTrace();
					System.out.println("startQueryCarThread Exception is" + e);
				}
			}
		}).start();
	}

	private boolean withKmUIOperation()
	{
		boolean result;
		mPageCount = 10;
		mOldAscPriceLoc = 0;
		mCacheQueryCarList.clear();
		mYearFlag = 0;
		mPriceFlag = 0;
		mKmFlag++;
		if (mKmFlag % 2 != 0)
		{
			setupImg(R.drawable.sort_c, R.drawable.sort_c, R.drawable.sort_a);
			result = true;
		} else
		{
			setupImg(R.drawable.sort_c, R.drawable.sort_c, R.drawable.sort_b);
			result = false;
		}
		return result;
	}

	private boolean withYearUIOperation()
	{
		boolean result;
		mPageCount = 10;
		mOldAscPriceLoc = 0;
		mCacheQueryCarList.clear();
		mKmFlag = 0;
		mPriceFlag = 0;
		mYearFlag++;
		if (mYearFlag % 2 != 0)
		{
			setupImg(R.drawable.sort_c, R.drawable.sort_a, R.drawable.sort_c);
			result = true;
		} else
		{
			setupImg(R.drawable.sort_c, R.drawable.sort_b, R.drawable.sort_c);
			result = false;
		}
		return result;
	}

	private boolean withPriceUIOperation()
	{
		boolean result;
		mPageCount = 10;
		mCacheQueryCarList.clear();
		mOldAscPriceLoc = 0;
		mKmFlag = 0;
		mYearFlag = 0;
		mPriceFlag++;
		if (mPriceFlag % 2 != 0)
		{
			setupImg(R.drawable.sort_a, R.drawable.sort_c, R.drawable.sort_c);
			result = true;
		} else
		{
			setupImg(R.drawable.sort_b, R.drawable.sort_c, R.drawable.sort_c);
			result = false;
		}
		return result;
	}

	@SuppressLint("NewApi")
	private void setupImg(int sort1, int sort2, int sort3)
	{
		mPriceImg.setBackground(mResources.getDrawable(sort1));
		mYearImg.setBackground(mResources.getDrawable(sort2));
		mKmImg.setBackground(mResources.getDrawable(sort3));
	}

	@Override
	public void onRefresh()
	{
		// TODO 下拉刷新·
	}

	@Override
	public void onLoadMore()
	{
		mPageCount += 10;
		startQueryCarThread();
		onLoad();
	}

	private void onLoad()
	{
		mCarListView.stopRefresh();
		mCarListView.stopLoadMore();
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		// setupImg(R.drawable.sort_c, R.drawable.sort_c, R.drawable.sort_c);
		mPageCount = 10;
		mOrderItem = "";
		mOrderType = "";
		mPriceFlag = 0;
		mYearFlag = 0;
		mKmFlag = 0;
		// startQueryCarThread();
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
	class AddBroadCast extends BroadcastReceiver
	{
		@Override
		public void onReceive(Context context, Intent intent)
		{
			System.out.println("add " + "intent is " + intent.getAction());
			int position = appContext.getAddPosition();
			mCacheQueryCarList.remove(position - 1);
			mAdapter.notifyDataSetChanged();
		}
	}

}
