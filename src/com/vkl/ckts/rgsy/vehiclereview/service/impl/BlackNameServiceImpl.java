package com.vkl.ckts.rgsy.vehiclereview.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.rgsy.statistic.log.service.ILogService;
import com.vkl.ckts.rgsy.vehiclereview.dao.IBlackNameDao;
import com.vkl.ckts.rgsy.vehiclereview.entity.VehBlackName;
import com.vkl.ckts.rgsy.vehiclereview.service.IBlackNameService;

/**
 * 车辆黑名单
 * @author xujunhua
 * @date 2017年4月7日
 * @ClassName: BlackNameService
 */
@Service
@Transactional
public class BlackNameServiceImpl extends ServiceImpl<IBlackNameDao, VehBlackName, String> implements IBlackNameService {
	
	@Autowired
	IBlackNameDao ibnd;
	@Autowired
	ILogService ils;	// 注入操作日志service
	/**
	 * 查看车辆黑名单详情
	 * @param id 车辆黑名单编号
	 * @return 
	 */
	public VehBlackName bNameDetail(VehBlackName vn){
		vn =  ibnd.bNameDetail(vn);
		String[] im = null;
		StringBuffer sb = null;
		try {
			im = vn.getImplCkPros().split(",");	// 重点查验项编号数组
		
		 sb = new StringBuffer();
		// 循环查询重点查验项名称
		for(int i=0; i < im.length; i++){
			vn.setImplCkPros(im[i]);	// 查询条件
			vn =ibnd.bNameDetail(vn);
			sb.append(vn.getIcp()+",");
		}
		
		vn.setImplCkPros(sb.toString().substring(0,sb.length()-1));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return vn;
	}
	/**
	 * 查验记录
	 * @param clsbdh 车辆识别代号
	 * @return
	 */
	public List<VehBlackName> checkRecord(String clsbdh){
		return ibnd.checkRecord(clsbdh);
	}
	/**
	 * 解除黑名单
	 * @param clsbdh
	 */
	public void changeWhite(String clsbdh, HttpServletRequest request)throws Exception{
		ils.insertLog("黑名管理", "业务管理", "解除黑名单", request);
		ibnd.changeWhite(clsbdh);
	}
}
