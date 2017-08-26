package com.vkl.ckts.cksy.vehicleinformation.entity;

import java.util.Date;

import com.vkl.ckts.common.entity.PicRecordEntity;
import com.vkl.ckts.common.utils.DateUtil;

/**
 * 查验拍照项目照片记录表
 * @author Administrator
 *
 */
public class PicRecordDto extends PicRecordEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 查验项名称
	 */
	private String proName;
	/**
	 * 是否打印
	 */
	private String sfdy;

	
	private String picUrlPa;
	
	
	@Override
	public String getPicUrl() {
		// TODO Auto-generated method stub
		return super.getPicUrl();
	}

	@Override
	public void setPicUrl(String picUrl) {
		// TODO Auto-generated method stub
		super.setPicUrl(picUrl);
		this.picUrlPa=picUrl+"?time"+DateUtil.format(new Date(), DateUtil.TIMESTAMP_NUMBER);
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getSfdy() {
		return sfdy;
	}

	public void setSfdy(String sfdy) {
		this.sfdy = sfdy;
	}

	public String getPicUrlPa() {
		return picUrlPa;
	}

	public void setPicUrlPa(String picUrlPa) {
		this.picUrlPa = picUrlPa;
	}

}
