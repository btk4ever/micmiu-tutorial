package com.micmiu.ejb2x;


/**
* com/micmiu/ejb2x/_HelloWorldHomeStub.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从HelloWorld.idl
* 2013年10月30日 星期三 上午10时57分49秒 CST
*/

public class _HelloWorldHomeStub extends org.omg.CORBA.portable.ObjectImpl implements com.micmiu.ejb2x.HelloWorldHome
{

  public com.micmiu.ejb2x.HelloWorldRemote create () throws javax.ejb.CreateEx
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("create", true);
                $in = _invoke ($out);
                com.micmiu.ejb2x.HelloWorldRemote $result = com.micmiu.ejb2x.HelloWorldRemoteHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:javax/ejb/CreateEx:1.0"))
                    throw javax.ejb.CreateExHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return create (        );
            } finally {
                _releaseReply ($in);
            }
  } // create

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "RMI:com.micmiu.ejb2x.HelloWorldHome:0000000000000000"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _HelloWorldHomeStub
