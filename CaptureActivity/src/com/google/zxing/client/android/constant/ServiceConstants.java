package com.google.zxing.client.android.constant;

/**
 * 记录所有webservice服务通信常量值
 * 
 * @author 汪渝栋
 * 
 */
public final class ServiceConstants {

	public static final String NAMESPACE = "http://www.ibm.com/maximo";

	// public static final String URL_PATH =
	// "http://192.168.1.189:9080/meaweb/services/mobileserviceinter";

	/**
	 * 用户数据常量
	 * 
	 * @author 汪渝栋
	 * 
	 */
	public static class UserService {

		public static final String PASSWORD_METHOD = "mobileserviceintergetUserPwd";

		public static final String USER_UNIQUE = "mobileserviceintercheckDevice";

		public static final String USER_APP = "mobileserviceintergetUserApp";

		public static final String AUTH_APP = "mobileserviceintergetUserAppAuthOther";

		public static final String USERNAME_PARAM1 = "username";

		public static final String USERNAME_PARAM = "userName";

		public static final String PASSWORD_PARAM = "password";

		public static final String GETACCOUNT_METHOD = "mobileserviceintergetMaxUsersOther";

		public static final String PHONENUM = "mobilenum";

		public static String DEVICE_ID = "deviceid";
	}

	/**
	 * 收件箱数据常量
	 */
	public static class InboxService {

		public static final String INBOX_METHOD = "mobileserviceintergetUserTask";

		public static final String USERNAME_PARAM = "username";
	}

	/**
	 * PR常量数据
	 */
	public static class PRService {

		public static final String PR_QUERY = "mobileserviceintergetPR";

		public static final String PR_ADD = "mobileserviceinteraddTbale";

		public static final String PR_DEL = "mobileserviceinterdellTbale";

		public static final String PR_MODIFY = "mobileserviceintermodifyTbale";

		public static final String PR_LINE_QUERY = "mobileserviceintergetPRLine";

		public static final String PR_LINE_DEL = "mobileserviceinterdellTbaleLine";

		public static final String PR_LINE_MODIFY = "mobileserviceintermodiyTbaleLine";

		public static final String PR_LINE_ADD = "mobileserviceinteraddTableLine";

		public static final String PR_LINE_INWFRO = "mobileserviceinterstartPro";

		public static final String PR_LINE_SPWFRO = "mobileserviceinterSPWFRO";

		public static final String PR_LINE_GETPRPAGE = "mobileserviceintergetPRPage";

		public static final String APP_NAME = "appName";

		public static final String TABLE = "table";

		public static final String WHERE_STR = "whereStr";

		public static final String PARMS_LIST = "listValue";

		public static final String RESULT_LIST = "reListAttribute";

		public static final String PR_KEY_ID = "pyKeyid";

		public static final String PR_RELATION_SHIP = "relationShip";

	}

	/**
	 * 工单常量数据
	 */
	public static class WorkorderService {

		public static final String TABLE = "table";
		public static final String TABLE_NAME = "workorder";

		public static final String WORK_ADD = "mobileserviceinteraddTbale";

		public static final String WORK_QUERY = "mobileserviceinterqueryListPage";

		public static final String WORK_MODIFY = "mobileserviceintermodifyTbale";

		public static final String WORK_DEL = "mobileserviceinterdellTbale";

	}

}
