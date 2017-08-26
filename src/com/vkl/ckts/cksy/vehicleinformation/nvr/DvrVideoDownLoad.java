/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JDialogPlayBackTime.java
 *
 * Created on 2009-11-24, 19:11:16
 */

/**
 *
 * @author Administrator
 */

package com.vkl.ckts.cksy.vehicleinformation.nvr;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Timer;


import com.sun.jna.NativeLong;
import com.sun.jna.ptr.IntByReference;


/*****************************************************************************
 * 类 ：JDialogPlayBackByTime 类描述 ：远程按时间回放操作
 ****************************************************************************/
public class DvrVideoDownLoad {

	static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
	static Map<String,String> params;
	public static NativeLong m_lLoadHandle;
	public  NativeLong m_lLoadHandle2;
	public static Timer Downloadtimer;
	public  Timer Downloadtimer2;
	private static NativeLong m_lUserID;
     private static String  srln;
     private static String filePath;
     
     
	public static NativeLong dvrRegister(String m_sDeviceIP, String userName, String password, int iPort) {// GEN-FIRST:event_jButtonLoginActionPerformed
		HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
		hCNetSDK.NET_DVR_SetLogToFile(true, "C:\\SdkLogBendi.log", true);
		hCNetSDK.NET_DVR_Init();
		NativeLong lUserID = hCNetSDK.NET_DVR_Login_V30(m_sDeviceIP, (short) iPort, userName, password,
				m_strDeviceInfo);

		long userID = lUserID.longValue();
		System.out.println("登陆异常：" + hCNetSDK.NET_DVR_GetLastError());

		if (userID == -1) {
			m_sDeviceIP = "";// 登录未成功,IP置为空
			System.out.println("注册失败");
		}
		m_lUserID = lUserID;
		return lUserID;
	}
	
	public  NativeLong dvrRegister2(String m_sDeviceIP, String userName, String password, int iPort) {// GEN-FIRST:event_jButtonLoginActionPerformed
		HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
		hCNetSDK.NET_DVR_SetLogToFile(true, "C:\\SdkLogBendi.log", true);
		hCNetSDK.NET_DVR_Init();
		NativeLong lUserID = hCNetSDK.NET_DVR_Login_V30(m_sDeviceIP, (short) iPort, userName, password,
				m_strDeviceInfo);

		long userID = lUserID.longValue();
		System.out.println("登陆异常：" + hCNetSDK.NET_DVR_GetLastError());

		if (userID == -1) {
			m_sDeviceIP = "";// 登录未成功,IP置为空
			System.out.println("注册失败");
		}
		m_lUserID = lUserID;
		return lUserID;
	}
	
	public static void videoDowload(long m_iChanShowNum, Date startTime, Date endTime, String filePath,String srln) {
		//DvrVideoDownLoad.params=params;
		DvrVideoDownLoad.filePath=filePath;
		DvrVideoDownLoad.srln=srln;
		HCNetSDK.NET_DVR_TIME struStartTime;
		HCNetSDK.NET_DVR_TIME struStopTime;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startTime);
		// 设置开始时间
		struStartTime = new HCNetSDK.NET_DVR_TIME();
        System.out.println(calendar.get(Calendar.MONTH));
		struStartTime.dwYear = calendar.get(Calendar.YEAR);// 开始时间
		struStartTime.dwMonth = calendar.get(Calendar.MONTH)+1;
		struStartTime.dwDay = calendar.get(Calendar.DAY_OF_MONTH);
		struStartTime.dwHour = calendar.get(Calendar.HOUR_OF_DAY);
		struStartTime.dwMinute = calendar.get(Calendar.MINUTE);
		struStartTime.dwSecond = calendar.get(Calendar.SECOND);
		// 设置结束时间
		calendar.setTime(endTime);
		struStopTime = new HCNetSDK.NET_DVR_TIME();
		struStopTime.dwYear = calendar.get(Calendar.YEAR);// 开始时间
		struStopTime.dwMonth = calendar.get(Calendar.MARCH)+1;
		struStopTime.dwDay = calendar.get(Calendar.DAY_OF_MONTH);
		struStopTime.dwHour = calendar.get(Calendar.HOUR_OF_DAY);
		struStopTime.dwMinute = calendar.get(Calendar.MINUTE);
		struStopTime.dwSecond = calendar.get(Calendar.SECOND);
		// 设置超时
		hCNetSDK.NET_DVR_SetRecvTimeOut(new NativeLong(3000));
		m_lLoadHandle = hCNetSDK.NET_DVR_GetFileByTime(m_lUserID, new NativeLong(m_iChanShowNum), struStartTime,
				struStopTime, filePath);
		if (m_lLoadHandle.intValue() >= 0) {
			hCNetSDK.NET_DVR_PlayBackControl(m_lLoadHandle, HCNetSDK.NET_DVR_PLAYSTART, 0, null);
			Downloadtimer = new Timer();// 新建定时器
			Downloadtimer.schedule(new DownloadTask(), 0, 5000);// 0秒后开始响应函数

		} else {
			System.out.println("错误异常 " + hCNetSDK.NET_DVR_GetLastError());
			return;
		}
	}
	static class DownloadTask extends java.util.TimerTask {
		// 定时器函数
		@Override
		public void run() {
			IntByReference nPos = new IntByReference(0);
			hCNetSDK.NET_DVR_PlayBackControl(m_lLoadHandle, HCNetSDK.NET_DVR_PLAYGETPOS, 0, nPos);
			if (nPos.getValue() > 100) {
				hCNetSDK.NET_DVR_StopGetFile(m_lLoadHandle);
				m_lLoadHandle.setValue(-1);
				Downloadtimer.cancel();
				System.out.println("由于网络原因或DVR忙,下载异常终止!");
			}
			if (nPos.getValue() == 100) {
				hCNetSDK.NET_DVR_StopGetFile(m_lLoadHandle);
				m_lLoadHandle.setValue(-1);
				Downloadtimer.cancel();
				try {
					//文件上传
				///	upload(filePath,srln);
					//删除文件
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("按时间下载结束!");
			}
			//FileUtils.deleteFile(filePath);
		}
	}
	

	
    /**
     * 视频上传
     * 
     * @param filePath	   目标文件目录
     * @param viewId     视频编号
     * 
     * @throws Exception 异常信息
     */
	public static void upload(String filePath,String videoId) throws Exception {
		File file = new File(filePath);

		
	}


	
}
