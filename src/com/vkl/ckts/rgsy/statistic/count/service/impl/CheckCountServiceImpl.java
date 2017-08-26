package com.vkl.ckts.rgsy.statistic.count.service.impl;

import java.text.NumberFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.entity.ChkptFileEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.rgsy.statistic.count.dao.ICheckCountDao;
import com.vkl.ckts.rgsy.statistic.count.entity.CheckCount;
import com.vkl.ckts.rgsy.statistic.count.service.ICheckCountService;
@Service
@Transactional
public class CheckCountServiceImpl extends ServiceImpl<ICheckCountDao, CheckCount, String> implements ICheckCountService {
	@Autowired
	ICheckCountDao iccd;
	
	/**
	 * 查询所有查验区
	 */
	public List<ChkptFileEntity> allChkpt(){
		return iccd.allChkpt();
	}
	 
	/**
	 * 查询查验信息的所有信息
	 */
	public List<CheckCount> countList(Page<CheckCount> page){
		List<CheckCount> list = super.findAll(page).getList();	// 所有查验信息
		for(CheckCount cc : list){
			// 查验总计是否为0
			if(cc.getaCount()!=0){
				// 创建一个数值格式化对象   
   	         	NumberFormat numberFormat = NumberFormat.getInstance();   
   	         	// 设置精确到小数点后2位   
    	         numberFormat.setMaximumFractionDigits(2); 
    	         int fm = cc.getaCount();		// 查验总记录
    	         int fz = cc.getcCount();		// 查验合格总记录
				String rate = numberFormat.format((float)fz / (float)fm * 100);	// 计算合格率
				cc.setRate(rate);			
			}else{
				cc.setRate("0");	// 如果为0，合格率为0
			}
		}
		return list;
	}
	/**
	 * 统计报废信息
	 */
	public List<CheckCount> findBfxx(Page<CheckCount> page){
		return iccd.findBfxx(page);
	}
		
	public Integer findBfxxCount(CheckCount checkcount){
		return iccd.findBfxxCount(checkcount);
	}
	/*
	 * 报废信息统计
	 * @按时间段统计
	 * */
	public List<CheckCount> findBfxxByTime(Page<CheckCount> page){
		return iccd.findBfxxbyTmie(page);
	}
	public Integer findBfxxByTimeCount(CheckCount checkcount){
		return iccd.findBfxxbyTmieCount(checkcount);
	}
}
