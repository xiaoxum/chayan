package com.vkl.ckts.rgsy.statistic.count.service.impl;

import java.text.NumberFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.rgsy.statistic.count.dao.IOverallCountDao;
import com.vkl.ckts.rgsy.statistic.count.entity.CheckCount;
import com.vkl.ckts.rgsy.statistic.count.service.IOverallCountService;
@Service
@Transactional
public class OverallCountServiceImpl extends ServiceImpl<IOverallCountDao, CheckCount, String> implements IOverallCountService{
	@Autowired
	IOverallCountDao iocd;
	
	/**
	 * 查询所有外廓查验信息
	 */
	public List<CheckCount> allCC(Page<CheckCount> page){
		
		List<CheckCount> list = super.findAll(page).getList();
		// 根据查询的结果查询查验总计和查验合格总计
		for(CheckCount cc : list){
			// 计算合格率并封装
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(2);	// 保留两位小数
			int fm = cc.getaCount();		// 查验总计
			int fz = cc.getcCount(); 		// 查验合格总计
			// 查验总计不为0，计算合格率
			if(fm !=0){
				String rate = nf.format((float)fz / (float)fm * 100);
				cc.setRate(rate);
			}else{
				cc.setRate("0");	// 查验总计为0，合格率为0
				
			}
			
		}
		return list;
	}
}
