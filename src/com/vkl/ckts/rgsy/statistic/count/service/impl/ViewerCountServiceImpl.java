package com.vkl.ckts.rgsy.statistic.count.service.impl;

import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.common.utils.Base64Utils;
import com.vkl.ckts.rgsy.statistic.count.dao.IViewerCountDao;
import com.vkl.ckts.rgsy.statistic.count.entity.CheckCount;
import com.vkl.ckts.rgsy.statistic.count.service.IViewerCountService;
/**
 * 查验员合格率serviceImpl
 * @author xujunhua
 * @date 2017年4月14日
 * @ClassName: ViewerCountServiceImpl
 */
@Service
@Transactional
public class ViewerCountServiceImpl extends ServiceImpl<IViewerCountDao, CheckCount, String> implements IViewerCountService{
	@Autowired
	IViewerCountDao ivcd;

	
	/**
	 * 查询所有查验信息
	 * @throws UnsupportedEncodingException 
	 */
	public List<CheckCount> allCheckInfo(Page<CheckCount> page) throws UnsupportedEncodingException {
		List<CheckCount> list = super.findAll(page).getList();	// 所有查验信息		
		for(CheckCount vc : list){
			vc.setAuditFlag(IdEntity.AUDIT_FLAG_NORMAL);	// 审核通过
			if(vc.getaCount() != 0){			// 如果查验总数不为0，则计算合格率
				NumberFormat nf = NumberFormat.getInstance();
				nf.setMaximumFractionDigits(2);
				int fm = vc.getaCount();
				int fz = vc.getcCount();
				vc.setRate(nf.format((float)fz / (float)fm * 100));	// 计算合格率并赋值
			}else{
				vc.setRate("0");	// 查验总数为0，合格率为0
			}
			String str = Base64Utils.decode(vc.getViewer());	// 查验员名称解密
			vc.setViewer(str);
		}
		return list;
	}

}
