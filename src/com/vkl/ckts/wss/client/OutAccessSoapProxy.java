package com.vkl.ckts.wss.client;

public class OutAccessSoapProxy implements com.vkl.ckts.wss.client.OutAccessSoap {
  private String _endpoint = null;
  private com.vkl.ckts.wss.client.OutAccessSoap outAccessSoap = null;
  
  public OutAccessSoapProxy() {
    _initOutAccessSoapProxy();
  }
  
  public OutAccessSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initOutAccessSoapProxy();
  }
  
  private void _initOutAccessSoapProxy() {
    try {
      outAccessSoap = (new com.vkl.ckts.wss.client.OutAccessLocator()).getOutAccessSoap();
      if (outAccessSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)outAccessSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)outAccessSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (outAccessSoap != null)
      ((javax.xml.rpc.Stub)outAccessSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.vkl.ckts.wss.client.OutAccessSoap getOutAccessSoap() {
    if (outAccessSoap == null)
      _initOutAccessSoapProxy();
    return outAccessSoap;
  }
  
  public java.lang.String writeBCData(java.lang.String jylsh, java.lang.String data, int dataType, java.lang.String para, java.lang.String lineID, java.lang.String pdaMac, java.lang.String jysj) throws java.rmi.RemoteException{
    if (outAccessSoap == null)
      _initOutAccessSoapProxy();
    return outAccessSoap.writeBCData(jylsh, data, dataType, para, lineID, pdaMac, jysj);
  }
  
  public java.lang.String writePdaData(java.lang.String jylsh, java.lang.String data, int dataType, java.lang.String para, java.lang.String lineID, java.lang.String pdaMac) throws java.rmi.RemoteException{
    if (outAccessSoap == null)
      _initOutAccessSoapProxy();
    return outAccessSoap.writePdaData(jylsh, data, dataType, para, lineID, pdaMac);
  }
  
  public java.lang.String takePhotoTest(java.lang.String ip, java.lang.String dk, java.lang.String yhm, java.lang.String mm, java.lang.String td, org.apache.axis.types.UnsignedShort zl, org.apache.axis.types.UnsignedShort fbl) throws java.rmi.RemoteException{
    if (outAccessSoap == null)
      _initOutAccessSoapProxy();
    return outAccessSoap.takePhotoTest(ip, dk, yhm, mm, td, zl, fbl);
  }
  
  public java.lang.String takePhoto(java.lang.String jcxh, java.lang.String zpzl, java.lang.String writeXmlDoc) throws java.rmi.RemoteException{
    if (outAccessSoap == null)
      _initOutAccessSoapProxy();
    return outAccessSoap.takePhoto(jcxh, zpzl, writeXmlDoc);
  }
  
  public java.lang.String URLEnCode(java.lang.String str) throws java.rmi.RemoteException{
    if (outAccessSoap == null)
      _initOutAccessSoapProxy();
    return outAccessSoap.URLEnCode(str);
  }
  
  public java.lang.String URLDeCode(java.lang.String str) throws java.rmi.RemoteException{
    if (outAccessSoap == null)
      _initOutAccessSoapProxy();
    return outAccessSoap.URLDeCode(str);
  }
  
  public java.lang.String queryObjectOut(java.lang.String jkid, java.lang.String queryXmlDoc) throws java.rmi.RemoteException{
    if (outAccessSoap == null)
      _initOutAccessSoapProxy();
    return outAccessSoap.queryObjectOut(jkid, queryXmlDoc);
  }
  
  public java.lang.String writeObjectOut(java.lang.String jkid, java.lang.String writeXmlDoc) throws java.rmi.RemoteException{
    if (outAccessSoap == null)
      _initOutAccessSoapProxy();
    return outAccessSoap.writeObjectOut(jkid, writeXmlDoc);
  }
  
  public java.lang.String PDALogin(java.lang.String username, java.lang.String password, java.lang.String pdamac) throws java.rmi.RemoteException{
    if (outAccessSoap == null)
      _initOutAccessSoapProxy();
    return outAccessSoap.PDALogin(username, password, pdamac);
  }
  
  public java.lang.String getData(java.lang.String jylsh, java.lang.String testMethod, java.lang.String pdaMac) throws java.rmi.RemoteException{
    if (outAccessSoap == null)
      _initOutAccessSoapProxy();
    return outAccessSoap.getData(jylsh, testMethod, pdaMac);
  }
  
  public java.lang.String beginWGTest(java.lang.String jylsh, java.lang.String testMethod, java.lang.String pdaMac) throws java.rmi.RemoteException{
    if (outAccessSoap == null)
      _initOutAccessSoapProxy();
    return outAccessSoap.beginWGTest(jylsh, testMethod, pdaMac);
  }
  
  
}