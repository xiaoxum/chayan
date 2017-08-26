package com.vkl.ckts.cksy.base.dto;

import java.io.Serializable;

public class ProjectRecordDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String ckflag;
	
	private String proId;

	public String getCkflag() {
		return ckflag;
	}

	public void setCkflag(String ckflag) {
		this.ckflag = ckflag;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}
	
	
}
