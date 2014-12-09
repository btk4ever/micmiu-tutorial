package com.micmiu.idl.hello;


/**
* com/micmiu/idl/hello/HelloServiceHelper.java .
* ?IDL-to-Java ??? (???), ?? "3.2"??
* ?/Users/micmiu/workspace/hx/Corba-demo/src/Hello.idl
* 2013?10?24? ??? ??12?50?47? CST
*/

abstract public class HelloServiceHelper
{
  private static String  _id = "IDL:com/micmiu/idl/hello/HelloService:1.0";

  public static void insert (org.omg.CORBA.Any a, com.micmiu.idl.hello.HelloService that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static com.micmiu.idl.hello.HelloService extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (com.micmiu.idl.hello.HelloServiceHelper.id (), "HelloService");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static com.micmiu.idl.hello.HelloService read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_HelloServiceStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, com.micmiu.idl.hello.HelloService value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static com.micmiu.idl.hello.HelloService narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof com.micmiu.idl.hello.HelloService)
      return (com.micmiu.idl.hello.HelloService)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      com.micmiu.idl.hello._HelloServiceStub stub = new com.micmiu.idl.hello._HelloServiceStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static com.micmiu.idl.hello.HelloService unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof com.micmiu.idl.hello.HelloService)
      return (com.micmiu.idl.hello.HelloService)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      com.micmiu.idl.hello._HelloServiceStub stub = new com.micmiu.idl.hello._HelloServiceStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
