package javax.ejb;


/**
* javax/ejb/CreateExHelper.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从HelloWorld.idl
* 2013年10月30日 星期三 上午10时57分49秒 CST
*/

abstract public class CreateExHelper
{
  private static String  _id = "IDL:javax/ejb/CreateEx:1.0";

  public static void insert (org.omg.CORBA.Any a, javax.ejb.CreateEx that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static javax.ejb.CreateEx extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [1];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = javax.ejb.CreateExceptionHelper.type ();
          _members0[0] = new org.omg.CORBA.StructMember (
            "value",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_exception_tc (javax.ejb.CreateExHelper.id (), "CreateEx", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static javax.ejb.CreateEx read (org.omg.CORBA.portable.InputStream istream)
  {
    javax.ejb.CreateEx value = new javax.ejb.CreateEx ();
    // read and discard the repository ID
    istream.read_string ();
    value.value = javax.ejb.CreateExceptionHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, javax.ejb.CreateEx value)
  {
    // write the repository ID
    ostream.write_string (id ());
    javax.ejb.CreateExceptionHelper.write (ostream, value.value);
  }

}
