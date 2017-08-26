package com.vkl.ckts.rgsy.statistic.count.web;


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
import com.vkl.ckts.rgsy.system.checksetting.service.ICheckSettingService;

@Controller
@RequestMapping("/count/check/")
public class CheckCountController {
	@Autowired
	ICheckCountService iccs;	// 查验去合格率service
	@Autowired
	ICheckSettingService ics;	// 查验项设置service
	Logger log = Logger.getLogger(CheckCountController.class); // 日志对象
	/**
	 * 查验站统计查询页面加载
	 */
	@RequestMapping("checkCountIndex")
	public String checkCountIndex(Model model){
		List<CkCllxEntity> allCllx = ics.allCKCllx();		// 所有车辆类型
		List<ChkptFileEntity> allChkpt = iccs.allChkpt();	// 所有查验区
		List<OperTypeEntity> allOType = ics.allOperType();	// 所有业务类型
		model.addAttribute("allCllx", allCllx);
		model.addAttribute("allChkpt", allChkpt);
		model.addAttribute("allOType", allOType);
		return "com/vkl/ckts_pc/rgsy/checkCount";
	}

	/**
	 * 分页
	 */
	@ResponseBody
	@RequestMapping("checkCountPage")
	public List<Object> checkCountPage(Page<CheckCount> page,String start,String end,String organCode, String cllx, String operType){
		CheckCount cc = new CheckCount();
		cc.setOrganCode(organCode);					// 查验场地
		cc.setCllx(cllx);							// 车辆类型
		cc.setOperType(operType);					// 业务类型
		if(start != null && !"".equals(start)){
			cc.setStart(start);	// 如果查询开始时间不为空
		}
		if(end != null && !"".equals(end)){
			cc.setEnd(end);		// 如果查询结束时间不为空
		}
		page.setT(cc);
		List<CheckCount> allCheckCount = null;
		int allCount = 0;
		try{
			allCheckCount = iccs.countList(page);	// 获得所有查验信息
			allCount = iccs.findCount(cc);			// 分页所有查验信息记录数
		}catch(Exception e){
			log.info("查验区合格率，捕获异常"+e.getMessage());
			e.printStackTrace();
		}						
		List<Object> list = new ArrayList<Object>();
		list.add(0, allCount);
		list.add(1, allCheckCount);
		return list;
	}
	@RequestMapping("statist")
	public String statistics(Model model){

		List<CkCllxEntity> allCllx = ics.allCKCllx();	
		model.addAttribute("allCllx", allCllx);
		List<ChkptFileEntity> allChkpt = iccs.allChkpt();
		model.addAttribute("allChkpt", allChkpt);
        model.addAttribute("syxzs",ics.allUsering());
        model.addAttribute("allOType", ics.allOperType());
		return "com/vkl/ckts_pc/rgsy/ywltj";
	}
	/*
	 * 分页
	 * @param page
	 * 
	 * */
	@ResponseBody
	@RequestMapping("seacherbyClxx")
	public Page<CheckCount> seacherbyClxx(Page<CheckCount> page,CheckCount cc,String time){
		    List<CheckCount> list=null;
//			CheckCount cc = new CheckCount();						
//			cc.setOrganCode(organCode);					// 查验场地										
//			cc.setSyxz(syxz);
//			cc.setStart(start);
//			cc.setEnd(end);
			page.setT(cc);
			list=iccs.findBfxxByTime(page);
			page.setTotalCount(iccs.findBfxxByTimeCount(cc));
			/*for(int i=0;i<list.size();i++){
				list.get(i).setShowTime(list.get(i).getDay().toString());
			}*/
			page.setList(list);			
			return page;			

	}	
}

