/**
 * Project Name:JingZhenGu
 * File Name:CarColorAdapter.java
 * Package Name:com.gc.jingzhengu.adapter
 * Date:2014-6-3上午11:58:25
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.adapter;

import java.util.List;

import com.gc.jingzhengu.R;
import com.gc.jingzhengu.vo.CarColor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ClassName:CarColorAdapter <br/>
 * Function: 汽车颜色列表适配 <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-3 上午11:58:25 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class CarColorAdapter extends BaseAdapter
{
	private LayoutInflater inflater;

	private List<CarColor> list;

	public CarColorAdapter(Context context, List<CarColor> list)
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
			convertView = inflater.inflate(R.layout.color_list_content, null);
			holder = new ViewHolder();
			holder.colorPic = (ImageView) convertView
					.findViewById(R.id.car_color_pic);
			holder.colorName = (TextView) convertView
					.findViewById(R.id.car_color_name);
			convertView.setTag(holder);
		} else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.colorPic.setBackgroundResource(list.get(position).getColorPic());
		holder.colorName.setText(list.get(position).getColorName());

		return convertView;
	}

	private class ViewHolder
	{
		ImageView colorPic;
		TextView colorName;
	}
}
