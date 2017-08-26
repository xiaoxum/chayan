package com.vkl.ckts.rgsy.system.nvrsetting.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.vkl.ckts.common.entity.ChkptFileEntity;
import com.vkl.ckts.rgsy.system.nvrsetting.entity.ChkptNvrConfigDto;
import com.vkl.ckts.rgsy.system.nvrsetting.service.IChkptNvrConfigService;

/**
 * 查验区视频配置controller
 * 
 * @author X260
 *
 */
@Controller
@RequestMapping(value="module/nvrsetting/")
public class ChkptNvrConfigController {
	
	@Autowired
	IChkptNvrConfigService chkptNvrConfig;
	/**
	 * 跳转到设置页面
	 * 
	 * @return
	 */
	@RequestMapping(value="tosettingpage")
	public String tosettingPage(){
		return  "com/vkl/ckts_pc/rgsy/system/chkptnvrsetting";
	}
	
	/**
	 * 加载查验区
	 * 
	 * @return 将json返回至页面
	 */
	@ResponseBody
	@RequestMapping(value = "loadChkpt", produces = "application/json;charset=UTF-8")
	public String loadChkpt (ChkptFileEntity chkptFileDto,HttpServletRequest request) {
		try{
			
			// 获取查验区List
			List<ChkptFileEntity> chkptList = chkptNvrConfig.findChkptList(chkptFileDto);
			JSONArray jsonList = new JSONArray();
			// 遍历业务类型集合，插入到json集合中
			for (ChkptFileEntity cfe : chkptList) {
				jsonList.add(cfe);
			}
			return jsonList.toJSONString();
		}catch (Exception e){
			e.printStackTrace();
			
			return null;
		}
	}
	
	
	
	
	
	
	
	
	/**
	 * 跳转到设置页面
	 * 
	 * @return
	 */
	@RequestMapping(value="findchkptnvrInfos")
	@ResponseBody
	public List<ChkptNvrConfigDto> getChkptNvrConfig(String organCode,HttpServletRequest request){
		//获取当前用户 
		List<ChkptNvrConfigDto> coChkptNvrConfigDtos;
		try {
		/*	UserEntity  user = (UserEntity)request.getSession().getAttribute(Constrant.S_USER_SESSION);
			DeptEntity deptEntity = chkptNvrConfig.findDeptById(user.getUserDept()+"");
			String fileId =   deptEntity==null?null:deptEntity.getFileId();
			if (fileId==null|| fileId=="") {
				return null;
			}*/
			coChkptNvrConfigDtos = chkptNvrConfig.findChkptNvrConfigs(organCode);
			return coChkptNvrConfigDtos;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	/**
	 * 跳转到设置页面
	 * 
	 * @return
	 */
	@RequestMapping(value="addchkptconfigdto")
	@ResponseBody
	public String addChkptConfigDto(ChkptNvrConfigDto dto,HttpServletRequest request){
		try {
//			UserEntity  user = (UserEntity)request.getSession().getAttribute(Constrant.S_USER_SESSION);
//			DeptEntity deptEntity = chkptNvrConfig.findDeptById(user.getUserDept()+"");
//			String fileId =   deptEntity==null?null:deptEntity.getFileId();
//			if (fileId==null|| fileId=="") {
//				return "no-authority";
//			}
//			dto.setOrganCode(fileId);
			chkptNvrConfig.insertChkptNvrConfig(dto);
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "sys-error";
		}
	}
	
	@RequestMapping(value="toaddchkptnvrconfig")
	public String toaddChkptNvrConfig(String organCode,String checkLineId,Model model){
		if (!StringUtils.isBlank(checkLineId)&&!StringUtils.isBlank(organCode)) {
			model.addAttribute("videoConfig", chkptNvrConfig.findChNvrConfig(organCode, checkLineId));
		}
		return "com/vkl/ckts_pc/rgsy/system/chkptnvrsettingadd";
	}
	
	/**
	 * 加载视频
	 * @param organCode
	 * @param checkLineId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="loadvideo")
	public String loadVideo(String organCode,String checkLineId,Model model){
		model.addAttribute("videoConfig", chkptNvrConfig.findChNvrConfig(organCode, checkLineId));
		return "com/vkl/ckts_pc/rgsy/system/loadVideo";
	}
	
	
	
	
	/**
	 * 跳转到设置页面
	 * 
	 * @return
	 */
	@RequestMapping(value="updatechkptconfig")
	@ResponseBody
	public String updateChkptConfig(ChkptNvrConfigDto dto,HttpServletRequest request){
		try {
			chkptNvrConfig.updateChkptNvrInfo(dto);
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "sys-error";
		}
	}
	
	
	
	/**
	 * 跳转到设置页面
	 * 
	 * @return
	 */
	@RequestMapping(value="deletechkptnvrconfig")
	@ResponseBody
	public String deleteChkptNvrConfig(String organCode,String checkLineId){
		
		try {
			chkptNvrConfig.deleteChkptLineInfo(organCode, checkLineId);
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "sys-error";
		}
	}
	
	
	
	
}
