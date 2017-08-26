package com.vkl.ckts.rgsy.vehiclereview.service;

import java.util.List;

import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.rgsy.vehiclereview.dao.IResouDao;
import com.vkl.ckts.rgsy.vehiclereview.entity.ResourceDto;
/**
 * 资料拍照业务接口
 * 
 * @author xiaoxu 
 *
 */
public interface IResouService extends IService<IResouDao,ResourceDto,String> {

	
	/**
	    * 查询资料照片
	    * @param srln
	    * @param rckCount
	    * @return
	    */
		List<ResourceDto> findResPic(String srln, String rckCount);
	
	
}
