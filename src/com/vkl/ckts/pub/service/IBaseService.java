package com.vkl.ckts.pub.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vkl.ckts.common.entity.SysConfigEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.pub.dao.IBaseDao;
import com.vkl.ckts.pub.dto.MdxxDto;

/**
 * 用户公用Service
 * 
 * @author hwf
 * @version 1.0
 */
public interface IBaseService extends IService<IBaseDao,UserEntity,String>{
	

	/**
	 * 根据用户名查找用户
	 * 
	 * @param userEntity
	 *            用户实体
	 * @return userEntity 用户实体
	 */
	UserEntity findUserByLoginName(UserEntity userEntity);
	
	/**
	 * 用户登录验证
	 * 
	 * @param userEntity 用户实体
	 * @return 用户名不存在，在线，登录成功，密码输入错误
	 */
	String checkLogin(UserEntity userEntity,HttpServletRequest request) throws Exception;
	
	/**
	 * 修改用户登录ip和最近登录时间
	 * 
	 * @param userEntity 用户实体
	 */
	void updateLoginIpAndTime(UserEntity userEntity);
	
	/**
	 * 下线
	 * 
	 * @param userEntity 用户实体
	 */
	void offLine(UserEntity userEntity);
	
	/**
	 * 添加用户
	 *
	 * @param userEntity
	 * @return
	 */
	boolean addUser(UserEntity userEntity) throws Exception;

	/**
	 * 修改密码
	 * @param id 用户id
	 * @param password 密码
	 * @param newpassword 新密码
	 * @return
	 */
	String uppwdAction(String password,String newpassword,String id);
	
	

	
	/**
	 * 查找当前登录用户所属部门类型
	 *
	 * @param userEntity
	 * @return
	 * @throws Exception
	 */
	String findUserDeptType(UserEntity userEntity) throws Exception;
	/**
	 * 查询系统设置当前状态 
	 * @return
	 */
	public List<SysConfigEntity> selSet();
	
	
	/**
	 * 修改本地设置
	 * @param key 
	 * @param value 
	 * @return 
	 */
	@RequestMapping("/sysup")
	public String sysup(String key,String value);
	
	
	
	/**
	 * 查询菜单信息
	 * 
	 * @param yhbh  用户编号
	 * 
	 * @return
	 */
	List<MdxxDto>  findMdxx(String yhbh);
	
	/**
	 * 根据父级菜单查询子菜单信息
	 * 
	 * @param yhbh   用户编号
	 * @param zcdId  子菜单信息
	 * 
	 * @return
	 */
	List<MdxxDto>  findZcdxx(String yhbh,String zcdId);
	
	/**
	 * 查询菜单信息
	 * 
	 * @param yhbh  用户编号
	 * 
	 * @return
	 */
	List<MdxxDto>  findMdxx2();
	/**
	 * 根据父级菜单查询子菜单信息
	 * 
	 * @param zcdId  子菜单信息
	 * 
	 * @return
	 */
	List<MdxxDto>  findZcdxx2(@Param(value="fcdId")String fcdId);

}
