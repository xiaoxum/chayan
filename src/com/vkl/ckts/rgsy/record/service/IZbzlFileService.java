package com.vkl.ckts.rgsy.record.service;

import javax.servlet.http.HttpServletRequest;

import com.vkl.ckts.common.entity.ZbzlFileEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.rgsy.record.dao.IZbzlFileDao;

/**
 * 整备质量备案Service
 *
 * @author hwf
 * @version 1.0
 */
public interface IZbzlFileService extends IService<IZbzlFileDao, ZbzlFileEntity, String> {
	/**
	 * 添加整备质量备案申请
	 * 
	 * @param zbzlFileEntity 整备质量备案实体
	 * @param request
	 * @return true：添加成功，false：添加失败
	 */
	boolean addZbzlFile(ZbzlFileEntity zbzlFileEntity, HttpServletRequest request) throws Exception;
	
	/**
	 * 分页查询整备质量备案信息
	 * 
	 * @param page 分页工具实体
	 * @return Gbr集合
	 */
	Page<ZbzlFileEntity> pageZbzlFile(Page<ZbzlFileEntity> page) throws Exception;
	
	/**
	 * 根据整备质量编号逻辑删除备案信息
	 * 
	 * @param zbzlFileEntity
	 */
	void deleteZbzlFileById(ZbzlFileEntity zbzlFileEntity) throws Exception;
	
	/**
	 * 根据整备质量编号查找备案信息
	 * 
	 * @param zbzlFileEntity
	 * @return zbzlFileEntity
	 */
	ZbzlFileEntity findZbzlFileById(ZbzlFileEntity zbzlFileEntity) throws Exception;
	
	/**
	 * 更新备案信息
	 *
	 * @param zbzlFileEntity
	 * @param request
	 * @return true：更新成功，fasle：更新失败
	 */
	boolean updateZbzlFile(ZbzlFileEntity zbzlFileEntity, HttpServletRequest request) throws Exception;

}
