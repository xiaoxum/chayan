package com.vkl.ckts.cksy.vehicleinformation.service;

import java.util.List;

import com.vkl.ckts.cksy.vehicleinformation.dao.IPicRecordDaos;
import com.vkl.ckts.cksy.vehicleinformation.entity.PicRecordDto;
import com.vkl.ckts.common.service.IService;
/**
 * 查验拍照项目照片记录表Service接口
 * @author Administrator
 *
 */
public interface IPicRecordServices extends IService<IPicRecordDaos,PicRecordDto,String>{
	/**
	 * 根据流水号查询照片记录
	 * @param srln
	 * @return
	 */
	public List<PicRecordDto> findBySrln(String srln,String rckCount);
	/**
	 * 不合格照片补拍
	 * 
	 * @param srln
	 * @param rckCount
	 * @param picId
	 */
	public void photoBp(String srln ,String rckCount,String picId,String userId);
	
	/**
	 * 缺失照片补拍
	 * 
	 * @param srln
	 * @param rckCount
	 * @param picId
	 */
	public void photoQsBp(String srln, String rckCount,String userId);
}
