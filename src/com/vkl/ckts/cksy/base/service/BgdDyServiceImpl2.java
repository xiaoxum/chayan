package com.vkl.ckts.cksy.base.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vkl.ckts.cksy.base.dao.IBgdDyDao;
import com.vkl.ckts.cksy.base.dto.PicRecordDto;
import com.vkl.ckts.cksy.base.dto.ProjectRecordDto;
import com.vkl.ckts.common.controller.SpringContextHolder;
import com.vkl.ckts.common.entity.CkProjectEntity;
import com.vkl.ckts.common.entity.VehInfoEntity;
import com.vkl.ckts.common.service.ICehUsnrEntityService;
import com.vkl.ckts.common.utils.BarCodeUtils;
import com.vkl.ckts.common.utils.DateUtil;
import com.vkl.ckts.common.utils.PropertiesUtils;


/**
 * 查验记录打印
 * 
 * @author xiaoxu
 * 
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class BgdDyServiceImpl2 {
	/**
	 * 使用性质表
	 */
	@Autowired
	ICehUsnrEntityService CehUsnrEntityService;

	   /**
	    * 客户端自动打印
	    * 
	    * @throws Exception
	    */
		@SuppressWarnings("null")
		public List<String> dy1() throws Exception {
			IBgdDyDao bgdDyDao = SpringContextHolder.getBean(IBgdDyDao.class);
			// 获取未打印的查验业务
			List<Map<String, Object>> lists = bgdDyDao.findCkInfo();
			List<String> urls=new ArrayList<String>();
			if (lists != null || lists.size() > 0) {
				for (Map<String, Object> map : lists) {

					if (map == null || map.size() < 1) {
						continue;
					}
					if (!dyyw(map)) {
						continue;
					};
					String parentSyxz = bgdDyDao.findParentSyxz(map.get("syxzx")
							.toString());
					// 校车打印
					String cllxs = map.get("cllxs").toString();
					if ("K38".equals(cllxs) || "K28".equals(cllxs)
							|| "K18".equals(cllxs)) {
						// 专用校车
						map.put("zyxc", "☑");
						map.put("fzyxc", "□");
					} else if ("U".equals(parentSyxz)) {
						// 非专用校车，确做校车使用的车型
						map.put("zyxc", "□");
						map.put("fzyxc", "☑");
					}
					bgdDyDao.updatedyStatu(map.get("srln").toString(),
							map.get("rckCount").toString());
				}
			}
			return urls;
		}

		
		/**
		 * 客户端打印
		 * 
		 * @param srln
		 *            流水号
		 * @param rckCount
		 *            复检次数
		 * @throws SQLException
		 * @throws IOException
		 */
		public Map<String, Object> dyOne1(String srln, String rckCount) throws Exception {
			IBgdDyDao bgdDyDao = SpringContextHolder.getBean(IBgdDyDao.class);
			Map<String, Object> map = bgdDyDao.findCkOne(srln, rckCount);
			
			Map<String, Object>  mapsMap=new HashMap<String, Object>();
			
			
			if (map == null || map.size() < 1) {
				return null;
			}
			dyyw(map);
			String parentSyxz = bgdDyDao
					.findParentSyxz(map.get("syxzx").toString());
			// 校车打印
			String cllxs = map.get("cllxs").toString();
			// word生成保存路径
			if ("K38".equals(cllxs) || "K28".equals(cllxs) || "K18".equals(cllxs)) {
				// 专用校车
				map.put("zyxc", "☑");
				map.put("fzyxc", "□");
			} else if ("U".equals(parentSyxz)) {
				// 非专用校车，确做校车使用的车型
				map.put("zyxc", "□");
				map.put("fzyxc", "☑");
			}else {
				mapsMap.put("hphm", map.get("hphm"));
				mapsMap.put("clsbdh", map.get("clsbdh"));
				mapsMap.put("a", map.get("a"));
				mapsMap.put("aqd", map.get("aqd"));
				mapsMap.put("aqjs", map.get("aqjs"));
				mapsMap.put("c", map.get("c"));
				mapsMap.put("cfhzz", map.get("cfhzz"));
				mapsMap.put("cllx", map.get("cllx"));
				mapsMap.put("clpb", map.get("clpb"));
				mapsMap.put("clwg", map.get("clwg"));
				mapsMap.put("csfg", map.get("csfg"));
				mapsMap.put("csys", map.get("csys"));
				mapsMap.put("cysj", map.get("cysj"));
				mapsMap.put("d", map.get("d"));
				mapsMap.put("dj", map.get("dj"));
				mapsMap.put("e", map.get("e"));
				mapsMap.put("f", map.get("f"));
				mapsMap.put("fdjxh", map.get("fdjxh"));
				mapsMap.put("g", map.get("g"));
				mapsMap.put("h", map.get("h"));
				mapsMap.put("hdzrs", map.get("hdzrs"));
				mapsMap.put("hpzl", map.get("hpzl"));
				mapsMap.put("img1", map.get("img1"));
				mapsMap.put("img5", map.get("img5"));
				mapsMap.put("img6", map.get("img6"));
				mapsMap.put("j", map.get("j"));
				mapsMap.put("k", map.get("k"));
				mapsMap.put("l", map.get("l"));
				mapsMap.put("ltgk", map.get("ltgk"));
				mapsMap.put("ltwh", map.get("ltwh"));
				mapsMap.put("mhq", map.get("mhq"));
				mapsMap.put("q", map.get("q"));
				mapsMap.put("r", map.get("r"));
				mapsMap.put("s", map.get("s"));
				mapsMap.put("syxz", map.get("syxz"));
				mapsMap.put("t", map.get("t"));
				mapsMap.put("wbbs", map.get("wbbs"));
				mapsMap.put("wkcc", map.get("wkcc"));
				mapsMap.put("x", map.get("x"));
				mapsMap.put("xsjl", map.get("xsjl"));
				mapsMap.put("y", map.get("y"));
				mapsMap.put("ycjk", map.get("ycjk"));
				mapsMap.put("z", map.get("z"));
				mapsMap.put("zbzl", map.get("zbzl"));
				
			} 
			bgdDyDao.updatedyStatu(map.get("srln").toString(), map.get("rckCount")
					.toString());
			
			
			return mapsMap;
		}

		/**
		 * 数据填充
		 * 
		 * @param map
		 * @throws IOException
		 * @throws SQLException
		 */
		public boolean dyyw(Map<String, Object> map) throws Exception {
			IBgdDyDao bgdDyDao = SpringContextHolder.getBean(IBgdDyDao.class);
			String localIp=PropertiesUtils.getValues("localIp");
			String localPorts=PropertiesUtils.getValues("localPort");
			String fwlj=PropertiesUtils.getValues("docSaveFwPath");
			// 获取查验项目
			operType(map);
			List<ProjectRecordDto> projectRecordDtos = bgdDyDao.findCkItem(map.get(
					"srln").toString());
			List<CkProjectEntity> ckProjectEntities = bgdDyDao.findProject();
			for (CkProjectEntity ckProjectEntity : ckProjectEntities) {
				boolean flag = false;
				for (ProjectRecordDto projectRecordDto : projectRecordDtos) {
					if (projectRecordDto.getProId().equals(ckProjectEntity.getId())) {
						String matchfield = ckProjectEntity.getMatchField();
						if ("A".equals(map.get("operType"))) {
							if ("csys".equals(matchfield)) {
								continue;
							}
							if ("hdzrs".equals(matchfield)) {
								continue;
							}
							if ("cllx".equals(matchfield)) {
								continue;
							}
						}
						
						if ("0".equals(projectRecordDto.getCkflag())) {
							map.put(ckProjectEntity.getMatchField(), "√");
						} else {
							map.put(ckProjectEntity.getMatchField(), "×");
						}
						flag = true;
						break;
					}
				}
				if (!flag) {
					map.put(ckProjectEntity.getMatchField(), "—");
				}
			}
			
			if ("A".equals(map.get("operType"))) {
				VehInfoEntity vehInfoEntity = bgdDyDao.findVehInfo(map.get("srln")
						.toString());
				if (vehInfoEntity == null) {
					map.put("csys", "-");
					map.put("hdzrs", "-");

				} else {
					if (vehInfoEntity.getCsys() == null
							|| vehInfoEntity.getCsys() == "") {
						map.put("csys", "-");
					} else {
						map.put("csys", vehInfoEntity.getCsys());
					}
					if (vehInfoEntity.getHdzk() == null) {
						map.put("hdzrs", "-");
					} else {
						map.put("hdzrs", vehInfoEntity.getHdzk());
					}
				}
				String cllxName = bgdDyDao.findCllxName(map.get("cllxs").toString());
				if (cllxName == null || cllxName == "") {
					map.put("cllx", "-");
				} else {
					map.put("cllx", cllxName);
				}

			}
			if (map.get("hphm") == null || map.get("hphm").toString().trim() == ""
					|| map.get("hphm") == "赣A") {
				map.put("hphm", map.get("clsbdh2"));
			}
			String fileName3 = map.get("srln").toString() + ".jpg";
					
		     com.vkl.ckts.wss.util.Base64.GenerateImage(BarCodeUtils.BarCode(map.get("srln").toString())
					.replace("data:image/png;base64,", ""), PropertiesUtils.getValues("docSaveAbsolutePath")+"/"+ fileName3);
		     map.put("img1", "http:\\\\" + localIp + ":"+ localPorts + "\\"+ fwlj + "\\" + fileName3);
			PicRecordDto picRecord = bgdDyDao.findPicById(map.get("srln")
					.toString(), PropertiesUtils.getValues("zhzphoto"));
			if (picRecord != null) {
				map.put("img5", PropertiesUtils.getValues("fileTomcatUrl")
						+ "/" + picRecord.getPicUrl());
				
				
			}
			PicRecordDto picRecord2 = bgdDyDao.findPicById(map.get("srln")
					.toString(), "03");

			if (picRecord2 != null) {
				// 获取网络照片
				map.put("img6", PropertiesUtils.getValues("fileTomcatUrl")
						+ "/" + picRecord2.getPicUrl());
			}
			// 查验时间
			Date date1 = DateUtil.parseDate(map.get("cysj"));
			String cysj = DateUtil.format(date1, "yyyy年 MM月 dd日 ") == null ? ""
					: DateUtil.format(date1, "yyyy年 MM月 dd日 ");
			map.put("cysj", cysj);
			if (picRecord2 == null || picRecord == null || picRecord.getPicUrl() == null
					|| picRecord.getPicUrl()  == "" || picRecord2.getPicUrl()  == null || picRecord2.getPicUrl()  == "") {
				return false;
			}
			return true;
		}
		

	

		/**
		 * 添加业务类型
		 * 
		 * @param map
		 */
		public void operType(Map<String, Object> map) {
			String[] operTs = null;
			String operType = map.get("operType").toString();
			if (operType != null) {
				operTs = operType.split(",");
				Arrays.sort(operTs);
			}
			if (Arrays.binarySearch(operTs, "A") >= 0) {
				map.put("z", "☑");
			} else {
				map.put("z", "□");
			}

			if (Arrays.binarySearch(operTs, "S") >= 0) {
				map.put("r", "☑");
			} else {
				map.put("r", "□");
			}

			if (Arrays.binarySearch(operTs, "B") >= 0) {
				map.put("y", "☑");
			} else {
				map.put("y", "□");
			}

			if (Arrays.binarySearch(operTs, "C") >= 0) {
				map.put("q", "☑");
			} else {
				map.put("q", "□");
			}

			if (Arrays.binarySearch(operTs, "D") >= 0) {
				map.put("s", "☑");
			} else {
				map.put("s", "□");
			}
			if (Arrays.binarySearch(operTs, "P") >= 0) {
				map.put("g", "☑");
			} else {
				map.put("g", "□");
			}

			if (Arrays.binarySearch(operTs, "E") >= 0) {
				map.put("e", "☑");
			} else {
				map.put("e", "□");
			}
			if (Arrays.binarySearch(operTs, "G") >= 0) {
				map.put("f", "☑");
			} else {
				map.put("f", "□");
			}
			if (Arrays.binarySearch(operTs, "H") >= 0) {
				map.put("x", "☑");
			} else {
				map.put("x", "□");
			}
			if (Arrays.binarySearch(operTs, "I") >= 0) {
				map.put("d", "☑");
			} else {
				map.put("d", "□");
			}

			if (Arrays.binarySearch(operTs, "J") >= 0) {
				map.put("k", "☑");
			} else {
				map.put("k", "□");
			}
			if (Arrays.binarySearch(operTs, "F") >= 0) {
				map.put("c", "☑");
			} else {
				map.put("c", "□");
			}
			if (Arrays.binarySearch(operTs, "K") >= 0) {
				map.put("j", "☑");
			} else {
				map.put("j", "□");
			}

			if (Arrays.binarySearch(operTs, "L") >= 0) {
				map.put("l", "☑");
			} else {
				map.put("l", "□");
			}
			if (Arrays.binarySearch(operTs, "M") >= 0) {
				map.put("h", "☑");
			} else {
				map.put("h", "□");
			}
			if (Arrays.binarySearch(operTs, "N") >= 0) {
				map.put("a", "☑");
			} else {
				map.put("a", "□");
			}
			if (Arrays.binarySearch(operTs, "Y") >= 0) {
				map.put("t", "☑");
			} else {
				map.put("t", "□");
			}
			if (Arrays.binarySearch(operTs, "O") >= 0) {
				map.put("o", "☑");
			} else {
				map.put("o", "□");
			}
			if (Arrays.binarySearch(operTs, "O") >= 0) {
				map.put("p", "☑");
			} else {
				map.put("p", "□");
			}
			if (Arrays.binarySearch(operTs, "Q") >= 0) {
				map.put("er", "☑");
			} else {
				map.put("er", "□");
			}
		}

		
		
}
