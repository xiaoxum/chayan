package com.vkl.ckts.rgsy.dept.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.base.Message;
import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.constr.ErrorInfo;
import com.vkl.ckts.common.entity.DeptEntity;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.rgsy.dept.dao.IDeptDao;
import com.vkl.ckts.rgsy.dept.service.IDeptService;

/**
 * 部门ServiceImpl
 *
 * @author hwf
 * @version 1.0
 */
@Service
@Transactional
public class DeptServiceImpl extends ServiceImpl<IDeptDao, DeptEntity, String> implements IDeptService {

	// 日志
	Logger log = Logger.getLogger(DeptServiceImpl.class);
	/**
	 * 根据父类id查找所属部门
	 *
	 * @return
	 */
	public List<DeptEntity> findSubDeptByParentId(DeptEntity deptEntity) throws Exception {
		return super.dao.findSubDeptByParentId(deptEntity);
	}

	/**
	 * 根据父类id查找所有部门
	 *
	 * @return
	 */
	public List<DeptEntity> findAllDeptByParentId(DeptEntity deptEntity) throws Exception {
		return super.dao.findAllDeptByParentId(deptEntity);
	}
	
	/**
	 * 添加部门
	 *
	 * @param deptEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean addDept(DeptEntity deptEntity, HttpServletRequest request) throws Exception {
		// 判断当前用户是否为空
		if (request.getSession().getAttribute(Constrant.S_USER_SESSION) == null) {
			Message<String> message = new Message<String>();
			message.setErrorCode(ErrorInfo.S_USER_SESSION_NULL);
			log.error(message.getErrorMsg());

			return false;
		}		
		// 设置创建时间
		deptEntity.setCreateDate(new Date());
		// 设置创建人
		deptEntity.setCreater(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId());
		// 设置修改人
		deptEntity.setUpdater(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId());
		// 设置修改时间
		deptEntity.setUpdDate(new Date());
		// 设置删除标识为正常
		deptEntity.setDelFlag(IdEntity.DEL_FLAG_NORMAL);
		if (super.dao.addDept(deptEntity) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 查找部门
	 *
	 * @param deptEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public DeptEntity findDept(DeptEntity deptEntity) throws Exception {
		return super.dao.findDept(deptEntity);
	}

	/**
	 * 查找删除的部门
	 *
	 * @param deptEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public DeptEntity findDeleteDept(DeptEntity deptEntity) throws Exception {

		return super.dao.findDeleteDept(deptEntity);
	}

	/**
	 * 恢复删除的部门
	 *
	 * @param deptEntity
	 * @throws Exception
	 */
	@Override
	public void recoverDeleteDept(DeptEntity deptEntity, HttpServletRequest request) throws Exception {
		// 设置修改人
		deptEntity.setUpdater(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId());
		// 设置修改时间
		deptEntity.setUpdDate(new Date());
		super.dao.recoverDeleteDept(deptEntity);
	}
	
	/**
	 * 分页查询部门
	 * 
	 * @param page 分页工具实体
	 * @return 部门集合
	 */
	@Override
	public Page<DeptEntity> pageDept(Page<DeptEntity> page) throws Exception {
		page.setList(super.dao.pageDept(page));
		return page;
	}

	/**
	 * 根据部门编号逻辑删除部门
	 * 
	 * @param deptEntity
	 */
	@Override
	public void deleteDeptById(DeptEntity deptEntity) throws Exception {
		super.dao.deleteDeptById(deptEntity);
		
	}

	
	/**
	 * 更新部门
	 *
	 * @param deptEntity
	 * @param request
	 * @return true：更新成功，fasle：更新失败
	 */
	@Override
	public boolean updateDept(DeptEntity deptEntity, HttpServletRequest request) throws Exception {
		// 判断当前用户session是否为空
		if (request.getSession().getAttribute(Constrant.S_USER_SESSION) == null) {
			Message<String> message = new Message<String>();
			message.setErrorCode(ErrorInfo.S_USER_SESSION_NULL);
			log.error(message.getErrorMsg());

			return false;
		}
		// 获取当前时间并赋值
		deptEntity.setUpdDate(new Date());
		// 获取当前操作用户并赋值
		deptEntity.setUpdater(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId().toString());
		super.dao.updateDept(deptEntity);

		return true;
	}

	/**
	 * 物理删除部门信息
	 *
	 * @param deptEntity
	 * @throws Exception
	 */
	@Override
	public void physicsDeleteDept(DeptEntity deptEntity) throws Exception {
		super.dao.physicsDeleteDept(deptEntity);
	}

	/**
	 * 根据id查找部门
	 *
	 * @param deptEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public DeptEntity findDeptById(DeptEntity deptEntity) throws Exception {
		return super.dao.findDeptById(deptEntity);
	}


}
