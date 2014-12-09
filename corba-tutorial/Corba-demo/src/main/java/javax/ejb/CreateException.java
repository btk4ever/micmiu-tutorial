package javax.ejb;


/**
* javax/ejb/CreateException.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从HelloWorld.idl
* 2013年10月30日 星期三 上午10时57分49秒 CST
*/

public abstract class CreateException implements org.omg.CORBA.portable.StreamableValue
{
  private static String[] _truncatable_ids = {
    javax.ejb.CreateExceptionHelper.id ()
  };

  public String[] _truncatable_ids() {
    return _truncatable_ids;
  }

  public void _read (org.omg.CORBA.portable.InputStream istream)
  {
  }

  public void _write (org.omg.CORBA.portable.OutputStream ostream)
  {
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return javax.ejb.CreateExceptionHelper.type ();
  }
} // class CreateException
