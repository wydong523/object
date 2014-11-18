/**
 * Project Name:JZGPingGuShi
 * File Name:GridAdapter.java
 * Package Name:com.gc.jzgpinggushi.adapter
 * Date:2014-9-4上午9:55:40
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.adapter;

import java.util.ArrayList;

import com.example.jzgpinggushi.R;
import com.gc.jzgpinggushi.app.ActivityHelp;
import com.gc.jzgpinggushi.ui.IndexCarActivity;
import com.gc.jzgpinggushi.ui.NewCarListActivity;
import com.gc.jzgpinggushi.ui.OfferListActivity;
import com.gc.jzgpinggushi.ui.OfferSuccessListActivity;
import com.gc.jzgpinggushi.ui.UserApplyActiviy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ClassName:GridAdapter <br/>
 * Function: 操作GridView适配器. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-4 上午9:55:40 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class GridAdapter extends BaseAdapter
{

	private Context mContext;

	// 操作图片
	private ArrayList<Integer> mImgs;

	// 操作名
	private ArrayList<String> mNames;

	public GridAdapter(Context context, ArrayList<String> names,
			ArrayList<Integer> imgs)
	{
		this.mContext = context;
		this.mNames = names;
		this.mImgs = imgs;
	}

	@Override
	public int getCount()
	{
		return mNames.size();
	}

	@Override
	public Object getItem(int position)
	{
		return position;
	}

	@Override
	public long getItemId(int position)
	{
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent)
	{
		if (convertView == null)
		{
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.grid_item, null);
			final ImageView img = (ImageView) convertView
					.findViewById(R.id.ItemImage);
			img.setTag(mImgs.get(position));
			// img.setOnClickListener(new OnClickListener()
			// {
			// @Override
			// public void onClick(View v)
			// {
			// int tag = (Integer) img.getTag();
			// switch (tag) {
			// // 查看车源
			// case R.drawable.cheyuan:
			// ActivityHelp.startActivity(mContext,
			// IndexCarActivity.class);
			// break;
			// // 已出价车辆
			// case R.drawable.yichujia:
			// ActivityHelp.startActivity(mContext,
			// OfferListActivity.class);
			// break;
			// // 出价成功
			// case R.drawable.chenggong:
			// ActivityHelp.startActivity(mContext,
			// OfferSuccessListActivity.class);
			// break;
			// // 最新车源
			// case R.drawable.zuixin:
			// ActivityHelp.startActivity(mContext,
			// NewCarListActivity.class);
			// break;
			// // 用户申请
			// case R.drawable.shenqing:
			// ActivityHelp.startActivity(mContext,
			// UserApplyActiviy.class);
			// break;
			// }
			// }
			// });
			img.setImageResource(mImgs.get(position));
			TextView text = (TextView) convertView.findViewById(R.id.ItemText);
			text.setTextSize(18);
			text.setText(mNames.get(position));
			return convertView;
		} else
		{
			return convertView;
		}
	}
}
