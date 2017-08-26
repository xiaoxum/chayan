package com.vkl.ckts.rgsy.system.photosetting.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.vkl.ckts.common.entity.ResProEntity;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.rgsy.system.photosetting.dao.IResProDao;
import com.vkl.ckts.rgsy.system.photosetting.entity.OperRes;
/**
 * 高拍项设置Service
 * @author xujunhua
 * @date 2017年3月29日
 * @ClassName: IResProService
 */
public interface IResProService extends IService<IResProDao, OperRes, String>{
	/**
	 * 查询所有高拍项
	 */
	public List<ResProEntity> allResPro();
	/**
	 * 修改设置
	 */
	public void updHighPic(OperRes or,HttpServletRequest request) throws Exception;
	/**
	 * 新增设置
	 * @throws Exception 
	 */
	public boolean addHPic(OperRes or,HttpServletRequest request) throws Exception;
	/**
	 * 删除设置项
	 * @throws Exception 
	 */
	public void delHPic(OperRes or,HttpServletRequest request) throws Exception;
	/**
	 * 修改项查询
	 */
	public List<OperRes> existSet(OperRes or);
}
