package com.vkl.ckts.rgsy.system.nvrsetting.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.ChkptFileEntity;
import com.vkl.ckts.common.entity.DeptEntity;
import com.vkl.ckts.rgsy.record.entity.ChkptFileDto;
import com.vkl.ckts.rgsy.system.nvrsetting.entity.ChkptNvrConfigDto;

/**
 * 查验区视频配置信息dao层接口
 * 
 * @author X260
 *
 */
@Mybatis
public interface IChkptNvrConfigDao extends Dao<ChkptNvrConfigDto, String>{

	
	/**
	 * 查验视频配置信息
	 * 
	 * @return
	 */
	List<ChkptNvrConfigDto> findChkptNvrConfigs(@Param(value="organCode")String organCode);
	
	/**
	 * 插入视频信息
	 * @param c
	 */
	void insertChkptNvrConfig(ChkptNvrConfigDto c);
	
	/**
	 * 更新下视频信息 
	 * @param c
	 */
	void updateChkptNvrInfo(ChkptNvrConfigDto c);
	
	/**
	 * 删除视频信息
	 * @param organCode
	 * @param checkLineId
	 */
	void deleteChkptLineInfo(String organCode,	String checkLineId);
	
	/**
	 * 根据编号
	 * @param id
	 */
	DeptEntity findDeptById(@Param(value="id")String id);
	
	
	/**
	 * 根据机构编号和查验线编号查询配置信息
	 * 
	 * @param organCode
	 * @param lineId
	 * @return
	 */
	ChkptNvrConfigDto findChNvrConfig(String organCode,String lineId);
	
	
	/**
	 * 查询备案通过的查验区列表
	 *
	 * @param chkptFileEntity
	 * @return
	 * @throws Exception
	 */
	List<ChkptFileEntity> findChkptList(ChkptFileEntity chkptFileEntity) throws Exception;
}
