package com.vkl.ckts.rgsy.system.photosetting.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.entity.ResProEntity;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.rgsy.statistic.log.service.ILogService;
import com.vkl.ckts.rgsy.system.photosetting.dao.IResProDao;
import com.vkl.ckts.rgsy.system.photosetting.entity.OperRes;
import com.vkl.ckts.rgsy.system.photosetting.service.IResProService;
/**
 * 高拍项设置impl
 * @author xujunhua
 * @date 2017年3月29日
 * @ClassName: ResProServiceImpl
 */
@Service
@Transactional
public class ResProServiceImpl extends ServiceImpl<IResProDao, OperRes, String> implements IResProService{
	@Autowired
	IResProDao irpd;
	@Autowired
	ILogService ils;
	/**
	 * 查询所有高拍项
	 */
	public List<ResProEntity> allResPro(){
		return irpd.allResPro();
	}
	/**
	 * 修改项查询
	 */
	public List<OperRes> existSet(OperRes or){
		return irpd.existSet(or);
	}
	/**
	 * 修改设置
	 */
	public void updHighPic(OperRes or,HttpServletRequest request) throws Exception{
		String newHPic[] = or.getResStr().split(",");	// 修改后的高拍项数组
		List<OperRes> oldHP = irpd.existSet(or);	// 查询修改前的高拍设置
		String oldHPic[] = new String[oldHP.size()];
		int i = 0;	// 数组标志位
		// 修改后的高拍项数组是否包含修改前的高拍项
		for(OperRes o: oldHP){
			oldHPic[i++] = o.getResId();	// 得到修改前的高拍项数组
			if(Arrays.asList(newHPic).contains(o.getResId())){
				continue;	// 包含，进入下一循环
			}else{
				o.setOperType(or.getOperType());
				irpd.deleteData(o); 	// 不包含，删除
			}
		}
		// 修改前的高拍项数组是否包含修改后的高拍项 
		for(int j=0; j<newHPic.length; j++){ 
			if(Arrays.asList(oldHPic).contains(newHPic[j])){
				continue;	// 包含，进入下一循环
			}else{
				or.setResId(newHPic[j]);
				irpd.insertData(or);	// 不包含，插入高拍项
			}
		}
		ils.insertLog("高拍项设置", "系统设置", "修改高拍项设置成功", request);	// 添加操作日志
		
	}
	/**
	 * 新增设置
	 */
	public boolean addHPic(OperRes or, HttpServletRequest request) throws Exception{
		List<OperRes> exist = irpd.existSet(or);	// 查询该项设置是否存在
		if(exist.size() != 0){
			return false;		// 存在，直接返回
		}
		String[] pic = or.getResStr().split(",");	// 将高拍项切割为数组
		for(int i = 0; i < pic.length; i ++){
			or.setResId(pic[i]);
			irpd.insertData(or);
		}
		ils.insertLog("高拍项设置", "系统设置", "新增高拍项设置成功", request);	// 添加操作日志
		return true;
	}
	/**
	 * 删除设置项
	 */
	public void delHPic(OperRes or,HttpServletRequest request) throws Exception{
		irpd.deleteData(or);	// 删除
		ils.insertLog("删除高拍项", "系统设置", "删除高拍项设置成功", request);	// 添加操作日志
	}
}
