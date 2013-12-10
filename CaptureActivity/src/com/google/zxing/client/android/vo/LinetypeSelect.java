/**   
 * @Title: LinetypeSelect.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import com.google.zxing.client.android.utils.SoapHelper;
import com.google.zxing.client.android.vo.AssetSelect.Fields;

/**
 * @Desctiption 行类型选择值实体
 * @author 汪渝栋
 * @date 2013-10-30 下午2:26:30
 */
public class LinetypeSelect extends Base implements Serializable,
		ParserInterface {

	private String value;

	private String description;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "LinetypeSelect [value=" + value + ", description="
				+ description + "]";
	}

	class Fields {
		public static final String VALUE = "VALUE";
		public static final String DESCRIPTION = "DESCRIPTION";
	}

	/*
	 * (非 Javadoc) <p>Title: parse</p> <p>Description: </p>
	 * 
	 * @param result
	 * 
	 * @return
	 * 
	 * @see com.google.zxing.client.android.vo.ParserInterface#parse(org.ksoap2.
	 * serialization.SoapObject)
	 */
	@Override
	public Serializable parse(SoapObject result) {
		SoapObject typesData = result;
		LinetypeSelect linetype = null;
		LinetypeSelectList lineList = new LinetypeSelectList();
		ArrayList<LinetypeSelect> types = lineList.getTypes();
		int resultCount = typesData.getPropertyCount();
		for (int i = 0; i < resultCount; i++) {
			linetype = new LinetypeSelect();
			SoapObject subObj = (SoapObject) result.getProperty(i);
			linetype.setValue(SoapHelper.getSoapObjectString(subObj,
					Fields.VALUE));
			linetype.setDescription(SoapHelper.getSoapObjectString(subObj,
					Fields.DESCRIPTION));
			types.add(linetype);
		}
		return lineList;
	}

}
