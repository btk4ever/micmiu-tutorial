package com.micmiu.corba.jacorb.hello;

/**
 * Generated from IDL interface "HelloService".
 *
 * @author JacORB IDL compiler V 3.5
 * @version generated at Dec 11, 2014 10:38:48 AM
 */

public final class HelloServiceHolder	implements org.omg.CORBA.portable.Streamable{
	 public HelloService value;
	public HelloServiceHolder()
	{
	}
	public HelloServiceHolder (final HelloService initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return HelloServiceHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = HelloServiceHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		HelloServiceHelper.write (_out,value);
	}
}
