package com.vkl.ckts.cksy.servacpt.service;

import javax.servlet.http.HttpServletRequest;

import com.vkl.ckts.common.entity.OperApplEntity;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.cksy.servacpt.dao.IOperApplDao;
import com.vkl.ckts.cksy.servacpt.entity.CkInfoDto;
import com.vkl.ckts.cksy.servacpt.entity.TJbxx;
import com.vkl.ckts.cksy.servacpt.entity.VehInfoDto;

/**
 * @see 注册登记，注销，转入，转移业务申请表Service
 * @author hwf
 * @version 1.0
 */
public interface IOperApplService extends IService<IOperApplDao,OperApplEntity, java.lang.String>{
	
	/**
	 * @see 合格证与公告比对并添加受理业务信息
	 * @param operApplEntity
	 * @param vehCertifiInfoEntity
	 * @return Message
	 */
	String matchInfoAddOperAppl(OperApplEntity operApplEntity,VehInfoDto vehInfoDto,HttpServletRequest request);

	/**
	 * @see 复检受理
	 * @param srln
	 * @param rckCount
	 * @return Message
	 */
	String reCheck(String srln,String rckCount, HttpServletRequest request);
	/**
	 * 获取机动车基本信息
	 * 
	 * @param hphm
	 * @param hpzl
	 * @param clsbdh
	 * 
	 * @return
	 */
	TJbxx  getJdcjBxx(String hphm,String hpzl,String cllx,String clsbdh);
	
	/**
	 * 修改预录入信息
	 * 
	 * @param ckInfoDto
	 * @param vehInfoDto
	 */
	void updateCkInfo(CkInfoDto ckInfoDto,VehInfoDto vehInfoDto);
	/**
	 * 更新车型排序
	 * 
	 * @param cllx
	 */
	void updateCllxProSort(String cllx);
}
