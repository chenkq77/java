/**
 * IGwService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.jtv.server;

public interface IGwService extends java.rmi.Remote {
    public java.lang.String uploadFile(byte[] arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public byte[] downloadFile(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public java.lang.String publicInfo(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
}
