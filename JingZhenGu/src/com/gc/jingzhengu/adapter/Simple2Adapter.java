/**
 * Project Name:JingZhenGu
 * File Name:ProvinceAdapter.java
 * Package Name:com.gc.jingzhengu.adapter
 * Date:2014-6-3上午10:34:23
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gc.jingzhengu.R;
import com.gc.jingzhengu.vo.SimpleList;

/**
 * ClassName:ProvinceAdapter <br/>
 * Function: 带数据缓存的list适配<br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-3 上午10:34:23 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class Simple2Adapter extends BaseAdapter
{
	private LayoutInflater inflater;

	private List<SimpleList> list;

	private ArrayList<View> views = new ArrayList<View>();

	public Simple2Adapter(Context context, List<SimpleList> list)
	{
		super();
		this.inflater = LayoutInflater.from(context);
		this.list = list;
	}

	public int getCount()
	{

		return list.size();
	}

	public Object getItem(int position)
	{
		return list.get(position);
	}

	public long getItemId(int position)
	{

		return position;
	}

	SimpleList getSelectedPosition(int position)
	{
		return ((SimpleList) getItem(position));
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder;
		View view = convertView;
		if (view == null)
		{
			view = inflater
					.inflate(R.layout.simple_list_content, parent, false);
		}
		holder = new ViewHolder();
		holder.iamge = (ImageView) view
				.findViewById(R.id.car_list_content_image);
		holder.line = (ImageView) view.findViewById(R.id.line);
		holder.name = (TextView) view.findViewById(R.id.car_list_content_name);
		view.setTag(holder);
		views.add(view);
		SimpleList simpleList = getSelectedPosition(position);
		holder.name.setText(simpleList.getName());
		holder.name.setTextColor(simpleList.getColor());
		return view;
	}

	private class ViewHolder
	{
		ImageView iamge;
		ImageView line;
		ImageView alphaLeftLine;
		TextView name;
		TextView alpha;
	}
}
