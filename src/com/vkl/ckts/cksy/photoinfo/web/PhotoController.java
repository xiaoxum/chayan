package com.vkl.ckts.cksy.photoinfo.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vkl.ckts.cksy.photoinfo.entity.PhotoDto;
import com.vkl.ckts.cksy.photoinfo.entity.PicProjectDto;
import com.vkl.ckts.cksy.photoinfo.entity.Tree;
import com.vkl.ckts.cksy.photoinfo.service.IPhotoService;
import com.vkl.ckts.cksy.vehicleinformation.service.IVehicleInfoService;
import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.entity.DeptEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IOperTypeEntityService;
import com.vkl.ckts.common.utils.PropertiesUtils;

@Controller
@RequestMapping(value="module/photoinfo/")
public class PhotoController {

	@Autowired
	IPhotoService photoService;
	
	
	/**
	 * 业务类型表
	 */
	@Autowired
	IOperTypeEntityService operTypeEntityService;
	
	@Autowired
	IVehicleInfoService vehicleInfoService;
	
	/**
	 * 加载列表页面
	 * 
	 * @return
	 */
	@RequestMapping("tophotojsp")
	public String loadList(Model model) {
		try {
			// 加载下拉框数据
		model.addAttribute("operType", operTypeEntityService.selectAll());
			return "com/vkl/ckts_pc/cksy/photo/photoList";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 查找照片信息
	 * 
	 * @param page
	 * @param photoDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="findphotodtos")
	public Page<PhotoDto> findPhotoInDtos(Page<PhotoDto> page,PhotoDto photoDto,HttpServletRequest request){
		String picUrl = PropertiesUtils.getValues("fileTomcatUrl");
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
			photoDto.setOrganCode(deptEntity.getFileId());
		}
		page.setT(photoDto);
		photoService.findPidInfo(page);
		List<PhotoDto> photoDtos = page.getList();
		if (photoDtos==null||photoDtos.size()<=0) {
			return page;
		}
		for (PhotoDto photoDto2 : photoDtos) {
			photoDto2.setPicUrl(picUrl+"/"+photoDto2.getPicUrl());
		}
		page.setList(photoDtos);
		return page;
	}
	
	
	/**
	 * 查找拍照项目
	 * 
	 * @param page
	 * @param photoDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getpicprojectlist", produces = "application/json;charset=utf-8")
	public List<Tree> getPicProjectList(){
		List<PicProjectDto> lists=photoService.findPicProject();
		List<Tree> treeList = new ArrayList<Tree>();
		for (int i = 0; i < lists.size(); i++) {
			// 树实体
			Tree tree = new Tree();
			// 获取菜单名称
			String picName = lists.get(i).getPicName();
			// 设置树的id为菜单的id
			tree.setId(lists.get(i).getPicId());
			// 设置树的内容为菜单名称
			tree.setText(picName);
			tree.setState("open");
			// 把这个树添加到树集合中
			treeList.add(tree);
		}
		return treeList;
	}
	
	
	@ResponseBody
	@RequestMapping(value="findzddys")
	public List<PhotoDto> findNoDyPhoto(Page<PhotoDto> page,PhotoDto photoDto,HttpServletRequest request){
		String picUrl = PropertiesUtils.getValues("fileTomcatUrl");
		List<String> wareIdList = Arrays.asList(photoDto.getPicId().split(","));
		photoDto.setPicIds(wareIdList);
		photoDto.setSfdy("0");
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
			photoDto.setOrganCode(deptEntity.getFileId());
		}
		page.setT(photoDto);
		photoService.findPidInfo(page);
		List<PhotoDto> photoDtos = page.getList();
		if (photoDtos==null||photoDtos.size()<=0) {
			return null;
		}
		for (PhotoDto photoDto2 : photoDtos) {
			photoDto2.setPicUrl(picUrl+"/"+photoDto2.getPicUrl());
		}
		return photoDtos;
	}
}
