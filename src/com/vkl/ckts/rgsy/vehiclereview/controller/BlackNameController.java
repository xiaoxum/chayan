package com.vkl.ckts.rgsy.vehiclereview.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vkl.ckts.common.entity.CkCllxEntity;
import com.vkl.ckts.common.entity.OperTypeEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.utils.Base64Utils;
import com.vkl.ckts.rgsy.system.checksetting.service.ICheckSettingService;
import com.vkl.ckts.rgsy.vehiclereview.entity.VehBlackName;
import com.vkl.ckts.rgsy.vehiclereview.service.IBlackNameService;

/**
 * 车辆黑名单
 * @author xujunhua
 * @date 2017年4月7日
 * @ClassName: BlackNameController
 */
@Controller
@RequestMapping("/veh/blackName")
public class BlackNameController {
	@Autowired
	ICheckSettingService icss;	// 注入查验项service
	@Autowired
	IBlackNameService ibns; 	// 注入车辆黑名单service
	
	/**
	 * 黑名单一览表页面加载
	 * @param model
	 * @return 页面地址
	 */
	@RequestMapping("/blackIndex")
	public String bNameListIndex(Model model){
		List<CkCllxEntity> allCllx = icss.allCKCllx();		// 得多所有车辆类型
		List<OperTypeEntity> allOper = icss.allOperType();	// 所有业务类型
		model.addAttribute("allCllx", allCllx);
		model.addAttribute("allOper", allOper);
		return "com/vkl/ckts_pc/rgsy/blackName";
	}
	/**
	 * 分页
	 * @param page
	 * @param start	查询条件加入黑名单时间
	 * @param end 查询条件加入黑名单时间
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/blackPage")
	public List<Object> blackPage(Page<VehBlackName> page,VehBlackName vbn){
		/*VehBlackName vbn = page.getT();
		vbn.setStart(start);	// 封装查询条件加入黑名单时间start
		vbn.setEnd(end);		// 加入黑名单时间end
*/		page.setT(vbn);			// 重新封装到page对象
		List<VehBlackName> allBlack = ibns.findAll(page).getList();	// 所有车辆黑名单
		int count = ibns.findCount(vbn);	// 所有黑名单记录数
		List<Object> list = new ArrayList<Object>();
		list.add(0, count);
		list.add(1, allBlack);
		return list;
	}
	/**
	 * 查看黑明详情
	 * @param id	黑名单编号
	 * @param model
	 * @return
	 */
	@RequestMapping("/bNameDetail")
	public String bNameDetail(VehBlackName bn,Model model){
		VehBlackName vbn = ibns.bNameDetail(bn); 	// 黑名单详情
		List<VehBlackName> record = ibns.checkRecord(vbn.getClsbdh());
		// 查验员名称解码
		for(VehBlackName vn : record){
			String[] viewer = vn.getViewer().split(",");
			StringBuffer sb = new StringBuffer();
			for(int i=0; i < viewer.length; i++){
				String name = Base64Utils.decode(viewer[i]);	// 解码
				// 如果是双人查验
				if(i>0){
					sb.append(","+name);
				}else{
					sb.append(name);
				}
			}
			vn.setViewer(sb.toString());	// 重新封装解码后的查验员
		}
		model.addAttribute("record", record);
		model.addAttribute("detail", vbn);
		return "com/vkl/ckts_pc/rgsy/bmdetail";
	}
	@RequestMapping("/changeWhite")
	public String cWhite(String clsbdh,HttpServletRequest request){
		 try {
			ibns.changeWhite(clsbdh, request);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		 request.getSession().setAttribute("flag11", "upd");
		 return "redirect:/veh/blackName/blackIndex";
	}
}
