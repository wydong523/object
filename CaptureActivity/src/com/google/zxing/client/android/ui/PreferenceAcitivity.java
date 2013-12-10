package com.google.zxing.client.android.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import com.actionbarsherlock.R.style;
import com.actionbarsherlock.app.SherlockPreferenceActivity;
import com.google.zxing.client.android.R;

/**
 * 应用程序配置类
 * 
 * @author 汪渝栋
 * 
 */
public class PreferenceAcitivity extends SherlockPreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(style.Sherlock___Theme_Light);
		// 取消标题
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 全屏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
		}
		return false;
	}
}
