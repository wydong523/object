package com.google.zxing.client.android.ui;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.TreeSet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.app.AppContext;
import com.google.zxing.client.android.constant.Actions;
import com.google.zxing.client.android.constant.StringContants;
import com.google.zxing.client.android.exceptions.AuthenticationException;
import com.google.zxing.client.android.exceptions.AuthorizationException;
import com.google.zxing.client.android.exceptions.CommunicationException;
import com.google.zxing.client.android.utils.HandlerUtils;
import com.google.zxing.client.android.vo.PersonSelect;
import com.google.zxing.client.android.vo.PersonSelectList;
import com.google.zxing.client.android.vo.SelectDialogEntity;
import com.google.zxing.client.android.vo.Workorder;
import com.google.zxing.client.android.vo.WorkorderPlanTask;
import com.google.zxing.client.android.vo.WorkorderPlanWplabor;

/**   
 * @Title: WorkorderPlanPersonnelActivity.java 
 * @Package  
 * @version V1.0   
 */

/**
 * @Desctiption 工单模块 人员
 * @author 汪渝栋
 * @date 2013-9-26 下午3:07:57
 */
public class WorkorderPlanPersonnelActivity extends BaseActivity {
	// 人工(选择&&查询)
	private EditText workorderPlanPersonnelName;
	// 数量
	private EditText workorderPlanPersonnelCount;

	// 常规小时数(手写)
	private EditText workorderPlanPersonnelHours;

	private ImageButton workorderPlanPersonnelNameBtn;

	private Button workorderPlanPersonnelReturnBtn;
	private Button workorderAddPlanPersonBtn;
	private Button workorderDelPlanPersonBtn;
	private Button workorderModifyPlanPersonBtn;

	private Handler planPersonnelHandler;

	private AppContext appContext;

	private WorkorderPlanWplabor planWplabor;
	private Workorder workorder;

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
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workorder_plan_personnel);
		planWplabor = (WorkorderPlanWplabor) getIntent().getSerializableExtra(
				"planWplabor");
		workorder = (Workorder) getIntent().getSerializableExtra("workorder");
		appContext = (AppContext) getApplicationContext();
		init();
		if (planWplabor != null) {
			workorderPlanPersonnelName.setText(planWplabor.getLaborcode());
			workorderPlanPersonnelCount.setText(planWplabor.getQuantity());
			workorderPlanPersonnelHours.setText(planWplabor.getLaborhrs());
			workorderDelPlanPersonBtn.setVisibility(View.VISIBLE);
			workorderModifyPlanPersonBtn.setVisibility(View.VISIBLE);
		} else {
			workorderAddPlanPersonBtn.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * @Title: init
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void init() {
		planPersonnelHandler = getPlanPersonnelHandler();
		initWidget();
		initEvent();
	}

	/**
	 * @Title: getPlanPersonnelHandler
	 * @Description: TODO
	 * @param @return
	 * @return Handler
	 * @throws
	 */
	private Handler getPlanPersonnelHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				TreeSet<String> datas = (TreeSet<String>) msg.obj;
				switch (msg.what) {
				case R.id.fault_person_select:
					showPersonList(datas);
					break;
				default:
					break;
				}
			}
		};
	}

	private void showPersonList(TreeSet<String> datas) {
		SelectDialogEntity dialogEntity = new SelectDialogEntity();
		dialogEntity.setContext(this);
		dialogEntity.setContents(datas);
		dialogEntity.setAppContext(appContext);
		dialogEntity.setTitle("人员");
		dialogEntity.setEdit(workorderPlanPersonnelName);
		dialogEntity.setGetFocusView(workorderPlanPersonnelCount);
		dialogEntity.setTableName("PERSON");
		ArrayList<String> cum = new ArrayList<String>();
		cum.add("DISPLAYNAME=");
		cum.add("PERSONID=");
		cum.add("WHERESTR=");
		show(dialogEntity, R.drawable.dialog_list_bg, cum);

	}

	/**
	 * @Title: initEvent
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void initEvent() {
		workorderPlanPersonnelReturnBtn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						finish();
					}
				});

		workorderPlanPersonnelNameBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				personPopDialog();
			}
		});

		workorderAddPlanPersonBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				addPlanPerson();
			}
		});

		workorderDelPlanPersonBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				delPlanPerson();
			}
		});

		workorderModifyPlanPersonBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				modifyPlanPerson();
			}
		});
	}

	/**
	 * @Title: modifyPlanPerson
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	protected void modifyPlanPerson() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String flag = "ModifyWorkorderPlanWplabor";
				String className = WorkorderPlanWplabor.class.getName();
				String table = "WORKORDER";
				String pyKeyid = workorder.getWorkorderid();
				String relationShip = "SHOWPLANLABOR";
				String tableLine = "WPLABOR";
				String tableLineId = planWplabor.getWplaborid();
				String listValue = assemblyParmas(StringContants.SPLIT, false);
				try {
					Serializable datas = appContext.modify(flag, className,
							table, pyKeyid, relationShip, tableLine,
							tableLineId, listValue);
					Intent intent = new Intent(Actions.WORKORDER_PLAN_BROADCAST);
					intent.putExtra("id", R.id.workorder_plan_wplabor);
					intent.putExtra("operate",
							R.id.workorder_plan_wplabor_modify);
					intent.putExtra("datas", datas);
					finish();
					sendBroadcast(intent);
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

	/**
	 * @Title: delPlanPerson
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	protected void delPlanPerson() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String flag = "DelWorkorderPlanWplabor";
				String className = WorkorderPlanWplabor.class.getName();
				String table = "workorder";
				String pyKeyid = workorder.getWorkorderid();
				String tableLine = "WPLABOR";
				String tableLineid = planWplabor.getWplaborid();
				String relationShip = "SHOWPLANLABOR";
				try {
					Serializable datas = appContext.delete(flag, className,
							table, pyKeyid, tableLine, tableLineid,
							relationShip);
					Intent intent = new Intent(Actions.WORKORDER_PLAN_BROADCAST);
					intent.putExtra("id", R.id.workorder_plan_wplabor);
					intent.putExtra("operate", R.id.workorder_plan_wplabor_del);
					intent.putExtra("datas", datas);
					sendBroadcast(intent);
					finish();
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

	/**
	 * @Title: addPlanPerson
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	protected void addPlanPerson() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String flag = "AddWorkorderPlanWplabor";
				String className = WorkorderPlanWplabor.class.getName();
				String table = "WORKORDER";
				String appName = "";
				String pyKeyid = workorder.getWorkorderid();
				String relationShip = "SHOWPLANLABOR";
				String listValue = assemblyParmas(StringContants.SPLIT, true);
				try {
					Serializable datas = appContext.add(flag, className, table,
							appName, pyKeyid, relationShip, listValue);
					Intent intent = new Intent(Actions.WORKORDER_PLAN_BROADCAST);
					intent.putExtra("id", R.id.workorder_plan_wplabor);
					intent.putExtra("operate", R.id.workorder_plan_wplabor_add);
					intent.putExtra("datas", datas);
					finish();
					sendBroadcast(intent);
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

	protected void personPopDialog() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				String whereStrParma = "status in (select value from synonymdomain where maxvalue='ACTIVE' and domainid='PERSONSTATUS')";
				String tableName = "PERSON";
				PersonSelectList psList;
				ArrayList<PersonSelect> persons;
				TreeSet<String> datas = new TreeSet<String>();
				try {
					psList = (PersonSelectList) appContext.querySelectValue(
							PersonSelect.class.getName(), tableName, tableName,
							whereStrParma, "PersonPlanSelectValue");
					if (psList == null) {
						persons = new ArrayList<PersonSelect>();
					} else {
						persons = psList.getPersons();
					}

					for (PersonSelect ps : persons) {
						String content = ps.getPersonid()
								+ StringContants.SPLIT_LINE
								+ ps.getDisplayname();
						datas.add(content);
					}
					HandlerUtils.sendMessage(planPersonnelHandler, datas,
							R.id.fault_person_select);

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

	/**
	 * @Title: showError
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	protected void showError() {
		appContext.showError(getResources().getString(R.string.workorder_plan),
				this);

	}

	/**
	 * @Title: initWidget
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void initWidget() {

		workorderPlanPersonnelName = (EditText) findViewById(R.id.workorder_plan_personnel_name);
		workorderPlanPersonnelCount = (EditText) findViewById(R.id.workorder_plan_personnel_count);
		workorderPlanPersonnelHours = (EditText) findViewById(R.id.workorder_plan_personnel_hours);

		workorderPlanPersonnelNameBtn = (ImageButton) findViewById(R.id.workorder_plan_personnel_name_button);

		workorderPlanPersonnelReturnBtn = (Button) findViewById(R.id.workorder_plan_personnel_return_btn);
		workorderAddPlanPersonBtn = (Button) findViewById(R.id.workorder_add_plan_person_btn);
		workorderDelPlanPersonBtn = (Button) findViewById(R.id.workorder_del_plan_person_btn);
		workorderModifyPlanPersonBtn = (Button) findViewById(R.id.workorder_modify_plan_person_btn);
	}

	/**
	 * 
	 * @Title: assemblyParmas
	 * @Description: TODO
	 * @param @param split
	 * @param @param flag false修改/true 增加
	 * @param @return
	 * @return String
	 * @throws
	 */
	protected String assemblyParmas(String split, boolean tag) {

		String laborcode = workorderPlanPersonnelName.getText().toString()
				.split(StringContants.SPLIT_LINE)[0];
		String quantity = workorderPlanPersonnelCount.getText().toString();
		String laborhrs = workorderPlanPersonnelHours.getText().toString();
		StringBuffer buffer = new StringBuffer();

		buffer.append("LABORCODE=");
		buffer.append(laborcode);
		buffer.append(split);
		buffer.append("QUANTITY=");
		buffer.append(quantity);
		buffer.append(split);
		buffer.append("LABORHRS=");
		buffer.append(laborhrs);

		return buffer.toString();
	}
}
