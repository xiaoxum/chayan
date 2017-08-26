package com.vkl.ckts.rgsy.record.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.base.Message;
import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.constr.ErrorInfo;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.entity.ZbzlFileEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.rgsy.record.dao.IZbzlFileDao;
import com.vkl.ckts.rgsy.record.service.IZbzlFileService;

/**
 * 整备质量备案ServiceImpl
 *
 * @author hwf
 * @version 1.0
 */
@Service
@Transactional
public class ZbzlFileServiceImpl extends ServiceImpl<IZbzlFileDao, ZbzlFileEntity, String> implements IZbzlFileService {

	// 日志
	Logger log = Logger.getLogger(ZbzlFileServiceImpl.class);
	/**
	 * 添加整备质量备案申请
	 * 
	 * @param zbzlFileEntity 整备质量备案实体
	 * @param request
	 * @return true：添加成功，false：添加失败
	 */
	public boolean addZbzlFile(ZbzlFileEntity zbzlFileEntity, HttpServletRequest request) throws Exception {
		// 判断当前用户是否为空
		if (request.getSession().getAttribute(Constrant.S_USER_SESSION) == null) {
			Message<String> message = new Message<String>();
			message.setErrorCode(ErrorInfo.S_USER_SESSION_NULL);
			log.error(message.getErrorMsg());

			return false;
		}
		// 设置创建时间
		zbzlFileEntity.setCreateDate(new Date());
		// 设置创建人
		zbzlFileEntity.setCreater(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId().toString());
		// 设置修改时间
		zbzlFileEntity.setUpdDate(new Date());
		// 设置修改人
		zbzlFileEntity.setUpdater(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId().toString());
		// 设置删除标识为正常
		zbzlFileEntity.setDelFlag(IdEntity.DEL_FLAG_NORMAL);
		// 设置审核状态为未审核
		zbzlFileEntity.setAuditFlag(IdEntity.AUDIT_FLAG_WAIT);
		// 设置备案时间
		zbzlFileEntity.setFileTime(new Date());
		// 设置备案登记员工号
		zbzlFileEntity.setFilerRgJop(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getFilerId());
		// 返回的结果为1则添加成功，否则添加失败
		if (super.dao.addZbzlFile(zbzlFileEntity) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 分页查询整备质量备案信息
	 * 
	 * @param page 分页工具实体
	 * @return 整备质量集合
	 */
	@Override
	public Page<ZbzlFileEntity> pageZbzlFile(Page<ZbzlFileEntity> page) throws Exception {
		page.setList(super.dao.pageZbzlFile(page));
		return page;
	}

	/**
	 * 根据整备质量编号逻辑删除备案信息
	 * 
	 * @param zbzlFileEntity
	 */
	@Override
	public void deleteZbzlFileById(ZbzlFileEntity zbzlFileEntity) throws Exception {
		super.dao.deleteZbzlFileById(zbzlFileEntity);
		
	}

	/**
	 * 根据整备质量编号查找备案信息
	 * 
	 * @param zbzlFileEntity
	 * @return zbzlFileEntity
	 */
	@Override
	public ZbzlFileEntity findZbzlFileById(ZbzlFileEntity zbzlFileEntity) throws Exception {
		return super.dao.findZbzlFileById(zbzlFileEntity);
	}

	/**
	 * 更新备案信息
	 *
	 * @param zbzlFileEntity
	 * @param request
	 * @return true：更新成功，fasle：更新失败
	 */
	@Override
	public boolean updateZbzlFile(ZbzlFileEntity zbzlFileEntity, HttpServletRequest request) throws Exception {
		// 判断当前用户session是否为空
		if (request.getSession().getAttribute(Constrant.S_USER_SESSION) == null) {
			Message<String> message = new Message<String>();
			message.setErrorCode(ErrorInfo.S_USER_SESSION_NULL);
			log.error(message.getErrorMsg());

			return false;
		}
		// 获取当前时间并赋值
		zbzlFileEntity.setUpdDate(new Date());
		// 获取当前操作用户并赋值
		zbzlFileEntity.setUpdater(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId().toString());
		super.dao.updateZbzlFile(zbzlFileEntity);

		return true;
	}


	

}
