package com.vkl.ckts.cksy.base.dao;

import com.vkl.ckts.common.annotation.Mybatis;

/**
 * 系统配置表
 * @author xiaoxu 
 * @version 1.0
 */
@Mybatis
public interface ISysSetDao {
	/**
	 * 根据参数获取系统配置值
	 * @param key
	 * @return
	 */
	String getConfigParam(String key);
}
