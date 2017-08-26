package com.vkl.ckts.rgsy.record.dao;

import java.util.List;



import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.common.entity.DeptEntity;
import com.vkl.ckts.common.entity.ViewerFileEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.rgsy.record.entity.ViewerFileDto;

/**
 * 查验员备案Dao
 * 
 * @author hwf
 * @version 1.0
 */
@Mybatis
public interface IViewerFileDao extends Dao<ViewerFileEntity, java.lang.String> {
	
	/**
	 * 添加查验员备案申请
	 *
	 * @param viewerFileEntity
	 * @return 插入成功的数据个数
	 */
	Integer addViewerFile(ViewerFileDto viewerFileEntity) throws Exception;

	/**
	 * 分页查询查验员备案信息
	 * 
	 * @param page 分页工具实体
	 * @return 查验员集合
	 */
	List<ViewerFileDto> pageViewerFile(Page<ViewerFileDto> page) throws Exception;

	/**
	 * 根据申请id逻辑删除备案信息
	 * 
	 * @param viewerFileEntity
	 */
	void deleteViewerFileById(ViewerFileEntity viewerFileEntity) throws Exception;
	
	/**
	 * 根据条件查找相应查验区备案通过的查验员
	 *
	 * @param viewerFileEntity
	 * @return
	 * @throws Exception
	 */
	List<ViewerFileDto> findViewerFileList(ViewerFileDto viewerFileEntity) throws Exception;

	/**
	 *  根据申请id查找备案信息
	 *  
	 * @param viewerFileEntity
	 * @return viewerFileEntity
	 */
	ViewerFileDto findViewerFileById(ViewerFileDto viewerFileEntity) throws Exception;

	/**
	 *  更新备案信息
	 *  
	 * @param viewerFileEntity
	 */
	void updateViewerFile(ViewerFileEntity viewerFileEntity) throws Exception;
	
	/**
	 * 根据备案编号查找查验员
	 *
	 * @param fileId
	 * @return
	 * @throws Exception
	 */
	ViewerFileDto findViewerFileByFileId(ViewerFileDto viewerFileEntity) throws Exception;
	
	/**
	 * 查询部门信息
	 * 
	 * @param fileId
	 * @return
	 */
	DeptEntity finDeptEntity(String fileId);
	

}
