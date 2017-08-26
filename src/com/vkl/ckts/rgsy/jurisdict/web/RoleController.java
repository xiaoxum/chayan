package com.vkl.ckts.rgsy.jurisdict.web;

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
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.entity.MenuEntity;
import com.vkl.ckts.common.entity.RoleEntity;
import com.vkl.ckts.common.entity.RoleMenuEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.pub.service.IBaseService;
import com.vkl.ckts.rgsy.jurisdict.entity.RoleDto;
import com.vkl.ckts.rgsy.jurisdict.service.IMenuService;
import com.vkl.ckts.rgsy.jurisdict.service.IRoleService;
import com.vkl.ckts.rgsy.record.entity.Tree;
import com.vkl.ckts.rgsy.statistic.log.service.ILogService;

/**
 * 角色管理Controller
 *
 * @author hwf
 * @version 1.0
 */
@Controller
@RequestMapping("module/role")
public class RoleController {

	// 注入权限Service
	@Autowired
	IMenuService menuService;
	// 注入角色Service
	@Autowired
	IRoleService roleService;
	// 注入用户Service
	@Autowired
	IBaseService baseService;
	// 注入操作日志
	@Autowired
	ILogService logService;
	// 获取日志对象
	Logger log = Logger.getLogger(RoleController.class);

	/**
	 * 角色管理页面初始化
	 *
	 * @return
	 */
	@RequestMapping("jsgl")
	public String jsgl() {
		return "com/vkl/ckts_pc/rgsy/jsgl";
	}

	/**
	 * 添加角色页面初始化
	 *
	 * @return
	 */
	@RequestMapping("jsadd")
	public String jsadd() {
		return "com/vkl/ckts_pc/rgsy/jsadd";
	}

	/**
	 * 获取菜单树形下拉框
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findMenuList", produces = "application/json;charset=utf-8")
	public List<Tree> findMenuList(HttpServletRequest request, MenuEntity menuEntity) {
		// 根据父id查找子菜单
		List<MenuEntity> menuList;
		// 构建返回的树形集合
		List<Tree> treeList = new ArrayList<Tree>();
		try {
			UserEntity userEntity = (UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION);
			String yhbh = "";
			if (!"1".equals(userEntity.getSfgly())) {
				yhbh = userEntity.getId();
			}
			menuList = roleService.findMenuListByParentId(menuEntity.getParentMenu()+"",yhbh);
			// 遍历部门集合
			for (int i = 0; i < menuList.size(); i++) {
				// 树实体
				Tree tree = new Tree();
				// 获取菜单名称
				String menuName = menuList.get(i).getMenuName();
				// 设置树的id为菜单的id
				tree.setId(Integer.parseInt(menuList.get(i).getId()));
				// 设置树的内容为菜单名称
				tree.setText(menuName);
				// 查找当前菜单的子菜单集合
				menuEntity.setParentMenu(Integer.parseInt(menuList.get(i).getId()));
				List<MenuEntity> subMenuList = menuService.findMenuListByParentId(menuEntity);
				// 如果当前菜单有子菜单，设置树的状态为关闭，否则为打开
				if (subMenuList.size() <= 0) {
					// 设置树的状态：open为打开，closed为关闭
					tree.setState("open");
				} else {
					// 设置树的状态：open为打开，closed为关闭
					tree.setState("open");
				}
				// 遍历二级菜单给子树赋值
				for (int j = 0; j < subMenuList.size(); j++) {
					// 递归调用当前方法查找子树
					List<Tree> children = findMenuList(request, subMenuList.get(j));
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
			log.error(e);
			e.printStackTrace();

			return treeList;
		}

	}

	
	
	
	
	/**
	 * 判断角色是否存在
	 *
	 * @param roleEntity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("ckRoleExist")
	public Message<String> ckRoleExist(RoleEntity roleEntity, HttpServletRequest request) {
		Message<String> message = new Message<String>();
		try {
			// 判断当前用户的所属部门类型，如果是市车管所或者县区车管所则赋值为车管所，否则赋值为查验站
			String deptType = baseService.findUserDeptType((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION));
			if (deptType.equals(IdEntity.DEPT_TYPE_CITY_OFFICE) || deptType.equals(IdEntity.DEPT_TYPE_COUNTY_OFFICE)) {
				deptType = IdEntity.S_MENU_OWER_VEHICLE_OFFICE;
			} else {
				deptType = IdEntity.S_MENU_OWER_CHECK_OFFICE;
			}
			roleEntity.setRoleOwer(deptType);
			if (roleService.findRole(roleEntity) != null) {
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
	 * 添加角色
	 *
	 * @param request
	 * @param deptEntity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("submit")
	public Message<String> submit(HttpServletRequest request, RoleDto roleEntity, RoleMenuEntity roleMenuEntity) {
		Message<String> message = new Message<String>();
		try {
			// 插入操作日志
			logService.insertLog("添加角色", "角色管理", "添加角色", request);
			// 添加角色，true：成功，false：失败
			roleService.addRole(roleEntity, request);
			String roleId = roleEntity.getId();
			if (roleId != "" && roleId != null) {
				roleMenuEntity.setId(roleId);
				String menuIds[] = roleMenuEntity.getMenuId().split(",");
				for (int i = 0; i < menuIds.length; i++) {
					roleMenuEntity.setMenuId(menuIds[i]);
					roleService.addRoleMenu(roleMenuEntity);
				}
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
	 * 加载角色
	 *
	 * @param roleEntity
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findRoleList", produces = "application/json;charset=UTF-8")
	public String findRoleList(RoleDto roleEntity, HttpServletRequest request) {
		try {
			// 获取角色List
			List<RoleEntity> roleList = roleService.findRoleList(roleEntity, request);
			JSONArray jsonList = new JSONArray();
			// 遍历角色集合，插入到json集合中
			for (RoleEntity role : roleList) {
				jsonList.add(role);
			}

			return jsonList.toJSONString();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();

			return null;
		}
	}

	/**
	 * 分页查找角色信息
	 * 
	 * @param pageSize
	 *            每页显示的数据个数
	 * @param roleEntity
	 * @return map
	 */
	@ResponseBody
	@RequestMapping(value = "pageFindRole", produces = "application/json;charset=UTF-8")
	public Map<String, Object> pageFindRole(Integer pageSize, Integer pageNumber, RoleDto roleEntity, Page<RoleDto> page,
			HttpServletRequest request) {

		try {
		
			UserEntity userEntity = (UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION);
			// 判断当前用户的所属部门类型，如果是市车管所或者县区车管所则赋值为车管所，否则赋值为查验站
			
			if ("1".equals(userEntity.getSfgly())) {
				roleEntity.setDeptId(null);
			}else {
				roleEntity.setDeptId(userEntity.getUserDept()+"");
			}
			Map<String, Object> map = new HashMap<String, Object>();
			// 设置分页的实体
			page.setT(roleEntity);
			// 设置分页的当前页号
			page.setPageNo(pageNumber);
			// 设置分页的页面大小
			page.setPageSize(pageSize);
			// 分页
			page = roleService.pageRole(page);
			// 分页后的数据
			map.put("list", page.getList());
			// 总记录数
			map.put("total", roleService.findCount(roleEntity));

			return map;
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();

			return null;
		}

	}

	/**
	 * 删除角色信息
	 * 
	 * @param pageSize
	 *            页面大小
	 * @param roleEntity
	 *            角色实体
	 * @return map
	 */
	@ResponseBody
	@RequestMapping(value = "deleteRole", produces = "application/text;charset=UTF-8")
	public String deleteRole(Integer pageSize, Integer pageNumber, RoleDto roleEntity, Page<RoleDto> page,
			HttpServletRequest request) {

		try {
			// 插入操作日志
			logService.insertLog("删除角色", "角色管理", "删除角色信息", request);
			
			// 设置角色所属
			// 判断当前用户的所属部门类型，如果是市车管所或者县区车管所则赋值为车管所，否则赋值为查验站
			String deptType = baseService.findUserDeptType((UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION));
			if (deptType.equals(IdEntity.DEPT_TYPE_CITY_OFFICE) || deptType.equals(IdEntity.DEPT_TYPE_COUNTY_OFFICE)) {
				deptType = IdEntity.S_MENU_OWER_VEHICLE_OFFICE;
			} else {
				deptType = IdEntity.S_MENU_OWER_CHECK_OFFICE;
			}
			roleEntity.setRoleOwer(deptType);
			// 删除角色
			roleService.deleteRoleById(roleEntity);
//			RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
//			roleMenuEntity.setId(roleEntity.getId());
//			// 删除角色权限
//			roleService.deleteRoleMenu(roleMenuEntity);
//			Integer total = roleService.findCount(roleEntity);
//			// 如果数据总数除以页面大小余数为0说明删除的是当前页面的最后一条数据，删除后当前页需要减一
//			if (total % pageSize == 0) {
//				pageNumber = pageNumber - 1;
//			}
//			
//			UserEntity userEntity = (UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION);
//			// 判断当前用户的所属部门类型，如果是市车管所或者县区车管所则赋值为车管所，否则赋值为查验站
//			
//			if ("1".equals(userEntity.getSfgly())) {
//				roleEntity.setDeptId(null);
//			}else {
//				roleEntity.setDeptId(userEntity.getUserDept()+"");
//			}
//			Map<String, Object> map = new HashMap<String, Object>();
//			// 设置分页的实体
//			page.setT(roleEntity);
//			// 设置分页的当前页号
//			page.setPageNo(pageNumber);
//			// 设置分页的页面大小
//			page.setPageSize(pageSize);
//			// 分页
//			page = roleService.pageRole(page);
//			/*
//			 * // 遍历分页结果 for (RoleEntity role : page.getList()) { RoleMenuEntity
//			 * roleMenuEntity = new RoleMenuEntity();
//			 * roleMenuEntity.setId(role.getId()); // 查找角色的权限集合
//			 * List<RoleMenuEntity>
//			 * roleMenulist=roleService.findRoleMenuList(roleMenuEntity);
//			 * List<MenuEntity> menuList =new ArrayList<MenuEntity>();
//			 * for(RoleMenuEntity roleMenu:roleMenulist){ MenuEntity menuEntity
//			 * = new MenuEntity(); menuEntity.setId(roleMenu.getMenuId()); //
//			 * 根据权限id查找权限 menuList.add(menuService.findMenuById(menuEntity)); }
//			 * role.setMenus(menuList); }
//			 */
//			// 分页后的数据
//			map.put("list", page.getList());
//			// 总记录数
//			map.put("total", total);
			return "success";
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return "sys-error";
		}

	}

	/**
	 * 根据部门编号查找部门信息
	 * 
	 * @param roleEntity
	 * @param request
	 * @return 跳转到部门修改页面
	 */
	@RequestMapping("findRoleById")
	public String findRoleById(RoleEntity roleEntity, HttpServletRequest request) {
		try {
			// 根据id查找角色
			roleEntity = roleService.findRoleById(roleEntity);
			RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
			roleMenuEntity.setId(roleEntity.getId());
			// 查找角色的权限集合
			List<RoleMenuEntity> roleMenulist = roleService.findRoleMenuList(roleMenuEntity);
			StringBuffer menuId = new StringBuffer();
			for (int i = 0; i < roleMenulist.size(); i++) {
				if (i == 0) {
					menuId.append(roleMenulist.get(i).getMenuId());
				} else {
					menuId.append("," + roleMenulist.get(i).getMenuId());
				}

			}
			request.setAttribute("role", roleEntity);
			request.setAttribute("menuId", menuId);
			
			return "com/vkl/ckts_pc/rgsy/jsxg";
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();

			return null;
		}

	}

	/**
	 * 更新角色
	 * 
	 * @param roleEntity
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateRole", produces = "application/json;charset=UTF-8")
	public Message<String> updateRole(RoleEntity roleEntity, HttpServletRequest request, RoleMenuEntity roleMenuEntity) {
		Message<String> message=new Message<String>();
		try{
			// 插入操作日志
			logService.insertLog("修改角色", "角色管理", "修改角色信息", request);
			// 根据部门编号查找部门信息
			RoleEntity role = roleService.findRoleById(roleEntity);
			// 角色名称
			role.setRoleName(roleEntity.getRoleName());
			// 任务描述
			role.setRoleIntr(roleEntity.getRoleIntr());
			
			// 修改部门信息，成功返回true 失败返回false
			if (roleService.updateRole(role, request)){
				// 删除角色权限
				roleMenuEntity.setId(roleEntity.getId());
				roleService.deleteRoleMenu(roleMenuEntity);
				// 添加角色权限
				String menuIds[] = roleMenuEntity.getMenuId().split(",");
				for (int i = 0; i < menuIds.length; i++) {
					roleMenuEntity.setMenuId(menuIds[i]);
					roleService.addRoleMenu(roleMenuEntity);
				}	
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
