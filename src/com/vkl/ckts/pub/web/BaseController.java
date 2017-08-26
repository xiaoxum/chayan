package com.vkl.ckts.pub.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.entity.SysConfigEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.log.LogUtils;
import com.vkl.ckts.common.utils.Base64Utils;
import com.vkl.ckts.pub.dto.MdxxDto;
import com.vkl.ckts.pub.service.IBaseService;

/**
 * 用户请求处理
 * 
 * @author hwf
 * @version 1.0
 */
@Controller
@RequestMapping("module/base")
public class BaseController {

	@Autowired
	IBaseService baseService;
	
	
	// 日志
	Logger log = Logger.getLogger(BaseController.class);



	/**
	 * 加载首页
	 * 
	 * @return 首页的路径
	 */
	@RequestMapping("/home")
	public String loadHome(HttpServletRequest request) {
		String userId=((UserEntity)request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId();
	     request.setAttribute("userId", userId);
		return "com/vkl/ckts_pc/home";
	}
	
	/**
	 * 跳转修改密码页
	 * 
	 * @return 密码修改页
	 */
	@RequestMapping("/uppwd")
	public String uppwd(HttpServletRequest request) {
		return "com/vkl/ckts_pc/pub/uppwd";
	}
	
	/**
	 * 修改密码
	 * 
	 * @return 
	 */
	@RequestMapping(value="/uppwdAction" , produces = {"application/text;charset=UTF-8"})
	@ResponseBody
	public String uppwdAction(String password,String newpassword, HttpServletRequest request) {
		
		// 从Session中取当前用户对象
		UserEntity userEntity = (UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION);
		try {
			
		// 插入操作日志
			LogUtils.insertLog("修改密码", "用户修改密码", "用户修改密码", request);
		// 密码加密后传入	
	    return baseService.uppwdAction(Base64Utils.encode(password), Base64Utils.encode(newpassword), userEntity.getId());	
	    
		} catch (Exception e) {
			
		   e.printStackTrace();
		}
		
		return "false";
	}
	
	
	/**
	 * 跳转修改本地设置页
	 * 
	 * @return 本地设置页
	 */
	@RequestMapping("/sys")
	public String sys(HttpServletRequest request,Model model) {
		List<SysConfigEntity> currSet = baseService.selSet();
		model.addAttribute("set", currSet);
		return "com/vkl/ckts_pc/pub/sys";
	}
	
	/**
	 * 修改本地设置
	 * @param key 
	 * @param value  
	 * @return 
	 */
	@RequestMapping("/sysup")
	@ResponseBody
	public String sysup(String key,String value,HttpServletRequest request) {
		try {
		// 插入操作日志
		LogUtils.insertLog("修改本地设置", "修改本地设置", "修改本地设置", request);	
		// 修改本地设置
		 return baseService.sysup(key, value);
		} catch (Exception e) {
		  e.printStackTrace();
		  return "false";
		}
		
	}


	/**
	 * 获取菜单信息
	 * 
	 * @param request
	 * 
	 * @return
	 */
	@RequestMapping("/findmdxx")
	@ResponseBody
	public List<MdxxDto> findMdxx(HttpServletRequest request){
		//获取当期那用户编号
		UserEntity yhxx=(UserEntity)request.getSession().getAttribute(Constrant.S_USER_SESSION);
		List<MdxxDto> lists=null;
		String yhbh=null;
		if("1".equals(yhxx.getSfgly())){
			//当前不是管理员
			lists=baseService.findMdxx2();
		}else {
			yhbh=yhxx.getId();
			lists = baseService.findMdxx(yhbh);;
		}
	
		return lists;
	}
	
	/**
	 * 获取菜单信息
	 * 
	 * @param request
	 * 
	 * @return
	 */
	@RequestMapping("/findzcdxx")
	@ResponseBody
	public List<MdxxDto> findZcdxx(String  cdId,HttpServletRequest request){
		//获取当期那用户编号
		UserEntity yhxx=(UserEntity)request.getSession().getAttribute(Constrant.S_USER_SESSION);
		List<MdxxDto> lists = null;
		String yhbh=null;
		if("1".equals(yhxx.getSfgly())){
			//当前不是管理员
			lists=baseService.findZcdxx2(cdId);
		}else {
			yhbh=yhxx.getId();
			lists = baseService.findZcdxx(yhbh, cdId);
		}
		return lists;
	}
	
	
	/**
	 * 跳转到500页面
	 * 
	 * @param request
	 * 
	 * @return
	 */
	@RequestMapping("to500")
	public String toerror(){
		return "com/vkl/ckts_pc/pub/500";
	}
	/**
	 * 跳转修改密码页
	 * 
	 * @return 密码修改页
	 */
	@RequestMapping("/downplugin")
	public String downplugin(HttpServletRequest request) {
		return "com/vkl/ckts_pc/pub/downplugin";
	}
	
}
