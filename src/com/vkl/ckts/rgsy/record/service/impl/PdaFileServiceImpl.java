package com.vkl.ckts.rgsy.record.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.vkl.ckts.common.base.Message;
import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.constr.ErrorInfo;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.entity.PdaDeviceFifleEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.common.utils.DateUtil;
import com.vkl.ckts.rgsy.record.dao.IPdaFileDao;
import com.vkl.ckts.rgsy.record.entity.PdaDeviceFifleDto;
import com.vkl.ckts.rgsy.record.service.IPdaFileService;

/**
 * Pda备案ServiceImpl
 *
 * @author hwf
 * @version 1.0
 */
@Service
public class PdaFileServiceImpl extends ServiceImpl<IPdaFileDao, PdaDeviceFifleEntity, String> implements IPdaFileService {

	// 日志
	Logger log = Logger.getLogger(PdaFileServiceImpl.class);
	/**
	 * 添加pda备案申请
	 * 
	 * @param pdaDeviceFifleEntity pda备案实体
	 * @param request
	 * @return true：添加成功，false：添加失败
	 */
	public String addPdaFile(PdaDeviceFifleEntity pdaDeviceFifleEntity, HttpServletRequest request) throws Exception {
		PdaDeviceFifleEntity pdaDeviceFifleEntity2=super.dao.findPdaFileByMac(pdaDeviceFifleEntity.getDeviceMac());
		if (pdaDeviceFifleEntity2!=null) {
			return "already";
		}
		String tiString=DateUtil.format(new Date(), DateUtil.TIMESTAMP_NUMBER);
		String pdafile="p"+tiString;
		pdaDeviceFifleEntity.setFileId(pdafile);
		// 设置创建时间
		pdaDeviceFifleEntity.setCreateDate(new Date());
		// 设置创建人
		pdaDeviceFifleEntity.setCreater(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId().toString());
		// 设置修改时间
		pdaDeviceFifleEntity.setUpdDate(new Date());
		// 设置修改人
		pdaDeviceFifleEntity.setUpdater(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId().toString());
		// 设置删除标识为正常
		pdaDeviceFifleEntity.setDelFlag(IdEntity.DEL_FLAG_NORMAL);
		// 设置审核状态为未审核
		pdaDeviceFifleEntity.setAuditFlag(IdEntity.AUDIT_FLAG_NORMAL);
		// 设置备案时间
		pdaDeviceFifleEntity.setDeviceFileTime(new Date());
		// 设置备案登记员工号
		pdaDeviceFifleEntity.setFilerRgJop(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getFilerId());
		// 返回的结果为1则添加成功，否则添加失败
		if (super.dao.addPdaFile(pdaDeviceFifleEntity) > 0) {
			return "success";
		} else {
			return "error";
		}
	}
	
	/**
	 * 分页查询pda备案信息
	 * 
	 * @param page 分页工具实体
	 * @return pda集合
	 */
	@Override
	public Page<PdaDeviceFifleDto> pagePdaFile(Page<PdaDeviceFifleDto> page) throws Exception {
		page.setList(super.dao.pagePdaFile(page));
		return page;
	}

	/**
	 * 根据pda编号逻辑删除备案信息
	 * 
	 * @param pdaDeviceFifleEntity
	 */
	@Override
	public void deletePdaFileById(PdaDeviceFifleDto pdaDeviceFifleEntity) throws Exception {
		super.dao.deletePdaFileById(pdaDeviceFifleEntity);
		
	}

	/**
	 * 根据pda编号查找备案信息
	 * 
	 * @param pdaDeviceFifleEntity
	 * @return pdaDeviceFifleEntity
	 */
	@Override
	public PdaDeviceFifleEntity findPdaFileById(PdaDeviceFifleEntity pdaDeviceFifleEntity) throws Exception {
		return super.dao.findPdaFileById(pdaDeviceFifleEntity);
	}

	/**
	 * 更新备案信息
	 *
	 * @param pdaDeviceFifleEntity
	 * @param request
	 * @return true：更新成功，fasle：更新失败
	 */
	@Override
	public boolean updatePdaFile(PdaDeviceFifleEntity pdaDeviceFifleEntity, HttpServletRequest request) throws Exception {
		// 判断当前用户session是否为空
		if (request.getSession().getAttribute(Constrant.S_USER_SESSION) == null) {
			Message<String> message = new Message<String>();
			message.setErrorCode(ErrorInfo.S_USER_SESSION_NULL);
			log.error(message.getErrorMsg());

			return false;
		}
		// 获取当前时间并赋值
		pdaDeviceFifleEntity.setUpdDate(new Date());
		// 获取当前操作用户并赋值
		pdaDeviceFifleEntity.setUpdater(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId().toString());
		super.dao.updatePdaFile(pdaDeviceFifleEntity);

		return true;
	}


}
