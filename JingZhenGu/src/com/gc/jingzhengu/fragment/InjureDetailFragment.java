/**
 * Project Name:JingZhenGu
 * File Name:InjureDetailFragment.java
 * Package Name:com.gc.jingzhengu.fragment
 * Date:2014-6-4下午8:53:40
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.gc.jingzhengu.R;
import com.gc.jingzhengu.adapter.FacadeAdapter;
import com.gc.jingzhengu.adapter.ImpetusAdapter;
import com.gc.jingzhengu.adapter.SimpleAdapter;
import com.gc.jingzhengu.adapter.UpholsteryAdapter;
import com.gc.jingzhengu.app.ActivityHelp;
import com.gc.jingzhengu.app.AppContext;
import com.gc.jingzhengu.app.HttpService;
import com.gc.jingzhengu.service.CarDamageSerivce;
import com.gc.jingzhengu.ui.DetailResultActivity;
import com.gc.jingzhengu.uitls.MessageUtils;
import com.gc.jingzhengu.vo.DetailResult;
import com.gc.jingzhengu.vo.Facade;
import com.gc.jingzhengu.vo.Impetus;
import com.gc.jingzhengu.vo.Upholstery;

import android.R.string;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

/**
 * ClassName:InjureDetailFragment <br/>
 * Function: 报损界面碎片处理. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-4 下午8:53:40 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class InjureDetailFragment extends Fragment implements OnScrollListener,
		OnItemClickListener, OnCheckedChangeListener, OnClickListener
{
	/**
	 * 需要关闭的drawer
	 */
	private ArrayList<SlidingDrawer> closeDrawers = new ArrayList<SlidingDrawer>();

	/**
	 * 缓存facade
	 */
	private ArrayList<Facade> facadeItems = new ArrayList<Facade>();

	/**
	 * 缓存upholstery
	 */
	private ArrayList<Upholstery> upholsteryItems = new ArrayList<Upholstery>();

	/**
	 * 缓存impetus
	 */
	private ArrayList<Impetus> impetusItems = new ArrayList<Impetus>();

	/**
	 * 外观列表
	 */
	private ListView mFacadeList;

	/**
	 * 外观下一步按钮
	 */
	private Button mFacadeNextBtn;

	/**
	 * 内饰列表
	 */
	private ListView mUpholstery;

	/**
	 * 内饰上一步按钮
	 */
	private Button mUpholsteryTopBtn;

	/**
	 * 内饰下一步按钮
	 */
	private Button mUpholsteryNextBtn;

	/**
	 * 动力列表
	 */
	private ListView mImpetus;

	/**
	 * 外观列表适配器
	 */
	private FacadeAdapter mFacadeAdapter;

	/**
	 * 内饰列表适配器
	 */
	private UpholsteryAdapter mUpholsteryAdapter;

	/**
	 * 动力列表适配器
	 */
	private ImpetusAdapter mImpetusAdapter;

	/**
	 * 外观列表Handler
	 */
	private Handler mFacadeDrawerHandler;

	/**
	 * 内饰列表Handler
	 */
	private Handler mUpholsteryDrawerHandler;

	/**
	 * 动力列表Handler
	 */
	private Handler mImpetusDrawerHandler;

	/**
	 * 主体界面操作Handler
	 */
	private Handler mHandler;

	/**
	 * 发动机布局
	 */
	private RelativeLayout mEngineLayout;

	/**
	 * 变速箱布局
	 */
	private RelativeLayout mTransmissionLayout;

	/**
	 * 车轮drawer
	 */
	private SlidingDrawer mFacadeCarriagesDrawer;

	/**
	 * 车轮list
	 */
	private ListView mFacadeCarriagesListContent;

	/**
	 * 车顶drawer
	 */
	private SlidingDrawer mFacadeCarTopDrawer;// 车顶使用

	/**
	 * 车顶list
	 */
	private ListView mFacadeCarTopContent;

	/**
	 * 剐蹭、碰撞一级drawer
	 */
	private SlidingDrawer mFacadeInjureTypeDrawer;// 其他部件使用1级 剐蹭、碰撞
	/**
	 * 剐蹭、碰撞一级list
	 */
	private ListView mFacadeInjureTypeContent;

	/**
	 * 剐蹭2级drawer
	 */
	private SlidingDrawer mFacadeInjureTypelistDrawer;// 其他部件使用2级 剐蹭列表

	/**
	 * 剐蹭2级list
	 */
	private ListView mFacadeInjureTypelistContent;

	/**
	 * 碰撞2级drawer
	 */
	private SlidingDrawer mFacadeInjureCollisionlistDrawer;// 其他部件使用2级 碰撞列表
	/**
	 * 碰撞2级list
	 */
	private ListView mFacadeInjureCollisionlistContent;

	/**
	 * 内饰使用drawer
	 */
	private SlidingDrawer mUpholsteryTypeDrawer;
	/**
	 * 内饰使用list
	 */
	private ListView mUpholsteryTypeListContent;

	/**
	 * 动力使用drawer
	 */
	private SlidingDrawer mImpetusDrawer;
	/**
	 * 动力使用list
	 */
	private ListView mImpetusContent;
	private ImageView mImpetusHandle;

	/**
	 * 动力上一步按钮
	 */
	private Button mImpetusTopBtn;

	/**
	 * 动力下一步按钮
	 */
	private Button mImpetusNextBtn;

	private CheckBox mRunOk;
	private CheckBox mRunExp;
	private CheckBox mRunLeaning;
	private CheckBox mBrakeProblem;

	private CheckBox mChassisOk;
	private CheckBox mChassisDragBottom;
	private CheckBox mSuspensionProblem;
	private CheckBox mShockAbsorberProblem;

	private CheckBox mElectron1;
	private CheckBox mElectron2;
	private CheckBox mElectron3;
	private CheckBox mElectron4;
	private CheckBox mElectron5;
	private CheckBox mElectron6;
	private CheckBox mElectron7;
	private CheckBox mElectron8;
	private CheckBox mElectron9;
	private CheckBox mElectron10;
	private CheckBox mElectron11;
	private CheckBox mElectron12;
	private CheckBox mElectron13;

	/**
	 * 其他界面生成结果按钮
	 */
	private Button mGenResultBtn;
	/**
	 * 其他界面上一步按钮
	 */
	private Button mGenTopBtn;

	/**
	 * 需要在Viewpager中显示的布局id
	 */
	private int mLayoutId;

	/** 内饰列表被点击的位置 */
	private int mUpholsteryPosition = -1;

	/** 外观列表被点击的位置 */
	private int mFacadePosition = -1;

	/** 动力列表被点击的位置 */
	private int mImpetusPosition = -1;

	private static CarDamageSerivce mCarDamageSerivce;

	private AppContext appContext;

	private List<String> mResultData;

	private static Handler mViewPagerHandler;

	public static InjureDetailFragment newInstance(int layout,
			Handler mViewpagerHandler)
	{
		mCarDamageSerivce = new CarDamageSerivce();
		InjureDetailFragment fragment = new InjureDetailFragment();
		fragment.mLayoutId = layout;
		if (mViewPagerHandler == null)
		{
			mViewPagerHandler = mViewpagerHandler;
		}
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		appContext = (AppContext) getActivity().getApplication();
		mResultData = appContext.getmResultData();
		if (mHandler == null)
		{
			mHandler = getmHandler();
		}
	}

	private Handler getmHandler()
	{
		return new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				int id = msg.what;
				switch (id) {
				case R.id.gen_detail_result:
					ActivityHelp.startActivity(getActivity(),
							DetailResultActivity.class, "DetailResult",
							(DetailResult) msg.obj);
					break;
				default:
					break;
				}
			}
		};
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = null;
		switch (mLayoutId) {
		case R.layout.facade:
			view = inflater.inflate(R.layout.facade, null);
			initFacade(view);
			break;
		case R.layout.upholstery:
			view = inflater.inflate(R.layout.upholstery, null);
			initUpholstery(view);
			break;
		case R.layout.impetus:
			view = inflater.inflate(R.layout.impetus, null);
			initImpetus(view);
			break;
		case R.layout.other_problem:
			view = inflater.inflate(R.layout.other_problem, null);
			initOtherProblem(view);
			break;
		default:
			break;
		}
		return view;
	}

	private void initOtherProblem(View view)
	{

		mRunOk = (CheckBox) view.findViewById(R.id.run_ok);
		mRunOk.setOnCheckedChangeListener(this);
		mRunExp = (CheckBox) view.findViewById(R.id.run_exp);
		mRunExp.setOnCheckedChangeListener(this);
		mRunLeaning = (CheckBox) view.findViewById(R.id.run_leaning);
		mRunLeaning.setOnCheckedChangeListener(this);
		mBrakeProblem = (CheckBox) view.findViewById(R.id.brake_problem);
		mBrakeProblem.setOnCheckedChangeListener(this);

		mChassisOk = (CheckBox) view.findViewById(R.id.chassis_ok);
		mChassisOk.setOnCheckedChangeListener(this);
		mChassisDragBottom = (CheckBox) view
				.findViewById(R.id.chassis_drag_bottom);
		mChassisDragBottom.setOnCheckedChangeListener(this);
		mSuspensionProblem = (CheckBox) view
				.findViewById(R.id.suspension_problem);
		mSuspensionProblem.setOnCheckedChangeListener(this);
		mShockAbsorberProblem = (CheckBox) view
				.findViewById(R.id.shock_absorber_problem);
		mShockAbsorberProblem.setOnCheckedChangeListener(this);

		mElectron1 = (CheckBox) view.findViewById(R.id.electron_1);
		mElectron1.setOnCheckedChangeListener(this);
		mElectron2 = (CheckBox) view.findViewById(R.id.electron_2);
		mElectron2.setOnCheckedChangeListener(this);
		mElectron3 = (CheckBox) view.findViewById(R.id.electron_3);
		mElectron3.setOnCheckedChangeListener(this);
		mElectron4 = (CheckBox) view.findViewById(R.id.electron_4);
		mElectron4.setOnCheckedChangeListener(this);
		mElectron5 = (CheckBox) view.findViewById(R.id.electron_5);
		mElectron5.setOnCheckedChangeListener(this);
		mElectron6 = (CheckBox) view.findViewById(R.id.electron_6);
		mElectron6.setOnCheckedChangeListener(this);
		mElectron7 = (CheckBox) view.findViewById(R.id.electron_7);
		mElectron7.setOnCheckedChangeListener(this);
		mElectron8 = (CheckBox) view.findViewById(R.id.electron_8);
		mElectron8.setOnCheckedChangeListener(this);
		mElectron9 = (CheckBox) view.findViewById(R.id.electron_9);
		mElectron9.setOnCheckedChangeListener(this);
		mElectron10 = (CheckBox) view.findViewById(R.id.electron_10);
		mElectron10.setOnCheckedChangeListener(this);
		mElectron11 = (CheckBox) view.findViewById(R.id.electron_11);
		mElectron11.setOnCheckedChangeListener(this);
		mElectron12 = (CheckBox) view.findViewById(R.id.electron_12);
		mElectron12.setOnCheckedChangeListener(this);
		mElectron13 = (CheckBox) view.findViewById(R.id.electron_13);
		mElectron13.setOnCheckedChangeListener(this);
		mRunOk.setChecked(true);
		mChassisOk.setChecked(true);
		mElectron1.setChecked(true);

		mGenResultBtn = (Button) view.findViewById(R.id.gen_result_btn);
		mGenResultBtn.setOnClickListener(this);

		mGenTopBtn = (Button) view.findViewById(R.id.gen_top_btn);
		mGenTopBtn.setOnClickListener(this);
	}

	/**
	 * 
	 * setImpetusData: <br/>
	 * 
	 * @author wang
	 * @since JDK 1.6
	 */
	private void setImpetusData()
	{
		List<String> list = Arrays.asList(getResources().getStringArray(
				R.array.impetus));
		for (int i = 1; i <= list.size(); i++)
		{
			impetusItems.add(new Impetus(i, View.INVISIBLE, View.INVISIBLE,
					list.get(i - 1)));
		}
	}

	private void initUpholstery(View view)
	{
		mUpholsteryDrawerHandler = getUpholsteryDrawerHandler();
		mUpholstery = (ListView) view.findViewById(R.id.upholstery_list);
		mUpholstery.setOnScrollListener(this);
		View v = getActivity().getLayoutInflater().inflate(
				R.layout.bottom_btn_list1, null);
		mUpholsteryTopBtn = (Button) v.findViewById(R.id.top_setup_btn);
		mUpholsteryTopBtn.setOnClickListener(this);
		mUpholsteryNextBtn = (Button) v.findViewById(R.id.next_setup_btn);
		mUpholsteryNextBtn.setOnClickListener(this);
		mUpholstery.addFooterView(v);
		if (upholsteryItems.size() == 0)
			setUpholsteryItem();
		mUpholsteryAdapter = new UpholsteryAdapter(getActivity(),
				upholsteryItems, mUpholsteryDrawerHandler);
		mUpholstery.setAdapter(mUpholsteryAdapter);

		mUpholsteryTypeDrawer = (SlidingDrawer) view
				.findViewById(R.id.upholstery_type_drawer);
		mUpholsteryTypeListContent = (ListView) view
				.findViewById(R.id.upholstery_type_list_content);
		mUpholsteryTypeListContent.setOnItemClickListener(this);
		List<String> list = Arrays.asList(getResources().getStringArray(
				R.array.upholstery_drawer));
		mUpholsteryTypeListContent.setAdapter(new SimpleAdapter(getActivity(),
				list));
	}

	private void initImpetus(View view)
	{
		mImpetusDrawerHandler = getmImpetusDrawerHandler();
		mImpetus = (ListView) view.findViewById(R.id.impetus_list);
		mImpetus.setOnScrollListener(this);
		if (impetusItems.size() == 0)
			setImpetusData();
		mImpetusAdapter = new ImpetusAdapter(getActivity(), impetusItems,
				mImpetusDrawerHandler);
		mImpetus.setAdapter(mImpetusAdapter);

		mImpetusDrawer = (SlidingDrawer) view.findViewById(R.id.impetus_drawer);
		mImpetusContent = (ListView) view.findViewById(R.id.impetus_content);
		mImpetusContent.setOnItemClickListener(this);
		mImpetusHandle = (ImageView) view.findViewById(R.id.impetus_handle);

		mImpetusTopBtn = (Button) view.findViewById(R.id.top_setup_btn);
		mImpetusTopBtn.setOnClickListener(this);
		mImpetusNextBtn = (Button) view.findViewById(R.id.next_setup_btn);
		mImpetusNextBtn.setOnClickListener(this);

		List<String> list = Arrays.asList(getResources().getStringArray(
				R.array.impetus_drawer));
		mImpetusContent.setAdapter(new SimpleAdapter(getActivity(), list));
	}

	private Handler getmImpetusDrawerHandler()
	{

		return new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				if (msg.what != R.id.no_select)
				{
					openDrawer(mImpetusDrawer);
					mImpetusPosition = msg.what;
				} else
				{
					if (mImpetusDrawer.isOpened())
					{
						mImpetusDrawer.close();
					}
					removeElement(CarDamageSerivce.THREE_PAGE, msg.obj);
				}
			}
		};
	}

	private Handler getUpholsteryDrawerHandler()
	{

		return new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				if (msg.what != R.id.no_select)
				{
					openDrawer(mUpholsteryTypeDrawer);
					mUpholsteryPosition = msg.what;
				} else
				{
					if (mUpholsteryTypeDrawer.isOpened())
					{
						mUpholsteryTypeDrawer.close();
					}
					removeElement(CarDamageSerivce.TWO_PAGE, msg.obj);
				}
			}
		};
	}

	/**
	 * 初始化内饰数据 setUpholsteryItem: <br/>
	 * 
	 * @author wang
	 * @since JDK 1.6
	 */
	private void setUpholsteryItem()
	{
		upholsteryItems.clear();
		String[] upholsteryName = getResources().getStringArray(
				R.array.upholstery);
		for (int i = 1; i <= upholsteryName.length; i++)
		{
			upholsteryItems.add(new Upholstery(i, View.INVISIBLE,
					upholsteryName[i - 1], View.INVISIBLE));
		}
	}

	/**
	 * 初始化外观界面 initFacade: <br/>
	 * 
	 * @author wang
	 * @param view
	 * @since JDK 1.6
	 */
	private void initFacade(View view)
	{
		mFacadeDrawerHandler = getFacadeDrawerHandler();
		// 如果缓冲区没有数据则设置初始数据,否则使用之前选定的数据
		// System.out.println("facadeItems.size() is " + facadeItems.size());
		if (facadeItems.size() == 0)
		{
			setDacadeItem();
		}

		mFacadeList = (ListView) view.findViewById(R.id.facade_list);
		mFacadeList.setOnScrollListener(this);
		View v = getActivity().getLayoutInflater().inflate(
				R.layout.bottom_btn_list, null);
		mFacadeNextBtn = (Button) v.findViewById(R.id.bottom_btn);
		mFacadeNextBtn.setOnClickListener(this);
		mFacadeList.addFooterView(v);
		mFacadeAdapter = new FacadeAdapter(getActivity(), facadeItems,
				mFacadeDrawerHandler);
		mFacadeList.setAdapter(mFacadeAdapter);

		mFacadeCarriagesDrawer = (SlidingDrawer) view
				.findViewById(R.id.facade_carriages_drawer);
		mFacadeCarriagesListContent = (ListView) view
				.findViewById(R.id.facade_carriages_list_content);
		List<String> carriages = Arrays.asList(getResources().getStringArray(
				R.array.carriage_drawer));
		mFacadeCarriagesListContent.setAdapter(new SimpleAdapter(getActivity(),
				carriages));
		mFacadeCarriagesListContent.setOnItemClickListener(this);

		mFacadeCarTopDrawer = (SlidingDrawer) view
				.findViewById(R.id.facade_car_top_drawer);
		mFacadeCarTopContent = (ListView) view
				.findViewById(R.id.facade_car_top_content);
		mFacadeCarTopContent.setOnItemClickListener(this);
		mFacadeCarTopContent
				.setAdapter(new SimpleAdapter(getActivity(), Arrays
						.asList(getResources().getStringArray(
								R.array.car_top_drawer))));

		mFacadeInjureTypeDrawer = (SlidingDrawer) view
				.findViewById(R.id.facade_car_injure_type_drawer);
		mFacadeInjureTypeContent = (ListView) view
				.findViewById(R.id.facade_car_injure_type_content);
		mFacadeInjureTypeContent
				.setOnItemClickListener(new OnItemClickListener()
				{

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id)
					{
						openDrawerByPosition(position);
					}

					private void openDrawerByPosition(int position)
					{
						switch (position) {
						case 0:
							if (mFacadeInjureCollisionlistDrawer.isOpened())
								mFacadeInjureCollisionlistDrawer.animateClose();
							openDrawer(mFacadeInjureTypelistDrawer);
							break;
						case 1:
							if (mFacadeInjureTypelistDrawer.isOpened())
								mFacadeInjureTypelistDrawer.animateClose();
							openDrawer(mFacadeInjureCollisionlistDrawer);
							break;
						default:
							break;
						}

					}
				});
		mFacadeInjureTypeContent.setAdapter(new SimpleAdapter(getActivity(),
				Arrays.asList(getResources().getStringArray(
						R.array.car_injure_type))));

		mFacadeInjureTypelistDrawer = (SlidingDrawer) view
				.findViewById(R.id.facade_car_injure_typelist_drawer);
		mFacadeInjureTypelistContent = (ListView) view
				.findViewById(R.id.facade_car_injure_typelist_content);
		mFacadeInjureTypelistContent.setOnItemClickListener(this);

		mFacadeInjureTypelistContent.setAdapter(new SimpleAdapter(
				getActivity(), Arrays.asList(getResources().getStringArray(
						R.array.car_injure_degree1))));

		mFacadeInjureCollisionlistDrawer = (SlidingDrawer) view
				.findViewById(R.id.facade_car_injure_collisionlist_drawer);
		mFacadeInjureCollisionlistContent = (ListView) view
				.findViewById(R.id.facade_car_injure_collisionlist_content);
		mFacadeInjureCollisionlistContent.setOnItemClickListener(this);
		mFacadeInjureCollisionlistContent.setAdapter(new SimpleAdapter(
				getActivity(), Arrays.asList(getResources().getStringArray(
						R.array.car_injure_degree2))));

	}

	private Handler getFacadeDrawerHandler()
	{
		return new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				// 清空需要关闭的缓存列表
				mFacadePosition = msg.what;
				closeDrawers.clear();
				if (msg.what == R.id.no_select)
				{
					closeAllDrawer();
					removeElement(CarDamageSerivce.ONE_PAGE, msg.obj);

				} else if (mFacadePosition <= 3)
				{
					// 关闭其他开启的滑动
					closeDrawers.add(mFacadeCarTopDrawer);
					closeDrawers.add(mFacadeInjureTypeDrawer);
					closeDrawers.add(mFacadeInjureTypelistDrawer);
					closeDrawers.add(mFacadeInjureCollisionlistDrawer);
					closeDrawer(closeDrawers);
					// 开启车轮滑动
					openDrawer(mFacadeCarriagesDrawer);

				} else if (mFacadePosition == 4)
				{
					// 关闭其他开启的滑动
					closeDrawers.add(mFacadeCarriagesDrawer);
					closeDrawers.add(mFacadeInjureTypeDrawer);
					closeDrawers.add(mFacadeInjureTypelistDrawer);
					closeDrawers.add(mFacadeInjureCollisionlistDrawer);
					closeDrawer(closeDrawers);
					// 开启车顶滑动
					openDrawer(mFacadeCarTopDrawer);
				} else
				{
					// 关闭其他开启的滑动
					closeDrawers.add(mFacadeCarriagesDrawer);
					closeDrawers.add(mFacadeCarTopDrawer);
					closeDrawers.add(mFacadeInjureTypelistDrawer);
					closeDrawers.add(mFacadeInjureCollisionlistDrawer);
					closeDrawer(closeDrawers);
					// 开启其他部件一级滑动
					openDrawer(mFacadeInjureTypeDrawer);
				}

			}

		};
	}

	/**
	 * 删除结果列表中当前页面对应的列表位置包含的元素 removeElement: <br/>
	 * 
	 * @author wang
	 * @param onePage
	 * @param obj
	 * @since JDK 1.6
	 */
	protected void removeElement(int pagePosition, Object obj)
	{
		// 获取列表点击位置
		int position = (Integer) obj;
		List<String> elements = mCarDamageSerivce.getElementsByPosition(
				pagePosition, position);
		// 删除对应的列表元素
		for (String element : elements)
		{
			if (mResultData.contains(element))
			{
				mResultData.remove(element);
			}
		}
		System.out.println("mResultData.remove() sezi is " + mResultData.size()
				+ "," + mResultData.toString());
	}

	/**
	 * 开启drawer openDrawer: <br/>
	 * 
	 * @author wang
	 * @param mFacadeCarTopDrawer
	 * @since JDK 1.6
	 */
	private void openDrawer(SlidingDrawer drawer)
	{
		if (!drawer.isOpened())
		{
			drawer.animateOpen();
		} else
		{
			drawer.close();
			drawer.animateOpen();
		}

	}

	/**
	 * 显示外观列表的勾 showTickForFacade: <br/>
	 * 
	 * @author wang
	 * @since JDK 1.6
	 */
	private void showTickForFacade()
	{
		// 设置勾选
		facadeItems.get(mFacadePosition).setTick(View.VISIBLE);
		facadeItems.get(mFacadePosition).setFlagPic(View.INVISIBLE);
		mFacadeAdapter.notifyDataSetChanged();
	}

	/**
	 * 关闭所有开启的drawer closeAllDrawer: <br/>
	 * 
	 * @author wang
	 * @since JDK 1.6
	 */
	private void closeAllDrawer()
	{
		closeDrawers.clear();
		closeDrawers.add(mFacadeCarriagesDrawer);
		closeDrawers.add(mFacadeCarTopDrawer);
		closeDrawers.add(mFacadeInjureTypeDrawer);
		closeDrawers.add(mFacadeInjureTypelistDrawer);
		closeDrawers.add(mFacadeInjureCollisionlistDrawer);
		closeDrawer(closeDrawers);
	}

	/**
	 * 关闭SlidingDrawer closeDrawer: <br/>
	 * 
	 * @author wang
	 * @param drawer
	 * @since JDK 1.6
	 */
	private void closeDrawer(ArrayList<SlidingDrawer> drawers)
	{
		for (SlidingDrawer drawer : drawers)
		{
			if (drawer.isOpened())
			{
				drawer.close();
			}
		}
	}

	/**
	 * 缓存数据 setDacadeItem: <br/>
	 * 
	 * @author wang
	 * @since JDK 1.6
	 */
	private void setDacadeItem()
	{
		// 清空缓存列表数据
		facadeItems.clear();
		String[] facName = getResources().getStringArray(R.array.facade);
		for (int i = 1; i <= facName.length; i++)
		{
			facadeItems.add(new Facade(i, View.INVISIBLE, facName[i - 1],
					View.INVISIBLE));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		System.out.println("onsave");
	}

	// private void changeTextColor(View v)
	// {
	// ((TextView) v.findViewById(R.id.engine_text))
	// .setTextColor(getResources().getColor(R.color.list_click));
	// }

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState)
	{
		switch (scrollState) {
		case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
			closeDrawers.clear();
			if (view.getId() == R.id.facade_list)
			{
				closeDrawers.add(mFacadeCarriagesDrawer);
				closeDrawers.add(mFacadeCarTopDrawer);
				closeDrawers.add(mFacadeInjureTypeDrawer);
				closeDrawers.add(mFacadeInjureTypelistDrawer);
				closeDrawers.add(mFacadeInjureCollisionlistDrawer);
				closeDrawer(closeDrawers);
			} else if (view.getId() == R.id.upholstery_list)
			{
				closeDrawers.add(mUpholsteryTypeDrawer);
				closeDrawer(closeDrawers);
			}

			break;

		default:
			break;
		}

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount)
	{

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{
		int parentId = parent.getId();
		switch (parentId) {
		case R.id.upholstery_type_list_content:
			mUpholsteryTypeDrawer.close();
			// 当滑动列表被点击则显示在当前点击位置显示勾
			upholsteryItems.get(mUpholsteryPosition).setTick(View.VISIBLE);
			upholsteryItems.get(mUpholsteryPosition).setFlagPic(View.INVISIBLE);
			mUpholsteryAdapter.notifyDataSetChanged();
			addResultData(CarDamageSerivce.TWO_PAGE, mUpholsteryPosition,
					position);
			break;
		case R.id.impetus_content:
			mImpetusDrawer.close();
			impetusItems.get(mImpetusPosition).setTickFlag(View.VISIBLE);
			impetusItems.get(mImpetusPosition).setCircleFlag(View.INVISIBLE);
			mImpetusAdapter.notifyDataSetChanged();
			addResultData(CarDamageSerivce.THREE_PAGE, mImpetusPosition,
					position);
			break;
		case R.id.facade_carriages_list_content:
		case R.id.facade_car_top_content:
		case R.id.facade_car_injure_typelist_content:
			closeAllDrawer();
			showTickForFacade();
			addResultData(CarDamageSerivce.ONE_PAGE, mFacadePosition, position);
			break;
		case R.id.facade_car_injure_collisionlist_content:
			closeAllDrawer();
			showTickForFacade();
			addResultData(CarDamageSerivce.ONE_PAGE, mFacadePosition,
					position + 3);
			break;
		default:
			break;
		}
	}

	/**
	 * 标示数据到结果集列表 addResultData: <br/>
	 * 
	 * @author wang
	 * @param position
	 * @since JDK 1.6
	 */
	private void addResultData(int pagePosition, int listPosition, int position)
	{
		String selectid;
		selectid = mCarDamageSerivce.getSelectId(pagePosition, listPosition,
				position);
		mResultData.add(selectid);
		System.out.println("resulData size is " + mResultData.size());
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
	{

		// ==============================车辆行驶问题======================================
		if (isChecked == true && buttonView.getId() == R.id.run_ok)
		{
			mRunExp.setChecked(false);
			mRunLeaning.setChecked(false);
			mBrakeProblem.setChecked(false);
			buttonView.setChecked(true);
			appContext.modifyData1Checked(true, false, false, false);
		} else if (isChecked == false && buttonView.getId() == R.id.run_ok)
		{
			appContext.modifyCheckBox(AppContext.getmFourPageData1(),
					CarDamageSerivce.CHECKBOX_1, false);
		}

		if (buttonView.getId() == R.id.run_exp && isChecked == true)
		{
			mRunOk.setChecked(false);
			appContext.modifyCheckBox(AppContext.getmFourPageData1(),
					CarDamageSerivce.CHECKBOX_1, false);
			appContext.modifyCheckBox(AppContext.getmFourPageData1(),
					CarDamageSerivce.CHECKBOX_2, true);
		} else if (buttonView.getId() == R.id.run_exp && isChecked == false)
		{
			appContext.modifyCheckBox(AppContext.getmFourPageData1(),
					CarDamageSerivce.CHECKBOX_2, false);
		}

		if (buttonView.getId() == R.id.run_leaning && isChecked == true)
		{
			mRunOk.setChecked(false);
			appContext.modifyCheckBox(AppContext.getmFourPageData1(),
					CarDamageSerivce.CHECKBOX_1, false);
			appContext.modifyCheckBox(AppContext.getmFourPageData1(),
					CarDamageSerivce.CHECKBOX_3, true);
		} else if (buttonView.getId() == R.id.run_leaning && isChecked == false)
		{
			appContext.modifyCheckBox(AppContext.getmFourPageData1(),
					CarDamageSerivce.CHECKBOX_3, false);
		}

		if (buttonView.getId() == R.id.brake_problem && isChecked == true)
		{
			mRunOk.setChecked(false);
			appContext.modifyCheckBox(AppContext.getmFourPageData1(),
					CarDamageSerivce.CHECKBOX_1, false);
			appContext.modifyCheckBox(AppContext.getmFourPageData1(),
					CarDamageSerivce.CHECKBOX_4, true);

		} else if (buttonView.getId() == R.id.brake_problem
				&& isChecked == false)
		{
			appContext.modifyCheckBox(AppContext.getmFourPageData1(),
					CarDamageSerivce.CHECKBOX_4, false);
		}

		// Set<String> key = appContext.getmFourPageData1().keySet();
		// for (Iterator<String> it = key.iterator(); it.hasNext();)
		// {
		// String s = (String) it.next();
		// System.out
		// .println(s + "==" + appContext.getmFourPageData1().get(s));
		// }

		// =======================车辆底盘问题=================================================

		if (isChecked == true && buttonView.getId() == R.id.chassis_ok)
		{
			mChassisDragBottom.setChecked(false);
			mSuspensionProblem.setChecked(false);
			mShockAbsorberProblem.setChecked(false);
			buttonView.setChecked(true);
			appContext.modifyData2Checked(true, false, false, false);
		} else if (isChecked == false && buttonView.getId() == R.id.chassis_ok)
		{
			appContext.modifyCheckBox(AppContext.getmFourPageData2(),
					CarDamageSerivce.CHECKBOX_5, false);
		}

		if (buttonView.getId() == R.id.chassis_drag_bottom && isChecked == true)
		{
			mChassisOk.setChecked(false);
			appContext.modifyCheckBox(AppContext.getmFourPageData2(),
					CarDamageSerivce.CHECKBOX_5, false);
			appContext.modifyCheckBox(AppContext.getmFourPageData2(),
					CarDamageSerivce.CHECKBOX_6, true);
		} else if (buttonView.getId() == R.id.chassis_drag_bottom
				&& isChecked == false)
		{
			appContext.modifyCheckBox(AppContext.getmFourPageData2(),
					CarDamageSerivce.CHECKBOX_6, false);
		}

		if (buttonView.getId() == R.id.suspension_problem && isChecked == true)
		{
			mChassisOk.setChecked(false);
			appContext.modifyCheckBox(AppContext.getmFourPageData2(),
					CarDamageSerivce.CHECKBOX_5, false);
			appContext.modifyCheckBox(AppContext.getmFourPageData2(),
					CarDamageSerivce.CHECKBOX_7, true);
		} else if (buttonView.getId() == R.id.suspension_problem
				&& isChecked == false)
		{
			appContext.modifyCheckBox(AppContext.getmFourPageData2(),
					CarDamageSerivce.CHECKBOX_7, false);
		}

		if (buttonView.getId() == R.id.shock_absorber_problem
				&& isChecked == true)
		{
			mChassisOk.setChecked(false);
			appContext.modifyCheckBox(AppContext.getmFourPageData2(),
					CarDamageSerivce.CHECKBOX_5, false);
			appContext.modifyCheckBox(AppContext.getmFourPageData2(),
					CarDamageSerivce.CHECKBOX_8, true);
		} else if (buttonView.getId() == R.id.shock_absorber_problem
				&& isChecked == true)
		{
			appContext.modifyCheckBox(AppContext.getmFourPageData2(),
					CarDamageSerivce.CHECKBOX_8, false);
		}

		// Set<String> key = AppContext.getmFourPageData2().keySet();
		// for (Iterator<String> it = key.iterator(); it.hasNext();)
		// {
		// String s = (String) it.next();
		// System.out
		// .println(s + "==" + AppContext.getmFourPageData2().get(s));
		// }

		// =======================电子设备问题==============================================

		if (isChecked == true && buttonView.getId() == R.id.electron_1)
		{
			mElectron2.setChecked(false);
			mElectron3.setChecked(false);
			mElectron4.setChecked(false);
			mElectron5.setChecked(false);
			mElectron6.setChecked(false);
			mElectron7.setChecked(false);
			mElectron8.setChecked(false);
			mElectron9.setChecked(false);
			mElectron10.setChecked(false);
			mElectron11.setChecked(false);
			mElectron12.setChecked(false);
			mElectron13.setChecked(false);
			buttonView.setChecked(true);
			boolean[] value = { true, false, false, false, false, false, false,
					false, false, false, false, false, false };
			appContext.modifyData3Checked(value);
		} else if (isChecked == false && buttonView.getId() == R.id.electron_1)
		{
			buttonView.setChecked(false);
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_9, false);
		}

		if (buttonView.getId() == R.id.electron_2 && isChecked == true)
		{
			mElectron1.setChecked(false);
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_9, false);
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_10, true);
		} else if (buttonView.getId() == R.id.electron_2 && isChecked == false)
		{
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_10, false);
		}

		if (buttonView.getId() == R.id.electron_3 && isChecked == true)
		{
			mElectron1.setChecked(false);
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_9, false);
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_11, true);
		} else if (buttonView.getId() == R.id.electron_3 && isChecked == false)
		{
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_11, false);
		}

		if (buttonView.getId() == R.id.electron_4 && isChecked == true)
		{
			mElectron1.setChecked(false);
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_9, false);
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_12, true);
		} else if (buttonView.getId() == R.id.electron_4 && isChecked == false)
		{
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_12, false);
		}

		if (buttonView.getId() == R.id.electron_5 && isChecked == true)
		{
			mElectron1.setChecked(false);
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_9, false);
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_13, true);
		} else if (buttonView.getId() == R.id.electron_5 && isChecked == false)
		{
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_13, false);
		}

		if (buttonView.getId() == R.id.electron_6 && isChecked == true)
		{
			mElectron1.setChecked(false);
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_9, false);
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_14, true);
		} else if (buttonView.getId() == R.id.electron_6 && isChecked == false)
		{
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_14, false);
		}

		if (buttonView.getId() == R.id.electron_7 && isChecked == true)
		{
			mElectron1.setChecked(false);
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_9, false);
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_15, true);
		} else if (buttonView.getId() == R.id.electron_7 && isChecked == false)
		{
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_15, false);
		}

		if (buttonView.getId() == R.id.electron_8 && isChecked == true)
		{
			mElectron1.setChecked(false);
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_9, false);
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_16, true);
		} else if (buttonView.getId() == R.id.electron_8 && isChecked == false)
		{
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_16, false);
		}

		if (buttonView.getId() == R.id.electron_9 && isChecked == true)
		{
			mElectron1.setChecked(false);
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_9, false);
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_17, true);
		} else if (buttonView.getId() == R.id.electron_9 && isChecked == false)
		{
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_17, false);
		}

		if (buttonView.getId() == R.id.electron_10 && isChecked == true)
		{
			mElectron1.setChecked(false);
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_9, false);
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_18, true);
		} else if (buttonView.getId() == R.id.electron_10 && isChecked == false)
		{
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_18, false);
		}

		if (buttonView.getId() == R.id.electron_11 && isChecked == true)
		{
			mElectron1.setChecked(false);
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_9, false);
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_19, true);
		} else if (buttonView.getId() == R.id.electron_11 && isChecked == false)
		{
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_19, false);
		}

		if (buttonView.getId() == R.id.electron_12 && isChecked == true)
		{
			mElectron1.setChecked(false);
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_9, false);
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_20, true);
		} else if (buttonView.getId() == R.id.electron_12 && isChecked == false)
		{
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_20, false);
		}

		if (buttonView.getId() == R.id.electron_13 && isChecked == true)
		{
			mElectron1.setChecked(false);
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_9, false);
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_21, true);
		} else if (buttonView.getId() == R.id.electron_13 && isChecked == false)
		{
			appContext.modifyCheckBox(AppContext.getmFourPageData3(),
					CarDamageSerivce.CHECKBOX_21, false);
		}

		// Set<String> key = AppContext.getmFourPageData3().keySet();
		// for (Iterator<String> it = key.iterator(); it.hasNext();)
		// {
		// String s = (String) it.next();
		// System.out
		// .println(s + "==" + AppContext.getmFourPageData3().get(s));
		// }

	}

	@Override
	public void onClick(View v)
	{
		int id = v.getId();
		switch (id) {
		case R.id.gen_result_btn:
			startGenResultThread();
			break;
		case R.id.gen_top_btn:
			MessageUtils.sendMessage(mViewPagerHandler, R.id.gen_top_btn, null);
			break;
		case R.id.top_setup_btn:
			MessageUtils.sendMessage(mViewPagerHandler, R.id.top_setup_btn,
					null);
			break;
		case R.id.next_setup_btn:
			MessageUtils.sendMessage(mViewPagerHandler, R.id.next_setup_btn,
					null);
			break;
		case R.id.bottom_btn:
			MessageUtils.sendMessage(mViewPagerHandler, R.id.bottom_btn, null);
			break;
		default:
			break;
		}
	}

	/**
	 * 获取生成详细结果线程 startGenResultThread: <br/>
	 * 
	 * @author wang
	 * @since JDK 1.6
	 */
	private void startGenResultThread()
	{

		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					DetailResult detailResult = HttpService
							.sendGenResultData(appContext);
					MessageUtils.sendMessage(mHandler, R.id.gen_detail_result,
							detailResult);

				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}).start();
	}
}
