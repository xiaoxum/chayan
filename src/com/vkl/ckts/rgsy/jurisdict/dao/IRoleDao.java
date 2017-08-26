package com.vkl.ckts.rgsy.jurisdict.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.MenuEntity;
import com.vkl.ckts.common.entity.RoleEntity;
import com.vkl.ckts.common.entity.RoleMenuEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.rgsy.jurisdict.entity.RoleDto;

/**
 * 角色管理Dao
 *
 * @author hwf
 * @version 1.0
 */
@Mybatis
public interface IRoleDao extends Dao<RoleEntity, String> {

	/**
	 * 添加角色
	 *
	 * @param roleEntity
	 * @return
	 * @throws Exception
	 */
	void addRole(RoleDto roleEntity) throws Exception;

	/**
	 * 根据角色编号逻辑删除角色
	 * 
	 * @param roleEntity
	 */
	void deleteRoleById(RoleEntity roleEntity) throws Exception;

	/**
	 * 查找角色（根据当前登录用户的部门类型和角色名称查找）
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
	 * 角色分页
	 *
	 * @param roleEntity
	 * @return
	 * @throws Exception
	 */
	List<RoleDto> pageRole(Page<RoleDto> page) throws Exception;

	/**
	 * 查找所有角色（根据当前登录用户的部门类型查找）
	 *
	 * @param roleEntity
	 * @return
	 * @throws Exception
	 */
	List<RoleEntity> findRoleList(RoleDto roleEntity) throws Exception;

	/**
	 * 修改角色
	 *
	 * @param roleEntity
	 * @throws Exception
	 */
	void updateRole(RoleEntity roleEntity) throws Exception;
	

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
	List<MenuEntity> findMenuListByParentId(@Param(value="parentMenu")String parentMenu,@Param(value="yhbh")String yhbh);
	
	

}
