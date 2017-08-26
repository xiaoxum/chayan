package com.vkl.ckts.rgsy.statistic.count.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vkl.ckts.common.entity.ChkptFileEntity;
import com.vkl.ckts.common.entity.CkCllxEntity;
import com.vkl.ckts.common.entity.OperTypeEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.rgsy.statistic.count.entity.CheckCount;
import com.vkl.ckts.rgsy.statistic.count.service.ICheckCountService;
import com.vkl.ckts.rgsy.statistic.count.service.IViewerCountService;
import com.vkl.ckts.rgsy.system.checksetting.service.ICheckSettingService;
@Controller
@RequestMapping("/count/viewer")
public class ViewerCountController {
	@Autowired
	IViewerCountService ivcs;	// 注入查验员合格率service
	@Autowired
	ICheckSettingService ics;	// 注入查验项设置service
	@Autowired
	ICheckCountService iccs;	// 注入查验区合格率service
	Logger log = Logger.getLogger(ViewerCountController.class); 	// 日志对象
	/**
	 * 查验员合格率统计页面加载
	 */
	@RequestMapping("/viewerIndex")
	public String viewerIndex(Model model){
		List<OperTypeEntity> operType = ics.allOperType();	// 所有业务类型
		List<CkCllxEntity> ckCllx = ics.allCKCllx();		// 所有车辆类型
		List<ChkptFileEntity> allChkpt = iccs.allChkpt();	// 所有查验区
		model.addAttribute("allOType", operType);
		model.addAttribute("allCllx", ckCllx);		
		model.addAttribute("allChkpt", allChkpt);
		return "com/vkl/ckts_pc/rgsy/viewerCount";
	}
	
	/**
	 * 分页
	 */
	@ResponseBody
	@RequestMapping("/viewerPage")
	public List<Object> viewerPage(Page<CheckCount> page,String end,String start, String organCode,String cllx,String operType){
		CheckCount vc = new CheckCount();		// 定义对象封装查询条件
		vc.setOrganCode(organCode);				// 封装查验场地
		vc.setCllx(cllx);						// 封车辆类型
		vc.setOperType(operType); 				// 封装业务类型
		vc.setStart(start);						// 开始统计时间
		vc.setEnd(end);							// 截止统计时间
		page.setT(vc);
		List<Object> list = new ArrayList<Object> ();
		int count = ivcs.findCount(vc);			// 总记录数
		List<CheckCount> allVC = null;
		try {
			allVC = ivcs.allCheckInfo(page);	// 所有查验信息
		} catch (UnsupportedEncodingException e) {
			log.info("查验员合格率，捕获异常"+e.getMessage());
			e.printStackTrace();
		}	
		list.add(0, count);
		list.add(allVC);
		return list;
	}
}
