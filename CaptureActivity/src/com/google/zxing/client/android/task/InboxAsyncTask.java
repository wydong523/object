package com.google.zxing.client.android.task;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.google.zxing.client.android.R;
import com.google.zxing.client.android.adapter.InboxsAdapter;
import com.google.zxing.client.android.app.AppContext;
import com.google.zxing.client.android.constant.ServiceConstants;
import com.google.zxing.client.android.exceptions.AuthenticationException;
import com.google.zxing.client.android.exceptions.AuthorizationException;
import com.google.zxing.client.android.exceptions.CommunicationException;
import com.google.zxing.client.android.ui.HomeActivity;
import com.google.zxing.client.android.ui.PRDetailActivity;
import com.google.zxing.client.android.view.pulllist.XListView;
import com.google.zxing.client.android.view.pulllist.XListView.IXListViewListener;
import com.google.zxing.client.android.vo.Inbox;
import com.google.zxing.client.android.webservice.ApiService;

/**
 * �ռ����������
 * 
 * @author ���嶰
 * 
 */
public class InboxAsyncTask extends AsyncTask<String, Void, ArrayList<Inbox>>
		implements IXListViewListener {

	private static final String TAG = InboxAsyncTask.class.getSimpleName();

	private String username;

	private HomeActivity inboxActivity;

	private XListView inboxList;

	private InboxsAdapter inboxAdapter;

	private AppContext appContext;

	// ������
	ArrayList<Inbox> inboxs = null;

	private Handler inboxListHandler;

	public InboxAsyncTask(String username, XListView listView,
			HomeActivity inboxActivity, AppContext appContext) {
		this.username = username;
		this.inboxActivity = inboxActivity;
		this.inboxList = listView;
		this.appContext = appContext;
	}

	@Override
	protected void onPostExecute(final ArrayList<Inbox> result) {
		Log.d(TAG, "InboxAsyncTask onPostExecute is here!");
		super.onPostExecute(result);
		if (result == null || result.size() <= 0) {
			return;
		}
		// inboxActivity.hideProgressBar();
		// ����inbox��������, ���listview
		inboxListHandler = getinboxHandler();
		inboxList = (XListView) inboxActivity.findViewById(R.id.inbox_list);
		inboxList.setPullLoadEnable(true);
		inboxAdapter = new InboxsAdapter(inboxActivity, result,
				R.layout.inbox_listitem, appContext, inboxListHandler);
		inboxList.setAdapter(inboxAdapter);
		inboxList.setXListViewListener(this);
		inboxList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int index, long arg3) {
				// TODO �ռ����б��е�ĳһ������Ĳ���(����Ӧ�ñ�ʶ������ת)��Ŀǰ��Ϊģ�鲻ȷ�����Ȳ�������
				// Inbox inbox = result.get(index - 1);
				// Intent intent = new Intent(inboxActivity,
				// PRDetailActivity.class);
				// intent.putExtra("inboxdetail", inbox);
				// inboxActivity.startActivity(intent);
			}
		});
	}

	private Handler getinboxHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				int position = msg.arg1;
				inboxs.remove(position);
				inboxAdapter.notifyDataSetChanged();
			}
		};
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		Log.d(TAG, "InboxAsyncTask onProgressUpdate is here!");
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPreExecute() {
		Log.d(TAG, "InboxAsyncTask onPreExecute is here!");
		super.onPreExecute();
	}

	@Override
	protected ArrayList<Inbox> doInBackground(String... params) {
		Log.d(TAG, "InboxAsyncTask doInBackground is here!");
		//
		URL inboxUrl = null;
		try {
			// ��ȡwebservice�����inbox����
			// inboxActivity.visibleProgressBar("");
			inboxUrl = new URL(appContext.getAddress());
			inboxs = ApiService.getInboxData(username, inboxUrl, inboxActivity);
		} catch (CommunicationException e) {
			e.printStackTrace();
		} catch (AuthorizationException e) {
			e.printStackTrace();
		} catch (AuthenticationException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return inboxs;
	}

	@Override
	public void onRefresh() {
		inboxListHandler.postDelayed(new Runnable() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Runnable#run()
			 */
			@Override
			public void run() {
				// ������
				Inbox inbox = new Inbox();
				inbox.setDescription("���嶰������");
				inbox.setApp("���������ɷ����޹�˾Maximo��Դ������񣬻�ӭʹ�ã�");
				inboxs.add(0, inbox);
				inboxAdapter.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);
	}

	@Override
	public void onLoadMore() {
		inboxListHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO ����webservice���󣬻�ȡ��һ������,��Ϊ������û����Ӧ�߼�����ʹ�ü�����
				for (int i = 0; i < 20; i++) {
					// ������
					Inbox inbox = new Inbox();
					inbox.setDescription("���嶰������");
					inbox.setApp("���������ɷ����޹�˾Maximo��Դ������񣬻�ӭʹ�ã�");
					inboxs.add(inbox);
				}
				inboxAdapter.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);
	}

	private void onLoad() {
		inboxList.stopRefresh();
		inboxList.stopLoadMore();
		inboxList.setRefreshTime("2013/5/1");
	}
}
