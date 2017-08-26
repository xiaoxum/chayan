package com.vkl.ckts.cksy.servacpt.entity;

import com.vkl.ckts.common.entity.ResourceEntity;

public class ResourceDto extends ResourceEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	
	private String srln;

    private String repicUrl;

    private String resId;
    
    
    private String sysc;
    
    
	public String getSrln() {
		return srln;
	}




	public void setSrln(String srln) {
		this.srln = srln;
	}




	public String getRepicUrl() {
		return repicUrl;
	}




	public void setRepicUrl(String repicUrl) {
		this.repicUrl = repicUrl;
	}




	



	public String getResId() {
		return resId;
	}




	public void setResId(String resId) {
		this.resId = resId;
	}




	public String getSysc() {
		return sysc;
	}




	public void setSysc(String sysc) {
		this.sysc = sysc;
	}
	
}
