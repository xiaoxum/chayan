package com.vkl.ckts.rgsy.system.operationsetting.service.impl;



import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.rgsy.statistic.log.service.ILogService;
import com.vkl.ckts.rgsy.system.operationsetting.dao.IOperationDao;
import com.vkl.ckts.rgsy.system.operationsetting.entity.newProAnnc;
import com.vkl.ckts.rgsy.system.operationsetting.service.IOperationService;
@Service
@Transactional
public class OperationServiceImpl extends ServiceImpl<IOperationDao,newProAnnc,String> implements IOperationService{
	
	@Autowired
	IOperationDao sd;
	@Autowired
	ILogService ils;	// 注入操作日志
	/**
	 * 查询一条业务信息
	 */
	public newProAnnc oneOper(newProAnnc npa){
		return sd.oneOper(npa);
	}
	
	/**
	 * 添加业务信息
	 */
	public boolean insertOper(newProAnnc npa,HttpServletRequest request) throws Exception{
		// 获取session中的用户对象
		UserEntity user = (UserEntity)request.getSession().getAttribute(Constrant.S_USER_SESSION);
		npa.setCreater(user.getId());	// 添加创建人
		npa.setUpdater(user.getId());	// 同时修改人为创建人
		npa.setCreateDate(new Date());	// 创建时间
		npa.setUpdDate(new Date());		// 修改时间为创建时间
		if("0".equals(npa.getAnncStatu())){
			npa.setAnncStatu(IdEntity.ANNC_STATU_USE); // 如果页面封装状态为0，则启用
		}else if("1".equals(npa.getAnncStatu())){
			npa.setAnncStatu(IdEntity.ANNC_STATU_UNUER);	// 状态为1，禁用
		}
		newProAnnc pa = sd.oneOper(npa);	// 查询添加项是否存在
		if(pa == null){
			super.insertData(npa);	// 不存在，插入业务信息	
			ils.insertLog("业务信息设置", "系统设置", "添加业务信息设置", request);	// 插入日志信息
			return true;
		}
		return false;
	}
	
	/**
	 * 删除业务信息
	 */
	public void delData(newProAnnc npa,HttpServletRequest request) throws Exception{
		
		super.deleteData(npa);	// 删除
		ils.insertLog("业务信息设置", "系统", "删除业务信息设置成功", request);		// 插入日志信息
	}
	
	/**
	 * 修改业务信息
	 * @param npa
	 * @param request
	 */
	public void doUpdOpera(newProAnnc npa,HttpServletRequest request)throws Exception{
		// 获取session中的用户
		UserEntity user = (UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION);
		npa.setUpdater(user.getId());	// 添加修改人
		npa.setUpdDate(new Date());	// 添加修改时间
		super.updateData(npa);	// 执行修改
		ils.insertLog("业务信息设置", "系统设置", "修改业务信息设置", request);
	}
	
	/**
	 * 查询所有业务信息
	 */
	public List<newProAnnc> allAnnc(Page<newProAnnc> page,newProAnnc npa){
		page.setT(npa);	// 设置分页查询对象
		List<newProAnnc> list = super.findAll(page).getList();	// 得到所有业务设置
		
		// 根据状态标志，修改为字符，方便页面显示
		for(newProAnnc l : list){
			if(IdEntity.ANNC_STATU_USE.equals(l.getAnncStatu())){
				l.setAnncStatu("启用");	// 状态为启用
			}
			if(IdEntity.ANNC_STATU_UNUER.equals(l.getAnncStatu())){
				l.setAnncStatu("禁用");	// 状态为禁用
			}
		}
		return list;
	}
}
