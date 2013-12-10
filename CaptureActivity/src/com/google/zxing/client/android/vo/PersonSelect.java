/**   
 * @Title: PersonSelect.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import com.google.zxing.client.android.utils.SoapHelper;

/**
 * @Desctiption 人员选择值实体
 * @author 汪渝栋
 * @date 2013-10-10 上午10:57:26
 */
public class PersonSelect extends Base implements Serializable, ParserInterface {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 7989204741246212411L;

	/**
	 * 人员
	 */
	private String personid;

	/**
	 * 名称
	 */
	private String displayname;

	@Override
	public String toString() {
		return "PersonSelect [personid=" + personid + ", displayname="
				+ displayname + "]";
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	class Fields {
		public static final String PERSONID = "PERSONID";
		public static final String DISPLAYNAME = "DISPLAYNAME";
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
		SoapObject personData = result;
		PersonSelect personSelect = null;
		PersonSelectList lineList = new PersonSelectList();
		ArrayList<PersonSelect> personCodes = lineList.getPersons();
		int resultCount = personData.getPropertyCount();
		for (int i = 0; i < resultCount; i++) {
			personSelect = new PersonSelect();
			SoapObject subObj = (SoapObject) result.getProperty(i);
			personSelect.setPersonid(SoapHelper.getSoapObjectString(subObj,
					Fields.PERSONID));
			personSelect.setDisplayname(SoapHelper.getSoapObjectString(subObj,
					Fields.DISPLAYNAME));
			personCodes.add(personSelect);
		}
		return lineList;
	}
}
