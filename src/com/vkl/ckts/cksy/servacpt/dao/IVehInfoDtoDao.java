package com.vkl.ckts.cksy.servacpt.dao;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.cksy.servacpt.entity.VehInfoDto;
/**
 * @合格证Dao接口
 * @author Administrator
 *
 */
@Mybatis
public interface IVehInfoDtoDao extends Dao<VehInfoDto,String>{
     
	
	/**
	 * 更新车辆信息
	 * 
	 * @param vehInfoDto
	 */
	void updateVehicleInfo(VehInfoDto vehInfoDto);
}
