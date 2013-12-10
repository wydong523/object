/**   
 * @Title: Asset.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import com.google.zxing.client.android.utils.SoapHelper;
import com.google.zxing.client.android.vo.PMSelect.Fields;

/**
 * @Desctiption 资产实体
 * @author 汪渝栋
 * @date 2013-10-23 上午10:40:33
 */
public class Asset extends Base implements Serializable, ParserInterface {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 512459814347480295L;

	private String assetnum;

	private String description;

	private String location;

	public String getAssetnum() {
		return assetnum;
	}

	public void setAssetnum(String assetnum) {
		this.assetnum = assetnum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Asset [assetnum=" + assetnum + ", description=" + description
				+ ", location=" + location + "]";
	}

	class Fields {
		public static final String ASSETNUM = "ASSETNUM";
		public static final String DESCRIPTION = "DESCRIPTION";
		public static final String LOCATION = "LOCATION";
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
		SoapObject assetSet = result;
		Asset asset = new Asset();
		int resultCount = assetSet.getPropertyCount();
		if (resultCount == 1) {
			SoapObject subObj = (SoapObject) result.getProperty(0);
			asset.setAssetnum(SoapHelper.getSoapObjectString(subObj,
					Fields.ASSETNUM));
			asset.setDescription(SoapHelper.getSoapObjectString(subObj,
					Fields.DESCRIPTION));
			asset.setDescription(SoapHelper.getSoapObjectString(subObj,
					Fields.LOCATION));
		}
		return asset;
	}
}
