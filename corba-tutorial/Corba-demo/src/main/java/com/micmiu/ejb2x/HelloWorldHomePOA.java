package com.micmiu.ejb2x;


/**
* com/micmiu/ejb2x/HelloWorldHomePOA.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从HelloWorld.idl
* 2013年10月30日 星期三 上午10时57分49秒 CST
*/

public abstract class HelloWorldHomePOA extends org.omg.PortableServer.Servant
 implements com.micmiu.ejb2x.HelloWorldHomeOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("create", new java.lang.Integer (0));
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
       case 0:  // com/micmiu/ejb2x/HelloWorldHome/create
       {
         try {
           com.micmiu.ejb2x.HelloWorldRemote $result = null;
           $result = this.create ();
           out = $rh.createReply();
           com.micmiu.ejb2x.HelloWorldRemoteHelper.write (out, $result);
         } catch (javax.ejb.CreateEx $ex) {
           out = $rh.createExceptionReply ();
           javax.ejb.CreateExHelper.write (out, $ex);
         }
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "RMI:com.micmiu.ejb2x.HelloWorldHome:0000000000000000"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public HelloWorldHome _this() 
  {
    return HelloWorldHomeHelper.narrow(
    super._this_object());
  }

  public HelloWorldHome _this(org.omg.CORBA.ORB orb) 
  {
    return HelloWorldHomeHelper.narrow(
    super._this_object(orb));
  }


} // class HelloWorldHomePOA
