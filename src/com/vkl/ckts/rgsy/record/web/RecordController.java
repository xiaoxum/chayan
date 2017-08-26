package com.vkl.ckts.rgsy.record.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.vkl.ckts.cksy.vehicleinformation.service.IVehicleInfoService;
import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.controller.BaseController;
import com.vkl.ckts.common.entity.CehUsnrEntity;
import com.vkl.ckts.common.entity.ChkptFileEntity;
import com.vkl.ckts.common.entity.CkCllxEntity;
import com.vkl.ckts.common.entity.DeptEntity;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.entity.OperTypeEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.entity.ViewerFileEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IDictEntityService;
import com.vkl.ckts.common.utils.Base64Utils;
import com.vkl.ckts.common.utils.RandomUtils;
import com.vkl.ckts.pub.service.IBaseService;
import com.vkl.ckts.rgsy.record.entity.ChkptFileDto;
import com.vkl.ckts.rgsy.record.entity.ViewerFileDto;
import com.vkl.ckts.rgsy.record.service.IChkptFileService;
import com.vkl.ckts.rgsy.record.service.IViewerFileService;
import com.vkl.ckts.rgsy.statistic.log.service.ILogService;
import com.vkl.ckts.rgsy.system.checksetting.service.ICheckSettingService;

/**
 * 查验员备案Controller
 * 
 * @author hwf
 * @version 1.0
 */
@Controller
@RequestMapping("module/record")
public class RecordController extends BaseController {

	// 注入查验员备案Service
	@Autowired
	IViewerFileService viewerFileService;
	// 注入查验项设置Service
	@Autowired
	ICheckSettingService checkSettingService;
	// 注入字典表Service
	@Autowired
	IDictEntityService dictEntityService;
	// 注入查验区备案Service
	@Autowired
	IChkptFileService chkptFileService;
	@Autowired
	IVehicleInfoService vehicleInfoService;
	// 注入操作日志
	@Autowired
	ILogService logService;
	@Autowired
	IBaseService baseService;
	// 获取日志对象
	Logger log = Logger.getLogger(RecordController.class);

	/**
	 * 查验员备案页面初始化
	 * 
	 * @return 跳转到查验员备案
	 */
	@RequestMapping("cyyba")
	public String loadCyyba() {
		return "com/vkl/ckts_pc/rgsy/cyyba";
	}

	/**
	 * 查验员备案申请初始化
	 * 
	 * @return 跳转到查验员备案申请
	 */
	@RequestMapping("cyybasq")
	public String loadCyybasq(HttpServletRequest request) {
		try {
			// 获取车辆使用性质List
			List<CehUsnrEntity> cehUsnrList = checkSettingService.allUsering();
			// 获取业务类型List
			List<OperTypeEntity> operTypeList = checkSettingService.allOperType();
			// 获取车辆类型集合
			List<CkCllxEntity> ckCllxList = checkSettingService.allCKCllx();
			request.setAttribute("operTypeList", operTypeList);
			request.setAttribute("cehUsnrList", cehUsnrList);
			request.setAttribute("ckCllxList", ckCllxList);
			
			return "com/vkl/ckts_pc/rgsy/cyybasq";
		} catch (Exception e) {
			log.error("e");
			e.printStackTrace();

			return "com/vkl/ckts_pc/rgsy/cyybasq";
		}
	}
	

	/**
	 * 加载查验员
	 * 
	 * @return 将json返回至页面
	 */
	@ResponseBody
	@RequestMapping(value = "loadViewer", produces = "application/json;charset=UTF-8")
	public String loadViewer (ViewerFileDto viewerFileEntity) {
		try{
			// 获取查验员List
			List<ViewerFileDto> viewerList = viewerFileService.findViewerFileList(viewerFileEntity);
			JSONArray jsonList = new JSONArray();
			// 遍历查验员集合，插入到json集合中
			for (ViewerFileEntity vfe : viewerList) {
				vfe.setViewName(Base64Utils.decode(vfe.getViewName()));
				jsonList.add(vfe);
			}
			
			return jsonList.toJSONString();
		}catch (Exception e){
			log.error(e);
			e.printStackTrace();
			
			return null;
		}
	}


	/**
	 * 备案员申请提交
	 * 
	 * @param viewerFileEntity
	 * @param request
	 * @return true:备案申请成功；false：备案申请失败
	 */
	@ResponseBody
	@RequestMapping(value = "submit", produces = "application/json;charset=UTF-8")
	public String submit(ViewerFileDto viewerFileEntity, HttpServletRequest request) {
		try{
			// 插入操作日志
			logService.insertLog("查验员备案申请", "查验员备案", "提交查验员备案申请", request);
			// 调用添加查验员备案方法，返回true备案申请成功，返回false备案申请失败
			if (viewerFileService.addViewerFile(viewerFileEntity, request)) {
				log.info("备案申请成功");

				return "true";
			} else {
				log.info("备案申请失败");

				return "false";
			}
		}catch (Exception e){
			log.error(e);
			e.printStackTrace();
			
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/validateloginname", produces = "application/text;charset=UTF-8")
	public String validateLoginName(UserEntity userEntity){
		try {
			if (baseService.findUserByLoginName(userEntity) != null) {
				// 从新生成用户名
				userEntity.setLoginName(RandomUtils.getStringRandom(1, 0, 5));
				return "false";
			}else {
				return "true";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
		
	}

	/**
	 * 分页查找查验员备案信息
	 * 
	 * @param pageSize 每页显示的数据个数
	 * @param viewerFileEntity
	 * @return map
	 */
	@ResponseBody
	@RequestMapping(value = "/pageFindViewerFile", produces = "application/json;charset=UTF-8")
	public Map<String, Object> pageFindViewerFile(Integer pageSize, Integer pageNumber, ViewerFileDto viewerFileEntity,
			Page<ViewerFileDto> page, ChkptFileDto chkptFileEntity,HttpServletRequest request) {
		
		try{
			HttpSession session = request.getSession();
			UserEntity user = (UserEntity) session
					.getAttribute(Constrant.S_USER_SESSION);
			// 设置用户机构
			// 查找部门信息
			DeptEntity deptEntity = vehicleInfoService.findDeptById(user
					.getUserDept().toString());
			if (deptEntity == null) {
				return null;
			}
			if (!"1".equals(deptEntity.getDeptType())) {
				// 当前不为为市车管所
				viewerFileEntity.setOrgan(deptEntity.getFileId());
			}
			Map<String, Object> map = new HashMap<String, Object>();
			// 查验员姓名解密
			viewerFileEntity.setViewName((Base64Utils.encode(viewerFileEntity.getViewName())));
			// 设置分页的实体
			page.setT(viewerFileEntity);
			// 设置分页的当前页号
			page.setPageNo(pageNumber);
			// 设置分页的页面大小
			page.setPageSize(pageSize);
			// 分页
			page = viewerFileService.pageViewerFile(page);
			// 遍历分页结果
			for (ViewerFileEntity vfe : page.getList()) {
				// 将实体中字段为key的装换成对应的值
				vfe.setViewerRank(dictEntityService.findInfoByKeyAndType(vfe.getViewerRank(), Constrant.VIEWER_RANK).getLdictionaryAbel());
				// 忽略大小写
				if (IdEntity.S_YES.equalsIgnoreCase(vfe.getIsPolice())) {
					vfe.setIsPolice("是");
				} else {
					vfe.setIsPolice("否");
				}
				// 查验员姓名解密
				vfe.setViewName((Base64Utils.decode(vfe.getViewName())));
				// 身份证解密
				vfe.setIdentity((Base64Utils.decode(vfe.getIdentity())));
				chkptFileEntity.setOrganCode(vfe.getOrgan());
				ChkptFileEntity chkptFileEntity2=chkptFileService.findChkptFileById(chkptFileEntity);
				if (chkptFileEntity2!=null) {
					vfe.setOrgan(chkptFileEntity2.getOrganName());
				}else {
					vfe.setOrgan("");
				}
				
				vfe.setFileStatu(dictEntityService.findInfoByKeyAndType(vfe.getFileStatu(), Constrant.FILE_STATU).getLdictionaryAbel());
				vfe.setLocalStatu(dictEntityService.findInfoByKeyAndType(vfe.getLocalStatu(), Constrant.LOCAL_STATU).getLdictionaryAbel());
			}
			// 分页后的数据
			map.put("list", page.getList());
			// 总记录数
			map.put("total", viewerFileService.findCount(viewerFileEntity));
			return map;
		}catch (Exception e){
			log.error(e);
			e.printStackTrace();
			
			return null;
		}
		
	}

	/**
	 * 删除备案信息
	 * 
	 * @param pageSize 页面大小
	 * @param viewerFileEntity 查验员备案实体
	 * @return map
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteViewerFile", produces = "application/json;charset=UTF-8")
	public Map<String, Object> deleteViewerFile(Integer pageSize, Integer pageNumber, ViewerFileDto viewerFileEntity,
			Page<ViewerFileDto> page,HttpServletRequest request,ChkptFileDto chkptFileEntity) {
		
		try{
			// 插入操作日志
			logService.insertLog("查验员备案申请删除", "查验员备案", "查验员备案申请信息删除", request);
			// 查验员姓名解密
			viewerFileEntity.setViewName((Base64Utils.decode(viewerFileEntity.getViewName())));
			viewerFileService.deleteViewerFileById(viewerFileEntity);
			Integer total=viewerFileService.findCount(viewerFileEntity);
			// 如果数据总数除以页面大小余数为0说明删除的是当前页面的最后一条数据，删除后当前页需要减一
			if(total%pageSize==0){
				pageNumber=pageNumber-1;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			HttpSession session = request.getSession();
			UserEntity user = (UserEntity) session
					.getAttribute(Constrant.S_USER_SESSION);
			// 设置用户机构
			// 查找部门信息
			DeptEntity deptEntity = vehicleInfoService.findDeptById(user
					.getUserDept().toString());
			if (deptEntity == null) {
				return null;
			}
			if (!"1".equals(deptEntity.getDeptType())) {
				// 当前不为为市车管所
				viewerFileEntity.setOrgan(deptEntity.getFileId());
			}
			// 设置分页的实体
			page.setT(viewerFileEntity);
			// 设置分页的当前页号
			page.setPageNo(pageNumber);
			// 设置分页的页面大小
			page.setPageSize(pageSize);
			// 分页
			page = viewerFileService.pageViewerFile(page);
			// 遍历分页结果
			for (ViewerFileEntity vfe : page.getList()) {
				// 将实体中字段为key的装换成对应的值
				vfe.setViewerRank(dictEntityService.findInfoByKeyAndType(vfe.getViewerRank(), Constrant.VIEWER_RANK).getLdictionaryAbel());
				if (IdEntity.S_YES.equalsIgnoreCase(vfe.getIsPolice())) {
					vfe.setIsPolice("是");
				} else {
					vfe.setIsPolice("否");
				}
				// 查验员姓名解密
				vfe.setViewName((Base64Utils.decode(vfe.getViewName())));
				// 身份证解密
				vfe.setIdentity((Base64Utils.decode(vfe.getIdentity())));
				chkptFileEntity.setOrganCode(vfe.getOrgan());
				ChkptFileEntity chkptFileEntity2=chkptFileService.findChkptFileById(chkptFileEntity);
				if (chkptFileEntity2!=null) {
					vfe.setOrgan(chkptFileEntity2.getOrganName());
				}else {
					vfe.setOrgan("");
				}
				
//				vfe.setOrgan(chkptFileService.findChkptFileById(chkptFileEntity).getOrganName());
				vfe.setFileStatu(dictEntityService.findInfoByKeyAndType(vfe.getFileStatu(), Constrant.FILE_STATU).getLdictionaryAbel());
				vfe.setLocalStatu(dictEntityService.findInfoByKeyAndType(vfe.getLocalStatu(), Constrant.LOCAL_STATU).getLdictionaryAbel());
			}
			// 分页后的数据
			map.put("list", page.getList());
			// 总记录数
			map.put("total", viewerFileService.findCount(viewerFileEntity));
			
			return map;
		}catch (Exception e){
			log.error(e);
			e.printStackTrace();
			
			return null;
		}
		
	}

	/**
	 * 根据查验员编号查找备案信息
	 * 
	 * @param viewerFileEntity
	 * @param request
	 * @return 跳转到查验员备案修改页面
	 */
	@RequestMapping("findViewerFileById")
	public String findViewerFileById(ViewerFileDto viewerFileEntity, HttpServletRequest request) {
		try{
			// 获取车辆使用性质List
			List<CehUsnrEntity> cehUsnrList = checkSettingService.allUsering();
			// 获取业务类型List
			List<OperTypeEntity> operTypeList = checkSettingService.allOperType();
			// 获取车辆类型集合
			List<CkCllxEntity> ckCllxList = checkSettingService.allCKCllx();
			request.setAttribute("operTypeList", operTypeList);
			request.setAttribute("cehUsnrList", cehUsnrList);
			request.setAttribute("ckCllxList", ckCllxList);
			// 根据查验员编号查找备案信息
			viewerFileEntity = viewerFileService.findViewerFileById(viewerFileEntity);
			// 查验员姓名解密
			viewerFileEntity.setViewName((Base64Utils.decode(viewerFileEntity.getViewName())));
			// 身份证解密
			viewerFileEntity.setIdentity((Base64Utils.decode(viewerFileEntity.getIdentity())));
			// 将查找的备案信息实体存入request
			request.setAttribute("vfe", viewerFileEntity);
			// 从字典表里查找是否是警员状态集合存入request
			request.setAttribute("isPolice", dictEntityService.findInfoByType(Constrant.IS_POLICE));
			// 从字典表里查找查验员等级集合存入request
			request.setAttribute("viewerRank", dictEntityService.findInfoByType(Constrant.VIEWER_RANK));
			// 从字典表里查找备案状态集合存入request
			request.setAttribute("fileStatu", dictEntityService.findInfoByType(Constrant.FILE_STATU));
			// 从字典表里查找使用性质集合存入request
			request.setAttribute("usnrs", dictEntityService.findInfoByType(Constrant.USNRS));
			// 从字典表里查找本地状态集合存入request
			request.setAttribute("localStatu", dictEntityService.findInfoByType(Constrant.LOCAL_STATU));
			
			return "com/vkl/ckts_pc/rgsy/cyybaxg";
		}catch (Exception e){
			log.error(e);
			e.printStackTrace();
			
			return null;
		}
		
	}

	/**
	 * 更新备案信息
	 * 
	 * @param viewerFileEntity
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateViewerFile", produces = "application/json;charset=UTF-8")
	public String updateViewerFile(ViewerFileDto viewerFileEntity, HttpServletRequest request) {
		try{
			// 插入操作日志
			logService.insertLog("查验员备案申请修改", "查验员备案", "查验员备案申请信息修改", request);
			// 根据申请id查找备案信息
			ViewerFileDto vfe = viewerFileService.findViewerFileById(viewerFileEntity);
			// 修改查验员姓名
			vfe.setViewName(viewerFileEntity.getViewName());
			// 修改警员编号
			vfe.setPoliceId(viewerFileEntity.getPoliceId());
			// 修改是否是警员
			vfe.setIsPolice(viewerFileEntity.getIsPolice());
			// 修改身份证号码
			vfe.setIdentity(viewerFileEntity.getIdentity());
			// 修改资格证发放单位
			vfe.setValidIntrName(viewerFileEntity.getValidIntrName());
			// 修改资格证编号
			vfe.setValidId(viewerFileEntity.getValidId());
			// 修改查验员等级
			vfe.setViewerRank(viewerFileEntity.getViewerRank());
			// 修改资格证有效期止
			vfe.setValidEndTime(viewerFileEntity.getValidEndTime());
			// 修改备案状态
			vfe.setFileStatu(viewerFileEntity.getFileStatu());
			// 修改所属查验区
			vfe.setOrgan(viewerFileEntity.getOrgan());
			// 修改使用性质
			vfe.setUsnrs(viewerFileEntity.getUsnrs());
			// 修改查验员本地状态
			vfe.setLocalStatu(viewerFileEntity.getLocalStatu());
			// 修改业务类型
			vfe.setOperTypes(viewerFileEntity.getOperTypes());
			// 修改可查车型
			vfe.setcCllxs(viewerFileEntity.getcCllxs());
			vfe.setPermissionFlag(viewerFileEntity.getPermissionFlag());
			// 修改备案信息，成功返回true 失败返回false
			if (viewerFileService.updateViewerFile(vfe, request)){
				return "true";
			}else{
				return "false";
			}
		}catch (Exception e){
			log.error(e);
			e.printStackTrace();
			
			return null;
		}
		
	}
}
