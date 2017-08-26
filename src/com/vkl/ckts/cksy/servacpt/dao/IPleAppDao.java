package com.vkl.ckts.cksy.servacpt.dao;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.PleApplEntity;

/**
 * @see 质押登记申请表Dao
 * @author hwf
 * @version 1.0
 */
@Mybatis
public interface IPleAppDao extends Dao<PleApplEntity,java.lang.String>{
	
	
	/**
	 * @see 添加质押登记申请表
	 * @param pleApplEntity
	 */
	Integer addPleApp(PleApplEntity pleApplEntity);

}
