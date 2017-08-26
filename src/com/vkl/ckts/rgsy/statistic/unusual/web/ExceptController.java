package com.vkl.ckts.rgsy.statistic.unusual.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vkl.ckts.common.entity.CkCllxEntity;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.entity.OperTypeEntity;
import com.vkl.ckts.common.entity.VedioRecordEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.utils.Base64Utils;
import com.vkl.ckts.common.utils.DateUtil;
import com.vkl.ckts.common.utils.PropertiesUtils;
import com.vkl.ckts.rgsy.statistic.unusual.entity.NewExceptInfo;
import com.vkl.ckts.rgsy.statistic.unusual.entity.PicRecord;
import com.vkl.ckts.rgsy.statistic.unusual.service.IExceptService;
import com.vkl.ckts.rgsy.system.checksetting.service.ICheckSettingService;
/**
 * 异常处理
 * @author xujunhua
 * @date 2017年4月14日
 * @ClassName: ExceptController
 */
@Controller
@RequestMapping("/statistic/except")
public class ExceptController {
	@Autowired
	ICheckSettingService css;
	@Autowired
	IExceptService ies;
	Logger log = Logger.getLogger(ExceptController.class);	// 日志对象
	
	/**
	 * 异常信息主页加载
	 */
	@RequestMapping("/exceptIndex")
	public String exceptIndex(Model model){
		// 得到所有车辆类型
		List<CkCllxEntity> cllx = css.allCKCllx();
		List<OperTypeEntity> type = css.allOperType();
		model.addAttribute("type", type);
		model.addAttribute("cllx", cllx);
		return "com/vkl/ckts_pc/rgsy/yccl";
	}
	/**
	 * 异常信息分页
	 */
	@ResponseBody
	@RequestMapping("/exceptPage")
	public List<Object> exceptPage(Page<NewExceptInfo> page,String end,String start, String orginName, String viewName,String vehType, String type) {
		NewExceptInfo exeInfo = new NewExceptInfo();	// 定义实体类，封装查询调件
		// 判断查询条件开始时间
		if(!"".equals(start) ){
			exeInfo.setStart(DateUtil.parseDate(start));	// 不为空，转换为Date类型
		}
		// 判断查询条件结束时间
		if(!"".equals(end)){
			exeInfo.setEnd(DateUtil.parseDate(end));	// 不为空，转换为Date类型
		}
		exeInfo.setOrginName(orginName);	// 查验区
		exeInfo.setViewName(Base64Utils.encode(viewName));		// 查验员加密
		exeInfo.setVehType(vehType);		// 车辆类型
		exeInfo.setOperType(type);			// 业务类型
		List<NewExceptInfo> exeList = null;
		int count = 0;
		try {
			count = ies.findCount(exeInfo);
			exeList = ies.allExcept(page, exeInfo);
		} catch (Exception e) {
			log.info("异常处理，捕获异常"+e.getMessage());
			e.printStackTrace();
		}
		List<Object> list = new ArrayList<Object>();
		list.add(0, count);	// 总记录数
		list.add(1,exeList );	// 所有异常信息
		
		return list;
	}
	
	/**
	 * 查看异常信息详情
	 * @throws IOException  
	 */
	@RequestMapping("/exceptDetail")
	public String exceptDetail(int excId,Model model,HttpServletResponse response) throws IOException{
		
		NewExceptInfo oneExcept = null;
		List<PicRecord> allPic = null;
		List<VedioRecordEntity> allVedio = null;
		try {
			oneExcept = ies.detail(excId);
			// 修改时间格式为 yyyy-MM-dd hh:mm:ss
			if(oneExcept.getCreateDate() != null){
				oneExcept.setDate(DateUtil.format(oneExcept.getCreateDate(), DateUtil.DATE_TIME_FORMAT));
			}
			allVedio = ies.allVedio(oneExcept.getSrln()); // 所有视频
			String url= "http://"+PropertiesUtils.getValues("ip")+":"+PropertiesUtils.getValues("port");	// 文件服务器地址，端口号
			// 循环获得视频详细路径
			for(VedioRecordEntity vd : allVedio){
				String vedioUrl =url+"/"+ vd.getVedioUrl();	// 得到视频图片路径	
				vd.setVedioUrl(vedioUrl);
				if("1".equals(vd.getVedioType())){
					vd.setVedioType("pda 视频");
				}else if("2".equals(vd.getVedioType())){
					vd.setVedioType("外廓尺寸视频 ");
				} else if("3".equals(vd.getVedioType())){
					vd.setVedioType("整备质量视频");
				}
			}
			allPic = ies.allPic(oneExcept.getSrln());	// 所有图片
			// 循环获得图片详细路径
			for(PicRecord pic:allPic){
				pic.setPicUrl(url+"/"+pic.getPicUrl());
			}
		} catch (Exception e) {
			log.info("异常处理，图像捕获异常"+e.getMessage());
			e.printStackTrace();
		}
		model.addAttribute("exc", oneExcept);
		model.addAttribute("allPic", allPic);
		model.addAttribute("allVedio", allVedio);
		return "com/vkl/ckts_pc/rgsy/cyycxx";
	}
	

	 
	/**
	 * 修改查验员状态
	 * @param nei 异常信息实体类，用于接收页面数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/dealExcept")
	public String dealExcept(NewExceptInfo nei,HttpServletRequest request){
		try {
			ies.updViewerStatu(nei,request);
			request.getSession().setAttribute("del", "dealExe");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "com/vkl/ckts_pc/rgsy/yccl";
	}
}
