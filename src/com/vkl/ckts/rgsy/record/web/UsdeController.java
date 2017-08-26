package com.vkl.ckts.rgsy.record.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.vkl.ckts.common.base.Message;
import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.constr.ErrorInfo;
import com.vkl.ckts.common.entity.CehUsnrEntity;
import com.vkl.ckts.common.entity.CkCllxEntity;
import com.vkl.ckts.common.entity.CkProjectEntity;
import com.vkl.ckts.common.entity.OperTypeEntity;
import com.vkl.ckts.common.entity.UsdeProFileEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IDictEntityService;
import com.vkl.ckts.rgsy.record.service.IChkptFileService;
import com.vkl.ckts.rgsy.record.service.IUsdeFileService;
import com.vkl.ckts.rgsy.statistic.log.service.ILogService;
import com.vkl.ckts.rgsy.system.checksetting.service.ICheckSettingService;

/**
 * 自定义查验项备案Controller
 *
 * @author hwf
 * @version 1.0
 */
@Controller
@RequestMapping("module/usde")
public class UsdeController {
	// 注入字典表Service
	@Autowired
	IDictEntityService dictEntityService;
	// 自定义查验项备案Service
	@Autowired
	IUsdeFileService usdeFileService;
	// 注入查验项设置Service
	@Autowired
	ICheckSettingService checkSettingService;
	// 查验区备案Service
	@Autowired
	IChkptFileService chkptFileService;
	// 注入操作日志
	@Autowired
	ILogService logService;
	// 获取日志对象
	Logger log = Logger.getLogger(PdaController.class);
	
	/**
	 * 自定义查验项页面初始化
	 *
	 * @return 自定义查验项备案页面路径
	 */
	@RequestMapping("cyxba")
	public String cyxba(){
		return "com/vkl/ckts_pc/rgsy/cyxba";
	}
	
	/**
	 * 自定义查验项申请页面初始化
	 *
	 * @return 自定义查验项备案申请页面路径
	 */
	@RequestMapping("cyxbasq")
	public String cyxbasq(HttpServletRequest request){
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
			
			return "com/vkl/ckts_pc/rgsy/cyxbasq";
		} catch (Exception e) {
			log.error("e");
			e.printStackTrace();

			return "com/vkl/ckts_pc/rgsy/cyxbasq";
		}
	}
	
	/**
	 * 加载查验项目
	 * 
	 * @return 将json返回至页面
	 */
	@ResponseBody
	@RequestMapping(value = "loadCkProject", produces = "application/json;charset=UTF-8")
	public String loadCkProject (CkProjectEntity ckProjectEntity) {
		try{
			
			// 获取查验项目List
			List<CkProjectEntity> ckProjectList = checkSettingService.allCkProject(ckProjectEntity);
			JSONArray jsonList = new JSONArray();
			// 遍历业务类型集合，插入到json集合中
			for (CkProjectEntity cpj : ckProjectList) {
				jsonList.add(cpj);
			}
			return jsonList.toJSONString();
		}catch (Exception e){
			log.error(e);
			e.printStackTrace();
			
			return null;
		}
	}
	
	/**
	 * 修改查验项目时加载的查验项目
	 * 
	 * @return 将json返回至页面
	 */
	@ResponseBody
	@RequestMapping(value = "loadUpdateCkProject", produces = "application/json;charset=UTF-8")
	public String loadUpdateCkProject (CkProjectEntity ckProjectEntity) {
		try{
			
			
			// 查找当前修改的查验项目
			CkProjectEntity ckProject= new CkProjectEntity();
			ckProject.setId(ckProjectEntity.getId());
			List<CkProjectEntity> ckProjectList2 = checkSettingService.allCkProject(ckProject);
			// 获取查验项目List
			ckProjectEntity.setId("");
			List<CkProjectEntity> ckProjectList = checkSettingService.allCkProject(ckProjectEntity);
			JSONArray jsonList = new JSONArray();
			jsonList.add(ckProjectList2.get(0));
			// 遍历业务类型集合，插入到json集合中
			for (CkProjectEntity cpj : ckProjectList) {
				jsonList.add(cpj);
			}
			return jsonList.toJSONString();
		}catch (Exception e){
			log.error(e);
			e.printStackTrace();
			
			return null;
		}
	}
	
	/**
	 * 提交自定义查验项申备案申请
	 *
	 * @param usdeProFileEntity
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "submit", produces = "application/json;charset=UTF-8")
	public Message<String> submit(UsdeProFileEntity usdeProFileEntity, HttpServletRequest request) {
		Message<String> message = new Message<String>();
		try {
			// 插入操作日志
			logService.insertLog("查验项备案申请", "查验项备案", "提交自定义查验项备案申请", request);	
			// 添加自定义查验项备案，true：备案申请成功，false：备案失败
			if (usdeFileService.addUsdeFile(usdeProFileEntity, request)) {
				// 设置返回的信息
				message.setData("true");
			} else {
				message.setData("false");
				message.setErrorCode(ErrorInfo.S_SYS_EXCEPTION);
			}

			return message;
		} catch (Exception e) {
			log.error(e);
			message.setErrorCode(ErrorInfo.S_SQL_EXCEPTION);
			e.printStackTrace();

			return message;
		}
	}
	
	
	/**
	 * 分页查找自定义查验项申备案信息
	 * 
	 * @param pageSize
	 *            每页显示的数据个数
	 * @param usdeProFileEntity
	 * @return map
	 */
	@ResponseBody
	@RequestMapping(value = "pageFindUsdeFile", produces = "application/json;charset=UTF-8")
	public Map<String, Object> pageFindUsdeFile(Integer pageSize, Integer pageNumber, UsdeProFileEntity usdeProFileEntity,
			Page<UsdeProFileEntity> page) {

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			// 设置分页的实体
			page.setT(usdeProFileEntity);
			// 设置分页的当前页号
			page.setPageNo(pageNumber);
			// 设置分页的页面大小
			page.setPageSize(pageSize);
			// 分页
			page = usdeFileService.pageUsdeFile(page);
			// 遍历分页结果
			for (UsdeProFileEntity usde : page.getList()) {
				// 如果备案编号为null 设为"";
				if (usde.getFileId() == null || usde.getFileId() == ""){
					usde.setFileId("");
				}
				// 将业务类型，车辆类型，使用性质转成对应的值
				String operTypes[] =usde.getOperType().split(",");
				StringBuffer ot= new StringBuffer();
				for(int i=0;i<operTypes.length;i++){
					if(i==0){
						ot.append(usdeFileService.findOperTypeById(operTypes[i]));
					}
					ot.append(","+usdeFileService.findOperTypeById(operTypes[i]));
				}
				usde.setOperType(ot.toString());
				String ckVehs[] =usde.getCkVehs().split(",");
				StringBuffer cv= new StringBuffer();
				for(int i=0;i<ckVehs.length;i++){
					if(i==0){
						cv.append(usdeFileService.findCkVehByParentId(ckVehs[i]));
					}
					cv.append(","+usdeFileService.findCkVehByParentId(ckVehs[i]));
				}
				usde.setCkVehs(cv.toString());
				String usnr[]=usde.getUsnr().split(",");
				StringBuffer ur= new StringBuffer();
				for(int i=0;i<usnr.length;i++){
					if(i==0){
						ur.append(usdeFileService.findUsnrByParentId(usnr[i]));
					}
					ur.append(","+usdeFileService.findUsnrByParentId(usnr[i]));
				}
				usde.setUsnr(ur.toString());
				// 将实体中字段为key的转换成对应的值
				usde.setFileStatu(dictEntityService.findInfoByKeyAndType(usde.getFileStatu(), Constrant.FILE_STATU).getLdictionaryAbel());
				usde.setLocalStatu(dictEntityService.findInfoByKeyAndType(usde.getLocalStatu(), Constrant.LOCAL_STATU).getLdictionaryAbel());
			}
			// 分页后的数据
			map.put("list", page.getList());
			// 总记录数
			map.put("total", usdeFileService.findCount(usdeProFileEntity));

			return map;
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();

			return null;
		}

	}
	
	
	/**
	 * 删除备案信息
	 * 
	 * @param pageSize 页面大小
	 * @param usdeProFileEntity 自定义查验项申备案实体
	 * @return map
	 */
	@ResponseBody
	@RequestMapping(value = "deleteUsdeFile", produces = "application/json;charset=UTF-8")
	public Map<String, Object> deleteUsdeFile(Integer pageSize, Integer pageNumber, UsdeProFileEntity usdeProFileEntity,
			Page<UsdeProFileEntity> page,HttpServletRequest request) {
		
		try{
			// 插入操作日志
			logService.insertLog("删除查验项备案申请", "查验项备案", "删除查验项备案信息", request);
			usdeFileService.deleteUsdeFileById(usdeProFileEntity);
			Integer total=usdeFileService.findCount(usdeProFileEntity);
			// 如果数据总数除以页面大小余数为0说明删除的是当前页面的最后一条数据，删除后当前页需要减一
			if(total%pageSize==0){
				pageNumber=pageNumber-1;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			// 设置分页的实体
			page.setT(usdeProFileEntity);
			// 设置分页的当前页号
			page.setPageNo(pageNumber);
			// 设置分页的页面大小
			page.setPageSize(pageSize);
			// 分页
			page = usdeFileService.pageUsdeFile(page);
			// 遍历分页结果
			for (UsdeProFileEntity usde : page.getList()) {
				// 如果备案编号为null 设为"";
				if (usde.getFileId() == null || usde.getFileId() == ""){
					usde.setFileId("");
				}
				// 将业务类型，车辆类型，使用性质转成对应的值
				String operTypes[] =usde.getOperType().split(",");
				StringBuffer ot= new StringBuffer();
				for(int i=0;i<operTypes.length;i++){
					if(i==0){
						ot.append(usdeFileService.findOperTypeById(operTypes[i]));
					}
					ot.append(","+usdeFileService.findOperTypeById(operTypes[i]));
				}
				usde.setOperType(ot.toString());
				String ckVehs[] =usde.getCkVehs().split(",");
				StringBuffer cv= new StringBuffer();
				for(int i=0;i<ckVehs.length;i++){
					if(i==0){
						cv.append(usdeFileService.findCkVehByParentId(ckVehs[i]));
					}
					cv.append(","+usdeFileService.findCkVehByParentId(ckVehs[i]));
				}
				usde.setCkVehs(cv.toString());
				String usnr[]=usde.getUsnr().split(",");
				StringBuffer ur= new StringBuffer();
				for(int i=0;i<usnr.length;i++){
					if(i==0){
						ur.append(usdeFileService.findUsnrByParentId(usnr[i]));
					}
					ur.append(","+usdeFileService.findUsnrByParentId(usnr[i]));
				}
				usde.setUsnr(ur.toString());
				// 将实体中字段为key的转换成对应的值
				usde.setFileStatu(dictEntityService.findInfoByKeyAndType(usde.getFileStatu(), Constrant.FILE_STATU).getLdictionaryAbel());
				usde.setLocalStatu(dictEntityService.findInfoByKeyAndType(usde.getLocalStatu(), Constrant.LOCAL_STATU).getLdictionaryAbel());
			}
			// 分页后的数据
			map.put("list", page.getList());
			// 总记录数
			map.put("total", total);
			
			return map;
		}catch (Exception e){
			log.error(e);
			e.printStackTrace();
			
			return null;
		}
		
	}
	
	/**
	 * 根据自定义查验项申编号查找备案信息
	 * 
	 * @param usdeProFileEntity
	 * @param request
	 * @return 跳转到自定义查验项申备案修改页面
	 */
	@RequestMapping("findUsdeFileById")
	public String findUsdeFileById(UsdeProFileEntity usdeProFileEntity, HttpServletRequest request) {
		try{
			// 根据查验区编号查找备案信息
			usdeProFileEntity = usdeFileService.findUsdeFileById(usdeProFileEntity);
			// 获取车辆使用性质List
			List<CehUsnrEntity> cehUsnrList = checkSettingService.allUsering();
			// 获取业务类型List
			List<OperTypeEntity> operTypeList = checkSettingService.allOperType();
			// 获取车辆类型集合
			List<CkCllxEntity> ckCllxList = checkSettingService.allCKCllx();
			request.setAttribute("operTypeList", operTypeList);
			request.setAttribute("cehUsnrList", cehUsnrList);
			request.setAttribute("ckCllxList", ckCllxList);
			// 将查找的备案信息实体存入request
			request.setAttribute("usde", usdeProFileEntity);
			// 从字典表里查找备案状态集合存入request
			request.setAttribute("fileStatu", dictEntityService.findInfoByType(Constrant.FILE_STATU));
			// 从字典表里查找本地状态集合存入request
			request.setAttribute("localStatu", dictEntityService.findInfoByType(Constrant.LOCAL_STATU));
			
			return "com/vkl/ckts_pc/rgsy/cyxbaxg";
		}catch (Exception e){
			log.error(e);
			e.printStackTrace();
			
			return null;
		}
		
	}
	
	/**
	 * 更新备案信息
	 * 
	 * @param usdeProFileEntity
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateUsdeFile", produces = "application/json;charset=UTF-8")
	public String updateUsdeFile(UsdeProFileEntity usdeProFileEntity, HttpServletRequest request, CkProjectEntity ckProjectEntity) {
		try{
			// 插入操作日志
			logService.insertLog("修改查验项备案申请", "查验项备案", "修改查验项备案信息申请", request);
			// 根据自定义查验项申编号查找备案信息
			UsdeProFileEntity usde = usdeFileService.findUsdeFileById(usdeProFileEntity);
			// 设置修改前的项目id
			ckProjectEntity.setId(usde.getProId());
			// 修改项目序号
			usde.setProId(usdeProFileEntity.getProId());
			// 修改项目名称
			usde.setFileProName(usdeProFileEntity.getFileProName());
			// 修改可办理业务类型
			usde.setOperType(usdeProFileEntity.getOperType());
			// 修改可查验车辆
			usde.setCkVehs(usdeProFileEntity.getCkVehs());
			// 修改查验区本地状态
			usde.setUsnr(usdeProFileEntity.getUsnr());
			// 修改查验区本地状态
			usde.setLocalStatu(usdeProFileEntity.getLocalStatu());
			// 修改备案状态
			usde.setFileStatu(usdeProFileEntity.getFileStatu());
			// 修改备案信息，成功返回true 失败返回false
			if (usdeFileService.updateUsdeFile(usde, request, ckProjectEntity)){
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
