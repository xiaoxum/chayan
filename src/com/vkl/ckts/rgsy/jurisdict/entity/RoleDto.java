package com.vkl.ckts.rgsy.jurisdict.entity;

import com.vkl.ckts.common.entity.RoleEntity;


public class RoleDto extends RoleEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 创建部门
	 */
	private String deptId;

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

}
