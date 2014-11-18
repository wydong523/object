/**
 * Project Name:InvoiceCADA
 * File Name:ActivityHelp.java
 * Package Name:com.example.invoicecada
 * Date:2014-4-15下午1:36:12
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.jzg.invoicecada.app;

import com.jzg.invoicecada.ui.TakingActivity;

import android.content.Context;
import android.content.Intent;

/**
 * ClassName:ActivityHelp <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-15 下午1:36:12 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class ActivityHelp
{

	public static void startActivity(Context context, Class<TakingActivity> cls)
	{
		Intent intent = new Intent(context, cls);
		context.startActivity(intent);
	}

}
