package com.vkl.ckts.rgsy.system.photosetting.entity;

import java.util.List;

import com.vkl.ckts.common.entity.OperPicProjectEntity;
import com.vkl.ckts.common.entity.PicProjectEntity;

public class OperPicProject extends OperPicProjectEntity{
	/**
	 * 使用性质名称
	 */
	private String syxzName;
	/**
	 * 业务类型名称
	 */
	private String typeName;
	/**
	 * 车辆类型父类名称
	 */
	private String parentName;
	/**
	 * 拍照类型
	 */
	private List<PicProjectEntity> ppe;
	/**
	 * 拍照项数目
	 */
	private String isMust;
	/**
	 * 添加的拍照项
	 */
	private String pic;
	public String getSyxzName() {
		return syxzName;
	}
	public void setSyxzName(String syxzName) {
		this.syxzName = syxzName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getIsMust() {
		return isMust;
	}
	public void setIsMust(String isMust) {
		this.isMust = isMust;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public List<PicProjectEntity> getPpe() {
		return ppe;
	}
	public void setPpe(List<PicProjectEntity> ppe) {
		this.ppe = ppe;
	}
	
	
	
}
