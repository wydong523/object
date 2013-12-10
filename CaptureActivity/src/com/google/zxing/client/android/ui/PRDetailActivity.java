package com.google.zxing.client.android.ui;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.adapter.PRLineListAdapter;
import com.google.zxing.client.android.adapter.PRTitelPoplistAdapter;
import com.google.zxing.client.android.app.AppContext;
import com.google.zxing.client.android.constant.Actions;
import com.google.zxing.client.android.exceptions.AuthenticationException;
import com.google.zxing.client.android.exceptions.AuthorizationException;
import com.google.zxing.client.android.exceptions.CommunicationException;
import com.google.zxing.client.android.utils.DialogManager;
import com.google.zxing.client.android.utils.EditTextManager;
import com.google.zxing.client.android.view.calendar.CaldroidFragment;
import com.google.zxing.client.android.view.calendar.CaldroidListener;
import com.google.zxing.client.android.view.dialog.SelectPopDialog;
import com.google.zxing.client.android.view.pulllist.XListView;
import com.google.zxing.client.android.view.pulllist.XListView.IXListViewListener;
import com.google.zxing.client.android.vo.PR;
import com.google.zxing.client.android.vo.PRLine;

/**
 * PR详情
 * 
 * @author 汪渝栋
 * 
 */
public class PRDetailActivity extends FragmentActivity implements
		IXListViewListener {
	private AppContext appContext;

	// dialog管理器
	private static final DialogManager dialogManager = new DialogManager();

	// PR标题模块选项标记 1=PR，2=PR行，3=收款人，4=条件，5=规范
	public static final int PR_FLAG = 1;
	public static final int PR_LINE_FLAG = 2;
	public static final int PR_RECEIVER_FLAG = 3;
	public static final int PR_CONDITION_FLAG = 4;
	public static final int PR_STANDARD_FLAG = 5;

	// PR操作标记
	public static final int PR_LINE_DEL = 0;

	public static final int PR_LINE_MODIFY = 1;

	public static final int PR_LINE_ADD = 2;

	public static final int PR_LINE_QUERY = 4;

	public static final int PR_SPWFRO = 5;

	public static final int PR_LINE_OTHER = 6;

	private View progressView;

	// 当前选项标记
	private int curFlag;

	private TextView prTitle;
	private TextView prSubTitle;

	// PR主目录布局
	private ScrollView PRDetail;
	private RelativeLayout PRLineDetail;

	// pr主表显示数据
	private EditText prPrnum;
	private EditText prDescription;
	private EditText prCutype;
	private EditText prWonum;
	private EditText prUdremark1;
	private EditText prUddate1;
	private EditText prNewvendor;
	private EditText prStatus;
	private EditText prRequestedby;
	private EditText prRequireddate;
	private EditText prUdadditional;

	private ImageButton prStatusButton;
	private ImageButton prCutypeButton;
	private ImageButton prRequireddateButton;
	private ImageButton prUddate1Button;

	private Button frameBtnPR;
	private Button frameBtnPRLine;
	private Button prCreateBtn;
	private Button prDelBtn;
	private Button prModifyBtn;
	private Button prLineReturnBtn;
	private Button prMultifunctionBtn;
	private Button prLineAddBtn;

	private View contentView;

	private PRTitelPoplistAdapter pRTitelPoplistAdapter;

	private PopupWindow mPopupWindow;

	private ListView prTitlePopList;

	private WindowManager manager;

	private XListView prLineList;

	private PrLineOperateBroadcast prLineBroadcast;

	private PRLineListAdapter prLineAdapter;

	private Handler prLineHandler;

	private ArrayList<PRLine> prlines;

	// 日历控件
	private CaldroidFragment dialogCaldroidFragment;

	// 日期格式
	private SimpleDateFormat formatter;

	// 弹出日历标记
	final String dialogTag = "CALDROID_DIALOG_FRAGMENT";

	// pr操作标记
	private int prFlag;
	private PR pr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pr_frame);
		init();
		paddingValue(prFlag, pr);
	}

	/**
	 * 初始化
	 */
	private void init() {
		initApp();
		initWigdet();
		initEvent();
	}

	private void initApp() {
		appContext = (AppContext) getApplication();
		prFlag = appContext.getPrFlag();
		Intent intent = getIntent();
		pr = (PR) intent.getSerializableExtra("pr");
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		prLineHandler = getPrHandler(this);
		// 注册广播接收器
		prLineBroadcast = new PrLineOperateBroadcast();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Actions.PR_LINE_BROADCAST_ACTION);
		registerReceiver(prLineBroadcast, filter);
	}

	private Handler getPrHandler(final Activity pr) {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				Object result = msg.obj;
				PRLine prline = appContext.getCurPrLine();
				switch (msg.what) {
				case PR_LINE_ADD:
					String addInfo = result.toString();
					if (addInfo != null && addInfo.contains("TRUE")) {
						String[] arr = addInfo.split(",");
						prline.setPrnum(arr[2].substring(
								arr[2].indexOf("=") + 1, arr[2].length()));
						String str2 = arr[3] + arr[4];
						prline.setPrlineid(str2.substring(
								str2.indexOf("=") + 1, str2.length()));
						if (prlines == null) {
							prLineList.setPullLoadEnable(true);
							prlines = new ArrayList<PRLine>();
							prlines.add(prline);
							prLineAdapter = new PRLineListAdapter(
									getApplicationContext(), prlines,
									R.layout.pr_line_listitem);
							prLineList.setAdapter(prLineAdapter);
						} else {
							prlines.add(0, prline);
							prLineAdapter.notifyDataSetChanged();
						}
					} else {
						appContext.showError(addInfo, pr);
					}
					break;
				case PR_LINE_DEL:
					String delInfo = result.toString();
					if (delInfo != null && delInfo.toString().contains("TRUE")) {
						prlines.remove(appContext.getPrLineCurPostion());
						prLineAdapter.notifyDataSetChanged();
					} else {
						appContext.showError(delInfo, pr);
					}
					break;
				case PR_LINE_MODIFY:
					String modifyInfo = result.toString();
					if (modifyInfo != null && modifyInfo.contains("TRUE")) {
						int position = appContext.getPrLineCurPostion();
						prlines.remove(position);
						prlines.add(position, appContext.getCurPrLine());
						prLineAdapter.notifyDataSetChanged();
					} else {
						appContext.showError(modifyInfo, pr);
					}
					break;
				case PR_LINE_QUERY:
					hideProgressBar();
					prlines = (ArrayList<PRLine>) result;
					if (prlines != null && prlines.size() > 0) {
						prLineList.setPullLoadEnable(true);
						prLineAdapter = new PRLineListAdapter(
								getApplicationContext(), prlines,
								R.layout.pr_line_listitem);
						prLineList.setAdapter(prLineAdapter);
					} else {
						appContext.showError("PRline列表没有数据", pr);
					}
					break;
				case PR_SPWFRO:
					String spwfroInfo = result.toString();
					if (spwfroInfo != null && spwfroInfo.contains("TRUE")) {
						appContext.showError("发起流程成功！", pr);
					} else {
						appContext.showError(spwfroInfo, pr);
					}
					break;
				}
			}
		};
	}

	/**
	 * 查看、修改、删除、创建对应的pr详情设置
	 */
	private void paddingValue(int flag, PR pr) {
		int key = flag;
		switch (key) {
		case PRListActivity.PR_ADD_FLAG:
			// 设置界面为无数据
			settingValue(flag, pr);
			break;
		case PRListActivity.PR_OTHER_FLAG:
			// 其他操作界面为有数据进行
			settingValue(flag, pr);
			break;
		default:
			break;
		}
	}

	/**
	 * 设置pr详细信息值
	 * 
	 * @param flag
	 *            操作标记
	 * @param pr
	 *            pr实体
	 */
	private void settingValue(int flag, PR pr) {
		prTitle.setText("采购操作");
		if (flag != PRListActivity.PR_ADD_FLAG) {
			prPrnum.setText(pr.getPrnum().toString());
			prDescription.setText(pr.getDescription().toString());
			prCutype.setText(pr.getCutype().toString());
			prWonum.setText(pr.getWonum().toString());
			prUdremark1.setText(pr.getUdremark1().toString());
			prUddate1.setText(pr.getUddate1().toString());
			prNewvendor.setText(pr.getVendor().toString());
			prStatus.setText(pr.getStatus().toString());
			prRequestedby.setText(pr.getRequestedby().toString());
			prRequireddate.setText(pr.getRequireddate().toString());
			prUdadditional.setText("0");
			prSubTitle.setText("修改/删除采购申请");
		} else {
			prPrnum.setText("");
			prDescription.setText("");
			prCutype.setText("WZ-GN");
			prWonum.setText("");
			prUdremark1.setText("");
			prUddate1.setText("");
			prNewvendor.setText("");
			prStatus.setText("");
			prRequestedby.setText("");
			prRequireddate.setText("");
			prUdadditional.setText("0");
			prSubTitle.setText("添加采购申请");
		}
	}

	/**
	 * 初始化控件事件效果
	 */
	private void initEvent() {

		// 日历弹出效果
		prUddate1Button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialogCaldroidFragment = new CaldroidFragment();
				dialogCaldroidFragment.setCaldroidListener(prUddate1Listener);
				// 修改弹出日历效果
				int style = DialogFragment.STYLE_NO_FRAME;
				int theme = android.R.style.Theme_DeviceDefault_Dialog;
				dialogCaldroidFragment.setStyle(style, theme);
				dialogCaldroidFragment.show(getSupportFragmentManager(),
						dialogTag);
			}
		});

		prRequireddateButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialogCaldroidFragment = new CaldroidFragment();
				dialogCaldroidFragment
						.setCaldroidListener(prRequireddateListener);
				int style = DialogFragment.STYLE_NO_FRAME;
				int theme = android.R.style.Theme_DeviceDefault_Dialog;
				dialogCaldroidFragment.setStyle(style, theme);
				dialogCaldroidFragment.show(getSupportFragmentManager(),
						dialogTag);
			}
		});

		// 状态选择
		prStatusButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				statusPopDialog();
			}
		});

		// 类型选择
		prCutypeButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				typePopDialog();
			}
		});

		// 主表按钮点击事件
		frameBtnPR.setOnClickListener(framePRBtnClick(frameBtnPR, PR_FLAG));

		// 子表按钮点击事件
		frameBtnPRLine.setOnClickListener(framePRBtnClick(frameBtnPRLine,
				PR_LINE_FLAG));

		// 新增按钮点击事件
		prCreateBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 新增pr
				addPRData();
			}
		});

		// 删除按钮点击事件
		prDelBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 删除pr
				delPRData();
			}

		});

		// 修改按钮点击事件
		prModifyBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				modifyPRData();
			}
		});

		prLineList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adpter, View view,
					int position, long arg3) {
				// prline详情
				PRLine prLine = prlines.get(position - 1);
				appContext.setPrlineid(prLine.getPrlineid());
				// 把当前新增的prline对象添加到应用
				appContext.setCurPrLine(prLine);
				skipToPrlineDrtail(PR_LINE_OTHER);
			}

		});

		prLineReturnBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		prMultifunctionBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				inwfro();
			}

		});

		prLineAddBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				skipToPrlineDrtail(PR_LINE_ADD);
			}
		});

	}

	/**
	 * 发起流程
	 */
	protected void inwfro() {
		new Thread(new Runnable() {

			@Override
			public void run() {
//				try {
//					String result = appContext.inwfro();
//					Message message = new Message();
//					message.what = PR_SPWFRO;
//					message.obj = result;
//					prLineHandler.sendMessage(message);
//
//				} catch (MalformedURLException e) {
//					e.printStackTrace();
//				} catch (CommunicationException e) {
//					e.printStackTrace();
//				} catch (AuthorizationException e) {
//					e.printStackTrace();
//				} catch (AuthenticationException e) {
//					e.printStackTrace();
//				}
			}
		}).start();
	}

	/**
	 * 跳转到prline详情
	 * 
	 * @param prLine
	 */
	private void skipToPrlineDrtail(int flag) {
		Intent intent = new Intent(this, PRLineDetail.class);
		intent.putExtra("flag", flag);
		startActivity(intent);
	}

	/**
	 * 状态选择弹出窗口
	 */
	public void statusPopDialog() {
//		Set<String> datas = new TreeSet<String>();
//		datas.add("WAPPR");
//		datas.add("WCGBACK");
//		datas.add("WAPPR2");
//		datas.add("WAPPR3");
//		datas.add("WREQ");
//		PopDialog dialog = new PopDialog(this, datas, "状态", prStatus,
//				prRequestedby);
//		dialog.show();
//		// 设置Dialog弹出框大小，需要放在show以后
//		dialogManager
//				.setDialogPopScale(this, dialog, R.drawable.dialog_list_bg);
	}

	/**
	 * 类型选择弹出窗口
	 */
	public void typePopDialog() {
//		Set<String> datas = new TreeSet<String>();
//		datas.add("WZ-GN");
//		PopDialog dialog = new PopDialog(this, datas, "类型", prCutype, prWonum);
//		dialog.show();
//		// 设置Dialog弹出框大小，需要放在show以后
//		dialogManager
//				.setDialogPopScale(this, dialog, R.drawable.dialog_list_bg);
	}

	/**
	 * 初始化界面控件
	 */
	private void initWigdet() {
		PRDetail = (ScrollView) findViewById(R.id.pr_detail_layout);
		PRDetail.requestFocus();
		PRLineDetail = (RelativeLayout) findViewById(R.id.pr_line_layout);

		prTitle = (TextView) findViewById(R.id.pr_title);
		prTitle.requestFocus();
		prSubTitle = (TextView) findViewById(R.id.pr_sub_title);

		prLineList = (XListView) findViewById(R.id.pr_line_list);
		prLineList.setXListViewListener(this);
		prTitlePopList = (ListView) findViewById(R.id.lv_group);

		prPrnum = (EditText) findViewById(R.id.pr_prnum);
		prDescription = (EditText) findViewById(R.id.pr_description);
		prCutype = (EditText) findViewById(R.id.pr_cutype);
		prWonum = (EditText) findViewById(R.id.pr_wonum);
		prUdremark1 = (EditText) findViewById(R.id.pr_udremark1);
		prUddate1 = (EditText) findViewById(R.id.pr_uddate1);
		prNewvendor = (EditText) findViewById(R.id.pr_newvendor);
		prStatus = (EditText) findViewById(R.id.pr_status);
		prRequestedby = (EditText) findViewById(R.id.pr_requestedby);
		prRequireddate = (EditText) findViewById(R.id.pr_requireddate);
		prUdadditional = (EditText) findViewById(R.id.pr_udadditional);

		prStatusButton = (ImageButton) findViewById(R.id.pr_status_button);
		prCutypeButton = (ImageButton) findViewById(R.id.pr_cutype_button);
		prUddate1Button = (ImageButton) findViewById(R.id.pr_uddate1_button);
		prRequireddateButton = (ImageButton) findViewById(R.id.pr_requireddate_button);

		frameBtnPR = (Button) findViewById(R.id.frame_btn_PR);
		frameBtnPRLine = (Button) findViewById(R.id.frame_btn_PR_line);
		prCreateBtn = (Button) findViewById(R.id.pr_create_btn);
		prDelBtn = (Button) findViewById(R.id.pr_del_btn);
		prDelBtn.setVisibility(View.GONE);
		prModifyBtn = (Button) findViewById(R.id.pr_modify_btn);
		prLineReturnBtn = (Button) findViewById(R.id.pr_line_return_btn);
		prMultifunctionBtn = (Button) findViewById(R.id.pr_multifunction_button);
		prLineAddBtn = (Button) findViewById(R.id.pr_line_add_btn);
		// 默认属性设置
		frameBtnPR.setEnabled(false);
		switch (prFlag) {
		case PRListActivity.PR_ADD_FLAG:
			prCreateBtn.setVisibility(View.VISIBLE);
			prDelBtn.setVisibility(View.GONE);
			prModifyBtn.setVisibility(View.GONE);
			break;
		case PRListActivity.PR_OTHER_FLAG:
			prCreateBtn.setVisibility(View.GONE);
			prDelBtn.setVisibility(View.VISIBLE);
			prModifyBtn.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}

	/**
	 * PR分类点击事件
	 * 
	 * @param btn
	 * @param selFlag
	 * @return
	 */
	private View.OnClickListener framePRBtnClick(final Button btn,
			final int selFlag) {
		visibleProgressBar("");
		return new View.OnClickListener() {
			public void onClick(View v) {
				Message msg = null;
				if (btn == frameBtnPR) {
					frameBtnPR.setEnabled(false);
				} else {
					frameBtnPR.setEnabled(true);
				}
				if (btn == frameBtnPRLine) {
					frameBtnPRLine.setEnabled(false);
				} else {
					frameBtnPRLine.setEnabled(true);
				}
				curFlag = selFlag;
				switch (curFlag) {
				case PR_FLAG:
					PRDetail.setVisibility(View.VISIBLE);
					PRLineDetail.setVisibility(View.GONE);
					prModifyBtn.setVisibility(View.VISIBLE);
					prMultifunctionBtn.setVisibility(View.VISIBLE);
					prLineAddBtn.setVisibility(View.GONE);
					if (prFlag == PRListActivity.PR_ADD_FLAG) {
						prSubTitle.setText("新增采购申请");
					}
					if (prFlag == PRListActivity.PR_OTHER_FLAG) {
						prSubTitle.setText("修改/删除采购申请");
					}
					break;
				case PR_LINE_FLAG:
					PRDetail.setVisibility(View.GONE);
					PRLineDetail.setVisibility(View.VISIBLE);
					prModifyBtn.setVisibility(View.GONE);
					prMultifunctionBtn.setVisibility(View.GONE);
					prLineAddBtn.setVisibility(View.VISIBLE);
					prTitle.setText("采购详情");
					prSubTitle.setText("采购详情列表");

					// 查询prline
					queryPrLineData();
					break;
				case PR_RECEIVER_FLAG:
					// PRDetail.setVisibility(View.GONE);
					// PRLineDetail.setVisibility(View.GONE);
					// PRReceiverLayout.setVisibility(View.VISIBLE);
					// PRConditionLayout.setVisibility(View.GONE);
					// PRStandardLayout.setVisibility(View.GONE);
					// prModifyBtn.setVisibility(View.GONE);
					// msg = new Message();
					// prReceiverHandler.sendMessage(msg);
					break;
				case PR_CONDITION_FLAG:
					// PRDetail.setVisibility(View.GONE);
					// PRLineDetail.setVisibility(View.GONE);
					// PRReceiverLayout.setVisibility(View.GONE);
					// PRConditionLayout.setVisibility(View.VISIBLE);
					// PRStandardLayout.setVisibility(View.GONE);
					// prModifyBtn.setVisibility(View.GONE);
					// msg = new Message();
					// prConditionHandler.sendMessage(msg);
					break;
				case PR_STANDARD_FLAG:
					// PRDetail.setVisibility(View.GONE);
					// PRLineDetail.setVisibility(View.GONE);
					// PRReceiverLayout.setVisibility(View.GONE);
					// PRConditionLayout.setVisibility(View.GONE);
					// PRStandardLayout.setVisibility(View.VISIBLE);
					// prModifyBtn.setVisibility(View.GONE);
					// msg = new Message();
					// prStandardHandler.sendMessage(msg);
					break;
				}
			}

		};
	}

	/**
	 * Save current states of the Caldroid here
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);

		if (dialogCaldroidFragment != null) {
			dialogCaldroidFragment.saveStatesToKey(outState,
					"DIALOG_CALDROID_SAVED_STATE");
		}
	}

	/**
	 * 创建pr数据
	 * 
	 * @return
	 */
	public void addPRData() {
		final Activity activity = this;
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// TODO 加载进度条
					String split = "&";
					PR pr = new PR();
					String parmas = assemblyPRParmas(split, pr);
					String result = appContext.addPrData(parmas);
					// 处理返回结果
					String[] resultArray = result.split(",");
					String t = resultArray[0].toString();
					if (t.contains("TRUE")) {
						finish();
						// TODO 返回值有问题需要和webservice开发人员沟通
						String prNo = resultArray[3] + resultArray[4];
						int index = prNo.indexOf("=") + 1;
						int length = prNo.length();
						String prid = prNo.substring(index, length);
						System.out.println(prid);
						pr.setPrid(prid);
						Intent intent = new Intent();
						intent.setAction(Actions.PR_BROADCAST_ACTION);
						intent.putExtra("flag", PRListActivity.PR_ADD_FLAG);
						intent.putExtra("pr", pr);
						sendBroadcast(intent);
					} else {
						appContext.showError(result, activity);
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
	 * 删除pr数据
	 */
	private void delPRData() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String prid = pr.getPrid();
					String result = appContext.delPrData(prid);
					if ("true".equalsIgnoreCase(result)) {
						finish();
						Intent intent = new Intent();
						intent.setAction(Actions.PR_BROADCAST_ACTION);
						intent.putExtra("flag", PRListActivity.PR_DEL_FLAG);
						intent.putExtra("pr", pr);
						sendBroadcast(intent);
					} else {
						finish();
					}
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * 修改pr数据
	 */
	private void modifyPRData() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// TODO 加载进度条
					String split = "&";
					// 组装pr数据
					String parmas = assemblyPRParmas(split, pr);
					String result = appContext.modifyPrData(parmas);
					// TODO 处理返回结果
					String[] resultArray = result.split(",");
					String t = resultArray[0].toString();
					if (t.contains("TRUE")) {
						finish();
						String prNo = resultArray[3] + resultArray[4];
						int index = prNo.indexOf("=") + 1;
						int length = prNo.length();
						String prid = prNo.substring(index, length);
						pr.setPrid(prid);
						Intent intent = new Intent();
						intent.setAction(Actions.PR_BROADCAST_ACTION);
						intent.putExtra("flag", PRListActivity.PR_MODIFY_FLAG);
						intent.putExtra("pr", pr);
						sendBroadcast(intent);
					} else {
						// TODO 失败处理
					}

				} catch (MalformedURLException e) {
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

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (prLineBroadcast != null)
			unregisterReceiver(prLineBroadcast);
	}

	/**
	 * 查询prline
	 */
	private void queryPrLineData() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					prlines = appContext.queryPrLineList();
					Message message = new Message();
					message.obj = prlines;
					message.what = PR_LINE_QUERY;
					prLineHandler.sendMessage(message);
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
	 * 组装传递参数
	 * 
	 * @param split
	 *            分割符
	 * @param pr
	 *            实体对象
	 * @return
	 */
	private String assemblyPRParmas(String split, PR pr) {
		StringBuffer parmas = new StringBuffer();
		String prnum = prPrnum.getText().toString();
		String description = prDescription.getText().toString();
		String cutype = prCutype.getText().toString();
		String wonum = prWonum.getText().toString();
		String udremark1 = prUdremark1.getText().toString();
		String uddate1 = prUddate1.getText().toString();
		String newvendor = prNewvendor.getText().toString();
		String status = prStatus.getText().toString();
		String requestedby = prRequestedby.getText().toString();
		String requireddate = prRequireddate.getText().toString();
		String udadditional = prUdadditional.getText().toString();
		parmas.append("PRNUM=");
		parmas.append(prnum);
		parmas.append(split);
		parmas.append("DESCRIPTION=");
		parmas.append(description);
		parmas.append(split);
		parmas.append("CUTYPE=");
		parmas.append(cutype);
		parmas.append(split);
		parmas.append("WONUM=");
		parmas.append(wonum);
		parmas.append(split);
		parmas.append("UDREMARK1=");
		parmas.append(udremark1);
		parmas.append(split);
		parmas.append("UDDATE1=");
		parmas.append(uddate1);
		parmas.append(split);
		parmas.append("NEWVENDOR=");
		parmas.append(newvendor);
		parmas.append(split);
		parmas.append("STATUS=");
		parmas.append(status);
		parmas.append(split);
		parmas.append("REQUESTEDBY=");
		parmas.append(requestedby);
		parmas.append(split);
		parmas.append("REQUIREDDATE=");
		parmas.append(requireddate);
		parmas.append(split);
		parmas.append("UDADDITIONAL=");
		parmas.append(udadditional);

		pr.setPrnum(prnum != null ? prnum : "");
		pr.setDescription(description != null ? description : "");
		pr.setCutype(cutype != null ? cutype : "");
		pr.setWonum(wonum != null ? wonum : "");
		pr.setUdremark1(udremark1 != null ? udremark1 : "");
		pr.setUddate1(uddate1 != null ? uddate1 : "");
		pr.setVendor(newvendor != null ? newvendor : "");
		pr.setStatus(status != null ? status : "");
		pr.setRequestedby(requestedby != null ? requestedby : "");
		pr.setRequireddate(requireddate != null ? requireddate : "");
		pr.setUdadditional(udadditional != null ? udadditional : "");
		return parmas.toString();
	}

	/**
	 * 设置日历选中和刷新事件
	 */
	final CaldroidListener prUddate1Listener = new CaldroidListener() {

		@Override
		public void onSelectDate(Date date, View view) {
			String mDate = formatter.format(date);
			prUddate1.setText(mDate);
			dialogCaldroidFragment.dismiss();
		}

		@Override
		public void onChangeMonth(int month, int year) {
			String text = "month: " + month + " year: " + year;
			Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT)
					.show();
		}

	};

	final CaldroidListener prRequireddateListener = new CaldroidListener() {

		@Override
		public void onSelectDate(Date date, View view) {
			String mDate = formatter.format(date);
			prRequireddate.setText(mDate);
			dialogCaldroidFragment.dismiss();
		}

		@Override
		public void onChangeMonth(int month, int year) {
			String text = "month: " + month + " year: " + year;
			Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT)
					.show();
		}
	};

	public void showPopwindow(View displayView, ArrayList<String> groups) {
		if (mPopupWindow == null) {
			LayoutInflater mLayoutInflater = LayoutInflater.from(this);
			contentView = mLayoutInflater.inflate(R.layout.group_list, null);

			prTitlePopList = (ListView) contentView.findViewById(R.id.lv_group);

			pRTitelPoplistAdapter = new PRTitelPoplistAdapter(this, groups);
			prTitlePopList.setAdapter(pRTitelPoplistAdapter);

			manager = (WindowManager) this
					.getSystemService(Service.WINDOW_SERVICE);
			mPopupWindow = new PopupWindow(contentView, manager
					.getDefaultDisplay().getWidth() / 4,
					LayoutParams.WRAP_CONTENT);
		}
		mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		mPopupWindow.setFocusable(true);

		// 显示的位置为:屏幕的宽度的1/16
		int xPos = manager.getDefaultDisplay().getWidth() / 16;

		mPopupWindow.showAsDropDown(displayView, xPos, 0);

		prTitlePopList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adpter, View view,
					int position, long arg3) {
				inwfro();
			}
		});
	}

	/**
	 * 显示进度条
	 * 
	 * @param dataLoading
	 */
	public synchronized void visibleProgressBar(String dataLoading) {
		progressView = getWindow().getDecorView();
		progressView.findViewById(R.id.progress_bar)
				.setVisibility(View.VISIBLE);
		progressView.findViewById(R.id.data_is_loading).setVisibility(
				View.VISIBLE);
	}

	/**
	 * 隐藏进度条
	 */
	public synchronized void hideProgressBar() {
		progressView.findViewById(R.id.progress_bar).setVisibility(View.GONE);
		progressView.findViewById(R.id.data_is_loading)
				.setVisibility(View.GONE);
	}

	@Override
	public void onRefresh() {

	}

	@Override
	public void onLoadMore() {

	}

	/**
	 * prline操作广播接收器
	 * 
	 * @author 汪渝栋
	 */
	class PrLineOperateBroadcast extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String result = intent.getStringExtra("result");
			int flag = intent.getIntExtra("flag", 5);
			Message msg = new Message();
			msg.what = flag;
			msg.obj = result;
			prLineHandler.sendMessage(msg);
		}
	}
}
