package com.google.zxing.client.android.service;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;

import com.google.zxing.client.android.vo.SmsInfo;

public class SmsContent {
	private Activity activity;
	private Uri uri;
	List<SmsInfo> infos;

	public SmsContent(Activity activity, Uri uri) {
		infos = new ArrayList<SmsInfo>();
		this.activity = activity;
		this.uri = uri;
	}

	/**
	 * 获取短信信息列表
	 */
	public List<SmsInfo> getSmsInfo() {
		String[] projection = new String[] { "_id", "address", "person",
				"body", "date", "type" };
		Cursor cusor = activity.managedQuery(uri, projection, null, null,
				"date desc");
		int nameColumn = cusor.getColumnIndex("person");
		int phoneNumberColumn = cusor.getColumnIndex("address");
		int smsbodyColumn = cusor.getColumnIndex("body");
		int dateColumn = cusor.getColumnIndex("date");
		int typeColumn = cusor.getColumnIndex("type");
		if (cusor != null) {
			while (cusor.moveToNext()) {
				SmsInfo smsinfo = new SmsInfo();
				smsinfo.setName(cusor.getString(nameColumn));
				smsinfo.setDate(cusor.getString(dateColumn));
				smsinfo.setPhoneNumber(cusor.getString(phoneNumberColumn));
				smsinfo.setSmsbody(cusor.getString(smsbodyColumn));
				smsinfo.setType(cusor.getString(typeColumn));
				infos.add(smsinfo);
			}
			cusor.close();
		}
		return infos;
	}
}
