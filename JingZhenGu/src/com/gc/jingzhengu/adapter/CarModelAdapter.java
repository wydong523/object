/**
 * Project Name:JingZhenGu
 * File Name:CarTypeAdapter.java
 * Package Name:com.gc.jingzhengu.adapter
 * Date:2014-5-26上午11:28:41
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gc.jingzhengu.R;
import com.gc.jingzhengu.vo.CarType;
import com.gc.jingzhengu.vo.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ClassName:CarTypeAdapter <br/>
 * Function: TODO 车系列表适配器. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-5-26 上午11:28:41 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class CarModelAdapter extends BaseAdapter
{
	private LayoutInflater inflater;

	private List<Model> list;

	public CarModelAdapter(Context context, ArrayList<Model> list)
	{
		super();
		this.inflater = LayoutInflater.from(context);
		this.list = list;
	}

	@Override
	public int getCount()
	{

		return list.size();
	}

	@Override
	public Object getItem(int position)
	{
		return list.get(position);
	}

	@Override
	public long getItemId(int position)
	{

		return position;
	}

	@Override
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
			holder.alphaLeftLine = (ImageView) convertView
					.findViewById(R.id.left_line);
			holder.name = (TextView) convertView
					.findViewById(R.id.car_list_content_name);
			holder.alpha = (TextView) convertView
					.findViewById(R.id.car_list_content_alpha);
			convertView.setTag(holder);
		} else
		{
			holder = (ViewHolder) convertView.getTag();
		}

		holder.name.setText(list.get(position).getName());

		// if (list.get(position).getmCarFactory() != null)
		// {
		// holder.alpha.setText(list.get(position).getmCarFactory());
		// holder.alphaLeftLine.setVisibility(View.VISIBLE);
		// holder.alpha.setVisibility(View.VISIBLE);
		// holder.iamge.setVisibility(View.GONE);
		// holder.line.setVisibility(View.GONE);
		// } else
		// {
		// holder.name.setText(list.get(position).getmCarType());
		// }

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
