package javax.ejb;


/**
* javax/ejb/CreateEx.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从HelloWorld.idl
* 2013年10月30日 星期三 上午10时57分49秒 CST
*/

public final class CreateEx extends org.omg.CORBA.UserException
{
  public javax.ejb.CreateException value = null;

  public CreateEx ()
  {
    super(CreateExHelper.id());
  } // ctor

  public CreateEx (javax.ejb.CreateException _value)
  {
    super(CreateExHelper.id());
    value = _value;
  } // ctor


  public CreateEx (String $reason, javax.ejb.CreateException _value)
  {
    super(CreateExHelper.id() + "  " + $reason);
    value = _value;
  } // ctor

} // class CreateEx
