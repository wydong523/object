package com.google.zxing.client.android.view.poplist;

import java.util.ArrayList;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.adapter.PRTitelPoplistAdapter;

import android.app.Service;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;

/**
 * 弹出list样式的PopupWindow
 * 
 * @author 汪渝栋
 * 
 */
public class ListPopWindow implements OnItemClickListener {

	private View contentView;

	private PRTitelPoplistAdapter pRTitelPoplistAdapter;

	private PopupWindow mPopupWindow;

	private ListView listView;

	private WindowManager manager;

	private Handler mulHandler;

	public void showPopwindow(Context context, Handler mulHandler, View parent,
			ArrayList<String> groups, int contentId, int listId) {
		this.mulHandler = mulHandler;
		if (mPopupWindow == null) {
			LayoutInflater mLayoutInflater = LayoutInflater.from(context);
			contentView = mLayoutInflater.inflate(contentId, null);

			listView = (ListView) contentView.findViewById(listId);

			pRTitelPoplistAdapter = new PRTitelPoplistAdapter(context, groups);
			listView.setAdapter(pRTitelPoplistAdapter);

			manager = (WindowManager) context
					.getSystemService(Service.WINDOW_SERVICE);
			mPopupWindow = new PopupWindow(contentView, manager
					.getDefaultDisplay().getWidth() / 4,
					LayoutParams.WRAP_CONTENT);
		}
		mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		mPopupWindow.setFocusable(true);

		// 显示的位置为:屏幕的宽度的1/16
		int xPos = manager.getDefaultDisplay().getWidth() / 16;

		mPopupWindow.showAsDropDown(parent, xPos, 0);

		listView.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> adapterview, View view, int postion,
			long id) {
		Message msg = new Message();
		msg.what = postion;
		mulHandler.sendMessage(msg);
		mPopupWindow.dismiss();
	}
}
