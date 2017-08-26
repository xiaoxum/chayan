package com.vkl.ckts.cksy.vehicleinformation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vkl.ckts.cksy.base.dto.PrinterDto;
import com.vkl.ckts.cksy.base.service.BgdDyServiceImpl;
import com.vkl.ckts.cksy.base.service.BgdDyServiceImpl2;
import com.vkl.ckts.cksy.servacpt.service.ICkInfoEntityService;
import com.vkl.ckts.cksy.vehicleinformation.entity.CkInfoEntityDto;
import com.vkl.ckts.cksy.vehicleinformation.entity.PicRecordDto;
import com.vkl.ckts.cksy.vehicleinformation.entity.ProjectRecordDto;
import com.vkl.ckts.cksy.vehicleinformation.entity.ResourceDto;
import com.vkl.ckts.cksy.vehicleinformation.entity.VedioRecordDto;
import com.vkl.ckts.cksy.vehicleinformation.service.IPicRecordServices;
import com.vkl.ckts.cksy.vehicleinformation.service.IProjectRecordServices;
import com.vkl.ckts.cksy.vehicleinformation.service.IResService;
import com.vkl.ckts.cksy.vehicleinformation.service.IVedioRecordServices;
import com.vkl.ckts.cksy.vehicleinformation.service.IVehicleInfoService;
import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.entity.DeptEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.ICehUsnrEntityService;
import com.vkl.ckts.common.service.IOperTypeEntityService;
import com.vkl.ckts.common.utils.Base64Utils;
import com.vkl.ckts.common.utils.FileUtils;
import com.vkl.ckts.common.utils.PropertiesUtils;
import com.vkl.ckts.common.utils.StringUtils;

/**
 * 车辆信息
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("module/vehicleinfo/")
public class vehicleInformationController {
	/**
	 * 车辆信息service
	 */
	@Autowired
	IVehicleInfoService vehicleInfoService;
	/**
	 * 业务类型表
	 */
	@Autowired
	IOperTypeEntityService operTypeEntityService;
	/**
	 * 查验拍照项目
	 */
	@Autowired
	IProjectRecordServices projectRecordServices;
	/**
	 * 查验拍照记录
	 */
	@Autowired
	IPicRecordServices picRecordServices;
	/**
	 * 查验视频记录
	 */
	@Autowired
	IVedioRecordServices vedioRecordServices;
	/**
	 * 查验信息表接口
	 */
	@Autowired
	ICkInfoEntityService iCkInfoEntityService;
	@Autowired
	IResService resService;
	/**
	 * 使用性质表
	 */
	@Autowired
	ICehUsnrEntityService CehUsnrEntityService;

	/**
	 * 日志处理
	 */
	private static Logger logger = LoggerFactory
			.getLogger(vehicleInformationController.class);

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
			logger.info("加载页面数据....................");
			return "com/vkl/ckts_pc/cksy/vehicleList";
		} catch (Exception e) {
			logger.info("加载页面数据失败....................");
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 加载列表页面
	 * 
	 * @return
	 */
	@RequestMapping("dyinfo")
	public String dyinfo(Model model) {
		try {
			// 加载下拉框数据
			model.addAttribute("operType", operTypeEntityService.selectAll());
			logger.info("加载页面数据....................");
			return "com/vkl/ckts_pc/cksy/dyjlbinfo";
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
	@RequestMapping("loadinfo")
	public Page<CkInfoEntityDto> loadInfo(HttpServletRequest request,
			Page<CkInfoEntityDto> page, CkInfoEntityDto ckInfoEntityDto) {
		try {
			// 从request中获取session
			HttpSession session = request.getSession();
			UserEntity user = (UserEntity) session
					.getAttribute(Constrant.S_USER_SESSION);
			// 设置用户机构
			//查找部门信息
			DeptEntity deptEntity=vehicleInfoService.findDeptById(user.getUserDept().toString());
			if (deptEntity==null) {
				return null;
			}
			if (!"1".equals(deptEntity.getDeptType())) {
				//当前不为为市车管所
				ckInfoEntityDto.setOrganCode(deptEntity.getFileId());
			}
			page.setT(ckInfoEntityDto);
			logger.info("用户：" + user.getId() + "查询页面数据");
			return vehicleInfoService.findByPage(page);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询数据异常");
			return null;
		}
	}

	/**
	 * 加载车辆详情详情
	 * 
	 * @param srln
	 *            流水号
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("loadcheckaudit")
	public String loadCheckAudit(String srln, String rckCount, Model model,
			HttpServletRequest resuest) throws IOException {
		try {
			logger.info("加载车辆详情信息........................");
			// 根据流水号查询信息
			CkInfoEntityDto ckInfoEntityDto = vehicleInfoService
					.findVehicleInfo(srln, rckCount, Constrant.HPZL_TYPE);
			// 获取查验员姓名id
			String[] ckers =null;
			String[] operTypes =null;
			if(ckInfoEntityDto.getCker()!=null){
				ckers = StringUtils.stringToArray(
						ckInfoEntityDto.getCker(), StringUtils.SPLIT_COMMA);
				
			}
			if(ckInfoEntityDto.getOperType()!=null){
				operTypes = StringUtils.stringToArray(
						ckInfoEntityDto.getOperType(), StringUtils.SPLIT_COMMA);
			}
			// 获取业务类型id
			// 通过id得到名称
			List<String> operTypeInfo = vehicleInfoService
					.findAllOperType(operTypes);
			// 通过id得到名称
			List<String> ckerInfo = null;
			// 定义一个变量接收名称内容
			String name = "";
			// 循环取出业务类型的名称
			if (ckers != null) {
				ckerInfo = vehicleInfoService.findAllCker(ckers);
			}
			for (int i = 0; i <= operTypeInfo.size() - 1; i++) {
				
				if (i == operTypeInfo.size() - 1) {
					name += operTypeInfo.get(i);
				} else {
					name += operTypeInfo.get(i) + ",";
				}
				ckInfoEntityDto.setTypeName(name);
			}
			if (ckerInfo != null) {
				// 循环取出查验员的名称
				for (int i = 0; i <= ckerInfo.size() - 1; i++) {
					String key = new String(Base64Utils.decode(ckerInfo.get(i))
							);
					name = "";
					
					if (i == ckerInfo.size() - 1) {
						name += key;

					} else {
						name += key + ",";
					}
					ckInfoEntityDto.setCker(name);
				}
			}
			
			List<ResourceDto> resourceDtos=resService.findResPic(srln, rckCount);	
			if (resourceDtos!=null&&resourceDtos.size()>0) {
				for (ResourceDto resourceDto : resourceDtos) {
					String localIp=PropertiesUtils.getValues("localIp");
					String localPort=PropertiesUtils.getValues("localPort");
					String picUrl="http://"+localIp+":"+localPort+"/"+resourceDto.getRepicUrl();
					resourceDto.setRepicUrl(picUrl);
				}
			}
			// 获取当前查验项
			List<ProjectRecordDto> list = projectRecordServices.findById(srln,
					rckCount);
			Map<String, String> map = new HashMap<String, String>();
			// 循环取出查验项存入map集合
			if (list != null) {
				for (ProjectRecordDto info : list) {
					map.put(info.getProId(), info.getCkFlag());
				}
			}
			// 将查验项记录存入session
			resuest.getSession().setAttribute("map", map);
			// 获取查验照片记录
			List<PicRecordDto> picProjectDto = picRecordServices.findBySrln(
					srln, rckCount);
			// 获取文件服务器ip和端口并拼接
			String url = PropertiesUtils.getValues("fileTomcatUrl");
			try {
				for (int i = 0; i < picProjectDto.size(); i++) {
					picProjectDto.get(i).setPicUrl(
							url + "/" + picProjectDto.get(i).getPicUrlPa());
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
			List<VedioRecordDto> vedioRecordDto = vedioRecordServices
					.findBySrln(srln,rckCount);
			for (int i = 0; i < vedioRecordDto.size(); i++) {
				vedioRecordDto.get(i).setVedioUrl("http://"+ckInfoEntityDto.getVideoId()+":"+ckInfoEntityDto.getVideoPort()+"//"
						+ vedioRecordDto.get(i).getVedioUrl());
			}
			// 根据使用性质查看是否校车
			model.addAttribute("parentId",
					CehUsnrEntityService.findById(ckInfoEntityDto.getSyxz())
							.getParentId());
			model.addAttribute("vedio", vedioRecordDto);
			model.addAttribute("pic", picProjectDto);
			model.addAttribute("obj", ckInfoEntityDto);
			model.addAttribute("resPics", resourceDtos);
			model.addAttribute("srln", srln);
			return "com/vkl/ckts_pc/cksy/vehicleInformation";
		} catch (Exception e) {
			logger.info("加载车辆详情信息异常........................" + e.getMessage());
			e.printStackTrace();
			return "com/vkl/ckts_pc/cksy/vehicleInformation";
		}
	}

	
	/**
	 * 加载车辆详情详情
	 * 
	 * @param srln
	 *            流水号
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("dyvehinfo")
	public String dyVehInfo(String srln, String rckCount, Model model,
			HttpServletRequest resuest) throws IOException {
		try {
			logger.info("加载车辆详情信息........................");
			// 根据流水号查询信息
			CkInfoEntityDto ckInfoEntityDto = vehicleInfoService
					.findVehicleInfo(srln, rckCount, Constrant.HPZL_TYPE);
			// 获取查验员姓名id
			String[] ckers =null;
			String[] operTypes =null;
			if(ckInfoEntityDto.getCker()!=null){
				ckers = StringUtils.stringToArray(
						ckInfoEntityDto.getCker(), StringUtils.SPLIT_COMMA);
				
			}
			if(ckInfoEntityDto.getOperType()!=null){
				operTypes = StringUtils.stringToArray(
						ckInfoEntityDto.getOperType(), StringUtils.SPLIT_COMMA);
			}
			// 获取业务类型id
			// 通过id得到名称
			List<String> operTypeInfo = vehicleInfoService
					.findAllOperType(operTypes);
			// 通过id得到名称
			List<String> ckerInfo = null;
			// 定义一个变量接收名称内容
			String name = "";
			// 循环取出业务类型的名称
			if (ckers != null) {
				ckerInfo = vehicleInfoService.findAllCker(ckers);
			}
			for (int i = 0; i <= operTypeInfo.size() - 1; i++) {
				
				if (i == operTypeInfo.size() - 1) {
					name += operTypeInfo.get(i);
				} else {
					name += operTypeInfo.get(i) + ",";
				}
				ckInfoEntityDto.setTypeName(name);
			}
			if (ckerInfo != null) {
				// 循环取出查验员的名称
				for (int i = 0; i <= ckerInfo.size() - 1; i++) {
					String key = new String(Base64Utils.decode(ckerInfo.get(i))
							);
					name = "";
					
					if (i == ckerInfo.size() - 1) {
						name += key;

					} else {
						name += key + ",";
					}
					ckInfoEntityDto.setCker(name);
				}
			}
			
			// 获取当前查验项
			List<ProjectRecordDto> list = projectRecordServices.findById(srln,
					rckCount);
			Map<String, String> map = new HashMap<String, String>();
			// 循环取出查验项存入map集合
			if (list != null) {
				for (ProjectRecordDto info : list) {
					map.put(info.getProId(), info.getCkFlag());
				}
			}
			// 将查验项记录存入session
			resuest.getSession().setAttribute("map", map);
			// 获取查验照片记录
			List<PicRecordDto> picProjectDto = picRecordServices.findBySrln(
					srln, rckCount);
			// 获取文件服务器ip和端口并拼接
			String url = PropertiesUtils.getValues("fileTomcatUrl");
			try {
				for (int i = 0; i < picProjectDto.size(); i++) {
					picProjectDto.get(i).setPicUrl(
							url + "/" + picProjectDto.get(i).getPicUrlPa());
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
			// 获取查验视频记录
			List<VedioRecordDto> vedioRecordDto = vedioRecordServices
					.findBySrln(srln,rckCount);
			for (int i = 0; i < vedioRecordDto.size(); i++) {
				vedioRecordDto.get(i).setVedioUrl(
						url + "/" + vedioRecordDto.get(i).getVedioUrl());
			}
			// 根据使用性质查看是否校车
			model.addAttribute("parentId",
					CehUsnrEntityService.findById(ckInfoEntityDto.getSyxz())
							.getParentId());
			model.addAttribute("vedio", vedioRecordDto);
			model.addAttribute("pic", picProjectDto);
			model.addAttribute("obj", ckInfoEntityDto);
			model.addAttribute("srln", srln);
			return "com/vkl/ckts_pc/cksy/dyvehInfor";
		} catch (Exception e) {
			logger.info("加载车辆详情信息异常........................" + e.getMessage());
			e.printStackTrace();

			return "com/vkl/ckts_pc/cksy/dyvehInfor";
		}
	}
	/**
	 * 打印查验记录表
	 * 
	 */
	@RequestMapping("printCkData")
	@ResponseBody
	public String printCkData(Model model, String srln, String rckCount) {
		BgdDyServiceImpl bgdDyServiceImpl = new BgdDyServiceImpl();

		try {
			bgdDyServiceImpl.serviceDyJl(srln, rckCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	

	/**
	 * 打印查验记录表
	 * 
	 */
	@RequestMapping("printCkData1")
	@ResponseBody
	public String printCkData1(Model model, String srln, String rckCount) {
		BgdDyServiceImpl bgdDyServiceImpl = new BgdDyServiceImpl();
		String docPath="";
		try {
			docPath=bgdDyServiceImpl.clientDyJl(srln, rckCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
		return docPath;
	}
	
	
	/**
	 * 打印查验记录表
	 * 
	 */
	@RequestMapping("printCkData2")
	@ResponseBody
	public List<PrinterDto>  printCkData2(Model model, String srln, String rckCount) {
		BgdDyServiceImpl2 bgdDyServiceImpl = new BgdDyServiceImpl2();
		try {
			Map<String, Object> maps= bgdDyServiceImpl.dyOne1(srln, rckCount);
			if (maps!=null&&maps.size()>0) {
				return getdtos(maps);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
	}
	
	
	public List<PrinterDto>  getdtos(Map<String, Object> maps){
		Set<String> sets=maps.keySet();
		List<PrinterDto> lists=new ArrayList<PrinterDto>();
		for (String string : sets) {
			PrinterDto printerDto=new PrinterDto();
			printerDto.setText(string);
			printerDto.setValue(maps.get(string));
			lists.add(printerDto);
			
		}
		return lists;
	}
	
	
	
	
	
	
	/**
	 * 手动打印照片记录
	 * 
	 * @param srln
	 *            流水号
	 * @param rckCount
	 *            复检次数
	 */
	@RequestMapping("printCkPhoto")
	@ResponseBody
	public String printCkPhoto(Model model, String srln, String rckCount) {
		BgdDyServiceImpl bgdDyServiceImpl = new BgdDyServiceImpl();
		try {
			bgdDyServiceImpl.serviceDyPhoto(srln, rckCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	
	/**
	 * 客户端手动打印照片记录
	 * 
	 * @param srln
	 *            流水号
	 * @param rckCount
	 *            复检次数
	 */
	@RequestMapping("printCkPhoto1")
	@ResponseBody
	public List<String> printCkPhoto1(Model model, String srln, String rckCount) {
		BgdDyServiceImpl bgdDyServiceImpl = new BgdDyServiceImpl();
		try {
			List<String> listStrings=bgdDyServiceImpl.clientDyPhoto(srln, rckCount);
			return listStrings;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 自动打印查验记录表
	 * 
	 */
	@RequestMapping("doPrintList")
	@ResponseBody
	public String doPrintList(Model model) {
		BgdDyServiceImpl bgdDyServiceImpl = new BgdDyServiceImpl();
		try {
			bgdDyServiceImpl.servicebatchdy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * 自动打印查验记录表
	 * 
	 */
	@RequestMapping("doPrintList1")
	@ResponseBody
	public List<String> doPrintList1(Model model) {
		BgdDyServiceImpl bgdDyServiceImpl = new BgdDyServiceImpl();
		try {
			List<String> urls=bgdDyServiceImpl.clientBatchdy();
			return urls;
		}catch (Exception e) {
			return null;
		}

	}
	
	/**
	 * 客户端选择性打印照片 
	 * 
	 * @param printParam
	 * 
	 * @return
	 */
	@RequestMapping("clientdyPhotoInfo")
	@ResponseBody
	public List<String>  clientdyPhotoInfo(String printParam){
		BgdDyServiceImpl bgdDyServiceImpl = new BgdDyServiceImpl();
		try {
			List<String> listStrings=bgdDyServiceImpl.clientSelDy(printParam);
			return listStrings;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 服务端选择性打印照片 
	 * 
	 * @param printParam
	 * 
	 * @return
	 */
	@RequestMapping("servicedyPhotoInfo")
	@ResponseBody
	public void  servicedyPhotoInfo(String printParam){
		BgdDyServiceImpl bgdDyServiceImpl = new BgdDyServiceImpl();
		try {
			bgdDyServiceImpl.serviceSelDy(printParam);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 添加补拍
	 * 
	 * @param srln       流水号
	 * @param rckCount   复检次数
	 * @param picId      照片编号
	 * 
	 * @return
	 */
	@RequestMapping("addpbphoto")
	@ResponseBody
	public String addPbPhoto(String srln ,String rckCount,String picId,HttpServletRequest request){
		try {
			UserEntity userEntity = (UserEntity)request.getSession().getAttribute(Constrant.S_USER_SESSION);
			String userId="";
			if (userEntity!=null) {
				userId=userEntity.getId();
			}
			if (picId==null) {
				return "false";
			}
			String[] picIds=picId.split(",");
			if (picIds!=null && picIds.length>0) {
			   for (String string : picIds) {
				   picRecordServices.photoBp(srln, rckCount, string, userId);
			   }	
			}
		} catch (Exception e) {
			return "false";
		}
		return "true";
	}
	
	/**
	 * 添加补拍
	 * 
	 * @param srln       流水号
	 * @param rckCount   复检次数
	 * @param picId      照片编号
	 * 
	 * @return
	 */
	@RequestMapping("addqspbphoto")
	@ResponseBody
	public String addQsPbPhoto(String srln ,String rckCount,HttpServletRequest request){
		try {
			UserEntity userEntity = (UserEntity)request.getSession().getAttribute(Constrant.S_USER_SESSION);
			String userId="";
			if (userEntity!=null) {
				userId=userEntity.getId();
			}
		    picRecordServices.photoQsBp(srln, rckCount,  userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
		return "true";
	}
	
	
	
	
	/**
	 * 删除查验信息
	 * 
	 * @param ckid       流水号
	 * 
	 * @return
	 */
	@RequestMapping("deleteckinfo")
	@ResponseBody
	public String deleteCkInfo(String ckid,HttpServletRequest request){
		try {
		    String srln=ckid.split(":")[0];
		    String rckCount=ckid.split(":")[1];
		    vehicleInfoService.deleteCkInfo(srln, rckCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
		return "true";
	}
	
	/**
	 * 删除查验信息
	 * 
	 * @param ckid       流水号
	 * 
	 * @return
	 */
	@RequestMapping("deletedocfile")
	@ResponseBody
	public String deletedocfile(String url,HttpServletRequest request){
		try {
			String localIp=PropertiesUtils.getValues("localIp");
			String localPorts=PropertiesUtils.getValues("localPort");
			String fwlj=PropertiesUtils.getValues("docSaveFwPath");
			String filePath=url.substring(("http:\\\\" + localIp + ":"+ localPorts + "\\"+ fwlj).length());
			String savePath = PropertiesUtils.getValues("docSaveAbsolutePath");
			String absoPath=savePath+"\\"+filePath;
			FileUtils.deleteFile(absoPath);
		} catch (Exception e) {
			return "false";
		}
		return "true";
	}
	
	@RequestMapping("getnodyjl")
	@ResponseBody
	public List<Map<String, Object>> getNoDyJl(){
		BgdDyServiceImpl bgdDyServiceImpl = new BgdDyServiceImpl();
		try {
			return bgdDyServiceImpl.getNoDyJl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	

	@RequestMapping("downvideo")
	@ResponseBody
	public String downvideo(String srln,String rckCount,String videoAngle){
		try {
			vedioRecordServices.videoDownLoad(srln, rckCount, videoAngle);
			return "操作成功";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "sys-error";
		}
	}
	
	
}
