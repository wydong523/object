package com.google.zxing.client.android.service;

import android.app.Service;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;

import com.google.zxing.client.android.app.AppContext;
import com.google.zxing.client.android.receive.SmsReceive;

/**
 * SMS后台操作服务
 * 
 * @author 汪渝栋
 * 
 */
public class SmsService extends Service {

	private boolean isRunning = false;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (intent == null)
			return START_STICKY;
		if (!isRunning) {
			isRunning = true;
			AppContext context = (AppContext) getApplication();
			ContentObserver co = new SmsReceive(new Handler(),
					context.getSMSActivity(), context);
			this.getContentResolver().registerContentObserver(
					Uri.parse("content://sms/"), true, co);
			System.out.println("SMSService is running！！！");
		}
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		System.out.println("smsservice is ondestory!");
		Intent service = new Intent(this, SmsService.class);
		startService(service);
	}

}
