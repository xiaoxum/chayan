package com.vkl.ckts.cksy.servacpt.service;


import java.util.List;

import com.vkl.ckts.common.entity.ResourceEntity;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.cksy.servacpt.dao.IResourceDao;
import com.vkl.ckts.cksy.servacpt.entity.ResProDto;


/**
 * 业务影像资料表Service接口
 * @author jiajun
 * @version 1.0
 */
public interface IResourceService extends IService<IResourceDao,ResourceEntity,String> {
	
	void upResource(String url ,String srln,String rckCount,String resId);
	
	
	List<ResProDto> findResources();

}
