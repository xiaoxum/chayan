package com.vkl.ckts.rgsy.record.dao;

import java.util.List;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.VisitCkFileEntity;
import com.vkl.ckts.common.page.Page;

/**
 * 上门查验备案Dao
 *
 * @author hwf
 * @version 1.0
 */
@Mybatis
public interface IVisitCkFileDao extends Dao<VisitCkFileEntity, String> {
	
	/**
	 * 添加上门查验备案申请
	 * 
	 * @param visitCkFileEntity 上门查验备案实体
	 * @return 插入成功的数据个数
	 */
	Integer addVisitCkFile(VisitCkFileEntity visitCkFileEntity) throws Exception;
	
	/**
	 * 分页查询上门查验备案信息
	 * 
	 * @param page 分页工具实体
	 * @return 查验区集合
	 */
	List<VisitCkFileEntity> pageVisitCkFile(Page<VisitCkFileEntity> page) throws Exception;
	
	/**
	 * 根据上门查验编号逻辑删除备案信息
	 * 
	 * @param visitCkFileEntity
	 */
	void deleteVisitCkFileById(VisitCkFileEntity visitCkFileEntity) throws Exception;
	
	/**
	 * 根据上门查验编号查找备案信息
	 * 
	 * @param visitCkFileEntity
	 * @return visitCkFileEntity
	 */
	VisitCkFileEntity findVisitCkFileById(VisitCkFileEntity visitCkFileEntity) throws Exception;
	
	/**
	 *  更新备案信息
	 *  
	 * @param visitCkFileEntity
	 */
	void updateVisitCkFile(VisitCkFileEntity visitCkFileEntity) throws Exception;

}
