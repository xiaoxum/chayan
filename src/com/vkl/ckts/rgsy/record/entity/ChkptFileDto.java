package com.vkl.ckts.rgsy.record.entity;

import com.vkl.ckts.common.entity.ChkptFileEntity;
/**
 * 查验区备案dto 
 * 
 * @author X260
 *
 */
public class ChkptFileDto extends ChkptFileEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**
	 * 是否自动审核
	 * 
	 */
	private String isAutoAudit;

    private String videoIp;
    
    private String videoPort;

	public String getIsAutoAudit() {
		return isAutoAudit;
	}



	public void setIsAutoAudit(String isAutoAudit) {
		this.isAutoAudit = isAutoAudit;
	}



	public String getVideoIp() {
		return videoIp;
	}



	public void setVideoIp(String videoIp) {
		this.videoIp = videoIp;
	}



	public String getVideoPort() {
		return videoPort;
	}



	public void setVideoPort(String videoPort) {
		this.videoPort = videoPort;
	}

}
