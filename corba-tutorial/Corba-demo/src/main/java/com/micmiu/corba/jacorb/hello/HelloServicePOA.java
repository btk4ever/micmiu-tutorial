package com.micmiu.corba.jacorb.hello;


/**
 * Generated from IDL interface "HelloService".
 *
 * @author JacORB IDL compiler V 3.5
 * @version generated at Dec 11, 2014 10:38:48 AM
 */

public abstract class HelloServicePOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, com.micmiu.corba.jacorb.hello.HelloServiceOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "sayHello", Integer.valueOf(0));
	}
	private String[] ids = {"IDL:com/micmiu/corba/jacorb/hello/HelloService:1.0"};
	public com.micmiu.corba.jacorb.hello.HelloService _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		com.micmiu.corba.jacorb.hello.HelloService __r = com.micmiu.corba.jacorb.hello.HelloServiceHelper.narrow(__o);
		return __r;
	}
	public com.micmiu.corba.jacorb.hello.HelloService _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		com.micmiu.corba.jacorb.hello.HelloService __r = com.micmiu.corba.jacorb.hello.HelloServiceHelper.narrow(__o);
		return __r;
	}
	public org.omg.CORBA.portable.OutputStream _invoke(String method, org.omg.CORBA.portable.InputStream _input, org.omg.CORBA.portable.ResponseHandler handler)
		throws org.omg.CORBA.SystemException
	{
		org.omg.CORBA.portable.OutputStream _out = null;
		// do something
		// quick lookup of operation
		Integer opsIndex = (Integer)m_opsHash.get ( method );
		if ( null == opsIndex )
			throw new org.omg.CORBA.BAD_OPERATION(method + " not found");
		switch ( opsIndex.intValue() )
		{
			case 0: // sayHello
			{
				String _arg0=_input.read_string();
				_out = handler.createReply();
				String tmpResult1 = sayHello(_arg0);
_out.write_string( tmpResult1 );
				break;
			}
		}
		return _out;
	}

	public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] obj_id)
	{
		return ids;
	}
}
