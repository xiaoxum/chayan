package com.vkl.ckts.rgsy.system.photosetting.dao;

import java.util.List;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.ResProEntity;
import com.vkl.ckts.rgsy.system.photosetting.entity.OperRes;
/**
 * 高拍项设置Dao
 * @author xujunhua
 * @date 2017年3月29日
 * @ClassName: IResProDao
 */
@Mybatis
public interface IResProDao extends Dao<OperRes, String>{
	/**
	 * 查询所有高拍项
	 */
	List<ResProEntity> allResPro();
	/**
	 * 查询修改项
	 */
	List<OperRes> existSet(OperRes or);
}
