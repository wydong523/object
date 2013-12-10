package com.google.zxing.client.android.task;

import java.util.ArrayList;
import java.util.TreeSet;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.app.AppContext;
import com.google.zxing.client.android.connector.Connector;
import com.google.zxing.client.android.constant.DBConstants;
import com.google.zxing.client.android.constant.PreperenceConstants;
import com.google.zxing.client.android.dao.DBHelper;
import com.google.zxing.client.android.exceptions.AuthenticationException;
import com.google.zxing.client.android.exceptions.AuthorizationException;
import com.google.zxing.client.android.exceptions.CommunicationException;
import com.google.zxing.client.android.ui.LoginActivity;
import com.google.zxing.client.android.utils.StringUtils;
import com.google.zxing.client.android.vo.Account;

/**
 * 加载后台服务器账户
 * 
 * @author 汪渝栋
 * 
 */
public class AccountAsyncTask extends AsyncTask<Void, Void, String> {

	private static final String TAG = AccountAsyncTask.class.getSimpleName();

	private LoginActivity activity;
	private final Connector connector;
	private EditText username;
	private EditText password;
	private CheckBox remeberBox;
	private CheckBox onlineBox;
	private Button loginBtn;
	private Button managerBtn;
	private Button settingBtn;
	private View view;// 进度条
	private SharedPreferences accountPreferences;
	public static final String COMMA = ",";

	private String servicePath;

	public AccountAsyncTask(LoginActivity activity,
			SharedPreferences accountPreferences, Connector connector,
			EditText username, EditText password, CheckBox remeberBox,
			CheckBox onlineBox, Button loginBtn, Button managerBtn,
			Button settingBtn, String servicePath) {
		super();
		this.activity = activity;
		this.connector = connector;
		this.username = username;
		this.password = password;
		this.remeberBox = remeberBox;
		this.onlineBox = onlineBox;
		this.loginBtn = loginBtn;
		this.managerBtn = managerBtn;
		this.settingBtn = settingBtn;
		this.accountPreferences = accountPreferences;
		this.servicePath = servicePath;
	}

	@Override
	protected synchronized void onPreExecute() {
		super.onPreExecute();
		Log.d(TAG, "onPreExecute is here!!");
		username.setVisibility(TextView.INVISIBLE);
		password.setVisibility(TextView.INVISIBLE);
		loginBtn.setVisibility(TextView.INVISIBLE);
		managerBtn.setVisibility(TextView.INVISIBLE);
		settingBtn.setVisibility(TextView.INVISIBLE);
		remeberBox.setVisibility(TextView.INVISIBLE);
		onlineBox.setVisibility(TextView.INVISIBLE);
		visibleProgressBar("");
	}

	/**
	 * 显示进度条
	 * 
	 * @param dataLoading
	 */
	public synchronized void visibleProgressBar(String dataLoading) {
		view = activity.getWindow().getDecorView();
		view.findViewById(R.id.progress_bar).setVisibility(TextView.VISIBLE);
		view.findViewById(R.id.data_is_loading).setVisibility(TextView.VISIBLE);
	}

	/**
	 * 隐藏进度条
	 */
	public synchronized void hideProgressBar() {
		view.findViewById(R.id.progress_bar).setVisibility(TextView.INVISIBLE);
		view.findViewById(R.id.data_is_loading).setVisibility(
				TextView.INVISIBLE);
	}

	@Override
	protected synchronized String doInBackground(Void... params) {
		Log.d(TAG, "doInBackground is here!!");
		String result = null;
		try {
			System.out.println(servicePath);
			result = connector.getAccount(servicePath);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (AuthenticationException e) {
			e.printStackTrace();
		} catch (CommunicationException e) {
			e.printStackTrace();
		} catch (AuthorizationException e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressLint("NewApi")
	@SuppressWarnings("unused")
	@Override
	protected void onPostExecute(String result) {
		Log.d(TAG, "onPreExecute is here!!");
		ContentValues values = null;
		SQLiteDatabase db = null;
		TreeSet<String> accountNames = new TreeSet<String>();
		if (StringUtils.isEmpty(result)) {
			hideAndShowUI();
			Toast.makeText(activity, "网络故障，无法加载用户！", Toast.LENGTH_SHORT).show();
			return;
		} else {
			// 删除数据库中当前用户
			SQLiteOpenHelper helper = new DBHelper(activity);
			db = helper.getWritableDatabase();
			db.delete(DBConstants.Account.TABLE_NAME, null, null);
			String[] userdata = result.split(",");
			for (int i = 0; i < userdata.length; i++) {
				String[] accountdata = userdata[i].split("&");
				values = new ContentValues();
				accountNames.add(accountdata[0]);
				values.put(DBConstants.Account.NAME, accountdata[0]);
				values.put(DBConstants.Account.PASSWORD, accountdata[1]);
				values.put(DBConstants.Account.DEFSITE, accountdata[2]);
				db.insert(DBConstants.Account.TABLE_NAME, null, values);
			}
			DBHelper.close(null, db);
			// 缓存
			accountPreferences
					.edit()
					.putStringSet(PreperenceConstants.Account.ACCOUNT_NAMES,
							accountNames).commit();
			hideAndShowUI();
		}
	}

	private Account paserResult(ArrayList<Account> result) {
		Account account = new Account();
		if (result.contains("wangyudong")) {
			account.setUsername("maxadmin");
		}
		if (result.contains("wangyudong")) {
			account.setPassword("C5A223CAF1CE9A16C2E1D3138F4AC391");
		}
		return account;
	}

	@Override
	protected synchronized void onCancelled() {
		super.onCancelled();
		hideAndShowUI();
		Log.d(TAG, "onCancelled is here!!");
	}

	/**
	 * 显示登录界面
	 */
	private void hideAndShowUI() {
		hideProgressBar();
		username.setVisibility(TextView.VISIBLE);
		password.setVisibility(TextView.VISIBLE);
		loginBtn.setVisibility(TextView.VISIBLE);
		managerBtn.setVisibility(TextView.VISIBLE);
		settingBtn.setVisibility(TextView.VISIBLE);
		remeberBox.setVisibility(TextView.VISIBLE);
		onlineBox.setVisibility(TextView.VISIBLE);

	}

}
