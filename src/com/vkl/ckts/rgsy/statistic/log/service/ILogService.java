package com.vkl.ckts.rgsy.statistic.log.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.rgsy.statistic.log.dao.ILogDao;
import com.vkl.ckts.rgsy.statistic.log.entity.OperaDaily;

public interface ILogService extends IService<ILogDao, OperaDaily, String>{
	/**
	 * 插入日志
	 * @param log 日志实体类
	 * @throws Exception 
	 */
	public void insertLog(String operation, String mkName, String operIntr, HttpServletRequest request) throws Exception;
	
	/**
	 * 分页查看所有日志
	 * @param pageNo 页号
	 * @param pageSize 页面大小
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param loginName 用户账号
	 * @return
	 */
	public List<OperaDaily> allOperDaily(Integer pageNo, Integer pageSize, String start, String end, String loginName);
	
	/**
	 * 查询记录数
	 * @return
	 */
	public int count(OperaDaily ole);
}
