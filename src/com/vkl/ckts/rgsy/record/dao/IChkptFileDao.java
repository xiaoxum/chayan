package com.vkl.ckts.rgsy.record.dao;

import java.util.List;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.ChkptFileEntity;
import com.vkl.ckts.common.entity.DeptEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.rgsy.record.entity.ChkptFileDto;

/**
 * 查验区备案 Dao
 * 
 * @author hwf
 * @version 1.0
 */
@Mybatis
public interface IChkptFileDao extends Dao<ChkptFileEntity, String> {

	/**
	 * 添加查验区备案申请
	 * 
	 * @param chkptFileEntity 查验区备案实体
	 * @return 插入成功的数据个数
	 */
	Integer addChkptFile(ChkptFileDto chkptFileEntity) throws Exception;
	
	
	/**
	 * 分页查询查验区备案信息
	 * 
	 * @param page 分页工具实体
	 * @return 查验区集合
	 */
	List<ChkptFileDto> pageChkptFile(Page<ChkptFileDto> page) throws Exception;
	
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
	 *  更新备案信息
	 *  
	 * @param chkptFileEntity
	 */
	void updateChkptFile(ChkptFileDto chkptFileEntity) throws Exception;
	
	
	/**
	 * 添加部门
	 *
	 * @param deptEntity
	 * @return
	 * @throws Exception
	 */
	Integer addDept(DeptEntity deptEntity) throws Exception;

}
