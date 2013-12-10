package com.google.zxing.client.android.ui;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.google.zxing.client.android.R;
import com.google.zxing.client.android.adapter.OperateGridAdapter;
import com.google.zxing.client.android.app.AppContext;
import com.google.zxing.client.android.constant.StringContants;
import com.google.zxing.client.android.exceptions.AuthenticationException;
import com.google.zxing.client.android.exceptions.AuthorizationException;
import com.google.zxing.client.android.exceptions.CommunicationException;
import com.google.zxing.client.android.task.InboxAsyncTask;
import com.google.zxing.client.android.view.dialog.SureOrCancleDialog;
import com.google.zxing.client.android.view.pulllist.XListView;
import com.google.zxing.client.android.vo.AppPermissions;
import com.google.zxing.client.android.vo.AppPermissionsList;

/**
 * 启动中心
 * 
 * @author 汪渝栋
 * 
 */
@SuppressLint("NewApi")
public class HomeActivity extends BaseActivity {
	private static final String TAG = HomeActivity.class.getSimpleName();

	private XListView inboxList = null;

	private GridView operateGrid = null;

	private InboxAsyncTask inboxAsyncTask = null;

	private AppContext appContext = null;

	public static int THEME = R.style.Theme_Sherlock_Light_DarkActionBar;

	private String username;

	private AppPermissionsList appList;

	private HashMap<String, Integer> allAppImgs;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		menu.add(0, R.id.login_out, 0, "loginOut")
				.setIcon(R.drawable.login_out)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

		ActionBar actionBar = getSupportActionBar();
		Drawable bg = (Drawable) getResources().getDrawable(R.drawable.titlebg);
		actionBar.setBackgroundDrawable(bg);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);

		View view = this.getLayoutInflater()
				.inflate(R.layout.custom_view, null);
		TextView title = (TextView) view.findViewById(R.id.title);
		title.setText("控制中心");
		TextView subTitle = (TextView) view.findViewById(R.id.sub_title);
		subTitle.setText("用户名:" + username);

		actionBar.setCustomView(view);
		actionBar.setDisplayShowCustomEnabled(true);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, android.view.MenuItem item) {
		switch (item.getItemId()) {
		case R.id.login_out:
			loginOut();
			break;

		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	/**
	 * 退出用户
	 */
	private void loginOut() {
		SureOrCancleDialog sureOrCancleDialog = new SureOrCancleDialog(
				appContext, this, R.style.exit_dialog, "用户退出",
				"您确定退出Maximo系统吗?");
		sureOrCancleDialog.show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(THEME);
		setContentView(R.layout.home_layout);
		appContext = (AppContext) getApplication();
		init();
		username = appContext.getLoginUid();
		// 查询用户对应的app权限
		queryApp();
		startInboxTask(username, inboxList, this);
	}

	private void init() {
		allAppImgs = new HashMap<String, Integer>();
		allAppImgs.put("采购申请", R.drawable.cgsq);
		allAppImgs.put("工单跟踪", R.drawable.gdgz);
		allAppImgs.put("资产", R.drawable.zc);
		allAppImgs.put("员工报告", R.drawable.ygbg);
		allAppImgs.put("员工", R.drawable.yg);
	}

	private void queryApp() {
		final Handler appHandler = getAppNameHandler();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String key = "app_name";
					// 最终组装的应用名称
					ArrayList<String> appNames = new ArrayList<String>();

					// TODO 目前开放模块，等所有模块开发完毕以后改为可配置选项
					ArrayList<String> proName = new ArrayList<String>();
					proName.add("采购申请");
					proName.add("工单跟踪");
					proName.add("资产");
					proName.add("员工报告");
					proName.add("员工");

					if (appContext.doWeHaveInternet(getApplicationContext())) {
						appList = appContext.queryApp(username);

						// 查询用户对应的app操作权限
						appContext.queryAppOperateAuth();

						// TODO null判断，稍后添加
						// 对应用户所有的应用名称
						TreeSet<String> totalName = appContext.getApps();
						ArrayList<AppPermissions> apps = appList
								.getAppPermissions();
						for (AppPermissions app : apps) {
							totalName.add(app.getDescription());
						}

						for (String name : proName) {
							if (totalName.contains(name)) {
								appNames.add(name);
							}
						}
						appContext.saveObject(appNames, key);
					} else {
						appNames = (ArrayList<String>) appContext
								.readObject(key);
					}

					Message message = new Message();
					message.obj = appNames;
					appHandler.sendMessage(message);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (CommunicationException e) {
					e.printStackTrace();
				} catch (AuthorizationException e) {
					e.printStackTrace();
				} catch (AuthenticationException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	private Handler getAppNameHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				ArrayList<String> appNames = (ArrayList<String>) msg.obj;
				adpterAppName(appNames);
			}
		};
	}

	private void adpterAppName(ArrayList<String> appNames) {
		// 获取应用对应图片数据
		ArrayList<Integer> appImgs = new ArrayList<Integer>();
		for (String appName : appNames) {
			Integer imgs = allAppImgs.get(appName);
			appImgs.add(imgs);
		}

		// 组装应用
		operateGrid = (GridView) findViewById(R.id.operate_grid);
		operateGrid.setSelector(new ColorDrawable(Color.TRANSPARENT));
		operateGrid.setEnabled(false);
		operateGrid.setAdapter(new OperateGridAdapter(this, appNames, appImgs));

	}

	/**
	 * 启动收件箱异步线程
	 * 
	 * @param username
	 *            用户名
	 * @param homeActivity
	 * @return
	 */
	private void startInboxTask(String username, XListView listView,
			HomeActivity homeActivity) {
		inboxAsyncTask = new InboxAsyncTask(username, listView, homeActivity,
				appContext);
		inboxAsyncTask.execute();
	}
}
