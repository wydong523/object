/**
 * Project Name:JZGPingGuShi
 * File Name:OfferSuccessList.java
 * Package Name:com.gc.jzgpinggushi.vo
 * Date:2014-9-9下午3:24:45
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ClassName:OfferSuccessList <br/>
 * Function: 出价成功列表实体. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-9 下午3:24:45 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class OfferSuccessList extends BaseObject implements Serializable
{
	private ArrayList<OfferSuccess> offerSuccesses = new ArrayList<OfferSuccess>();

	public ArrayList<OfferSuccess> getOfferSuccesses()
	{
		return offerSuccesses;
	}

	public void setOfferSuccesses(ArrayList<OfferSuccess> offerSuccesses)
	{
		this.offerSuccesses = offerSuccesses;
	}

}
