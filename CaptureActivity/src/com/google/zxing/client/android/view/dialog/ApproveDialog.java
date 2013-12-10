/**   
 * @Title: ApproveDialog.java 
 * @Package com.google.zxing.client.android.view.dialog 
 * @version V1.0   
 */
package com.google.zxing.client.android.view.dialog;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.app.AppContext;
import com.google.zxing.client.android.app.AppManager;
import com.google.zxing.client.android.exceptions.AuthenticationException;
import com.google.zxing.client.android.exceptions.AuthorizationException;
import com.google.zxing.client.android.exceptions.CommunicationException;
import com.google.zxing.client.android.utils.HandlerUtils;
import com.google.zxing.client.android.vo.Wfaction;
import com.google.zxing.client.android.vo.Workorder;

import de.keyboardsurfer.android.widget.crouton.Style;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * @Desctiption 审批流程弹出窗口
 * @author 汪渝栋
 * @date 2013-11-15 上午9:05:57
 */
public class ApproveDialog extends Dialog {
	private String title;

	private String msg;

	private Context context;

	private AppContext appContext;

	private Handler approveResultHandler;

	private ArrayList<Wfaction> wfactions;

	private Workorder workorder;

	public ApproveDialog(Workorder workorder, AppContext appContext,
			Context context, int theme, String title,
			ArrayList<Wfaction> wfactions) {
		super(context, theme);
		this.context = context;
		this.title = title;
		this.appContext = appContext;
		this.wfactions = wfactions;
		this.workorder = workorder;
		approveResultHandler = getApproveResultHandler();
	}

	/**
	 * @Title: getApproveResultHandler
	 * @Description: TODO
	 * @param @return
	 * @return Handler
	 * @throws
	 */
	private Handler getApproveResultHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case R.id.approve:
					showinfo(msg.obj);
					break;
				case R.id.approve_error:
					showerror(msg.obj);
					break;
				default:
					break;
				}
			}
		};
	}

	/**
	 * @Title: showerror
	 * @Description: TODO
	 * @param @param obj
	 * @return void
	 * @throws
	 */
	protected void showerror(Object obj) {
		appContext.showError(obj.toString(), (Activity) context);
	}

	/**
	 * @Title: showinfo
	 * @Description: TODO
	 * @param @param obj
	 * @return void
	 * @throws
	 */
	protected void showinfo(Object obj) {
		appContext.showInfo(obj.toString(), (Activity) context, Style.INFO);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.approve_dialog);
		setCanceledOnTouchOutside(true);
		((TextView) findViewById(R.id.dialog_title)).setText(title);
		final EditText msg = (EditText) findViewById(R.id.approve_remark);
		final RadioButton radio1 = (RadioButton) findViewById(R.id.approve_radio1);
		final RadioButton radio2 = (RadioButton) findViewById(R.id.approve_radio2);
		if (wfactions.size() == 1) {
			radio1.setText(wfactions.get(0).getInstruction());
			radio2.setVisibility(View.GONE);
		} else {
			radio1.setText(wfactions.get(1).getInstruction());
			radio2.setText(wfactions.get(0).getInstruction());
		}
		((Button) findViewById(R.id.dialog_sure))
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						new Thread(new Runnable() {
							@Override
							public void run() {
								try {
									String userid = appContext.getLoginUid();
									String memo = msg.getText().toString();
									String pykeyid = workorder.getWorkorderid();
									String appName = "WOTRACK";
									String ownerTable = "WORKORDER";
									boolean selectWhat = false;
									Serializable result;
									if (radio1.isChecked()) {
										selectWhat = true;
									} else if (radio2.isChecked()) {
										selectWhat = false;
									}
									result = appContext.approvePro(userid,
											memo, pykeyid, appName, ownerTable,
											selectWhat);
									HandlerUtils.sendMessage(
											approveResultHandler, result,
											R.id.approve);
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

		radio1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				radio2.setChecked(false);
			}
		});

		radio2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				radio1.setChecked(false);
			}
		});

	}
}
