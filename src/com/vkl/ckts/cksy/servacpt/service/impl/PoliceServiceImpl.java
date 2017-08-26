package com.vkl.ckts.cksy.servacpt.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vkl.ckts.common.base.Message;
import com.vkl.ckts.cksy.servacpt.service.IPoliceService;



/**
 * @see 模拟六合一接口实现类
 * @author jiajun
 * @version 1.0
 */
@Service
@Transactional


public class PoliceServiceImpl implements IPoliceService {

	@Override

	public Message<Object> policeCheck(String clsbdh,String hphm) {
		
		Message<Object> Messages  =new Message<Object>();
		
		
		if("fjakfhe123".equals(clsbdh)||"赣A88888".equals(hphm)){
		String realpath = this.getClass().getClassLoader().getResource("policeMessages.xml").getPath();
		
		File inputXml = new File(realpath);
		SAXReader saxReader = new SAXReader(); // 使用SAXReader读取XML
		Map<String, String> result = new HashMap<String, String>();
		try {
			Document document = saxReader.read(inputXml); // 读取XML文件,获得document对象
			Element elements = document.getRootElement(); // 获取文档的根节点
			for (Iterator<?> i = elements.elementIterator(); i.hasNext();) { // 对节点下的所有子节点进行遍历.
				Element employee = (Element) i.next();
				for(Iterator<?> j =employee.elementIterator();j.hasNext();){ // 对子节点下的所有子节点进行遍历.
					Element datas = (Element) j.next();
					// 以键值对形式存入HashMap
					result.put(datas.getName(), datas.getText());
				}
				 
			}
			
			Messages.setErrorCode("0000");
			Messages.setErrorMsg("操作成功！");	
			Messages.setData(result);
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		}else if("abcd456".equals(clsbdh)){
			Messages.setErrorCode("0001");
			Messages.setErrorMsg("盗抢嫌疑车辆！");
			
		}else{
			Messages.setErrorCode("0003");
			Messages.setErrorMsg("系统繁忙！");
		
			
		}
		
		
		
		return Messages;
	}
	
}
