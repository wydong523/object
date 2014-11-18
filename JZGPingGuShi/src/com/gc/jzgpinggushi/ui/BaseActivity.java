/**
 * Project Name:JZGPingGuShi
 * File Name:BaseActivity.java
 * Package Name:com.gc.jzgpinggushi.ui
 * Date:2014-9-3下午12:09:28
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.ui;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.example.jzgpinggushi.R;
import com.gc.jzgpinggushi.app.AppContext;
import com.gc.jzgpinggushi.app.AppManager;
import com.gc.jzgpinggushi.uitls.DialogManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * ClassName:BaseActivity <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-3 下午12:09:28 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class BaseActivity extends Activity
{
	public AppContext appContext;

	protected SharedPreferences configPres;

	public static final int SUCCESS = 100;

	protected ImageLoader imageLoader = ImageLoader.getInstance();

	protected ImageLoadingListener mAnimateFirstListener = new AnimateFirstDisplayListener();

	protected DisplayImageOptions mOptions;

	/**
	 * 弹出窗口管理器
	 */
	protected DialogManager mDialogManager;

	/**
	 * 加载中提示框
	 */
	protected Dialog mDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		appContext = (AppContext) getApplicationContext();

		configPres = getPreferences(MODE_PRIVATE);

		mOptions = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.no_car)
				.showImageForEmptyUri(R.drawable.no_car)
				.showImageOnFail(R.drawable.no_car).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(0)).build();

		// 添加Activity到堆栈
		AppManager.getAppManager().addActivity(this);
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		// 结束Activity&从堆栈中移除
		AppManager.getAppManager().finishActivity(this);
	}

	/**
	 * 初始化界面元素 init: <br/>
	 * 
	 * @author wang
	 * @since JDK 1.6
	 */
	public void init()
	{
		mDialogManager = new DialogManager();
	}

	/**
	 * 验证界面的文本元素是否为空 checkText: <br/>
	 * 
	 * @author wang
	 * @return
	 * @since JDK 1.6
	 */
	public boolean checkText()
	{
		return true;
	}

	protected void showInfo(String string)
	{
		appContext.showInfo(string, this);
	}

	protected void showError(String info)
	{
		appContext.showError(info, this);
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

}
