package com.vkl.ckts.cksy.servacpt.service;

import java.util.List;

import com.vkl.ckts.common.entity.CkInfoEntity;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.cksy.servacpt.dao.ICkInfoEntityDao;
import com.vkl.ckts.cksy.servacpt.entity.CkInfoDto;
/**
 * @查验信息Service接口
 * @author Administrator
 *
 */
public interface ICkInfoEntityService extends IService<ICkInfoEntityDao,CkInfoEntity,String>{

	/**
	 * 根据流水号查询查验信息
	 * @param String srln 流水号
	 * @return CkInfoEntity 查验信息
	 */
	CkInfoEntity findckinfobysrln(String srln ,String rckCount);
	
	
	/**
	 * @根据流水号查询打印信息
	 * @param String srln 流水号
	 * @return CkInfoDto 打印信息
	 */

   CkInfoDto findbysrln(String srln,String rckCount); 
		
  /**
   * 根据车辆识别代号和当前时间查询查验业务
   * 
   * @param clsbdh
   * @param currentDate
   * @return
   */
  List<CkInfoDto> findCkInfoByCd(String clsbdh,String currentDate);
	
  /**
	  * 修改查验信息
	  * 
	  * @param ckInfoEntity
	  */
	 void  updateCkInfo(CkInfoEntity ckInfoEntity);
	 
}
