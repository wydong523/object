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
 * 工单列表适配器
 * 
 * @author 汪渝栋
 * 
 */
public class WorkorderAdapter extends BaseAdapter {

	private List<Workorder> listItems;// 数据集合
	private LayoutInflater listContainer;// 视图容器
	private int resId;// 自定义项视图源
	private Activity context;

	static class ListItemView { // 自定义控件集合
		public TextView appTitle;
		public TextView description;
		public Button img;
	}

	public WorkorderAdapter(Activity context, ArrayList<Workorder> prList,
			int resId) {
		this.listContainer = LayoutInflater.from(context); // 创建视图容器并设置上下文
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
	 * ListView Item设置
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		// 自定义视图
		ListItemView listItemView = null;
		if (convertView == null) {
			// 获取list_item布局文件的视图
			listItemView = new ListItemView();
			convertView = listContainer.inflate(resId, null);
			// 获取控件对象
			listItemView.appTitle = (TextView) convertView
					.findViewById(R.id.pr_list_name);
			listItemView.description = (TextView) convertView
					.findViewById(R.id.pr_list_description);
			listItemView.img = (Button) convertView
					.findViewById(R.id.pr_list_operate);

			// 设置控件集到convertView
			convertView.setTag(listItemView);
		} else {
			listItemView = (ListItemView) convertView.getTag();
		}

		// 设置文字和图片
		Workorder workorder = listItems.get(position);

		// 列表标题显示内容组装
		StringBuffer titleContent = new StringBuffer();
		titleContent.append(workorder.getDescription());
		listItemView.appTitle.setText(titleContent.toString());
		
		// 列表描述显示内容组装
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
