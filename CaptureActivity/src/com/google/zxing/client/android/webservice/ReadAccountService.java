package com.google.zxing.client.android.webservice;

import java.net.URL;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import android.util.Log;
import com.google.zxing.client.android.constant.ServiceConstants;
import com.google.zxing.client.android.exceptions.AuthenticationException;
import com.google.zxing.client.android.exceptions.AuthorizationException;
import com.google.zxing.client.android.exceptions.CommunicationException;
import com.google.zxing.client.android.utils.KSoapExecutor;
import com.google.zxing.client.android.utils.SoapObjectBuilder;
import com.google.zxing.client.android.vo.Base;

/**
 * 账户管理服务
 * 
 * @author 汪渝栋
 * 
 */
public class ReadAccountService {
	private static final String TAG = ReadAccountService.class.getSimpleName();
	private KSoapExecutor soap;
	private String token;
	private URL url;

	/**
	 * 获取账户信息
	 * 
	 * @param url
	 *            访问地址
	 * @return 返回用户信息
	 * @throws AuthenticationException
	 * @throws CommunicationException
	 * @throws AuthorizationException
	 * @throws IllegalStateException
	 */
	public String getAccount(URL url) throws AuthenticationException,
			CommunicationException, AuthorizationException,
			IllegalStateException {
		SoapObject request = SoapObjectBuilder.start()
				.withMethod(ServiceConstants.UserService.GETACCOUNT_METHOD)
				.build();
		PropertyInfo info = new PropertyInfo();
		info.setNamespace(ServiceConstants.NAMESPACE);
		info.setName("username");
		info.setValue("");
		request.addProperty(info);

		this.url = url;
		soap = new KSoapExecutor();
		Object result = (Object) soap.execute(request, url, Object.class);
		System.out.println("result.toString() is " + result.toString());
		if (!Base.ANY_TYPE.equals(result.toString())) {
			return result.toString();
		}
		Log.d(TAG, "getAccount is " + result);
		return result.toString();
	}

	/**
	 * 校验登录用户
	 * 
	 * @param url
	 *            service地址
	 * @param username
	 *            用户名
	 * 
	 * @param password
	 *            密码
	 * @return
	 * @throws CommunicationException
	 * @throws AuthorizationException
	 * @throws AuthenticationException
	 */
	public String checkPassword(URL url, String username, String password)
			throws CommunicationException, AuthorizationException,
			AuthenticationException {
		SoapObject request = SoapObjectBuilder.start()
				.withMethod(ServiceConstants.UserService.PASSWORD_METHOD)
				.build();

		PropertyInfo usernameInfo = new PropertyInfo();
		usernameInfo.setNamespace(ServiceConstants.NAMESPACE);
		usernameInfo.setName(ServiceConstants.UserService.USERNAME_PARAM);
		usernameInfo.setValue(username);
		request.addProperty(usernameInfo);

		this.url = url;
		soap = new KSoapExecutor();
		Object result = soap.execute(request, url, Object.class);
		this.setToken(result.toString());
		return result.toString();
	}

	public URL getServerUrl() {
		if (url == null) {
			throw new IllegalStateException(
					"cannot return server url because not connected to any, some went wrong");
		}
		return url;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
