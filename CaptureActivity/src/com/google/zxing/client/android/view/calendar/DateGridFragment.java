package com.google.zxing.client.android.view.calendar;

import com.google.zxing.client.android.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class DateGridFragment extends Fragment {
	private GridView gridView;
	private CaldroidGridAdapter gridAdapter;
	private OnItemClickListener onItemClickListener;

	public OnItemClickListener getOnItemClickListener() {
		return onItemClickListener;
	}

	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}

	public CaldroidGridAdapter getGridAdapter() {
		return gridAdapter;
	}

	public void setGridAdapter(CaldroidGridAdapter gridAdapter) {
		this.gridAdapter = gridAdapter;
	}

	public GridView getGridView() {
		return gridView;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		gridView = (GridView) inflater.inflate(R.layout.date_grid_fragment,
				container, false);
		if (gridAdapter != null) {
			gridView.setAdapter(gridAdapter);
		}

		if (onItemClickListener != null) {
			gridView.setOnItemClickListener(onItemClickListener);
		}
		return gridView;
	}

}
