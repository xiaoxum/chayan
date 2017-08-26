package com.vkl.ckts.rgsy.vehiclereview.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.rgsy.vehiclereview.dao.IPicRecordDao;
import com.vkl.ckts.rgsy.vehiclereview.entity.PicRecordDto;
import com.vkl.ckts.rgsy.vehiclereview.service.IPicRecordService;
/**
 * 查验拍照项目照片记录表Service实现类
 * @author Administrator
 *
 */
@Service
@Transactional
public class PicRecordServiceImpl extends ServiceImpl<IPicRecordDao,PicRecordDto,String> implements 
		IPicRecordService{

	@Override
	public List<PicRecordDto> findBySrln(String srln,String rckCount) {
		return super.dao.findBySrln(srln,rckCount);
	}


}
