package com.vkl.ckts.cksy.servacpt.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.entity.CkInfoEntity;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.cksy.servacpt.dao.ICkInfoEntityDao;
import com.vkl.ckts.cksy.servacpt.entity.CkInfoDto;
import com.vkl.ckts.cksy.servacpt.service.ICkInfoEntityService;
/**
 * @查验信息Service实现类
 * @author Administrator
 *
 */
@Service
@Transactional
public class CkInfoEntityServiceImpl extends ServiceImpl<ICkInfoEntityDao,CkInfoEntity,String>
		implements ICkInfoEntityService{
	/**
	 * @根据流水号查询查验信息
	 * @param String srln 流水号
	 * @return CkInfoEntity 查验信息
	 */
	@Override
	public CkInfoEntity findckinfobysrln(String srln,String rckCount) {
		
		return super.dao.findckinfobysrln(srln,rckCount);
	}
	
	
	/**
	 * @根据流水号查询打印信息
	 * @param String srln 流水号
	 * @return CkInfoDto 打印信息
	 */
	@Override
	public CkInfoDto findbysrln(String srln,String rckCount) {
		
		return super.dao.findbysrln(srln,rckCount);
	}

	/**
	   * 根据车辆识别代号和当前时间查询查验业务
	   * 
	   * @param clsbdh
	   * @param currentDate
	   * @return
	   */
	@Override
	public List<CkInfoDto> findCkInfoByCd(String clsbdh, String currentDate) {
		// TODO Auto-generated method stub
		return super.dao.findCkInfoByCd(clsbdh, currentDate);
	}


	@Override
	public void updateCkInfo(CkInfoEntity ckInfoEntity) {
		// TODO Auto-generated method stub
		super.dao.updateCkInfo(ckInfoEntity);
	}
	
	
	
	

}
