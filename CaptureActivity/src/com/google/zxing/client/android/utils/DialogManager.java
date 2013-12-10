package com.google.zxing.client.android.utils;

import java.util.ArrayList;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.view.dialog.SelectPopDialog;
import com.google.zxing.client.android.vo.SelectDialogEntity;

import android.app.Activity;
import android.app.Dialog;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;

/**
 * dialog����������
 * 
 * @author ���嶰
 * 
 * 
 */
public final class DialogManager {

	/**
	 * ����dialog
	 * 
	 * @param dialog
	 */
	public void dismissDialog(Dialog dialog) {
		dialog.dismiss();
	}

	/**
	 * ���dialog�ⲿ����dialog
	 * 
	 * @param dialog
	 */
	public void outsideDismiss(Dialog dialog) {
		dialog.setCanceledOnTouchOutside(true);
	}

	/**
	 * ����Dialog�������ڴ�С
	 * 
	 * @param context
	 * @param dialog
	 */
	public void setDialogPopScale(Activity activity, Dialog dialog, int resId) {
		Display display = activity.getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		Window window = dialog.getWindow();
		window.setLayout(width * 2 / 3, height / 2);
		window.setGravity(Gravity.CENTER);
		window.setBackgroundDrawableResource(resId);
	}

	private Dialog showDialog(SelectDialogEntity dialogEntity,
			ArrayList<String> cum) {
		SelectPopDialog dialog = new SelectPopDialog(dialogEntity, R.style.dialog, cum);
		dialog.show();
		return dialog;
	}

	/**
	 * @Title: show
	 * @Description: TODO
	 * @param @param dialogEntity
	 * @param @param dialogListBg
	 * @param @return
	 * @return PopDialog
	 * @throws
	 */
	public void show(SelectDialogEntity dialogEntity, int resId,
			ArrayList<String> cum) {
		Dialog dialog = showDialog(dialogEntity, cum);
		setDialogPopScale((Activity) dialogEntity.getContext(), dialog, resId);
	}

}
