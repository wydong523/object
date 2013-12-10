package com.google.zxing.client.android.ui;

import java.util.ArrayList;
import java.util.TreeSet;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.google.zxing.client.android.R;
import com.google.zxing.client.android.app.AppContext;
import com.google.zxing.client.android.app.AppManager;
import com.google.zxing.client.android.utils.DialogManager;
import com.google.zxing.client.android.vo.SelectDialogEntity;

import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * 应用程序Activity基类
 * 
 * @author 汪渝栋
 * 
 */
public class BaseActivity extends SherlockActivity {

	public static int THEME = R.style.Theme_Sherlock_Light_DarkActionBar;

	public static final DialogManager dialogManager = new DialogManager();

	private View progressView;

	public AppContext appContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		appContext = (AppContext) getApplicationContext();
		// 添加Activity到堆栈
		AppManager.getAppManager().addActivity(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		// 结束Activity&从堆栈中移除
		AppManager.getAppManager().finishActivity(this);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		return super.onMenuItemSelected(featureId, item);
	}

	/**
	 * 显示进度条
	 * 
	 * @param dataLoading
	 */
	public synchronized void visibleProgressBar(String dataLoading) {
		progressView = getWindow().getDecorView();
		progressView.findViewById(R.id.progress_bar)
				.setVisibility(View.VISIBLE);
		progressView.findViewById(R.id.data_is_loading).setVisibility(
				View.VISIBLE);
	}

	/**
	 * 隐藏进度条
	 */
	public synchronized void hideProgressBar() {
		progressView.findViewById(R.id.progress_bar).setVisibility(View.GONE);
		progressView.findViewById(R.id.data_is_loading)
				.setVisibility(View.GONE);
	}

	protected void showInfo(String string) {
		appContext.showInfo(string, this, Style.INFO);
	}

	protected void showError(String info) {
		appContext.showError(info, this);
	}

	protected void show(SelectDialogEntity dialogEntity, int dialogListBg,
			ArrayList<String> cum) {
		dialogManager.show(dialogEntity, dialogListBg, cum);
	}
}
