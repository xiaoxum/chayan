package com.vkl.ckts.cksy.vehicleinformation.entity;

import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.vkl.ckts.common.entity.IdEntity;


/**
 * 查验线视频 配置
 * 
 * @author X260
 *
 */
@XStreamAlias("chkptNvrConfigDto") 
public class ChkptNvrConfigDto extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	/**
	 * 查验区备案号
	 */
	@XStreamAlias("organCode") 
	private String organCode;
	/**
	 * 查验线代号
	 */
	@XStreamAlias("checkLineId") 
	private String checkLineId;
	
	/**
	 * 查验线名称
	 */
	@XStreamAlias("checkLineName") 
	private String checkLineName;
	/**
	 * nvr编号
	 */
	@XStreamAlias("nvrIp") 
	private String nvrIp;
	
	/**
	 * nvr端口
	 */
	@XStreamAlias("nvrPort") 
	private String nvrPort;
	/**
	 * nvr 用户名称
	 */
	@XStreamAlias("nvrUser") 
	private String nvrUser;
	/**
	 * nvr密码
	 */
	@XStreamAlias("nvrPwd") 
	private String nvrPwd;
	
	@XStreamAlias("nvrChannel") 
	private String nvrChannel;

	@XStreamAlias("chkptName") 
	private String chkptName;
	
	
	/**
	 * 视频下载通道号
	 */
	@XStreamAlias("videoDownLoadChannel") 
	private String videoDownLoadChannel;
	/**
	 * 服务端口
	 */
	@XStreamAlias("servPort") 
	private String servPort;
	
	@XStreamAlias("startTime") 
	private Date startTime;
	@XStreamAlias("endTime") 
	
	private Date endTime;
	
	public String getOrganCode() {
		return organCode;
	}

	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}

	public String getCheckLineId() {
		return checkLineId;
	}

	public void setCheckLineId(String checkLineId) {
		this.checkLineId = checkLineId;
	}

	public String getCheckLineName() {
		return checkLineName;
	}

	public void setCheckLineName(String checkLineName) {
		this.checkLineName = checkLineName;
	}

	
	public String getNvrPort() {
		return nvrPort;
	}

	public void setNvrPort(String nvrPort) {
		this.nvrPort = nvrPort;
	}

	public String getNvrUser() {
		return nvrUser;
	}

	public void setNvrUser(String nvrUser) {
		this.nvrUser = nvrUser;
	}

	public String getNvrPwd() {
		return nvrPwd;
	}

	public String getNvrIp() {
		return nvrIp;
	}

	public void setNvrIp(String nvrIp) {
		this.nvrIp = nvrIp;
	}

	public void setNvrPwd(String nvrPwd) {
		this.nvrPwd = nvrPwd;
	}

	public String getNvrChannel() {
		return nvrChannel;
	}

	public void setNvrChannel(String nvrChannel) {
		this.nvrChannel = nvrChannel;
	}

	public String getChkptName() {
		return chkptName;
	}

	public void setChkptName(String chkptName) {
		this.chkptName = chkptName;
	}

	

	public String getServPort() {
		return servPort;
	}

	public void setServPort(String servPort) {
		this.servPort = servPort;
	}

	public String getVideoDownLoadChannel() {
		return videoDownLoadChannel;
	}

	public void setVideoDownLoadChannel(String videoDownLoadChannel) {
		this.videoDownLoadChannel = videoDownLoadChannel;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
