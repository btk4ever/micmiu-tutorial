package javax.ejb;


/**
* javax/ejb/CreateExceptionDefaultFactory.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从HelloWorld.idl
* 2013年10月30日 星期三 上午10时57分49秒 CST
*/

public class CreateExceptionDefaultFactory implements org.omg.CORBA.portable.ValueFactory {

  public java.io.Serializable read_value (org.omg.CORBA_2_3.portable.InputStream is)
  {
    return is.read_value(new CreateException(){});
  }
}
