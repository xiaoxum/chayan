package com.vkl.ckts.cksy.servacpt.service;

import javax.servlet.http.HttpServletRequest;

import com.vkl.ckts.common.entity.PleApplEntity;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.cksy.servacpt.dao.IPleAppDao;
import com.vkl.ckts.cksy.servacpt.entity.VehInfoDto;

/**
 * @see 质押登记申请表Service
 * @author hwf
 * @version 1.0
 */
public interface IPleAppService extends IService<IPleAppDao,PleApplEntity,String>{
	/**
	 * @see 添加质押登记申请表
	 * @param pleApplEntity
	 */
	String addPleApp(PleApplEntity pleApplEntity,VehInfoDto VehInfoDto,HttpServletRequest request);

}
