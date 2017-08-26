package com.vkl.ckts.rgsy.record.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vkl.ckts.common.base.Message;
import com.vkl.ckts.common.constr.ErrorInfo;
import com.vkl.ckts.common.entity.CehUsnrEntity;
import com.vkl.ckts.common.entity.CkCllxEntity;
import com.vkl.ckts.common.entity.DictEntity;
import com.vkl.ckts.common.entity.VisitCkFileEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IDictEntityService;
import com.vkl.ckts.rgsy.record.service.IChkptFileService;
import com.vkl.ckts.rgsy.record.service.IViewerFileService;
import com.vkl.ckts.rgsy.record.service.IVisitCkFileService;
import com.vkl.ckts.rgsy.statistic.log.service.ILogService;
import com.vkl.ckts.rgsy.system.checksetting.service.ICheckSettingService;

/**
 * 上门查验备案Controller
 *
 * @author hwf
 * @version 1.0
 */
@Controller
@RequestMapping("module/visitCk")
public class VisitCkController {
	// 注入字典表Service
	@Autowired
	IDictEntityService dictEntityService;
	// 上门查验备案Service
	@Autowired
	IVisitCkFileService visitCkFileService;
	// 查验员备案Service
	@Autowired
	IViewerFileService viewerFileService;
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
	Logger log = Logger.getLogger(VisitCkController.class);
	
	/**
	 * 上门查验备案页面初始化
	 *
	 * @return 上门查验备案页面路径
	 */
	@RequestMapping("smcyba")
	public String smcyba(){
		return "com/vkl/ckts_pc/rgsy/smcyba";
	}
	
	/**
	 * 上门查验备案申请页面初始化
	 *
	 * @return 上门查验备案申请页面路径
	 */
	@RequestMapping("smcybasq")
	public String smcybasq(HttpServletRequest request){
		try {
			// 获取车辆使用性质List
			List<CehUsnrEntity> cehUsnrList = checkSettingService.allUsering();
			// 获取车辆类型集合
			List<CkCllxEntity> ckCllxList = checkSettingService.allCKCllx();
			request.setAttribute("cehUsnrList", cehUsnrList);
			request.setAttribute("ckCllxList", ckCllxList);
			
			return "com/vkl/ckts_pc/rgsy/smcybasq";
		} catch (Exception e) {
			log.error("e");
			e.printStackTrace();

			return "com/vkl/ckts_pc/rgsy/smcybasq";
		}
		
	}


	
	/**
	 * 提交上门查验备案申请
	 *
	 * @param visitCkFileEntity
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "submit", produces = "application/json;charset=UTF-8")
	public Message<String> submit(VisitCkFileEntity visitCkFileEntity, HttpServletRequest request) {
		Message<String> message = new Message<String>();
		try {
			// 插入操作日志
			logService.insertLog("上门查验备案申请", "上门查验备案", "提交上门查验备案申请", request);	
			// 添加上门查验备案，true：备案申请成功，false：备案失败
			if (visitCkFileService.addVisitCkFile(visitCkFileEntity, request)) {
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
	 * 分页查找上门查验备案信息
	 * 
	 * @param pageSize
	 *            每页显示的数据个数
	 * @param visitCkFileEntity
	 * @return map
	 */
	@ResponseBody
	@RequestMapping(value = "pageFindVisitCkFile", produces = "application/json;charset=UTF-8")
	public Map<String, Object> pageFindVisitCkFile(Integer pageSize, Integer pageNumber, VisitCkFileEntity visitCkFileEntity,
			Page<VisitCkFileEntity> page) {
//
//		try {
//			Map<String, Object> map = new HashMap<String, Object>();
//			// 设置分页的实体
//			page.setT(visitCkFileEntity);
//			// 设置分页的当前页号
//			page.setPageNo(pageNumber);
//			// 设置分页的页面大小
//			page.setPageSize(pageSize);
//			// 分页
//			page = visitCkFileService.pageVisitCkFile(page);
//			// 遍历分页结果
//			for (VisitCkFileEntity visitCk : page.getList()) {
//				if (visitCk.getFileId() == null) {
//					visitCk.setFileId("");
//				}
//				// 将实体中字段为key的转换成对应的值
//				visitCk.setFileStatu(dictEntityService.findInfoByKeyAndType(visitCk.getFileStatu(), Constrant.FILE_STATU).getLdictionaryAbel());
//				StringBuffer ckFileName = new StringBuffer();
//				String ckFileNames[] = visitCk.getCkFileId().split(",");
//				for (int i = 0; i < ckFileNames.length; i++) {
//					ViewerFileEntity viewerFileEntity = new ViewerFileEntity();
//					viewerFileEntity.setFileId(ckFileNames[i]);
//					if (i == 0) {
//						ckFileName.append(Base64Utils.decode(viewerFileService.findViewerFileByFileId(viewerFileEntity).getViewName()));
//					} else {
//						ckFileName.append("," + Base64Utils.decode(viewerFileService.findViewerFileByFileId(viewerFileEntity).getViewName()));
//					}
//				}
//				visitCk.setCkFileId(ckFileName.toString());
//			}
//			// 分页后的数据
//			map.put("list", page.getList());
//			// 总记录数
//			map.put("total", visitCkFileService.findCount(visitCkFileEntity));
//
//			return map;
//		} catch (Exception e) {
//			log.error(e);
//			e.printStackTrace();
//
			return null;
//		}

	}
	
	
	/**
	 * 删除备案信息
	 * 
	 * @param pageSize 页面大小
	 * @param visitCkFileEntity 上门查验备案实体
	 * @return map
	 */
	@ResponseBody
	@RequestMapping(value = "deleteVisitCkFile", produces = "application/json;charset=UTF-8")
	public Map<String, Object> deleteVisitCkFile(Integer pageSize, Integer pageNumber, VisitCkFileEntity visitCkFileEntity,
			Page<VisitCkFileEntity> page,HttpServletRequest request) {
//		
//		try{
//			// 插入操作日志
//			logService.insertLog("删除上门查验备案", "上门查验备案", "删除上门查验备案信息", request);
//			visitCkFileService.deleteVisitCkFileById(visitCkFileEntity);
//			Integer total=visitCkFileService.findCount(visitCkFileEntity);
//			// 如果数据总数除以页面大小余数为0说明删除的是当前页面的最后一条数据，删除后当前页需要减一
//			if(total%pageSize==0){
//				pageNumber=pageNumber-1;
//			}
//			Map<String, Object> map = new HashMap<String, Object>();
//			// 设置分页的实体
//			page.setT(visitCkFileEntity);
//			// 设置分页的当前页号
//			page.setPageNo(pageNumber);
//			// 设置分页的页面大小
//			page.setPageSize(pageSize);
//			// 分页
//			page = visitCkFileService.pageVisitCkFile(page);
//			// 遍历分页结果
//			for (VisitCkFileEntity visitCk : page.getList()) {
//				if (visitCk.getFileId() == null) {
//					visitCk.setFileId("");
//				}
//				// 将实体中字段为key的转换成对应的值
//				visitCk.setFileStatu(dictEntityService.findInfoByKeyAndType(visitCk.getFileStatu(), Constrant.FILE_STATU).getLdictionaryAbel());
//				StringBuffer ckFileName = new StringBuffer();
//				String ckFileNames[] = visitCk.getCkFileId().split(",");
//				for (int i = 0; i < ckFileNames.length; i++) {
//					ViewerFileEntity viewerFileEntity = new ViewerFileEntity();
//					viewerFileEntity.setFileId(ckFileNames[i]);
//					if (i == 0) {
//						ckFileName.append(Base64Utils.decode(viewerFileService.findViewerFileByFileId(viewerFileEntity).getViewName()));
//					} else {
//						ckFileName.append("," + Base64Utils.decode(viewerFileService.findViewerFileByFileId(viewerFileEntity).getViewName()));
//					}
//				}
//				visitCk.setCkFileId(ckFileName.toString());
//			}
//			// 分页后的数据
//			map.put("list", page.getList());
//			// 总记录数
//			map.put("total", total);
//			
//			return map;
//		}catch (Exception e){
//			log.error(e);
//			e.printStackTrace();
//			
			return null;
//		}
		
	}
	
	/**
	 * 根据上门查验编号查找备案信息
	 * 
	 * @param visitCkFileEntity
	 * @param request
	 * @return 跳转到上门查验备案修改页面
	 */
	@RequestMapping("findVisitCkFileById")
	public String findVisitCkFileById(VisitCkFileEntity visitCkFileEntity, HttpServletRequest request) {
		try{
			// 根据查验区编号查找备案信息
			visitCkFileEntity = visitCkFileService.findVisitCkFileById(visitCkFileEntity);
			// 获取车辆使用性质List
			List<CehUsnrEntity> cehUsnrList = checkSettingService.allUsering();
			request.setAttribute("cehUsnrList", cehUsnrList);
			// 获取车辆类型集合
			List<CkCllxEntity> ckCllxList = checkSettingService.allCKCllx();
			request.setAttribute("ckCllxList", ckCllxList);
			// 将查找的备案信息实体存入request
			request.setAttribute("visitCk", visitCkFileEntity);
			// 从字典表里查找备案状态集合存入request
			List<DictEntity> fileStatu= new ArrayList<DictEntity>();
			fileStatu.add(dictEntityService.findInfoByKeyAndType("1", "fileStatu"));
			fileStatu.add(dictEntityService.findInfoByKeyAndType("2", "fileStatu"));
			request.setAttribute("fileStatu", fileStatu);
			
			return "com/vkl/ckts_pc/rgsy/smcybaxg";
		}catch (Exception e){
			log.error(e);
			e.printStackTrace();
			
			return null;
		}
		
	}
	
	/**
	 * 更新备案信息
	 * 
	 * @param visitCkFileEntity
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateVisitCkFile", produces = "application/json;charset=UTF-8")
	public String updateVisitCkFile(VisitCkFileEntity visitCkFileEntity, HttpServletRequest request) {
		try{
			// 插入操作日志
			logService.insertLog("修改上门查验备案", "上门查验备案", "修改上门查验备案信息", request);
			// 根据上门查验编号查找备案信息
			VisitCkFileEntity visitCk = visitCkFileService.findVisitCkFileById(visitCkFileEntity);
			// 修改上门查验部门名称
			visitCk.setCkDeptName(visitCkFileEntity.getCkDeptName());
			// 修改上门查验部门编号
			visitCk.setCkDeptId(visitCkFileEntity.getCkDeptId());
			// 修改上门查验管理部门
			visitCk.setAdminIntrName(visitCkFileEntity.getAdminIntrName());
			// 修改查验员编号
			visitCk.setCkFileId(visitCkFileEntity.getCkFileId());
			// 上门查验服务单位名称
			visitCk.setSerFileName(visitCkFileEntity.getSerFileName());
			// 修改备案状态
			visitCk.setFileStatu(visitCkFileEntity.getFileStatu());
			// 使用性质
			visitCk.setUsnr(visitCkFileEntity.getUsnr());
			// 可查验车辆类型
			visitCk.setCkCllxs(visitCkFileEntity.getCkCllxs());
			// 修改备案信息，成功返回true 失败返回false
			if (visitCkFileService.updateVisitCkFile(visitCk, request)){
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
