package com.vkl.ckts.cksy.base.utils;

import com.vkl.ckts.cksy.base.dao.ISysSetDao;
import com.vkl.ckts.common.controller.SpringContextHolder;
/**
 * 系统参数配置表
 * @author xiaoxu
 * @version 1.0
 */
public class SysParamUtils {
  
	/**
	 * 根据key值获取value
	 * @param key
	 * @return
	 */
	public static String getValue(String key){
		ISysSetDao sysSetDao=SpringContextHolder.getBean(ISysSetDao.class);
		return sysSetDao.getConfigParam(key);
	}
}
