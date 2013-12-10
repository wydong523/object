package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import com.google.zxing.client.android.utils.SoapHelper;

/**
 * pr行实体对象
 * 
 * @author Administrator
 * 
 */
public class PRLine extends Base implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8066234132052607327L;
	// 采购申请
	private String prnum;
	// 数量
	private String orderqty;
	// 采购单位
	private String orderunit;
	// 采购单价
	private String unitcost;
	// 建议完成时间
	private String reqdeliverydate;
	// 创建时间
	private String enterdate;
	// 创建人
	private String enterby;
	// 描述
	private String description;
	// 总价
	private String linecost;
	// 采购申请单行号
	private String prlinenum;
	// 唯一标识
	private String prlineid;
	// 行类型
	private String linetype;
	// 合同编号
	private String contractnum;
	// 合同行号
	private String contractlinenum;
	// 分类
	private String classstructureid;
	// 唯一标识
	private String rowstamp;
	// 执行人
	private String udassigner;
	// 用途
	private String udusage;
	// 规格型号
	private String udmodel;
	// 验收人
	private String udaccepter;
	// 验收数量
	private String receivedqty;
	// 验收总价
	private String receivedtotalcost;
	// 验收单价
	private String receivedunitcost;
	// 验收完成
	private String receiptscomplete;
	// 采购申请行状态
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
	 * 解析prline返回数据
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
		// 采购申请
		public static final String PRNUM = "PRNUM";
		// 数量
		public static final String ORDERQTY = "ORDERQTY";
		// 采购单位
		public static final String ORDERUNIT = "ORDERUNIT";
		// 采购单价
		public static final String UNITCOST = "UNITCOST";
		// 建议完成时间
		public static final String REQDELIVERYDATE = "REQDELIVERYDATE";
		// 创建时间
		public static final String ENTERDATE = "ENTERDATE";
		// 创建人
		public static final String ENTERBY = "ENTERBY";
		// 描述
		public static final String DESCRIPTION = "DESCRIPTION";
		// 总价
		public static final String LINECOST = "LINECOST";
		// 采购申请单行号
		public static final String PRLINENUM = "PRLINENUM";
		// 唯一标识
		public static final String PRLINEID = "PRLINEID";
		// 行类型
		public static final String LINETYPE = "LINETYPE";
		// 合同编号
		public static final String CONTRACTNUM = "CONTRACTNUM";
		// 合同行号
		public static final String CONTRACTLINENUM = "CONTRACTLINENUM";
		// 分类
		public static final String CLASSSTRUCTUREID = "CLASSSTRUCTUREID";
		// 唯一标识
		public static final String ROWSTAMP = "ROWSTAMP";
		// 执行人
		public static final String UDASSIGNER = "UDASSIGNER";
		// 用途
		public static final String UDUSAGE = "UDUSAGE";
		// 规格型号
		public static final String UDMODEL = "UDMODEL";
		// 验收人
		public static final String UDACCEPTER = "UDACCEPTER";
		// 验收数量
		public static final String RECEIVEDQTY = "RECEIVEDQTY";
		// 验收总价
		public static final String RECEIVEDTOTALCOST = "RECEIVEDTOTALCOST";
		// 验收单价
		public static final String RECEIVEDUNITCOST = "RECEIVEDUNITCOST";
		// 验收完成
		public static final String RECEIPTSCOMPLETE = "RECEIPTSCOMPLETE";
		// 采购申请行状态
		public static final String STATUS = "STATUS";

	}

}
