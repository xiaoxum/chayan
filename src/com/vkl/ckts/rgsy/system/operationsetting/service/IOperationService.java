package com.vkl.ckts.rgsy.system.operationsetting.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.rgsy.system.operationsetting.dao.IOperationDao;
import com.vkl.ckts.rgsy.system.operationsetting.entity.newProAnnc;

public interface IOperationService extends IService<IOperationDao, newProAnnc, String>{
	/**
	 * 查询一条业务信息
	 */
	public newProAnnc oneOper(newProAnnc npa);
	
	/**
	 * 添加业务信息
	 * @param npa 
	 * @param request
	 * @throws Exception
	 */
	public boolean insertOper(newProAnnc npa,HttpServletRequest request) throws Exception;
	
	/**
	 * 删除业务信息
	 * @param npa
	 * @throws Exception
	 */
	public void delData(newProAnnc npa,HttpServletRequest request) throws Exception;
	
	/**
	 * 修改业务信息
	 * @param npa
	 * @param request
	 */
	public void doUpdOpera(newProAnnc npa,HttpServletRequest request) throws Exception;
	
	/**
	 * 查询所有业务信息
	 * @param page	
	 * @param npa
	 * @return 返回业务信息集合
	 */
	public List<newProAnnc> allAnnc(Page<newProAnnc> page,newProAnnc npa);
}
