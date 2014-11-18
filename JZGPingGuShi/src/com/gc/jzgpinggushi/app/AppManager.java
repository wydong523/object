/**
 * Project Name:JZGPingGuShi
 * File Name:AppManager.java
 * Package Name:com.gc.jzgpinggushi.app
 * Date:2014-9-1上午10:18:48
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.app;

import java.util.Stack;

import com.gc.jzgpinggushi.ui.HomeActivity;
import com.gc.jzgpinggushi.ui.IndexCarActivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

/**
 * ClassName:AppManager <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-1 上午10:18:48 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class AppManager
{
	private static Stack<Activity> activityStack;

	private static AppManager instance;

	private AppManager()
	{
	}

	public static AppManager getAppManager()
	{
		if (instance == null)
		{
			instance = new AppManager();
		}
		return instance;
	}

	public void addActivity(Activity activity)
	{
		if (activityStack == null)
		{
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	public Activity currentActivity()
	{
		Activity activity = activityStack.lastElement();
		return activity;
	}

	public void finishActivity()
	{
		Activity activity = activityStack.lastElement();
		finishActivity(activity);
	}

	public void finishActivity(Activity activity)
	{
		if (activity != null)
		{
			activityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}

	public void finishActivity(Class<?> cls)
	{
		for (Activity activity : activityStack)
		{
			if (activity.getClass().equals(cls))
			{
				finishActivity(activity);
			}
		}
	}

	public void finishAllActivity()
	{
		for (int i = 0, size = activityStack.size(); i < size; i++)
		{
			if (null != activityStack.get(i))
			{
				activityStack.get(i).finish();
			}
		}
		activityStack.clear();
	}

	/**
	 * 关闭除了主页和汽车索引界面之外的所有界面 finishOtherActivity: <br/>
	 * 
	 * @author wang
	 * @since JDK 1.6
	 */
	public void finishOtherActivity()
	{
		for (int i = 0, size = activityStack.size(); i < size; i++)
		{
			if (null != activityStack.get(i)
					&& IndexCarActivity.class != activityStack.get(i)
							.getClass()
					&& HomeActivity.class != activityStack.get(i).getClass())
			{
				activityStack.get(i).finish();
			}
		}
		// activityStack.clear();
	}

	public void AppExit(Context context)
	{
		try
		{
			finishAllActivity();
			ActivityManager activityMgr = (ActivityManager) context
					.getSystemService(Context.ACTIVITY_SERVICE);
			activityMgr.restartPackage(context.getPackageName());
			System.exit(0);
		} catch (Exception e)
		{
		}
	}
}
