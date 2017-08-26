package com.vkl.ckts.cksy.base.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * 重定向拦截
 * 
 * @author xiaoxu
 * 
 * @version 1.0
 *
 */
public class HttpSenderUrl {
	/**
	 * 参数列表
	 */
	Map<String, String> parameter = new HashMap<String, String>(); 
	/**
	 * 响应
	 */
	HttpServletResponse response;

	public HttpSenderUrl(HttpServletResponse response) {
		this.response = response;
	}
    /**
     * 参数设置
     * 
     * @param key     键
     * @param value   值
     */
	public void setParameter(String key, String value) {
		this.parameter.put(key, value);
	}

	/**
	 * 重定向传参
	 * 
	 * @param url
	 *            重点项路径
	 * 
	 * @throws IOException
	 */
	public void sendByPost(String url) throws IOException {
		this.response.setContentType("text/html");
		PrintWriter out = this.response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println(" <HEAD><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><TITLE>sender</TITLE></HEAD>");
		out.println(" <BODY>");
		out.println("<form name=\"submitForm\" action=\"" + url + "\" method=\"post\">");
		Iterator<String> it = this.parameter.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
	        String value = this.parameter.get(key);
			value=URLEncoder.encode(value,"utf-8");
			out.println("<input type=\"hidden\" name=\"" + key + "\" value=\"" + value + "\"/>");
		}
		out.println("</from>");
		out.println("<script>window.document.submitForm.submit();</script> ");
		out.println(" </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
}
