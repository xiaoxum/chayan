package com.vkl.ckts.cksy.attractfunction.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vkl.ckts.cksy.attractfunction.dao.IAttractFunctionDao;
import com.vkl.ckts.cksy.attractfunction.entity.AttactPhoto;
import com.vkl.ckts.cksy.attractfunction.service.IAttactFunctionService;
import com.vkl.ckts.common.entity.DeptEntity;
import com.vkl.ckts.common.page.Page;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.common.utils.PropertiesUtils;


/**
 * 附加功能实现业务层
 * 
 * @author xiaoxu
 *
 */
@Service
public class AttactFunctionServiceImpl extends ServiceImpl<IAttractFunctionDao, AttactPhoto, String> implements IAttactFunctionService{

	/**
	 * 附加拍照查看
	 * 
	 */
	@Override
	public Page<AttactPhoto> findAttactPhoto(Page<AttactPhoto> page) {
		// TODO Auto-generated method stub
		List<AttactPhoto> lists=super.dao.findAttactPhoto(page);
		String url = PropertiesUtils.getValues("fileTomcatUrl");
		if (lists!=null) {
			for (AttactPhoto attactPhoto : lists) {
				attactPhoto.setPhotoUrl(url+"/"+attactPhoto.getPhotoUrl());
			}
		}
		
		page.setList(lists);
		page.setTotalCount(super.dao.findCount(page.getT()));
		return page;
	}

	@Override
	public DeptEntity findDeptById(String id) {
		// TODO Auto-generated method stub
		return super.dao.findDeptById(id);
	}

	
	
	
	
	
	
	
	
}
