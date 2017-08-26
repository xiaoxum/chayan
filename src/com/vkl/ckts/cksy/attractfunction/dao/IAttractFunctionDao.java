package com.vkl.ckts.cksy.attractfunction.dao;

import java.util.List;

import com.vkl.ckts.cksy.attractfunction.entity.AttactPhoto;
import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.DeptEntity;
import com.vkl.ckts.common.page.Page;

/**
 * 获取功能接口
 * 
 * @author xiaoxu
 *
 */
@Mybatis
public interface IAttractFunctionDao extends Dao<AttactPhoto, String>{

	/**
	 * 获取附加照片
	 * 
	 * @param page
	 * @return
	 */
	List<AttactPhoto>  findAttactPhoto(Page<AttactPhoto> page);
	
	
	/**
	 * 获取总数
	 */
	Integer findCount(AttactPhoto attactPhoto);
	
    /**
     * 查找部门
     * 
     * @param id
     * @return
     */
	DeptEntity findDeptById(String id);
}
