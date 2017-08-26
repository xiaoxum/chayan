package com.vkl.ckts.cksy.servacpt.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.cksy.servacpt.dao.IVehInfoDtoDao;
import com.vkl.ckts.cksy.servacpt.entity.VehInfoDto;
import com.vkl.ckts.cksy.servacpt.service.IVehInfoDtoService;

/**
 * @合格证Service实现类
 * @author Administrator
 *
 */
@Service
@Transactional
public class VehInfoDtoServiceImpl extends ServiceImpl<IVehInfoDtoDao, VehInfoDto, String>
		implements IVehInfoDtoService {

	@Override
	public void updateVehicleInfo(VehInfoDto vehInfoDto) {
		// TODO Auto-generated method stub
		super.dao.updateVehicleInfo(vehInfoDto);
	}
	
	

}
