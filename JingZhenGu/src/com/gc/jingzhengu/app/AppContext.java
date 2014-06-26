package com.gc.jingzhengu.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gc.jingzhengu.service.CarDamageSerivce;
import com.gc.jingzhengu.vo.CarDamage;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.View;
import android.view.View.OnClickListener;

@TargetApi(Build.VERSION_CODES.FROYO)
public class AppContext extends Application
{
	@Override
	public void onCreate()
	{
		super.onCreate();
		initImageLoader(getApplicationContext());
	}

	/**
	 * 初始化ImageLoader initImageLoader: <br/>
	 * 
	 * @author wang
	 * @param context
	 * @since JDK 1.6
	 */
	public static void initImageLoader(Context context)
	{
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}

	/**
	 * 详细评估所需数据
	 */
	private CarDamage mCarDamage = new CarDamage();

	/**
	 * 传送给服务器的标示集合
	 */
	private List<String> mResultData = new ArrayList<String>();

	/**
	 * 第4个车损页面集合1对应的元素 key:当前元素的id value:当前元素的是否被选中false/true
	 */
	private static Map<String, Boolean> mFourPageData1 = new HashMap<String, Boolean>();

	/**
	 * 第4个车损页面集合2对应的元素 key:当前元素的id value:当前元素的是否被选中false/true
	 */
	private static Map<String, Boolean> mFourPageData2 = new HashMap<String, Boolean>();

	/**
	 * 第4个车损页面集合3对应的元素 key:当前元素的id value:当前元素的是否被选中false/true
	 */
	private static Map<String, Boolean> mFourPageData3 = new HashMap<String, Boolean>();

	static
	{
		mFourPageData1.put(CarDamageSerivce.CHECKBOX_1, true);
		mFourPageData1.put(CarDamageSerivce.CHECKBOX_2, false);
		mFourPageData1.put(CarDamageSerivce.CHECKBOX_3, false);
		mFourPageData1.put(CarDamageSerivce.CHECKBOX_4, false);

		mFourPageData2.put(CarDamageSerivce.CHECKBOX_5, true);
		mFourPageData2.put(CarDamageSerivce.CHECKBOX_6, false);
		mFourPageData2.put(CarDamageSerivce.CHECKBOX_7, false);
		mFourPageData2.put(CarDamageSerivce.CHECKBOX_8, false);

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

	/**
	 * 显示错误文本信息
	 * 
	 * @param result
	 * @param activity
	 */
	public void showError(String result, Activity activity)
	{
		Style croutonStyle = Style.ALERT;

		final Crouton crouton;
		crouton = Crouton.makeText(activity, result, croutonStyle);
		crouton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				crouton.cancel();
			}
		}).setConfiguration(Configuration.DEFAULT).show();
	}

	/**
	 * 显示自定义文本信息
	 * 
	 * @param result
	 * @param activity
	 */
	public void showInfo(String result, Activity activity)
	{
		Style croutonStyle = Style.INFO;

		final Crouton crouton;
		crouton = Crouton.makeText(activity, result, croutonStyle);
		crouton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				crouton.cancel();
			}
		}).setConfiguration(Configuration.DEFAULT).show();
	}

	/**
	 * 检测网络是否可用
	 * 
	 * @return
	 */
	public boolean isNetworkConnected()
	{
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return ni != null && ni.isConnectedOrConnecting();
	}

	/**
	 * 改变checkbox选项列表1的原始结果集 modifyData1Checked: <br/>
	 * 
	 * @author wang
	 * @param one
	 * @param two
	 * @param three
	 * @param four
	 * @since JDK 1.6
	 */
	public void modifyData1Checked(boolean one, boolean two, boolean three,
			boolean four)
	{
		mFourPageData1.put(CarDamageSerivce.CHECKBOX_1, one);
		mFourPageData1.put(CarDamageSerivce.CHECKBOX_2, two);
		mFourPageData1.put(CarDamageSerivce.CHECKBOX_3, three);
		mFourPageData1.put(CarDamageSerivce.CHECKBOX_4, four);
	}

	/**
	 * 改变checkbox选项列表2的原始结果集 modifyData2Checked: <br/>
	 * 
	 * @author wang
	 * @param one
	 * @param two
	 * @param three
	 * @param four
	 * @since JDK 1.6
	 */
	public void modifyData2Checked(boolean one, boolean two, boolean three,
			boolean four)
	{
		mFourPageData2.put(CarDamageSerivce.CHECKBOX_5, one);
		mFourPageData2.put(CarDamageSerivce.CHECKBOX_6, two);
		mFourPageData2.put(CarDamageSerivce.CHECKBOX_7, three);
		mFourPageData2.put(CarDamageSerivce.CHECKBOX_8, four);
	}

	/**
	 * 
	 * modifyData3Checked: <br/>
	 * 改变checkbox选项列表3的原始结果集
	 * 
	 * @author wang
	 * @param value
	 * @since JDK 1.6
	 */
	public void modifyData3Checked(boolean... value)
	{
		mFourPageData3.put(CarDamageSerivce.CHECKBOX_9, value[0]);
		mFourPageData3.put(CarDamageSerivce.CHECKBOX_10, value[1]);
		mFourPageData3.put(CarDamageSerivce.CHECKBOX_11, value[2]);
		mFourPageData3.put(CarDamageSerivce.CHECKBOX_12, value[3]);
		mFourPageData3.put(CarDamageSerivce.CHECKBOX_13, value[4]);
		mFourPageData3.put(CarDamageSerivce.CHECKBOX_14, value[5]);
		mFourPageData3.put(CarDamageSerivce.CHECKBOX_15, value[6]);
		mFourPageData3.put(CarDamageSerivce.CHECKBOX_16, value[7]);
		mFourPageData3.put(CarDamageSerivce.CHECKBOX_17, value[8]);
		mFourPageData3.put(CarDamageSerivce.CHECKBOX_18, value[9]);
		mFourPageData3.put(CarDamageSerivce.CHECKBOX_19, value[10]);
		mFourPageData3.put(CarDamageSerivce.CHECKBOX_20, value[11]);
		mFourPageData3.put(CarDamageSerivce.CHECKBOX_21, value[12]);
	}

	public void modifyCheckBox(Map<String, Boolean> pagedata, String key,
			Boolean value)
	{
		pagedata.put(key, value);
	}

	public List<String> getmResultData()
	{
		return mResultData;
	}

	public void setmResultData(List<String> mResultData)
	{
		this.mResultData = mResultData;
	}

	public static Map<String, Boolean> getmFourPageData1()
	{
		return mFourPageData1;
	}

	public static void setmFourPageData1(Map<String, Boolean> mFourPageData1)
	{
		AppContext.mFourPageData1 = mFourPageData1;
	}

	public static Map<String, Boolean> getmFourPageData2()
	{
		return mFourPageData2;
	}

	public static void setmFourPageData2(Map<String, Boolean> mFourPageData2)
	{
		AppContext.mFourPageData2 = mFourPageData2;
	}

	public static Map<String, Boolean> getmFourPageData3()
	{
		return mFourPageData3;
	}

	public static void setmFourPageData3(Map<String, Boolean> mFourPageData3)
	{
		AppContext.mFourPageData3 = mFourPageData3;
	}

	public CarDamage getmCarDamage()
	{
		return mCarDamage;
	}

	public void setmCarDamage(CarDamage mCarDamage)
	{
		this.mCarDamage = mCarDamage;
	}
}
