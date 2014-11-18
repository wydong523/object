package com.gc.jzgpinggushi.view;

import com.example.jzgpinggushi.R;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * ClassName:RerefshDialog <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-10-15 上午11:04:35 <br/>
 * @author   汪渝栋
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class SureOrCancleDialog extends AlertDialog {

	private String title;

	private String msg;

	public SureOrCancleDialog(Context context,
			int theme, String title, String msg) {
		super(context, theme);
		this.title = title;
		this.msg = msg;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contacts_layout);
		((TextView) findViewById(R.id.dialog_title)).setText(title);
		((TextView) findViewById(R.id.dialog_msg)).setText(msg);
		((Button) findViewById(R.id.dialog_cancle))
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dismiss();
					}
				});

	}

}
