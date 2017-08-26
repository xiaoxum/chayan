package com.vkl.ckts.rgsy.dept.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.vkl.ckts.common.entity.DeptEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.rgsy.dept.dao.IDeptDao;

/**
 * 部门service
 *
 * @author hwf
 * @version 1.0
 */
public interface IDeptService extends IService<IDeptDao, DeptEntity, String> {
	
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
	boolean addDept(DeptEntity deptEntity,HttpServletRequest request) throws Exception;
	
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
	void recoverDeleteDept(DeptEntity deptEntity, HttpServletRequest request) throws Exception;
	
	/**
	 * 分页查询部门
	 * 
	 * @param page 分页工具实体
	 * @return 部门集合
	 */
	Page<DeptEntity> pageDept(Page<DeptEntity> page) throws Exception;
	
	/**
	 * 根据部门编号逻辑删除部门
	 * 
	 * @param deptEntity
	 */
	void deleteDeptById(DeptEntity deptEntity) throws Exception;
	
	
	/**
	 * 更新部门
	 *
	 * @param deptEntity
	 * @param request
	 * @return true：更新成功，fasle：更新失败
	 */
	boolean updateDept(DeptEntity deptEntity, HttpServletRequest request) throws Exception;
	
	/**
	 * 物理删除部门信息
	 *
	 * @param deptEntity
	 * @throws Exception
	 */
	void physicsDeleteDept(DeptEntity deptEntity ) throws Exception;

}
