package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * PRLine实体列表
 * 
 * @author 汪渝栋
 * 
 */
public class PRLineList implements Serializable {

	private static final long serialVersionUID = -6415943193005182783L;

	private int pagesize;

	private int prLinesCount;

	private ArrayList<PRLine> prLines = new ArrayList<PRLine>();

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPrLinesCount() {
		return prLinesCount;
	}

	public void setPrLinesCount(int prLinesCount) {
		this.prLinesCount = prLinesCount;
	}

	public ArrayList<PRLine> getPrLines() {
		return prLines;
	}

	public void setPrLines(ArrayList<PRLine> prLines) {
		this.prLines = prLines;
	}

}
