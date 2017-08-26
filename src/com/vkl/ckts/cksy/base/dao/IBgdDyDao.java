package com.vkl.ckts.cksy.base.dao;

import java.util.List;
import java.util.Map;

import com.vkl.ckts.cksy.base.dto.PicRecordDto;
import com.vkl.ckts.cksy.base.dto.ProjectRecordDto;
import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.entity.CkProjectEntity;
import com.vkl.ckts.common.entity.VehInfoEntity;
/**
 * 报告单自动打印
 * 
 * @author xiaoxu
 * 
 * @version 1.0
 *
 */
@Mybatis
public interface IBgdDyDao {
	/**
	 * 获取查验信息
	 * 
	 * @return
	 */
	List<Map<String,Object>>  findCkInfo();
	
	
	/**
	 * 查询单个查验业务用于打印 
	 * 
	 * @param srln     流水号 
	 * @param rckCount 复检次数 
	 * 
	 * @return
	 */
	Map<String,Object>  findCkOne(String srln,String rckCount);
	
	
	
	/**
	 * 根据备案编号获取签名信息
	 * 
	 * @param fileId
	 * 
	 * @return
	 */
	Map<String, Object>  findCkerQm(String fileId);
	/**
	 * 获取查验项目记录
	 * 
	 * @return
	 */
	List<ProjectRecordDto> findCkItem(String  srln);
	/**
	 * 获取所有查验项目
	 * 
	 * @return
	 */
	List<CkProjectEntity> findProject();
	
	
	/**
	 * 
	 */
	PicRecordDto findPicById(String srln,String proId);
	
	/**
	 * 更新打印状态
	 * 
	 * @param srln
	 * @param rckCount
	 */
	void updatedyStatu(String srln, String rckCount);
	/**
	 * 查询照片打印记录
	 * 
	 * @param srln      
	 * @param operType   
	 * @param syxz
	 * @param cllx
	 * @return
	 */
	List<Map<String, Object>>  findPhotoRecords(String srln ,String operType,String syxz,String cllx);
	
	
	/**
	 * 查询单条查验信息照片打印记录
	 * 
	 * @param srln      
	 * @param operType   
	 * @param syxz
	 * @param cllx
	 * @return
	 */
	List<Map<String, Object>>  findPhotoRecordsOneCK(String srln ,String operType,String syxz,String cllx);
	
	/**
	 * 查询父级车型 
	 * 
	 * @param cllx
	 * 
	 * @return
	 */
	String findParentCllx(String cllx);
	
	/**
	 * 查询车型名称
	 * 
	 * @param cllx
	 * 
	 * @return
	 */
	String findCllxName(String cllx);
	/**
	 * 更新照片打印状态
	 * 
	 * @param srln     流水号 
	 * @param rckCount 复检次数 
	 */
	void  updatePicDyStatu(String srln,String rckCount,String proId);
	
	
	/**
	 * 查询父级车型 
	 * 
	 * @param syxz 使用性质
	 * 
	 * @return
	 */
	String findParentSyxz(String syxz);
	
	/**
	 * 获取车辆信息
	 * 
	 * @param srln   流水号
	 * 
	 * @return
	 */
	VehInfoEntity findVehInfo(String srln);
	
	
	/**
	 * 根据备案编号获取签名信息
	 * 
	 * @param fileId
	 * 
	 * @return
	 */
	Map<String, Object>  findCkerQmView(String fileId);
	
}
