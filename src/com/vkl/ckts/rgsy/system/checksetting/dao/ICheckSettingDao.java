package com.vkl.ckts.rgsy.system.checksetting.dao;

import java.util.List;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.CehUsnrEntity;
import com.vkl.ckts.common.entity.CkCllxEntity;
import com.vkl.ckts.common.entity.CkProjectEntity;
import com.vkl.ckts.common.entity.OperTypeEntity;
import com.vkl.ckts.rgsy.system.checksetting.entity.CheckItem;

@Mybatis
public interface ICheckSettingDao extends Dao<CheckItem, String> {
	/**
	 * 查询所有查验车型
	 * 
	 * @return
	 */
	List<CkCllxEntity> allCKCllx();
	
	/**
	 * 查询所有查验车型
	 * 
	 * @return
	 */
	List<CkCllxEntity> allPCKCllx();
	/**
	 * 查询所有查验车型
	 * 
	 * @return
	 */
	List<CkCllxEntity> allCKCllxBy(String pcllx);

	/*
	 * 查询所有业务类型
	 */
	List<OperTypeEntity> allOperType();

	/**
	 * 查询所有查验项目
	 */
	List<CkProjectEntity> allCkProject(CkProjectEntity cpe);

	/**
	 * 查询一项设置
	 */
	List<CheckItem> oneCheckItems(CheckItem check);

	/**
	 * 查询所有车辆使用性质
	 */
	List<CehUsnrEntity> allUsering();

	/**
	 * 修改查验项设置
	 */
	void updateCheck(CheckItem item);

	/**
	 * 唯一查询
	 */
	CheckItem onlyCheckItems(CheckItem item);

	/**
	 * 根据车辆使用性质的父类id查找父类名字
	 *
	 * @param parentId
	 * @return
	 */
	String findCehUsnrPNameByPId(String parentId);

}
