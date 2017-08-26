package com.vkl.ckts.rgsy.vehiclereview.service;

import java.util.List;

import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.rgsy.vehiclereview.dao.IPicRecordDao;
import com.vkl.ckts.rgsy.vehiclereview.entity.PicRecordDto;
/**
 * 查验拍照项目照片记录表Service接口
 * @author Administrator
 *
 */
public interface IPicRecordService extends IService<IPicRecordDao,PicRecordDto,String>{
	/**
	 * 根据流水号查询照片记录
	 * @param srln
	 * @return
	 */
	public List<PicRecordDto> findBySrln(String srln,String rckCount);

}
