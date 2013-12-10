package com.google.zxing.client.android.utils;

import android.text.InputType;
import android.widget.EditText;

/**
 * ����EditText������
 * 
 * @author ���嶰
 * 
 */
public final class EditTextManager {

	/**
	 * ��ȡ����
	 * 
	 * @param editText
	 */
	public void getFocusable(EditText editText) {
		editText.setFocusable(true);
		editText.setFocusableInTouchMode(true);
		editText.requestFocus();
	}

	/**
	 * ȡ������
	 */
	public void removeFocusable(EditText editText) {
		editText.setFocusable(false);
		editText.setFocusableInTouchMode(false);
	}

	/**
	 * ��ֹEditText����
	 */
	public void forbidInput(EditText editText) {
		editText.setInputType(InputType.TYPE_NULL);
	}

}
