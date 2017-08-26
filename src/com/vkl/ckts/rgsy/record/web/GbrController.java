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

import com.vkl.ckts.common.base.Message;
import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.constr.ErrorInfo;
import com.vkl.ckts.common.entity.CkCllxEntity;
import com.vkl.ckts.common.entity.GbrFileEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IDictEntityService;

import com.vkl.ckts.rgsy.record.service.IGbrFileService;
import com.vkl.ckts.rgsy.statistic.log.service.ILogService;
import com.vkl.ckts.rgsy.system.checksetting.service.ICheckSettingService;


/**
 * 外廓设备备案Controller
 *
 * @author hwf
 * @version 1.0
 */
@Controller
@RequestMapping("module/gbr")
public class GbrController {
	// 注入字典表Service
	@Autowired
	IDictEntityService dictEntityService;
	// 注入操作日志
	@Autowired
	ILogService logService;	
	// 注入查验项设置Service
	@Autowired
	ICheckSettingService checkSettingService;
	// 注入gbr设置Service
	@Autowired
	IGbrFileService gbrFileService;
	
	// 获取日志对象
	Logger log = Logger.getLogger(GbrController.class);
	
	/**
	 * gbr备案页面初始化
	 *
	 * @return gbr备案页面路径
	 */
	@RequestMapping("gbrba")
	public String gbrba() {
		return "com/vkl/ckts_pc/rgsy/gbrba";
	}
	
	/**
	 * gbr备案申请页面初始化
	 *
	 * @return gbr备案申请页面路径
	 */
	@RequestMapping("gbrbasq")
	public String gbrbasq(HttpServletRequest request){
		// 获取车辆类型集合
		List<CkCllxEntity> ckCllxList = checkSettingService.allCKCllx();
		request.setAttribute("ckCllxList", ckCllxList);
		return "com/vkl/ckts_pc/rgsy/gbrbasq";
	}
	
	/**
	 * 提交外廓备案申请
	 *
	 * @param gbrFileEntity
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "submit", produces = "application/json;charset=UTF-8")
	public Message<String> submit(GbrFileEntity gbrFileEntity, HttpServletRequest request) {
		Message<String> message = new Message<String>();
		try {
			// 插入操作日志
			logService.insertLog("外廓备案申请", "外廓备案", "提交外廓备案申请", request);	
			// 添加外廓备案，true：备案申请成功，false：备案失败
			if (gbrFileService.addGbrFile(gbrFileEntity, request)) {
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
	 * 分页查找外廓备案信息
	 * 
	 * @param pageSize
	 *            每页显示的数据个数
	 * @param gbrFileEntity
	 * @return map
	 */
	@ResponseBody
	@RequestMapping(value = "pageFindGbrFile", produces = "application/json;charset=UTF-8")
	public Map<String, Object> pageFindGbrFile(Integer pageSize, Integer pageNumber, GbrFileEntity gbrFileEntity,
			Page<GbrFileEntity> page) {

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			// 设置分页的实体
			page.setT(gbrFileEntity);
			// 设置分页的当前页号
			page.setPageNo(pageNumber);
			// 设置分页的页面大小
			page.setPageSize(pageSize);
			// 分页
			page = gbrFileService.pageGbrFile(page);
			// 遍历分页结果
			for (GbrFileEntity gbr : page.getList()) {
				// 将实体中字段为key的转换成对应的值
				gbr.setFileStatu(dictEntityService.findInfoByKeyAndType(gbr.getFileStatu(), Constrant.FILE_STATU).getLdictionaryAbel());
				gbr.setLocalStatu(dictEntityService.findInfoByKeyAndType(gbr.getLocalStatu(), Constrant.LOCAL_STATU).getLdictionaryAbel());
			}
			// 分页后的数据
			map.put("list", page.getList());
			// 总记录数
			map.put("total", gbrFileService.findCount(gbrFileEntity));

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
	 * @param GbrFileEntity gbr备案实体
	 * @return map
	 */
	@ResponseBody
	@RequestMapping(value = "deleteGbrFile", produces = "application/json;charset=UTF-8")
	public Map<String, Object> deleteGbrFile(Integer pageSize, Integer pageNumber, GbrFileEntity gbrFileEntity,
			Page<GbrFileEntity> page,HttpServletRequest request) {
		
		try{
			// 插入操作日志
			logService.insertLog("删除外廓备案", "外廓备案", "删除外廓备案信息", request);
			gbrFileService.deleteGbrFileById(gbrFileEntity);
			Integer total=gbrFileService.findCount(gbrFileEntity);
			// 如果数据总数除以页面大小余数为0说明删除的是当前页面的最后一条数据，删除后当前页需要减一
			if(total%pageSize==0){
				pageNumber=pageNumber-1;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			// 设置分页的实体
			page.setT(gbrFileEntity);
			// 设置分页的当前页号
			page.setPageNo(pageNumber);
			// 设置分页的页面大小
			page.setPageSize(pageSize);
			// 分页
			page = gbrFileService.pageGbrFile(page);
			// 遍历分页结果
			for (GbrFileEntity gbr : page.getList()) {
				// 将实体中字段为key的转换成对应的值
				gbr.setFileStatu(dictEntityService.findInfoByKeyAndType(gbr.getFileStatu(), Constrant.FILE_STATU).getLdictionaryAbel());
				gbr.setLocalStatu(dictEntityService.findInfoByKeyAndType(gbr.getLocalStatu(), Constrant.LOCAL_STATU).getLdictionaryAbel());
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
	 * 根据外廓编号查找备案信息
	 * 
	 * @param gbrFileEntity
	 * @param request
	 * @return 跳转到pda备案修改页面
	 */
	@RequestMapping("findGbrFileById")
	public String findGbrFileById(GbrFileEntity gbrFileEntity, HttpServletRequest request) {
		try{
			// 根据查验区编号查找备案信息
			gbrFileEntity = gbrFileService.findGbrFileById(gbrFileEntity);
			// 获取车辆类型集合
			List<CkCllxEntity> ckCllxList = checkSettingService.allCKCllx();
			request.setAttribute("ckCllxList", ckCllxList);
			// 将查找的备案信息实体存入request
			request.setAttribute("gbr", gbrFileEntity);
			// 从字典表里查找备案状态集合存入request
			request.setAttribute("fileStatu", dictEntityService.findInfoByType(Constrant.FILE_STATU));
			// 从字典表里查找本地状态集合存入request
			request.setAttribute("localStatu", dictEntityService.findInfoByType(Constrant.LOCAL_STATU));
			
			return "com/vkl/ckts_pc/rgsy/gbrbaxg";
		}catch (Exception e){
			log.error(e);
			e.printStackTrace();
			
			return null;
		}
		
	}
	
	/**
	 * 更新备案信息
	 * 
	 * @param gbrFileEntity
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateGbrFile", produces = "application/json;charset=UTF-8")
	public String updateGbrFile(GbrFileEntity gbrFileEntity, HttpServletRequest request) {
		try{
			// 插入操作日志
			logService.insertLog("修改外廓备案", "外廓备案", "修改外廓备案信息", request);
			// 根据pda编号查找备案信息
			GbrFileEntity gbr = gbrFileService.findGbrFileById(gbrFileEntity);
			// 修改查验装置备案编号
			gbr.setFileId(gbrFileEntity.getFileId());
			// 修改测量范围（长）
			gbr.setMeaLg(gbrFileEntity.getMeaLg());
			// 修改查验装置生产企业
			gbr.setMeaFact(gbrFileEntity.getMeaFact());
			// 修改测量范围（宽）
			gbr.setMeaWg(gbrFileEntity.getMeaWg());
			// 修改查验装置型号
			gbr.setDeviceType(gbrFileEntity.getDeviceType());
			// 修改测量范围（高）
			gbr.setMeaHt(gbrFileEntity.getMeaHt());
			// 修改可查验车辆
			gbr.setCkVehs(gbrFileEntity.getCkVehs());
			// 修改查验区本地状态
			gbr.setLocalStatu(gbrFileEntity.getLocalStatu());
			// 修改备案状态
			gbr.setFileStatu(gbrFileEntity.getFileStatu());
			// 修改备案信息，成功返回true 失败返回false
			if (gbrFileService.updateGbrFile(gbr, request)){
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
