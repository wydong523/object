/**   
 * @Title: HandlerUtils.java 
 * @Package com.google.zxing.client.android.utils 
 * @version V1.0   
 */
package com.google.zxing.client.android.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.vo.Workorder;

import android.os.Handler;
import android.os.Message;

/**
 * @Desctiption handler工具类
 * @author 汪渝栋
 * @date 2013-10-11 下午1:55:17
 */
public class HandlerUtils {

	/**
	 * @Title: sendMessage
	 * @Description: 发送信息
	 * @param @param handler
	 * @param @param datas
	 * @param @param locationSelect
	 * @return void
	 * @throws
	 */
	public static void sendMessage(Handler handler, TreeSet<String> datas,
			int ids) {
		Message message = new Message();
		message.obj = datas;
		message.what = ids;
		handler.sendMessage(message);
	}

	public static void sendMessage(Handler handler, Serializable datas, int ids) {
		Message message = new Message();
		message.obj = datas;
		message.what = ids;
		handler.sendMessage(message);
	}

	public static void sendMessage(Handler handler, String datas, int ids) {
		Message message = new Message();
		message.obj = datas;
		message.what = ids;
		handler.sendMessage(message);
	}

	/**
	 * @Title: sendMessage
	 * @Description: 发送信息
	 * @param @param handler
	 * @param @param datas
	 * @param @param locationSelect
	 * @return void
	 * @throws
	 */
	public static void sendMessage(Handler handler, ArrayList<Workorder> datas,
			int ids) {
		Message message = new Message();
		message.obj = datas;
		message.what = ids;
		handler.sendMessage(message);
	}

	/**
	 * 
	 * @Title: sendErrorMessage
	 * @Description: TODO
	 * @param @param handler
	 * @param @param id
	 * @param @param error
	 * @return void
	 * @throws
	 */
	public static void sendErrorMessage(Handler handler, int id, String error) {
		Message message = new Message();
		message.obj = error;
		message.what = id;
		handler.sendMessage(message);
	}

}
