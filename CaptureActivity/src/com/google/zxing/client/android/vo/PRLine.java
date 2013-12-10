package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import com.google.zxing.client.android.utils.SoapHelper;

/**
 * pr��ʵ�����
 * 
 * @author Administrator
 * 
 */
public class PRLine extends Base implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8066234132052607327L;
	// �ɹ�����
	private String prnum;
	// ����
	private String orderqty;
	// �ɹ���λ
	private String orderunit;
	// �ɹ�����
	private String unitcost;
	// �������ʱ��
	private String reqdeliverydate;
	// ����ʱ��
	private String enterdate;
	// ������
	private String enterby;
	// ����
	private String description;
	// �ܼ�
	private String linecost;
	// �ɹ����뵥�к�
	private String prlinenum;
	// Ψһ��ʶ
	private String prlineid;
	// ������
	private String linetype;
	// ��ͬ���
	private String contractnum;
	// ��ͬ�к�
	private String contractlinenum;
	// ����
	private String classstructureid;
	// Ψһ��ʶ
	private String rowstamp;
	// ִ����
	private String udassigner;
	// ��;
	private String udusage;
	// ����ͺ�
	private String udmodel;
	// ������
	private String udaccepter;
	// ��������
	private String receivedqty;
	// �����ܼ�
	private String receivedtotalcost;
	// ���յ���
	private String receivedunitcost;
	// �������
	private String receiptscomplete;
	// �ɹ�������״̬
	private String status;

	public String getPrnum() {
		return prnum;
	}

	public void setPrnum(String prnum) {
		this.prnum = prnum;
	}

	public String getOrderqty() {
		return orderqty;
	}

	public void setOrderqty(String orderqty) {
		this.orderqty = orderqty;
	}

	public String getOrderunit() {
		return orderunit;
	}

	public void setOrderunit(String orderunit) {
		this.orderunit = orderunit;
	}

	public String getUnitcost() {
		return unitcost;
	}

	public void setUnitcost(String unitcost) {
		this.unitcost = unitcost;
	}

	public String getReqdeliverydate() {
		return reqdeliverydate;
	}

	public void setReqdeliverydate(String reqdeliverydate) {
		this.reqdeliverydate = reqdeliverydate;
	}

	public String getEnterdate() {
		return enterdate;
	}

	public void setEnterdate(String enterdate) {
		this.enterdate = enterdate;
	}

	public String getEnterby() {
		return enterby;
	}

	public void setEnterby(String enterby) {
		this.enterby = enterby;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLinecost() {
		return linecost;
	}

	public void setLinecost(String linecost) {
		this.linecost = linecost;
	}

	public String getPrlinenum() {
		return prlinenum;
	}

	public void setPrlinenum(String prlinenum) {
		this.prlinenum = prlinenum;
	}

	public String getPrlineid() {
		return prlineid;
	}

	public void setPrlineid(String prlineid) {
		this.prlineid = prlineid;
	}

	public String getLinetype() {
		return linetype;
	}

	public void setLinetype(String linetype) {
		this.linetype = linetype;
	}

	public String getContractnum() {
		return contractnum;
	}

	public void setContractnum(String contractnum) {
		this.contractnum = contractnum;
	}

	public String getContractlinenum() {
		return contractlinenum;
	}

	public void setContractlinenum(String contractlinenum) {
		this.contractlinenum = contractlinenum;
	}

	public String getClassstructureid() {
		return classstructureid;
	}

	public void setClassstructureid(String classstructureid) {
		this.classstructureid = classstructureid;
	}

	public String getRowstamp() {
		return rowstamp;
	}

	public void setRowstamp(String rowstamp) {
		this.rowstamp = rowstamp;
	}

	public String getUdassigner() {
		return udassigner;
	}

	public void setUdassigner(String udassigner) {
		this.udassigner = udassigner;
	}

	public String getUdusage() {
		return udusage;
	}

	public void setUdusage(String udusage) {
		this.udusage = udusage;
	}

	public String getUdmodel() {
		return udmodel;
	}

	public void setUdmodel(String udmodel) {
		this.udmodel = udmodel;
	}

	public String getUdaccepter() {
		return udaccepter;
	}

	public void setUdaccepter(String udaccepter) {
		this.udaccepter = udaccepter;
	}

	public String getReceivedqty() {
		return receivedqty;
	}

	public void setReceivedqty(String receivedqty) {
		this.receivedqty = receivedqty;
	}

	public String getReceivedtotalcost() {
		return receivedtotalcost;
	}

	public void setReceivedtotalcost(String receivedtotalcost) {
		this.receivedtotalcost = receivedtotalcost;
	}

	public String getReceivedunitcost() {
		return receivedunitcost;
	}

	public void setReceivedunitcost(String receivedunitcost) {
		this.receivedunitcost = receivedunitcost;
	}

	public String getReceiptscomplete() {
		return receiptscomplete;
	}

	public void setReceiptscomplete(String receiptscomplete) {
		this.receiptscomplete = receiptscomplete;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * ����prline��������
	 * 
	 * @param result
	 * @return
	 */
	public ArrayList<PRLine> parse(SoapObject result) {
		SoapObject accountData = result;
		PRLine prLine = null;
		PRLineList lineList = new PRLineList();
		ArrayList<PRLine> prlines = lineList.getPrLines();
		int resultCount = accountData.getPropertyCount();
		for (int i = 0; i < resultCount; i++) {
			prLine = new PRLine();
			SoapObject subObj = (SoapObject) result.getProperty(i);
			prLine.setDescription(SoapHelper.getSoapObjectString(subObj,
					Fileds.DESCRIPTION));
			prLine.setUdmodel(SoapHelper.getSoapObjectString(subObj,
					Fileds.UDMODEL));
			prLine.setClassstructureid(SoapHelper.getSoapObjectString(subObj,
					Fileds.CLASSSTRUCTUREID));
			prLine.setOrderqty(SoapHelper.getSoapObjectString(subObj,
					Fileds.ORDERQTY));
			prLine.setOrderunit(SoapHelper.getSoapObjectString(subObj,
					Fileds.ORDERUNIT));
			prLine.setUnitcost(SoapHelper.getSoapObjectString(subObj,
					Fileds.UNITCOST));
			prLine.setUdusage(SoapHelper.getSoapObjectString(subObj,
					Fileds.UDUSAGE));
			prLine.setPrlineid(SoapHelper.getSoapObjectString(subObj,
					Fileds.PRLINEID));
			prlines.add(prLine);

		}
		return prlines;
	}

	class Fileds {
		// �ɹ�����
		public static final String PRNUM = "PRNUM";
		// ����
		public static final String ORDERQTY = "ORDERQTY";
		// �ɹ���λ
		public static final String ORDERUNIT = "ORDERUNIT";
		// �ɹ�����
		public static final String UNITCOST = "UNITCOST";
		// �������ʱ��
		public static final String REQDELIVERYDATE = "REQDELIVERYDATE";
		// ����ʱ��
		public static final String ENTERDATE = "ENTERDATE";
		// ������
		public static final String ENTERBY = "ENTERBY";
		// ����
		public static final String DESCRIPTION = "DESCRIPTION";
		// �ܼ�
		public static final String LINECOST = "LINECOST";
		// �ɹ����뵥�к�
		public static final String PRLINENUM = "PRLINENUM";
		// Ψһ��ʶ
		public static final String PRLINEID = "PRLINEID";
		// ������
		public static final String LINETYPE = "LINETYPE";
		// ��ͬ���
		public static final String CONTRACTNUM = "CONTRACTNUM";
		// ��ͬ�к�
		public static final String CONTRACTLINENUM = "CONTRACTLINENUM";
		// ����
		public static final String CLASSSTRUCTUREID = "CLASSSTRUCTUREID";
		// Ψһ��ʶ
		public static final String ROWSTAMP = "ROWSTAMP";
		// ִ����
		public static final String UDASSIGNER = "UDASSIGNER";
		// ��;
		public static final String UDUSAGE = "UDUSAGE";
		// ����ͺ�
		public static final String UDMODEL = "UDMODEL";
		// ������
		public static final String UDACCEPTER = "UDACCEPTER";
		// ��������
		public static final String RECEIVEDQTY = "RECEIVEDQTY";
		// �����ܼ�
		public static final String RECEIVEDTOTALCOST = "RECEIVEDTOTALCOST";
		// ���յ���
		public static final String RECEIVEDUNITCOST = "RECEIVEDUNITCOST";
		// �������
		public static final String RECEIPTSCOMPLETE = "RECEIPTSCOMPLETE";
		// �ɹ�������״̬
		public static final String STATUS = "STATUS";

	}

}
