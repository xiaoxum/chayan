package com.vkl.ckts.cksy.vehicleinformation.nvr;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vkl.ckts.cksy.vehicleinformation.entity.ChkptNvrConfigDto;
import com.vkl.ckts.common.utils.DateUtil;
import com.vkl.ckts.common.utils.PropertiesUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestNVR {

	
	
	/**
	 * 视频截取
	 * 
	 * @param startTime   视频开始时间
	 * @param endTime     视频结束时间
	 * @param fileName    文件名
	 * 
	 * @return            临时文件目录
	 */
	public  static String uploadDvrVedio(String startTime,String endTime,String fileName,String srln){
		//dvr服务器的ip
		String serverIp=PropertiesUtils.getValues("nvr_ip");
		int serverPorts=Integer.parseInt(PropertiesUtils.getValues("nvr_port"));
		String serverUserName=PropertiesUtils.getValues("nvr_user");
		String serverPwd=PropertiesUtils.getValues("nvr_pwd");
		String channel=PropertiesUtils.getValues("nvr_channel");
		long serverChancel=Long.parseLong(channel);
				String tempFileAddr=PropertiesUtils.getValues("temp_file");
		File file=new File(tempFileAddr);
		if(file.exists()){
			file.mkdir();
		}
		String filePath=tempFileAddr+"/"+srln+"/"+fileName;
		//dvr注册
		DvrVideoDownLoad.dvrRegister(serverIp, serverUserName, serverPwd, serverPorts);
		//DVR文件下载
		DvrVideoDownLoad.videoDowload(serverChancel, DateUtil.parseDate(startTime), DateUtil.parseDate(endTime), filePath,srln);
		return tempFileAddr+"/"+srln+"/";
	}
	
	
	
	
	/**
	 * 视频截取
	 * 
	 * @param startTime   视频开始时间
	 * @param endTime     视频结束时间
	 * @param fileName    文件名
	 * 
	 * @return            临时文件目录
	 */
	public  static String uploadDvrVedio2(ChkptNvrConfigDto chkptNvrConfigDto,String srln ,String fileName){
		//dvr服务器的ip
		String serverIp=chkptNvrConfigDto.getNvrIp();
		if (StringUtils.isBlank(chkptNvrConfigDto.getServPort())) {
			return null;
		}
		int serverPorts=Integer.parseInt(chkptNvrConfigDto.getServPort());
		String serverUserName=chkptNvrConfigDto.getNvrUser();
		String serverPwd=chkptNvrConfigDto.getNvrPwd();
		String channel=chkptNvrConfigDto.getVideoDownLoadChannel();
		long serverChancel=Long.parseLong(channel);
		String tempFileAddr=PropertiesUtils.getValues("video_file");
		File file=new File(tempFileAddr+"/"+srln);
		if(!file.exists()){
			file.mkdirs();
		}
		String filePath=tempFileAddr+"/"+srln+"/"+fileName;
		//dvr注册
		DvrVideoDownLoad.dvrRegister(serverIp, serverUserName, serverPwd, serverPorts);
		//DVR文件下载
		DvrVideoDownLoad.videoDowload(serverChancel,chkptNvrConfigDto.getStartTime(), chkptNvrConfigDto.getEndTime(), filePath,srln);
		return srln+"/";
	}
	
	
	
	@Test
	public  void main() {
		
		//nvr注册
		DvrVideoDownLoad.dvrRegister("192.168.1.201", "admin", "abc123456", 8000);
		//查找文件
		Calendar calendar=Calendar.getInstance();
		calendar.set(2017, 1, 30, 10, 50, 45);
		Date startTime=calendar.getTime();
		calendar.set(2017, 1, 30, 11, 00, 45);
		Date endTime=calendar.getTime();
		Map<String,String> params=new HashMap<String,String>();
		params.put("jiji", "想");
		DvrVideoDownLoad.videoDowload(34,startTime,endTime,"D://temp//TESTDVR2.mp4","");
		//test();
	}
	public static void main(String[] args) {
		Calendar cal=Calendar.getInstance();
		cal.setTime(new Date());
		System.out.println(cal.get(Calendar.MONTH));
	}
	

}
