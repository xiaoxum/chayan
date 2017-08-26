package com.vkl.ckts.rgsy.jurisdict.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import com.vkl.ckts.common.entity.MenuEntity;
import com.vkl.ckts.common.entity.RoleEntity;
import com.vkl.ckts.common.entity.RoleMenuEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.rgsy.jurisdict.dao.IRoleDao;
import com.vkl.ckts.rgsy.jurisdict.entity.RoleDto;

/**
 * 角色管理Service
 *
 * @author hwf
 * @version 1.0
 */
public interface IRoleService extends IService<IRoleDao, RoleEntity, String> {
	/**
	 * 添加角色
	 *
	 * @param roleEntity
	 * @return
	 * @throws Exception
	 */
	void addRole(RoleDto roleEntity, HttpServletRequest request) throws Exception;

	/**
	 * 根据角色编号逻辑删除角色
	 * 
	 * @param roleEntity
	 */
	void deleteRoleById(RoleEntity roleEntity) throws Exception;

	/**
	 * 查找角色
	 *
	 * @param roleEntity
	 * @return
	 * @throws Exception
	 */
	RoleEntity findRole(RoleEntity roleEntity) throws Exception;
	
	/**
	 * 根据id查找角色
	 *
	 * @param roleEntity
	 * @return
	 * @throws Exception
	 */
	RoleEntity findRoleById(RoleEntity roleEntity) throws Exception;

	/**
	 * 分页查询角色
	 * 
	 * @param page 分页工具实体
	 * @return 部门集合
	 */
	Page<RoleDto> pageRole(Page<RoleDto> page) throws Exception;
	
	/**
	 * 查找所有角色（根据当前登录用户的部门类型查找）
	 *
	 * @param roleEntity
	 * @return
	 * @throws Exception
	 */
	List<RoleEntity> findRoleList(RoleDto roleEntity ,HttpServletRequest request) throws Exception;

	/**
	 * 修改角色
	 *
	 * @param roleEntity
	 * @throws Exception
	 * @return true：更新成功，fasle：更新失败
	 */
	boolean updateRole(RoleEntity roleEntity, HttpServletRequest request) throws Exception;
	
	/**
	 * 添加角色权限
	 *
	 * @param roleMenuEntity
	 * @throws Exception
	 */
	void addRoleMenu(RoleMenuEntity roleMenuEntity) throws Exception;
	
	/**
	 * 删除角色权限
	 *
	 * @param roleMenuEntity
	 * @throws Exception
	 */
	void deleteRoleMenu(RoleMenuEntity roleMenuEntity) throws Exception; 
	
	/**
	 * 查找角色的权限集合
	 *
	 * @param roleMenuEntity
	 * @return
	 * @throws Exception
	 */
	List<RoleMenuEntity> findRoleMenuList(RoleMenuEntity roleMenuEntity) throws Exception;
	
	
	/**
	 * 查询菜单信息
	 *
	 * @param roleMenuEntity
	 * @return
	 * @throws Exception
	 */
	List<MenuEntity> findMenuListByParentId(String parentMenu,String yhbh);

}
