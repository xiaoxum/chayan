package com.vkl.ckts.rgsy.statistic.unusual.entity;

import java.util.Date;

import com.vkl.ckts.common.entity.ExeInfoEntity;

public class NewExceptInfo extends ExeInfoEntity{
	//String格式时间
	private String date;
	/**
	 * 异常名称
	 */
	private String exceptName;
	/**
	 * 照片名称
	 */
	private String picName;
	/**
	 * 车辆类型名称
	 */
	private String cllxName;
	/**
	 * 查验员名称
	 */
	private String viewName;
	/**
	 * 查验区名称
	 */
	private String orginName;
	/**
	 * 业务类型
	 */
	private String operTypeName;
	/**
	 * 开始时间
	 */
	private Date start;
	/**
	 * 结束时间
	 */
	private Date end;
	
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	
	
	public String getOperTypeName() {
		return operTypeName;
	}
	public void setOperTypeName(String operTypeName) {
		this.operTypeName = operTypeName;
	}
	public String getOrginName() {
		return orginName;
	}
	public void setOrginName(String orginName) {
		this.orginName = orginName;
	}
	public String getViewName() {
		return viewName;
	}
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getExceptName() {
		return exceptName;
	}
	public void setExceptName(String exceptName) {
		this.exceptName = exceptName;
	}
	public String getCllxName() {
		return cllxName;
	}
	public void setCllxName(String cllxName) {
		this.cllxName = cllxName;
	}
	
}
