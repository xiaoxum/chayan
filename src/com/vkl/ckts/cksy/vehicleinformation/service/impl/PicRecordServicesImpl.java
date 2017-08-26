package com.vkl.ckts.cksy.vehicleinformation.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.cksy.vehicleinformation.dao.IPicRecordDaos;
import com.vkl.ckts.cksy.vehicleinformation.entity.CkInfoEntityDto;
import com.vkl.ckts.cksy.vehicleinformation.entity.PicRecordDto;
import com.vkl.ckts.cksy.vehicleinformation.service.IPicRecordServices;
import com.vkl.ckts.cksy.vehicleinformation.service.IVehicleInfoService;
import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.dao.ICehUsnrEntityDao;
import com.vkl.ckts.common.dao.ICkCllxEntityDao;
import com.vkl.ckts.common.entity.CehUsnrEntity;
import com.vkl.ckts.common.entity.CkCllxEntity;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.service.impl.ServiceImpl;
/**
 * 查验拍照项目照片记录表Service实现类
 * @author Administrator
 *
 */
@Service
@Transactional
public class PicRecordServicesImpl extends ServiceImpl<IPicRecordDaos,PicRecordDto,String>
		implements IPicRecordServices{
	/**
	 * 车辆信息service
	 */
	@Autowired
	IVehicleInfoService vehicleInfoService;
	@Autowired
	ICkCllxEntityDao ckInfoEntityDao;
	
	@Autowired
	ICehUsnrEntityDao cehUsnrEntityDao;
	@Override
	public List<PicRecordDto> findBySrln(String srln,String rckCount) {
		// TODO Auto-generated method stub
		return super.dao.findBySrln(srln,rckCount);
	}
	/**
	 * 照片补拍
	 * 
	 * @param srln
	 * @param rckCount    
	 * @param picId
	 */
	@Override
	public void photoBp(String srln, String rckCount, String picId,String userId) {
		// TODO Auto-generated method stub
		//获取照片记录
		PicRecordDto picRecordDto=super.dao.findPicRecordDto(srln, rckCount, picId);
		if (picRecordDto==null) {
			picRecordDto=new PicRecordDto();
			picRecordDto.setSrln(srln);
			picRecordDto.setRckCount(Integer.parseInt(rckCount));
			picRecordDto.setSfdy("0");
			picRecordDto.setPicId(picId);
			picRecordDto.setCreateDate(new Date());
			picRecordDto.setUpdDate(new Date());
			picRecordDto.setDelFlag(IdEntity.DEL_FLAG_NORMAL);
			picRecordDto.setUpdater(userId);
			picRecordDto.setCreater(userId);
			super.dao.addPicRecord(picRecordDto);
		}else {
			super.dao.updatePicRecord(srln, rckCount, picId);
		}
		//更新查验状态
		super.dao.updateCkStatu(srln, rckCount);
	}

	
	
	/**
	 * 照片补拍
	 * 
	 * @param srln
	 * @param rckCount    
	 * @param picId
	 */
	@Override
	public void photoQsBp(String srln, String rckCount,String userId) {
		// TODO Auto-generated method stub
		// 根据流水号查询信息
		CkInfoEntityDto ckInfoEntityDto = vehicleInfoService
					.findVehicleInfo(srln, rckCount, Constrant.HPZL_TYPE);
		CkCllxEntity ckCllxEntity=ckInfoEntityDao.findById(ckInfoEntityDto.getCllx());
		String pcllx="";
		if (ckCllxEntity!=null) {
			pcllx=ckCllxEntity.getParentCllx();
		}
		CehUsnrEntity cehUsnrEntity = cehUsnrEntityDao.findById(ckInfoEntityDto.getSyxz());
		
		String psyxz="";
		if (cehUsnrEntity!=null) {
			psyxz=cehUsnrEntity.getParentId();
		}
		List<PicRecordDto> picRecordDtos=super.dao.findOperPicPro(ckInfoEntityDto.getOperType(), psyxz, pcllx, srln, rckCount);
		boolean flag=false;
		for (PicRecordDto picRecordDto1 : picRecordDtos) {
			//获取照片记录
			PicRecordDto picRecordDto2=super.dao.findPicRecordDto(srln, rckCount, picRecordDto1.getPicId());
			if ((picRecordDto1.getPicUrl()==null||picRecordDto1.getPicUrl()=="")&&picRecordDto2==null) {
				PicRecordDto picRecordDto=new PicRecordDto();
				picRecordDto=new PicRecordDto();
				picRecordDto.setSrln(srln);
				picRecordDto.setRckCount(Integer.parseInt(rckCount));
				picRecordDto.setSfdy("0");
				picRecordDto.setPicId(picRecordDto1.getPicId());
				picRecordDto.setCreateDate(new Date());
				picRecordDto.setUpdDate(new Date());
				picRecordDto.setDelFlag(IdEntity.DEL_FLAG_NORMAL);
				picRecordDto.setUpdater(userId);
				picRecordDto.setCreater(userId);
				picRecordDto.setPicStatu(IdEntity.S_PIC_STATU_AUDIT_NOT_PASS);
				super.dao.addPicRecord(picRecordDto);
				flag=true;
			}
		}
		//更新查验状态
		if (flag) {
			super.dao.updateCkStatu(srln, rckCount);
		}
	}

}
