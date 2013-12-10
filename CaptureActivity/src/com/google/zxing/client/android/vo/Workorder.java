package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;

import org.ksoap2.serialization.SoapObject;

import com.google.zxing.client.android.utils.SoapHelper;

import android.provider.UserDictionary.Words;

/**
 * 工单实体
 * 
 * @author 汪渝栋
 * 
 */
public class Workorder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6853894358519355940L;

	private TreeSet<String> auths;

	private String workpackmtlstatus; // 工作包物料状态
	private String ownergroup; // 所有者组
	private String downtime; // 停工时间
	private String woeq9; // woeq9
	private String assignedownergroup; // 分配的所有者组
	private String calcorgid; // organization
	private String historyflag; // 历史记录
	private String ignoresrmavail; // 针对工单状态忽略库房可用性
	private String ignorediavail; // 针对工单状态忽略直接发放可用性
	private String ownersysid; // 所有者系统标识
	private String origrecordclass; // 原始记录的类
	private String estatapproutlabcost; // 外部人工的估算小时数
	private String verification; // 验证
	private String flowcontrolled; // 在流程控制下
	private String crewid; // 班组
	private String persongroup; // 工作组
	private String estintlabcost; // 内部人工的估算成本
	private String woeq2; // woeq2
	private String safetyplanid; // 安全计划
	private String reasonforchange; // 变更的原因
	private String description; // 描述
	private String pmnum; // pm
	private String esttoolcost; // 估算的工具成本
	private String woci; // ci 工单
	private String commodity; // 服务
	private String woeq1; // woeq1
	private String schedfinish; // 计划完成时间
	private String pmnextduedate; // pm 下一截止日期
	private String actlabhrs; // 实际工时
	private String wol2; // wol2
	private String wol3; // wol3
	private String estdur; // 持续时间
	private String estlabhrs; // 估算的工时
	private String estmatcost; // 估算的物料成本
	private String woeq5; // woeq5
	private String estoutlabcost; // 外部人工的估算成本
	private String actintlabcost; // 内部人工的实际成本
	private String actstart; // 实际开始时间
	private String assetcust; // 管理人
	private String acttotalcost; // 实际成本总计
	private String hasld; // 具有详细描述
	private String woeq4; // woeq4
	private String wopriority; // 优先级
	private String outlabcost; // 外部人工成本
	private String newchildclass; // 新子类
	private String pluscjprevnum; // 作业计划修订版号
	private String pluscloop; // 循环校准
	private String schedstart; // 计划开始时间
	private String status; // 状态
	private String description_longdescription; // 详细信息
	private String route; // 检修路线
	private String firstapprstatus; // 首个批准状态
	private String pluscfrequnit; // 频率单位
	private String startfeaturelabel; // 参照点
	private String endfeaturelabel; // 参照点
	private String parent; // 父工单
	private String woloc; // 工单位置
	private String owner; // 所有者
	private String phone; // 电话
	private String actoutlabcost; // 外部人工的实际小时数
	private String pluscismobile; // 是否移动
	private String woeq11; // woeq11
	private String predessorwos; // 先行
	private String reportedbyname; // 名称
	private String onbehalfofid; // 代表
	private String statusiface; // 状态已变更
	private String hasparent; // 有父级吗？
	private String risk; // 风险评估
	private String assetwarrantynotice; // 资产担保通知
	private String backoutplan; // 撤销计划
	private String backoutplan_longdescription; // “撤销计划”的详细描述
	private String inspector; // 检验员
	private String measurementvalue; // 测量结果
	private String justifypriority; // 优先级理由
	private String wolo5; // wolo5
	private String wolo6; // wolo6
	private String wolo7; // wolo7
	private String wolo8; // wolo8
	private String genforporevision; // po 修订
	private String storeroommtlstatus; // 库房物料状态
	private String filterdate; // 日期
	private String pluscfrequency; // 频率字段
	private String worts3; // worts3
	private String worts4; // worts4
	private String sourcesysid; // 源系统标识
	private String flowaction; // 流程操作
	private String wojo3; // wojo3
	private String chargestore; // 记入库存
	private String reportdate; // 报告日期
	private String istask; // 是任务
	private String woeq12; // woeq12
	private String woasset; // 资产的工单
	private String changebyparent; // 按父级变更
	private String routestopid; // 检修路线站点
	private String warrantyexpdate; // 保修期
	private String contract; // 合同
	private String remarkenterdate; // 备注日期
	private String remarkdesc; // 备注
	private String fr2code; // 问题代码
	private String pl1; // 问题代码
	private String remdur; // 剩余时间
	private String worts5; // worts5
	private String location; // 位置
	private String justifypriority_longdescription; // 说明优先级理由的详细描述
	private String estatapprtotalcost; // 估算的物料成本
	private String targcompdate; // 目标完成时间
	private String fctaskid; // 任务
	private String actservcost; // 实际服务成本
	private String disabled; // 禁用
	private String estatapprlabhrs; // 批准时估算工时数
	private String estatapprlabcost; // 批准时估算人工成本
	private String wolo4; // wolo4
	private String wol4; // wol4
	private String estservcost; // 估算的服务成本
	private String jpassets; // 作业计划资产
	private String workorderid; // 工单标识
	private String targetdesc_longdescription; // targetdesc 的详细描述
	private String estatapproutlabhrs; // 外部人工的估算成本
	private String pluscphyloc; // 实际位置
	private String launchentryname; // 启动条目名称
	private String fgfz; // 分管副总人员组
	private String assetlocpriority; // 资产/位置优先级
	private String wolo10; // wolo10
	private String environment; // 环境
	private String pluscphyloc_longdescription; // 实际位置详细描述
	private String classstructureid; // 类结构
	private String repairfacility; // 维修设施
	private String estatapprintlabcost; // 内部人工的批准成本
	private String estoutlabhrs; // 外部人工的估算小时数
	private String sendersysid; // 发送者系统标识
	private String featurelabel; // 功能部件标签
	private String vendor; // 供应商
	private String wosequence; // 序号
	private String pmduedate; // pm 截止日期
	private String estlabcost; // 估算人工成本
	private String haslinear; // 具有线性
	private String onbehalfof; // 代表
	private String whomischangefor_longdescription; // 用于指明次变更用于谁的“详细描述”
	private String actoutlabhrs; // 外部人工的实际小时数
	private String worts1; // worts1
	private String objectname; // 对象
	private String origrecordid; // 原始记录
	private String dept; // 部门
	private String apptype; // 应用类型
	private String projectnum; // 项目编号
	private String dirissuemtlstatus; // 直接发放物料状态
	private String usembodata; // 仅显示与当前工作资产相关联的数据表
	private String actintlabhrs; // 内部人工的实际小时数
	private String actmatcost; // 实际物料成本
	private String actlabcost; // 实际人工成本
	private String pointnum; // 测点
	private String wojo1; // wojo1
	private String parentchgsstatus; // 继承状态变更
	private String fnlconstraint; // 最晚完成日期
	private String reportedbyid; // 报告人
	private String woeq8; // woeq8
	private String displaytaskid; // 任务
	private String newtaskparentpmnum; // 父级 pm 数目
	private String hasfollowupwork; // 是否有后续工作
	private String commoditygroup; // 服务组
	private String calcshift; // shift
	private String wojp2; // wojp2
	private String fcprojectid; // 项目标识
	private String assetnum; // 资产
	private String slaapplied; // 应用 sla
	private String displaywonum; // 工单
	private String safetyplan_lookup_list_type; // 安全计划查找列表类型
	private String selectslas_mode; // 选择方式
	private String pluscnextdate; // 下一个校准到期日期
	private String jobtaskid; // 作业任务标识
	private String changeby; // 变更人
	private String fincntrlid; // 财务控制标识
	private String woeq13; // woeq13
	private String pluscnextdate_np; // 下一个校准到期日期
	private String pluscoverduedate; // 校准过期日期
	private String verification_longdescription; // 验证详细描述
	private String faildate; // 故障日期
	private String projectdesc; // 项目名称
	private String budgetnum; // 课题号
	private String budgettype; // 课题类型
	private String reqtype; // 需求类型
	private String leadergroup; // 部门负责人组
	private String splocations; // 位置
	private String changedate; // 变更日期
	private String statusdate; // 状态日期
	private String outmatcost; // 外部物料成本
	private String sneconstraint; // 最早开始日期
	private String inctasksinsched; // 在调度中包含任务
	private String woeq3; // woeq3
	private String targstartdate; // 目标开始时间
	private String wojo8; // wojo8
	private String generatedforpo; // po
	private String orgid; // 组织
	private String siteid; // 地点
	private String taskid; // 任务
	private String origtkid; // 原始凭单
	private String fnconoffset; // 完成约束偏移量
	private String actfinish; // 实际完成时间
	private String feature; // 功能部件
	private String wojo6; // wojo6
	private String warrantyexist; // 有保修
	private String cinum; // 配置项
	private String measuredate; // 测量日期
	private String observation; // 观测
	private String woeq14; // woeq14
	private String wojp1; // wojp1
	private String np_statusmemo; // 变更状态备忘录
	private String langcode; // 语言代码
	private String acttoolcost; // 实际工具成本
	private String woeq10; // woeq10
	private String estatapprtoolcost; // 估计批准时的工具成本
	private String interruptible; // 可中断
	private String wogroup; // 工单组
	private String reasonforchange_longdescription; // 指明变更原因的详细描述
	private String wojo4; // wojo4
	private String wojo5; // wojo5
	private String estatapprintlabhrs; // 内部人工的估算小时数
	private String genforpolineid; // po 行标识
	private String haschildren; // 有子级
	private String failurecode; // 故障类
	private String worktype; // 工作类型
	private String esttotalcost; // 估计成本总计
	private String woclass; // 类
	private String glaccount; // gl 科目
	private String targetdesc; // 目标描述
	private String lastcopylinkdate; // 上次复制文档链接的时间
	private String repfacsiteid; // 维修设施地点
	private String wojp3; // wojp3
	private String woisswap; // 这是资产交换吗
	private String environment_longdescription; // 环境详细描述
	private String jptask; // 任务
	private String woeq7; // woeq7
	private String assetuser; // 用户
	private String flowactionassist; // 流程操作辅助
	private String wolablnk; // 人工
	private String suspendflow; // 中止流程控制
	private String worts2; // worts2
	private String problemcode; // 问题代码
	private String calendar; // 日历
	private String lead; // 负责人
	private String spassets; // 资产
	private String assetfilterby; // 过滤条件
	private String availstatusdate; // 上次更新的物料状态
	private String calcpriority; // 计算的优先级
	private String wonum; // 工单
	private String outtoolcost; // 外部工具成本
	private String jpclass; // 工单类
	private String jpincludeclassless; // 显示未定义类的作业计划
	private String remarkdesc_longdescription; // 详细描述
	private String estatapprservcost; // 批准时估计服务成本
	private String respondby; // 响应人
	private String estatapprmatcost; // 估计批准时的物料成本
	private String wolo9; // wolo9
	private String whomischangefor; // 为谁进行此变更？
	private String wojp5; // wojp5
	private String wol1; // wol1
	private String wojo2; // wojo2
	private String supervisor; // 主管人
	private String worklocation; // 工作位置
	private String externalrefid; // 外部引用标识
	private String calccalendar; // calendar
	private String stconoffset; // 开始约束偏移量
	private String assetreconrstkt; // 凭单资产调整结果
	private String reportedby; // 报告人
	private String locwarrantynotice; // 位置担保通知
	private String wolo1; // wolo1
	private String wolo2; // wolo2
	private String wolo3; // wolo3
	private String woacceptscharges; // 接受费用
	private String woeq6; // woeq6
	private String origwoid; // 原始工单
	private String dupflag; // 重复标志
	private String jpnum; // 作业计划
	private String pmextdate; // pm 延长日期
	private String estintlabhrs; // 内部人工的估算小时数
	private String prnum; // 关联采购计划编码
	private String ponum; // 关联验收单编号
	private String wojp4; // wojp4
	private String onbehalfofname; // 名称
	private String wojo7; // wojo7

	public String getWorkpackmtlstatus() {
		return workpackmtlstatus;
	}

	public void setWorkpackmtlstatus(String workpackmtlstatus) {
		this.workpackmtlstatus = workpackmtlstatus;
	}

	public String getOwnergroup() {
		return ownergroup;
	}

	public void setOwnergroup(String ownergroup) {
		this.ownergroup = ownergroup;
	}

	public String getDowntime() {
		return downtime;
	}

	public void setDowntime(String downtime) {
		this.downtime = downtime;
	}

	public String getWoeq9() {
		return woeq9;
	}

	public void setWoeq9(String woeq9) {
		this.woeq9 = woeq9;
	}

	public String getAssignedownergroup() {
		return assignedownergroup;
	}

	public void setAssignedownergroup(String assignedownergroup) {
		this.assignedownergroup = assignedownergroup;
	}

	public String getCalcorgid() {
		return calcorgid;
	}

	public void setCalcorgid(String calcorgid) {
		this.calcorgid = calcorgid;
	}

	public String getHistoryflag() {
		return historyflag;
	}

	public void setHistoryflag(String historyflag) {
		this.historyflag = historyflag;
	}

	public String getIgnoresrmavail() {
		return ignoresrmavail;
	}

	public void setIgnoresrmavail(String ignoresrmavail) {
		this.ignoresrmavail = ignoresrmavail;
	}

	public String getIgnorediavail() {
		return ignorediavail;
	}

	public void setIgnorediavail(String ignorediavail) {
		this.ignorediavail = ignorediavail;
	}

	public String getOwnersysid() {
		return ownersysid;
	}

	public void setOwnersysid(String ownersysid) {
		this.ownersysid = ownersysid;
	}

	public String getOrigrecordclass() {
		return origrecordclass;
	}

	public void setOrigrecordclass(String origrecordclass) {
		this.origrecordclass = origrecordclass;
	}

	public String getEstatapproutlabcost() {
		return estatapproutlabcost;
	}

	public void setEstatapproutlabcost(String estatapproutlabcost) {
		this.estatapproutlabcost = estatapproutlabcost;
	}

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

	public String getFlowcontrolled() {
		return flowcontrolled;
	}

	public void setFlowcontrolled(String flowcontrolled) {
		this.flowcontrolled = flowcontrolled;
	}

	public String getCrewid() {
		return crewid;
	}

	public void setCrewid(String crewid) {
		this.crewid = crewid;
	}

	public String getPersongroup() {
		return persongroup;
	}

	public void setPersongroup(String persongroup) {
		this.persongroup = persongroup;
	}

	public String getEstintlabcost() {
		return estintlabcost;
	}

	public void setEstintlabcost(String estintlabcost) {
		this.estintlabcost = estintlabcost;
	}

	public String getWoeq2() {
		return woeq2;
	}

	public void setWoeq2(String woeq2) {
		this.woeq2 = woeq2;
	}

	public String getSafetyplanid() {
		return safetyplanid;
	}

	public void setSafetyplanid(String safetyplanid) {
		this.safetyplanid = safetyplanid;
	}

	public String getReasonforchange() {
		return reasonforchange;
	}

	public void setReasonforchange(String reasonforchange) {
		this.reasonforchange = reasonforchange;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPmnum() {
		return pmnum;
	}

	public void setPmnum(String pmnum) {
		this.pmnum = pmnum;
	}

	public String getEsttoolcost() {
		return esttoolcost;
	}

	public void setEsttoolcost(String esttoolcost) {
		this.esttoolcost = esttoolcost;
	}

	public String getWoci() {
		return woci;
	}

	public void setWoci(String woci) {
		this.woci = woci;
	}

	public String getCommodity() {
		return commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	public String getWoeq1() {
		return woeq1;
	}

	public void setWoeq1(String woeq1) {
		this.woeq1 = woeq1;
	}

	public String getSchedfinish() {
		return schedfinish;
	}

	public void setSchedfinish(String schedfinish) {
		this.schedfinish = schedfinish;
	}

	public String getPmnextduedate() {
		return pmnextduedate;
	}

	public void setPmnextduedate(String pmnextduedate) {
		this.pmnextduedate = pmnextduedate;
	}

	public String getActlabhrs() {
		return actlabhrs;
	}

	public void setActlabhrs(String actlabhrs) {
		this.actlabhrs = actlabhrs;
	}

	public String getWol2() {
		return wol2;
	}

	public void setWol2(String wol2) {
		this.wol2 = wol2;
	}

	public String getWol3() {
		return wol3;
	}

	public void setWol3(String wol3) {
		this.wol3 = wol3;
	}

	public String getEstdur() {
		return estdur;
	}

	public void setEstdur(String estdur) {
		this.estdur = estdur;
	}

	public String getEstlabhrs() {
		return estlabhrs;
	}

	public void setEstlabhrs(String estlabhrs) {
		this.estlabhrs = estlabhrs;
	}

	public String getEstmatcost() {
		return estmatcost;
	}

	public void setEstmatcost(String estmatcost) {
		this.estmatcost = estmatcost;
	}

	public String getWoeq5() {
		return woeq5;
	}

	public void setWoeq5(String woeq5) {
		this.woeq5 = woeq5;
	}

	public String getEstoutlabcost() {
		return estoutlabcost;
	}

	public void setEstoutlabcost(String estoutlabcost) {
		this.estoutlabcost = estoutlabcost;
	}

	public String getActintlabcost() {
		return actintlabcost;
	}

	public void setActintlabcost(String actintlabcost) {
		this.actintlabcost = actintlabcost;
	}

	public String getActstart() {
		return actstart;
	}

	public void setActstart(String actstart) {
		this.actstart = actstart;
	}

	public String getAssetcust() {
		return assetcust;
	}

	public void setAssetcust(String assetcust) {
		this.assetcust = assetcust;
	}

	public String getActtotalcost() {
		return acttotalcost;
	}

	public void setActtotalcost(String acttotalcost) {
		this.acttotalcost = acttotalcost;
	}

	public String getHasld() {
		return hasld;
	}

	public void setHasld(String hasld) {
		this.hasld = hasld;
	}

	public String getWoeq4() {
		return woeq4;
	}

	public void setWoeq4(String woeq4) {
		this.woeq4 = woeq4;
	}

	public String getWopriority() {
		return wopriority;
	}

	public void setWopriority(String wopriority) {
		this.wopriority = wopriority;
	}

	public String getOutlabcost() {
		return outlabcost;
	}

	public void setOutlabcost(String outlabcost) {
		this.outlabcost = outlabcost;
	}

	public String getNewchildclass() {
		return newchildclass;
	}

	public void setNewchildclass(String newchildclass) {
		this.newchildclass = newchildclass;
	}

	public String getPluscjprevnum() {
		return pluscjprevnum;
	}

	public void setPluscjprevnum(String pluscjprevnum) {
		this.pluscjprevnum = pluscjprevnum;
	}

	public String getPluscloop() {
		return pluscloop;
	}

	public void setPluscloop(String pluscloop) {
		this.pluscloop = pluscloop;
	}

	public String getSchedstart() {
		return schedstart;
	}

	public void setSchedstart(String schedstart) {
		this.schedstart = schedstart;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription_longdescription() {
		return description_longdescription;
	}

	public void setDescription_longdescription(
			String description_longdescription) {
		this.description_longdescription = description_longdescription;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getFirstapprstatus() {
		return firstapprstatus;
	}

	public void setFirstapprstatus(String firstapprstatus) {
		this.firstapprstatus = firstapprstatus;
	}

	public String getPluscfrequnit() {
		return pluscfrequnit;
	}

	public void setPluscfrequnit(String pluscfrequnit) {
		this.pluscfrequnit = pluscfrequnit;
	}

	public String getStartfeaturelabel() {
		return startfeaturelabel;
	}

	public void setStartfeaturelabel(String startfeaturelabel) {
		this.startfeaturelabel = startfeaturelabel;
	}

	public String getEndfeaturelabel() {
		return endfeaturelabel;
	}

	public void setEndfeaturelabel(String endfeaturelabel) {
		this.endfeaturelabel = endfeaturelabel;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getWoloc() {
		return woloc;
	}

	public void setWoloc(String woloc) {
		this.woloc = woloc;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getActoutlabcost() {
		return actoutlabcost;
	}

	public void setActoutlabcost(String actoutlabcost) {
		this.actoutlabcost = actoutlabcost;
	}

	public String getPluscismobile() {
		return pluscismobile;
	}

	public void setPluscismobile(String pluscismobile) {
		this.pluscismobile = pluscismobile;
	}

	public String getWoeq11() {
		return woeq11;
	}

	public void setWoeq11(String woeq11) {
		this.woeq11 = woeq11;
	}

	public String getPredessorwos() {
		return predessorwos;
	}

	public void setPredessorwos(String predessorwos) {
		this.predessorwos = predessorwos;
	}

	public String getReportedbyname() {
		return reportedbyname;
	}

	public void setReportedbyname(String reportedbyname) {
		this.reportedbyname = reportedbyname;
	}

	public String getOnbehalfofid() {
		return onbehalfofid;
	}

	public void setOnbehalfofid(String onbehalfofid) {
		this.onbehalfofid = onbehalfofid;
	}

	public String getStatusiface() {
		return statusiface;
	}

	public void setStatusiface(String statusiface) {
		this.statusiface = statusiface;
	}

	public String getHasparent() {
		return hasparent;
	}

	public void setHasparent(String hasparent) {
		this.hasparent = hasparent;
	}

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public String getAssetwarrantynotice() {
		return assetwarrantynotice;
	}

	public void setAssetwarrantynotice(String assetwarrantynotice) {
		this.assetwarrantynotice = assetwarrantynotice;
	}

	public String getBackoutplan() {
		return backoutplan;
	}

	public void setBackoutplan(String backoutplan) {
		this.backoutplan = backoutplan;
	}

	public String getBackoutplan_longdescription() {
		return backoutplan_longdescription;
	}

	public void setBackoutplan_longdescription(
			String backoutplan_longdescription) {
		this.backoutplan_longdescription = backoutplan_longdescription;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public String getMeasurementvalue() {
		return measurementvalue;
	}

	public void setMeasurementvalue(String measurementvalue) {
		this.measurementvalue = measurementvalue;
	}

	public String getJustifypriority() {
		return justifypriority;
	}

	public void setJustifypriority(String justifypriority) {
		this.justifypriority = justifypriority;
	}

	public String getWolo5() {
		return wolo5;
	}

	public void setWolo5(String wolo5) {
		this.wolo5 = wolo5;
	}

	public String getWolo6() {
		return wolo6;
	}

	public void setWolo6(String wolo6) {
		this.wolo6 = wolo6;
	}

	public String getWolo7() {
		return wolo7;
	}

	public void setWolo7(String wolo7) {
		this.wolo7 = wolo7;
	}

	public String getWolo8() {
		return wolo8;
	}

	public void setWolo8(String wolo8) {
		this.wolo8 = wolo8;
	}

	public String getGenforporevision() {
		return genforporevision;
	}

	public void setGenforporevision(String genforporevision) {
		this.genforporevision = genforporevision;
	}

	public String getStoreroommtlstatus() {
		return storeroommtlstatus;
	}

	public void setStoreroommtlstatus(String storeroommtlstatus) {
		this.storeroommtlstatus = storeroommtlstatus;
	}

	public String getFilterdate() {
		return filterdate;
	}

	public void setFilterdate(String filterdate) {
		this.filterdate = filterdate;
	}

	public String getPluscfrequency() {
		return pluscfrequency;
	}

	public void setPluscfrequency(String pluscfrequency) {
		this.pluscfrequency = pluscfrequency;
	}

	public String getWorts3() {
		return worts3;
	}

	public void setWorts3(String worts3) {
		this.worts3 = worts3;
	}

	public String getWorts4() {
		return worts4;
	}

	public void setWorts4(String worts4) {
		this.worts4 = worts4;
	}

	public String getSourcesysid() {
		return sourcesysid;
	}

	public void setSourcesysid(String sourcesysid) {
		this.sourcesysid = sourcesysid;
	}

	public String getFlowaction() {
		return flowaction;
	}

	public void setFlowaction(String flowaction) {
		this.flowaction = flowaction;
	}

	public String getWojo3() {
		return wojo3;
	}

	public void setWojo3(String wojo3) {
		this.wojo3 = wojo3;
	}

	public String getChargestore() {
		return chargestore;
	}

	public void setChargestore(String chargestore) {
		this.chargestore = chargestore;
	}

	public String getReportdate() {
		return reportdate;
	}

	public void setReportdate(String reportdate) {
		this.reportdate = reportdate;
	}

	public String getIstask() {
		return istask;
	}

	public void setIstask(String istask) {
		this.istask = istask;
	}

	public String getWoeq12() {
		return woeq12;
	}

	public void setWoeq12(String woeq12) {
		this.woeq12 = woeq12;
	}

	public String getWoasset() {
		return woasset;
	}

	public void setWoasset(String woasset) {
		this.woasset = woasset;
	}

	public String getChangebyparent() {
		return changebyparent;
	}

	public void setChangebyparent(String changebyparent) {
		this.changebyparent = changebyparent;
	}

	public String getRoutestopid() {
		return routestopid;
	}

	public void setRoutestopid(String routestopid) {
		this.routestopid = routestopid;
	}

	public String getWarrantyexpdate() {
		return warrantyexpdate;
	}

	public void setWarrantyexpdate(String warrantyexpdate) {
		this.warrantyexpdate = warrantyexpdate;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getRemarkenterdate() {
		return remarkenterdate;
	}

	public void setRemarkenterdate(String remarkenterdate) {
		this.remarkenterdate = remarkenterdate;
	}

	public String getRemarkdesc() {
		return remarkdesc;
	}

	public void setRemarkdesc(String remarkdesc) {
		this.remarkdesc = remarkdesc;
	}

	public String getFr2code() {
		return fr2code;
	}

	public void setFr2code(String fr2code) {
		this.fr2code = fr2code;
	}

	public String getPl1() {
		return pl1;
	}

	public void setPl1(String pl1) {
		this.pl1 = pl1;
	}

	public String getRemdur() {
		return remdur;
	}

	public void setRemdur(String remdur) {
		this.remdur = remdur;
	}

	public String getWorts5() {
		return worts5;
	}

	public void setWorts5(String worts5) {
		this.worts5 = worts5;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getJustifypriority_longdescription() {
		return justifypriority_longdescription;
	}

	public void setJustifypriority_longdescription(
			String justifypriority_longdescription) {
		this.justifypriority_longdescription = justifypriority_longdescription;
	}

	public String getEstatapprtotalcost() {
		return estatapprtotalcost;
	}

	public void setEstatapprtotalcost(String estatapprtotalcost) {
		this.estatapprtotalcost = estatapprtotalcost;
	}

	public String getTargcompdate() {
		return targcompdate;
	}

	public void setTargcompdate(String targcompdate) {
		this.targcompdate = targcompdate;
	}

	public String getFctaskid() {
		return fctaskid;
	}

	public void setFctaskid(String fctaskid) {
		this.fctaskid = fctaskid;
	}

	public String getActservcost() {
		return actservcost;
	}

	public void setActservcost(String actservcost) {
		this.actservcost = actservcost;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getEstatapprlabhrs() {
		return estatapprlabhrs;
	}

	public void setEstatapprlabhrs(String estatapprlabhrs) {
		this.estatapprlabhrs = estatapprlabhrs;
	}

	public String getEstatapprlabcost() {
		return estatapprlabcost;
	}

	public void setEstatapprlabcost(String estatapprlabcost) {
		this.estatapprlabcost = estatapprlabcost;
	}

	public String getWolo4() {
		return wolo4;
	}

	public void setWolo4(String wolo4) {
		this.wolo4 = wolo4;
	}

	public String getWol4() {
		return wol4;
	}

	public void setWol4(String wol4) {
		this.wol4 = wol4;
	}

	public String getEstservcost() {
		return estservcost;
	}

	public void setEstservcost(String estservcost) {
		this.estservcost = estservcost;
	}

	public String getJpassets() {
		return jpassets;
	}

	public void setJpassets(String jpassets) {
		this.jpassets = jpassets;
	}

	public String getWorkorderid() {
		return workorderid;
	}

	public void setWorkorderid(String workorderid) {
		this.workorderid = workorderid;
	}

	public String getTargetdesc_longdescription() {
		return targetdesc_longdescription;
	}

	public void setTargetdesc_longdescription(String targetdesc_longdescription) {
		this.targetdesc_longdescription = targetdesc_longdescription;
	}

	public String getEstatapproutlabhrs() {
		return estatapproutlabhrs;
	}

	public void setEstatapproutlabhrs(String estatapproutlabhrs) {
		this.estatapproutlabhrs = estatapproutlabhrs;
	}

	public String getPluscphyloc() {
		return pluscphyloc;
	}

	public void setPluscphyloc(String pluscphyloc) {
		this.pluscphyloc = pluscphyloc;
	}

	public String getLaunchentryname() {
		return launchentryname;
	}

	public void setLaunchentryname(String launchentryname) {
		this.launchentryname = launchentryname;
	}

	public String getFgfz() {
		return fgfz;
	}

	public void setFgfz(String fgfz) {
		this.fgfz = fgfz;
	}

	public String getAssetlocpriority() {
		return assetlocpriority;
	}

	public void setAssetlocpriority(String assetlocpriority) {
		this.assetlocpriority = assetlocpriority;
	}

	public String getWolo10() {
		return wolo10;
	}

	public void setWolo10(String wolo10) {
		this.wolo10 = wolo10;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getPluscphyloc_longdescription() {
		return pluscphyloc_longdescription;
	}

	public void setPluscphyloc_longdescription(
			String pluscphyloc_longdescription) {
		this.pluscphyloc_longdescription = pluscphyloc_longdescription;
	}

	public String getClassstructureid() {
		return classstructureid;
	}

	public void setClassstructureid(String classstructureid) {
		this.classstructureid = classstructureid;
	}

	public String getRepairfacility() {
		return repairfacility;
	}

	public void setRepairfacility(String repairfacility) {
		this.repairfacility = repairfacility;
	}

	public String getEstatapprintlabcost() {
		return estatapprintlabcost;
	}

	public void setEstatapprintlabcost(String estatapprintlabcost) {
		this.estatapprintlabcost = estatapprintlabcost;
	}

	public String getEstoutlabhrs() {
		return estoutlabhrs;
	}

	public void setEstoutlabhrs(String estoutlabhrs) {
		this.estoutlabhrs = estoutlabhrs;
	}

	public String getSendersysid() {
		return sendersysid;
	}

	public void setSendersysid(String sendersysid) {
		this.sendersysid = sendersysid;
	}

	public String getFeaturelabel() {
		return featurelabel;
	}

	public void setFeaturelabel(String featurelabel) {
		this.featurelabel = featurelabel;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getWosequence() {
		return wosequence;
	}

	public void setWosequence(String wosequence) {
		this.wosequence = wosequence;
	}

	public String getPmduedate() {
		return pmduedate;
	}

	public void setPmduedate(String pmduedate) {
		this.pmduedate = pmduedate;
	}

	public String getEstlabcost() {
		return estlabcost;
	}

	public void setEstlabcost(String estlabcost) {
		this.estlabcost = estlabcost;
	}

	public String getHaslinear() {
		return haslinear;
	}

	public void setHaslinear(String haslinear) {
		this.haslinear = haslinear;
	}

	public String getOnbehalfof() {
		return onbehalfof;
	}

	public void setOnbehalfof(String onbehalfof) {
		this.onbehalfof = onbehalfof;
	}

	public String getWhomischangefor_longdescription() {
		return whomischangefor_longdescription;
	}

	public void setWhomischangefor_longdescription(
			String whomischangefor_longdescription) {
		this.whomischangefor_longdescription = whomischangefor_longdescription;
	}

	public String getActoutlabhrs() {
		return actoutlabhrs;
	}

	public void setActoutlabhrs(String actoutlabhrs) {
		this.actoutlabhrs = actoutlabhrs;
	}

	public String getWorts1() {
		return worts1;
	}

	public void setWorts1(String worts1) {
		this.worts1 = worts1;
	}

	public String getObjectname() {
		return objectname;
	}

	public void setObjectname(String objectname) {
		this.objectname = objectname;
	}

	public String getOrigrecordid() {
		return origrecordid;
	}

	public void setOrigrecordid(String origrecordid) {
		this.origrecordid = origrecordid;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getApptype() {
		return apptype;
	}

	public void setApptype(String apptype) {
		this.apptype = apptype;
	}

	public String getProjectnum() {
		return projectnum;
	}

	public void setProjectnum(String projectnum) {
		this.projectnum = projectnum;
	}

	public String getDirissuemtlstatus() {
		return dirissuemtlstatus;
	}

	public void setDirissuemtlstatus(String dirissuemtlstatus) {
		this.dirissuemtlstatus = dirissuemtlstatus;
	}

	public String getUsembodata() {
		return usembodata;
	}

	public void setUsembodata(String usembodata) {
		this.usembodata = usembodata;
	}

	public String getActintlabhrs() {
		return actintlabhrs;
	}

	public void setActintlabhrs(String actintlabhrs) {
		this.actintlabhrs = actintlabhrs;
	}

	public String getActmatcost() {
		return actmatcost;
	}

	public void setActmatcost(String actmatcost) {
		this.actmatcost = actmatcost;
	}

	public String getActlabcost() {
		return actlabcost;
	}

	public void setActlabcost(String actlabcost) {
		this.actlabcost = actlabcost;
	}

	public String getPointnum() {
		return pointnum;
	}

	public void setPointnum(String pointnum) {
		this.pointnum = pointnum;
	}

	public String getWojo1() {
		return wojo1;
	}

	public void setWojo1(String wojo1) {
		this.wojo1 = wojo1;
	}

	public String getParentchgsstatus() {
		return parentchgsstatus;
	}

	public void setParentchgsstatus(String parentchgsstatus) {
		this.parentchgsstatus = parentchgsstatus;
	}

	public String getFnlconstraint() {
		return fnlconstraint;
	}

	public void setFnlconstraint(String fnlconstraint) {
		this.fnlconstraint = fnlconstraint;
	}

	public String getReportedbyid() {
		return reportedbyid;
	}

	public void setReportedbyid(String reportedbyid) {
		this.reportedbyid = reportedbyid;
	}

	public String getWoeq8() {
		return woeq8;
	}

	public void setWoeq8(String woeq8) {
		this.woeq8 = woeq8;
	}

	public String getDisplaytaskid() {
		return displaytaskid;
	}

	public void setDisplaytaskid(String displaytaskid) {
		this.displaytaskid = displaytaskid;
	}

	public String getNewtaskparentpmnum() {
		return newtaskparentpmnum;
	}

	public void setNewtaskparentpmnum(String newtaskparentpmnum) {
		this.newtaskparentpmnum = newtaskparentpmnum;
	}

	public String getHasfollowupwork() {
		return hasfollowupwork;
	}

	public void setHasfollowupwork(String hasfollowupwork) {
		this.hasfollowupwork = hasfollowupwork;
	}

	public String getCommoditygroup() {
		return commoditygroup;
	}

	public void setCommoditygroup(String commoditygroup) {
		this.commoditygroup = commoditygroup;
	}

	public String getCalcshift() {
		return calcshift;
	}

	public void setCalcshift(String calcshift) {
		this.calcshift = calcshift;
	}

	public String getWojp2() {
		return wojp2;
	}

	public void setWojp2(String wojp2) {
		this.wojp2 = wojp2;
	}

	public String getFcprojectid() {
		return fcprojectid;
	}

	public void setFcprojectid(String fcprojectid) {
		this.fcprojectid = fcprojectid;
	}

	public String getAssetnum() {
		return assetnum;
	}

	public void setAssetnum(String assetnum) {
		this.assetnum = assetnum;
	}

	public String getSlaapplied() {
		return slaapplied;
	}

	public void setSlaapplied(String slaapplied) {
		this.slaapplied = slaapplied;
	}

	public String getDisplaywonum() {
		return displaywonum;
	}

	public void setDisplaywonum(String displaywonum) {
		this.displaywonum = displaywonum;
	}

	public String getSafetyplan_lookup_list_type() {
		return safetyplan_lookup_list_type;
	}

	public void setSafetyplan_lookup_list_type(
			String safetyplan_lookup_list_type) {
		this.safetyplan_lookup_list_type = safetyplan_lookup_list_type;
	}

	public String getSelectslas_mode() {
		return selectslas_mode;
	}

	public void setSelectslas_mode(String selectslas_mode) {
		this.selectslas_mode = selectslas_mode;
	}

	public String getPluscnextdate() {
		return pluscnextdate;
	}

	public void setPluscnextdate(String pluscnextdate) {
		this.pluscnextdate = pluscnextdate;
	}

	public String getJobtaskid() {
		return jobtaskid;
	}

	public void setJobtaskid(String jobtaskid) {
		this.jobtaskid = jobtaskid;
	}

	public String getChangeby() {
		return changeby;
	}

	public void setChangeby(String changeby) {
		this.changeby = changeby;
	}

	public String getFincntrlid() {
		return fincntrlid;
	}

	public void setFincntrlid(String fincntrlid) {
		this.fincntrlid = fincntrlid;
	}

	public String getWoeq13() {
		return woeq13;
	}

	public void setWoeq13(String woeq13) {
		this.woeq13 = woeq13;
	}

	public String getPluscnextdate_np() {
		return pluscnextdate_np;
	}

	public void setPluscnextdate_np(String pluscnextdate_np) {
		this.pluscnextdate_np = pluscnextdate_np;
	}

	public String getPluscoverduedate() {
		return pluscoverduedate;
	}

	public void setPluscoverduedate(String pluscoverduedate) {
		this.pluscoverduedate = pluscoverduedate;
	}

	public String getVerification_longdescription() {
		return verification_longdescription;
	}

	public void setVerification_longdescription(
			String verification_longdescription) {
		this.verification_longdescription = verification_longdescription;
	}

	public String getFaildate() {
		return faildate;
	}

	public void setFaildate(String faildate) {
		this.faildate = faildate;
	}

	public String getProjectdesc() {
		return projectdesc;
	}

	public void setProjectdesc(String projectdesc) {
		this.projectdesc = projectdesc;
	}

	public String getBudgetnum() {
		return budgetnum;
	}

	public void setBudgetnum(String budgetnum) {
		this.budgetnum = budgetnum;
	}

	public String getBudgettype() {
		return budgettype;
	}

	public void setBudgettype(String budgettype) {
		this.budgettype = budgettype;
	}

	public String getReqtype() {
		return reqtype;
	}

	public void setReqtype(String reqtype) {
		this.reqtype = reqtype;
	}

	public String getLeadergroup() {
		return leadergroup;
	}

	public void setLeadergroup(String leadergroup) {
		this.leadergroup = leadergroup;
	}

	public String getSplocations() {
		return splocations;
	}

	public void setSplocations(String splocations) {
		this.splocations = splocations;
	}

	public String getChangedate() {
		return changedate;
	}

	public void setChangedate(String changedate) {
		this.changedate = changedate;
	}

	public String getStatusdate() {
		return statusdate;
	}

	public void setStatusdate(String statusdate) {
		this.statusdate = statusdate;
	}

	public String getOutmatcost() {
		return outmatcost;
	}

	public void setOutmatcost(String outmatcost) {
		this.outmatcost = outmatcost;
	}

	public String getSneconstraint() {
		return sneconstraint;
	}

	public void setSneconstraint(String sneconstraint) {
		this.sneconstraint = sneconstraint;
	}

	public String getInctasksinsched() {
		return inctasksinsched;
	}

	public void setInctasksinsched(String inctasksinsched) {
		this.inctasksinsched = inctasksinsched;
	}

	public String getWoeq3() {
		return woeq3;
	}

	public void setWoeq3(String woeq3) {
		this.woeq3 = woeq3;
	}

	public String getTargstartdate() {
		return targstartdate;
	}

	public void setTargstartdate(String targstartdate) {
		this.targstartdate = targstartdate;
	}

	public String getWojo8() {
		return wojo8;
	}

	public void setWojo8(String wojo8) {
		this.wojo8 = wojo8;
	}

	public String getGeneratedforpo() {
		return generatedforpo;
	}

	public void setGeneratedforpo(String generatedforpo) {
		this.generatedforpo = generatedforpo;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getSiteid() {
		return siteid;
	}

	public void setSiteid(String siteid) {
		this.siteid = siteid;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getOrigtkid() {
		return origtkid;
	}

	public void setOrigtkid(String origtkid) {
		this.origtkid = origtkid;
	}

	public String getFnconoffset() {
		return fnconoffset;
	}

	public void setFnconoffset(String fnconoffset) {
		this.fnconoffset = fnconoffset;
	}

	public String getActfinish() {
		return actfinish;
	}

	public void setActfinish(String actfinish) {
		this.actfinish = actfinish;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getWojo6() {
		return wojo6;
	}

	public void setWojo6(String wojo6) {
		this.wojo6 = wojo6;
	}

	public String getWarrantyexist() {
		return warrantyexist;
	}

	public void setWarrantyexist(String warrantyexist) {
		this.warrantyexist = warrantyexist;
	}

	public String getCinum() {
		return cinum;
	}

	public void setCinum(String cinum) {
		this.cinum = cinum;
	}

	public String getMeasuredate() {
		return measuredate;
	}

	public void setMeasuredate(String measuredate) {
		this.measuredate = measuredate;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getWoeq14() {
		return woeq14;
	}

	public void setWoeq14(String woeq14) {
		this.woeq14 = woeq14;
	}

	public String getWojp1() {
		return wojp1;
	}

	public void setWojp1(String wojp1) {
		this.wojp1 = wojp1;
	}

	public String getNp_statusmemo() {
		return np_statusmemo;
	}

	public void setNp_statusmemo(String np_statusmemo) {
		this.np_statusmemo = np_statusmemo;
	}

	public String getLangcode() {
		return langcode;
	}

	public void setLangcode(String langcode) {
		this.langcode = langcode;
	}

	public String getActtoolcost() {
		return acttoolcost;
	}

	public void setActtoolcost(String acttoolcost) {
		this.acttoolcost = acttoolcost;
	}

	public String getWoeq10() {
		return woeq10;
	}

	public void setWoeq10(String woeq10) {
		this.woeq10 = woeq10;
	}

	public String getEstatapprtoolcost() {
		return estatapprtoolcost;
	}

	public void setEstatapprtoolcost(String estatapprtoolcost) {
		this.estatapprtoolcost = estatapprtoolcost;
	}

	public String getInterruptible() {
		return interruptible;
	}

	public void setInterruptible(String interruptible) {
		this.interruptible = interruptible;
	}

	public String getWogroup() {
		return wogroup;
	}

	public void setWogroup(String wogroup) {
		this.wogroup = wogroup;
	}

	public String getReasonforchange_longdescription() {
		return reasonforchange_longdescription;
	}

	public void setReasonforchange_longdescription(
			String reasonforchange_longdescription) {
		this.reasonforchange_longdescription = reasonforchange_longdescription;
	}

	public String getWojo4() {
		return wojo4;
	}

	public void setWojo4(String wojo4) {
		this.wojo4 = wojo4;
	}

	public String getWojo5() {
		return wojo5;
	}

	public void setWojo5(String wojo5) {
		this.wojo5 = wojo5;
	}

	public String getEstatapprintlabhrs() {
		return estatapprintlabhrs;
	}

	public void setEstatapprintlabhrs(String estatapprintlabhrs) {
		this.estatapprintlabhrs = estatapprintlabhrs;
	}

	public String getGenforpolineid() {
		return genforpolineid;
	}

	public void setGenforpolineid(String genforpolineid) {
		this.genforpolineid = genforpolineid;
	}

	public String getHaschildren() {
		return haschildren;
	}

	public void setHaschildren(String haschildren) {
		this.haschildren = haschildren;
	}

	public String getFailurecode() {
		return failurecode;
	}

	public void setFailurecode(String failurecode) {
		this.failurecode = failurecode;
	}

	public String getWorktype() {
		return worktype;
	}

	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}

	public String getEsttotalcost() {
		return esttotalcost;
	}

	public void setEsttotalcost(String esttotalcost) {
		this.esttotalcost = esttotalcost;
	}

	public String getWoclass() {
		return woclass;
	}

	public void setWoclass(String woclass) {
		this.woclass = woclass;
	}

	public String getGlaccount() {
		return glaccount;
	}

	public void setGlaccount(String glaccount) {
		this.glaccount = glaccount;
	}

	public String getTargetdesc() {
		return targetdesc;
	}

	public void setTargetdesc(String targetdesc) {
		this.targetdesc = targetdesc;
	}

	public String getLastcopylinkdate() {
		return lastcopylinkdate;
	}

	public void setLastcopylinkdate(String lastcopylinkdate) {
		this.lastcopylinkdate = lastcopylinkdate;
	}

	public String getRepfacsiteid() {
		return repfacsiteid;
	}

	public void setRepfacsiteid(String repfacsiteid) {
		this.repfacsiteid = repfacsiteid;
	}

	public String getWojp3() {
		return wojp3;
	}

	public void setWojp3(String wojp3) {
		this.wojp3 = wojp3;
	}

	public String getWoisswap() {
		return woisswap;
	}

	public void setWoisswap(String woisswap) {
		this.woisswap = woisswap;
	}

	public String getEnvironment_longdescription() {
		return environment_longdescription;
	}

	public void setEnvironment_longdescription(
			String environment_longdescription) {
		this.environment_longdescription = environment_longdescription;
	}

	public String getJptask() {
		return jptask;
	}

	public void setJptask(String jptask) {
		this.jptask = jptask;
	}

	public String getWoeq7() {
		return woeq7;
	}

	public void setWoeq7(String woeq7) {
		this.woeq7 = woeq7;
	}

	public String getAssetuser() {
		return assetuser;
	}

	public void setAssetuser(String assetuser) {
		this.assetuser = assetuser;
	}

	public String getFlowactionassist() {
		return flowactionassist;
	}

	public void setFlowactionassist(String flowactionassist) {
		this.flowactionassist = flowactionassist;
	}

	public String getWolablnk() {
		return wolablnk;
	}

	public void setWolablnk(String wolablnk) {
		this.wolablnk = wolablnk;
	}

	public String getSuspendflow() {
		return suspendflow;
	}

	public void setSuspendflow(String suspendflow) {
		this.suspendflow = suspendflow;
	}

	public String getWorts2() {
		return worts2;
	}

	public void setWorts2(String worts2) {
		this.worts2 = worts2;
	}

	public String getProblemcode() {
		return problemcode;
	}

	public void setProblemcode(String problemcode) {
		this.problemcode = problemcode;
	}

	public String getCalendar() {
		return calendar;
	}

	public void setCalendar(String calendar) {
		this.calendar = calendar;
	}

	public String getLead() {
		return lead;
	}

	public void setLead(String lead) {
		this.lead = lead;
	}

	public String getSpassets() {
		return spassets;
	}

	public void setSpassets(String spassets) {
		this.spassets = spassets;
	}

	public String getAssetfilterby() {
		return assetfilterby;
	}

	public void setAssetfilterby(String assetfilterby) {
		this.assetfilterby = assetfilterby;
	}

	public String getAvailstatusdate() {
		return availstatusdate;
	}

	public void setAvailstatusdate(String availstatusdate) {
		this.availstatusdate = availstatusdate;
	}

	public String getCalcpriority() {
		return calcpriority;
	}

	public void setCalcpriority(String calcpriority) {
		this.calcpriority = calcpriority;
	}

	public String getWonum() {
		return wonum;
	}

	public void setWonum(String wonum) {
		this.wonum = wonum;
	}

	public String getOuttoolcost() {
		return outtoolcost;
	}

	public void setOuttoolcost(String outtoolcost) {
		this.outtoolcost = outtoolcost;
	}

	public String getJpclass() {
		return jpclass;
	}

	public void setJpclass(String jpclass) {
		this.jpclass = jpclass;
	}

	public String getJpincludeclassless() {
		return jpincludeclassless;
	}

	public void setJpincludeclassless(String jpincludeclassless) {
		this.jpincludeclassless = jpincludeclassless;
	}

	public String getRemarkdesc_longdescription() {
		return remarkdesc_longdescription;
	}

	public void setRemarkdesc_longdescription(String remarkdesc_longdescription) {
		this.remarkdesc_longdescription = remarkdesc_longdescription;
	}

	public String getEstatapprservcost() {
		return estatapprservcost;
	}

	public void setEstatapprservcost(String estatapprservcost) {
		this.estatapprservcost = estatapprservcost;
	}

	public String getRespondby() {
		return respondby;
	}

	public void setRespondby(String respondby) {
		this.respondby = respondby;
	}

	public String getEstatapprmatcost() {
		return estatapprmatcost;
	}

	public void setEstatapprmatcost(String estatapprmatcost) {
		this.estatapprmatcost = estatapprmatcost;
	}

	public String getWolo9() {
		return wolo9;
	}

	public void setWolo9(String wolo9) {
		this.wolo9 = wolo9;
	}

	public String getWhomischangefor() {
		return whomischangefor;
	}

	public void setWhomischangefor(String whomischangefor) {
		this.whomischangefor = whomischangefor;
	}

	public String getWojp5() {
		return wojp5;
	}

	public void setWojp5(String wojp5) {
		this.wojp5 = wojp5;
	}

	public String getWol1() {
		return wol1;
	}

	public void setWol1(String wol1) {
		this.wol1 = wol1;
	}

	public String getWojo2() {
		return wojo2;
	}

	public void setWojo2(String wojo2) {
		this.wojo2 = wojo2;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getWorklocation() {
		return worklocation;
	}

	public void setWorklocation(String worklocation) {
		this.worklocation = worklocation;
	}

	public String getExternalrefid() {
		return externalrefid;
	}

	public void setExternalrefid(String externalrefid) {
		this.externalrefid = externalrefid;
	}

	public String getCalccalendar() {
		return calccalendar;
	}

	public void setCalccalendar(String calccalendar) {
		this.calccalendar = calccalendar;
	}

	public String getStconoffset() {
		return stconoffset;
	}

	public void setStconoffset(String stconoffset) {
		this.stconoffset = stconoffset;
	}

	public String getAssetreconrstkt() {
		return assetreconrstkt;
	}

	public void setAssetreconrstkt(String assetreconrstkt) {
		this.assetreconrstkt = assetreconrstkt;
	}

	public String getReportedby() {
		return reportedby;
	}

	public void setReportedby(String reportedby) {
		this.reportedby = reportedby;
	}

	public String getLocwarrantynotice() {
		return locwarrantynotice;
	}

	public void setLocwarrantynotice(String locwarrantynotice) {
		this.locwarrantynotice = locwarrantynotice;
	}

	public String getWolo1() {
		return wolo1;
	}

	public void setWolo1(String wolo1) {
		this.wolo1 = wolo1;
	}

	public String getWolo2() {
		return wolo2;
	}

	public void setWolo2(String wolo2) {
		this.wolo2 = wolo2;
	}

	public String getWolo3() {
		return wolo3;
	}

	public void setWolo3(String wolo3) {
		this.wolo3 = wolo3;
	}

	public String getWoacceptscharges() {
		return woacceptscharges;
	}

	public void setWoacceptscharges(String woacceptscharges) {
		this.woacceptscharges = woacceptscharges;
	}

	public String getWoeq6() {
		return woeq6;
	}

	public void setWoeq6(String woeq6) {
		this.woeq6 = woeq6;
	}

	public String getOrigwoid() {
		return origwoid;
	}

	public void setOrigwoid(String origwoid) {
		this.origwoid = origwoid;
	}

	public String getDupflag() {
		return dupflag;
	}

	public void setDupflag(String dupflag) {
		this.dupflag = dupflag;
	}

	public String getJpnum() {
		return jpnum;
	}

	public void setJpnum(String jpnum) {
		this.jpnum = jpnum;
	}

	public String getPmextdate() {
		return pmextdate;
	}

	public void setPmextdate(String pmextdate) {
		this.pmextdate = pmextdate;
	}

	public String getEstintlabhrs() {
		return estintlabhrs;
	}

	public void setEstintlabhrs(String estintlabhrs) {
		this.estintlabhrs = estintlabhrs;
	}

	public String getPrnum() {
		return prnum;
	}

	public void setPrnum(String prnum) {
		this.prnum = prnum;
	}

	public String getPonum() {
		return ponum;
	}

	public void setPonum(String ponum) {
		this.ponum = ponum;
	}

	public String getWojp4() {
		return wojp4;
	}

	public void setWojp4(String wojp4) {
		this.wojp4 = wojp4;
	}

	public String getOnbehalfofname() {
		return onbehalfofname;
	}

	public void setOnbehalfofname(String onbehalfofname) {
		this.onbehalfofname = onbehalfofname;
	}

	public String getWojo7() {
		return wojo7;
	}

	public void setWojo7(String wojo7) {
		this.wojo7 = wojo7;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 解析工单数据
	 * 
	 * @param result
	 * @return 工单列表
	 */
	public WorkorderList paser(SoapObject result) {
		WorkorderList workorderList = new WorkorderList();
		ArrayList<Workorder> workorders = workorderList.getWorkorders();
		Workorder workorder = null;

		int count = result.getPropertyCount();
		for (int i = 0; i < count; i++) {
			SoapObject subObj = (SoapObject) result.getProperty(i);
			workorder = new Workorder();
			workorder.setWonum(SoapHelper.getSoapObjectString(subObj,
					Fields.WONUM));
			workorder.setDescription(SoapHelper.getSoapObjectString(subObj,
					Fields.DESCRIPTION));
			workorder.setStatus(SoapHelper.getSoapObjectString(subObj,
					Fields.STATUS));
			workorder.setStatusdate(SoapHelper.getSoapObjectString(subObj,
					Fields.STATUSDATE));
			workorder.setWorktype(SoapHelper.getSoapObjectString(subObj,
					Fields.WORKTYPE));
			workorder.setLocation(SoapHelper.getSoapObjectString(subObj,
					Fields.LOCATION));
			workorder.setAssetnum(SoapHelper.getSoapObjectString(subObj,
					Fields.ASSETNUM));
			workorder.setSiteid(SoapHelper.getSoapObjectString(subObj,
					Fields.SITEID));
			workorder.setFailurecode(SoapHelper.getSoapObjectString(subObj,
					Fields.FAILURECODE));
			workorder.setWorkorderid(SoapHelper.getSoapObjectString(subObj,
					Fields.WORKORDERID));
			workorder.setProblemcode(SoapHelper.getSoapObjectString(subObj,
					Fields.PROBLEMCODE));
			workorder.setProblemcode(SoapHelper.getSoapObjectString(subObj,
					Fields.PL1));
			workorder.setJpnum(SoapHelper.getSoapObjectString(subObj,
					Fields.JPNUM));
			workorder.setWopriority(SoapHelper.getSoapObjectString(subObj,
					Fields.WOPRIORITY));
			workorder.setPmnum(SoapHelper.getSoapObjectString(subObj,
					Fields.PMNUM));
			workorder.setReportedby(SoapHelper.getSoapObjectString(subObj,
					Fields.REPORTEDBY));
			workorder.setLead(SoapHelper.getSoapObjectString(subObj,
					Fields.LEAD));
			workorder.setSupervisor(SoapHelper.getSoapObjectString(subObj,
					Fields.SUPERVISOR));
			workorder.setReportdate(SoapHelper.getSoapObjectString(subObj,
					Fields.REPORTDATE));
			workorder.setOnbehalfof(SoapHelper.getSoapObjectString(subObj,
					Fields.ONBEHALFOF));
			workorder.setPhone(SoapHelper.getSoapObjectString(subObj,
					Fields.PHONE));
			workorder.setClassstructureid(SoapHelper.getSoapObjectString(
					subObj, Fields.CLASSSTRUCTUREID));

			workorders.add(workorder);
		}
		return workorderList;
	}

	/**
	 * @return the auths
	 */
	public TreeSet<String> getAuths() {
		return auths;
	}

	/**
	 * @param auths
	 *            the auths to set
	 */
	public void setAuths(TreeSet<String> auths) {
		this.auths = auths;
	}

	class Fields {
		public static final String WORKPACKMTLSTATUS = "WORKPACKMTLSTATUS"; // 工作包物料状态
		public static final String OWNERGROUP = "OWNERGROUP"; // 所有者组
		public static final String DOWNTIME = "DOWNTIME"; // 停工时间
		public static final String WOEQ9 = "WOEQ9"; // Woeq9
		public static final String ASSIGNEDOWNERGROUP = "ASSIGNEDOWNERGROUP"; // 分配的所有者组
		public static final String CALCORGID = "CALCORGID"; // Organization
		public static final String HISTORYFLAG = "HISTORYFLAG"; // 历史记录
		public static final String IGNORESRMAVAIL = "IGNORESRMAVAIL"; // 针对工单状态忽略库房可用性
		public static final String IGNOREDIAVAIL = "IGNOREDIAVAIL"; // 针对工单状态忽略直接发放可用性
		public static final String OWNERSYSID = "OWNERSYSID"; // 所有者系统标识
		public static final String ORIGRECORDCLASS = "ORIGRECORDCLASS"; // 原始记录的类
		public static final String ESTATAPPROUTLABCOST = "ESTATAPPROUTLABCOST"; // 外部人工的估算小时数
		public static final String VERIFICATION = "VERIFICATION"; // 验证
		public static final String FLOWCONTROLLED = "FLOWCONTROLLED"; // 在流程控制下
		public static final String CREWID = "CREWID"; // 班组
		public static final String PERSONGROUP = "PERSONGROUP"; // 工作组
		public static final String ESTINTLABCOST = "ESTINTLABCOST"; // 内部人工的估算成本
		public static final String WOEQ2 = "WOEQ2"; // Woeq2
		public static final String SAFETYPLANID = "SAFETYPLANID"; // 安全计划
		public static final String REASONFORCHANGE = "REASONFORCHANGE"; // 变更的原因
		public static final String DESCRIPTION = "DESCRIPTION"; // 描述
		public static final String PMNUM = "PMNUM"; // PM
		public static final String ESTTOOLCOST = "ESTTOOLCOST"; // 估算的工具成本
		public static final String WOCI = "WOCI"; // CI 工单
		public static final String COMMODITY = "COMMODITY"; // 服务
		public static final String WOEQ1 = "WOEQ1"; // Woeq1
		public static final String SCHEDFINISH = "SCHEDFINISH"; // 计划完成时间
		public static final String PMNEXTDUEDATE = "PMNEXTDUEDATE"; // PM 下一截止日期
		public static final String ACTLABHRS = "ACTLABHRS"; // 实际工时
		public static final String WOL2 = "WOL2"; // Wol2
		public static final String WOL3 = "WOL3"; // Wol3
		public static final String ESTDUR = "ESTDUR"; // 持续时间
		public static final String ESTLABHRS = "ESTLABHRS"; // 估算的工时
		public static final String ESTMATCOST = "ESTMATCOST"; // 估算的物料成本
		public static final String WOEQ5 = "WOEQ5"; // Woeq5
		public static final String ESTOUTLABCOST = "ESTOUTLABCOST"; // 外部人工的估算成本
		public static final String ACTINTLABCOST = "ACTINTLABCOST"; // 内部人工的实际成本
		public static final String ACTSTART = "ACTSTART"; // 实际开始时间
		public static final String ASSETCUST = "ASSETCUST"; // 管理人
		public static final String ACTTOTALCOST = "ACTTOTALCOST"; // 实际成本总计
		public static final String HASLD = "HASLD"; // 具有详细描述
		public static final String WOEQ4 = "WOEQ4"; // Woeq4
		public static final String WOPRIORITY = "WOPRIORITY"; // 优先级
		public static final String OUTLABCOST = "OUTLABCOST"; // 外部人工成本
		public static final String NEWCHILDCLASS = "NEWCHILDCLASS"; // 新子类
		public static final String PLUSCJPREVNUM = "PLUSCJPREVNUM"; // 作业计划修订版号
		public static final String PLUSCLOOP = "PLUSCLOOP"; // 循环校准
		public static final String SCHEDSTART = "SCHEDSTART"; // 计划开始时间
		public static final String STATUS = "STATUS"; // 状态
		public static final String DESCRIPTION_LONGDESCRIPTION = "DESCRIPTION_LONGDESCRIPTION"; // 详细信息
		public static final String ROUTE = "ROUTE"; // 检修路线
		public static final String FIRSTAPPRSTATUS = "FIRSTAPPRSTATUS"; // 首个批准状态
		public static final String PLUSCFREQUNIT = "PLUSCFREQUNIT"; // 频率单位
		public static final String STARTFEATURELABEL = "STARTFEATURELABEL"; // 参照点
		public static final String ENDFEATURELABEL = "ENDFEATURELABEL"; // 参照点
		public static final String PARENT = "PARENT"; // 父工单
		public static final String WOLOC = "WOLOC"; // 工单位置
		public static final String OWNER = "OWNER"; // 所有者
		public static final String PHONE = "PHONE"; // 电话
		public static final String ACTOUTLABCOST = "ACTOUTLABCOST"; // 外部人工的实际小时数
		public static final String PLUSCISMOBILE = "PLUSCISMOBILE"; // 是否移动
		public static final String WOEQ11 = "WOEQ11"; // Woeq11
		public static final String PREDESSORWOS = "PREDESSORWOS"; // 先行
		public static final String REPORTEDBYNAME = "REPORTEDBYNAME"; // 名称
		public static final String ONBEHALFOFID = "ONBEHALFOFID"; // 代表
		public static final String STATUSIFACE = "STATUSIFACE"; // 状态已变更
		public static final String HASPARENT = "HASPARENT"; // 有父级吗？
		public static final String RISK = "RISK"; // 风险评估
		public static final String ASSETWARRANTYNOTICE = "ASSETWARRANTYNOTICE"; // 资产担保通知
		public static final String BACKOUTPLAN = "BACKOUTPLAN"; // 撤销计划
		public static final String BACKOUTPLAN_LONGDESCRIPTION = "BACKOUTPLAN_LONGDESCRIPTION"; // “撤销计划”的详细描述
		public static final String INSPECTOR = "INSPECTOR"; // 检验员
		public static final String MEASUREMENTVALUE = "MEASUREMENTVALUE"; // 测量结果
		public static final String JUSTIFYPRIORITY = "JUSTIFYPRIORITY"; // 优先级理由
		public static final String WOLO5 = "WOLO5"; // Wolo5
		public static final String WOLO6 = "WOLO6"; // Wolo6
		public static final String WOLO7 = "WOLO7"; // Wolo7
		public static final String WOLO8 = "WOLO8"; // Wolo8
		public static final String GENFORPOREVISION = "GENFORPOREVISION"; // PO
																			// 修订
		public static final String STOREROOMMTLSTATUS = "STOREROOMMTLSTATUS"; // 库房物料状态
		public static final String FILTERDATE = "FILTERDATE"; // 日期
		public static final String PLUSCFREQUENCY = "PLUSCFREQUENCY"; // 频率字段
		public static final String WORTS3 = "WORTS3"; // Worts3
		public static final String WORTS4 = "WORTS4"; // Worts4
		public static final String SOURCESYSID = "SOURCESYSID"; // 源系统标识
		public static final String FLOWACTION = "FLOWACTION"; // 流程操作
		public static final String WOJO3 = "WOJO3"; // WOJO3
		public static final String CHARGESTORE = "CHARGESTORE"; // 记入库存
		public static final String REPORTDATE = "REPORTDATE"; // 报告日期
		public static final String ISTASK = "ISTASK"; // 是任务
		public static final String WOEQ12 = "WOEQ12"; // Woeq12
		public static final String WOASSET = "WOASSET"; // 资产的工单
		public static final String CHANGEBYPARENT = "CHANGEBYPARENT"; // 按父级变更
		public static final String ROUTESTOPID = "ROUTESTOPID"; // 检修路线站点
		public static final String WARRANTYEXPDATE = "WARRANTYEXPDATE"; // 保修期
		public static final String CONTRACT = "CONTRACT"; // 合同
		public static final String REMARKENTERDATE = "REMARKENTERDATE"; // 备注日期
		public static final String REMARKDESC = "REMARKDESC"; // 备注
		public static final String FR2CODE = "FR2CODE"; // 问题代码
		public static final String PL1 = "PL1"; // 问题代码
		public static final String REMDUR = "REMDUR"; // 剩余时间
		public static final String WORTS5 = "WORTS5"; // Worts5
		public static final String LOCATION = "LOCATION"; // 位置
		public static final String JUSTIFYPRIORITY_LONGDESCRIPTION = "JUSTIFYPRIORITY_LONGDESCRIPTION"; // 说明优先级理由的详细描述
		public static final String ESTATAPPRTOTALCOST = "ESTATAPPRTOTALCOST"; // 估算的物料成本
		public static final String TARGCOMPDATE = "TARGCOMPDATE"; // 目标完成时间
		public static final String FCTASKID = "FCTASKID"; // 任务
		public static final String ACTSERVCOST = "ACTSERVCOST"; // 实际服务成本
		public static final String DISABLED = "DISABLED"; // 禁用
		public static final String ESTATAPPRLABHRS = "ESTATAPPRLABHRS"; // 批准时估算工时数
		public static final String ESTATAPPRLABCOST = "ESTATAPPRLABCOST"; // 批准时估算人工成本
		public static final String WOLO4 = "WOLO4"; // Wolo4
		public static final String WOL4 = "WOL4"; // Wol4
		public static final String ESTSERVCOST = "ESTSERVCOST"; // 估算的服务成本
		public static final String JPASSETS = "JPASSETS"; // 作业计划资产
		public static final String WORKORDERID = "WORKORDERID"; // 工单标识
		public static final String TARGETDESC_LONGDESCRIPTION = "TARGETDESC_LONGDESCRIPTION"; // TARGETDESC
		// 的详细描述
		public static final String ESTATAPPROUTLABHRS = "ESTATAPPROUTLABHRS"; // 外部人工的估算成本
		public static final String PLUSCPHYLOC = "PLUSCPHYLOC"; // 实际位置
		public static final String LAUNCHENTRYNAME = "LAUNCHENTRYNAME"; // 启动条目名称
		public static final String FGFZ = "FGFZ"; // 分管副总人员组
		public static final String ASSETLOCPRIORITY = "ASSETLOCPRIORITY"; // 资产/位置优先级
		public static final String WOLO10 = "WOLO10"; // Wolo10
		public static final String ENVIRONMENT = "ENVIRONMENT"; // 环境
		public static final String PLUSCPHYLOC_LONGDESCRIPTION = "PLUSCPHYLOC_LONGDESCRIPTION"; // 实际位置详细描述
		public static final String CLASSSTRUCTUREID = "CLASSSTRUCTUREID"; // 类结构
		public static final String REPAIRFACILITY = "REPAIRFACILITY"; // 维修设施
		public static final String ESTATAPPRINTLABCOST = "ESTATAPPRINTLABCOST"; // 内部人工的批准成本
		public static final String ESTOUTLABHRS = "ESTOUTLABHRS"; // 外部人工的估算小时数
		public static final String SENDERSYSID = "SENDERSYSID"; // 发送者系统标识
		public static final String FEATURELABEL = "FEATURELABEL"; // 功能部件标签
		public static final String VENDOR = "VENDOR"; // 供应商
		public static final String WOSEQUENCE = "WOSEQUENCE"; // 序号
		public static final String PMDUEDATE = "PMDUEDATE"; // PM 截止日期
		public static final String ESTLABCOST = "ESTLABCOST"; // 估算人工成本
		public static final String HASLINEAR = "HASLINEAR"; // 具有线性
		public static final String ONBEHALFOF = "ONBEHALFOF"; // 代表
		public static final String WHOMISCHANGEFOR_LONGDESCRIPTION = "WHOMISCHANGEFOR_LONGDESCRIPTION"; // 用于指明次变更用于谁的“详细描述”
		public static final String ACTOUTLABHRS = "ACTOUTLABHRS"; // 外部人工的实际小时数
		public static final String WORTS1 = "WORTS1"; // Worts1
		public static final String OBJECTNAME = "OBJECTNAME"; // 对象
		public static final String ORIGRECORDID = "ORIGRECORDID"; // 原始记录
		public static final String DEPT = "DEPT"; // 部门
		public static final String APPTYPE = "APPTYPE"; // 应用类型
		public static final String PROJECTNUM = "PROJECTNUM"; // 项目编号
		public static final String DIRISSUEMTLSTATUS = "DIRISSUEMTLSTATUS"; // 直接发放物料状态
		public static final String USEMBODATA = "USEMBODATA"; // 仅显示与当前工作资产相关联的数据表
		public static final String ACTINTLABHRS = "ACTINTLABHRS"; // 内部人工的实际小时数
		public static final String ACTMATCOST = "ACTMATCOST"; // 实际物料成本
		public static final String ACTLABCOST = "ACTLABCOST"; // 实际人工成本
		public static final String POINTNUM = "POINTNUM"; // 测点
		public static final String WOJO1 = "WOJO1"; // WOJO1
		public static final String PARENTCHGSSTATUS = "PARENTCHGSSTATUS"; // 继承状态变更
		public static final String FNLCONSTRAINT = "FNLCONSTRAINT"; // 最晚完成日期
		public static final String REPORTEDBYID = "REPORTEDBYID"; // 报告人
		public static final String WOEQ8 = "WOEQ8"; // Woeq8
		public static final String DISPLAYTASKID = "DISPLAYTASKID"; // 任务
		public static final String NEWTASKPARENTPMNUM = "NEWTASKPARENTPMNUM"; // 父级
																				// PM
																				// 数目
		public static final String HASFOLLOWUPWORK = "HASFOLLOWUPWORK"; // 是否有后续工作
		public static final String COMMODITYGROUP = "COMMODITYGROUP"; // 服务组
		public static final String CALCSHIFT = "CALCSHIFT"; // Shift
		public static final String WOJP2 = "WOJP2"; // Wojp2
		public static final String FCPROJECTID = "FCPROJECTID"; // 项目标识
		public static final String ASSETNUM = "ASSETNUM"; // 资产
		public static final String SLAAPPLIED = "SLAAPPLIED"; // 应用 SLA
		public static final String DISPLAYWONUM = "DISPLAYWONUM"; // 工单
		public static final String SAFETYPLAN_LOOKUP_LIST_TYPE = "SAFETYPLAN_LOOKUP_LIST_TYPE"; // 安全计划查找列表类型
		public static final String SELECTSLAS_MODE = "SELECTSLAS_MODE"; // 选择方式
		public static final String PLUSCNEXTDATE = "PLUSCNEXTDATE"; // 下一个校准到期日期
		public static final String JOBTASKID = "JOBTASKID"; // 作业任务标识
		public static final String CHANGEBY = "CHANGEBY"; // 变更人
		public static final String FINCNTRLID = "FINCNTRLID"; // 财务控制标识
		public static final String WOEQ13 = "WOEQ13"; // Woeq13
		public static final String PLUSCNEXTDATE_NP = "PLUSCNEXTDATE_NP"; // 下一个校准到期日期
		public static final String PLUSCOVERDUEDATE = "PLUSCOVERDUEDATE"; // 校准过期日期
		public static final String VERIFICATION_LONGDESCRIPTION = "VERIFICATION_LONGDESCRIPTION"; // 验证详细描述
		public static final String FAILDATE = "FAILDATE"; // 故障日期
		public static final String PROJECTDESC = "PROJECTDESC"; // 项目名称
		public static final String BUDGETNUM = "BUDGETNUM"; // 课题号
		public static final String BUDGETTYPE = "BUDGETTYPE"; // 课题类型
		public static final String REQTYPE = "REQTYPE"; // 需求类型
		public static final String LEADERGROUP = "LEADERGROUP"; // 部门负责人组
		public static final String SPLOCATIONS = "SPLOCATIONS"; // 位置
		public static final String CHANGEDATE = "CHANGEDATE"; // 变更日期
		public static final String STATUSDATE = "STATUSDATE"; // 状态日期
		public static final String OUTMATCOST = "OUTMATCOST"; // 外部物料成本
		public static final String SNECONSTRAINT = "SNECONSTRAINT"; // 最早开始日期
		public static final String INCTASKSINSCHED = "INCTASKSINSCHED"; // 在调度中包含任务
		public static final String WOEQ3 = "WOEQ3"; // Woeq3
		public static final String TARGSTARTDATE = "TARGSTARTDATE"; // 目标开始时间
		public static final String WOJO8 = "WOJO8"; // WOJO8
		public static final String GENERATEDFORPO = "GENERATEDFORPO"; // PO
		public static final String ORGID = "ORGID"; // 组织
		public static final String SITEID = "SITEID"; // 地点
		public static final String TASKID = "TASKID"; // 任务
		public static final String ORIGTKID = "ORIGTKID"; // 原始凭单
		public static final String FNCONOFFSET = "FNCONOFFSET"; // 完成约束偏移量
		public static final String ACTFINISH = "ACTFINISH"; // 实际完成时间
		public static final String FEATURE = "FEATURE"; // 功能部件
		public static final String WOJO6 = "WOJO6"; // WOJO6
		public static final String WARRANTYEXIST = "WARRANTYEXIST"; // 有保修
		public static final String CINUM = "CINUM"; // 配置项
		public static final String MEASUREDATE = "MEASUREDATE"; // 测量日期
		public static final String OBSERVATION = "OBSERVATION"; // 观测
		public static final String WOEQ14 = "WOEQ14"; // Woeq14
		public static final String WOJP1 = "WOJP1"; // Wojp1
		public static final String NP_STATUSMEMO = "NP_STATUSMEMO"; // 变更状态备忘录
		public static final String LANGCODE = "LANGCODE"; // 语言代码
		public static final String ACTTOOLCOST = "ACTTOOLCOST"; // 实际工具成本
		public static final String WOEQ10 = "WOEQ10"; // Woeq10
		public static final String ESTATAPPRTOOLCOST = "ESTATAPPRTOOLCOST"; // 估计批准时的工具成本
		public static final String INTERRUPTIBLE = "INTERRUPTIBLE"; // 可中断
		public static final String WOGROUP = "WOGROUP"; // 工单组
		public static final String REASONFORCHANGE_LONGDESCRIPTION = "REASONFORCHANGE_LONGDESCRIPTION"; // 指明变更原因的详细描述
		public static final String WOJO4 = "WOJO4"; // WOJO4
		public static final String WOJO5 = "WOJO5"; // WOJO5
		public static final String ESTATAPPRINTLABHRS = "ESTATAPPRINTLABHRS"; // 内部人工的估算小时数
		public static final String GENFORPOLINEID = "GENFORPOLINEID"; // PO 行标识
		public static final String HASCHILDREN = "HASCHILDREN"; // 有子级
		public static final String FAILURECODE = "FAILURECODE"; // 故障类
		public static final String WORKTYPE = "WORKTYPE"; // 工作类型
		public static final String ESTTOTALCOST = "ESTTOTALCOST"; // 估计成本总计
		public static final String WOCLASS = "WOCLASS"; // 类
		public static final String GLACCOUNT = "GLACCOUNT"; // GL 科目
		public static final String TARGETDESC = "TARGETDESC"; // 目标描述
		public static final String LASTCOPYLINKDATE = "LASTCOPYLINKDATE"; // 上次复制文档链接的时间
		public static final String REPFACSITEID = "REPFACSITEID"; // 维修设施地点
		public static final String WOJP3 = "WOJP3"; // Wojp3
		public static final String WOISSWAP = "WOISSWAP"; // 这是资产交换吗
		public static final String ENVIRONMENT_LONGDESCRIPTION = "ENVIRONMENT_LONGDESCRIPTION"; // 环境详细描述
		public static final String JPTASK = "JPTASK"; // 任务
		public static final String WOEQ7 = "WOEQ7"; // Woeq7
		public static final String ASSETUSER = "WOEQ7"; // 用户
		public static final String FLOWACTIONASSIST = "FLOWACTIONASSIST"; // 流程操作辅助
		public static final String WOLABLNK = "WOLABLNK"; // 人工
		public static final String SUSPENDFLOW = "SUSPENDFLOW"; // 中止流程控制
		public static final String WORTS2 = "WORTS2"; // Worts2
		public static final String PROBLEMCODE = "PROBLEMCODE"; // 问题代码
		public static final String CALENDAR = "CALENDAR"; // 日历
		public static final String LEAD = "LEAD"; // 负责人
		public static final String SPASSETS = "SPASSETS"; // 资产
		public static final String ASSETFILTERBY = "ASSETFILTERBY"; // 过滤条件
		public static final String AVAILSTATUSDATE = "AVAILSTATUSDATE"; // 上次更新的物料状态
		public static final String CALCPRIORITY = "CALCPRIORITY"; // 计算的优先级
		public static final String WONUM = "WONUM"; // 工单
		public static final String OUTTOOLCOST = "OUTTOOLCOST"; // 外部工具成本
		public static final String JPCLASS = "JPCLASS"; // 工单类
		public static final String JPINCLUDECLASSLESS = "JPINCLUDECLASSLESS"; // 显示未定义类的作业计划
		public static final String REMARKDESC_LONGDESCRIPTION = "REMARKDESC_LONGDESCRIPTION"; // 详细描述
		public static final String ESTATAPPRSERVCOST = "ESTATAPPRSERVCOST"; // 批准时估计服务成本
		public static final String RESPONDBY = "RESPONDBY"; // 响应人
		public static final String ESTATAPPRMATCOST = "ESTATAPPRMATCOST"; // 估计批准时的物料成本
		public static final String WOLO9 = "WOLO9"; // Wolo9
		public static final String WHOMISCHANGEFOR = "WHOMISCHANGEFOR"; // 为谁进行此变更？
		public static final String WOJP5 = "WOJP5"; // Wojp5
		public static final String WOL1 = "WOL1"; // Wol1
		public static final String WOJO2 = "WOJO2"; // WOJO2
		public static final String SUPERVISOR = "SUPERVISOR"; // 主管人
		public static final String WORKLOCATION = "WORKLOCATION"; // 工作位置
		public static final String EXTERNALREFID = "EXTERNALREFID"; // 外部引用标识
		public static final String CALCCALENDAR = "CALCCALENDAR"; // Calendar
		public static final String STCONOFFSET = "STCONOFFSET"; // 开始约束偏移量
		public static final String ASSETRECONRSTKT = "ASSETRECONRSTKT"; // 凭单资产调整结果
		public static final String REPORTEDBY = "REPORTEDBY"; // 报告人
		public static final String LOCWARRANTYNOTICE = "LOCWARRANTYNOTICE"; // 位置担保通知
		public static final String WOLO1 = "WOLO1"; // Wolo1
		public static final String WOLO2 = "WOLO2"; // Wolo2
		public static final String WOLO3 = "WOLO3"; // Wolo3
		public static final String WOACCEPTSCHARGES = "WOACCEPTSCHARGES"; // 接受费用
		public static final String WOEQ6 = "WOEQ6"; // Woeq6
		public static final String ORIGWOID = "ORIGWOID"; // 原始工单
		public static final String DUPFLAG = "DUPFLAG"; // 重复标志
		public static final String JPNUM = "JPNUM"; // 作业计划
		public static final String PMEXTDATE = "PMEXTDATE"; // PM 延长日期
		public static final String ESTINTLABHRS = "ESTINTLABHRS"; // 内部人工的估算小时数
		public static final String PRNUM = "PRNUM"; // 关联采购计划编码
		public static final String PONUM = "PONUM"; // 关联验收单编号
		public static final String WOJP4 = "WOJP4"; // Wojp4
		public static final String ONBEHALFOFNAME = "ONBEHALFOFNAME"; // 名称
		public static final String WOJO7 = "WOJO7"; // WOJO7

	}

}
