package com.google.zxing.client.android.connector;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import com.google.zxing.client.android.constant.ServiceConstants;
import com.google.zxing.client.android.exceptions.AuthenticationException;
import com.google.zxing.client.android.exceptions.AuthorizationException;
import com.google.zxing.client.android.exceptions.CommunicationException;
import com.google.zxing.client.android.vo.Account;
import com.google.zxing.client.android.webservice.ReadAccountService;

public final class Connector {

	private static final String TAG = Connector.class.getSimpleName();

	private boolean isConnected = false;

	private ReadAccountService readAccountService;

	public boolean getIsConnected() {
		return isConnected;
	}

	public void setIsConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

	public String getAccount(String servicePath)
			throws IllegalStateException, AuthenticationException,
			CommunicationException, AuthorizationException {
		String accounts = null;
		try {
			URL url = new URL(servicePath);
			readAccountService = new ReadAccountService();
			accounts = readAccountService.getAccount(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return accounts;

	}

	public String checkPassword(String username, String pass, String url)
			throws CommunicationException, AuthorizationException,
			AuthenticationException {
		String result = null;
		try {
			URL loginUrl = new URL(url);
			readAccountService = new ReadAccountService();
			result = readAccountService.checkPassword(loginUrl, username, pass);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
