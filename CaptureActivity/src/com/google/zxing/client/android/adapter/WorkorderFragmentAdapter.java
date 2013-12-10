/**   
 * @Title: WorkorderFragmentAdapter.java 
 * @Package com.google.zxing.client.android.adapter 
 * @version V1.0   
 */
package com.google.zxing.client.android.adapter;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.app.AppContext;
import com.google.zxing.client.android.fragment.WorkorderFragment;
import com.google.zxing.client.android.vo.Workorder;
import com.viewpagerindicator.IconPagerAdapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Button;

/**
 * @Desctiption
 * @author 汪渝栋
 * @date 2013-9-12 下午5:52:56
 */
public class WorkorderFragmentAdapter extends FragmentPagerAdapter implements
		IconPagerAdapter {

	 protected static final String[] CONTENT = new String[] { "工单详情", "工单计划",
	 "实际情况" };

	 protected static final int[] LAYOUT_ID = new int[] {
	 R.layout.workoder_detail, R.layout.workoder_plan,
	 R.layout.workorder_actual_situation };

	private int count = CONTENT.length;

	protected static final int[] ICONS = new int[] { R.drawable.setting_sel,
			R.drawable.setting_sel };

	private Workorder workorder;

	private AppContext appContext;

	private Activity workorderActivity;

	private FragmentManager fm;

	private Button addBtn;

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param fm
	 */
	public WorkorderFragmentAdapter(FragmentManager fm, Button addBtn,
			Workorder workorder, AppContext appContext,
			Activity workorderActivity) {
		super(fm);
		this.fm = fm;
		this.addBtn = addBtn;
		this.workorder = workorder;
		this.appContext = appContext;
		this.workorderActivity = workorderActivity;
	}

	/*
	 * (非 Javadoc) <p>Title: getItem</p> <p>Description: </p>
	 * 
	 * @param position
	 * 
	 * @return
	 * 
	 * @see android.support.v4.app.FragmentPagerAdapter#getItem(int)
	 */
	@Override
	public Fragment getItem(int position) {
		return WorkorderFragment.newInstance(LAYOUT_ID[position], count,
				addBtn, workorder, appContext, workorderActivity, fm);
	}

	/*
	 * (非 Javadoc) <p>Title: getCount</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see android.support.v4.view.PagerAdapter#getCount()
	 */
	@Override
	public int getCount() {
		return count;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return WorkorderFragmentAdapter.CONTENT[position % CONTENT.length];
	}

	@Override
	public int getIconResId(int index) {
		return ICONS[index % ICONS.length];
	}

	public void setCount(int count) {
		if (count > 0 && count <= 10) {
			this.count = count;
			notifyDataSetChanged();
		}
	}

}
