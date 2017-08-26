package com.vkl.ckts.cksy.vehicleinformation.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vkl.ckts.cksy.vehicleinformation.dao.IVehicleInfoDao;
import com.vkl.ckts.cksy.vehicleinformation.entity.CkInfoEntityDto;
import com.vkl.ckts.common.entity.DeptEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IService;
/**
 * 车辆信息Service接口
 * @author Administrator
 *
 */
public interface IVehicleInfoService extends IService<IVehicleInfoDao,CkInfoEntityDto,String>{
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
	 * 删除查验信息
	 * @param srln
	 * @param rckCount
	 */
	void deleteCkInfo(String srln,String rckCount);
	
	DeptEntity findDeptById(String id);
	
}
