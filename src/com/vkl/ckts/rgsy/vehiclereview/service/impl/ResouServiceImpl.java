package com.vkl.ckts.rgsy.vehiclereview.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.rgsy.vehiclereview.dao.IResouDao;
import com.vkl.ckts.rgsy.vehiclereview.entity.ResourceDto;
import com.vkl.ckts.rgsy.vehiclereview.service.IResouService;
/**
 * 资料业务类
 * @author Administrator
 *
 */
@Service
public class ResouServiceImpl extends ServiceImpl<IResouDao, ResourceDto, String> implements IResouService{

	@Override
	public List<ResourceDto> findResPic(String srln, String rckCount) {
		// TODO Auto-generated method stub
		return super.dao.findResPic(srln, rckCount);
	}




}
