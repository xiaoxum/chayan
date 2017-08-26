package com.vkl.ckts.rgsy.record.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.base.Message;
import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.constr.ErrorInfo;
import com.vkl.ckts.common.entity.ChkptFileEntity;
import com.vkl.ckts.common.entity.DeptEntity;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.common.utils.Base64Utils;
import com.vkl.ckts.common.utils.DateUtil;
import com.vkl.ckts.rgsy.dept.service.IDeptService;
import com.vkl.ckts.rgsy.record.dao.IChkptFileDao;
import com.vkl.ckts.rgsy.record.entity.ChkptFileDto;
import com.vkl.ckts.rgsy.record.service.IChkptFileService;

/**
 * 查验区备案Impl
 * 
 * @author hwf
 * @version 1.0
 */
@Service
@Transactional
public class ChkptFileServiceImpl extends ServiceImpl<IChkptFileDao, ChkptFileEntity, String> implements IChkptFileService {

	// 日志
	Logger log = Logger.getLogger(ChkptFileServiceImpl.class);
	@Autowired
	IDeptService deptService;

	/**
	 * 添加查验区备案申请
	 *
	 * @param chkptFileEntity
	 * @param request
	 * @return true：添加成功，false：添加失败
	 */
	@Override
	public boolean addChkptFile(ChkptFileDto chkptFileEntity, HttpServletRequest request)  throws Exception{
		DeptEntity deptEntity1=new DeptEntity();
		deptEntity1.setId(chkptFileEntity.getParentDeptId());
		String orangeCode="";
		deptEntity1=deptService.findDeptById(deptEntity1);
		String tiString=DateUtil.format(new Date(), DateUtil.TIMESTAMP_NUMBER);
		orangeCode="q"+tiString;
		// 判断当前用户是否为空
//		UserEntity userEntity=(UserEntity)request.getSession().getAttribute(Constrant.S_USER_SESSION);
//		if (request.getSession().getAttribute(Constrant.S_USER_SESSION) == null) {
//			Message<String> message = new Message<String>();
//			message.setErrorCode(ErrorInfo.S_USER_SESSION_NULL);
//			log.error(message.getErrorMsg());
//			return false;
//		}
		chkptFileEntity.setOrganCode(orangeCode);
		// 设置创建时间
		chkptFileEntity.setCreateDate(new Date());
		// 设置创建人
		chkptFileEntity.setCreater(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId().toString());
		// 设置修改时间
		chkptFileEntity.setUpdDate(new Date());
		// 设置修改人
		chkptFileEntity.setUpdater(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId().toString());
		// 设置删除标识为正常
		chkptFileEntity.setDelFlag(IdEntity.DEL_FLAG_NORMAL);
		// 设置审核状态为未审核
		chkptFileEntity.setAuditFlag(IdEntity.AUDIT_FLAG_NORMAL);
		// 负责人姓名加密
		chkptFileEntity.setPripName(Base64Utils.encode(chkptFileEntity.getPripName()));
		// 联系电话加密
		//chkptFileEntity.setPripPhone(Base64Utils.encode(chkptFileEntity.getPripPhone()));
		// 设置备案时间
		chkptFileEntity.setFileTime(new Date());
		// 设置备案登记员工号
		chkptFileEntity.setFilerRgJop(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getFilerId());
		// 返回的结果为1则添加成功，否则添加失败
		if (super.dao.addChkptFile(chkptFileEntity) > 0) {
			DeptEntity deptEntity=new DeptEntity();
			deptEntity.setAsts(chkptFileEntity.getAsts()+"");
			deptEntity.setDeptAddr(chkptFileEntity.getOrganAddr());
			deptEntity.setDeptEmail(chkptFileEntity.getOrganEmail());
			deptEntity.setDeptName(chkptFileEntity.getOrganName());
			deptEntity.setDeptPhone(chkptFileEntity.getPripPhone());
			deptEntity.setDeptType(IdEntity.DEPT_TYPE_CHECK_OFFICE);
			deptEntity.setParentId(chkptFileEntity.getParentDeptId());
			deptEntity.setAsts(deptEntity1.getAsts());
			deptEntity.setIsFileDept(IdEntity.S_YES);
			deptEntity.setDelFlag(IdEntity.DEL_FLAG_NORMAL);
			deptEntity.setFileId(chkptFileEntity.getOrganCode());
			super.dao.addDept(deptEntity);
			return true;
		} else {
			return false;
		}
		
		
		
		
		
		
		
		

	}
	
	/**
	 * 分页查询查验区备案信息
	 *
	 * @param page 分页工具
	 *            
	 * @return 用page封装的结果
	 */
	@Override
	public Page<ChkptFileDto> pageChkptFile(Page<ChkptFileDto> page) throws Exception {
		page.setList(super.dao.pageChkptFile(page));

		return page;
	}
	
	/**
	 * 根据查验区编号逻辑删除备案信息
	 * 
	 * @param viewerFileEntity
	 */
	@Override
	public void deleteChkptFileById(ChkptFileDto chkptFileEntity) throws Exception {
		super.dao.deleteChkptFileById(chkptFileEntity);
	}
	
	/**
	 * 根据查验区编号查找备案信息
	 * 
	 * @param chkptFileEntity
	 * @return chkptFileEntity
	 */
	@Override
	public ChkptFileDto findChkptFileById(ChkptFileDto chkptFileEntity) throws Exception {
		return super.dao.findChkptFileById(chkptFileEntity);
	}

	/**
	 * 更新备案信息
	 *
	 * @param chkptFileEntity
	 * @param request
	 * @return true：更新成功，fasle：更新失败
	 */
	@Override
	public boolean updateChkptFile(ChkptFileDto chkptFileEntity, HttpServletRequest request) throws Exception {
		// 判断当前用户session是否为空
		if (request.getSession().getAttribute(Constrant.S_USER_SESSION) == null) {
			Message<String> message = new Message<String>();
			message.setErrorCode(ErrorInfo.S_USER_SESSION_NULL);
			log.error(message.getErrorMsg());

			return false;
		}
		// 获取当前时间并赋值
		chkptFileEntity.setUpdDate(new Date());
		// 获取当前操作用户并赋值
		chkptFileEntity.setUpdater(((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId().toString());
		super.dao.updateChkptFile(chkptFileEntity);

		return true;

	}

	/**
	 * 查询备案通过的查验区列表
	 *
	 * @param chkptFileEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ChkptFileDto> findChkptList(ChkptFileDto chkptFileEntity) throws Exception {
		
		return super.dao.findChkptList(chkptFileEntity);
	}
}
