package com.vkl.ckts.rgsy.system.set.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.entity.SysConfigEntity;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.rgsy.statistic.log.service.ILogService;
import com.vkl.ckts.rgsy.system.set.dao.ISetDao;
import com.vkl.ckts.rgsy.system.set.service.ISetService;
@Service
@Transactional
public class SetServiceImpl extends ServiceImpl<ISetDao, SysConfigEntity, String> implements ISetService{
	@Autowired
	ISetDao isd;
	
	@Autowired
	ILogService ils;	// 注入操作日志service
	/**
	 * 查询系统设置当前状态 
	 * @param sce
	 * @return
	 */
	public SysConfigEntity selStatu(SysConfigEntity sce){
		return isd.selStatu(sce);
	}
	
	/**
	 * 修改系统设置状态
	 * @param sce
	 */
	public void updStatu(SysConfigEntity sce,HttpServletRequest request)throws Exception{
		isd.updStatu(sce);
		ils.insertLog("修改系统设置", "系统设置", "修改"+sce.getKey()+"成功", request);
	}
}
