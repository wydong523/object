package com.google.zxing.client.android.adapter;

import java.net.MalformedURLException;
import java.util.List;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.app.AppContext;
import com.google.zxing.client.android.constant.StringContants;
import com.google.zxing.client.android.exceptions.AuthenticationException;
import com.google.zxing.client.android.exceptions.AuthorizationException;
import com.google.zxing.client.android.exceptions.CommunicationException;
import com.google.zxing.client.android.vo.Inbox;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Inbox�б�������
 * 
 * @author ���嶰
 */
public class InboxsAdapter extends BaseAdapter {

	public static final String TAG = InboxsAdapter.class.getSimpleName();

	private List<Inbox> listItems;// ���ݼ���
	private LayoutInflater listContainer;// ��ͼ����
	private int itemViewResource;// �Զ�������ͼԴ
	private AppContext appContext;
	private Handler inboxHandler;

	static class ListItemView { // �Զ���ؼ�����
		public TextView description;
		public TextView appTitle;
		public ImageView img;
	}

	/**
	 * ʵ����Adapter
	 * 
	 * @param context
	 * @param data
	 * @param resource
	 * @return
	 */
	public InboxsAdapter(Context context, List<Inbox> data, int resource,
			AppContext appContext, Handler inboxHandler) {
		this.listContainer = LayoutInflater.from(context); // ������ͼ����������������
		this.itemViewResource = resource;
		this.listItems = data;
		this.appContext = appContext;
		this.inboxHandler = inboxHandler;
	}

	public int getCount() {
		return listItems.size();
	}

	public Object getItem(int arg0) {
		return null;
	}

	public long getItemId(int arg0) {
		return 0;
	}

	/**
	 * ListView Item����
	 */
	public View getView(final int position, View convertView, ViewGroup parent) {
		// Log.d("method", "getView");

		// �Զ�����ͼ
		ListItemView listItemView = null;
		if (convertView == null) {
			// ��ȡlist_item�����ļ�����ͼ
			listItemView = new ListItemView();
			convertView = listContainer.inflate(itemViewResource, null);
			// ��ȡ�ؼ�����
			listItemView.description = (TextView) convertView
					.findViewById(R.id.inbox_app_description);
			listItemView.appTitle = (TextView) convertView
					.findViewById(R.id.inbox_app_name);
			listItemView.img = (ImageView) convertView
					.findViewById(R.id.inbox_app_img);

			// ���ÿؼ�����convertView
			convertView.setTag(listItemView);
		} else {
			listItemView = (ListItemView) convertView.getTag();
		}

		// �������ֺ�ͼƬ
		final Inbox inbox = listItems.get(position);

		// �б������ʾ������װ
		StringBuffer titleContent = new StringBuffer();
		titleContent.append(inbox.getDuedate());
		titleContent.append(StringContants.SPLIT_LINE);
		titleContent.append(inbox.getOwnerid());
		titleContent.append(StringContants.SPLIT_LINE);
		titleContent.append(inbox.getProcessname());
		listItemView.appTitle.setText(titleContent.toString());

		// �б�������ʾ������װ
		StringBuffer dspContent = new StringBuffer();
		dspContent.append(inbox.getAssignstatus());
		dspContent.append(StringContants.SPLIT_LINE);
		String startDate = inbox.getStartdate() == null ? "" : inbox
				.getStartdate().substring(0, 9);
		dspContent.append(startDate);
		dspContent.append(StringContants.SPLIT_LINE);
		dspContent.append(inbox.getDescription());
		listItemView.description.setText(dspContent.toString());

		listItemView.img.setImageResource(R.drawable.sp);
		listItemView.img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							String result = appContext.spwfro(inbox);
							if (result.contains("TRUE")) {
								Message msg = new Message();
								msg.arg1 = position;
								inboxHandler.sendMessage(msg);
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
		});

		return convertView;
	}
}
