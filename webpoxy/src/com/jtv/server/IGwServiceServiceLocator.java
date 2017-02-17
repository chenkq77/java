/**
 * IGwServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.jtv.server;

public class IGwServiceServiceLocator extends org.apache.axis.client.Service implements com.jtv.server.IGwServiceService {

    public IGwServiceServiceLocator() {
    }


    public IGwServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public IGwServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for IGwServicePort
    private java.lang.String IGwServicePort_address = "http://117.158.192.94:8181/gw/ws/webservice";

    public java.lang.String getIGwServicePortAddress() {
        return IGwServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String IGwServicePortWSDDServiceName = "IGwServicePort";

    public java.lang.String getIGwServicePortWSDDServiceName() {
        return IGwServicePortWSDDServiceName;
    }

    public void setIGwServicePortWSDDServiceName(java.lang.String name) {
        IGwServicePortWSDDServiceName = name;
    }

    public com.jtv.server.IGwService getIGwServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(IGwServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getIGwServicePort(endpoint);
    }

    public com.jtv.server.IGwService getIGwServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.jtv.server.IGwServiceServiceSoapBindingStub _stub = new com.jtv.server.IGwServiceServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getIGwServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setIGwServicePortEndpointAddress(java.lang.String address) {
        IGwServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.jtv.server.IGwService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.jtv.server.IGwServiceServiceSoapBindingStub _stub = new com.jtv.server.IGwServiceServiceSoapBindingStub(new java.net.URL(IGwServicePort_address), this);
                _stub.setPortName(getIGwServicePortWSDDServiceName());
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
        if ("IGwServicePort".equals(inputPortName)) {
            return getIGwServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://server.jtv.com/", "IGwServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://server.jtv.com/", "IGwServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("IGwServicePort".equals(portName)) {
            setIGwServicePortEndpointAddress(address);
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
