/**   
 * @Title: WorkorderActualMaterialActivity.java 
 * @Package com.google.zxing.client.android.ui 
 * @version V1.0   
 */
package com.google.zxing.client.android.ui;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.TreeSet;

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
import com.google.zxing.client.android.exceptions.AuthenticationException;
import com.google.zxing.client.android.exceptions.AuthorizationException;
import com.google.zxing.client.android.exceptions.CommunicationException;
import com.google.zxing.client.android.utils.DialogManager;
import com.google.zxing.client.android.utils.HandlerUtils;
import com.google.zxing.client.android.vo.PersonSelect;
import com.google.zxing.client.android.vo.PersonSelectList;
import com.google.zxing.client.android.vo.ProjectSelect;
import com.google.zxing.client.android.vo.ProjectSelectList;
import com.google.zxing.client.android.vo.WarehouseSelect;
import com.google.zxing.client.android.vo.WarehouseSelectList;

/**
 * @Desctiption 实际情况 物料详情
 * @author 汪渝栋
 * @date 2013-9-29 下午2:59:08
 */
public class WorkorderActualMaterialActivity extends BaseActivity {

	// 项目(需要查找功能)
	private EditText workorderActualMaterialProject;

	// 仓库(需要查找功能)
	private EditText workorderActualMaterialWarehouse;

	private ImageButton workorderActualMaterialProjectBtn;

	private ImageButton workorderActualMaterialWarehouseBtn;

	private Button workorderActualMaterialReturnBtn;

	private Button workorderAddActualMaterialBtn;

	private Button workorderDelActualMaterialBtn;

	private Button workorderModifyActualMaterialBtn;

	private AppContext appContext;

	private Handler materialHanlder;

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
		setContentView(R.layout.workorder_actual_material);
		appContext = (AppContext) getApplicationContext();
		materialHanlder = getMaterialHanlder();
		init();
		workorderDelActualMaterialBtn.setVisibility(View.VISIBLE);
		workorderModifyActualMaterialBtn.setVisibility(View.VISIBLE);
	}

	/**
	 * @Title: getMaterialHanlder
	 * @Description: TODO
	 * @param @return
	 * @return Handler
	 * @throws
	 */
	private Handler getMaterialHanlder() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				TreeSet<String> datas = (TreeSet<String>) msg.obj;
				switch (msg.what) {
				case R.id.actual_project_select:
					showActualProject(datas, "项目");
					break;
				case R.id.actual_warehouse_select:
					showWarehouseProject(datas, "库房");
					break;
				default:
					break;
				}
			}
		};
	}

	protected void showActualProject(TreeSet<String> datas, String title) {
//		dialogManager.show(this, datas, title, workorderActualMaterialProject,
//				workorderActualMaterialWarehouse, R.drawable.dialog_list_bg);
	}

	protected void showWarehouseProject(TreeSet<String> datas, String title) {
//		dialogManager.show(this, datas, title, workorderActualMaterialProject,
//				workorderActualMaterialWarehouse, R.drawable.dialog_list_bg);
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
		workorderActualMaterialReturnBtn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						finish();
					}
				});

		workorderAddActualMaterialBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			}
		});

		workorderDelActualMaterialBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			}
		});

		workorderModifyActualMaterialBtn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
					}
				});

		workorderActualMaterialProjectBtn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						projectPopDialog();
					}
				});

		workorderActualMaterialWarehouseBtn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						warehousePopDialog();
					}
				});
	}

	/**
	 * @Title: warehousePopDialog
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	protected void warehousePopDialog() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				String whereStrParma = "type='STOREROOM'";
				String tableName = "locations";
				WarehouseSelectList wsList;
				ArrayList<WarehouseSelect> warehouses;
				TreeSet<String> datas = new TreeSet<String>();
				try {
					wsList = (WarehouseSelectList) appContext.querySelectValue(
							WarehouseSelect.class.getName(), tableName,
							tableName, whereStrParma,
							"MaterialWarehouseSelectValue");
					if (wsList == null) {
						warehouses = new ArrayList<WarehouseSelect>();
					} else {
						warehouses = wsList.getWarehouses();
					}

					for (WarehouseSelect ws : warehouses) {
						String content = ws.getLocation() + "/"
								+ ws.getDescription();
						datas.add(content);
					}
					HandlerUtils.sendMessage(materialHanlder, datas,
							R.id.actual_warehouse_select);
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
	 * @Title: projectPopDialog
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	protected void projectPopDialog() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				String whereStrParma = "1=1";
				String tableName = "item";
				ProjectSelectList pList;
				ArrayList<ProjectSelect> projects;
				TreeSet<String> datas = new TreeSet<String>();
				try {
					pList = (ProjectSelectList) appContext.querySelectValue(
							ProjectSelect.class.getName(), tableName,
							tableName, whereStrParma,
							"MaterialProjectSelectValue");
					if (pList == null) {
						projects = new ArrayList<ProjectSelect>();
					} else {
						projects = pList.getProjects();
					}

					for (ProjectSelect ps : projects) {
						String content = ps.getItemnum() + "/"
								+ ps.getDescription();
						datas.add(content);
					}
					HandlerUtils.sendMessage(materialHanlder, datas,
							R.id.actual_project_select);
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
		workorderActualMaterialProject = (EditText) findViewById(R.id.workorder_actual_material_project);
		workorderActualMaterialWarehouse = (EditText) findViewById(R.id.workorder_actual_material_warehouse);

		workorderActualMaterialProjectBtn = (ImageButton) findViewById(R.id.workorder_actual_material_project_button);
		workorderActualMaterialWarehouseBtn = (ImageButton) findViewById(R.id.workorder_actual_material_warehouse_button);

		workorderActualMaterialReturnBtn = (Button) findViewById(R.id.workorder_actual_material_return_btn);
		workorderAddActualMaterialBtn = (Button) findViewById(R.id.workorder_add_actual_material_btn);
		workorderDelActualMaterialBtn = (Button) findViewById(R.id.workorder_del_actual_material_btn);
		workorderModifyActualMaterialBtn = (Button) findViewById(R.id.workorder_modify_actual_material_btn);

	}

}
