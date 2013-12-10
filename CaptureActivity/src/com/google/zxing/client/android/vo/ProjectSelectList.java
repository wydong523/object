/**   
 * @Title: ProjectSelectList.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Desctiption 项目选择值列表实体
 * @author 汪渝栋
 * @date 2013-10-14 上午11:51:33
 */
public class ProjectSelectList implements Serializable {
	/** 
	* @Fields serialVersionUID : TODO 
	*/ 
	private static final long serialVersionUID = -243311892699036639L;

	private int pagesize;

	private int projectsCount;

	private ArrayList<ProjectSelect> projects = new ArrayList<ProjectSelect>();

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getProjectsCount() {
		return projectsCount;
	}

	public void setProjectsCount(int projectsCount) {
		this.projectsCount = projectsCount;
	}

	public ArrayList<ProjectSelect> getProjects() {
		return projects;
	}

	public void setProjects(ArrayList<ProjectSelect> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "ProjectSelectList [pagesize=" + pagesize + ", projectsCount="
				+ projectsCount + ", projects=" + projects + "]";
	}

}
