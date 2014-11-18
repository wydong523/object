/**
 * Project Name:JZGPingGuShi
 * File Name:OfferList.java
 * Package Name:com.gc.jzgpinggushi.vo
 * Date:2014-9-9下午2:53:10
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ClassName:OfferList <br/>
 * Function: 已出价车辆实体列表. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-9 下午2:53:10 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class OfferList extends BaseObject implements Serializable
{
	private ArrayList<Offer> offerCars = new ArrayList<Offer>();

	public ArrayList<Offer> getOfferCars()
	{
		return offerCars;
	}

	public void setOfferCars(ArrayList<Offer> offerCars)
	{
		this.offerCars = offerCars;
	}
}
