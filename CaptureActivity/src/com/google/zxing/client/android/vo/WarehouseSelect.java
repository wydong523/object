/**   
 * @Title: WarehouseSelect.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import com.google.zxing.client.android.utils.SoapHelper;
import com.google.zxing.client.android.vo.ProjectSelect.Fields;

/**
 * @Desctiption 库房选择值实体
 * @author 汪渝栋
 * @date 2013-10-14 下午2:35:54
 */
public class WarehouseSelect extends Base implements ParserInterface,
		Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -182424224729938631L;
	
	private String location;
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
		return "WarehouseSelect [location=" + location + ", description="
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
		SoapObject projectsData = result;
		WarehouseSelect warehousesSelect = null;
		WarehouseSelectList lineList = new WarehouseSelectList();
		ArrayList<WarehouseSelect> warehouses = lineList.getWarehouses();
		int resultCount = projectsData.getPropertyCount();
		for (int i = 0; i < resultCount; i++) {
			warehousesSelect = new WarehouseSelect();
			SoapObject subObj = (SoapObject) result.getProperty(i);
			warehousesSelect.setLocation(SoapHelper.getSoapObjectString(subObj,
					Fields.LOCATION));
			warehousesSelect.setDescription(SoapHelper.getSoapObjectString(
					subObj, Fields.DESCRIPTION));
			warehouses.add(warehousesSelect);
		}
		return lineList;
	}

}
