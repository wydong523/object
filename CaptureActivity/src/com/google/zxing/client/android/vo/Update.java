package com.google.zxing.client.android.vo;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URI;

/**
 * Ӧ�ó������ʵ����
 * 
 * @author ���嶰
 * 
 */
public class Update implements Serializable {

	public final static String UTF8 = "UTF-8";
	public final static String NODE_ROOT = "oschina";

	private int versionCode;
	private String versionName;
	private String downloadUrl;
	private String updateLog;

	public int getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getUpdateLog() {
		return updateLog;
	}

	public void setUpdateLog(String updateLog) {
		this.updateLog = updateLog;
	}

	public static Update parse(InputStream inputStream) throws IOException {
		Update update = null;
		update = new Update();
		update.setVersionCode(2);
		update.setVersionName("1.1");
		update.setDownloadUrl("http://192.168.1.93/ATTACHMENTS/CaptureActivity.apk");
		update.setUpdateLog("���ΰ汾�����޸����û����߲������ʲɹ����ֳ����Զ���������");
		// TODO ����webservice����
		return update;
	}

}
