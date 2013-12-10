package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;
import org.ksoap2.serialization.SoapObject;
import com.google.zxing.client.android.utils.SoapHelper;

/**
 * inbox实体
 * 
 * @author 汪渝栋
 * 
 */
public class Inbox extends Base implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3099841117936190408L;
	private String assignid;
	private String description;
	private String priority;
	private String assigncode;
	private String app;
	private String timelimit;
	private String startdate;
	private String duedate;
	private String emailnotification;
	private String assignstatus;
	private String wfid;
	private String nodeid;
	private String assign_01;
	private String assign_02;
	private String assign_03;
	private String assign_04;
	private String assign_05;
	private String processrev;
	private String processname;
	private String roleid;
	private String origperson;
	private String wfassignmentid;
	private String templateid;
	private String condition;
	private String conditionclass;
	private String ownertable;
	private String ownerid;
	private String langcode;
	private String escrole;
	private String calendarbased;
	private String hasld;
	private String groupnum;
	private String acceptexpr;
	private String nonacceptmsg;
	private String relationship;
	private String separategroups;
	private String udappname;

	public String getAssignid() {
		return assignid;
	}

	public void setAssignid(String assignid) {
		this.assignid = assignid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getAssigncode() {
		return assigncode;
	}

	public void setAssigncode(String assigncode) {
		this.assigncode = assigncode;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getTimelimit() {
		return timelimit;
	}

	public void setTimelimit(String timelimit) {
		this.timelimit = timelimit;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getDuedate() {
		return duedate;
	}

	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}

	public String getEmailnotification() {
		return emailnotification;
	}

	public void setEmailnotification(String emailnotification) {
		this.emailnotification = emailnotification;
	}

	public String getAssignstatus() {
		return assignstatus;
	}

	public void setAssignstatus(String assignstatus) {
		this.assignstatus = assignstatus;
	}

	public String getWfid() {
		return wfid;
	}

	public void setWfid(String wfid) {
		this.wfid = wfid;
	}

	public String getNodeid() {
		return nodeid;
	}

	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}

	public String getAssign_01() {
		return assign_01;
	}

	public void setAssign_01(String assign_01) {
		this.assign_01 = assign_01;
	}

	public String getAssign_02() {
		return assign_02;
	}

	public void setAssign_02(String assign_02) {
		this.assign_02 = assign_02;
	}

	public String getAssign_03() {
		return assign_03;
	}

	public void setAssign_03(String assign_03) {
		this.assign_03 = assign_03;
	}

	public String getAssign_04() {
		return assign_04;
	}

	public void setAssign_04(String assign_04) {
		this.assign_04 = assign_04;
	}

	public String getAssign_05() {
		return assign_05;
	}

	public void setAssign_05(String assign_05) {
		this.assign_05 = assign_05;
	}

	public String getProcessrev() {
		return processrev;
	}

	public void setProcessrev(String processrev) {
		this.processrev = processrev;
	}

	public String getProcessname() {
		return processname;
	}

	public void setProcessname(String processname) {
		this.processname = processname;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getOrigperson() {
		return origperson;
	}

	public void setOrigperson(String origperson) {
		this.origperson = origperson;
	}

	public String getWfassignmentid() {
		return wfassignmentid;
	}

	public void setWfassignmentid(String wfassignmentid) {
		this.wfassignmentid = wfassignmentid;
	}

	public String getTemplateid() {
		return templateid;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getConditionclass() {
		return conditionclass;
	}

	public void setConditionclass(String conditionclass) {
		this.conditionclass = conditionclass;
	}

	public String getOwnertable() {
		return ownertable;
	}

	public void setOwnertable(String ownertable) {
		this.ownertable = ownertable;
	}

	public String getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}

	public String getLangcode() {
		return langcode;
	}

	public void setLangcode(String langcode) {
		this.langcode = langcode;
	}

	public String getEscrole() {
		return escrole;
	}

	public void setEscrole(String escrole) {
		this.escrole = escrole;
	}

	public String getCalendarbased() {
		return calendarbased;
	}

	public void setCalendarbased(String calendarbased) {
		this.calendarbased = calendarbased;
	}

	public String getHasld() {
		return hasld;
	}

	public void setHasld(String hasld) {
		this.hasld = hasld;
	}

	public String getGroupnum() {
		return groupnum;
	}

	public void setGroupnum(String groupnum) {
		this.groupnum = groupnum;
	}

	public String getAcceptexpr() {
		return acceptexpr;
	}

	public void setAcceptexpr(String acceptexpr) {
		this.acceptexpr = acceptexpr;
	}

	public String getNonacceptmsg() {
		return nonacceptmsg;
	}

	public void setNonacceptmsg(String nonacceptmsg) {
		this.nonacceptmsg = nonacceptmsg;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getSeparategroups() {
		return separategroups;
	}

	public void setSeparategroups(String separategroups) {
		this.separategroups = separategroups;
	}

	class Fields {
		public static final String ASSIGNID_1 = "ASSIGNID";
		public static final String DESCRIPTION_2 = "DESCRIPTION";
		public static final String PRIORITY_3 = "PRIORITY";
		public static final String ASSIGNCODE_4 = "ASSIGNCODE";
		public static final String APP_5 = "APP";
		public static final String TIMELIMIT_6 = "TIMELIMIT";
		public static final String STARTDATE_7 = "STARTDATE";
		public static final String DUEDATE_8 = "DUEDATE";
		public static final String EMAILNOTIFICATION_9 = "EMAILNOTIFICATION";
		public static final String ASSIGNSTATUS_10 = "ASSIGNSTATUS";
		public static final String WFID_11 = "WFID";
		public static final String NODEID_12 = "NODEID";
		public static final String ASSIGN_01_13 = "ASSIGN_01";
		public static final String ASSIGN_02_14 = "ASSIGN_02";
		public static final String ASSIGN_03_15 = "ASSIGN_03";
		public static final String ASSIGN_04_16 = "ASSIGN_04";
		public static final String ASSIGN_05_17 = "ASSIGN_05";
		public static final String PROCESSREV_18 = "PROCESSREV";
		public static final String PROCESSNAME_19 = "PROCESSNAME";
		public static final String ROLEID_20 = "ROLEID";
		public static final String ORIGPERSON_21 = "ORIGPERSON";
		public static final String WFASSIGNMENTID_22 = "WFASSIGNMENTID";
		public static final String TEMPLATEID_23 = "TEMPLATEID";
		public static final String CONDITION_24 = "CONDITION";
		public static final String CONDITIONCLASS_25 = "CONDITIONCLASS";
		public static final String OWNERTABLE_26 = "OWNERTABLE";
		public static final String OWNERID_27 = "OWNERID";
		public static final String LANGCODE_28 = "LANGCODE";
		public static final String ESCROLE_29 = "ESCROLE";
		public static final String CALENDARBASED_30 = "CALENDARBASED";
		public static final String HASLD_31 = "HASLD";
		public static final String GROUPNUM_32 = "GROUPNUM";
		public static final String ACCEPTEXPR_33 = "ACCEPTEXPR";
		public static final String NONACCEPTMSG_34 = "NONACCEPTMSG";
		public static final String RELATIONSHIP_35 = "RELATIONSHIP";
		public static final String SEPARATEGROUPS_36 = "SEPARATEGROUPS";
	}

	public ArrayList<Inbox> parse(SoapObject result) {
		SoapObject inboxData = result;
		InboxList inboxList = new InboxList();
		ArrayList<Inbox> inboxs = inboxList.getInboxs();
		Inbox inbox = null;
		int objCount = inboxData.getPropertyCount();
		for (int i = 0; i < objCount; i++) {
			SoapObject subObj = (SoapObject) result.getProperty(i);
			// 组装webservice返回数据
			inbox = new Inbox();
			inbox.setAssignid(SoapHelper.getSoapObjectString(subObj,
					Fields.ASSIGNID_1));
			inbox.setDescription(SoapHelper.getSoapObjectString(subObj,
					Fields.DESCRIPTION_2));
			inbox.setPriority(SoapHelper.getSoapObjectString(subObj,
					Fields.PRIORITY_3));
			inbox.setAssigncode(SoapHelper.getSoapObjectString(subObj,
					Fields.ASSIGNCODE_4));
			inbox.setApp(SoapHelper.getSoapObjectString(subObj, Fields.APP_5));
			inbox.setTimelimit(SoapHelper.getSoapObjectString(subObj,
					Fields.TIMELIMIT_6));
			inbox.setStartdate(SoapHelper.getSoapObjectString(subObj,
					Fields.STARTDATE_7));
			inbox.setDuedate(SoapHelper.getSoapObjectString(subObj,
					Fields.DUEDATE_8));
			inbox.setEmailnotification(SoapHelper.getSoapObjectString(subObj,
					Fields.EMAILNOTIFICATION_9));
			inbox.setAssignstatus(SoapHelper.getSoapObjectString(subObj,
					Fields.ASSIGNSTATUS_10));
			inbox.setWfid(SoapHelper
					.getSoapObjectString(subObj, Fields.WFID_11));
			inbox.setNodeid(SoapHelper.getSoapObjectString(subObj,
					Fields.NODEID_12));
			inbox.setAssign_01(SoapHelper.getSoapObjectString(subObj,
					Fields.ASSIGN_01_13));
			inbox.setAssign_02(SoapHelper.getSoapObjectString(subObj,
					Fields.ASSIGN_02_14));
			inbox.setAssign_03(SoapHelper.getSoapObjectString(subObj,
					Fields.ASSIGN_03_15));
			inbox.setAssign_04(SoapHelper.getSoapObjectString(subObj,
					Fields.ASSIGN_04_16));
			inbox.setAssign_05(SoapHelper.getSoapObjectString(subObj,
					Fields.ASSIGN_05_17));
			inbox.setProcessrev(SoapHelper.getSoapObjectString(subObj,
					Fields.PROCESSREV_18));
			inbox.setProcessname(SoapHelper.getSoapObjectString(subObj,
					Fields.PROCESSNAME_19));
			inbox.setRoleid(SoapHelper.getSoapObjectString(subObj,
					Fields.ROLEID_20));
			inbox.setOrigperson(SoapHelper.getSoapObjectString(subObj,
					Fields.ORIGPERSON_21));
			inbox.setWfassignmentid(SoapHelper.getSoapObjectString(subObj,
					Fields.WFASSIGNMENTID_22));
			inbox.setTemplateid(SoapHelper.getSoapObjectString(subObj,
					Fields.TEMPLATEID_23));
			inbox.setCondition(SoapHelper.getSoapObjectString(subObj,
					Fields.CONDITION_24));
			inbox.setConditionclass(SoapHelper.getSoapObjectString(subObj,
					Fields.CONDITIONCLASS_25));
			inbox.setOwnertable(SoapHelper.getSoapObjectString(subObj,
					Fields.OWNERTABLE_26));
			inbox.setOwnerid(SoapHelper.getSoapObjectString(subObj,
					Fields.OWNERID_27));
			inbox.setLangcode(SoapHelper.getSoapObjectString(subObj,
					Fields.LANGCODE_28));
			inbox.setEscrole(SoapHelper.getSoapObjectString(subObj,
					Fields.ESCROLE_29));
			inbox.setCalendarbased(SoapHelper.getSoapObjectString(subObj,
					Fields.CALENDARBASED_30));
			inbox.setHasld(SoapHelper.getSoapObjectString(subObj,
					Fields.HASLD_31));
//			inbox.setGroupnum(SoapHelper.getSoapObjectString(subObj,
//					"UDAPPNAME"));
//			inbox.setAcceptexpr(SoapHelper.getSoapObjectString(subObj,
//					Fields.ACCEPTEXPR_33));
//			inbox.setNonacceptmsg(SoapHelper.getSoapObjectString(subObj,
//					Fields.NONACCEPTMSG_34));
//			inbox.setRelationship(SoapHelper.getSoapObjectString(subObj,ss
//					Fields.RELATIONSHIP_35));
//			inbox.setSeparategroups(SoapHelper.getSoapObjectString(subObj,
//					Fields.SEPARATEGROUPS_36));
			inboxs.add(inbox);
		}
		return inboxs;
	}

	/**
	 * @return the udappname
	 */
	public String getUdappname() {
		return udappname;
	}

	/**
	 * @param udappname the udappname to set
	 */
	public void setUdappname(String udappname) {
		this.udappname = udappname;
	}
}
