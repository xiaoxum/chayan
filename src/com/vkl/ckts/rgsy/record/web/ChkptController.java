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

import com.vkl.ckts.cksy.vehicleinformation.service.IVehicleInfoService;
import com.vkl.ckts.common.base.Message;
import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.constr.ErrorInfo;
import com.vkl.ckts.common.entity.CehUsnrEntity;
import com.vkl.ckts.common.entity.ChkptFileEntity;
import com.vkl.ckts.common.entity.CkCllxEntity;
import com.vkl.ckts.common.entity.DeptEntity;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.entity.OperTypeEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IDictEntityService;
import com.vkl.ckts.common.utils.Base64Utils;
import com.vkl.ckts.rgsy.dept.service.IDeptService;
import com.vkl.ckts.rgsy.record.entity.ChkptFileDto;
import com.vkl.ckts.rgsy.record.service.IChkptFileService;
import com.vkl.ckts.rgsy.statistic.log.service.ILogService;
import com.vkl.ckts.rgsy.system.checksetting.service.ICheckSettingService;



/**
 * 查验区备案Controller
 *
 * @author hwf
 * @version 1.0
 */
@Controller()
@RequestMapping("module/chkpt")
public class ChkptController {
	// 注入部门Service
	@Autowired
	IDeptService deptService;
	// 注入查验项设置Service
	@Autowired
	ICheckSettingService checkSettingService;
	// 注入字典表Service
	@Autowired
	IDictEntityService dictEntityService;
	// 查验区备案Service
	@Autowired
	IChkptFileService chkptFileService;
	// 注入操作日志
	@Autowired
	ILogService logService;	

	@Autowired
	IVehicleInfoService vehicleInfoService;

	// 获取日志对象
	Logger log = Logger.getLogger(RecordController.class);

	/**
	 * 查验区备案页面初始化
	 *
	 * @return 跳转到查验区备案
	 */
	@RequestMapping("cyqba")
	public String loadCyqba() {
		return "com/vkl/ckts_pc/rgsy/cyqba";
	}

	/**
	 * 查验区备案申请页面初始化
	 *
	 * @return 跳转到查验区备案申请
	 */
	@RequestMapping("cyqbasq")
	public String loadCyqbasq(HttpServletRequest request) {
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
			
			return "com/vkl/ckts_pc/rgsy/cyqbasq";
		} catch (Exception e) {
			log.error("e");
			e.printStackTrace();

			return "com/vkl/ckts_pc/rgsy/cyqbasq";
		}
	}



	/**
	 * 提交查验区备案申请
	 *
	 * @param chkptFileEntity
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "submit", produces = "application/json;charset=UTF-8")
	public Message<String> submit(ChkptFileDto chkptFileEntity, HttpServletRequest request) {
		Message<String> message = new Message<String>();
		try {
			// 插入操作日志
			logService.insertLog("查验区备案申请", "查验区备案", "提交查验区备案申请", request);	
			// 添加查验区备案，true：备案申请成功，false：备案失败
			if (chkptFileService.addChkptFile(chkptFileEntity, request)) {
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
	 * 分页查找查验区备案信息
	 * 
	 * @param pageSize
	 *            每页显示的数据个数
	 * @param chkptFileEntity
	 * @return map
	 */
	@ResponseBody
	@RequestMapping(value = "pageFindChkptFile", produces = "application/json;charset=UTF-8")
	public Map<String, Object> pageFindChkptFile(Integer pageSize, Integer pageNumber, ChkptFileDto chkptFileEntity,
			Page<ChkptFileDto> page,HttpServletRequest request) {

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
				chkptFileEntity.setOrganCode(deptEntity.getFileId());;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			// 设置分页的实体
			page.setT(chkptFileEntity);
			// 设置分页的当前页号
			page.setPageNo(pageNumber);
			// 设置分页的页面大小
			page.setPageSize(pageSize);
			// 分页
			page = chkptFileService.pageChkptFile(page);
			// 遍历分页结果
			for (ChkptFileEntity cfe : page.getList()) {
				// 将实体中字段为key的转换成对应的值
				cfe.setOrganType(dictEntityService.findInfoByKeyAndType(cfe.getOrganType(), Constrant.ORGAN_TYPE).getLdictionaryAbel());
				// 解密联系电话
//				cfe.setPripPhone(Base64Utils.decode(cfe.getPripPhone()));
				// 把查验使用性质用逗号分割成字符串数组
				String syxzsKey[] = cfe.getSyxzs().split(",");
				StringBuffer syxzsName = new StringBuffer();
				syxzsName.append(checkSettingService.findCehUsnrPNameByPId(syxzsKey[0]));
				for (int i = 1; i < syxzsKey.length; i++) {
					// 把查验使用性质转成对应的值追加到syxzsName
					syxzsName.append("/" + checkSettingService.findCehUsnrPNameByPId(syxzsKey[i]));
				}
				cfe.setSyxzs(syxzsName.toString());
				if (IdEntity.S_YES.equals(cfe.getUserDz())) {
					cfe.setUserDz("启用");
				} else {
					cfe.setUserDz("禁用");
				}
				cfe.setPripName(Base64Utils.decode(cfe.getPripName()));
				cfe.setFileStatu(dictEntityService.findInfoByKeyAndType(cfe.getFileStatu(), Constrant.FILE_STATU).getLdictionaryAbel());
				cfe.setLocalStatu(dictEntityService.findInfoByKeyAndType(cfe.getLocalStatu(), Constrant.LOCAL_STATU).getLdictionaryAbel());
			}
			// 分页后的数据
			map.put("list", page.getList());
			// 总记录数
			map.put("total", chkptFileService.findCount(chkptFileEntity));

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
	 * @param chkptFileEntity 查验员区备案实体
	 * @return map
	 */
	@ResponseBody
	@RequestMapping(value = "deleteChkptFile", produces = "application/json;charset=UTF-8")
	public Map<String, Object> deleteChkptFile(Integer pageSize, Integer pageNumber, ChkptFileDto chkptFileEntity,
			Page<ChkptFileDto> page,HttpServletRequest request,String searchOrganCode) {
		
		try{
			// 插入操作日志
			logService.insertLog("删除查验区备案", "查验区备案", "删除查验区备案信息", request);
			chkptFileService.deleteChkptFileById(chkptFileEntity);
			// 将输入框中的查验区序号放到实体中作为条件
			chkptFileEntity.setOrganCode(searchOrganCode);
			Integer total=chkptFileService.findCount(chkptFileEntity);
			// 如果数据总数除以页面大小余数为0说明删除的是当前页面的最后一条数据，删除后当前页需要减一
			if(total%pageSize==0){
				pageNumber=pageNumber-1;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			// 设置分页的实体
			page.setT(chkptFileEntity);
			// 设置分页的当前页号
			page.setPageNo(pageNumber);
			// 设置分页的页面大小
			page.setPageSize(pageSize);
			// 分页
			page = chkptFileService.pageChkptFile(page);
			// 遍历分页结果
			for (ChkptFileEntity cfe : page.getList()) {
				// 将实体中字段为key的转换成对应的值
				cfe.setOrganType(dictEntityService.findInfoByKeyAndType(cfe.getOrganType(), Constrant.ORGAN_TYPE).getLdictionaryAbel());
				// 解密联系电话
//				cfe.setPripPhone(Base64Utils.decode(cfe.getPripPhone()));
				// 把查验使用性质用逗号分割成字符串数组
				String syxzsKey[] = cfe.getSyxzs().split(",");
				StringBuffer syxzsName = new StringBuffer();
				syxzsName.append(checkSettingService.findCehUsnrPNameByPId(syxzsKey[0]));
				for (int i = 1; i < syxzsKey.length; i++) {
					// 把查验使用性质转成对应的值追加到syxzsName
					syxzsName.append("," + checkSettingService.findCehUsnrPNameByPId(syxzsKey[i]));
				}
				cfe.setSyxzs(syxzsName.toString());
				if (IdEntity.S_YES.equals(cfe.getUserDz())) {
					cfe.setUserDz("启用");
				} else {
					cfe.setUserDz("禁用");
				}
				cfe.setPripName(Base64Utils.decode(cfe.getPripName()));
				cfe.setFileStatu(dictEntityService.findInfoByKeyAndType(cfe.getFileStatu(), Constrant.FILE_STATU).getLdictionaryAbel());
				cfe.setLocalStatu(dictEntityService.findInfoByKeyAndType(cfe.getLocalStatu(), Constrant.LOCAL_STATU).getLdictionaryAbel());
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
	 * 根据查验区编号查找备案信息
	 * 
	 * @param chkptFileEntity
	 * @param request
	 * @return 跳转到查验区备案修改页面
	 */
	@RequestMapping("findChkptFileById")
	public String findChkptFileById(ChkptFileDto chkptFileEntity, HttpServletRequest request) {
		try{
			// 根据查验区编号查找备案信息
			chkptFileEntity = chkptFileService.findChkptFileById(chkptFileEntity);
			// 解密联系电话和负责人姓名
//			chkptFileEntity.setPripPhone(Base64Utils.decode(chkptFileEntity.getPripPhone()));
			chkptFileEntity.setPripName(Base64Utils.decode(chkptFileEntity.getPripName()));
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
			request.setAttribute("cfe", chkptFileEntity);
			// 从字典表里查找查验区类集合存入request
			request.setAttribute("organTypes", dictEntityService.findInfoByType(Constrant.ORGAN_TYPE));
			// 从字典表里查找备案状态集合存入request
			request.setAttribute("fileStatu", dictEntityService.findInfoByType(Constrant.FILE_STATU));
			// 从字典表里查找本地状态集合存入request
			request.setAttribute("localStatu", dictEntityService.findInfoByType(Constrant.LOCAL_STATU));
			
			return "com/vkl/ckts_pc/rgsy/cyqbaxg";
		}catch (Exception e){
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
	@RequestMapping(value = "updateChkptFile", produces = "application/json;charset=UTF-8")
	public String updateChkptFile(ChkptFileDto chkptFileEntity, HttpServletRequest request) {
		try{
			// 插入操作日志
			logService.insertLog("修改查验区备案", "查验区备案", "修改查验区备案信息", request);
			// 根据查验员编号查找备案信息
			ChkptFileDto cfe = chkptFileService.findChkptFileById(chkptFileEntity);
			// 修改查验区编号
			cfe.setOrganCode(chkptFileEntity.getOrganCode());
			// 修改经度
			cfe.setCkCentLg(chkptFileEntity.getCkCentLg());
			// 修改纬度
			cfe.setCkCentLt(chkptFileEntity.getCkCentLt());
			// 修改查验区名称
			cfe.setOrganName(chkptFileEntity.getOrganName());
			// 修改电子围栏半径
			cfe.setCkCentR(chkptFileEntity.getCkCentR());
			// 修改查验区类型
			cfe.setOrganType(chkptFileEntity.getOrganType());
			cfe.setIsAutoAudit(chkptFileEntity.getIsAutoAudit());
			// 修改所属部门id
			cfe.setParentDeptId(chkptFileEntity.getParentDeptId());
			// 修改所属部门名称
			cfe.setParentDeptName(chkptFileEntity.getParentDeptName());
			// 修改联系方式
			cfe.setPripPhone(chkptFileEntity.getPripPhone());
			// 修改负责人姓名
			cfe.setPripName(Base64Utils.encode(chkptFileEntity.getPripName()));
			// 修改查验使用性质
			cfe.setSyxzs(chkptFileEntity.getSyxzs());
			// 修改电子围栏状态
			cfe.setUserDz(chkptFileEntity.getUserDz());
			// 修改查验区本地状态
			cfe.setLocalStatu(chkptFileEntity.getLocalStatu());
			// 修改业务类型
			cfe.setOperTypes(chkptFileEntity.getOperTypes());
			// 修改备案状态
			cfe.setFileStatu(chkptFileEntity.getFileStatu());
			// 修改可查车型
			cfe.setCkCllxs(chkptFileEntity.getCkCllxs());
			cfe.setVideoIp(chkptFileEntity.getVideoIp());
			cfe.setVideoPort(chkptFileEntity.getVideoPort());
			// 修改备案信息，成功返回true 失败返回false
			if (chkptFileService.updateChkptFile(cfe, request)){
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
