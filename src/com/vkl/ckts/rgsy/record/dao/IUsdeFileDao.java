package com.vkl.ckts.rgsy.record.dao;

import java.util.List;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.CkProjectEntity;
import com.vkl.ckts.common.entity.UsdeProFileEntity;
import com.vkl.ckts.common.page.Page;

/**
 * 自定义查验项备案Dao
 *
 * @author hwf
 * @version 1.0
 */
@Mybatis
public interface IUsdeFileDao extends Dao<UsdeProFileEntity, String> {
	
	
	/**
	 * 根据项目id修改查验项目的状态
	 *
	 * @param ckProjectEntity
	 * @throws Exception
	 */
	void updateCkProState(CkProjectEntity ckProjectEntity) throws Exception;
	
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
	 * 添加自定义查验项目备案申请
	 * 
	 * @param usdeProFileEntity 自定义查验项目备案实体
	 * @return 插入成功的数据个数
	 */
	Integer addUsdeFile(UsdeProFileEntity usdeProFileEntity) throws Exception;
	
	/**
	 * 分页查询自定义查验项目备案信息
	 * 
	 * @param page 分页工具实体
	 * @return 查验区集合
	 */
	List<UsdeProFileEntity> pageUsdeFile(Page<UsdeProFileEntity> page) throws Exception;
	
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
	 *  更新备案信息
	 *  
	 * @param usdeProFileEntity
	 */
	void updateUsdeFile(UsdeProFileEntity usdeProFileEntity) throws Exception;
}
