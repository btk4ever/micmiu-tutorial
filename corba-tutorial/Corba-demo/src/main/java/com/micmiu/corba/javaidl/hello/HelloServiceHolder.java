package com.micmiu.corba.javaidl.hello;

/**
* com/micmiu/corba/javaidl/hello/HelloServiceHolder.java .
* ?IDL-to-Java ??? (???), ?? "3.2"??
* ?/Users/micmiu/workspace/hx/Corba-demo/src/Hello4JavaIDL.idl
* 2013?10?25? ??? ??10?19?06? CST
*/

public final class HelloServiceHolder implements org.omg.CORBA.portable.Streamable
{
  public com.micmiu.corba.javaidl.hello.HelloService value = null;

  public HelloServiceHolder ()
  {
  }

  public HelloServiceHolder (com.micmiu.corba.javaidl.hello.HelloService initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = com.micmiu.corba.javaidl.hello.HelloServiceHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    com.micmiu.corba.javaidl.hello.HelloServiceHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return com.micmiu.corba.javaidl.hello.HelloServiceHelper.type ();
  }

}
