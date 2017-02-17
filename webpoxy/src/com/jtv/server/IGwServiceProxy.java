package com.jtv.server;

public class IGwServiceProxy implements com.jtv.server.IGwService {
  private String _endpoint = null;
  private com.jtv.server.IGwService iGwService = null;
  
  public IGwServiceProxy() {
    _initIGwServiceProxy();
  }
  
  public IGwServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initIGwServiceProxy();
  }
  
  private void _initIGwServiceProxy() {
    try {
      iGwService = (new com.jtv.server.IGwServiceServiceLocator()).getIGwServicePort();
      if (iGwService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iGwService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iGwService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iGwService != null)
      ((javax.xml.rpc.Stub)iGwService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.jtv.server.IGwService getIGwService() {
    if (iGwService == null)
      _initIGwServiceProxy();
    return iGwService;
  }
  
  public java.lang.String uploadFile(byte[] arg0, java.lang.String arg1) throws java.rmi.RemoteException{
    if (iGwService == null)
      _initIGwServiceProxy();
    return iGwService.uploadFile(arg0, arg1);
  }
  
  public byte[] downloadFile(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException{
    if (iGwService == null)
      _initIGwServiceProxy();
    return iGwService.downloadFile(arg0, arg1);
  }
  
  public java.lang.String publicInfo(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException{
    if (iGwService == null)
      _initIGwServiceProxy();
    return iGwService.publicInfo(arg0, arg1);
  }
  
  
}