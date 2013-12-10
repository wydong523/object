package com.google.zxing.client.android.ui;

import java.net.MalformedURLException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.google.zxing.client.android.R;
import com.google.zxing.client.android.adapter.PRListAdapter;
import com.google.zxing.client.android.adapter.PopGroupAdapter;
import com.google.zxing.client.android.app.AppContext;
import com.google.zxing.client.android.constant.Actions;
import com.google.zxing.client.android.exceptions.AuthenticationException;
import com.google.zxing.client.android.exceptions.AuthorizationException;
import com.google.zxing.client.android.exceptions.CommunicationException;
import com.google.zxing.client.android.view.pulllist.XListView;
import com.google.zxing.client.android.view.pulllist.XListView.IXListViewListener;
import com.google.zxing.client.android.view.quickaction.ActionItem;
import com.google.zxing.client.android.view.quickaction.QuickAction;
import com.google.zxing.client.android.vo.PR;
import com.google.zxing.client.android.vo.PRList;

/**
 * PR����������
 * 
 * @author ���嶰
 * 
 */
public class PRListActivity extends BaseActivity implements IXListViewListener {

	public static final int PR_ADD_FLAG = 1;// �������
	public static final int PR_DEL_FLAG = 2;// ɾ�����
	public static final int PR_MODIFY_FLAG = 3;// �޸ı��
	public static final int PR_SEE_FLAG = 4;// �鿴���
	public static final int PR_OTHER_FLAG = 5;// ɾ��/�޸�/�鿴���

	public static final int PRLIST_QUERY_FLAG = 1;// pr�б�������ѯ���
	public static final int PRLIST_LOADMORE_FLAG = 2;// pr�б���ظ�����
	public static final int PRLIST_PULL_FLAG = 3;// pr�б�����ˢ�±��

	private boolean isloadmore = false;

	private AppContext appContext;

	private PrOperateBroadcast operateBroadcast;

	private Button addBtn;
	private Button returnBtn;

	private XListView prListView;// PR�б�

	private PRListAdapter prListAdapter;

	private Handler prHandler;
	private Handler prOperateHandler;

	private ArrayList<PR> prList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(THEME);
		setContentView(R.layout.pr_list);
		init();
		queryPRList(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, R.id.pr_add, 0, "add").setIcon(R.drawable.add)
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
		title.setText("����ɹ�");
		TextView subTitle = (TextView) view.findViewById(R.id.sub_title);
		subTitle.setText("�ɹ��б�");

		actionBar.setCustomView(view);
		actionBar.setDisplayShowCustomEnabled(true);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, android.view.MenuItem item) {
		switch (item.getItemId()) {
		case R.id.pr_add:
			appContext.setPrFlag(PR_ADD_FLAG);
			toDetailView(PR_ADD_FLAG);
			break;
		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	private void init() {
		initApp();
		initWeigit();
		initEvent();
	}

	private void initEvent() {
		prListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				// ȫ�ֱ��pr�޸ĺ����ӹ���
				appContext.setPrFlag(PR_OTHER_FLAG);
				// ȫ��pr�б���λ��
				appContext.setPrCurPosition(position - 1);
				// ȫ��pr���id
				PR pr = prList.get(position - 1);
				appContext.setPrId(pr.getPrid());
				// ����б���ת����ϸ��Ϣ����
				toDetailView(pr);
			}
		});

		returnBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		addBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				appContext.setPrFlag(PR_ADD_FLAG);
				toDetailView(PR_ADD_FLAG);
			}
		});

	}

	private void initWeigit() {
		prListView = (XListView) findViewById(R.id.pr_list);
		prListView.setXListViewListener(this);
		addBtn = (Button) findViewById(R.id.pr_add_btn);
		returnBtn = (Button) findViewById(R.id.return_btn);
		visibleProgressBar("");
	}

	private void initApp() {
		operateBroadcast = new PrOperateBroadcast();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Actions.PR_BROADCAST_ACTION);
		registerReceiver(operateBroadcast, filter);

		prHandler = getPRListHandler();
		prOperateHandler = getPrOperateHandler();
		appContext = (AppContext) getApplication();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		appContext.setPageIndex(1);
		if (operateBroadcast != null)
			unregisterReceiver(operateBroadcast);
	}

	/**
	 * �����̼߳���pr�б�����
	 */
	public void queryPRList(final Activity activity) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					int pageIndex = appContext.getPageIndex();
					if (!isloadmore && pageIndex == 1) {
						PRList prList = appContext.getPRPage();
						ArrayList<PR> prs = prList.getPrs();
						if (prs != null && prs.size() > 0) {
							isloadmore = true;
							Message msg = new Message();
							msg.what = PRLIST_QUERY_FLAG;
							msg.obj = prs;
							prHandler.sendMessage(msg);
						} else {
							appContext.showError("û�����ݼ���", activity);
						}
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
	 * pr�б����������
	 * 
	 * @return
	 */
	private Handler getPRListHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// ��ȡpr�����б�
				hideProgressBar();
				switchOperate(msg);
			}
		};
	}

	protected void switchOperate(Message msg) {
		switch (msg.what) {
		case PRLIST_QUERY_FLAG:
			prList = (ArrayList<PR>) msg.obj;
			if (prList != null && prList.size() > 0) {
				prListView.setPullLoadEnable(true);
				prListAdapter = new PRListAdapter(this, prList,
						R.layout.pr_listitem);
				prListView.setAdapter(prListAdapter);
			} else {
				// TODO �����ݵ�ʱ��list������ʾ Ĭ��Ϊһ����ʾ�����ݵ�ͼƬ���������
			}
			break;
		case PRLIST_LOADMORE_FLAG:
			prList.addAll((ArrayList<PR>) msg.obj);
			prListAdapter.notifyDataSetChanged();
			break;

		default:
			break;
		}

	}

	/**
	 * pr����handler
	 * 
	 * @return
	 */
	private Handler getPrOperateHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				PR pr = (PR) msg.obj;
				int flag = msg.arg1;
				switch (flag) {
				case PR_ADD_FLAG:
					prList.add(0, pr);
					prListAdapter.notifyDataSetChanged();
					break;
				case PR_DEL_FLAG:
					prList.remove(appContext.getPrCurPosition());
					prListAdapter.notifyDataSetChanged();
					break;
				case PR_MODIFY_FLAG:
					int loc = appContext.getPrCurPosition();
					prList.remove(loc);
					prList.add(loc, pr);
					prListAdapter.notifyDataSetChanged();
					break;
				default:
					break;
				}

			}
		};
	}

	/**
	 * ��תpr��ϸ��Ϣ����
	 * 
	 * @param position
	 *            �����item position
	 * @param prList
	 *            pr�б����
	 */
	private void toDetailView(PR pr) {
		Intent intent = new Intent(this, PRDetailActivity.class);
		intent.putExtra("pr", pr);
		startActivity(intent);
	}

	/**
	 * ��תpr��ϸ��Ϣ����
	 * 
	 * @param position
	 *            �����item position
	 * @param prList
	 *            pr�б����
	 */
	private void toDetailView(int addFlag) {
		Intent intent = new Intent(this, PRDetailActivity.class);
		startActivity(intent);
	}

	@Override
	public void onRefresh() {
		// TODO ����ˢ��
	}

	@Override
	public void onLoadMore() {
		final Activity activity = this;
		new Thread(new Runnable() {

			@Override
			public void run() {
				// ��ҳ��ѯ����
				try {
					int pageIndex = appContext.getPageIndex();
					appContext.setPageIndex(++pageIndex);
					PRList prList = appContext.getPRPage();
					ArrayList<PR> prs = prList.getPrs();
					if (prs != null && prs.size() > 0) {
						Message msg = new Message();
						msg.what = PRLIST_LOADMORE_FLAG;
						msg.obj = prs;
						prHandler.sendMessage(msg);
					} else {
						appContext.showError("û�����ݼ���", activity);
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
	 * pr�б�����㲥������
	 * 
	 * @author ���嶰
	 */
	class PrOperateBroadcast extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			int flag = intent.getIntExtra("flag", 0);
			PR pr = (PR) intent.getSerializableExtra("pr");
			Message msg = new Message();
			msg.arg1 = flag;
			msg.obj = pr;
			prOperateHandler.sendMessage(msg);
		}
	}
}
