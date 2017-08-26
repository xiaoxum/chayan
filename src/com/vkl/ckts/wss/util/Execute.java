package com.vkl.ckts.wss.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.axis.AxisFault;
import org.apache.axis.client.Service;





import com.vkl.ckts.wss.client.OutAccessSoapStub;
import com.vkl.ckts.wss.szclient.DlWebServiceSoapStub;

import sun.misc.BASE64Encoder;




public class Execute {
	private OutAccessSoapStub stub = null;
	private DlWebServiceSoapStub stub1 = null;
	private String url;
	public Execute(String url) {
		super();
		this.url = url;
	}
	
	private void ensureStub() throws MalformedURLException, AxisFault{
		if(stub==null){
			String webserviceURL = url;
			URL url = new URL(webserviceURL);
			Service service = new Service();
			stub = new OutAccessSoapStub(url, service);
		}
	}
	
	private void ensureStub1() throws MalformedURLException, AxisFault{
		if(stub1==null){
			String webserviceURL = url;
			URL url = new URL(webserviceURL);
			Service service = new Service();
			stub1 = new DlWebServiceSoapStub(url, service);
		}
	}
	/**
	 * 业务查询
	 * @param xtlb
	 * @param jkxlh
	 * @param jkid
	 * @param queryXmlDoc
	 * @return
	 * @throws Exception
	 */
	public String doQuery1(String jkid, String queryXmlDoc) throws Exception{
		this.ensureStub1();
		String result=stub1.queryObjectOut(jkid, queryXmlDoc);
		try{
			result = URLDecoder.decode(result, "utf-8");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	
		return result;
	}
	public String doWrite1(String xtlb, String jkxlh, String jkid, String writeXmlDoc) throws Exception{
		this.ensureStub1();
		String result=stub1.writeObjectOut(jkid, writeXmlDoc);
		try{
			result = URLDecoder.decode(result, "utf-8");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 业务查询
	 * @param xtlb
	 * @param jkxlh
	 * @param jkid
	 * @param queryXmlDoc
	 * @return
	 * @throws Exception
	 */
	public String doQuery(String xtlb, String jkxlh, String jkid, String queryXmlDoc) throws Exception{
		this.ensureStub();
		String result=stub.queryObjectOut(jkid, queryXmlDoc);
		try{
			result = URLDecoder.decode(result, "utf-8");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		System.out.println("读取机动车基本信息:"+result);
		return result;
	}

	
	
	public String doWrite(String xtlb, String jkxlh, String jkid, String writeXmlDoc) throws Exception{
		this.ensureStub();
		
		String result=stub.writeObjectOut( jkid, writeXmlDoc);
		try{
			result = URLDecoder.decode(result, "utf-8");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		System.out.println("读取机动车基本信息:"+result);
		return result;
	}

	        
	public static void main(String[] args) { 
		Execute execute=new Execute("http://192.168.100.30:88/dlwebservice.asmx");
		try {
			execute.doQuery1("18C49", "dddd");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	
}
