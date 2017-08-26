package com.vkl.ckts.cksy.attractfunction.entity;

import java.util.Date;

import com.vkl.ckts.common.entity.IdEntity;


/**
 * 附加功能   照片拍摄  
 * 
 * @author X260
 *
 */
public class AttactPhoto extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String  tackeOrganCode;
	
	
	private String photoUrl;
	
	
	private String photoName;
	
	
	private String clsbdh;
	
	
	private Date  tackeDate;

    private String startTime;
    
    private String endTime;
    


	public String getPhotoUrl() {
		return photoUrl;
	}


	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}


	public String getPhotoName() {
		return photoName;
	}


	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}


	public String getClsbdh() {
		return clsbdh;
	}


	public void setClsbdh(String clsbdh) {
		this.clsbdh = clsbdh;
	}


	public Date getTackeDate() {
		return tackeDate;
	}


	public void setTackeDate(Date tackeDate) {
		this.tackeDate = tackeDate;
	}


	public String getTackeOrganCode() {
		return tackeOrganCode;
	}


	public void setTackeOrganCode(String tackeOrganCode) {
		this.tackeOrganCode = tackeOrganCode;
	}


	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
	
	
	
	
	
	
}
