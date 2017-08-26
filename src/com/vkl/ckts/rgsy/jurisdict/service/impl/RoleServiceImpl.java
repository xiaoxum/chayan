package com.vkl.ckts.rgsy.jurisdict.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.base.Message;
import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.constr.ErrorInfo;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.entity.MenuEntity;
import com.vkl.ckts.common.entity.RoleEntity;
import com.vkl.ckts.common.entity.RoleMenuEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.pub.service.IBaseService;
import com.vkl.ckts.rgsy.jurisdict.dao.IRoleDao;
import com.vkl.ckts.rgsy.jurisdict.entity.RoleDto;
import com.vkl.ckts.rgsy.jurisdict.service.IRoleService;

/**
 * 角色管理Impl
 *
 * @author hwf
 * @version 1.0
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<IRoleDao, RoleEntity, String> implements IRoleService {

	// 注入用户Service
	@Autowired
	IBaseService baseService;
	// 日志
	Logger log = Logger.getLogger(RoleServiceImpl.class);

	/**
	 * 添加角色
	 *
	 * @param roleEntity
	 * @return
	 * @throws Exception
	 */
	public void addRole(RoleDto roleEntity, HttpServletRequest request) throws Exception {
		// 判断当前用户是否为空
		// 设置创建时间
		roleEntity.setCreateDate(new Date());
		UserEntity userEntity = (UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION);
		// 设置创建人
		roleEntity.setCreater(userEntity.getId());
		// 设置修改人
		roleEntity.setUpdater(userEntity.getId());
		// 设置修改时间
		roleEntity.setUpdDate(new Date());
		// 设置删除标识为正常
		roleEntity.setDelFlag(IdEntity.DEL_FLAG_NORMAL);
		roleEntity.setDeptId(userEntity.getUserDept()+"");
		// 设置角色所属
		// 判断当前用户的所属部门类型，如果是市车管所或者县区车管所则赋值为车管所，否则赋值为查验站
		String deptType = baseService.findUserDeptType((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION));
		if (deptType.equals(IdEntity.DEPT_TYPE_CITY_OFFICE) || deptType.equals(IdEntity.DEPT_TYPE_COUNTY_OFFICE)) {
			deptType = IdEntity.S_MENU_OWER_VEHICLE_OFFICE;
		}else{
			deptType = IdEntity.S_MENU_OWER_CHECK_OFFICE;
		}
		roleEntity.setRoleOwer(deptType);		
		super.dao.addRole(roleEntity);
	}

	/**
	 * 根据角色编号逻辑删除角色
	 * 
	 * @param roleEntity
	 */
	public void deleteRoleById(RoleEntity roleEntity) throws Exception {
		super.dao.deleteRoleById(roleEntity);
	}

	/**
	 * 查找角色
	 *
	 * @param roleEntity
	 * @return
	 * @throws Exception
	 */
	public RoleEntity findRole(RoleEntity roleEntity) throws Exception {
		return super.dao.findRole(roleEntity);
	}
	
	/**
	 * 根据id查找角色
	 *
	 * @param roleEntity
	 * @return
	 * @throws Exception
	 */
	public RoleEntity findRoleById(RoleEntity roleEntity) throws Exception {
		return super.dao.findRoleById(roleEntity);
	}

	/**
	 * 分页查询角色
	 * 
	 * @param page 分页工具实体
	 * @return 部门集合
	 */
	@Override
	public Page<RoleDto> pageRole(Page<RoleDto> page) throws Exception {
		page.setList(super.dao.pageRole(page));
		return page;
	}
	
	/**
	 * 查找所有角色（根据当前登录用户的部门类型查找）
	 *
	 * @param roleEntity
	 * @return
	 * @throws Exception
	 */
	public List<RoleEntity> findRoleList(RoleDto roleEntity,HttpServletRequest request) throws Exception {
		// 设置角色所属
		UserEntity userEntity = (UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION);
		// 判断当前用户的所属部门类型，如果是市车管所或者县区车管所则赋值为车管所，否则赋值为查验站
		
		if ("1".equals(userEntity.getSfgly())) {
			roleEntity.setDeptId(null);
		}else {
			roleEntity.setDeptId(userEntity.getUserDept()+"");
		}
		return super.dao.findRoleList(roleEntity);
	}

	/**
	 * 修改角色
	 *
	 * @param roleEntity
	 * @throws Exception
	 * @return true：更新成功，fasle：更新失败
	 */
	public boolean updateRole(RoleEntity roleEntity, HttpServletRequest request) throws Exception{
		// 判断当前用户session是否为空
		if (request.getSession().getAttribute(Constrant.S_USER_SESSION) == null) {
			Message<String> message = new Message<String>();
			message.setErrorCode(ErrorInfo.S_USER_SESSION_NULL);
			log.error(message.getErrorMsg());

			return false;
		}
		// 获取当前时间并赋值
		roleEntity.setUpdDate(new Date());
		// 获取当前操作用户并赋值
		roleEntity.setUpdater(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId().toString());
		super.dao.updateRole(roleEntity);

		return true;		
	}
	
	/**
	 * 添加角色权限
	 *
	 * @param roleMenuEntity
	 * @throws Exception
	 */
	public void addRoleMenu(RoleMenuEntity roleMenuEntity) throws Exception{
		super.dao.addRoleMenu(roleMenuEntity);
	}
	
	/**
	 * 删除角色权限
	 *
	 * @param roleMenuEntity
	 * @throws Exception
	 */
	public void deleteRoleMenu(RoleMenuEntity roleMenuEntity) throws Exception{
		super.dao.deleteRoleMenu(roleMenuEntity);
	}
	
	/**
	 * 查找角色的权限集合
	 *
	 * @param roleMenuEntity
	 * @return
	 * @throws Exception
	 */
	public List<RoleMenuEntity> findRoleMenuList(RoleMenuEntity roleMenuEntity) throws Exception {
		return super.dao.findRoleMenuList(roleMenuEntity);
	}
    
	/**
	 * 根据id查找权限
	 *
	 * @param menuEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<MenuEntity> findMenuListByParentId(String parentMenu,
			String yhbh) {
		// TODO Auto-generated method stub
		return super.dao.findMenuListByParentId(parentMenu, yhbh);
	}

}
