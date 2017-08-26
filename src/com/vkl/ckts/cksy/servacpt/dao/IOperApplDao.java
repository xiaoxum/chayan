package com.vkl.ckts.cksy.servacpt.dao;

import com.vkl.ckts.cksy.servacpt.entity.TJbxx;
import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.DeptEntity;
import com.vkl.ckts.common.entity.OperApplEntity;

/**
 * @see 注册登记，注销，转入，转移业务申请表实体Dao
 * @author hwf
 * @version 1.0
 */
@Mybatis
public interface IOperApplDao extends Dao<OperApplEntity, java.lang.String>{
	
	/**
	 * @see 添加注册登记，注销，转入，转移业务申请
	 * @param operApplEntity
	 */
	void addOperAppl (OperApplEntity operApplEntity);
	
	
	/**
	 * @see 添加合格证信息(因为更换了表所以此方法需要修改）
	 * @param vehCertifiInfoEntity
	 */
	//void addVehCertifiInfo(VehCertifiInfoEntity vehCertifiInfoEntity);
	
	
	/**
	 * 添加车辆基本信息
	 * 
	 * @param jbxx
	 */
	void insertVehBaseInfo(TJbxx jbxx);
	
	/**
	 * 根据好牌号码或者车辆识别代号查询车辆基本信息
	 * 
	 * @param clsbdh
	 * @param cllx
	 * @return
	 */
	TJbxx  findVehBaseInfo(String hpzl,String hbhm);
	/**
	 * 根据好牌号码或者车辆识别代号查询车辆基本信息
	 * 
	 * @param clsbdh
	 * @param cllx
	 * @return
	 */
	TJbxx  findVehBaseInfo1(String hpzl,String hbhm);
	/**
	 * 根据好牌号码或者车辆识别代号查询车辆基本信息
	 * 
	 * @param clsbdh
	 * @param hbhm
	 * @return
	 */
	TJbxx  findVehBaseInfo2(String hpzl,String hbhm);
	
	/**
	 * 更新车辆基本信息
	 * 
	 * @param jbxx
	 */
	void  updateVehBaseInfo(TJbxx jbxx);
	/**
	 * 查询车身颜色
	 * 
	 * @param key
	 * @param type
	 * 
	 * @return
	 */
	String findCsysfromDict(String key,String type);
	
	/**
	 * 根据部门编号查询部门
	 * 
	 */
	DeptEntity findDeptById(String bmbh);
	/**
	 * 更新车型排序
	 * 
	 * @param cllx
	 */
	void updateCllxProSort(String cllx);
}
