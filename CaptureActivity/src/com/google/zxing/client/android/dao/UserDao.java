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
 * @Desctiption �û����ݿ����
 * @author ���嶰
 * @date 2013-10-11 ����11:02:58
 */
public class UserDao {

	private final Activity activity;

	public UserDao(Activity activity) {
		this.activity = activity;
	}

	/**
	 * ��ȡ�����û�����
	 * 
	 * @param name
	 *            �û���
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
