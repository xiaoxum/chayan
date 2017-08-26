package com.vkl.ckts.cksy.base.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.vkl.ckts.cksy.base.dao.IBgdDyDao;
import com.vkl.ckts.cksy.base.dto.PicRecordDto;
import com.vkl.ckts.cksy.base.dto.ProjectRecordDto;
import com.vkl.ckts.common.controller.SpringContextHolder;
import com.vkl.ckts.common.entity.CkProjectEntity;
import com.vkl.ckts.common.entity.VehInfoEntity;
import com.vkl.ckts.common.utils.BarCodeUtils;
import com.vkl.ckts.common.utils.Base64Image;
import com.vkl.ckts.common.utils.Base64Utils;
import com.vkl.ckts.common.utils.DateUtil;
import com.vkl.ckts.common.utils.FileUtils;
import com.vkl.ckts.common.utils.PropertiesUtils;
import com.vkl.ckts.common.utils.WordUtil;

/**
 * 查验记录打印
 * 
 * @author xiaoxu
 * 
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class BgdDyServiceImpl {
	IBgdDyDao bgdDyDao = SpringContextHolder.getBean(IBgdDyDao.class);
    private static String localIp;
    private static String localPort;
    private static String localAddress;
    private static String servPicAdr;
    private static String fwlj;
    private static String savePath;
    static{
    	localIp=PropertiesUtils.getValues("localIp");
    	localPort=PropertiesUtils.getValues("localPort");
    	localAddress="http://" + localIp + ":" + localPort + "/";
    	servPicAdr=PropertiesUtils.getValues("fileTomcatUrl");
    	fwlj = PropertiesUtils.getValues("docSaveFwPath");
    	savePath = PropertiesUtils.getValues("docSaveAbsolutePath");
    }
	
	
	
	
	/**
	 * 服务端自动打印
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("null")
	@Test
	public void servicebatchdy() throws Exception {
		// 获取未打印的查验业务
		List<Map<String, Object>> lists = bgdDyDao.findCkInfo();
		if (lists != null || lists.size() > 0) {
			for (Map<String, Object> map : lists) {
				if (map == null || map.size() < 1) {
					return;
				}
				String fileName = geneteJlWord(map, savePath);
				// 打印
				String docPath = savePath + "\\" + fileName;
				String printName = PropertiesUtils
						.getValues("recordPrinterName");
				dyto(docPath, printName);
				FileUtils.deleteFile(docPath);
				bgdDyDao.updatedyStatu(map.get("srln").toString(),
						map.get("rckCount").toString());
				// 打印照片
				/* dyPhoto(map); */
			}
		}
	}

	/**
	 * 客户端自动打印
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("null")
	public List<String> clientBatchdy() throws Exception {
		// 获取未打印的查验业务
		List<Map<String, Object>> lists = bgdDyDao.findCkInfo();
		// word生成保存路径
		List<String> urls = new ArrayList<String>();
		if (lists != null || lists.size() > 0) {
			for (Map<String, Object> map : lists) {
				if (map == null || map.size() < 1) {
					return null;
				}
				String fileName = geneteJlWord(map, savePath);
				urls.add(localAddress + fwlj
						+ "/" + fileName);
				bgdDyDao.updatedyStatu(map.get("srln").toString(),
						map.get("rckCount").toString());
			}
		}
		return urls;
	}

	/**
	 * 客户端打印单个
	 * 
	 * @return
	 */
	public String clientDyJl(String srln, String rckCount) {
		Map<String, Object> map = bgdDyDao.findCkOne(srln, rckCount);
		if (map == null || map.size() < 1) {
			return null;
		}
		String fileName = geneteJlWord(map, savePath);
		bgdDyDao.updatedyStatu(map.get("srln").toString(), map.get("rckCount")
				.toString());
		return localAddress+ fwlj + "/" + fileName;
				
	}

	/**
	 * 服务端打印单个
	 * 
	 * @param srln
	 * @param rckCount
	 */
	public void serviceDyJl(String srln, String rckCount) {
		Map<String, Object> map = bgdDyDao.findCkOne(srln, rckCount);
		if (map == null || map.size() < 1) {
			return;
		}
		String fileName = geneteJlWord(map, savePath);
		// 打印
		String docPath = savePath + "/" + fileName;
		String printName = PropertiesUtils.getValues("recordPrinterName");
		dyto(docPath, printName);
		bgdDyDao.updatedyStatu(map.get("srln").toString(), map.get("rckCount")
				.toString());
	}

	/**
	 * 生成文档
	 * 
	 * @return
	 */
	public String geneteJlWord(Map<String, Object> map, String savePath) {
		String templeteName = "";
		String fileName = map.get("srln").toString()
				+ map.get("rckCount").toString() + "i.doc";
		getDyJlData(map);
		String parentSyxz = bgdDyDao
				.findParentSyxz(map.get("syxzx").toString());
		String cllxs = map.get("cllxs").toString();
		if ("K38".equals(cllxs) || "K28".equals(cllxs) || "K18".equals(cllxs)) {
			// 专用校车
			templeteName = "/xcjlb.ftl";
		} else if ("U".equals(parentSyxz)) {
			// 非专用校车，确做校车使用的车型
			templeteName = "/xcjlb.ftl";

		} else {
			templeteName = "/cyjlb.ftl";
		}
		WordUtil.createWord(map, templeteName, savePath, fileName, "/templete");
		return fileName;
	}

	/**
	 * 获取打印数据
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, Object> getDyJlData(Map<String, Object> map) {
		if (map == null || map.size() < 1) {
			return null;
		}
		try {
			dyyw(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		}
		return map;
	}

	/**
	 * 手动打印照片
	 * 
	 * @param srln
	 *            流水号
	 * @param rckCount
	 *            复检次数
	 */
	public void serviceDyPhoto(String srln, String rckCount) {
		Map<String, Object> map = bgdDyDao.findCkOne(srln, rckCount);
		if (map == null || map.size() < 1) {
			return;
		}
		List<String> fileNames = getDyPhotoUrls(map);
		for (String string : fileNames) {
			dyto(savePath + string, PropertiesUtils.getValues("photoPrintName"));
		}
	}

	/**
	 * 客户端手动打印，返会路径集合
	 * 
	 * @param srln
	 * @param rckCount
	 * @return
	 */
	public List<String> clientDyPhoto(String srln, String rckCount) {
		Map<String, Object> map = bgdDyDao.findCkOne(srln, rckCount);
		if (map == null || map.size() < 1) {
			return null;
		}
		List<String> urls = new ArrayList<String>();
		String fileDir = localAddress + fwlj
				+ "/";
		List<String> fileNames = getDyPhotoUrls(map);
		for (String string : fileNames) {
			urls.add(fileDir + string);
		}
		return urls;
	}

	/**
	 * 获得打印照片路径
	 * 
	 * @param map
	 * @return
	 */
	public List<String> getDyPhotoUrls(Map<String, Object> map) {
		List<String> urls = new ArrayList<String>();
		String parentCllx = bgdDyDao
				.findParentCllx(map.get("cllxs").toString());
		String parentSyxz = bgdDyDao
				.findParentSyxz(map.get("syxzx").toString());
		List<Map<String, Object>> photos = bgdDyDao.findPhotoRecordsOneCK(map
				.get("srln").toString(), map.get("operType").toString(),
				parentSyxz, parentCllx);
		if (photos == null || photos.size() <= 0) {
			return null;
		}
		for (Map<String, Object> map2 : photos) {
			String fileName2 = map.get("srln").toString()
					+ map2.get("picId").toString()
					+ map2.get("rckCount").toString() + ".jpg";
			String imgSt1 = getBase64StrByUrl(servPicAdr + "/" + map2.get("picUrl").toString(), savePath + "/" + fileName2);
			if (imgSt1 == null || imgSt1 == "") {
				continue;
			}
			String fileName3 = map.get("srln").toString()
					+ map2.get("picId").toString()
					+ map2.get("rckCount").toString();
			String printNums = map2.get("printNum").toString();
			List<String> fileNames = genatePhotoWord(imgSt1, printNums,
					fileName3, savePath);
			urls.addAll(fileNames);
			// // 更新照片状态
			bgdDyDao.updatePicDyStatu(map.get("srln").toString(),
					map2.get("rckCount").toString(), map2.get("picId")
							.toString());
		}
		return urls;
	}

	/**
	 * 服务端选择性打印
	 * 
	 * @param printParam
	 */
	public void serviceSelDy(String printParam) {
		List<String> fileNames = selectPhotoDy(printParam);
		for (String string : fileNames) {
			dyto(savePath + string, PropertiesUtils.getValues("photoPrintName"));
		}
	}

	/**
	 * 客户端选择性打印
	 * 
	 * @param printParam
	 * @return
	 */
	public List<String> clientSelDy(String printParam) {
		List<String> urls = new ArrayList<String>();
		String fileDir = localAddress + fwlj
				+ "/";
		List<String> fileNames = selectPhotoDy(printParam);
		if (fileNames==null) {
			return null;
		}
		for (String string : fileNames) {
			urls.add(fileDir + string);
		}
		return urls;

	}

	/**
	 * 选择性照片打印
	 * 
	 * @param printParam
	 * @return
	 */
	public List<String> selectPhotoDy(String printParam) {
		if (StringUtils.isBlank(printParam)) {
			return null;
		}
		List<String> urls = new ArrayList<String>();
		String[] printParamS = printParam.split(",");
		for (String param : printParamS) {
			String[] params = param.split("%#");
			String printNum = params[0];
			String printUrl = params[1];
			int index = printUrl.lastIndexOf("/");
			int indexExt = printUrl.lastIndexOf(".");
			String fileNameNotExit = printUrl.substring(index + 1, indexExt);
			String imgStr = getBase64StrByUrl(printUrl, fileNameNotExit
					+ ".jpg");
			List<String> fileNames = genatePhotoWord(imgStr, printNum,
					fileNameNotExit, savePath);
			urls.addAll(fileNames);
		}
		return urls;

	}

	/**
	 * 通过路径获取base64字符串图片
	 * 
	 * @param url
	 * @param fileName
	 * @return
	 */
	public String getBase64StrByUrl(String url, String fileName) {
		getUrlFile(savePath + "/" + fileName, url);
		String imgSt1 = Base64Image.GetImageStr(savePath + "/" + fileName);
		return imgSt1;
	}

	/**
	 * 生成单条照片word模板
	 * 
	 * @param imgString
	 * @param printNums
	 * @param fileNameNotExit
	 * @param savePath
	 * @return
	 */
	public List<String> genatePhotoWord(String imgString, String printNums,
			String fileNameNotExit, String savePath) {
		String templetePath = "/photos.ftl";
		List<String> urls = new ArrayList<String>();
		Map<String, Object> map3 = new HashMap<String, Object>();
		Integer printNum = Integer.parseInt(printNums);
		Integer num = printNum % 2 == 0 ? printNum : printNum - 1;
		map3.put("img1", imgString);
		String initString = "iVBORw0KGgoAAAANSUhEUgAAAzMAAAHMCAIAAABukmEEAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAABetSURBVHhe7dbBDcQgFENBmqcXuiRRGuD6iGavezHzLcVj+xEgQIAAAQIECDQERiOGFAQIECBAgAABAtsyUwICBAgQIECAQEXAMqtcQg4CBAgQIECAgGWmAwQIECBAgACBioBlVrmEHAQIECBAgAABy0wHCBAgQIAAAQIVAcuscgk5CBAgQIAAAQKWmQ4QIECAAAECBCoCllnlEnIQIECAAAECBCwzHSBAgAABAgQIVAQss8ol5CBAgAABAgQIWGY6QIAAAQIECBCoCFhmlUvIQYAAAQIECBCwzHSAAAECBAgQIFARsMwql5CDAAECBAgQIGCZ6QABAgQIECBAoCJgmVUuIQcBAgQIECBAwDLTAQIECBAgQIBARcAyq1xCDgIECBAgQICAZaYDBAgQIECAAIGKgGVWuYQcBAgQIECAAAHLTAcIECBAgAABAhUBy6xyCTkIECBAgAABApaZDhAgQIAAAQIEKgKWWeUSchAgQIAAAQIELDMdIECAAAECBAhUBCyzyiXkIECAAAECBAhYZjrwf4G11pzz/+/0QgIECBC4X8Ayu/+GXnASeGfZGKp+YvI/AQIECAQEfK4CRxCBAAECBAgQIPAJWGaKQIAAAQIECBCoCFhmlUvIQYAAAQIECBCwzHSAAAECBAgQIFARsMwql5CDAAECBAgQIGCZ6QABAgQIECBAoCJgmVUuIQcBAgQIECBAwDLTAQIECBAgQIBARcAyq1xCDgIECBAgQICAZaYDBAgQIECAAIGKgGVWuYQcBAgQIECAAAHLTAcIECBAgAABAhUBy6xyCTkIECBAgAABApaZDhAgQIAAAQIEKgKWWeUSchAgQIAAAQIELDMdIECAAAECBAhUBCyzyiXkIECAAAECBAhYZjpAgAABAgQIEKgIWGaVS8hBgAABAgQIELDMdIAAAQIECBAgUBGwzCqXkIMAAQIECBAgYJnpAAECBAgQIECgImCZVS4hBwECBAgQIEDAMtMBAgQIECBAgEBFwDKrXEIOAgQIECBAgIBlpgMECBAgQIAAgYqAZVa5hBwECBAgQIAAActMBwgQIECAAAECFQHLrHIJOQgQIECAAAEClpkOECBAgAABAgQqApZZ5RJyECBAgAABAgQsMx0gQIAAAQIECFQELLPKJeQgQIAAAQIECFhmOkCAAAECBAgQqAhYZpVLyEGAAAECBAgQsMx0gAABAgQIECBQEbDMKpeQgwABAgQIECBgmekAAQIECBAgQKAiYJlVLiEHAQIECBAgQMAy0wECBAgQIECAQEXAMqtcQg4CBAgQIECAgGWmAwQIECBAgACBioBlVrmEHAQIECBAgAABy0wHCBAgQIAAAQIVAcuscgk5CBAgQIAAAQKWmQ4QIECAAAECBCoCllnlEnIQIECAAAECBCwzHSBAgAABAgQIVAQss8ol5CBAgAABAgQIWGY6QIAAAQIECBCoCFhmlUvIQYAAAQIECBCwzHSAAAECBAgQIFARsMwql5CDAAECBAgQIGCZ6QABAgQIECBAoCJgmVUuIQcBAgQIECBAwDLTAQIECBAgQIBARcAyq1xCDgIECBAgQICAZaYDBAgQIECAAIGKgGVWuYQcBAgQIECAAAHLTAcIECBAgAABAhUBy6xyCTkIECBAgAABApaZDhAgQIAAAQIEKgKWWeUSchAgQIAAAQIELDMdIECAAAECBAhUBCyzyiXkIECAAAECBAhYZjpAgAABAgQIEKgIWGaVS8hBgAABAgQIELDMdIAAAQIECBAgUBGwzCqXkIMAAQIECBAgYJnpAAECBAgQIECgImCZVS4hBwECBAgQIEDAMtMBAgQIECBAgEBFwDKrXEIOAgQIECBAgIBlpgMECBAgQIAAgYqAZVa5hBwECBAgQIAAActMBwgQIECAAAECFQHLrHIJOQgQIECAAAEClpkOECBAgAABAgQqApZZ5RJyECBAgAABAgQsMx0gQIAAAQIECFQELLPKJeQgQIAAAQIECFhmOkCAAAECBAgQqAhYZpVLyEGAAAECBAgQsMx0gAABAgQIECBQEbDMKpeQgwABAgQIECBgmekAAQIECBAgQKAiYJlVLiEHAQIECBAgQMAy0wECBAgQIECAQEXAMqtcQg4CBAgQIECAgGWmAwQIECBAgACBioBlVrmEHAQIECBAgAABy0wHCBAgQIAAAQIVAcuscgk5CBAgQIAAAQKWmQ4QIECAAAECBCoCllnlEnIQIECAAAECBCwzHSBAgAABAgQIVAQss8ol5CBAgAABAgQIWGY6QIAAAQIECBCoCFhmlUvIQYAAAQIECBCwzHSAAAECBAgQIFARsMwql5CDAAECBAgQIGCZ6QABAgQIECBAoCJgmVUuIQcBAgQIECBAwDLTAQIECBAgQIBARcAyq1xCDgIECBAgQICAZaYDBAgQIECAAIGKgGVWuYQcBAgQIECAAAHLTAcIECBAgAABAhUBy6xyCTkIECBAgAABApaZDhAgQIAAAQIEKgKWWeUSchAgQIAAAQIELDMdIECAAAECBAhUBCyzyiXkIECAAAECBAhYZjpAgAABAgQIEKgIWGaVS8hBgAABAgQIELDMdIAAAQIECBAgUBGwzCqXkIMAAQIECBAgYJnpAAECBAgQIECgImCZVS4hBwECBAgQIEDAMtMBAgQIECBAgEBFwDKrXEIOAgQIECBAgIBlpgMECBAgQIAAgYqAZVa5hBwECBAgQIAAActMBwgQIECAAAECFQHLrHIJOQgQIECAAAEClpkOECBAgAABAgQqApZZ5RJyECBAgAABAgQsMx0gQIAAAQIECFQELLPKJeQgQIAAAQIECFhmOkCAAAECBAgQqAhYZpVLyEGAAAECBAgQsMx0gAABAgQIECBQEbDMKpeQgwABAgQIECBgmekAAQIECBAgQKAiYJlVLiEHAQIECBAgQMAy0wECBAgQIECAQEXAMqtcQg4CBAgQIECAgGWmAwQIECBAgACBioBlVrmEHAQIECBAgAABy0wHCBAgQIAAAQIVAcuscgk5CBAgQIAAAQKWmQ4QIECAAAECBCoCllnlEnIQIECAAAECBCwzHSBAgAABAgQIVAQss8ol5CBAgAABAgQIWGY6QIAAAQIECBCoCFhmlUvIQYAAAQIECBCwzHSAAAECBAgQIFARsMwql5CDAAECBAgQIGCZ6QABAgQIECBAoCJgmVUuIQcBAgQIECBAwDLTAQIECBAgQIBARcAyq1xCDgIECBAgQICAZaYDBAgQIECAAIGKgGVWuYQcBAgQIECAAAHLTAcIECBAgAABAhUBy6xyCTkIECBAgAABApaZDhAgQIAAAQIEKgKWWeUSchAgQIAAAQIELDMdIECAAAECBAhUBCyzyiXkIECAAAECBAhYZjpAgAABAgQIEKgIWGaVS8hBgAABAgQIELDMdIAAAQIECBAgUBGwzCqXkIMAAQIECBAgYJnpAAECBAgQIECgImCZVS4hBwECBAgQIEDAMtMBAgQIECBAgEBFwDKrXEIOAgQIECBAgIBlpgMECBAgQIAAgYqAZVa5hBwECBAgQIAAActMBwgQIECAAAECFQHLrHIJOQgQIECAAAEClpkOECBAgAABAgQqApZZ5RJyECBAgAABAgQsMx0gQIAAAQIECFQELLPKJeQgQIAAAQIECFhmOkCAAAECBAgQqAhYZpVLyEGAAAECBAgQsMx0gAABAgQIECBQEbDMKpeQgwABAgQIECBgmekAAQIECBAgQKAiYJlVLiEHAQIECBAgQMAy0wECBAgQIECAQEXAMqtcQg4CBAgQIECAgGWmAwQIECBAgACBioBlVrmEHAQIECBAgAABy0wHCBAgQIAAAQIVAcuscgk5CBAgQIAAAQKWmQ4QIECAAAECBCoCllnlEnIQIECAAAECBCwzHSBAgAABAgQIVAQss8ol5CBAgAABAgQIWGY6QIAAAQIECBCoCFhmlUvIQYAAAQIECBCwzHSAAAECBAgQIFARsMwql5CDAAECBAgQIGCZ6QABAgQIECBAoCJgmVUuIQcBAgQIECBAwDLTAQIECBAgQIBARcAyq1xCDgIECBAgQICAZaYDBAgQIECAAIGKgGVWuYQcBAgQIECAAAHLTAcIECBAgAABAhUBy6xyCTkIECBAgAABApaZDhAgQIAAAQIEKgKWWeUSchAgQIAAAQIELDMdIECAAAECBAhUBCyzyiXkIECAAAECBAhYZjpAgAABAgQIEKgIWGaVS8hBgAABAgQIELDMdIAAAQIECBAgUBGwzCqXkIMAAQIECBAgYJnpAAECBAgQIECgImCZVS4hBwECBAgQIEDAMtMBAgQIECBAgEBFwDKrXEIOAgQIECBAgIBlpgMECBAgQIAAgYqAZVa5hBwECBAgQIAAActMBwgQIECAAAECFQHLrHIJOQgQIECAAAEClpkOECBAgAABAgQqApZZ5RJyECBAgAABAgQsMx0gQIAAAQIECFQELLPKJeQgQIAAAQIECFhmOkCAAAECBAgQqAhYZpVLyEGAAAECBAgQsMx0gAABAgQIECBQEbDMKpeQgwABAgQIECBgmekAAQIECBAgQKAiYJlVLiEHAQIECBAgQMAy0wECBAgQIECAQEXAMqtcQg4CBAgQIECAgGWmAwQIECBAgACBioBlVrmEHAQIECBAgAAB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";
		for (int i = 1; i <= num; i = i + 2) {
			String fileName3 = fileNameNotExit + i + ".doc";
			map3.put("img2", imgString);
			WordUtil.createWord(map3, templetePath, savePath, fileName3,
					"/templete");
			urls.add(fileName3);
		}
		if (printNum % 2 != 0) {
			String fileName3 = fileNameNotExit + "a.doc";
			map3.put("img2", initString);
			WordUtil.createWord(map3, templetePath, savePath, fileName3,
					"/templete");
			urls.add(fileName3);
		}
		return urls;

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
		// 获取查验项目
		operType(map);
		List<ProjectRecordDto> projectRecordDtos = bgdDyDao.findCkItem(map.get(
				"srln").toString());
		List<CkProjectEntity> ckProjectEntities = bgdDyDao.findProject();
		VehInfoEntity vehInfoEntity = bgdDyDao.findVehInfo(map.get("srln")
				.toString());
		String cllxName = bgdDyDao.findCllxName(map.get("cllxs").toString());
		for (CkProjectEntity ckProjectEntity : ckProjectEntities) {
			boolean flag = false;
			for (ProjectRecordDto projectRecordDto : projectRecordDtos) {
				if (projectRecordDto.getProId().equals(ckProjectEntity.getId())) {
					String matchfield = ckProjectEntity.getMatchField();
					//if ("A".equals(map.get("operType"))) {
						if ("csys".equals(matchfield)) {
							if (vehInfoEntity == null) {
								map.put("csys", "-");
							} else {
								if (vehInfoEntity.getCsys() == null
										|| vehInfoEntity.getCsys() == "") {
									map.put("csys", "-");
								} else {
									if ("0".equals(projectRecordDto.getCkflag())) {
										map.put("csys", vehInfoEntity.getCsys()
												+ "  √");
										//
										// map.put(ckProjectEntity.getMatchField(),
										// "√");
									} else {
										map.put("csys", vehInfoEntity.getCsys()
												+ "  ×");
										// map.put(ckProjectEntity.getMatchField(),
										// "×");
									}
								}
								
							}
							flag = true;
							continue;
						}
						if ("hdzrs".equals(matchfield)) {
							if (vehInfoEntity == null) {
								map.put("hdzrs", "-");
							} else {
								if (vehInfoEntity.getHdzk() == null
										) {
									map.put("hdzrs", "-");
								} else {
									if ("0".equals(projectRecordDto.getCkflag())) {
										map.put("hdzrs", vehInfoEntity.getHdzk()
												+ "  √");
										//
										// map.put(ckProjectEntity.getMatchField(),
										// "√");
									} else {
										map.put("hdzrs", vehInfoEntity.getHdzk()
												+ "  ×");
										// map.put(ckProjectEntity.getMatchField(),
										// "×");
									}
								}
								
							}
							flag = true;
							continue;
						}
						if ("cllx".equals(matchfield)) {
							if (cllxName == null || cllxName == "") {
								map.put("cllx", "-");
							} else {
								if ("0".equals(projectRecordDto.getCkflag())) {
									map.put("cllx", cllxName
											+ "  √");
									//
									// map.put(ckProjectEntity.getMatchField(),
									// "√");
								} else {
									map.put("cllx", cllxName
											+ "  ×");
									// map.put(ckProjectEntity.getMatchField(),
									// "×");
								}
								//map.put("cllx", cllxName);
							}
							flag = true;
							continue;
						}
					//}
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
		String clsbdh = map.get("clsbdh2") == null ? "" : map.get("clsbdh2")
				.toString();
		String hphm = map.get("hphm") == null ? "" : map.get("hphm").toString();
		map.put("hphm", hphm);
		map.put("clsbdhhou", clsbdh);
		// 获取签名
		// Map<String, Object> jms = bgdDyDao.findCkerQm(map.get("cker")
		// .toString());
		String rckC = map.get("rckCount").toString();
		String initString = "iVBORw0KGgoAAAANSUhEUgAAAzMAAAHMCAIAAABukmEEAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAABetSURBVHhe7dbBDcQgFENBmqcXuiRRGuD6iGavezHzLcVj+xEgQIAAAQIECDQERiOGFAQIECBAgAABAtsyUwICBAgQIECAQEXAMqtcQg4CBAgQIECAgGWmAwQIECBAgACBioBlVrmEHAQIECBAgAABy0wHCBAgQIAAAQIVAcuscgk5CBAgQIAAAQKWmQ4QIECAAAECBCoCllnlEnIQIECAAAECBCwzHSBAgAABAgQIVAQss8ol5CBAgAABAgQIWGY6QIAAAQIECBCoCFhmlUvIQYAAAQIECBCwzHSAAAECBAgQIFARsMwql5CDAAECBAgQIGCZ6QABAgQIECBAoCJgmVUuIQcBAgQIECBAwDLTAQIECBAgQIBARcAyq1xCDgIECBAgQICAZaYDBAgQIECAAIGKgGVWuYQcBAgQIECAAAHLTAcIECBAgAABAhUBy6xyCTkIECBAgAABApaZDhAgQIAAAQIEKgKWWeUSchAgQIAAAQIELDMdIECAAAECBAhUBCyzyiXkIECAAAECBAhYZjrwf4G11pzz/+/0QgIECBC4X8Ayu/+GXnASeGfZGKp+YvI/AQIECAQEfK4CRxCBAAECBAgQIPAJWGaKQIAAAQIECBCoCFhmlUvIQYAAAQIECBCwzHSAAAECBAgQIFARsMwql5CDAAECBAgQIGCZ6QABAgQIECBAoCJgmVUuIQcBAgQIECBAwDLTAQIECBAgQIBARcAyq1xCDgIECBAgQICAZaYDBAgQIECAAIGKgGVWuYQcBAgQIECAAAHLTAcIECBAgAABAhUBy6xyCTkIECBAgAABApaZDhAgQIAAAQIEKgKWWeUSchAgQIAAAQIELDMdIECAAAECBAhUBCyzyiXkIECAAAECBAhYZjpAgAABAgQIEKgIWGaVS8hBgAABAgQIELDMdIAAAQIECBAgUBGwzCqXkIMAAQIECBAgYJnpAAECBAgQIECgImCZVS4hBwECBAgQIEDAMtMBAgQIECBAgEBFwDKrXEIOAgQIECBAgIBlpgMECBAgQIAAgYqAZVa5hBwECBAgQIAAActMBwgQIECAAAECFQHLrHIJOQgQIECAAAEClpkOECBAgAABAgQqApZZ5RJyECBAgAABAgQsMx0gQIAAAQIECFQELLPKJeQgQIAAAQIECFhmOkCAAAECBAgQqAhYZpVLyEGAAAECBAgQsMx0gAABAgQIECBQEbDMKpeQgwABAgQIECBgmekAAQIECBAgQKAiYJlVLiEHAQIECBAgQMAy0wECBAgQIECAQEXAMqtcQg4CBAgQIECAgGWmAwQIECBAgACBioBlVrmEHAQIECBAgAABy0wHCBAgQIAAAQIVAcuscgk5CBAgQIAAAQKWmQ4QIECAAAECBCoCllnlEnIQIECAAAECBCwzHSBAgAABAgQIVAQss8ol5CBAgAABAgQIWGY6QIAAAQIECBCoCFhmlUvIQYAAAQIECBCwzHSAAAECBAgQIFARsMwql5CDAAECBAgQIGCZ6QABAgQIECBAoCJgmVUuIQcBAgQIECBAwDLTAQIECBAgQIBARcAyq1xCDgIECBAgQICAZaYDBAgQIECAAIGKgGVWuYQcBAgQIECAAAHLTAcIECBAgAABAhUBy6xyCTkIECBAgAABApaZDhAgQIAAAQIEKgKWWeUSchAgQIAAAQIELDMdIECAAAECBAhUBCyzyiXkIECAAAECBAhYZjpAgAABAgQIEKgIWGaVS8hBgAABAgQIELDMdIAAAQIECBAgUBGwzCqXkIMAAQIECBAgYJnpAAECBAgQIECgImCZVS4hBwECBAgQIEDAMtMBAgQIECBAgEBFwDKrXEIOAgQIECBAgIBlpgMECBAgQIAAgYqAZVa5hBwECBAgQIAAActMBwgQIECAAAECFQHLrHIJOQgQIECAAAEClpkOECBAgAABAgQqApZZ5RJyECBAgAABAgQsMx0gQIAAAQIECFQELLPKJeQgQIAAAQIECFhmOkCAAAECBAgQqAhYZpVLyEGAAAECBAgQsMx0gAABAgQIECBQEbDMKpeQgwABAgQIECBgmekAAQIECBAgQKAiYJlVLiEHAQIECBAgQMAy0wECBAgQIECAQEXAMqtcQg4CBAgQIECAgGWmAwQIECBAgACBioBlVrmEHAQIECBAgAABy0wHCBAgQIAAAQIVAcuscgk5CBAgQIAAAQKWmQ4QIECAAAECBCoCllnlEnIQIECAAAECBCwzHSBAgAABAgQIVAQss8ol5CBAgAABAgQIWGY6QIAAAQIECBCoCFhmlUvIQYAAAQIECBCwzHSAAAECBAgQIFARsMwql5CDAAECBAgQIGCZ6QABAgQIECBAoCJgmVUuIQcBAgQIECBAwDLTAQIECBAgQIBARcAyq1xCDgIECBAgQICAZaYDBAgQIECAAIGKgGVWuYQcBAgQIECAAAHLTAcIECBAgAABAhUBy6xyCTkIECBAgAABApaZDhAgQIAAAQIEKgKWWeUSchAgQIAAAQIELDMdIECAAAECBAhUBCyzyiXkIECAAAECBAhYZjpAgAABAgQIEKgIWGaVS8hBgAABAgQIELDMdIAAAQIECBAgUBGwzCqXkIMAAQIECBAgYJnpAAECBAgQIECgImCZVS4hBwECBAgQIEDAMtMBAgQIECBAgEBFwDKrXEIOAgQIECBAgIBlpgMECBAgQIAAgYqAZVa5hBwECBAgQIAAActMBwgQIECAAAECFQHLrHIJOQgQIECAAAEClpkOECBAgAABAgQqApZZ5RJyECBAgAABAgQsMx0gQIAAAQIECFQELLPKJeQgQIAAAQIECFhmOkCAAAECBAgQqAhYZpVLyEGAAAECBAgQsMx0gAABAgQIECBQEbDMKpeQgwABAgQIECBgmekAAQIECBAgQKAiYJlVLiEHAQIECBAgQMAy0wECBAgQIECAQEXAMqtcQg4CBAgQIECAgGWmAwQIECBAgACBioBlVrmEHAQIECBAgAABy0wHCBAgQIAAAQIVAcuscgk5CBAgQIAAAQKWmQ4QIECAAAECBCoCllnlEnIQIECAAAECBCwzHSBAgAABAgQIVAQss8ol5CBAgAABAgQIWGY6QIAAAQIECBCoCFhmlUvIQYAAAQIECBCwzHSAAAECBAgQIFARsMwql5CDAAECBAgQIGCZ6QABAgQIECBAoCJgmVUuIQcBAgQIECBAwDLTAQIECBAgQIBARcAyq1xCDgIECBAgQICAZaYDBAgQIECAAIGKgGVWuYQcBAgQIECAAAHLTAcIECBAgAABAhUBy6xyCTkIECBAgAABApaZDhAgQIAAAQIEKgKWWeUSchAgQIAAAQIELDMdIECAAAECBAhUBCyzyiXkIECAAAECBAhYZjpAgAABAgQIEKgIWGaVS8hBgAABAgQIELDMdIAAAQIECBAgUBGwzCqXkIMAAQIECBAgYJnpAAECBAgQIECgImCZVS4hBwECBAgQIEDAMtMBAgQIECBAgEBFwDKrXEIOAgQIECBAgIBlpgMECBAgQIAAgYqAZVa5hBwECBAgQIAAActMBwgQIECAAAECFQHLrHIJOQgQIECAAAEClpkOECBAgAABAgQqApZZ5RJyECBAgAABAgQsMx0gQIAAAQIECFQELLPKJeQgQIAAAQIECFhmOkCAAAECBAgQqAhYZpVLyEGAAAECBAgQsMx0gAABAgQIECBQEbDMKpeQgwABAgQIECBgmekAAQIECBAgQKAiYJlVLiEHAQIECBAgQMAy0wECBAgQIECAQEXAMqtcQg4CBAgQIECAgGWmAwQIECBAgACBioBlVrmEHAQIECBAgAABy0wHCBAgQIAAAQIVAcuscgk5CBAgQIAAAQKWmQ4QIECAAAECBCoCllnlEnIQIECAAAECBCwzHSBAgAABAgQIVAQss8ol5CBAgAABAgQIWGY6QIAAAQIECBCoCFhmlUvIQYAAAQIECBCwzHSAAAECBAgQIFARsMwql5CDAAECBAgQIGCZ6QABAgQIECBAoCJgmVUuIQcBAgQIECBAwDLTAQIECBAgQIBARcAyq1xCDgIECBAgQICAZaYDBAgQIECAAIGKgGVWuYQcBAgQIECAAAHLTAcIECBAgAABAhUBy6xyCTkIECBAgAABApaZDhAgQIAAAQIEKgKWWeUSchAgQIAAAQIELDMdIECAAAECBAhUBCyzyiXkIECAAAECBAhYZjpAgAABAgQIEKgIWGaVS8hBgAABAgQIELDMdIAAAQIECBAgUBGwzCqXkIMAAQIECBAgYJnpAAECBAgQIECgImCZVS4hBwECBAgQIEDAMtMBAgQIECBAgEBFwDKrXEIOAgQIECBAgIBlpgMECBAgQIAAgYqAZVa5hBwECBAgQIAAActMBwgQIECAAAECFQHLrHIJOQgQIECAAAEClpkOECBAgAABAgQqApZZ5RJyECBAgAABAgQsMx0gQIAAAQIECFQELLPKJeQgQIAAAQIECFhmOkCAAAECBAgQqAhYZpVLyEGAAAECBAgQsMx0gAABAgQIECBQEbDMKpeQgwABAgQIECBgmekAAQIECBAgQKAiYJlVLiEHAQIECBAgQMAy0wECBAgQIECAQEXAMqtcQg4CBAgQIECAgGWmAwQIECBAgACBioBlVrmEHAQIECBAgAABy0wHCBAgQIAAAQIVAcuscgk5CBAgQIAAAQKWmQ4QIECAAAECBCoCllnlEnIQIECAAAECBCwzHSBAgAABAgQIVAQss8ol5CBAgAABAgQIWGY6QIAAAQIECBCoCFhmlUvIQYAAAQIECBCwzHSAAAECBAgQIFARsMwql5CDAAECBAgQIGCZ6QABAgQIECBAoCJgmVUuIQcBAgQIECBAwDLTAQIECBAgQIBARcAyq1xCDgIECBAgQICAZaYDBAgQIECAAIGKgGVWuYQcBAgQIECAAAHLTAcIECBAgAABAhUBy6xyCTkIECBAgAABApaZDhAgQIAAAQIEKgKWWeUSchAgQIAAAQIELDMdIECAAAECBAhUBCyzyiXkIECAAAECBAhYZjpAgAABAgQIEKgIWGaVS8hBgAABAgQIELDMdIAAAQIECBAgUBGwzCqXkIMAAQIECBAgYJnpAAECBAgQIECgImCZVS4hBwECBAgQIEDAMtMBAgQIECBAgEBFwDKrXEIOAgQIECBAgIBlpgMECBAgQIAAgYqAZVa5hBwECBAgQIAAActMBwgQIECAAAECFQHLrHIJOQgQIECAAAEClpkOECBAgAABAgQqApZZ5RJyECBAgAABAgQsMx0gQIAAAQIECFQELLPKJeQgQIAAAQIECFhmOkCAAAECBAgQqAhYZpVLyEGAAAECBAgQsMx0gAABAgQIECBQEbDMKpeQgwABAgQIECBgmekAAQIECBAgQKAiYJlVLiEHAQIECBAgQMAy0wECBAgQIECAQEXAMqtcQg4CBAgQIECAgGWmAwQIECBAgACBioBlVrmEHAQIECBAgAABy0wHCBAgQIAAAQIVAcuscgk5CBAgQIAAAQKWmQ4QIECAAAECBCoCllnlEnIQIECAAAECBCwzHSBAgAABAgQIVAQss8ol5CBAgAABAgQIWGY6QIAAAQIECBCoCFhmlUvIQYAAAQIECBCwzHSAAAECBAgQIFARsMwql5CDAAECBAgQIGCZ6QABAgQIECBAoCJgmVUuIQcBAgQIECBAwDLTAQIECBAgQIBARcAyq1xCDgIECBAgQICAZaYDBAgQIECAAIGKgGVWuYQcBAgQIECAAAHLTAcIECBAgAABAhUBy6xyCTkIECBAgAABApaZDhAgQIAAAQIEKgKWWeUSchAgQIAAAQIELDMdIECAAAECBAhUBCyzyiXkIECAAAECBAhYZjpAgAABAgQIEKgIWGaVS8hBgAABAgQIELDMdIAAAQIECBAgUBGwzCqXkIMAAQIECBAgYJnpAAECBAgQIECgImCZVS4hBwECBAgQIEDAMtMBAgQIECBAgEBFwDKrXEIOAgQIECBAgIBlpgMECBAgQIAAgYqAZVa5hBwECBAgQIAAActMBwgQIECAAAECFQHLrHIJOQgQIECAAAEClpkOECBAgAABAgQqApZZ5RJyECBAgAABAgQsMx0gQIAAAQIECFQELLPKJeQgQIAAAQIECFhmOkCAAAECBAgQqAhYZpVLyEGAAAECBAgQsMx0gAABAgQIECBQEbDMKpeQgwABAgQIECBgmekAAQIECBAgQKAiYJlVLiEHAQIECBAgQMAy0wECBAgQIECAQEXAMqtcQg4CBAgQIECAgGWmAwQIECBAgACBioBlVrmEHAQIECBAgAABy0wHCBAgQIAAAQIVAcuscgk5CBAgQIAAAQKWmQ4QIECAAAECBCoCllnlEnIQIECAAAECBCwzHSBAgAABAgQIVAQss8ol5CBAgAABAgQIWGY6QIAAAQIECBCoCFhmlUvIQYAAAQIECBCwzHSAAAECBAgQIFARsMwql5CDAAECBAgQIGCZ6QABAgQIECBAoCJgmVUuIQcBAgQIECBAwDLTAQIECBAgQIBARcAyq1xCDgIECBAgQICAZaYDBAgQIECAAIGKgGVWuYQcBAgQIECAAAHLTAcIECBAgAABAhUBy6xyCTkIECBAgAABApaZDhAgQIAAAQIEKgKWWeUSchAgQIAAAQIELDMdIECAAAECBAhUBCyzyiXkIECAAAECBAhYZjpAgAABAgQIEKgIWGaVS8hBgAABAgQIELDMdIAAAQIECBAgUBGwzCqXkIMAAQIECBAgYJnpAAECBAgQIECgImCZVS4hBwECBAgQIEDAMtMBAgQIECBAgEBFwDKrXEIOAgQIECBAgIBlpgMECBAgQIAAgYqAZVa5hBwECBAgQIAAActMBwgQIECAAAECFQHLrHIJOQgQIECAAAEClpkOECBAgAABAgQqApZZ5RJyECBAgAABAgQsMx0gQIAAAQIECFQELLPKJeQgQIAAAQIECFhmOkCAAAECBAgQqAhYZpVLyEGAAAECBAgQsMx0gAABAgQIECBQEbDMKpeQgwABAgQIECBgmekAAQIECBAgQKAiYJlVLiEHAQIECBAgQMAy0wECBAgQIECAQEXAMqtcQg4CBAgQIECAgGWmAwQIECBAgACBioBlVrmEHAQIECBAgAABy0wHCBAgQIAAAQIVAcuscgk5CBAgQIAAAQKWmQ4QIECAAAECBCoCllnlEnIQIECAAAECBCwzHSBAgAABAgQIVAQss8ol5CBAgAABAgQIWGY6QIAAAQIECBCoCFhmlUvIQYAAAQIECBCwzHSAAAECBAgQIFARsMwql5CDAAECBAgQIGCZ6QABAgQIECBAoCJgmVUuIQcBAgQIECBAwDLTAQIECBAgQIBARcAyq1xCDgIECBAgQICAZaYDBAgQIECAAIGKgGVWuYQcBAgQIECAAAHLTAcIECBAgAABAhUBy6xyCTkIECBAgAABApaZDhAgQIAAAQIEKgKWWeUSchAgQIAAAQIELDMdIECAAAECBAhUBCyzyiXkIECAAAECBAhYZjpAgAABAgQIEKgIWGaVS8hBgAABAgQIELDMdIAAAQIECBAgUBGwzCqXkIMAAQIECBAgYJnpAAECBAgQIECgImCZVS4hBwECBAgQIEDAMtMBAgQIECBAgEBFwDKrXEIOAgQIECBAgIBlpgMECBAgQIAAgYqAZVa5hBwECBAgQIAAActMBwgQIECAAAECFQHLrHIJOQgQIECAAAEClpkOECBAgAABAgQqApZZ5RJyECBAgAABAgQsMx0gQIAAAQIECFQELLPKJeQgQIAAAQIECFhmOkCAAAECBAgQqAhYZpVLyEGAAAECBAgQsMx0gAABAgQIECBQEbDMKpeQgwABAgQIECBgmekAAQIECBAgQKAiYJlVLiEHAQIECBAgQMAy0wECBAgQIECAQEXAMqtcQg4CBAgQIECAgGWmAwQIECBAgACBioBlVrmEHAQIECBAgAABy0wHCBAgQIAAAQIVAcuscgk5CBAgQIAAAQKWmQ4QIECAAAECBCoCD9X6p+nf5ct2AAAAAElFTkSuQmCC";

		map.put("img1", BarCodeUtils.BarCode(map.get("srln").toString())
				.replace("data:image/png;base64,", ""));
		String imgSt1 = "";
		String imgSt = "";

		PicRecordDto picRecord = bgdDyDao.findPicById(map.get("srln")
				.toString(), PropertiesUtils.getValues("zhzphoto"));
		if (picRecord != null) {
			// 获取网络照片
			getUrlFile(PropertiesUtils.getValues("printpath") + map.get("srln")
					+ "01z.jpg", PropertiesUtils.getValues("fileTomcatUrl")
					+ "/" + picRecord.getPicUrl());
			// 转化为字符串
			imgSt = Base64Image.GetImageStr(PropertiesUtils
					.getValues("printpath") + map.get("srln") + "01z.jpg");
		}
		PicRecordDto picRecord2 = bgdDyDao.findPicById(map.get("srln")
				.toString(), "03");

		if (picRecord2 != null) {
			// 获取网络照片
			getUrlFile(PropertiesUtils.getValues("printpath") + map.get("srln")
					+ "03z.jpg", PropertiesUtils.getValues("fileTomcatUrl")
					+ "/" + picRecord2.getPicUrl());
			imgSt1 = Base64Image.GetImageStr(PropertiesUtils
					.getValues("printpath") + map.get("srln") + "03z.jpg");
		}
		String bzhgzString = "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//////////8D6AAAAAD/////////////////////////////A+gAAAAA/////////////////////////////wPoAAAAAP////////////////////////////8D6AAAOEJJTQQAAAAAAAACAAE4QklNBAIAAAAAAAQAAAAAOEJJTQQwAAAAAAACAQE4QklNBC0AAAAAAAYAAQAAAAI4QklNBAgAAAAAABAAAAABAAACQAAAAkAAAAAAOEJJTQQeAAAAAAAEAAAAADhCSU0EGgAAAAADPwAAAAYAAAAAAAAAAAAAADkAAADjAAAABWcqaAeYmAAtADEAAAABAAAAAAAAAAAAAAAAAAAAAAAAAAEAAAAAAAAAAAAAAOMAAAA5AAAAAAAAAAAAAAAAAAAAAAEAAAAAAAAAAAAAAAAAAAAAAAAAEAAAAAEAAAAAAABudWxsAAAAAgAAAAZib3VuZHNPYmpjAAAAAQAAAAAAAFJjdDEAAAAEAAAAAFRvcCBsb25nAAAAAAAAAABMZWZ0bG9uZwAAAAAAAAAAQnRvbWxvbmcAAAA5AAAAAFJnaHRsb25nAAAA4wAAAAZzbGljZXNWbExzAAAAAU9iamMAAAABAAAAAAAFc2xpY2UAAAASAAAAB3NsaWNlSURsb25nAAAAAAAAAAdncm91cElEbG9uZwAAAAAAAAAGb3JpZ2luZW51bQAAAAxFU2xpY2VPcmlnaW4AAAANYXV0b0dlbmVyYXRlZAAAAABUeXBlZW51bQAAAApFU2xpY2VUeXBlAAAAAEltZyAAAAAGYm91bmRzT2JqYwAAAAEAAAAAAABSY3QxAAAABAAAAABUb3AgbG9uZwAAAAAAAAAATGVmdGxvbmcAAAAAAAAAAEJ0b21sb25nAAAAOQAAAABSZ2h0bG9uZwAAAOMAAAADdXJsVEVYVAAAAAEAAAAAAABudWxsVEVYVAAAAAEAAAAAAABNc2dlVEVYVAAAAAEAAAAAAAZhbHRUYWdURVhUAAAAAQAAAAAADmNlbGxUZXh0SXNIVE1MYm9vbAEAAAAIY2VsbFRleHRURVhUAAAAAQAAAAAACWhvcnpBbGlnbmVudW0AAAAPRVNsaWNlSG9yekFsaWduAAAAB2RlZmF1bHQAAAAJdmVydEFsaWduZW51bQAAAA9FU2xpY2VWZXJ0QWxpZ24AAAAH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bmcAAAAAAAAAAEFGcm1sb25nAAAAAAAAAABGc0ZyVmxMcwAAAAFsb25nVAClegAAAABMQ250bG9uZwAAAAAAADhCSU1Sb2xsAAAACAAAAAAAAAAAOEJJTQ+hAAAAAAAcbWZyaQAAAAIAAAAQAAAAAQAAAAAAAAABAAAAADhCSU0EBgAAAAAABwACAAAAAQEA/+EN1mh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8APD94cGFja2V0IGJlZ2luPSLvu78iIGlkPSJXNU0wTXBDZWhpSHpyZVN6TlRjemtjOWQiPz4gPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyIgeDp4bXB0az0iQWRvYmUgWE1QIENvcmUgNS4zLWMwMTEgNjYuMTQ1NjYxLCAyMDEyLzAyLzA2LTE0OjU2OjI3ICAgICAgICAiPiA8cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPiA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtbG5zOnhtcE1NPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvbW0vIiB4bWxuczpzdEV2dD0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL3NUeXBlL1Jlc291cmNlRXZlbnQjIiB4bWxuczpkYz0iaHR0cDovL3B1cmwub3JnL2RjL2VsZW1lbnRzLzEuMS8iIHhtbG5zOnBob3Rvc2hvcD0iaHR0cDovL25zLmFkb2JlLmNvbS9waG90b3Nob3AvMS4wLyIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgQ1M2IChXaW5kb3dzKSIgeG1wOkNyZWF0ZURhdGU9IjIwMTctMDYtMTJUMjI6MjI6NTArMDg6MDAiIHhtcDpNZXRhZGF0YURhdGU9IjIwMTctMDYtMTJUMjI6MjI6NTArMDg6MDAiIHhtcDpNb2RpZnlEYXRlPSIyMDE3LTA2LTEyVDIyOjIyOjUwKzA4OjAwIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOkZBMzcxQ0YyNzg0RkU3MTE5QkZBQUMyOUI5QUMzODA2IiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOkY5MzcxQ0YyNzg0RkU3MTE5QkZBQUMyOUI5QUMzODA2IiB4bXBNTTpPcmlnaW5hbERvY3VtZW50SUQ9InhtcC5kaWQ6RjkzNzFDRjI3ODRGRTcxMTlCRkFBQzI5QjlBQzM4MDYiIGRjOmZvcm1hdD0iaW1hZ2UvanBlZyIgcGhvdG9zaG9wOkNvbG9yTW9kZT0iMyIgcGhvdG9zaG9wOklDQ1Byb2ZpbGU9InNSR0IgSUVDNjE5NjYtMi4xIj4gPHhtcE1NOkhpc3Rvcnk+IDxyZGY6U2VxPiA8cmRmOmxpIHN0RXZ0OmFjdGlvbj0iY3JlYXRlZCIgc3RFdnQ6aW5zdGFuY2VJRD0ieG1wLmlpZDpGOTM3MUNGMjc4NEZFNzExOUJGQUFDMjlCOUFDMzgwNiIgc3RFdnQ6d2hlbj0iMjAxNy0wNi0xMlQyMjoyMjo1MCswODowMCIgc3RFdnQ6c29mdHdhcmVBZ2VudD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoV2luZG93cykiLz4gPHJkZjpsaSBzdEV2dDphY3Rpb249InNhdmVkIiBzdEV2dDppbnN0YW5jZUlEPSJ4bXAuaWlkOkZBMzcxQ0YyNzg0RkU3MTE5QkZBQUMyOUI5QUMzODA2IiBzdEV2dDp3aGVuPSIyMDE3LTA2LTEyVDIyOjIyOjUwKzA4OjAwIiBzdEV2dDpzb2Z0d2FyZUFnZW50PSJBZG9iZSBQaG90b3Nob3AgQ1M2IChXaW5kb3dzKSIgc3RFdnQ6Y2hhbmdlZD0iLyIvPiA8L3JkZjpTZXE+IDwveG1wTU06SGlzdG9yeT4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg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";
		if (map.get("rckCount") != null && Integer.parseInt(rckC) >= 1) {
			if ("0".equals(map.get("auditFlag"))) {
				map.put("img4", bzhgzString);
				;
				map.put("img2", initString);
			}

		} else {
			if ("0".equals(map.get("auditFlag"))) {
				map.put("img2", bzhgzString);
				map.put("img4", initString);
			}
		}
		map.put("img2", bzhgzString);
		String viewName = map.get("viewName") == null ? "" : map
				.get("viewName").toString();
		String viewBaseName = viewName == "" ? "" : Base64Utils
				.decode(viewName);
		String pathString = this.getClass().getClassLoader().getResource("/")
				.getPath();
		String ckerpath = pathString + "/templete/ckername/" + viewBaseName
				+ ".png";
		File file = new File(ckerpath);
		String signNameStr = "";
		if (file.exists()) {
			signNameStr = Base64Image.GetImageStr(ckerpath);
		} else {
			signNameStr = initString;
		}
		map.put("img3", signNameStr);
		map.put("img4", initString);
		// 质证照
		if (imgSt == null || imgSt.length() == 0) {
			map.put("img5", initString);
		} else {
			map.put("img5", imgSt);
		}
		// 车辆识别代号
		if (imgSt1 == null || imgSt1.length() == 0) {
			map.put("img6", initString);
		} else {
			map.put("img6", imgSt1);
		}
		// 查验时间
		Date date1 = DateUtil.parseDate(map.get("cysj"));
		String cysj = DateUtil.format(date1, "yyyy年 MM月 dd日 ") == null ? ""
				: DateUtil.format(date1, "yyyy年 MM月 dd日 ");
		map.put("cysj", cysj);
		map.put("fjsh", cysj);
		// 删除临时文件
		FileUtils.deleteFile(PropertiesUtils.getValues("printpath")
				+ map.get("srln") + "01z.jpg");
		FileUtils.deleteFile(PropertiesUtils.getValues("printpath")
				+ map.get("srln") + "03z.jpg");
		if (picRecord2 == null || picRecord == null || imgSt1 == null
				|| imgSt1 == "" || imgSt1 == null || imgSt1 == "") {
			return false;
		}
		return true;
	}

	/**
	 * clob转化为字符串
	 * 
	 * @param clob
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public String clobToString(Clob clob) throws SQLException, IOException {
		String reString = "";
		java.io.Reader is = clob.getCharacterStream();// 得到流
		BufferedReader br = new BufferedReader(is);
		String s = br.readLine();
		StringBuffer sb = new StringBuffer();
		while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
			sb.append(s);
			s = br.readLine();
		}
		reString = sb.toString();
		return reString;
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

	/**
	 * 打印
	 * 
	 * @param path
	 *            文件路径
	 * @param pringName
	 *            打印机名字
	 */
	public void dyto(String path, String pringName) {
		try {
			ComThread.InitSTA();
			ActiveXComponent wd = new ActiveXComponent("Word.Application");
			wd.setProperty("ActivePrinter", new Variant(pringName));
			// 不打开文档
			Dispatch.put(wd, "Visible", new Variant(false));
			Dispatch document = wd.getProperty("Documents").toDispatch();
			// 打开文档
			Dispatch doc = Dispatch.invoke(document, "Open", Dispatch.Method,
					new Object[] { path }, new int[1]).toDispatch();
			// 开始打印
			Dispatch.callN(doc, "PrintOut");

			wd.invoke("Quit", new Variant[] {});
			// Dispatch.call(doc, "Close",new Variant(true));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 始终释放资源
			ComThread.Release();
			try {
				final String pathsString = path;
				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(10000);
							FileUtils.deleteFile(pathsString);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).start();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 网络获取文件
	 * 
	 * @param fileName
	 * @param urls
	 * @return
	 */
	public static File getUrlFile(String fileName, String urls) {
		File file = null;
		try {
			file = new File(fileName);// 创建新文件
			if (file != null && !file.exists()) {
				file.createNewFile();
			}
			OutputStream oputstream = new FileOutputStream(file);
			URL url = new URL(urls);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			uc.setDoInput(true);// 设置是否要从 URL 连接读取数据,默认为true
			uc.connect();
			InputStream iputstream = uc.getInputStream();
			org.apache.commons.io.FileUtils.copyInputStreamToFile(iputstream,
					file);
			oputstream.flush();
			iputstream.close();
			oputstream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;

	}

	
	
	public Map<String, Object> test(String srln,String rckCount){
		Map<String, Object> map = bgdDyDao.findCkOne(srln, rckCount);
		if (map == null || map.size() < 1) {
			return  null;
		}
		map=getDyJlData1(map);
		bgdDyDao.updatedyStatu(map.get("srln").toString(),
				map.get("rckCount").toString());
		return map;
		
		
	}
	
	
	
	
	/**
	 * 获取打印数据
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, Object> getDyJlData1(Map<String, Object> map) {
		if (map == null || map.size() < 1) {
			return null;
		}
		try {
			dyyw1(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		}
		return map;
	}
	/**
	 * 数据填充
	 * 
	 * @param map
	 * @throws IOException
	 * @throws SQLException
	 */
	public boolean dyyw1(Map<String, Object> map) throws Exception {
		IBgdDyDao bgdDyDao = SpringContextHolder.getBean(IBgdDyDao.class);
		// 获取查验项目
		operType(map);
		List<ProjectRecordDto> projectRecordDtos = bgdDyDao.findCkItem(map.get(
				"srln").toString());
		List<CkProjectEntity> ckProjectEntities = bgdDyDao.findProject();
		VehInfoEntity vehInfoEntity = bgdDyDao.findVehInfo(map.get("srln")
				.toString());
		String cllxName = bgdDyDao.findCllxName(map.get("cllxs").toString());
		for (CkProjectEntity ckProjectEntity : ckProjectEntities) {
			boolean flag = false;
			for (ProjectRecordDto projectRecordDto : projectRecordDtos) {
				if (projectRecordDto.getProId().equals(ckProjectEntity.getId())) {
					String matchfield = ckProjectEntity.getMatchField();
						if ("csys".equals(matchfield)) {
							if (vehInfoEntity == null) {
								map.put("csys", "-");
							} else {
								if (vehInfoEntity.getCsys() == null
										|| vehInfoEntity.getCsys() == "") {
									map.put("csys", "-");
								} else {
									if ("0".equals(projectRecordDto.getCkflag())) {
										map.put("csys", vehInfoEntity.getCsys());
												
									} else {
										map.put("csys", vehInfoEntity.getCsys());
												
									}
								}
								
							}
							flag = true;
							continue;
						}
						if ("hdzrs".equals(matchfield)) {
							if (vehInfoEntity == null) {
								map.put("hdzrs", "-");
							} else {
								if (vehInfoEntity.getHdzk() == null
										) {
									map.put("hdzrs", "-");
								} else {
									if ("0".equals(projectRecordDto.getCkflag())) {
										map.put("hdzrs", vehInfoEntity.getHdzk());
												
									} else {
										map.put("hdzrs", vehInfoEntity.getHdzk());
												
									}
								}
								
							}
							flag = true;
							continue;
						}
						if ("cllx".equals(matchfield)) {
							if (cllxName == null || cllxName == "") {
								map.put("cllx", "-");
							} else {
								if ("0".equals(projectRecordDto.getCkflag())) {
									map.put("cllx", cllxName);
											

								} else {
									map.put("cllx", cllxName);
											

								}
							}
							flag = true;
							continue;
						}
					//}
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
		map.put("hphm", map.get("hphm"));
		map.put("clsbdhhou", map.get("clsbdh2"));
		BarCodeUtils.BarCode2(map.get("srln").toString(),savePath+"/"+map.get("srln").toString()+".jpg");
		map.put("smt", localAddress+ fwlj+"/"+map.get("srln").toString()+".jpg");
		//map.put("img1", map.get("srln").toString());
		String imgSt1 = "";
		String imgSt = "";
		PicRecordDto picRecord = bgdDyDao.findPicById(map.get("srln")
				.toString(), PropertiesUtils.getValues("zhzphoto"));
		if (picRecord != null) {
			// 转化为字符串
			imgSt = servPicAdr+ "/" + picRecord.getPicUrl();			
		}
		PicRecordDto picRecord2 = bgdDyDao.findPicById(map.get("srln")
				.toString(), "03");
		if (picRecord2 != null) {
			// 获取网络照片
			imgSt1 = servPicAdr+ "/" + picRecord2.getPicUrl();			
		}
		map.put("img2", localAddress+"ckts_pc/photo/hege.png");
				
//		String viewName = map.get("loginName") == null ? "" : map
//				.get("loginName").toString();
		
		
		
//		String ckerpath =  localAddress+"ckts_pc/photo/ckername/" + viewName
//				+ ".png";
		String fileId=map.get("cker")==null?"":map.get("cker").toString();
		Map<String, Object> map2=bgdDyDao.findCkerQmView(fileId);
		String ckerpath =  servPicAdr+"/" + map2.get("cyqm");
		map.put("img3", ckerpath);
		/*map.put("img4", "");*/
		// 质证照
		map.put("img5", imgSt);
		map.put("img6", imgSt1);
		// 查验时间
		Date date1 = DateUtil.parseDate(map.get("cysj"));
		String cysj = DateUtil.format(date1, "yyyy年 MM月 dd日 ") == null ? ""
				: DateUtil.format(date1, "yyyy年 MM月 dd日 ");
		map.put("cysj", cysj);
		/*map.put("fjsh", cysj);*/
		if (picRecord2 == null || picRecord == null || imgSt1 == null
				|| imgSt1 == "" || imgSt1 == null || imgSt1 == "") {
			return false;
		}
		return true;
	}
	
	
	/**
	    * 客户端自动打印
	    * 
	    * @throws Exception
	    */
		public List<Map<String, Object>> getNoDyJl() throws Exception {
			// 获取未打印的查验业务
			List<Map<String, Object>> lists = bgdDyDao.findCkInfo();
			// word生成保存路径
			return lists;
		}
}
