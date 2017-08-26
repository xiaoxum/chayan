package com.vkl.ckts.cksy.vehicleinformation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vkl.ckts.cksy.vehicleinformation.entity.CkInfoEntityDto;
import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.DeptEntity;
import com.vkl.ckts.common.page.Page;

/**
 * 车辆信息Dao
 * @author Administrator
 *
 */
@Mybatis
public interface IVehicleInfoDao extends Dao<CkInfoEntityDto,String>{
	/**
	 * 分页查询车辆信息
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
	public int updataInfo(@Param(value="pAuditFlag")String pAuditFlag,@Param(value="srln")String srln,@Param(value="rckCount")String rckCount);
	
	
	/**
	 * 删除查验信息
	 * @param srln
	 * @param rckCount
	 */
	void deleteCkInfo(String srln,String rckCount);
	
	
	DeptEntity findDeptById(String id);
	
	void deletePicRcords(String srln,String rckCount);
	
	void deleteProjectRcords(String srln,String rckCount);
}
