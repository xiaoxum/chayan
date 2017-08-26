package com.vkl.ckts.rgsy.spotcheck.dao;

import java.util.List;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.rgsy.spotcheck.entity.CkInfoDto;

/**
 * 查验抽查DAO层
 * @author jiajun
 *
 */
@Mybatis
public interface ICkInfoDao extends Dao<CkInfoDto,String>{
	
	/**
	 * 查验抽查
	 * @param Page<CkInfoDto> 
	 * @return List<CkInfoDto>  
	 */
	List<CkInfoDto> spotCheck(Page<CkInfoDto> page);

}
