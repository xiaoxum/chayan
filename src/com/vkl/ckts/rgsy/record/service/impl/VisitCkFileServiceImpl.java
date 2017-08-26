package com.vkl.ckts.rgsy.record.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.vkl.ckts.common.base.Message;
import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.constr.ErrorInfo;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.entity.VisitCkFileEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.rgsy.record.dao.IVisitCkFileDao;
import com.vkl.ckts.rgsy.record.service.IVisitCkFileService;

/**
 * 上门查验备案ServiceImpl
 *
 * @author hwf
 * @version 1.0
 */
@Service
public class VisitCkFileServiceImpl extends ServiceImpl<IVisitCkFileDao, VisitCkFileEntity, String> implements IVisitCkFileService {

	// 日志
	Logger log = Logger.getLogger(VisitCkFileServiceImpl.class);
	/**
	 * 添加上门查验备案申请
	 * 
	 * @param visitCkFileEntity 上门查验备案实体
	 * @param request
	 * @return true：添加成功，false：添加失败
	 */
	public boolean addVisitCkFile(VisitCkFileEntity visitCkFileEntity, HttpServletRequest request) throws Exception {
		// 判断当前用户是否为空
		if (request.getSession().getAttribute(Constrant.S_USER_SESSION) == null) {
			Message<String> message = new Message<String>();
			message.setErrorCode(ErrorInfo.S_USER_SESSION_NULL);
			log.error(message.getErrorMsg());

			return false;
		}
		// 设置创建时间
		visitCkFileEntity.setCreateDate(new Date());
		// 设置创建人
		visitCkFileEntity.setCreater(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId().toString());
		// 设置修改时间
		visitCkFileEntity.setUpdDate(new Date());
		// 设置修改人
		visitCkFileEntity.setUpdater(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId().toString());
		// 设置删除标识为正常
		visitCkFileEntity.setDelFlag(IdEntity.DEL_FLAG_NORMAL);
		// 设置审核状态为未审核
		visitCkFileEntity.setAuditFlag(IdEntity.AUDIT_FLAG_WAIT);
		// 设置备案时间
		visitCkFileEntity.setFileTime(new Date());
		// 设置备案登记员工号
		visitCkFileEntity.setFilerRgJop(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getFilerId());
		// 返回的结果为1则添加成功，否则添加失败
		if (super.dao.addVisitCkFile(visitCkFileEntity) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 分页查询上门查验备案信息
	 * 
	 * @param page 分页工具实体
	 * @return 上门查验集合
	 */
	@Override
	public Page<VisitCkFileEntity> pageVisitCkFile(Page<VisitCkFileEntity> page) throws Exception {
		page.setList(super.dao.pageVisitCkFile(page));
		return page;
	}

	/**
	 * 根据上门查验编号逻辑删除备案信息
	 * 
	 * @param visitCkFileEntity
	 */
	@Override
	public void deleteVisitCkFileById(VisitCkFileEntity visitCkFileEntity) throws Exception {
		super.dao.deleteVisitCkFileById(visitCkFileEntity);
		
	}

	/**
	 * 根据上门查验编号查找备案信息
	 * 
	 * @param visitCkFileEntity
	 * @return visitCkFileEntity
	 */
	@Override
	public VisitCkFileEntity findVisitCkFileById(VisitCkFileEntity visitCkFileEntity) throws Exception {
		return super.dao.findVisitCkFileById(visitCkFileEntity);
	}

	/**
	 * 更新备案信息
	 *
	 * @param visitCkFileEntity
	 * @param request
	 * @return true：更新成功，fasle：更新失败
	 */
	@Override
	public boolean updateVisitCkFile(VisitCkFileEntity visitCkFileEntity, HttpServletRequest request) throws Exception {
		// 判断当前用户session是否为空
		if (request.getSession().getAttribute(Constrant.S_USER_SESSION) == null) {
			Message<String> message = new Message<String>();
			message.setErrorCode(ErrorInfo.S_USER_SESSION_NULL);
			log.error(message.getErrorMsg());

			return false;
		}
		// 获取当前时间并赋值
		visitCkFileEntity.setUpdDate(new Date());
		// 获取当前操作用户并赋值
		visitCkFileEntity.setUpdater(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId().toString());
		super.dao.updateVisitCkFile(visitCkFileEntity);

		return true;
	}

}
