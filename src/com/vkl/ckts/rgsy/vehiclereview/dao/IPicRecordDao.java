package com.vkl.ckts.rgsy.vehiclereview.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.rgsy.vehiclereview.entity.PicRecordDto;

/**
 * 查验拍照项目照片记录表Dao接口
 * 
 * @author Administrator
 *
 */
@Mybatis
public interface IPicRecordDao extends Dao<PicRecordDto, String> {
	/**
	 * 根据流水号查询照片记录
	 * 
	 * @param srln
	 * @return
	 */
	public List<PicRecordDto> findBySrln(@Param(value="srln")String srln,@Param(value="rckCount")String rckCount);

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
}
