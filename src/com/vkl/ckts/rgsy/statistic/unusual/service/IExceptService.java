package com.vkl.ckts.rgsy.statistic.unusual.service;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.vkl.ckts.common.entity.VedioRecordEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.rgsy.statistic.unusual.dao.IExceptDao;
import com.vkl.ckts.rgsy.statistic.unusual.entity.NewExceptInfo;
import com.vkl.ckts.rgsy.statistic.unusual.entity.PicRecord;

public interface IExceptService  extends IService<IExceptDao, NewExceptInfo, String>{
	
	/**
	 * 查看异常信息详情
	 */
	public NewExceptInfo detail(Integer excId);
	
	/**
	 * 根据异常信息修改查验员状态
	 * @throws Exception 
	 */
	public void updViewerStatu(NewExceptInfo nei,HttpServletRequest request) throws Exception;
	
	/**
	 * 查看所有异常信息
	 */
	public List<NewExceptInfo> allExcept(Page<NewExceptInfo> page, NewExceptInfo exeInfo);
	
	/**
	 * 查看查验照片
	 * @param srln 流水号
	 * @return
	 */
	public List<PicRecord> allPic(String srln);
	/**
	 * 获取所有视频
	 * @param srln 流水号
	 * @return 视频集合
	 */
	public List<VedioRecordEntity> allVedio(String srln);
}
