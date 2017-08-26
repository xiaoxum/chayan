package com.vkl.ckts.cksy.vehicleinformation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vkl.ckts.cksy.vehicleinformation.entity.PicRecordDto;
import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;

/**
 * 查验拍照项目照片记录表Dao接口
 * 
 * @author Administrator
 *
 */
@Mybatis
public interface IPicRecordDaos extends Dao<PicRecordDto, String> {
	/**
	 * 根据流水号查询照片记录
	 * 
	 * @param srln
	 * @return
	 */
	public List<PicRecordDto> findBySrln(String srln,String rckCount);

	/**
	 * 更新审核数据
	 * 
	 * @param srln
	 *            流水号
	 * @param picId
	 *            拍照项目编号
	 * @param auditRemaks
	 *            审核备注
	 * @param picStatu
	 *            照片状态（1，正常 2.审核不通过）
	 * @return
	 */
	public int updInfo(@Param(value = "srln") String srln, @Param(value = "picId") String picId,
			@Param(value = "auditRemaks") String auditRemaks, @Param(value = "picStatu") String picStatu);
	
	/**
	 * 修改照片为补拍
	 * 
	 * @param srln
	 * @param rckCount
	 * @param picId
	 */
	public void  updatePicRecord(String srln,String rckCount, String picId);
	
	/**
	 * 添加打印记录
	 * 
	 * @param picRecordDto
	 */
	public void addPicRecord(PicRecordDto picRecordDto);
	/**
	 * 查询照片记录
	 * 
	 * @param srln
	 * @param rckCount
	 * @param picId
	 * 
	 * @return
	 */
	public PicRecordDto findPicRecordDto(String srln,String rckCount, String picId);
	
	
	/**
	 * 更新查验状态
	 * 
	 * @param srln       流水号
	 * @param rckCount   复检次数
	 */
	
	public void updateCkStatu(String srln ,String rckCount);
	
	/**
	 * 获取业务员拍照项
	 * 
	 * @param operType
	 * @param syxz
	 * @param cllx
	 * @param srln
	 * @param rckCount
	 * @return
	 */
	List<PicRecordDto> findOperPicPro(String operType,String syxz,String cllx,String srln,String rckCount);
}
