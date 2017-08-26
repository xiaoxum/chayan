package com.vkl.ckts.rgsy.system.photosetting.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vkl.ckts.common.entity.CehUsnrEntity;
import com.vkl.ckts.common.entity.CkCllxEntity;
import com.vkl.ckts.common.entity.OperTypeEntity;
import com.vkl.ckts.common.entity.PicProjectEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.rgsy.system.checksetting.service.ICheckSettingService;
import com.vkl.ckts.rgsy.system.photosetting.entity.OperPicProject;
import com.vkl.ckts.rgsy.system.photosetting.service.impl.PhotoSetServiceImpl;
/**
 * 拍照项设置
 * @author xujunhua
 * @date 2017年4月14日
 * @ClassName: PhotoSetController
 */
@Controller
@RequestMapping("/system/photo")
public class PhotoSetController {
	@Autowired
	PhotoSetServiceImpl pss;
	@Autowired
	ICheckSettingService css;	// 查验项设置service
	Logger log = Logger.getLogger(PhotoSetController.class);	// 日志对象 
	/**
	 * 拍照项设置页面加载
	 */
	@RequestMapping("/photoIndex")
	public String photoIndex(Page<OperPicProject> page,Model model,OperPicProject opp){
		log.info("拍照项设置....................");
		page.setT(opp);
		List<OperTypeEntity> operType = css.allOperType();	// 获得所有业务类型
		List<CkCllxEntity> cllx = css.allCKCllx();	// 获得所有车辆类型
		List<CehUsnrEntity> ceh = css.allUsering(); 	// 获得所有使用性质
		model.addAttribute("ceh", ceh);
		model.addAttribute("operType", operType);
		model.addAttribute("cllx", cllx);
		return "com/vkl/ckts_pc/rgsy/pzxsz";
	}
	/**
	 * 拍照项设置分页
	 */
	@ResponseBody
	@RequestMapping("/photoPage")
	public List<Object> photoPage(Page<OperPicProject> page,OperPicProject opp){
		log.info("拍照项设置....................");
		page.setT(opp);
		List<OperPicProject> pic = pss.findAll(page).getList();	// 获得所有拍照项
		// 查找拍照项数目，并封装
		for(OperPicProject p : pic){
			p.setIsMust(pss.oneOperPic(p).getPpe().size()+"");
		}
		List<Object>  list = new ArrayList<Object> ();
		list.add(0, pss.findCount(opp));	// 将总记录数添加到返回页面的集合
		list.add(1, pic);
		return list;
	}
	/**
	 * 添加设置页面加载
	 */
	@RequestMapping("/addPhotoSet")
	public String addPhotoSet1(Model model,HttpServletRequest request){
		log.info("拍照项设置....................");
		HttpSession session = request.getSession();
		session.removeAttribute("flag11");	// 删除拍照项设置主页提示信息
		List<OperTypeEntity> operType = css.allOperType();	// 所有业务类型
		List<CkCllxEntity> cllx = css.allCKCllx();	// 所有车辆类型
		List<PicProjectEntity> pic = pss.allPicPro();	// 所有拍照项
		List<CehUsnrEntity> ceh = css.allUsering(); 	// 所有车辆使用性质
		model.addAttribute("operType", operType);
		model.addAttribute("cllx", cllx);
		model.addAttribute("pic", pic);
		model.addAttribute("ceh", ceh);
		return "com/vkl/ckts_pc/rgsy/pzxadd";
	}
	
	/**
	 * 添加设置页面加载
	 */
	@RequestMapping("/addPhotoSet1")
	public String addPhotoSet(Model model,HttpServletRequest request){
		log.info("拍照项设置....................");
		HttpSession session = request.getSession();
		session.removeAttribute("flag11");	// 删除拍照项设置主页提示信息
		List<OperTypeEntity> operType = css.allOperType();	// 所有业务类型
		List<CkCllxEntity> cllx = css.allCKCllx();	// 所有车辆类型
		List<PicProjectEntity> pic = pss.allPicPro();	// 所有拍照项
		List<CehUsnrEntity> ceh = css.allUsering(); 	// 所有车辆使用性质
		model.addAttribute("operType", operType);
		model.addAttribute("cllx", cllx);
		model.addAttribute("pic", pic);
		model.addAttribute("ceh", ceh);
		return "com/vkl/ckts_pc/rgsy/pzxadd";
	}
	/**
	 * 添加设置
	 */
	@RequestMapping("/insertPhotoSet")
	@ResponseBody
	public String insertPhotoSet(OperPicProject opp,HttpServletRequest request){
		try {
			pss.addPicSet1(opp, request);
			request.getSession().setAttribute("flag11", "insert");
		} catch (Exception e) {
			log.info("拍照项设置，捕获异常"+e.getMessage());
			e.printStackTrace();
			request.getSession().setAttribute("flag11", "insertF");
			return "insertF";
		}
		
		return "success";
	}
	
	
	
	
	/**
	 * 添加设置
	 */
	@RequestMapping("/insertPhotoSet1")
	public String insertPhotoSet1(OperPicProject opp,HttpServletRequest request){
		try {
			pss.addPicSet(opp, request);
			request.getSession().setAttribute("flag11", "insert");
		} catch (Exception e) {
			log.info("拍照项设置，捕获异常"+e.getMessage());
			e.printStackTrace();
			request.getSession().setAttribute("flag11", "insertF");
		}
		
		return "redirect:/system/photo/photoIndex";
	}
	/**
	 * 删除
	 */
	@RequestMapping("/delPhotoSet")
	public String delPhotoSet(OperPicProject opp,HttpServletRequest request){
		try {
			pss.delPicSet(opp, request);	
			request.getSession().setAttribute("flag11", "del");	// 将提示信息保存到session
		} catch (Exception e) {
			e.printStackTrace();
			log.info("拍照项设置，捕获异常"+e.getMessage());
		}
		return "redirect:/system/photo/photoIndex";
	}
	/**
	 * 删除
	 */
	@RequestMapping("/delPhotoSet1")
	public String delPhotoSet1(OperPicProject opp,HttpServletRequest request){
		try {
			pss.delPicSet1(opp, request);	
			request.getSession().setAttribute("flag11", "del");	// 将提示信息保存到session
		} catch (Exception e) {
			e.printStackTrace();
			log.info("拍照项设置，捕获异常"+e.getMessage());
		}
		return "redirect:/system/photo/photoIndex";
	}
	/**
	 * 修改设置
	 */
	@RequestMapping("/toUpdPhotoSet")
	public String updPhotoSet(OperPicProject opp,Model model){
		log.info("拍照项设置....................");
		OperPicProject pic = pss.oneOperPic(opp);	// 得到需修改的拍照项
		List<OperTypeEntity> operType = css.allOperType();	// 所有业务类型
		List<CkCllxEntity> cllx = css.allCKCllx();	// 所有车辆类型
		List<PicProjectEntity> p = pss.allPicPro();	// 所有拍照项
		List<CehUsnrEntity> ceh = css.allUsering(); 	// 所有使用性质
		model.addAttribute("operType", operType);
		model.addAttribute("cllx", cllx);
		model.addAttribute("p", p);
		model.addAttribute("ceh", ceh);
		model.addAttribute("pic", pic);
		
		return "com/vkl/ckts_pc/rgsy/updpzxsz";
	}
	
	
	/**
	 * 修改设置
	 */
	@RequestMapping("/toUpdPhotoSet1")
	public String updPhotoSet1(OperPicProject opp,Model model){
		log.info("拍照项设置....................");
		OperPicProject pic = pss.oneOperPic1(opp);	// 得到需修改的拍照项
		List<OperTypeEntity> operType = css.allOperType();	// 所有业务类型
		List<CkCllxEntity> cllx = css.allCKCllx();	// 所有车辆类型
		List<PicProjectEntity> p = pss.allPicPro();	// 所有拍照项
		List<CehUsnrEntity> ceh = css.allUsering(); 	// 所有使用性质
		model.addAttribute("operType", operType);
		model.addAttribute("cllx", cllx);
		model.addAttribute("p", p);
		model.addAttribute("ceh", ceh);
		model.addAttribute("pic", pic);
		return "com/vkl/ckts_pc/rgsy/updpzxsz";
	}
	/**
	 * 修改
	 */
	@RequestMapping("/doUpdateSet")
	public String doUpdateSet(OperPicProject opp,HttpServletRequest request){
		try {
			pss.updPicSet(opp, request);
			request.getSession().setAttribute("flag11", "udp");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("拍照项设置，捕获异常"+e.getMessage());
		}
		return "redirect:/system/photo/photoIndex";
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/doUpdateSet1")
	public String doUpdateSet1(OperPicProject opp,HttpServletRequest request){
		try {
			pss.updPicSet1(opp, request);
			request.getSession().setAttribute("flag11", "udp");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("拍照项设置，捕获异常"+e.getMessage());
		}
		return "redirect:/system/photo/photoIndex";
	}
}
