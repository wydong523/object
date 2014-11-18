/**
 * Project Name:InvoiceCADA
 * File Name:LoginActivity.java
 * Package Name:com.example.invoicecada
 * Date:2014-4-15上午11:32:01
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.jzg.invoicecada.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.jzg.invoicecada.R;
import com.jzg.invoicecada.app.ActivityHelp;
import com.jzg.invoicecada.app.AppContext;
import com.jzg.invoicecada.app.HttpService;
import com.jzg.invoicecada.utils.MD5Utils;
import com.jzg.invoicecada.utils.MessageUtils;

/**
 * ClassName:LoginActivity <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-15 上午11:32:01 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class LoginActivity extends BaseActivity
{

	private static final String privateNum = "9F731209-CE2D-4C54-9AC8-0001A03A6DD6";

	private Button loginBtn;

	private EditText username;

	private EditText password;

	private Handler loginHandler;

	private AppContext appContext;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Window window = getWindow();// 得到窗口
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 没有标题
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
		window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);// 设置高亮
		setContentView(R.layout.login);
		init();
		appContext = (AppContext) getApplication();
		loginHandler = getLoginHandler();

		// 判断是否第一次登陆
		boolean isFirst = configPres.getBoolean("isFirst", true);
		System.out.println("isFirst is : " + isFirst);
		if (!isFirst)
		{
			String usernamePre = configPres.getString("username", null);
			System.out.println("usernamePre value is : " + usernamePre);
			if (!TextUtils.isEmpty(usernamePre))
			{
				finish();
				startActivity();
			}
		}
	}

	private Handler getLoginHandler()
	{

		return new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				int id = msg.what;
				switch (id) {
				case R.id.login_success:
					configPres.edit().putBoolean("isFirst", false)
							.putString("username", (String) msg.obj).commit();
					appContext.setLname((String) msg.obj);
					startActivity();
					break;

				case R.id.login_failure:
					showError(getResources().getString(R.string.login_failure));
					break;

				default:
					break;
				}
			}

		};
	}

	private void init()
	{
		loginBtn = (Button) findViewById(R.id.login_login_Button);
		username = (EditText) findViewById(R.id.login_username);
		password = (EditText) findViewById(R.id.login_password);
		initEvent();
	}

	private void initEvent()
	{
		loginBtn.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				final CharSequence name = username.getText();
				final CharSequence pass = password.getText();
				if (TextUtils.isEmpty(name))
				{
					username.setError("用户名不能为空，请输入用户名！");
					return;
				}

				if (TextUtils.isEmpty(pass))
				{
					password.setError("密码不能为空，请输入密码！");
					return;
				}
				final String sign = genSign(name, pass);
				System.out.println("sign value is ：" + sign);
				new Thread(new Runnable()
				{

					@Override
					public void run()
					{
						String result = HttpService.login(name, pass, sign);
						if (result.contains("100"))
						{
							MessageUtils.sendMessage(loginHandler,
									R.id.login_success, name.toString());
						} else
						{
							MessageUtils.sendMessage(loginHandler,
									R.id.login_failure, null);
						}
					}
				}).start();
			}

			/**
			 * genSign: <br/>
			 * 
			 * @author wang
			 * @param name
			 * @param pass
			 * @return
			 * @since JDK 1.6
			 */
			private String genSign(CharSequence name, CharSequence pass)
			{
				StringBuffer buffer = new StringBuffer();
				buffer.append("?username=");
				buffer.append(name);
				buffer.append("&pass=");
				buffer.append(pass + privateNum);
				System.out.println("sign  String is :" + buffer.toString());

				return MD5Utils.MD5(buffer.toString());
			}
		});
	}

	private void startActivity()
	{
		finish();
		ActivityHelp.startActivity(this, TakingActivity.class);
	}
}
