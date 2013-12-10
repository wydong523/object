/**   
 * @Title: WorktypeSelectList.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Desctiption 工作类型选择值列表实体
 * @author 汪渝栋
 * @date 2013-10-10 上午10:36:34
 */
public class WorktypeSelectList implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 8278695579653607024L;

	private int pagesize;

	private int worktypesCount;

	private ArrayList<WorktypeSelect> worktypes = new ArrayList<WorktypeSelect>();

	@Override
	public String toString() {
		return "WorktypeSelectList [pagesize=" + pagesize + ", worktypesCount="
				+ worktypesCount + ", worktypes=" + worktypes + "]";
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getWorktypesCount() {
		return worktypesCount;
	}

	public void setWorktypesCount(int worktypesCount) {
		this.worktypesCount = worktypesCount;
	}

	public ArrayList<WorktypeSelect> getWorktypes() {
		return worktypes;
	}

	public void setWorktypes(ArrayList<WorktypeSelect> worktypes) {
		this.worktypes = worktypes;
	}

}
