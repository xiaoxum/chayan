package com.vkl.ckts.wss.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class XMLConverter {

	
	
	public static String getXmlFiedHeader(){
		return "<?xml version=\"1.0\" encoding=\"GBK\"?><root>";
	}
	
	public static String getXmlFiedFoot(){
		return "</root>";
	}
	/**
	 * 将实体类解析成xml 
	 * @param obj         实体 类
	 * @param itemName    根节 点
	 * @param itemId      跟属性
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static String bean2XMLUTIL8(Object obj,String itemName,String itemId) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		StringBuffer buffer=new StringBuffer();
		Map<String, String> map=BeanUtils.describe(obj);
		Iterator<String> itegers=map.keySet().iterator();
		buffer.append("<");
		buffer.append(itemName);
		
		if(StringUtils.isBlank(itemId)){
			buffer.append(">");
		}else{
			buffer.append(" id=\"");
			buffer.append(itemId);
			buffer.append("\">");
		}
		while(itegers.hasNext()){
			Object key=itegers.next();
			if("class".equals(key)){continue;}
			Object value=map.get(key);
			buffer.append("\n <");
			buffer.append(key);
			buffer.append(">");
			if(value==null || "".equals(value.toString())){
				buffer.append("");
			}else{
				buffer.append(value);
			}
			buffer.append("</");
			buffer.append(key);
			buffer.append(">");
		}
		buffer.append("\n </");
		buffer.append(itemName);
		buffer.append(">");
		return buffer.toString();
	}
	
	
	/**
	 * 将map解析成xml 
	 * @param map         map类
	 * @param itemName    根节 点
	 * @param itemId      跟属性
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static String Map2XMLUTIL8(Map<String, String> map,String itemName,String itemId) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		StringBuffer buffer=new StringBuffer();
		Iterator<String> itegers=map.keySet().iterator();
		buffer.append("<");
		buffer.append(itemName);
		
		if(StringUtils.isBlank(itemId)){
			buffer.append(">");
		}else{
			buffer.append(" id=\"");
			buffer.append(itemId);
			buffer.append("\">");
		}
		while(itegers.hasNext()){
			Object key=itegers.next();
			if("class".equals(key)){continue;}
			Object value=map.get(key);
			buffer.append("\n <");
			buffer.append(key);
			buffer.append(">");
			if(value==null || "".equals(value.toString())){
				buffer.append("");
			}else{
				buffer.append(value);
			}
			buffer.append("</");
			buffer.append(key);
			buffer.append(">");
		}
		buffer.append("\n </");
		buffer.append(itemName);
		buffer.append(">");
		return buffer.toString();
	}
	
	


	/**
	 * 获取指定节点转化成Map
	 * 
	 * @return
	 * 
	 * @throws Exception 
	 */
	public  static Map<String,String> XmlParsel(String xmlDoc,String elmentName) throws Exception{
	  byte[] bytes=xmlDoc.getBytes();
	  InputStream in=new ByteArrayInputStream(bytes);
	  Reader read=new InputStreamReader(in,"UTF-8");
	  // 获得document
	  Document document = new SAXReader().read(in);
	  //获取根节点
	  Element root=document.getRootElement();
	  Element ele=root.element(elmentName);
	  Map<String,String> map=new HashMap<>();
	  @SuppressWarnings("unchecked")
	  Iterator<Element> iterate=ele.elementIterator();
	  while (iterate.hasNext()) {
		Element element=iterate.next();
		String name=element.getName();
		String text=element.getText();
		map.put(name, text);
	  }
	  return map;
	}
	/**
	 * 获取根节点
	 * @param xmlDoc
	 * @return
	 * @throws DocumentException
	 * @throws UnsupportedEncodingException 
	 */
	public static Element getRootElement(String xmlDoc) throws Exception{
		if(StringUtils.isBlank(xmlDoc)){
			return null;
		}
		 byte[] bytes=xmlDoc.getBytes();
		 InputStream in=new ByteArrayInputStream(bytes);
		 Reader read=new InputStreamReader(in);
		 // 获得document
		 Document document = new SAXReader().read(read);
		return document.getRootElement();
	}
	
	
	/**
	 * 获取element 节点的指定子节点下节点集，并转化成Map
	 * 
	 * @param    element       指定节点
	 * @param    elmentName    指定子节点名
	 * 
	 * @return    map          结果存储
	 * 
	 * @throws Exception 
	 */
	public static  Map<String,String> XmlParsel(Element element,String elementName) throws Exception{
	  //获取根节点
	  Map<String,String> map=new HashMap<>();
	  Element ele1=element.element(elementName);
	  @SuppressWarnings("unchecked")
	  Iterator<Element> iterate=ele1.elementIterator();
	  while (iterate.hasNext()) {
		Element ele=iterate.next();
		String name=ele.getName();
		String text=ele.getText();
		map.put(name, text);
	  }
	
	  return map;
	}
	/**
	 * 获取指定子节点
	 * 
	 * @param element             指定节点
	 * @param childEementName     节点名
	 * 
	 * @return  element
	 */
	public static Element getElement(Element element ,String childEementName){
		return element.element(childEementName);
	}
	
	public static void main(String[] args) throws Exception {
		String de="<?xml version=\"1.0\" encoding=\"GBK\"?><root><head><code>1</code><message>数据下载成功！</message><rownum>1</rownum> </head><body><vehispara><jczbh>001</jczbh><jczmc>南昌安吉车检站</jczmc><sflw>1</sflw><rdsbh>ABCDEFG</rdsbh><rdyxqs>2016-7-26</rdyxqs><rdyxqz>2018-7-26</rdyxqz><shejirjcnl>200</shejirjcnl><shijirjcnl>150</shijirjcnl><jcryzs>10</jcryzs><wjgwrs>8</wjgwrs><lrgwrs>4</lrgwrs><ycyrs>6</ycyrs><dpgwrs>2</dpgwrs><zjgwrs>10</zjgwrs><qtgwrs>8</qtgwrs><tgszjbmkhrs>30</tgszjbmkhrs><wtgszjbmkhrs>4</wtgszjbmkhrs><fzjg>南昌市车管所</fzjg><glbm>下罗车管所</glbm><gxrq>2017-1-1</gxrq><bz>这是哪高三范德萨</bz><shejirjcmtsl>180</shejirjcmtsl><shijirjcmtsl>150</shijirjcmtsl><shbj>1</shbj><syglbm>下罗车管所</syglbm><shyj>通过</shyj><zt>1</zt><ztyy></ztyy><dwdz>江西南昌市红谷滩新区</dwdz><yzbm>330032</yzbm><xkjyfw>00,01,02,03</xkjyfw><rdsffdw>南昌市车管所</rdsffdw><frdb>老徐</frdb><frdbsfzh>360425199001300016</frdbsfzh><frdblxdh>1579259623</frdblxdh><fzr>揭尚文</fzr><fzrsfzh>36042519900130016</fzrsfzh><fzrlxdh>13578945124</fzrlxdh><rclxr>胡伟峰</rclxr><rclxrsfzh>36042519900300016</rclxrsfzh><rclxrlxdh>13978895623</rclxrlxdh></vehispara></body></root>";
	
		Element root=XMLConverter.getRootElement(de);
		XMLConverter.XmlParsel(root,"head");
	
	}
	
	
	/**
	 * 将xml 解析成list
	 * 
	 * @param bodyElement    body节点
	 * @param clas           接收clas
	 * @param elementName    节点名
	 * @return               list集合
	 * @throws Exception
	 */
	public static List<?>  xmlParseltoList(Element bodyElement,Class<?> clas,String elementName) throws Exception{
		List<Object> lists=new ArrayList<Object>();
		@SuppressWarnings("unchecked")
		
		List<Element> eleLists=bodyElement.elements(elementName);
		for (Element ele : eleLists) {
			lists.add(xmlParseltoObject(ele,clas));
		}
		return lists;
	}
	/**
	 * 将xml节点解析成object类型
	 * 
	 * @param ele     节点
	 * @param clas    clas属性
	 * 
	 * @return        
	 * @throws Exception
	 */
	public static Object xmlParseltoObject(Element ele,Class<?> clas) throws Exception{
		Field[] fields=clas.getDeclaredFields();
		@SuppressWarnings("unchecked")
		List<Element> childLists=ele.elements();
		Object obj=clas.newInstance();
		for (Element element : childLists) {
			String name=element.getName();
			String value=element.getText();
			for (Field field : fields) {
				field.setAccessible(true);
				String fieldName=field.getName();
				if(name.equals(fieldName)){
					String setMethodName="set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					Method method=clas.getMethod(setMethodName, field.getType());
					method.setAccessible(true);
					method.invoke(obj, value);
				}
			}
		}
		return obj;
	}
	
}
