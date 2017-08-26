package com.vkl.ckts.rgsy.jurisdict.dao;

import java.util.List;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.MenuEntity;

/**
 * 权限管理Dao
 *
 * @author hwf
 * @version 1.0
 */
@Mybatis
public interface IMenuDao extends Dao<MenuEntity, String> {
	
	/**
	 * 查找权限集合（根据当前登录用户的部门类型查找）
	 *
	 * @param menuEntity
	 * @return
	 */
	List<MenuEntity> findMenuList(MenuEntity menuEntity) throws Exception;
	
	/**
	 * 根据父类id查找子权限
	 *
	 * @param menuEntity
	 * @return
	 */
	List<MenuEntity> findMenuListByParentId(MenuEntity menuEntity) throws Exception;
	
	/**
	 * 根据id查找权限
	 *
	 * @param menuEntity
	 * @return
	 * @throws Exception
	 */
	MenuEntity findMenuById(MenuEntity menuEntity) throws Exception;

}
