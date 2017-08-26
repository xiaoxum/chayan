package com.vkl.ckts.cksy.photoinfo.service;


import java.util.List;

import com.vkl.ckts.cksy.photoinfo.entity.PhotoDto;
import com.vkl.ckts.cksy.photoinfo.entity.PicProjectDto;
import com.vkl.ckts.common.page.Page;


/**
 * 
 * @author X260
 *
 */
public interface IPhotoService {
	
	
	
	public Page<PhotoDto> findPidInfo(Page<PhotoDto> page);  
	
	/**
	 * 查询照片项目
	 * 
	 * @return
	 */
	List<PicProjectDto> findPicProject();
	/**
	 * 更新照片打印状态
	 * 
	 * @param srln     流水号 
	 * @param rckCount 复检次数 
	 */
	void  updatePicDyStatu(String srln,String rckCount,String proId);
}
