package com.vkl.ckts.rgsy.vehiclereview.dao;

import java.util.List;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.rgsy.vehiclereview.entity.ResourceDto;
/**
 * 资料照片地址 
 * 
 * @author xiaoxu
 *
 */
@Mybatis
public interface IResouDao extends Dao<ResourceDto,String>{
   /**
    * 查询资料照片
    * @param srln
    * @param rckCount
    * @return
    */
	List<ResourceDto> findResPic(String srln, String rckCount);
	
	
}
