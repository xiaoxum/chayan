package com.vkl.ckts.rgsy.statistic.count.service;

import java.util.List;

import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.rgsy.statistic.count.dao.IOverallCountDao;
import com.vkl.ckts.rgsy.statistic.count.entity.CheckCount;

public interface IOverallCountService extends IService<IOverallCountDao, CheckCount, String>{
	/**
	 * 查询所有外廓查验信息
	 */
	public List<CheckCount> allCC(Page<CheckCount> page);
}
