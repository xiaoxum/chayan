package com.vkl.ckts.cksy.servacpt.service.impl;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.entity.CkInfoEntity;
import com.vkl.ckts.common.entity.HureSettEntity;
import com.vkl.ckts.common.entity.IMplVehSetEntity;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.entity.OperVehProEntity;
import com.vkl.ckts.common.entity.PleApplEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.service.IHureSettEntityService;
import com.vkl.ckts.common.service.IIMplVehSetEntityService;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.common.utils.Base64Utils;
import com.vkl.ckts.common.utils.DateUtil;
import com.vkl.ckts.common.utils.StringUtils;
import com.vkl.ckts.cksy.servacpt.dao.IPleAppDao;
import com.vkl.ckts.cksy.servacpt.entity.VehInfoDto;
import com.vkl.ckts.cksy.servacpt.service.ICkInfoEntityService;
import com.vkl.ckts.cksy.servacpt.service.IOperVehProService;
import com.vkl.ckts.cksy.servacpt.service.IPleAppService;
import com.vkl.ckts.cksy.servacpt.service.IVehInfoDtoService;

/**
 * @see 质押登记申请表Impl
 * @author hwf
 * @version 1.0
 */
@Service
@Transactional
public class PleAppServiceImpl extends ServiceImpl<IPleAppDao, PleApplEntity, java.lang.String> implements IPleAppService {
	/**
	 * @合格证service
	 */
	@Autowired
	IVehInfoDtoService IVehInfoDtoDao;
	/**
	 * @查验信息service
	 */
	@Autowired 
	ICkInfoEntityService ICkInfoEntityService;
	/**
	 * 业务车型查验项目
	 */
	@Autowired IOperVehProService iOperVehProService;
	
	/**
	 * @重点车辆配置service
	 */
	@Autowired 
	IIMplVehSetEntityService IIMplVehSetEntityService;
	
	/**
	 * @人工审核配置service
	 */
	@Autowired 
	IHureSettEntityService iHureSettEntityService;
	
	/**
	 * @see 添加质押登记申请表
	 * @param pleApplEntity
	 */
	
	public String addPleApp(PleApplEntity pleApplEntity,VehInfoDto vehInfoDto,HttpServletRequest request) {
		
		
		//Session 中获取当前用户
		UserEntity user =(UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION);
		
		String srln=StringUtils.genereateSNVR(user.getUserDept().toString());  //  取出用户部门编号拼接生成流水号,
		//加入创建时间
		pleApplEntity.setCreateDate(DateUtil.parseDate(DateUtil.format(new Date(), DateUtil.DATE_TIME_FORMAT)));
		//加入创建人
		pleApplEntity.setCreater(user.getId());
		pleApplEntity.setSrln(srln);                    // 查验申请表加入流水号
		pleApplEntity.setOwName(Base64Utils.encode(pleApplEntity.getOwName()));           // 车主姓名加密
		pleApplEntity.setOwIdentity(Base64Utils.encode(pleApplEntity.getOwIdentity() ));  // 车主身份证加密
		pleApplEntity.setPlerName(Base64Utils.encode(pleApplEntity.getPlerName()));           // 抵押权人姓名加密
		pleApplEntity.setPlerIdenIndus(Base64Utils.encode(pleApplEntity.getPlerIdenIndus())); // 抵押权人身份证加密
		//代理人姓名不为空时
		if(null != pleApplEntity.getProxyName() && "" != pleApplEntity.getProxyName()){
			
			//pleApplEntity.setPleProxyName(Base64Utils.encode(pleApplEntity.getProxyName())); //代理人姓名加密
			pleApplEntity.setPleProxyName(pleApplEntity.getProxyName()); //代理人姓名加密
		}
		//抵押代理人姓名不为空时
		if(null != pleApplEntity.getPleProxyName() && "" != pleApplEntity.getPleProxyName()){
					
			pleApplEntity.setPleProxyName(Base64Utils.encode(pleApplEntity.getPleProxyName())); //抵押代理人姓名加密
		}
		
		vehInfoDto.setSrln(srln);                       // 合格证加入流水号
		
		vehInfoDto.setClsbdh(vehInfoDto.getCopyClsbdh()); //车辆识别代号拷贝
		
		CkInfoEntity ckInfoEntity = new CkInfoEntity(); // 创建查验信息实体类
		ckInfoEntity.setSrln(srln);                     // 查验信息加入流水号
		
		//ckInfoEntity.setAsts(asts);                           // 查验信息加入行政区
		ckInfoEntity.setOrganCode(user.getUserDept().toString());  // 查验场地
		ckInfoEntity.setCllx(pleApplEntity.getCllx());         // 车辆型号
		ckInfoEntity.setClsbdh(vehInfoDto.getCopyClsbdh());     // 车辆识别代号
		ckInfoEntity.setHphm(pleApplEntity.getHphm());          // 车辆号牌号码
		ckInfoEntity.setHpzl(pleApplEntity.getHpzl());          // 车辆号牌种类
		ckInfoEntity.setOperType(pleApplEntity.getOperType().toString()); // 业务类型
		// 业务受理人
		ckInfoEntity.setOperaer(Integer.parseInt(user.getId())); 
		// 受理时间
		ckInfoEntity.setCreateDate(DateUtil.parseDate(DateUtil.format(new Date(), DateUtil.DATE_TIME_FORMAT)));
		// 创建人
		ckInfoEntity.setCreater(user.getId());
		// 初始化复检次数为0
		ckInfoEntity.setRckCount(0);
		
		IMplVehSetEntity IMplVehSetEntity = new IMplVehSetEntity(); // 新建重点车辆配置实体类
		IMplVehSetEntity.setCllx(pleApplEntity.getCllx());             // 车辆类型
		IMplVehSetEntity.setSyxz(pleApplEntity.getSyxz());          // 使用性质
		
		// 根据车辆类型和使用性质到重点车辆配置表中查询数据
		List<IMplVehSetEntity> list = null;
		
		list = IIMplVehSetEntityService.findByCllxAndSyxz(IMplVehSetEntity);
			
		// 如果长度大于0，说明该车属于重点车辆
		if(list.size()>0){
			
			ckInfoEntity.setIsImpl(IdEntity.S_YES);     // 是重点车辆
			
			
		}else{
		
			ckInfoEntity.setIsImpl(IdEntity.S_NO);      // 非重点车辆
			
			
		}
		
		// 根据车辆类型和使用性质到人工审核配置表中查询数据
		List<HureSettEntity> listSet = null;
		
		HureSettEntity hureSettEntity = new HureSettEntity(); // 新建人工审核车辆配置实体类
		hureSettEntity.setCllx(pleApplEntity.getCllx());         // 车辆类型
		hureSettEntity.setSyxz(pleApplEntity.getSyxz());      // 使用性质
		
		listSet = iHureSettEntityService.findByCllxAndSyxz(hureSettEntity);
		// 如果长度大于0，说明该车不属于人工审核车辆
		if(listSet.size()>0){
			
			ckInfoEntity.setIsPAudit(IdEntity.S_NO);  // 不需人工审核
			
		}else{
			
			ckInfoEntity.setIsPAudit(IdEntity.S_YES); // 需人工审核
			
		}
		  // 根据车型和业务类型查看车辆是否需要外扩设备查验项目
		   OperVehProEntity  operVehProEntity =  new OperVehProEntity();
		   operVehProEntity.setCllx(pleApplEntity.getCllx());
		   operVehProEntity.setOperType(pleApplEntity.getOperType());
		   List<OperVehProEntity> list2 = iOperVehProService.find(operVehProEntity);
		   // 判断项目编号是否为 11 、10 则需 外扩、整备质量检测
		   for (OperVehProEntity operVehProEntity2 : list2) {
			   
			   if("11".equals(operVehProEntity2.getProId())||"10".equals(operVehProEntity2.getProId())){ 
			
				   ckInfoEntity.setDeviceCkStatu(IdEntity.OPER_STATU_GBR_ZBZL); // 外廓尺寸查验中 
				   
				   break;
		        }
		   }
		
	 	
		
		 super.dao.addPleApp(pleApplEntity);            // 添加申请表到数据库
		 
		 ckInfoEntity.setApplId(Integer.parseInt(pleApplEntity.getId()));  // 查验信息加入业务申请编号
		 
		 ICkInfoEntityService.insertData(ckInfoEntity); // 添加查验信息到数据库
		    
		 IVehInfoDtoDao.insertData(vehInfoDto);         // 添加合格证信息到数据库
		  
		
		 
		   return srln; //返回流水号
	}
	
}
