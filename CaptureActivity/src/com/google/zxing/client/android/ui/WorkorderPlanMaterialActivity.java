/**   
 * @Title: WorkorderPlanMaterialActivity.java 
 * @Package com.google.zxing.client.android.ui 
 * @version V1.0   
 */
package com.google.zxing.client.android.ui;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.TreeSet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.constant.Actions;
import com.google.zxing.client.android.constant.StringContants;
import com.google.zxing.client.android.exceptions.AuthenticationException;
import com.google.zxing.client.android.exceptions.AuthorizationException;
import com.google.zxing.client.android.exceptions.CommunicationException;
import com.google.zxing.client.android.utils.HandlerUtils;
import com.google.zxing.client.android.utils.StringUtils;
import com.google.zxing.client.android.vo.Avgcost;
import com.google.zxing.client.android.vo.LinetypeSelect;
import com.google.zxing.client.android.vo.LinetypeSelectList;
import com.google.zxing.client.android.vo.MeasureunitSelect;
import com.google.zxing.client.android.vo.MeasureunitSelectList;
import com.google.zxing.client.android.vo.SelectDialogEntity;
import com.google.zxing.client.android.vo.ProjectSelect;
import com.google.zxing.client.android.vo.ProjectSelectList;
import com.google.zxing.client.android.vo.SiteSelect;
import com.google.zxing.client.android.vo.SiteSelectList;
import com.google.zxing.client.android.vo.WarehouseSelect;
import com.google.zxing.client.android.vo.WarehouseSelectList;
import com.google.zxing.client.android.vo.Workorder;
import com.google.zxing.client.android.vo.WorkorderPlanMaterial;

/**
 * @Desctiption 工单模块 物料
 * @author 汪渝栋
 * @date 2013-9-26 下午3:09:35
 */
public class WorkorderPlanMaterialActivity extends BaseActivity {
	// 项目(选择&&查询)
	private EditText workorderPlanMaterialProject;

	// 行类型(表)
	private EditText workorderPlanMaterialLineType;

	// 数量
	private EditText workorderPlanMaterialCount;

	// 单位(选择&&查询)
	private EditText workorderPlanMaterialUnit;

	// 单位成本
	private EditText workorderPlanMaterialUnitCost;

	// 行成本(数量+单位成本)
	private EditText workorderPlanMaterialLineCost;

	// 库房(选择&&查询)
	private EditText workorderPlanMaterialWarehouse;

	// 库房地点(选择&&查询)
	private EditText workorderPlanMaterialWarehouseLocation;

	private ImageButton workorderPlanMaterialProjectBtn;
	private ImageButton workorderPlanMaterialLinetypeBtn;
	private ImageButton workorderPlanMaterialUnitBtn;
	private ImageButton workorderPlanMaterialWarehouseBtn;
	private ImageButton workorderPlanMaterialWarehouseLocationBtn;

	private Button workorderPlanMaterialReturnBtn;
	private Button workorderAddPlanMaterialBtn;
	private Button workorderDelPlanMaterialBtn;
	private Button workorderModifyPlanMaterialBtn;

	// 直接发放(checkbox)
	private CheckBox workorderPlanMaterialDirectDistribution;

	private Handler materialHanlder;
	private Handler materialEditHanlder;

	private WorkorderPlanMaterial planMaterial;
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
		setContentView(R.layout.workorder_plan_material);
		planMaterial = (WorkorderPlanMaterial) getIntent()
				.getSerializableExtra("planMaterial");
		workorder = (Workorder) getIntent().getSerializableExtra("workorder");
		init();
	}

	/**
	 * @Title: init
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void init() {
		materialHanlder = getMaterialHanlder();
		materialEditHanlder = getMaterialEditHandler();
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
		workorderPlanMaterialProjectBtn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						projectPopDialog();
					}
				});

		workorderPlanMaterialLinetypeBtn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						linetypePopDialog();
					}
				});
		workorderPlanMaterialUnitBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				unitPopDialog();
			}
		});

		workorderPlanMaterialWarehouseBtn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						warehousePopDialog();
					}
				});

		workorderPlanMaterialWarehouseLocationBtn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						sitePopDialog();
					}
				});

		workorderPlanMaterialReturnBtn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						finish();

					}
				});
		workorderAddPlanMaterialBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				addPlanMaterial();
			}
		});
		workorderDelPlanMaterialBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				delPlanMaterial();

			}
		});
		workorderModifyPlanMaterialBtn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						modifyPlanMaterial();

					}
				});

		workorderPlanMaterialDirectDistribution
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						workorderPlanMaterialDirectDistribution
								.setChecked(true);
					}
				});

		workorderPlanMaterialWarehouse
				.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						if (StringUtils.isEmpty(workorderPlanMaterialCount
								.toString()))
							showError("请填写数量信息！！！");

						new Thread(new Runnable() {
							@Override
							public void run() {
								Avgcost avgcost;
								String flag = "PlanWarehouseFlagGetresult";
								String className = Avgcost.class.getName();
								String table = "invcost";
								String location = workorderPlanMaterialWarehouse
										.getText().toString()
										.split(StringContants.SPLIT_LINE)[0];
								String itemnum = workorderPlanMaterialProject
										.getText().toString()
										.split(StringContants.SPLIT_LINE)[0];
								String listValue = "ITEMNUM=" + itemnum
										+ "&LOCATION=" + location;

								try {
									avgcost = (Avgcost) appContext.getResult(
											flag, className, table, listValue);
									HandlerUtils
											.sendMessage(
													materialEditHanlder,
													avgcost,
													R.id.workorder_plan_material_getresult);
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

					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {

					}

					@Override
					public void afterTextChanged(Editable s) {

					}
				});

	}

	/**
	 * @Title: sitePopDialog
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	protected void sitePopDialog() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				String whereStrParma = "siteid in (select siteid from locations where type='STOREROOM')";
				String tableName = "site";
				SiteSelectList siteList;
				ArrayList<SiteSelect> sites;
				TreeSet<String> datas = new TreeSet<String>();
				try {
					siteList = (SiteSelectList) appContext.querySelectValue(
							SiteSelect.class.getName(), tableName, tableName,
							whereStrParma, "SiteSelectValue");
					if (siteList == null) {
						sites = new ArrayList<SiteSelect>();
					} else {
						sites = siteList.getSites();
					}

					for (SiteSelect ss : sites) {
						String content = ss.getSiteid() + "/"
								+ ss.getDescription();
						datas.add(content);
					}
					HandlerUtils.sendMessage(materialHanlder, datas,
							R.id.fault_site_select);
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
	 * @Title: unitPopDialog
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	protected void unitPopDialog() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				String whereStrParma = "1=1";
				String tableName = "measureunit";
				MeasureunitSelectList unitList;
				ArrayList<MeasureunitSelect> units;
				TreeSet<String> datas = new TreeSet<String>();
				try {
					unitList = (MeasureunitSelectList) appContext
							.querySelectValue(
									MeasureunitSelect.class.getName(),
									tableName, tableName, whereStrParma,
									"MeasureunitSelectValue");
					if (unitList == null) {
						units = new ArrayList<MeasureunitSelect>();
					} else {
						units = unitList.getUnits();
					}

					for (MeasureunitSelect ms : units) {
						String content = ms.getMeasureunitid() + "/"
								+ ms.getAbbreviation();
						datas.add(content);
					}
					HandlerUtils.sendMessage(materialHanlder, datas,
							R.id.fault_measureunit_select);
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
	 * @Title: linetypePopDialog
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	protected void linetypePopDialog() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				String whereStrParma = "domainid='LINETYPE' and maxvalue in ('ITEM','MATERIAL')";
				String tableName = "synonymdomain";
				LinetypeSelectList typeList;
				ArrayList<LinetypeSelect> types;
				TreeSet<String> datas = new TreeSet<String>();
				try {
					typeList = (LinetypeSelectList) appContext
							.querySelectValue(LinetypeSelect.class.getName(),
									tableName, tableName, whereStrParma,
									"LinetypeSelectValue");
					if (typeList == null) {
						types = new ArrayList<LinetypeSelect>();
					} else {
						types = typeList.getTypes();
					}

					for (LinetypeSelect ls : types) {
						String content = ls.getValue() + "/"
								+ ls.getDescription();
						datas.add(content);
					}
					HandlerUtils.sendMessage(materialHanlder, datas,
							R.id.fault_linetype_select);
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
	 * @Title: modifyPlanTask
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	protected void modifyPlanMaterial() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String flag = "ModifyWorkorderPlanTask";
				String className = WorkorderPlanMaterial.class.getName();
				String pyKeyid = workorder.getWorkorderid();
				String table = "WORKORDER";
				String relationShip = "SHOWPLANITEM";
				String tableLine = "wpitem";
				String tableLineId = planMaterial.getWpitemid();
				String listValue = assemblyParmas(StringContants.SPLIT, false);
				try {
					Serializable datas = appContext.modify(flag, className,
							table, pyKeyid, relationShip, tableLine,
							tableLineId, listValue);
					Intent intent = new Intent(Actions.WORKORDER_PLAN_BROADCAST);
					intent.putExtra("id", R.id.workorder_plan_material);
					intent.putExtra("operate",
							R.id.workorder_plan_material_modify);
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
	protected void delPlanMaterial() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String flag = "DelWorkorderPlanTask";
				String className = WorkorderPlanMaterial.class.getName();
				String table = "workorder";
				String pyKeyid = workorder.getWorkorderid();
				String tableLine = "wpitem";
				String tableLineid = planMaterial.getWpitemid();
				;
				String relationShip = "SHOWPLANITEM";
				try {
					Serializable datas = appContext.delete(flag, className,
							table, pyKeyid, tableLine, tableLineid,
							relationShip);
					Intent intent = new Intent(Actions.WORKORDER_PLAN_BROADCAST);
					intent.putExtra("id", R.id.workorder_plan_material);
					intent.putExtra("operate", R.id.workorder_plan_material_del);
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
	protected void addPlanMaterial() {

		new Thread(new Runnable() {
			@Override
			public void run() {
				String flag = "AddWorkorderPlanMaterial";
				String className = WorkorderPlanMaterial.class.getName();
				String table = "WORKORDER";
				String appName = "";
				String pyKeyid = workorder.getWorkorderid();
				String relationShip = "SHOWPLANITEM";
				String listValue = assemblyParmas(StringContants.SPLIT, true);
				try {
					Serializable datas = appContext.add(flag, className, table,
							appName, pyKeyid, relationShip, listValue);
					Intent intent = new Intent(Actions.WORKORDER_PLAN_BROADCAST);
					intent.putExtra("id", R.id.workorder_plan_material);
					intent.putExtra("operate", R.id.workorder_plan_material_add);
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
							tableName, whereStrParma, "ProjectSelectValue");
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
							R.id.fault_warehouse_select);
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
		workorderPlanMaterialProject = (EditText) findViewById(R.id.workorder_plan_material_project);
		workorderPlanMaterialLineType = (EditText) findViewById(R.id.workorder_plan_material_line_type);
		workorderPlanMaterialCount = (EditText) findViewById(R.id.workorder_plan_material_count);
		workorderPlanMaterialUnit = (EditText) findViewById(R.id.workorder_plan_material_unit);
		workorderPlanMaterialUnitCost = (EditText) findViewById(R.id.workorder_plan_material_unit_cost);
		workorderPlanMaterialLineCost = (EditText) findViewById(R.id.workorder_plan_material_line_cost);
		workorderPlanMaterialWarehouse = (EditText) findViewById(R.id.workorder_plan_material_warehouse);
		workorderPlanMaterialWarehouseLocation = (EditText) findViewById(R.id.workorder_plan_material_warehouse_location);

		workorderPlanMaterialProjectBtn = (ImageButton) findViewById(R.id.workorder_plan_material_project_btn);
		workorderPlanMaterialLinetypeBtn = (ImageButton) findViewById(R.id.workorder_plan_material_line_type_btn);
		workorderPlanMaterialUnitBtn = (ImageButton) findViewById(R.id.workorder_plan_material_unit_btn);
		workorderPlanMaterialWarehouseBtn = (ImageButton) findViewById(R.id.workorder_plan_material_warehouse_btn);
		workorderPlanMaterialWarehouseLocationBtn = (ImageButton) findViewById(R.id.workorder_plan_material_warehouse_location_btn);

		workorderPlanMaterialReturnBtn = (Button) findViewById(R.id.workorder_plan_material_return_btn);
		workorderAddPlanMaterialBtn = (Button) findViewById(R.id.workorder_add_plan_material_btn);
		workorderDelPlanMaterialBtn = (Button) findViewById(R.id.workorder_del_plan_material_btn);
		workorderModifyPlanMaterialBtn = (Button) findViewById(R.id.workorder_modify_plan_material_btn);

		workorderPlanMaterialDirectDistribution = (CheckBox) findViewById(R.id.workorder_plan_material_direct_distribution);

		if (planMaterial != null) {
			workorderPlanMaterialProject.setText(planMaterial.getItemnum());
			workorderPlanMaterialLineType.setText(planMaterial.getLinetype());
			workorderPlanMaterialCount.setText(planMaterial.getItemqty());
			workorderPlanMaterialUnit.setText(planMaterial.getOrderunit());
			workorderPlanMaterialUnitCost.setText(planMaterial.getUnitcost());
			workorderPlanMaterialLineCost.setText(planMaterial.getLinecost());
			workorderPlanMaterialWarehouse.setText(planMaterial.getLocation());
			workorderPlanMaterialWarehouseLocation.setText(planMaterial
					.getStorelocsite());
			workorderDelPlanMaterialBtn.setVisibility(View.VISIBLE);
			workorderModifyPlanMaterialBtn.setVisibility(View.VISIBLE);
		} else {
			workorderAddPlanMaterialBtn.setVisibility(View.VISIBLE);
		}
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
		String itemnum = workorderPlanMaterialProject.getText().toString()
				.split(StringContants.SPLIT_LINE)[0];
		String linetype = workorderPlanMaterialLineType.getText().toString()
				.split(StringContants.SPLIT_LINE)[0];
		String itemqty = workorderPlanMaterialCount.getText().toString();
		String orderunit = workorderPlanMaterialUnit.getText().toString()
				.split(StringContants.SPLIT_LINE)[0];
		String unitcost = workorderPlanMaterialUnitCost.getText().toString();
		String linecost = workorderPlanMaterialLineCost.getText().toString();
		String location = workorderPlanMaterialWarehouse.getText().toString()
				.split(StringContants.SPLIT_LINE)[0];
		String storelocsite = workorderPlanMaterialWarehouseLocation.getText()
				.toString().split(StringContants.SPLIT_LINE)[0];

		StringBuffer buffer = new StringBuffer();
		buffer.append("ITEMNUM=");
		buffer.append(itemnum);
		buffer.append(split);
		buffer.append("LINETYPE=");
		buffer.append(linetype);
		buffer.append(split);
		buffer.append("ITEMQTY=");
		buffer.append(itemqty);
		buffer.append(split);
		buffer.append("ORDERUNIT=");
		buffer.append(orderunit);
		buffer.append(split);
		buffer.append("UNITCOST=");
		buffer.append(unitcost);
		buffer.append(split);
		buffer.append("LINECOST=");
		buffer.append(linecost);
		buffer.append(split);
		buffer.append("LOCATION=");
		buffer.append(location);
		buffer.append(split);
		buffer.append("STORELOCSITE=");
		buffer.append(storelocsite);
		if (tag == true) {
			buffer.append(split);
			buffer.append("DIRECTREQ=0");
		}
		return buffer.toString();
	}

	private Handler getMaterialEditHandler() {

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
				Avgcost avgcost = (Avgcost) msg.obj;
				switch (msg.what) {
				case R.id.workorder_plan_material_getresult:// 根据库房修改单位成本和行成本
					if (avgcost != null) {
						String cost = avgcost.getAvgcost();
						workorderPlanMaterialUnitCost.setText(cost);
						String materialCount = workorderPlanMaterialCount
								.getText().toString();
						double cost1 = Double.valueOf(cost);
						double count = Double.valueOf(materialCount);
						double result = cost1 * count;
						workorderPlanMaterialLineCost.setText(String
								.valueOf(result));
					}
					break;
				default:
					break;
				}

			}
		};
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
				ArrayList<String> cum = new ArrayList<String>();
				String tableName;
				TreeSet<String> datas = (TreeSet<String>) msg.obj;
				switch (msg.what) {
				case R.id.actual_project_select:
					cum.add("DESCRIPTION=");
					cum.add("ITEMNUM=");
					cum.add("WHERESTR=");
					tableName = "item";
					showSelectList(datas, "项目", workorderPlanMaterialProject,
							workorderPlanMaterialLineType, cum, tableName);
					break;
				case R.id.fault_linetype_select:
					cum.add("DESCRIPTION=");
					cum.add("VALUE=");
					cum.add("WHERESTR=");
					tableName = "synonymdomain";
					showSelectList(datas, "行类型", workorderPlanMaterialLineType,
							workorderPlanMaterialCount, cum, tableName);
					break;
				case R.id.fault_measureunit_select:
					cum.add("ABBREVIATION=");
					cum.add("MEASUREUNITID=");
					cum.add("WHERESTR=");
					tableName = "measureunit";
					showSelectList(datas, "单位", workorderPlanMaterialUnit,
							workorderPlanMaterialUnitCost, cum, tableName);
					break;
				case R.id.fault_warehouse_select:
					cum.add("DESCRIPTION=");
					cum.add("LOCATION=");
					cum.add("WHERESTR=");
					tableName = "locations";
					showSelectList(datas, "库房", workorderPlanMaterialWarehouse,
							workorderPlanMaterialWarehouseLocation, cum,
							tableName);
					break;
				case R.id.fault_site_select:
					cum.add("DESCRIPTION=");
					cum.add("SITEID=");
					cum.add("WHERESTR=");
					tableName = "site";
					showSelectList(datas, "库房地点",
							workorderPlanMaterialWarehouseLocation, null, cum,
							tableName);
					break;
				default:
					break;
				}
			}
		};
	}

	/**
	 * @Title: showSelectList
	 * @Description: TODO
	 * @param @param datas
	 * @param @param string
	 * @param @param workorderPlanMaterialProject2
	 * @param @param workorderPlanMaterialLineType2
	 * @return void
	 * @throws
	 */
	protected void showSelectList(TreeSet<String> datas, String title,
			EditText edit, EditText focus, ArrayList<String> cum,
			String tableName) {
		SelectDialogEntity dialogEntity = new SelectDialogEntity();
		dialogEntity.setContext(this);
		dialogEntity.setContents(datas);
		dialogEntity.setAppContext(appContext);
		dialogEntity.setTitle(title);
		dialogEntity.setEdit(edit);
		dialogEntity.setGetFocusView(focus);
		dialogEntity.setTableName(tableName);
		show(dialogEntity, R.drawable.dialog_list_bg, cum);
	}

}
