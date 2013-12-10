/**   
 * @Title: WorkOrderPlanTaskAdapter.java 
 * @Package com.google.zxing.client.android.adapter 
 * @version V1.0   
 */
package com.google.zxing.client.android.adapter;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.app.AppContext;
import com.google.zxing.client.android.constant.StringContants;
import com.google.zxing.client.android.exceptions.AuthenticationException;
import com.google.zxing.client.android.exceptions.AuthorizationException;
import com.google.zxing.client.android.exceptions.CommunicationException;
import com.google.zxing.client.android.utils.HandlerUtils;
import com.google.zxing.client.android.view.dialog.ApproveDialog;
import com.google.zxing.client.android.vo.Wfaction;
import com.google.zxing.client.android.vo.Workorder;
import com.google.zxing.client.android.vo.WorkorderPlanTask;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
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
 * @date 2013-9-25 下午3:51:54
 */
public class WorkOrderPlanTaskAdapter extends BaseAdapter {

	private List<WorkorderPlanTask> taskList;
	private LayoutInflater listContainer;
	private int resId;
	private Activity activity;
	private AppContext appContext;
	private Handler wfactionHandler;
	private Workorder workorder;

	static class ListItemView {
		public TextView appTitle;
		public TextView description;
		public Button img;
	}

	public WorkOrderPlanTaskAdapter(Workorder workorder, AppContext appContext,
			Activity activity, ArrayList<WorkorderPlanTask> taskList, int resId) {
		this.listContainer = LayoutInflater.from(activity); // 创建视图容器并设置上下文
		this.appContext = appContext;
		this.activity = activity;
		this.resId = resId;
		this.taskList = taskList;
		this.workorder = workorder;
		wfactionHandler = getWfactionHandler();
	}

	/**
	 * @Title: getWfactionHandler
	 * @Description: TODO
	 * @param @return
	 * @return Handler
	 * @throws
	 */
	private Handler getWfactionHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case R.id.wfaction:
					ArrayList<Wfaction> wfactions = (ArrayList<Wfaction>) msg.obj;
					ApproveDialog approveDialog = new ApproveDialog(workorder,appContext,
							activity, R.style.exit_dialog, "流程审批", wfactions);
					approveDialog.show();
					break;

				default:
					break;
				}
			}
		};
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
		return taskList.size();
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
	public View getView(final int position, View convertView, ViewGroup parent) {
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
		final WorkorderPlanTask content = taskList.get(position);

		// 列表标题显示内容组装
		StringBuffer titleContent = new StringBuffer();
		titleContent.append(content.getDescription());

		// 列表描述显示内容组装
		StringBuffer dspContent = new StringBuffer();
		dspContent.append(content.getTaskid());
		dspContent.append(StringContants.SPLIT_LINE);
		dspContent.append(content.getEstdur());

		listItemView.description.setText(dspContent.toString());
		listItemView.appTitle.setText(titleContent.toString());

		listItemView.img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						String className = Wfaction.class.getName();
						String userid = appContext.getLoginUid();
						String pykeyid = workorder.getWorkorderid();
						String appName = "WOTRACK";
						String ownerTable = "WORKORDER";
						Serializable wfaction = null;
						try {
							wfaction = appContext.getAssignNodeActions(
									className, userid, pykeyid, appName,
									ownerTable);
						} catch (MalformedURLException e) {
							e.printStackTrace();
						} catch (CommunicationException e) {
							e.printStackTrace();
						} catch (AuthorizationException e) {
							e.printStackTrace();
						} catch (AuthenticationException e) {
							e.printStackTrace();
						}
						HandlerUtils.sendMessage(wfactionHandler, wfaction,
								R.id.wfaction);
					}
				}).start();

			}
		});

		return convertView;
	}
}
