package com.vkl.ckts.pub.service;

import javax.servlet.http.HttpServletRequest;





import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.pub.dao.IUserAdminDao;
import com.vkl.ckts.pub.dto.UserAdmin;
/**
 * 用户管理service
 * @author xujunhua
 * @date 2017年4月10日
 * @ClassName: IUserAdminService
 */
public interface IUserAdminService extends IService<IUserAdminDao, UserAdmin, String>{
	/**
	 * 查询修改项
	 * @param id 用户编号
	 * @return
	 */
	public UserAdmin oneUser(String id);
	
	/**
	 * 添加用户
	 * @param ua 添加对象
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public void addUser(UserAdmin ua,HttpServletRequest request) throws Exception;
	/**
	 * 逻辑删除用户，物理删除用户角色
	 * @param ua
	 * @param request
	 * @throws Exception
	 */
	public void deleUser(UserAdmin ua,HttpServletRequest request) throws Exception;
	/**
	 * 修改用户信息
	 * @param ua 修改对象
	 * @param request
	 * @throws Exception
	 */
	public void userUpd(UserAdmin ua,HttpServletRequest request) throws Exception;
	
	/**
	 * 修改用户信息
	 * @param ua 修改对象
	 * @param request
	 * @throws Exception
	 */
	public void updatePwd(UserAdmin ua) throws Exception;
	
	
	/**
	 * 修改用户 
	 * 
	 * @param ua
	 */
	void updateUser(UserEntity ua);
}
