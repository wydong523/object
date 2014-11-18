/**
 * Project Name:JingZhenGu
 * File Name:QueryCarAdapter.java
 * Package Name:com.gc.jingzhengu.adapter
 * Date:2014-8-5下午5:31:13
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.adapter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.example.jzgpinggushi.R;
import com.gc.jzgpinggushi.uitls.StringUtil;
import com.gc.jzgpinggushi.vo.QueryCar;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ClassName:QueryCarAdapter <br/>
 * Function: 查车列表适配器. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-8-5 下午5:31:13 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class QueryCarAdapter extends BaseAdapter
{

	private LayoutInflater inflater;

	private List<QueryCar> list;

	protected ImageLoader imageLoader = ImageLoader.getInstance();

	private ImageLoadingListener mAnimateFirstListener = new AnimateFirstDisplayListener();

	private DisplayImageOptions mOptions;

	public QueryCarAdapter(Context context, List<QueryCar> list)
	{
		super();
		this.inflater = LayoutInflater.from(context);
		this.list = list;
		mOptions = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.no_car)
				.showImageForEmptyUri(R.drawable.no_car)
				.showImageOnFail(R.drawable.no_car).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(0)).build();
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
			convertView = inflater.inflate(R.layout.querycar_list_item, null);
			holder = new ViewHolder();
			holder.carUrl = (ImageView) convertView.findViewById(R.id.car_url);
			holder.carFullName = (TextView) convertView
					.findViewById(R.id.car_full_name);
			holder.carLv = (TextView) convertView.findViewById(R.id.car_lv);
			holder.carMileage = (TextView) convertView
					.findViewById(R.id.car_mileage);
			holder.carPrice = (TextView) convertView
					.findViewById(R.id.car_price);
			holder.carSignTime = (TextView) convertView
					.findViewById(R.id.car_sign_time);
			convertView.setTag(holder);
		} else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		// holder.carUrl.setBackgroundResource(list.get(position).getColorPic());
		imageLoader.displayImage(list.get(position).getImgUrl(), holder.carUrl,
				mOptions, mAnimateFirstListener);
		holder.carFullName.setText(list.get(position).getFullName());
		holder.carLv.setText(StringUtil.getlv(Integer.valueOf(list.get(position).getCarLevel())));
		holder.carMileage.setText(StringUtil.getMileage(Integer.valueOf(list.get(position).getMileage())));
		holder.carPrice.setText(list.get(position).getMarketPrice() + "万");
		holder.carSignTime.setText(list.get(position).getRegistDate() + "上牌");

		return convertView;
	}

	private class ViewHolder
	{
		ImageView carUrl;
		TextView carLv;
		TextView carFullName;
		TextView carMileage;
		TextView carSignTime;
		TextView carPrice;
	}

	private static class AnimateFirstDisplayListener extends
			SimpleImageLoadingListener
	{

		static final List<String> displayedImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage)
		{
			if (loadedImage != null)
			{
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay)
				{
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}

}
