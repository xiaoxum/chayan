package com.vkl.ckts.rgsy.system.photosetting.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.entity.CehUsnrEntity;
import com.vkl.ckts.common.entity.CkCllxEntity;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.entity.OperTypeEntity;
import com.vkl.ckts.common.entity.PicProjectEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.rgsy.statistic.log.service.ILogService;
import com.vkl.ckts.rgsy.system.checksetting.dao.ICheckSettingDao;
import com.vkl.ckts.rgsy.system.checksetting.service.ICheckSettingService;
import com.vkl.ckts.rgsy.system.photosetting.dao.IPhotoSetDao;
import com.vkl.ckts.rgsy.system.photosetting.entity.OperPicProject;
import com.vkl.ckts.rgsy.system.photosetting.service.IPhotoSetService;
@Transactional
@Service
public class PhotoSetServiceImpl extends ServiceImpl<IPhotoSetDao, OperPicProject, String> implements IPhotoSetService{
	@Autowired
	IPhotoSetDao ipd;
	@Autowired
	ICheckSettingService css;
	@Autowired
	ILogService ils;
	
	@Autowired
	ICheckSettingDao checkDao;
	/**
	 * 查询所有拍照项
	 */
	public List<PicProjectEntity> allPicPro(){
		return ipd.allPicPro();
	}
	/**
	 * 查询修改项拍照
	 */
	public OperPicProject oneOperPic(OperPicProject opp){
		
		return ipd.oneOperPic(opp);
	}
	
	
	/**
	 * 查询修改项拍照
	 */
	public OperPicProject oneOperPic1(OperPicProject opp){
		return ipd.oneOperPic1(opp);
	}
	/**
	 * 添加拍照项设置
	 */
	public void addPicSet(OperPicProject opp,HttpServletRequest request) throws Exception{
		//HttpSession session = request.getSession();
		// 得到session的用户
		UserEntity user = (UserEntity)request.getSession().getAttribute(Constrant.S_USER_SESSION);
		opp.setUpdater(user.getId());	// 修改人
		opp.setUpdDate(new Date());		// 修改时间
		opp.setDelFlag(IdEntity.DEL_FLAG_NORMAL);
		String pic = opp.getPic();		// 所有修改后的拍照项ID拼接成的字符串
		String p[] = pic.split(",");	// 切割成数组
		List<OperTypeEntity> operTypes = css.allOperType();	// 获得所有业务类型
		List<CkCllxEntity> cllxs = checkDao.allCKCllxBy(opp.getCllx());	// 获得所有车辆类型
		List<CehUsnrEntity> ceh = css.allUsering(); 	// 获得所有使用性质
		
		for (OperTypeEntity operTypeEntity : operTypes) {
			for (CehUsnrEntity cehUsnrEntity : ceh) {
				//for (CkCllxEntity cllxw : cllxs) {
					// 循环赋值
					for(int i=0; i<p.length; i++){
						opp.setOperType(operTypeEntity.getId());
						opp.setSyxz(cehUsnrEntity.getParentId());
						// 拍照项目ID与打印数量分离
						String arr[] = p[i].split("_");
						opp.setPicPro(arr[0]);	// 给拍照项ID赋值
						opp.setPrintNum(Integer.parseInt(arr[1]));
						super.insertData(opp);
						ils.insertLog("添加拍照设置", "系统设置", "添加拍照项设置成功", request);	// 添加日志信息
					}
				//}
			}
		}
	}
	
	
	/**
	 * 添加拍照项设置
	 */
	public void addPicSet1(OperPicProject opp,HttpServletRequest request) throws Exception{
		//HttpSession session = request.getSession();
		// 得到session的用户
		UserEntity user = (UserEntity)request.getSession().getAttribute(Constrant.S_USER_SESSION);
		opp.setUpdater(user.getId());	// 修改人
		opp.setUpdDate(new Date());		// 修改时间
		opp.setDelFlag(IdEntity.DEL_FLAG_NORMAL);
		String pic = opp.getPic();		// 所有修改后的拍照项ID拼接成的字符串
		String p[] = pic.split(",");	// 切割成数组
		// 循环赋值
		for(int i=0; i<p.length; i++){
			// 拍照项目ID与打印数量分离
			String arr[] = p[i].split("_");
			opp.setPicPro(arr[0]);	// 给拍照项ID赋值
			opp.setPrintNum(Integer.parseInt(arr[1]));
			super.insertData(opp);
			ils.insertLog("添加拍照设置", "系统设置", "添加拍照项设置成功", request);	// 添加日志信息
		}
	}
	
	/**
	 * 删除拍照项
	 */
	public void delPicSet(OperPicProject opp,HttpServletRequest request) throws Exception{
		super.dao.deleteData(opp);	// 删除
		ils.insertLog("删除拍照设置", "系统设置", "删除拍照项设置成功", request);	// 添加操作日志
	}
	
	/**
	 * 删除拍照项
	 */
	public void delPicSet1(OperPicProject opp,HttpServletRequest request) throws Exception{
		super.dao.deleteData1(opp.getCllx());	// 删除
		ils.insertLog("删除拍照设置", "系统设置", "删除拍照项设置成功", request);	// 添加操作日志
	}
	/**
	 * 修改拍照项
	 */
	public void updPicSet1(OperPicProject opp,HttpServletRequest request) throws Exception{
		
		UserEntity user =(UserEntity)request.getSession().getAttribute(Constrant.S_USER_SESSION);
		ils.insertLog("修改拍照项设置", "系统设置", "修改拍照项成功", request);	// 添加操作日志
		OperPicProject oldPic = ipd.oneOperPic1(opp);	// 修改前的拍照项设置
		opp.setUpdater(user.getId());
		String pic = opp.getPic();
		//修改后的拍照项
		String p[] = pic.split(",");
		
		/**
		//修改前的拍照项
		String pe[] = new String[oldPic.getPpe().size()];
		for(int i=0; i< oldPic.getPpe().size();i++){
			pe[i] = oldPic.getPpe().get(i).getId();
		}
		// 判断修改后的拍照项是否存在于修改前的拍照项中
		for(int i=0; i<p.length; i++){
			// 拍照项目ID与打印数量分离
			String arr[] = p[i].split("_");
			opp.setPicPro(arr[0]);	// 给拍照项ID赋值
			opp.setPrintNum(Integer.parseInt(arr[1]));		
			if(Arrays.asList(pe).contains(p[i])){
				super.updateData(opp); // 存在，修改
			}else{
				super.insertData(opp);	// 不存在，添加
			}
			
		}
		// 判断修改前的拍照项是否存在于修改后的拍照项中
		for (int i = 0 ; i<pe.length; i++) {
			opp.setPicPro(pe[i]);
			if(!Arrays.asList(p).contains(pe[i])){
				super.deleteData(opp);	// 不存在，删除
			}			
		}
		*/
		
		// 修改前的拍照项目全删除
		super.dao.deleteData1(opp.getCllx());
		
		List<OperTypeEntity> operTypes = css.allOperType();	// 获得所有业务类型
		List<CkCllxEntity> cllxs = checkDao.allCKCllxBy(opp.getCllx());	// 获得所有车辆类型
		List<CehUsnrEntity> ceh = css.allUsering(); 	// 获得所有使用性质
		
		for (OperTypeEntity operTypeEntity : operTypes) {
			for (CehUsnrEntity cehUsnrEntity : ceh) {
				//for (CkCllxEntity cllxw : cllxs) {
					// 循环赋值
					for(int i=0; i<p.length; i++){
						opp.setOperType(operTypeEntity.getId());
						opp.setSyxz(cehUsnrEntity.getParentId());
						// 拍照项目ID与打印数量分离
						String arr[] = p[i].split("_");
						opp.setPicPro(arr[0]);	// 给拍照项ID赋值
						opp.setPrintNum(Integer.parseInt(arr[1]));
						super.insertData(opp);
						ils.insertLog("添加拍照设置", "系统设置", "添加拍照项设置成功", request);	// 添加日志信息
					}
				//}
			}
		}
		
		
	/*	// 修改后的拍照项目全新增
		for (int i=0; i<p.length; i++) {
			// 拍照项目ID与打印数量分离
			String arr[] = p[i].split("_");
			// 给拍照项ID赋值
			opp.setPicPro(arr[0]);
			// 拍照数量
			opp.setPrintNum(Integer.parseInt(arr[1]));
			super.insertData(opp);
		}
		*/
	}
	
	
	/**
	 * 修改拍照项
	 */
	public void updPicSet(OperPicProject opp,HttpServletRequest request) throws Exception{
		UserEntity user =(UserEntity)request.getSession().getAttribute(Constrant.S_USER_SESSION);
		ils.insertLog("修改拍照项设置", "系统设置", "修改拍照项成功", request);	// 添加操作日志
		OperPicProject oldPic = ipd.oneOperPic(opp);	// 修改前的拍照项设置
		opp.setUpdater(user.getId());
		String pic = opp.getPic();
		//修改后的拍照项
		String p[] = pic.split(",");
		// 修改前的拍照项目全删除
		super.dao.deleteData(opp);
		// 修改后的拍照项目全新增
		for (int i=0; i<p.length; i++) {
			// 拍照项目ID与打印数量分离
			String arr[] = p[i].split("_");
			// 给拍照项ID赋值
			opp.setPicPro(arr[0]);
			// 拍照数量
			opp.setPrintNum(Integer.parseInt(arr[1]));
			super.insertData(opp);
		}
		
	}
}
