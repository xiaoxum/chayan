package com.vkl.ckts.cksy.vehicleinformation.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.cksy.vehicleinformation.dao.IProjectRecordDaos;
import com.vkl.ckts.cksy.vehicleinformation.entity.ProjectRecordDto;
import com.vkl.ckts.cksy.vehicleinformation.service.IProjectRecordServices;
import com.vkl.ckts.common.service.impl.ServiceImpl;
/**
 * 查验拍照项目表Service实现类
 * @author Administrator
 *
 */
@Service
@Transactional
public class ProjectRecordServicesImpl extends ServiceImpl<IProjectRecordDaos,ProjectRecordDto,String>
		implements IProjectRecordServices{

	@Override
	public List<ProjectRecordDto> findById(String srln,String rckCount) {
		// TODO Auto-generated method stub
		return super.dao.findById(srln, rckCount);
	}

}
