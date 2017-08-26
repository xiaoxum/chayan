package com.vkl.ckts.rgsy.system.operationsetting.dao;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.rgsy.system.operationsetting.entity.newProAnnc;
@Mybatis
public interface IOperationDao extends Dao<newProAnnc,String>{
	/**
	 * 查询一条业务信息
	 */
	newProAnnc oneOper(newProAnnc npa);
}