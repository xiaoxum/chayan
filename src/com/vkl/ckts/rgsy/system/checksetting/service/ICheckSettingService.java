package com.vkl.ckts.rgsy.system.checksetting.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.vkl.ckts.common.entity.CehUsnrEntity;
import com.vkl.ckts.common.entity.CkCllxEntity;
import com.vkl.ckts.common.entity.CkProjectEntity;
import com.vkl.ckts.common.entity.OperTypeEntity;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.rgsy.system.checksetting.dao.ICheckSettingDao;
import com.vkl.ckts.rgsy.system.checksetting.entity.CheckItem;

public interface ICheckSettingService extends IService<ICheckSettingDao,CheckItem,String>{
	/**
	 * 插入一套查验项设置
	 * @param item
	 * @return
	 * @throws Exception 
	 */
	public String addCheckItem(CheckItem item,HttpServletRequest request) throws Exception;
	/**
	 * 查找所有业务类型
	 */
	public List<OperTypeEntity> allOperType();
	/**
	 * 查询所有车型
	 */
	public List<CkCllxEntity> allCKCllx();
	/**
	  * 查询所有查验项目
	  */
	public List<CkProjectEntity> allCkProject(CkProjectEntity cpe);
	/**
	  * 查询一项设置
	  */
	public List<CheckItem> oneCheckItems(CheckItem check);
	/**
	  * 查询所有车辆使用性质
	  */
	public List<CehUsnrEntity> allUsering();
	/**
	 * 修改查验项设置
	 * @throws Exception 
	 */
	public String updateCheck(CheckItem item, HttpServletRequest request) throws Exception;
	/**
	 * 删除查验项
	 * @throws Exception 
	 */
	public void delData(HttpServletRequest request, CheckItem check) throws Exception;
	/**
	 * 根据车辆使用性质的父类id查找父类名字
	 *
	 * @param parentId
	 * @return
	 */
	String findCehUsnrPNameByPId(String parentId) throws Exception;
	/**
	 * 查询所有查验车型
	 * 
	 * @return
	 */
	List<CkCllxEntity> allPCKCllx();
}
