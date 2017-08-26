package com.vkl.ckts.cksy.vehicleinformation.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.cksy.vehicleinformation.dao.IVehicleInfoDao;
import com.vkl.ckts.cksy.vehicleinformation.entity.CkInfoEntityDto;
import com.vkl.ckts.cksy.vehicleinformation.service.IVehicleInfoService;
import com.vkl.ckts.common.entity.DeptEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.impl.ServiceImpl;
/**
 * 车辆信息Service实现类
 * @author Administrator
 *
 */
@Service
@Transactional
public class VehicleInfoServiceImpl extends ServiceImpl<IVehicleInfoDao,CkInfoEntityDto,String>
		implements IVehicleInfoService{

	@Override
	public Page<CkInfoEntityDto> findByPage(Page<CkInfoEntityDto> page) {
		
		page.setTotalCount(super.dao.countNum(page.getT()));
		page.setList(super.dao.findByPage(page));
		return page;
	}

	@Override
	public CkInfoEntityDto findVehicleInfo(String srln,String rckCount, String dictType) {
		return super.dao.findVehicleInfo(srln,rckCount, dictType);
	}

	@Override
	public List<String> findAllOperType(String[] opertypes) {
		return super.dao.findAllOperType(opertypes);
	}

	@Override
	public List<String> findAllCker(String[] ckers) {
		return super.dao.findAllCker(ckers);
	}

	@Override
	public void deleteCkInfo(String srln, String rckCount) {
		// TODO Auto-generated method stub
		super.dao.deleteCkInfo(srln, rckCount);
		super.dao.deletePicRcords(srln, rckCount);
		super.dao.deleteProjectRcords(srln, rckCount);
	}

	@Override
	public DeptEntity findDeptById(String id) {
		// TODO Auto-generated method stub
		return super.dao.findDeptById(id);
	}

}
