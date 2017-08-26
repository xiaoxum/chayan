/**
 * DlWebServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.vkl.ckts.wss.szclient;

public class DlWebServiceLocator extends org.apache.axis.client.Service implements com.vkl.ckts.wss.szclient.DlWebService {

    public DlWebServiceLocator() {
    }


    public DlWebServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DlWebServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for DlWebServiceSoap
    private java.lang.String DlWebServiceSoap_address = "http://192.168.100.30:88/dlwebservice.asmx";

    public java.lang.String getDlWebServiceSoapAddress() {
        return DlWebServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String DlWebServiceSoapWSDDServiceName = "DlWebServiceSoap";

    public java.lang.String getDlWebServiceSoapWSDDServiceName() {
        return DlWebServiceSoapWSDDServiceName;
    }

    public void setDlWebServiceSoapWSDDServiceName(java.lang.String name) {
        DlWebServiceSoapWSDDServiceName = name;
    }

    public com.vkl.ckts.wss.szclient.DlWebServiceSoap getDlWebServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(DlWebServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getDlWebServiceSoap(endpoint);
    }

    public com.vkl.ckts.wss.szclient.DlWebServiceSoap getDlWebServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.vkl.ckts.wss.szclient.DlWebServiceSoapStub _stub = new com.vkl.ckts.wss.szclient.DlWebServiceSoapStub(portAddress, this);
            _stub.setPortName(getDlWebServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setDlWebServiceSoapEndpointAddress(java.lang.String address) {
        DlWebServiceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.vkl.ckts.wss.szclient.DlWebServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.vkl.ckts.wss.szclient.DlWebServiceSoapStub _stub = new com.vkl.ckts.wss.szclient.DlWebServiceSoapStub(new java.net.URL(DlWebServiceSoap_address), this);
                _stub.setPortName(getDlWebServiceSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("DlWebServiceSoap".equals(inputPortName)) {
            return getDlWebServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.ncdalei.cn/", "DlWebService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.ncdalei.cn/", "DlWebServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("DlWebServiceSoap".equals(portName)) {
            setDlWebServiceSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
