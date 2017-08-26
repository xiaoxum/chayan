package com.vkl.ckts.rgsy.statistic.count.entity;


import java.sql.Date;

import com.vkl.ckts.common.entity.CkInfoEntity;

public class CheckCount extends CkInfoEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 查验员姓名
	 */
	private String viewer;
	/**
	 * 查验员编号
	 */
	private String viewId;
	/**
	 * 查验区名称
	 */
	private String organName;
	/**
	 * 外廓测量仪编号
	 */
	private String overall;
	/**
	 * 车辆类型名称
	 */
	private String parentName;
	/**
	 * 业务类型名称
	 */
	private String typeName="-----";
	/**
	 * 查验时间String
	 */
	private String date;
	/**
	 * 查询条件开始时间
	 */
	private String start;
	/**
	 * 查询条件结束时间
	 */
	private String end;
	/**
	 * 查验总记录数
	 */
	private Integer aCount;
	
	private Integer hgCount;
	/**
	 * 查验合格总记录数
	 */
	private Integer cCount;
	/**
	 * 合格率
	 */
	private String rate;

	/*
	 * 创建时间
	 * 
	 * */
    private Date crtTime;
    
    /*
     * 分组日期
     * 
     * */
    private Date day;
    /*
     * 
     * 显示时间
     * 
     * */
    private String showTime;
    /*
     *使用性质名称 
     * */
    private String syxzName="-----";
    
    private String cllxName;
    
    private String pSyxz;
    /**
     * 合格率
     */
    private float hgVg;
    
    private String time;
    
    
    
	public String getOverall() {
		return overall;
	}
	public void setOverall(String overall) {
		this.overall = overall;
	}
	public String getViewer() {
		return viewer;
	}
	public void setViewer(String viewer) {
		this.viewer = viewer;
	}
	public String getViewId() {
		return viewId;
	}
	public void setViewId(String viewId) {
		this.viewId = viewId;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getaCount() {
		return aCount;
	}
	public void setaCount(Integer aCount) {
		this.aCount = aCount;
	}
	public Integer getcCount() {
		return cCount;
	}
	public void setcCount(Integer cCount) {
		this.cCount = cCount;
		try {
			if (this.hgCount!=null&&this.cCount!=null) {
				this.hgVg=(float) (Math.round(((float) this.hgCount / (float) this.cCount) * 10000))/100 ;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
//	public Date getCrtTime() {
//		return crtTime;
//	}
//	public void setCrtTime(Date crtTime) {
//		this.crtTime = crtTime;
//	}
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	public Date getCrtTime() {
		return crtTime;
	}
	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	public String getSyxzName() {
		return syxzName;
	}
	public void setSyxzName(String syxzName) {
		this.syxzName = syxzName;
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
	public Integer getHgCount() {
		return hgCount;
	}
	public void setHgCount(Integer hgCount) {
		this.hgCount = hgCount;
		try {
			if (this.cCount!=null&&this.hgCount!=null) {
				this.hgVg=(float) (Math.round(((float) this.hgCount / (float) this.cCount) * 10000))/100 ;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public float getHgVg() {
//		float hgv=0;
		
		return hgVg;
	}
	public void setHgVg(float hgVg) {
		this.hgVg = hgVg;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}



	
}
