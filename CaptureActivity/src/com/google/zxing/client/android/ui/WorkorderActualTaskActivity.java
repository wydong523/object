/**   
 * @Title: WorkorderActualTask.java 
 * @Package com.google.zxing.client.android.ui 
 * @version V1.0   
 */
package com.google.zxing.client.android.ui;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.zxing.client.android.R;

/**
 * @Desctiption 实际情况 任务详情
 * @author 汪渝栋
 * @date 2013-9-29 下午2:55:59
 */
public class WorkorderActualTaskActivity extends BaseActivity {

	private Button workorderActualTaskReturnBtn;
	private Button workorderAddActualTaskBtn;
	private Button workorderDelActualTaskBtn;
	private Button workorderModifyActualTaskBtn;
	private ImageButton workorderActualTaskDescriptionBtn;
	private EditText workorderActualTaskDescription;
	private EditText workorderActualTaskWonum;
	private EditText workorderActualTaskContinuousTime;

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
		setContentView(R.layout.workorder_actual_task);
		init();
		workorderDelActualTaskBtn.setVisibility(View.VISIBLE);
		workorderModifyActualTaskBtn.setVisibility(View.VISIBLE);
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
		workorderActualTaskReturnBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		workorderAddActualTaskBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 添加实际任务
			}
		});

		workorderDelActualTaskBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 删除实际任务
			}
		});

		workorderModifyActualTaskBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 修改实际任务
			}
		});

		workorderActualTaskDescriptionBtn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO 显示详细描述
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
		workorderActualTaskReturnBtn = (Button) findViewById(R.id.workorder_actual_task_return_btn);
		workorderAddActualTaskBtn = (Button) findViewById(R.id.workorder_add_actual_task_btn);
		workorderDelActualTaskBtn = (Button) findViewById(R.id.workorder_del_actual_task_btn);
		workorderModifyActualTaskBtn = (Button) findViewById(R.id.workorder_modify_actual_task_btn);

		workorderActualTaskDescriptionBtn = (ImageButton) findViewById(R.id.workorder_actual_task_description_button);
		workorderActualTaskDescription = (EditText) findViewById(R.id.workorder_actual_task_description);
		workorderActualTaskWonum = (EditText) findViewById(R.id.workorder_actual_task_wonum);
		workorderActualTaskContinuousTime = (EditText) findViewById(R.id.workorder_actual_task_continuous_time);
	}
}
