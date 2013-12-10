package com.google.zxing.client.android.adapter;

import java.util.ArrayList;
import java.util.List;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.adapter.InboxsAdapter.ListItemView;
import com.google.zxing.client.android.constant.StringContants;
import com.google.zxing.client.android.vo.Inbox;
import com.google.zxing.client.android.vo.PR;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * pr�б�������
 * 
 * @author ���嶰
 * 
 */
public class PRListAdapter extends BaseAdapter {

	private List<PR> listItems;// ���ݼ���
	private LayoutInflater listContainer;// ��ͼ����
	private int itemViewResource;// �Զ�������ͼԴ
	private Activity context;
	private PopupWindow popupWindow;
	private View contentView;// popwindow������ͼ
	private ArrayList<String> popGroups;// popwindow�����б����ݼ���
	private ListView popListView;// popwindow�б�
	private PopGroupAdapter popGroupAdapter;

	static class ListItemView { // �Զ���ؼ�����
		public TextView appTitle;
		public TextView description;
		public Button img;
	}

	public PRListAdapter(Activity context, ArrayList<PR> prList,
			int inboxListitem) {
		this.listContainer = LayoutInflater.from(context); // ������ͼ����������������
		this.context = context;
		this.itemViewResource = inboxListitem;
		this.listItems = prList;
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
	public View getView(int position, View convertView, ViewGroup parent) {
		// �Զ�����ͼ
		ListItemView listItemView = null;
		if (convertView == null) {
			// ��ȡlist_item�����ļ�����ͼ
			listItemView = new ListItemView();
			convertView = listContainer.inflate(itemViewResource, null);
			// ��ȡ�ؼ�����
			listItemView.appTitle = (TextView) convertView
					.findViewById(R.id.pr_list_name);
			listItemView.description = (TextView) convertView
					.findViewById(R.id.pr_list_description);
			listItemView.img = (Button) convertView
					.findViewById(R.id.pr_list_operate);

			// ���ÿؼ�����convertView
			convertView.setTag(listItemView);
		} else {
			listItemView = (ListItemView) convertView.getTag();
		}

		// �������ֺ�ͼƬ
		PR pr = listItems.get(position);

		// �б������ʾ������װ
		StringBuffer titleContent = new StringBuffer();
		titleContent.append(pr.getPrnum());
		titleContent.append(StringContants.SPLIT_LINE);
		titleContent.append(pr.getCutype());
		titleContent.append(StringContants.SPLIT_LINE);
		titleContent.append(pr.getUdremark1());
		listItemView.appTitle.setText(titleContent.toString());

		// �б�������ʾ������װ
		StringBuffer dspContent = new StringBuffer();
		dspContent.append(pr.getDescription());
		// TODO ʱ��ؼ���װ
		// dspContent.append(StringContants.SPLIT_LINE);
		// String uddate = pr.getUddate1() == null ? "" : pr.getStatusdate()
		// .substring(0, 9);
		// dspContent.append(uddate);
		listItemView.description.setText(dspContent.toString());

		listItemView.img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				showPopwindow(v);
			}
		});

		return convertView;
	}

	/**
	 * ��ʾ��������
	 * 
	 * @param parent
	 *            ����Ŀؼ�
	 
	private void showPopwindow(View parent) {
		if (popupWindow == null) {
			LayoutInflater mLayoutInflater = LayoutInflater.from(context);
			contentView = mLayoutInflater.inflate(R.layout.group_list, null);

			popListView = (ListView) contentView.findViewById(R.id.lv_group);

			// ��������
			popGroups = new ArrayList<String>();

			popGroups.add("��������");

			popGroupAdapter = new PopGroupAdapter(context, popGroups);
			popListView.setAdapter(popGroupAdapter);

			popupWindow = new PopupWindow(contentView, context
					.getWindowManager().getDefaultDisplay().getWidth() / 2, 250);
		}
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		popupWindow.setFocusable(true);

		// ��ʾ��λ��Ϊ:��Ļ�Ŀ�ȵ�1/16
		int xPos = context.getWindowManager().getDefaultDisplay().getWidth() / 16;
		popupWindow.showAsDropDown(parent, xPos, 0);
		popListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (popupWindow != null) {
					popupWindow.dismiss();
				}
				switch (position) {
				case 0:
					// // ����pr����
					// appContext.setPrFlag(PR_CREATE_FLAG);
					// createPR();
					// break;
				}
			}

		});

	}*/
}
