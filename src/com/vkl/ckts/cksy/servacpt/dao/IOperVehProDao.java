package com.vkl.ckts.cksy.servacpt.dao;

import java.util.List;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.OperVehProEntity;

/**
 * @业务车型查验项目表Dao接口
 * @author jiajun
 *
 */
@Mybatis
public interface IOperVehProDao extends Dao<OperVehProEntity,String> {
	
	/**
	 * 业务车型查验项目
	 * @param OperVehProEntity
	 * @return List 
	 */
	List<OperVehProEntity> find(OperVehProEntity operVehProEntity);
	

}
