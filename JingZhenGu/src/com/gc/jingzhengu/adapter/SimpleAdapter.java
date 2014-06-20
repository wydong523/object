/**
 * Project Name:JingZhenGu
 * File Name:ProvinceAdapter.java
 * Package Name:com.gc.jingzhengu.adapter
 * Date:2014-6-3上午10:34:23
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gc.jingzhengu.R;

/**
 * ClassName:ProvinceAdapter <br/>
 * Function: 省、市列表适配 <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-3 上午10:34:23 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class SimpleAdapter extends BaseAdapter
{
	private LayoutInflater inflater;

	private List<String> list;

	public SimpleAdapter(Context context, List<String> list)
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

	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder;
		if (convertView == null)
		{
			convertView = inflater.inflate(R.layout.simple_list_content, null);
			holder = new ViewHolder();
			holder.iamge = (ImageView) convertView
					.findViewById(R.id.car_list_content_image);
			holder.line = (ImageView) convertView.findViewById(R.id.line);
			holder.name = (TextView) convertView
					.findViewById(R.id.car_list_content_name);
			convertView.setTag(holder);
		} else
		{
			holder = (ViewHolder) convertView.getTag();
		}

		holder.name.setText(list.get(position));

		return convertView;
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
