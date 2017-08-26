package com.vkl.ckts.cksy.servacpt.web;

import java.io.File;
import java.io.IOException;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;

import com.vkl.ckts.cksy.base.service.BgdDyServiceImpl;
import com.vkl.ckts.cksy.servacpt.entity.CkInfoDto;
import com.vkl.ckts.cksy.servacpt.entity.TJbxx;
import com.vkl.ckts.cksy.servacpt.entity.VehInfoDto;
import com.vkl.ckts.cksy.servacpt.service.IChApplItemService;
import com.vkl.ckts.cksy.servacpt.service.ICkInfoEntityService;
import com.vkl.ckts.cksy.servacpt.service.IOperApplService;
import com.vkl.ckts.cksy.servacpt.service.IPleAppService;
import com.vkl.ckts.cksy.servacpt.service.IPoliceService;
import com.vkl.ckts.cksy.servacpt.service.IResourceService;
import com.vkl.ckts.cksy.servacpt.service.IVehInfoDtoService;
import com.vkl.ckts.common.base.Message;
import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.controller.BaseController;
import com.vkl.ckts.common.entity.ChApplItemEntity;
import com.vkl.ckts.common.entity.CkCllxEntity;
import com.vkl.ckts.common.entity.CkInfoEntity;
import com.vkl.ckts.common.entity.OperApplEntity;
import com.vkl.ckts.common.entity.PleApplEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.service.ICehUsnrEntityService;
import com.vkl.ckts.common.service.ICkCllxEntityService;
import com.vkl.ckts.common.service.IDictEntityService;
import com.vkl.ckts.common.service.IOperTypeEntityService;
import com.vkl.ckts.common.utils.BarCodeUtils;
import com.vkl.ckts.common.utils.DateUtil;
import com.vkl.ckts.common.utils.PropertiesUtils;
import com.vkl.ckts.common.utils.WordUtil;
import com.vkl.ckts.rgsy.statistic.log.service.ILogService;
import com.vkl.ckts.rgsy.vehiclereview.controller.VehicleReviewCotroller;

/**
 * @see 业务受理请求处理
 * @author hwf
 * @version 1.0
 */
@Controller
@RequestMapping("module/servacpt")
public class ServacptController extends BaseController {

	/**
	 * 注入操作日志
	 */
	@Autowired
	ILogService logService;
	/**
	 * 字典表
	 */
	@Autowired
	IDictEntityService DictEntityService;
	/**
	 * 车型表
	 */
	@Autowired
	ICkCllxEntityService ckCllxEntityService;
	/**
	 * 使用性质表
	 */
	@Autowired
	ICehUsnrEntityService CehUsnrEntityService;
	/**
	 * 业务类型表
	 */
	@Autowired
	IOperTypeEntityService OperTypeEntityService;

	/**
	 * 插入注册申请表数据
	 */
	@Autowired
	IOperApplService operApplService;

	/**
	 * 插入抵押申请表数据
	 */
	@Autowired
	IPleAppService iPleAppService;

	/**
	 * 插入变更申请表数据
	 */
	@Autowired
	IChApplItemService iChApplItemService;

	/**
	 * 模拟六合一接口
	 */
	@Autowired
	IPoliceService iPoliceService;

	/**
	 * 查验信息表接口
	 */
	@Autowired
	ICkInfoEntityService iCkInfoEntityService;
	
	@Autowired
	IVehInfoDtoService VehicleInfoServiceImpl;

	/**
	 * 业务影像资料上传接口
	 */
	@Autowired
	IResourceService iResourceService;
	/**
	 * 日志处理
	 */
	private static Logger logger = LoggerFactory.getLogger(VehicleReviewCotroller.class);

	/**
	 * @高拍仪页加载
	 */
	@RequestMapping("/gaopai")
	public String gaopai(Model model, String srln,String rckCount) {
		logger.info("---------加载高拍页面------------");
		model.addAttribute("srln", srln);
		model.addAttribute("respros", iResourceService.findResources());
		String localAddress="http://"+PropertiesUtils.getValues("localIp")+":"+PropertiesUtils.getValues("localPort");
		model.addAttribute("localAddress", localAddress);
		model.addAttribute("rckCount", rckCount);
		return "com/vkl/ckts_pc/cksy/gaopai";
	}


	/**
	 * @打印页加载
	 */
	@ResponseBody
	@RequestMapping("/print")
	public String print(Model model, String srln,String rckCount) {

		logger.info("---------加载打印受理单------------");
		String img = null;
		String img2 = null;
		CkInfoDto ckInfoDto = null;
		try {
			img = BarCodeUtils.BarCode(srln);// 生成条形码
			ckInfoDto = iCkInfoEntityService.findbysrln(srln,rckCount);
			CkCllxEntity ckCllxEntity =	ckCllxEntityService.findById(ckInfoDto.getCllx());
			if (ckCllxEntity==null) {
				ckInfoDto.setHpzl("");
			}else {
				ckInfoDto.setHpzl(ckCllxEntity.getCllxName());
			}
//			DictEntity dictEntity=DictEntityService.findInfoByKeyAndType(ckInfoDto.getHpzl(), "hpzl");
//			if (dictEntity!=null) {
//				ckInfoDto.setHpzl(dictEntity.getLdictionaryAbel());
//			}else {
//				ckInfoDto.setHpzl("");
//			}
			/*String xml = XMLUtils.toXML(ckInfoDto);
			img2 = BarCodeUtils.load(xml, 300, 300);// 生成二维码
*/
			img2 = BarCodeUtils.load(srln, 300, 300);// 生成二维码
			model.addAttribute("ckInfoDto", ckInfoDto);
			Map<String, Object> map = new HashMap<String, Object>();
           
			map.put("img", img.replace("data:image/png;base64,", ""));
			map.put("img2", img2.replace("data:image/png;base64,", ""));
			map.put("operType", OperTypeEntityService.findById(ckInfoDto.getOperType()).getTypeName());
			map.put("srln", ckInfoDto.getSrln());
			if(ckInfoDto.getName() == null || ckInfoDto.getName() == ""){
				ckInfoDto.setName("——");
			}
			map.put("name", ckInfoDto.getName());
			if (ckInfoDto.getHphm() == null || ckInfoDto.getHphm() == "") {
				ckInfoDto.setHphm("——");
			}
			map.put("hphm", ckInfoDto.getHphm());
			map.put("hpzl", ckInfoDto.getHpzl());
			map.put("clsbdh", ckInfoDto.getClsbdh());
			String date = DateUtil.format(ckInfoDto.getCreateDate(), DateUtil.DATE_TIME_FORMAT);
			map.put("createDate", date);
			String fileName = DateUtil.format(new Date(), DateUtil.TIMESTAMP_NUMBER) + ".doc";
			WordUtil.createWord(map, "proof.ftl", PropertiesUtils.getValues("printpath"), fileName, "/templete");
			String path = PropertiesUtils.getValues("printpath") + fileName;
			BgdDyServiceImpl bgdDyServiceImpl = new BgdDyServiceImpl();
			bgdDyServiceImpl.dyto(path, PropertiesUtils.getValues("proofPrinterName"));
			return "";
		} catch (IOException e) {

			e.printStackTrace();
			return "";
		}
	}
	/**
	 * @打印页加载
	 */
	@ResponseBody
	@RequestMapping("/print1")
	public String print1(Model model, String srln,String rckCount) {
		String localIp=PropertiesUtils.getValues("localIp");
		String localPorts=PropertiesUtils.getValues("localPort");
		String savePath = PropertiesUtils.getValues("docSaveAbsolutePath");
		String fwlj=PropertiesUtils.getValues("docSaveFwPath");
		logger.info("---------加载打印受理单------------");
		String img = null;
		String img2 = null;
		CkInfoDto ckInfoDto = null;
		try {
			img = BarCodeUtils.BarCode(srln);// 生成条形码
			ckInfoDto = iCkInfoEntityService.findbysrln(srln,rckCount);
			CkCllxEntity ckCllxEntity =	ckCllxEntityService.findById(ckInfoDto.getCllx());
			if (ckCllxEntity==null) {
				ckInfoDto.setHpzl("");
			}else {
				ckInfoDto.setHpzl(ckCllxEntity.getCllxName());
			}
//			DictEntity dictEntity=DictEntityService.findInfoByKeyAndType(ckInfoDto.getHpzl(), "hpzl");
//			if (dictEntity!=null) {
//				ckInfoDto.setHpzl(dictEntity.getLdictionaryAbel());
//			}else {
//				ckInfoDto.setHpzl("");
//			}
			/*String xml = XMLUtils.toXML(ckInfoDto);
			img2 = BarCodeUtils.load(xml, 300, 300);// 生成二维码
*/
			img2 = BarCodeUtils.load(srln, 300, 300);// 生成二维码
			model.addAttribute("ckInfoDto", ckInfoDto);
			Map<String, Object> map = new HashMap<String, Object>();
           
			map.put("img", img.replace("data:image/png;base64,", ""));
			map.put("img2", img2.replace("data:image/png;base64,", ""));
			map.put("operType", OperTypeEntityService.findById(ckInfoDto.getOperType()).getTypeName());
			map.put("srln", ckInfoDto.getSrln());
			if(ckInfoDto.getName() == null || ckInfoDto.getName() == ""){
				ckInfoDto.setName("——");
			}
			map.put("name", ckInfoDto.getName());
			if (ckInfoDto.getHphm() == null || ckInfoDto.getHphm() == "") {
				ckInfoDto.setHphm("——");
			}
			map.put("hphm", ckInfoDto.getHphm());
			map.put("hpzl", ckInfoDto.getHpzl());
			map.put("clsbdh", ckInfoDto.getClsbdh());
			String date = DateUtil.format(ckInfoDto.getCreateDate(), DateUtil.DATE_TIME_FORMAT);
			map.put("createDate", date);
			String fileName = DateUtil.format(new Date(), DateUtil.TIMESTAMP_NUMBER) + ".doc";
			WordUtil.createWord(map, "proof.ftl",savePath, fileName, "/templete");
			String path = PropertiesUtils.getValues("printpath") + fileName;
//			BgdDyServiceImpl bgdDyServiceImpl = new BgdDyServiceImpl();
//			bgdDyServiceImpl.dyto(path, PropertiesUtils.getValues("proofPrinterName"));
			return "http:\\\\" + localIp + ":"+ localPorts + "\\"+ fwlj + "\\" + fileName;
		} catch (IOException e) {

			e.printStackTrace();
			return "";
		}
	}

	
	
	
	/**
	 * @查验业务申请页加载
	 */
	@RequestMapping("/applyfor")
	public String loadApplyFor() {
		logger.info("---------加载查验业务申请------------");
		return "com/vkl/ckts_pc/cksy/aplayForTable";
	}

	/**
	 * @加载注册登记受理表 机动车注册、转移、注销登记/转入申请表
	 * @return
	 */
	@RequestMapping("/tableone")
	public String loadTableOne(Model model) {
		/**
		 * 加载下拉框内容
		 */
		logger.info("---------加载注册登记受理表------------");
		model.addAttribute("hpzl", DictEntityService.findInfoByType("hpzl"));
		model.addAttribute("csys", DictEntityService.findInfoByType("csys"));
		model.addAttribute("djyy", DictEntityService.findInfoByType("djyy"));
		model.addAttribute("hqfs", DictEntityService.findInfoByType("hqfs"));
		model.addAttribute("cllxType", ckCllxEntityService.selectAll());
		model.addAttribute("cheUse", CehUsnrEntityService.selectAll());
		model.addAttribute("operType", OperTypeEntityService.selectAll());
		return "com/vkl/ckts_pc/cksy/tableOne";
	}

	/**
	 * @插入申请表数据 机动车注册、转移、注销登记/转入申请表
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/registeredData")
	public String registeredData(OperApplEntity operApplEntity, VehInfoDto vehInfoDto, HttpServletRequest request) {
		 
		String message = null;
		// Session 中获取当前用户
		UserEntity user = (UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION);
		try {
			operApplService.updateCllxProSort(vehInfoDto.getClxh());
            if(operApplEntity.getHphm()==null||"".equals(operApplEntity.getHphm()) ){
            	String hphm=vehInfoDto.getClsbdh().substring(vehInfoDto.getClsbdh().length()-5);
            	operApplEntity.setHphm(hphm);
            }else {
            	operApplEntity.setHphm(vehInfoDto.getXzqh()+vehInfoDto.getPinyin()+operApplEntity.getHphm());
			}
			message = operApplService.matchInfoAddOperAppl(operApplEntity, vehInfoDto, request);
			logService.insertLog("业务受理", "注册、注销", "业务受理", request);
			logger.info("用户:" + user.getId() + "受理机动车注册、转移、注销登记/转入申请表信息写入");
		} catch (Exception e) {
			e.printStackTrace();
			message = "false";

		}

		return message;
	}

	/**
	 * @加载机动车抵押登记申请表 机动车抵押登记/质押备案申请表
	 * @return
	 */
	@RequestMapping("/tabletwo")
	public String loadTableTwo(Model model) {
		/**
		 * 加载下拉框内容
		 */
		logger.info("---------加载机动车抵押登记受理表------------");
		model.addAttribute("cllxType", ckCllxEntityService.selectAll());
		model.addAttribute("cheUse", CehUsnrEntityService.selectAll());
		model.addAttribute("hpzl", DictEntityService.findInfoByType("hpzl"));
		model.addAttribute("operType", OperTypeEntityService.findByParentId("Z"));
		return "com/vkl/ckts_pc/cksy/tableTwo";
	}

	/**
	 * @插入申请表数据 机动车抵押登记/质押备案申请表
	 * @return message true/false
	 */
	@ResponseBody
	@RequestMapping("/pledgeData")
	public String pledgeData(PleApplEntity pleApplEntity, VehInfoDto vehInfoDto, HttpServletRequest request) {
		String message = null;
		// Session 中获取当前用户
		UserEntity user = (UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION);
		try {

			message = iPleAppService.addPleApp(pleApplEntity, vehInfoDto, request);
			logService.insertLog("业务受理", "抵押、质押", "业务受理", request);
			logger.info("用户:" + user.getId() + "受理机动车抵押登记受理表信息写入");
		} catch (Exception e) {

			e.printStackTrace();

			message = "false";
		}

		return message;
	}

	/**
	 * @加载机动车变更登记申请表 机动车变更登记/备案申请表
	 * @return
	 */
	@RequestMapping("/tablethree")
	public String loadTableThree(Model model) {
		/**
		 * 加载下拉框内容
		 */
		logger.info("---------加载机动车变更登记/备案申请受理表------------");
		model.addAttribute("hpzl", DictEntityService.findInfoByType("hpzl"));
		model.addAttribute("cllxType", ckCllxEntityService.selectAll());
		model.addAttribute("cheUse", CehUsnrEntityService.selectAll());
		return "com/vkl/ckts_pc/cksy/tableThree";
	}

	/**
	 * @插入申请表数据 机动车变更登记/备案申请表
	 * @return message true/false
	 */
	@ResponseBody
	@RequestMapping("/changeData")
	public String changeData(ChApplItemEntity chApplItemEntity, VehInfoDto vehInfoDto, HttpServletRequest request) {
		String message = null;
		// Session 中获取当前用户
		UserEntity user = (UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION);
		try {

			message = iChApplItemService.addChAppl(chApplItemEntity, vehInfoDto, request);
			logService.insertLog("业务受理", "变更、备案", "业务受理", request);
			logger.info("用户:" + user.getId() + "受理机动车变更登记/备案申请表信息写入");
		} catch (Exception e) {

			e.printStackTrace();

			message = "false";

		}

		return message;
	}

	/**
	 * 模拟 输入车牌、车架号点击查询调用六合一接口返回车辆信息并进行盗抢验证和黑名单验证
	 * 
	 * @param String
	 *            clsbdh 车辆识别代号
	 * @param String
	 *            hphm 车辆号牌号码
	 * @return Message
	 */
	@RequestMapping("/policeMessages")
	@ResponseBody
	public Message<Object> policeMessages(String clsbdh, String hphm, HttpServletRequest request) {
		// Session 中获取当前用户
		UserEntity user = (UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION);
		logger.info("用户:" + user.getId() + "受理模拟 输入车牌、车架号点击查询调用六合一接口");
		return iPoliceService.policeCheck(clsbdh, hphm);
	}

	/**
	 * 根据流水号查询查验信息
	 * 
	 * @param String
	 *            srln 流水号
	 * @return CkInfoEntity 查验信息
	 */
	@RequestMapping("/getCkInfo")
	public CkInfoEntity getCkInfo(String srln,String rckCount) {

		return iCkInfoEntityService.findckinfobysrln(srln,rckCount);
	}

	/**
	 * 业务影像资料上传
	 * 
	 * @param String
	 *            paths 上传文件路径名称逗号分隔
	 * @param String
	 *            srln 流水号
	 * @return
	 */
	@RequestMapping("/upResource")
	@ResponseBody
	public String upResource(String paths, String srln,String rckCount, HttpServletRequest request) {
		String message = null;
		// Session 中获取当前用户
		UserEntity user = (UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION);
		try {
			//message = iResourceService.upResource(paths, srln,rckCount, request);
			logService.insertLog("业务受理", "高拍上传", "业务受理", request);
			logger.info("用户:" + user.getId() + "业务高拍资料上传");
		} catch (Exception e) {

			e.printStackTrace();
			message = "false";
		}

		return message;
	}
	
	
	@RequestMapping("/reCheck")
	@ResponseBody
	public String reCheck(String srln,String rckCount, HttpServletRequest request) {
		String message = null;
		// Session 中获取当前用户
		UserEntity user = (UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION);
		try {
			message = operApplService.reCheck(srln, rckCount, request);
			
			logService.insertLog("复检受理", "复检", "复检受理", request);
			logger.info("用户:" + user.getId() + "复检");
		} catch (Exception e) {
			e.printStackTrace();
			message = "false";
		}
		return message;
	}
	
	/**
	 * 父级车辆类型
	 */
	@RequestMapping("/allcllx")
	@ResponseBody
	public List<CkCllxEntity> allcllx(){
	    List<CkCllxEntity> list = ckCllxEntityService.selectAll();
		return list;
	}
	/**
	 * 父级车辆类型
	 */
	@RequestMapping("/parentCllx")
	@ResponseBody
	public List<CkCllxEntity> parentCllx(){
	    List<CkCllxEntity> list = ckCllxEntityService.selectAllParent();
		return list;
	}
	
	
	/**
	 * 子级车辆类型
	 */
	@RequestMapping("/allCllx")
	@ResponseBody
	public List<CkCllxEntity> allCllx(String parentCllx){
	    List<CkCllxEntity> list = ckCllxEntityService.findByParentId(parentCllx);
		return list;
	}
	/**
	 * 子级车辆类型
	 */
	@RequestMapping("/sm")
	@ResponseBody
	public String  sm(String str){
	  return "dddd";
	}
	/**
	 * 子级车辆类型
	 */
	@RequestMapping("/getjdcjbxx")
	@ResponseBody
	public TJbxx  getJdcjBxx(String hphm,String hpzl,String cllx,String clsbdh){
		TJbxx jbxx=operApplService.getJdcjBxx(hphm, hpzl,cllx, clsbdh);
		return jbxx;
	}
	
	/**
	 * 子级车辆类型
	 */
	@RequestMapping("/toCkUpdatejsp")
	public String  toCkUpdatejsp(String ckid,Model model){
		
		String srln=ckid.split(":")[0];
		String rckCount=ckid.split(":")[1];
		logger.info("---------加载注册登记受理表------------");
		model.addAttribute("hpzl", DictEntityService.findInfoByType("hpzl"));
		model.addAttribute("csys", DictEntityService.findInfoByType("csys"));
		model.addAttribute("djyy", DictEntityService.findInfoByType("djyy"));
		model.addAttribute("hqfs", DictEntityService.findInfoByType("hqfs"));
		model.addAttribute("cllxType", ckCllxEntityService.selectAll());
		model.addAttribute("cheUse", CehUsnrEntityService.selectAll());
		model.addAttribute("operType", OperTypeEntityService.selectAll());
		model.addAttribute("chinfo", iCkInfoEntityService.findckinfobysrln(srln, rckCount));
		model.addAttribute("vehInfo", VehicleInfoServiceImpl.selectById(srln));
		return "com/vkl/ckts_pc/cksy/tableTree";
	}
	
	
	/**
	 * 更新下查验信息
	 * @param vehInfoDto
	 * @param ckInfoDto
	 * @return
	 */
	@RequestMapping("/updateckinfo")
	@ResponseBody
	public String updateCkInfo(VehInfoDto vehInfoDto,CkInfoDto ckInfoDto){
		
		try {
			operApplService.updateCkInfo(ckInfoDto, vehInfoDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
		return "true";
	}

	
	/**
	 * 更新下查验信息
	 * @param vehInfoDto
	 * @param ckInfoDto
	 * @return
	 */
	@RequestMapping("/uploadpic")
	@ResponseBody
	public String uploadResPic(MultipartFile picfile,String srln,String rckCount,String respicId){
        String savaePath = PropertiesUtils.getValues("tomcatWapps");
        String fwUrl= PropertiesUtils.getValues("respicfwurl");
        String datedir = DateUtil.format(new Date(), DateUtil.TIMEDATE_NUMBER);
        File file=new File(savaePath+fwUrl+"/"+datedir);
        if (!file.exists()) {
        	file.mkdirs();
		}
		String fileName= srln+"_"+rckCount+"_"+respicId+"_zl.jpg";
		File file2 = new File(file,fileName);
		try {
			picfile.transferTo(file2);
			String url="/"+fwUrl+"/"+datedir+"/"+fileName;
			iResourceService.upResource(url, srln, rckCount, respicId);
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "sys-error";
		}
	}
}
