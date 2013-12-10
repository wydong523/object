package com.google.zxing.client.android.view.calendar;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class WeekdayArrayAdapter extends ArrayAdapter<String> {
	public static int textColor = Color.LTGRAY;

	public WeekdayArrayAdapter(Context context, int textViewResourceId,
			List<String> objects) {
		super(context, textViewResourceId, objects);
	}

	@Override
	public boolean areAllItemsEnabled() {
		return false;
	}

	@Override
	public boolean isEnabled(int position) {
		return false;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView textView = (TextView) super.getView(position, convertView,
				parent);

		String item = getItem(position);

		if (item.length() <= 3) {
			textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
		} else {
			textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
		}

		textView.setTextColor(textColor);
		textView.setGravity(Gravity.CENTER);
		return textView;
	}

}
