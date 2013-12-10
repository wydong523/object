/**   
 * @Title: WorkorderPlanMaterialAdapter.java 
 * @Package com.google.zxing.client.android.adapter 
 * @version V1.0   
 */
package com.google.zxing.client.android.adapter;

import java.util.ArrayList;
import java.util.List;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.adapter.WorkOrderPlanTaskAdapter.ListItemView;
import com.google.zxing.client.android.constant.StringContants;
import com.google.zxing.client.android.vo.WorkorderPlanMaterial;
import com.google.zxing.client.android.vo.WorkorderPlanTask;

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
 * @author 汪渝栋
 * @date 2013-10-24 下午2:46:17
 */
public class WorkorderPlanMaterialAdapter extends BaseAdapter {
	private List<WorkorderPlanMaterial> materialList; // 数据集合
	private LayoutInflater listContainer;// 视图容器
	private int resId;// 自定义项视图源
	private Activity activity;

	static class ListItemView { // 自定义控件集合
		public TextView appTitle;
		public TextView description;
		public Button img;
	}

	public WorkorderPlanMaterialAdapter(Activity activity,
			ArrayList<WorkorderPlanMaterial> materialList, int resId) {
		this.listContainer = LayoutInflater.from(activity); // 创建视图容器并设置上下文
		this.activity = activity;
		this.resId = resId;
		this.materialList = materialList;
	}

	/*
	 * (非 Javadoc) <p>Title: getCount</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return materialList.size();
	}

	/*
	 * (非 Javadoc) <p>Title: getItem</p> <p>Description: </p>
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
	 * (非 Javadoc) <p>Title: getItemId</p> <p>Description: </p>
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
	 * (非 Javadoc) <p>Title: getView</p> <p>Description: </p>
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
		WorkorderPlanMaterial content = materialList.get(position);

		// 列表标题显示内容组装
		StringBuffer titleContent = new StringBuffer();
		titleContent.append(content.getDescription());

		// 列表描述显示内容组装
		StringBuffer dspContent = new StringBuffer();
		dspContent.append(content.getItemqty());
		dspContent.append(StringContants.SPLIT_LINE);
		dspContent.append(content.getLocation());

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
