package com.vkl.ckts.cksy.servacpt.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.common.controller.SpringContextHolder;
import com.vkl.ckts.common.entity.CkInfoEntity;
import com.vkl.ckts.common.entity.ResourceEntity;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.common.utils.PropertiesUtils;
import com.vkl.ckts.wss.util.URLDeEUtils;
import com.vkl.ckts.wss.util.XMLConverter;
import com.vkl.ckts.cksy.servacpt.dao.IResourceDao;
import com.vkl.ckts.cksy.servacpt.entity.ResProDto;
import com.vkl.ckts.cksy.servacpt.entity.ResourceDto;
import com.vkl.ckts.cksy.servacpt.service.ICkInfoEntityService;
import com.vkl.ckts.cksy.servacpt.service.IResourceService;

/**
 * 业务影像资料表Service接口实现类
 * 
 * @author jiajun
 * @version 1.0
 */
@Service
@Transactional
public class ResourceServiceImpl extends ServiceImpl<IResourceDao, ResourceEntity, String> implements IResourceService {

	@Autowired
	ICkInfoEntityService iCkInfoEntityService;

	/**
	 * 业务影像资料上传
	 * 
	 * @param File
	 *            上传的影像文件
	 * @param String
	 *            上传的影像名称
	 */
	@Override
	public void upResource(String url, String srln, String rckCount, String resId) {
		CkInfoEntity ckInfoEntity = iCkInfoEntityService.findckinfobysrln(srln, rckCount);
		if (ckInfoEntity==null) {
			return;
		}
		String operStatu = ckInfoEntity.getOperStatu();
		ResourceDto resourceDto=super.dao.findResRecord(srln, rckCount, resId);
		if (resourceDto!=null) {
			resourceDto.setRepicUrl(url);
			resourceDto.setUpdDate(new Date());
			resourceDto.setUpDateTime(new Date());
			super.dao.updateResInfo(resourceDto);
			if ("5".equals(operStatu)) {
				new uploadResThread(resourceDto).start();
			}
			return;
		}
		// 新建业务影像资料对象
		ResourceDto resourceEntity = new ResourceDto();
		Date date = new Date();
		resourceEntity.setCreateDate(date); // 创建日期
		resourceEntity.setHdTime(date); // 高拍时间
		resourceEntity.setRckCount(rckCount); // 复检次数
		resourceEntity.setUpDateTime(date); // 上传时间
		resourceEntity.setSrln(srln);
		resourceEntity.setRepicUrl(url);
		resourceEntity.setResId(resId);
		resourceEntity.setDelFlag("0");
		super.dao.insertData(resourceEntity);
		if ("5".equals(operStatu)) {
		     new uploadResThread(resourceEntity).start();
		}
	}

	@Override
	public List<ResProDto> findResources() {
		// TODO Auto-generated method stub
		return super.dao.findResources();
	}

	class uploadResThread extends Thread {
		private ResourceDto resourceDto;

		@Override
		public void run() {
			// TODO Auto-generated method stub
			uploadPicRecord(resourceDto);
		}

		public String fileUpload(String url, String writeDocXml, File file) {
			PostMethod postMethod = new PostMethod(url);
			String returnStr = "";
			try {
				// FilePart：用来上传文件的类
				FilePart fp = new FilePart("filedata", file);
				StringPart sp = new StringPart("writeDocXml", writeDocXml);
				Part[] parts = { fp, sp };

				// 对于MIME类型的请求，httpclient建议全用MulitPartRequestEntity进行包装
				MultipartRequestEntity mre = new MultipartRequestEntity(parts, postMethod.getParams());
				postMethod.setRequestEntity(mre);
				HttpClient client = new HttpClient();
				client.getHttpConnectionManager().getParams().setConnectionTimeout(50000);// 设置连接时间
				int status = client.executeMethod(postMethod);
				if (status == HttpStatus.SC_OK) {
					returnStr = postMethod.getResponseBodyAsString();
				} else {
					returnStr = "fail";
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 释放连接
				postMethod.releaseConnection();
			}
			return returnStr;
		}

		public void uploadPicRecord(ResourceDto resourceDto) {
			IResourceDao resourceDao = SpringContextHolder.getBean(IResourceDao.class);
			if (resourceDto == null) {
				return;
			}
			String saveUrl = PropertiesUtils.getValues("tomcatWapps");
			///String localPicUrl = PropertiesUtils.getValues("respicfwurl");
			try {
				StringBuffer writeXml = new StringBuffer();
				writeXml.append(XMLConverter.getXmlFiedHeader());
				writeXml.append(XMLConverter.bean2XMLUTIL8(resourceDto, "vehispara", null));
				writeXml.append(XMLConverter.getXmlFiedFoot());
				String picUrl = resourceDto.getRepicUrl();
				String filePath = saveUrl+ picUrl;
				File file = new File(filePath);
				if (!file.exists()) {
					return;
				}
				String returnStr = fileUpload(
						PropertiesUtils.getValues("cgsjaddresss") + "ckts_pcwss/picaction/respicupload",
						URLDeEUtils.encodeUTF8(writeXml.toString()), file);
				if (!"fail".equals(returnStr)) {
					Map<String, String> headMap = XMLConverter.XmlParsel(returnStr, "head");
					if ("1".equals(headMap.get("code"))) {
						resourceDao.updateResSc(resourceDto.getSrln(), resourceDto.getRckCount() + "",
								resourceDto.getResId());
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		public uploadResThread(ResourceDto resourceDto) {
			super();
			this.resourceDto = resourceDto;
		}
	}
}
