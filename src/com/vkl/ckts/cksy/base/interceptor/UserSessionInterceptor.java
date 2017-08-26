package com.vkl.ckts.cksy.base.interceptor;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.entity.UserEntity;



/**
 * 用户session拦截器
 * 
 * @author xiaoxu
 * 
 * @version 1.0
 */
public class UserSessionInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}
    /**
     * 事前拦截
     */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String servletPath = request.getServletPath();
		if(servletPath.startsWith("/static")||servletPath.startsWith("/photo")||servletPath.startsWith("/login.jsp")||servletPath.startsWith("/module/login/")){
			return true;
		}
		if (servletPath.startsWith("/module/servacpt/uploadpic")) {
			return true;
		}
		// 获取session
		HttpSession session = request.getSession();
		// 获取登陆用户
		UserEntity user = (UserEntity) session.getAttribute(Constrant.S_USER_SESSION);
		if(user==null){
			if(isAjaxReq(request,response)){
        		//ajax请求
        		PrintWriter out = response.getWriter();
        		//用户无权限
        		out.print("no-login");
        		out.flush();
        		
        	}else{
    			//当前用户不存在
    			HttpSenderUrl httpSenderUrl = new HttpSenderUrl(response);
    			//httpSenderUrl.setParameter("msg", "当前用户已失效或不存在，请重新登录");
    			httpSenderUrl.setParameter("msg", "no-login");
    			//重定向
    			httpSenderUrl.sendByPost(request.getContextPath()+"/error.jsp");
        	}
			return false;
		}
		return true;
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
