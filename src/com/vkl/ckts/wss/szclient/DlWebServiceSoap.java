/**
 * DlWebServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.vkl.ckts.wss.szclient;

public interface DlWebServiceSoap extends java.rmi.Remote {

    /**
     * 从公安网查询数据
     */
    public java.lang.String queryObjectOut(java.lang.String jkid, java.lang.String queryXmlDoc) throws java.rmi.RemoteException;

    /**
     * 数据写入公安网
     */
    public java.lang.String writeObjectOut(java.lang.String jkid, java.lang.String writeXmlDoc) throws java.rmi.RemoteException;
}
