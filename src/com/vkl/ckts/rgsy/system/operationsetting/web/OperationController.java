package com.vkl.ckts.rgsy.system.operationsetting.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vkl.ckts.common.entity.CkProjectEntity;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.rgsy.system.checksetting.service.ICheckSettingService;
import com.vkl.ckts.rgsy.system.operationsetting.entity.newProAnnc;
import com.vkl.ckts.rgsy.system.operationsetting.service.IOperationService;

@Controller
@RequestMapping("/system/operaSetting")
public class OperationController {
	@Autowired
	IOperationService is;
	@Autowired
	ICheckSettingService iss;
	Logger log = Logger.getLogger(OperationController.class);	// 获取日志信息
	/**
	 * 业务信息设置首页加载
	 * @param model
	 * @return
	 */
	@RequestMapping("/operaIndex")
	public String operaIndex( Model model){
		List<CkProjectEntity> ckPro = iss.allCkProject(new CkProjectEntity());	// 获得所有查验项
		model.addAttribute("ckPro", ckPro);
		return "com/vkl/ckts_pc/rgsy/ywxxsz";
	}
	/**
	 * 业务信息分页加载
	 */
	@ResponseBody
	@RequestMapping("/opertaion")
	public List<Object> operation(Page<newProAnnc> page,newProAnnc npa){
		log.info("业务信息设置.........................");
		List<newProAnnc> annc = is.allAnnc(page, npa);	// 所有业务信息
		List<Object> list = new ArrayList<Object>();
		list.add(0, is.findCount(npa));	// 保存记录数
		list.add(1, annc);
		return list;
	
	}
	
	/**
	 * 去添加业务信息
	 */
	@RequestMapping("/toAddOpera")
	public String toAddOpera(Model model){
		CkProjectEntity cpe = new CkProjectEntity();
		cpe.setIsEnable(IdEntity.S_ENABLE);	// 添加搜索条件 ‘启用’
		List<CkProjectEntity> ckPro = iss.allCkProject(cpe);	// 所有查验项
		model.addAttribute("ckPro", ckPro);
		return "com/vkl/ckts_pc/rgsy/ywxxadd";
	}
	/**
	 * 添加业务信息
	 */
	@ResponseBody
	@RequestMapping("/doAddOpera")
	public String doAddOpera(newProAnnc npa,HttpServletRequest request){
		try {
			boolean flag = is.insertOper(npa, request);	// 添加业务信息
			if(flag) return "success";	// 添加成功，返回success
			return "false";				// 已存在，添加失败，返回false
		} catch (Exception e) {
			e.printStackTrace();
			log.info("业务信息设置，异常"+e.getMessage());
			return "error";
		}
	}
	/**
	 * 删除
	 */
	@RequestMapping("/deleteOpera")
	public String deleteOpera(newProAnnc npa,HttpServletRequest request){
		try {
			is.delData(npa,request);	// 删除
			request.getSession().setAttribute("flag11", "dele");	// 页面提示删除成功
		} catch (Exception e) {
			log.info("异常信息设置，异常"+e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/system/operaSetting/operaIndex";
	} 
	/**
	 * 去修改
	 */
	@RequestMapping("/toUpdateOpera")
	public String toUpdateOpera(newProAnnc npa,Model model){
		newProAnnc proa =is.oneOper(npa);					// 得到修改项
		CkProjectEntity cpe = new CkProjectEntity();
		cpe.setIsEnable(IdEntity.S_ENABLE);	// 添加搜索条件 ‘启用’
		List<CkProjectEntity> ckPro = iss.allCkProject( cpe);	// 查验项
		model.addAttribute("ckPro", ckPro);
		model.addAttribute("pro", proa);
		return "com/vkl/ckts_pc/rgsy/updywxx";
	}
	/**
	 * 修改业务信息
	 */
	@ResponseBody
	@RequestMapping("/doUpdateOpera")
	public String doUpdateOpera(newProAnnc npa,HttpServletRequest request){
		
		try {
			is.doUpdOpera(npa, request);	// 修改
		} catch (Exception e) {
			e.printStackTrace();
			log.info("业务信息设置，异常"+e.getMessage());
		}
		return "com/vkl/ckts_pc/rgsy/ywxxsz";
	}
}
