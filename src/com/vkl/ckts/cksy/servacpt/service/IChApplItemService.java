package com.vkl.ckts.cksy.servacpt.service;



import javax.servlet.http.HttpServletRequest;
import com.vkl.ckts.common.entity.ChApplItemEntity;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.cksy.servacpt.dao.IChApplItemDao;
import com.vkl.ckts.cksy.servacpt.entity.VehInfoDto;

/**
 * @see 变更登记/备案申请Service
 * @author hwf
 * @version 1.0
 */
public interface IChApplItemService extends IService<IChApplItemDao,ChApplItemEntity,String>{
	/**
	 * @see 添加变更登记申请和变更事项
	 * @param ChApplItemEntity  变更信息
	 * @param VehInfoDto        车辆信息
	 */
	String addChAppl(ChApplItemEntity chApplItemEntity,VehInfoDto vehInfoDto,HttpServletRequest request);
}
