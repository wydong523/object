package com.google.zxing.client.android.ui;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Set;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.zxing.client.android.Intents;
import com.google.zxing.client.android.R;
import com.google.zxing.client.android.app.AppConfig;
import com.google.zxing.client.android.app.AppContext;
import com.google.zxing.client.android.app.SecurityManager;
import com.google.zxing.client.android.app.UpdateManager;
import com.google.zxing.client.android.connector.Connector;
import com.google.zxing.client.android.constant.PreperenceConstants;
import com.google.zxing.client.android.constant.StringContants;
import com.google.zxing.client.android.dao.LoginDao;
import com.google.zxing.client.android.exceptions.AuthenticationException;
import com.google.zxing.client.android.exceptions.AuthorizationException;
import com.google.zxing.client.android.exceptions.CommunicationException;
import com.google.zxing.client.android.service.SmsService;
import com.google.zxing.client.android.service.TimerService;
import com.google.zxing.client.android.task.AccountAsyncTask;
import com.google.zxing.client.android.utils.DialogManager;
import com.google.zxing.client.android.utils.EditTextManager;
import com.google.zxing.client.android.utils.FileUtils;
import com.google.zxing.client.android.utils.PasswordCheck;
import com.google.zxing.client.android.view.dialog.SelectPopDialog;
import com.google.zxing.client.android.vo.SelectDialogEntity;

/**
 * 登录类
 * 
 * @author 汪渝栋
 * 
 */
public class LoginActivity extends BaseActivity {

	private static final String TAG = LoginActivity.class.getSimpleName();

	private String phoneNum;

	private String deviceId;

	private EditText username;

	private EditText password;

	private Button loginBtn;

	private Button managerBtn;

	private Button settingBtn;

	private CheckBox rememberMe;

	private CheckBox onlineLogin;

	private AccountAsyncTask readAccountThread;

	// 存放用户名
	private SharedPreferences accountPreferences;

	// 记住用户名数据存储
	private SharedPreferences remeberPreferences;

	private LoginDao loginDao;

	private SecurityManager securityManager;

	private static final EditTextManager editManager = new EditTextManager();

	private static final DialogManager dialogManager = new DialogManager();

	private Handler controlHandler = null;// 登录

	private Handler jcdjHandler = null;// 解除冻结

	private Connector connector = new Connector();

	private AppContext appContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		appContext = (AppContext) getApplication();
		// 检查新版本
		if (appContext.isCheckUp()) {
			UpdateManager.getUpdateManager().checkAppUpdate(this, false);
		}
		securityManager = SecurityManager.getSecurityManager();
		controlHandler = getControlHandler();
		jcdjHandler = getJcdjHandler();
		accountPreferences = getPreferences(MODE_PRIVATE);
		remeberPreferences = getPreferences(MODE_PRIVATE);

		init();
		FileUtils.createDirectory("/hxqh");
		System.out
				.println("file is exsit" + FileUtils.checkFileExists("/hxqh"));
		System.out.println(FileUtils.checkSaveLocationExists());
	}

	private Handler getJcdjHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				int key = msg.what;
				switch (key) {
				case R.id.device_jcdj:
					loginBtn.setEnabled(true);
					managerBtn.setEnabled(true);
					settingBtn.setEnabled(true);
					break;
				default:
					showError("操作标识出错！");
					break;
				}
			}
		};
	}

	private Handler getControlHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				String error = (String) msg.obj;
				switch (msg.what) {
				case R.id.login_error:
					showError(error);
					break;
				case R.id.check_device_error:
					showError("设备校验不通过，请使用制定设备和电话号码!");
					break;
				default:
					break;
				}
			}
		};
	}

	private void init() {
		initConfig();
		inniWeigit();
		initEvent();
		initSecurityParmas();
		initService();
	}

	/**
	 * 初始化需要开启的后台服务
	 */
	private void initService() {
		appContext.setSMSActivity(this);
		Intent smsService = new Intent(this, SmsService.class);
		startService(smsService);

		Intent timerService = new Intent(this, TimerService.class);
		startService(timerService);
	}

	/**
	 * 初始化安全信息
	 */
	private void initSecurityParmas() {
		phoneNum = securityManager.getCurPhoneNum(this);
		deviceId = securityManager.getCurDeviceId(this);
		appContext.setJcdjHanlder(jcdjHandler);
		// TODO 验证用户电话号码和设备id是否一致
		System.out.println("phoneNum is " + phoneNum);
		System.out.println("deviceId is " + deviceId);
		// securityManager.checkPhoneNum(phoneNum);
		// 如果手机没有电话号码&&没有网络则销毁重要文件及重要数据
		if (phoneNum == null && !appContext.isNetworkConnected()) {
			// TODO 处理重要文件操作
			if (FileUtils.checkFileExists("hxqh")) {
				FileUtils.deleteDirectory("hxqh");
				appContext.setDJ(true);
			}
		} else if (appContext.isDJ()) {
			// System.out.println("dj is true!");
			loginBtn.setEnabled(false);
			managerBtn.setEnabled(false);
			settingBtn.setEnabled(false);
		}
	}

	private void initEvent() {
		username.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 弹出选择用户窗口
				popDialog();
			}
		});

		loginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String ip = appContext.getServerIp();
				if (ip == null || "".equals(ip)) {
					showError("请设置服务器IP地址！");
					return;
				}

				if (phoneNum == null) {
					showError("请检查是否插入电话卡，确认号码是否正常！");
					return;
				}
				// 保存用户
				remeberPreferences
						.edit()
						.putString(PreperenceConstants.Account.REMEBER_ME,
								username.toString()).commit();
				login();
			}

		});

		managerBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 获取用户
				queryAccount(accountPreferences);
				// 验证设备唯一性
				checkDevice(phoneNum, deviceId);

			}
		});

		settingBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				appSetting();
			}
		});
	}

	/**
	 * 查询用户对所有应用程序的权限
	 */
	protected void getUserAllAppOperateAuth() {
		new Thread(new Runnable() {

			@Override
			public void run() {

			}
		}).start();
	}

	/**
	 * 验证手机设备唯一性
	 * 
	 * @param phoneNum
	 * @param deviceId
	 */
	protected void checkDevice(final String phoneNum, final String deviceId) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				String result;
				try {
					result = appContext.checkDevice(phoneNum, deviceId);
					if ("FALSE".equals(result) || "ERROR".equals(result)) {
						Message message = new Message();
						message.what = R.id.check_device_error;
						controlHandler.sendMessage(message);
					}
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

	protected void showError(String string) {
		appContext.showError(string, this);

	}

	protected void login() {
		String name = getUsername();
		String pass = getPassword();
		appContext.setLoginUid(name);

		// 获取用户对应用程序的操作权限
		getUserAllAppOperateAuth();

		if (pass == null || "".equals(pass.trim())) {
			showError(getString(R.string.login_toast_invalidPassword));
		} else if (!appContext.isNetworkConnected()) {
			if (!onlineLogin.isChecked()) {
				// 离线登录操作
				unlineCheckpass(name, pass);
			} else {
				showError("没有网络,请选择离线登录！");
			}
		} else {
			// 在线登录操作
			startLoginThread().start();
		}
	}

	/**
	 * 启动在线登录线程
	 * 
	 * @return
	 */
	private Thread startLoginThread() {
		return new Thread() {
			@Override
			public void run() {
				String username = getUsername();
				String password = getPassword();
				String result;
				Message msg = new Message();
				msg.what = R.id.login_error;
				try {
					result = connector.checkPassword(username, password,
							appContext.getAddress());
					boolean passflag = PasswordCheck
							.checkPass(password, result);
					if (passflag) {
						startOperate(username, password);
					} else {
						msg.obj = "密码错误，请输入正确密码!";
						controlHandler.sendMessage(msg);
					}

				} catch (CommunicationException e) {
					msg.obj = "网络故障，无法链接服务器，请稍后再试!";
					controlHandler.sendMessage(msg);
					e.printStackTrace();
				} catch (AuthorizationException e) {
					msg.obj = "网络故障，无法链接服务器，请稍后再试!";
					controlHandler.sendMessage(msg);
					e.printStackTrace();
				} catch (AuthenticationException e) {
					msg.obj = "网络故障，无法链接服务器，请稍后再试!";
					controlHandler.sendMessage(msg);
					e.printStackTrace();
				}
			}
		};
	}

	private void inniWeigit() {
		username = (EditText) findViewById(R.id.login_username_EditText);
		editManager.forbidInput(username);
		editManager.removeFocusable(username);

		password = (EditText) findViewById(R.id.login_password_EditText);
		loginBtn = (Button) findViewById(R.id.login_login_Button);
		managerBtn = (Button) findViewById(R.id.login_manager_Button);
		settingBtn = (Button) findViewById(R.id.login_set_Button);
		rememberMe = (CheckBox) findViewById(R.id.login_remember_checkbox);
		onlineLogin = (CheckBox) findViewById(R.id.login_online_checkbox);
	}

	private void initConfig() {
		String value = AppConfig.getSharedPreferences(this).getString(
				"ip_preference", null);
		if (value != null)
			appContext.setServerIp(value);

	}

	/**
	 * 应用设置界面
	 */
	protected void appSetting() {
		Intent intent = new Intent(this, PreferenceAcitivity.class);
		startActivity(intent);
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		// 校验CheckBox
		if (rememberMe.isChecked()) {
			String name = remeberPreferences.getString(
					PreperenceConstants.Account.REMEBER_ME, null);
			username.setText(name != null ? name : "");
			password.setText("");
		} else {
			username.setText("");
			password.setText("");
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 校验CheckBox
		if (rememberMe.isChecked()) {
			String name = username.getText().toString();
			remeberPreferences
					.edit()
					.putString(PreperenceConstants.Account.REMEBER_ME,
							name != null ? name : "").commit();
		}
		finish();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	/**
	 * 离线操作校验密码
	 * 
	 * @param name
	 *            用户名
	 * @param pass
	 *            用户密码
	 */
	private void unlineCheckpass(String name, String pass) {
		loginDao = new LoginDao(this);
		String nativePass = loginDao.getNativePass(name);
		boolean passflag = PasswordCheck.checkPass(pass, nativePass);
		if (passflag) {
			startOperate(name, pass);
		} else {
			Message msg = new Message();
			msg.what = R.id.login_error;
			msg.obj = "密码错误，请输入正确密码!";
			controlHandler.sendMessage(msg);
		}
	}

	/**
	 * 弹出用户选择窗口
	 */
	@SuppressLint("NewApi")
	protected void popDialog() {
		Set<String> accounts = accountPreferences.getStringSet(
				PreperenceConstants.Account.ACCOUNT_NAMES, null);
		SelectDialogEntity dialogEntity = new SelectDialogEntity();
		dialogEntity.setContext(this);
		dialogEntity.setContents(accounts);
		dialogEntity.setAppContext(appContext);
		dialogEntity.setTitle("用户名");
		dialogEntity.setEdit(username);
		dialogEntity.setGetFocusView(password);
		ArrayList<String> cum = new ArrayList<String>();
		show(dialogEntity, R.drawable.dialog_list_bg, cum);
	}

	/**
	 * 在线获取账户数据
	 * 
	 * @param accountPreferences
	 */
	private void queryAccount(SharedPreferences accountPreferences) {
		// 查看网络，获取数据
		if (!appContext.doWeHaveInternet(this)) {
			showError(getString(R.string.login_toast_no_internet));
		} else {

			String address = appContext.getAddress();
			if (address == null || address.length() == 0) {
				showError("请设置ip地址!!");
				return;
			}
			readAccountThread = new AccountAsyncTask(this, accountPreferences,
					connector, username, password, rememberMe, onlineLogin,
					loginBtn, managerBtn, settingBtn, address);
			readAccountThread.execute();
		}
	}

	/**
	 * 跳转到首页
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 */
	private void startOperate(String username, String password) {
		Intent intent = new Intent();
		intent.setAction(Intents.Home.ACTION);
		intent.putExtra(StringContants.USERNAME, username);
		startActivity(intent);
	}

	private String getUsername() {
		Editable str = username.getText();
		if (str == null || str.length() == 0) {
			return null;
		}
		return str.toString().trim();
	}

	private String getPassword() {
		String cs = password.getText().toString();
		if (cs == null || cs.length() == 0) {
			return null;
		}
		return cs;
	}

}
