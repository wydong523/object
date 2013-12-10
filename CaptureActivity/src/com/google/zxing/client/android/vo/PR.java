package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import com.google.zxing.client.android.utils.SoapHelper;

/**
 * PRʵ����
 * 
 * @author ���嶰
 * 
 */
public class PR extends Base implements Serializable {

	// �ɹ�������
	private String prnum;
	// ��������
	private String requireddate;
	// ������
	private String requestedby;
	// ��Ӧ��
	private String vendor;
	// ��ϵ��
	private String contact;
	// ����
	private String description;
	// ״̬
	private String status;
	// ״̬����
	private String statusdate;
	// �޸�ʱ��
	private String changedate;
	// �޸���
	private String changeby;
	// ���
	private String totalcost;
	// ��ʷ��ʶ
	private String historyflag;
	// Ψһ��ʶ
	private String prid;
	// Ψһ��ʶ
	private String rowstamp;
	// ����
	private String cutype;
	// ��Ŀ
	private String projectid;
	// ����
	private String wonum;
	// �������ʱ��
	private String uddate1;
	// Ҫ�����ʱ��
	private String uddate2;
	// �������ʱ��
	private String uddate3;
	// ����ԭ��
	private String udremark1;
	// ��ұ���
	private String udcurrencycode;
	// ��ҽ��
	private String pr6;
	// ����Ŀ����
	private String udowner;
	// ���ķֹ��쵼
	private String rdchead;
	// ��ͬ������
	private String udpcowner;
	// �绰
	private String phonenum;

	// �Ƿ�¼��
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

	// ���񵽴�ʱ��
	private String udacceptdate;

	/**
	 * ����webservice���ص�pr�б�����
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
			// ��װwebservice��������
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
