/**
 * Project Name:JZGPingGuShi
 * File Name:StringUtil.java
 * Package Name:com.gc.jzgpinggushi.uitls
 * Date:2014-10-8下午2:18:50
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
*/

package com.gc.jzgpinggushi.uitls;
/**
 * ClassName:StringUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-10-8 下午2:18:50 <br/>
 * @author   汪渝栋
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class StringUtil
{
	public static CharSequence getlv(int carLevel)
	{
		String lv = null;
		switch (carLevel) {
		case 1:
			lv = "A级";
			break;
		case 2:
			lv = "B级";
			break;
		case 3:
			lv = "C级";
			break;
		case 4:
			lv = "D级";
			break;
		case 5:
			lv = "E级";
			break;
		}
		return lv;
	}

	public static CharSequence getMileage(int mileage)
	{
		String mMileage = null;
		if (mileage >= 10000)
		{
			mMileage = mileage / 10000 + "万多公里";
		} else if (mileage < 500)
		{
			mMileage = "小于500公里";
		} else if (mileage > 500 && mileage < 5000)
		{
			mMileage = "小于5000公里";
		} else if (mileage >= 5000 && mileage < 10000)
		{
			mMileage = "小于10000公里";
		}
		return mMileage;
	}
}

