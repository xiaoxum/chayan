package com.vkl.ckts.rgsy.record.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.vkl.ckts.common.entity.ChkptFileEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.rgsy.record.dao.IChkptFileDao;
import com.vkl.ckts.rgsy.record.entity.ChkptFileDto;

/**
 * 查验区备案Service
 * 
 * @author hwf
 * @version 1.0
 */
public interface IChkptFileService extends IService<IChkptFileDao, ChkptFileEntity, String> {
	
	/**
	 * 添加查验区备案申请
	 *
	 * @param chkptFileEntity
	 * @param request
	 * @return true：添加成功，false：添加失败
	 */
	boolean addChkptFile(ChkptFileDto chkptFileEntity, HttpServletRequest request) throws Exception;

	/**
	 * 分页查询查验区备案信息
	 * 
	 * @param page 分页工具实体
	 * @return 查验区集合
	 */
	Page<ChkptFileDto> pageChkptFile(Page<ChkptFileDto> page) throws Exception;
	
	/**
	 * 查询备案通过的查验区列表
	 *
	 * @param chkptFileEntity
	 * @return
	 * @throws Exception
	 */
	List<ChkptFileDto> findChkptList(ChkptFileDto chkptFileEntity) throws Exception;
	
	/**
	 * 根据查验区编号逻辑删除备案信息
	 * 
	 * @param chkptFileEntity
	 */
	void deleteChkptFileById(ChkptFileDto chkptFileEntity) throws Exception;
	
	/**
	 * 根据查验区编号查找备案信息
	 * 
	 * @param chkptFileEntity
	 * @return chkptFileEntity
	 */
	ChkptFileDto findChkptFileById(ChkptFileDto chkptFileEntity) throws Exception;
	
	/**
	 * 更新备案信息
	 *
	 * @param chkptFileEntity
	 * @param request
	 * @return true：更新成功，fasle：更新失败
	 */
	boolean updateChkptFile(ChkptFileDto chkptFileEntity, HttpServletRequest request) throws Exception;
}
