package com.vkl.ckts.rgsy.spotcheck.entity;




import com.vkl.ckts.common.entity.CkInfoEntity;
/**
 * 查验抽查实体类
 * 
 * @author jiajun
 *
 */

public class CkInfoDto extends CkInfoEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7569992971518729620L;
	/**
	 * 业务类型
	 */
	private String typeName;
	/**
	 * 查验区名称
	 */
	private String organName;
	/**
	 * 人工审核人姓名
	 */
	private String pAuditerName;
	
	/**
	 * 业务受理人姓名
	 */
	private String operaerName;
	
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	/**
	 * 抽查比例
	 */
	private String proportion;
	
	/**
	 * 业务类型id
	 */
	private String parentId;
	
	/**
	 * 车辆识别代号
	 */
	private String clsbdh;
	
	
	
	public String getClsbdh() {
		return clsbdh;
	}
	public void setClsbdh(String clsbdh) {
		this.clsbdh = clsbdh;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
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
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public String getpAuditerName() {
		return pAuditerName;
	}
	public void setpAuditerName(String pAuditerName) {
		this.pAuditerName = pAuditerName;
	}
	public String getProportion() {
		return proportion;
	}
	public void setProportion(String proportion) {
		this.proportion = proportion;
	}
	public String getOperaerName() {
		return operaerName;
	}
	public void setOperaerName(String operaerName) {
		this.operaerName = operaerName;
	}
	
	
	
}
