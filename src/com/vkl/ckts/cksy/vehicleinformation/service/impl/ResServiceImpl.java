package com.vkl.ckts.cksy.vehicleinformation.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vkl.ckts.cksy.vehicleinformation.dao.IResDao;
import com.vkl.ckts.cksy.vehicleinformation.entity.ResourceDto;
import com.vkl.ckts.cksy.vehicleinformation.service.IResService;
import com.vkl.ckts.common.service.impl.ServiceImpl;
/**
 * 资料业务类
 * @author Administrator
 *
 */
@Service
public class ResServiceImpl extends ServiceImpl<IResDao, ResourceDto, String> implements IResService{

	@Override
	public List<ResourceDto> findResPic(String srln, String rckCount) {
		// TODO Auto-generated method stub
		return super.dao.findResPic(srln, rckCount);
	}




}
