package com.vkl.ckts.cksy.vehicleinformation.dao;

import java.util.List;

import com.vkl.ckts.cksy.vehicleinformation.entity.ChkptNvrConfigDto;
import com.vkl.ckts.cksy.vehicleinformation.entity.CkInfoEntityDto;
import com.vkl.ckts.cksy.vehicleinformation.entity.VedioRecordDto;
import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
/**
 * 查验视频记录Dao接口
 * @author Administrator
 *
 */
@Mybatis
public interface IVedioRecordDaos extends Dao<VedioRecordDto,String>{
	/**
	 * 根据流水号查询
	 * @param srln	 流水号
	 * @return
	 */
	public List<VedioRecordDto> findBySrln(String srln,String rckCount);
	
	/**
	 * 获取视频配置表
	 * 
	 * @param organCode
	 * @param checkLineId
	 * @return
	 */
	ChkptNvrConfigDto findVideoConfigByBh(String organCode,String checkLineId);
	
	/**
	 * 查验信息
	 * 
	 * @param srln
	 * @param rckCount
	 * @return
	 */
	CkInfoEntityDto findckinfobysrln(String srln,String rckCount);
	
	
	
	VedioRecordDto findVideoInfo(String srln,String rckCount,String videoAngle);
	
	
	void  insertVideoInfo(VedioRecordDto viDto);

}
