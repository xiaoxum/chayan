package com.vkl.ckts.rgsy.dept.web;

import java.util.ArrayList;
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
import com.vkl.ckts.common.entity.AreaEntity;
import com.vkl.ckts.common.entity.ChkptFileEntity;
import com.vkl.ckts.common.entity.DeptEntity;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IAreaEntityService;
import com.vkl.ckts.common.service.IDictEntityService;
import com.vkl.ckts.common.utils.Base64Utils;
import com.vkl.ckts.rgsy.dept.service.IDeptService;
import com.vkl.ckts.rgsy.record.entity.ChkptFileDto;
import com.vkl.ckts.rgsy.record.entity.Tree;
import com.vkl.ckts.rgsy.record.service.IChkptFileService;
import com.vkl.ckts.rgsy.statistic.log.service.ILogService;

/**
 * 部门Controller
 *
 * @author hwf
 * @version 1.0
 */
@Controller()
@RequestMapping("module/dept")
public class DeptController {
	// 注入字典表Service
	@Autowired
	IDictEntityService dictEntityService;
	// 注入部门Service
	@Autowired
	IDeptService deptService;
	// 注入操作日志
	@Autowired
	ILogService logService;
	// 注入地区Service
	@Autowired
	IAreaEntityService areaService;
	// 获取日志对象
	Logger log = Logger.getLogger(DeptController.class);
	
	// 查验区备案Service
	@Autowired
	IChkptFileService chkptFileService;
	/**
	 * 部门管理页面初始化
	 *
	 * @return
	 */
	@RequestMapping("bmgl")
	public String bmgl(){
		return "com/vkl/ckts_pc/rgsy/bmgl";
	}
	
	/**
	 * 添加部门页面初始化
	 *
	 * @return
	 */
	@RequestMapping("bmadd")
	public String bmadd(HttpServletRequest request){
		List<AreaEntity> areaList=areaService.findCityArea(360100);
		request.setAttribute("areaList", areaList);
		UserEntity userEntity=(UserEntity)request.getSession().getAttribute(Constrant.S_USER_SESSION);
		request.setAttribute("deptId", userEntity.getUserDept());
		return "com/vkl/ckts_pc/rgsy/bmadd";
	}
	
	/**
	 * 获取部门树形下拉框
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findAllDeptList", produces = "application/json;charset=utf-8")
	public List<Tree> findAllDeptList(DeptEntity deptEntity,String flag,HttpServletRequest request) {
		// 根据父id查找子部门
		List<DeptEntity> deptList=new ArrayList<DeptEntity>();
		DeptEntity deptEntity2=new DeptEntity();
		 List<Tree> treeList = new ArrayList<Tree>();
		 UserEntity userEntity=(UserEntity)request.getSession().getAttribute(Constrant.S_USER_SESSION);
		try {
			
			
				if("1".equals(flag)){
					if ("1".equals(userEntity.getSfgly())) {
						deptEntity.setParentId("0");
						deptList = deptService.findAllDeptByParentId(deptEntity);
					}else {
						deptEntity2=deptService.findDeptById(deptEntity);
						deptList.add(deptEntity2);
					}
					
				}else {
					deptList = deptService.findAllDeptByParentId(deptEntity);
				}
		   // 构建返回的树形集合
			// 遍历部门集合
			for (int i = 0; i < deptList.size(); i++) {
				// 树实体
				Tree tree = new Tree();
				// 获取部门名称
				String deptName = deptList.get(i).getDeptName();
				// 设置树的id为部门的id
				tree.setId(Integer.parseInt(deptList.get(i).getId()));
				// 设置树的内容为部门名称
				tree.setText(deptName);
				// 查找当前部门的子部门集合
				deptEntity.setParentId(deptList.get(i).getId());
				List<DeptEntity> subDeptList = deptService.findAllDeptByParentId(deptEntity);
				// 如果当前部门有子部门，设置树的状态为关闭，否则为打开
				if (subDeptList.size() <= 0) {
					// 设置树的状态：open为打开，closed为关闭
					tree.setState("open");
				} else {
					// 设置树的状态：open为打开，closed为关闭
					tree.setState("open");
				}
				// 遍历二级部门给子树赋值
				for (int j = 0; j < subDeptList.size(); j++) {
					// 递归调用当前方法查找子树
					List<Tree> children = findAllDeptList(subDeptList.get(j),null,request);
					// 把子树集合添加到当前树下作为它的子树
					tree.setChildren(children);
				}
				// 把这个树添加到树集合中
				treeList.add(tree);
			}
			JSONArray json = new JSONArray();
			for (Tree tree : treeList) {
				json.add(tree);
			}

			return treeList;
		} catch (Exception e) {
			e.printStackTrace();

			return treeList;
		}

	}
	
	/**
	 * 获取部门树形下拉框
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findDeptList", produces = "application/json;charset=utf-8")
	public List<Tree> findDeptList(DeptEntity deptEntity,String flag) {
		// 根据父id查找子部门
		List<DeptEntity> deptList=new ArrayList<DeptEntity>();
		DeptEntity deptEntity2=new DeptEntity();
		// 构建返回的树形集合
		List<Tree> treeList = new ArrayList<Tree>();
		try {
			if("1".equals(flag)){
				deptEntity2=deptService.findDeptById(deptEntity);
				deptList.add(deptEntity2);
			}else {
				deptList = deptService.findSubDeptByParentId(deptEntity);
			}
			// 遍历部门集合
			for (int i = 0; i < deptList.size(); i++) {
				// 树实体
				Tree tree = new Tree();
				// 获取部门名称
				String deptName = deptList.get(i).getDeptName();
				// 设置树的id为部门的id
				tree.setId(Integer.parseInt(deptList.get(i).getId()));
				// 设置树的内容为部门名称
				tree.setText(deptName);
				// 查找当前部门的子部门集合
				deptEntity.setParentId(deptList.get(i).getId());
				List<DeptEntity> subDeptList = deptService.findSubDeptByParentId(deptEntity);
				// 如果当前部门有子部门，设置树的状态为关闭，否则为打开
				if (subDeptList.size() <= 0) {
					// 设置树的状态：open为打开，closed为关闭
					tree.setState("open");
				} else {
					// 设置树的状态：open为打开，closed为关闭
					tree.setState("open");
				}
				// 遍历二级部门给子树赋值
				for (int j = 0; j < subDeptList.size(); j++) {
					// 递归调用当前方法查找子树
					List<Tree> children = findDeptList(subDeptList.get(j),null);
					// 把子树集合添加到当前树下作为它的子树
					tree.setChildren(children);
				}

				// 把这个树添加到树集合中
				treeList.add(tree);

			}
			JSONArray json = new JSONArray();
			for (Tree tree : treeList) {
				json.add(tree);
			}

			return treeList;
		} catch (Exception e) {
			e.printStackTrace();

			return treeList;
		}

	}
	
	
	
	
	
//	/**
//	 * 获取部门树形下拉框
//	 *
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping(value = "findAllDeptList2", produces = "application/json;charset=utf-8")
//	public List<Tree> findAllDeptList2(DeptEntity deptEntity) {
//		List<Tree> treeList = new ArrayList<Tree>();
//		DeptEntity deptEntity2=new DeptEntity();
//		deptEntity2=deptService.findDeptById(deptEntity);
//		deptList.add(deptEntity2);
//	}
//	
	
	
	/**
	 * 添加部门
	 *
	 * @param request
	 * @param deptEntity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("submit")
	public Message<String> submit(HttpServletRequest request,DeptEntity deptEntity){
		Message<String> message=new Message<String>();
		try {
			
			// 插入操作日志
			logService.insertLog("添加部门", "部门管理", "添加部门", request);	
			if ("3".equals(deptEntity.getDeptType())&&deptEntity.getFileId()!=null&&deptEntity.getFileId()!="") {
				DeptEntity deptEntity2=new DeptEntity();
				deptEntity2.setFileId(deptEntity.getFileId());
				DeptEntity dept=deptService.findDept(deptEntity2);
				if (dept!=null&&IdEntity.DEL_FLAG_DELETE.equals(dept.getDelFlag())) {
					// 修改部门类型
					dept.setDeptType(deptEntity.getDeptType());
					// 修改部门名称
					dept.setDeptName(deptEntity.getDeptName());
					// 修改备案编号
					dept.setFileId(deptEntity.getFileId());
					// 修改上级部门
					dept.setParentId(deptEntity.getParentId());
					// 修改行政区划
					dept.setAsts(deptEntity.getAsts());
					// 修改联系电话
					dept.setDeptPhone(deptEntity.getDeptPhone());
					// 修改联系地址
					dept.setDeptAddr(deptEntity.getDeptAddr());
					// 修改邮箱
					dept.setDeptEmail(deptEntity.getDeptEmail());
					// 修改网址
					dept.setDeptUrl(deptEntity.getDeptUrl());
					// 修改部门描述
					dept.setDeptIntr(deptEntity.getDeptIntr());
					dept.setDelFlag(IdEntity.DEL_FLAG_NORMAL);
					// 修改部门信息，成功返回true 失败返回false
					if (deptService.updateDept(dept, request)){
						message.setData("true");
						return message;
					}else{
						message.setData("false");
						message.setErrorCode(ErrorInfo.S_SYS_EXCEPTION);
						return message;
					}
				}
			
			}
			// 添加部门，true：成功，false：失败
			if (deptService.addDept(deptEntity, request)) {
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
	 * 判断部门是否存在
	 *
	 * @param deptEntity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("ckDeptExist")
	public Message<String> ckDeptExist(DeptEntity deptEntity){
		Message<String> message=new Message<String>();
		try {
			if (deptEntity==null || org.apache.commons.lang3.StringUtils.isBlank(deptEntity.getFileId())) {
				message.setData("false");
				return message;
			}
			DeptEntity deptEntity2=deptService.findDept(deptEntity);
			if(deptEntity2 != null && IdEntity.DEL_FLAG_NORMAL.equals(deptEntity2.getDelFlag())){
				// 设置返回的信息
				message.setData("true");
				return message;
			}
			// 设置返回的信息
			message.setData("false");
			return message;
		} catch (Exception e) {
			log.error(e);
			// 设置返回的信息
			message.setData("error");
			message.setErrorCode(ErrorInfo.S_SQL_EXCEPTION);
			e.printStackTrace();
			return message;
		}
		
	}
	
	
	/**
	 * 根据查验区编号查找备案信息
	 * 
	 * @param chkptFileEntity
	 * @param request
	 * @return 跳转到查验区备案修改页面
	 */
	@ResponseBody
	@RequestMapping("findChkptFileById")
	public ChkptFileEntity findChkptFileById(ChkptFileDto chkptFileEntity, HttpServletRequest request) {
		try{
			// 根据查验区编号查找备案信息
			chkptFileEntity = chkptFileService.findChkptFileById(chkptFileEntity);
			// 解密联系电话和负责人姓名
			chkptFileEntity.setPripPhone(chkptFileEntity.getPripPhone());
			chkptFileEntity.setPripName(Base64Utils.decode(chkptFileEntity.getPripName()));
			return chkptFileEntity;
		
		}catch (Exception e){
			log.error(e);
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	
	
	/**
	 * 判断部门是否存在并且已删除
	 *
	 * @param deptEntity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("ckDeleteDeptExist")
	public Message<String> ckDeleteDeptExist(DeptEntity deptEntity){
		Message<String> message=new Message<String>();
		try {
			if(deptService.findDeleteDept(deptEntity) != null){
				// 设置返回的信息
				message.setData("true");
				return message;
			}
			// 设置返回的信息
			message.setData("false");
			return message;
		} catch (Exception e) {
			log.error(e);
			// 设置返回的信息
			message.setData("error");
			message.setErrorCode(ErrorInfo.S_SQL_EXCEPTION);
			e.printStackTrace();
			return message;
		}
	}
	
	/**
	 * 恢复删除的部门
	 *
	 * @param request
	 * @param deptEntity
	 * @return
	 */
	@RequestMapping("recoverDeleteDept")
	public String recoverDeleteDept(HttpServletRequest request,DeptEntity deptEntity){
		try {
			// 插入操作日志
			logService.insertLog("添加部门", "部门管理", "恢复删除的部门", request);
			deptService.recoverDeleteDept(deptEntity, request);
			return "com/vkl/ckts_pc/rgsy/bmgl";
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return "com/vkl/ckts_pc/rgsy/bmgl";
		}
	}
	
	/**
	 * 分页查找部门信息
	 * 
	 * @param pageSize
	 *            每页显示的数据个数
	 * @param deptEntity
	 * @return map
	 */
	@ResponseBody
	@RequestMapping(value = "pageFindDept", produces = "application/json;charset=UTF-8")
	public Map<String, Object> pageFindDept(Integer pageSize, Integer pageNumber, DeptEntity deptEntity,
			Page<DeptEntity> page) {

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			// 设置分页的实体
			page.setT(deptEntity);
			// 设置分页的当前页号
			page.setPageNo(pageNumber);
			// 设置分页的页面大小
			page.setPageSize(pageSize);
			// 分页
			page = deptService.pageDept(page);
			// 遍历分页结果
			for (DeptEntity dept : page.getList()) {
				// 将实体中字段为key的转换成对应的值
				dept.setDeptType(dictEntityService.findInfoByKeyAndType(dept.getDeptType(), Constrant.DEPT_TYPE).getLdictionaryAbel());
				if(dept.getParentId().equals("0")){
					dept.setParentId("");
				}else{
					DeptEntity d=new DeptEntity();
					d.setId(dept.getParentId());
					dept.setParentId(deptService.findDept(d).getDeptName());
				}
				if(dept.getDeptPhone()==null){
					dept.setDeptPhone("");
				}
				if(dept.getDeptAddr()==null){
					dept.setDeptAddr("");
				}
				dept.setAsts(areaService.findAreaById(Integer.parseInt(dept.getAsts())).getAreaName());
			}
			// 分页后的数据
			map.put("list", page.getList());
			// 总记录数
			map.put("total", deptService.findCount(deptEntity));

			return map;
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();

			return null;
		}

	}
	
	
	/**
	 * 删除部门信息
	 * 
	 * @param pageSize 页面大小
	 * @param deptEntity 部门实体
	 * @return map
	 */
	@ResponseBody
	@RequestMapping(value = "deleteDept", produces = "application/json;charset=UTF-8")
	public Map<String, Object> deleteDept(Integer pageSize, Integer pageNumber, DeptEntity deptEntity,
			Page<DeptEntity> page,HttpServletRequest request) {
		
		try{
			// 插入操作日志
			logService.insertLog("删除部门", "部门管理", "删除部门信息", request);
			deptService.deleteDeptById(deptEntity);
			Integer total=deptService.findCount(deptEntity);
			// 如果数据总数除以页面大小余数为0说明删除的是当前页面的最后一条数据，删除后当前页需要减一
			if(total%pageSize==0){
				pageNumber=pageNumber-1;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			// 设置分页的实体
			page.setT(deptEntity);
			// 设置分页的当前页号
			page.setPageNo(pageNumber);
			// 设置分页的页面大小
			page.setPageSize(pageSize);
			// 分页
			page = deptService.pageDept(page);
			// 遍历分页结果
			for (DeptEntity dept : page.getList()) {
				// 将实体中字段为key的转换成对应的值
				dept.setDeptType(dictEntityService.findInfoByKeyAndType(dept.getDeptType(), Constrant.DEPT_TYPE).getLdictionaryAbel());
				if(dept.getParentId().equals("0")){
					dept.setParentId("");
				}else{
					DeptEntity d=new DeptEntity();
					d.setId(dept.getParentId());
					dept.setParentId(deptService.findDept(d).getDeptName());
				}
				if(dept.getDeptPhone()==null){
					dept.setDeptPhone("");
				}
				if(dept.getDeptAddr()==null){
					dept.setDeptAddr("");
				}
				dept.setAsts(areaService.findAreaById(Integer.parseInt(dept.getAsts())).getAreaName());
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
	 * 根据部门编号查找部门信息
	 * 
	 * @param deptEntity
	 * @param request
	 * @return 跳转到部门修改页面
	 */
	@RequestMapping("findDeptById")
	public String findDeptById(DeptEntity deptEntity, HttpServletRequest request) {
		try{
			List<AreaEntity> areaList=areaService.findCityArea(360100);
			request.setAttribute("areaList", areaList);
			// 根据部门编号查找部门信息
			deptEntity = deptService.findDept(deptEntity);
			request.setAttribute("dept", deptEntity);
			UserEntity userEntity=(UserEntity)request.getSession().getAttribute(Constrant.S_USER_SESSION);
			request.setAttribute("deptId", userEntity.getUserDept());
			return "com/vkl/ckts_pc/rgsy/bmxg";
		}catch (Exception e){
			log.error(e);
			e.printStackTrace();
			
			return null;
		}
		
	}
	
	/**
	 * 更新部门
	 * 
	 * @param deptEntity
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateDept", produces = "application/json;charset=UTF-8")
	public Message<String> updateDept(DeptEntity deptEntity, HttpServletRequest request) {
		Message<String> message=new Message<String>();
		try{
			// 插入操作日志
			logService.insertLog("修改部门", "部门管理", "修改部门信息", request);
			// 根据部门编号查找部门信息
			DeptEntity dept = deptService.findDeptById(deptEntity);
			// 修改部门类型
			dept.setDeptType(deptEntity.getDeptType());
			// 修改部门名称
			dept.setDeptName(deptEntity.getDeptName());
			// 修改备案编号
			dept.setFileId(deptEntity.getFileId());
			// 修改上级部门
			dept.setParentId(deptEntity.getParentId());
			// 修改行政区划
			dept.setAsts(deptEntity.getAsts());
			// 修改联系电话
			dept.setDeptPhone(deptEntity.getDeptPhone());
			// 修改联系地址
			dept.setDeptAddr(deptEntity.getDeptAddr());
			// 修改邮箱
			dept.setDeptEmail(deptEntity.getDeptEmail());
			// 修改网址
			dept.setDeptUrl(deptEntity.getDeptUrl());
			// 修改部门描述
			dept.setDeptIntr(deptEntity.getDeptIntr());
			// 修改部门信息，成功返回true 失败返回false
			if (deptService.updateDept(dept, request)){
				message.setData("true");
				return message;
			}else{
				message.setData("false");
				message.setErrorCode(ErrorInfo.S_SYS_EXCEPTION);
				return message;
			}
		}catch (Exception e){
			log.error(e);
			message.setErrorCode(ErrorInfo.S_SQL_EXCEPTION);
			e.printStackTrace();
			
			return message;
		}
		
	}
	
	/**
	 * 删除已存在但被删除的部门，然后更新部门
	 * 
	 * @param deptEntity
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deleteAndUpdateDept", produces = "application/json;charset=UTF-8")
	public Message<String> deleteAndUpdateDept(DeptEntity deptEntity, HttpServletRequest request) {
		Message<String> message=new Message<String>();
		try{
			// 插入操作日志
			logService.insertLog("删除部门", "部门管理", "物理删除部门信息", request);
			deptService.physicsDeleteDept(deptEntity);
			// 插入操作日志
			logService.insertLog("修改部门", "部门管理", "修改部门信息", request);
			// 根据部门编号查找部门信息
			DeptEntity dept = deptService.findDeptById(deptEntity);
			// 修改部门类型
			dept.setDeptType(deptEntity.getDeptType());
			// 修改部门名称
			dept.setDeptName(deptEntity.getDeptName());
			// 修改备案编号
			dept.setFileId(deptEntity.getFileId());
			// 修改上级部门
			dept.setParentId(deptEntity.getParentId());
			// 修改行政区划
			dept.setAsts(deptEntity.getAsts());
			// 修改联系电话
			dept.setDeptPhone(deptEntity.getDeptPhone());
			// 修改联系地址
			dept.setDeptAddr(deptEntity.getDeptAddr());
			// 修改邮箱
			dept.setDeptEmail(deptEntity.getDeptEmail());
			// 修改网址
			dept.setDeptUrl(deptEntity.getDeptUrl());
			// 修改部门描述
			dept.setDeptIntr(deptEntity.getDeptIntr());
			// 修改部门信息，成功返回true 失败返回false
			if (deptService.updateDept(dept, request)){
				message.setData("true");
				return message;
			}else{
				message.setData("false");
				message.setErrorCode(ErrorInfo.S_SYS_EXCEPTION);
				return message;
			}
		}catch (Exception e){
			log.error(e);
			message.setErrorCode(ErrorInfo.S_SQL_EXCEPTION);
			e.printStackTrace();
			
			return message;
		}
		
	}
}
