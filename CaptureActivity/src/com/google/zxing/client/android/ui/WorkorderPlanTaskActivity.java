/**   
 * @Title: WorkorderPlanTaskActivity.java 
 * @Package com.google.zxing.client.android.ui 
 * @version V1.0   
 */
package com.google.zxing.client.android.ui;

import java.io.Serializable;
import java.net.MalformedURLException;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.constant.Actions;
import com.google.zxing.client.android.constant.StringContants;
import com.google.zxing.client.android.exceptions.AuthenticationException;
import com.google.zxing.client.android.exceptions.AuthorizationException;
import com.google.zxing.client.android.exceptions.CommunicationException;
import com.google.zxing.client.android.utils.HandlerUtils;
import com.google.zxing.client.android.vo.Workorder;
import com.google.zxing.client.android.vo.WorkorderPlanTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * @Desctiption 工单任务详情
 * @author 汪渝栋
 * @date 2013-9-26 上午11:29:42
 */
public class WorkorderPlanTaskActivity extends BaseActivity {

	// 新建行(按钮&&事件处理)

	// 任务序号
	private EditText workorderPlanTaskId;

	// 描述(文字)
	private EditText workorderPlanTaskDescription;

	// 估计持续时间
	private EditText workorderPlanTaskContinuousTime;

	private ImageButton workorderPlanTaskDescriptionBtn;

	private Button workorderPlanReturnBtn;
	private Button workorderAddPlanTaskBtn;
	private Button workorderDelPlanTaskBtn;
	private Button workorderModifyPlanTaskBtn;

	private WorkorderPlanTask planTask;
	private Workorder workorder;

	/*
	 * (非 Javadoc) <p>Title: onCreate</p> <p>Description: </p>
	 * 
	 * @param savedInstanceState
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workorder_plan_task);
		planTask = (WorkorderPlanTask) getIntent().getSerializableExtra(
				"planTask");
		workorder = (Workorder) getIntent().getSerializableExtra("workorder");
		init();
		if (planTask != null) {
			workorderPlanTaskId.setText(planTask.getTaskid());
			workorderPlanTaskDescription.setText(planTask.getDescription());
			workorderPlanTaskContinuousTime.setText(planTask.getEstdur());
			workorderDelPlanTaskBtn.setVisibility(View.VISIBLE);
			workorderModifyPlanTaskBtn.setVisibility(View.VISIBLE);
		} else {
			workorderAddPlanTaskBtn.setVisibility(View.VISIBLE);
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
		workorderPlanTaskDescriptionBtn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO 显示详细描述信息
					}
				});

		workorderPlanReturnBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		workorderAddPlanTaskBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				addPlanTask();
			}
		});

		workorderDelPlanTaskBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				delPlanTask();
			}
		});

		workorderModifyPlanTaskBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				modifyPlanTask();
			}
		});

	}

	/**
	 * @Title: modifyPlanTask
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	protected void modifyPlanTask() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String flag = "ModifyWorkorderPlanTask";
				String className = WorkorderPlanTask.class.getName();
				String pyKeyid = planTask.getWorkorderid();
				String table = "WOACTIVITY";
				String relationShip = "";
				String tableLine = "";
				String tableLineId = "";
				String listValue = assemblyParmas(StringContants.SPLIT, false);
				try {
					Serializable datas = appContext.modify(flag, className,
							table, pyKeyid, relationShip, tableLine,
							tableLineId, listValue);
					Intent intent = new Intent(Actions.WORKORDER_PLAN_BROADCAST);
					intent.putExtra("id", R.id.workorder_plan_task);
					intent.putExtra("operate", R.id.workorder_plan_task_modify);
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
	 * @Title: delPlanTask
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	protected void delPlanTask() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String flag = "DelWorkorderPlanTask";
				String className = WorkorderPlanTask.class.getName();
				String table = "WOACTIVITY";
				String pyKeyid = planTask.getWorkorderid();
				String tableLine = "";
				String tableLineid = "";
				String relationShip = "";
				try {
					Serializable datas = appContext.delete(flag, className,
							table, pyKeyid, tableLine, tableLineid,
							relationShip);
					Intent intent = new Intent(Actions.WORKORDER_PLAN_BROADCAST);
					intent.putExtra("id", R.id.workorder_plan_task);
					intent.putExtra("operate", R.id.workorder_plan_task_del);
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
	 * @Title: addPlanTask
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	protected void addPlanTask() {

		new Thread(new Runnable() {
			@Override
			public void run() {
				String flag = "AddWorkorderPlanTask";
				String className = WorkorderPlanTask.class.getName();
				String table = "WOACTIVITY";
				String appName = "";
				String pyKeyid = "";
				String relationShip = "";
				String listValue = assemblyParmas(StringContants.SPLIT, true);
				try {
					Serializable datas = appContext.add(flag, className, table,
							appName, pyKeyid, relationShip, listValue);
					Intent intent = new Intent(Actions.WORKORDER_PLAN_BROADCAST);
					intent.putExtra("id", R.id.workorder_plan_task);
					intent.putExtra("operate", R.id.workorder_plan_task_add);
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
	 * @Title: initWidget
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void initWidget() {
		workorderPlanTaskId = (EditText) findViewById(R.id.workorder_plan_task_wonum);
		workorderPlanTaskDescription = (EditText) findViewById(R.id.workorder_plan_task_description);
		workorderPlanTaskContinuousTime = (EditText) findViewById(R.id.workorder_plan_task_continuous_time);

		workorderPlanReturnBtn = (Button) findViewById(R.id.workorder_plan_return_btn);
		workorderAddPlanTaskBtn = (Button) findViewById(R.id.workorder_add_plan_task_btn);
		workorderDelPlanTaskBtn = (Button) findViewById(R.id.workorder_del_plan_task_btn);
		workorderModifyPlanTaskBtn = (Button) findViewById(R.id.workorder_modify_plan_task_btn);

		workorderPlanTaskDescriptionBtn = (ImageButton) findViewById(R.id.workorder_plan_task_description_button);
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
		String taskid = workorderPlanTaskId.getText().toString();
		String description = workorderPlanTaskDescription.getText().toString();
		String continuousTime = workorderPlanTaskContinuousTime.getText()
				.toString();
		StringBuffer buffer = new StringBuffer();
		if (tag) {
			buffer.append("WONUM=");
			buffer.append(workorder.getWonum());
			buffer.append(split);
			buffer.append("WORKTYPE=");
			buffer.append(workorder.getWorktype());
			buffer.append(split);
		}
		buffer.append("TASKID=");
		buffer.append(taskid);
		buffer.append(split);
		buffer.append("DESCRIPTION=");
		buffer.append(description);
		buffer.append(split);
		buffer.append("ESTDUR=");
		buffer.append(continuousTime);

		return buffer.toString();
	}
}
