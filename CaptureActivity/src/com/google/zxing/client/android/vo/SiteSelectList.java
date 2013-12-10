/**   
 * @Title: SiteSelectList.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Desctiption 地点选择值列表尸体
 * @author 汪渝栋
 * @date 2013-10-30 下午3:43:37
 */
public class SiteSelectList implements Serializable {
	private int pagesize;

	private int siteCount;

	private ArrayList<SiteSelect> sites = new ArrayList<SiteSelect>();

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getSiteCount() {
		return siteCount;
	}

	public void setSiteCount(int siteCount) {
		this.siteCount = siteCount;
	}

	public ArrayList<SiteSelect> getSites() {
		return sites;
	}

	public void setSites(ArrayList<SiteSelect> sites) {
		this.sites = sites;
	}

	@Override
	public String toString() {
		return "SiteSelectList [pagesize=" + pagesize + ", siteCount="
				+ siteCount + ", sites=" + sites + "]";
	}

}
