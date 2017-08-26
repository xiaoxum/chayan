package com.vkl.ckts.rgsy.vehiclereview.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.rgsy.vehiclereview.entity.ProjectRecordDto;
/**
 * 查验拍照项目表Dao接口
 * @author Administrator
 *
 */
@Mybatis
public interface IProjectRecordDao extends Dao<ProjectRecordDto,String>{
	/**
	 * 根据流水号查询
	 * @param srln
	 * @return
	 */
	public List<ProjectRecordDto> findById(@Param(value="srln")String srln,@Param(value="rckCount")String rckCount);

}
