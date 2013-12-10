/**   
 * @Title: PMSelectList.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Desctiption pm选择值列表实体
 * @author 汪渝栋
 * @date 2013-10-12 上午10:34:20
 */
public class PMSelectList implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO 
	*/ 
	private static final long serialVersionUID = 7195453391699431154L;

	private int pagesize;

	private int pmCount;

	private ArrayList<PMSelect> pms = new ArrayList<PMSelect>();

	@Override
	public String toString() {
		return "PMSelectList [pagesize=" + pagesize + ", pmCount=" + pmCount
				+ ", pms=" + pms + "]";
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPmCount() {
		return pmCount;
	}

	public void setPmCount(int pmCount) {
		this.pmCount = pmCount;
	}

	public ArrayList<PMSelect> getPms() {
		return pms;
	}

	public void setPms(ArrayList<PMSelect> pms) {
		this.pms = pms;
	}
	
}
