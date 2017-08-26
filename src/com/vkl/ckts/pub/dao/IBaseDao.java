package com.vkl.ckts.pub.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.DeptEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.pub.dto.MdxxDto;

/**
 * 用户公用Dao
 * 
 * @author hwf
 * @version 1.0
 */
@Mybatis
public interface IBaseDao extends Dao<UserEntity, java.lang.String> {

	/**
	 * 根据用户名查找用户
	 * 
	 * @param userEntity
	 *            用户实体
	 * @return userEntity 用户实体
	 */
	UserEntity findUserByLoginName(UserEntity userEntity);

	/**
	 * 修改用户在线状态
	 * 
	 * @param userEntity
	 */
	void updateOnlineState(UserEntity userEntity);

	/**
	 * 修改用户登录ip和最近登录时间
	 * 
	 * @param userEntity
	 */
	void updateLoginIpAndTime(UserEntity userEntity);

	/**
	 * 添加用户
	 *
	 * @param userEntity
	 * @return
	 */
	Integer addUser(UserEntity userEntity) throws Exception;
	
	/**
	 * 根据用户id查找用户
	 * 
	 * @param id 用户id
	 * @return userEntity 用户实体
	 */
	UserEntity findUserByid(String id);
	
	
	/**
	 * 根据用户id修改密码
	 * 
	 * @param id 用户id
	 * @param newpassword 新密码
	 * @return Integer
	 */
	 Integer upPasswordByid(@Param(value="id")String id,@Param(value="newpassword")String newpassword);

	/**
	 * 查找当前登录用户所属部门类型
	 *
	 * @param userEntity
	 * @return
	 * @throws Exception
	 */
	Integer findUserDeptType(UserEntity userEntity) throws Exception;
	
	/**
	 * 根据id查找部门
	 *
	 * @param deptEntity
	 * @return
	 * @throws Exception
	 */
	DeptEntity findDeptById(DeptEntity deptEntity) throws Exception;
	
	
	
	
	
	/**
	 * 查询菜单信息
	 * 
	 * @param yhbh  用户编号
	 * 
	 * @return
	 */
	List<MdxxDto>  findMdxx(@Param(value="yhbh")String yhbh);
	
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
	 * @param yhbh   用户编号
	 * @param zcdId  子菜单信息
	 * 
	 * @return
	 */
	List<MdxxDto>  findZcdxx(@Param(value="yhbh")String yhbh,@Param(value="fcdId")String fcdId);
	
	/**
	 * 根据父级菜单查询子菜单信息
	 * 
	 * @param zcdId  子菜单信息
	 * 
	 * @return
	 */
	List<MdxxDto>  findZcdxx2(@Param(value="fcdId")String fcdId);

}
