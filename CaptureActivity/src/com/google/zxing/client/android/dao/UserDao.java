/**   
 * @Title: UserDao.java 
 * @Package com.google.zxing.client.android.dao 
 * @version V1.0   
 */
package com.google.zxing.client.android.dao;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.zxing.client.android.constant.DBConstants;

/**
 * @Desctiption 用户数据库对象
 * @author 汪渝栋
 * @date 2013-10-11 上午11:02:58
 */
public class UserDao {

	private final Activity activity;

	public UserDao(Activity activity) {
		this.activity = activity;
	}

	/**
	 * 获取本地用户密码
	 * 
	 * @param name
	 *            用户名
	 * @return true/false
	 */
	public String queryUserSite(String name) {
		SQLiteDatabase db = null;
		Cursor cursor = null;
		String site = null;
		SQLiteOpenHelper helper = new DBHelper(activity);
		db = helper.getReadableDatabase();
		cursor = db.query(DBConstants.Account.TABLE_NAME,
				new String[] { DBConstants.Account.DEFSITE },
				DBConstants.Account.NAME + "=?", new String[] { name }, null,
				null, null);
		while (cursor.moveToNext()) {
			site = cursor.getString(0);
		}
		return site;
	}
}
