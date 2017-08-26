package com.vkl.ckts.cksy.base.dao;

import java.util.List;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.entity.CehUsnrEntity;
import com.vkl.ckts.common.entity.OperPicProjectEntity;
@Mybatis
public interface AddpicDao {
	
	OperPicProjectEntity findPic(String operType,String syxz,String cllx,String picId);
	
	List<CehUsnrEntity>   selectAll();
	
	void insertData(OperPicProjectEntity OperPicProjectEntity);
}
