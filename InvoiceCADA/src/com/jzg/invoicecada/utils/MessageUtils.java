/**
 * Project Name:InvoiceCADA
 * File Name:MessageUtils.java
 * Package Name:com.example.invoicecada
 * Date:2014-4-15下午1:48:34
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.jzg.invoicecada.utils;

import android.os.Handler;
import android.os.Message;

/**
 * ClassName:MessageUtils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-15 下午1:48:34 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class MessageUtils
{
	public static void sendMessage(Handler handler, int id, Object object)
	{
		Message msg = new Message();
		msg.what = id;
		if (object != null)
			msg.obj = object;
		handler.sendMessage(msg);
	}
}
