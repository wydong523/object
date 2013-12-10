package com.google.zxing.client.android.ui;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.google.zxing.client.android.R;
import com.google.zxing.client.android.adapter.WorkorderAdapter;
import com.google.zxing.client.android.app.AppContext;
import com.google.zxing.client.android.constant.Actions;
import com.google.zxing.client.android.constant.AuthConstants;
import com.google.zxing.client.android.exceptions.AuthenticationException;
import com.google.zxing.client.android.exceptions.AuthorizationException;
import com.google.zxing.client.android.exceptions.CommunicationException;
import com.google.zxing.client.android.utils.HandlerUtils;
import com.google.zxing.client.android.view.pulllist.XListView;
import com.google.zxing.client.android.view.pulllist.XListView.IXListViewListener;
import com.google.zxing.client.android.vo.PR;
import com.google.zxing.client.android.vo.PRList;
import com.google.zxing.client.android.vo.Workorder;
import com.google.zxing.client.android.vo.WorkorderList;

import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * 工单列表
 * 
 * @author 汪渝栋
 * 
 */
public class WorkorderListActivity extends BaseActivity implements
		IXListViewListener {

	private WorkorderAdapter workorderAdapter;

	private XListView workorderList;

	private Handler workHandler;

	private Handler errorHandler;

	private ArrayList<Workorder> works;

	private WorkorderBroadCast workorderBroadCast;

	private AppContext appContext;

	private TreeSet<String> auths;

	private static int pageIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(THEME);
		super.onCreate(savedInstanceState);
		pageIndex = 1;
		setContentView(R.layout.workorder_list_layout);
		appContext = (AppContext) getApplication();
		IntentFilter filter = new IntentFilter(
				Actions.WORKORDER_LIST_BROADCAST_ACTION);
		workorderBroadCast = new WorkorderBroadCast();
		registerReceiver(workorderBroadCast, filter);
		auths = appContext.getAppAuth(AuthConstants.WOTRACK);
		final AppContext appContext = (AppContext) getApplication();
		init();
		workHandler = getWorkHandler();
		errorHandler = getRrrorHandler();
		visibleProgressBar(null);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					WorkorderList workorderList = appContext
							.queryWork(pageIndex);
					if (workorderList != null) {
						ArrayList<Workorder> workorders = workorderList
								.getWorkorders();
						HandlerUtils.sendMessage(workHandler, workorders,
								R.id.workorder_list_query);
					} else {
						Message msg = new Message();
						msg.what = R.id.workorder_list_query;
						msg.obj = "您好，没有工单相关数据！";
						workHandler.sendMessage(msg);
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
	 * @Title: getRrrorHandler
	 * @Description: TODO
	 * @param @return
	 * @return Handler
	 * @throws
	 */
	private Handler getRrrorHandler() {
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
				switch (msg.what) {
				case R.id.loadlist_error:
					workorderList.stopLoadMore();
					show();
					break;
				default:
					break;
				}
			}
		};
	}

	/**
	 * @Title: show
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	protected void show() {
		appContext.showError("您好，已经没有剩余工单数据！", this);
	}

	@SuppressLint("HandlerLeak")
	private Handler getWorkHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				int key = msg.what;
				Workorder workorder = null;
				switch (key) {
				case R.id.workorder_add:
					showInfo("添加工单信息成功!!!");
					workorder = (Workorder) msg.obj;
					works.add(0, workorder);
					workorderAdapter.notifyDataSetChanged();
					break;
				case R.id.workorder_del:
					showInfo("删除工单信息成功!!!");
					works.remove(appContext.getListClickPosition());
					workorderAdapter.notifyDataSetChanged();
					break;
				case R.id.workorder_modify:
					showInfo("修改工单信息成功!!!");
					workorder = (Workorder) msg.obj;
					int loc = appContext.getListClickPosition();
					works.remove(loc);
					works.add(loc, workorder);
					workorderAdapter.notifyDataSetChanged();
					break;
				case R.id.workorder_list_query:
					hideProgressBar();
					works = (ArrayList<Workorder>) msg.obj;
					adapterList(works);
					break;
				case R.id.workorder_list_loadmore:
					works.addAll((ArrayList<Workorder>) msg.obj);
					workorderAdapter.notifyDataSetChanged();
					break;
				default:
					showError(msg.obj.toString());
					break;
				}
			}
		};
	}


	protected void adapterList(ArrayList<Workorder> works) {
		if (works != null && works.size() > 0) {
			workorderList.setPullLoadEnable(true);
			workorderAdapter = new WorkorderAdapter(this, works,
					R.layout.pr_listitem);
			workorderList.setAdapter(workorderAdapter);
		} else {
			// TODO 无数据的时候list界面显示 默认为一张显示无数据的图片，后期添加
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, R.id.workorder_add, 0, "add").setIcon(R.drawable.add)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

		ActionBar actionBar = getSupportActionBar();
		Drawable bg = (Drawable) getResources().getDrawable(R.drawable.titlebg);
		actionBar.setBackgroundDrawable(bg);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);

		View view = this.getLayoutInflater()
				.inflate(R.layout.custom_view, null);
		ImageView titleImg = (ImageView) view.findViewById(R.id.title_img);
		titleImg.setBackgroundResource(R.drawable.return_btn);
		titleImg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		TextView title = (TextView) view.findViewById(R.id.title);
		title.setText("工单");
		TextView subTitle = (TextView) view.findViewById(R.id.sub_title);
		subTitle.setText("工单列表");

		actionBar.setCustomView(view);
		actionBar.setDisplayShowCustomEnabled(true);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, android.view.MenuItem item) {
		switch (item.getItemId()) {
		case R.id.workorder_add:
			if (auths.contains(AuthConstants.INSERT)) {
				Intent intent = new Intent(this, WorkorderActivity.class);
				startActivity(intent);
			} else {
				showInfo("请向服务器申请提供工单添加权限,否则将无法进行下一步操作!!!");
			}
			break;
		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	private void init() {
		initWeigit();
		initEvent();
	}

	private void initEvent() {
		workorderList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// 当应用程序拥有读取权限才进行其他操作
				if (auths.contains(AuthConstants.READ)) {
					appContext.setListClickPosition(position - 1);
					Workorder workorder = works.get(position - 1);
					workorder.setAuths(auths);
					startActivity(workorder);
				} else {
					showError("请向服务器申请提供工单读取权限,否则将无法进行下一步查看操作!!!");
				}
			}

		});
	}

	private void startActivity(Workorder workorder) {
		Intent intent = new Intent(this, WorkorderActivity.class);
		intent.putExtra("workorder", workorder);
		startActivity(intent);
	}

	private void initWeigit() {
		workorderList = (XListView) findViewById(R.id.workorder_list);
		workorderList.setXListViewListener(this);
	}

	@Override
	public void onRefresh() {
		workorderList.stopRefresh();
	}

	@Override
	public void onLoadMore() {
		pageIndex++;
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					WorkorderList list = appContext.queryWork(pageIndex);
					if (list != null) {
						ArrayList<Workorder> workorders = list.getWorkorders();
						if (workorders != null && workorders.size() > 0) {
							Message msg = new Message();
							msg.what = R.id.workorder_list_loadmore;
							msg.obj = workorders;
							workHandler.sendMessage(msg);
						} else {
							HandlerUtils.sendErrorMessage(errorHandler,
									R.id.loadlist_error, "你好，已经没有剩余工单数据！");
						}
					} else {
						HandlerUtils.sendErrorMessage(errorHandler,
								R.id.loadlist_error, "你好，已经没有剩余工单数据！");
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

	class WorkorderBroadCast extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {

			Message msg = new Message();
			msg.what = intent.getIntExtra("wath", -1);
			msg.obj = intent.getSerializableExtra("workorder");
			workHandler.sendMessage(msg);

		}
	}

}
