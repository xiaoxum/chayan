package com.vkl.ckts.cksy.photoinfo.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vkl.ckts.cksy.photoinfo.dao.IPhotoDao;
import com.vkl.ckts.cksy.photoinfo.entity.PhotoDto;
import com.vkl.ckts.cksy.photoinfo.entity.PicProjectDto;
import com.vkl.ckts.cksy.photoinfo.service.IPhotoService;
import com.vkl.ckts.common.page.Page;

/**
 * 照片信息实现类
 * 
 * @author X260
 *
 */
@Service
public class PhotoServiceImpl implements IPhotoService{

	@Autowired
	IPhotoDao photoDao;
	/**
	 * 查找照片信息
	 * 
	 */
	@Override
	public Page<PhotoDto> findPidInfo(Page<PhotoDto> page) {
		// TODO Auto-generated method stub
		List<PhotoDto> photoDtos = photoDao.findPidInfo(page);
		page.setList(photoDtos);
		page.setTotalCount(photoDao.findCount(page.getT()));
		return null;
	}
	
	/**
	 * 查询照片项目
	 * 
	 * @return
	 */
	@Override
	public List<PicProjectDto> findPicProject() {
		// TODO Auto-generated method stub
		return photoDao.findPicProject();
	}

	@Override
	public void updatePicDyStatu(String srln, String rckCount, String proId) {
		// TODO Auto-generated method stub
		photoDao.updatePicDyStatu(srln, rckCount, proId);
	}

}
