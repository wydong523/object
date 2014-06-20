/**
 * Project Name:JingZhenGu
 * File Name:GenerateDetailReportActivity.java
 * Package Name:com.gc.jingzhengu.ui
 * Date:2014-5-28下午3:01:52
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.ui;

import com.gc.jingzhengu.R;
import com.gc.jingzhengu.app.ActivityHelp;
import com.gc.jingzhengu.vo.Car;
import com.gc.jingzhengu.vo.SimpleAssessment;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * ClassName:GenerateDetailReportActivity <br/>
 * Function: 生成详细报告. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-5-28 下午3:01:52 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class GenerateDetailReportActivity extends BaseActivity implements
		OnClickListener
{
	private Button mGenerateDetailReportBtn;

	private ImageButton mReturnBtn;

	private TextView mReportKm;

	private TextView mCarYear;

	private TextView mCarStyle;

	private TextView mPriceRange;

	private SimpleAssessment mSimpleAssessment;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.generate_detail_report);
		mSimpleAssessment = (SimpleAssessment) getIntent()
				.getSerializableExtra("simpleAssessment");
		init();
	}

	@Override
	public void init()
	{
		mGenerateDetailReportBtn = (Button) findViewById(R.id.generate_detail_report_btn);
		mGenerateDetailReportBtn.setOnClickListener(this);
		mReturnBtn = (ImageButton) findViewById(R.id.return_btn);
		mReturnBtn.setOnClickListener(this);

		mReportKm = (TextView) findViewById(R.id.generate_detail_report_km);
		mCarYear = (TextView) findViewById(R.id.generate_detail_report_car_year);
		mCarStyle = (TextView) findViewById(R.id.generate_detail_report_car_style);
		mPriceRange = (TextView) findViewById(R.id.generate_detail_report_pricerange);
		mReportKm.setText(mSimpleAssessment.getMileage());
		mCarYear.setText(mSimpleAssessment.getGetLicenseDate());
		mCarStyle.setText(mSimpleAssessment.getStyle());
		mPriceRange.setText(mSimpleAssessment.getPriceRange());
		mPriceRange.setTextColor(getResources().getColor(R.color.list_click));
	}

	@Override
	public void onClick(View v)
	{
		int id = v.getId();
		switch (id) {
		case R.id.generate_detail_report_btn:
			ActivityHelp.startActivity(this, CarDetailInfoActivity.class);
			break;
		case R.id.return_btn:
			finish();
			break;
		default:
			break;
		}
	}
}
