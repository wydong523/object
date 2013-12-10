package com.google.zxing.client.android.ui;

import java.net.MalformedURLException;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.app.AppContext;
import com.google.zxing.client.android.constant.Actions;
import com.google.zxing.client.android.exceptions.AuthenticationException;
import com.google.zxing.client.android.exceptions.AuthorizationException;
import com.google.zxing.client.android.exceptions.CommunicationException;
import com.google.zxing.client.android.ui.PRListActivity.PrOperateBroadcast;
import com.google.zxing.client.android.vo.PR;
import com.google.zxing.client.android.vo.PRLine;

/**
 * prline详情
 * 
 * @author 汪渝栋
 * 
 */
public class PRLineDetail extends Activity {

	private AppContext appContext;

	private int prlineFlag;// pr行操作标记

	private Intent intent = null;

	private PRLine prLine = null;

	private TextView subTitle;

	private EditText prLineDescription;
	private EditText prLineUdmodel;
	private EditText prLineClassstructureid;
	private EditText prLineOrderqty;
	private EditText prLineOrderunit;
	private EditText prLineUnitcost;
	private EditText prLineUdusage;
	private EditText prLineUdaccepter;

	private Button prLineDelBtn;
	private Button prLineModifyBtn;
	private Button prLineAddBtn;
	private Button prLineReturnBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pr_line_detail);
		appContext = (AppContext) getApplication();
		prlineFlag = getIntent().getIntExtra("flag",
				PRDetailActivity.PR_LINE_ADD);
		prLine = appContext.getCurPrLine();
		init();
	}

	private void init() {
		initWigdet();
		initContent();
		initEvent();
	}

	private void initEvent() {
		prLineDelBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				delPrLine();
			}
		});

		prLineModifyBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				modifyPrLine();
			}
		});

		prLineAddBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				addPrLine();
			}
		});

		prLineReturnBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	protected void spwfro() {
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				try {
//					String result = appContext.inwfro();
//					if (result != null && result.contains("TRUE")) {
//						finish();
//					} else {
//					}
//				} catch (MalformedURLException e) {
//					e.printStackTrace();
//				} catch (CommunicationException e) {
//					e.printStackTrace();
//				} catch (AuthorizationException e) {
//					e.printStackTrace();
//				} catch (AuthenticationException e) {
//					e.printStackTrace();
//				}
//			}
//		}).start();
	}

	protected void addPrLine() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				String parmas = assemblyPrLineParmas("&", prLine);
				try {
					String result = appContext.addPrLine(parmas);
					finish();
					sendBroadcast(result, PRDetailActivity.PR_LINE_ADD,
							Actions.PR_LINE_BROADCAST_ACTION);

				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CommunicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AuthorizationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AuthenticationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	protected void modifyPrLine() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String parmas = assemblyPrLineParmas("&", prLine);
				try {
					String result = appContext.modifyPrline(parmas);
					finish();
					sendBroadcast(result, PRDetailActivity.PR_LINE_MODIFY,
							Actions.PR_LINE_BROADCAST_ACTION);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CommunicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AuthorizationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AuthenticationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	protected void delPrLine() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					String result = appContext.delPrLine();
					finish();
					sendBroadcast(result, PRDetailActivity.PR_LINE_DEL,
							Actions.PR_LINE_BROADCAST_ACTION);
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

	protected void sendBroadcast(String result, int flag, String action) {
		Intent intent = new Intent();
		intent.setAction(action);
		intent.putExtra("result", result);
		intent.putExtra("flag", flag);
		sendBroadcast(intent);
	}

	private void initContent() {
		switch (prlineFlag) {
		case PRDetailActivity.PR_LINE_OTHER:
		case PRDetailActivity.PR_LINE_MODIFY:
		case PRDetailActivity.PR_LINE_DEL:
			subTitle.setText("修改/删除");
			prLineDescription.setText(prLine.getDescription());
			prLineUdmodel.setText(prLine.getUdmodel());
			prLineClassstructureid.setText(prLine.getClassstructureid());
			prLineOrderqty.setText(prLine.getOrderqty());
			prLineOrderunit.setText(prLine.getOrderunit());
			prLineUnitcost.setText(prLine.getUnitcost());
			// prLineUdusage.setText(prLine.getUdusage());
			prLineUdaccepter.setText(prLine.getUdaccepter());
			break;
		case PRDetailActivity.PR_LINE_ADD:
			subTitle.setText("添加");
			prLineDescription.setText("");
			prLineUdmodel.setText("");
			prLineClassstructureid.setText("");
			prLineOrderqty.setText("");
			prLineOrderunit.setText("");
			prLineUnitcost.setText("");
			// prLineUdusage.setText("");
			prLineUdaccepter.setText("");
		default:
			break;

		}

	}

	private void initWigdet() {
		subTitle = (TextView) findViewById(R.id.pr_line_sub_title);

		prLineDescription = (EditText) findViewById(R.id.pr_line_description);
		prLineUdmodel = (EditText) findViewById(R.id.pr_line_udmodel);
		prLineClassstructureid = (EditText) findViewById(R.id.pr_line_classstructureid);
		prLineOrderqty = (EditText) findViewById(R.id.pr_line_orderqty);
		prLineOrderunit = (EditText) findViewById(R.id.pr_line_orderunit);
		prLineUnitcost = (EditText) findViewById(R.id.pr_line_unitcost);
		// prLineUdusage = (EditText) findViewById(R.id.pr_line_udusage);
		prLineUdaccepter = (EditText) findViewById(R.id.pr_line_udaccepter);

		prLineDelBtn = (Button) findViewById(R.id.pr_line_del_button);
		prLineModifyBtn = (Button) findViewById(R.id.pr_line_modify_button);
		prLineAddBtn = (Button) findViewById(R.id.pr_multifunction_button);
		prLineReturnBtn = (Button) findViewById(R.id.pr_line_return_btn);
		if (prlineFlag != PRDetailActivity.PR_LINE_ADD) {
			prLineModifyBtn.setVisibility(View.VISIBLE);
			prLineDelBtn.setVisibility(View.VISIBLE);
			prLineAddBtn.setVisibility(View.GONE);
		} else {
			prLineModifyBtn.setVisibility(View.GONE);
			prLineDelBtn.setVisibility(View.GONE);
			prLineAddBtn.setVisibility(View.VISIBLE);
		}
	}

	private PRLine getPrline() {
		intent = getIntent();
		prLine = (PRLine) intent.getSerializableExtra("prline");
		return prLine;
	}

	protected String assemblyPrLineParmas(String split, PRLine prLine) {
		StringBuffer buffer = new StringBuffer();
		String description = prLineDescription.getText().toString();
		String udmodel = prLineUdmodel.getText().toString();
		String classstructureid = prLineClassstructureid.getText().toString();
		String orderqty = prLineOrderqty.getText().toString();
		String orderunit = prLineOrderunit.getText().toString();
		String unitcost = prLineUnitcost.getText().toString();
		String udusage = prLineUdusage.getText().toString();
		String udaccepter = prLineUdaccepter.getText().toString();
		buffer.append("LINETYPE=MATERIAL");
		buffer.append(split);
		buffer.append("DESCRIPTION=");
		buffer.append(description);
		buffer.append(split);
		buffer.append("UDMODEL=");
		buffer.append(udmodel);
		buffer.append(split);
		buffer.append("CLASSSTRUCTUREID=");
		buffer.append(classstructureid);
		buffer.append(split);
		buffer.append("ORDERQTY=");
		buffer.append(orderqty);
		buffer.append(split);
		buffer.append("ORDERUNIT=");
		buffer.append(orderunit);
		buffer.append(split);
		buffer.append("UNITCOST=");
		buffer.append(unitcost);
		buffer.append(split);
		buffer.append("UDUSAGE=");
		buffer.append(udusage);
		buffer.append(split);
		buffer.append("UDACCEPTER=");
		buffer.append(udaccepter);

		if (prLine == null) {
			prLine = new PRLine();
			appContext.setCurPrLine(prLine);
		}
		prLine.setDescription(description != null ? description : "");
		prLine.setUdmodel(udmodel != null ? udmodel : "");
		prLine.setClassstructureid(classstructureid != null ? classstructureid
				: "");
		prLine.setOrderqty(orderqty != null ? orderqty : "");
		prLine.setOrderunit(orderunit != null ? orderunit : "");
		prLine.setUnitcost(unitcost != null ? unitcost : "");
		prLine.setUdusage(udusage != null ? udusage : "");
		prLine.setUdaccepter(udaccepter != null ? udaccepter : "");

		return buffer.toString();
	}

}
