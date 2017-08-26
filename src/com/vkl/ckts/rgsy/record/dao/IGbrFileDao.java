package com.vkl.ckts.rgsy.record.dao;

import java.util.List;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.GbrFileEntity;
import com.vkl.ckts.common.page.Page;

/**
 * 外廓设备备案dao
 *
 * @author hwf
 * @version 1.0
 */
@Mybatis
public interface IGbrFileDao extends Dao<GbrFileEntity, String>{
	/**
	 * 添加Gbr备案申请
	 * 
	 * @param gbrFileEntity Gbr备案实体
	 * @return 插入成功的数据个数
	 */
	Integer addGbrFile(GbrFileEntity gbrFileEntity) throws Exception;
	
	/**
	 * 分页查询Gbr备案信息
	 * 
	 * @param page 分页工具实体
	 * @return Gbr集合
	 */
	List<GbrFileEntity> pageGbrFile(Page<GbrFileEntity> page) throws Exception;
	
	/**
	 * 根据Gbr编号逻辑删除备案信息
	 * 
	 * @param gbrFileEntity
	 */
	void deleteGbrFileById(GbrFileEntity gbrFileEntity) throws Exception;
	
	/**
	 * 根据Gbr编号查找备案信息
	 * 
	 * @param gbrFileEntity
	 * @return gbrFileEntity
	 */
	GbrFileEntity findGbrFileById(GbrFileEntity gbrFileEntity) throws Exception;
	
	/**
	 *  更新备案信息
	 *  
	 * @param gbrFileEntity
	 */
	void updateGbrFile(GbrFileEntity gbrFileEntity) throws Exception;

}
