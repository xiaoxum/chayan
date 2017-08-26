/**
 * OutAccessSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.vkl.ckts.wss.client;

public interface OutAccessSoap extends java.rmi.Remote {

    /**
     * 项目补传信息
     */
    public java.lang.String writeBCData(java.lang.String jylsh, java.lang.String data, int dataType, java.lang.String para, java.lang.String lineID, java.lang.String pdaMac, java.lang.String jysj) throws java.rmi.RemoteException;

    /**
     * PDA上传信息
     */
    public java.lang.String writePdaData(java.lang.String jylsh, java.lang.String data, int dataType, java.lang.String para, java.lang.String lineID, java.lang.String pdaMac) throws java.rmi.RemoteException;

    /**
     * 抓拍测试 
     *     zl:0-最好，1-较好，2-一般 
     *     fbl: 0-CIF(352*288/352*240)，1-QCIF(176*144/176*120)，2-4CIF(704*576/704*480)或D1(720*576/720*486)，3-UXGA(1600*1200)，
     * 4-SVGA(800*600)，5-HD720P(1280*720)，6-VGA(640*480)，7-XVGA(1280*960)，8-HD900P(1600*900)，9-HD1080P(1920*1080)，10-2560*1920，
     * 11-1600*304，12-2048*1536，13-2448*2048，14-2448*1200，15-2448*800，16-XGA(1024*768)，17-SXGA(1280*1024)，18-WD1(960*576/960*480),
     * 19-1080I(1920*1080)，20-576*576，21-1536*1536，22-1920*1920，0xff-Auto(使用当前码流分辨率)
     */
    public java.lang.String takePhotoTest(java.lang.String ip, java.lang.String dk, java.lang.String yhm, java.lang.String mm, java.lang.String td, org.apache.axis.types.UnsignedShort zl, org.apache.axis.types.UnsignedShort fbl) throws java.rmi.RemoteException;

    /**
     * 检测线拍照
     */
    public java.lang.String takePhoto(java.lang.String jcxh, java.lang.String zpzl, java.lang.String writeXmlDoc) throws java.rmi.RemoteException;

    /**
     * URLEnCode
     */
    public java.lang.String URLEnCode(java.lang.String str) throws java.rmi.RemoteException;

    /**
     * URLDeCode
     */
    public java.lang.String URLDeCode(java.lang.String str) throws java.rmi.RemoteException;

    /**
     * 从公安网查询数据
     */
    public java.lang.String queryObjectOut(java.lang.String jkid, java.lang.String queryXmlDoc) throws java.rmi.RemoteException;

    /**
     * 数据写入公安网
     */
    public java.lang.String writeObjectOut(java.lang.String jkid, java.lang.String writeXmlDoc) throws java.rmi.RemoteException;

    /**
     * PDA用户登录
     */
    public java.lang.String PDALogin(java.lang.String username, java.lang.String password, java.lang.String pdamac) throws java.rmi.RemoteException;

    /**
     * PAD获取数据
     */
    public java.lang.String getData(java.lang.String jylsh, java.lang.String testMethod, java.lang.String pdaMac) throws java.rmi.RemoteException;

    /**
     * PDA开始检测车辆
     */
    public java.lang.String beginWGTest(java.lang.String jylsh, java.lang.String testMethod, java.lang.String pdaMac) throws java.rmi.RemoteException;
}
