package com.vkl.ckts.cksy.vehicleinformation.dao;

import java.util.List;

import com.vkl.ckts.cksy.vehicleinformation.entity.ProjectRecordDto;
import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
/**
 * 查验拍照项目表Dao接口
 * @author Administrator
 *
 */
@Mybatis
public interface IProjectRecordDaos extends Dao<ProjectRecordDto,String>{
	/**
	 * 根据流水号查询
	 * @param srln
	 * @return
	 */
	public List<ProjectRecordDto> findById(String srln,String rckCount);

}
