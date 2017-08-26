package com.vkl.ckts.rgsy.vehiclereview.entity;

import com.vkl.ckts.common.entity.ProjectRecordEntity;
/**
 * 查验拍照项目表
 * @author Administrator
 *
 */
public class ProjectRecordDto extends ProjectRecordEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8729105226250095761L;
	/**
	 * 项目编号
	 */
	private String proId;
	/**
	 * 查验标识
	 */
	private String ckFlag;
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	public String getCkFlag() {
		return ckFlag;
	}
	public void setCkFlag(String ckFlag) {
		this.ckFlag = ckFlag;
	}
	

}
