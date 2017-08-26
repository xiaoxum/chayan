package com.vkl.ckts.rgsy.record.dao;

import java.util.List;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.PdaDeviceFifleEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.rgsy.record.entity.PdaDeviceFifleDto;

/**
 * PDA备案Dao
 *
 * @author hwf
 * @version 1.0
 */
@Mybatis
public interface IPdaFileDao extends Dao<PdaDeviceFifleEntity, String> {
	
	/**
	 * 添加pda备案申请
	 * 
	 * @param pdaDeviceFifleEntity pda备案实体
	 * @return 插入成功的数据个数
	 */
	Integer addPdaFile(PdaDeviceFifleEntity pdaDeviceFifleEntity) throws Exception;
	
	/**
	 * 分页查询pda备案信息
	 * 
	 * @param page 分页工具实体
	 * @return 查验区集合
	 */
	List<PdaDeviceFifleDto> pagePdaFile(Page<PdaDeviceFifleDto> page) throws Exception;
	
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
	 *  更新备案信息
	 *  
	 * @param pdaDeviceFifleEntity
	 */
	void updatePdaFile(PdaDeviceFifleEntity pdaDeviceFifleEntity) throws Exception;
	
	
	/**
	 * 
	 * @param mac
	 * @return
	 */
	PdaDeviceFifleEntity findPdaFileByMac(String mac);

}
