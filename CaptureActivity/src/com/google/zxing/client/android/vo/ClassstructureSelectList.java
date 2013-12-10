/**   
 * @Title: ClassstructureSelectList.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Desctiption ����ѡ��ֵ�б�ʵ��
 * @author ���嶰
 * @date 2013-10-12 ����4:26:37
 */
public class ClassstructureSelectList implements Serializable {

	private int pagesize;

	private int csCount;

	private ArrayList<ClassstructureSelect> classs = new ArrayList<ClassstructureSelect>();

	@Override
	public String toString() {
		return "ClassstructureSelectList [pagesize=" + pagesize + ", csCount="
				+ csCount + ", classs=" + classs + "]";
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getCsCount() {
		return csCount;
	}

	public void setCsCount(int csCount) {
		this.csCount = csCount;
	}

	public ArrayList<ClassstructureSelect> getClasss() {
		return classs;
	}

	public void setClasss(ArrayList<ClassstructureSelect> classs) {
		this.classs = classs;
	}

}
