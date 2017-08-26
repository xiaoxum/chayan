package com.vkl.ckts.rgsy.record.service;

import javax.servlet.http.HttpServletRequest;

import com.vkl.ckts.common.entity.PdaDeviceFifleEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.rgsy.record.dao.IPdaFileDao;
import com.vkl.ckts.rgsy.record.entity.PdaDeviceFifleDto;

/**
 * Pda备案Service
 *
 * @author hwf
 * @version 1.0
 */
public interface IPdaFileService extends IService<IPdaFileDao, PdaDeviceFifleEntity, String> {
	
	/**
	 * 添加pda备案申请
	 * 
	 * @param pdaDeviceFifleEntity pda备案实体
	 * @param request
	 * @return true：添加成功，false：添加失败
	 */
	String addPdaFile(PdaDeviceFifleEntity pdaDeviceFifleEntity, HttpServletRequest request) throws Exception;
	
	/**
	 * 分页查询pda备案信息
	 * 
	 * @param page 分页工具实体
	 * @return pda集合
	 */
	Page<PdaDeviceFifleDto> pagePdaFile(Page<PdaDeviceFifleDto> page) throws Exception;
	
	/**
	 * 根据pda编号逻辑删除备案信息
	 * 
	 * @param pdaDeviceFifleEntity
	 */
	void deletePdaFileById(PdaDeviceFifleDto pdaDeviceFifleEntity) throws Exception;
	
	/**
	 * 根据pda编号查找备案信息
	 * 
	 * @param pdaDeviceFifleEntity
	 * @return pdaDeviceFifleEntity
	 */
	PdaDeviceFifleEntity findPdaFileById(PdaDeviceFifleEntity pdaDeviceFifleEntity) throws Exception;
	
	/**
	 * 更新备案信息
	 *
	 * @param pdaDeviceFifleEntity
	 * @param request
	 * @return true：更新成功，fasle：更新失败
	 */
	boolean updatePdaFile(PdaDeviceFifleEntity pdaDeviceFifleEntity, HttpServletRequest request) throws Exception;

}
