package com.google.zxing.client.android.adapter;

import java.util.ArrayList;
import java.util.List;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.adapter.PRListAdapter.ListItemView;
import com.google.zxing.client.android.constant.StringContants;
import com.google.zxing.client.android.vo.PR;
import com.google.zxing.client.android.vo.Workorder;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * �����б�������
 * 
 * @author ���嶰
 * 
 */
public class WorkorderAdapter extends BaseAdapter {

	private List<Workorder> listItems;// ���ݼ���
	private LayoutInflater listContainer;// ��ͼ����
	private int resId;// �Զ�������ͼԴ
	private Activity context;

	static class ListItemView { // �Զ���ؼ�����
		public TextView appTitle;
		public TextView description;
		public Button img;
	}

	public WorkorderAdapter(Activity context, ArrayList<Workorder> prList,
			int resId) {
		this.listContainer = LayoutInflater.from(context); // ������ͼ����������������
		this.context = context;
		this.resId = resId;
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
			convertView = listContainer.inflate(resId, null);
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
		Workorder workorder = listItems.get(position);

		// �б������ʾ������װ
		StringBuffer titleContent = new StringBuffer();
		titleContent.append(workorder.getDescription());
		listItemView.appTitle.setText(titleContent.toString());
		
		// �б�������ʾ������װ
		StringBuffer dspContent = new StringBuffer();
		dspContent.append(workorder.getWonum() + "/"
				+ workorder.getReportdate());
		listItemView.description.setText(dspContent.toString());

		listItemView.img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// showPopwindow(v);
			}
		});

		return convertView;
	}

}
