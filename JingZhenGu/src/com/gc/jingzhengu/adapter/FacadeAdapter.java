/**
 * Project Name:JingZhenGu
 * File Name:FacadeAdapter.java
 * Package Name:com.gc.jingzhengu.adapter
 * Date:2014-6-5下午5:26:24
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.adapter;

import java.util.ArrayList;
import java.util.List;

import com.gc.jingzhengu.R;
import com.gc.jingzhengu.vo.Facade;
import com.gc.jingzhengu.vo.Position;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ClassName:FacadeAdapter <br/>
 * Function: 外观损伤列表适配. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-5 下午5:26:24 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class FacadeAdapter extends BaseAdapter
{

	private LayoutInflater inflater;

	private ArrayList<Facade> facadeItems;

	private ArrayList<View> views = new ArrayList<View>();

	private Context context;

	private int oldPostion = -1;

	private Handler handler;

	public FacadeAdapter(FragmentActivity activity,
			ArrayList<Facade> facadeItems, Handler drawerHandler)
	{
		inflater = activity.getLayoutInflater();
		this.context = activity;
		this.facadeItems = facadeItems;
		this.handler = drawerHandler;
	}

	public int getCount()
	{

		return facadeItems.size();
	}

	public Object getItem(int position)
	{
		return facadeItems.get(position);
	}

	public long getItemId(int position)
	{

		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder;
		View view = convertView;
		if (view == null)
		{
			view = inflater
					.inflate(R.layout.facade_item_content, parent, false);
			view.setOnClickListener(clickListener);
		}
		// 添加所有试图到arraylist中,当有点击列表内容操作的时候遍历所有试图
		// 把之前点击过的内容的图片隐藏,隐藏图片在点击监听中实现
		views.add(view);
		// 获取列表填充数据
		Facade facade = getSelectedPosition(position);
		holder = new ViewHolder();
		holder.flagPic = (ImageView) view.findViewById(R.id.flag);
		holder.facadeName = (TextView) view.findViewById(R.id.facade_name);
		holder.tick = (ImageView) view.findViewById(R.id.tick);
		// 当前填充数据的位置
		holder.position = position;
		// 设置标记，在列表点击操作后对列表中flag进行后续操作
		view.setTag(holder);
		holder.flagPic.setVisibility(facade.getFlagPic());
		holder.facadeName.setText(facade.getFacadeName());
		holder.tick.setVisibility(facade.getTick());

		return view;
	}

	Facade getSelectedPosition(int position)
	{
		return ((Facade) getItem(position));
	}

	private class ViewHolder
	{
		ImageView flagPic;
		TextView facadeName;
		int position;
		ImageView tick;
	}

	OnClickListener clickListener = new OnClickListener()
	{

		@Override
		public void onClick(View v)
		{
			int position = ((ViewHolder) v.getTag()).position;
			// 取消列表内容上一次点击的flag被选中状态
			for (View view : views)
			{
				ImageView img = (ImageView) view.findViewById(R.id.flag);
				if (img.getVisibility() == View.VISIBLE)
				{
					img.setVisibility(View.INVISIBLE);
				}
			}
			// System.out.println("vvv" + ((ViewHolder) v.getTag()).position);
			// 当前被点击内容flag状态为选中
			((ViewHolder) v.getTag()).flagPic.setVisibility(View.VISIBLE);

			// 当点击内容被改变同时也改变缓冲区中的内容状态
			facadeItems.get(position).setFlagPic(View.VISIBLE);
			// 根据上一次点击的位置，修改缓冲区数据的状态
			if (oldPostion != -1)
			{
				facadeItems.get(oldPostion).setFlagPic(View.INVISIBLE);
			}
			oldPostion = position;

			// 判断是否当前位置选项是否被修改过
			if (facadeItems.get(position).getTick() == View.VISIBLE)
			{
				facadeItems.get(position).setTick(View.INVISIBLE);
				facadeItems.get(position).setFlagPic(View.INVISIBLE);
				notifyDataSetChanged();
				handler.sendEmptyMessage(R.id.no_select);
			} else
			{
				// 根据位置弹出对应窗口
				handler.sendEmptyMessage(position);
			}

		}
	};
}
