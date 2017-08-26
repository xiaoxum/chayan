package com.vkl.ckts.rgsy.statistic.unusual.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.VedioRecordEntity;
import com.vkl.ckts.common.entity.ViewerFileEntity;
import com.vkl.ckts.rgsy.statistic.unusual.entity.NewExceptInfo;
import com.vkl.ckts.rgsy.statistic.unusual.entity.PicRecord;
@Mybatis
public interface IExceptDao extends Dao<NewExceptInfo, String>{

	/**
	 * 查看异常信息详情
	 */
	NewExceptInfo detail(@Param("excId") Integer excId);
	
	/**
	 * 根据异常信息修改查验员状态
	 */
	void updViewerStatu(ViewerFileEntity cfe);
	
	/**
	 * 获得查验所有照片
	 * @param srln 流水号
	 * @return 照片集合
	 */
	List<PicRecord> allPic(@Param("srln") String srln);
	/**
	 * 获取所有视频
	 * @param srln 流水号
	 * @return 视频集合
	 */
	List<VedioRecordEntity> allVedio(@Param("srln") String srln);
}
