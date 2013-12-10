package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * PR数据列表实体
 * 
 * @author 汪渝栋
 * 
 */
public class PRList extends Base implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6787062295509090340L;

	private int pagesize;

	private int prsCount;

	private ArrayList<PR> prs = new ArrayList<PR>();

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPrsCount() {
		return prsCount;
	}

	public void setPrsCount(int prsCount) {
		this.prsCount = prsCount;
	}

	public ArrayList<PR> getPrs() {
		return prs;
	}

	public void setPrs(ArrayList<PR> prs) {
		this.prs = prs;
	}
}
