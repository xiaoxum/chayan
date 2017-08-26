package com.vkl.ckts.rgsy.vehiclereview.service;

import java.util.List;

import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.rgsy.vehiclereview.dao.IProjectRecordDao;
import com.vkl.ckts.rgsy.vehiclereview.entity.ProjectRecordDto;

/**
 * 查验拍照项目表Service接口
 * @author Administrator
 *
 */
public interface IProjectRecordService extends IService<IProjectRecordDao,ProjectRecordDto,String>{
	/**
	 * 根据流水号查询
	 * @param srln
	 * @return
	 */
	public List<ProjectRecordDto> findById(String srln,String rckCount);
}
