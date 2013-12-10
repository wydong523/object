package com.google.zxing.client.android.dao;

import com.google.zxing.client.android.constant.DBConstants;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 操作登录相关
 * 
 * @author 汪渝栋
 * 
 */
public class LoginDao {
	private static final String TAG = LoginDao.class.getSimpleName();

	private final Activity activity;

	public LoginDao(Activity activity) {
		this.activity = activity;
	}

	/**
	 * 获取本地用户密码
	 * 
	 * @param name
	 *            用户名
	 * @return true/false
	 */
	public String getNativePass(String name) {
		SQLiteDatabase db = null;
		Cursor cursor = null;
		String pass = null;
		SQLiteOpenHelper helper = new DBHelper(activity);
		db = helper.getReadableDatabase();
		cursor = db.query(DBConstants.Account.TABLE_NAME,
				new String[] { DBConstants.Account.PASSWORD },
				DBConstants.Account.NAME + "=?", new String[] { name }, null,
				null, null);
		while (cursor.moveToNext()) {
			pass = cursor.getString(0);

		}
		return pass;
	}

}
