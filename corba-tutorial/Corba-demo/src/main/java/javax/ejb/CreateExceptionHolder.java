package javax.ejb;

/**
* javax/ejb/CreateExceptionHolder.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从HelloWorld.idl
* 2013年10月30日 星期三 上午10时57分49秒 CST
*/

public final class CreateExceptionHolder implements org.omg.CORBA.portable.Streamable
{
  public javax.ejb.CreateException value = null;

  public CreateExceptionHolder ()
  {
  }

  public CreateExceptionHolder (javax.ejb.CreateException initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = javax.ejb.CreateExceptionHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    javax.ejb.CreateExceptionHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return javax.ejb.CreateExceptionHelper.type ();
  }

}
