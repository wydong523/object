package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import com.google.zxing.client.android.utils.SoapHelper;

/**
 * PR实体类
 * 
 * @author 汪渝栋
 * 
 */
public class PR extends Base implements Serializable {

	// 采购申请编号
	private String prnum;
	// 申请日期
	private String requireddate;
	// 申请人
	private String requestedby;
	// 供应商
	private String vendor;
	// 联系人
	private String contact;
	// 描述
	private String description;
	// 状态
	private String status;
	// 状态日期
	private String statusdate;
	// 修改时间
	private String changedate;
	// 修改人
	private String changeby;
	// 金额
	private String totalcost;
	// 历史标识
	private String historyflag;
	// 唯一标识
	private String prid;
	// 唯一标识
	private String rowstamp;
	// 类型
	private String cutype;
	// 项目
	private String projectid;
	// 任务单
	private String wonum;
	// 建议完成时间
	private String uddate1;
	// 要求完成时间
	private String uddate2;
	// 结算完成时间
	private String uddate3;
	// 申请原因
	private String udremark1;
	// 外币币种
	private String udcurrencycode;
	// 外币金额
	private String pr6;
	// 子项目经理
	private String udowner;
	// 中心分管领导
	private String rdchead;
	// 合同负责人
	private String udpcowner;
	// 电话
	private String phonenum;

	// 是否补录？
	private String udadditional;

	public String getPrnum() {
		return prnum;
	}

	public void setPrnum(String prnum) {
		this.prnum = prnum;
	}

	public String getRequireddate() {
		return requireddate;
	}

	public void setRequireddate(String requireddate) {
		this.requireddate = requireddate;
	}

	public String getRequestedby() {
		return requestedby;
	}

	public void setRequestedby(String requestedby) {
		this.requestedby = requestedby;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusdate() {
		return statusdate;
	}

	public void setStatusdate(String statusdate) {
		this.statusdate = statusdate;
	}

	public String getChangedate() {
		return changedate;
	}

	public void setChangedate(String changedate) {
		this.changedate = changedate;
	}

	public String getChangeby() {
		return changeby;
	}

	public void setChangeby(String changeby) {
		this.changeby = changeby;
	}

	public String getTotalcost() {
		return totalcost;
	}

	public void setTotalcost(String totalcost) {
		this.totalcost = totalcost;
	}

	public String getHistoryflag() {
		return historyflag;
	}

	public void setHistoryflag(String historyflag) {
		this.historyflag = historyflag;
	}

	public String getPrid() {
		return prid;
	}

	public void setPrid(String prid) {
		this.prid = prid;
	}

	public String getRowstamp() {
		return rowstamp;
	}

	public void setRowstamp(String rowstamp) {
		this.rowstamp = rowstamp;
	}

	public String getCutype() {
		return cutype;
	}

	public void setCutype(String cutype) {
		this.cutype = cutype;
	}

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

	public String getWonum() {
		return wonum;
	}

	public void setWonum(String wonum) {
		this.wonum = wonum;
	}

	public String getUddate1() {
		return uddate1;
	}

	public void setUddate1(String uddate1) {
		this.uddate1 = uddate1;
	}

	public String getUddate2() {
		return uddate2;
	}

	public void setUddate2(String uddate2) {
		this.uddate2 = uddate2;
	}

	public String getUddate3() {
		return uddate3;
	}

	public void setUddate3(String uddate3) {
		this.uddate3 = uddate3;
	}

	public String getUdremark1() {
		return udremark1;
	}

	public void setUdremark1(String udremark1) {
		this.udremark1 = udremark1;
	}

	public String getUdcurrencycode() {
		return udcurrencycode;
	}

	public void setUdcurrencycode(String udcurrencycode) {
		this.udcurrencycode = udcurrencycode;
	}

	public String getPr6() {
		return pr6;
	}

	public void setPr6(String pr6) {
		this.pr6 = pr6;
	}

	public String getUdowner() {
		return udowner;
	}

	public void setUdowner(String udowner) {
		this.udowner = udowner;
	}

	public String getRdchead() {
		return rdchead;
	}

	public void setRdchead(String rdchead) {
		this.rdchead = rdchead;
	}

	public String getUdpcowner() {
		return udpcowner;
	}

	public void setUdpcowner(String udpcowner) {
		this.udpcowner = udpcowner;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getUdacceptdate() {
		return udacceptdate;
	}

	public void setUdacceptdate(String udacceptdate) {
		this.udacceptdate = udacceptdate;
	}

	// 任务到达时间
	private String udacceptdate;

	/**
	 * 解析webservice返回的pr列表数据
	 * 
	 * @param result
	 * @return
	 */
	public PRList parse(SoapObject result) {
		PRList prList = new PRList();
		ArrayList<PR> prs = prList.getPrs();
		PR pr = null;
		int objCount = result.getPropertyCount();
		for (int i = 0; i < objCount; i++) {
			SoapObject subObj = (SoapObject) result.getProperty(i);
			// 组装webservice返回数据
			pr = new PR();
			pr.setPrnum(SoapHelper.getSoapObjectString(subObj, Fields.PRNUM));
			pr.setRequireddate(SoapHelper.getSoapObjectString(subObj,
					Fields.REQUIREDDATE));
			pr.setRequestedby(SoapHelper.getSoapObjectString(subObj,
					Fields.REQUESTEDBY));
			pr.setVendor(SoapHelper.getSoapObjectString(subObj, Fields.VENDOR));
			pr.setContact(SoapHelper
					.getSoapObjectString(subObj, Fields.CONTACT));
			pr.setDescription(SoapHelper.getSoapObjectString(subObj,
					Fields.DESCRIPTION));
			pr.setStatus(SoapHelper.getSoapObjectString(subObj, Fields.STATUS));
			pr.setStatusdate(SoapHelper.getSoapObjectString(subObj,
					Fields.STATUSDATE));
			pr.setChangedate(SoapHelper.getSoapObjectString(subObj,
					Fields.CHANGEDATE));
			pr.setChangeby(SoapHelper.getSoapObjectString(subObj,
					Fields.CHANGEBY));
			pr.setTotalcost(SoapHelper.getSoapObjectString(subObj,
					Fields.TOTALCOST));
			pr.setHistoryflag(SoapHelper.getSoapObjectString(subObj,
					Fields.HISTORYFLAG));
			pr.setPrid(SoapHelper.getSoapObjectString(subObj, Fields.PRID));
			// pr.setRowstamp(SoapHelper.getSoapObjectString(subObj,
			// Fields.ROWSTAMP));
			pr.setCutype(SoapHelper.getSoapObjectString(subObj, Fields.CUTYPE));
			pr.setProjectid(SoapHelper.getSoapObjectString(subObj,
					Fields.PROJECTID));
			pr.setWonum(SoapHelper.getSoapObjectString(subObj, Fields.WONUM));
			pr.setUddate1(SoapHelper
					.getSoapObjectString(subObj, Fields.UDDATE1));
			pr.setUddate2(SoapHelper
					.getSoapObjectString(subObj, Fields.UDDATE2));
			pr.setUddate3(SoapHelper
					.getSoapObjectString(subObj, Fields.UDDATE3));
			pr.setUdremark1(SoapHelper.getSoapObjectString(subObj,
					Fields.UDREMARK1));
			pr.setUdcurrencycode(SoapHelper.getSoapObjectString(subObj,
					Fields.UDCURRENCYCODE));
			pr.setPr6(SoapHelper.getSoapObjectString(subObj, Fields.PR6));
			pr.setUdowner(SoapHelper
					.getSoapObjectString(subObj, Fields.UDOWNER));
			pr.setRdchead(SoapHelper
					.getSoapObjectString(subObj, Fields.RDCHEAD));
			pr.setUdpcowner(SoapHelper.getSoapObjectString(subObj,
					Fields.UDPCOWNER));
			pr.setPhonenum(SoapHelper.getSoapObjectString(subObj,
					Fields.PHONENUM));
			pr.setUdacceptdate(SoapHelper.getSoapObjectString(subObj,
					Fields.UDACCEPTDATE));
			pr.setUdacceptdate(SoapHelper.getSoapObjectString(subObj,
					Fields.UDADDITIONAL));
			prs.add(pr);
		}
		return prList;
	}

	/**
	 * @return the udadditional
	 */
	public String getUdadditional() {
		return udadditional;
	}

	/**
	 * @param udadditional
	 *            the udadditional to set
	 */
	public void setUdadditional(String udadditional) {
		this.udadditional = udadditional;
	}

}

class Fields {
	public static final String PRNUM = "PRNUM";
	public static final String REQUIREDDATE = "REQUIREDDATE";
	public static final String REQUESTEDBY = "REQUESTEDBY";
	public static final String VENDOR = "VENDOR";
	public static final String CONTACT = "CONTACT";
	public static final String DESCRIPTION = "DESCRIPTION";
	public static final String STATUS = "STATUS";
	public static final String STATUSDATE = "STATUSDATE";
	public static final String CHANGEDATE = "CHANGEDATE";
	public static final String CHANGEBY = "CHANGEBY";
	public static final String TOTALCOST = "TOTALCOST";
	public static final String HISTORYFLAG = "HISTORYFLAG";
	public static final String PRID = "PRID";
	public static final String ROWSTAMP = "ROWSTAMP";
	public static final String CUTYPE = "CUTYPE";
	public static final String PROJECTID = "PROJECTID";
	public static final String WONUM = "WONUM";
	public static final String UDDATE1 = "UDDATE1";
	public static final String UDDATE2 = "UDDATE2";
	public static final String UDDATE3 = "UDDATE3";
	public static final String UDREMARK1 = "UDREMARK1";
	public static final String UDCURRENCYCODE = "UDCURRENCYCODE";
	public static final String PR6 = "PR6";
	public static final String UDOWNER = "UDOWNER";
	public static final String RDCHEAD = "RDCHEAD";
	public static final String UDPCOWNER = "UDPCOWNER";
	public static final String PHONENUM = "PHONENUM";
	public static final String UDACCEPTDATE = "UDACCEPTDATE";
	public static final String UDADDITIONAL = "UDADDITIONAL";
}
