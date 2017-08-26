package com.vkl.ckts.rgsy.record.entity;

import com.vkl.ckts.common.entity.ViewerFileEntity;

public class ViewerFileDto extends ViewerFileEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 权限标识
	 */
	private String permissionFlag;

	public String getPermissionFlag() {
		return permissionFlag;
	}

	public void setPermissionFlag(String permissionFlag) {
		this.permissionFlag = permissionFlag;
	}

}
