package com.vkl.ckts.rgsy.vehiclereview.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.ICehUsnrEntityService;
import com.vkl.ckts.common.service.IDictEntityService;
import com.vkl.ckts.common.service.IOperTypeEntityService;
import com.vkl.ckts.common.service.IOperVehProEntityService;
import com.vkl.ckts.common.utils.Base64Utils;
import com.vkl.ckts.common.utils.PropertiesUtils;
import com.vkl.ckts.common.utils.StringUtils;
import com.vkl.ckts.rgsy.vehiclereview.entity.CkInfoEntityDto;
import com.vkl.ckts.rgsy.vehiclereview.entity.PicRecordDto;
import com.vkl.ckts.rgsy.vehiclereview.entity.ProjectRecordDto;
import com.vkl.ckts.rgsy.vehiclereview.entity.ResourceDto;
import com.vkl.ckts.rgsy.vehiclereview.entity.ShtjDto;
import com.vkl.ckts.rgsy.vehiclereview.entity.VedioRecordDto;
import com.vkl.ckts.rgsy.vehiclereview.service.IPicRecordService;
import com.vkl.ckts.rgsy.vehiclereview.service.IProjectRecordService;
import com.vkl.ckts.rgsy.vehiclereview.service.IResouService;
import com.vkl.ckts.rgsy.vehiclereview.service.IVedioRecordService;
import com.vkl.ckts.rgsy.vehiclereview.service.IVehicleReviewService;

/**
 * 查验审核Controller
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("module/vehicle/")
public class VehicleReviewCotroller {
	/**
	 * 业务类型表
	 */
	@Autowired
	IOperTypeEntityService operTypeEntityService;
	/**
	 * 查验审核
	 */
	@Autowired
	IVehicleReviewService vehicleReviewService;
	/**
	 * 查验项
	 */
	@Autowired
	ICehUsnrEntityService cehUsnrEntityService;
	/**
	 * 查验拍照项目
	 */
	@Autowired
	IProjectRecordService projectRecordService;
	/**
	 * 业务车型查验
	 */
	@Autowired
	IOperVehProEntityService operVehProEntityService;
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
	@Autowired
	IDictEntityService DictEntityService;
	
	@Autowired
	ICehUsnrEntityService CehUsnrEntityService;
	@Autowired
	IResouService resouService;
	/**
	 * 日志处理
	 */
	private static Logger logger = LoggerFactory.getLogger(VehicleReviewCotroller.class);
	
	/**
	 * 审核修改数据状态
	 * @param auditFlag		审核标识（0：通过；1：不通过）
	 * @param srln			流水号
	 * @param rckCount		复检次数
	 * @return
	 */
	@ResponseBody
	@RequestMapping("examine")
	public String examine(String auditFlag,String srln,String rckCount,String remarks,HttpServletRequest request){
		try {
			UserEntity user = (UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION);
			
			logger.info("审核数据：- >   auditFlag:"+auditFlag+"srln:"+srln+"rckCount:"+rckCount);
			int num = vehicleReviewService.updaExamine(auditFlag, srln, rckCount,remarks,user.getId(),new Date());
			if (num>0){
				return "true";
			}else{
				return "false";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("审核失败捕获到异常:"+e.getMessage());
			return "exe";
		}
		
	}

	/**
	 * 加载审核列表
	 * 
	 * @return
	 */
	@RequestMapping("loadview")
	public String loadView(Model model, Page<CkInfoEntityDto> page, CkInfoEntityDto ckInfoEntityDto) {
		try {
			// 加载下拉框数据
			model.addAttribute("operType", operTypeEntityService.selectAll());
			model.addAttribute("hpzl", DictEntityService.findInfoByType("hpzl"));
			model.addAttribute("chkpts", vehicleReviewService.findChkpts());
			logger.info("加载页面数据....................");
			return "com/vkl/ckts_pc/rgsy/auditing";
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("加载页面数据异常...................异常内容："+e.getMessage());
			return null;
		}
	
	}

	/**
	 * 加载数据
	 * 
	 * @param page
	 *            分页对象
	 * @param ckInfoEntityDto
	 *            接受页面参数
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("loaddata")
	public Page<CkInfoEntityDto> loadData(Page<CkInfoEntityDto> page, CkInfoEntityDto ckInfoEntityDto,
			HttpServletRequest request) throws IOException {
		try {
			// 从request中获取session
			HttpSession session = request.getSession();
			UserEntity user = (UserEntity) session.getAttribute(Constrant.S_USER_SESSION);
			// 设置当前审核员id
			ckInfoEntityDto.setpAuditer(Integer.parseInt(user.getId()));
			// 设置是否人工审核
			ckInfoEntityDto.setIsPAudit(IdEntity.S_YES);
			page.setT(ckInfoEntityDto);
			logger.info("用户："+user.getId()+"查询页面数据");
			return vehicleReviewService.findByPage(page);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			logger.info("查询页面数据异常.......................异常内容："+e.getMessage());
			return null;
		}
	}

	/**
	 * 加载查验审核详情
	 * 
	 * @param srln
	 *            流水号
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("loadcheckaudit")
	public String loadCheckAudit(String srln,String rckCount, Model model,HttpServletRequest resuest) throws IOException {
		try {
			String[] ckers = null;
			List<String> ckerInfo = null;
			logger.info("加载查验审核信息........................");
			// 根据流水号查询信息
			CkInfoEntityDto ckInfoEntityDto = vehicleReviewService.findVehicleInfo(srln,Constrant.HPZL_TYPE,rckCount);
			// 获取查验员姓名id
			if(ckInfoEntityDto.getCker()!=null){
			 ckers = StringUtils.stringToArray(ckInfoEntityDto.getCker(), StringUtils.SPLIT_COMMA);
			}
			// 获取业务类型id
			String[] operTypes = StringUtils.stringToArray(ckInfoEntityDto.getOperType(), StringUtils.SPLIT_COMMA);
			// 通过id得到名称
			List<String> operTypeInfo = vehicleReviewService.findAllOperType(operTypes);
			// 通过id得到名称
			if(ckers!=null){
			 ckerInfo = vehicleReviewService.findAllCker(ckers);
			}
			// 定义一个变量接收名称内容
			String name = "";
			// 循环取出业务类型的名称
			for (int i = 0; i <= operTypeInfo.size() - 1; i++) {
				if (i == operTypeInfo.size() - 1) {
					name += operTypeInfo.get(i);
					ckInfoEntityDto.setTypeName(name);
					break;
				}
				name += operTypeInfo.get(i) + ",";
			}
			name = "";
			// 循环取出查验员的名称
			if(ckerInfo!=null){
			for (int i = 0; i <= ckerInfo.size() - 1; i++) {
				String key = Base64Utils.decode(ckerInfo.get(i));
				if (i == ckerInfo.size() - 1) {
					name += key;
					ckInfoEntityDto.setCker(name);
					break;
				}
				name += key + ",";
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
			String url = PropertiesUtils.getValues("fileTomcatUrl");
			for(int i=0;i<picProjectDto.size();i++){
				picProjectDto.get(i).setPicUrl(url+"/"+picProjectDto.get(i).getPicUrlPa());
			}
			// 获取查验视频记录
				List<VedioRecordDto> vedioRecordDto = vedioRecordService
						.findBySrln(srln,rckCount);
				for (int i = 0; i < vedioRecordDto.size(); i++) {
					vedioRecordDto.get(i).setVedioUrl("http://"+ckInfoEntityDto.getVideoId()+":"+ckInfoEntityDto.getVideoPort()+"//"
							+ vedioRecordDto.get(i).getVedioUrl());
				}
			List<ResourceDto> resourceDtos=resouService.findResPic(srln, rckCount);	
			if (resourceDtos!=null&&resourceDtos.size()>0) {
				for (ResourceDto resourceDto : resourceDtos) {
					String localIp=PropertiesUtils.getValues("localIp");
					String localPort=PropertiesUtils.getValues("localPort");
					String fileUrl=PropertiesUtils.getValues("fileTomcatUrl");
					String picUrl=fileUrl+"/"+resourceDto.getRepicUrl();
					resourceDto.setRepicUrl(picUrl);
				}
			}
			model.addAttribute("resPics", resourceDtos);
			//获取查验员签名
		    String servPicAdr=PropertiesUtils.getValues("fileTomcatUrl");
		    if (ckers!=null) {
		    	Map<String, Object> map2=vehicleReviewService.findCkerQmView(ckers[0]);
				String qm=servPicAdr+"/" + map2.get("cyqm");
				model.addAttribute("qm", qm);
			}
			model.addAttribute("vedio", vedioRecordDto);
			model.addAttribute("pic", picProjectDto);
			model.addAttribute("obj", ckInfoEntityDto);
			model.addAttribute("srln", srln);
			model.addAttribute("rckCount", rckCount);
			return "com/vkl/ckts_pc/rgsy/checkAudit";
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("加载查验审核信息异常......................异常内容："+e.getMessage());
			return null;
		}
	}
	
	/**
	 * 表单提交
	 * @return
	 */
	@ResponseBody
	@RequestMapping("subinfo")
	public String subInfo(HttpServletRequest request,String pAuditFlag,String srln,String rckCount){
		try {
			// 获取页面数组
			String[] arr=request.getParameterValues("arr");
			int number = vehicleReviewService.updData(arr, pAuditFlag, srln,rckCount);
			logger.info("审核信息更新........................");
			// 判断更新结果
			if(number>0){
				return "success";
			}
		} catch (Exception e) {
			logger.info("审核信息更新异常........................异常内容："+e.getMessage());
			e.printStackTrace();
			return "error";
		}
		return "error";
	}

	
	/**
	 * 审核统计
	 * 
	 * @return
	 */
	@RequestMapping(value="shtj")
	public String shtj(Model model){
		ShtjDto shtjDto=vehicleReviewService.findShTj();
		model.addAttribute("shtj", shtjDto);
		return "com/vkl/ckts_pc/rgsy/shtj";
	}
	/**
	 * 审核统计
	 * 
	 * @return
	 */
	@RequestMapping(value="shtj11")
	@ResponseBody
	public ShtjDto shtj11(Model model){
		ShtjDto shtjDto=vehicleReviewService.findShTj();
		model.addAttribute("shtj", shtjDto);
		return shtjDto;
	}
	
	
	/**
	 * 当前页自动审核
	 * 
	 * @param page
	 *            分页对象
	 * @param ckInfoEntityDto
	 *            接受页面参数
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("zdsh")
	public String zdsh(Page<CkInfoEntityDto> page, CkInfoEntityDto ckInfoEntityDto,
			HttpServletRequest request) throws IOException {
		try {
			// 从request中获取session
			HttpSession session = request.getSession();
			UserEntity user = (UserEntity) session.getAttribute(Constrant.S_USER_SESSION);
			// 设置当前审核员id
			ckInfoEntityDto.setpAuditer(Integer.parseInt(user.getId()));
			// 设置是否人工审核
			ckInfoEntityDto.setIsPAudit(IdEntity.S_YES);
			page.setT(ckInfoEntityDto);
			ckInfoEntityDto.setIsAudoAudit("1");
			page=vehicleReviewService.findByPage(page);
			if (page!=null) {
				List<CkInfoEntityDto> ckInfoEntityDtos = page.getList();
				if (ckInfoEntityDtos!=null&&ckInfoEntityDtos.size()>0) {
					for (CkInfoEntityDto ckInfoEntityDto2 : ckInfoEntityDtos) {
						vehicleReviewService.updaExamine("0", ckInfoEntityDto2.getSrln(), ckInfoEntityDto2.getRckCount()+"",null,user.getId(),new Date());
					}
				}
			}
			logger.info("用户："+user.getId()+"查询页面数据");
			return "success";
		} catch (NumberFormatException e) {
			e.printStackTrace();
			logger.info("查询页面数据异常.......................异常内容："+e.getMessage());
			return "sys-error";
		}
	}
	/**
	 * 加载列表页面
	 * 
	 * @return
	 */
	@RequestMapping("loadlist")
	public String loadList(Model model) {
		try {
			// 加载下拉框数据
			model.addAttribute("operType", operTypeEntityService.selectAll());
			model.addAttribute("chkpts", vehicleReviewService.findChkpts());
			model.addAttribute("cheUse", CehUsnrEntityService.selectAll());
			model.addAttribute("hpzl", DictEntityService.findInfoByType("hpzl"));
			logger.info("加载页面数据....................");
			return "com/vkl/ckts_pc/rgsy/vehicleSearch";
		} catch (Exception e) {
			logger.info("加载页面数据失败....................");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 加载列表页面数据内容
	 * 
	 * @param request
	 *            获取session
	 * @param page
	 *            分页对象
	 * @return
	 */
	@ResponseBody
	@RequestMapping("loadivehicles")
	public Page<CkInfoEntityDto> loadInfo(HttpServletRequest request,
			Page<CkInfoEntityDto> page, CkInfoEntityDto ckInfoEntityDto) {
		try {
			page.setT(ckInfoEntityDto);
			return vehicleReviewService.findVehicleList(page);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询数据异常");
			return null;
		}
	}
	
	/**
	 * 加载查验审核详情
	 * 
	 * @param srln
	 *            流水号
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("loadtoviewInfo")
	public String loadtoViewInfo(String srln,String rckCount, Model model,HttpServletRequest resuest) throws IOException {
		try {
			String[] ckers = null;
			List<String> ckerInfo = null;
			logger.info("加载查验审核信息........................");
			// 根据流水号查询信息
			CkInfoEntityDto ckInfoEntityDto = vehicleReviewService.findVehicleInfo(srln, Constrant.HPZL_TYPE,rckCount);
			// 获取查验员姓名id
			if(ckInfoEntityDto.getCker()!=null){
			 ckers = StringUtils.stringToArray(ckInfoEntityDto.getCker(), StringUtils.SPLIT_COMMA);
			}
			// 获取业务类型id
			String[] operTypes = StringUtils.stringToArray(ckInfoEntityDto.getOperType(), StringUtils.SPLIT_COMMA);
			// 通过id得到名称
			List<String> operTypeInfo = vehicleReviewService.findAllOperType(operTypes);
			// 通过id得到名称
			if(ckers!=null){
			 ckerInfo = vehicleReviewService.findAllCker(ckers);
			}
			// 定义一个变量接收名称内容
			String name = "";
			// 循环取出业务类型的名称
			for (int i = 0; i <= operTypeInfo.size() - 1; i++) {
				if (i == operTypeInfo.size() - 1) {
					name += operTypeInfo.get(i);
					ckInfoEntityDto.setTypeName(name);
					break;
				}
				name += operTypeInfo.get(i) + ",";
			}
			name = "";
			// 循环取出查验员的名称
			if(ckerInfo!=null){
			for (int i = 0; i <= ckerInfo.size() - 1; i++) {
				String key = Base64Utils.decode(ckerInfo.get(i));
				if (i == ckerInfo.size() - 1) {
					name += key;
					ckInfoEntityDto.setCker(name);
					break;
				}
				name += key + ",";
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
			String url = PropertiesUtils.getValues("fileTomcatUrl");
			for(int i=0;i<picProjectDto.size();i++){
				picProjectDto.get(i).setPicUrl(url+"/"+picProjectDto.get(i).getPicUrl());
			}
			List<ResourceDto> resourceDtos=resouService.findResPic(srln, rckCount);	
			if (resourceDtos!=null&&resourceDtos.size()>0) {
				for (ResourceDto resourceDto : resourceDtos) {
					String localIp=PropertiesUtils.getValues("localIp");
					String localPort=PropertiesUtils.getValues("localPort");
					String fileUrl=PropertiesUtils.getValues("fileTomcatUrl");
					String picUrl=fileUrl+"/"+resourceDto.getRepicUrl();
					resourceDto.setRepicUrl(picUrl);
				}
			}
			
			// 获取查验视频记录
			 List<VedioRecordDto> vedioRecordDto = vedioRecordService
						.findBySrln(srln,rckCount);
			 for (int i = 0; i < vedioRecordDto.size(); i++) {
					vedioRecordDto.get(i).setVedioUrl("http://"+ckInfoEntityDto.getVideoId()+":"+ckInfoEntityDto.getVideoPort()+"//"
							+ vedioRecordDto.get(i).getVedioUrl());
				}
			model.addAttribute("resPics", resourceDtos);
			model.addAttribute("vedio", vedioRecordDto);
			model.addAttribute("pic", picProjectDto);
			model.addAttribute("obj", ckInfoEntityDto);
			model.addAttribute("srln", srln);
			model.addAttribute("rckCount", rckCount);
			return "com/vkl/ckts_pc/rgsy/vehicleInfoView";
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("加载查验审核信息异常......................异常内容："+e.getMessage());
			return null;
		}
	}
	
	
	
}
