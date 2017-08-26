package com.vkl.ckts.rgsy.system.set.service;

import javax.servlet.http.HttpServletRequest;

import com.vkl.ckts.common.entity.SysConfigEntity;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.rgsy.system.set.dao.ISetDao;

public interface ISetService extends IService<ISetDao, SysConfigEntity, String>{
	/**
	 * 查询系统设置当前状态 
	 * @param sce
	 * @return
	 */
	public SysConfigEntity selStatu(SysConfigEntity sce);
	
	/**
	 * 修改系统设置状态
	 * @param sce
	 */
	public void updStatu(SysConfigEntity sce,HttpServletRequest request)throws Exception;
}
