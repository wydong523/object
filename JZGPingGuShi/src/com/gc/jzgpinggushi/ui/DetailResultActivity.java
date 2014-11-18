/**
 * Project Name:JZGPingGuShi
 * File Name:DetailResultActivity.java
 * Package Name:com.gc.jzgpinggushi.ui
 * Date:2014-9-4下午3:54:05
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.ui;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jzgpinggushi.R;
import com.gc.jzgpinggushi.adapter.OfferCarAdapter;
import com.gc.jzgpinggushi.app.ActivityHelp;
import com.gc.jzgpinggushi.app.AppManager;
import com.gc.jzgpinggushi.app.HttpService;
import com.gc.jzgpinggushi.ui.OfferListActivity.UploadBroadCast;
import com.gc.jzgpinggushi.uitls.DialogManager;
import com.gc.jzgpinggushi.uitls.MessageUtils;
import com.gc.jzgpinggushi.uitls.ScreenShotUtils;
import com.gc.jzgpinggushi.vo.CarSource;
import com.gc.jzgpinggushi.vo.DetailResult;
import com.gc.jzgpinggushi.vo.NewCar;
import com.gc.jzgpinggushi.vo.Offer;
import com.gc.jzgpinggushi.vo.QueryCar;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.SnsPostListener;
import com.umeng.socialize.media.TencentWbShareContent;
import com.umeng.socialize.media.UMVideo;

/**
 * ClassName:DetailResultActivity <br/>
 * Function: 结果详情. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-4 下午3:54:05 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
@SuppressLint("SetJavaScriptEnabled")
public class DetailResultActivity extends BaseActivity implements
		OnClickListener
{
	public static final String DESCRIPTOR = "com.umeng.share";

	private String FILTER = "com.gc.jzgpinggushi.ui.upload";

	private UploadBroadCast mUploadBroadCast;

	private DetailResult mDetailResult;

	private WebView mWebView;

	private View mWebLayout;

	private RelativeLayout mReturnBtnLayout;

	private ImageButton mRefreshBtn;

	private TextView mCunText;

	private Button mOfferBtn;

	private QueryCar mQueryCar;

	private String url;

	private int carid;

	private DialogManager mDialogManager;

	private Dialog mDialog;

	private Handler mHandler;

	private ImageButton returnBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_result);
		mHandler = getHandler();
		init();
	}

	@Override
	protected void onPause()
	{
		super.onPause();
	}

	@Override
	protected void onResume()
	{
		super.onResume();
	}

	@Override
	public void init()
	{
		super.init();

		returnBtn = (ImageButton) findViewById(R.id.return_btn);
		returnBtn.setOnClickListener(this);

		mDialogManager = new DialogManager();

		// mCunText = (TextView) findViewById(R.id.cun_text);

		mOfferBtn = (Button) findViewById(R.id.offer_btn);
		mOfferBtn.setOnClickListener(this);

		// 实例化WebView对象
		mWebView = (WebView) findViewById(R.id.gen_web1);

		// 设置WebView属性，能够执行Javascript脚本
		mWebView.getSettings().setJavaScriptEnabled(true);

		// mCunText.setVisibility(View.INVISIBLE);
		// 查看车源列表操作
		if (appContext.getDetailFlag().equals("addprice"))
		{
			mQueryCar = (QueryCar) getIntent().getSerializableExtra("queryCar");
			url = mQueryCar.getReportUrl();
			carid = mQueryCar.getCarsourceid();
		} else if (appContext.getDetailFlag().equals("newCarPrice"))
		{
			NewCar newCar = (NewCar) getIntent().getSerializableExtra("newCar");
			carid = newCar.getCarsourceid();
			url = newCar.getReportUrl();
		}

		mWebView.clearCache(true);
		mWebView.loadUrl(url);

		// mReturnBtnLayout = (RelativeLayout)
		// findViewById(R.id.return_btn_layout);
		// mReturnBtnLayout.setOnClickListener(this);

		mRefreshBtn = (ImageButton) findViewById(R.id.refresh_btn);
		mRefreshBtn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId()) {
		case R.id.return_btn:
			finish();
			break;
		case R.id.refresh_btn:
			mWebView.loadUrl(url);
			break;
		case R.id.offer_btn:
			startQueryByCarsourceid(carid);
			break;
		default:
			break;
		}
	}

	private void startQueryByCarsourceid(final int carId)
	{
		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				CarSource carSource = HttpService.queryByCarsourceid(carId);
				System.out.println("carSource is " + carSource);
				MessageUtils.sendMessage(mHandler, R.id.start_offer_activity,
						carSource);
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
				// mDialog.dismiss();
				switch (msg.what) {
				case R.id.start_offer_activity:
					CarSource carSource = (CarSource) msg.obj;
					startOfferActivity(carSource);
				default:
					break;
				}
			}
		};
	}

	protected void startOfferActivity(CarSource carSource)
	{
		ActivityHelp.startActivity(this, OfferActivity.class, "carSource",
				carSource);
	}

}
