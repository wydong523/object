package com.google.zxing.client.android.view.dialog;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Set;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.app.AppContext;
import com.google.zxing.client.android.constant.StringContants;
import com.google.zxing.client.android.exceptions.AuthenticationException;
import com.google.zxing.client.android.exceptions.AuthorizationException;
import com.google.zxing.client.android.exceptions.CommunicationException;
import com.google.zxing.client.android.utils.HandlerUtils;
import com.google.zxing.client.android.vo.SelectDialogEntity;

/**
 * 用户选择弹出窗口
 * 
 * @author 汪渝栋
 * 
 */
public class SelectPopDialog extends Dialog {

	private SelectDialogEntity dialogEntity;// 弹出窗口实体

	private static int i = 0;

	private ArrayList<String> content;// 查询结果数据列表

	private ArrayList<String> cum;// 模糊查询需要传递的参数key列表

	private ArrayAdapter arrayAdapter;
	
	private ListView contentList;

	private Handler handler;

	public Handler getHandler() {
		return new Handler() {
			/*
			 * (非 Javadoc) <p>Title: handleMessage</p> <p>Description: </p>
			 * 
			 * @param msg
			 * 
			 * @see android.os.Handler#handleMessage(android.os.Message)
			 */
			@Override
			public void handleMessage(Message msg) {
				String result = (String) msg.obj;
				switch (msg.what) {
				case R.id.select_fuzzy_query:
					content.clear();
					if (result != null) {
						String[] datas = result.split(StringContants.SPLIT);
						for (String data : datas) {
							content.add(data);
						}
						arrayAdapter = new ArrayAdapter<String>(dialogEntity.getContext(),
								R.layout.account_list_item, content);
						contentList.setAdapter(arrayAdapter);
					} else {
						dialogEntity.getAppContext().showError(
								"您好，没有对应的查询数据！！！",
								(Activity) dialogEntity.getContext());
					}
					break;
				default:
					break;
				}
			}
		};
	}

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param dialogEntity
	 * @param dialog
	 */
	public SelectPopDialog(SelectDialogEntity dialogEntity, int theme,
			ArrayList<String> cum) {
		super(dialogEntity.getContext(), theme);
		this.cum = cum;
		this.dialogEntity = dialogEntity;
		handler = getHandler();
	}

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pop_dialog);
		setCanceledOnTouchOutside(true);
		final TextView title = (TextView) findViewById(R.id.title);
		contentList = (ListView) findViewById(R.id.list);
		final EditText queryContent = (EditText) findViewById(R.id.query_content);
		final Button queryBtn = (Button) findViewById(R.id.query_btn);
		queryContent.setFocusableInTouchMode(true);
		queryContent.requestFocusFromTouch();
		queryContent.setVisibility(View.GONE);

		queryBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				i++;
				if (i % 2 != 0) {
					// 隐藏title进行查询操作
					queryContent.setVisibility(View.VISIBLE);
					title.setVisibility(View.GONE);
				} else {
					// 显示title
					queryContent.setVisibility(View.GONE);
					title.setVisibility(View.VISIBLE);
					fuzzyQuery(queryContent);
				}
			}
		});

		setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss(DialogInterface dialog) {
				i = 0;
			}
		});

		title.setText(dialogEntity.getTitle());
		content = new ArrayList<String>();
		Set<String> contents = dialogEntity.getContents();
		if (contents != null && contents.size() > 0) {
			for (Object obj : contents.toArray()) {
				content.add(obj.toString());
			}
			arrayAdapter = new ArrayAdapter<String>(dialogEntity.getContext(),
					R.layout.account_list_item, content);
			contentList.setAdapter(arrayAdapter);
		}

		contentList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int arg2, long arg3) {
				TextView textView = (TextView) view
						.findViewById(R.id.account_item);
				EditText edit = dialogEntity.getEdit();
				edit.setText(textView.getText());
				edit.setFocusable(false);
				getFocusable(dialogEntity.getGetFocusView());
				dismiss();
			}
		});
	}

	/**
	 * @param queryContent
	 * @Title: fuzzyQuery
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	protected void fuzzyQuery(final EditText queryContent) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				AppContext appContext = dialogEntity.getAppContext();
				String table = dialogEntity.getTableName();
				String listValue = getListValue(queryContent.getText()
						.toString());
				Serializable datas;
				try {
					datas = appContext.selectFuzzyQuery(table, listValue);
					HandlerUtils.sendMessage(handler, datas,
							R.id.select_fuzzy_query);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (CommunicationException e) {
					e.printStackTrace();
				} catch (AuthorizationException e) {
					e.printStackTrace();
				} catch (AuthenticationException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	/**
	 * @Title: getListValue
	 * @Description: TODO
	 * @param @return
	 * @return String
	 * @throws
	 */
	protected String getListValue(String content) {
		StringBuilder builder = new StringBuilder();
		builder.append(cum.get(0));
		builder.append(content);
		builder.append(StringContants.SPLIT);
		builder.append(cum.get(1));
		builder.append(StringContants.SPLIT);
		builder.append(cum.get(2));
		return builder.toString();
	}

	public void getFocusable(EditText editText) {
		if (editText != null) {
			editText.setFocusable(true);
			editText.setFocusableInTouchMode(true);
			editText.requestFocus();
		}
	}

}
