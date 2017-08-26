package com.vkl.ckts.rgsy.record.service;

import javax.servlet.http.HttpServletRequest;

import com.vkl.ckts.common.entity.GbrFileEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.rgsy.record.dao.IGbrFileDao;

/**
 * 外廓设备备案Service
 *
 * @author hwf
 * @version 1.0
 */
public interface IGbrFileService extends IService<IGbrFileDao, GbrFileEntity, String> {
	/**
	 * 添加Gbr备案申请
	 * 
	 * @param GbrDeviceFifleEntity Gbr备案实体
	 * @param request
	 * @return true：添加成功，false：添加失败
	 */
	boolean addGbrFile(GbrFileEntity gbrFileEntity, HttpServletRequest request) throws Exception;
	
	/**
	 * 分页查询Gbr备案信息
	 * 
	 * @param page 分页工具实体
	 * @return Gbr集合
	 */
	Page<GbrFileEntity> pageGbrFile(Page<GbrFileEntity> page) throws Exception;
	
	/**
	 * 根据Gbr编号逻辑删除备案信息
	 * 
	 * @param GbrDeviceFifleEntity
	 */
	void deleteGbrFileById(GbrFileEntity gbrFileEntity) throws Exception;
	
	/**
	 * 根据Gbr编号查找备案信息
	 * 
	 * @param GbrDeviceFifleEntity
	 * @return GbrDeviceFifleEntity
	 */
	GbrFileEntity findGbrFileById(GbrFileEntity gbrFileEntity) throws Exception;
	
	/**
	 * 更新备案信息
	 *
	 * @param GbrDeviceFifleEntity
	 * @param request
	 * @return true：更新成功，fasle：更新失败
	 */
	boolean updateGbrFile(GbrFileEntity gbrFileEntity, HttpServletRequest request) throws Exception;

}
