package com.micmiu.corba.javaidl.hello;


/**
* com/micmiu/corba/javaidl/hello/HelloServicePOATie.java .
* ?IDL-to-Java ??? (???), ?? "3.2"??
* ?/Users/micmiu/workspace/hx/Corba-demo/src/Hello4JavaIDL.idl
* 2013?10?25? ??? ??10?19?06? CST
*/

public class HelloServicePOATie extends HelloServicePOA
{

  // Constructors

  public HelloServicePOATie ( com.micmiu.corba.javaidl.hello.HelloServiceOperations delegate ) {
      this._impl = delegate;
  }
  public HelloServicePOATie ( com.micmiu.corba.javaidl.hello.HelloServiceOperations delegate , org.omg.PortableServer.POA poa ) {
      this._impl = delegate;
      this._poa      = poa;
  }
  public com.micmiu.corba.javaidl.hello.HelloServiceOperations _delegate() {
      return this._impl;
  }
  public void _delegate (com.micmiu.corba.javaidl.hello.HelloServiceOperations delegate ) {
      this._impl = delegate;
  }
  public org.omg.PortableServer.POA _default_POA() {
      if(_poa != null) {
          return _poa;
      }
      else {
          return super._default_POA();
      }
  }
  public String sayHello (String name)
  {
    return _impl.sayHello(name);
  } // sayHello

  private com.micmiu.corba.javaidl.hello.HelloServiceOperations _impl;
  private org.omg.PortableServer.POA _poa;

} // class HelloServicePOATie
