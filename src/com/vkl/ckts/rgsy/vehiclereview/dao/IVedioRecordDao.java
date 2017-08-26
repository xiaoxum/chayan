package com.vkl.ckts.rgsy.vehiclereview.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.rgsy.vehiclereview.entity.VedioRecordDto;
/**
 * 查验视频记录Dao接口
 * @author Administrator
 *
 */
@Mybatis
public interface IVedioRecordDao extends Dao<VedioRecordDto,String>{
	/**
	 * 根据流水号查询
	 * @param srln		 流水号
	 * @param rdkCount 	复检次数
	 * @return
	 */
	public List<VedioRecordDto> findBySrln(@Param(value="srln")String srln,@Param(value="rdkCount")String rdkCount);

}
