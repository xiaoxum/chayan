package com.vkl.ckts.cksy.servacpt.service;

import java.util.List;

import com.vkl.ckts.common.entity.OperVehProEntity;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.cksy.servacpt.dao.IOperVehProDao;


/**
 * @业务车型查验项目Service接口
 * @author jiajun
 *
 */
public interface IOperVehProService extends IService<IOperVehProDao,OperVehProEntity,String>{

	/**
	 * 业务车型查验项目
	 * @param OperVehProEntity
	 * @return List 
	 */
	List<OperVehProEntity> find(OperVehProEntity operVehProEntity);
}
