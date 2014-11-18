package com.example.testapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity implements OnItemClickListener
{

	/** Called when the activity is first created. */
	private List<TextContent> list = null;
	private List<String> groupkey = new ArrayList<String>();
	private List<TextContent> aList = new ArrayList<TextContent>();
	private List<TextContent> bList = new ArrayList<TextContent>();
	private List<TextContent> cList = new ArrayList<TextContent>();
	private List<TextContent> dList = new ArrayList<TextContent>();
	private ListView listview;

	MyAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listview = (ListView) findViewById(R.id.listView_list);
		listview.setOnItemClickListener(this);
		initData();
		adapter = new MyAdapter();
		listview.setAdapter(adapter);

	}

	public void initData()
	{
		list = new ArrayList<TextContent>();

		groupkey.add("A组");
		groupkey.add("B组");
		groupkey.add("C组");
		groupkey.add("D组");
		TextContent cotent = null;
		for (int i = 0; i < 20; i++)
		{
			cotent = new TextContent();
			cotent.setColor(Color.BLACK);
			cotent.setContent("A组" + i);
			aList.add(cotent);
		}
		cotent = new TextContent();
		cotent.setColor(Color.BLACK);
		cotent.setContent("A组");
		list.add(cotent);
		list.addAll(aList);

		for (int i = 0; i < 20; i++)
		{
			cotent = new TextContent();
			cotent.setColor(Color.BLACK);
			cotent.setContent("B组" + i);
			bList.add(cotent);
		}
		cotent = new TextContent();
		cotent.setColor(Color.BLACK);
		cotent.setContent("B组");
		list.add(cotent);
		list.addAll(bList);

		for (int i = 0; i < 20; i++)
		{
			cotent = new TextContent();
			cotent.setColor(Color.BLACK);
			cotent.setContent("C组" + i);
			cList.add(cotent);
		}
		cotent = new TextContent();
		cotent.setColor(Color.BLACK);
		cotent.setContent("C组");
		list.add(cotent);
		list.addAll(cList);

	}

	private class MyAdapter extends BaseAdapter
	{

		@Override
		public int getCount()
		{
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position)
		{
			// TODO Auto-generated method stub
			return list.get(position).getContent();
		}

		@Override
		public long getItemId(int position)
		{
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public boolean isEnabled(int position)
		{
			// TODO Auto-generated method stub
			if (groupkey.contains(getItem(position)))
			{
				return false;
			}
			return super.isEnabled(position);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View view = convertView;
			if (groupkey.contains(getItem(position)))
			{
				view = LayoutInflater.from(getApplicationContext()).inflate(
						R.layout.addexam_list_item_tag, null);
			} else
			{
				view = LayoutInflater.from(getApplicationContext()).inflate(
						R.layout.addexam_list_item, null);
			}
			TextView text = (TextView) view
					.findViewById(R.id.addexam_list_item_text);
			text.setText((CharSequence) getItem(position));
			text.setTextColor(list.get(position).getColor());
			return view;
		}

	}

	private int oldPosition = -1;

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{
		TextView textView = (TextView) view
				.findViewById(R.id.addexam_list_item_text);
		textView.setTextColor(Color.BLUE);
		list.get(position).setColor(Color.BLUE);
		if (-1 != oldPosition && oldPosition != position)
		{
			list.get(oldPosition).setColor(Color.BLACK);
		}
		oldPosition = position;
		adapter.notifyDataSetChanged();

	}

}
