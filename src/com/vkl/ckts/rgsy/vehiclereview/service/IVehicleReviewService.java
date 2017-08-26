package com.vkl.ckts.rgsy.vehiclereview.service;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.vkl.ckts.common.entity.ChkptFileEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.rgsy.vehiclereview.dao.IVehicleReviewDao;
import com.vkl.ckts.rgsy.vehiclereview.entity.CkInfoEntityDto;
import com.vkl.ckts.rgsy.vehiclereview.entity.ShtjDto;
/**
 * 查验审核Service接口
 * @author Administrator
 *
 */
public interface IVehicleReviewService extends IService<IVehicleReviewDao,CkInfoEntityDto,String>{
	/**
	 * 分页查询审核信息
	 * @param page					 分页对象
	 * 
	 * @return List<CkInfoEntityDto> 数据集合
	 */
	public Page<CkInfoEntityDto> findByPage(Page<CkInfoEntityDto> page);
	/**
	 * 根据流水号查询信息
	 * @param srln					流水号
	 * @return CkInfoEntityDto		数据对象
	 */
	CkInfoEntityDto findVehicleInfo(@Param(value="srln")String srln,@Param(value="dictType")String dictType,@Param(value="rckCount")String rckCount);
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
	 * 更新状态
	 * @param arr		数组
	 * @param auditFlag 状态
	 * @param srln 		流水号
	 * @return
	 */
	public int updData(String[] arr,String pAuditFlag,String srln,String rckCount);
	
	public int updaExamine(String auditFlag,String srln,String rckCount,String remarks,String pAuditer,Date pAuditerTime);
	ShtjDto findShTj();
	
	 List<ChkptFileEntity>   findChkpts();
	Page<CkInfoEntityDto> findVehicleList(Page<CkInfoEntityDto> page);
	
	/**
	 * 根据备案编号获取签名信息
	 * 
	 * @param fileId
	 * 
	 * @return
	 */
	Map<String, Object>  findCkerQmView(String fileId);
}
