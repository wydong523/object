/**
 * Project Name:JZGPingGuShi
 * File Name:ScreenShotUtils.java
 * Package Name:com.gc.jzgpinggushi.uitls
 * Date:2014-9-1上午10:39:55
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.uitls;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;

/**
 * ClassName:ScreenShotUtils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-1 上午10:39:55 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class ScreenShotUtils
{
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

}
