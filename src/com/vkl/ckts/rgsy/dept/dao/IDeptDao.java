package com.vkl.ckts.rgsy.dept.dao;

import java.util.List;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.DeptEntity;
import com.vkl.ckts.common.page.Page;

/**
 * 部门dao
 *
 * @author hwf
 * @version 1.0
 */
@Mybatis
public interface IDeptDao extends Dao<DeptEntity, String> {

	
	/**
	 * 根据父类id查找所属部门
	 *
	 * @return
	 */
	List<DeptEntity> findSubDeptByParentId(DeptEntity deptEntity) throws Exception;
	
	/**
	 * 根据父类id查找所有部门
	 *
	 * @return
	 */
	List<DeptEntity> findAllDeptByParentId(DeptEntity deptEntity) throws Exception;
	
	/**
	 * 添加部门
	 *
	 * @param deptEntity
	 * @return
	 * @throws Exception
	 */
	Integer addDept(DeptEntity deptEntity) throws Exception;

	/**
	 * 查找部门
	 *
	 * @param deptEntity
	 * @return
	 * @throws Exception
	 */
	DeptEntity findDept(DeptEntity deptEntity) throws Exception;
	
	/**
	 * 根据id查找部门
	 *
	 * @param deptEntity
	 * @return
	 * @throws Exception
	 */
	DeptEntity findDeptById(DeptEntity deptEntity) throws Exception;

	/**
	 * 查找删除的部门
	 *
	 * @param deptEntity
	 * @return
	 * @throws Exception
	 */
	DeptEntity findDeleteDept(DeptEntity deptEntity) throws Exception;

	/**
	 * 恢复删除的部门
	 *
	 * @param deptEntity
	 * @throws Exception
	 */
	void recoverDeleteDept(DeptEntity deptEntity) throws Exception;

	/**
	 * 分页查询部门
	 * 
	 * @param page
	 *            分页工具实体
	 * @return 部门集合
	 */
	List<DeptEntity> pageDept(Page<DeptEntity> page) throws Exception;

	/**
	 * 根据部门编号逻辑删除部门
	 * 
	 * @param deptEntity
	 */
	void deleteDeptById(DeptEntity deptEntity) throws Exception;

	/**
	 * 更新部门信息
	 * 
	 * @param deptEntity
	 */
	void updateDept(DeptEntity deptEntity) throws Exception;
	
	/**
	 * 物理删除部门信息
	 *
	 * @param deptEntity
	 * @throws Exception
	 */
	void physicsDeleteDept(DeptEntity deptEntity ) throws Exception;

}
