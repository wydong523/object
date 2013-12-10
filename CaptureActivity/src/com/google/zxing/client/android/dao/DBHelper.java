/*
 * Copyright (C) 2009 ZXing authors
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

package com.google.zxing.client.android.dao;

import com.google.zxing.client.android.constant.DBConstants;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

/**
 * Êý¾Ý¿â²Ù×÷
 * 
 * @author ÍôÓå¶°
 */
public final class DBHelper extends SQLiteOpenHelper {
				
	private static final int DB_VERSION = 5;
	private static final String DB_NAME = "barcode.db";

	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		sqLiteDatabase.execSQL(DBConstants.History.TABLE_CREATE);

		sqLiteDatabase.execSQL(DBConstants.Account.TABLE_CREATE);

		sqLiteDatabase.execSQL(DBConstants.Inbox.TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion,
			int newVersion) {
		sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "
				+ DBConstants.Account.TABLE_NAME);
		sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "
				+ DBConstants.History.TABLE_NAME);
		sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "
				+ DBConstants.Inbox.TABLE_NAME);
		onCreate(sqLiteDatabase);
	}

	public static synchronized void close(Cursor cursor, SQLiteDatabase database) {
		if (cursor != null) {
			cursor.close();
		}
		if (database != null) {
			database.close();
		}
	}

}
