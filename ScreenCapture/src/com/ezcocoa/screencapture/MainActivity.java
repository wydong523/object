package com.ezcocoa.screencapture;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Iterator;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * 
 * @author Baek, hojun
 * @create Sep 24, 2013
 */
public class MainActivity extends Activity
{
	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		WebView mWebView = (WebView) findViewById(R.id.gen_web);
		// 设置WebView属性，能够执行Javascript脚本
		mWebView.getSettings().setJavaScriptEnabled(true);
		// 加载需要显示的网页
		mWebView.loadUrl("http://www.baidu.com");
	}

	/**
	 * capture screen
	 * 
	 * @param rootLayout
	 *            root layout
	 * @param fileName
	 *            file name
	 */
	private void captureScreen(View rootLayout, String fileName)
	{
		Bitmap bitmap = Bitmap.createBitmap(rootLayout.getWidth(),
				rootLayout.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		rootLayout.draw(canvas);
		String string = insertImage(getContentResolver(), bitmap, fileName,
				fileName);
		System.out.println(string);
		// File root = Environment.getExternalStorageDirectory();
		// File root = getExternalFilesDir(null);
		// System.out.println(root);
		// File file = new File(root, fileName);
		// Bitmap b = Bitmap.createBitmap(rootLayout.getWidth(),
		// rootLayout.getHeight(), Bitmap.Config.ARGB_8888);
		// String photoUrl = MediaStore.Images.Media.insertImage(
		// getContentResolver(), b, fileName, null);
		// System.out.println(photoUrl);
		//
		// sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
		// Uri.parse(photoUrl)));
		// File file = new File(root，photoUrl);
		// Canvas c = new Canvas(bitmap);
		// rootLayout.draw(c);
		// FileOutputStream fos = null;
		// try
		// {
		// fos = new FileOutputStream(file);
		// if (fos != null)
		// {
		// bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
		// fos.close();
		// Toast.makeText(this, "Successful Capturing", Toast.LENGTH_SHORT)
		// .show();
		// }
		// } catch (Exception e)
		// {
		// e.printStackTrace();
		// System.out.println(e);
		// }
	}

	public static final String insertImage(ContentResolver cr, Bitmap source,
			String title, String description)
	{

		ContentValues values = new ContentValues();
		values.put(Images.Media.TITLE, title);
		values.put(Images.Media.DISPLAY_NAME, title);
		values.put(Images.Media.DESCRIPTION, description);
		values.put(Images.Media.MIME_TYPE, "image/jpeg");
		values.put(Images.Media.DATE_ADDED, System.currentTimeMillis());
		values.put(Images.Media.DATE_TAKEN, System.currentTimeMillis());

		Uri url = null;
		String stringUrl = null; /* value to be returned */

		try
		{
			url = cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
					values);

			if (source != null)
			{
				OutputStream imageOut = cr.openOutputStream(url);
				try
				{
					source.compress(Bitmap.CompressFormat.JPEG, 50, imageOut);
				} finally
				{
					imageOut.close();
				}

				long id = ContentUris.parseId(url);
				// Wait until MINI_KIND thumbnail is generated.
				Bitmap miniThumb = Images.Thumbnails.getThumbnail(cr, id,
						Images.Thumbnails.MINI_KIND, null);
				// This is for backward compatibility.
				storeThumbnail(cr, miniThumb, id, 50F, 50F,
						Images.Thumbnails.MICRO_KIND);
			} else
			{
				cr.delete(url, null, null);
				url = null;
			}
		} catch (Exception e)
		{
			if (url != null)
			{
				cr.delete(url, null, null);
				url = null;
			}
		}

		if (url != null)
		{
			stringUrl = url.toString();
		}

		return stringUrl;
	}

	private static final Bitmap storeThumbnail(ContentResolver cr,
			Bitmap source, long id, float width, float height, int kind)
	{

		// create the matrix to scale it
		Matrix matrix = new Matrix();

		float scaleX = width / source.getWidth();
		float scaleY = height / source.getHeight();

		matrix.setScale(scaleX, scaleY);

		Bitmap thumb = Bitmap.createBitmap(source, 0, 0, source.getWidth(),
				source.getHeight(), matrix, true);

		ContentValues values = new ContentValues(4);
		values.put(Images.Thumbnails.KIND, kind);
		values.put(Images.Thumbnails.IMAGE_ID, (int) id);
		values.put(Images.Thumbnails.HEIGHT, thumb.getHeight());
		values.put(Images.Thumbnails.WIDTH, thumb.getWidth());

		Uri url = cr.insert(Images.Thumbnails.EXTERNAL_CONTENT_URI, values);

		try
		{
			OutputStream thumbOut = cr.openOutputStream(url);
			thumb.compress(Bitmap.CompressFormat.JPEG, 100, thumbOut);
			thumbOut.close();
			return thumb;
		} catch (FileNotFoundException ex)
		{
			return null;
		} catch (IOException ex)
		{
			return null;
		}
	}

	/**
	 * This is function you can capture what you're seeing.
	 * 
	 * @param v
	 */
	public void onAction(View v)
	{
		switch (v.getId()) {
		case R.id.activity_main_captureBtn:
			View rootLayout = findViewById(R.id.activity_main_contentLayout);
			captureScreen(rootLayout, "screenshot.jpg");
			break;

		case R.id.activity_main_deleteBtn:
			File dir = getExternalFilesDir(null);
			File[] files = dir.listFiles();
			if (files.length > 0)
			{
				Iterator<File> i = Arrays.asList(files).iterator();
				while (i.hasNext())
				{
					File f = i.next();
					String fn = f.getAbsolutePath();
					if (f.delete())
						Log.d("TAG", "Deleted " + fn);
					else
						Log.d("TAG", "Failed " + fn);
				}
			}
			break;
		}
	}
}
