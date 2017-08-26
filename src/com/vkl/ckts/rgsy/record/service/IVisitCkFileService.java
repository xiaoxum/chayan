package com.vkl.ckts.rgsy.record.service;

import javax.servlet.http.HttpServletRequest;

import com.vkl.ckts.common.entity.VisitCkFileEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.rgsy.record.dao.IVisitCkFileDao;

/**
 * 上门查验备案Service
 *
 * @author hwf
 * @version 1.0
 */
public interface IVisitCkFileService extends IService<IVisitCkFileDao, VisitCkFileEntity, String> {

	/**
	 * 添加pda备案申请
	 * 
	 * @param visitCkFileEntity 上门查验备案实体
	 * @param request
	 * @return true：添加成功，false：添加失败
	 */
	boolean addVisitCkFile(VisitCkFileEntity visitCkFileEntity, HttpServletRequest request) throws Exception;
	
	/**
	 * 分页查询上门查验备案信息
	 * 
	 * @param page 分页工具实体
	 * @return 上门查验集合
	 */
	Page<VisitCkFileEntity> pageVisitCkFile(Page<VisitCkFileEntity> page) throws Exception;
	
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
	 * 更新备案信息
	 *
	 * @param visitCkFileEntity
	 * @param request
	 * @return true：更新成功，fasle：更新失败
	 */
	boolean updateVisitCkFile(VisitCkFileEntity visitCkFileEntity, HttpServletRequest request) throws Exception;
}
