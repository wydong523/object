/*
 * Copyright (C) 2010 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.zxing.client.android;

import android.graphics.Bitmap;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.client.android.ui.CaptureActivity;
import com.google.zxing.common.HybridBinarizer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.util.Map;

final class DecodeHandler extends Handler {

	private static final String TAG = DecodeHandler.class.getSimpleName();

	private final CaptureActivity activity;
	private final MultiFormatReader multiFormatReader;
	// 解码线程启动标识
	private boolean running = true;

	DecodeHandler(CaptureActivity activity, Map<DecodeHintType, Object> hints) {
		multiFormatReader = new MultiFormatReader();
		multiFormatReader.setHints(hints);
		this.activity = activity;
	}

	@Override
	public void handleMessage(Message message) {
		if (!running) {
			return;
		}
		switch (message.what) {
		case R.id.decode:
			decode((byte[]) message.obj, message.arg1, message.arg2);
			break;
		case R.id.quit:
			running = false;
			Looper.myLooper().quit();
			break;
		}
	}

	/**
	 * Decode the data within the viewfinder rectangle, and time how long it
	 * took. For efficiency, reuse the same reader objects from one decode to
	 * the next. 解码取景条中的矩形，根据读取信息，发送成功消息或者失败消息到handler
	 * 
	 * @param data
	 *            The YUV preview frame. 解码数据
	 * @param width
	 *            The width of the preview frame. 宽
	 * @param height
	 *            The height of the preview frame. 高
	 */
	private void decode(byte[] data, int width, int height) {
		long start = System.currentTimeMillis();
		Result rawResult = null;
		PlanarYUVLuminanceSource source = activity.getCameraManager()
				.buildLuminanceSource(data, width, height);
		if (source != null) {
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			try {
				//获取解析结果
				rawResult = multiFormatReader.decodeWithState(bitmap);
			} catch (ReaderException re) {
				// continue
			} finally {
				multiFormatReader.reset();
			}
		}

		Handler handler = activity.getHandler();
		// 解析成功
		if (rawResult != null) {
			// Don't log the barcode contents for security.
			long end = System.currentTimeMillis();
			Log.d(TAG, "Found barcode in " + (end - start) + " ms");
			if (handler != null) {
				Message message = Message.obtain(handler,
						R.id.decode_succeeded, rawResult);
				Bundle bundle = new Bundle();
				Bitmap grayscaleBitmap = toBitmap(source,
						source.renderCroppedGreyscaleBitmap());
				bundle.putParcelable(DecodeThread.BARCODE_BITMAP,
						grayscaleBitmap);
				message.setData(bundle);
				message.sendToTarget();
			}
		} else {// 解析失败
			if (handler != null) {
				Message message = Message.obtain(handler, R.id.decode_failed);
				message.sendToTarget();
			}
		}
	}

	private static Bitmap toBitmap(LuminanceSource source, int[] pixels) {
		int width = source.getWidth();
		int height = source.getHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}

}
