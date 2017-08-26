package com.vkl.ckts.pub.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vkl.ckts.cksy.base.service.BgdDyServiceImpl;
import com.vkl.ckts.cksy.photoinfo.service.IPhotoService;
import com.vkl.ckts.cksy.vehicleinformation.entity.CkInfoEntityDto;
import com.vkl.ckts.cksy.vehicleinformation.service.IVehicleInfoService;
import com.vkl.ckts.common.base.Message;
import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.constr.ErrorInfo;
import com.vkl.ckts.common.controller.SpringContextHolder;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.log.LogUtils;
import com.vkl.ckts.common.utils.RequestUtils;
import com.vkl.ckts.pub.service.IBaseService;

/**
 * 用户请求处理
 * 
 * @author hwf
 * @version 1.0
 */
@Controller
@RequestMapping("module/login")
public class LoginController {

	@Autowired
	IBaseService baseService;
	
	
	// 日志
	Logger log = Logger.getLogger(LoginController.class);

	/**
	 * 登录
	 *
	 * @param userEntity
	 * @param request
	 * @return 登录返回的结果
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Message<String> login(Message<String> message,UserEntity userEntity, HttpServletRequest request,HttpServletResponse response) {
		try{
			// 将登录返回的结果存入map
			message.setErrorMsg(baseService.checkLogin(userEntity, request));
			if("登录成功".equals(message.getErrorMsg())){
				// 插入操作日志
				Cookie cookie = new Cookie("loginName", userEntity.getLoginName());
				cookie.setMaxAge(30);
				Cookie cookie1 = new Cookie("loginPwd", userEntity.getLoginPwd());
				cookie.setMaxAge(5*365*24*60*60); //使cookie失效      
				cookie1.setMaxAge(5*365*24*60*60);      
				 cookie.setPath("/");   //这个不能少      
				 cookie1.setPath("/");
				response.addCookie(cookie1);
				response.addCookie(cookie);
				//cookie.
				//response.set
				LogUtils.insertLog("登录", "用户登录", "用户登录", request);
			}
			return message;
		}catch (Exception e){
			e.printStackTrace();
			message.setErrorCode(ErrorInfo.S_SYS_EXCEPTION);
			log.error(e);
			
			return message;
		}
	}

	/**
	 * 退出
	 * 
	 * @param response
	 * @param request
	 * @param userEntity
	 */
	@RequestMapping("/exit")
	public void exit(HttpServletResponse response, HttpServletRequest request, UserEntity userEntity) {
		//Integer iiInteger=Integer.parseInt("as");
		try {
			// 插入操作日志
			LogUtils.insertLog("下线", "用户注销", "用户注销", userEntity.getId(),RequestUtils.getIp(request));
			// 从Session中取当前用户对象
			// 下线
			baseService.offLine(userEntity);
			// 把用户从Session中移除
			request.getSession().removeAttribute(Constrant.S_USER_SESSION);
			// 重定向到登录页面
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="getdysj",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Map<String, List<Map<String, Object>>> getDySj(String srln,String rckCount){
		BgdDyServiceImpl bgdDyServiceImpl = new BgdDyServiceImpl();
		Map<String, Object> map=bgdDyServiceImpl.test(srln, rckCount);
		if (map==null) {
			map = new HashMap<String, Object>();
		}
		List<Map<String, Object>> lists=new ArrayList<>();
		lists.add(map);
		Map<String, List<Map<String, Object>>> maps=new HashMap<String, List<Map<String, Object>>>();
		maps.put("detial", lists);
		return maps;
	}
	
	@RequestMapping("getdysj1")
	@ResponseBody
	public String getDySj1(String srln,String rckCount){
	String jsonString="{\"ddd\":[{\"ProductID\": 2,\"ProductName\": \"牛奶\",\"UnitPrice\": 15.2,\"Quantity\": 20,\"Amount\": 304}]}";     
//  
//          
//        
        
    
         return jsonString;
	
//		
//		
	}
	
	
	@RequestMapping(value="getphotosj",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Map<String, List<Map<String, Object>>> getphotosj(String picUrl,Integer printNum,String srln,String rckCount,String picId){
		Map<String, Object> map=new HashMap<String, Object>();;
		if (printNum<=0) {
			return null;
		}
		map.put("img1", picUrl);
		if (printNum>1) {
			map.put("img2", picUrl);
		}
		IPhotoService photoService=SpringContextHolder.getBean(IPhotoService.class);
		photoService.updatePicDyStatu(srln, rckCount, picId);
		List<Map<String, Object>> lists=new ArrayList<>();
		lists.add(map);
		Map<String, List<Map<String, Object>>> maps=new HashMap<String, List<Map<String, Object>>>();
		maps.put("detial", lists);
		return maps;
	}
	
	
	@RequestMapping(value="getbzxx",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Map<String, List<Map<String, Object>>> getbzxx(String clsbdh,String csys,String srln,String cllxName,String hpzlName,String hphm,String rckCount){
		Map<String, Object> map=new HashMap<String, Object>();;
		IVehicleInfoService vIVehicleInfoService=SpringContextHolder.getBean(IVehicleInfoService.class);
		CkInfoEntityDto ckInfoEntityDto=vIVehicleInfoService.findVehicleInfo(srln, rckCount, null);
		map.put("clsbdh", ckInfoEntityDto.getClsbdh());
		map.put("csys",ckInfoEntityDto.getCsys());
		map.put("srln", srln);
		map.put("cllxName", ckInfoEntityDto.getCllxName());
		map.put("hpzlName", ckInfoEntityDto.getHpzlName());
		map.put("hphm", ckInfoEntityDto.getHphm());
		List<Map<String, Object>> lists=new ArrayList<>();
		lists.add(map);
		Map<String, List<Map<String, Object>>> maps=new HashMap<String, List<Map<String, Object>>>();
		maps.put("detial", lists);
		return maps;
	}
	
	
	
	
	@RequestMapping(value="getattactphotosj",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Map<String, List<Map<String, Object>>> getattactphotosj(String picUrl,Integer printNum){
		Map<String, Object> map=new HashMap<String, Object>();;
		if (printNum<=0) {
			return null;
		}
		map.put("img1", picUrl);
		if (printNum>1) {
			map.put("img2", picUrl);
		}
		List<Map<String, Object>> lists=new ArrayList<>();
		lists.add(map);
		Map<String, List<Map<String, Object>>> maps=new HashMap<String, List<Map<String, Object>>>();
		maps.put("detial", lists);
		return maps;
	}
}
