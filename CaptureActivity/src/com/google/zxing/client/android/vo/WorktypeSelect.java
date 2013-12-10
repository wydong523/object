/**   
 * @Title: WorktypeSelect.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import com.google.zxing.client.android.utils.SoapHelper;
import com.google.zxing.client.android.vo.PRLine.Fileds;

/**
 * @Desctiption 工作类型选择值实体
 * @author 汪渝栋
 * @date 2013-10-10 上午10:32:13
 */
public class WorktypeSelect extends Base implements Serializable,
		ParserInterface {

	/**
	 * 工作类型
	 */
	private String worktype;

	/**
	 * 描述
	 */
	private String wtypedesc;

	@Override
	public String toString() {
		return "WorktypeSelect [worktype=" + worktype + ", wtypedesc="
				+ wtypedesc + "]";
	}

	public String getWorktype() {
		return worktype;
	}

	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}

	public String getWtypedesc() {
		return wtypedesc;
	}

	public void setWtypedesc(String wtypedesc) {
		this.wtypedesc = wtypedesc;
	}

	/*
	 * (非 Javadoc) <p>Title: parse</p> <p>Description: </p>
	 * 
	 * @see com.google.zxing.client.android.vo.SelectInterface#parse()
	 */
	@Override
	public Serializable parse(SoapObject result) {
		SoapObject worktypesData = result;
		WorktypeSelect worktypeSelect = null;
		WorktypeSelectList lineList = new WorktypeSelectList();
		ArrayList<WorktypeSelect> worktypes = lineList.getWorktypes();
		int resultCount = worktypesData.getPropertyCount();
		for (int i = 0; i < resultCount; i++) {
			worktypeSelect = new WorktypeSelect();
			SoapObject subObj = (SoapObject) result.getProperty(i);
			worktypeSelect.setWorktype(SoapHelper.getSoapObjectString(subObj,
					Fields.WORKTYPE));
			worktypeSelect.setWtypedesc(SoapHelper.getSoapObjectString(
					subObj, Fields.WTYPEDESC));
			worktypes.add(worktypeSelect);
		}
		return lineList;
	}

	class Fields {
		public static final String WORKTYPE = "WORKTYPE";
		public static final String WTYPEDESC = "WTYPEDESC";
	}

}
