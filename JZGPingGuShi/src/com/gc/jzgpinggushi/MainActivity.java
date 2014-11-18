package com.gc.jzgpinggushi;

import com.example.jzgpinggushi.R;
import com.gc.jzgpinggushi.app.ActivityHelp;
import com.gc.jzgpinggushi.ui.BaseActivity;
import com.gc.jzgpinggushi.ui.LoginActivity;

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
		ActivityHelp.startActivity(this, LoginActivity.class);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
