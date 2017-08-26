package com.vkl.ckts.cksy.vehicleinformation.service;

import java.util.List;

import com.vkl.ckts.cksy.vehicleinformation.dao.IResDao;
import com.vkl.ckts.cksy.vehicleinformation.entity.ResourceDto;
import com.vkl.ckts.common.service.IService;
/**
 * 资料拍照业务接口
 * 
 * @author xiaoxu 
 *
 */
public interface IResService extends IService<IResDao,ResourceDto,String> {

	
	/**
	    * 查询资料照片
	    * @param srln
	    * @param rckCount
	    * @return
	    */
		List<ResourceDto> findResPic(String srln, String rckCount);
	
	
}
