package com.vkl.ckts.wss.szclient;

public class DlWebServiceSoapProxy implements com.vkl.ckts.wss.szclient.DlWebServiceSoap {
  private String _endpoint = null;
  private com.vkl.ckts.wss.szclient.DlWebServiceSoap dlWebServiceSoap = null;
  
  public DlWebServiceSoapProxy() {
    _initDlWebServiceSoapProxy();
  }
  
  public DlWebServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initDlWebServiceSoapProxy();
  }
  
  private void _initDlWebServiceSoapProxy() {
    try {
      dlWebServiceSoap = (new com.vkl.ckts.wss.szclient.DlWebServiceLocator()).getDlWebServiceSoap();
      if (dlWebServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)dlWebServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)dlWebServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (dlWebServiceSoap != null)
      ((javax.xml.rpc.Stub)dlWebServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.vkl.ckts.wss.szclient.DlWebServiceSoap getDlWebServiceSoap() {
    if (dlWebServiceSoap == null)
      _initDlWebServiceSoapProxy();
    return dlWebServiceSoap;
  }
  
  public java.lang.String queryObjectOut(java.lang.String jkid, java.lang.String queryXmlDoc) throws java.rmi.RemoteException{
    if (dlWebServiceSoap == null)
      _initDlWebServiceSoapProxy();
    return dlWebServiceSoap.queryObjectOut(jkid, queryXmlDoc);
  }
  
  public java.lang.String writeObjectOut(java.lang.String jkid, java.lang.String writeXmlDoc) throws java.rmi.RemoteException{
    if (dlWebServiceSoap == null)
      _initDlWebServiceSoapProxy();
    return dlWebServiceSoap.writeObjectOut(jkid, writeXmlDoc);
  }
  
  
}