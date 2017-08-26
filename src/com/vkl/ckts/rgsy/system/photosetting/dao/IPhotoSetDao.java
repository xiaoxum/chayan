package com.vkl.ckts.rgsy.system.photosetting.dao;

import java.util.List;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.PicProjectEntity;
import com.vkl.ckts.rgsy.system.photosetting.entity.OperPicProject;

@Mybatis
public interface IPhotoSetDao extends Dao<OperPicProject, String>{
	/**
	 * 查询所有拍照项
	 */
	List<PicProjectEntity> allPicPro();
	/**
	 * 查询修改项拍照
	 */
	OperPicProject oneOperPic(OperPicProject opp);
	/**
	 * 查询修改项拍照
	 */
	OperPicProject oneOperPic1(OperPicProject opp);
	/**
	 * 删除查验拍照项目
	 * 
	 * @param cllx
	 */
	void deleteData1(String cllx);
	
}
