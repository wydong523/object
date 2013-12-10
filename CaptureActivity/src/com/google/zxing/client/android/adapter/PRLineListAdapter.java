package com.google.zxing.client.android.adapter;

import java.util.ArrayList;
import java.util.List;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.vo.PRLine;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * PR���б�������
 * 
 * @author ���嶰
 * 
 */
public class PRLineListAdapter extends BaseAdapter {

	private List<PRLine> listItems;// ���ݼ���
	private LayoutInflater listContainer;// ��ͼ����
	private int itemViewResource;// �Զ�������ͼԴ

	static class ListItemView { // �Զ���ؼ�����
		public TextView title;
		public TextView description;
		public Button img;
	}

	public PRLineListAdapter(Context context, ArrayList<PRLine> prLineList,
			int listitem) {
		this.listContainer = LayoutInflater.from(context); // ������ͼ����������������
		this.itemViewResource = listitem;
		this.listItems = prLineList;
	}

	@Override
	public int getCount() {
		return listItems.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// �Զ�����ͼ
		ListItemView listItemView = null;
		if (convertView == null) {
			// ��ȡlist_item�����ļ�����ͼ
			listItemView = new ListItemView();
			convertView = listContainer.inflate(itemViewResource, null);
			// ��ȡ�ؼ�����
			listItemView.title = (TextView) convertView
					.findViewById(R.id.pr_line_list_title);
			listItemView.description = (TextView) convertView
					.findViewById(R.id.pr_line_list_description);
			listItemView.img = (Button)convertView.findViewById(R.id.pr_line_list_img);
			// ���ÿؼ�����convertView
			convertView.setTag(listItemView);
		} else {
			listItemView = (ListItemView) convertView.getTag();
		}

		// �������ֺ�ͼƬ
		PRLine prline = listItems.get(position);
		String description = prline.getDescription();
		String orderunit = prline.getOrderunit();
		String udaccepter = prline.getUdaccepter();
		String orderqty = prline.getOrderqty();
		listItemView.title.setText(orderqty+orderunit+"/"+udaccepter);
		listItemView.description.setText(description);

		return convertView;
	}

}
