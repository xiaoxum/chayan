package com.vkl.ckts.pub.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.entity.UserRoleEntity;
import com.vkl.ckts.pub.dto.UserAdmin;
/**
 * 用户管理dao
 * @author xujunhua
 * @date 2017年4月10日
 * @ClassName: IUserAdminDao
 */
@Mybatis
public interface IUserAdminDao extends Dao<UserAdmin,String>{
	/**
	 * 查询修改项
	 * @param id 用户编号
	 * @return
	 */
	UserAdmin oneUser(@Param("id") String id);
	/**
	 * 添加用户
	 * @param ua 添加对象
	 * 
	 */
	void addUser(UserAdmin ua);
	/**
	 * 插入用户角色
	 * @param list
	 */
	void addURole(List<UserRoleEntity> list);
	/**
	 * 逻辑删除用户
	 * @param ua 删除对象
	 */
	void delUser(UserAdmin ua);
	/**
	 * 删除用户角色
	 * @param ure 删除对象
	 */
	void delURole(UserRoleEntity ure);
	/**
	 * 修改用户
	 * @param ua
	 */
	void userUpd(UserEntity ua);
	
}
