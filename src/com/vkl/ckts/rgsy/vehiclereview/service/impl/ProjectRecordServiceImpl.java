package com.vkl.ckts.rgsy.vehiclereview.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.rgsy.vehiclereview.dao.IProjectRecordDao;
import com.vkl.ckts.rgsy.vehiclereview.entity.ProjectRecordDto;
import com.vkl.ckts.rgsy.vehiclereview.service.IProjectRecordService;

/**
 * 查验拍照项目表Service实现类
 * 
 * @author Administrator
 *
 */
@Service
@Transactional
public class ProjectRecordServiceImpl extends ServiceImpl<IProjectRecordDao, ProjectRecordDto, String>
		implements IProjectRecordService {

	@Override
	public List<ProjectRecordDto> findById(String srln,String rckCount) {
		return super.dao.findById(srln,rckCount);
	}

}
