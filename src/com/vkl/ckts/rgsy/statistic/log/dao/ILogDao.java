package com.vkl.ckts.rgsy.statistic.log.dao;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.rgsy.statistic.log.entity.OperaDaily;

/**
 * 日志信息dao
 * @author xujunhua
 * @date 2017年3月23日
 * @ClassName: ILogDao
 */
@Mybatis
public interface ILogDao extends Dao<OperaDaily, String>{
	/**
	 * 插入日志
	 * @param log 日志实体类
	 */
	void insertLog(OperaDaily log);
}
