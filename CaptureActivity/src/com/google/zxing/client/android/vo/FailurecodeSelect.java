/**   
 * @Title: FailurecodeSelect.java 
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
 * @Desctiption 故障代码选择值实体
 * @author 汪渝栋
 * @date 2013-10-10 上午10:39:47
 */
public class FailurecodeSelect extends Base implements Serializable,
		ParserInterface {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -2942543609454612679L;

	/**
	 * 故障代码
	 */
	private String failurecode;

	/**
	 * 描述
	 */
	private String description;

	public String getFailurecode() {
		return failurecode;
	}

	public void setFailurecode(String failurecode) {
		this.failurecode = failurecode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	class Fields {
		public static final String FAILURECODE = "FAILURECODE";
		public static final String DESCRIPTION = "DESCRIPTION";
	}

	
	@Override
	public String toString() {
		return "FailurecodeSelect [failurecode=" + failurecode
				+ ", description=" + description + "]";
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
		SoapObject failurecodeData = result;
		FailurecodeSelect failurecodeSelect = null;
		FailurecodeSelectList lineList = new FailurecodeSelectList();
		ArrayList<FailurecodeSelect> failurecodes = lineList.getFailurecodes();
		int resultCount = failurecodeData.getPropertyCount();
		for (int i = 0; i < resultCount; i++) {
			failurecodeSelect = new FailurecodeSelect();
			SoapObject subObj = (SoapObject) result.getProperty(i);
			failurecodeSelect.setFailurecode(SoapHelper.getSoapObjectString(
					subObj, Fields.FAILURECODE));
			failurecodeSelect.setDescription(SoapHelper.getSoapObjectString(
					subObj, Fields.DESCRIPTION));
			failurecodes.add(failurecodeSelect);
		}
		return lineList;
	}
}
