package com.vkl.ckts.cksy.vehicleinformation.service;

import java.util.List;

import com.vkl.ckts.cksy.vehicleinformation.dao.IVedioRecordDaos;
import com.vkl.ckts.cksy.vehicleinformation.entity.VedioRecordDto;
import com.vkl.ckts.common.service.IService;
/**
 * 查验视频记录Service接口
 * @author Administrator
 *
 */
public interface IVedioRecordServices extends IService<IVedioRecordDaos,VedioRecordDto,String>{
	/**
	 * 根据流水号查询
	 * @param srln	 流水号
	 * @return
	 */
	public List<VedioRecordDto> findBySrln(String srln,String rckCount);
	
	
	
	/**
	 * 视频截取
	 * 
	 * @param srln
	 * @param rckCount
	 * @param videoAngle
	 * @return
	 */
	public String videoDownLoad(String srln,String rckCount,String videoAngle);

}
