package com.gc.jzgpinggushi.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jzgpinggushi.R;
import com.gc.jzgpinggushi.constant.Constant;
import com.gc.jzgpinggushi.vo.Model;

/**
 * 车系分类列表适配器 ClassName: ModelCategoryAdapter <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * date: 2014-6-18 下午3:15:31 <br/>
 * 
 * @author wang
 * @version
 * @since JDK 1.6
 */
public class ModelCategoryAdapter extends BaseAdapter
{
	private LayoutInflater inflater;
	private List<Model> list;
	private List<String> groupkey;

	public ModelCategoryAdapter(Context context, List<Model> list,
			List<String> groupkey)
	{
		super();
		inflater = LayoutInflater.from(context);
		this.list = list;
		this.groupkey = groupkey;
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position)
	{
		// return list.get(position).getName();
		return list.get(position);
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
		Model model = (Model) getItem(position);
		if (groupkey.contains(model.getName())
				&& Constant.IS_TITLE.equals(model.getManufacturerName()))
		{
			view = inflater.inflate(R.layout.addexam_list_item_tag, null);
		} else
		{
			view = inflater.inflate(R.layout.addexam_list_item, null);
		}
		TextView text = (TextView) view
				.findViewById(R.id.addexam_list_item_text);
		// text.setText((CharSequence) getItem(position).);
		text.setText(list.get(position).getName());
		text.setTextColor(list.get(position).getFontColor());
		return view;
	}
}
