package com.vkl.ckts.rgsy.spotcheck.service;

import java.util.List;

import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.rgsy.spotcheck.dao.ICkInfoDao;
import com.vkl.ckts.rgsy.spotcheck.entity.CkInfoDto;

/**
 * 查验抽查Service
 * @author jiajun
 *
 */
public interface ICkInfoService extends IService<ICkInfoDao,CkInfoDto,String>{

	/**
	 * 查验抽查
	 * @param Page<CkInfoDto> 
	 * @return List<CkInfoDto>  
	 */
	List<CkInfoDto> spotCheck(Page<CkInfoDto> page);
	
}
