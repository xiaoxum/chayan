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
import com.vkl.ckts.common.entity.ZbzlFileEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IDictEntityService;
import com.vkl.ckts.rgsy.record.service.IZbzlFileService;
import com.vkl.ckts.rgsy.statistic.log.service.ILogService;
import com.vkl.ckts.rgsy.system.checksetting.service.ICheckSettingService;

/**
 * 整备质量备案Controller
 *
 * @author hwf
 * @version 1.0
 */
@Controller
@RequestMapping("module/zbzl")
public class ZbzlController {
	// 注入字典表Service
	@Autowired
	IDictEntityService dictEntityService;
	// 注入操作日志
	@Autowired
	ILogService logService;	
	// 注入查验项设置Service
	@Autowired
	ICheckSettingService checkSettingService;
	// 注入整备质量设置Service
	@Autowired
	IZbzlFileService zbzlFileService;
	
	// 获取日志对象
	Logger log = Logger.getLogger(ZbzlController.class);
	
	/**
	 * 整备质量备案页面初始化
	 *
	 * @return 整备质量备案页面路径
	 */
	@RequestMapping("zbzlba")
	public String zbzlba() {
		return "com/vkl/ckts_pc/rgsy/zbzlba";
	}
	
	/**
	 * 整备质量备案申请页面初始化
	 *
	 * @return 整备质量备案申请页面路径
	 */
	@RequestMapping("zbzlbasq")
	public String zbzlbasq(HttpServletRequest request){
		// 获取车辆类型集合
		List<CkCllxEntity> ckCllxList = checkSettingService.allCKCllx();
		request.setAttribute("ckCllxList", ckCllxList);
		return "com/vkl/ckts_pc/rgsy/zbzlbasq";
	}
	
	/**
	 * 提交整备质量备案申请
	 *
	 * @param zbzlFileEntity
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "submit", produces = "application/json;charset=UTF-8")
	public Message<String> submit(ZbzlFileEntity zbzlFileEntity, HttpServletRequest request) {
		Message<String> message = new Message<String>();
		try {
			// 插入操作日志
			logService.insertLog("整备质量备案申请", "整备质量备案", "提交整备质量备案申请", request);	
			// 添加整备质量备案，true：备案申请成功，false：备案失败
			if (zbzlFileService.addZbzlFile(zbzlFileEntity, request)) {
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
	 * 分页查找外整备质量案信息
	 * 
	 * @param pageSize
	 *            每页显示的数据个数
	 * @param zbzlFileEntity
	 * @return map
	 */
	@ResponseBody
	@RequestMapping(value = "pageFindZbzlFile", produces = "application/json;charset=UTF-8")
	public Map<String, Object> pageFindZbzlFile(Integer pageSize, Integer pageNumber, ZbzlFileEntity zbzlFileEntity,
			Page<ZbzlFileEntity> page) {

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			// 设置分页的实体
			page.setT(zbzlFileEntity);
			// 设置分页的当前页号
			page.setPageNo(pageNumber);
			// 设置分页的页面大小
			page.setPageSize(pageSize);
			// 分页
			page = zbzlFileService.pageZbzlFile(page);
			// 遍历分页结果
			for (ZbzlFileEntity zbzl : page.getList()) {
				// 将实体中字段为key的转换成对应的值
				zbzl.setFileStatu(dictEntityService.findInfoByKeyAndType(zbzl.getFileStatu(), Constrant.FILE_STATU).getLdictionaryAbel());
				zbzl.setLocalStatu(dictEntityService.findInfoByKeyAndType(zbzl.getLocalStatu(), Constrant.LOCAL_STATU).getLdictionaryAbel());
			}
			// 分页后的数据
			map.put("list", page.getList());
			// 总记录数
			map.put("total", zbzlFileService.findCount(zbzlFileEntity));

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
	 * @param zbzlFileEntity 整备质量备案实体
	 * @return map
	 */
	@ResponseBody
	@RequestMapping(value = "deleteZbzlFile", produces = "application/json;charset=UTF-8")
	public Map<String, Object> deleteZbzlFile(Integer pageSize, Integer pageNumber, ZbzlFileEntity zbzlFileEntity,
			Page<ZbzlFileEntity> page,HttpServletRequest request) {
		
		try{
			// 插入操作日志
			logService.insertLog("删除整备质量备案", "整备质量备案", "删除整备质量备案信息", request);
			zbzlFileService.deleteZbzlFileById(zbzlFileEntity);
			Integer total=zbzlFileService.findCount(zbzlFileEntity);
			// 如果数据总数除以页面大小余数为0说明删除的是当前页面的最后一条数据，删除后当前页需要减一
			if(total%pageSize==0){
				pageNumber=pageNumber-1;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			// 设置分页的实体
			page.setT(zbzlFileEntity);
			// 设置分页的当前页号
			page.setPageNo(pageNumber);
			// 设置分页的页面大小
			page.setPageSize(pageSize);
			// 分页
			page = zbzlFileService.pageZbzlFile(page);
			// 遍历分页结果
			for (ZbzlFileEntity zbzl : page.getList()) {
				// 将实体中字段为key的转换成对应的值
				zbzl.setFileStatu(dictEntityService.findInfoByKeyAndType(zbzl.getFileStatu(), Constrant.FILE_STATU).getLdictionaryAbel());
				zbzl.setLocalStatu(dictEntityService.findInfoByKeyAndType(zbzl.getLocalStatu(), Constrant.LOCAL_STATU).getLdictionaryAbel());
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
	 * 根据整备质量编号查找备案信息
	 * 
	 * @param zbzlFileEntity
	 * @param request
	 * @return 跳转到整备质量备案修改页面
	 */
	@RequestMapping("findZbzlFileById")
	public String findZbzlFileById(ZbzlFileEntity zbzlFileEntity, HttpServletRequest request) {
		try{
			// 根据查验区编号查找备案信息
			zbzlFileEntity = zbzlFileService.findZbzlFileById(zbzlFileEntity);
			// 获取车辆类型集合
			List<CkCllxEntity> ckCllxList = checkSettingService.allCKCllx();
			request.setAttribute("ckCllxList", ckCllxList);
			// 将查找的备案信息实体存入request
			request.setAttribute("zbzl", zbzlFileEntity);
			// 从字典表里查找备案状态集合存入request
			request.setAttribute("fileStatu", dictEntityService.findInfoByType(Constrant.FILE_STATU));
			// 从字典表里查找本地状态集合存入request
			request.setAttribute("localStatu", dictEntityService.findInfoByType(Constrant.LOCAL_STATU));
			
			return "com/vkl/ckts_pc/rgsy/zbzlbaxg";
		}catch (Exception e){
			log.error(e);
			e.printStackTrace();
			
			return null;
		}
		
	}
	
	/**
	 * 更新备案信息
	 * 
	 * @param zbzlFileEntity
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateZbzlFile", produces = "application/json;charset=UTF-8")
	public String updateZbzlFile(ZbzlFileEntity zbzlFileEntity, HttpServletRequest request) {
		try{
			// 插入操作日志
			logService.insertLog("修改整备质量备案", "整备质量备案", "修改整备质量备案信息", request);
			// 根据整备质量编号查找备案信息
			ZbzlFileEntity zbzl = zbzlFileService.findZbzlFileById(zbzlFileEntity);
			// 修改整备质量备案编号
			zbzl.setFileId(zbzlFileEntity.getFileId());
			// 修改整备质量生产企业
			zbzl.setClyscqy(zbzlFileEntity.getClyscqy());
			// 修改整备质量型号
			zbzl.setClyxh(zbzlFileEntity.getClyxh());
			// 修改整备质量检定有效期止
			zbzl.setClyjdyxqz(zbzlFileEntity.getClyjdyxqz());
			// 修改整备质量设备编号
			zbzl.setClybh(zbzlFileEntity.getClybh());
			// 修改整备质量启用时间
			zbzl.setClyqysj(zbzlFileEntity.getClyqysj());
			// 修改整备质量设备检定/校准证书编号
			zbzl.setClyjdzsbh(zbzlFileEntity.getClyjdzsbh());
			// 修改整备质量经办人
			zbzl.setJbr(zbzlFileEntity.getJbr());
			// 修改整备质量测量范围上限值
			zbzl.setClfwsx(zbzlFileEntity.getClfwsx());
			// 修改整备质量测量范围下限值
			zbzl.setClfwxx(zbzlFileEntity.getClfwxx());
			// 修改整备质量本地状态
			zbzl.setLocalStatu(zbzlFileEntity.getLocalStatu());
			// 修改备案状态
			zbzl.setFileStatu(zbzlFileEntity.getFileStatu());
			// 修改整备质量管理部门
			zbzl.setGlbm(zbzlFileEntity.getGlbm());
			// 修改整备质量可查验车辆
			zbzl.setCycllx(zbzlFileEntity.getCycllx());
			// 修改备案信息，成功返回true 失败返回false
			if (zbzlFileService.updateZbzlFile(zbzl, request)){
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
