/**   
 * @Title: AssetSelectList.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Desctiption 资产选择列表实体
 * @author 汪渝栋
 * @date 2013-10-10 上午10:29:06
 */
public class AssetSelectList implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 6053086664904648349L;

	private int pagesize;

	private int inboxsCount;

	private ArrayList<AssetSelect> assets = new ArrayList<AssetSelect>();

	@Override
	public String toString() {
		return "AssetSelectList [pagesize=" + pagesize + ", inboxsCount="
				+ inboxsCount + ", assets=" + assets + "]";
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getInboxsCount() {
		return inboxsCount;
	}

	public void setInboxsCount(int inboxsCount) {
		this.inboxsCount = inboxsCount;
	}

	public ArrayList<AssetSelect> getAssets() {
		return assets;
	}

	public void setAssets(ArrayList<AssetSelect> assets) {
		this.assets = assets;
	}

}
