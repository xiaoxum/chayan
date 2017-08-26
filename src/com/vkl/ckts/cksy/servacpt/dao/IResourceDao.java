package com.vkl.ckts.cksy.servacpt.dao;

import java.util.List;

import com.vkl.ckts.cksy.servacpt.entity.ResProDto;
import com.vkl.ckts.cksy.servacpt.entity.ResourceDto;
import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.ResourceEntity;

/**
 * 业务影像资料表dao接口
 * @author jiajun
 * @version 1.0
 */
@Mybatis
public interface IResourceDao extends Dao<ResourceEntity,String> {
	
	void insertData(ResourceEntity ResourceEntity);
	
	
	List<ResProDto> findResources();
	/**
	 * 修改资料照片为已上传
	 * 
	 * @param srln
	 * @param rckCount
	 * @param resId
	 */
	void updateResSc(String srln,String rckCount,String resId);

	
	/**
	 * 查询数据
	 * 
	 * @param srln
	 * @param rckCount
	 * @param resId
	 * @return
	 */
	ResourceDto findResRecord(String srln,String rckCount,String resId);
	
	/**
	 * 修改资源记录
	 * @param resourceDto
	 */
	void updateResInfo(ResourceDto resourceDto);
	
}
