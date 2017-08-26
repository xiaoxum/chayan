package com.vkl.ckts.rgsy.statistic.count.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.rgsy.statistic.count.dao.IViewerCountDao;
import com.vkl.ckts.rgsy.statistic.count.entity.CheckCount;

public interface IViewerCountService extends IService<IViewerCountDao, CheckCount, String>{
	/**
	 * 查询所有信息
	 * @throws UnsupportedEncodingException 
	 */
	public List<CheckCount> allCheckInfo(Page<CheckCount> page) throws UnsupportedEncodingException;
	

}
