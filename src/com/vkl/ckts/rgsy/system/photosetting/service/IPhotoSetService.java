package com.vkl.ckts.rgsy.system.photosetting.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.vkl.ckts.common.entity.PicProjectEntity;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.rgsy.system.photosetting.dao.IPhotoSetDao;
import com.vkl.ckts.rgsy.system.photosetting.entity.OperPicProject;

public interface IPhotoSetService extends IService<IPhotoSetDao, OperPicProject, String>{
	/**
	 * 查询所有拍照项
	 */
	public List<PicProjectEntity> allPicPro();
	/**
	 * 查询修改项拍照
	 */
	public OperPicProject oneOperPic(OperPicProject opp);
	
	/**
	 * 查询修改项拍照
	 */
	public OperPicProject oneOperPic1(OperPicProject opp);
	
	/**
	 * 添加拍照项设置
	 * @throws Exception 
	 */
	public void addPicSet(OperPicProject opp,HttpServletRequest request) throws Exception;
	
	/**
	 * 删除拍照项
	 * @throws Exception 
	 */
	public void delPicSet(OperPicProject opp,HttpServletRequest request) throws Exception;
	
	
	/**
	 * 修改拍照项
	 * @throws Exception 
	 */
	public void updPicSet(OperPicProject opp,HttpServletRequest request) throws Exception;
	
	/**
	 * 修改拍照项
	 * @throws Exception 
	 */
	public void updPicSet1(OperPicProject opp,HttpServletRequest request) throws Exception;
	

}
