package com.google.zxing.client.android.webservice;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import android.app.Activity;
import android.util.Log;

import com.google.zxing.client.android.app.AppContext;
import com.google.zxing.client.android.constant.ServiceConstants;
import com.google.zxing.client.android.exceptions.AuthenticationException;
import com.google.zxing.client.android.exceptions.AuthorizationException;
import com.google.zxing.client.android.exceptions.CommunicationException;
import com.google.zxing.client.android.utils.KSoapExecutor;
import com.google.zxing.client.android.utils.SoapObjectBuilder;
import com.google.zxing.client.android.vo.AppPermissions;
import com.google.zxing.client.android.vo.AppPermissionsList;
import com.google.zxing.client.android.vo.Base;
import com.google.zxing.client.android.vo.Inbox;
import com.google.zxing.client.android.vo.PR;
import com.google.zxing.client.android.vo.PRLine;
import com.google.zxing.client.android.vo.PRList;
import com.google.zxing.client.android.vo.ParserInterface;
import com.google.zxing.client.android.vo.Update;
import com.google.zxing.client.android.vo.Workorder;
import com.google.zxing.client.android.vo.WorkorderList;

/**
 * webservice交互操作
 * 
 * @author 汪渝栋
 * 
 */
public class ApiService {
	private static final String TAG = ApiService.class.getSimpleName();

	private static KSoapExecutor soap;

	/**
	 * 获取收件箱信息
	 * 
	 * @param username
	 *            用户名
	 * @param url
	 *            访问路径
	 * @return 收件箱信息
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 */
	public static ArrayList<Inbox> getInboxData(String username, URL url,
			Activity activity) throws CommunicationException,
			AuthorizationException, AuthenticationException {
		Log.d(TAG, "getInboxData is here!!! ");
		ArrayList<Inbox> inboxs = null;
		SoapObject request = SoapObjectBuilder.start()
				.withMethod(ServiceConstants.InboxService.INBOX_METHOD).build();
		PropertyInfo info = new PropertyInfo();
		info.setNamespace(ServiceConstants.NAMESPACE);
		info.setName("username");
		info.setValue(username);
		request.addProperty(info);
		soap = new KSoapExecutor();
		SoapObject result = (SoapObject) soap.execute(request, url,
				Object.class);
		System.out.println("result.toString() is " + result.toString()
				+ result.getPropertyCount());
		if (!Base.ANY_TYPE.equals(result.toString())) {
			Inbox inbox = new Inbox();
			inboxs = inbox.parse(result);
		}
		// TODO 访问通过后组装数据 入库操作
		// insertInboxData(inboxs, activity);
		Log.d(TAG, "getAccount is " + result);
		return inboxs;
	}

	/**
	 * 新增pr数据
	 * 
	 * @param url
	 *            访问路径
	 * @param list
	 *            传递参数
	 * @return
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 */
	public String addPrData(URL url, String parmas)
			throws CommunicationException, AuthorizationException,
			AuthenticationException {

		String tableName = "PR";
		String result = new String();
		SoapObject request = SoapObjectBuilder.start()
				.withMethod(ServiceConstants.PRService.PR_ADD).build();
		System.out.println("request " + request);
		PropertyInfo info1 = getPropertyInfo(ServiceConstants.PRService.TABLE,
				tableName, ServiceConstants.NAMESPACE);
		request.addProperty(info1);
		PropertyInfo info2 = getPropertyInfo(
				ServiceConstants.PRService.APP_NAME, "appName",
				ServiceConstants.NAMESPACE);
		request.addProperty(info2);
		PropertyInfo info3 = getPropertyInfo(
				ServiceConstants.PRService.PARMS_LIST, parmas,
				ServiceConstants.NAMESPACE);
		request.addProperty(info3);
		PropertyInfo info4 = getPropertyInfo(
				ServiceConstants.PRService.RESULT_LIST, "PRNUM&PRID",
				ServiceConstants.NAMESPACE);
		request.addProperty(info4);
		System.out.println(request.toString());
		soap = new KSoapExecutor();
		Object respone = soap.execute(request, url, Object.class);
		if (respone != null) {
			result = respone.toString();
			if (result.length() > 0 && !Base.ANY_TYPE.equals(result))
				return result;
		}
		return result;
	}

	/**
	 * 删除pr
	 * 
	 * @param url
	 * @param prid
	 * @return
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 */
	public String delPrData(URL url, String prid)
			throws CommunicationException, AuthorizationException,
			AuthenticationException {

		String tableName = "PR";
		String result = new String();
		SoapObject request = SoapObjectBuilder.start()
				.withMethod(ServiceConstants.PRService.PR_DEL).build();
		System.out.println("request " + request);
		PropertyInfo info1 = getPropertyInfo(ServiceConstants.PRService.TABLE,
				tableName, ServiceConstants.NAMESPACE);
		request.addProperty(info1);
		PropertyInfo info2 = getPropertyInfo(
				ServiceConstants.PRService.PR_KEY_ID, prid,
				ServiceConstants.NAMESPACE);
		request.addProperty(info2);
		System.out.println(request.toString());
		soap = new KSoapExecutor();
		Object respone = soap.execute(request, url, Object.class);
		if (respone != null) {
			result = respone.toString();
			if (result.length() > 0 && !Base.ANY_TYPE.equals(result))
				return result;
		}
		return result;
	}

	/**
	 * 修改pr
	 * 
	 * @param url
	 * @param parmas
	 * @return
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 */
	public String modifyPrData(URL url, String parmas, String prid)
			throws CommunicationException, AuthorizationException,
			AuthenticationException {
		String tableName = "PR";
		String result = new String();
		SoapObject request = SoapObjectBuilder.start()
				.withMethod(ServiceConstants.PRService.PR_MODIFY).build();
		System.out.println("request " + request);
		PropertyInfo info1 = getPropertyInfo(ServiceConstants.PRService.TABLE,
				tableName, ServiceConstants.NAMESPACE);
		request.addProperty(info1);
		PropertyInfo info2 = getPropertyInfo(
				ServiceConstants.PRService.PR_KEY_ID, prid,
				ServiceConstants.NAMESPACE);
		request.addProperty(info2);
		PropertyInfo info3 = getPropertyInfo(
				ServiceConstants.PRService.PARMS_LIST, parmas,
				ServiceConstants.NAMESPACE);
		request.addProperty(info3);
		PropertyInfo info4 = getPropertyInfo(
				ServiceConstants.PRService.RESULT_LIST, "PRNUM&PRID",
				ServiceConstants.NAMESPACE);
		request.addProperty(info4);
		System.out.println(request.toString());
		soap = new KSoapExecutor();
		Object respone = soap.execute(request, url, Object.class);
		if (respone != null) {
			result = respone.toString();
			if (result.length() > 0 && !Base.ANY_TYPE.equals(result))
				return result;
		}
		return result;
	}

	/**
	 * 查询prline数据
	 * 
	 * @param url
	 *            路劲
	 * @return
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 */
	public static ArrayList<PRLine> queryPrLineData(URL url, String prid)
			throws CommunicationException, AuthorizationException,
			AuthenticationException {
		ArrayList<PRLine> prlines = null;
		SoapObject request = SoapObjectBuilder.start()
				.withMethod(ServiceConstants.PRService.PR_LINE_QUERY).build();
		String tableName = "PR";
		System.out.println("request " + request);
		PropertyInfo info1 = getPropertyInfo(
				ServiceConstants.PRService.APP_NAME, "appName",
				ServiceConstants.NAMESPACE);
		request.addProperty(info1);

		PropertyInfo info2 = getPropertyInfo(ServiceConstants.PRService.TABLE,
				tableName, ServiceConstants.NAMESPACE);
		request.addProperty(info2);

		PropertyInfo info3 = new PropertyInfo();
		info3.setNamespace(ServiceConstants.NAMESPACE);
		info3.setName("pyKeyId");
		info3.setValue(prid);
		request.addProperty(info3);

		PropertyInfo info4 = getPropertyInfo(
				ServiceConstants.PRService.PR_RELATION_SHIP, "PRLINE",
				ServiceConstants.NAMESPACE);
		request.addProperty(info4);

		soap = new KSoapExecutor();
		SoapObject result = (SoapObject) soap.execute(request, url,
				Object.class);
		if (result != null && !Base.ANY_TYPE.equals(result.toString())) {
			System.out.println("result.toString() is " + result.toString());
			PRLine pr = new PRLine();
			prlines = pr.parse(result);
		}
		return prlines;
	}

	/**
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @Title: querySelectValue
	 * @Description: 查询值列表
	 * @param @param url
	 * @param @param className
	 * @param @param tableName
	 * @param @param parmas
	 * @param @return
	 * @return ArrayList<Object>
	 * @throws
	 */
	public static Serializable querySelectValue(URL url, String className,
			String tableName, String tableParma, String whereStrParma)
			throws CommunicationException, AuthorizationException,
			AuthenticationException {
		Serializable seri = null;
		try {
			SoapObject request = SoapObjectBuilder.start()
					.withMethod("mobileserviceinterquerySelectValue").build();
			System.out.println("request " + request);
			PropertyInfo info1 = getPropertyInfo("table", tableParma,
					ServiceConstants.NAMESPACE);
			request.addProperty(info1);

			PropertyInfo info2 = getPropertyInfo("whereStr", whereStrParma,
					ServiceConstants.NAMESPACE);
			request.addProperty(info2);
			soap = new KSoapExecutor();

			SoapObject result = (SoapObject) soap.execute(request, url,
					Object.class);

			if (result != null && !Base.ANY_TYPE.equals(result.toString())) {
				System.out.println("result.toString() is " + result.toString());
				ParserInterface face = (ParserInterface) Class.forName(
						className).newInstance();
				System.out.println(Class.forName(className).toString());
				seri = face.parse(result);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return seri;
	}

	/**
	 * 添加soap参数
	 * 
	 * @param key
	 *            参数名
	 * @param value
	 *            参数值
	 * @param namespace
	 *            命名空间
	 */
	private static PropertyInfo getPropertyInfo(String key, String value,
			String namespace) {
		PropertyInfo info = new PropertyInfo();
		info.setNamespace(ServiceConstants.NAMESPACE);
		info.setName(key);
		info.setValue(value);
		return info;
	}

	public static String delPrLine(URL url, String prId, String prlineid)
			throws CommunicationException, AuthorizationException,
			AuthenticationException {
		String result = null;
		SoapObject request = SoapObjectBuilder.start()
				.withMethod(ServiceConstants.PRService.PR_LINE_DEL).build();
		PropertyInfo info1 = new PropertyInfo();
		info1.setNamespace(ServiceConstants.NAMESPACE);
		info1.setName("table");
		info1.setValue("PR");
		request.addProperty(info1);

		PropertyInfo info2 = new PropertyInfo();
		info2.setNamespace(ServiceConstants.NAMESPACE);
		info2.setName("pyKeyid");
		info2.setValue(prId);
		request.addProperty(info2);

		PropertyInfo info3 = new PropertyInfo();
		info3.setNamespace(ServiceConstants.NAMESPACE);
		info3.setName("tableLine");
		info3.setValue("PRLINE");
		request.addProperty(info3);

		PropertyInfo info4 = new PropertyInfo();
		info4.setNamespace(ServiceConstants.NAMESPACE);
		info4.setName("tableLineid");
		info4.setValue(prlineid);
		request.addProperty(info4);

		PropertyInfo info5 = new PropertyInfo();
		info5.setNamespace(ServiceConstants.NAMESPACE);
		info5.setName("relationShip");
		info5.setValue("PRLINE");
		request.addProperty(info5);

		soap = new KSoapExecutor();
		Object respone = soap.execute(request, url, Object.class);
		if (respone != null) {
			result = respone.toString();
			if (result.length() > 0 && !Base.ANY_TYPE.equals(result))
				return result;
		}
		return result;
	}

	public static String modifyPrline(URL url, String parmas, String prlineid,
			String prId) throws CommunicationException, AuthorizationException,
			AuthenticationException {
		String result = null;
		SoapObject request = SoapObjectBuilder.start()
				.withMethod(ServiceConstants.PRService.PR_LINE_MODIFY).build();
		PropertyInfo info1 = new PropertyInfo();
		info1.setNamespace(ServiceConstants.NAMESPACE);
		info1.setName("table");
		info1.setValue("PR");
		request.addProperty(info1);

		PropertyInfo info2 = new PropertyInfo();
		info2.setNamespace(ServiceConstants.NAMESPACE);
		info2.setName("pyKeyid");
		info2.setValue(prId);
		request.addProperty(info2);

		PropertyInfo info3 = new PropertyInfo();
		info3.setNamespace(ServiceConstants.NAMESPACE);
		info3.setName("relationShip");
		info3.setValue("PRLINE");
		request.addProperty(info3);

		PropertyInfo info4 = new PropertyInfo();
		info4.setNamespace(ServiceConstants.NAMESPACE);
		info4.setName("tableLine");
		info4.setValue("PRLINE");
		request.addProperty(info4);

		PropertyInfo info5 = new PropertyInfo();
		info5.setNamespace(ServiceConstants.NAMESPACE);
		info5.setName("tableLineId");
		info5.setValue(prlineid);
		request.addProperty(info5);

		PropertyInfo info6 = new PropertyInfo();
		info6.setNamespace(ServiceConstants.NAMESPACE);
		info6.setName("listValue");
		info6.setValue(parmas);
		request.addProperty(info6);

		PropertyInfo info7 = new PropertyInfo();
		info7.setNamespace(ServiceConstants.NAMESPACE);
		info7.setName("reListAttribute");
		info7.setValue("PRNUM&PRLINEID");
		request.addProperty(info7);

		soap = new KSoapExecutor();
		Object respone = soap.execute(request, url, Object.class);
		if (respone != null) {
			result = respone.toString();
			if (result.length() > 0 && !Base.ANY_TYPE.equals(result))
				return result;
		}
		return result;
	}

	public static String addPrLine(URL url, String parmas, String prId)
			throws CommunicationException, AuthorizationException,
			AuthenticationException {
		String result = null;
		SoapObject request = SoapObjectBuilder.start()
				.withMethod(ServiceConstants.PRService.PR_LINE_ADD).build();
		PropertyInfo info1 = new PropertyInfo();
		info1.setNamespace(ServiceConstants.NAMESPACE);
		info1.setName("table");
		info1.setValue("PR");
		request.addProperty(info1);

		PropertyInfo info2 = new PropertyInfo();
		info2.setNamespace(ServiceConstants.NAMESPACE);
		info2.setName("pyKeyid");
		info2.setValue(prId);
		request.addProperty(info2);

		PropertyInfo info3 = new PropertyInfo();
		info3.setNamespace(ServiceConstants.NAMESPACE);
		info3.setName("relationShip");
		info3.setValue("PRLINE");
		request.addProperty(info3);

		PropertyInfo info4 = new PropertyInfo();
		info4.setNamespace(ServiceConstants.NAMESPACE);
		info4.setName("listValue");
		info4.setValue(parmas);
		request.addProperty(info4);

		PropertyInfo info5 = new PropertyInfo();
		info5.setNamespace(ServiceConstants.NAMESPACE);
		info5.setName("reListAttribute");
		info5.setValue("PRNUM&PRLINEID");
		request.addProperty(info5);
		soap = new KSoapExecutor();
		Object respone = soap.execute(request, url, Object.class);
		if (respone != null) {
			result = respone.toString();
			if (result.length() > 0 && !Base.ANY_TYPE.equals(result))
				return result;
		}
		return result;
	}

	/**
	 * 启动流程
	 * 
	 * @param url
	 * @param prId
	 * @return
	 * @throws CommunicationException
	 * @throws AuthorizationException
	 * @throws AuthenticationException
	 */
	public static String inwfro(URL url, String ownerTable, String wfName,
			String pykeyid) throws CommunicationException,
			AuthorizationException, AuthenticationException {
		String result = null;
		SoapObject request = SoapObjectBuilder.start()
				.withMethod(ServiceConstants.PRService.PR_LINE_INWFRO).build();

		PropertyInfo info1 = new PropertyInfo();
		info1.setNamespace(ServiceConstants.NAMESPACE);
		info1.setName("ownerTable");
		info1.setValue(ownerTable);
		request.addProperty(info1);

		PropertyInfo info2 = new PropertyInfo();
		info2.setNamespace(ServiceConstants.NAMESPACE);
		info2.setName("wfName");
		info2.setValue(wfName);
		request.addProperty(info2);

		PropertyInfo info3 = new PropertyInfo();
		info3.setNamespace(ServiceConstants.NAMESPACE);
		info3.setName("pykeyid");
		info3.setValue(pykeyid);
		request.addProperty(info3);
		soap = new KSoapExecutor();
		Object respone = soap.execute(request, url, Object.class);
		if (respone != null) {
			result = respone.toString();
			if (result.length() > 0 && !Base.ANY_TYPE.equals(result))
				return result;
		}
		return result;
	}

	/**
	 * 审批流程(不使用)
	 * 
	 * @param url
	 * @param ownerid
	 * @return
	 * @throws CommunicationException
	 * @throws AuthorizationException
	 * @throws AuthenticationException
	 */
	public static String spwfro(URL url, String ownerid)
			throws CommunicationException, AuthorizationException,
			AuthenticationException {
		String result = null;
		SoapObject request = SoapObjectBuilder.start()
				.withMethod(ServiceConstants.PRService.PR_LINE_SPWFRO).build();
		PropertyInfo info1 = new PropertyInfo();
		info1.setNamespace(ServiceConstants.NAMESPACE);
		info1.setName("userid");
		info1.setValue("LUBINGWU");
		request.addProperty(info1);

		PropertyInfo info2 = new PropertyInfo();
		info2.setNamespace(ServiceConstants.NAMESPACE);
		info2.setName("memo");
		info2.setValue("同意");
		request.addProperty(info2);

		PropertyInfo info3 = new PropertyInfo();
		info3.setNamespace(ServiceConstants.NAMESPACE);
		info3.setName("pykeyid");
		info3.setValue(ownerid);
		request.addProperty(info3);

		PropertyInfo info4 = new PropertyInfo();
		info4.setNamespace(ServiceConstants.NAMESPACE);
		info4.setName("appName");
		info4.setValue("PR");
		request.addProperty(info4);

		PropertyInfo info5 = new PropertyInfo();
		info5.setNamespace(ServiceConstants.NAMESPACE);
		info5.setName("ownerTable");
		info5.setValue("PR");
		request.addProperty(info5);

		soap = new KSoapExecutor();
		Object respone = soap.execute(request, url, Object.class);
		if (respone != null) {
			result = respone.toString();
			if (result.length() > 0 && !Base.ANY_TYPE.equals(result))
				return result;
		}
		return result;
	}

	/**
	 * 获取pr分页数据
	 * 
	 * @param url
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 */
	public static PRList getPRPage(URL url, int pageSize, String pageIndex)
			throws CommunicationException, AuthorizationException,
			AuthenticationException {
		PRList prList = null;
		SoapObject request = SoapObjectBuilder.start()
				.withMethod(ServiceConstants.PRService.PR_LINE_GETPRPAGE)
				.build();
		PropertyInfo info1 = new PropertyInfo();
		info1.setNamespace(ServiceConstants.NAMESPACE);
		info1.setName("appName");
		info1.setValue("appName");
		request.addProperty(info1);

		PropertyInfo info2 = new PropertyInfo();
		info2.setNamespace(ServiceConstants.NAMESPACE);
		info2.setName("table");
		info2.setValue("PR");
		request.addProperty(info2);

		PropertyInfo info3 = new PropertyInfo();
		info3.setNamespace(ServiceConstants.NAMESPACE);
		info3.setName("whereStr");
		info3.setValue("cutype in ('YP-GN','YP-GW','WZ-GN','WZ-GW','ZL-TS','ZL-FY','ZL-DH','ZL-CX','ZL-YS','QT-DHJ','DW-QK','QT-BZH')");
		request.addProperty(info3);

		PropertyInfo info4 = new PropertyInfo();
		info4.setNamespace(ServiceConstants.NAMESPACE);
		info4.setName("page");
		info4.setValue(pageIndex);
		request.addProperty(info4);

		PropertyInfo info5 = new PropertyInfo();
		info5.setNamespace(ServiceConstants.NAMESPACE);
		info5.setName("pageCounts");
		info5.setValue(pageSize);
		request.addProperty(info5);
		soap = new KSoapExecutor();
		SoapObject result = (SoapObject) soap.execute(request, url,
				Object.class);
		if (result != null && !Base.ANY_TYPE.equals(result.toString())) {
			System.out.println("result.toString() is " + result.toString());
			PR pr = new PR();
			prList = pr.parse(result);
		}
		return prList;

	}

	public static AppPermissionsList queryApp(URL url, String username)
			throws CommunicationException, AuthorizationException,
			AuthenticationException {
		AppPermissionsList apList = null;
		SoapObject request = SoapObjectBuilder.start()
				.withMethod(ServiceConstants.UserService.USER_APP).build();
		PropertyInfo info1 = new PropertyInfo();
		info1.setNamespace(ServiceConstants.NAMESPACE);
		info1.setName(ServiceConstants.UserService.USERNAME_PARAM1);
		info1.setValue(username);
		request.addProperty(info1);

		soap = new KSoapExecutor();
		SoapObject result = (SoapObject) soap.execute(request, url,
				Object.class);
		if (result != null && !Base.ANY_TYPE.equals(result.toString())) {
			System.out.println("result.toString() is " + result.toString());
			AppPermissions ap = new AppPermissions();
			apList = ap.parse(result);
		}
		return apList;
	}

	/**
	 * 验证设备唯一性
	 * 
	 * @param url
	 * @param phoneNum
	 * @param deviceId
	 * @return
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 */
	public static String checkDevice(URL url, String phoneNum, String deviceId)
			throws CommunicationException, AuthorizationException,
			AuthenticationException {
		SoapObject request = SoapObjectBuilder.start()
				.withMethod(ServiceConstants.UserService.USER_UNIQUE).build();
		PropertyInfo info1 = new PropertyInfo();
		info1.setNamespace(ServiceConstants.NAMESPACE);
		info1.setName(ServiceConstants.UserService.PHONENUM);
		info1.setValue(phoneNum);
		request.addProperty(info1);

		PropertyInfo info2 = new PropertyInfo();
		info2.setNamespace(ServiceConstants.NAMESPACE);
		info2.setName(ServiceConstants.UserService.DEVICE_ID);
		info2.setValue(deviceId);
		request.addProperty(info2);

		soap = new KSoapExecutor();
		Object result = soap.execute(request, url, Object.class);
		if (result != null && !Base.ANY_TYPE.equals(result.toString())) {
			System.out.println("result.toString() is " + result.toString());
			return result.toString();
		}
		return result.toString();
	}

	public static WorkorderList queryWorkorder(URL url, int pageIndex,
			int pageSize) throws CommunicationException,
			AuthorizationException, AuthenticationException {
		WorkorderList list = null;
		SoapObject request = SoapObjectBuilder.start()
				.withMethod(ServiceConstants.WorkorderService.WORK_QUERY)
				.build();
		PropertyInfo info1 = new PropertyInfo();
		info1.setNamespace(ServiceConstants.NAMESPACE);
		info1.setName("appName");
		info1.setValue("");
		request.addProperty(info1);

		PropertyInfo info2 = new PropertyInfo();
		info2.setNamespace(ServiceConstants.NAMESPACE);
		info2.setName("table");
		info2.setValue("workorder");
		request.addProperty(info2);

		PropertyInfo info3 = new PropertyInfo();
		info3.setNamespace(ServiceConstants.NAMESPACE);
		info3.setName("whereStr");
		info3.setValue(" worktype='CM' and woclass='WORKORDER'");
		request.addProperty(info3);

		PropertyInfo info4 = new PropertyInfo();
		info4.setNamespace(ServiceConstants.NAMESPACE);
		info4.setName("page");
		info4.setValue(String.valueOf(pageIndex));
		request.addProperty(info4);

		PropertyInfo info5 = new PropertyInfo();
		info5.setNamespace(ServiceConstants.NAMESPACE);
		info5.setName("pageCounts");
		info5.setValue(String.valueOf(pageSize));
		request.addProperty(info5);

		soap = new KSoapExecutor();
		SoapObject result = (SoapObject) soap.execute(request, url,
				Object.class);
		if (result != null && !Base.ANY_TYPE.equals(result.toString())) {
			System.out.println("result.toString() is " + result.toString());
			Workorder workorder = new Workorder();
			list = workorder.paser(result);
		}
		return list;
	}

	public String addWorkorder(URL url, String parmas)
			throws CommunicationException, AuthorizationException,
			AuthenticationException {
		String result = new String();
		SoapObject request = SoapObjectBuilder.start()
				.withMethod(ServiceConstants.WorkorderService.WORK_ADD).build();
		System.out.println("request " + request);
		PropertyInfo info1 = getPropertyInfo(
				ServiceConstants.WorkorderService.TABLE,
				ServiceConstants.WorkorderService.TABLE_NAME,
				ServiceConstants.NAMESPACE);
		request.addProperty(info1);

		PropertyInfo info2 = getPropertyInfo(
				ServiceConstants.PRService.APP_NAME, "WOTRACK",
				ServiceConstants.NAMESPACE);
		request.addProperty(info2);

		PropertyInfo info3 = getPropertyInfo(
				ServiceConstants.PRService.PARMS_LIST, parmas,
				ServiceConstants.NAMESPACE);
		request.addProperty(info3);

		PropertyInfo info4 = getPropertyInfo(
				ServiceConstants.PRService.RESULT_LIST, "WONUM&WORKORDERID",
				ServiceConstants.NAMESPACE);
		request.addProperty(info4);

		System.out.println(request.toString());
		soap = new KSoapExecutor();
		Object respone = soap.execute(request, url, Object.class);
		if (respone != null) {
			result = respone.toString();
			if (result.length() > 0 && !Base.ANY_TYPE.equals(result))
				return result;
		}
		return result;
	}

	public String modifyWorkorder(URL url, String parmas, String workorderId)
			throws CommunicationException, AuthorizationException,
			AuthenticationException {
		String result = new String();
		SoapObject request = SoapObjectBuilder.start()
				.withMethod(ServiceConstants.WorkorderService.WORK_MODIFY)
				.build();
		System.out.println("request " + request);

		PropertyInfo info1 = new PropertyInfo();
		info1.setNamespace(ServiceConstants.NAMESPACE);// 需要设置
		info1.setName("pyKeyid");
		info1.setValue(workorderId);
		request.addProperty(info1);

		PropertyInfo info2 = new PropertyInfo();
		info2.setNamespace(ServiceConstants.NAMESPACE);// 需要设置
		info2.setName("table");
		info2.setValue("workorder");
		request.addProperty(info2);

		PropertyInfo info3 = new PropertyInfo();
		info3.setNamespace(ServiceConstants.NAMESPACE);// 需要设置
		info3.setName("appName");
		info3.setValue("WOTRACK");
		request.addProperty(info3);

		PropertyInfo info4 = new PropertyInfo();
		info4.setNamespace(ServiceConstants.NAMESPACE);// 需要设置
		info4.setName("listValue");
		info4.setValue(parmas);
		request.addProperty(info4);

		PropertyInfo info5 = new PropertyInfo();
		info5.setNamespace(ServiceConstants.NAMESPACE);// 需要设置
		info5.setName("reListAttribute");
		info5.setValue("WORKORDERID&DESCRIPTION");
		request.addProperty(info5);

		System.out.println(request.toString());
		soap = new KSoapExecutor();
		Object respone = soap.execute(request, url, Object.class);
		if (respone != null) {
			result = respone.toString();
			if (result.length() > 0 && !Base.ANY_TYPE.equals(result))
				return result;
		}
		return result;
	}

	public String delWorkorder(URL url, String workorderid)
			throws CommunicationException, AuthorizationException,
			AuthenticationException {
		String tableName = "workorder";
		String result = new String();
		SoapObject request = SoapObjectBuilder.start()
				.withMethod(ServiceConstants.WorkorderService.WORK_DEL).build();
		System.out.println("request " + request);
		PropertyInfo info1 = getPropertyInfo(ServiceConstants.PRService.TABLE,
				tableName, ServiceConstants.NAMESPACE);
		request.addProperty(info1);
		PropertyInfo info2 = getPropertyInfo(
				ServiceConstants.PRService.PR_KEY_ID, workorderid,
				ServiceConstants.NAMESPACE);
		request.addProperty(info2);
		System.out.println(request.toString());
		soap = new KSoapExecutor();
		Object respone = soap.execute(request, url, Object.class);
		if (respone != null) {
			result = respone.toString();
			if (result.length() > 0 && !Base.ANY_TYPE.equals(result))
				return result;
		}
		return result;
	}

	public HashMap<String, TreeSet<String>> queryAppOperateAuth(URL url,
			String loginUid) throws CommunicationException,
			AuthorizationException, AuthenticationException {
		String result = null;
		HashMap<String, TreeSet<String>> allAppAuths = new HashMap<String, TreeSet<String>>();
		SoapObject request = SoapObjectBuilder.start()
				.withMethod(ServiceConstants.UserService.AUTH_APP).build();
		PropertyInfo info1 = getPropertyInfo(
				ServiceConstants.UserService.USERNAME_PARAM1, loginUid,
				ServiceConstants.NAMESPACE);
		request.addProperty(info1);
		System.out.println(request.toString());
		soap = new KSoapExecutor();
		Object respone = soap.execute(request, url, Object.class);
		if (respone != null) {
			result = respone.toString();
			if (result.length() > 0 && !Base.ANY_TYPE.equals(result))
				allAppAuths = splitResult(result, allAppAuths);
			return allAppAuths;
		}
		return allAppAuths;
	}

	private HashMap<String, TreeSet<String>> splitResult(String result,
			HashMap<String, TreeSet<String>> allAppAuths) {
		String[] app = result.split("&");
		String key = null;
		for (int i = 0; i < app.length; i++) {
			String[] data = app[i].split(",");
			TreeSet<String> auth = new TreeSet<String>();
			for (int j = 0; j < data.length; j++) {
				if (j == 0) {
					key = data[j];
				} else {
					auth.add(data[j]);
				}
			}
			allAppAuths.put(key, auth);
		}
		return allAppAuths;
	}

	/**
	 * 检查版本更新
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static Update checkVersion(AppContext appContext) throws IOException {
		return Update.parse(null);
	}

	/**
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * 
	 * @Title: queryList
	 * @Description: 查询列表数据公共方法
	 * @param @param url 地址
	 * @param @param appName 预留
	 * @param @param table 表名
	 * @param @param whereStr 条件
	 * @param @param pageIndex 页数
	 * @param @param pageSize 每页显示条数
	 * @param @return
	 * @return WorkorderPlanTaskList
	 * @throws
	 */
	public static Serializable queryList(String appName, String className,
			URL url, String table, String whereStr, int pageIndex, int pageSize)
			throws CommunicationException, AuthorizationException,
			AuthenticationException {
		Serializable list = null;
		SoapObject request = SoapObjectBuilder.start()
				.withMethod(ServiceConstants.WorkorderService.WORK_QUERY)
				.build();
		PropertyInfo info1 = new PropertyInfo();
		info1.setNamespace(ServiceConstants.NAMESPACE);
		info1.setName("appName");
		info1.setValue(appName);
		request.addProperty(info1);

		PropertyInfo info2 = new PropertyInfo();
		info2.setNamespace(ServiceConstants.NAMESPACE);
		info2.setName("table");
		info2.setValue(table);
		request.addProperty(info2);

		PropertyInfo info3 = new PropertyInfo();
		info3.setNamespace(ServiceConstants.NAMESPACE);
		info3.setName("whereStr");
		info3.setValue(whereStr);
		request.addProperty(info3);

		PropertyInfo info4 = new PropertyInfo();
		info4.setNamespace(ServiceConstants.NAMESPACE);
		info4.setName("page");
		info4.setValue(String.valueOf(pageIndex));
		request.addProperty(info4);

		PropertyInfo info5 = new PropertyInfo();
		info5.setNamespace(ServiceConstants.NAMESPACE);
		info5.setName("pageCounts");
		info5.setValue(String.valueOf(pageSize));
		request.addProperty(info5);

		soap = new KSoapExecutor();
		SoapObject result = (SoapObject) soap.execute(request, url,
				Object.class);
		if (result != null && !Base.ANY_TYPE.equals(result.toString())) {
			ParserInterface face;
			try {
				face = (ParserInterface) Class.forName(className).newInstance();
				System.out.println(Class.forName(className).toString()
						+ result.getPropertyCount());
				list = face.parse(result);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @Title: getValueByAnother
	 * @Description: 通用通过一个值返回另外一个值 如：选择资产带入位置
	 * @param @param url
	 * @param @param table
	 * @param @param relationship 关系
	 * @param @param attr 对应的数据库字段
	 * @param @param value 传入值
	 * @param @return
	 * @return Serializable
	 * @throws
	 */
	public static String getValueByAnother(URL url, String className,
			String table, String relationship, String attr, String value)
			throws CommunicationException, AuthorizationException,
			AuthenticationException {
		String result = null;
		SoapObject request = SoapObjectBuilder.start()
				.withMethod("mobileserviceintergetValueByAnother").build();
		PropertyInfo info1 = new PropertyInfo();
		info1.setNamespace(ServiceConstants.NAMESPACE);
		info1.setName("table");
		info1.setValue(table);
		request.addProperty(info1);

		PropertyInfo info4 = new PropertyInfo();
		info4.setNamespace(ServiceConstants.NAMESPACE);
		info4.setName("relationship");
		info4.setValue(relationship);
		request.addProperty(info4);

		PropertyInfo info2 = new PropertyInfo();
		info2.setNamespace(ServiceConstants.NAMESPACE);
		info2.setName("attributename");
		info2.setValue(attr);
		request.addProperty(info2);

		PropertyInfo info3 = new PropertyInfo();
		info3.setNamespace(ServiceConstants.NAMESPACE);
		info3.setName("value");
		info3.setValue(value);
		request.addProperty(info3);

		soap = new KSoapExecutor();
		Object respone = soap.execute(request, url, Object.class);
		if (respone != null) {
			result = respone.toString();
			if (result.length() > 0 && !Base.ANY_TYPE.equals(result))
				return result;
		}
		return null;
	}

	/**
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @Title: add
	 * @Description: 通用在线新增
	 * @param @param className
	 * @param @param url
	 * @param @param appName
	 * @param @param table
	 * @param @param listValue
	 * @param @param reListAttribute
	 * @param @return
	 * @return Serializable
	 * @throws
	 */
	public static Serializable add(URL url, String className, String table,
			String appName, String pyKeyid, String relationShip,
			String listValue) throws CommunicationException,
			AuthorizationException, AuthenticationException {
		Serializable list = null;
		SoapObject request = SoapObjectBuilder.start()
				.withMethod("mobileserviceinteradd").build();

		PropertyInfo info2 = new PropertyInfo();
		info2.setNamespace(ServiceConstants.NAMESPACE);
		info2.setName("table");
		info2.setValue(table);
		request.addProperty(info2);

		PropertyInfo info1 = new PropertyInfo();
		info1.setNamespace(ServiceConstants.NAMESPACE);
		info1.setName("appName");
		info1.setValue(appName);
		request.addProperty(info1);

		PropertyInfo info4 = new PropertyInfo();
		info4.setNamespace(ServiceConstants.NAMESPACE);
		info4.setName("pyKeyid");
		info4.setValue(pyKeyid);
		request.addProperty(info4);

		PropertyInfo info5 = new PropertyInfo();
		info5.setNamespace(ServiceConstants.NAMESPACE);
		info5.setName("relationShip");
		info5.setValue(relationShip);
		request.addProperty(info5);

		PropertyInfo info3 = new PropertyInfo();
		info3.setNamespace(ServiceConstants.NAMESPACE);
		info3.setName("listValue");
		info3.setValue(listValue);
		request.addProperty(info3);

		soap = new KSoapExecutor();
		SoapObject result = (SoapObject) soap.execute(request, url,
				Object.class);

		if (result != null && !Base.ANY_TYPE.equals(result.toString())) {
			ParserInterface face;
			try {
				face = (ParserInterface) Class.forName(className).newInstance();
				System.out.println(Class.forName(className).toString()
						+ result.getPropertyCount());
				list = face.parse(result);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public static Serializable modify(URL url, String className, String table,
			String pyKeyid, String relationShip, String tableLine,
			String tableLineId, String listValue)
			throws CommunicationException, AuthorizationException,
			AuthenticationException {
		Serializable list = null;
		SoapObject request = SoapObjectBuilder.start()
				.withMethod("mobileserviceintermodify").build();

		PropertyInfo info2 = new PropertyInfo();
		info2.setNamespace(ServiceConstants.NAMESPACE);
		info2.setName("table");
		info2.setValue(table);
		request.addProperty(info2);

		PropertyInfo info1 = new PropertyInfo();
		info1.setNamespace(ServiceConstants.NAMESPACE);
		info1.setName("pyKeyid");
		info1.setValue(pyKeyid);
		request.addProperty(info1);

		PropertyInfo info4 = new PropertyInfo();
		info4.setNamespace(ServiceConstants.NAMESPACE);
		info4.setName("relationShip");
		info4.setValue(relationShip);
		request.addProperty(info4);

		PropertyInfo info5 = new PropertyInfo();
		info5.setNamespace(ServiceConstants.NAMESPACE);
		info5.setName("tableLine");
		info5.setValue(tableLine);
		request.addProperty(info5);

		PropertyInfo info6 = new PropertyInfo();
		info6.setNamespace(ServiceConstants.NAMESPACE);
		info6.setName("tableLineId");
		info6.setValue(tableLineId);
		request.addProperty(info6);

		PropertyInfo info3 = new PropertyInfo();
		info3.setNamespace(ServiceConstants.NAMESPACE);
		info3.setName("listValue");
		info3.setValue(listValue);
		request.addProperty(info3);

		soap = new KSoapExecutor();
		SoapObject result = (SoapObject) soap.execute(request, url,
				Object.class);

		if (result != null && !Base.ANY_TYPE.equals(result.toString())) {
			ParserInterface face;
			try {
				face = (ParserInterface) Class.forName(className).newInstance();
				System.out.println(Class.forName(className).toString()
						+ result.getPropertyCount());
				list = face.parse(result);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @Title: delete
	 * @Description: TODO
	 * @param @param url
	 * @param @param className
	 * @param @param table
	 * @param @param pyKeyid
	 * @param @return
	 * @return Serializable
	 * @throws
	 */
	public static Serializable delete(URL url, String className, String table,
			String pyKeyid, String tableLine, String tableLineid,
			String relationShip) throws CommunicationException,
			AuthorizationException, AuthenticationException {
		String result = null;
		SoapObject request = SoapObjectBuilder.start()
				.withMethod("mobileserviceinterdelete").build();

		PropertyInfo info2 = new PropertyInfo();
		info2.setNamespace(ServiceConstants.NAMESPACE);
		info2.setName("table");
		info2.setValue(table);
		request.addProperty(info2);

		PropertyInfo info1 = new PropertyInfo();
		info1.setNamespace(ServiceConstants.NAMESPACE);
		info1.setName("pyKeyid");
		info1.setValue(pyKeyid);
		request.addProperty(info1);

		PropertyInfo info3 = new PropertyInfo();
		info3.setNamespace(ServiceConstants.NAMESPACE);
		info3.setName("tableLine");
		info3.setValue(tableLine);
		request.addProperty(info3);

		PropertyInfo info4 = new PropertyInfo();
		info4.setNamespace(ServiceConstants.NAMESPACE);
		info4.setName("tableLineid");
		info4.setValue(tableLineid);
		request.addProperty(info4);

		PropertyInfo info5 = new PropertyInfo();
		info5.setNamespace(ServiceConstants.NAMESPACE);
		info5.setName("relationShip");
		info5.setValue(relationShip);
		request.addProperty(info5);

		soap = new KSoapExecutor();
		Object respone = (Object) soap.execute(request, url, Object.class);

		if (respone != null) {
			result = respone.toString();
			if (result.length() > 0 && !Base.ANY_TYPE.equals(result))
				return result;
		}
		return result;
	}

	/**
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @Title: getResult
	 * @Description: TODO
	 * @param @param url
	 * @param @param className
	 * @param @param table
	 * @param @param table2
	 * @param @param listValue
	 * @param @return
	 * @return Serializable
	 * @throws
	 */
	public static Serializable getResult(URL url, String className,
			String table, String listValue) throws CommunicationException,
			AuthorizationException, AuthenticationException {
		Serializable list = null;
		SoapObject request = SoapObjectBuilder.start()
				.withMethod("mobileserviceintergetResult").build();

		PropertyInfo info2 = new PropertyInfo();
		info2.setNamespace(ServiceConstants.NAMESPACE);
		info2.setName("table");
		info2.setValue(table);
		request.addProperty(info2);

		PropertyInfo info1 = new PropertyInfo();
		info1.setNamespace(ServiceConstants.NAMESPACE);
		info1.setName("listValue");
		info1.setValue(listValue);
		request.addProperty(info1);

		soap = new KSoapExecutor();
		SoapObject result = (SoapObject) soap.execute(request, url,
				Object.class);

		if (result != null && !Base.ANY_TYPE.equals(result.toString())) {
			ParserInterface face;
			try {
				face = (ParserInterface) Class.forName(className).newInstance();
				System.out.println(Class.forName(className).toString()
						+ result.getPropertyCount());
				list = face.parse(result);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @Title: selectFuzzyQuery
	 * @Description: 选择值模糊查询
	 * @param @param url
	 * @param @param table
	 * @param @param listValue
	 * @param @return
	 * @return Serializable
	 * @throws
	 */
	public static Serializable selectFuzzyQuery(URL url, String table,
			String listValue) throws CommunicationException,
			AuthorizationException, AuthenticationException {
		Serializable list = null;
		SoapObject request = SoapObjectBuilder.start()
				.withMethod("mobileserviceinterselectFuzzyQuery").build();

		PropertyInfo info2 = new PropertyInfo();
		info2.setNamespace(ServiceConstants.NAMESPACE);
		info2.setName("table");
		info2.setValue(table);
		request.addProperty(info2);

		PropertyInfo info1 = new PropertyInfo();
		info1.setNamespace(ServiceConstants.NAMESPACE);
		info1.setName("listValue");
		info1.setValue(listValue);
		request.addProperty(info1);

		soap = new KSoapExecutor();
		Object result = (Object) soap.execute(request, url, Object.class);
		if (result != null && !Base.ANY_TYPE.equals(result.toString())) {
			return list = result.toString();
		}
		return list;
	}

	/**
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @Title: getAssignNodeActions
	 * @Description: 获取流程操作
	 * @param @param url
	 * @param @param className
	 * @param @param userid
	 * @param @param pykeyid
	 * @param @param appName
	 * @param @param ownerTable
	 * @param @return
	 * @return Serializable
	 * @throws
	 */
	public static Serializable getAssignNodeActions(URL url, String className,
			String userid, String pykeyid, String appName, String ownerTable)
			throws CommunicationException, AuthorizationException,
			AuthenticationException {
		Serializable list = null;
		SoapObject request = SoapObjectBuilder.start()
				.withMethod("mobileserviceintergetAssignNodeActions").build();

		PropertyInfo info1 = new PropertyInfo();
		info1.setNamespace(ServiceConstants.NAMESPACE);
		info1.setName("userid");
		info1.setValue(userid);
		request.addProperty(info1);

		PropertyInfo info2 = new PropertyInfo();
		info2.setNamespace(ServiceConstants.NAMESPACE);
		info2.setName("pykeyid");
		info2.setValue(pykeyid);
		request.addProperty(info2);

		PropertyInfo info3 = new PropertyInfo();
		info3.setNamespace(ServiceConstants.NAMESPACE);
		info3.setName("appName");
		info3.setValue(appName);
		request.addProperty(info3);

		PropertyInfo info4 = new PropertyInfo();
		info4.setNamespace(ServiceConstants.NAMESPACE);
		info4.setName("ownerTable");
		info4.setValue(ownerTable);
		request.addProperty(info4);

		soap = new KSoapExecutor();
		SoapObject result = (SoapObject) soap.execute(request, url,
				Object.class);

		if (result != null && !Base.ANY_TYPE.equals(result.toString())) {
			ParserInterface face;
			try {
				face = (ParserInterface) Class.forName(className).newInstance();
				System.out.println(Class.forName(className).toString()
						+ result.getPropertyCount());
				list = face.parse(result);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @Title: approvePro
	 * @Description: 审批流程
	 * @param @param url
	 * @param @param userid
	 * @param @param memo
	 * @param @param pykeyid
	 * @param @param appName
	 * @param @param ownerTable
	 * @param @param selectWhat
	 * @param @return
	 * @return Serializable
	 * @throws
	 */
	public static Serializable approvePro(URL url, String userid, String memo,
			String pykeyid, String appName, String ownerTable,
			boolean selectWhat) throws CommunicationException,
			AuthorizationException, AuthenticationException {
		Serializable list = null;
		SoapObject request = SoapObjectBuilder.start()
				.withMethod("mobileserviceinterapprovePro").build();

		PropertyInfo info1 = new PropertyInfo();
		info1.setNamespace(ServiceConstants.NAMESPACE);
		info1.setName("userid");
		info1.setValue(userid);
		request.addProperty(info1);

		PropertyInfo info2 = new PropertyInfo();
		info2.setNamespace(ServiceConstants.NAMESPACE);
		info2.setName("memo");
		info2.setValue(memo);
		request.addProperty(info2);

		PropertyInfo info3 = new PropertyInfo();
		info3.setNamespace(ServiceConstants.NAMESPACE);
		info3.setName("pykeyid");
		info3.setValue(pykeyid);
		request.addProperty(info3);

		PropertyInfo info4 = new PropertyInfo();
		info4.setNamespace(ServiceConstants.NAMESPACE);
		info4.setName("appName");
		info4.setValue(appName);
		request.addProperty(info4);

		PropertyInfo info5 = new PropertyInfo();
		info5.setNamespace(ServiceConstants.NAMESPACE);
		info5.setName("ownerTable");
		info5.setValue(ownerTable);
		request.addProperty(info5);

		PropertyInfo info6 = new PropertyInfo();
		info6.setNamespace(ServiceConstants.NAMESPACE);
		info6.setName("selectWhat");
		info6.setValue(selectWhat);
		request.addProperty(info6);

		soap = new KSoapExecutor();
		Object result = (Object) soap.execute(request, url, Object.class);
		if (result != null && !Base.ANY_TYPE.equals(result.toString())) {
			return list = result.toString();
		}
		return list;
	}

}
