package com.vkl.ckts.cksy.servacpt.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.entity.ChApplItemEntity;
import com.vkl.ckts.common.entity.CkInfoEntity;
import com.vkl.ckts.common.entity.HureSettEntity;
import com.vkl.ckts.common.entity.IMplVehSetEntity;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.entity.OperVehProEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.service.IHureSettEntityService;
import com.vkl.ckts.common.service.IIMplVehSetEntityService;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.common.utils.Base64Utils;
import com.vkl.ckts.common.utils.DateUtil;
import com.vkl.ckts.common.utils.StringUtils;
import com.vkl.ckts.cksy.servacpt.dao.IChApplItemDao;
import com.vkl.ckts.cksy.servacpt.entity.VehInfoDto;
import com.vkl.ckts.cksy.servacpt.service.IChApplItemService;
import com.vkl.ckts.cksy.servacpt.service.ICkInfoEntityService;
import com.vkl.ckts.cksy.servacpt.service.IOperVehProService;
import com.vkl.ckts.cksy.servacpt.service.IVehInfoDtoService;


/**
 * @see 变更登记/备案申请Impl
 * @author hwf
 * @version 1.0
 */
@Service
@Transactional
public class ChApplItemServiceImpl extends ServiceImpl<IChApplItemDao, ChApplItemEntity, java.lang.String> implements IChApplItemService {
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
	 * @see 添加变更登记申请和变更事项
	 * @param ChApplItemEntity  变更信息
	 * @param VehInfoDto        车辆信息
	 * @return 受理成功返回true，失败返回false
	 */
	public String addChAppl(ChApplItemEntity chApplItemEntity, VehInfoDto vehInfoDto, HttpServletRequest request) {
		
		            //Session 中获取当前用户
		            UserEntity user =(UserEntity) request.getSession().getAttribute(Constrant.S_USER_SESSION);
				
					String srln=StringUtils.genereateSNVR(user.getUserDept().toString()); //  取出用户部门编号拼接生成流水号,
					chApplItemEntity.setSrln(srln);                  // 查验申请表加入流水号
					
					//加入创建时间
					chApplItemEntity.setCreateDate(DateUtil.parseDate(DateUtil.format(new Date(), DateUtil.DATE_TIME_FORMAT)));
					//加入创建人
					chApplItemEntity.setCreater(user.getId());
					chApplItemEntity.setOwName(Base64Utils.encode(chApplItemEntity.getOwName()));         // 车主姓名加密
					chApplItemEntity.setOwIdentity(Base64Utils.encode(chApplItemEntity.getOwIdentity())); // 车主身份证加密
					//变更所有人姓名不为空时
					if(null != chApplItemEntity.getKerName() && "" != chApplItemEntity.getKerName()){
						
						//chApplItemEntity.setKerName(Base64Utils.encode(chApplItemEntity.getKerName()));   //所有人姓名加密
						chApplItemEntity.setKerName(chApplItemEntity.getKerName());   //所有人姓名加密
					}
					
					//变更所有人身份证不为空时
					if(null != chApplItemEntity.getKerIdentity() && "" != chApplItemEntity.getKerIdentity()){
						
						chApplItemEntity.setKerIdentity(Base64Utils.encode(chApplItemEntity.getKerIdentity()));   //所有人身份证加密
					}
					
					//变更共有人姓名不为空时
					if(null != chApplItemEntity.getCoOwName()&& "" != chApplItemEntity.getCoOwName()){
						
						//chApplItemEntity.setCoOwName(Base64Utils.encode(chApplItemEntity.getCoOwName()));   //共有人姓名加密
						chApplItemEntity.setCoOwName(chApplItemEntity.getCoOwName());   //共有人姓名加密
					}
					
					//变更共有人身份证不为空时
					if(null != chApplItemEntity.getCoOwIdentity() && "" != chApplItemEntity.getCoOwIdentity()){
						
						//chApplItemEntity.setCoOwIdentity(Base64Utils.encode(chApplItemEntity.getCoOwIdentity()));   //共有人身份证加密
						chApplItemEntity.setCoOwIdentity(chApplItemEntity.getCoOwIdentity());   //共有人身份证加密
					}
					
					vehInfoDto.setSrln(srln);                       // 合格证加入流水号
					
					CkInfoEntity ckInfoEntity = new CkInfoEntity(); // 创建查验信息实体类
					ckInfoEntity.setSrln(srln);                     // 查验信息加入流水号
					//ckInfoEntity.setAsts(asts);                           // 查验信息加入行政区
					ckInfoEntity.setOrganCode(user.getUserDept().toString());  // 查验场地
					ckInfoEntity.setCllx(chApplItemEntity.getCllx());         // 车辆型号
					ckInfoEntity.setClsbdh(vehInfoDto.getCopyClsbdh());       // 车辆识别代号
					ckInfoEntity.setHphm(chApplItemEntity.getHphm());         // 车辆号牌号码
					ckInfoEntity.setHpzl(chApplItemEntity.getHpzl());         // 车辆号牌种类
					ckInfoEntity.setOperType(chApplItemEntity.getOperType()); // 业务类型
					// 业务受理人
					ckInfoEntity.setOperaer(Integer.parseInt(user.getId())); 
					ckInfoEntity.setSyxz(chApplItemEntity.getSyxz());         // 使用性质
					//受理时间
					ckInfoEntity.setCreateDate(DateUtil.parseDate(DateUtil.format(new Date(), DateUtil.DATE_TIME_FORMAT)));
					//创建人
					ckInfoEntity.setCreater(user.getId());
					//初始化复检次数为0
					ckInfoEntity.setRckCount(0);
					
					//根据车辆类型和使用性质到重点车辆配置表中查询数据
					List<IMplVehSetEntity> list = null;
					
					IMplVehSetEntity IMplVehSetEntity = new IMplVehSetEntity(); // 新建重点车辆配置实体类
					IMplVehSetEntity.setCllx(chApplItemEntity.getCllx());       // 车辆类型
					
					//根据车辆类型和使用性质到人工审核配置表中查询数据
					List<HureSettEntity> listSet = null;
					
					HureSettEntity hureSettEntity = new HureSettEntity(); // 新建人工审核车辆配置实体类
				    hureSettEntity.setCllx(chApplItemEntity.getCllx());   // 车辆类型
					
					//判断变更后的使用性质
					if(null == chApplItemEntity.getNewSyxz()||"" == chApplItemEntity.getNewSyxz()){
						
						IMplVehSetEntity.setSyxz(chApplItemEntity.getSyxz());   // 使用性质
						hureSettEntity.setSyxz(chApplItemEntity.getSyxz());     // 使用性质
					}else{
						
						IMplVehSetEntity.setSyxz(chApplItemEntity.getNewSyxz()); // 新使用性质
						hureSettEntity.setSyxz(chApplItemEntity.getNewSyxz());   // 新使用性质
					}
					
					list = IIMplVehSetEntityService.findByCllxAndSyxz(IMplVehSetEntity);
						
					// 如果长度大于0，说明该车属于重点车辆
					if(list.size()>0){
						
						ckInfoEntity.setIsImpl(IdEntity.S_YES);     // 是重点车辆
						
					}else{
					
						ckInfoEntity.setIsImpl(IdEntity.S_NO);      // 非重点车辆
						
					}
				
					listSet = iHureSettEntityService.findByCllxAndSyxz(hureSettEntity);
					
					// 如果长度大于0，说明该车不属于人工审核车辆
					if(listSet.size()>0){
						
						ckInfoEntity.setIsPAudit(IdEntity.S_NO);  // 不需人工审核
						
					}else{
						
						ckInfoEntity.setIsPAudit(IdEntity.S_YES); // 需人工审核
					}
					 // 逗号分隔变更业务
					  String[] operType = chApplItemEntity.getOperType().split(",");
					  
					Out:for (String type : operType) {
						
					
					 // 根据车型和业务类型查看车辆是否需要外扩设备查验项目
					   OperVehProEntity  operVehProEntity =  new OperVehProEntity();
					   operVehProEntity.setCllx(chApplItemEntity.getCllx());
					   operVehProEntity.setOperType(type);
					   
					   List<OperVehProEntity> list2 = iOperVehProService.find(operVehProEntity);
					   // 判断项目编号是否为 11 、10 则需 外扩、整备质量检测
					   for (OperVehProEntity operVehProEntity2 : list2) {
						   
						   if("11".equals(operVehProEntity2.getProId())||"10".equals(operVehProEntity2.getProId())){ 
						
							   ckInfoEntity.setDeviceCkStatu(IdEntity.OPER_STATU_GBR_ZBZL); // 外廓尺寸查验中  
							   
							    break Out; //跳出out循环
					        }
					   }
					
					 }
						super.dao.addChAppl(chApplItemEntity);         // 添加申请表到数据库
					    
						ckInfoEntity.setApplId(Integer.parseInt(chApplItemEntity.getId()));  // 查验信息加入业务申请编号
						
						//ckInfoEntity.setCkType(0) //查验单人0、双人1
						
					    ICkInfoEntityService.insertData(ckInfoEntity); // 添加查验信息到数据库
					    
					    IVehInfoDtoDao.insertData(vehInfoDto);         // 添加合格证信息到数据库
					
					  
					    return srln; //返回流水号
				
			}

		
		
		
		
	}
	
	

	
	

