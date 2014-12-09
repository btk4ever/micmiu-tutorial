package com.micmiu.ejb2x;

/**
* com/micmiu/ejb2x/HelloWorldRemoteHolder.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从HelloWorld.idl
* 2013年10月30日 星期三 上午10时57分49秒 CST
*/

public final class HelloWorldRemoteHolder implements org.omg.CORBA.portable.Streamable
{
  public com.micmiu.ejb2x.HelloWorldRemote value = null;

  public HelloWorldRemoteHolder ()
  {
  }

  public HelloWorldRemoteHolder (com.micmiu.ejb2x.HelloWorldRemote initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = com.micmiu.ejb2x.HelloWorldRemoteHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    com.micmiu.ejb2x.HelloWorldRemoteHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return com.micmiu.ejb2x.HelloWorldRemoteHelper.type ();
  }

}
