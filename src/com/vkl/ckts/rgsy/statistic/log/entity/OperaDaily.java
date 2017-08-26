package com.vkl.ckts.rgsy.statistic.log.entity;

import java.util.Date;

import com.vkl.ckts.common.entity.OperLogEntity;

public class OperaDaily extends OperLogEntity{
	/**
	 * 操作人名称
	 */
	private String operName;
	/**
	 * 用户名称
	 */
	private String userName;
	
	/**
	 * String时间
	 */
	private String date;
	
	/**
	 * 开始时间
	 */
	private Date start;
	/**
	 * 结束时间
	 */
	private Date end;
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

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

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
