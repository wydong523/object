package com.google.zxing.client.android.ui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.android.Contents;
import com.google.zxing.client.android.Intents;
import com.google.zxing.client.android.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * 生成二维码操作类
 * 
 * @author wyd
 * 
 */
public class GenerateActivity extends BaseActivity {
	private final static String TAG = GenerateActivity.class.getSimpleName();

	private EditText name = null;

	private EditText email = null;

	private EditText phone = null;

	private Button genButton = null;

	private Button retButton = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.generate);
	}

	@Override
	protected void onResume() {
		super.onResume();
		name = (EditText) findViewById(R.id.gen_name);
		email = (EditText) findViewById(R.id.gen_email);
		phone = (EditText) findViewById(R.id.gen_phone);
		genButton = (Button) findViewById(R.id.gen_button);
		retButton = (Button) findViewById(R.id.return_button);

		retButton.setOnClickListener(new ButtonListenner(this));
		genButton.setOnClickListener(new ButtonListenner(this, name, email,
				phone));
	}

	final static class ButtonListenner implements View.OnClickListener {
		Context mContext;
		EditText name;
		EditText email;
		EditText phone;

		public ButtonListenner(Context context, EditText name, EditText email,
				EditText phone) {
			this.mContext = context;
			this.name = name;
			this.email = email;
			this.phone = phone;
		}

		public ButtonListenner(Context context) {
			this.mContext = context;
		}

		@Override
		public void onClick(View v) {

			int id = v.getId();
			if (id == R.id.gen_button) {
				String content = getContent(name, email, phone);
				Intent intent = new Intent(Intents.Encode.ACTION);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
				intent.putExtra(Intents.Encode.TYPE, Contents.Type.TEXT);
				intent.putExtra(Intents.Encode.DATA, content);
				intent.putExtra(Intents.Encode.FORMAT,
						BarcodeFormat.QR_CODE.toString());
				mContext.startActivity(intent);
			} else if (id == R.id.return_button) {
				((Activity) mContext).finish();
			}
		}

		private String getContent(EditText name, EditText email, EditText phone) {
			StringBuilder builder = new StringBuilder();
			String nameContent = name.getText().toString();
			if (nameContent.length() > 0 && nameContent != null) {
				builder.append(nameContent + "\r\n");
			}
			String emailContent = email.getText().toString();
			if (emailContent.length() > 0 && emailContent != null) {
				builder.append(emailContent + "\r\n");
			}
			String phoneContent = phone.getText().toString();
			if (phoneContent.length() > 0 && phoneContent != null) {
				builder.append(phoneContent + "\r\n");
			}
			return builder.toString();
		}
	}
}
