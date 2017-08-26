package com.vkl.ckts.rgsy.statistic.count.dao;

import java.util.List;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.ChkptFileEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.rgsy.statistic.count.entity.CheckCount;
@Mybatis
public interface ICheckCountDao extends Dao<CheckCount, String> {
	/**
	 * 查询所有查验区
	 */
	List<ChkptFileEntity> allChkpt();
	
	/**
	 * 查询查验合格记录数
	 */
	Integer checkCount(CheckCount cc);
	
	/**
	 * 查询查验总记录数
	 */
	Integer allCount(CheckCount cc);
	/*
	 * 报废统计查询
	 * @按天查询
	 * */
	List<CheckCount> findBfxx(Page<CheckCount> page);
	Integer findBfxxCount(CheckCount checkcount);
	/*
	 * 报废统计查询
	 * @按时间段查询
	 * */	
	
	List<CheckCount> findBfxxbyTmie(Page<CheckCount> page);
	Integer findBfxxbyTmieCount(CheckCount checkcount);
}
