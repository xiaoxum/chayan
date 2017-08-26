package com.vkl.ckts.cksy.vehicleinformation.dao;

import java.util.List;

import com.vkl.ckts.cksy.vehicleinformation.entity.ResourceDto;
import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
/**
 * 资料照片地址 
 * 
 * @author xiaoxu
 *
 */
@Mybatis
public interface IResDao extends Dao<ResourceDto,String>{
   /**
    * 查询资料照片
    * @param srln
    * @param rckCount
    * @return
    */
	List<ResourceDto> findResPic(String srln, String rckCount);
	
	
}
