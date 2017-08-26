package com.vkl.ckts.rgsy.system.nvrsetting.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.vkl.ckts.common.entity.IdEntity;


/**
 * 查验线视频 配置
 * 
 * @author X260
 *
 */
public class ChkptNvrConfigDto extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	/**
	 * 查验区备案号
	 */
	private String organCode;
	/**
	 * 查验线代号
	 */
	private String checkLineId;
	
	/**
	 * 查验线名称
	 */
	private String checkLineName;
	/**
	 * nvr编号
	 */
	private String nvrIp;
	
	/**
	 * nvr端口
	 */
	private String nvrPort;
	/**
	 * nvr 用户名称
	 */
	private String nvrUser;
	/**
	 * nvr密码
	 */
	private String nvrPwd;
	
	
	private String nvrChannel;

	
	private String chkptName;
	
	/**
	 * 服务端口
	 */
	private String servPort;
	/**
	 * 视频下载通道号
	 */
	private String videoDownLoadChannel;
	
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
}
