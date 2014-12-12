package com.micmiu.corba.jacorb.hello;


/**
 * Generated from IDL interface "HelloService".
 *
 * @author JacORB IDL compiler V 3.5
 * @version generated at Dec 11, 2014 10:38:48 AM
 */

public abstract class HelloServiceHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(HelloServiceHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:com/micmiu/corba/jacorb/hello/HelloService:1.0", "HelloService");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final com.micmiu.corba.jacorb.hello.HelloService s)
	{
			any.insert_Object(s);
	}
	public static com.micmiu.corba.jacorb.hello.HelloService extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:com/micmiu/corba/jacorb/hello/HelloService:1.0";
	}
	public static HelloService read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(com.micmiu.corba.jacorb.hello._HelloServiceStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final com.micmiu.corba.jacorb.hello.HelloService s)
	{
		_out.write_Object(s);
	}
	public static com.micmiu.corba.jacorb.hello.HelloService narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof com.micmiu.corba.jacorb.hello.HelloService)
		{
			return (com.micmiu.corba.jacorb.hello.HelloService)obj;
		}
		else if (obj._is_a("IDL:com/micmiu/corba/jacorb/hello/HelloService:1.0"))
		{
			com.micmiu.corba.jacorb.hello._HelloServiceStub stub;
			stub = new com.micmiu.corba.jacorb.hello._HelloServiceStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static com.micmiu.corba.jacorb.hello.HelloService unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof com.micmiu.corba.jacorb.hello.HelloService)
		{
			return (com.micmiu.corba.jacorb.hello.HelloService)obj;
		}
		else
		{
			com.micmiu.corba.jacorb.hello._HelloServiceStub stub;
			stub = new com.micmiu.corba.jacorb.hello._HelloServiceStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
