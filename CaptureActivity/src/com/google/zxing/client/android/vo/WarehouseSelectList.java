/**   
 * @Title: WarehouseSelectList.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Desctiption �ⷿѡ��ֵ�б�ʵ��
 * @author ���嶰
 * @date 2013-10-14 ����2:40:23
 */
public class WarehouseSelectList implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -1407287432257212218L;

	private int pagesize;

	private int warehouseCount;

	private ArrayList<WarehouseSelect> warehouses = new ArrayList<WarehouseSelect>();

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getWarehouseCount() {
		return warehouseCount;
	}

	public void setWarehouseCount(int warehouseCount) {
		this.warehouseCount = warehouseCount;
	}

	public ArrayList<WarehouseSelect> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(ArrayList<WarehouseSelect> warehouses) {
		this.warehouses = warehouses;
	}
}
