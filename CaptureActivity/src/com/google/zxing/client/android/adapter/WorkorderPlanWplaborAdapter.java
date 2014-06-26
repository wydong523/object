/**   
 * @Title: WorkorderPlanWplaborAdapter.java 
 * @Package com.google.zxing.client.android.adapter 
 * @version V1.0   
 */
package com.google.zxing.client.android.adapter;

import java.util.ArrayList;
import java.util.List;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.adapter.WorkOrderPlanTaskAdapter.ListItemView;
import com.google.zxing.client.android.constant.StringContants;
import com.google.zxing.client.android.vo.WorkorderPlanTask;
import com.google.zxing.client.android.vo.WorkorderPlanWplabor;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * @Desctiption
 * @author ���嶰
 * @date 2013-10-23 ����4:57:59
 */
public class WorkorderPlanWplaborAdapter extends BaseAdapter {

	private List<WorkorderPlanWplabor> wplaborList; // ���ݼ���
	private LayoutInflater listContainer;// ��ͼ����
	private int resId;// �Զ�������ͼԴ
	private Activity activity;

	static class ListItemView { // �Զ���ؼ�����
		public TextView appTitle;
		public TextView description;
		public Button img;
	}

	public WorkorderPlanWplaborAdapter(Activity activity,
			ArrayList<WorkorderPlanWplabor> wplaborList, int resId) {
		this.listContainer = LayoutInflater.from(activity); // ������ͼ����������������
		this.activity = activity;
		this.resId = resId;
		this.wplaborList = wplaborList;
	}

	/*
	 * (�� Javadoc) <p>Title: getCount</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return wplaborList.size();
	}

	/*
	 * (�� Javadoc) <p>Title: getItem</p> <p>Description: </p>
	 * 
	 * @param position
	 * 
	 * @return
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (�� Javadoc) <p>Title: getItemId</p> <p>Description: </p>
	 * 
	 * @param position
	 * 
	 * @return
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (�� Javadoc) <p>Title: getView</p> <p>Description: </p>
	 * 
	 * @param position
	 * 
	 * @param convertView
	 * 
	 * @param parent
	 * 
	 * @return
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
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
		WorkorderPlanWplabor content = wplaborList.get(position);

		// �б�������ʾ������װ
		StringBuffer titleContent = new StringBuffer();
		titleContent.append(content.getLaborhrs());

		// �б�������ʾ������װ
		StringBuffer dspContent = new StringBuffer();
		dspContent.append(content.getLaborcode());
		dspContent.append(StringContants.SPLIT_LINE);
		dspContent.append(content.getQuantity());

		listItemView.description.setText(dspContent.toString());
		listItemView.appTitle.setText(titleContent.toString());

		listItemView.img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// showPopwindow(v);
			}
		});

		return convertView;
	}

}