/**
 * Project Name:JingZhenGu
 * File Name:CarInfoActivity.java
 * Package Name:com.gc.jingzhengu.ui
 * Date:2014-5-27下午2:45:58
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.ui;

import java.util.Calendar;

import com.gc.jingzhengu.R;
import com.gc.jingzhengu.app.ActivityHelp;
import com.gc.jingzhengu.app.HttpService;
import com.gc.jingzhengu.uitls.MessageUtils;
import com.gc.jingzhengu.view.CustomerDataPickerDialog;
import com.gc.jingzhengu.view.YearMonthDialog;
import com.gc.jingzhengu.vo.Car;
import com.gc.jingzhengu.vo.PriceRange;
import com.gc.jingzhengu.vo.SimpleAssessment;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * ClassName:CarInfoActivity <br/>
 * Function: 获得汽车信息，进行查看汽车价格操作. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-5-27 下午2:45:58 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class CarInfoActivity extends BaseActivity implements OnClickListener
{

	private CarInfoActivity activity = this;

	private TextView mCarInfoMakename;
	private TextView mCarInfoModelname;
	private TextView mCarInfoStylename;

	private Button mYearMonthBtn;
	private Button mCarInfoPriceBtn;

	private ImageButton mReturnBtn;

	private RelativeLayout mCarInfoNameLayout;
	private RelativeLayout mCarInfoTypeLayout;
	private RelativeLayout mCarInfoTypeLevelLayout;

	private EditText mCarInfoYearMonthEdit;
	private EditText mCarInfoMileage;

	private Handler mHandler;

	private Car car;

	private SimpleAssessment simpleAssessment;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.car_info);
		mHandler = getHandler();
		car = (Car) getIntent().getSerializableExtra("car");
		System.out.println("car is  " + car);
		init();

	}

	private Handler getHandler()
	{

		return new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				int id = msg.what;
				switch (id) {
				case R.id.pricerange:
					PriceRange priceRange = (PriceRange) msg.obj;
					if (priceRange.getStatus() == SUCCESS)
					{
						simpleAssessment.setPriceRange(priceRange
								.getPriceRange());
						simpleAssessment.setAppraiseReportId(priceRange
								.getAppraiseReportId());
						System.out.println("simpleAssessment"
								+ simpleAssessment.toString());
						ActivityHelp.startActivity(getApplicationContext(),
								GenerateDetailReportActivity.class,
								"simpleAssessment", simpleAssessment,
								Intent.FLAG_ACTIVITY_NEW_TASK);
					} else
					{
						showError(priceRange.getMsg());
					}

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

		mCarInfoMakename = (TextView) findViewById(R.id.car_info_makename);
		mCarInfoModelname = (TextView) findViewById(R.id.car_info_modelname);
		mCarInfoStylename = (TextView) findViewById(R.id.car_info_stylename);
		mCarInfoMakename.setText(car.getMakeName());
		mCarInfoModelname.setText(car.getModelName());
		mCarInfoStylename.setText(car.getStyleName());

		mYearMonthBtn = (Button) findViewById(R.id.car_info_year_month_btn);
		mYearMonthBtn.setOnClickListener(this);

		mCarInfoPriceBtn = (Button) findViewById(R.id.car_info_price_btn);
		mCarInfoPriceBtn.setOnClickListener(this);

		mReturnBtn = (ImageButton) findViewById(R.id.return_btn);
		mReturnBtn.setOnClickListener(this);

		mCarInfoNameLayout = (RelativeLayout) findViewById(R.id.car_info_name_layout);
		mCarInfoNameLayout.setOnClickListener(this);
		mCarInfoTypeLayout = (RelativeLayout) findViewById(R.id.car_info_type_layout);
		mCarInfoTypeLayout.setOnClickListener(this);
		mCarInfoTypeLevelLayout = (RelativeLayout) findViewById(R.id.car_info_type_level_layout);
		mCarInfoTypeLevelLayout.setOnClickListener(this);

		mCarInfoYearMonthEdit = (EditText) findViewById(R.id.car_info_year_month_edit);
		mCarInfoMileage = (EditText) findViewById(R.id.car_info_mileage);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId()) {
		case R.id.car_info_year_month_btn:
			final Calendar c = Calendar.getInstance();
			CustomerDataPickerDialog mDatepicker = new CustomerDataPickerDialog(
					this, mDateSetListner, true, true, false,
					c.get(Calendar.YEAR), c.get(Calendar.MONTH),
					c.get(Calendar.DAY_OF_MONTH));
			mDatepicker.show();
			break;
		case R.id.return_btn:
			finish();
			break;
		case R.id.car_info_price_btn:
			// 启动查询价格区间线程
			startAssessmenPriceThread();
			break;
		case R.id.car_info_name_layout:
		case R.id.car_info_type_layout:
		case R.id.car_info_type_level_layout:
			ActivityHelp.startActivity(this, IndexCarActivity.class);
			break;
		default:
			break;
		}
	}

	private void startAssessmenPriceThread()
	{
		simpleAssessment = new SimpleAssessment();
		simpleAssessment.setMakeId(car.getMakeid());
		simpleAssessment.setModelId(car.getModelid());
		simpleAssessment.setStyleId(car.getStyleid());
		simpleAssessment.setStyle(car.getStyleName());
		simpleAssessment.setMileage(mCarInfoMileage.getText().toString());
		simpleAssessment.setGetLicenseDate(mCarInfoYearMonthEdit.getText()
				.toString());
		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					PriceRange priceRange = HttpService
							.getSimpleAssessmentPrice(simpleAssessment);
					MessageUtils.sendMessage(mHandler, R.id.pricerange,
							priceRange);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void startGenerateDetailReportActivity()
	{
		// ActivityHelp.startActivity(this, GenerateDetailReportActivity.class ,
		// nameFlag, serializable)
	}

	DatePickerDialog.OnDateSetListener mDateSetListner = new OnDateSetListener()
	{

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth)
		{
			System.out.println("year" + year);
			if (monthOfYear < 9)
			{
				mCarInfoYearMonthEdit.setText(year + "-0" + (monthOfYear + 1)
						+ "-01");
			} else
			{
				mCarInfoYearMonthEdit.setText(year + "-" + (monthOfYear + 1)
						+ "-01");
			}
		}
	};
}
