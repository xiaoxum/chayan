package com.vkl.ckts.pub.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.dao.ISysConfigEntityDao;
import com.vkl.ckts.common.entity.DeptEntity;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.entity.SysConfigEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.common.utils.Base64Utils;
import com.vkl.ckts.pub.dao.IBaseDao;
import com.vkl.ckts.pub.dto.MdxxDto;
import com.vkl.ckts.pub.service.IBaseService;

/**
 * 用户公用serviceImpl
 * 
 * @author hwf
 * @version 1.0
 */
@Service
@Transactional
public class BaseServiceImpl extends ServiceImpl<IBaseDao, UserEntity, java.lang.String> implements IBaseService {

	@Autowired
	ISysConfigEntityDao iSysConfigEntityDao;
	/**
	 * 用户登录验证
	 * 
	 * @param userEntity
	 *            用户实体
	 * @return 用户名不存在，在线，登录成功，密码输入错误
	 * @throws Exception 
	 */
	@Override
	public String checkLogin(UserEntity userEntity, HttpServletRequest request) throws Exception {
		UserEntity user = null; // 定义一个用户实体user用来接收查找返回的结果
		// 根据传入的用户实体参数查找结果并赋值给user
		user = super.dao.findUserByLoginName(userEntity);
		if (user == null||"1".equals(user.getDelFlag())) {
			return "用户名不存在";
			// 将传入的密码进行Base64加密后与数据库中的密码进行匹配，ture则密码正确，false密码错误
		} else if (Base64Utils.encode(userEntity.getLoginPwd()).equals(user.getLoginPwd())) {
			// 从request中获取session
			HttpSession session = request.getSession();
			DeptEntity deptEntity = new DeptEntity();
			deptEntity.setId(user.getUserDept().toString());
			deptEntity = super.dao.findDeptById(deptEntity);
			// 将查找出来的user存入session
			session.setAttribute(Constrant.S_USER_SESSION, user);
			session.setAttribute("userName", Base64Utils.decode(user.getUserName()));
			// 将查找出来的deptName存入session
			if(deptEntity != null){
				session.setAttribute("userDeptName", deptEntity.getDeptName());
			}
			if (IdEntity.U_USER_STATU_UNUSE.equals(user.getUserStatu())) {
				return "当前用户状态异常，请联系管理员";
			}
			// true表示在线，false表示不在线
//			if (IdEntity.S_YES.equalsIgnoreCase(user.getIsOnline())) {
//				return "在线";
//			} else {
//				// 设置为在线
//				user.setIsOnline(IdEntity.S_YES);
//				super.dao.updateOnlineState(user);
//
//				return "登录成功";
//			}
			return "登录成功";
		} else {
			return "密码输入错误";
		}
	}
	/**
	 * 修改用户登录ip和最近登录时间
	 * 
	 * @param userEntity
	 */
	public void updateLoginIpAndTime(UserEntity userEntity) {
		super.dao.updateLoginIpAndTime(userEntity);
	}

	/**
	 * 下线
	 * 
	 * @param userEntity
	 */
	public void offLine(UserEntity userEntity) {
		userEntity.setIsOnline(IdEntity.S_NO);
		super.dao.updateOnlineState(userEntity);
	}

	/**
	 * 添加用户
	 *
	 * @param userEntity
	 * @return
	 */
	@Override
	public boolean addUser(UserEntity userEntity) throws Exception {
		if (super.dao.addUser(userEntity) > 0) {
			return true;
		}
		return false;
	}
     
	/**
	 * 修改密码
	 * @param id 用户id
	 * @param password 密码
	 * @param newpassword 新密码
	 * @return
	 */
	@Override
	public String uppwdAction(String password, String newpassword,String id) {
		String error = null;
		// 根据用户ID 查询用户信息
		UserEntity user = super.dao.findUserByid(id);
		
	    //原密码进行比较
		if(!password.equals(user.getLoginPwd())){
		    
		error = "原密码不正确!";
			
		return error;
			
		}
		
		 super.dao.upPasswordByid(id, newpassword);
		  
		 error = "密码修改成功!";
		 
		 return error;
		                   
	}

	/**
	 * 查找当前登录用户所属部门类型
	 *
	 * @param userEntity
	 * @return
	 * @throws Exception
	 */
	public String findUserDeptType(UserEntity userEntity) throws Exception{
		return super.dao.findUserDeptType(userEntity).toString();
	}


	/**
	 * 根据用户名查找用户
	 * 
	 * @param userEntity
	 *            用户实体
	 * @return userEntity 用户实体
	 */
	@Override
	public UserEntity findUserByLoginName(UserEntity userEntity) {
		return super.dao.findUserByLoginName(userEntity);
	}
	/**
	 * 查询系统设置当前状态 
	 * @return
	 */
	public List<SysConfigEntity> selSet(){
		return iSysConfigEntityDao.selSet();
	}
	/**
	 * 修改本地设置
	 * 
	 * @return 
	 */
	@RequestMapping("/sysup")
	public String sysup(String key,String value){
		String[] keys = key.split(",");     //分割key
		String[] values = value.split(","); //分割val
		// 循环修改配置表
		for(int i=0;i<keys.length;i++){
			SysConfigEntity sysConfigEntity = new SysConfigEntity();
			sysConfigEntity.setKey(keys[i]);   //系统参数配置对象
			sysConfigEntity.setValue(values[i]);
			iSysConfigEntityDao.updateData(sysConfigEntity); 
			
		}
		return "true";
	}
	
	
	
	/**
	 * 查询菜单信息
	 * 
	 * @param yhbh  用户编号
	 * 
	 * @return
	 */
	@Override
	public List<MdxxDto> findMdxx(String yhbh) {
		// TODO Auto-generated method stub
		return super.dao.findMdxx(yhbh);
	}
	/**
	 * 根据父级菜单查询子菜单信息
	 * 
	 * @param yhbh   用户编号
	 * @param zcdId  子菜单信息
	 * 
	 * @return
	 */
	@Override
	public List<MdxxDto> findZcdxx(String yhbh,String zcdId) {
		// TODO Auto-generated method stub
		return super.dao.findZcdxx(yhbh,zcdId);
	}
	
	
	/**
	 * 查询菜单信息
	 * 
	 * @param yhbh  用户编号
	 * 
	 * @return
	 */
	@Override
	public List<MdxxDto> findMdxx2() {
		// TODO Auto-generated method stub
		return super.dao.findMdxx2();
	}
	/**
	 * 根据父级菜单查询子菜单信息
	 * 
	 * @param zcdId  子菜单信息
	 * 
	 * @return
	 */
	@Override
	public List<MdxxDto> findZcdxx2(String fcdId) {
		// TODO Auto-generated method stub
		return super.dao.findZcdxx2(fcdId);
	}
	
	
	
	
}
