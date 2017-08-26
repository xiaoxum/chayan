package com.vkl.ckts.rgsy.statistic.unusual.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.entity.VedioRecordEntity;
import com.vkl.ckts.common.entity.ViewerFileEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.common.utils.Base64Utils;
import com.vkl.ckts.common.utils.DateUtil;
import com.vkl.ckts.rgsy.statistic.log.service.ILogService;
import com.vkl.ckts.rgsy.statistic.unusual.dao.IExceptDao;
import com.vkl.ckts.rgsy.statistic.unusual.entity.NewExceptInfo;
import com.vkl.ckts.rgsy.statistic.unusual.entity.PicRecord;
import com.vkl.ckts.rgsy.statistic.unusual.service.IExceptService;
/**
 * 异常信息逻辑处理impl
 * @author xujunhua
 * @date 2017年3月23日
 * @ClassName: ExceptServiceImpl
 */
@Service
@Transactional
public class ExceptServiceImpl extends ServiceImpl<IExceptDao, NewExceptInfo, String> implements IExceptService{
	@Autowired
	IExceptDao ied;
	@Autowired
	ILogService ils;	// 注入操作日志
	
	/**
	 * 查看异常信息详情
	 */
	public NewExceptInfo detail(Integer excId){
		NewExceptInfo oneExcept = ied.detail(excId);
		
		return oneExcept;
	}

	/**
	 * 根据异常信息修改查验员状态
	 */
	public void updViewerStatu(NewExceptInfo nei,HttpServletRequest request)throws Exception {
		UserEntity user = (UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION);	//获取session的用户对象
		ViewerFileEntity cfe = new ViewerFileEntity();	//创建查验员对象
		cfe.setId(nei.getViewerId());
		cfe.setUpdDate(new Date());
		cfe.setFileStatu(IdEntity.FILE_STATU_DISABLE);
		cfe.setUpdater(user.getId());
		ied.updViewerStatu(cfe);	// 修改查验员状态 
		 ils.insertLog("修改查验员状态", "统计查询", "根据异常信息修改查验员状态", request);	//插入操作日志
	}
	
	/**
	 * 查看所有异常信息
	 */
	public List<NewExceptInfo> allExcept(Page<NewExceptInfo> page, NewExceptInfo exeInfo){
		exeInfo.setDelFlag(IdEntity.DEL_FLAG_NORMAL);
		page.setT(exeInfo);
		List<NewExceptInfo> allExe = super.findAll(page).getList();
		// 修改时间格式为YYYY-MM-dd hh:mm:ss
		for (NewExceptInfo exe : allExe) {
			if (exe.getCreateDate() != null) {
				exe.setDate(DateUtil.format(exe.getCreateDate(), DateUtil.DATE_TIME_FORMAT));
			}
			exe.setViewName(Base64Utils.decode(exe.getViewName()));	// 查验员名称解密
		}
		return allExe;
	}
	/**
	 * 查看查验照片
	 */
	public List<PicRecord> allPic(String srln){
		
		return ied.allPic(srln);
	}
	
	/**
	 * 获取所有视频
	 * @param srln 流水号
	 * @return 视频集合
	 */
	public List<VedioRecordEntity> allVedio(String srln){
		return ied.allVedio(srln);
	}
}
