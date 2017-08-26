package com.vkl.ckts.cksy.vehicleinformation.service;

import java.util.List;

import com.vkl.ckts.cksy.vehicleinformation.dao.IProjectRecordDaos;
import com.vkl.ckts.cksy.vehicleinformation.entity.ProjectRecordDto;
import com.vkl.ckts.common.service.IService;

/**
 * 查验拍照项目表Service接口
 * @author Administrator
 *
 */
public interface IProjectRecordServices extends IService<IProjectRecordDaos,ProjectRecordDto,String>{
	/**
	 * 根据流水号查询
	 * @param srln
	 * @return
	 */
	public List<ProjectRecordDto> findById(String srln,String rckCount);
}
