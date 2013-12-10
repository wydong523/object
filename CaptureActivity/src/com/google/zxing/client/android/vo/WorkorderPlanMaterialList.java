/**   
 * @Title: WorkorderPlanMaterialList.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Desctiption 物料列表实体
 * @author 汪渝栋
 * @date 2013-10-24 下午2:29:18
 */
public class WorkorderPlanMaterialList implements Serializable {
	private int pagesize;

	private int materialCount;

	private ArrayList<WorkorderPlanMaterial> materials = new ArrayList<WorkorderPlanMaterial>();

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getMaterialCount() {
		return materialCount;
	}

	public void setMaterialCount(int materialCount) {
		this.materialCount = materialCount;
	}

	public ArrayList<WorkorderPlanMaterial> getMaterials() {
		return materials;
	}

	public void setMaterials(ArrayList<WorkorderPlanMaterial> materials) {
		this.materials = materials;
	}

	@Override
	public String toString() {
		return "WorkorderPlanMaterialList [pagesize=" + pagesize
				+ ", materialCount=" + materialCount + ", materials="
				+ materials + "]";
	}

}
