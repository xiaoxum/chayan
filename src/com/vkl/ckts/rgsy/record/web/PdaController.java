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
import com.vkl.ckts.common.base.Message;
import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.constr.ErrorInfo;
import com.vkl.ckts.common.controller.BaseController;
import com.vkl.ckts.common.entity.ChkptFileEntity;
import com.vkl.ckts.common.entity.DeptEntity;
import com.vkl.ckts.common.entity.PdaDeviceFifleEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IDictEntityService;
import com.vkl.ckts.rgsy.record.entity.ChkptFileDto;
import com.vkl.ckts.rgsy.record.entity.PdaDeviceFifleDto;
import com.vkl.ckts.rgsy.record.service.IChkptFileService;
import com.vkl.ckts.rgsy.record.service.IPdaFileService;
import com.vkl.ckts.rgsy.statistic.log.service.ILogService;

/**
 * Pda备案Controller
 *
 * @author hwf
 * @version 1.0
 */
@Controller
@RequestMapping("module/pda")
public class PdaController extends BaseController {
	// 注入字典表Service
	@Autowired
	IDictEntityService dictEntityService;
	// pda备案Service
	@Autowired
	IPdaFileService pdaFileService;
	// 查验区备案Service
	@Autowired
	IChkptFileService chkptFileService;
	// 注入操作日志
	@Autowired
	ILogService logService;
	// 获取日志对象
	Logger log = Logger.getLogger(PdaController.class);

	@Autowired
	IVehicleInfoService vehicleInfoService;

	/**
	 * pda备案页面初始化
	 *
	 * @return pda备案页面路径
	 */
	@RequestMapping("pdaba")
	public String pdaba() {
		return "com/vkl/ckts_pc/rgsy/pdaba";
	}

	/**
	 * pda备案申请页面初始化
	 *
	 * @return pda备案申请页面路径
	 */
	@RequestMapping("pdabasq")
	public String pdabasq() {
		return "com/vkl/ckts_pc/rgsy/pdabasq";
	}

	/**
	 * 加载查验区
	 * 
	 * @return 将json返回至页面
	 */
	@ResponseBody
	@RequestMapping(value = "loadChkpt", produces = "application/json;charset=UTF-8")
	public String loadChkpt(ChkptFileDto chkptFileDto,
			HttpServletRequest request) {
		try {
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
				chkptFileDto.setOrganCode(deptEntity.getFileId());
			}
			// 获取查验区List
			List<ChkptFileDto> chkptList = chkptFileService
					.findChkptList(chkptFileDto);
			JSONArray jsonList = new JSONArray();
			// 遍历业务类型集合，插入到json集合中
			for (ChkptFileEntity cfe : chkptList) {
				jsonList.add(cfe);
			}
			return jsonList.toJSONString();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();

			return null;
		}
	}

	/**
	 * 提交pda备案申请
	 *
	 * @param chkptFileEntity
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "submit", produces = "application/json;charset=UTF-8")
	public Message<String> submit(PdaDeviceFifleEntity pdaDeviceFifleEntity,
			HttpServletRequest request) {
		Message<String> message = new Message<String>();
		try {
			// 插入操作日志
			logService.insertLog("pda备案申请", "pda备案", "提交pda备案申请", request);
			// 添加pda备案，true：备案申请成功，false：备案失败
			message.setData(pdaFileService.addPdaFile(pdaDeviceFifleEntity,
					request));
			return message;
		} catch (Exception e) {
			log.error(e);
			message.setErrorCode(ErrorInfo.S_SQL_EXCEPTION);
			e.printStackTrace();

			return message;
		}
	}

	/**
	 * 分页查找pda备案信息
	 * 
	 * @param pageSize
	 *            每页显示的数据个数
	 * @param pdaDeviceFifleEntity
	 * @return map
	 */
	@ResponseBody
	@RequestMapping(value = "pageFindPdaFile", produces = "application/json;charset=UTF-8")
	public Map<String, Object> pageFindPdaFile(Integer pageSize,
			Integer pageNumber, PdaDeviceFifleDto pdaDeviceFifleEntity,
			Page<PdaDeviceFifleDto> page, HttpServletRequest request) {

		try {
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
				pdaDeviceFifleEntity.setSerial(deptEntity.getFileId());
			}
			Map<String, Object> map = new HashMap<String, Object>();
			// 设置分页的实体
			page.setT(pdaDeviceFifleEntity);
			// 设置分页的当前页号
			page.setPageNo(pageNumber);
			// 设置分页的页面大小
			page.setPageSize(pageSize);
			// 分页
			page = pdaFileService.pagePdaFile(page);
			// 遍历分页结果
			for (PdaDeviceFifleEntity pda : page.getList()) {
				// 将实体中字段为key的转换成对应的值
				pda.setFileStatu(dictEntityService.findInfoByKeyAndType(
						pda.getFileStatu(), Constrant.FILE_STATU)
						.getLdictionaryAbel());
				pda.setLocalStatu(dictEntityService.findInfoByKeyAndType(
						pda.getLocalStatu(), Constrant.LOCAL_STATU)
						.getLdictionaryAbel());
				// 把查验使用性质用"/"隔开
				String serials = pda.getSerial();
				if (serials != null) {
					pda.setSerial(pda.getSerial().replace(",", "/"));
				}
			}
			// 分页后的数据
			map.put("list", page.getList());
			// 总记录数
			map.put("total", pdaFileService.findCount(pdaDeviceFifleEntity));

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
	 * @param pageSize
	 *            页面大小
	 * @param pdaDeviceFifleEntity
	 *            pda备案实体
	 * @return map
	 */
	@ResponseBody
	@RequestMapping(value = "deletePdaFile", produces = "application/json;charset=UTF-8")
	public Map<String, Object> deletePdaFile(Integer pageSize,
			Integer pageNumber, PdaDeviceFifleDto pdaDeviceFifleEntity,
			Page<PdaDeviceFifleDto> page, HttpServletRequest request) {

		try {
			// 插入操作日志
			logService.insertLog("删除pda备案", "pda备案", "删除pda备案信息", request);
			pdaFileService.deletePdaFileById(pdaDeviceFifleEntity);
			Integer total = pdaFileService.findCount(pdaDeviceFifleEntity);
			// 如果数据总数除以页面大小余数为0说明删除的是当前页面的最后一条数据，删除后当前页需要减一
			if (total % pageSize == 0) {
				pageNumber = pageNumber - 1;
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
				pdaDeviceFifleEntity.setSerial(deptEntity.getFileId());
			}
			// 设置分页的实体
			page.setT(pdaDeviceFifleEntity);
			// 设置分页的当前页号
			page.setPageNo(pageNumber);
			// 设置分页的页面大小
			page.setPageSize(pageSize);
			// 分页
			page = pdaFileService.pagePdaFile(page);
			// 遍历分页结果
			for (PdaDeviceFifleEntity pda : page.getList()) {
				// 将实体中字段为key的转换成对应的值
				pda.setFileStatu(dictEntityService.findInfoByKeyAndType(
						pda.getFileStatu(), Constrant.FILE_STATU)
						.getLdictionaryAbel());
				pda.setLocalStatu(dictEntityService.findInfoByKeyAndType(
						pda.getLocalStatu(), Constrant.LOCAL_STATU)
						.getLdictionaryAbel());
			}
			// 分页后的数据
			map.put("list", page.getList());
			// 总记录数
			map.put("total", total);

			return map;
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();

			return null;
		}

	}

	/**
	 * 根据dpa编号查找备案信息
	 * 
	 * @param pdaDeviceFifleEntity
	 * @param request
	 * @return 跳转到pda备案修改页面
	 */
	@RequestMapping("findPdaFileById")
	public String findPdaFileById(PdaDeviceFifleEntity pdaDeviceFifleEntity,
			HttpServletRequest request) {
		try {
			// 根据查验区编号查找备案信息
			pdaDeviceFifleEntity = pdaFileService
					.findPdaFileById(pdaDeviceFifleEntity);

			// 将查找的备案信息实体存入request
			request.setAttribute("pda", pdaDeviceFifleEntity);
			// 从字典表里查找备案状态集合存入request
			request.setAttribute("fileStatu",
					dictEntityService.findInfoByType(Constrant.FILE_STATU));
			// 从字典表里查找本地状态集合存入request
			request.setAttribute("localStatu",
					dictEntityService.findInfoByType(Constrant.LOCAL_STATU));

			return "com/vkl/ckts_pc/rgsy/pdabaxg";
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();

			return null;
		}

	}

	/**
	 * 更新备案信息
	 * 
	 * @param chkptFileEntity
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updatePdaFile", produces = "application/json;charset=UTF-8")
	public String updatePdaFile(PdaDeviceFifleEntity pdaDeviceFifleEntity,
			HttpServletRequest request) {
		try {
			// 插入操作日志
			logService.insertLog("修改pda备案", "pda备案", "修改pda备案信息", request);
			// 根据pda编号查找备案信息
			PdaDeviceFifleEntity pda = pdaFileService
					.findPdaFileById(pdaDeviceFifleEntity);
			// 修改系统说明
			pda.setSysExp(pdaDeviceFifleEntity.getSysExp());
			// 修改系统备案编号
			pda.setFileId(pdaDeviceFifleEntity.getFileId());
			// 修改终端品牌型号
			pda.setDeviceUt(pdaDeviceFifleEntity.getDeviceUt());
			// 修改系统名称
			pda.setSysName(pdaDeviceFifleEntity.getSysName());
			// 修改操作系统
			pda.setOperSys(pdaDeviceFifleEntity.getOperSys());
			// 修改系统版本
			pda.setVerNo(pdaDeviceFifleEntity.getVerNo());
			// 修改开发单位
			pda.setDeviceVender(pdaDeviceFifleEntity.getDeviceVender());
			// 修改验收时间
			pda.setCaTime(pdaDeviceFifleEntity.getCaTime());
			// 修改查验区序号范围
			pda.setSerial(pdaDeviceFifleEntity.getSerial());
			// 修改查验区本地状态
			pda.setLocalStatu(pdaDeviceFifleEntity.getLocalStatu());
			// 修改备案状态
			pda.setFileStatu(pdaDeviceFifleEntity.getFileStatu());
			// 修改备案信息，成功返回true 失败返回false
			if (pdaFileService.updatePdaFile(pda, request)) {
				return "true";
			} else {
				return "false";
			}
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();

			return null;
		}

	}
}
