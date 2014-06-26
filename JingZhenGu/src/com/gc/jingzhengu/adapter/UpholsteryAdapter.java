/**
 * Project Name:JingZhenGu
 * File Name:UpholsteryAdapter.java
 * Package Name:com.gc.jingzhengu.adapter
 * Date:2014-6-6下午12:14:35
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.adapter;

import java.util.ArrayList;

import com.gc.jingzhengu.R;
import com.gc.jingzhengu.uitls.MessageUtils;
import com.gc.jingzhengu.vo.Upholstery;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ClassName:UpholsteryAdapter <br/>
 * Function: 内饰损伤列表适配器. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-6 下午12:14:35 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class UpholsteryAdapter extends BaseAdapter
{
	private LayoutInflater inflater;

	private ArrayList<Upholstery> upholsteryItems;

	private ArrayList<View> views = new ArrayList<View>();

	private Context context;

	private int oldPostion = -1;

	private Handler handler;

	public UpholsteryAdapter(FragmentActivity activity,
			ArrayList<Upholstery> upholsteryItems, Handler drawerHandler)
	{
		inflater = activity.getLayoutInflater();
		this.context = activity;
		this.upholsteryItems = upholsteryItems;
		this.handler = drawerHandler;
	}

	public int getCount()
	{

		return upholsteryItems.size();
	}

	public Object getItem(int position)
	{
		return upholsteryItems.get(position);
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
		Upholstery upholstery = getSelectedPosition(position);
		holder = new ViewHolder();
		holder.flagPic = (ImageView) view.findViewById(R.id.flag);
		holder.name = (TextView) view.findViewById(R.id.facade_name);
		holder.tick = (ImageView) view.findViewById(R.id.tick);
		// 当前填充数据的位置
		holder.position = position;
		// 设置标记，在列表点击操作后对列表中flag进行后续操作
		view.setTag(holder);
		holder.flagPic.setVisibility(upholstery.getFlagPic());
		holder.name.setText(upholstery.getUpholsteryName());
		holder.tick.setVisibility(upholstery.getTick());

		return view;
	}

	Upholstery getSelectedPosition(int position)
	{
		return ((Upholstery) getItem(position));
	}

	private class ViewHolder
	{
		ImageView flagPic;
		TextView name;
		ImageView tick;
		int position;
	}

	OnClickListener clickListener = new OnClickListener()
	{

		@Override
		public void onClick(View v)
		{
			// 取消列表内容上一次点击的flag被选中状态
			for (View view : views)
			{
				ImageView img = (ImageView) view.findViewById(R.id.flag);
				if (img.getVisibility() == View.VISIBLE)
				{
					img.setVisibility(View.INVISIBLE);
				}
			}
			int position = ((ViewHolder) v.getTag()).position;
			// System.out.println("vvv" + position);
			// 当前被点击内容flag状态为选中
			((ViewHolder) v.getTag()).flagPic.setVisibility(View.VISIBLE);

			// 当点击内容被改变同时也改变缓冲区中的内容状态
			upholsteryItems.get(position).setFlagPic(View.VISIBLE);
			// 根据上一次点击的位置，修改缓冲区数据的状态
			if (oldPostion != -1 && oldPostion != position)
			{
				upholsteryItems.get(oldPostion).setFlagPic(View.INVISIBLE);
			}
			oldPostion = position;

			// 判断当前被点击项是否之前修改过，如果修改过则不弹出滑动列表,隐藏tick图片
			if (upholsteryItems.get(position).getTick() == View.VISIBLE)
			{
				upholsteryItems.get(position).setTick(View.INVISIBLE);
				upholsteryItems.get(position).setFlagPic(View.INVISIBLE);
				notifyDataSetChanged();
				MessageUtils.sendMessage(handler, R.id.no_select, position);
			} else
			{
				handler.sendEmptyMessage(((ViewHolder) v.getTag()).position);
			}
		}
	};
}
