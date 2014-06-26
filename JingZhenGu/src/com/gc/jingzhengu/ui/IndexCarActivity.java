/**
 * Project Name:JingZhenGu
 * File Name:IndexCarActivity.java
 * Package Name:com.gc.jingzhengu.ui
 * Date:2014-5-23下午12:28:50
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.ui;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Shader.TileMode;
import android.hardware.Camera.Size;
import android.net.NetworkInfo.DetailedState;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.View.OnSystemUiVisibilityChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.SlidingDrawer.OnDrawerScrollListener;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.jingzhengu.R;
import com.gc.jingzhengu.adapter.CarModelAdapter;
import com.gc.jingzhengu.adapter.StyleCategoryAdapter;
import com.gc.jingzhengu.adapter.ModelCategoryAdapter;
import com.gc.jingzhengu.app.ActivityHelp;
import com.gc.jingzhengu.app.HttpService;
import com.gc.jingzhengu.constant.Constant;
import com.gc.jingzhengu.uitls.ChineseUtil;
import com.gc.jingzhengu.uitls.HttpClientUtils;
import com.gc.jingzhengu.uitls.MessageUtils;
import com.gc.jingzhengu.view.MyLetterListView;
import com.gc.jingzhengu.view.MyLetterListView.OnTouchingLetterChangedListener;
import com.gc.jingzhengu.vo.Car;
import com.gc.jingzhengu.vo.CarType;
import com.gc.jingzhengu.vo.ModelCategory;
import com.gc.jingzhengu.vo.Make;
import com.gc.jingzhengu.vo.MakeList;
import com.gc.jingzhengu.vo.Model;
import com.gc.jingzhengu.vo.ModelList;
import com.gc.jingzhengu.vo.Style;
import com.gc.jingzhengu.vo.StyleCategory;
import com.gc.jingzhengu.vo.StyleList;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * ClassName:IndexCarActivity <br/>
 * Function: 选择车分类 <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-5-23 下午12:28:50 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class IndexCarActivity extends BaseActivity implements OnClickListener,
		OnItemClickListener, OnDrawerOpenListener, OnDrawerCloseListener,
		OnScrollListener
{

	private MyLetterListView mIndexListView = null;
	private ListView mIndexCarListView = null;
	private ImageButton mReturnBtn = null;

	// 存放存在的汉语拼音首字母
	private String[] sections;

	// 存放存在的汉语拼音首字母和与之对应的列表位置
	private HashMap<String, Integer> index;

	/**
	 * 汽车系列drawer
	 */
	private SlidingDrawer mCarTypeDrawer;
	private ImageView mCarTypeHandle;
	private ListView mCarTypeContent;

	/**
	 * 汽车类型drawer
	 */
	private SlidingDrawer mCarYearstyleDrawer;
	private ImageView mCarYearstyleHandle;
	private ListView mCarYearstyleContent;

	/**
	 * 品牌列表
	 */
	private MakeList mMakeList;

	/**
	 * 车系列表
	 */
	private ModelList mModelList;

	/**
	 * 类型列表
	 */
	private StyleList mStyleList;

	/**
	 * 品牌、车系、车型控制
	 */
	private Handler mHandler;

	/**
	 * 汽车品牌排序集合
	 */
	private ArrayList<Map<String, Object>> items;

	/**
	 * 当前选择的汽车品牌
	 */
	private String mCurMake;

	/**
	 * 当前选择的品牌id
	 */
	private int mCurMakeid;

	/**
	 * 当前选择的汽车车系
	 */
	private String mCurModel;

	/**
	 * 当前选择的车系id
	 */
	private int mCurModelid;

	/**
	 * 当前选择的汽车类型
	 */
	private String mCurStyle;

	/**
	 * 当前选择的类型id
	 */
	private int mCurStyleid;

	/**
	 * 解析后车系json数据封装
	 */
	private List<ModelCategory> mModelCategorys;

	/**
	 * 车系列表所有数据包括标题
	 */
	private List<Model> mModels;

	/**
	 * 所有车系标题
	 */
	private List<String> mModelsGroupkey;

	/**
	 * 车型列表所有数据包括标题
	 */
	private List<Style> mStyles;

	/**
	 * 所有车型标题
	 */
	private List<String> mStylessGroupkey;

	/**
	 * 车型json解析数据封装
	 */
	private List<StyleCategory> carStyles;

	/**
	 * 品牌列表适配器
	 */
	private ListAdapter mIndexCarListAdapter;

	/**
	 * 车系列表适配器
	 */
	private ModelCategoryAdapter mModelCategoryAdapter;

	/**
	 * 车型列表适配器
	 */
	private StyleCategoryAdapter mStyleCategoryAdapter;

	/**
	 * 车系列表的上一次点击位置
	 */
	private int mModelListOldPosition = -1;

	/**
	 * 车系列表的上一次点击位置
	 */
	private int mMakeListOldPosition = -1;

	/**
	 * 图片加载配置选项
	 */
	private DisplayImageOptions mOptions;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index_car);
		init();
		mHandler = getHandler();
		mOptions = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.jingzhengu)
				.showImageForEmptyUri(R.drawable.jingzhengu)
				.showImageOnFail(R.drawable.jingzhengu).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(20)).build();

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
				case R.id.makelist:
					// 组装汽车品牌数据
					assemblyMakeList(msg);
					break;
				case R.id.modellist:
					colseDrawer(mCarYearstyleDrawer);
					if (checkModelist(msg))
					{
						openDrawer(mCarTypeDrawer);
						// 组装汽车系列数据
						assemblyModelList(msg);
					} else
					{
						colseDrawer(mCarTypeDrawer);
					}
					break;
				case R.id.stylelist:
					// 组装汽车类型数据
					if (checkResult(msg))
					{
						assemblyStyleList(msg);
					}
					break;
				default:
					break;
				}

			}

			private void assemblyStyleList(Message msg)
			{
				openDrawer(mCarYearstyleDrawer);
				StyleList styleList = (StyleList) msg.obj;
				carStyles = styleList.getCarStyles();
				mStyles = new ArrayList<Style>();
				mStylessGroupkey = new ArrayList<String>();
				for (StyleCategory category : carStyles)
				{
					Style style = new Style();
					String groupName = category.getYearTitle();
					mStylessGroupkey.add(groupName);
					style.setName(groupName);
					style.setFontColor(getResources().getColor(
							R.color.categroy_title));
					// 添加标题到列表
					mStyles.add(style);
					// 添加所有选项到列表
					mStyles.addAll(category.getCategoryItem());
				}

				mStyleCategoryAdapter = new StyleCategoryAdapter(
						getApplicationContext(), mStyles, mStylessGroupkey);
				mCarYearstyleContent.setAdapter(mStyleCategoryAdapter);

			}

			private void assemblyModelList(Message msg)
			{
				ModelList modelList = (ModelList) msg.obj;
				mModelCategorys = modelList.getModels();
				System.out.println("mModelCategorys size "
						+ mModelCategorys.size());
				mModels = new ArrayList<Model>();
				mModelsGroupkey = new ArrayList<String>();
				for (ModelCategory category : mModelCategorys)
				{
					Model model = new Model();
					String groupName = category.getmCategoryName();
					mModelsGroupkey.add(groupName);
					model.setName(groupName);
					model.setManufacturerName(Constant.IS_TITLE);
					model.setFontColor(getResources().getColor(
							R.color.categroy_title));
					mModels.add(model);
					mModels.addAll(category.getmCategoryItem());
				}

				mModelCategoryAdapter = new ModelCategoryAdapter(
						getApplicationContext(), mModels, mModelsGroupkey);
				mCarTypeContent.setAdapter(mModelCategoryAdapter);

			}

			ArrayList<Make> makes;

			private void assemblyMakeList(Message msg)
			{
				String contactSort = null;
				items = new ArrayList<Map<String, Object>>();
				MakeList makeList = (MakeList) msg.obj;
				makes = makeList.getMakes();
				Map<String, Object> map = null;
				for (int i = 0; i < makes.size(); i++)
				{
					map = new HashMap<String, Object>();
					map.put("name", makes.get(i).getMakeName());
					map.put("fontColor", makes.get(i).getFontColor());
					map.put("logo", makes.get(i).getMakeLogo());
					contactSort = ChineseUtil
							.getFullSpell(map.get("name").toString())
							.toUpperCase().substring(0, 1);
					map.put("Sort", contactSort);
					// System.out.println("full Spell is ：" + contactSort);
					items.add(map);
				}
				Comparator comp = new Mycomparator();
				Collections.sort(items, comp);
				mIndexCarListAdapter = new ListAdapter(getApplicationContext(),
						items);
				mIndexCarListView.setAdapter(mIndexCarListAdapter);
			}
		};
	}

	protected void openDrawer(SlidingDrawer mDrawer)
	{
		if (!mDrawer.isOpened())
		{
			mDrawer.animateOpen();
		}
	}

	protected void colseDrawer(SlidingDrawer mDrawer)
	{
		if (mDrawer.isOpened())
		{
			mDrawer.close();
		}
	}

	/**
	 * 校验Modellist返回结果 checkModelist: <br/>
	 * 
	 * @author wang
	 * @param msg
	 * @return
	 * @since JDK 1.6
	 */
	protected boolean checkModelist(Message msg)
	{
		ModelList modelList = (ModelList) msg.obj;
		if (modelList.getStatus() != SUCCESS)
			return false;
		return true;
	}

	protected boolean checkResult(Message msg)
	{
		StyleList styleList = (StyleList) msg.obj;
		int status = styleList.getStatus();
		if (status != SUCCESS)
		{
			showError(styleList.getMsg());
			return false;
		}
		return true;
	}

	// 按中文拼音排序
	public class Mycomparator implements Comparator
	{
		public int compare(Object o1, Object o2)
		{
			Map<String, Object> c1 = (Map<String, Object>) o1;
			Map<String, Object> c2 = (Map<String, Object>) o2;
			Comparator cmp = Collator.getInstance(java.util.Locale.ENGLISH);
			return cmp.compare(c1.get("Sort"), c2.get("Sort"));
		}
	}

	private static class AnimateFirstDisplayListener extends
			SimpleImageLoadingListener
	{

		static final List<String> displayedImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage)
		{
			if (loadedImage != null)
			{
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay)
				{
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}

	@Override
	protected void onRestart()
	{
		super.onRestart();
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		// 活动界面暂停的时候对开启的滑动界面进行关闭操作
		if (mCarTypeDrawer != null && mCarTypeDrawer.isOpened())
		{
			mCarTypeDrawer.close();
		}
		if (mCarYearstyleDrawer != null && mCarYearstyleDrawer.isOpened())
		{
			mCarYearstyleDrawer.close();
		}
	}

	@Override
	public void init()
	{
		// 初始化汽车品牌
		mIndexListView = (MyLetterListView) findViewById(R.id.index_car_index_list);
		mIndexListView
				.setOnTouchingLetterChangedListener(new LetterListViewListener());
		mIndexCarListView = (ListView) findViewById(R.id.index_car_list);
		mIndexCarListView.setAlpha(200);
		mIndexCarListView.setOnItemClickListener(this);
		// 开启汽车品牌查询线程
		startMakeListThread();
		mReturnBtn = (ImageButton) findViewById(R.id.return_btn);
		mReturnBtn.setOnClickListener(this);

		// 初始化汽车类型
		mCarTypeDrawer = (SlidingDrawer) this
				.findViewById(R.id.index_car_type_drawer);
		mCarTypeHandle = (ImageView) this
				.findViewById(R.id.index_car_type_handle);
		mCarTypeContent = (ListView) this
				.findViewById(R.id.index_car_type_list_content);
		mCarTypeContent.setOnItemClickListener(this);
		// 设置SlidingDrawer打开或者关闭时的监听器，设置失去
		mCarTypeDrawer.setOnDrawerOpenListener(this);
		mCarTypeDrawer.setOnDrawerCloseListener(this);

		// 设置mCarTypeDrawer滑动时监听，如果mCarYearstyleDrawer是打开状态则先关闭
		mCarTypeDrawer.setOnDrawerScrollListener(new OnDrawerScrollListener()
		{

			@Override
			public void onScrollStarted()
			{
				colseDrawer(mCarYearstyleDrawer);
			}

			@Override
			public void onScrollEnded()
			{

			}
		});

		// 初始化汽车年度款式
		mCarYearstyleDrawer = (SlidingDrawer) this
				.findViewById(R.id.index_car_yearstyle_drawer);
		mCarYearstyleHandle = (ImageView) this
				.findViewById(R.id.index_car_yearstyle_handle);

		mCarYearstyleContent = (ListView) this
				.findViewById(R.id.index_car_yearstyle_content);
		mCarYearstyleContent.setOnItemClickListener(this);

		// 设置SlidingDrawer打开或者关闭时的监听器
		mCarYearstyleDrawer.setOnDrawerOpenListener(this);
		mCarYearstyleDrawer.setOnDrawerCloseListener(this);

		mIndexCarListView.setOnScrollListener(this);

	}

	/**
	 * 汽车品牌查询线程 runCarListThread: <br/>
	 * 
	 * @author wang
	 * @since JDK 1.6
	 */
	private void startMakeListThread()
	{
		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					mMakeList = HttpService.getMakeList();
					MessageUtils
							.sendMessage(mHandler, R.id.makelist, mMakeList);
				} catch (Exception e)
				{
					e.printStackTrace();
					System.out.println("Exception is " + e);
				}
			}
		}).start();
	}

	@Override
	public void onClick(View v)
	{
		finish();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{
		int viewid = parent.getId();
		switch (viewid) {
		// 品牌
		case R.id.index_car_list:
			Make make = mMakeList.getMakes().get(position);
			final String makeid = String.valueOf(make.getMakeId());
			// 设置当前选择的品牌、id
			mCurMake = make.getMakeName();
			mCurMakeid = make.getMakeId();
			System.out.println("makeid is " + makeid);
			modifyMakeFontColor(view, R.id.car_name, R.color.list_click,
					position);
			startModelListThread(makeid);
			// 每次点击选品牌列表后，都对车系最后一次点击位置进行刷新
			mModelListOldPosition = -1;
			break;
		// 车系
		case R.id.index_car_type_list_content:
			modifyModelFontColor(view, R.id.addexam_list_item_text,
					R.color.list_click, position);
			int modelid = mModels.get(position).getId();
			mCurModelid = modelid;
			mCurModel = mModels.get(position).getName();
			startStyleListThread(String.valueOf(modelid));
			break;
		// 车型
		case R.id.index_car_yearstyle_content:
			Style style = mStyles.get(position);
			mCurStyle = style.getName();
			mCurStyleid = style.getId();
			startCarInfoActivity();
			break;
		default:
			showError("哥们，出错了请仔细检查！");
			break;
		}
	}

	/**
	 * 修改品牌列表字体点击颜色 modifyMakeFontColor: <br/>
	 * 
	 * @author wang
	 * @param view
	 * @param carName
	 * @param listClick
	 * @param position
	 * @since JDK 1.6
	 */
	private void modifyMakeFontColor(View view, int resid, int listClickColor,
			int position)
	{
		TextView textView = (TextView) view.findViewById(resid);
		textView.setTextColor(getResources().getColor(listClickColor));
		items.get(position).put("fontColor",
				getResources().getColor(listClickColor));
		// items.get(position).setFontColor(
		// );
		if (-1 != mMakeListOldPosition && mMakeListOldPosition != position)
		{
			items.get(mMakeListOldPosition).put("fontColor", Color.BLACK);
			// mModels.get(mMakeListOldPosition).setFontColor(Color.BLACK);
		}
		mMakeListOldPosition = position;
		mIndexCarListAdapter.notifyDataSetChanged();
	}

	/**
	 * 修改车系列表字体点击颜色 modifyFontColor: <br/>
	 * 
	 * @author wang
	 * @param view
	 * @param resid
	 * @param listClickColor
	 * @param oldPosition
	 * @param position
	 * @since JDK 1.6
	 */
	private void modifyModelFontColor(View view, int resid, int listClickColor,
			int position)
	{
		TextView textView = (TextView) view.findViewById(resid);
		textView.setTextColor(getResources().getColor(listClickColor));
		mModels.get(position).setFontColor(
				getResources().getColor(listClickColor));
		if (-1 != mModelListOldPosition && mModelListOldPosition != position)
		{
			mModels.get(mModelListOldPosition).setFontColor(Color.BLACK);
		}
		mModelListOldPosition = position;
		mModelCategoryAdapter.notifyDataSetChanged();
	}

	/**
	 * 
	 * startCarInfoActivity: <br/>
	 * 根据汽车的品牌、车系、类型跳转到汽车信息界面
	 * 
	 * @author wang
	 * @since JDK 1.6
	 */
	private void startCarInfoActivity()
	{
		Car car = new Car();
		car.setMakeName(mCurMake);
		car.setModelName(mCurModel);
		car.setStyleName(mCurStyle);
		car.setMakeid(mCurMakeid);
		car.setModelid(mCurModelid);
		car.setStyleid(mCurStyleid);
		ActivityHelp.startActivity(this, CarInfoActivity.class, "car", car);
	}

	/**
	 * 车型查询线程 startStyleListThread: <br/>
	 * 
	 * @author wang
	 * @param modelid
	 * @since JDK 1.6
	 */
	private void startStyleListThread(final String modelid)
	{
		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					mStyleList = HttpService.getStyleList(modelid);
					MessageUtils.sendMessage(mHandler, R.id.stylelist,
							mStyleList);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * 车系查询线程 startModelListThread: <br/>
	 * 
	 * @author wang
	 * @param makeid
	 * @since JDK 1.6
	 */
	private void startModelListThread(final String makeid)
	{
		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					mModelList = HttpService.getModelList(makeid);
					MessageUtils.sendMessage(mHandler, R.id.modellist,
							mModelList);
				} catch (Exception e)
				{

					e.printStackTrace();

				}
			}
		}).start();
	}

	/**
	 * a-z索引监听 ClassName: LetterListViewListener <br/>
	 * Function: TODO ADD FUNCTION. <br/>
	 * Reason: TODO ADD REASON. <br/>
	 * date: 2014-6-2 下午3:44:10 <br/>
	 * 
	 * @author wang
	 * @version IndexCarActivity
	 * @since JDK 1.6
	 */
	class LetterListViewListener implements OnTouchingLetterChangedListener
	{

		@Override
		public void onTouchingLetterChanged(final String s)
		{
			if (index != null && index.get(s) != null)
			{
				int position = index.get(s);
				mIndexCarListView.setSelection(position);
				// overlay.setText(sections[position]);
				// overlay.setVisibility(View.VISIBLE);
				// handler.removeCallbacks(overlayThread);
				// 延迟一秒后执行，让overlay为不可见
				// handler.postDelayed(overlayThread, 1500);
			}
		}

	}

	/**
	 * 品牌列表适配器 ClassName: ListAdapter <br/>
	 * Function: TODO ADD FUNCTION. <br/>
	 * Reason: TODO ADD REASON. <br/>
	 * date: 2014-6-26 下午1:17:09 <br/>
	 * 
	 * @author wang
	 * @version IndexCarActivity
	 * @since JDK 1.6
	 */
	class ListAdapter extends BaseAdapter
	{
		private ImageLoadingListener mAnimateFirstListener = new AnimateFirstDisplayListener();
		private LayoutInflater inflater;
		private List<Map<String, Object>> list;

		public ListAdapter(Context context, List<Map<String, Object>> list)
		{

			this.inflater = LayoutInflater.from(context);
			this.list = list;
			Map<String, Integer> alphaIndexer = new HashMap<String, Integer>();
			sections = new String[list.size()];
			for (int i = 0; i < list.size(); i++)
			{
				// 当前汉语拼音首字母
				String currentStr = list.get(i).get("Sort").toString();
				// 上一个汉语拼音首字母，如果不存在为“ ”
				String previewStr = (i - 1) >= 0 ? list.get(i - 1).get("Sort")
						.toString() : " ";
				if (!previewStr.equals(currentStr))
				{
					String name = list.get(i).get("Sort").toString();
					alphaIndexer.put(name, i);
					System.out
							.println("name is " + name + ", position is " + i);
					sections[i] = name;
				}
			}
			index = (HashMap<String, Integer>) alphaIndexer;

		}

		@Override
		public int getCount()
		{
			return list.size();
		}

		@Override
		public Object getItem(int position)
		{
			return list.get(position);
		}

		@Override
		public long getItemId(int position)
		{
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			ViewHolder holder;
			if (convertView == null)
			{
				convertView = inflater.inflate(R.layout.car_list_content, null);
				holder = new ViewHolder();
				holder.iamge = (ImageView) convertView
						.findViewById(R.id.car_image);
				holder.name = (TextView) convertView
						.findViewById(R.id.car_name);
				holder.alpha = (TextView) convertView.findViewById(R.id.alpha);
				convertView.setTag(holder);
			} else
			{
				holder = (ViewHolder) convertView.getTag();
			}
			holder.name.setText(list.get(position).get("name").toString());
			holder.name.setTextColor((Integer) list.get(position).get(
					"fontColor"));

			String imgUrl = (String) list.get(position).get("logo");
			// 品牌logo异步加载
			imageLoader.displayImage(imgUrl, holder.iamge, mOptions,
					mAnimateFirstListener);
			String currentStr = list.get(position).get("Sort").toString();
			String previewStr = (position - 1) >= 0 ? list.get(position - 1)
					.get("Sort").toString() : " ";
			if (!previewStr.equals(currentStr))
			{
				holder.alpha.setVisibility(View.VISIBLE);
				holder.alpha.setText(currentStr);
			} else
			{
				holder.alpha.setVisibility(View.GONE);
			}
			return convertView;
		}

		private class ViewHolder
		{
			ImageView iamge;
			TextView name;
			TextView alpha;
		}

	}

	@Override
	public void onDrawerClosed()
	{
		// Toast.makeText(this, "onDrawerClosed", Toast.LENGTH_SHORT).show();

		// 如果mCarTypeDrawer关闭，但是mCarYearstyleDrawer还开启着，则mCarYearstyleDrawer也关联关闭
		if (!mCarTypeDrawer.isOpened() && mCarYearstyleDrawer.isOpened())
		{
			mCarYearstyleDrawer.close();
		}
		// 如果CarYearstyleDrawer关闭，则调整handle的初始宽度为0
		RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams(0,
				LayoutParams.MATCH_PARENT);
		if (!mCarYearstyleDrawer.isOpened())
		{
			mCarYearstyleHandle.setLayoutParams(layout);
		}

		// 如果mCarTypeDrawer关闭，则调整两个Drawer的handle的初始宽度为0
		if (!mCarTypeDrawer.isOpened())
		{
			mCarTypeHandle.setLayoutParams(layout);
		}
	}

	@Override
	public void onDrawerOpened()
	{
		if (mCarTypeDrawer.isOpened())
		{
			mCarTypeHandle.setLayoutParams(new LayoutParams(40,
					LayoutParams.MATCH_PARENT));
		}
		if (mCarYearstyleDrawer.isOpened())
		{
			mCarYearstyleHandle.setLayoutParams(new LayoutParams(40,
					LayoutParams.MATCH_PARENT));
		}
		// Toast.makeText(this, "onDrawerOpened", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState)
	{
		// ListView开始滚动和结束滚动时候会调用
		switch (scrollState) {
		case OnScrollListener.SCROLL_STATE_IDLE:
			// 滚动结束调用
			System.out.println("1");
			break;
		case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
			// System.out.println("2");滚动时调用
			if (mCarTypeDrawer.isOpened())
				mCarTypeDrawer.animateClose();
			if (mCarYearstyleDrawer.isOpened())
				mCarYearstyleDrawer.animateClose();

			break;
		case OnScrollListener.SCROLL_STATE_FLING:
			System.out.println("3");
			break;
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount)
	{
		// ListView滚动过程中会一直调用
	}
}
