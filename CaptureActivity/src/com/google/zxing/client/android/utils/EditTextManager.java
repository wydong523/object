package com.google.zxing.client.android.utils;

import android.text.InputType;
import android.widget.EditText;

/**
 * 操作EditText工具类
 * 
 * @author 汪渝栋
 * 
 */
public final class EditTextManager {

	/**
	 * 获取焦距
	 * 
	 * @param editText
	 */
	public void getFocusable(EditText editText) {
		editText.setFocusable(true);
		editText.setFocusableInTouchMode(true);
		editText.requestFocus();
	}

	/**
	 * 取消焦距
	 */
	public void removeFocusable(EditText editText) {
		editText.setFocusable(false);
		editText.setFocusableInTouchMode(false);
	}

	/**
	 * 禁止EditText输入
	 */
	public void forbidInput(EditText editText) {
		editText.setInputType(InputType.TYPE_NULL);
	}

}
