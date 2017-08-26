package com.vkl.ckts.rgsy.vehiclereview.entity;

import com.vkl.ckts.common.entity.BlacklistEntity;

/**
 * 车辆黑名单
 * @author xujunhua
 * @date 2017年4月7日
 * @ClassName: VehBlackName
 */
public class VehBlackName extends BlacklistEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7546459070834994706L;
	/**
	 * 车辆类型
	 */
	private String parentName;
	/**
	 * 业务类型
	 */
	private String operName;
	/**
	 * 加入黑名单时间start
	 */
	private String start;
	/**
	 * 加入黑名单时间 end
	 */
	private String end;
	/**
	 * 查验点
	 */
	private String organName;
	/**
	 * 不合格提示语
	 */
	private String unqualAnnc;
	/**
	 * 查验时间
	 */
	private String chektTime;
	/**
	 * 查验员
	 */
	private String viewer;
	/**
	 * 不合格项
	 */
	private String proName;
	/**
	 * 重点查验项
	 */
	private String icp;
	/**
	 * 
	 */
	private String implCkPros;
	public String getImplCkPros() {
		return implCkPros;
	}
	public void setImplCkPros(String implCkPros) {
		this.implCkPros = implCkPros;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getOperName() {
		return operName;
	}
	public void setOperName(String operName) {
		this.operName = operName;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getUnqualAnnc() {
		return unqualAnnc;
	}
	public void setUnqualAnnc(String unqualAnnc) {
		this.unqualAnnc = unqualAnnc;
	}
	public String getChektTime() {
		return chektTime;
	}
	public void setChektTime(String chektTime) {
		this.chektTime = chektTime;
	}
	public String getViewer() {
		return viewer;
	}
	public void setViewer(String viewer) {
		this.viewer = viewer;
	}
	public String getIcp() {
		return icp;
	}
	public void setIcp(String icp) {
		this.icp = icp;
	}
	
	
}
