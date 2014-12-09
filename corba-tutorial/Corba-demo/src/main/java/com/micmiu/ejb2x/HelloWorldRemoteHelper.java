package com.micmiu.ejb2x;


/**
* com/micmiu/ejb2x/HelloWorldRemoteHelper.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从HelloWorld.idl
* 2013年10月30日 星期三 上午10时57分49秒 CST
*/

abstract public class HelloWorldRemoteHelper
{
  private static String  _id = "RMI:com.micmiu.ejb2x.HelloWorldRemote:0000000000000000";

  public static void insert (org.omg.CORBA.Any a, com.micmiu.ejb2x.HelloWorldRemote that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static com.micmiu.ejb2x.HelloWorldRemote extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (com.micmiu.ejb2x.HelloWorldRemoteHelper.id (), "HelloWorldRemote");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static com.micmiu.ejb2x.HelloWorldRemote read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_HelloWorldRemoteStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, com.micmiu.ejb2x.HelloWorldRemote value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static com.micmiu.ejb2x.HelloWorldRemote narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof com.micmiu.ejb2x.HelloWorldRemote)
      return (com.micmiu.ejb2x.HelloWorldRemote)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      com.micmiu.ejb2x._HelloWorldRemoteStub stub = new com.micmiu.ejb2x._HelloWorldRemoteStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static com.micmiu.ejb2x.HelloWorldRemote unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof com.micmiu.ejb2x.HelloWorldRemote)
      return (com.micmiu.ejb2x.HelloWorldRemote)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      com.micmiu.ejb2x._HelloWorldRemoteStub stub = new com.micmiu.ejb2x._HelloWorldRemoteStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
