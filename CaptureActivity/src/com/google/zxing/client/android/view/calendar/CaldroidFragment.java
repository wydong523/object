package com.google.zxing.client.android.view.calendar;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.view.calendar.InfinitePagerAdapter;
import com.google.zxing.client.android.view.calendar.InfiniteViewPager;

/**
 * 日历类
 * @author 汪渝栋
 *
 */

@SuppressLint("DefaultLocale")
public class CaldroidFragment extends DialogFragment {
	public String TAG = "CaldroidFragment";
	public static int selectedBackgroundDrawable = -1;
	public static int selectedTextColor = Color.BLACK;

	public final static int NUMBER_OF_PAGES = 4;

	public static int disabledBackgroundDrawable = -1;
	public static int disabledTextColor = Color.GRAY;

	private Button leftArrowButton;
	private Button rightArrowButton;
	private TextView monthTitleTextView;
	private GridView weekdayGridView;
	private InfiniteViewPager dateViewPager;
	private DatePageChangeListener pageChangeListener;
	private ArrayList<DateGridFragment> fragments;

	protected String dialogTitle;
	protected int month = -1;
	protected int year = -1;
	protected ArrayList<DateTime> disableDates = new ArrayList<DateTime>();
	protected ArrayList<DateTime> selectedDates = new ArrayList<DateTime>();
	protected DateTime minDateTime;
	protected DateTime maxDateTime;
	protected ArrayList<DateTime> dateInMonthsList;

	protected HashMap<String, Object> caldroidData = new HashMap<String, Object>();
	protected HashMap<String, Object> extraData = new HashMap<String, Object>();

	protected int startDayOfWeek = DateTimeConstants.SUNDAY;

	private boolean fitAllMonths = true;

	protected ArrayList<CaldroidGridAdapter> datePagerAdapters = new ArrayList<CaldroidGridAdapter>();

	protected boolean enableSwipe = true;
	protected boolean showNavigationArrows = true;

	private OnItemClickListener dateItemClickListener;

	private CaldroidListener caldroidListener;

	public CaldroidGridAdapter getNewDatesGridAdapter(int month, int year) {
		return new CaldroidGridAdapter(getActivity(), month, year,
				getCaldroidData(), extraData);
	}

	public GridView getWeekdayGridView() {
		return weekdayGridView;
	}

	public Button getLeftArrowButton() {
		return leftArrowButton;
	}

	public void setLeftArrowButton(Button leftArrowButton) {
		this.leftArrowButton = leftArrowButton;
	}

	public Button getRightArrowButton() {
		return rightArrowButton;
	}

	public void setRightArrowButton(Button rightArrowButton) {
		this.rightArrowButton = rightArrowButton;
	}

	public TextView getMonthTitleTextView() {
		return monthTitleTextView;
	}

	public void setMonthTitleTextView(TextView monthTitleTextView) {
		this.monthTitleTextView = monthTitleTextView;
	}

	public ArrayList<CaldroidGridAdapter> getDatePagerAdapters() {
		return datePagerAdapters;
	}

	public HashMap<String, Object> getCaldroidData() {
		caldroidData.clear();
		caldroidData.put("disableDates", disableDates);
		caldroidData.put("selectedDates", selectedDates);
		caldroidData.put("minDateTime", minDateTime);
		caldroidData.put("maxDateTime", maxDateTime);
		caldroidData.put("startDayOfWeek", Integer.valueOf(startDayOfWeek));
		return caldroidData;
	}

	public HashMap<String, Object> getExtraData() {
		return extraData;
	}

	public void setExtraData(HashMap<String, Object> extraData) {
		this.extraData = extraData;
	}

	public Bundle getSavedStates() {
		Bundle bundle = new Bundle();
		bundle.putInt("month", month);
		bundle.putInt("year", year);

		if (dialogTitle != null) {
			bundle.putString("dialogTitle", dialogTitle);
		}

		if (selectedDates != null && selectedDates.size() > 0) {
			bundle.putStringArrayList("selectedDates",
					CalendarHelper.convertToStringList(selectedDates));
		}

		if (disableDates != null && disableDates.size() > 0) {
			bundle.putStringArrayList("disableDates",
					CalendarHelper.convertToStringList(disableDates));
		}

		if (minDateTime != null) {
			bundle.putString("minDate", minDateTime.toString("yyyy-MM-dd"));
		}

		if (maxDateTime != null) {
			bundle.putString("maxDate", maxDateTime.toString("yyyy-MM-dd"));
		}

		bundle.putBoolean("showNavigationArrows", showNavigationArrows);
		bundle.putBoolean("enableSwipe", enableSwipe);
		bundle.putInt("startDayOfWeek", startDayOfWeek);
		bundle.putBoolean("fitAllMonths", fitAllMonths);

		return bundle;
	}

	public void saveStatesToKey(Bundle outState, String key) {
		outState.putBundle(key, getSavedStates());
	}

	public void restoreStatesFromKey(Bundle savedInstanceState, String key) {
		if (savedInstanceState != null && savedInstanceState.containsKey(key)) {
			Bundle caldroidSavedState = savedInstanceState.getBundle(key);
			setArguments(caldroidSavedState);
		}
	}

	public void restoreDialogStatesFromKey(FragmentManager manager,
			Bundle savedInstanceState, String key, String dialogTag) {
		restoreStatesFromKey(savedInstanceState, key);

		CaldroidFragment existingDialog = (CaldroidFragment) manager
				.findFragmentByTag(dialogTag);
		if (existingDialog != null) {
			existingDialog.dismiss();
			show(manager, dialogTag);
		}
	}

	public int getCurrentVirtualPosition() {
		int currentPage = dateViewPager.getCurrentItem();
		return pageChangeListener.getCurrent(currentPage);
	}

	public void moveToDate(Date date) {
		moveToDateTime(CalendarHelper.convertDateToDateTime(date));
	}

	public void moveToDateTime(DateTime dateTime) {

		DateTime firstOfMonth = new DateTime(year, month, 1, 0, 0);
		DateTime lastOfMonth = firstOfMonth.dayOfMonth().withMaximumValue();

		if (dateTime.isBefore(firstOfMonth)) {
			DateTime firstDayNextMonth = dateTime.plusMonths(1);

			pageChangeListener.setCurrentDateTime(firstDayNextMonth);
			int currentItem = dateViewPager.getCurrentItem();
			pageChangeListener.refreshAdapters(currentItem);

			dateViewPager.setCurrentItem(currentItem - 1);
		}

		else if (dateTime.isAfter(lastOfMonth)) {
			DateTime firstDayLastMonth = dateTime.minusMonths(1);

			pageChangeListener.setCurrentDateTime(firstDayLastMonth);
			int currentItem = dateViewPager.getCurrentItem();
			pageChangeListener.refreshAdapters(currentItem);

			dateViewPager.setCurrentItem(currentItem + 1);
		}

	}

	public void setCalendarDate(Date date) {
		setCalendarDateTime(new DateTime(date));
	}

	public void setCalendarDateTime(DateTime dateTime) {
		month = dateTime.getMonthOfYear();
		year = dateTime.getYear();

		// Notify listener
		if (caldroidListener != null) {
			caldroidListener.onChangeMonth(month, year);
		}

		refreshView();
	}

	public void prevMonth() {
		dateViewPager.setCurrentItem(pageChangeListener.getCurrentPage() - 1);
	}

	public void nextMonth() {
		dateViewPager.setCurrentItem(pageChangeListener.getCurrentPage() + 1);
	}

	public void clearDisableDates() {
		disableDates.clear();
	}

	public void setDisableDates(ArrayList<Date> disableDateList) {
		disableDates.clear();
		if (disableDateList == null || disableDateList.size() == 0) {
			return;
		}

		for (Date date : disableDateList) {
			DateTime dateTime = CalendarHelper.convertDateToDateTime(date);
			disableDates.add(dateTime);
		}

	}

	public void setDisableDatesFromString(ArrayList<String> disableDateStrings) {
		setDisableDatesFromString(disableDateStrings, null);
	}

	public void setDisableDatesFromString(ArrayList<String> disableDateStrings,
			String dateFormat) {
		disableDates.clear();
		if (disableDateStrings == null) {
			return;
		}

		for (String dateString : disableDateStrings) {
			DateTime dateTime = CalendarHelper.getDateTimeFromString(
					dateString, dateFormat);
			disableDates.add(dateTime);
		}
	}

	public void clearSelectedDates() {
		selectedDates.clear();
	}

	public void setSelectedDates(Date fromDate, Date toDate) {
		// Ensure fromDate is before toDate
		if (fromDate == null || toDate == null || fromDate.after(toDate)) {
			return;
		}

		selectedDates.clear();

		DateTime fromDateTime = CalendarHelper.convertDateToDateTime(fromDate);
		DateTime toDateTime = CalendarHelper.convertDateToDateTime(toDate);

		DateTime dateTime = fromDateTime;
		while (dateTime.isBefore(toDateTime)) {
			selectedDates.add(dateTime);
			dateTime = dateTime.plusDays(1);
		}
		selectedDates.add(toDateTime);
	}

	public void setSelectedDateStrings(String fromDateString,
			String toDateString, String dateFormat) throws ParseException {

		Date fromDate = CalendarHelper.getDateFromString(fromDateString,
				dateFormat);
		Date toDate = CalendarHelper
				.getDateFromString(toDateString, dateFormat);
		setSelectedDates(fromDate, toDate);
	}

	public boolean isShowNavigationArrows() {
		return showNavigationArrows;
	}

	public void setShowNavigationArrows(boolean showNavigationArrows) {
		this.showNavigationArrows = showNavigationArrows;
		if (showNavigationArrows) {
			leftArrowButton.setVisibility(View.VISIBLE);
			rightArrowButton.setVisibility(View.VISIBLE);
		} else {
			leftArrowButton.setVisibility(View.INVISIBLE);
			rightArrowButton.setVisibility(View.INVISIBLE);
		}
	}

	public boolean isEnableSwipe() {
		return enableSwipe;
	}

	public void setEnableSwipe(boolean enableSwipe) {
		this.enableSwipe = enableSwipe;
		dateViewPager.setEnabled(enableSwipe);
	}

	public void setMinDate(Date minDate) {
		if (minDate == null) {
			minDateTime = null;
		} else {
			minDateTime = CalendarHelper.convertDateToDateTime(minDate);
		}
	}

	public boolean isFitAllMonths() {
		return fitAllMonths;
	}

	public void setFitAllMonths(boolean fitAllMonths) {
		this.fitAllMonths = fitAllMonths;
		dateViewPager.setFitAllMonths(fitAllMonths);
	}

	public void setMinDateFromString(String minDateString, String dateFormat) {
		if (minDateString == null) {
			setMinDate(null);
		} else {
			minDateTime = CalendarHelper.getDateTimeFromString(minDateString,
					dateFormat);
		}
	}

	public void setMaxDate(Date maxDate) {
		if (maxDate == null) {
			maxDateTime = null;
		} else {
			maxDateTime = CalendarHelper.convertDateToDateTime(maxDate);
		}
	}

	public void setMaxDateFromString(String maxDateString, String dateFormat) {
		if (maxDateString == null) {
			setMaxDate(null);
		} else {
			maxDateTime = CalendarHelper.getDateTimeFromString(maxDateString,
					dateFormat);
		}
	}

	public void setCaldroidListener(CaldroidListener caldroidListener) {
		this.caldroidListener = caldroidListener;
	}

	private OnItemClickListener getDateItemClickListener() {
		dateItemClickListener = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				DateTime dateTime = dateInMonthsList.get(position);

				if (caldroidListener != null) {
					if ((minDateTime != null && dateTime.isBefore(minDateTime))
							|| (maxDateTime != null && dateTime
									.isAfter(maxDateTime))
							|| (disableDates != null && disableDates
									.indexOf(dateTime) != -1)) {
						return;
					}

					caldroidListener.onSelectDate(dateTime.toDate(), view);
				}
			}
		};

		return dateItemClickListener;
	}

	public void refreshView() {
		monthTitleTextView.setText(new DateTime(year, month, 1, 0, 0)
				.monthOfYear().getAsText().toUpperCase()
				+ " " + year);

		for (CaldroidGridAdapter adapter : datePagerAdapters) {
			adapter.setCaldroidData(getCaldroidData());

			adapter.setExtraData(extraData);

			adapter.notifyDataSetChanged();
		}
	}

	private void retrieveInitialArgs(Bundle savedInstanceState) {
		Bundle args = getArguments();
		Dialog dialog = getDialog();
		//修改点击dialog外部无法取消弹出
		dialog.setCanceledOnTouchOutside(true);
		if (args != null) {
			month = args.getInt("month", -1);
			year = args.getInt("year", -1);
			dialogTitle = args.getString("dialogTitle");
			if (dialog != null) {
				if (dialogTitle != null) {
					dialog.setTitle(dialogTitle);
				} else {
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				}
			}

			startDayOfWeek = args.getInt("startDayOfWeek",
					DateTimeConstants.SUNDAY);
			if (startDayOfWeek > 7) {
				startDayOfWeek = startDayOfWeek % 7;
			}

			showNavigationArrows = args
					.getBoolean("showNavigationArrows", true);

			enableSwipe = args.getBoolean("enableSwipe", true);

			fitAllMonths = args.getBoolean("fitAllMonths", true);

			DateTimeFormatter formatter = DateTimeFormat
					.forPattern("yyyy-MM-dd");

			ArrayList<String> disableDateStrings = args
					.getStringArrayList("disableDates");
			if (disableDateStrings != null && disableDateStrings.size() > 0) {
				for (String dateString : disableDateStrings) {
					DateTime dt = formatter.parseDateTime(dateString);
					disableDates.add(dt);
				}
			}

			ArrayList<String> selectedDateStrings = args
					.getStringArrayList("selectedDates");
			if (selectedDateStrings != null && selectedDateStrings.size() > 0) {
				for (String dateString : selectedDateStrings) {
					DateTime dt = formatter.parseDateTime(dateString);
					selectedDates.add(dt);
				}
			}

			String minDateTimeString = args.getString("minDate");
			if (minDateTimeString != null) {
				minDateTime = CalendarHelper.getDateTimeFromString(
						minDateTimeString, null);
			}

			String maxDateTimeString = args.getString("maxDate");
			if (maxDateTimeString != null) {
				maxDateTime = CalendarHelper.getDateTimeFromString(
						maxDateTimeString, null);
			}

		}
		if (month == -1 || year == -1) {
			DateTime dateTime = new DateTime();
			month = dateTime.getMonthOfYear();
			year = dateTime.getYear();
		}
	}

	public static CaldroidFragment newInstance(String dialogTitle, int month,
			int year) {
		CaldroidFragment f = new CaldroidFragment();

		Bundle args = new Bundle();
		args.putString("dialogTitle", dialogTitle);
		args.putInt("month", month);
		args.putInt("year", year);

		f.setArguments(args);

		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public void onDestroyView() {
		if (getDialog() != null && getRetainInstance())
			getDialog().setDismissMessage(null);
		super.onDestroyView();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		retrieveInitialArgs(savedInstanceState);

		View view = inflater.inflate(R.layout.calendar_view, container, false);

		monthTitleTextView = (TextView) view
				.findViewById(R.id.calendar_month_year_textview);

		leftArrowButton = (Button) view.findViewById(R.id.calendar_left_arrow);
		rightArrowButton = (Button) view
				.findViewById(R.id.calendar_right_arrow);

		leftArrowButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				prevMonth();
			}
		});

		rightArrowButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				nextMonth();
			}
		});

		setShowNavigationArrows(showNavigationArrows);

		weekdayGridView = (GridView) view.findViewById(R.id.weekday_gridview);
		WeekdayArrayAdapter weekdaysAdapter = new WeekdayArrayAdapter(
				getActivity(), android.R.layout.simple_list_item_1,
				getDaysOfWeek());
		weekdayGridView.setAdapter(weekdaysAdapter);

		setupDateGridPages(view);

		refreshView();

		return view;
	}

	private void setupDateGridPages(View view) {
		DateTime currentDateTime = new DateTime(year, month, 1, 0, 0, 0);
		dateInMonthsList = CalendarHelper.getFullWeeks(month, year,
				startDayOfWeek);

		pageChangeListener = new DatePageChangeListener();
		pageChangeListener.setCurrentDateTime(currentDateTime);

		CaldroidGridAdapter adapter0 = getNewDatesGridAdapter(
				currentDateTime.getMonthOfYear(), currentDateTime.getYear());

		DateTime nextDateTime = currentDateTime.plusMonths(1);
		CaldroidGridAdapter adapter1 = getNewDatesGridAdapter(
				nextDateTime.getMonthOfYear(), nextDateTime.getYear());

		DateTime next2DateTime = nextDateTime.plusMonths(1);
		CaldroidGridAdapter adapter2 = getNewDatesGridAdapter(
				next2DateTime.getMonthOfYear(), next2DateTime.getYear());

		DateTime prevDateTime = currentDateTime.minusMonths(1);
		CaldroidGridAdapter adapter3 = getNewDatesGridAdapter(
				prevDateTime.getMonthOfYear(), prevDateTime.getYear());

		datePagerAdapters.add(adapter0);
		datePagerAdapters.add(adapter1);
		datePagerAdapters.add(adapter2);
		datePagerAdapters.add(adapter3);

		pageChangeListener.setCaldroidGridAdapters(datePagerAdapters);

		dateViewPager = (InfiniteViewPager) view
				.findViewById(R.id.months_infinite_pager);

		dateViewPager.setEnabled(enableSwipe);

		dateViewPager.setFitAllMonths(fitAllMonths);

		dateViewPager.setDateInMonthsList(dateInMonthsList);

		final MonthPagerAdapter pagerAdapter = new MonthPagerAdapter(
				getChildFragmentManager());

		fragments = pagerAdapter.getFragments();
		for (int i = 0; i < NUMBER_OF_PAGES; i++) {
			DateGridFragment dateGridFragment = fragments.get(i);
			CaldroidGridAdapter adapter = datePagerAdapters.get(i);
			dateGridFragment.setGridAdapter(adapter);
			dateGridFragment.setOnItemClickListener(getDateItemClickListener());
		}

		InfinitePagerAdapter infinitePagerAdapter = new InfinitePagerAdapter(
				pagerAdapter);

		dateViewPager.setAdapter(infinitePagerAdapter);

		dateViewPager.setOnPageChangeListener(pageChangeListener);
	}

	private ArrayList<String> getDaysOfWeek() {
		ArrayList<String> list = new ArrayList<String>();

		DateTime sunday = new DateTime(2013, 2, 17, 0, 0);
		DateTime nextDay = sunday;

		if (startDayOfWeek != DateTimeConstants.SUNDAY) {
			nextDay = sunday.plusDays(startDayOfWeek);
		}

		for (int i = 0; i < 7; i++) {
			list.add(nextDay.dayOfWeek().getAsShortText().toUpperCase());
			nextDay = nextDay.plusDays(1);
		}

		return list;
	}

	public class DatePageChangeListener implements OnPageChangeListener {
		private int currentPage = InfiniteViewPager.OFFSET;
		private DateTime currentDateTime;
		private ArrayList<CaldroidGridAdapter> caldroidGridAdapters;

		public int getCurrentPage() {
			return currentPage;
		}

		public void setCurrentPage(int currentPage) {
			this.currentPage = currentPage;
		}

		public DateTime getCurrentDateTime() {
			return currentDateTime;
		}

		public void setCurrentDateTime(DateTime dateTime) {
			this.currentDateTime = dateTime;
			setCalendarDateTime(currentDateTime);
		}

		public ArrayList<CaldroidGridAdapter> getCaldroidGridAdapters() {
			return caldroidGridAdapters;
		}

		public void setCaldroidGridAdapters(
				ArrayList<CaldroidGridAdapter> caldroidGridAdapters) {
			this.caldroidGridAdapters = caldroidGridAdapters;
		}

		private int getNext(int position) {
			return (position + 1) % CaldroidFragment.NUMBER_OF_PAGES;
		}

		private int getPrevious(int position) {
			return (position + 3) % CaldroidFragment.NUMBER_OF_PAGES;
		}

		public int getCurrent(int position) {
			return position % CaldroidFragment.NUMBER_OF_PAGES;
		}

		@Override
		public void onPageScrollStateChanged(int position) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		public void refreshAdapters(int position) {
			CaldroidGridAdapter currentAdapter = caldroidGridAdapters
					.get(getCurrent(position));
			CaldroidGridAdapter prevAdapter = caldroidGridAdapters
					.get(getPrevious(position));
			CaldroidGridAdapter nextAdapter = caldroidGridAdapters
					.get(getNext(position));

			if (position == currentPage) {

				currentAdapter.setAdapterDateTime(currentDateTime);
				currentAdapter.notifyDataSetChanged();

				prevAdapter.setAdapterDateTime(currentDateTime.minusMonths(1));
				prevAdapter.notifyDataSetChanged();

				nextAdapter.setAdapterDateTime(currentDateTime.plusMonths(1));
				nextAdapter.notifyDataSetChanged();
			}
			else if (position > currentPage) {
				currentDateTime = currentDateTime.plusMonths(1);

				nextAdapter.setAdapterDateTime(currentDateTime.plusMonths(1));
				nextAdapter.notifyDataSetChanged();

			}
			else {
				currentDateTime = currentDateTime.minusMonths(1);

				prevAdapter.setAdapterDateTime(currentDateTime.minusMonths(1));
				prevAdapter.notifyDataSetChanged();
			}

			currentPage = position;
		}

		@Override
		public void onPageSelected(int position) {
			refreshAdapters(position);

			setCalendarDateTime(currentDateTime);

			CaldroidGridAdapter currentAdapter = caldroidGridAdapters
					.get(position % CaldroidFragment.NUMBER_OF_PAGES);

			dateInMonthsList.clear();
			dateInMonthsList.addAll(currentAdapter.getDatetimeList());
		}

	}

}
