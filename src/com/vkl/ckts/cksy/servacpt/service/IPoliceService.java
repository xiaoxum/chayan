package com.vkl.ckts.cksy.servacpt.service;



import com.vkl.ckts.common.base.Message;

/**
 * @see 模拟六合一接口
 * @author jiajun
 * @version 1.0
 */
public interface IPoliceService {
	
	Message<Object> policeCheck(String clsbdh,String hphm );
	
	
	

}
