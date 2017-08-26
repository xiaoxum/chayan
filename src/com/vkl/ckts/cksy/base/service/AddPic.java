package com.vkl.ckts.cksy.base.service;

import java.util.Date;
import java.util.List;




import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vkl.ckts.cksy.base.dao.AddpicDao;
import com.vkl.ckts.common.dao.IOperPicProjectEntityDao;
import com.vkl.ckts.common.entity.CehUsnrEntity;
import com.vkl.ckts.common.entity.CkCllxEntity;
import com.vkl.ckts.common.entity.OperPicProjectEntity;
import com.vkl.ckts.common.entity.OperTypeEntity;
import com.vkl.ckts.common.service.ICehUsnrEntityService;
import com.vkl.ckts.common.service.ICkCllxEntityService;
import com.vkl.ckts.common.service.IOperTypeEntityService;



@Service
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class AddPic {

	@Autowired
	AddpicDao addpicDao;
	/**
	 * 业务类型表
	 */
	@Autowired
	IOperTypeEntityService OperTypeEntityService;
	/**
	 * 车型表
	 */
	@Autowired
	ICkCllxEntityService ckCllxEntityService;
	/**
	 * 使用性质表
	 */
	@Autowired
	ICehUsnrEntityService CehUsnrEntityService;
	
	@Autowired
	IOperPicProjectEntityDao oper;
	public void addpicSet(){
		
		List<CkCllxEntity> lists=ckCllxEntityService.selectAllParent();
		List<CehUsnrEntity> cehUsnrEntities= addpicDao.selectAll();
		List<OperTypeEntity> operTypeEntitie=OperTypeEntityService.selectAll();
		for (OperTypeEntity operTypeEntity : operTypeEntitie) {
			for (CehUsnrEntity cehUsnrEntity : cehUsnrEntities) {
				for (CkCllxEntity ckCllxEntity : lists) {
					OperPicProjectEntity operPicProjectEntity=new OperPicProjectEntity();
					operPicProjectEntity.setCllx(ckCllxEntity.getParentCllx());
					operPicProjectEntity.setOperType(operTypeEntity.getId());
					operPicProjectEntity.setSyxz(cehUsnrEntity.getParentId());
					operPicProjectEntity.setCreateDate(new Date());
					operPicProjectEntity.setDelFlag("0");
					operPicProjectEntity.setPrintNum(0);
					if (addpicDao.findPic(operTypeEntity.getId(), cehUsnrEntity.getParentId(), ckCllxEntity.getParentCllx(), "01")==null) {
						operPicProjectEntity.setPicPro("01");
						addpicDao.insertData(operPicProjectEntity);
						
						
					}
                   if (addpicDao.findPic(operTypeEntity.getId(), cehUsnrEntity.getParentId(), ckCllxEntity.getParentCllx(), "03")==null) {
                	   operPicProjectEntity.setPicPro("03");
                	   addpicDao.insertData(operPicProjectEntity);
					}
				}
			}
		}
		
		
		
		
	}
	
	@Test
	public void est(){
		addpicSet();
		System.out.println("dddd");
		
	}
	
}
