package com.vkl.ckts.cksy.base.utils;

import java.util.List;

import com.vkl.ckts.cksy.base.dao.ISysDao;
import com.vkl.ckts.common.controller.SpringContextHolder;
import com.vkl.ckts.common.entity.MenuEntity;



public class UserUtils {

	/**
	 * 获取当前用户的子菜单信息
	 * 
	 * @param yhid        用户编号
	 *           
	 * @return            子菜单信息
	 */
	public static List<MenuEntity> findUserZgx(String yhid) {
		ISysDao sysDao = SpringContextHolder.getBean(ISysDao.class);
//		return sysDao.findUserZgx(yhid, IdEntity.S_SHKY_YES);
		return sysDao.findUserZgx(yhid);
	}

}
