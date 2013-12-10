package com.google.zxing.client.android.view.calendar;


import java.util.ArrayList;

import org.joda.time.DateTime;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class InfiniteViewPager extends ViewPager {

	public static final int OFFSET = 1000;

	private ArrayList<DateTime> dateInMonthsList;

	private boolean enabled = true;

	private boolean fitAllMonths = true;

	private int rowHeight = 0;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isFitAllMonths() {
		return fitAllMonths;
	}

	public void setFitAllMonths(boolean fitAllMonths) {
		this.fitAllMonths = fitAllMonths;
		rowHeight = 0;
	}

	public ArrayList<DateTime> getDateInMonthsList() {
		return dateInMonthsList;
	}

	public void setDateInMonthsList(ArrayList<DateTime> dateInMonthsList) {
		this.dateInMonthsList = dateInMonthsList;
	}

	public InfiniteViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public InfiniteViewPager(Context context) {
		super(context);
	}

	@Override
	public void setAdapter(PagerAdapter adapter) {
		super.setAdapter(adapter);
		setCurrentItem(OFFSET);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (enabled) {
			return super.onTouchEvent(event);
		}
		return false;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		if (enabled) {
			return super.onInterceptTouchEvent(event);
		}
		return false;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int rows = dateInMonthsList.size() / 7;

		boolean wrapHeight = MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST;

		int height = getMeasuredHeight();
		if (wrapHeight && rowHeight == 0) {

			int width = getMeasuredWidth();

			widthMeasureSpec = MeasureSpec.makeMeasureSpec(width,
					MeasureSpec.EXACTLY);
			if (getChildCount() > 0) {
				View firstChild = getChildAt(0);
				firstChild.measure(widthMeasureSpec, MeasureSpec
						.makeMeasureSpec(height, MeasureSpec.AT_MOST));

				height = firstChild.getMeasuredHeight();
				rowHeight = height / rows;
			}
		}

		int calHeight = 0;
		if (fitAllMonths) {
			calHeight = rowHeight * 6;
		} else { 
			calHeight = rowHeight * rows;
		}

		if (calHeight > height) {
			calHeight = height;
		}

		heightMeasureSpec = MeasureSpec.makeMeasureSpec(calHeight,
				MeasureSpec.EXACTLY);

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

}
