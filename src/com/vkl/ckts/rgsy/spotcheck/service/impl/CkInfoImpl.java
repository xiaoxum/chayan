package com.vkl.ckts.rgsy.spotcheck.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.rgsy.spotcheck.dao.ICkInfoDao;
import com.vkl.ckts.rgsy.spotcheck.entity.CkInfoDto;
import com.vkl.ckts.rgsy.spotcheck.service.ICkInfoService;

/**
 * 查验抽查Service实现类
 * @author jiajun
 *
 */
@Service
@Transactional
public class CkInfoImpl extends ServiceImpl<ICkInfoDao,CkInfoDto,String> implements ICkInfoService{

	/**
	 * 查验抽查
	 * @param ckInfoDto 
	 * @return List<CkInfoDto>  
	 */
	@Override
	public List<CkInfoDto> spotCheck(Page<CkInfoDto> page) {
		
		
		return super.dao.spotCheck(page);
	}

}
