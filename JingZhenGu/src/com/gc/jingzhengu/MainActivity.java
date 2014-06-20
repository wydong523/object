package com.gc.jingzhengu;

import com.gc.jingzhengu.R;
import com.gc.jingzhengu.R.layout;
import com.gc.jingzhengu.R.menu;
import com.gc.jingzhengu.app.ActivityHelp;
import com.gc.jingzhengu.ui.BaseActivity;
import com.gc.jingzhengu.ui.HomeActivity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends BaseActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActivityHelp.startActivity(this, HomeActivity.class);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
