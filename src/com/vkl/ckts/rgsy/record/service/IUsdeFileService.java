package com.vkl.ckts.rgsy.record.service;

import javax.servlet.http.HttpServletRequest;

import com.vkl.ckts.common.entity.CkProjectEntity;
import com.vkl.ckts.common.entity.UsdeProFileEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.rgsy.record.dao.IUsdeFileDao;

/**
 * 自定义查验项备案Service
 *
 * @author hwf
 * @version 1.0
 */
public interface IUsdeFileService extends IService<IUsdeFileDao, UsdeProFileEntity, String> {
	
	/**
	 * 添加自定义查验项目备案申请
	 * 
	 * @param usdeProFileEntity pda备案实体
	 * @param request
	 * @return true：添加成功，false：添加失败
	 */
	boolean addUsdeFile(UsdeProFileEntity usdeProFileEntity, HttpServletRequest request) throws Exception;
	
	
	/**
	 * 根据业务类型id查找业务名称
	 *
	 * @param id
	 * @return 业务名称
	 * @throws Exception
	 */
	String findOperTypeById(String id) throws Exception;
	
	/**
	 * 根据车辆类型父级id查找父级名称
	 *
	 * @param parentCllx
	 * @return parentName 父级名称
	 * @throws Exception
	 */
	String findCkVehByParentId(String parentCllx) throws Exception;
	
	/**
	 * 根据使用性质父类id查找父级名称
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	String findUsnrByParentId(String parentId) throws Exception;
	
	/**
	 * 分页查询自定义查验项目备案信息
	 * 
	 * @param page 分页工具实体
	 * @return 自定义查验项目集合
	 */
	Page<UsdeProFileEntity> pageUsdeFile(Page<UsdeProFileEntity> page) throws Exception;
	
	/**
	 * 根据自定义查验项目编号逻辑删除备案信息
	 * 
	 * @param usdeProFileEntity
	 */
	void deleteUsdeFileById(UsdeProFileEntity usdeProFileEntity) throws Exception;
	
	/**
	 * 根据自定义查验项目编号查找备案信息
	 * 
	 * @param usdeProFileEntity
	 * @return usdeProFileEntity
	 */
	UsdeProFileEntity findUsdeFileById(UsdeProFileEntity usdeProFileEntity) throws Exception;
	
	/**
	 * 更新备案信息
	 *
	 * @param usdeProFileEntity
	 * @param request
	 * @return true：更新成功，fasle：更新失败
	 */
	boolean updateUsdeFile(UsdeProFileEntity usdeProFileEntity, HttpServletRequest request, CkProjectEntity ckProjectEntity) throws Exception;
}
