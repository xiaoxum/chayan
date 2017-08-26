/**
 * DlWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.vkl.ckts.wss.szclient;

public interface DlWebService extends javax.xml.rpc.Service {
    public java.lang.String getDlWebServiceSoapAddress();

    public com.vkl.ckts.wss.szclient.DlWebServiceSoap getDlWebServiceSoap() throws javax.xml.rpc.ServiceException;

    public com.vkl.ckts.wss.szclient.DlWebServiceSoap getDlWebServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
