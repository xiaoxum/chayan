package com.vkl.ckts.pub.dto;

import com.vkl.ckts.common.entity.UserEntity;

public class UserAdmin extends UserEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 角色
	 */
	private String uRole;
	private String idd;
	
	private String sfgly;
	
	/**
	 * 用户机构
	 */
	private String deptName;

	public String getuRole() {
		return uRole;
	}

	public void setuRole(String uRole) {
		this.uRole = uRole;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getIdd() {
		return idd;
	}

	public void setIdd(String idd) {
		this.idd = idd;
	}

	public String getSfgly() {
		return sfgly;
	}

	public void setSfgly(String sfgly) {
		this.sfgly = sfgly;
	}
	
}
