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
import com.vkl.ckts.rgsy.statistic.count.service.IOverallCountService;
import com.vkl.ckts.rgsy.system.checksetting.service.ICheckSettingService;

@Controller
@RequestMapping("/count/overall")
public class OverallCountController {
	@Autowired
	IOverallCountService iocs;	// 注入外廓合格率Service
	@Autowired				
	ICheckSettingService icss;	// 注入查验项设置Service
	@Autowired
	ICheckCountService iccs;	// 注入查验区合格率Service
	Logger log = Logger.getLogger(OverallCountController.class);
	/**
	 * 外廓尺寸查验合格率页面加载
	 */
	@RequestMapping("/overallIndex")
	public String overallIndex(Model model){
		List<OperTypeEntity> operType = icss.allOperType();	// 所有业务类型
		List<CkCllxEntity> ckCllx = icss.allCKCllx();		// 所有车辆类型
		List<ChkptFileEntity> allChkpt = iccs.allChkpt();	// 所有查验区
		model.addAttribute("allOType", operType);
		model.addAttribute("allCllx", ckCllx);		
		model.addAttribute("allChkpt", allChkpt);
		return "com/vkl/ckts_pc/rgsy/overallCount";
	}
	@ResponseBody
	@RequestMapping("/overallPage")
	public List<Object> overallPage(Page<CheckCount> page,String end,String start, String organCode,String cllx,String operType){
		CheckCount cc = new CheckCount();	// 定义实体类封装查询条件
		cc.setEnd(end);						// 搜索截止时间
		cc.setStart(start);					// 搜索开始时间
		cc.setOrganCode(organCode);			// 查验区编号
		cc.setCllx(cllx);					// 车辆类型
		cc.setOperType(operType);			// 业务类型
		page.setT(cc);
		List<CheckCount> allCheck = null;
		int count = 0;
		try {
			allCheck = iocs.allCC(page);	// 查验信息
			count = iocs.findCount(cc);		// 查验总计
		} catch (Exception e) {
			log.info("外廓尺寸，捕获异常"+e.getMessage());
			e.printStackTrace();
		}
		List<Object> list = new ArrayList<Object>();
		list.add(0, count);
		list.add(1, allCheck);
		return list;
		
	}
}
