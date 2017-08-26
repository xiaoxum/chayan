package com.vkl.ckts.rgsy.statistic.log.service.impl;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.xml.internal.messaging.saaj.util.Base64;
import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.common.utils.Base64Utils;
import com.vkl.ckts.common.utils.DateUtil;
import com.vkl.ckts.common.utils.RequestUtils;
import com.vkl.ckts.rgsy.statistic.log.dao.ILogDao;
import com.vkl.ckts.rgsy.statistic.log.entity.OperaDaily;
import com.vkl.ckts.rgsy.statistic.log.service.ILogService;

/**
 * 操作日志
 * @author xujunhua
 * @date 2017年3月23日
 * @ClassName: LogServiceImpl
 */

@Service
@Transactional
public class LogServiceImpl extends ServiceImpl<ILogDao, OperaDaily, String> implements ILogService {

	@Autowired
	ILogDao ild;
	/**
	 * 插入操作
	 */
	public void insertLog(String operation, String mkName, String operIntr, HttpServletRequest request) throws Exception{
		
		// 获取存在session中的用户对象
		UserEntity user = (UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION);
		OperaDaily ole = new OperaDaily();	// 创建日志对象
		ole.setCreateDate(new Date());
		ole.setCreater(user.getId());
		ole.setUpdater(user.getId());
		ole.setUpdDate(new Date());
		ole.setDelFlag(IdEntity.DEL_FLAG_NORMAL);
		ole.setMkName(mkName);
		ole.setIp(RequestUtils.getIp(request));
		ole.setOperation(operation);
		ole.setOperTime(new Date());
		ole.setOperIntr(operIntr);
		ild.insertLog(ole);	// 插入日志
		
		
	}
	/**
	 * 分页查询
	 */
	public List<OperaDaily> allOperDaily(Integer pageNo, Integer pageSize, String start, String end, String loginName){
		Page<OperaDaily> page = new Page<OperaDaily>();
		OperaDaily ole = new OperaDaily();
		ole.setStart(DateUtil.parseDate(start));
		ole.setEnd(DateUtil.parseDate(end));
		ole.setOperName(loginName);
		page.setT(ole);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		List<OperaDaily> logList = super.findAll(page).getList();	// 得到日志集合
		// 将日志时间格式转换为String，方便页面显示
		for(OperaDaily log : logList){
			log.setDate(DateUtil.format(log.getOperTime(), DateUtil.DATE_TIME_FORMAT));
			log.setUserName(Base64Utils.decode(log.getUserName()));	// 操作人解码
		}
		return logList;		
	}
	
	/**
	 * 查询记录数
	 * @return
	 */
	public int count(OperaDaily ole ){
		return super.findCount(ole);
	}
	
}
