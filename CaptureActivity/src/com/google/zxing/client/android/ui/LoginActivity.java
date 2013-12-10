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
 * ��¼��
 * 
 * @author ���嶰
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

	// ����û���
	private SharedPreferences accountPreferences;

	// ��ס�û������ݴ洢
	private SharedPreferences remeberPreferences;

	private LoginDao loginDao;

	private SecurityManager securityManager;

	private static final EditTextManager editManager = new EditTextManager();

	private static final DialogManager dialogManager = new DialogManager();

	private Handler controlHandler = null;// ��¼

	private Handler jcdjHandler = null;// �������

	private Connector connector = new Connector();

	private AppContext appContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		appContext = (AppContext) getApplication();
		// ����°汾
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
					showError("������ʶ����");
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
					showError("�豸У�鲻ͨ������ʹ���ƶ��豸�͵绰����!");
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
	 * ��ʼ����Ҫ�����ĺ�̨����
	 */
	private void initService() {
		appContext.setSMSActivity(this);
		Intent smsService = new Intent(this, SmsService.class);
		startService(smsService);

		Intent timerService = new Intent(this, TimerService.class);
		startService(timerService);
	}

	/**
	 * ��ʼ����ȫ��Ϣ
	 */
	private void initSecurityParmas() {
		phoneNum = securityManager.getCurPhoneNum(this);
		deviceId = securityManager.getCurDeviceId(this);
		appContext.setJcdjHanlder(jcdjHandler);
		// TODO ��֤�û��绰������豸id�Ƿ�һ��
		System.out.println("phoneNum is " + phoneNum);
		System.out.println("deviceId is " + deviceId);
		// securityManager.checkPhoneNum(phoneNum);
		// ����ֻ�û�е绰����&&û��������������Ҫ�ļ�����Ҫ����
		if (phoneNum == null && !appContext.isNetworkConnected()) {
			// TODO ������Ҫ�ļ�����
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
				// ����ѡ���û�����
				popDialog();
			}
		});

		loginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String ip = appContext.getServerIp();
				if (ip == null || "".equals(ip)) {
					showError("�����÷�����IP��ַ��");
					return;
				}

				if (phoneNum == null) {
					showError("�����Ƿ����绰����ȷ�Ϻ����Ƿ�������");
					return;
				}
				// �����û�
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
				// ��ȡ�û�
				queryAccount(accountPreferences);
				// ��֤�豸Ψһ��
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
	 * ��ѯ�û�������Ӧ�ó����Ȩ��
	 */
	protected void getUserAllAppOperateAuth() {
		new Thread(new Runnable() {

			@Override
			public void run() {

			}
		}).start();
	}

	/**
	 * ��֤�ֻ��豸Ψһ��
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

		// ��ȡ�û���Ӧ�ó���Ĳ���Ȩ��
		getUserAllAppOperateAuth();

		if (pass == null || "".equals(pass.trim())) {
			showError(getString(R.string.login_toast_invalidPassword));
		} else if (!appContext.isNetworkConnected()) {
			if (!onlineLogin.isChecked()) {
				// ���ߵ�¼����
				unlineCheckpass(name, pass);
			} else {
				showError("û������,��ѡ�����ߵ�¼��");
			}
		} else {
			// ���ߵ�¼����
			startLoginThread().start();
		}
	}

	/**
	 * �������ߵ�¼�߳�
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
						msg.obj = "���������������ȷ����!";
						controlHandler.sendMessage(msg);
					}

				} catch (CommunicationException e) {
					msg.obj = "������ϣ��޷����ӷ����������Ժ�����!";
					controlHandler.sendMessage(msg);
					e.printStackTrace();
				} catch (AuthorizationException e) {
					msg.obj = "������ϣ��޷����ӷ����������Ժ�����!";
					controlHandler.sendMessage(msg);
					e.printStackTrace();
				} catch (AuthenticationException e) {
					msg.obj = "������ϣ��޷����ӷ����������Ժ�����!";
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
	 * Ӧ�����ý���
	 */
	protected void appSetting() {
		Intent intent = new Intent(this, PreferenceAcitivity.class);
		startActivity(intent);
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		// У��CheckBox
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
		// У��CheckBox
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
	 * ���߲���У������
	 * 
	 * @param name
	 *            �û���
	 * @param pass
	 *            �û�����
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
			msg.obj = "���������������ȷ����!";
			controlHandler.sendMessage(msg);
		}
	}

	/**
	 * �����û�ѡ�񴰿�
	 */
	@SuppressLint("NewApi")
	protected void popDialog() {
		Set<String> accounts = accountPreferences.getStringSet(
				PreperenceConstants.Account.ACCOUNT_NAMES, null);
		SelectDialogEntity dialogEntity = new SelectDialogEntity();
		dialogEntity.setContext(this);
		dialogEntity.setContents(accounts);
		dialogEntity.setAppContext(appContext);
		dialogEntity.setTitle("�û���");
		dialogEntity.setEdit(username);
		dialogEntity.setGetFocusView(password);
		ArrayList<String> cum = new ArrayList<String>();
		show(dialogEntity, R.drawable.dialog_list_bg, cum);
	}

	/**
	 * ���߻�ȡ�˻�����
	 * 
	 * @param accountPreferences
	 */
	private void queryAccount(SharedPreferences accountPreferences) {
		// �鿴���磬��ȡ����
		if (!appContext.doWeHaveInternet(this)) {
			showError(getString(R.string.login_toast_no_internet));
		} else {

			String address = appContext.getAddress();
			if (address == null || address.length() == 0) {
				showError("������ip��ַ!!");
				return;
			}
			readAccountThread = new AccountAsyncTask(this, accountPreferences,
					connector, username, password, rememberMe, onlineLogin,
					loginBtn, managerBtn, settingBtn, address);
			readAccountThread.execute();
		}
	}

	/**
	 * ��ת����ҳ
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            ����
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
