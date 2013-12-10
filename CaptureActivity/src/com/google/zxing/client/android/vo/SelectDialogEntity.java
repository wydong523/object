/**   
 * @Title: PopDialogEntity.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.Set;

import android.content.Context;
import android.widget.EditText;

import com.google.zxing.client.android.app.AppContext;

/**
 * @Desctiption ѡ��ֵ��������ʵ�����
 * @author ���嶰
 * @date 2013-11-5 ����10:18:04
 */
public class SelectDialogEntity implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 6615953134781933144L;

	private Context context;// ��ǰactivity

	private Set<String> contents;// ��Ҫ��װ������

	private String title; // ����

	private EditText edit;// ����б������Edit

	private EditText getFocusView;// �����ɺ���һ����ȥ����Ŀؼ�

	private AppContext appContext;

	private String listValue;// ģ����ѯ����

	private String tableName;// ��ѯ�ı���

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public Set<String> getContents() {
		return contents;
	}

	public void setContents(Set<String> contents) {
		this.contents = contents;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public EditText getEdit() {
		return edit;
	}

	public void setEdit(EditText edit) {
		this.edit = edit;
	}

	public EditText getGetFocusView() {
		return getFocusView;
	}

	public void setGetFocusView(EditText getFocusView) {
		this.getFocusView = getFocusView;
	}

	public AppContext getAppContext() {
		return appContext;
	}

	public void setAppContext(AppContext appContext) {
		this.appContext = appContext;
	}

	public String getListValue() {
		return listValue;
	}

	public void setListValue(String listValue) {
		this.listValue = listValue;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName
	 *            the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Override
	public String toString() {
		return "PopDialogEntity [context=" + context + ", contents=" + contents
				+ ", title=" + title + ", edit=" + edit + ", getFocusView="
				+ getFocusView + ", appContext=" + appContext + ", listValue="
				+ listValue + ", tableName=" + tableName + "]";
	}

}
