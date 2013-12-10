package com.google.zxing.client.android.service;

import java.util.Timer;
import java.util.TimerTask;

import com.google.zxing.client.android.app.AppContext;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * 定时任务:处理需要定期处理的数据
 * 
 * @author 汪渝栋
 * 
 */
public class TimerService extends Service {

	private AppContext appContext;

	private Timer timer;

	public static int i = 1;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (intent == null)
			return START_STICKY;
		System.out.println("startTimer is start!!!");
		appContext = (AppContext) getApplication();
		startTimer();
		return START_STICKY;

	}

	/**
	 * 定时任务清除缓存
	 */
	private void startTimer() {

		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				System.out.println("startTimer is run!!!");
				System.out.println("run is " + i++);
				String key = "workorder_add" + appContext.getLoginUid();
				appContext.delCache(key);
			}
		}, 0, 1000 * 60);
	}

	@Override
	public void onDestroy() {
		System.out.println("smsservice is ondestory!");
		Intent service = new Intent(this, TimerService.class);
		startService(service);
	}

}
