package com.google.zxing.client.android.constant;

/**
 * ���ݿⳣ��
 * 
 * @author ���嶰
 * 
 */
public final class DBConstants {
	// ��ʷ��¼
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

	// �˻�
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

	// inbox��
	public static final class Inbox {

		// ����
		public static final String TABLE_NAME = "WFINBOX";

		// �����ʶ
		public static final String TASKID = "taskid";

		// ��������
		public static final String DESCRIPTION = "description";

		// ״̬
		public static final String STATUS = "status";

		// ������
		public static final String SENDER = "sender";

		// ������
		public static final String RECEIVER = "receiver";

		// ��ʼ����
		public static final String STARTDATE = "startdate";

		// Ӧ�ó���
		public static final String APP = "app";
  
		// Ӧ�ñ�ʶ
		public static final String OWNERID = "ownerid";

		// �������
		public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME
				+ " (" + TASKID + " INTEGER(19) PRIMARY KEY NOT NULL, "
				+ DESCRIPTION + " VARCHAR(100) NOT NULL, " + STATUS
				+ " VARCHAR(18) NOT NULL, " + SENDER + " VARCHAR(32), "
				+ RECEIVER + " VARCHAR(30) NOT NULL, " + STARTDATE + " DATE, "
				+ APP + " VARCHAR(10) NOT NULL, " + OWNERID
				+ " INTEGER(19) NOT NULL);";
	}
	
	// ������
		public static final class Workorder {

			// ����
			public static final String TABLE_NAME = "WFINBOX";

		}
}
