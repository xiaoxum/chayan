package com.vkl.ckts.cksy.servacpt.dao;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.ChApplItemEntity;

/**
 * @see 变更登记/备案申请Dao
 * @author hwf
 * @version 1.0
 */
@Mybatis
public interface IChApplItemDao extends Dao<ChApplItemEntity,java.lang.String>{
	
	/**
	 * @see 添加变更登记申请
	 * @param ChApplItemEntity
	 */
	void addChAppl(ChApplItemEntity chApplItemEntity);
	
	

}
