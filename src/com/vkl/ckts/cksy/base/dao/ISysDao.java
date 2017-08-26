package com.vkl.ckts.cksy.base.dao;

import java.util.List;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.entity.MenuEntity;


/**
 * 系统
 * 
 * @author xiaoxu
 *
 * @version 1.0
 */
@Mybatis
public interface ISysDao{

	/**
	 * 获取当期用户的子菜单信息
	 * 
	 * @param yhid    用户id
	 * @param cdzt    子菜单状态
	 * 
	 * @return        当前用户的菜单子功能信息
	 */
	List<MenuEntity> findUserZgx(String yhid);
	
	
	
}
