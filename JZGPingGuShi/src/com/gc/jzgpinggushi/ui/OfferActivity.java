/**
 * Project Name:JZGPingGuShi
 * File Name:OfferActivity.java
 * Package Name:com.gc.jzgpinggushi.ui
 * Date:2014-9-4下午5:35:10
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jzgpinggushi.R;
import com.gc.jzgpinggushi.app.AppContext;
import com.gc.jzgpinggushi.app.AppManager;
import com.gc.jzgpinggushi.app.HttpService;
import com.gc.jzgpinggushi.uitls.MessageUtils;
import com.gc.jzgpinggushi.uitls.StringUtil;
import com.gc.jzgpinggushi.vo.CarSource;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

/**
 * ClassName:OfferActivity <br/>
 * Function: 出价. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-4 下午5:35:10 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class OfferActivity extends BaseActivity implements OnClickListener
{
	private ImageButton mReturnBtn;

	private Button mOfferSure;

	private EditText mPrice;

	private CarSource mCarSource;

	private ImageView carLevel;

	private ImageView imgUrl;

	private TextView fullName;

	private TextView registDate;

	private TextView mileage;

	private TextView bodyColor;

	private TextView insuranceExpireDate;

	private TextView inspectionExpireDate;

	private TextView priceRange;

	private AppContext mAppContext;

	private Handler mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mCarSource = (CarSource) getIntent().getSerializableExtra("carSource");
		setContentView(R.layout.offer);
		mAppContext = (AppContext) getApplicationContext();
		mHandler = getHandler();
		init();
	}

	private Handler getHandler()
	{
		return new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				switch (msg.what) {
				case R.id.upload_price_success:
					// 查看车源
					if ("ycjcl".equals(mAppContext.getModelFlag()))
					{
						Intent intent = new Intent(
								"com.gc.jzgpinggushi.ui.upload");
						intent.putExtra("price", mPrice.getText().toString());
						intent.putExtra("position",
								mCarSource.getClickPosition());
						sendBroadcast(intent);
						finish();
						// 最新车源
					} else if ("zxcy".equals(mAppContext.getModelFlag()))
					{
						Intent intent = new Intent(
								"com.gc.jzgpinggushi.ui.newCarPrice");
						sendBroadcast(intent);
						finish();
						AppManager.getAppManager().finishActivity(
								DetailResultActivity.class);
					} else if ("ckcy".equals(mAppContext.getModelFlag()))
					{
						Intent intent = new Intent(
								"com.gc.jzgpinggushi.ui.addprice");
						sendBroadcast(intent);
						finish();
						AppManager.getAppManager().finishActivity(
								DetailResultActivity.class);
					}
					break;
				case R.id.upload_price_faild:
					showError("尊敬的用户，您更新出价失败!!");
					break;
				default:
					break;
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

		mOfferSure = (Button) findViewById(R.id.offer_sure);
		mOfferSure.setOnClickListener(this);

		mPrice = (EditText) findViewById(R.id.price);

		carLevel = (ImageView) findViewById(R.id.carLevel);
		imageLoader.displayImage(mCarSource.getCarLevelUrl(), carLevel,
				mOptions, mAnimateFirstListener);

		imgUrl = (ImageView) findViewById(R.id.imgUrl);
		imageLoader.displayImage(mCarSource.getImgUrl(), imgUrl, mOptions,
				mAnimateFirstListener);

		fullName = (TextView) findViewById(R.id.fullName);
		fullName.setText(mCarSource.getFullName());
		registDate = (TextView) findViewById(R.id.registDate);
		registDate.setText("上牌时间：" + mCarSource.getRegistDate());
		mileage = (TextView) findViewById(R.id.mileage);
		mileage.setText(String.valueOf("行驶里程：" + mCarSource.getMileage()));
		bodyColor = (TextView) findViewById(R.id.bodyColor);
		bodyColor.setText("车身颜色：" + mCarSource.getBodyColor());
		insuranceExpireDate = (TextView) findViewById(R.id.insuranceExpireDate);
		insuranceExpireDate.setText("保险到期："
				+ mCarSource.getInsuranceExpireDate());
		inspectionExpireDate = (TextView) findViewById(R.id.inspectionExpireDate);
		inspectionExpireDate.setText("年检到期："
				+ mCarSource.getInspectionExpireDate());
		priceRange = (TextView) findViewById(R.id.priceRange);
		priceRange.setText("你的车辆属于B级，目前市场价格区间为" + mCarSource.getPriceRange()
				+ "万元。");
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId()) {
		case R.id.return_btn:
			finish();
			break;
		case R.id.offer_sure:
			startOfferThread();
			break;
		default:
			break;
		}
	}

	private void startOfferThread()
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				String myBidPrice = mPrice.getText().toString();
				int pgsid = mAppContext.getPgsid();
				int carsourceid = mCarSource.getCarsourceid();
				boolean result;
				try
				{
					result = HttpService.uploadOffer(myBidPrice, pgsid,
							carsourceid);
					if (result)
					{
						MessageUtils.sendMessage(mHandler,
								R.id.upload_price_success, null);
					} else
					{
						MessageUtils.sendMessage(mHandler,
								R.id.upload_price_faild, null);
					}
				} catch (Exception e)
				{
					e.printStackTrace();

				}
			}
		}).start();
	}
}
