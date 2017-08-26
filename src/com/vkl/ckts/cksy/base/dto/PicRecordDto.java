package com.vkl.ckts.cksy.base.dto;

import java.io.Serializable;

public class PicRecordDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String picId;
	
	private String picUrl;
    /**
     * 打印数量 
     */
	private Integer printNum;
	
	public String getPicId() {
		return picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Integer getPrintNum() {
		return printNum;
	}

	public void setPrintNum(Integer printNum) {
		this.printNum = printNum;
	}
	
	
	
}
