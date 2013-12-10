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
 * PR行列表适配器
 * 
 * @author 汪渝栋
 * 
 */
public class PRLineListAdapter extends BaseAdapter {

	private List<PRLine> listItems;// 数据集合
	private LayoutInflater listContainer;// 视图容器
	private int itemViewResource;// 自定义项视图源

	static class ListItemView { // 自定义控件集合
		public TextView title;
		public TextView description;
		public Button img;
	}

	public PRLineListAdapter(Context context, ArrayList<PRLine> prLineList,
			int listitem) {
		this.listContainer = LayoutInflater.from(context); // 创建视图容器并设置上下文
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
		// 自定义视图
		ListItemView listItemView = null;
		if (convertView == null) {
			// 获取list_item布局文件的视图
			listItemView = new ListItemView();
			convertView = listContainer.inflate(itemViewResource, null);
			// 获取控件对象
			listItemView.title = (TextView) convertView
					.findViewById(R.id.pr_line_list_title);
			listItemView.description = (TextView) convertView
					.findViewById(R.id.pr_line_list_description);
			listItemView.img = (Button)convertView.findViewById(R.id.pr_line_list_img);
			// 设置控件集到convertView
			convertView.setTag(listItemView);
		} else {
			listItemView = (ListItemView) convertView.getTag();
		}

		// 设置文字和图片
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
