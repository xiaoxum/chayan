package com.vkl.ckts.rgsy.statistic.log.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vkl.ckts.common.utils.DateUtil;
import com.vkl.ckts.rgsy.statistic.log.entity.OperaDaily;
import com.vkl.ckts.rgsy.statistic.log.service.ILogService;
/**
 * 操作日志
 * @author xujunhua
 * @date 2017年4月14日
 * @ClassName: LogController
 */
@Controller
@RequestMapping("/statistic/log")
public class LogController {
	
	@Autowired
	ILogService ils;	
	
	Logger log = Logger.getLogger(LogController.class); //日志信息
	
	/**
	 * 页面跳转
	 * @return 返回页面
	 */
	@RequestMapping("/logIndex")
	public String logIndex(){
		return "com/vkl/ckts_pc/rgsy/OperaDaily";
	}
	
	/**
	 * 分页
	 * @param pageNo
	 * @param pageSize
	 * @param start
	 * @param end
	 * @param loginName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/log")
	public List<Object> logIndex(Integer pageNo, Integer pageSize, String start, String end, String loginName){
		OperaDaily ole  = new OperaDaily();			// 条件查询对象
		List<OperaDaily> logList = null;
		int count = 0;
		try {
			logList = ils.allOperDaily(pageNo, pageSize, start, end, loginName);	// 日志信息
			ils.count(ole);	 // 日志总计
		} catch (Exception e) {
			log.info("操作日志，捕获异常"+e.getMessage());
			e.printStackTrace();
		}
		List<Object> list = new ArrayList<Object>();
		ole.setStart(DateUtil.parseDate(start));
		ole.setEnd(DateUtil.parseDate(end));
		ole.setOperName(loginName);
		list.add(0,count);
		list.add(1, logList);
 		return list;
	}
}
