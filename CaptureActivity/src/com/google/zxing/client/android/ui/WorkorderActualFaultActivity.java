/**   
 * @Title: WorkorderActualFaultActivity.java 
 * @Package com.google.zxing.client.android.ui 
 * @version V1.0   
 */
package com.google.zxing.client.android.ui;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.app.AppContext;
import com.google.zxing.client.android.exceptions.AuthenticationException;
import com.google.zxing.client.android.exceptions.AuthorizationException;
import com.google.zxing.client.android.exceptions.CommunicationException;
import com.google.zxing.client.android.utils.DialogManager;
import com.google.zxing.client.android.utils.HandlerUtils;
import com.google.zxing.client.android.view.calendar.CaldroidFragment;
import com.google.zxing.client.android.view.calendar.CaldroidListener;
import com.google.zxing.client.android.vo.FailurecodeSelect;
import com.google.zxing.client.android.vo.FailurecodeSelectList;

/**
 * @Desctiption 实际情况 故障详情
 * @author 汪渝栋
 * @date 2013-9-29 下午3:00:32
 */
public class WorkorderActualFaultActivity extends FragmentActivity {

	public static final DialogManager dialogManager = new DialogManager();

	// 故障类:(可选可查)
	private EditText workorder_actual_fault_class;

	// 故障日期(可选可查)
	private EditText workorder_actual_fault_date;

	// 备注(纯手写)
	private EditText workorder_actual_fault_remark;

	// 子类(可选可查)
	private EditText workorder_actual_fault_subclass;

	// 问题(可选可查)
	private EditText workorder_actual_fault_problem;

	// 原因(可选可查)
	private EditText workorder_actual_fault_cause;

	// 维修(可选可查)
	private EditText workorder_actual_fault_maintenance;

	private ImageButton workorder_actual_fault_class_btn;
	private ImageButton workorder_actual_fault_date_btn;
	private ImageButton workorder_actual_fault_subclass_btn;
	private ImageButton workorder_actual_fault_problem_btn;
	private ImageButton workorder_actual_fault_cause_btn;
	private ImageButton workorder_actual_fault_maintenance_btn;

	private Button workorder_add_actual_fault_btn;
	private Button workorder_del_actual_fault_btn;
	private Button workorder_modify_actual_fault_btn;
	private Button workorder_actual_fault_return_btn;

	private AppContext appContext;

	private Handler faultHandler;

	// 日期格式
	private SimpleDateFormat formatter;

	private CaldroidFragment dialogCaldroidFragment;
	
	// 弹出日历标记
	final String dialogTag = "CALDROID_DIALOG_FRAGMENT";

	/*
	 * (非 Javadoc) <p>Title: onCreate</p> <p>Description: </p>
	 * 
	 * @param savedInstanceState
	 * 
	 * @see
	 * com.google.zxing.client.android.ui.BaseActivity#onCreate(android.os.Bundle
	 * )
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workorder_actual_fault);
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		appContext = (AppContext) getApplicationContext();
		faultHandler = getFaultHandler();
		init();
		workorder_del_actual_fault_btn.setVisibility(View.VISIBLE);
		workorder_modify_actual_fault_btn.setVisibility(View.VISIBLE);
	}

	/**
	 * @Title: getFaultHandler
	 * @Description: TODO
	 * @param @return
	 * @return Handler
	 * @throws
	 */
	private Handler getFaultHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				TreeSet<String> datas = (TreeSet<String>) msg.obj;
				switch (msg.what) {
				case R.id.fault_category_select:
					show(datas, "故障类", workorder_actual_fault_class,
							workorder_actual_fault_date);
					break;
				case R.id.fault_subcalss_select:
					show(datas, "子类", workorder_actual_fault_subclass,
							workorder_actual_fault_problem);
					break;
				case R.id.fault_question_code_select:
					show(datas, "问题", workorder_actual_fault_problem,
							workorder_actual_fault_cause);
					break;
				case R.id.actual_fault_cause_select:
					show(datas, "原因", workorder_actual_fault_cause,
							workorder_actual_fault_maintenance);
					break;
				case R.id.actual_fault_maintenance_select:
					show(datas, "维修", workorder_actual_fault_maintenance, null);
					break;
				default:
					break;
				}
			}
		};
	}

	protected void show(TreeSet<String> datas, String title, EditText curEdit,
			EditText getFous) {
//		dialogManager.show(this, datas, title, curEdit, getFous,
//				R.drawable.dialog_list_bg);
	}

	protected void faultPopDialog(final int id) {
		new Thread(new Runnable() {

			@Override
			public void run() {

				String tableName = "FAILURECODE";
				String whereStrParma = getwhereStr(id);
				FailurecodeSelectList fcList;
				ArrayList<FailurecodeSelect> fcs;
				TreeSet<String> datas = new TreeSet<String>();
				// 获取缓存文件名标记
				String flag = getflag();
				try {
					fcList = (FailurecodeSelectList) appContext
							.querySelectValue(
									FailurecodeSelect.class.getName(),
									tableName, tableName, whereStrParma, flag);
					if (fcList == null) {
						fcs = new ArrayList<FailurecodeSelect>();
					} else {
						fcs = fcList.getFailurecodes();
					}

					for (FailurecodeSelect fc : fcs) {
						String content = fc.getFailurecode() + "/"
								+ fc.getDescription();
						datas.add(content);
					}
					HandlerUtils.sendMessage(faultHandler, datas, id);

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

			private String getflag() {
				String str = "";
				switch (id) {
				case R.id.fault_category_select:
					str = "ActualFaultClassSelect";
					break;
				case R.id.fault_subcalss_select:
					str = "ActualFaultSubClassSelect";
					break;
				case R.id.fault_question_code_select:
					str = "ActualFaultQuestionCodeSelect";
					break;
				case R.id.actual_fault_cause_select:
					str = "ActualFaultCauseSelect";
					break;
				case R.id.actual_fault_maintenance_select:
					str = "ActualFaultMaintenanceSelect";
					break;
				default:
					break;
				}
				return str;
			}

			private String getwhereStr(int id) {
				String str = "";
				String parms;
				switch (id) {
				case R.id.fault_category_select:
					str = "failurecode in (select failurecode from failurelist where parent is null)";
					break;
				case R.id.fault_subcalss_select:
					parms = workorder_actual_fault_class.getText().toString()
							.split("/")[0];
					str = "failurecode in (select failurecode from failurelist where parent in (select failurelist from failurelist where failurecode='"
							+ parms + "'))";
					break;
				case R.id.fault_question_code_select:
					parms = workorder_actual_fault_subclass.getText()
							.toString().split("/")[0];
					str = "failurecode in (select failurecode from failurelist where parent in (select failurelist from failurelist where failurecode='"
							+ parms + "'))";
					break;
				case R.id.actual_fault_cause_select:
					parms = workorder_actual_fault_problem.getText().toString()
							.split("/")[0];
					str = "failurecode in (select failurecode from failurelist where parent in (select failurelist from failurelist where failurecode='"
							+ parms + "'))";
					break;
				case R.id.actual_fault_maintenance_select:
					parms = workorder_actual_fault_cause.getText().toString()
							.split("/")[0];
					str = "failurecode in (select failurecode from failurelist where parent in (select failurelist from failurelist where failurecode='"
							+ parms + "'))";
					break;

				default:
					break;
				}
				return str;
			}
		}).start();
	}

	/**
	 * @Title: init
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void init() {
		initWidget();
		initEvent();
	}

	/**
	 * @Title: initEvent
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void initEvent() {
		workorder_actual_fault_class_btn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						faultPopDialog(R.id.fault_category_select);
					}
				});

		workorder_actual_fault_date_btn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialogCaldroidFragment = new CaldroidFragment();
						dialogCaldroidFragment.setCaldroidListener(startDateListener);
						int style = DialogFragment.STYLE_NO_FRAME;
						int theme = android.R.style.Theme_DeviceDefault_Dialog;
						dialogCaldroidFragment.setStyle(style, theme);
						dialogCaldroidFragment.show(getSupportFragmentManager(),
								dialogTag);
					}
				});

		workorder_actual_fault_subclass_btn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						faultPopDialog(R.id.fault_subcalss_select);
					}
				});

		workorder_actual_fault_problem_btn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						faultPopDialog(R.id.fault_question_code_select);
					}
				});

		workorder_actual_fault_cause_btn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						faultPopDialog(R.id.actual_fault_cause_select);
					}
				});

		workorder_actual_fault_maintenance_btn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						faultPopDialog(R.id.actual_fault_maintenance_select);
					}
				});

		workorder_add_actual_fault_btn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

					}
				});

		workorder_del_actual_fault_btn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

					}
				});

		workorder_modify_actual_fault_btn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

					}
				});

		workorder_actual_fault_return_btn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						finish();
					}
				});

	}

	/**
	 * @Title: initWidget
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void initWidget() {
		workorder_actual_fault_class = (EditText) findViewById(R.id.workorder_actual_fault_class);
		workorder_actual_fault_date = (EditText) findViewById(R.id.workorder_actual_fault_date);
		workorder_actual_fault_remark = (EditText) findViewById(R.id.workorder_actual_fault_remark);
		workorder_actual_fault_subclass = (EditText) findViewById(R.id.workorder_actual_fault_subclass);
		workorder_actual_fault_problem = (EditText) findViewById(R.id.workorder_actual_fault_problem);
		workorder_actual_fault_cause = (EditText) findViewById(R.id.workorder_actual_fault_cause);
		workorder_actual_fault_maintenance = (EditText) findViewById(R.id.workorder_actual_fault_maintenance);

		workorder_actual_fault_class_btn = (ImageButton) findViewById(R.id.workorder_actual_fault_class_btn);
		workorder_actual_fault_date_btn = (ImageButton) findViewById(R.id.workorder_actual_fault_date_btn);
		workorder_actual_fault_subclass_btn = (ImageButton) findViewById(R.id.workorder_actual_fault_subclass_btn);
		workorder_actual_fault_problem_btn = (ImageButton) findViewById(R.id.workorder_actual_fault_problem_btn);
		workorder_actual_fault_cause_btn = (ImageButton) findViewById(R.id.workorder_actual_fault_cause_btn);
		workorder_actual_fault_maintenance_btn = (ImageButton) findViewById(R.id.workorder_actual_fault_maintenance_btn);

		workorder_add_actual_fault_btn = (Button) findViewById(R.id.workorder_add_actual_fault_btn);
		workorder_del_actual_fault_btn = (Button) findViewById(R.id.workorder_del_actual_fault_btn);
		workorder_modify_actual_fault_btn = (Button) findViewById(R.id.workorder_modify_actual_fault_btn);
		workorder_actual_fault_return_btn = (Button) findViewById(R.id.workorder_actual_fault_return_btn);
	}
	
	final CaldroidListener startDateListener = new CaldroidListener() {

		@Override
		public void onSelectDate(Date date, View view) {
			String mDate = formatter.format(date);
			workorder_actual_fault_date.setText(mDate);
			dialogCaldroidFragment.dismiss();
		}

		@Override
		public void onChangeMonth(int month, int year) {
		}
	};
}
