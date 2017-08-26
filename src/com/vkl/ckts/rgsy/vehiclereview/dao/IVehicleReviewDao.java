package com.vkl.ckts.rgsy.vehiclereview.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.ChkptFileEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.rgsy.vehiclereview.entity.CkInfoEntityDto;
import com.vkl.ckts.rgsy.vehiclereview.entity.ShtjDto;

/**
 * 查验审核Dao
 * @author Administrator
 *
 */
@Mybatis
public interface IVehicleReviewDao extends Dao<CkInfoEntityDto,String>{
	/**
	 * 分页查询审核信息
	 * @param page					 分页对象
	 * 
	 * @return List<CkInfoEntityDto> 数据集合
	 */
	public List<CkInfoEntityDto> findByPage(Page<CkInfoEntityDto> page);
	/**
	 * 查询总条数
	 * @param ckInfoEntityDto 	查询对象
	 * @return int 				总条数
	 */
	public int countNum(CkInfoEntityDto ckInfoEntityDto);
	/**
	 * 根据流水号查询信息
	 * @param srln					流水号
	 * @return CkInfoEntityDto		数据对象
	 */
	CkInfoEntityDto findVehicleInfo(@Param(value="srln")String srln,@Param(value="rckCount")String rckCount,@Param(value="dictType")String dictType);
	/**
	 * 查出所有类型
	 * @param opertypes
	 * @return
	 */
	public List<String> findAllOperType(String[] opertypes);
	
	/**
	 * 根据查验员编号查询
	 * @param ckers
	 * @return
	 */
	public List<String> findAllCker(String[] ckers);
	/**
	 * 更新审核状态
	 * @param auditFlag
	 * @param srln
	 * @return
	 */
	public int updataInfo(@Param(value="auditFlag")String pAuditFlag,
			@Param(value="srln")String srln,
			@Param(value="rckCount")String rckCount,
			@Param(value="remarks")String remarks,
			@Param(value="pAuditer")String pAuditer,
			@Param(value="pAuditerTime")Date pAuditerTime);
	
	 ShtjDto findShTj(@Param(value="pdaEnd")String nowDate);
	
     List<CkInfoEntityDto> findVehicleList(Page<CkInfoEntityDto> page);
     List<ChkptFileEntity>   findChkpts();
	 
	 Integer vehicleCount(CkInfoEntityDto ckInfoEntityDto);
	 
	 
	 /**
		 * 根据备案编号获取签名信息
		 * 
		 * @param fileId
		 * 
		 * @return
		 */
		Map<String, Object>  findCkerQmView(String fileId);
}
