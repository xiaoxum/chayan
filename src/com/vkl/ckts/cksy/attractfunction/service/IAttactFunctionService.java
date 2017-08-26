package com.vkl.ckts.cksy.attractfunction.service;


import com.vkl.ckts.cksy.attractfunction.dao.IAttractFunctionDao;
import com.vkl.ckts.cksy.attractfunction.entity.AttactPhoto;
import com.vkl.ckts.common.entity.DeptEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IService;



/**
 * 附加功能接口
 * 
 * @author X260
 *
 */
public interface IAttactFunctionService extends IService<IAttractFunctionDao, AttactPhoto, String> {

	
	

	/**
	 * 获取附加照片
	 * 
	 * @param page
	 * @return
	 */
	Page<AttactPhoto>  findAttactPhoto(Page<AttactPhoto> page);
	
	/**
     * 查找部门
     * 
     * @param id
     * @return
     */
	DeptEntity findDeptById(String id);
}
