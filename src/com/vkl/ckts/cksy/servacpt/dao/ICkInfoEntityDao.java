package com.vkl.ckts.cksy.servacpt.dao;

import java.util.List;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.CkInfoEntity;

import org.apache.ibatis.annotations.Param;

import com.vkl.ckts.cksy.servacpt.entity.CkInfoDto;
/**
 * @查验信息Dao接口
 * @author Administrator
 *
 */
@Mybatis
public interface ICkInfoEntityDao extends Dao<CkInfoEntity,String>{

	/**
	 * @根据流水号查询查验信息
	 * @param String srln 流水号
	 * @return CkInfoEntity 查验信息
	 */
	CkInfoEntity findckinfobysrln(@Param(value="srln")String srln,@Param(value="rckCount")String rckCount);
	
	/**
	 * @根据流水号查询打印信息
	 * @param String srln 流水号
	 * @return CkInfoDto 打印信息
	 */

	 CkInfoDto findbysrln(@Param(value="srln")String srln,@Param(value="rckCount")String rckCount);
	 
	 
	 
	 List<CkInfoDto> findCkInfoByCd(String clsbdh,String currentDate);
	 
	 /**
	  * 修改查验信息
	  * 
	  * @param ckInfoEntity
	  */
	 void  updateCkInfo(CkInfoEntity ckInfoEntity);
	 
	 
	 
}