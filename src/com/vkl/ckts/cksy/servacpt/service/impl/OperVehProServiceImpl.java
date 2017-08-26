package com.vkl.ckts.cksy.servacpt.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.vkl.ckts.common.entity.OperVehProEntity;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.cksy.servacpt.dao.IOperVehProDao;
import com.vkl.ckts.cksy.servacpt.service.IOperVehProService;


/**
 * @查验项目Service接口
 * @author jiajun
 *
 */
@Service
@Transactional
public class OperVehProServiceImpl extends ServiceImpl<IOperVehProDao,OperVehProEntity,String> implements IOperVehProService {

	/**
	 * 业务车型查验项目
	 * @param OperVehProEntity
	 * @return List 
	 */
	@Override
	public List<OperVehProEntity> find(OperVehProEntity operVehProEntity) {
		
		return super.dao.find(operVehProEntity);
	}

	

}
