/**
 * Project Name:JingZhenGu
 * File Name:InjureDetailActivity.java
 * Package Name:com.gc.jingzhengu.ui
 * Date:2014-6-4下午5:06:18
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.ui;

import java.util.Map;

import com.gc.jingzhengu.R;
import com.gc.jingzhengu.adapter.InjureDetailFragmentAdapter;
import com.gc.jingzhengu.app.AppContext;
import com.gc.jingzhengu.app.AppManager;
import com.gc.jingzhengu.service.CarDamageSerivce;
import com.gc.jingzhengu.uitls.MessageUtils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ClassName:InjureDetailActivity <br/>
 * Function: 损伤详情界面. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-4 下午5:06:18 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class InjureDetailActivity extends FragmentActivity implements
		OnPageChangeListener
{
	private TextView mTitle1;
	private ImageView mTitle1Img;
	private TextView mTitle2;
	private ImageView mTitle2Img;
	private TextView mTitle3;
	private ImageView mTitle3Img;
	private TextView mTitle4;
	private ImageView mTitle4Img;
	private ViewPager mPager;
	private Handler mViewPagerHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.injure_detail);
		AppManager.getAppManager().addActivity(this);
		mViewPagerHandler = getmViewPagerHandler();
		init();
	}

	private Handler getmViewPagerHandler()
	{
		return new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				System.out.println("mPager.getCurrentItem() is"
						+ mPager.getCurrentItem());
				;
				switch (msg.what) {
				case R.id.gen_top_btn:
				case R.id.top_setup_btn:
					mPager.setCurrentItem(mPager.getCurrentItem() - 1);
					break;
				case R.id.next_setup_btn:
				case R.id.bottom_btn:
					mPager.setCurrentItem(mPager.getCurrentItem() + 1);
					break;
				default:
					break;
				}

			}
		};
	}

	public void init()
	{
		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(new InjureDetailFragmentAdapter(
				getSupportFragmentManager(), mViewPagerHandler));
		mPager.setOnPageChangeListener(this);
		mTitle1 = (TextView) findViewById(R.id.title1);
		mTitle1Img = (ImageView) findViewById(R.id.title1img);
		mTitle2 = (TextView) findViewById(R.id.title2);
		mTitle2Img = (ImageView) findViewById(R.id.title2img);
		mTitle3 = (TextView) findViewById(R.id.title3);
		mTitle3Img = (ImageView) findViewById(R.id.title3img);
		mTitle4 = (TextView) findViewById(R.id.title4);
		mTitle4Img = (ImageView) findViewById(R.id.title4img);
		showTitle(0, R.color.list_click, R.drawable.arrow_sel,
				R.color.car_info, R.drawable.arrow_nor);
	}

	@Override
	public void onPageScrollStateChanged(int arg0)
	{

		// System.out.println("onPageScrollStateChanged is " + arg0);
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2)
	{

		// System.out.println("onPageScrolled is " + "arg0" + arg0 + "，arg1 "
		// + arg1 + "，arg2 " + arg2);
	}

	@Override
	public void onPageSelected(int pageid)
	{
		showTitle(pageid, R.color.list_click, R.drawable.arrow_sel,
				R.color.car_info, R.drawable.arrow_nor);
		// System.out.println("onPageSelected arg0 is " + arg0);
	}

	/**
	 * 显示当前选中页的title showTitle: <br/>
	 * 
	 * @author wang
	 * @param pageid
	 *            当前选中页id
	 * @param listClick
	 *            选中的字体颜色
	 * @param arrowSel
	 *            选中的箭头的图片资源
	 * @param listClickNor
	 *            未选中的字体颜色
	 * @param arrowNor
	 *            未选中的箭头的图片资源
	 * @since JDK 1.6
	 */
	private void showTitle(int pageid, int listClick, int arrowSel,
			int listClickNor, int arrowNor)
	{
		switch (pageid) {
		case 0:
			mTitle1.setTextColor(getResources().getColor(listClick));
			mTitle1Img.setBackgroundResource(arrowSel);
			mTitle2.setTextColor(getResources().getColor(listClickNor));
			mTitle2Img.setBackgroundResource(arrowNor);
			mTitle3.setTextColor(getResources().getColor(listClickNor));
			mTitle3Img.setBackgroundResource(arrowNor);
			mTitle4.setTextColor(getResources().getColor(listClickNor));
			mTitle4Img.setBackgroundResource(arrowNor);
			break;
		case 1:
			mTitle1.setTextColor(getResources().getColor(listClickNor));
			mTitle1Img.setBackgroundResource(arrowNor);
			mTitle2.setTextColor(getResources().getColor(listClick));
			mTitle2Img.setBackgroundResource(arrowSel);
			mTitle3.setTextColor(getResources().getColor(listClickNor));
			mTitle3Img.setBackgroundResource(arrowNor);
			mTitle4.setTextColor(getResources().getColor(listClickNor));
			mTitle4Img.setBackgroundResource(arrowNor);
			break;
		case 2:
			mTitle1.setTextColor(getResources().getColor(listClickNor));
			mTitle1Img.setBackgroundResource(arrowNor);
			mTitle2.setTextColor(getResources().getColor(listClickNor));
			mTitle2Img.setBackgroundResource(arrowNor);
			mTitle3.setTextColor(getResources().getColor(listClick));
			mTitle3Img.setBackgroundResource(arrowSel);
			mTitle4.setTextColor(getResources().getColor(listClickNor));
			mTitle4Img.setBackgroundResource(arrowNor);
			break;
		case 3:
			mTitle1.setTextColor(getResources().getColor(listClickNor));
			mTitle1Img.setBackgroundResource(arrowNor);
			mTitle2.setTextColor(getResources().getColor(listClickNor));
			mTitle2Img.setBackgroundResource(arrowNor);
			mTitle3.setTextColor(getResources().getColor(listClickNor));
			mTitle3Img.setBackgroundResource(arrowNor);
			mTitle4.setTextColor(getResources().getColor(listClick));
			mTitle4Img.setBackgroundResource(arrowSel);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onPause()
	{
		super.onPause();

		AppContext appContext = ((AppContext) getApplication());
		appContext.getmResultData().clear();

		Map<String, Boolean> mFourPageData1 = AppContext.getmFourPageData1();
		mFourPageData1.put(CarDamageSerivce.CHECKBOX_1, true);
		mFourPageData1.put(CarDamageSerivce.CHECKBOX_2, false);
		mFourPageData1.put(CarDamageSerivce.CHECKBOX_3, false);
		mFourPageData1.put(CarDamageSerivce.CHECKBOX_4, false);

		Map<String, Boolean> mFourPageData2 = AppContext.getmFourPageData2();
		mFourPageData2.put(CarDamageSerivce.CHECKBOX_5, true);
		mFourPageData2.put(CarDamageSerivce.CHECKBOX_6, false);
		mFourPageData2.put(CarDamageSerivce.CHECKBOX_7, false);
		mFourPageData2.put(CarDamageSerivce.CHECKBOX_8, false);

		Map<String, Boolean> mFourPageData3 = AppContext.getmFourPageData3();
		mFourPageData3.put(CarDamageSerivce.CHECKBOX_9, true);
		mFourPageData3.put(CarDamageSerivce.CHECKBOX_10, false);
		mFourPageData3.put(CarDamageSerivce.CHECKBOX_11, false);
		mFourPageData3.put(CarDamageSerivce.CHECKBOX_12, false);
		mFourPageData3.put(CarDamageSerivce.CHECKBOX_13, false);
		mFourPageData3.put(CarDamageSerivce.CHECKBOX_14, false);
		mFourPageData3.put(CarDamageSerivce.CHECKBOX_15, false);
		mFourPageData3.put(CarDamageSerivce.CHECKBOX_16, false);
		mFourPageData3.put(CarDamageSerivce.CHECKBOX_17, false);
		mFourPageData3.put(CarDamageSerivce.CHECKBOX_18, false);
		mFourPageData3.put(CarDamageSerivce.CHECKBOX_19, false);
		mFourPageData3.put(CarDamageSerivce.CHECKBOX_20, false);
		mFourPageData3.put(CarDamageSerivce.CHECKBOX_21, false);
	}

}
