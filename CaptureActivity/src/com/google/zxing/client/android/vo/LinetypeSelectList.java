/**   
 * @Title: LinetypeSelectList.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Desctiption
 * @author ���嶰
 * @date 2013-10-30 ����2:29:47
 */
public class LinetypeSelectList implements Serializable {
	/**
	 * value:������ֵ description:������ֵ����
	 */
	private int pagesize;

	private int typesCount;

	private ArrayList<LinetypeSelect> types = new ArrayList<LinetypeSelect>();

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getTypesCount() {
		return typesCount;
	}

	public void setTypesCount(int typesCount) {
		this.typesCount = typesCount;
	}

	public ArrayList<LinetypeSelect> getTypes() {
		return types;
	}

	public void setTypes(ArrayList<LinetypeSelect> types) {
		this.types = types;
	}

	@Override
	public String toString() {
		return "LinetypeSelectList [pagesize=" + pagesize + ", typesCount="
				+ typesCount + ", types=" + types + "]";
	}

}
