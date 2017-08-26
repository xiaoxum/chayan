package com.vkl.ckts.rgsy.system.photosetting.entity;


import java.util.List;

import com.vkl.ckts.common.entity.OperResEntity;

/**
 * 高拍项设置Entity
 * @author xujunhua
 * @date 2017年3月29日
 * @ClassName: OperRes
 */
public class OperRes extends OperResEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 影像项名称
	 */
	private String resName;

	/**
	 * 业务类型名称
	 */
	private String operName;
	/**
	 * 影像化项字符串
	 */
	private String resStr;
	/**
	 * 影像化集合
	 */
	private List<OperRes> picset;
	
	
	
	public List<OperRes> getPicset() {
		return picset;
	}
	public void setPicset(List<OperRes> picset) {
		this.picset = picset;
	}
	public String getResStr() {
		return resStr;
	}
	public void setResStr(String resStr) {
		this.resStr = resStr;
	}
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getOperName() {
		return operName;
	}
	public void setOperName(String operName) {
		this.operName = operName;
	}
	
	
}
