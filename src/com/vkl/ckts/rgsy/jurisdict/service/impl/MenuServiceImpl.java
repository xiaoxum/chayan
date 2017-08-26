package com.vkl.ckts.rgsy.jurisdict.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.entity.MenuEntity;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.rgsy.jurisdict.dao.IMenuDao;
import com.vkl.ckts.rgsy.jurisdict.service.IMenuService;

/**
 * 权限管理Service
 *
 * @author hwf
 * @version 1.0
 */
@Service
@Transactional
public class MenuServiceImpl extends ServiceImpl<IMenuDao, MenuEntity, String> implements IMenuService {

	/**
	 * 查找权限集合（根据当前登录用户的部门类型查找）
	 *
	 * @param menuEntity
	 * @return
	 */
	public List<MenuEntity> findMenuList(MenuEntity menuEntity) throws Exception{
		return super.dao.findMenuList(menuEntity);
	}

	/**
	 * 根据父类id查找子权限
	 *
	 * @param menuEntity
	 * @return
	 */
	public List<MenuEntity> findMenuListByParentId(MenuEntity menuEntity) throws Exception{
		return super.dao.findMenuListByParentId(menuEntity);
	}
	
	/**
	 * 根据id查找权限
	 *
	 * @param menuEntity
	 * @return
	 * @throws Exception
	 */
	public MenuEntity findMenuById(MenuEntity menuEntity) throws Exception{
		return super.dao.findMenuById(menuEntity);
	}
}
