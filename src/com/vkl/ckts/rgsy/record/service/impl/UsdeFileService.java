package com.vkl.ckts.rgsy.record.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.base.Message;
import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.constr.ErrorInfo;
import com.vkl.ckts.common.entity.CkProjectEntity;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.entity.UsdeProFileEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.rgsy.record.dao.IUsdeFileDao;
import com.vkl.ckts.rgsy.record.service.IUsdeFileService;

/**
 * 自定义查验项备案ServiceImpl
 *
 * @author hwf
 * @version 1.0
 */
@Service
@Transactional
public class UsdeFileService extends ServiceImpl<IUsdeFileDao, UsdeProFileEntity, String> implements IUsdeFileService {

	// 日志
	Logger log = Logger.getLogger(UsdeFileService.class);
	/**
	 * 添加自定义查验项目备案申请
	 * 
	 * @param usdeProFileEntity 自定义查验项目备案实体
	 * @param request
	 * @return true：添加成功，false：添加失败
	 */
	public boolean addUsdeFile(UsdeProFileEntity usdeProFileEntity, HttpServletRequest request) throws Exception {
		// 判断当前用户是否为空
		if (request.getSession().getAttribute(Constrant.S_USER_SESSION) == null) {
			Message<String> message = new Message<String>();
			message.setErrorCode(ErrorInfo.S_USER_SESSION_NULL);
			log.error(message.getErrorMsg());

			return false;
		}
		// 设置创建时间
		usdeProFileEntity.setCreateDate(new Date());
		// 设置创建人
		usdeProFileEntity.setCreater(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId().toString());
		// 设置修改时间
		usdeProFileEntity.setUpdDate(new Date());
		// 设置修改人
		usdeProFileEntity.setUpdater(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId().toString());
		// 设置删除标识为正常
		usdeProFileEntity.setDelFlag(IdEntity.DEL_FLAG_NORMAL);
		// 设置审核状态为未审核
		usdeProFileEntity.setAuditFlag(IdEntity.AUDIT_FLAG_WAIT);
		// 设置备案时间
		usdeProFileEntity.setFileTime(new Date());
		// 设置备案登记员工号
		usdeProFileEntity.setFilerRgJop(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getFilerId());
		// 返回的结果为1则添加成功，否则添加失败
		if (super.dao.addUsdeFile(usdeProFileEntity) > 0) {
			// 修改项目的状态为审核中
			CkProjectEntity ckProjectEntity = new CkProjectEntity();
			ckProjectEntity.setId(usdeProFileEntity.getProId());
			ckProjectEntity.setIsEnable(IdEntity.S_AUDIT);
			super.dao.updateCkProState(ckProjectEntity);
			
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 分页查询自定义查验项目备案信息
	 * 
	 * @param page 分页工具实体
	 * @return 自定义查验项目集合
	 */
	@Override
	public Page<UsdeProFileEntity> pageUsdeFile(Page<UsdeProFileEntity> page) throws Exception {
		page.setList(super.dao.pageUsdeFile(page));
		return page;
	}

	/**
	 * 根据自定义查验项目编号逻辑删除备案信息
	 * 
	 * @param usdeProFileEntity
	 */
	@Override
	public void deleteUsdeFileById(UsdeProFileEntity usdeProFileEntity) throws Exception {
		super.dao.deleteUsdeFileById(usdeProFileEntity);
		usdeProFileEntity=super.dao.findUsdeFileById(usdeProFileEntity);
		// 修改项目的状态为未启用
		CkProjectEntity ckProjectEntity = new CkProjectEntity();
		ckProjectEntity.setId(usdeProFileEntity.getProId());
		ckProjectEntity.setIsEnable(IdEntity.S_NOT_ENABLE);
		super.dao.updateCkProState(ckProjectEntity);
		
	}

	/**
	 * 根据自定义查验项目编号查找备案信息
	 * 
	 * @param usdeProFileEntity
	 * @return usdeProFileEntity
	 */
	@Override
	public UsdeProFileEntity findUsdeFileById(UsdeProFileEntity usdeProFileEntity) throws Exception {
		return super.dao.findUsdeFileById(usdeProFileEntity);
	}

	/**
	 * 更新备案信息
	 *
	 * @param usdeProFileEntity
	 * @param request
	 * @return true：更新成功，fasle：更新失败
	 */
	@Override
	public boolean updateUsdeFile(UsdeProFileEntity usdeProFileEntity, HttpServletRequest request, CkProjectEntity ckProjectEntity) throws Exception {
		// 判断当前用户session是否为空
		if (request.getSession().getAttribute(Constrant.S_USER_SESSION) == null) {
			Message<String> message = new Message<String>();
			message.setErrorCode(ErrorInfo.S_USER_SESSION_NULL);
			log.error(message.getErrorMsg());

			return false;
		}
		// 获取当前时间并赋值
		usdeProFileEntity.setUpdDate(new Date());
		// 获取当前操作用户并赋值
		usdeProFileEntity.setUpdater(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId().toString());
		super.dao.updateUsdeFile(usdeProFileEntity);
		// 判断项目是否修改，是则将之前的项目状态设为未启用，修改后的项目状态设为审核中
		if(!ckProjectEntity.getId().equals(usdeProFileEntity.getProId())){
			ckProjectEntity.setIsEnable(IdEntity.S_NOT_ENABLE);
			super.dao.updateCkProState(ckProjectEntity);
			ckProjectEntity.setId(usdeProFileEntity.getProId());
			ckProjectEntity.setIsEnable(IdEntity.S_AUDIT);
			super.dao.updateCkProState(ckProjectEntity);
		}
		
		return true;
	}

	/**
	 * 根据业务类型id查找业务名称
	 *
	 * @param id
	 * @return 业务名称
	 * @throws Exception
	 */
	@Override
	public String findOperTypeById(String id) throws Exception {
		return super.dao.findOperTypeById(id);
	}
	
	/**
	 * 根据车辆类型父级id查找父级名称
	 *
	 * @param parentCllx
	 * @return parentName 父级名称
	 * @throws Exception
	 */
	@Override
	public String findCkVehByParentId(String parentCllx) throws Exception {
		return super.dao.findCkVehByParentId(parentCllx);
	}

	/**
	 * 根据使用性质父类id查找父级名称
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public String findUsnrByParentId(String parentId) throws Exception {
		
		return super.dao.findUsnrByParentId(parentId);
	}


	

}
