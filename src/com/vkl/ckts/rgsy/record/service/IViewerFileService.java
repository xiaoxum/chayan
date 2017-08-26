package com.vkl.ckts.rgsy.record.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.vkl.ckts.common.entity.ViewerFileEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.rgsy.record.dao.IViewerFileDao;
import com.vkl.ckts.rgsy.record.entity.ViewerFileDto;

/**
 * 查验员备案Service
 * 
 * @author hwf
 * @version 1.0
 */
public interface IViewerFileService extends IService<IViewerFileDao, ViewerFileEntity, String> {
	
	/**
	 * 添加查验员备案申请
	 *
	 * @param viewerFileEntity 查验员实体
	 * @param request
	 * @return true：添加成功，false：添加失败
	 */
	boolean addViewerFile(ViewerFileDto viewerFileEntity, HttpServletRequest request) throws Exception;

	/**
	 * 分页查询查验员备案信息
	 *
	 * @param page 分页工具
	 * @return 用page封装的结果
	 */
	Page<ViewerFileDto> pageViewerFile(Page<ViewerFileDto> page) throws Exception;

	/**
	 * 根据申请id逻辑删除备案信息
	 * 
	 * @param viewerFileEntity
	 */
	void deleteViewerFileById(ViewerFileEntity viewerFileEntity) throws Exception;

	/**
	 * 根据申请id查找备案信息
	 * 
	 * @param viewerFileEntity
	 * @return viewerFileEntity
	 */
	ViewerFileDto findViewerFileById(ViewerFileDto viewerFileEntity) throws Exception;

	/**
	 * 更新备案信息
	 *
	 * @param viewerFileEntity
	 * @param request
	 * @return true：更新成功，fasle：更新失败
	 */
	boolean updateViewerFile(ViewerFileEntity viewerFileEntity, HttpServletRequest request) throws Exception;
	
	/**
	 * 根据条件查找相应查验区备案通过的查验员
	 *
	 * @param viewerFileEntity
	 * @return
	 * @throws Exception
	 */
	List<ViewerFileDto> findViewerFileList(ViewerFileDto viewerFileEntity) throws Exception;
	
	/**
	 * 根据备案编号查找查验员
	 *
	 * @param viewerFileEntity
	 * @return
	 * @throws Exception
	 */
	ViewerFileDto findViewerFileByFileId(ViewerFileDto viewerFileEntity) throws Exception;

}
