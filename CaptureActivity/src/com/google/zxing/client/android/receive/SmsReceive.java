package com.google.zxing.client.android.receive;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.app.AppContext;
import com.google.zxing.client.android.service.SmsContent;
import com.google.zxing.client.android.utils.FileUtils;
import com.google.zxing.client.android.vo.SmsInfo;

public class SmsReceive extends ContentObserver {

	/**
	 * 所有的短信
	 */
	public static final String SMS_URI_ALL = "content://sms/";

	/**
	 * 收件箱短信
	 */
	public static final String SMS_URI_INBOX = "content://sms/inbox";

	/**
	 * 发件箱短信
	 */
	public static final String SMS_URI_SEND = "content://sms/sent";

	/**
	 * 草稿箱短信
	 */
	public static final String SMS_URI_DRAFT = "content://sms/draft";

	private Activity activity;
	private List<SmsInfo> infos;
	private AppContext appContext;
	private Handler jcdjHanlder;

	public SmsReceive(Handler handler) {
		super(handler);
	}

	public SmsReceive(Handler handler, Activity activity, AppContext appContext) {
		super(handler);
		jcdjHanlder = handler;
		this.activity = activity;
		this.appContext = appContext;
	}

	@SuppressLint("NewApi")
	@Override
	public void onChange(boolean selfChange) {
		Uri uri = Uri.parse(SMS_URI_INBOX);
		SmsContent smscontent = new SmsContent(activity, uri);
		infos = smscontent.getSmsInfo();
		String smsinfo = infos.get(0).getSmsbody();
		System.out.println(smsinfo);
		// 如果短信内容包含dj则删除系统中保存文件的目录
		if ("dj".equalsIgnoreCase(smsinfo)) {
			appContext.setDJ(true);
			if (FileUtils.checkFileExists("/hxqh")) {
				FileUtils.deleteDirectory("/hxqh");
			}
		} else if ("jcdj".equalsIgnoreCase(smsinfo)) {
			if (appContext.isDJ()) {
				appContext.setDJ(false);
				jcdjHanlder.sendEmptyMessage(R.id.device_jcdj);
			}
		}
		super.onChange(selfChange);
	}
}
