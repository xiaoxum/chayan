package com.vkl.ckts.rgsy.vehiclereview.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.entity.ChkptFileEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.common.utils.DateUtil;
import com.vkl.ckts.rgsy.vehiclereview.dao.IPicRecordDao;
import com.vkl.ckts.rgsy.vehiclereview.dao.IVehicleReviewDao;
import com.vkl.ckts.rgsy.vehiclereview.entity.CkInfoEntityDto;
import com.vkl.ckts.rgsy.vehiclereview.entity.ShtjDto;
import com.vkl.ckts.rgsy.vehiclereview.service.IVehicleReviewService;

/**
 * 查验审核Service实现类
 * 
 * @author Administrator
 *
 */
@Service
@Transactional
public class VehicleReviewServiceImpl extends ServiceImpl<IVehicleReviewDao, CkInfoEntityDto, String>
		implements IVehicleReviewService {
	@Autowired
	IPicRecordDao picRecordDao;

	@Override
	public Page<CkInfoEntityDto> findByPage(Page<CkInfoEntityDto> page) {
		page.setList(super.dao.findByPage(page));
		page.setTotalCount(super.dao.countNum(page.getT()));
		return page;
	}

	@Override
	public CkInfoEntityDto findVehicleInfo(String srln, String dictType,String rckCount) {
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
	public int updData(String[] arr, String pAuditFlag, String srln,String rckCount) {
		try {
			int num = 0;
			// 获取页面数组
			for (String info : arr) {
				arr = info.split(":");
				if (arr.length == 1) {
					String picId = arr[0];
					// 更新照片记录表审核状态
					num = picRecordDao.updInfo(srln, picId, null, "1");
				} else {
					String picId = arr[0];
					String auditRemaks = arr[1];
					// 更新照片记录表审核状态
					num = picRecordDao.updInfo(srln, picId, auditRemaks, "2");
				}
				if (num <= 0) {
					return 0;
				}
			}
			// 更新查验信息审核状态
			num = super.dao.updataInfo(pAuditFlag, srln,rckCount,null,null,null);
			if (num <= 0) {
				return 0;
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public int updaExamine(String auditFlag, String srln,String rckCount,String remarks,String pAuditer,Date pAuditerTime) {
			
		return super.dao.updataInfo(auditFlag, srln, rckCount,remarks,pAuditer,pAuditerTime);
	}

	@Override
	public ShtjDto findShTj() {
		String nowDate=DateUtil.format(new Date(), DateUtil.DATE_FORMAT);
		return super.dao.findShTj(nowDate);
	}
	@Override
	public List<ChkptFileEntity> findChkpts() {
		// TODO Auto-generated method stub
		return super.dao.findChkpts();
	}
	@Override
	public Page<CkInfoEntityDto> findVehicleList(Page<CkInfoEntityDto> page) {
		// TODO Auto-generated method stub
		page.setTotalCount(super.dao.vehicleCount(page.getT()));
		page.setList(super.dao.findVehicleList(page));
		return page;
	}

	@Override
	public Map<String, Object> findCkerQmView(String fileId) {
		// TODO Auto-generated method stub
		return super.dao.findCkerQmView(fileId);
	}
}
