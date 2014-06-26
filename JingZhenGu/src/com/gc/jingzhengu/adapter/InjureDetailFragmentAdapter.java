/**
 * Project Name:JingZhenGu
 * File Name:InjureDetailFragmentAdapter.java
 * Package Name:com.gc.jingzhengu.adapter
 * Date:2014-6-4下午8:50:52
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.adapter;

import com.gc.jingzhengu.R;
import com.gc.jingzhengu.fragment.InjureDetailFragment;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * ClassName:InjureDetailFragmentAdapter <br/>
 * Function: 报损ViewPager适配器. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-4 下午8:50:52 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class InjureDetailFragmentAdapter extends FragmentPagerAdapter
{
	protected static final int[] LAYOUT_ID = new int[] { R.layout.facade,
			R.layout.upholstery, R.layout.impetus, R.layout.other_problem };

	private Handler mViewPagerHandler;

	public InjureDetailFragmentAdapter(FragmentManager fm, Handler handler)
	{
		super(fm);
		mViewPagerHandler = handler;
	}

	@Override
	public Fragment getItem(int position)
	{
		return InjureDetailFragment.newInstance(LAYOUT_ID[position], mViewPagerHandler);
	}

	@Override
	public int getCount()
	{
		return LAYOUT_ID.length;
	}

}
