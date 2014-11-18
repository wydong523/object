/**
 * Project Name:JZGPingGuShi
 * File Name:AppContext.java
 * Package Name:com.gc.jzgpinggushi.app
 * Date:2014-9-1上午10:18:25
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.app;

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

/**
 * ClassName:AppContext <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-1 上午10:18:25 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
@TargetApi(Build.VERSION_CODES.FROYO)
public class AppContext extends Application
{
	/**
	 * 入口标记
	 */
	private int mEntranceFlag = -1;

	/**
	 * 评估师Id
	 */
	private int pgsid;

	/**
	 * 查看车源中点击车源列表后记录的位置
	 */
	private int addPosition;

	/**
	 * 根据detailFlag的不同确定需要获取的实体对象
	 */
	private String detailFlag;
	
	/**
	 * 模块标记
	 */
	private String modelFlag;

	@Override
	public void onCreate()
	{
		super.onCreate();
		initImageLoader(getApplicationContext());
	}

	/**
	 * 初始化ImageLoader initImageLoader: 使用imageload库必须加<br/>
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

	public int getmEntranceFlag()
	{
		return mEntranceFlag;
	}

	public void setmEntranceFlag(int mEntranceFlag)
	{
		this.mEntranceFlag = mEntranceFlag;
	}

	public int getPgsid()
	{
		return pgsid;
	}

	public void setPgsid(int pgsid)
	{
		this.pgsid = pgsid;
	}

	public int getAddPosition()
	{
		return addPosition;
	}

	public void setAddPosition(int addPosition)
	{
		this.addPosition = addPosition;
	}

	public String getDetailFlag()
	{
		return detailFlag;
	}

	public void setDetailFlag(String detailFlag)
	{
		this.detailFlag = detailFlag;
	}

	public String getModelFlag()
	{
		return modelFlag;
	}

	public void setModelFlag(String modelFlag)
	{
		this.modelFlag = modelFlag;
	}
}
