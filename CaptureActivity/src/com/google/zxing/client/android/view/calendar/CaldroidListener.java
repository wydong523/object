package com.google.zxing.client.android.view.calendar;


import java.util.Date;

import android.view.View;

/**
 * �����¼�����
 * @author ���嶰
 *
 */
public abstract class CaldroidListener {
	//����ѡ���¼�
	public abstract void onSelectDate(Date date, View view);

	//���ڸı��¼�
	public void onChangeMonth(int month, int year) {
		// Do nothing
	};
}
