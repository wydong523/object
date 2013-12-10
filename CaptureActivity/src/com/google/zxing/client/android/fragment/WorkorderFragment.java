/**   
 * @Title: WorkorderFragment.java 
 * @Package com.google.zxing.client.android 
 * @version V1.0   
 */
package com.google.zxing.client.android.fragment;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.adapter.WorkOrderPlanTaskAdapter;
import com.google.zxing.client.android.adapter.WorkorderPlanMaterialAdapter;
import com.google.zxing.client.android.adapter.WorkorderPlanWplaborAdapter;
import com.google.zxing.client.android.app.AppContext;
import com.google.zxing.client.android.constant.Actions;
import com.google.zxing.client.android.constant.AuthConstants;
import com.google.zxing.client.android.constant.StringContants;
import com.google.zxing.client.android.exceptions.AuthenticationException;
import com.google.zxing.client.android.exceptions.AuthorizationException;
import com.google.zxing.client.android.exceptions.CommunicationException;
import com.google.zxing.client.android.ui.WorkorderActualFaultActivity;
import com.google.zxing.client.android.ui.WorkorderActualMaterialActivity;
import com.google.zxing.client.android.ui.WorkorderActualPersonnelActivity;
import com.google.zxing.client.android.ui.WorkorderActualTaskActivity;
import com.google.zxing.client.android.ui.WorkorderPlanMaterialActivity;
import com.google.zxing.client.android.ui.WorkorderPlanPersonnelActivity;
import com.google.zxing.client.android.ui.WorkorderPlanTaskActivity;
import com.google.zxing.client.android.utils.DialogManager;
import com.google.zxing.client.android.utils.HandlerUtils;
import com.google.zxing.client.android.utils.StringUtils;
import com.google.zxing.client.android.view.calendar.CaldroidFragment;
import com.google.zxing.client.android.view.calendar.CaldroidListener;
import com.google.zxing.client.android.view.dialog.SelectPopDialog;
import com.google.zxing.client.android.view.pulllist.XListView;
import com.google.zxing.client.android.vo.AssetSelect;
import com.google.zxing.client.android.vo.AssetSelectList;
import com.google.zxing.client.android.vo.Cache;
import com.google.zxing.client.android.vo.ClassstructureSelect;
import com.google.zxing.client.android.vo.ClassstructureSelectList;
import com.google.zxing.client.android.vo.FailurecodeSelect;
import com.google.zxing.client.android.vo.FailurecodeSelectList;
import com.google.zxing.client.android.vo.HomeworkPlanSelect;
import com.google.zxing.client.android.vo.HomeworkPlanSelectList;
import com.google.zxing.client.android.vo.LocationsSelect;
import com.google.zxing.client.android.vo.LocationsSelectList;
import com.google.zxing.client.android.vo.PMSelect;
import com.google.zxing.client.android.vo.PMSelectList;
import com.google.zxing.client.android.vo.PersonSelect;
import com.google.zxing.client.android.vo.PersonSelectList;
import com.google.zxing.client.android.vo.SelectDialogEntity;
import com.google.zxing.client.android.vo.SafetyplanSelect;
import com.google.zxing.client.android.vo.SafetyplanSelectList;
import com.google.zxing.client.android.vo.Workorder;
import com.google.zxing.client.android.vo.WorkorderPlanMaterial;
import com.google.zxing.client.android.vo.WorkorderPlanMaterialList;
import com.google.zxing.client.android.vo.WorkorderPlanTask;
import com.google.zxing.client.android.vo.WorkorderPlanTaskList;
import com.google.zxing.client.android.vo.WorkorderPlanWplabor;
import com.google.zxing.client.android.vo.WorkorderPlanWplaborList;
import com.google.zxing.client.android.vo.WorktypeSelect;
import com.google.zxing.client.android.vo.WorktypeSelectList;

import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * @Desctiption 工单功能操作界面
 * @author 汪渝栋
 * @date 2013-9-23 上午9:20:51
 */
public final class WorkorderFragment extends Fragment {

	/**
	 * 需要加载布局的唯一标识
	 */
	private int layoutid;

	/**
	 * 对需要添加操作的模块进行定位的唯一标识
	 */
	private int addId = R.id.workorder_plan_task_query;
	private int planTaskPageindex;
	private int clickpostion = -1;
	private int totalTagCount;// 所有标签页个数
	private static int curTagCount;// 当前标签页个数

	private RelativeLayout workWonumLayout;

	private EditText workWonum;
	private EditText workDescription;
	private EditText workStatus;
	private EditText workStatusdate;
	private EditText workWorktype;
	private EditText workLocation;
	private EditText workAsset;
	private EditText workCategory;
	private EditText workSite;
	private EditText workFaultCategory;
	private EditText workfaultSubclass;
	private EditText workQuestionCode;
	private EditText workHomeworkPlan;
	private EditText workPreventionMaintenance;
	private EditText workSecurityPlan;
	private EditText workPriority;
	private EditText workPm;
	private EditText workReportPerson;
	private EditText workResponsibilityPerson;
	private EditText workChargePerson;
	private EditText workReportTime;
	private EditText workImpactPerson;
	private EditText workPhone;

	private Button addBtn;
	private Button modifyBtn;
	private Button delBtn;
	private static Button workorderAddBtn;// 工单主页添加按钮用于任务、人工、物料、故障公用

	// private ImageButton statusBtn;
	private ImageButton statusdateBtn;
	private ImageButton worktypeBtn;
	private ImageButton workLocationBtn;
	private ImageButton workAssetBtn;
	private ImageButton workCategoryBtn;
	private ImageButton workSiteBtn;
	private ImageButton workFaultCategoryBtn;
	private ImageButton workfaultSubclassBtn;
	private ImageButton workQuestionCodeBtn;
	private ImageButton workHomeworkPlanBtn;
	private ImageButton workSecurityPlanBtn;
	private ImageButton workPmBtn;
	private ImageButton workResponsibilityPersonBtn;
	private ImageButton workChargePersonBtn;

	private XListView workPlanTaskList;
	private XListView workPlanPersonnelList;
	private XListView workPlanMaterialList;
	private ListView workActualSituationTaskList;
	private ListView workActualSituationPersonnelList;
	private ListView workActualSituationMaterialList;
	private ListView workActualSituationFaultList;

	private WorkOrderPlanTaskAdapter workOrderPlanTaskAdapter;
	private WorkorderPlanWplaborAdapter workorderPlanWplaborAdapter;
	private WorkorderPlanMaterialAdapter workorderPlanMaterialAdapter;

	private RadioButton workPlanTaskBtn;
	private RadioButton workPlanPersonnelBtn;
	private RadioButton workPlanMaterialBtn;
	private RadioButton workActualSituationTaskBtn;
	private RadioButton workActualSituationPersonnelBtn;
	private RadioButton workActualSituationMaterialBtn;
	private RadioButton workorderActualSituationFaultBtn;

	private LinearLayout detailLayout;
	private LinearLayout planLayout;
	private LinearLayout actualSituationLayout;

	private ArrayList<WorkorderPlanTask> planTasks;
	private ArrayList<WorkorderPlanWplabor> planWplabors;
	private ArrayList<WorkorderPlanMaterial> planMaterials;

	private static Handler detailHandler;
	// private static Handler planHandler;
	private static Handler planTaskHandler;
	private static Handler planPersonnelHandler;
	private static Handler planMaterialHandler;
	private static Handler errorHandler;

	private WorkorderPlanBroadCast planBroadCast;

	private AppContext appContext;
	private static final DialogManager dialogManager = new DialogManager();

	private CaldroidFragment dialogCaldroidFragment;
	private FragmentManager fm;
	private SimpleDateFormat formatter;
	private TreeSet<String> auths;
	private Workorder workorder;
	private Activity workorderActivity;
	private static String userSite;// 用户站点

	public static WorkorderFragment newInstance(int layoutId, int count,
			Button addBtn, Workorder workorder, AppContext appContext,
			Activity workorderActivity, FragmentManager fm) {
		workorderAddBtn = addBtn;
		if (StringUtils.isEmpty(userSite)) {
			userSite = appContext.queryUserSite(workorderActivity);
		}
		WorkorderFragment fragment = new WorkorderFragment();
		fragment.layoutid = layoutId;
		fragment.workorder = workorder;
		fragment.appContext = appContext;
		fragment.workorderActivity = workorderActivity;
		fragment.fm = fm;
		fragment.totalTagCount = count;
		curTagCount = count;
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		initBroadcast();
		View view = null;
		LinearLayout layout = null;
		switch (layoutid) {
		case R.layout.workoder_detail:
			view = inflater.inflate(R.layout.workoder_detail, null);
			detailLayout = new LinearLayout(getActivity());
			detailLayout.setLayoutParams(new LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			detailLayout.addView(view);
			layout = detailLayout;
			initDetail();
			break;
		case R.layout.workoder_plan:
			view = inflater.inflate(R.layout.workoder_plan, null);
			planLayout = new LinearLayout(getActivity());
			planLayout.setLayoutParams(new LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			planLayout.addView(view);
			layout = planLayout;
			if (workorder != null) {
				initPlan();
			}
			break;
		case R.layout.workorder_actual_situation:
			view = inflater.inflate(R.layout.workorder_actual_situation, null);
			actualSituationLayout = new LinearLayout(getActivity());
			actualSituationLayout.setLayoutParams(new LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			actualSituationLayout.addView(view);
			layout = actualSituationLayout;
			initActualSituation();
			break;
		default:
			break;
		}
		return layout;
	}

	/**
	 * @Title: initActualSituation
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void initActualSituation() {
		initActualSituationWidget();
		initActualSituationEvent();
	}

	/**
	 * @Title: initActualSituationEvent
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void initActualSituationEvent() {
		workActualSituationTaskList
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Intent intent = new Intent(getActivity(),
								WorkorderActualTaskActivity.class);
						startActivity(intent);
					}
				});

		workActualSituationPersonnelList
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Intent intent = new Intent(getActivity(),
								WorkorderActualPersonnelActivity.class);
						startActivity(intent);
					}
				});

		workActualSituationMaterialList
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Intent intent = new Intent(getActivity(),
								WorkorderActualMaterialActivity.class);
						startActivity(intent);
					}
				});

		workActualSituationFaultList
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Intent intent = new Intent(getActivity(),
								WorkorderActualFaultActivity.class);
						startActivity(intent);
					}
				});

		workActualSituationTaskBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				workActualSituationTaskList.setVisibility(View.VISIBLE);
				workActualSituationPersonnelList.setVisibility(View.GONE);
				workActualSituationMaterialList.setVisibility(View.GONE);
				workActualSituationFaultList.setVisibility(View.GONE);
				workActualSituationTaskBtn.setChecked(true);
				workActualSituationPersonnelBtn.setChecked(false);
				workActualSituationMaterialBtn.setChecked(false);
				workorderActualSituationFaultBtn.setChecked(false);
			}
		});

		workActualSituationPersonnelBtn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						workActualSituationTaskList.setVisibility(View.GONE);
						workActualSituationPersonnelList
								.setVisibility(View.VISIBLE);
						workActualSituationMaterialList
								.setVisibility(View.GONE);
						workActualSituationFaultList.setVisibility(View.GONE);
						workActualSituationTaskBtn.setChecked(false);
						workActualSituationPersonnelBtn.setChecked(true);
						workActualSituationMaterialBtn.setChecked(false);
						workorderActualSituationFaultBtn.setChecked(false);

						ArrayList<String> actualSituationPersonnelList = new ArrayList<String>();
						// TODO 添加假数据，等接口调通后添加真实数据
						for (int i = 0; i < 5; i++) {
							actualSituationPersonnelList.add("你好，你有一条实际人员信息！");
						}
						// workActualSituationPersonnelList
						// .setAdapter(new WorkOrderPlanTaskAdapter(
						// getActivity(),
						// actualSituationPersonnelList,
						// R.layout.pr_listitem));

					}
				});

		workActualSituationMaterialBtn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						workActualSituationTaskList.setVisibility(View.GONE);
						workActualSituationPersonnelList
								.setVisibility(View.GONE);
						workActualSituationMaterialList
								.setVisibility(View.VISIBLE);
						workActualSituationFaultList.setVisibility(View.GONE);
						workActualSituationTaskBtn.setChecked(false);
						workActualSituationPersonnelBtn.setChecked(false);
						workActualSituationMaterialBtn.setChecked(true);
						workorderActualSituationFaultBtn.setChecked(false);

						ArrayList<String> actualSituationMaterialList = new ArrayList<String>();
						// TODO 添加假数据，等接口调通后添加真实数据
						for (int i = 0; i < 5; i++) {
							actualSituationMaterialList.add("你好，你有一条实际物料信息！");
						}
						// workActualSituationMaterialList
						// .setAdapter(new WorkOrderPlanTaskAdapter(
						// getActivity(),
						// actualSituationMaterialList,
						// R.layout.pr_listitem));

					}
				});

		workorderActualSituationFaultBtn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						workActualSituationTaskList.setVisibility(View.GONE);
						workActualSituationPersonnelList
								.setVisibility(View.GONE);
						workActualSituationMaterialList
								.setVisibility(View.GONE);
						workActualSituationFaultList
								.setVisibility(View.VISIBLE);
						workActualSituationTaskBtn.setChecked(false);
						workActualSituationPersonnelBtn.setChecked(false);
						workActualSituationMaterialBtn.setChecked(false);
						workorderActualSituationFaultBtn.setChecked(true);

						ArrayList<String> actualSituationFaultList = new ArrayList<String>();
						// TODO 添加假数据，等接口调通后添加真实数据
						for (int i = 0; i < 5; i++) {
							actualSituationFaultList.add("你好，你有一条实际故障信息！");
						}
						// workActualSituationFaultList
						// .setAdapter(new WorkOrderPlanTaskAdapter(
						// getActivity(),
						// actualSituationFaultList,
						// R.layout.pr_listitem));
					}
				});
	}

	/**
	 * @Title: initActualSituationWidget
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void initActualSituationWidget() {

		workActualSituationTaskList = (ListView) actualSituationLayout
				.findViewById(R.id.workorder_actual_situation_task_list);

		ArrayList<String> actualSituationTaskList = new ArrayList<String>();
		// TODO 添加假数据，等接口调通后添加真实数据
		for (int i = 0; i < 5; i++) {
			actualSituationTaskList.add("你好，你有一条实际任务信息！");
		}
		// workActualSituationTaskList.setAdapter(new WorkOrderPlanTaskAdapter(
		// getActivity(), actualSituationTaskList, R.layout.pr_listitem));

		workActualSituationPersonnelList = (ListView) actualSituationLayout
				.findViewById(R.id.workorder_actual_situation_personnel_list);
		workActualSituationMaterialList = (ListView) actualSituationLayout
				.findViewById(R.id.workorder_actual_situation_material_list);
		workActualSituationFaultList = (ListView) actualSituationLayout
				.findViewById(R.id.workorder_actual_situation_fault_list);

		workActualSituationTaskBtn = (RadioButton) actualSituationLayout
				.findViewById(R.id.workorder_actual_situation_task_btn);
		workActualSituationPersonnelBtn = (RadioButton) actualSituationLayout
				.findViewById(R.id.workorder_actual_situation_personnel_btn);
		workActualSituationMaterialBtn = (RadioButton) actualSituationLayout
				.findViewById(R.id.workorder_actual_situation_material_btn);
		workorderActualSituationFaultBtn = (RadioButton) actualSituationLayout
				.findViewById(R.id.workorder_actual_situation_fault_btn);
		workActualSituationTaskBtn.setChecked(true);
		workActualSituationPersonnelBtn.setChecked(false);
		workActualSituationMaterialBtn.setChecked(false);
		workorderActualSituationFaultBtn.setChecked(false);
	}

	/**
	 * @Title: initPlan
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void initPlan() {
		initPlanWeigit();
		initPlanEvent();
	}

	/**
	 * @Title: initEvent
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void initPlanEvent() {
		workorderAddBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				switchJumpActivity();
			}
		});

		workPlanTaskBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 任务业务操作
				workPlanPersonnelBtn.setChecked(false);
				workPlanMaterialBtn.setChecked(false);
				workPlanMaterialList.setVisibility(View.GONE);
				workPlanPersonnelList.setVisibility(View.GONE);
				workPlanTaskList.setVisibility(View.VISIBLE);
				addId = R.id.workorder_plan_task_query;
			}
		});

		workPlanTaskList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				clickpostion = position - 1;
				jumpToWorkorderPlanTaskActivity(position);
			}
		});

		workPlanPersonnelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO 人员业务操作
				workPlanTaskBtn.setChecked(false);
				workPlanMaterialBtn.setChecked(false);
				workPlanTaskList.setVisibility(View.GONE);
				workPlanMaterialList.setVisibility(View.GONE);
				workPlanPersonnelList.setVisibility(View.VISIBLE);
				addId = R.id.workorder_plan_wplabor_query;
				queryList(R.id.workorder_plan_wplabor_query);
			}
		});

		workPlanPersonnelList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				clickpostion = position - 1;
				jumpToWorkorderPlanPersonnelActivity(position);
			}
		});

		workPlanMaterialBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 物料业务操作
				workPlanTaskBtn.setChecked(false);
				workPlanPersonnelBtn.setChecked(false);
				workPlanTaskList.setVisibility(View.GONE);
				workPlanPersonnelList.setVisibility(View.GONE);
				workPlanMaterialList.setVisibility(View.VISIBLE);
				addId = R.id.workorder_plan_material_query;
				queryList(R.id.workorder_plan_material_query);
			}
		});

		workPlanMaterialList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				clickpostion = position - 1;
				jumpToWorkorderPlanMaterialActivity(position);
			}
		});
	}

	/**
	 * @Title: switchJumpActivity
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	protected void switchJumpActivity() {
		// (注:在线操作)生成任务编号时使用wonum后台服务器进行关联查询
		Intent intent = null;
		switch (addId) {
		case R.id.workorder_plan_task_query:
			intent = new Intent(getActivity(), WorkorderPlanTaskActivity.class);
			intent.putExtra("workorder", workorder);
			startActivity(intent);
			break;
		case R.id.workorder_plan_wplabor_query:
			intent = new Intent(getActivity(),
					WorkorderPlanPersonnelActivity.class);
			intent.putExtra("workorder", workorder);
			startActivity(intent);
			break;
		case R.id.workorder_plan_material_query:
			intent = new Intent(getActivity(),
					WorkorderPlanMaterialActivity.class);
			intent.putExtra("workorder", workorder);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	/**
	 * @Title: queryList
	 * @Description: 查询工单计划列表通用
	 * @param @param workorderPlanWplabor
	 * @return void
	 * @throws
	 */
	protected void queryList(final int id) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Serializable list = getList(id);
					if (id == R.id.workorder_plan_material_query) {
						HandlerUtils.sendMessage(planMaterialHandler, list, id);
					} else {
						HandlerUtils
								.sendMessage(planPersonnelHandler, list, id);
					}
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
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @throws MalformedURLException
	 * @Title: getList
	 * @Description: TODO
	 * @param @param id
	 * @param @return
	 * @return Serializable
	 * @throws
	 */
	protected Serializable getList(int id) throws MalformedURLException,
			CommunicationException, AuthorizationException,
			AuthenticationException {
		String wonum = workorder.getWonum();
		Serializable list = null;
		switch (id) {
		case R.id.workorder_plan_material_query:
			list = appContext.queryList("PlanMaterialList",
					WorkorderPlanMaterial.class.getName(), "", "wpmaterial",
					"wonum='" + wonum + "'", 1);
			break;
		case R.id.workorder_plan_wplabor_query:
			list = appContext.queryList("PlanWplaborList",
					WorkorderPlanWplabor.class.getName(), "", "wplabor",
					"wonum='" + wonum + "'", 1);
			break;
		default:
			break;
		}
		return list;
	}

	/**
	 * @Title: jumpToWorkorderPlanMaterialActivity
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	protected void jumpToWorkorderPlanMaterialActivity(int position) {
		Intent intent = new Intent(getActivity(),
				WorkorderPlanMaterialActivity.class);
		WorkorderPlanMaterial planMaterial = planMaterials.get(position - 1);
		intent.putExtra("planMaterial", planMaterial);
		intent.putExtra("workorder", workorder);
		startActivity(intent);
	}

	/**
	 * @Title: jumpToWorkorderPlanPersonnelActivity
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	protected void jumpToWorkorderPlanPersonnelActivity(int position) {
		Intent intent = new Intent(getActivity(),
				WorkorderPlanPersonnelActivity.class);
		WorkorderPlanWplabor planWplabor = planWplabors.get(position - 1);
		intent.putExtra("planWplabor", planWplabor);
		intent.putExtra("workorder", workorder);
		startActivity(intent);

	}

	/**
	 * @Title: jumpToWorkorderPlanTaskActivity
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	protected void jumpToWorkorderPlanTaskActivity(int position) {
		Intent intent = new Intent(getActivity(),
				WorkorderPlanTaskActivity.class);
		WorkorderPlanTask planTask = planTasks.get(position - 1);
		intent.putExtra("planTask", planTask);
		intent.putExtra("workorder", workorder);
		startActivity(intent);
	}

	/**
	 * @Title: initPlanWeigit
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void initPlanWeigit() {
		// planHandler = getPlanHandler();
		planTaskHandler = getPlanTaskHandler();
		planPersonnelHandler = getPlanPersonnelHandler();
		planMaterialHandler = getPlanMaterialHandler();
		workPlanTaskList = (XListView) planLayout
				.findViewById(R.id.workorder_plan_task_list);
		queryPlanTaskList();

		workPlanPersonnelList = (XListView) planLayout
				.findViewById(R.id.workorder_plan_personnel_list);
		workPlanMaterialList = (XListView) planLayout
				.findViewById(R.id.workorder_plan_material_list);

		workPlanTaskBtn = (RadioButton) planLayout
				.findViewById(R.id.workorder_plan_task_btn);
		workPlanTaskBtn.setChecked(true);
		workPlanPersonnelBtn = (RadioButton) planLayout
				.findViewById(R.id.workorder_plan_personnel_btn);
		workPlanMaterialBtn = (RadioButton) planLayout
				.findViewById(R.id.workorder_plan_material_btn);

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
			/*
			 * (非 Javadoc) <p>Title: handleMessage</p> <p>Description: </p>
			 * 
			 * @param msg
			 * 
			 * @see android.os.Handler#handleMessage(android.os.Message)
			 */
			@Override
			public void handleMessage(Message msg) {
				Serializable obj = (Serializable) msg.obj;
				WorkorderPlanWplaborList wplaborList = null;
				WorkorderPlanWplabor wplabor = null;
				switch (msg.what) {
				case R.id.workorder_plan_wplabor_query:
					wplaborList = (WorkorderPlanWplaborList) obj;
					if (wplaborList != null) {
						planWplabors = wplaborList.getWplabors();
					} else {
						planWplabors = new ArrayList<WorkorderPlanWplabor>();
					}
					workorderPlanWplaborAdapter = new WorkorderPlanWplaborAdapter(
							getActivity(), planWplabors, R.layout.pr_listitem);
					workPlanPersonnelList
							.setAdapter(workorderPlanWplaborAdapter);
					break;
				case R.id.workorder_plan_wplabor_add:
					wplaborList = (WorkorderPlanWplaborList) msg.obj;
					if (wplaborList != null) {
						wplabor = wplaborList.getWplabors().get(0);
						planWplabors.add(0, wplabor);
						workorderPlanWplaborAdapter.notifyDataSetChanged();
						curTagCount = totalTagCount;
						showInfo("添加人员信息成功!!!");
					} else {
						showError("添加人员信息失败!!!");
					}
					break;
				case R.id.workorder_plan_wplabor_del:
					String result = (String) msg.obj;
					if ("true".equalsIgnoreCase(result)) {
						showInfo("删除人员信息成功!!!");
						planWplabors.remove(clickpostion);
						workorderPlanWplaborAdapter.notifyDataSetChanged();
						curTagCount = totalTagCount;
					} else {
						showError(result);
					}
					break;
				case R.id.workorder_plan_wplabor_modify:
					wplaborList = (WorkorderPlanWplaborList) msg.obj;
					if (wplaborList != null) {
						wplabor = wplaborList.getWplabors().get(0);
						showInfo("修改人员信息成功!!!");
						planWplabors.remove(clickpostion);
						planWplabors.add(clickpostion, wplabor);
						workorderPlanWplaborAdapter.notifyDataSetChanged();
						curTagCount = totalTagCount;
					} else {
						showError("修改人员信息失败!!!");
					}

					break;
				default:
					break;
				}
			}
		};
	}

	/**
	 * 
	 * @Title: getPlanMaterialHandler
	 * @Description: TODO
	 * @param @return
	 * @return Handler
	 * @throws
	 */
	private Handler getPlanMaterialHandler() {
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
				Serializable obj = (Serializable) msg.obj;
				WorkorderPlanMaterialList materialList = null;
				WorkorderPlanMaterial material = null;
				switch (msg.what) {
				case R.id.workorder_plan_material_query:
					materialList = (WorkorderPlanMaterialList) obj;
					if (materialList != null) {
						planMaterials = materialList.getMaterials();
					} else {
						planMaterials = new ArrayList<WorkorderPlanMaterial>();
					}
					workorderPlanMaterialAdapter = new WorkorderPlanMaterialAdapter(
							getActivity(), planMaterials, R.layout.pr_listitem);
					workPlanMaterialList
							.setAdapter(workorderPlanMaterialAdapter);
					break;
				case R.id.workorder_plan_material_add:
					materialList = (WorkorderPlanMaterialList) msg.obj;
					if (materialList != null) {
						material = materialList.getMaterials().get(0);
						planMaterials.add(0, material);
						workorderPlanMaterialAdapter.notifyDataSetChanged();
						curTagCount = totalTagCount;
						showInfo("添加物料信息成功!!!");
					} else {
						showError("添加物料信息失败!!!");
					}
					break;
				case R.id.workorder_plan_material_del:
					String result = (String) msg.obj;
					if ("true".equalsIgnoreCase(result)) {
						showInfo("删除物料信息成功!!!");
						planMaterials.remove(clickpostion);
						workorderPlanMaterialAdapter.notifyDataSetChanged();
						curTagCount = totalTagCount;
					} else {
						showError(result);
					}
					break;
				case R.id.workorder_plan_material_modify:
					materialList = (WorkorderPlanMaterialList) msg.obj;
					if (materialList != null) {
						material = materialList.getMaterials().get(0);
						showInfo("修改物料信息成功!!!");
						planMaterials.remove(clickpostion);
						planMaterials.add(clickpostion, material);
						workorderPlanMaterialAdapter.notifyDataSetChanged();
						curTagCount = totalTagCount;
					} else {
						showError("修改物料信息失败!!!");
					}

					break;
				default:
					break;
				}
			}
		};
	}

	/**
	 * @Title: queryPlanTaskList
	 * @Description: TODO
	 * @param @return
	 * @return WorkorderPlanTaskList
	 * @throws
	 */
	private void queryPlanTaskList() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				String wonum = workorder.getWonum();
				try {
					planTaskPageindex = 1;
					Serializable taskList = appContext.queryPlanTaskList(wonum,
							planTaskPageindex);
					HandlerUtils.sendMessage(planTaskHandler, taskList,
							R.id.workorder_plan_task_query);
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
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// outState.putString(KEY_CONTENT, mContent);
	}

	private void initDetail() {
		detailHandler = getDetailHandler();
		errorHandler = getErrorHandler();
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		initDetailWeigit();
		initDetailEvent();
	}

	/**
	 * @Title: initBroadcast
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void initBroadcast() {
		IntentFilter filter = new IntentFilter(Actions.WORKORDER_PLAN_BROADCAST);
		planBroadCast = new WorkorderPlanBroadCast();
		getActivity().registerReceiver(planBroadCast, filter);
	}

	/**
	 * @Title: getPlanTaskHandler
	 * @Description: TODO
	 * @param @return
	 * @return Handler
	 * @throws
	 */
	private Handler getPlanTaskHandler() {
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
				WorkorderPlanTaskList tasks = null;
				WorkorderPlanTask task = null;
				switch (msg.what) {
				case R.id.workorder_plan_task_query:
					WorkorderPlanTaskList taskList = (WorkorderPlanTaskList) msg.obj;
					if (taskList != null) {
						planTasks = taskList.getPlanTasks();
					} else {
						planTasks = new ArrayList<WorkorderPlanTask>();
					}
					workOrderPlanTaskAdapter = new WorkOrderPlanTaskAdapter(
							workorder, appContext, getActivity(), planTasks,
							R.layout.pr_listitem);
					workPlanTaskList.setAdapter(workOrderPlanTaskAdapter);
					break;
				case R.id.workorder_plan_task_add:
					tasks = (WorkorderPlanTaskList) msg.obj;
					if (tasks != null) {
						task = tasks.getPlanTasks().get(0);
						planTasks.add(0, task);
						workOrderPlanTaskAdapter.notifyDataSetChanged();
						curTagCount = totalTagCount;
						showInfo("添加任务信息成功!!!");
					} else {
						showError("添加任务信息失败!!!");
					}
					break;
				case R.id.workorder_plan_task_del:
					String result = (String) msg.obj;
					if ("true".equalsIgnoreCase(result)) {
						showInfo("删除任务信息成功!!!");
						planTasks.remove(clickpostion);
						workOrderPlanTaskAdapter.notifyDataSetChanged();
						curTagCount = totalTagCount;
					} else {
						showError(result);
					}
					break;
				case R.id.workorder_plan_task_modify:
					tasks = (WorkorderPlanTaskList) msg.obj;
					if (tasks != null) {
						showInfo("修改任务信息成功!!!");
						task = tasks.getPlanTasks().get(0);
						planTasks.remove(clickpostion);
						planTasks.add(clickpostion, task);
						workOrderPlanTaskAdapter.notifyDataSetChanged();
						curTagCount = totalTagCount;
					} else {
						showError("修改任务信息失败!!!");
					}

					break;
				default:
					break;
				}
			}
		};
	}

	/**
	 * @Title: getPlanTaskHandler
	 * @Description: TODO
	 * @param @return
	 * @return Handler
	 * @throws
	 */
	private Handler getPlanHandler() {
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
				Serializable obj = (Serializable) msg.obj;
				switch (msg.what) {
				case R.id.workorder_plan_wplabor:
					WorkorderPlanWplaborList wplaborList = (WorkorderPlanWplaborList) obj;
					if (wplaborList != null) {
						planWplabors = wplaborList.getWplabors();
					} else {
						planWplabors = new ArrayList<WorkorderPlanWplabor>();
					}
					workPlanPersonnelList
							.setAdapter(new WorkorderPlanWplaborAdapter(
									getActivity(), planWplabors,
									R.layout.pr_listitem));
					break;
				case R.id.workorder_plan_material:
					WorkorderPlanMaterialList materialList = (WorkorderPlanMaterialList) obj;

					if (materialList != null) {
						planMaterials = materialList.getMaterials();
					} else {
						planMaterials = new ArrayList<WorkorderPlanMaterial>();
					}
					workPlanMaterialList
							.setAdapter(new WorkorderPlanMaterialAdapter(
									getActivity(), planMaterials,
									R.layout.pr_listitem));
					break;
				default:
					break;
				}
			}
		};
	}

	/**
	 * @Title: getErrorHandler
	 * @Description: 错误控制handler
	 * @param @return
	 * @return Handler
	 * @throws
	 */
	private Handler getErrorHandler() {

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
				int key = msg.what;
				Object obj = msg.obj;
				switch (key) {
				case R.id.show_error:
					showError(obj.toString());
					break;
				default:
					break;
				}
			}
		};
	}

	private Handler getDetailHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				String whereStr;
				SelectPopDialog dialog = null;
				int key = msg.what;
				TreeSet<String> datas = (TreeSet<String>) msg.obj;
				// 存放查询所需字段
				ArrayList<String> cum = new ArrayList<String>();
				SelectDialogEntity dialogEntity = new SelectDialogEntity();
				dialogEntity.setContext(workorderActivity);
				dialogEntity.setContents(datas);
				dialogEntity.setAppContext(appContext);
				switch (key) {
				case R.id.worktype_select:
					cum.add("WTYPEDESC=");
					cum.add("WORKTYPE=");
					cum.add("WHERESTR=");
					show(dialogEntity, "工作类型", workWorktype, workStatusdate,
							R.drawable.dialog_list_bg, cum, "WORKTYPE");
					break;
				case R.id.location_select:
					cum.add("DESCRIPTION=");
					cum.add("LOCATION=");
					cum.add("WHERESTR=");
					show(dialogEntity, "位置", workLocation, workAsset,
							R.drawable.dialog_list_bg, cum, "LOCATIONS");
					break;
				case R.id.asset_select:
					cum.add("DESCRIPTION=");
					cum.add("ASSETNUM=");
					cum.add("WHERESTR=");
					show(dialogEntity, "资产", workAsset, workCategory,
							R.drawable.dialog_list_bg, cum, "ASSET");
					break;
				case R.id.safetyplan_select:
					cum.add("DESCRIPTION=");
					cum.add("SAFETYPLANID=");
					cum.add("WHERESTR=");
					show(dialogEntity, "安全计划", workSecurityPlan, workPriority,
							R.drawable.dialog_list_bg, cum, "SAFETYPLAN");
					break;
				case R.id.responsibility_person_select:
					cum.add("DISPLAYNAME=");
					cum.add("PERSONID=");
					cum.add("WHERESTR=");
					show(dialogEntity, "负责人", workResponsibilityPerson,
							workChargePerson, R.drawable.dialog_list_bg, cum,
							"PERSON");
					break;
				case R.id.charge_person_select:
					cum.add("DISPLAYNAME=");
					cum.add("PERSONID=");
					cum.add("WHERESTR=");
					show(dialogEntity, "主管人", workChargePerson, workReportTime,
							R.drawable.dialog_list_bg, cum, "PERSON");
					break;
				case R.id.pm_select:
					cum.add("DESCRIPTION=");
					cum.add("PMNUM=");
					cum.add("WHERESTR=");
					show(dialogEntity, "PM", workPm, workReportPerson,
							R.drawable.dialog_list_bg, cum, "PM");
					break;
				case R.id.fault_category_select:
					whereStr = getwhereStr(R.id.fault_category_select);
					cum.add("DESCRIPTION=");
					cum.add("FAILURECODE=");
					cum.add("WHERESTR=" + whereStr);
					show(dialogEntity, "故障类别", workFaultCategory,
							workfaultSubclass, R.drawable.dialog_list_bg, cum,
							"FAILURECODE");
					break;
				case R.id.fault_subcalss_select:
					whereStr = getwhereStr(R.id.fault_subcalss_select);
					cum.add("DESCRIPTION=");
					cum.add("FAILURECODE=");
					cum.add("WHERESTR=" + whereStr);
					show(dialogEntity, "故障子类", workfaultSubclass,
							workQuestionCode, R.drawable.dialog_list_bg, cum,
							"FAILURECODE");
					break;
				case R.id.fault_question_code_select:
					whereStr = getwhereStr(R.id.fault_question_code_select);
					cum.add("DESCRIPTION=");
					cum.add("FAILURECODE=");
					cum.add("WHERESTR=" + whereStr);
					show(dialogEntity, "问答代码", workQuestionCode,
							workHomeworkPlan, R.drawable.dialog_list_bg, cum,
							"FAILURECODE");
					break;
				case R.id.homework_plan_select:
					whereStr = getwhereStr(R.id.homework_plan_select);
					cum.add("DESCRIPTION=");
					cum.add("JPNUM=");
					cum.add("WHERESTR=" + whereStr);
					show(dialogEntity, "作业计划", workHomeworkPlan,
							workPreventionMaintenance,
							R.drawable.dialog_list_bg, cum, "jobplan");
					break;
				case R.id.classstructure_select:
					show(dialogEntity, "分类", workCategory, workSite,
							R.drawable.dialog_list_bg, cum, "classstructure");
					break;
				case R.id.asset_by_location:// 位置带出资产
					if (datas != null) {
						workAsset.setText(datas.first()
								+ StringContants.SPLIT_LINE + datas.last());
					}
					break;
				case R.id.location_by_asset:// 资产带出位置
					if (datas != null) {
						workLocation.setText(datas.first()
								+ StringContants.SPLIT_LINE + datas.last());
					}
					break;
				default:
					break;
				}
			}
		};
	}

	/**
	 * @Title: show
	 * @Description: TODO 显示选择值弹出窗口
	 * @param @param dialogEntity 弹出窗口实体对象
	 * @param @param title 弹出窗口标题
	 * @param @param editView 选择值后需要编辑的view
	 * @param @param getFcousView 下次获取焦距的view
	 * @param @param dialogListBg 背景
	 * @return void
	 * @throws
	 */
	protected void show(SelectDialogEntity dialogEntity, String title,
			EditText editView, EditText getFcousView, int dialogListBg,
			ArrayList<String> cum, String tableName) {
		dialogEntity.setTitle(title);
		dialogEntity.setEdit(editView);
		dialogEntity.setGetFocusView(getFcousView);
		dialogEntity.setTableName(tableName);
		dialogManager.show(dialogEntity, dialogListBg, cum);
	}

	private void initDetailEvent() {
		addBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						boolean isNetwork = appContext.isNetworkConnected();
						if (isNetwork) {
							onlineAdd(isNetwork);
						} else {
							unlineAdd();
						}
					}
				}).start();
			}
		});

		modifyBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (!auths.contains(AuthConstants.SAVE)) {
					showError("请向服务器申请提供工单修改权限,否则将无法进行下一步操作!!!");
					return;
				}

				new Thread(new Runnable() {

					@Override
					public void run() {

						boolean isNetwork = appContext.isNetworkConnected();
						String key = "app_cache";
						// TODO 组装数据
						String parmas = assemblyParmas("&", workorder);
						if (isNetwork) {
							onlineModify(isNetwork, parmas, workorder);
						} else {
							unlineModify(isNetwork, parmas, workorder);

							// // 缓存工单数据 上传|清空缓存使用
							// Cache cache = Cache.getInstance();
							// ArrayList<Workorder> cacheWork = cache
							// .getCacheWorkorders();
							// cacheWork.add(workorder);
							// appContext.saveObject(cache, key);
						}
					}

				}).start();
			}
		});

		delBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if (!auths.contains(AuthConstants.DELETE)) {
					showError("请向服务器申请提供工单删除权限,否则将无法进行下一步操作!!!");
					return;
				}

				new Thread(new Runnable() {
					@Override
					public void run() {
						String workorderid = workorder.getWorkorderid();
						String result = null;
						try {
							result = appContext.delWorkorder(workorderid);
							if ("true".equalsIgnoreCase(result)) {
								workorderActivity.finish();
								Intent intent = new Intent();
								intent.setAction(Actions.WORKORDER_LIST_BROADCAST_ACTION);
								intent.putExtra("wath", R.id.workorder_del);
								workorderActivity.sendBroadcast(intent);
							} else {
								sendError(result);
							}
						} catch (MalformedURLException e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
		});

		worktypeBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				worktypePopDialog();
			}
		});

		// 日历弹出效果
		statusdateBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final String dialogTag = "CALDROID_DIALOG_FRAGMENT";
				dialogCaldroidFragment = new CaldroidFragment();
				dialogCaldroidFragment.setCaldroidListener(prUddate1Listener);
				// 修改弹出日历效果
				int style = DialogFragment.STYLE_NO_FRAME;
				int theme = android.R.style.Theme_DeviceDefault_Dialog;
				dialogCaldroidFragment.setStyle(style, theme);
				dialogCaldroidFragment.show(fm, dialogTag);
			}
		});

		workLocationBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				locationPopDialog();
			}
		});

		workAssetBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				assetPopDialog();
			}

			private void assetPopDialog() {
				new Thread(new Runnable() {

					@Override
					public void run() {
						String whereStrParma = "siteid='" + userSite + "'";
						String tableName = "ASSET";
						AssetSelectList asList;
						ArrayList<AssetSelect> assets;
						try {
							asList = (AssetSelectList) appContext
									.querySelectValue(
											AssetSelect.class.getName(),
											tableName, tableName,
											whereStrParma, "AssetSelectValue");

							TreeSet<String> datas = new TreeSet<String>();

							if (asList == null) {
								assets = new ArrayList<AssetSelect>();
							} else {
								assets = asList.getAssets();
							}

							for (AssetSelect as : assets) {
								String content = as.getAssetNum()
										+ StringContants.SPLIT_LINE
										+ as.getDescription();
								datas.add(content);
							}
							HandlerUtils.sendMessage(detailHandler, datas,
									R.id.asset_select);
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
		});

		workCategoryBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				classStructurePopDialog();
			}
		});

		workSiteBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO 需提供webservice接口或本地数据
			}
		});

		workFaultCategoryBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				faultPopDialog(R.id.fault_category_select);
			}
		});

		workfaultSubclassBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				faultPopDialog(R.id.fault_subcalss_select);
			}
		});

		workQuestionCodeBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				faultPopDialog(R.id.fault_question_code_select);
			}
		});

		workHomeworkPlanBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				homeworkPlanPopDialog();
			}
		});

		workSecurityPlanBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				securityPlanPopDialog();
			}
		});

		workPmBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				pmPopDialog();
			}
		});

		workResponsibilityPersonBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				workPersonPopDialog(true);
			}
		});

		workChargePersonBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				workPersonPopDialog(false);
			}
		});

		workLocation.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				getValueByAnother(R.id.asset_by_location);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		workAsset.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				getValueByAnother(R.id.location_by_asset);

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				// 查询资产自动带入位置(如果资产有多个则不带如)

			}
		});

	}

	/**
	 * @Title: queryAsset
	 * @Description: 根据位置信息查询资产
	 * @param
	 * @return void
	 * @throws
	 */
	protected void getValueByAnother(final int id) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				String table = null;
				String relationship = null;
				String attr = null;
				String value = null;
				if (id == R.id.location_by_asset) {
					table = "asset";
					relationship = "ASSET_LOCATIONS";
					attr = "assetnum";
					value = workAsset.getText().toString()
							.split(StringContants.SPLIT_LINE)[0];
				} else if (id == R.id.asset_by_location) {
					table = "asset";
					relationship = "ASSET_ASSET";
					attr = "location";
					value = workLocation.getText().toString()
							.split(StringContants.SPLIT_LINE)[0];
				}

				try {
					String[] contents = appContext.getValueByAnother(table,
							relationship, attr, value);
					if (contents == null) {
						return;
					}
					TreeSet<String> datas = new TreeSet<String>();
					datas.add(contents[0]);
					datas.add(contents[1]);
					HandlerUtils.sendMessage(detailHandler, datas, id);
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
	 * @Title: classStructurePopDialog
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	protected void classStructurePopDialog() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				String tableName = "classstructure";
				String whereStrParma = "1=1";
				ClassstructureSelectList csList;
				ArrayList<ClassstructureSelect> css;
				TreeSet<String> datas = new TreeSet<String>();
				try {
					csList = (ClassstructureSelectList) appContext
							.querySelectValue(
									ClassstructureSelect.class.getName(),
									tableName, tableName, whereStrParma,
									"ClassstructureSelectValue");
					if (csList == null) {
						css = new ArrayList<ClassstructureSelect>();
					} else {
						css = csList.getClasss();
					}

					for (ClassstructureSelect cs : css) {
						String content = cs.getClassstructureid()
								+ StringContants.SPLIT_LINE
								+ cs.getDescription();
						datas.add(content);
					}

					HandlerUtils.sendMessage(detailHandler, datas,
							R.id.classstructure_select);
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
	 * @Title: homeworkPlanPopDialog
	 * @Description: 作业计划列表弹出窗口
	 * @param
	 * @return void
	 * @throws
	 */
	protected void homeworkPlanPopDialog() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				String tableName = "jobplan";
				String whereStrParma = getwhereStr(R.id.homework_plan_select);
				HomeworkPlanSelectList hpList;
				ArrayList<HomeworkPlanSelect> hps;
				TreeSet<String> datas = new TreeSet<String>();
				try {
					hpList = (HomeworkPlanSelectList) appContext
							.querySelectValue(
									HomeworkPlanSelect.class.getName(),
									tableName, tableName, whereStrParma,
									"HomeworkPlanSelectValue");
					if (hpList == null) {
						hps = new ArrayList<HomeworkPlanSelect>();
					} else {
						hps = hpList.getHomeworks();
					}

					for (HomeworkPlanSelect hp : hps) {
						String content = hp.getJpnum()
								+ StringContants.SPLIT_LINE
								+ hp.getDescription();
						datas.add(content);
					}

					HandlerUtils.sendMessage(detailHandler, datas,
							R.id.homework_plan_select);
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
	 * @Title: faultPopDialog
	 * @Description: 故障类别、故障子类、问题代码公共弹出窗口
	 * @param @param faultCategorySelect
	 * @return void
	 * @throws
	 */
	protected void faultPopDialog(final int id) {
		new Thread(new Runnable() {

			@Override
			public void run() {

				String tableName = "FAILURECODE";
				String whereStrParma = getwhereStr(id);
				FailurecodeSelectList fcList;
				ArrayList<FailurecodeSelect> fcs;
				TreeSet<String> datas = new TreeSet<String>();
				try {
					fcList = (FailurecodeSelectList) appContext
							.querySelectValue(
									FailurecodeSelect.class.getName(),
									tableName, tableName, whereStrParma,
									"FailurecodeSelectValue");
					if (fcList == null) {
						fcs = new ArrayList<FailurecodeSelect>();
					} else {
						fcs = fcList.getFailurecodes();
					}

					for (FailurecodeSelect fc : fcs) {
						String content = fc.getFailurecode()
								+ StringContants.SPLIT_LINE
								+ fc.getDescription();
						datas.add(content);
					}
					HandlerUtils.sendMessage(detailHandler, datas, id);

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

	private String getwhereStr(int id) {
		String str = "";
		String parms;
		switch (id) {
		case R.id.fault_category_select:
			str = "failurecode in (select failurecode from failurelist where parent is null)";
			break;
		case R.id.fault_subcalss_select:
			parms = workFaultCategory.getText().toString()
					.split(StringContants.SPLIT_LINE)[0];
			str = "failurecode in (select failurecode from failurelist where parent in (select failurelist from failurelist where failurecode='"
					+ parms + "'))";
			break;
		case R.id.fault_question_code_select:
			parms = workfaultSubclass.getText().toString()
					.split(StringContants.SPLIT_LINE)[0];
			str = "failurecode in (select failurecode from failurelist where parent in (select failurelist from failurelist where failurecode='"
					+ parms + "'))";
			break;
		case R.id.homework_plan_select:
			str = "(orgid='JCHAINS' and siteid='"
					+ userSite
					+ "') or (orgid='JCHAINS' and siteid is null) or (orgid is null and siteid is null) and status='ACTIVE'";
			break;
		default:
			break;
		}
		return str;
	}

	/**
	 * @Title: pmPopDialog
	 * @Description: 显示pm选择值列表
	 * @param
	 * @return void
	 * @throws
	 */
	protected void pmPopDialog() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				String whereStrParma = "1=1";
				String tableName = "PM";
				PMSelectList pmList;
				ArrayList<PMSelect> pms;
				TreeSet<String> datas = new TreeSet<String>();
				try {
					pmList = (PMSelectList) appContext.querySelectValue(
							PMSelect.class.getName(), tableName, tableName,
							whereStrParma, "PmSelectValue");
					if (pmList == null) {
						pms = new ArrayList<PMSelect>();
					} else {
						pms = pmList.getPms();
					}

					for (PMSelect pm : pms) {
						String content = pm.getPmnum()
								+ StringContants.SPLIT_LINE
								+ pm.getDescription();
						datas.add(content);
					}
					HandlerUtils.sendMessage(detailHandler, datas,
							R.id.pm_select);

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
	 * @Title: workResponsibilityPersonPopDialog
	 * @Description: 人员列表弹出窗口
	 * @param flag
	 *            true 负责人 /false 主管人
	 * @return void
	 * @throws
	 */
	protected void workPersonPopDialog(final boolean flag) {
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
							whereStrParma,
							"ResponsibilityPersonPlanSelectValue");
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
					if (flag) {
						HandlerUtils.sendMessage(detailHandler, datas,
								R.id.responsibility_person_select);

					} else {
						HandlerUtils.sendMessage(detailHandler, datas,
								R.id.charge_person_select);
					}
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
	 * @Title: securityPlanPopDialog
	 * @Description: 显示安全计划选择值窗口
	 * @param
	 * @return void
	 * @throws
	 */
	protected void securityPlanPopDialog() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				String whereStrParma = "siteid='" + userSite + "'";
				String tableName = "SAFETYPLAN";
				SafetyplanSelectList spList;
				ArrayList<SafetyplanSelect> safetyplans;
				TreeSet<String> datas = new TreeSet<String>();
				try {
					spList = (SafetyplanSelectList) appContext
							.querySelectValue(SafetyplanSelect.class.getName(),
									tableName, tableName, whereStrParma,
									"SecurityPlanSelectValue");
					if (spList == null) {
						safetyplans = new ArrayList<SafetyplanSelect>();
					} else {
						safetyplans = spList.getSafetyplans();
					}

					for (SafetyplanSelect sp : safetyplans) {
						String content = sp.getSafetyplanid()
								+ StringContants.SPLIT_LINE
								+ sp.getDescription();
						datas.add(content);
					}
					HandlerUtils.sendMessage(detailHandler, datas,
							R.id.safetyplan_select);
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

	protected void onlineAdd(boolean isNetwork) {
		try {
			Workorder workorder = new Workorder();
			String split = StringContants.SPLIT;
			String parmas = assemblyParmas(split, workorder);
			String result = appContext.addWorkorder(isNetwork, parmas,
					workorder);
			String[] resultArray = result.split(split);
			// String result = resultArray[0].toString();
			if (resultArray[0].contains("TRUE")) {
				workorderActivity.finish();
				// TODO 返回值有问题需要和webservice开发人员沟通
				String wonum = resultArray[1];
				int index = wonum.indexOf("=") + 1;
				int length = wonum.length();
				String num = wonum.substring(index, length);
				workorder.setWonum(num);

				String workorderid = resultArray[2].substring(
						resultArray[2].indexOf("=") + 1,
						resultArray[2].length());
				workorder.setWorkorderid(workorderid);

				Intent intent = new Intent();
				intent.setAction(Actions.WORKORDER_LIST_BROADCAST_ACTION);
				intent.putExtra("wath", R.id.workorder_add);
				intent.putExtra("workorder", workorder);
				workorderActivity.sendBroadcast(intent);
			} else {
				sendError(result);
			}
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

	protected void unlineAdd() {
		String key = "workorder_add" + appContext.getLoginUid();
		Workorder workorder = new Workorder();
		String split = "&";
		String parmas = assemblyParmas(split, workorder);
		// 缓存工单数据 上传|清空缓存使用
		Cache cache = Cache.getInstance();
		ArrayList<String> cacheWork = cache.getCacheWorkorders();
		cacheWork.add(parmas);
		appContext.saveObject(cache, key);
		Intent intent = new Intent();
		intent.setAction(Actions.WORKORDER_LIST_BROADCAST_ACTION);
		intent.putExtra("wath", R.id.workorder_add);
		intent.putExtra("workorder", workorder);
		workorderActivity.sendBroadcast(intent);
		workorderActivity.finish();
	}

	public void locationPopDialog() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				String tableParma = "LOCATIONS";
				String whereStrParma = "siteid='" + userSite + "'";
				String tableName = "LOCATIONS";
				LocationsSelectList wsList;
				ArrayList<LocationsSelect> locations;
				try {
					wsList = (LocationsSelectList) appContext.querySelectValue(
							LocationsSelect.class.getName(), tableName,
							tableParma, whereStrParma, "LocationsSelectValue");
					TreeSet<String> datas = new TreeSet<String>();
					if (wsList == null) {
						locations = new ArrayList<LocationsSelect>();
					} else {
						locations = wsList.getLocations();
					}

					for (LocationsSelect ls : locations) {
						String content = ls.getLocation()
								+ StringContants.SPLIT_LINE
								+ ls.getDescription();
						datas.add(content);
					}
					HandlerUtils.sendMessage(detailHandler, datas,
							R.id.location_select);
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

	public void worktypePopDialog() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String tableParma = "worktype";
				String whereStrParma = "1=1";
				String tableName = "worktype";
				ArrayList<WorktypeSelect> selectTypes;
				try {
					WorktypeSelectList wsList = (WorktypeSelectList) appContext
							.querySelectValue(WorktypeSelect.class.getName(),
									tableName, tableParma, whereStrParma,
									"WorktypeSelectValues");
					TreeSet<String> datas = new TreeSet<String>();

					if (wsList == null) {
						selectTypes = new ArrayList<WorktypeSelect>();
					} else {
						selectTypes = wsList.getWorktypes();
					}

					for (WorktypeSelect ws : selectTypes) {
						String content = ws.getWorktype()
								+ StringContants.SPLIT_LINE + ws.getWtypedesc();
						datas.add(content);
					}

					Message message = new Message();
					message.obj = datas;
					message.what = R.id.worktype_select;
					detailHandler.sendMessage(message);

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

	protected String assemblyParmas(String split, Workorder workorder) {
		StringBuffer buffer = new StringBuffer();
		String wonum = workWonum.getText().toString();
		String description = workDescription.getText().toString();
		String worktype = workWorktype.getText().toString()
				.split(StringContants.SPLIT_LINE)[0];
		String status = workStatus.getText().toString();
		String statusdate = workStatusdate.getText().toString();
		String location = workLocation.getText().toString()
				.split(StringContants.SPLIT_LINE)[0];
		String asset = workAsset.getText().toString()
				.split(StringContants.SPLIT_LINE)[0];
		String category = workCategory.getText().toString()
				.split(StringContants.SPLIT_LINE)[0];
		String site = workSite.getText().toString();
		String faultCategory = workFaultCategory.getText().toString()
				.split(StringContants.SPLIT_LINE)[0];
		String faultSubclass = workfaultSubclass.getText().toString()
				.split(StringContants.SPLIT_LINE)[0];
		String questionCode = workQuestionCode.getText().toString()
				.split(StringContants.SPLIT_LINE)[0];
		String homeworkPlan = workHomeworkPlan.getText().toString()
				.split(StringContants.SPLIT_LINE)[0];
		String preventionMaintenance = workPreventionMaintenance.getText()
				.toString();
		String securityPlan = workSecurityPlan.getText().toString()
				.split(StringContants.SPLIT_LINE)[0];
		String priority = workPriority.getText().toString();
		String pm = workPm.getText().toString()
				.split(StringContants.SPLIT_LINE)[0];
		String reportPerson = workReportPerson.getText().toString();
		String responsibilityPerson = workResponsibilityPerson.getText()
				.toString().split(StringContants.SPLIT_LINE)[0];
		String chargePerson = workChargePerson.getText().toString()
				.split(StringContants.SPLIT_LINE)[0];
		String reportTime = workReportTime.getText().toString();
		String impactPerson = workImpactPerson.getText().toString();
		String phone = workPhone.getText().toString();

		buffer.append("WONUM=");
		buffer.append(wonum);
		buffer.append(split);

		buffer.append("DESCRIPTION=");
		buffer.append(description);
		buffer.append(split);

		buffer.append("WORKTYPE=");
		buffer.append(worktype);
		buffer.append(split);

		buffer.append("STATUS=");
		buffer.append(status);
		buffer.append(split);

		buffer.append("LOCATION=");
		buffer.append(location);
		buffer.append(split);

		buffer.append("ASSETNUM=");
		buffer.append(asset);
		buffer.append(split);

		buffer.append("CLASSSTRUCTUREID=");
		buffer.append(category);
		buffer.append(split);

		buffer.append("SITEID=");
		buffer.append(site);
		buffer.append(split);

		buffer.append("FAILURECODE=");
		buffer.append(faultCategory);
		buffer.append(split);

		buffer.append("PROBLEMCODE=");
		buffer.append(faultSubclass);
		buffer.append(split);

		buffer.append("PL1=");
		buffer.append(questionCode);
		buffer.append(split);

		buffer.append("JPNUM=");
		buffer.append(homeworkPlan);
		buffer.append(split);

		buffer.append("SAFETYPLANID=");
		buffer.append(securityPlan);
		buffer.append(split);

		buffer.append("WOPRIORITY=");
		buffer.append(priority);
		buffer.append(split);

		buffer.append("PMNUM=");
		buffer.append(pm);
		buffer.append(split);

		buffer.append("REPORTEDBY=");
		buffer.append(reportPerson);
		buffer.append(split);

		buffer.append("LEAD=");
		buffer.append(responsibilityPerson);
		buffer.append(split);

		buffer.append("SUPERVISOR=");
		buffer.append(chargePerson);
		buffer.append(split);

		buffer.append("REPORTDATE=");
		buffer.append(reportTime);
		buffer.append(split);

		buffer.append("ONBEHALFOF=");
		buffer.append(impactPerson);
		buffer.append(split);

		buffer.append("PHONE=");
		buffer.append(phone);

		workorder.setWonum(wonum != null ? wonum : "");
		workorder.setDescription(description != null ? description : "");
		workorder.setStatus(status != null ? status : "");
		workorder.setStatusdate(statusdate != null ? statusdate : "");
		workorder.setWorktype(worktype != null ? worktype : "");
		workorder.setLocation(location != null ? location : "");
		workorder.setAssetnum(asset != null ? asset : "");
		workorder.setClassstructureid(category != null ? category : "");
		workorder.setSiteid(site != null ? site : "");
		workorder.setFailurecode(faultCategory != null ? faultCategory : "");
		workorder.setProblemcode(faultSubclass != null ? faultSubclass : "");
		workorder.setPl1(questionCode != null ? questionCode : "");
		workorder.setJpnum(homeworkPlan != null ? homeworkPlan : "");
		workorder.setSafetyplanid(securityPlan != null ? securityPlan : "");
		workorder.setWopriority(priority != null ? priority : "");
		workorder.setPmnum(pm != null ? pm : "");
		workorder.setReportedby(reportPerson != null ? reportPerson : "");
		workorder.setLead(responsibilityPerson != null ? responsibilityPerson
				: "");
		workorder.setSupervisor(chargePerson != null ? chargePerson : "");
		workorder.setReportdate(reportTime != null ? reportTime : "");
		workorder.setOnbehalfof(impactPerson != null ? impactPerson : "");
		workorder.setPhone(phone != null ? phone : "");

		return buffer.toString();
	}

	private void unlineModify(boolean isNetwork, String parmas,
			Workorder workorder) {
		try {
			String reuslt = appContext.addWorkorder(isNetwork, parmas,
					workorder);
			if (!"FALSE".equalsIgnoreCase(reuslt)) {
				workorder = (Workorder) appContext.readObject(reuslt);
				workorderActivity.finish();
				Intent intent = new Intent();
				intent.setAction(Actions.WORKORDER_LIST_BROADCAST_ACTION);
				intent.putExtra("wath", R.id.workorder_modify);
				intent.putExtra("workorder", workorder);
			} else {
				sendError("修改工单失败！");
			}
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

	private void onlineModify(boolean isNetwork, String parmas,
			Workorder workorder) {
		try {
			String result = appContext.modifyWorkorder(isNetwork, parmas,
					workorder);
			if (result.contains("TRUE")) {
				workorderActivity.finish();
				Intent intent = new Intent();
				intent.setAction(Actions.WORKORDER_LIST_BROADCAST_ACTION);
				intent.putExtra("wath", R.id.workorder_modify);
				intent.putExtra("workorder", workorder);
				workorderActivity.sendBroadcast(intent);
			} else {
				sendError(result);
			}
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

	private void sendError(String result) {
		Message message = new Message();
		message.what = R.id.show_error;
		message.obj = result;
		errorHandler.sendMessage(message);
	}

	private void initDetailWeigit() {

		workWonumLayout = (RelativeLayout) detailLayout
				.findViewById(R.id.work_wonum_layout);
		workWonum = (EditText) detailLayout.findViewById(R.id.work_wonum);
		workDescription = (EditText) detailLayout
				.findViewById(R.id.work_description);
		workStatus = (EditText) detailLayout.findViewById(R.id.work_status);
		workStatus.setText("WAPPR");
		workStatusdate = (EditText) detailLayout
				.findViewById(R.id.work_statusdate);
		workWorktype = (EditText) detailLayout.findViewById(R.id.work_worktype);
		workLocation = (EditText) detailLayout.findViewById(R.id.work_location);
		workAsset = (EditText) detailLayout.findViewById(R.id.work_asset);

		workCategory = (EditText) detailLayout.findViewById(R.id.work_category);
		workSite = (EditText) detailLayout.findViewById(R.id.work_site);
		workSite.setText(userSite);
		workFaultCategory = (EditText) detailLayout
				.findViewById(R.id.work_fault_category);
		workfaultSubclass = (EditText) detailLayout
				.findViewById(R.id.work_fault_subclass);
		workQuestionCode = (EditText) detailLayout
				.findViewById(R.id.work_question_code);
		workHomeworkPlan = (EditText) detailLayout
				.findViewById(R.id.work_homework_plan);
		workPreventionMaintenance = (EditText) detailLayout
				.findViewById(R.id.work_prevention_maintenance);
		workSecurityPlan = (EditText) detailLayout
				.findViewById(R.id.work_security_plan);
		workPriority = (EditText) detailLayout.findViewById(R.id.work_priority);
		workPm = (EditText) detailLayout.findViewById(R.id.work_pm);
		workReportPerson = (EditText) detailLayout
				.findViewById(R.id.work_report_person);
		workReportPerson.setText(appContext.getLoginUid());
		workResponsibilityPerson = (EditText) detailLayout
				.findViewById(R.id.work_responsibility_person);
		workChargePerson = (EditText) detailLayout
				.findViewById(R.id.work_charge_person);
		workReportTime = (EditText) detailLayout
				.findViewById(R.id.work_report_time);
		workReportTime.setText(formatter.format(System.currentTimeMillis()));
		workImpactPerson = (EditText) detailLayout
				.findViewById(R.id.work_impact_person);
		workPhone = (EditText) detailLayout.findViewById(R.id.work_phone);

		statusdateBtn = (ImageButton) detailLayout
				.findViewById(R.id.work_statusdate_button);
		worktypeBtn = (ImageButton) detailLayout
				.findViewById(R.id.work_worktype_button);
		workLocationBtn = (ImageButton) detailLayout
				.findViewById(R.id.location_button);
		workAssetBtn = (ImageButton) detailLayout
				.findViewById(R.id.asset_button);
		workCategoryBtn = (ImageButton) detailLayout
				.findViewById(R.id.category_button);
		workSiteBtn = (ImageButton) detailLayout.findViewById(R.id.site_button);

		workFaultCategoryBtn = (ImageButton) detailLayout
				.findViewById(R.id.fault_category_button);
		workfaultSubclassBtn = (ImageButton) detailLayout
				.findViewById(R.id.fault_subclass_button);
		workQuestionCodeBtn = (ImageButton) detailLayout
				.findViewById(R.id.question_code_button);
		workHomeworkPlanBtn = (ImageButton) detailLayout
				.findViewById(R.id.homework_plan_button);
		workSecurityPlanBtn = (ImageButton) detailLayout
				.findViewById(R.id.security_plan_button);
		workPmBtn = (ImageButton) detailLayout.findViewById(R.id.pm_button);
		workResponsibilityPersonBtn = (ImageButton) detailLayout
				.findViewById(R.id.responsibility_button);
		workChargePersonBtn = (ImageButton) detailLayout
				.findViewById(R.id.charge_person_button);

		addBtn = (Button) detailLayout.findViewById(R.id.work_add_btn);
		modifyBtn = (Button) detailLayout.findViewById(R.id.work_modify_btn);
		delBtn = (Button) detailLayout.findViewById(R.id.work_del_btn);

		if (workorder != null) {
			auths = workorder.getAuths();
			workWonum.setText(workorder.getWonum());
			workDescription.setText(workorder.getDescription());
			workStatus.setText(workorder.getStatus());
			workStatusdate.setText(workorder.getStatusdate());
			workWorktype.setText(workorder.getWorktype());
			workLocation.setText(workorder.getLocation());
			workAsset.setText(workorder.getAssetnum());
			workCategory.setText(workorder.getClassstructureid());
			workSite.setText(workorder.getSiteid());
			workFaultCategory.setText(workorder.getFailurecode());
			workfaultSubclass.setText(workorder.getProblemcode());
			workQuestionCode.setText(workorder.getPl1());
			workHomeworkPlan.setText(workorder.getJpnum());
			workPreventionMaintenance.setText(workorder.getSafetyplanid());
			workSecurityPlan.setText(workorder.getSafetyplanid());
			workPriority.setText(workorder.getWopriority());
			workPm.setText(workorder.getPmnum());
			workReportPerson.setText(workorder.getReportedby());
			workResponsibilityPerson.setText(workorder.getLead());
			workChargePerson.setText(workorder.getSupervisor());
			workReportTime.setText(workorder.getReportdate());
			workImpactPerson.setText(workorder.getOnbehalfof());
			workPhone.setText(workorder.getPhone());
			modifyBtn.setVisibility(View.VISIBLE);
			delBtn.setVisibility(View.VISIBLE);
		} else {
			addBtn.setVisibility(View.VISIBLE);
			workWonumLayout.setVisibility(View.GONE);
		}
	}

	final CaldroidListener prUddate1Listener = new CaldroidListener() {

		@Override
		public void onSelectDate(Date date, View view) {
			String mDate = formatter.format(date);
			workStatusdate.setText(mDate);
			dialogCaldroidFragment.dismiss();
		}

		@Override
		public void onChangeMonth(int month, int year) {
			// String text = "month: " + month + " year: " + year;
			// Toast.makeText(workorderActivity, text,
			// Toast.LENGTH_SHORT).show();
		}

	};

	private void showError(String str) {
		appContext.showError(str, workorderActivity);
	}

	private void showInfo(String string) {
		appContext.showInfo(string, workorderActivity, Style.INFO);
	}

	/**
	 * 工单计划广播接收
	 * 
	 * @Desctiption
	 * @author 汪渝栋
	 * @date 2013-11-11 下午3:34:22
	 */
	class WorkorderPlanBroadCast extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			curTagCount--;
			if (curTagCount == 1) {
				Serializable datas = intent.getSerializableExtra("datas");
				int id = intent.getIntExtra("id", 0);
				int operate = intent.getIntExtra("operate", 0);
				switch (id) {
				case R.id.workorder_plan_task:
					HandlerUtils.sendMessage(planTaskHandler, datas, operate);
					break;
				case R.id.workorder_plan_wplabor:
					HandlerUtils.sendMessage(planPersonnelHandler, datas,
							operate);
					break;
				case R.id.workorder_plan_material:
					HandlerUtils.sendMessage(planMaterialHandler, datas,
							operate);
					break;
				default:
					break;
				}
			}
		}
	}

}
