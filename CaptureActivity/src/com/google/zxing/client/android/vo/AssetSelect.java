/**   
 * @Title: AssetSelect.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import com.google.zxing.client.android.utils.SoapHelper;
import com.google.zxing.client.android.vo.LocationsSelect.Fields;

/**
 * @Desctiption 资产选择值实体
 * @author 汪渝栋
 * @date 2013-10-10 上午10:26:28
 */
public class AssetSelect extends Base implements Serializable, ParserInterface {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -4610705045805776803L;

	/**
	 * 资产
	 */
	private String assetNum;

	/**
	 * 描述
	 */
	private String description;

	@Override
	public String toString() {
		return "AssetSelect [assetNum=" + assetNum + ", description="
				+ description + "]";
	}

	public String getAssetNum() {
		return assetNum;
	}

	public void setAssetNum(String assetNum) {
		this.assetNum = assetNum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	class Fields {
		public static final String ASSETNUM = "ASSETNUM";
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
		AssetSelect assetSelect = null;
		AssetSelectList lineList = new AssetSelectList();
		ArrayList<AssetSelect> assets = lineList.getAssets();
		int resultCount = locationsData.getPropertyCount();
		for (int i = 0; i < resultCount; i++) {
			assetSelect = new AssetSelect();
			SoapObject subObj = (SoapObject) result.getProperty(i);
			assetSelect.setAssetNum(SoapHelper.getSoapObjectString(subObj,
					Fields.ASSETNUM));
			assetSelect.setDescription(SoapHelper.getSoapObjectString(subObj,
					Fields.DESCRIPTION));
			assets.add(assetSelect);
		}
		return lineList;
	}

}
