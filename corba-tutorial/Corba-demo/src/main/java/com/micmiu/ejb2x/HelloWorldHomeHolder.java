package com.micmiu.ejb2x;

/**
* com/micmiu/ejb2x/HelloWorldHomeHolder.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从HelloWorld.idl
* 2013年10月30日 星期三 上午10时57分49秒 CST
*/

public final class HelloWorldHomeHolder implements org.omg.CORBA.portable.Streamable
{
  public com.micmiu.ejb2x.HelloWorldHome value = null;

  public HelloWorldHomeHolder ()
  {
  }

  public HelloWorldHomeHolder (com.micmiu.ejb2x.HelloWorldHome initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = com.micmiu.ejb2x.HelloWorldHomeHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    com.micmiu.ejb2x.HelloWorldHomeHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return com.micmiu.ejb2x.HelloWorldHomeHelper.type ();
  }

}
