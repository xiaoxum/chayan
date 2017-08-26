package com.vkl.ckts.pub.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vkl.ckts.common.base.Message;
import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.constr.ErrorInfo;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IDictEntityService;
import com.vkl.ckts.common.utils.Base64Utils;
import com.vkl.ckts.pub.dto.UserAdmin;
import com.vkl.ckts.pub.service.IBaseService;
import com.vkl.ckts.pub.service.IUserAdminService;
import com.vkl.ckts.rgsy.dept.service.IDeptService;

/**
 * 用户管理controller
 * @author xujunhua
 * @date 2017年4月10日
 * @ClassName: UserAdminController
 */
@Controller
@RequestMapping("/userAdmin")
public class UserAdminController {
	@Autowired
	IUserAdminService iuas;	// 注入用户管理service
	@Autowired
	IDictEntityService ides; // 注入字典表service
	@Autowired
	IBaseService ibs;	// 用户登录service
	Logger log = Logger.getLogger(UserAdminController.class);	// 日志
	
	@Autowired
	IDeptService deptService;
	/**
	 * 用户管理主页加载
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model){
		
		return "com/vkl/ckts_pc/pub/useradmin";
	}
	
	/**
	 * 分页
	 * @param page
	 * @param ue
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/page") 
	public List<Object> page(Page<UserAdmin> page,UserAdmin ue,HttpServletRequest request){
		ue.setDelFlag(IdEntity.DEL_FLAG_NORMAL);	// 封装删除标志
		ue.setUserName(Base64Utils.encode(ue.getUserName()));// 用户名称加密模糊查询
		UserEntity userEntity=(UserEntity)request.getSession().getAttribute(Constrant.S_USER_SESSION);
		if ("1".equals(userEntity.getSfgly())) {
			ue.setUserDept(null);	// 只能查询本部门用户
		}else {
			ue.setUserDept(userEntity.getUserDept());	// 只能查询本部门用户
		}
		page.setT(ue);
		List<UserAdmin> allUe = iuas.findAll(page).getList();
		// 遍历集合
		for(UserAdmin ua : allUe){
			// 判断是否是警员
			if(ua.getFuzz() == null){
				ua.setFuzz("");	// 不是，赋值""
			}
			// 判断性别是否为空
			if(ua.getUserSex() == null){
				ua.setUserSex("");
			}
			ua.setUserName(Base64Utils.decode(ua.getUserName()));	// 用户名称解密
			if(ua.getUserStatu() != null){
				ua.setUserStatu(ides.findInfoByKeyAndType(ua.getUserStatu(), "userStatu").getLdictionaryAbel()); // 键值对应取状态值 
			}
			if(ua.getIdentityCard() != null){
				ua.setIdentityCard(Base64Utils.encode(ua.getIdentityCard()));	// 解密身份证
			}
			if(ua.getUserPhone() != null){
				ua.setUserPhone(Base64Utils.encode(ua.getUserPhone())); 		// 解密手机号码
			}
		}
		List<Object> list = new ArrayList<Object>();
	
		int count = iuas.findCount(ue);
		list.add(0, count);
		list.add(1, allUe);
		return list;
	}
	/**
	 * 新增用户页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/uAddIndex")
	public String uAddIndex(Model model,HttpServletRequest request){
		UserEntity userEntity=(UserEntity)request.getSession().getAttribute(Constrant.S_USER_SESSION);
		//获取部门信息
//		DeptEntity deptEntity=new DeptEntity();
//		deptEntity.setId(userEntity.getUserDept()+"");
//		try {
//			deptEntity=deptService.findDeptById(deptEntity);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		request.setAttribute("parentId", deptEntity.getParentId());
		request.setAttribute("userDept", userEntity.getUserDept());
		return "com/vkl/ckts_pc/pub/useradd";
	}
	/**
	 * 添加用户
	 * @param ua 添加对象
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/add")
	public Message<String> addUser(UserAdmin ua, HttpServletRequest request){
		Message<String> message = new Message<String>();
		UserEntity uab = ibs.findUserByLoginName(ua);
		if(uab == null || IdEntity.DEL_FLAG_DELETE.equals(uab.getDelFlag())){
			try {
				ua.setLoginPwd("cyxtyh");
				iuas.addUser(ua, request);
				message.setErrorMsg("success");
			} catch (Exception e) {
				log.error(e);
				e.printStackTrace();
				message.setErrorCode(ErrorInfo.S_SQL_EXCEPTION);
			}
//		}else if(IdEntity.DEL_FLAG_DELETE.equals(uab.getDelFlag())){
////			ua.setLoginPwd("cyxtyh");
////			ua.setId(uab.getId());
////			ua.setUserName(Base64Utils.encode(ua.getUserName())); 		// 用户名加密
////			ua.setIdentityCard(Base64Utils.encode(ua.getIdentityCard()));// 身份证号加密
////			ua.setLoginPwd(Base64Utils.encode(ua.getLoginPwd())); 		// 用户密码加密
////			ua.setUserPhone(Base64Utils.encode(ua.getUserPhone()));		// 手机号加密
////			ua.setDelFlag(IdEntity.DEL_FLAG_NORMAL);					// 用户删除标志
////			ua.setCreater(((UserEntity)request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId());	// 创建者
////			ua.setCreateDate(new Date());																		// 创建时间
////			ua.setUpdater(((UserEntity)request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId());	// 修改者
////			ua.setUpdDate(new Date());																			// 修改时间
////			//ua.setUserDept(((UserEntity)request.getSession().getAttribute(Constrant.S_USER_SESSION)).getUserDept());// 用户部门
////			ua.setSfgly(IdEntity.S_NO);
////			iuas.updateUser(ua);
//			
//			
//			
//			
//			
//			message.setErrorMsg("success");
		}else {
			message.setErrorMsg("该用户已存在");
		}
		
		return message;
	}
	/**
	 * 修改用户页面
	 * @param model
	 * @param id 用户编号
	 * @return
	 */
	@RequestMapping("/toUserUpd")
	public String toUpdUser(Model model,String id,HttpServletRequest request){
		UserAdmin ua = iuas.oneUser(id);	// 得到查验项
		StringBuffer sb = new StringBuffer();
		// 将角色集合中的角色ID拼接成字符串
		for(int i=0; i<ua.getRoles().size(); i++){
			if(i==0){
				sb.append(ua.getRoles().get(i).getId());
			}else{
				sb.append(","+ua.getRoles().get(i).getId());
			}
		}
		ua.setuRole(sb.toString());	// 封装角色字符串
		model.addAttribute("ua", ua);
		UserEntity userEntity=(UserEntity)request.getSession().getAttribute(Constrant.S_USER_SESSION);
		//获取部门信息
//		DeptEntity deptEntity=new DeptEntity();
//		deptEntity.setId(userEntity.getUserDept()+"");
//		try {
//			deptEntity=deptService.findDeptById(deptEntity);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		request.setAttribute("parentId", deptEntity.getParentId());
		request.setAttribute("userDept", userEntity.getUserDept());
		request.setAttribute("userDept1", ua.getUserDept());
		return "com/vkl/ckts_pc/pub/userudp";
	}
	/**
	 * 修改
	 * @param ua
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/userUpd")
	public String userUpd(UserAdmin ua,HttpServletRequest request){
		try {
			iuas.userUpd(ua, request);	// 修改用户信息
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		request.getSession().setAttribute("flag11", "upd");	// 提示语
		return "upd";
	}
	/**
	 * 删除用户
	 * @param id	用户id
	 */
	@RequestMapping("/del")
	public String delUser(UserAdmin ua, HttpServletRequest request){
		Message<String> message = new Message<String>();
		try {
			iuas.deleUser(ua, request);
		} catch (Exception e) {
			log.error(e);
			message.setErrorCode(ErrorInfo.S_SYS_EXCEPTION);
			e.printStackTrace();
		}
		request.getSession().setAttribute("flag11", "del");	// 提示语
		return "redirect:/userAdmin/index";
	}
	
	
	/**
	 * 删除用户
	 * @param id	用户id
	 */
	@RequestMapping("/resertpwd")
	@ResponseBody
	public String resertpwd(UserAdmin ua, HttpServletRequest request){
		Message<String> message = new Message<String>();
		try {
			iuas.updatePwd(ua);
			return "success";
		} catch (Exception e) {
			log.error(e);
			message.setErrorCode(ErrorInfo.S_SYS_EXCEPTION);
			e.printStackTrace();
			return "sys-error";
		}
	}
}
