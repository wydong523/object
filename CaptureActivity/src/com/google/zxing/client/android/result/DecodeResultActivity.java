package com.google.zxing.client.android.result;

import java.util.EnumSet;
import java.util.Set;

import com.google.zxing.ResultMetadataType;
import com.google.zxing.client.android.R;
import com.google.zxing.client.android.Intents.ResultType;
import com.google.zxing.client.android.ui.BaseActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 呈现解码后的结果
 * 
 * @author wyd
 * 
 */
public class DecodeResultActivity extends BaseActivity {

	private static final Set<ResultMetadataType> DISPLAYABLE_METADATA_TYPES = EnumSet
			.of(ResultMetadataType.ISSUE_NUMBER,
					ResultMetadataType.SUGGESTED_PRICE,
					ResultMetadataType.ERROR_CORRECTION_LEVEL,
					ResultMetadataType.POSSIBLE_COUNTRY);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.decode_result);
		Intent intent = getIntent();
		// Bitmap barcode = intent.getParcelableExtra(ResultType.BARCODE);
		String formatText = intent.getStringExtra(ResultType.FORMAT_TEXT);
		// String typeText = intent.getStringExtra(ResultType.TYPE_TEXT);
		// String formattedTime =
		// intent.getStringExtra(ResultType.FORMATTED_TIME);
		// String displayContents = intent
		// .getStringExtra(ResultType.DISPLAY_CONTENTS);
		//
		// ImageView barcodeImageView = (ImageView)
		// findViewById(R.id.image_result);
		// if (barcode == null) {
		// barcodeImageView.setImageBitmap(BitmapFactory.decodeResource(
		// getResources(), R.drawable.launcher_icon));
		// } else {
		// barcodeImageView.setImageBitmap(barcode);
		// }
		//
		TextView formatTextView = (TextView) findViewById(R.id.text_result);
		formatTextView.setText(formatText);

		// TextView typeTextView = (TextView) findViewById(R.id.type_text_view);
		// typeTextView.setText(typeText);
		//
		// TextView timeTextView = (TextView) findViewById(R.id.time_text_view);
		// timeTextView.setText(formattedTime);

		// TextView contentsTextView = (TextView)
		// findViewById(R.id.contents_text_view);
		//
		// contentsTextView.setText(displayContents);
		// // Crudely scale betweeen 22 and 32 -- bigger font for shorter text
		// int scaledSize = Math.max(22, 32 - displayContents.length() / 4);
		// contentsTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaledSize);
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
