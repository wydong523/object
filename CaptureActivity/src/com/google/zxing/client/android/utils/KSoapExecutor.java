package com.google.zxing.client.android.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.transport.HttpsTransportSE;
import org.ksoap2.transport.Transport;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;

import com.google.zxing.client.android.exceptions.AuthenticationException;
import com.google.zxing.client.android.exceptions.AuthorizationException;
import com.google.zxing.client.android.exceptions.CommunicationException;
import com.google.zxing.client.android.exceptions.RemoteAuthenticationException;
import com.google.zxing.client.android.exceptions.RemoteException;
import com.google.zxing.client.android.exceptions.RemotePermissionException;
import com.google.zxing.client.android.exceptions.RemoteValidationException;
import com.google.zxing.client.android.webservice.ReadAccountService;

public class KSoapExecutor {

	ReadAccountService accountService;

	private static final int TIMEOUT = 20000;
	private static String SOAP_ACTION = "\"urn:action\"";
//	private static final String WSDL_PATH = "?method=";

	public <T> T execute(SoapObject soapObject, Class<T> clazz)
			throws CommunicationException, AuthorizationException,
			AuthenticationException {
		URL instanceUrl = accountService.getServerUrl();
		return execute(soapObject, instanceUrl, clazz);
	}

	public void execute(SoapObject soapObject) throws CommunicationException,
			AuthorizationException, AuthenticationException {
		URL instanceUrl = accountService.getServerUrl();
		this.execute(soapObject, instanceUrl, Object.class);
	}

	public void execute(SoapObject soapObject, URL instanceURL)
			throws CommunicationException, AuthorizationException,
			AuthenticationException {
		this.execute(soapObject, instanceURL, Object.class);
	}

	@SuppressWarnings("unchecked")
	public <T> T execute(SoapObject soapObject, URL instanceURL, Class<T> clazz)
			throws CommunicationException, AuthorizationException,
			AuthenticationException {

		Log.i(KSoapExecutor.class.getName(),
				"executing command with soapObject:\n" + soapObject);
		Transport transport = getTransport(instanceURL);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapSerializationEnvelope.VER11);
		envelope.setOutputSoapObject(soapObject);
		try {
			transport.call(SOAP_ACTION, envelope);
		} catch (IOException e) {
			throw new CommunicationException(e);
		} catch (XmlPullParserException e) {
			throw new CommunicationException(e);
		}

		T result = null;
		try {
			result = (T) envelope.getResponse();
			//SoapObject env = (SoapObject) envelope.bodyIn;
			//@SuppressWarnings("unused")
			// it's called to handle potential exception
			//Object property = env.getProperty(0);
		} catch (SoapFault e) {
			handleRemoteExceptions(e);
		}
		Log.i(KSoapExecutor.class.getName(), "result returned:\n" + result);
		return result;
	}

	private Transport getTransport(URL instanceURL) {
		URL transportURL = getURLwithWSDLPath(instanceURL);
		if ("http".equals(transportURL.getProtocol())) {
			return new HttpTransportSE(transportURL.toExternalForm(), TIMEOUT);
		} else if ("https".equals(transportURL.getProtocol())) {
			return new HttpsTransportSE(transportURL.getHost(),
					transportURL.getPort(), transportURL.getFile(), TIMEOUT);
		} else {
			throw new IllegalArgumentException(
					"Doesn't support other protocols than HTTP & HTTPS for now");
		}
	}

	private void handleRemoteExceptions(SoapFault ex)
			throws AuthorizationException, AuthenticationException,
			CommunicationException {
		try {
			throwException(ex);
		} catch (RemotePermissionException e) {
			throw new AuthorizationException(e);
		} catch (RemoteAuthenticationException e) {
			throw new AuthenticationException(e);
		} catch (RemoteValidationException e) {
			throw new CommunicationException(e);
		} catch (RemoteException e) {
			throw new CommunicationException(e);
		}

	}

	private void throwException(SoapFault ex) throws RemotePermissionException,
			RemoteAuthenticationException, RemoteValidationException,
			RemoteException {
		String msg = ex.getMessage();

		if (msg.contains("RemotePermissionException")) {
			throw new RemotePermissionException(ex);
		} else if (msg.contains("RemoteAuthenticationException")) {
			throw new RemoteAuthenticationException(ex);
		} else if (msg.contains("RemoteValidationException")) {
			throw new RemoteValidationException(ex);
		} else {
			throw new RemoteException(ex);
		}

	}

	private URL getURLwithWSDLPath(URL instanceURL) {
		URL result;
		try {
			result = new URL(instanceURL.getProtocol(), instanceURL.getHost(),
					instanceURL.getPort(), instanceURL.getFile());
		} catch (MalformedURLException e) {
			String msg = "This shouldn't happen because we only modify URL we received";
			Log.d(getClass().getName(), msg);
			throw new RuntimeException(msg);
		}
		return result;
	}
}
