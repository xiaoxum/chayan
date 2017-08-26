package com.vkl.ckts.cksy.base.interceptor;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;







import com.vkl.ckts.cksy.base.utils.UserUtils;
import com.vkl.ckts.common.annotation.Permission;
import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.constr.ErrorInfo;
import com.vkl.ckts.common.entity.MenuEntity;
import com.vkl.ckts.common.entity.UserEntity;


/**
 *  用户权限拦截
 * @author xiaoxu
 * @version 1.0
 */
public class UserAuthorityInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}
    
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		// 获得当前的用户
		// 获取session
		String servletPath = request.getServletPath();
		if(servletPath.startsWith("/static")||servletPath.startsWith("/photo")||servletPath.startsWith("/login.jsp")||servletPath.startsWith("/module/login/")){
			return true;
		}
		HttpSession session = request.getSession();
		// 获取登陆用户
		UserEntity user = (UserEntity) session.getAttribute(Constrant.S_USER_SESSION);
		if("1".equals(user.getSfgly())){
			//当前不是管理员
			return true;
		}
        if(!isAuthority(user, arg2, request)){
        	if(isAjaxReq(request,response)){
        		//ajax请求
        		PrintWriter out = response.getWriter();
        		//用户无权限
        		out.print("{\"msg\":\""+ErrorInfo.S_USER_NO_AUTHOR+"\"}");
        		out.flush();
        		
        	}else{
        		HttpSenderUrl httpSenderUrl = new HttpSenderUrl(response);
        		httpSenderUrl.setParameter("msg", "no-author");
    			//重定向
    			httpSenderUrl.sendByPost(request.getContextPath()+"/error.jsp");
    			return false;
        		
        	}
        	return false;
         }
		return true;
	}

	/**
	 *  判断该用户是否有当前的权限
	 * @param user
	 * @param arg2
	 * @param request
	 * @return
	 */
	public boolean isAuthority(UserEntity user, Object arg2, HttpServletRequest request) {
		HandlerMethod handlerMethod = (HandlerMethod) arg2;
		// 获得权限注解
		Permission permission = handlerMethod.getMethodAnnotation(Permission.class);
		if (permission == null) {
			// 未设置权限，放行
			return true;
		}
		// 获得权限标识
		String zcddmValue = permission.value();
		// 获得当前用户的所有权限
		List<MenuEntity> cdzxxs = UserUtils.findUserZgx(user.getId());
		if (cdzxxs == null || cdzxxs.isEmpty()) {
			// 当前用户没有任何权限
			return false;
		}
		for (MenuEntity cdzxx : cdzxxs) {
			if (zcddmValue.equals(cdzxx.getMenuFlag())) {
				// 当前用户拥护该权限
				return true;
			}
		}
		return false;
	}

	
	/**
	 * 判断是否ajax请求
	 * 
	 * @param req
	 * @param rep
	 * 
	 * @return
	 */
	public boolean isAjaxReq(HttpServletRequest req,HttpServletResponse rep){
		if (req.getHeader("x-requested-with")!= null && req.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
			//ajax请求
			return true;
		}
		//同步请求
		return false;
	}
}
