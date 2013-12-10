/**   
 * @Title: MeasureunitSelect.java 
 * @Package com.google.zxing.client.android.ui 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import com.google.zxing.client.android.utils.SoapHelper;

/**
 * @Desctiption 单位选择值实体
 * @author 汪渝栋
 * @date 2013-10-30 下午3:14:00
 */
public class MeasureunitSelect extends Base implements ParserInterface,
		Serializable {
	private String measureunitid;
	private String abbreviation;

	@Override
	public String toString() {
		return "MeasureunitSelect [measureunitid=" + measureunitid
				+ ", abbreviation=" + abbreviation + "]";
	}

	public String getMeasureunitid() {
		return measureunitid;
	}

	public void setMeasureunitid(String measureunitid) {
		this.measureunitid = measureunitid;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	class Fields {
		public static final String MEASUREUNITID = "MEASUREUNITID";
		public static final String ABBREVIATION = "ABBREVIATION";
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
		SoapObject unitsData = result;
		MeasureunitSelect unit = null;
		MeasureunitSelectList lineList = new MeasureunitSelectList();
		ArrayList<MeasureunitSelect> units = lineList.getUnits();
		int resultCount = unitsData.getPropertyCount();
		for (int i = 0; i < resultCount; i++) {
			unit = new MeasureunitSelect();
			SoapObject subObj = (SoapObject) result.getProperty(i);
			unit.setMeasureunitid(SoapHelper.getSoapObjectString(subObj,
					Fields.MEASUREUNITID));
			unit.setAbbreviation(SoapHelper.getSoapObjectString(subObj,
					Fields.ABBREVIATION));
			units.add(unit);
		}
		return lineList;
	}

}
