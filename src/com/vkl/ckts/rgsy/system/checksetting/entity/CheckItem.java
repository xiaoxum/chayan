package com.vkl.ckts.rgsy.system.checksetting.entity;

import java.util.List;

import com.vkl.ckts.common.entity.OperVehProEntity;

/**
 * 查验项目
 * @author xujunhua
 * @date 2017年3月15日
 * @ClassName: CheckItem
 */
public class CheckItem extends OperVehProEntity{
	/**
	 * 查验项编号
	 */
	private String operVeh;
	private String num;		// 查验项数目
	private String loginName;	// 创建人
	private String typeName;	// 业务类型
	private String syxz;		// 车辆使用性质
	private String parentName;	// 车辆类型名称
	private List<CheckItem> list;
	
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getSyxz() {
		return syxz;
	}

	public void setSyxz(String syxz) {
		this.syxz = syxz;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}



	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getOperVeh() {
		return operVeh;
	}

	public void setOperVeh(String operVeh) {
		this.operVeh = operVeh;
	}

	public List<CheckItem> getList() {
		return list;
	}

	public void setList(List<CheckItem> list) {
		this.list = list;
	}
	
	
}
