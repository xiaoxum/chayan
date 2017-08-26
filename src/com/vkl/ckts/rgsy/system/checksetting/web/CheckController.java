package com.vkl.ckts.rgsy.system.checksetting.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.entity.CehUsnrEntity;
import com.vkl.ckts.common.entity.CkCllxEntity;
import com.vkl.ckts.common.entity.CkProjectEntity;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.entity.OperTypeEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.rgsy.system.checksetting.entity.CheckItem;
import com.vkl.ckts.rgsy.system.checksetting.service.ICheckSettingService;
/**
 * 查验项设置
 * @author xujunhua
 * @date 2017年4月14日
 * @ClassName: CheckController
 */
@Controller
@RequestMapping("/pc/check")
public class CheckController {
	@Autowired
	ICheckSettingService css;
	Logger log = Logger.getLogger(CheckController.class); // 日志对象
	/**
	 * 初始化查验项设置
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/ckCheckSetting1")
	public String ckCheckSetting1(Model model){
		CkProjectEntity cpe = new CkProjectEntity();
		cpe.setIsEnable(IdEntity.S_ENABLE);	// 添加搜索条件 ‘启用’
		List<OperTypeEntity> list=css.allOperType();		// 所有业务类型
		List<CkCllxEntity> ckCllx = css.allCKCllx();		// 所有车辆类型
		List<CkProjectEntity>  ckProject = css.allCkProject(cpe);	// 所有查验项

		model.addAttribute("operType", list);
		model.addAttribute("ckCllx", ckCllx);
		model.addAttribute("ckProject", ckProject);
		return "com/vkl/ckts_pc/rgsy/cyxsz";
	}
	/**
	 * 初始化查验项设置
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/ckCheckSetting")
	public String ckCheckSetting(Model model){
		CkProjectEntity cpe = new CkProjectEntity();
		cpe.setIsEnable(IdEntity.S_ENABLE);	// 添加搜索条件 ‘启用’
		List<OperTypeEntity> list=css.allOperType();		// 所有业务类型
		List<CkCllxEntity> ckCllx = css.allCKCllx();		// 所有车辆类型
		List<CkProjectEntity>  ckProject = css.allCkProject(cpe);	// 所有查验项

		model.addAttribute("operType", list);
		model.addAttribute("ckCllx", ckCllx);
		model.addAttribute("ckProject", ckProject);
		return "com/vkl/ckts_pc/rgsy/cyxsz";
	}
	/**
	 * 分页
	 * @param page
	 * @param c
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/ckCheckPage")
	public List<Object> ckCheckPage(Page<CheckItem> page,CheckItem c){
		c.setDelFlag(IdEntity.DEL_FLAG_NORMAL); // 删除标志正常
		page.setT(c);
		List<CheckItem> operVehPro = null;
		int count = 0;
		try {
			operVehPro = css.findAll(page).getList();	// 所有查验项
			count = css.findCount(c);					// 总计
		} catch (Exception e) {
			log.info("查验项设置，捕获异常"+e.getMessage());
			e.printStackTrace();
		}
		// 得到查验项数目，并循环封装
		for(CheckItem ci : operVehPro){
			ci.setNum(css.oneCheckItems(ci).size()+"");;	// 转换为String类型
		}
		List<Object> list = new ArrayList<Object>();
		list.add(0, count);
		list.add(1, operVehPro);
		return list;
	}
	/**
	 * 去设置查验项
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping("/toCkCheckSetting")
	public String toCkCheckSetting(Model model){
		CkProjectEntity cpe = new CkProjectEntity();
		cpe.setIsEnable(IdEntity.S_ENABLE);	// 添加搜索条件 ‘启用’
		List<CkProjectEntity> ckPro = css.allCkProject(cpe);	// 查验项
		List<OperTypeEntity> list=css.allOperType();	// 业务类型
		List<CkCllxEntity> ckCllx = css.allCKCllx();	// 车辆类型
		List<CehUsnrEntity> user = css.allUsering();	// 使用性质
		model.addAttribute("operType", list);
		model.addAttribute("ckCllx", ckCllx);
		model.addAttribute("user", user);
		model.addAttribute("ckPro", ckPro);
		return "com/vkl/ckts_pc/rgsy/cyxadd";
	}
	/**
	 * 查验项设置
	 */
	@ResponseBody
	@RequestMapping("/doCkCheckSetting")
	public String doCkCheckSetting(CheckItem oper,HttpServletRequest request){
		
		UserEntity user = (UserEntity)request.getSession().getAttribute(Constrant.S_USER_SESSION);
		oper.setCreater(user.getId());
		oper.setUpdater(user.getId());
		String flag = null;
		try {
			flag = css.addCheckItem(oper, request);
		} catch (Exception e) {
			log.info("查验项设置，捕获异常"+e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * 去修改查验项设置
	 */
	@RequestMapping("/toUpdateCheck")
	public String toUpdateCheck(CheckItem check,Model model ){
		CkProjectEntity cpe = new CkProjectEntity();
		cpe.setIsEnable(IdEntity.S_ENABLE);	// 添加搜索条件 ‘启用’
		List<CkProjectEntity> ckPro = css.allCkProject(cpe);	// 查验项
		List<CheckItem> item = css.oneCheckItems(check);		// 修改项
		List<OperTypeEntity> list=css.allOperType();			// 业务类型
		List<CkCllxEntity> ckCllx = css.allCKCllx();			// 车辆类型
		List<CehUsnrEntity> user = css.allUsering();			// 使用性质
		model.addAttribute("item", item);
		model.addAttribute("operType", list);
		model.addAttribute("ckCllx", ckCllx);
		model.addAttribute("user", user);
		model.addAttribute("ckPro", ckPro);
		model.addAttribute("ch", item);
		return "com/vkl/ckts_pc/rgsy/updatecyx";
	}
	/**
	 * 修改查验项设置
	 */
	@ResponseBody
	@RequestMapping("/updateCheck")
	public String updateCheck(CheckItem check,HttpServletRequest request){
		UserEntity user = (UserEntity)request.getSession().getAttribute(Constrant.S_USER_SESSION);
		check.setUpdater(user.getId());	// 添加修改人
		String flag = null ;
		try {
			flag = css.updateCheck(check, request);	// 修改
		} catch (Exception e) {
			log.info("查验项设置，捕获异常"+e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 删除查验项设置
	 */
	@RequestMapping("/deleteCheck")
	public String deleteCheck(CheckItem check,HttpServletRequest request){		
			
			try {
				css.delData(request, check);	// 删除
			} catch (Exception e) {
				log.info("查验项设置，捕获异常"+e.getMessage());
				e.printStackTrace();
			}		
			request.getSession().setAttribute("flag11", "del");	// 页面提示删除成功
			return "redirect:/pc/check/ckCheckSetting";
		
	}
	
}
