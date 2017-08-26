package com.vkl.ckts.cksy.servacpt.entity;

import com.vkl.ckts.common.entity.VehInfoEntity;

/**
 * @合格证Dto
 * @author Administrator
 *
 */
public class VehInfoDto extends VehInfoEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7219610557590599852L;
	/**
	 * 防止属性冲突用这个字段接受车辆型号内容
	 */
	private String copyClxh;
	/**
	 * 防止属性冲突用这个字段接受车辆识别代号内容
	 */
	private String copyClsbdh;
	/**
	 * 车身颜色1
	 */
	private String csys1;
	/**
	 * 车身颜色2
	 */
	private String csys2;
	/**
	 * 车身颜色3
	 */
	private String csys3;
	/**
	 * 车牌号行政别名
	 */
	private String xzqh;
	/**
	 * 车牌号首字母
	 */
	private String pinyin;
	
	
	
	public String getCsys1() {
		return csys1;
	}
	public void setCsys1(String csys1) {
		this.csys1 = csys1;
	}
	public String getCsys2() {
		return csys2;
	}
	public void setCsys2(String csys2) {
		this.csys2 = csys2;
	}
	public String getCopyClxh() {
		return copyClxh;
	}
	public void setCopyClxh(String copyClxh) {
		this.copyClxh = copyClxh;
	}
	public String getCopyClsbdh() {
		return copyClsbdh;
	}
	public void setCopyClsbdh(String copyClsbdh) {
		this.copyClsbdh = copyClsbdh;
	}
	public String getCsys3() {
		return csys3;
	}
	public void setCsys3(String csys3) {
		this.csys3 = csys3;
	}
	public String getXzqh() {
		return xzqh;
	}
	public void setXzqh(String xzqh) {
		this.xzqh = xzqh;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	

}
