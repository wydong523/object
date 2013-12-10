/**   
 * @Title: WorkorderActualPersonnelActivity.java 
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
import android.widget.Toast;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.app.AppContext;
import com.google.zxing.client.android.exceptions.AuthenticationException;
import com.google.zxing.client.android.exceptions.AuthorizationException;
import com.google.zxing.client.android.exceptions.CommunicationException;
import com.google.zxing.client.android.utils.DialogManager;
import com.google.zxing.client.android.utils.HandlerUtils;
import com.google.zxing.client.android.view.calendar.CaldroidFragment;
import com.google.zxing.client.android.view.calendar.CaldroidListener;
import com.google.zxing.client.android.vo.PersonSelect;
import com.google.zxing.client.android.vo.PersonSelectList;

/**
 * @Desctiption 实际情况 人员详情
 * @author 汪渝栋
 * @date 2013-9-29 下午2:57:38
 */
public class WorkorderActualPersonnelActivity extends FragmentActivity {

	public static final DialogManager dialogManager = new DialogManager();

	// 工单:(显示不可修改)
	private EditText workorderActualWonum;

	// 开始日期:(选择) 开始时间
	private EditText workorderActualStartDate;

	// 结束日期:(选择) 结束时间
	private EditText workorderActualEndDate;

	// 员工(选择&&可以查询员工)
	private EditText workorderActualPersonnel;

	private ImageButton workorderActualStartDateBtn;
	private ImageButton workorderActualEndDateBtn;
	private ImageButton workorderActualPersonnelBtn;

	private Button workorder_actual_personnel_return_btn;
	private Button workorder_add_actual_personnel_btn;
	private Button workorder_del_actual_personnel_btn;
	private Button workorder_modify_actual_personnel_btn;

	private CaldroidFragment dialogCaldroidFragment;

	private Handler personnelHanlder;

	private AppContext appContext;

	// 日期格式
	private SimpleDateFormat formatter;

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
		setContentView(R.layout.workorder_actual_personnel);
		appContext = (AppContext) getApplicationContext();
		personnelHanlder = getPersonnelHanlder();
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		init();
		workorder_del_actual_personnel_btn.setVisibility(View.VISIBLE);
		workorder_modify_actual_personnel_btn.setVisibility(View.VISIBLE);
	}

	/**
	 * @Title: getPersonnelHanlder
	 * @Description:
	 * @param @return
	 * @return Handler
	 * @throws
	 */
	private Handler getPersonnelHanlder() {
		return new Handler() {
			/*
			 * (非 Javadoc) <p>Title: handleMessage</p> <p>Description: </p>
			 * 
			 * @param msg
			 * 
			 * @see android.os.Handler#handleMessage(android.os.Message)
			 */
			@Override
			public void handleMessage(Message msg) {
				TreeSet<String> datas = (TreeSet<String>) msg.obj;
				switch (msg.what) {
				case R.id.actual_personnel_select:
					showActualPersonnel(datas, "员工");
					break;
				default:
					break;
				}
			}
		};
	}

	/**
	 * @Title: showActualPersonnel
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	protected void showActualPersonnel(TreeSet<String> datas, String title) {
//		dialogManager.show(this, datas, title, workorderActualPersonnel, null,
//				R.drawable.dialog_list_bg);
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

	final CaldroidListener startDateListener = new CaldroidListener() {

		@Override
		public void onSelectDate(Date date, View view) {
			String mDate = formatter.format(date);
			workorderActualStartDate.setText(mDate);
			dialogCaldroidFragment.dismiss();
		}

		@Override
		public void onChangeMonth(int month, int year) {
		}
	};

	final CaldroidListener endDateListener = new CaldroidListener() {

		@Override
		public void onSelectDate(Date date, View view) {
			String mDate = formatter.format(date);
			workorderActualEndDate.setText(mDate);
			dialogCaldroidFragment.dismiss();
		}

		@Override
		public void onChangeMonth(int month, int year) {
		}
	};

	/**
	 * @Title: initEvent
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void initEvent() {
		workorderActualStartDateBtn.setOnClickListener(new OnClickListener() {

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

		workorderActualEndDateBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialogCaldroidFragment = new CaldroidFragment();
				dialogCaldroidFragment.setCaldroidListener(endDateListener);
				int style = DialogFragment.STYLE_NO_FRAME;
				int theme = android.R.style.Theme_DeviceDefault_Dialog;
				dialogCaldroidFragment.setStyle(style, theme);
				dialogCaldroidFragment.show(getSupportFragmentManager(),
						dialogTag);
			}
		});

		workorderActualPersonnelBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				personPopDialog();
			}
		});

		workorder_actual_personnel_return_btn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						finish();
					}
				});
	}

	/**
	 * @Title: personPopDialog
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	protected void personPopDialog() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				String whereStrParma = "personid in (select laborcode from labor where status='ACTIVE')";
				String tableName = "PERSON";
				PersonSelectList psList;
				ArrayList<PersonSelect> persons;
				TreeSet<String> datas = new TreeSet<String>();
				try {
					psList = (PersonSelectList) appContext.querySelectValue(
							PersonSelect.class.getName(), tableName, tableName,
							whereStrParma, "ActualPersonnelSelectValue");
					if (psList == null) {
						persons = new ArrayList<PersonSelect>();
					} else {
						persons = psList.getPersons();
					}

					for (PersonSelect ps : persons) {
						String content = ps.getPersonid() + "/"
								+ ps.getDisplayname();
						datas.add(content);
					}
					HandlerUtils.sendMessage(personnelHanlder, datas,
							R.id.actual_personnel_select);
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

	/**
	 * @Title: initWidget
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void initWidget() {

		workorderActualWonum = (EditText) findViewById(R.id.workorder_actual_wonum);
		workorderActualStartDate = (EditText) findViewById(R.id.workorder_actual_start_date);
		workorderActualEndDate = (EditText) findViewById(R.id.workorder_actual_end_date);
		workorderActualPersonnel = (EditText) findViewById(R.id.workorder_actual_personnel);

		workorderActualStartDateBtn = (ImageButton) findViewById(R.id.workorder_actual_start_date_button);
		workorderActualEndDateBtn = (ImageButton) findViewById(R.id.workorder_actual_end_date_button);
		workorderActualPersonnelBtn = (ImageButton) findViewById(R.id.workorder_actual_personnel_button);

		workorder_actual_personnel_return_btn = (Button) findViewById(R.id.workorder_actual_personnel_return_btn);
		workorder_add_actual_personnel_btn = (Button) findViewById(R.id.workorder_add_actual_personnel_btn);
		workorder_del_actual_personnel_btn = (Button) findViewById(R.id.workorder_del_actual_personnel_btn);
		workorder_modify_actual_personnel_btn = (Button) findViewById(R.id.workorder_modify_actual_personnel_btn);
	}
}
