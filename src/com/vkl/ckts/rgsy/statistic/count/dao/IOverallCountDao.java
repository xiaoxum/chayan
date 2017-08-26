package com.vkl.ckts.rgsy.statistic.count.dao;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.rgsy.statistic.count.entity.CheckCount;

@Mybatis
public interface IOverallCountDao extends Dao<CheckCount, String>{
	/**
	 * 查验总计
	 */
	Integer allCount(CheckCount cc);
	
	/**
	 * 查验合格总计
	 */
	Integer checkCount(CheckCount cc);
}
