package com.micmiu.ejb2x;


/**
* com/micmiu/ejb2x/HelloWorldRemotePOA.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从HelloWorld.idl
* 2013年10月30日 星期三 上午10时57分49秒 CST
*/

public abstract class HelloWorldRemotePOA extends org.omg.PortableServer.Servant
 implements com.micmiu.ejb2x.HelloWorldRemoteOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("sayHello", new java.lang.Integer (0));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // com/micmiu/ejb2x/HelloWorldRemote/sayHello
       {
         String arg0 = org.omg.CORBA.WStringValueHelper.read (in);
         String $result = null;
         $result = this.sayHello (arg0);
         out = $rh.createReply();
         org.omg.CORBA.WStringValueHelper.write (out, $result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "RMI:com.micmiu.ejb2x.HelloWorldRemote:0000000000000000"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public HelloWorldRemote _this() 
  {
    return HelloWorldRemoteHelper.narrow(
    super._this_object());
  }

  public HelloWorldRemote _this(org.omg.CORBA.ORB orb) 
  {
    return HelloWorldRemoteHelper.narrow(
    super._this_object(orb));
  }


} // class HelloWorldRemotePOA
