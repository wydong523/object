package com.google.zxing.client.android.adapter;

import java.util.ArrayList;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.app.AppContext;
import com.google.zxing.client.android.ui.CaptureActivity;
import com.google.zxing.client.android.ui.GenerateActivity;
import com.google.zxing.client.android.ui.PRListActivity;
import com.google.zxing.client.android.ui.WorkorderListActivity;
import com.google.zxing.client.android.vo.Workorder;

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
 * OperateGrid适配器
 * 
 * @author 汪渝栋
 * 
 */
public class OperateGridAdapter extends BaseAdapter {

	private Context mContext;

	// 应用对应图片
	private ArrayList<Integer> imgs;

	// 应用名
	private ArrayList<String> appNames;

	public OperateGridAdapter(Context context, ArrayList<String> appNames,
			ArrayList<Integer> imgs) {
		this.mContext = context;
		this.appNames = appNames;
		this.imgs = imgs;
	}

	@Override
	public int getCount() {
		return appNames.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.item, null);
			final ImageView img = (ImageView) convertView
					.findViewById(R.id.ItemImage);
			img.setTag(imgs.get(position));// 利用img的资源标识作为Tag
			img.setOnClickListener(new OnClickListener() {
				// 判断跳转标记
				@Override
				public void onClick(View v) {
					int tag = (Integer) img.getTag();
					Intent intent = null;
					switch (tag) {
					// 采购申请
					case R.drawable.cgsq:
						intent = new Intent(mContext, PRListActivity.class);
						mContext.startActivity(intent);
						break;

					case R.drawable.gdgz:
						intent = new Intent(mContext,
								WorkorderListActivity.class);
						mContext.startActivity(intent);
						break;
					// 条形码生成
					case R.drawable.zc:
						intent = new Intent(mContext, GenerateActivity.class);
						mContext.startActivity(intent);
						break;
					// 条形码扫描
					case R.drawable.yg:
						intent = new Intent(mContext, CaptureActivity.class);
						mContext.startActivity(intent);
						break;
					}
				}
			});
			img.setImageResource(imgs.get(position));
			TextView text = (TextView) convertView.findViewById(R.id.ItemText);
			text.setTextSize(18);
			text.setText(appNames.get(position));
			return convertView;
		} else {
			return convertView;
		}
	}

}
