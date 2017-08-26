package com.vkl.ckts.cksy.servacpt.service;

import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.cksy.servacpt.dao.IVehInfoDtoDao;
import com.vkl.ckts.cksy.servacpt.entity.VehInfoDto;
/**
 * @合格证Service接口
 * @author Administrator
 *
 */
public interface IVehInfoDtoService extends IService<IVehInfoDtoDao,VehInfoDto,String>{
	/**
	 * 更新车辆信息
	 * 
	 * @param vehInfoDto
	 */
	void updateVehicleInfo(VehInfoDto vehInfoDto);
}
