/**   
 * @Title: MeasureunitSelectList.java 
 * @Package com.google.zxing.client.android.ui 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * @Desctiption ��λѡ��ֵ�б�ʵ��
 * @author ���嶰
 * @date 2013-10-30 ����3:14:35
 */
public class MeasureunitSelectList implements Serializable {
	private int pagesize;

	private int unitCount;

	private ArrayList<MeasureunitSelect> units = new ArrayList<MeasureunitSelect>();

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getUnitCount() {
		return unitCount;
	}

	public void setUnitCount(int unitCount) {
		this.unitCount = unitCount;
	}

	public ArrayList<MeasureunitSelect> getUnits() {
		return units;
	}

	public void setUnits(ArrayList<MeasureunitSelect> units) {
		this.units = units;
	}

	@Override
	public String toString() {
		return "MeasureunitSelectList [pagesize=" + pagesize + ", unitCount="
				+ unitCount + ", units=" + units + "]";
	}

}
