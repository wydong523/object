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
 * �����ռ������ر������ݲ���
 * 
 * @author ���嶰
 * 
 */
public class InboxDao {
	private static final String TAG = InboxDao.class.getSimpleName();

	/**
	 * ����ռ�����Ϣ���������ݿ���
	 * 
	 * @param inboxs
	 *            �ռ�����Ϣ
	 */
	public void insertInboxData(ArrayList<HashMap<String, Object>> inboxs,
			Activity activity) {
		Log.d(TAG, "insertInboxData is here!!! ");
		ContentValues values = null;
		SQLiteDatabase db = null;
		values = new ContentValues();
		// TODO ��Ҫ������Ӧ�����ݿ��Ȼ����в������
		// values.put(DBConstants.Account.NAME, person.getName());
		// values.put(DBConstants.Account.PASSWORD, person.getPass());
		SQLiteOpenHelper helper = new DBHelper(activity);
		db = helper.getWritableDatabase();
		// ���inbox���ݵ����ؿ�
		db.insert(DBConstants.Account.TABLE_NAME, null, values);
		DBHelper.close(null, db);
	}

}
