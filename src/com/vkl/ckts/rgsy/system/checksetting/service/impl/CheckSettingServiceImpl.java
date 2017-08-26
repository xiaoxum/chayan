package com.vkl.ckts.rgsy.system.checksetting.service.impl;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.entity.CehUsnrEntity;
import com.vkl.ckts.common.entity.CkCllxEntity;
import com.vkl.ckts.common.entity.CkProjectEntity;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.entity.OperTypeEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.common.utils.DateUtil;
import com.vkl.ckts.rgsy.statistic.log.service.ILogService;
import com.vkl.ckts.rgsy.system.checksetting.dao.ICheckSettingDao;
import com.vkl.ckts.rgsy.system.checksetting.entity.CheckItem;
import com.vkl.ckts.rgsy.system.checksetting.service.ICheckSettingService;
@Service
@Transactional
public class CheckSettingServiceImpl extends ServiceImpl<ICheckSettingDao,CheckItem,String> implements ICheckSettingService{
	@Autowired
	ICheckSettingDao csd ;
	@Autowired
	ILogService ils;	// 注入日志
	/**
	 * 分页查询所有查验项设置
	 */
	public Page<CheckItem> findAll(Page<CheckItem> page){
		List<CheckItem> list = csd.findAll(page); 
		
		page.setList(list);
		return page;
	}
	/**
	 * 插入一套查验项设置
	 * @param item
	 * @return
	 */
	public String addCheckItem(CheckItem oper, HttpServletRequest request) throws Exception{
		String operVeh =oper.getOperVeh();
		String ov[] = operVeh.split(",");
		List<CheckItem> item = csd.oneCheckItems(oper);
		// 查验项是否存在
		if(item.size() != 0){
			return "error";
		}
		// 循环添加
		for(int i=0;i<ov.length;i++){
			oper.setProId(ov[i]);
			oper.setCreateDate(DateUtil.parseDate(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm")));	
			oper.setUpdDate(DateUtil.parseDate(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm")));
			oper.setVehModPro(0);
			oper.setDelFlag(IdEntity.DEL_FLAG_NORMAL);
			csd.insertData(oper);	// 添加
			ils.insertLog("新增查验项设置", "系统设置", "查验项新增成功", request);	// 添加操作日志	
		}
		return "success";
	}
	/**
	 * 查找所有业务类型
	 */
	public List<OperTypeEntity> allOperType(){
		return csd.allOperType();
	}
	/**
	 * 查询所有车型
	 */
	public List<CkCllxEntity> allCKCllx() {
		return csd.allCKCllx();
	}
	
	/**
	  * 查询所有查验项目
	  */
	public List<CkProjectEntity> allCkProject(CkProjectEntity cpe){
		
		return csd.allCkProject(cpe);
	}
	/**
	  * 查询一项设置
	  */
	public List<CheckItem> oneCheckItems(CheckItem check){
		 return csd.oneCheckItems(check);
	 }
	/**
	  * 查询所有车辆使用性质
	  */
	public List<CehUsnrEntity> allUsering(){
		 return csd.allUsering();
	 }
	/**
	 * 修改查验项设置
	 */
	public String updateCheck(CheckItem check, HttpServletRequest request) throws Exception{
		List<CheckItem> item = csd.oneCheckItems(check); // 查找修改前的查验项
		String cyx[] = new String[item.size()];	// 定义一个数组，存放修改前的查验项编号
		String operVeh =check.getOperVeh();	// 存放查验项标号的字符串
		String ov[] = operVeh.split(",");
		// 循环给对象的查验项编号赋值
		for(int i=0;i<ov.length;i++){
			check.setProId(ov[i]);
			CheckItem ch = csd.onlyCheckItems(check);	// 判断该项是否存在
			if(ch==null){
				check.setDelFlag(IdEntity.DEL_FLAG_NORMAL);
				check.setCreateDate(DateUtil.parseDate(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm")));
				check.setUpdDate(DateUtil.parseDate(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm")));
				csd.insertData(check);	// 不存在，插入
			}else{
				check.setUpdDate(DateUtil.parseDate(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm")));
				csd.updateCheck(check);	// 存在，修改
			}			
		}
		// 判断是否有删除
		for(int i=0;i<cyx.length;i++){
			cyx[i]=item.get(i).getProId();
			// 有删除
			if(!Arrays.asList(ov).contains(cyx[i])){
				check.setProId(cyx[i]);
				csd.deleteData(check);
			}
		}
		ils.insertLog("查验项设置修改", "系统设置", "查验项设置修改成功", request);
		return "success";		
	}
	
	/**
	 * 删除查验项
	 * @throws Exception 
	 */
	@Transactional
	public void delData(HttpServletRequest request, CheckItem check) throws Exception{
		
			csd.deleteData(check);
			ils.insertLog("查验项设置删除", "系统设置", "查验项设置删除成功", request);
		
	}
	/**
	 * 根据车辆使用性质的父类id查找父类名字
	 *
	 * @param parentId
	 * @return
	 */
	@Override
	public String findCehUsnrPNameByPId(String parentId) throws Exception {
		return super.dao.findCehUsnrPNameByPId(parentId);
	}
	@Override
	public List<CkCllxEntity> allPCKCllx() {
		// TODO Auto-generated method stub
		return super.dao.allPCKCllx();
	}

}
