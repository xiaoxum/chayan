package com.vkl.ckts.rgsy.system.photosetting.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vkl.ckts.common.entity.OperTypeEntity;
import com.vkl.ckts.common.entity.ResProEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.rgsy.system.checksetting.service.ICheckSettingService;
import com.vkl.ckts.rgsy.system.photosetting.entity.OperRes;
import com.vkl.ckts.rgsy.system.photosetting.service.IResProService;

/**
 * 高拍项设置
 * @author xujunhua
 * @date 2017年3月29日
 * @ClassName: ResProController
 */
@Controller
@RequestMapping("/system/respro")
public class ResProController {
	@Autowired
	IResProService irps;
	@Autowired
	ICheckSettingService icss;	// 查验项注入
	
	// 得到日志对象
	Logger log = Logger.getLogger(ResProController.class);
	
	/**
	 * 高拍项设置主页
	 */
	@RequestMapping("/highPicIndex")
	public String highPicIndex(Model model){
		log.info("高拍项设置.............................");
		List<OperTypeEntity> allOper = icss.allOperType();
		model.addAttribute("allOper", allOper);
		return "com/vkl/ckts_pc/rgsy/highPic";
	}
	/**
	 * 分页
	 */
	@ResponseBody
	@RequestMapping("/highPicPage")
	public List<Object> highPicPage(Page<OperRes> page,String operType){
		log.info("高拍项设置.............................");
		OperRes or = new OperRes();		// 定义实体类封装查询条件
		or.setOperType(operType);
		page.setT(or);
		List<OperRes> allRes = irps.findAll(page).getList();	// 得到 所有高拍项设置
		int count = irps.findCount(or);							// 得到高拍项设置总记录数
		List<Object> list = new ArrayList<Object>();
		list.add(0, count);
		list.add(1, allRes);
		return list;
	}
	/**
	 * 去新增设置
	 */
	@RequestMapping("/toAddHP")
	public String toAddHP(Model model){
		log.info("高拍项设置.............................");
		List<OperTypeEntity> allOper = icss.allOperType();		// 得到所有业务类型
		List<ResProEntity> allResP = irps.allResPro();			// 得到所有高拍项目
		model.addAttribute("allOper", allOper);
		model.addAttribute("allResP", allResP);
		return "com/vkl/ckts_pc/rgsy/highpicadd";
	}
	/**
	 * 新增设置
	 */
	@ResponseBody
	@RequestMapping("/doAddHP")
	public String doAddHp(OperRes or,HttpServletRequest request){
		boolean flag = false;
		try {
			flag = irps.addHPic(or,request);
		} catch (Exception e) {
			log.info("高拍项设置，捕获异常"+e.getMessage());
			e.printStackTrace();
		}
		if(flag){
			return "true";
		}
		return "false";
	}
	/**
	 * 删除设置项
	 */
	@RequestMapping("/delHP")
	public String delHp(HttpServletRequest request,OperRes or){
		try {
			irps.delHPic(or, request);
			request.getSession().setAttribute("Flag11", "del");
		} catch (Exception e) {
			log.info("高拍项设置，捕获异常"+e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/system/respro/highPicIndex";
	}
	/**
	 * 修改页面加载
	 */
	@RequestMapping("/toUpd")
	public String toUpd(Model model,OperRes or){
		log.info("高拍项设置.............................");
		List<ResProEntity> allResP = irps.allResPro();			// 得到所有高拍项目
		List<OperRes> allORes = irps.existSet(or);				// 得到修改项
		model.addAttribute("allResP", allResP);
		model.addAttribute("allORes", allORes);
		return "com/vkl/ckts_pc/rgsy/updhighpic";
	}
	/**
	 * 修改高拍设置
	 */
	@RequestMapping("/doUpd")
	public String doUpd(OperRes or, HttpServletRequest request){
		try {
			irps.updHighPic(or, request);	// 修改高拍项
		} catch (Exception e) {
			log.info("高拍项设置，捕获异常"+e.getMessage());
			e.printStackTrace();
		}
		request.getSession().setAttribute("Flag11", "upd");
		return "redirect:/system/respro/highPicIndex";
	}
}
