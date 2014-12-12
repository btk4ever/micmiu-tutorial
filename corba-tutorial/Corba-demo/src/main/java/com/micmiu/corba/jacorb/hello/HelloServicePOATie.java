package com.micmiu.corba.jacorb.hello;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "HelloService".
 *
 * @author JacORB IDL compiler V 3.5
 * @version generated at Dec 11, 2014 10:38:48 AM
 */

public class HelloServicePOATie
	extends HelloServicePOA
{
	private HelloServiceOperations _delegate;

	private POA _poa;
	public HelloServicePOATie(HelloServiceOperations delegate)
	{
		_delegate = delegate;
	}
	public HelloServicePOATie(HelloServiceOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
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
	public HelloServiceOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(HelloServiceOperations delegate)
	{
		_delegate = delegate;
	}
	public POA _default_POA()
	{
		if (_poa != null)
		{
			return _poa;
		}
		return super._default_POA();
	}
	public String sayHello(String name)
	{
		return _delegate.sayHello(name);
	}

}
