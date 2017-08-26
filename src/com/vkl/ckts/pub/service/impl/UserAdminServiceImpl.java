package com.vkl.ckts.pub.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.entity.UserRoleEntity;
import com.vkl.ckts.common.log.LogUtils;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.common.utils.Base64Utils;
import com.vkl.ckts.pub.dao.IUserAdminDao;
import com.vkl.ckts.pub.dto.UserAdmin;
import com.vkl.ckts.pub.service.IUserAdminService;
/**
 * 用户管理serviceImpl
 * @author xujunhua
 * @date 2017年4月10日
 * @ClassName: UserAdminServiceImpl
 */
@Service
@Transactional
public class UserAdminServiceImpl extends ServiceImpl<IUserAdminDao, UserAdmin, String> implements IUserAdminService {
	@Autowired
	IUserAdminDao iuad;	// 注入dao

	Logger log = Logger.getLogger(UserAdminServiceImpl.class);	// 日志
	/**
	 * 查询修改项
	 * @param id 用户编号
	 * @return
	 */
	public UserAdmin oneUser(String id){
		UserAdmin ua = iuad.oneUser(id);
		ua.setUserPhone(Base64Utils.decode(ua.getUserPhone()));		// 用户手机号解密
		ua.setIdentityCard(Base64Utils.decode(ua.getIdentityCard()));// 身份证号解密
		ua.setUserName(Base64Utils.decode(ua.getUserName()));		// 用户名解密
		ua.setLoginPwd(Base64Utils.decode(ua.getLoginPwd()));		// 用户密码解密
		return ua;
	}
	/**
	 * 添加用户
	 * @param ua 添加对象
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public void addUser(UserAdmin ua,HttpServletRequest request) throws Exception{
			ua.setUserName(Base64Utils.encode(ua.getUserName())); 		// 用户名加密
			ua.setIdentityCard(Base64Utils.encode(ua.getIdentityCard()));// 身份证号加密
			ua.setLoginPwd(Base64Utils.encode(ua.getLoginPwd())); 		// 用户密码加密
			ua.setUserPhone(Base64Utils.encode(ua.getUserPhone()));		// 手机号加密
			ua.setDelFlag(IdEntity.DEL_FLAG_NORMAL);					// 用户删除标志
			ua.setCreater(((UserEntity)request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId());	// 创建者
			ua.setCreateDate(new Date());																		// 创建时间
			ua.setUpdater(((UserEntity)request.getSession().getAttribute(Constrant.S_USER_SESSION)).getId());	// 修改者
			ua.setUpdDate(new Date());																			// 修改时间
		//	ua.setUserDept(((UserEntity)request.getSession().getAttribute(Constrant.S_USER_SESSION)).getUserDept());// 用户部门
			ua.setSfgly(IdEntity.S_NO);
			iuad.addUser(ua);	// 添加
			String[] role = ua.getuRole().split(",");
			List<UserRoleEntity> urList = new ArrayList<UserRoleEntity>();
			for(int i=0; i<role.length; i++){
				UserRoleEntity ure = new UserRoleEntity();
				ure.setId(ua.getId());
				ure.setRoleId(role[i]);
				urList.add(ure);
			}
			iuad.addURole(urList);
			LogUtils.insertLog("用户管理", "系统设置", "添加用户", request);
		
	}
	/**
	 * 逻辑删除用户，物理删除用户角色
	 * @param ua
	 * @param request
	 * @throws Exception
	 */
	public void deleUser(UserAdmin ua,HttpServletRequest request) throws Exception{
		ua.setDelFlag(IdEntity.DEL_FLAG_DELETE);	// 给对象赋删除标志
		iuad.delUser(ua);	// 逻辑删除用户
		UserRoleEntity ur = new UserRoleEntity();
		ur.setId(ua.getId());	// 封装用户角色用户id
		iuad.delURole(ur); 		// 物理删除用户角色
		LogUtils.insertLog("用户管理", "系统设置", "删除用户", request);
	}
	/**
	 * 修改用户 
	 * 
	 * @param ua
	 */
	public void updateUser(UserEntity ua){
		iuad.userUpd(ua);
	}
	
	/**
	 * 修改用户信息
	 * @param ua 修改对象
	 * @param request
	 * @throws Exception
	 */
	public void userUpd(UserAdmin ua,HttpServletRequest request) throws Exception{
		UserEntity userEntity=iuad.oneUser(ua.getId());
		userEntity.setUserTele(ua.getUserTele());
		userEntity.setFuzz(ua.getFuzz());
		userEntity.setUserAddr(ua.getUserAddr());
		userEntity.setUserStatu(ua.getUserStatu());
		userEntity.setUserSex(ua.getUserSex());
		userEntity.setUserDept(ua.getUserDept());
		// 修改时间
		userEntity.setUpdDate(new Date());
		userEntity.setIdentityCard(Base64Utils.encode(ua.getIdentityCard()));// 身份证号加密
		userEntity.setUserName(Base64Utils.encode(ua.getUserName())); 		// 用户名加密
		userEntity.setUserPhone(Base64Utils.encode(ua.getUserPhone()));		// 手机号加密
		userEntity.setId(ua.getId());
		iuad.userUpd(userEntity);
		UserAdmin oldUa = iuad.oneUser(ua.getId());	// 修改前的对象信息
		String[] oldRole = new String [oldUa.getRoles().size()];	// 修改前用户角色数组
		for(int i=0; i < oldRole.length; i++){
			oldRole[i] = oldUa.getRoles().get(i).getId();	// 封装进数组
		}
		String[] role = ua.getuRole().split(",");	// 修改后的对象角色
		List<UserRoleEntity> list = new ArrayList<UserRoleEntity>();
		// 判断用户角色是否有新增
		for(int i=0; i < role.length; i++){	
			// 角色不为空
			if(role[i] != null && !"".equals(role[i])){
				// 修改前用户角色不包含修改后的用户角色，有新增角色
				if(!Arrays.asList(oldRole).contains(role[i])){
					UserRoleEntity ure = new UserRoleEntity();
					ure.setId(ua.getId());
					ure.setRoleId(role[i]);
					list.add(ure);
				}
			}
			
		}
		// 有新增
		if(list.size() > 0){
			iuad.addURole(list); 	// 新增用户角色
		}
		// 判断用户角色是否有删除
		for(int i=0; i < oldRole.length; i++){
			// 修改后用户角色不包含修改前用户角色，有删除
			if(!Arrays.asList(role).contains(oldRole[i])){
				UserRoleEntity ure = new UserRoleEntity();
				ure.setId(ua.getId());
				ure.setRoleId(oldRole[i]);
				iuad.delURole(ure);		// 删除用户角色
			}
		}
		LogUtils.insertLog("用户管理", "系统设置", "修改用户信息", request);
	}
	
	
	
	@Override
	public void updatePwd(UserAdmin ua) throws Exception {
		// TODO Auto-generated method stub
		UserEntity userEntity=iuad.oneUser(ua.getId());
		userEntity.setLoginPwd(Base64Utils.encode("cyxtyh"));
		userEntity.setId(ua.getId());
		iuad.userUpd(userEntity);
	}
}
