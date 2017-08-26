package com.vkl.ckts.rgsy.record.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.base.Message;
import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.constr.ErrorInfo;
import com.vkl.ckts.common.entity.DeptEntity;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.entity.ViewerFileEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IDictEntityService;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.common.utils.Base64Utils;
import com.vkl.ckts.common.utils.DateUtil;
import com.vkl.ckts.common.utils.RandomUtils;
import com.vkl.ckts.pub.service.IBaseService;
import com.vkl.ckts.rgsy.dept.service.IDeptService;
import com.vkl.ckts.rgsy.record.dao.IViewerFileDao;
import com.vkl.ckts.rgsy.record.entity.ViewerFileDto;
import com.vkl.ckts.rgsy.record.service.IViewerFileService;

/**
 * 查验员备案impl
 * 
 * @author hwf
 * @version 1.0
 */
@Service
@Transactional
public class ViewerFileServiceImpl extends ServiceImpl<IViewerFileDao, ViewerFileEntity, java.lang.String> implements IViewerFileService {

	// 注入字典表Service
	@Autowired
	IDictEntityService DictEntityService;
	// 注入用户Service
	@Autowired
	IBaseService baseService;
	// 日志
	Logger log = Logger.getLogger(ViewerFileServiceImpl.class);

	
	@Autowired
	IDeptService deptService;
	/**
	 * 添加查验员备案申请
	 *
	 * @param viewerFileEntity
	 *            查验员实体
	 * @param request
	 * @return true：添加成功，false：添加失败
	 */
	public boolean addViewerFile(ViewerFileDto viewerFileEntity, HttpServletRequest request) throws Exception {
		// 判断当前用户是否为空
		if (request.getSession().getAttribute(Constrant.S_USER_SESSION) == null) {
			Message<String> message = new Message<String>();
			message.setErrorCode(ErrorInfo.S_USER_SESSION_NULL);
			log.error(message.getErrorMsg());

			return false;
		}
		StringBuffer buffer=new StringBuffer();
		buffer.append(DateUtil.format(new Date(), DateUtil.TIMEDATE_NUMBER));
		//闅忔満鏁�浣�
		for (int i = 0; i < 5; i++) {
			buffer.append(new Random().nextInt(9));
		}
		
		UserEntity user = new UserEntity();
		String tiString=DateUtil.format(new Date(), DateUtil.TIMESTAMP_NUMBER);
		// 判断用户名是否存在
		user = checkLoginNameExist(user);
		// 设置密码
		String loginPwd= viewerFileEntity.getIdentity().substring(viewerFileEntity.getIdentity().length()-6);
		viewerFileEntity.setLoginPwd(Base64Utils.encode(loginPwd));
		// 获取当前操作用户并赋值
		viewerFileEntity.setCreater(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId());
		viewerFileEntity.setFileId("v"+tiString);
		// 查验员姓名加密
		viewerFileEntity.setViewName((Base64Utils.encode(viewerFileEntity.getViewName())));
		// 身份证加密
		viewerFileEntity.setIdentity((Base64Utils.encode(viewerFileEntity.getIdentity())));
		// 获取当前时间并赋值
		viewerFileEntity.setCreateDate(new Date());
		// 获取当前时间并赋值
		viewerFileEntity.setFileRgTime(new Date());
		// 获取当前时间并赋值
		viewerFileEntity.setUpdDate(new Date());
		// 设置备案登记员工号
		UserEntity userEntity = (UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION);
		if (userEntity!=null && userEntity.getFilerId()!=null && userEntity.getFilerId()!="") {
			viewerFileEntity.setFilerRgJop(Integer.parseInt(userEntity.getId()));
		}
		// 设置删除标识为正常
		viewerFileEntity.setDelFlag(IdEntity.DEL_FLAG_NORMAL);
		// 设置审核状态为未审核
		viewerFileEntity.setAuditFlag(IdEntity.AUDIT_FLAG_NORMAL);
		// 返回的结果为1则添加成功，否则添加失败
		if (super.dao.addViewerFile(viewerFileEntity) > 0) {
			DeptEntity deptEntity=new DeptEntity();
			deptEntity.setFileId(viewerFileEntity.getOrgan());
			deptEntity=deptService.findDept(deptEntity);
			
			
			
			user.setUserDept(Integer.parseInt(deptEntity.getId()));
			// 设置创建时间
			user.setCreateDate(new Date());
			// 设置创建人
			user.setCreater(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId());
			// 设置删除标识
			user.setDelFlag(IdEntity.DEL_FLAG_NORMAL);
			// 设置用户姓名
			user.setUserName(viewerFileEntity.getViewName());
			// 设置身份证
			user.setIdentityCard(viewerFileEntity.getIdentity());
			// 设置警员编号
			user.setFuzz(viewerFileEntity.getPoliceId());
			// 设置在线状态
			user.setIsOnline(IdEntity.S_NO);
			// 设置用户名
			user.setLoginName(viewerFileEntity.getLoginName());
			user.setUserStatu(IdEntity.U_USER_STATU_USE);
			user.setDelFlag(IdEntity.DEL_FLAG_NORMAL);
			user.setSfgly("0");
			// 设置密码
			user.setLoginPwd(viewerFileEntity.getLoginPwd());
			user.setIsFiler("1");
			user.setFilerId(viewerFileEntity.getFileId());
			if (baseService.addUser(user)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 判断用户名是否存在，存在则重新生成
	 *
	 * @param userEntity
	 * @return
	 */
	public UserEntity checkLoginNameExist(UserEntity userEntity) {
		if (baseService.findUserByLoginName(userEntity) != null) {
			// 从新生成用户名
			userEntity.setLoginName(RandomUtils.getStringRandom(1, 0, 5));
			checkLoginNameExist(userEntity);
		}
		return userEntity;
	}

	/**
	 * 分页查询查验员备案信息
	 *
	 * @param page
	 *            分页工具
	 * @return 用page封装的结果
	 */
	@Override
	public Page<ViewerFileDto> pageViewerFile(Page<ViewerFileDto> page) throws Exception {
		page.setList(super.dao.pageViewerFile(page));

		return page;
	}

	/**
	 * 根据申请id逻辑删除备案信息
	 * 
	 * @param viewerFileEntity
	 */
	@Override
	public void deleteViewerFileById(ViewerFileEntity viewerFileEntity) throws Exception {
		super.dao.deleteViewerFileById(viewerFileEntity);
	}

	/**
	 * 根据申请id查找备案信息
	 * 
	 * @param viewerFileEntity
	 * @return viewerFileEntity
	 */
	@Override
	public ViewerFileDto findViewerFileById(ViewerFileDto viewerFileEntity) throws Exception {
		return super.dao.findViewerFileById(viewerFileEntity);
	}

	/**
	 * 更新备案信息
	 *
	 * @param viewerFileEntity
	 * @param request
	 * @return true：更新成功，fasle：更新失败
	 */
	@Override
	public boolean updateViewerFile(ViewerFileEntity viewerFileEntity, HttpServletRequest request) throws Exception {
		// 判断当前用户session是否为空
		if (request.getSession().getAttribute(Constrant.S_USER_SESSION) == null) {
			Message<String> message = new Message<String>();
			message.setErrorCode(ErrorInfo.S_USER_SESSION_NULL);
			log.error(message.getErrorMsg());

			return false;
		}
		// 查验员姓名加密
		viewerFileEntity.setViewName((Base64Utils.encode(viewerFileEntity.getViewName())));
		// 身份证加密
		viewerFileEntity.setIdentity((Base64Utils.encode(viewerFileEntity.getIdentity())));
		// 获取当前时间并赋值
		viewerFileEntity.setUpdDate(DateUtil.parseDate(DateUtil.format(new Date(), DateUtil.DATE_TIME_FORMAT)));
		// 获取当前操作用户并赋值
		viewerFileEntity.setUpdater(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId().toString());
		super.dao.updateViewerFile(viewerFileEntity);

		return true;

	}

	/**
	 * 根据条件查找相应查验区备案通过的查验员
	 *
	 * @param viewerFileEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ViewerFileDto> findViewerFileList(ViewerFileDto viewerFileEntity) throws Exception {

		return super.dao.findViewerFileList(viewerFileEntity);
	}

	/**
	 * 根据备案编号查找查验员
	 *
	 * @param fileId
	 * @return
	 * @throws Exception
	 */
	@Override
	public ViewerFileDto findViewerFileByFileId(ViewerFileDto viewerFileEntity) throws Exception {
		return super.dao.findViewerFileByFileId(viewerFileEntity);
	}

}
