/**   
 * @Title: Wfaction.java 
 * @Package com.google.zxing.client.android.vo 
 * @version V1.0   
 */
package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import com.google.zxing.client.android.utils.SoapHelper;
import com.google.zxing.client.android.vo.WorkorderPlanTask.Fields;

/**
 * @Desctiption 审批操作实体
 * @author 汪渝栋
 * @date 2013-11-15 下午3:06:20
 */
public class Wfaction implements Serializable, ParserInterface {

	// 操作id
	// 原节点
	// 目标节点
	// 操作
	// 说明
	// 过程修订
	// 正向
	// 过程
	// 唯一标识

	private String actionid;
	private String ownernodeid;
	private String membernodeid;
	private String action;
	private String instruction;
	private String processrev;
	private String ispositive;
	private String processname;
	private String wfactionid;

	public String getActionid() {
		return actionid;
	}

	public void setActionid(String actionid) {
		this.actionid = actionid;
	}

	public String getOwnernodeid() {
		return ownernodeid;
	}

	public void setOwnernodeid(String ownernodeid) {
		this.ownernodeid = ownernodeid;
	}

	public String getMembernodeid() {
		return membernodeid;
	}

	public void setMembernodeid(String membernodeid) {
		this.membernodeid = membernodeid;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public String getProcessrev() {
		return processrev;
	}

	public void setProcessrev(String processrev) {
		this.processrev = processrev;
	}

	public String getIspositive() {
		return ispositive;
	}

	public void setIspositive(String ispositive) {
		this.ispositive = ispositive;
	}

	public String getProcessname() {
		return processname;
	}

	public void setProcessname(String processname) {
		this.processname = processname;
	}

	public String getWfactionid() {
		return wfactionid;
	}

	public void setWfactionid(String wfactionid) {
		this.wfactionid = wfactionid;
	}

	@Override
	public String toString() {
		return "Wfaction [actionid=" + actionid + ", ownernodeid="
				+ ownernodeid + ", membernodeid=" + membernodeid + ", action="
				+ action + ", instruction=" + instruction + ", processrev="
				+ processrev + ", ispositive=" + ispositive + ", processname="
				+ processname + ", wfactionid=" + wfactionid + "]";
	}

	class Fields {
		public static final String ACTIONID = "ACTIONID";
		public static final String OWNERNODEID = "OWNERNODEID";
		public static final String MEMBERNODEID = "MEMBERNODEID";
		public static final String ACTION = "ACTION";
		public static final String INSTRUCTION = "INSTRUCTION";
		public static final String PROCESSREV = "PROCESSREV";
		public static final String ISPOSITIVE = "ISPOSITIVE";
		public static final String PROCESSNAME = "PROCESSNAME";
		public static final String WFACTIONID = "WFACTIONID";
	}

	/*
	 * (非 Javadoc) <p>Title: parse</p> <p>Description: </p>
	 * 
	 * @param result
	 * 
	 * @return
	 * 
	 * @see com.google.zxing.client.android.vo.ParserInterface#parse(org.ksoap2.
	 * serialization.SoapObject)
	 */
	@Override
	public Serializable parse(SoapObject result) {
		SoapObject datas = result;
		Wfaction wfaction = null;
		ArrayList<Wfaction> wfactions = new ArrayList<Wfaction>();
		int resultCount = datas.getPropertyCount();
		for (int i = 0; i < resultCount; i++) {
			wfaction = new Wfaction();
			SoapObject subObj = (SoapObject) result.getProperty(i);
			wfaction.setActionid(SoapHelper.getSoapObjectString(subObj,
					Fields.ACTIONID));
			wfaction.setOwnernodeid(SoapHelper.getSoapObjectString(subObj,
					Fields.OWNERNODEID));
			wfaction.setMembernodeid(SoapHelper.getSoapObjectString(subObj,
					Fields.MEMBERNODEID));
			wfaction.setAction(SoapHelper.getSoapObjectString(subObj,
					Fields.ACTION));
			wfaction.setInstruction(SoapHelper.getSoapObjectString(subObj,
					Fields.INSTRUCTION));
			wfaction.setProcessrev(SoapHelper.getSoapObjectString(subObj,
					Fields.PROCESSREV));
			wfaction.setIspositive(SoapHelper.getSoapObjectString(subObj,
					Fields.ISPOSITIVE));
			wfaction.setProcessname(SoapHelper.getSoapObjectString(subObj,
					Fields.PROCESSNAME));
			wfaction.setWfactionid(SoapHelper.getSoapObjectString(subObj,
					Fields.WFACTIONID));
			wfactions.add(wfaction);
		}
		return wfactions;
	}

}
