package com.vkl.ckts.rgsy.statistic.count.service;

import java.util.List;

import com.vkl.ckts.common.entity.ChkptFileEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.rgsy.statistic.count.dao.ICheckCountDao;
import com.vkl.ckts.rgsy.statistic.count.entity.CheckCount;

public interface ICheckCountService extends IService<ICheckCountDao, CheckCount, String> {
	/**
	 * 查询所有查验区
	 */
	public List<ChkptFileEntity> allChkpt(); 
	/**
	 * 查询查验信息的所有信息
	 */
	public List<CheckCount> countList(Page<CheckCount> page);
	/*
	 *报废信息统计
	 *
	 * */
	public List<CheckCount> findBfxx(Page<CheckCount> page);
	public Integer findBfxxCount(CheckCount checkcount);
	/*
	 *报废信息统计
	 *@按时间段统计
	 * */
	public List<CheckCount> findBfxxByTime(Page<CheckCount> page);
	public Integer findBfxxByTimeCount(CheckCount checkcount);
}
