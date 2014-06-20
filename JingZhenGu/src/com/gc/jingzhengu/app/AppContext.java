package com.gc.jingzhengu.app;

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
}
