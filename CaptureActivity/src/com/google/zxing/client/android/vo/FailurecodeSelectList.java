/**   
 * @Title: FailurecodeSelectList.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Desctiption 故障工单选择列表实体
 * @author 汪渝栋
 * @date 2013-10-10 上午10:42:52
 * 
 */
public class FailurecodeSelectList implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 8278695579653607024L;

	private int pagesize;

	private int inboxsCount;

	private ArrayList<FailurecodeSelect> failurecodes = new ArrayList<FailurecodeSelect>();

	@Override
	public String toString() {
		return "FailurecodeSelectList [pagesize=" + pagesize + ", inboxsCount="
				+ inboxsCount + ", failurecodes=" + failurecodes + "]";
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getInboxsCount() {
		return inboxsCount;
	}

	public void setInboxsCount(int inboxsCount) {
		this.inboxsCount = inboxsCount;
	}

	public ArrayList<FailurecodeSelect> getFailurecodes() {
		return failurecodes;
	}

	public void setFailurecodes(ArrayList<FailurecodeSelect> failurecodes) {
		this.failurecodes = failurecodes;
	}

}
