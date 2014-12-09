package com.micmiu.idl.hello;

/**
* com/micmiu/idl/hello/HelloServiceHolder.java .
* ?IDL-to-Java ??? (???), ?? "3.2"??
* ?/Users/micmiu/workspace/hx/Corba-demo/src/Hello.idl
* 2013?10?24? ??? ??12?50?47? CST
*/

public final class HelloServiceHolder implements org.omg.CORBA.portable.Streamable
{
  public com.micmiu.idl.hello.HelloService value = null;

  public HelloServiceHolder ()
  {
  }

  public HelloServiceHolder (com.micmiu.idl.hello.HelloService initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = com.micmiu.idl.hello.HelloServiceHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    com.micmiu.idl.hello.HelloServiceHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return com.micmiu.idl.hello.HelloServiceHelper.type ();
  }

}
