/**
 * Project Name:JingZhenGu
 * File Name:CarDetailInfoActivity.java
 * Package Name:com.gc.jingzhengu.ui
 * Date:2014-5-30上午11:05:23
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.gc.jingzhengu.R;
import com.gc.jingzhengu.adapter.CarColorAdapter;
import com.gc.jingzhengu.adapter.Simple2Adapter;
import com.gc.jingzhengu.adapter.SimpleAdapter;
import com.gc.jingzhengu.app.ActivityHelp;
import com.gc.jingzhengu.app.HttpService;
import com.gc.jingzhengu.uitls.MessageUtils;
import com.gc.jingzhengu.view.CustomerDataPickerDialog;
import com.gc.jingzhengu.vo.CarColor;
import com.gc.jingzhengu.vo.City;
import com.gc.jingzhengu.vo.CityList;
import com.gc.jingzhengu.vo.ModelCategory;
import com.gc.jingzhengu.vo.Province;
import com.gc.jingzhengu.vo.ProvinceList;
import com.gc.jingzhengu.vo.SimpleList;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.LiveFolders;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.SlidingDrawer.OnDrawerScrollListener;

/**
 * ClassName:CarDetailInfoActivity <br/>
 * Function: 填写汽车详细信息. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-5-30 上午11:05:23 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class CarDetailInfoActivity extends BaseActivity implements
		OnClickListener, OnDrawerOpenListener, OnDrawerCloseListener,
		OnItemClickListener
{
	private Button mCarDetailInfoRegistrationSiteFlagBtn;
	private Button mCarDetailInfoCarNumFlagBtn;
	private Button mCarDetailInfoCarColorFlagBtn;
	private Button mCarDetailInfoInsuranceDateFlagBtn;
	private Button mCarDetailInfoAnnualInspectionDateFlagBtn;
	private Button mCarDetailInfoActualPriceFlagBtn;
	private Button mCarInfoPriceBtn;

	private ImageButton mReturnBtn;

	private LinearLayout mCarDetailInfoRegistrationSiteLayout;
	private LinearLayout mCarDetailInfoCarColorLayout;
	private LinearLayout mCarDetailInfoInsuranceDateLayout;
	private LinearLayout mCarDetailInfoAnnualInspectionDateLayout;
	private RelativeLayout mCarDetailInfoActualPriceLayout;

	private TextView mCarDetailInfoRegistrationSiteText;
	private TextView mCarDetailInfoCarColorText;
	private TextView mCarDetailInfoInsuranceDateText;
	private TextView mCarDetailInfoAnnualInspectionDateText;
	private EditText mCarDetailInfoCctualPriceEdit;

	private SlidingDrawer mCarDetailInfoProvinceDrawer;
	private ImageView mCarDetailInfoProvinceHandle;
	private ListView mCarDetailInfoProvinceContent;

	private SlidingDrawer mCarDetailInfoCityDrawer;
	private ImageView mCarDetailInfoCityHandle;
	private ListView mCarDetailInfoCityContent;

	private SlidingDrawer mCarDetailInfoColorDrawer;
	private ImageView mCarDetailInfoColorHandle;
	private ListView mCarDetailInfoColorContent;

	/**
	 * 省列表被点击的位置
	 */
	private int mProvinceContentListClickPostion = -1;

	/**
	 * 市列表被点击的位置
	 */
	private int mCityContentListClickPostion = -1;

	/**
	 * 颜色列表被点击的位置
	 */
	private int mColorContentListClickPostion = -1;

	/**
	 * 省列表是否被点击
	 */
	private boolean isProvinceClick = false;

	/**
	 * 省份
	 */
	private String mProvince;

	/**
	 * 市
	 */
	private String mCity;

	/**
	 * 颜色
	 */
	private String mColor;

	/**
	 * 界面Handler控制
	 */
	private Handler mHandler;

	/**
	 * 省结果集
	 */
	private ArrayList<Province> provinces;

	/**
	 * 市结果集
	 */
	private ArrayList<City> cities;

	private List<SimpleList> list;

	private Simple2Adapter simple2Adpter;

	private int oldPoc;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.car_detail_info);
		mHandler = getHandler();
		init();

		ArrayList<CarColor> colors = new ArrayList<CarColor>();
		int[] pics = { R.drawable.black, R.drawable.white, R.drawable.silver,
				R.drawable.gray, R.drawable.red, R.drawable.brown,
				R.drawable.recst, R.drawable.blue, R.drawable.maroon,
				R.drawable.gold, R.drawable.orange, R.drawable.beige,
				R.drawable.yellow, R.drawable.purple, R.drawable.cyan,
				R.drawable.green };
		String[] names = { "黑色", "白色", "银色", "灰色", "红色", "棕色", "褐色", "蓝色",
				"栗色", "金色", "橙色", "米黄色", "黄色", "紫色", "蓝绿色", "绿色" };

		CarColor carcolor = null;
		for (int i = 0; i < 16; i++)
		{
			carcolor = new CarColor();
			carcolor.setColorPic(pics[i]);
			carcolor.setColorName(names[i]);
			colors.add(carcolor);
		}
		mCarDetailInfoColorContent
				.setAdapter(new CarColorAdapter(this, colors));
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
				case R.id.provinceList:
					assemblyProvinceList(msg);
					break;
				case R.id.cityList:
					assemblyCityList(msg);
					break;
				default:
					break;
				}
			}

			private void assemblyCityList(Message msg)
			{
				CityList cityList = (CityList) msg.obj;
				cities = cityList.getCitys();
				List<String> list = new ArrayList<String>();
				for (City city : cities)
				{
					list.add(city.getName());
				}
				mCarDetailInfoCityContent.setAdapter(new SimpleAdapter(
						getApplicationContext(), list));
			}

			private void assemblyProvinceList(Message msg)
			{
				ProvinceList provinceList = (ProvinceList) msg.obj;
				if (checkProvinceList(provinceList))
				{
					closeAllDrawer();
					mCarDetailInfoProvinceDrawer.animateOpen();
					provinces = provinceList.getProvinces();
					list = new ArrayList<SimpleList>();
					for (int i = 1; i <= provinces.size(); i++)
					{
						int color = getResources().getColor(
								android.R.color.black);
						String name = provinces.get(i - 1).getName();
						SimpleList simpleList = new SimpleList(i, color, name);
						list.add(simpleList);
					}
					simple2Adpter = new Simple2Adapter(getApplicationContext(),
							list);
					mCarDetailInfoProvinceContent.setAdapter(simple2Adpter);
				} else
				{
					showInfo("省列表无返回数据，请检查网络是否链接！！！");
				}
			}
		};
	}

	protected boolean checkProvinceList(ProvinceList provinceList)
	{
		if (provinceList.getStatus() != SUCCESS)
		{
			return false;
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void init()
	{
		mCarDetailInfoRegistrationSiteFlagBtn = (Button) findViewById(R.id.car_detail_info_registration_site_flag);
		mCarDetailInfoCarColorFlagBtn = (Button) findViewById(R.id.car_detail_info_car_color_flag);
		mCarDetailInfoInsuranceDateFlagBtn = (Button) findViewById(R.id.car_detail_info_insurance_date_flag);
		mCarDetailInfoAnnualInspectionDateFlagBtn = (Button) findViewById(R.id.car_detail_info_annual_inspection_date_flag);
		mCarDetailInfoActualPriceFlagBtn = (Button) findViewById(R.id.car_detail_info_actual_price_flag);
		mCarInfoPriceBtn = (Button) findViewById(R.id.car_info_price_btn);
		mCarInfoPriceBtn.setOnClickListener(this);
		mReturnBtn = (ImageButton) findViewById(R.id.return_btn);
		mReturnBtn.setOnClickListener(this);

		mCarDetailInfoRegistrationSiteLayout = (LinearLayout) findViewById(R.id.car_detail_info_registration_site_layout);
		mCarDetailInfoRegistrationSiteLayout.setOnClickListener(this);
		mCarDetailInfoCarColorLayout = (LinearLayout) findViewById(R.id.car_detail_info_car_color_layout);
		mCarDetailInfoCarColorLayout.setOnClickListener(this);
		mCarDetailInfoInsuranceDateLayout = (LinearLayout) findViewById(R.id.car_detail_info_insurance_date_layout);
		mCarDetailInfoInsuranceDateLayout.setOnClickListener(this);
		mCarDetailInfoAnnualInspectionDateLayout = (LinearLayout) findViewById(R.id.car_detail_info_annual_inspection_date_layout);
		mCarDetailInfoAnnualInspectionDateLayout.setOnClickListener(this);

		mCarDetailInfoRegistrationSiteText = (TextView) findViewById(R.id.car_detail_info_registration_site_text);
		mCarDetailInfoCarColorText = (TextView) findViewById(R.id.car_detail_info_car_color_text);
		mCarDetailInfoInsuranceDateText = (TextView) findViewById(R.id.car_detail_info_insurance_date_text);
		mCarDetailInfoAnnualInspectionDateText = (TextView) findViewById(R.id.car_detail_info_annual_inspection_date_text);
		mCarDetailInfoCctualPriceEdit = (EditText) findViewById(R.id.car_detail_info_actual_price_edit);
		mCarDetailInfoCctualPriceEdit.setOnClickListener(this);

		mCarDetailInfoActualPriceLayout = (RelativeLayout) findViewById(R.id.car_detail_info_actual_price_layout);
		mCarDetailInfoActualPriceLayout.setOnClickListener(this);

		// 初始化汽车省
		mCarDetailInfoProvinceDrawer = (SlidingDrawer) this
				.findViewById(R.id.car_detail_info_province_drawer);
		mCarDetailInfoProvinceHandle = (ImageView) this
				.findViewById(R.id.car_detail_info_province_handle);
		mCarDetailInfoProvinceContent = (ListView) this
				.findViewById(R.id.car_detail_info_province_content);
		mCarDetailInfoProvinceContent.setOnItemClickListener(this);
		// 设置SlidingDrawer打开或者关闭时的监听器，设置失去
		mCarDetailInfoProvinceDrawer.setOnDrawerOpenListener(this);
		mCarDetailInfoProvinceDrawer.setOnDrawerCloseListener(this);

		mCarDetailInfoProvinceDrawer
				.setOnDrawerScrollListener(new OnDrawerScrollListener()
				{

					@Override
					public void onScrollStarted()
					{
						if (mCarDetailInfoCityDrawer.isOpened())
							mCarDetailInfoCityDrawer.close();

						if (mCarDetailInfoColorDrawer.isOpened())
							mCarDetailInfoColorDrawer.close();
					}

					@Override
					public void onScrollEnded()
					{

					}
				});

		// 初始化汽车市
		mCarDetailInfoCityDrawer = (SlidingDrawer) this
				.findViewById(R.id.car_detail_info_city_drawer);
		mCarDetailInfoCityHandle = (ImageView) this
				.findViewById(R.id.car_detail_info_city_handle);
		mCarDetailInfoCityContent = (ListView) this
				.findViewById(R.id.car_detail_info_city_content);
		mCarDetailInfoCityContent.setOnItemClickListener(this);
		mCarDetailInfoCityDrawer.setOnDrawerOpenListener(this);
		mCarDetailInfoCityDrawer.setOnDrawerCloseListener(this);

		// 初始化汽车颜色
		mCarDetailInfoColorDrawer = (SlidingDrawer) this
				.findViewById(R.id.car_detail_info_color_drawer);
		mCarDetailInfoColorHandle = (ImageView) this
				.findViewById(R.id.car_detail_info_color_handle);
		mCarDetailInfoColorContent = (ListView) this
				.findViewById(R.id.car_detail_info_color_content);
		mCarDetailInfoColorContent.setOnItemClickListener(this);
		mCarDetailInfoColorDrawer.setOnDrawerOpenListener(this);
		mCarDetailInfoColorDrawer.setOnDrawerCloseListener(this);

		mCarDetailInfoColorDrawer
				.setOnDrawerScrollListener(new OnDrawerScrollListener()
				{

					@Override
					public void onScrollStarted()
					{
						if (mCarDetailInfoProvinceDrawer.isOpened())
							mCarDetailInfoProvinceDrawer.close();

						if (mCarDetailInfoCityDrawer.isOpened())
							mCarDetailInfoCityDrawer.close();
					}

					@Override
					public void onScrollEnded()
					{

					}
				});
	}

	@Override
	public void onClick(View v)
	{
		int id = v.getId();
		switch (id) {
		case R.id.return_btn:
			finish();
			break;
		// 省
		case R.id.car_detail_info_registration_site_layout:
			hideflag(View.VISIBLE, View.INVISIBLE, View.INVISIBLE,
					View.INVISIBLE, View.INVISIBLE);
			startProvinceThead();
			break;
		// 颜色
		case R.id.car_detail_info_car_color_layout:
			hideflag(View.INVISIBLE, View.VISIBLE, View.INVISIBLE,
					View.INVISIBLE, View.INVISIBLE);
			closeAllDrawer();
			mCarDetailInfoColorDrawer.animateOpen();
			break;
		case R.id.car_detail_info_insurance_date_layout:
			hideflag(View.INVISIBLE, View.INVISIBLE, View.VISIBLE,
					View.INVISIBLE, View.INVISIBLE);
			showInsuranceDatepicker();
			break;
		case R.id.car_detail_info_annual_inspection_date_layout:
			hideflag(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE,
					View.VISIBLE, View.INVISIBLE);
			showInspectionDatepicker();
			break;
		case R.id.car_detail_info_actual_price_edit:
			hideflag(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE,
					View.INVISIBLE, View.VISIBLE);
			break;
		case R.id.car_info_price_btn:
			String price = mCarDetailInfoCctualPriceEdit.getText().toString();
			if (price.length() > 0)
			{
				appContext.getmCarDamage().setPurchasePrice(
						Integer.parseInt(price));
			}
			ActivityHelp.startActivity(this, InjureDetailActivity.class);
			break;
		default:
			break;
		}
		if (id != R.id.car_detail_info_registration_site_layout
				|| id == R.id.car_detail_info_actual_price_layout)
			closeAllDrawer();
	}

	/**
	 * 获取省列表信息 startProvinceThead: <br/>
	 * 
	 * @author wang
	 * @since JDK 1.6
	 */
	private void startProvinceThead()
	{
		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					ProvinceList provinceList = HttpService.getProvinceList();
					MessageUtils.sendMessage(mHandler, R.id.provinceList,
							provinceList);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * 年检到期日日期选择显示 showInspectionDatepicker: <br/>
	 * 
	 * @author wang
	 * @since JDK 1.6
	 */
	private void showInspectionDatepicker()
	{
		final Calendar c = Calendar.getInstance();
		CustomerDataPickerDialog mDatepicker = new CustomerDataPickerDialog(
				this, mInspectionDateSetListner, true, true, false,
				c.get(Calendar.YEAR), c.get(Calendar.MONTH),
				c.get(Calendar.DAY_OF_MONTH));
		mDatepicker.show();
	}

	/**
	 * 保险到期日选择控件 显示showDatepicker: <br/>
	 * 
	 * @author wang
	 * @since JDK 1.6
	 */
	private void showInsuranceDatepicker()
	{
		final Calendar c = Calendar.getInstance();
		CustomerDataPickerDialog mDatepicker = new CustomerDataPickerDialog(
				this, mInsuranceDateSetListner, true, true, false,
				c.get(Calendar.YEAR), c.get(Calendar.MONTH),
				c.get(Calendar.DAY_OF_MONTH));
		mDatepicker.show();
	}

	/**
	 * 
	 * hideflag: 隐藏界面中的指示标记圆点<br/>
	 * 参数为View对控件的隐藏、显示、不可见三种
	 * 
	 * @author wang
	 * @param regSiteVisibility
	 * @param carNumVisibility
	 * @param carColorVisibility
	 * @param InDateVisibility
	 * @param annualDateVisibility
	 * @param actPriceVisibility
	 * @since JDK 1.6
	 */
	private void hideflag(int regSiteVisibility, int carColorVisibility,
			int InDateVisibility, int annualDateVisibility,
			int actPriceVisibility)
	{
		mCarDetailInfoRegistrationSiteFlagBtn.setVisibility(regSiteVisibility);
		mCarDetailInfoCarColorFlagBtn.setVisibility(carColorVisibility);
		mCarDetailInfoInsuranceDateFlagBtn.setVisibility(InDateVisibility);
		mCarDetailInfoAnnualInspectionDateFlagBtn
				.setVisibility(annualDateVisibility);
		mCarDetailInfoActualPriceFlagBtn.setVisibility(actPriceVisibility);
	}

	@Override
	public void onDrawerClosed()
	{

		if (!mCarDetailInfoProvinceDrawer.isOpened()
				&& mCarDetailInfoCityDrawer.isOpened())
		{
			mCarDetailInfoCityDrawer.animateClose();
		}

		// 如果关闭后
		RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams(0,
				LayoutParams.MATCH_PARENT);
		if (!mCarDetailInfoCityDrawer.isOpened())
		{
			mCarDetailInfoCityHandle.setLayoutParams(layout);
		}

		if (!mCarDetailInfoProvinceDrawer.isOpened())
		{
			mCarDetailInfoProvinceHandle.setLayoutParams(layout);
		}

		if (!mCarDetailInfoColorDrawer.isOpened())
		{
			mCarDetailInfoColorHandle.setLayoutParams(layout);
		}

		// 关闭省份选择列表的时候，取消选中列表中TextView的颜色值
		// if (mProvinceContentListClickPostion >= 0)
		// {
		// TextView provinceText = (TextView) mCarDetailInfoProvinceContent
		// .getChildAt(mProvinceContentListClickPostion).findViewById(
		// R.id.car_list_content_name);
		// provinceText.setTextColor(getResources().getColor(
		// R.color.black_font));
		// }

		// 关闭市选择列表的时候，取消选中列表中TextView的颜色值
		// if (mCityContentListClickPostion >= 0)
		// {
		// TextView cityText = (TextView) mCarDetailInfoCityContent
		// .getChildAt(mCityContentListClickPostion).findViewById(
		// R.id.car_list_content_name);
		// cityText.setTextColor(getResources().getColor(R.color.black_font));
		// }

		// if (mColorContentListClickPostion >= 0)
		// {
		// TextView colorText = (TextView) mCarDetailInfoColorContent
		// .getChildAt(mColorContentListClickPostion).findViewById(
		// R.id.car_color_name);
		// colorText.setTextColor(getResources().getColor(R.color.black_font));
		// }

	}

	@Override
	public void onDrawerOpened()
	{
		// TODO Auto-generated method stub
		if (mCarDetailInfoProvinceDrawer.isOpened())
		{
			mCarDetailInfoProvinceHandle.setLayoutParams(new LayoutParams(40,
					LayoutParams.MATCH_PARENT));
		}
		if (mCarDetailInfoCityDrawer.isOpened())
		{
			mCarDetailInfoCityHandle.setLayoutParams(new LayoutParams(40,
					LayoutParams.MATCH_PARENT));
		}

		if (mCarDetailInfoColorDrawer.isOpened())
		{
			mCarDetailInfoColorHandle.setLayoutParams(new LayoutParams(40,
					LayoutParams.MATCH_PARENT));
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{
		int viewid = parent.getId();
		switch (viewid) {
		case R.id.car_detail_info_province_content:
			isProvinceClick = true;
			// 开启查询线程
			mProvinceContentListClickPostion = position;
			startCityThread();
			// 如果城市选择列表没有开启则打开列表
			if (!mCarDetailInfoCityDrawer.isOpened())
				mCarDetailInfoCityDrawer.animateOpen();
			TextView name = (TextView) view
					.findViewById(R.id.car_list_content_name);
			// 赋值当前选择省份
			mProvince = name.getText().toString();
			// 修改当前选中项字体颜色
			list.get(mProvinceContentListClickPostion).setColor(
					getResources().getColor(R.color.list_click));
			if (oldPoc != -1 && oldPoc != position)
			{
				list.get(oldPoc).setColor(
						getResources().getColor(R.color.black_font));
			}
			simple2Adpter.notifyDataSetChanged();
			oldPoc = mProvinceContentListClickPostion;
			break;
		case R.id.car_detail_info_city_content:
			TextView city = (TextView) view
					.findViewById(R.id.car_list_content_name);
			// 赋值当前选择城市
			mCity = city.getText().toString();
			mCityContentListClickPostion = position;
			// 如果省列表数据被选择，则填充数据
			if (isProvinceClick)
			{

				mCarDetailInfoRegistrationSiteText.setText(mProvince + ","
						+ mCity);
				appContext.getmCarDamage().setLicenseCityId(
						cities.get(mCityContentListClickPostion).getId());
				if (mCarDetailInfoProvinceDrawer.isOpened())
				{
					mCarDetailInfoProvinceDrawer.animateClose();
				}
				if (mCarDetailInfoCityDrawer.isOpened())
				{
					mCarDetailInfoCityDrawer.animateClose();
				}

			}
			break;
		case R.id.car_detail_info_color_content:
			// 赋值当前选择颜色
			TextView color = (TextView) view.findViewById(R.id.car_color_name);
			mColor = color.getText().toString();
			mColorContentListClickPostion = position;
			if (mCarDetailInfoColorDrawer.isOpened())
			{
				mCarDetailInfoColorDrawer.close();
			}
			mCarDetailInfoCarColorText.setText(mColor);
			appContext.getmCarDamage().setBodyColor(mColor);
			break;
		default:
			break;
		}

	}

	private void startCityThread()
	{
		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				Province province = provinces
						.get(mProvinceContentListClickPostion);
				try
				{
					CityList cityList = HttpService.getCityList(province
							.getId());
					// 添加省id到生成结果对象中去
					appContext.getmCarDamage().setLicenseProvId(
							province.getId());
					MessageUtils.sendMessage(mHandler, R.id.cityList, cityList);
				} catch (Exception e)
				{
					e.printStackTrace();

				}
			}
		}).start();
	}

	/**
	 * 当颜色ListView列表被点击后修改list中字体颜色 modifyTextViewColor: <br/>
	 * 
	 * @author wang
	 * @param carListContentName
	 *            代表TextView
	 * @param position
	 *            代表点击位置
	 * @param parent
	 *            代表ListView
	 * @param view
	 *            代表ListView中的内容
	 * @since JDK 1.6
	 */
	private void modifyColorTextViewColor(int carColorName, int position,
			AdapterView<?> parent, View view)
	{
		TextView name = (TextView) view.findViewById(carColorName);
		name.setTextColor(getResources().getColor(R.color.list_click));
		// 赋值当前选择颜色
		mColor = name.getText().toString();
		if (mColorContentListClickPostion >= 0
				&& mColorContentListClickPostion != position)
		{
			TextView oldName = (TextView) ((ListView) parent).getChildAt(
					mColorContentListClickPostion).findViewById(
					R.id.car_color_name);
			oldName.setTextColor(getResources().getColor(R.color.black_font));
		}
		mColorContentListClickPostion = position;
	}

	/**
	 * 当市ListView列表被点击后修改list中字体颜色 modifyTextViewColor: <br/>
	 * 
	 * @author wang
	 * @param carListContentName
	 *            代表TextView
	 * @param position
	 *            代表点击位置
	 * @param parent
	 *            代表ListView
	 * @param view
	 *            代表ListView中的内容
	 * @since JDK 1.6
	 */
	private void modifyCityTextViewColor(int carListContentName, int position,
			AdapterView<?> parent, View view)
	{
		TextView name = (TextView) view.findViewById(carListContentName);
		name.setTextColor(getResources().getColor(R.color.list_click));
		// 赋值当前选择城市
		mCity = name.getText().toString();
		if (mCityContentListClickPostion >= 0
				&& mCityContentListClickPostion != position)
		{
			TextView oldName = (TextView) ((ListView) parent).getChildAt(
					mCityContentListClickPostion).findViewById(
					R.id.car_list_content_name);
			oldName.setTextColor(getResources().getColor(R.color.black_font));
		}
		mCityContentListClickPostion = position;
	}

	/**
	 * 当省ListView列表被点击后修改list中字体颜色 modifyTextViewColor: <br/>
	 * 
	 * @author wang
	 * @param carListContentName
	 *            代表TextView
	 * @param position
	 *            代表点击位置
	 * @param parent
	 *            代表ListView
	 * @param view
	 *            代表ListView中的内容
	 * @since JDK 1.6
	 * 
	 *        private void modifyProvinceTextViewColor(int carListContentName,
	 *        int position, AdapterView<?> parent, View view) { TextView name =
	 *        (TextView) view.findViewById(carListContentName);
	 *        name.setTextColor(getResources().getColor(R.color.list_click)); //
	 *        赋值当前选择省份 mProvince = name.getText().toString(); if
	 *        (mProvinceContentListClickPostion >= 0 &&
	 *        mProvinceContentListClickPostion != position) { TextView oldName =
	 *        (TextView) ((ListView) parent).getChildAt(
	 *        mProvinceContentListClickPostion).findViewById(
	 *        R.id.car_list_content_name);
	 *        oldName.setTextColor(getResources().getColor(R.color.black_font));
	 *        } mProvinceContentListClickPostion = position; }
	 */
	@Override
	protected void onPause()
	{
		super.onPause();
		// 活动界面暂停的时候对开启的滑动界面进行关闭操作
		closeAllDrawer();
	}

	private void closeAllDrawer()
	{
		if (mCarDetailInfoProvinceDrawer != null
				&& mCarDetailInfoProvinceDrawer.isOpened())
		{
			mCarDetailInfoProvinceDrawer.close();
		}
		if (mCarDetailInfoCityDrawer != null
				&& mCarDetailInfoCityDrawer.isOpened())
		{
			mCarDetailInfoCityDrawer.close();
		}

		if (mCarDetailInfoColorDrawer != null
				&& mCarDetailInfoColorDrawer.isOpened())
		{
			mCarDetailInfoColorDrawer.close();
		}
	}

	DatePickerDialog.OnDateSetListener mInsuranceDateSetListner = new OnDateSetListener()
	{

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth)
		{
			mCarDetailInfoInsuranceDateText.setText(year + "-"
					+ (monthOfYear + 1) + "-01");
			appContext.getmCarDamage().setInsuranceExpireDate(
					year + "-" + (monthOfYear + 1) + "-01");
		}
	};

	DatePickerDialog.OnDateSetListener mInspectionDateSetListner = new OnDateSetListener()
	{

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth)
		{
			mCarDetailInfoAnnualInspectionDateText.setText(year + "-"
					+ (monthOfYear + 1) + "-01");
			appContext.getmCarDamage().setInspectionExpireDate(
					year + "-" + (monthOfYear + 1) + "-01");
		}
	};

}
