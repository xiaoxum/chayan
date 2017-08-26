package com.vkl.ckts.rgsy.record.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.base.Message;
import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.constr.ErrorInfo;
import com.vkl.ckts.common.entity.GbrFileEntity;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.rgsy.record.dao.IGbrFileDao;
import com.vkl.ckts.rgsy.record.service.IGbrFileService;

/**
 * 外廓设备备案ServiceImpl
 *
 * @author hwf
 * @version 1.0
 */
@Service
@Transactional
public class GbrFileServiceImpl extends ServiceImpl<IGbrFileDao, GbrFileEntity, String> implements IGbrFileService {

	// 日志
	Logger log = Logger.getLogger(GbrFileServiceImpl.class);
	/**
	 * 添加Gbr备案申请
	 * 
	 * @param GbrDeviceFifleEntity Gbr备案实体
	 * @param request
	 * @return true：添加成功，false：添加失败
	 */
	public boolean addGbrFile(GbrFileEntity gbrFileEntity, HttpServletRequest request) throws Exception {
		// 判断当前用户是否为空
		if (request.getSession().getAttribute(Constrant.S_USER_SESSION) == null) {
			Message<String> message = new Message<String>();
			message.setErrorCode(ErrorInfo.S_USER_SESSION_NULL);
			log.error(message.getErrorMsg());

			return false;
		}
		// 设置创建时间
		gbrFileEntity.setCreateDate(new Date());
		// 设置创建人
		gbrFileEntity.setCreater(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId().toString());
		// 设置修改时间
		gbrFileEntity.setUpdDate(new Date());
		// 设置修改人
		gbrFileEntity.setUpdater(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId().toString());
		// 设置删除标识为正常
		gbrFileEntity.setDelFlag(IdEntity.DEL_FLAG_NORMAL);
		// 设置审核状态为未审核
		gbrFileEntity.setAuditFlag(IdEntity.AUDIT_FLAG_WAIT);
		// 设置备案时间
		gbrFileEntity.setFileTime(new Date());
		// 设置备案登记员工号
		gbrFileEntity.setFilerRgJop(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getFilerId());
		// 返回的结果为1则添加成功，否则添加失败
		if (super.dao.addGbrFile(gbrFileEntity) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 分页查询Gbr备案信息
	 * 
	 * @param page 分页工具实体
	 * @return Gbr集合
	 */
	@Override
	public Page<GbrFileEntity> pageGbrFile(Page<GbrFileEntity> page) throws Exception {
		page.setList(super.dao.pageGbrFile(page));
		return page;
	}

	/**
	 * 根据Gbr编号逻辑删除备案信息
	 * 
	 * @param GbrDeviceFifleEntity
	 */
	@Override
	public void deleteGbrFileById(GbrFileEntity gbrFileEntity) throws Exception {
		super.dao.deleteGbrFileById(gbrFileEntity);
		
	}

	/**
	 * 根据Gbr编号查找备案信息
	 * 
	 * @param GbrDeviceFifleEntity
	 * @return GbrDeviceFifleEntity
	 */
	@Override
	public GbrFileEntity findGbrFileById(GbrFileEntity gbrFileEntity) throws Exception {
		return super.dao.findGbrFileById(gbrFileEntity);
	}

	/**
	 * 更新备案信息
	 *
	 * @param GbrDeviceFifleEntity
	 * @param request
	 * @return true：更新成功，fasle：更新失败
	 */
	@Override
	public boolean updateGbrFile(GbrFileEntity gbrFileEntity, HttpServletRequest request) throws Exception {
		// 判断当前用户session是否为空
		if (request.getSession().getAttribute(Constrant.S_USER_SESSION) == null) {
			Message<String> message = new Message<String>();
			message.setErrorCode(ErrorInfo.S_USER_SESSION_NULL);
			log.error(message.getErrorMsg());

			return false;
		}
		// 获取当前时间并赋值
		gbrFileEntity.setUpdDate(new Date());
		// 获取当前操作用户并赋值
		gbrFileEntity.setUpdater(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId().toString());
		super.dao.updateGbrFile(gbrFileEntity);

		return true;
	}


	

}
