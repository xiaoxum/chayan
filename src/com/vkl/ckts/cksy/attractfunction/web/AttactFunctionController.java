package com.vkl.ckts.cksy.attractfunction.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vkl.ckts.cksy.attractfunction.entity.AttactPhoto;
import com.vkl.ckts.cksy.attractfunction.service.IAttactFunctionService;
import com.vkl.ckts.cksy.vehicleinformation.controller.vehicleInformationController;
import com.vkl.ckts.cksy.vehicleinformation.entity.CkInfoEntityDto;
import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.entity.DeptEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.page.Page;

/**
 * 附加功能web层接口
 * 
 * @author X260
 *
 */
@Controller
@RequestMapping(value="module/attact/")
public class AttactFunctionController {

	@Autowired
	IAttactFunctionService attactfunctionService;
	
	/**
	 * 日志处理
	 */
	private static Logger logger = LoggerFactory
			.getLogger(AttactFunctionController.class);
	/**
	 * 加载列表页面数据内容
	 * 
	 * @param request
	 *            获取session
	 * @param page
	 *            分页对象
	 * @return
	 */
	@ResponseBody
	@RequestMapping("loadattactphotos")
	public Page<AttactPhoto> loadInfo(HttpServletRequest request,
			Page<AttactPhoto> page, AttactPhoto attactPhoto) {
		try {
			// 从request中获取session
			HttpSession session = request.getSession();
			UserEntity user = (UserEntity) session
					.getAttribute(Constrant.S_USER_SESSION);
			// 设置用户机构
			//查找部门信息
			DeptEntity deptEntity=attactfunctionService.findDeptById(user.getUserDept().toString());
			if (deptEntity==null) {
				return null;
			}
			if (!"1".equals(deptEntity.getDeptType())) {
				//当前不为为市车管所
				attactPhoto.setTackeOrganCode(deptEntity.getFileId());
			}
			page.setT(attactPhoto);
			logger.info("用户：" + user.getId() + "查询页面数据");
			return attactfunctionService.findAttactPhoto(page);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询数据异常");
			return null;
		}
	}
	@RequestMapping("tophotojsp")
	public String toPhotoJsp(Model model){
		return "com/vkl/ckts_pc/cksy/attactfunction/attactphoto";
	}
	
}
