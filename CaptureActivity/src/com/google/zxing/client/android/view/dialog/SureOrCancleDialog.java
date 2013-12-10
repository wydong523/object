package com.google.zxing.client.android.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.app.AppContext;
import com.google.zxing.client.android.app.AppManager;

/**
 * 自定义确定/取消弹出窗口
 * 
 * @author 汪渝栋
 * 
 */
public class SureOrCancleDialog extends AlertDialog {

	private String title;

	private String msg;

	private Context context;

	private AppContext appContext;

	public SureOrCancleDialog(AppContext appContext, Context context,
			int theme, String title, String msg) {
		super(context, theme);
		this.context = context;
		this.title = title;
		this.msg = msg;
		this.appContext = appContext;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sure_or_cancle_layout);
		((TextView) findViewById(R.id.dialog_title)).setText(title);
		((TextView) findViewById(R.id.dialog_msg)).setText(msg);
		final CheckBox upload = (CheckBox) findViewById(R.id.upload);
		((Button) findViewById(R.id.dialog_sure))
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						System.out.println(upload.isChecked());
						if (upload.isChecked()) {
							// 上传
							new Thread(new Runnable() {
								@Override
								public void run() {
									appContext.upload();
								}
							}).start();
						}
						// 退出
						AppManager.getAppManager().AppExit(context);
						dismiss();
					}
				});

		((Button) findViewById(R.id.dialog_cancle))
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						dismiss();
					}
				});

	}

}
