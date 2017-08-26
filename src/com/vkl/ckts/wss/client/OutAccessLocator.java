/**
 * OutAccessLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.vkl.ckts.wss.client;

public class OutAccessLocator extends org.apache.axis.client.Service implements com.vkl.ckts.wss.client.OutAccess {

    public OutAccessLocator() {
    }


    public OutAccessLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public OutAccessLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for OutAccessSoap
    private java.lang.String OutAccessSoap_address = "http://192.168.11.11:88/WebService/OutAccess.asmx";

    public java.lang.String getOutAccessSoapAddress() {
        return OutAccessSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String OutAccessSoapWSDDServiceName = "OutAccessSoap";

    public java.lang.String getOutAccessSoapWSDDServiceName() {
        return OutAccessSoapWSDDServiceName;
    }

    public void setOutAccessSoapWSDDServiceName(java.lang.String name) {
        OutAccessSoapWSDDServiceName = name;
    }

    public com.vkl.ckts.wss.client.OutAccessSoap getOutAccessSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(OutAccessSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getOutAccessSoap(endpoint);
    }

    public com.vkl.ckts.wss.client.OutAccessSoap getOutAccessSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.vkl.ckts.wss.client.OutAccessSoapStub _stub = new com.vkl.ckts.wss.client.OutAccessSoapStub(portAddress, this);
            _stub.setPortName(getOutAccessSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setOutAccessSoapEndpointAddress(java.lang.String address) {
        OutAccessSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.vkl.ckts.wss.client.OutAccessSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.vkl.ckts.wss.client.OutAccessSoapStub _stub = new com.vkl.ckts.wss.client.OutAccessSoapStub(new java.net.URL(OutAccessSoap_address), this);
                _stub.setPortName(getOutAccessSoapWSDDServiceName());
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
        if ("OutAccessSoap".equals(inputPortName)) {
            return getOutAccessSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://OutAccess.org/", "OutAccess");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://OutAccess.org/", "OutAccessSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("OutAccessSoap".equals(portName)) {
            setOutAccessSoapEndpointAddress(address);
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
