package com.vkl.ckts.rgsy.jurisdict.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vkl.ckts.common.service.IAreaEntityService;
import com.vkl.ckts.common.service.IDictEntityService;
import com.vkl.ckts.rgsy.dept.service.IDeptService;
import com.vkl.ckts.rgsy.dept.web.DeptController;
import com.vkl.ckts.rgsy.statistic.log.service.ILogService;

/**
 * 权限管理Controller
 *
 * @author hwf
 * @version 1.0
 */
@Controller
@RequestMapping("module/menu")
public class MenuController {
	// 注入字典表Service
	@Autowired
	IDictEntityService dictEntityService;
	// 注入部门Service
	@Autowired
	IDeptService deptService;
	// 注入操作日志
	@Autowired
	ILogService logService;
	// 注入地区Service
	@Autowired
	IAreaEntityService areaService;
	// 获取日志对象
	Logger log = Logger.getLogger(DeptController.class);

	/**
	 * 权限管理页面初始化
	 *
	 * @return
	 */
	@RequestMapping("qxgl")
	public String qxgl() {
		return "com/vkl/ckts_pc/rgsy/qxgl";
	}

	/**
	 * 添加权限页面初始化
	 *
	 * @return
	 */
	@RequestMapping("qxadd")
	public String qxadd(HttpServletRequest request) {

		return "com/vkl/ckts_pc/rgsy/qxadd";
	}
}
