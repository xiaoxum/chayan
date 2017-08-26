package com.vkl.ckts.rgsy.record.dao;

import java.util.List;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.ZbzlFileEntity;
import com.vkl.ckts.common.page.Page;

/**
 * 整备质量备案dao
 *
 * @author hwf
 * @version 1.0
 */
@Mybatis
public interface IZbzlFileDao extends Dao<ZbzlFileEntity, String>{
	/**
	 * 添加整备质量备案申请
	 * 
	 * @param zbzlFileEntity整备质量备案实体
	 * @return 插入成功的数据个数
	 */
	Integer addZbzlFile(ZbzlFileEntity zbzlFileEntity) throws Exception;
	
	/**
	 * 分页查询整备质量备案信息
	 * 
	 * @param page 分页工具实体
	 * @return Gbr集合
	 */
	List<ZbzlFileEntity> pageZbzlFile(Page<ZbzlFileEntity> page) throws Exception;
	
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
	 *  更新备案信息
	 *  
	 * @param zbzlFileEntity
	 */
	void updateZbzlFile(ZbzlFileEntity zbzlFileEntity) throws Exception;

}
