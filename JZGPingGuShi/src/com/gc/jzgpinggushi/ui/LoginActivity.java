/**
 * Project Name:JZGPingGuShi
 * File Name:LoginActivity.java
 * Package Name:com.gc.jzgpinggushi.ui
 * Date:2014-9-3下午12:14:13
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jzgpinggushi.R;
import com.gc.jzgpinggushi.app.ActivityHelp;
import com.gc.jzgpinggushi.app.AppContext;
import com.gc.jzgpinggushi.app.HttpService;
import com.gc.jzgpinggushi.uitls.MessageUtils;
import com.gc.jzgpinggushi.uitls.StringUtil;
import com.gc.jzgpinggushi.vo.User;

/**
 * ClassName:LoginActivity <br/>
 * Function: 登陆界面. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-3 下午12:14:13 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class LoginActivity extends BaseActivity implements OnClickListener,
		OnTouchListener
{
	private Activity mActivity = this;

	private EditText mUsername;

	private EditText mPassword;

	private Button mLoginBtn;

	private TextView mError;

	private Handler mHandler;

	private CharSequence username;

	private CharSequence password;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		mHandler = getHandler();
		init();
	}

	private Handler getHandler()
	{
		return new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				User user = (User) msg.obj;
				switch (msg.what) {
				case R.id.login:
					if (user.getStatus() == SUCCESS)
					{
						AppContext mAppContext = (AppContext) getApplicationContext();
						mAppContext.setPgsid(user.getPgsid());
						SharedPreferences userInfo = getSharedPreferences(
								"user_info", 0);
						userInfo.edit().putString("name", username.toString())
								.commit();
						ActivityHelp.startActivity(mActivity,
								HomeActivity.class);
					} else
					{
						showError("登录失败，用户名或密码错误！");
					}
					break;
				default:
					break;
				}
			}

		};
	}

	@Override
	public void init()
	{
		super.init();

		mUsername = (EditText) findViewById(R.id.username);
		mUsername.setOnTouchListener(this);
		SharedPreferences userInfo = getSharedPreferences("user_info", 0);
		if (userInfo.getString("name", "") != null
				|| userInfo.getString("name", "").length() > 0)
		{
			mUsername.setText(userInfo.getString("name", ""));
		}
		mPassword = (EditText) findViewById(R.id.password);
		mPassword.setOnTouchListener(this);
		mLoginBtn = (Button) findViewById(R.id.login_btn);
		mLoginBtn.setOnClickListener(this);
		mError = (TextView) findViewById(R.id.error);

	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId()) {
		case R.id.login_btn:
			loginOperation();
			break;
		default:
			break;
		}
	}

	private void loginOperation()
	{
		if (checkInput())
		{
			startLoginThread();
		}
	}

	/**
	 * 启动登录线程 startLoginThread: <br/>
	 * 
	 * @author wang
	 * @since JDK 1.6
	 */
	public void startLoginThread()
	{

		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					User user = HttpService.login(username.toString(),
							password.toString());
					System.out.println(user);
					MessageUtils.sendMessage(mHandler, R.id.login, user);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}).start();

	}

	/**
	 * 验证输入 checkInput: <br/>
	 * 
	 * @author wang
	 * @return
	 * @since JDK 1.6
	 */
	private boolean checkInput()
	{
		username = mUsername.getText();
		password = mPassword.getText();

		if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password))
		{
			mError.setVisibility(View.VISIBLE);
			mError.setText(getResources().getString(R.string.emptyError));
			return false;
		} else if (!appContext.isNetworkConnected())
		{
			mError.setVisibility(View.VISIBLE);
			mError.setText(getResources().getString(R.string.netError));
			return false;
		}
		return true;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		mError.setVisibility(View.INVISIBLE);
		return false;
	}
}
