/**
 * Project Name:JZGPingGuShi
 * File Name:UserApplyAdapter.java
 * Package Name:com.gc.jzgpinggushi.adapter
 * Date:2014-10-20下午7:39:32
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.adapter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.example.jzgpinggushi.R;
import com.gc.jzgpinggushi.uitls.StringUtil;
import com.gc.jzgpinggushi.view.SureOrCancleDialog;
import com.gc.jzgpinggushi.vo.UserApply;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * ClassName:UserApplyAdapter <br/>
 * Function: 用户申请适配器. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-10-20 下午7:39:32 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class UserApplyAdapter extends BaseAdapter
{
	private LayoutInflater inflater;

	private List<UserApply> list;

	protected ImageLoader imageLoader = ImageLoader.getInstance();

	private ImageLoadingListener mAnimateFirstListener = new AnimateFirstDisplayListener();

	private DisplayImageOptions mOptions;

	private Context mContext;

	public UserApplyAdapter(Context context, List<UserApply> list)
	{
		super();
		this.mContext = context;
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

	public View getView(final int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder;
		if (convertView == null)
		{
			convertView = inflater.inflate(R.layout.user_apply_list_content,
					null);
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
			holder.myBidPrice = (TextView) convertView
					.findViewById(R.id.myBidPrice);
			holder.contact = (RelativeLayout) convertView
					.findViewById(R.id.contact_btn);
			convertView.setTag(holder);
		} else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		// holder.carUrl.setBackgroundResource(list.get(position).getColorPic());
		imageLoader.displayImage(list.get(position).getImgUrl(), holder.carUrl,
				mOptions, mAnimateFirstListener);
		holder.carFullName.setText(list.get(position).getFullName());
		holder.carLv.setText(StringUtil.getlv(Integer.valueOf(list
				.get(position).getCarLevel())));
		holder.carMileage.setText(StringUtil.getMileage(Integer.valueOf(list
				.get(position).getMileage())));
		holder.carPrice.setText(list.get(position).getMarketPrice() + "万");
		holder.carSignTime.setText(list.get(position).getRegistDate() + "上牌");
		// holder.myBidPrice.setText(list.get(position).getMyBidPrice());
		holder.contact.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				System.out.println("lianxifangshi click!!" + position);
				String sellerPhoneNum = list.get(position).getSellerPhoneNum();
				SureOrCancleDialog sureOrCancleDialog = new SureOrCancleDialog(
						mContext, R.style.exit_dialog, "联系方式", sellerPhoneNum);
				sureOrCancleDialog.show();
			}
		});
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
		TextView myBidPrice;
		RelativeLayout contact;
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
