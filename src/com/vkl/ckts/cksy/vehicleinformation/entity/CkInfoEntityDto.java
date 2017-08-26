package com.vkl.ckts.cksy.vehicleinformation.entity;



import java.util.Date;

import com.vkl.ckts.common.entity.CkInfoEntity;
import com.vkl.ckts.common.utils.Base64Utils;
/**
 * 用来接收查验审核信息
 * 
 * @author Administrator
 *
 */
public class CkInfoEntityDto extends CkInfoEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7957891192199358596L;
	/**
	 * 业务类型
	 */
	private String typeName;
	/**
	 * 查验区名称
	 */
	private String organName;
	/**
	 * 代理人姓名
	 */
	private String userName;
	/**
	 * 所属人姓名
	 */
	private String owName;
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	/**
	 * 业务类型id
	 */
	private String parentId;
	/**
	 * 车型种类
	 */
	private String ldictionaryAbel;
	/**
	 * 车辆品牌
	 */
	private String clpp;
	/**
	 * 查验员
	 */
	private String cker;
	/**
	 * 车辆型号
	 */
	private String clxh;
	/**
	 * 车身颜色
	 */
	private String csys;

	
	private String hdzk;
    private String auditerName;
	
	private String auditerNameBase;
	
	
	private String hpzlName;
	
	private String cllxName;
	private String pSyxz;
	
	private String syxzName;
	
	
	
	private String videoId;
	
	
	private String videoPort;
	
	private Date pdaPzStartTime;
	private Date pdaPzEndTime;
	private String cyxbh;
		
	public String getLdictionaryAbel() {
		return ldictionaryAbel;
	}
	public void setLdictionaryAbel(String ldictionaryAbel) {
		this.ldictionaryAbel = ldictionaryAbel;
	}
	public String getClpp() {
		return clpp;
	}
	public void setClpp(String clpp) {
		this.clpp = clpp;
	}
	public String getCker() {
		return cker;
	}
	public void setCker(String cker) {
		this.cker = cker;
	}
	public String getClxh() {
		return clxh;
	}
	public void setClxh(String clxh) {
		this.clxh = clxh;
	}
	public String getCsys() {
		return csys;
	}
	public void setCsys(String csys) {
		this.csys = csys;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOwName() {
		return owName;
	}
	public void setOwName(String owName) {
		this.owName = owName;
	}
	
	public String getAuditerName() {
		return auditerName;
	}
	public void setAuditerName(String auditerName) {
		this.auditerName = auditerName;
		this.auditerNameBase=Base64Utils.decode(auditerName);
	}
	public String getAuditerNameBase() {
		return auditerNameBase;
	}
	public void setAuditerNameBase(String auditerNameBase) {
		this.auditerNameBase = auditerNameBase;
	}
	public String getCllxName() {
		return cllxName;
	}
	public void setCllxName(String cllxName) {
		this.cllxName = cllxName;
	}
	public String getpSyxz() {
		return pSyxz;
	}
	public void setpSyxz(String pSyxz) {
		this.pSyxz = pSyxz;
	}
	public String getHpzlName() {
		return hpzlName;
	}
	public void setHpzlName(String hpzlName) {
		this.hpzlName = hpzlName;
	}
	public String getHdzk() {
		return hdzk;
	}
	public void setHdzk(String hdzk) {
		this.hdzk = hdzk;
	}
	public String getSyxzName() {
		return syxzName;
	}
	public void setSyxzName(String syxzName) {
		this.syxzName = syxzName;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getVideoPort() {
		return videoPort;
	}
	public void setVideoPort(String videoPort) {
		this.videoPort = videoPort;
	}
	public Date getPdaPzStartTime() {
		return pdaPzStartTime;
	}
	public void setPdaPzStartTime(Date pdaPzStartTime) {
		this.pdaPzStartTime = pdaPzStartTime;
	}
	public Date getPdaPzEndTime() {
		return pdaPzEndTime;
	}
	public void setPdaPzEndTime(Date pdaPzEndTime) {
		this.pdaPzEndTime = pdaPzEndTime;
	}
	public String getCyxbh() {
		return cyxbh;
	}
	public void setCyxbh(String cyxbh) {
		this.cyxbh = cyxbh;
	}
}
