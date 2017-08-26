package com.vkl.ckts.wss.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
/**
 * 中文字符字符转换
 * 
 * @author xiaoxu
 * 
 * @version 1.0
 */
public class URLDeEUtils {

	/**
	 * 将中文字符转换为utf-8
	 * 
	 * @param xmlDoc      文档内容
	 * 
	 * @return            转换后内容
	 */
	public static String encodeUTF8(String xmlDoc){
		String str="";
		try {
			str=URLEncoder.encode(xmlDoc,"utf-8");
		} catch (UnsupportedEncodingException e) {
			str=e.toString();
		}
		return str;
	}
	/**
	 * 将utf8转换为中文字符
	 * 
	 * @param xmlDoc    文档内容
	 * 
	 * @return          转换后内容
	 */
	public static String decodeUTF8(String xmlDoc){
		String str="";
		try {
			str=URLDecoder.decode(xmlDoc,"utf-8");
		} catch (UnsupportedEncodingException e) {
			str=e.toString();
		}
		return str;
	}
}
