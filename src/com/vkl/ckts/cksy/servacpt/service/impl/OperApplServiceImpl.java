package com.vkl.ckts.cksy.servacpt.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.constr.Constrant;
import com.vkl.ckts.common.entity.CkInfoEntity;
import com.vkl.ckts.common.entity.DeptEntity;
import com.vkl.ckts.common.entity.HureSettEntity;
import com.vkl.ckts.common.entity.IMplVehSetEntity;
import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.entity.OperApplEntity;
import com.vkl.ckts.common.entity.OperVehProEntity;
import com.vkl.ckts.common.entity.UserEntity;
import com.vkl.ckts.common.service.ICehUsnrEntityService;
import com.vkl.ckts.common.service.IHureSettEntityService;
import com.vkl.ckts.common.service.IIMplVehSetEntityService;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.common.utils.DateUtil;
import com.vkl.ckts.common.utils.PropertiesUtils;
import com.vkl.ckts.common.utils.StringUtils;
import com.vkl.ckts.cksy.servacpt.dao.IOperApplDao;
import com.vkl.ckts.cksy.servacpt.entity.CkInfoDto;
import com.vkl.ckts.cksy.servacpt.entity.TJbxx;
import com.vkl.ckts.cksy.servacpt.entity.VehInfoDto;
import com.vkl.ckts.cksy.servacpt.service.IOperApplService;
import com.vkl.ckts.cksy.servacpt.service.IOperVehProService;
import com.vkl.ckts.cksy.servacpt.service.IVehInfoDtoService;
import com.vkl.ckts.cksy.servacpt.service.ICkInfoEntityService;
import com.vkl.ckts.wss.util.Execute;
import com.vkl.ckts.wss.util.URLDeEUtils;
import com.vkl.ckts.wss.util.XMLConverter;

/**
 * @see 注册登记，注销，转入，转移业务申请表ServiceImpl
 * @author hwf
 * @version 1.0
 */
@Service
@Transactional
public class OperApplServiceImpl extends
		ServiceImpl<IOperApplDao, OperApplEntity, java.lang.String> implements
		IOperApplService {

	/**
	 * 使用性质表
	 */
	@Autowired
	ICehUsnrEntityService CehUsnrEntityService;
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
	 * 业务车型查验项目
	 */
	@Autowired
	IOperVehProService iOperVehProService;

	/**
	 * 受理业务信息写入
	 * 
	 * @param operApplEntity
	 * @param VehInfoEntity
	 * @return 比对成功返回true，失败返回false
	 */

	public String matchInfoAddOperAppl(OperApplEntity operApplEntity,
			VehInfoDto vehInfoDto, HttpServletRequest request) {

		String currentDate = DateUtil.format(new Date(), DateUtil.DATE_FORMAT);
		List<CkInfoDto> cks = ICkInfoEntityService.findCkInfoByCd(
				vehInfoDto.getClsbdh(), currentDate);
		if (cks != null && cks.size() > 0) {
			return "false2";
		}
		// Session 中获取当前用户
		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constrant.S_USER_SESSION);

		String srln = StringUtils.genereateSNVR(user.getUserDept().toString()); // 取出用户部门编号拼接生成流水号,
		operApplEntity.setSrln(srln); // 查验申请表加入流水号
		// operApplEntity.setOwIdentity(Base64Utils.encode(operApplEntity.getOwIdentity()));
		// // 车主身份证加密
		// operApplEntity.setOwName(Base64Utils.encode(operApplEntity.getOwName()));
		// // 车主姓名加密
		operApplEntity.setOwIdentity(operApplEntity.getOwIdentity()); // 车主身份证加密
		operApplEntity.setOwName(operApplEntity.getOwName()); // 车主姓名加密
		// 代理人不为空时，姓名加密
		if ("" != operApplEntity.getProxyName()
				&& null != operApplEntity.getProxyName()) {

			// operApplEntity.setProxyName(Base64Utils.encode(operApplEntity.getProxyName()));
			// // 代理人加密姓名
			operApplEntity.setProxyName(operApplEntity.getProxyName()); // 代理人加密姓名
		}

		// 加入创建时间
		operApplEntity.setCreateDate(DateUtil.parseDate(DateUtil.format(
				new Date(), DateUtil.DATE_TIME_FORMAT)));
		// 加入创建人
		operApplEntity.setCreater(user.getId());

		operApplEntity.setClpp(vehInfoDto.getClpp1()); // 加入车辆品牌

		vehInfoDto.setSrln(srln); // 合格证加入流水号
		CkInfoEntity ckInfoEntity = new CkInfoEntity(); // 创建查验信息实体类
		ckInfoEntity.setSrln(srln); // 查验信息加入流水号
		// ckInfoEntity.setAsts(user.get); // 查验信息加入行政区
		// ckInfoEntity.setOrganCode(user.getUserDept().toString()); // 查验场地
		DeptEntity deptEntity = super.dao.findDeptById(user.getUserDept()
				.toString());
		if (!IdEntity.S_YES.equals(deptEntity.getIsFileDept())) {
			return "false3";
		} else {
			ckInfoEntity.setOrganCode(deptEntity.getFileId());
		}
		ckInfoEntity.setCllx(vehInfoDto.getClxh()); // 车辆型号
		ckInfoEntity.setClsbdh(vehInfoDto.getClsbdh()); // 车辆识别代号
		ckInfoEntity.setHphm(operApplEntity.getHphm()); // 车辆号牌号码
		ckInfoEntity.setHpzl(operApplEntity.getHpzl()); // 车辆号牌种类
		ckInfoEntity.setOperType(operApplEntity.getOperType()); // 业务类型
		// 业务受理人
		ckInfoEntity.setOperaer(Integer.parseInt(user.getId()));
		ckInfoEntity.setSyxz(operApplEntity.getSyxz()); // 使用性质
		// 受理时间
		ckInfoEntity.setCreateDate(DateUtil.parseDate(DateUtil.format(
				new Date(), DateUtil.DATE_TIME_FORMAT)));
		// 创建人
		ckInfoEntity.setCreater(user.getId());
		// 初始化复检次数为0
		ckInfoEntity.setRckCount(0);
		// 打印初始化为0
		ckInfoEntity.setSfdy("0");
		ckInfoEntity.setAuditFlag(IdEntity.AUDIT_FLAG_NORMAL);
		ckInfoEntity.setDelFlag(IdEntity.DEL_FLAG_NORMAL);
		IMplVehSetEntity IMplVehSetEntity = new IMplVehSetEntity(); // 新建重点车辆配置实体类
		IMplVehSetEntity.setCllx(operApplEntity.getClxh()); // 车辆类型
		IMplVehSetEntity.setSyxz(operApplEntity.getSyxz()); // 使用性质
		// 根据车辆类型和使用性质到重点车辆配置表中查询数据
		List<IMplVehSetEntity> list = null;

		list = IIMplVehSetEntityService.findByCllxAndSyxz(IMplVehSetEntity);

		// 如果长度大于0，说明该车属于重点车辆
		if (list.size() > 0) {

			ckInfoEntity.setIsImpl(IdEntity.S_YES); // 是重点车辆

		} else {

			ckInfoEntity.setIsImpl(IdEntity.S_NO); // 非重点车辆

		}

		// 根据车辆类型和使用性质到人工审核配置表中查询数据
		List<HureSettEntity> listSet = null;

		HureSettEntity hureSettEntity = new HureSettEntity(); // 新建人工审核车辆配置实体类
		hureSettEntity.setCllx(operApplEntity.getClxh()); // 车辆类型
		hureSettEntity.setSyxz(operApplEntity.getSyxz()); // 使用性质

		listSet = iHureSettEntityService.findByCllxAndSyxz(hureSettEntity);

		// 如果长度大于0，说明该车不属于人工审核车辆
		if (listSet.size() > 0) {

			ckInfoEntity.setIsPAudit(IdEntity.S_NO); // 不需人工审核

		} else {

			ckInfoEntity.setIsPAudit(IdEntity.S_YES); // 需人工审核
		}

		// 根据车型和业务类型查看车辆是否需要外扩设备查验项目
		OperVehProEntity operVehProEntity = new OperVehProEntity();
		operVehProEntity.setCllx(operApplEntity.getClxh());
		operVehProEntity.setOperType(operApplEntity.getOperType());
		List<OperVehProEntity> list2 = iOperVehProService
				.find(operVehProEntity);
		// 判断项目编号是否为 11 、10 则需 外扩、整备质量检测
		for (OperVehProEntity operVehProEntity2 : list2) {
			if ("11".equals(operVehProEntity2.getProId())
					|| "10".equals(operVehProEntity2.getProId())) {
				ckInfoEntity.setDeviceCkStatu(IdEntity.OPER_STATU_GBR_ZBZL); // 外廓尺寸查验中
				break;
			}
		}
		super.dao.addOperAppl(operApplEntity); // 添加申请表到数据库
		ckInfoEntity.setOperStatu(IdEntity.OPER_STATU_PDA); // 业务状态 pda查验中
		ckInfoEntity.setApplId(Integer.parseInt(operApplEntity.getId())); // 查验信息加入业务申请编号

		// ckInfoEntity.setCkType(0) //查验单人0、双人1

		ICkInfoEntityService.insertData(ckInfoEntity); // 添加查验信息到数据库
		if (vehInfoDto.getCsys1() == null) {
			vehInfoDto.setCsys1("");
		}
		if (vehInfoDto.getCsys2() == null
				|| vehInfoDto.getCsys2().trim().length() == 0) {
			vehInfoDto.setCsys2("");
		} else {
			vehInfoDto.setCsys2("/" + vehInfoDto.getCsys2());
		}
		if (vehInfoDto.getCsys3() == null
				|| vehInfoDto.getCsys3().trim().length() == 0) {
			vehInfoDto.setCsys3("");
		} else {
			vehInfoDto.setCsys3("/" + vehInfoDto.getCsys3());
		}
		// if(vehInfoDto.getCsys1()==null&&vehInfoDto.getCsys2()!=null){
		// vehInfoDto.setCsys(vehInfoDto.getCsys2());//颜色组合
		// }else if(vehInfoDto.getCsys1()!=null&&vehInfoDto.getCsys2()==null){
		// vehInfoDto.setCsys(vehInfoDto.getCsys1());//颜色组合
		// }else if(vehInfoDto.getCsys1()!=null&&vehInfoDto.getCsys2()!=null){
		// vehInfoDto.setCsys(vehInfoDto.getCsys1()+vehInfoDto.getCsys2());//颜色组合
		// }
		vehInfoDto.setCsys(vehInfoDto.getCsys1() + vehInfoDto.getCsys2()
				+ vehInfoDto.getCsys3());
		vehInfoDto.setClxh(vehInfoDto.getCopyClxh());
		IVehInfoDtoDao.insertData(vehInfoDto); // 添加合格证信息到数据库
		return srln; // 返回流水
	}

	/**
	 * 修改查验信息
	 */
	public void updateCkInfo(CkInfoDto ckInfoDto, VehInfoDto vehInfoDto) {
		CkInfoEntity ckInfoDto2 = ICkInfoEntityService.findckinfobysrln(
				ckInfoDto.getSrln(), ckInfoDto.getRckCount() + "");
		if (ckInfoDto2 == null) {
			return;
		}
		ckInfoDto2.setClsbdh(ckInfoDto.getClsbdh());
		if (ckInfoDto2.getCllx().equals(ckInfoDto.getCllx())) {
			super.dao.updateCllxProSort(ckInfoDto.getCllx());
		}
		ckInfoDto2.setCllx(ckInfoDto.getCllx());
		ckInfoDto2.setSyxz(ckInfoDto.getSyxz());
		ckInfoDto2.setHpzl(ckInfoDto.getHpzl());
		ckInfoDto2.setHphm(ckInfoDto.getHphm());
		ckInfoDto2.setOperType(ckInfoDto.getOperType());
		ICkInfoEntityService.updateCkInfo(ckInfoDto2);
		VehInfoDto vehInfoDto2 = IVehInfoDtoDao.selectById(ckInfoDto.getSrln());
		if (vehInfoDto2 != null) {
			if (vehInfoDto.getCsys1() == null) {
				vehInfoDto.setCsys1("");
			}
			if (vehInfoDto.getCsys2() == null
					|| vehInfoDto.getCsys2().trim().length() == 0) {
				vehInfoDto.setCsys2("");
			} else {
				vehInfoDto.setCsys2("/" + vehInfoDto.getCsys2());
			}
			if (vehInfoDto.getCsys3() == null
					|| vehInfoDto.getCsys3().trim().length() == 0) {
				vehInfoDto.setCsys3("");
			} else {
				vehInfoDto.setCsys3("/" + vehInfoDto.getCsys3());
			}
			vehInfoDto2.setCsys(vehInfoDto.getCsys1() + vehInfoDto.getCsys2()
					+ vehInfoDto.getCsys3());
			vehInfoDto2.setClsbdh(vehInfoDto.getClsbdh());
			vehInfoDto2.setHdzk(vehInfoDto.getHdzk());
			vehInfoDto2.setSrln(ckInfoDto.getSrln());
			IVehInfoDtoDao.updateVehicleInfo(vehInfoDto2);
		}
	}

	/**
	 * @see 复检受理
	 * @param srln
	 * @param rckCount
	 * @return Message
	 */
	public String reCheck(String srln, String rckCount,
			HttpServletRequest request) {

		// Session 中获取当前用户
		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constrant.S_USER_SESSION);

		CkInfoEntity ckInfoEntity = ICkInfoEntityService.findckinfobysrln(srln,
				rckCount);
		CkInfoEntity ckInfoEntity2 = new CkInfoEntity();// 创建复检对象

		ckInfoEntity2.setSrln(ckInfoEntity.getSrln()); // 查验信息加入流水号
		// ckInfoEntity.setAsts(user.get); // 查验信息加入行政区
		ckInfoEntity2.setOrganCode(ckInfoEntity.getOrganCode()); // 查验场地
		ckInfoEntity2.setCllx(ckInfoEntity.getCllx()); // 车辆型号
		ckInfoEntity2.setClsbdh(ckInfoEntity.getClsbdh()); // 车辆识别代号
		ckInfoEntity2.setHphm(ckInfoEntity.getHphm()); // 车辆号牌号码
		ckInfoEntity2.setHpzl(ckInfoEntity.getHpzl()); // 车辆号牌种类
		ckInfoEntity2.setDelFlag("0");
		ckInfoEntity2.setOperType(ckInfoEntity.getOperType()); // 业务类型
		// 业务受理人
		ckInfoEntity2.setOperaer(Integer.parseInt(user.getId()));
		ckInfoEntity2.setSyxz(ckInfoEntity.getSyxz()); // 使用性质
		// 受理时间
		ckInfoEntity2.setCreateDate(DateUtil.parseDate(DateUtil.format(
				new Date(), DateUtil.DATE_TIME_FORMAT)));
		// 创建人
		ckInfoEntity2.setCreater(user.getId());
		// 初始化复检次数为0
		ckInfoEntity2.setRckCount(ckInfoEntity.getRckCount() + 1);
		// 打印初始化为0
		ckInfoEntity2.setSfdy("0");
		try {
			// 是否重点车辆
			ckInfoEntity2.setIsImpl(ckInfoEntity.getIsImpl());
			// 是否需人工审核
			ckInfoEntity2.setIsPAudit(ckInfoEntity.getIsPAudit());

		} catch (Exception e) {
			// TODO: handle exception
		}

		ckInfoEntity2.setOperStatu(IdEntity.OPER_STATU_PDA); // 业务状态 pda查验中
		ckInfoEntity2.setApplId(ckInfoEntity.getApplId()); // 查验信息加入业务申请编号
		ICkInfoEntityService.insertData(ckInfoEntity2); // 添加查验信息到数据库

		return "true";

	}

	/**
	 * 子级车辆类型
	 */

	public TJbxx getJdcjBxx(String hphm, String hpzl, String cllx, String clsbdh) {
		TJbxx jbxx = null;
//		jbxx = super.dao.findVehBaseInfo(hpzl, hphm);
//		if (jbxx == null) {
//			jbxx = super.dao.findVehBaseInfo1(hpzl, hphm);
//		}
//		if (jbxx == null) {
//			jbxx = super.dao.findVehBaseInfo2(hpzl, hphm);
//		}
//		if (jbxx == null) {
//			jbxx = super.dao.findVehBaseInfo3(hpzl, hphm);
//		}
//		if (jbxx == null) {
//			if (hphm != null && hphm.startsWith("赣")) {
//				hphm = hphm.substring(hphm.indexOf("赣") + 1);
//				try {
//					// 接口获取车辆基本信息
//					jbxx = getTjbxx1(hphm, hpzl, clsbdh);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			} 
			if (jbxx==null) {
				try {
					// 接口获取车辆基本信息
					jbxx = getTjbxx(hphm, hpzl, clsbdh);
				} catch (Exception e) {
				}
			}
			
			
		
//		}

		if (jbxx != null) {
			String csys = jbxx.getCsys();
			StringBuffer csyse = new StringBuffer();
			if (org.apache.commons.lang3.StringUtils.isNotBlank(csys)) {
				char[] csyss = csys.toCharArray();
				for (char c : csyss) {
					csyse.append(super.dao.findCsysfromDict(c + "", "csys"));
				}
			}
			jbxx.setCsys(csyse.toString());
			jbxx.setRlzl(super.dao.findCsysfromDict(jbxx.getRlzl(), "nlzl"));
		}
		return jbxx;
	}

	/**
	 * 接口获取检车基本信息
	 * 
	 * @param hphm
	 *            号牌号码
	 * @param hpzl
	 * @param clsbdh
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public TJbxx getTjbxx(String hphm, String hpzl, String clsbdh)
			throws Exception {
		TJbxx jbxx = null;
		Execute execute = new Execute(PropertiesUtils.getValues("jgxtjkdz"));
		StringBuffer queryXml = new StringBuffer();
//		queryXml.append(XMLConverter.getXmlFiedHeader());
//		queryXml.append("<QueryCondition>");
		queryXml.append("<hphm>" + hphm + "</hphm>");
		queryXml.append("<hpzl>" + hpzl + "</hpzl>");
		queryXml.append("<clsbdh>" + clsbdh + "</clsbdh>");
//		queryXml.append("<jyjgbh>" + PropertiesUtils.getValues("jgbh")
//				+ "</jyjgbh>");
//		queryXml.append("</QueryCondition>" + XMLConverter.getXmlFiedFoot());
		String resultXml = "";
		resultXml = execute.doQuery("18", PropertiesUtils.getValues("jgxtxlh"),
				"18C49", queryXml.toString());
		if (resultXml == null || resultXml == "") {
			return null;
		}
		Map<String, String> headMap = XMLConverter.XmlParsel(resultXml, "head");
		if (!"1".equals(headMap.get("code"))) {
			return null;
		}
		// 获取根节点
		Element rootE = XMLConverter.getRootElement(resultXml);
		Element bodyE = XMLConverter.getElement(rootE, "body");
		Element viE = XMLConverter.getElement(bodyE, "vehispara");
		Map<String, String> dataMap = XMLConverter
				.XmlParsel(bodyE, "vehispara");
		Field[] field = TJbxx.class.getDeclaredFields();
		for (Field field2 : field) {
			field2.setAccessible(true);
			Class fieldType = field2.getType();
			String fieldName = field2.getName();
			if ("serialVersionUID".equals(fieldName)) {
				continue;
			}
			if ("class".equals(fieldName)) {
				continue;
			}
			String setMethodName = "set"
					+ fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1);
			Method method = TJbxx.class.getMethod(setMethodName,
					field2.getType());
			method.setAccessible(true);
			String value = dataMap.get(fieldName);
			if (value == null || value == "") {
				continue;
			}
			Object valObject = value;
			if (jbxx==null) {
				jbxx = new TJbxx();
			}
			try {
				if (fieldType == Date.class) {
					valObject = DateUtil.parseDate(value);
					method.invoke(jbxx, valObject);
				} else if (fieldType == Integer.class) {
					method.invoke(jbxx, Integer.parseInt(value));
				} else if (fieldType == Double.class) {
					method.invoke(jbxx, Double.parseDouble(value));
				} else if (fieldType == String.class) {
					method.invoke(jbxx, (String) valObject);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			if (jbxx != null) {
				if (super.dao.findVehBaseInfo(clsbdh, hphm) != null) {
					// 本地有这条数据
					super.dao.updateVehBaseInfo(jbxx);
				} else {
					super.dao.insertVehBaseInfo(jbxx);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jbxx;
	}

	/**
	 * 接口获取检车基本信息
	 * 
	 * @param hphm
	 *            号牌号码
	 * @param hpzl
	 * @param clsbdh
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	public TJbxx getTjbxx1(String hphm, String hpzl, String clsbdh)
			throws Exception {
		TJbxx jbxx = null;
		Execute execute = new Execute(PropertiesUtils.getValues("szjkurl"));
		StringBuffer queryXml = new StringBuffer();
		queryXml.append("<hphm>" + hphm + "</hphm>");
		queryXml.append("<hpzl>" + hpzl + "</hpzl>");
		String resultXml = "";
		resultXml = execute.doQuery1("18C49",
				URLDeEUtils.encodeUTF8(queryXml.toString()));
		if (resultXml == null || resultXml == "") {
			return null;
		}
		Map<String, String> headMap = XMLConverter.XmlParsel(resultXml, "head");
		if (!"1".equals(headMap.get("code"))) {
			return null;
		}
		// 获取根节点
		Element rootE = XMLConverter.getRootElement(resultXml);
		Element bodyE = XMLConverter.getElement(rootE, "body");
		Element viE = XMLConverter.getElement(bodyE, "vehispara");
		Map<String, String> dataMap = XMLConverter.XmlParsel(bodyE, "veh");
		Field[] field = TJbxx.class.getDeclaredFields();
		for (Field field2 : field) {
			field2.setAccessible(true);
			Class fieldType = field2.getType();
			String fieldName = field2.getName();
			if ("serialVersionUID".equals(fieldName)) {
				continue;
			}
			if ("class".equals(fieldName)) {
				continue;
			}
			String setMethodName = "set"
					+ fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1);
			Method method = TJbxx.class.getMethod(setMethodName,
					field2.getType());
			method.setAccessible(true);
			String value = dataMap.get(fieldName);
			if (value == null || value == "") {
				continue;
			}
			Object valObject = value;
			if (jbxx==null) {
				jbxx = new TJbxx();
			}
			try {
				if (fieldType == Date.class) {
					valObject = DateUtil.parseDate(value);
					method.invoke(jbxx, valObject);
				} else if (fieldType == Integer.class) {
					method.invoke(jbxx, Integer.parseInt(value));
				} else if (fieldType == Double.class) {
					method.invoke(jbxx, Double.parseDouble(value));
				} else if (fieldType == String.class) {
					method.invoke(jbxx, (String) valObject);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			if (jbxx != null) {
				if (super.dao.findVehBaseInfo(clsbdh, hphm) != null) {
					// 本地有这条数据
					super.dao.updateVehBaseInfo(jbxx);
				} else {
					super.dao.insertVehBaseInfo(jbxx);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jbxx;
	}

	/**
	 * 更新车型排序
	 * 
	 * @param cllx
	 */
	@Override
	public void updateCllxProSort(String cllx) {
		// TODO Auto-generated method stub
		super.dao.updateCllxProSort(cllx);
	}

}
