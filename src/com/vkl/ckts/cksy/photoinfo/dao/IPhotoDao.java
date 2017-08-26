package com.vkl.ckts.cksy.photoinfo.dao;

import java.util.List;

import com.vkl.ckts.cksy.photoinfo.entity.PhotoDto;
import com.vkl.ckts.cksy.photoinfo.entity.PicProjectDto;
import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.page.Page;
/**
 * 照片信息
 * 
 * @author X260
 *
 */
@Mybatis
public interface IPhotoDao {

	/**
	 * 查找照片信息
	 * 
	 * @param photoDto
	 * @return
	 */
	List<PhotoDto> findPidInfo(Page<PhotoDto> page);  
	/**
	 * 查找总数
	 * 
	 * @return
	 */
	Integer findCount(PhotoDto photoDto);
	
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
