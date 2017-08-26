package com.vkl.ckts.rgsy.spotcheck.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.controller.BaseController;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IDictEntityService;
import com.vkl.ckts.common.service.IOperTypeEntityService;
import com.vkl.ckts.common.utils.Base64Utils;
import com.vkl.ckts.common.utils.PropertiesUtils;
import com.vkl.ckts.common.utils.StringUtils;
import com.vkl.ckts.rgsy.spotcheck.entity.CkInfoDto;
import com.vkl.ckts.rgsy.spotcheck.service.ICkInfoService;
import com.vkl.ckts.rgsy.vehiclereview.controller.VehicleReviewCotroller;
import com.vkl.ckts.rgsy.vehiclereview.entity.CkInfoEntityDto;
import com.vkl.ckts.rgsy.vehiclereview.entity.PicRecordDto;
import com.vkl.ckts.rgsy.vehiclereview.entity.ProjectRecordDto;
import com.vkl.ckts.rgsy.vehiclereview.entity.VedioRecordDto;
import com.vkl.ckts.rgsy.vehiclereview.service.IPicRecordService;
import com.vkl.ckts.rgsy.vehiclereview.service.IProjectRecordService;
import com.vkl.ckts.rgsy.vehiclereview.service.IVedioRecordService;
import com.vkl.ckts.rgsy.vehiclereview.service.IVehicleReviewService;


/**
 * 查验抽查请求
 * @author jiajun
 * @version 1.0
 */
@Controller
@RequestMapping("module/spotcheck")
public class SpotCheckController extends BaseController{
	/**
	 * 字典表
	 */
	@Autowired IDictEntityService DictEntityService;
	/**
	 * 业务类型表
	 */
	@Autowired IOperTypeEntityService OperTypeEntityService;
	/**
	 * 查验拍照项目
	 */
	@Autowired
	IProjectRecordService projectRecordService;
	/**
	 * 业务类型表
	 */
	@Autowired ICkInfoService iCkInfoService;
	/**
	 * 查验审核
	 */
	@Autowired
	IVehicleReviewService vehicleReviewService;
	/**
	 * 查验拍照记录
	 */
	@Autowired
	IPicRecordService picRecordService;
	/**
	 * 查验视频记录
	 */
	@Autowired
	IVedioRecordService vedioRecordService;
	
	/**
	 * 日志处理
	 */
	private static Logger logger = LoggerFactory.getLogger(VehicleReviewCotroller.class);
	/**
	 * 加载查验抽查页面
	 * @return
	 */
	@RequestMapping("/date")
	public String date(Model model){
		logger.info("---------加载查验抽查页------------");
		model.addAttribute("operType", OperTypeEntityService.selectAll()); // 获取业务类型下拉框
		return "com/vkl/ckts_pc/rgsy/cycc";
	} 
	
	/**
	 * 加载查验抽查列表
	 * @return
	 */
	@RequestMapping("/datelist")
	@ResponseBody
 
	public Page<CkInfoDto> datelist(CkInfoDto ckInfoDto,Page<CkInfoDto>  page){
		logger.info("---------加载查验抽查列表页------------");
		page.setT(ckInfoDto);
		List<CkInfoDto> list = iCkInfoService.spotCheck(page); // 获取查验list集合
		List<CkInfoDto> list2 = new ArrayList<CkInfoDto>();    // 创建新list集合
		List<CkInfoDto> list3 = new ArrayList<CkInfoDto>();    // 创建新list集合
		for (CkInfoDto ckInfoDto2 : list) {
			ckInfoDto2.setHpzl(DictEntityService.findInfoByKeyAndType(ckInfoDto2.getHpzl(), "hpzl").getLdictionaryAbel());
			//ckInfoDto2.setpAuditerName(Base64Utils.decode(ckInfoDto2.getpAuditerName())); // 审核人解码
			ckInfoDto2.setOperaerName(Base64Utils.decode(ckInfoDto2.getOperaerName()));   // 业务受理人解码
			list2.add(ckInfoDto2);  // 解码后的对象存入新list集合
		}
		
		page.setTotalCount(list2.size()); // 得到总条数
		for(int i=page.getPageSize()*(page.getPageNo()-1);i<list2.size();i++){
			
			list3.add(list2.get(i));
			
			
		}
		page.setList(list3);
		return page; // 返回新list集合
	} 
    
	
	/**
	 * 加载查验抽查详情页面
	 * @return
	 */
	@RequestMapping("/details")
	public String details(String srln,String rckCount, Model model,HttpServletRequest resuest) throws IOException{
		   logger.info("---------加载查验抽查详情页------------");
		// 根据流水号查询信息
				CkInfoEntityDto ckInfoEntityDto = vehicleReviewService.findVehicleInfo(srln,rckCount, Constrant.HPZL_TYPE);
				// 获取查验员姓名id
				String[] ckers = StringUtils.stringToArray(ckInfoEntityDto.getCker(), StringUtils.SPLIT_COMMA);
				// 获取业务类型id
				String[] operTypes = StringUtils.stringToArray(ckInfoEntityDto.getOperType(), StringUtils.SPLIT_COMMA);
				// 通过id得到名称
				List<String> operTypeInfo = vehicleReviewService.findAllOperType(operTypes);
				// 通过id得到名称
				List<String> ckerInfo = vehicleReviewService.findAllCker(ckers);
				// 定义一个变量接收名称内容
				String name = "";
				// 循环取出业务类型的名称
				for (int i = 0; i <= operTypeInfo.size() - 1; i++) {
					name += operTypeInfo.get(i) + ",";
					if (i == operTypeInfo.size() - 1) {
						name += operTypeInfo.get(i);
						ckInfoEntityDto.setTypeName(name);
					}
				}
				// 循环取出查验员的名称
				for (int i = 0; i <= ckerInfo.size() - 1; i++) {
					String key = new String(Base64Utils.decode(ckerInfo.get(i)).getBytes(), "utf-8");
					name = "";
					name += key + ",";
					if (i == ckerInfo.size() - 1) {
						name += key;
						ckInfoEntityDto.setCker(name);
					}
				}
				// 获取当前查验项
				List<ProjectRecordDto> list = projectRecordService.findById(srln,rckCount);
				Map<String,String> map = new HashMap<String,String>();
				// 循环取出查验项存入map集合
				for(ProjectRecordDto info : list){
					map.put(info.getProId(), info.getCkFlag());
				}
				// 将查验项记录存入session
				resuest.getSession().setAttribute("map", map);
				// 获取查验照片记录
				List<PicRecordDto> picProjectDto = picRecordService.findBySrln(srln,rckCount);
				// 获取文件服务器ip和端口并拼接
				String url = PropertiesUtils.getValues("ip")+":"+PropertiesUtils.getValues("port");
				for(int i=0;i<picProjectDto.size();i++){
					picProjectDto.get(i).setPicUrl("http://"+url+"/"+picProjectDto.get(i).getPicUrl());
				}
				// 获取查验视频记录
				List<VedioRecordDto> vedioRecordDto = null;
				try {
					//vedioRecordDto = vedioRecordService.findBySrln(srln,ckInfoEntityDto.getRckCount());
				
				for(int i=0;i<vedioRecordDto.size();i++){
					vedioRecordDto.get(i).setVedioUrl("http://"+url+"/"+vedioRecordDto.get(i).getVedioUrl());
				}
				
				} catch (Exception e) {
					// TODO: handle exception
				}
				model.addAttribute("vedio", vedioRecordDto);
				model.addAttribute("pic", picProjectDto);
				if(null == ckInfoEntityDto.getpAuditFlag()&& "" == ckInfoEntityDto.getpAuditFlag()){
					ckInfoEntityDto.setpAuditFlag(ckInfoEntityDto.getAuditFlag());
				}
				if("0".equals(ckInfoEntityDto.getpAuditFlag())){
					ckInfoEntityDto.setpAuditFlag("通过");
				}else{
					ckInfoEntityDto.setpAuditFlag("不通过");
				}
				model.addAttribute("obj", ckInfoEntityDto);
		return "com/vkl/ckts_pc/rgsy/cyccxq";
	} 
	
	
	/**
	 * 加载打印详情页面
	 * @return
	 */
	@RequestMapping("/print")
	public String print(String srln,String rckCount, Model model,HttpServletRequest resuest) throws IOException{
		   logger.info("---------加载打印详情页------------");
			// 根据流水号查询信息
					CkInfoEntityDto ckInfoEntityDto = vehicleReviewService.findVehicleInfo(srln,rckCount, Constrant.HPZL_TYPE);
					// 获取查验员姓名id
					String[] ckers = StringUtils.stringToArray(ckInfoEntityDto.getCker(), StringUtils.SPLIT_COMMA);
					// 获取业务类型id
					String[] operTypes = StringUtils.stringToArray(ckInfoEntityDto.getOperType(), StringUtils.SPLIT_COMMA);
					// 通过id得到名称
					List<String> operTypeInfo = vehicleReviewService.findAllOperType(operTypes);
					// 通过id得到名称
					List<String> ckerInfo = vehicleReviewService.findAllCker(ckers);
					// 定义一个变量接收名称内容
					String name = "";
					// 循环取出业务类型的名称
					for (int i = 0; i <= operTypeInfo.size() - 1; i++) {
						name += operTypeInfo.get(i) + ",";
						if (i == operTypeInfo.size() - 1) {
							name += operTypeInfo.get(i);
							ckInfoEntityDto.setTypeName(name);
						}
					}
					// 循环取出查验员的名称
					for (int i = 0; i <= ckerInfo.size() - 1; i++) {
						String key = new String(Base64Utils.decode(ckerInfo.get(i)).getBytes(), "utf-8");
						name = "";
						name += key + ",";
						if (i == ckerInfo.size() - 1) {
							name += key;
							ckInfoEntityDto.setCker(name);
						}
					}
					// 获取当前查验项
					List<ProjectRecordDto> list = projectRecordService.findById(srln,rckCount);
					Map<String,String> map = new HashMap<String,String>();
					// 循环取出查验项存入map集合
					for(ProjectRecordDto info : list){
						map.put(info.getProId(), info.getCkFlag());
					}
					// 将查验项记录存入session
					resuest.getSession().setAttribute("map", map);
					// 获取查验照片记录
					List<PicRecordDto> picProjectDto = picRecordService.findBySrln(srln,rckCount);
					// 获取文件服务器ip和端口并拼接
					String url = PropertiesUtils.getValues("ip")+":"+PropertiesUtils.getValues("port");
					for(int i=0;i<picProjectDto.size();i++){
						picProjectDto.get(i).setPicUrl("http://"+url+"/"+picProjectDto.get(i).getPicUrl());
					}
					// 获取查验视频记录
					List<VedioRecordDto> vedioRecordDto = null;
					try {
						//vedioRecordDto = vedioRecordService.findBySrln(srln,ckInfoEntityDto.getRckCount());
					
					for(int i=0;i<vedioRecordDto.size();i++){
						vedioRecordDto.get(i).setVedioUrl("http://"+url+"/"+vedioRecordDto.get(i).getVedioUrl());
					}
					
					} catch (Exception e) {
						// TODO: handle exception
					}
					model.addAttribute("vedio", vedioRecordDto);
					model.addAttribute("pic", picProjectDto);
					if(null == ckInfoEntityDto.getpAuditFlag()&& "" == ckInfoEntityDto.getpAuditFlag()){
						ckInfoEntityDto.setpAuditFlag(ckInfoEntityDto.getAuditFlag());
					}
					if("0".equals(ckInfoEntityDto.getpAuditFlag())){
						ckInfoEntityDto.setpAuditFlag("通过");
					}else{
						ckInfoEntityDto.setpAuditFlag("不通过");
					}
					model.addAttribute("obj", ckInfoEntityDto);
		return "com/vkl/ckts_pc/pub/ckdate";
	} 
	
	
}
