package com.vkl.ckts.cksy.vehicleinformation.nvr;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;




public class FtpUpload {

	
	
	public static String ftpUpload(String url, String port, String username, String password, String remotePath,
			String fileNamePath, String fileName) {
		FTPClient ftpClient = new FTPClient();
		
		FileInputStream fis = null;
		String returnMessage = "0";
		try {
			ftpClient.connect(url, Integer.parseInt(port));
			boolean loginResult = ftpClient.login(username, password);
			int returnCode = ftpClient.getReplyCode();
			if (loginResult && FTPReply.isPositiveCompletion(returnCode)) {// 如果登录成功
				createDir(ftpClient,remotePath);
				//System.out.println(ftpClient.makeDirectory(remotePath));
				// 设置上传目录
				ftpClient.changeWorkingDirectory(remotePath);
				ftpClient.setBufferSize(1024);
				ftpClient.setControlEncoding("GBK");
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				ftpClient.enterLocalPassiveMode();
				fis = new FileInputStream(fileNamePath + fileName);
				ftpClient.storeFile(new String(fileName.getBytes("GBK"), "iso-8859-1") , fis);

				returnMessage = "1"; // 上传成功
			} else {// 如果登录失败
				returnMessage = "0";
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("FTP客户端出错！", e);
		} finally {
			// IOUtils.closeQuietly(fis);
			try {
				ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("关闭FTP连接发生异常！", e);
			}
		}
		return returnMessage;
	}
	
	public static void createDir(FTPClient ftpClient,String dirPath){
		if(dirPath==null){
			return;
		}
		String[] filePath = dirPath.split("/");
		String dir=filePath[1];
		  try{
			  for (int i = 2; i < filePath.length; i++) {
				  dir=dir+"/"+filePath[i];
				  ftpClient.makeDirectory(dir);
				  
			  }
		    }catch(Exception ex){
		        System.out.println(ex.getMessage());
		    }
		
		
	}
	public static void main(String[] args) {
//		XLQECQ928DAGHYP/ftp
		System.out.println(ftpUpload("192.168.100.31", "2000", "anonymous", null, "/user1/20170628/rr/sse/eee",
				"d:/", "123.mp4"));
	}
}
