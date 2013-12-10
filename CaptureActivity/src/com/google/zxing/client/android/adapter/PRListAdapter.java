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
 * pr列表适配器
 * 
 * @author 汪渝栋
 * 
 */
public class PRListAdapter extends BaseAdapter {

	private List<PR> listItems;// 数据集合
	private LayoutInflater listContainer;// 视图容器
	private int itemViewResource;// 自定义项视图源
	private Activity context;
	private PopupWindow popupWindow;
	private View contentView;// popwindow弹出视图
	private ArrayList<String> popGroups;// popwindow弹出列表数据集合
	private ListView popListView;// popwindow列表
	private PopGroupAdapter popGroupAdapter;

	static class ListItemView { // 自定义控件集合
		public TextView appTitle;
		public TextView description;
		public Button img;
	}

	public PRListAdapter(Activity context, ArrayList<PR> prList,
			int inboxListitem) {
		this.listContainer = LayoutInflater.from(context); // 创建视图容器并设置上下文
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
	 * ListView Item设置
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		// 自定义视图
		ListItemView listItemView = null;
		if (convertView == null) {
			// 获取list_item布局文件的视图
			listItemView = new ListItemView();
			convertView = listContainer.inflate(itemViewResource, null);
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
		PR pr = listItems.get(position);

		// 列表标题显示内容组装
		StringBuffer titleContent = new StringBuffer();
		titleContent.append(pr.getPrnum());
		titleContent.append(StringContants.SPLIT_LINE);
		titleContent.append(pr.getCutype());
		titleContent.append(StringContants.SPLIT_LINE);
		titleContent.append(pr.getUdremark1());
		listItemView.appTitle.setText(titleContent.toString());

		// 列表描述显示内容组装
		StringBuffer dspContent = new StringBuffer();
		dspContent.append(pr.getDescription());
		// TODO 时间控件组装
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
	 * 显示弹出窗口
	 * 
	 * @param parent
	 *            点击的控件
	 
	private void showPopwindow(View parent) {
		if (popupWindow == null) {
			LayoutInflater mLayoutInflater = LayoutInflater.from(context);
			contentView = mLayoutInflater.inflate(R.layout.group_list, null);

			popListView = (ListView) contentView.findViewById(R.id.lv_group);

			// 加载数据
			popGroups = new ArrayList<String>();

			popGroups.add("创建申请");

			popGroupAdapter = new PopGroupAdapter(context, popGroups);
			popListView.setAdapter(popGroupAdapter);

			popupWindow = new PopupWindow(contentView, context
					.getWindowManager().getDefaultDisplay().getWidth() / 2, 250);
		}
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		popupWindow.setFocusable(true);

		// 显示的位置为:屏幕的宽度的1/16
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
					// // 创建pr操作
					// appContext.setPrFlag(PR_CREATE_FLAG);
					// createPR();
					// break;
				}
			}

		});

	}*/
}
