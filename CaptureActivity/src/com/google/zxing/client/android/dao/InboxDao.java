package com.google.zxing.client.android.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.zxing.client.android.constant.DBConstants;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 管理收件箱的相关本地数据操作
 * 
 * @author 汪渝栋
 * 
 */
public class InboxDao {
	private static final String TAG = InboxDao.class.getSimpleName();

	/**
	 * 添加收件箱信息到本地数据库中
	 * 
	 * @param inboxs
	 *            收件箱信息
	 */
	public void insertInboxData(ArrayList<HashMap<String, Object>> inboxs,
			Activity activity) {
		Log.d(TAG, "insertInboxData is here!!! ");
		ContentValues values = null;
		SQLiteDatabase db = null;
		values = new ContentValues();
		// TODO 需要创建对应的数据库表，然后进行插入操作
		// values.put(DBConstants.Account.NAME, person.getName());
		// values.put(DBConstants.Account.PASSWORD, person.getPass());
		SQLiteOpenHelper helper = new DBHelper(activity);
		db = helper.getWritableDatabase();
		// 添加inbox数据到本地库
		db.insert(DBConstants.Account.TABLE_NAME, null, values);
		DBHelper.close(null, db);
	}

}
