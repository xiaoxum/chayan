package com.vkl.ckts.cksy.photoinfo.entity;

import java.util.List;

import com.vkl.ckts.common.entity.PicRecordEntity;





public class PhotoDto extends PicRecordEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String clsbdh;
	
	
	private String hphm;
	
	private String cllxName;
	
	
	private String typeName;
	
	private String sfdy;

	
	private String picName;

	
	private String picTimeS;

	
	private String startTime;
	
	private String endTime;
	
	private List<String> picIds;
	
	
	private String organCode;
	
	public List<String> getPicIds() {
		return picIds;
	}




	public void setPicIds(List<String> picIds) {
		this.picIds = picIds;
	}




	private String operType;
	public String getClsbdh() {
		return clsbdh;
	}

	
	

	public void setClsbdh(String clsbdh) {
		this.clsbdh = clsbdh;
	}


	public String getHphm() {
		return hphm;
	}


	public void setHphm(String hphm) {
		this.hphm = hphm;
	}


	public String getCllxName() {
		return cllxName;
	}


	public void setCllxName(String cllxName) {
		this.cllxName = cllxName;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public String getSfdy() {
		return sfdy;
	}


	public void setSfdy(String sfdy) {
		this.sfdy = sfdy;
	}


	public String getPicName() {
		return picName;
	}


	public void setPicName(String picName) {
		this.picName = picName;
	}


	public String getPicTimeS() {
		return picTimeS;
	}


	public void setPicTimeS(String picTimeS) {
		this.picTimeS = picTimeS;
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




	public String getOperType() {
		return operType;
	}




	public String getOrganCode() {
		return organCode;
	}




	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}




	public void setOperType(String operType) {
		this.operType = operType;
	}





}
