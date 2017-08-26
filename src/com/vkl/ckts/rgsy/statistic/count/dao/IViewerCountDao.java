package com.vkl.ckts.rgsy.statistic.count.dao;

import org.apache.ibatis.annotations.Param;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.rgsy.statistic.count.entity.CheckCount;
@Mybatis
public interface IViewerCountDao extends Dao<CheckCount, String>{
	/**
	 * 查询查验员的查验信息总记录数
	 */
	Integer allCount(CheckCount vc);
	
	/**
	 * 查验员查验合格记录数
	 */
	Integer checkCount(CheckCount vc);
	
	/**
	 * 根据备案编号获得名称
	 * @param viewer 备案编号
	 * @return  名称
	 */
	String getVName(@Param("viewer") String viewer);
}
