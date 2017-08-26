package com.vkl.ckts.rgsy.system.set.dao;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.SysConfigEntity;

/**
 * 系统设置dao
 * @author xujunhua
 * @date 2017年3月31日
 * @ClassName: ISetDao
 */
@Mybatis
public interface ISetDao extends Dao<SysConfigEntity, String>{
	/**
	 * 查询系统设置当前状态 
	 * @param sce
	 * @return
	 */
	SysConfigEntity selStatu(SysConfigEntity sce);
	
	/**
	 * 修改系统设置状态
	 * @param sce
	 */
	void updStatu(SysConfigEntity sce);
}
