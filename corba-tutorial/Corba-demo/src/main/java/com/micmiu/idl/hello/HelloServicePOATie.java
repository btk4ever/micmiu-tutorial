package com.micmiu.idl.hello;


/**
* com/micmiu/idl/hello/HelloServicePOATie.java .
* ?IDL-to-Java ??? (???), ?? "3.2"??
* ?/Users/micmiu/workspace/hx/Corba-demo/src/Hello.idl
* 2013?10?24? ??? ??12?50?47? CST
*/

public class HelloServicePOATie extends HelloServicePOA
{

  // Constructors

  public HelloServicePOATie ( com.micmiu.idl.hello.HelloServiceOperations delegate ) {
      this._impl = delegate;
  }
  public HelloServicePOATie ( com.micmiu.idl.hello.HelloServiceOperations delegate , org.omg.PortableServer.POA poa ) {
      this._impl = delegate;
      this._poa      = poa;
  }
  public com.micmiu.idl.hello.HelloServiceOperations _delegate() {
      return this._impl;
  }
  public void _delegate (com.micmiu.idl.hello.HelloServiceOperations delegate ) {
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
  public String sayHello (String msg)
  {
    return _impl.sayHello(msg);
  } // sayHello

  private com.micmiu.idl.hello.HelloServiceOperations _impl;
  private org.omg.PortableServer.POA _poa;

} // class HelloServicePOATie
