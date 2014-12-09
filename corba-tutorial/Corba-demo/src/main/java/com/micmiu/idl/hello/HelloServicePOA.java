package com.micmiu.idl.hello;


/**
* com/micmiu/idl/hello/HelloServicePOA.java .
* ?IDL-to-Java ??? (???), ?? "3.2"??
* ?/Users/micmiu/workspace/hx/Corba-demo/src/Hello.idl
* 2013?10?24? ??? ??12?50?47? CST
*/

public abstract class HelloServicePOA extends org.omg.PortableServer.Servant
 implements com.micmiu.idl.hello.HelloServiceOperations, org.omg.CORBA.portable.InvokeHandler
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
       case 0:  // com/micmiu/idl/hello/HelloService/sayHello
       {
         String msg = in.read_string ();
         String $result = null;
         $result = this.sayHello (msg);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:com/micmiu/idl/hello/HelloService:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public HelloService _this() 
  {
    return HelloServiceHelper.narrow(
    super._this_object());
  }

  public HelloService _this(org.omg.CORBA.ORB orb) 
  {
    return HelloServiceHelper.narrow(
    super._this_object(orb));
  }


} // class HelloServicePOA
