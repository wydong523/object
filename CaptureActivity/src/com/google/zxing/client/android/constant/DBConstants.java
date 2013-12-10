package com.google.zxing.client.android.constant;

/**
 * 数据库常量
 * 
 * @author 汪渝栋
 * 
 */
public final class DBConstants {
	// 历史记录
	public static final class History {
		public static final String TABLE_NAME = "history";
		public static final String ID_COL = "id";
		public static final String TEXT_COL = "text";
		public static final String FORMAT_COL = "format";
		public static final String DISPLAY_COL = "display";
		public static final String TIMESTAMP_COL = "timestamp";
		public static final String DETAILS_COL = "details";

		public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME
				+ " (" + ID_COL + " INTEGER PRIMARY KEY, " + TEXT_COL
				+ " TEXT, " + FORMAT_COL + " TEXT, " + DISPLAY_COL + " TEXT, "
				+ TIMESTAMP_COL + " INTEGER, " + DETAILS_COL + " TEXT);";

	}

	// 账户
	public static final class Account {
		public static final String TABLE_NAME = "account";
		public static final String ID_COL = "id";
		public static final String NAME = "name";
		public static final String PASSWORD = "password";
		public static final String DEFSITE = "defsite";

		public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME
				+ " (" + ID_COL + " INTEGER PRIMARY KEY, " + NAME + " TEXT, "
				+ PASSWORD + " TEXT, " + DEFSITE + " TEXT);";
	}

	// inbox表
	public static final class Inbox {

		// 表名
		public static final String TABLE_NAME = "WFINBOX";

		// 任务标识
		public static final String TASKID = "taskid";

		// 任务名称
		public static final String DESCRIPTION = "description";

		// 状态
		public static final String STATUS = "status";

		// 发送人
		public static final String SENDER = "sender";

		// 接收人
		public static final String RECEIVER = "receiver";

		// 开始日期
		public static final String STARTDATE = "startdate";

		// 应用程序
		public static final String APP = "app";
  
		// 应用标识
		public static final String OWNERID = "ownerid";

		// 建表语句
		public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME
				+ " (" + TASKID + " INTEGER(19) PRIMARY KEY NOT NULL, "
				+ DESCRIPTION + " VARCHAR(100) NOT NULL, " + STATUS
				+ " VARCHAR(18) NOT NULL, " + SENDER + " VARCHAR(32), "
				+ RECEIVER + " VARCHAR(30) NOT NULL, " + STARTDATE + " DATE, "
				+ APP + " VARCHAR(10) NOT NULL, " + OWNERID
				+ " INTEGER(19) NOT NULL);";
	}
	
	// 工单表
		public static final class Workorder {

			// 表名
			public static final String TABLE_NAME = "WFINBOX";

		}
}
