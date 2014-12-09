package javax.ejb;

/**
* javax/ejb/CreateExHolder.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从HelloWorld.idl
* 2013年10月30日 星期三 上午10时57分49秒 CST
*/

public final class CreateExHolder implements org.omg.CORBA.portable.Streamable
{
  public javax.ejb.CreateEx value = null;

  public CreateExHolder ()
  {
  }

  public CreateExHolder (javax.ejb.CreateEx initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = javax.ejb.CreateExHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    javax.ejb.CreateExHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return javax.ejb.CreateExHelper.type ();
  }

}
