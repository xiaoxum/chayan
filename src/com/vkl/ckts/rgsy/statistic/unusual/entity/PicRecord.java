package com.vkl.ckts.rgsy.statistic.unusual.entity;

import com.vkl.ckts.common.entity.PicRecordEntity;
/**
 * 拍照记录entity
 * @author xujunhua
 * @date 2017年4月14日
 * @ClassName: PicRecord
 */
public class PicRecord extends PicRecordEntity{

	/**
	 * 照片名称
	 */
	private String picName;

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}
	
}
