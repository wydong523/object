/**   
 * @Title: LocationsSelect.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import com.google.zxing.client.android.utils.SoapHelper;

/**
 * @Desctiption 位置实体
 * @author 汪渝栋
 * @date 2013-10-10 上午10:05:39
 */
public class LocationsSelect extends Base implements Serializable,
		ParserInterface {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 4519992907091322769L;

	/**
	 * 位置
	 */
	private String location;

	/**
	 * 描述
	 */
	private String description;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "LocationsSelect [location=" + location + ", description="
				+ description + "]";
	}

	class Fields {
		public static final String LOCATION = "LOCATION";
		public static final String DESCRIPTION = "DESCRIPTION";
	}

	/*
	 * (非 Javadoc) <p>Title: parse</p> <p>Description: </p>
	 * 
	 * @param result
	 * 
	 * @return
	 * 
	 * @see com.google.zxing.client.android.vo.SelectInterface#parse(org.ksoap2.
	 * serialization.SoapObject)
	 */
	@Override
	public Serializable parse(SoapObject result) {
		SoapObject locationsData = result;
		LocationsSelect locationsSelect = null;
		LocationsSelectList lineList = new LocationsSelectList();
		ArrayList<LocationsSelect> locations = lineList.getLocations();
		int resultCount = locationsData.getPropertyCount();
		for (int i = 0; i < resultCount; i++) {
			locationsSelect = new LocationsSelect();
			SoapObject subObj = (SoapObject) result.getProperty(i);
			locationsSelect.setLocation(SoapHelper.getSoapObjectString(subObj,
					Fields.LOCATION));
			locationsSelect.setDescription(SoapHelper.getSoapObjectString(
					subObj, Fields.DESCRIPTION));
			locations.add(locationsSelect);
		}
		return lineList;
	}

}
