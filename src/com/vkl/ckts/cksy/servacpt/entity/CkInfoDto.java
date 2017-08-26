package com.vkl.ckts.cksy.servacpt.entity;

import javax.xml.bind.annotation.XmlRootElement;

import com.vkl.ckts.common.entity.CkInfoEntity;

/**
 * 打印受理信息实体类
 * @author jiajun
 * @version 1.0
 */
@XmlRootElement
public class CkInfoDto  extends CkInfoEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5104644378174995299L;

	/**
	 * 车主姓名
	 */
	String  name;
	
	/**
	 * 车主电话
	 */
	String  phone;
	
	
	
	/**
	 * 车辆品牌型号
	 * 
	 */
	String ppxh;
	


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getPpxh() {
		return ppxh;
	}

	public void setPpxh(String ppxh) {
		this.ppxh = ppxh;
	}

	
    
}
