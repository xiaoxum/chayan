package com.vkl.ckts.rgsy.vehiclereview.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.rgsy.vehiclereview.dao.IVedioRecordDao;
import com.vkl.ckts.rgsy.vehiclereview.entity.VedioRecordDto;
import com.vkl.ckts.rgsy.vehiclereview.service.IVedioRecordService;
/**
 * 查验视频记录Service实现类
 * @author Administrator
 *
 */
@Service
@Transactional
public class VedioRecordServiceImpl extends ServiceImpl<IVedioRecordDao,VedioRecordDto,String>
		implements IVedioRecordService{

	@Override
	public List<VedioRecordDto> findBySrln(String srln,String rdkCount) {
		return super.dao.findBySrln(srln,rdkCount);
	}

}
