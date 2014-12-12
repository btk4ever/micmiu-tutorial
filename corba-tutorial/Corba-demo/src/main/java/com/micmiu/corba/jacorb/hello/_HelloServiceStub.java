package com.micmiu.corba.jacorb.hello;


/**
 * Generated from IDL interface "HelloService".
 *
 * @author JacORB IDL compiler V 3.5
 * @version generated at Dec 11, 2014 10:38:48 AM
 */

public class _HelloServiceStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements com.micmiu.corba.jacorb.hello.HelloService
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] ids = {"IDL:com/micmiu/corba/jacorb/hello/HelloService:1.0"};
	public String[] _ids()
	{
		return ids;
	}

	public final static Class _opsClass = com.micmiu.corba.jacorb.hello.HelloServiceOperations.class;
	public String sayHello(String name)
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "sayHello", true);
					String tmpResult0 = name;
_os.write_string( tmpResult0 );
					_is = _invoke(_os);
					String _result = _is.read_string();
					return _result;
				}
				catch( org.omg.CORBA.portable.RemarshalException _rx )
					{
						continue;
					}
				catch( org.omg.CORBA.portable.ApplicationException _ax )
				{
					String _id = _ax.getId();
					try
					{
							_ax.getInputStream().close();
					}
					catch (java.io.IOException e)
					{
						throw new RuntimeException("Unexpected exception " + e.toString() );
					}
					throw new RuntimeException("Unexpected exception " + _id );
			}
			finally
			{
				if (_os != null)
				{
					try
					{
						_os.close();
					}
					catch (java.io.IOException e)
					{
						throw new RuntimeException("Unexpected exception " + e.toString() );
					}
				}
				this._releaseReply(_is);
			}
		}
		else
		{
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "sayHello", _opsClass );
			if( _so == null )
				continue;
			HelloServiceOperations _localServant = (HelloServiceOperations)_so.servant;
			String _result;
			try
			{
				_result = _localServant.sayHello(name);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return _result;
			}
			catch (RuntimeException re) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(re);
				throw re;
			}
			catch (Error err)
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(err);
				throw err;
			}
			finally
			{
				_servant_postinvoke(_so);
			}
		}

		}

	}

}
