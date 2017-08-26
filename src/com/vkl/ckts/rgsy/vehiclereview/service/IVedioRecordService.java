package com.vkl.ckts.rgsy.vehiclereview.service;

import java.util.List;

import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.rgsy.vehiclereview.dao.IVedioRecordDao;
import com.vkl.ckts.rgsy.vehiclereview.entity.VedioRecordDto;
/**
 * 查验视频记录Service接口
 * @author Administrator
 *
 */
public interface IVedioRecordService extends IService<IVedioRecordDao,VedioRecordDto,String>{
	/**
	 * 根据流水号查询
	 * @param srln	 	流水号
	 * @param rdkCount 	复检次数
	 * @return
	 */
	public List<VedioRecordDto> findBySrln(String srln,String rdkCount);

}
