package com.vkl.ckts.rgsy.system.set.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.entity.SysConfigEntity;
import com.vkl.ckts.rgsy.system.set.service.ISetService;

/**
 * 系统设置
 * @author xujunhua
 * @date 2017年3月31日
 * @ClassName: SetController
 */
@Controller
@RequestMapping("/system")
public class SetController {
	@Autowired
	ISetService iss;	// 注入系统设置Service
	
	Logger log = Logger.getLogger(SetController.class);
	/**
	 * 系统设置
	 */
	@RequestMapping("/systemSet")
	public String systemSet(Model model){
		log.info("系统设置.................................");
		SysConfigEntity sce = new SysConfigEntity();
		sce.setKey(Constrant.S_VIDEO_INFO_READ_WAY);
		SysConfigEntity sc = iss.selStatu(sce);
		model.addAttribute("sce", sc);
		return "";
	}
	
	/**
	 * 更该系统设置
	 */
	@RequestMapping("/setSystem")
	public String setSystem(SysConfigEntity sce,HttpServletRequest request){
		try {
			iss.updStatu(sce, request);
		} catch (Exception e) {
			log.info("系统设置，捕获异常"+e.getMessage());
			e.printStackTrace();
		}
		return "";
	}
}
