package demo.notification.office;

/**
 * Generated from IDL alias "UserID".
 *
 * @author JacORB IDL compiler V 3.5
 * @version generated at Dec 16, 2014 2:51:15 PM
 */

public abstract class UserIDHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, String s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static String extract (final org.omg.CORBA.Any any)
	{
		if ( any.type().kind() == org.omg.CORBA.TCKind.tk_null)
		{
			throw new org.omg.CORBA.BAD_OPERATION ("Can't extract from Any with null type.");
		}
		return read (any.create_input_stream ());
	}

	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(UserIDHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(UserIDHelper.id(), "UserID",org.omg.CORBA.ORB.init().create_string_tc(0));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:demo/notification/office/UserID:1.0";
	}
	public static String read (final org.omg.CORBA.portable.InputStream _in)
	{
		String _result;
		_result=_in.read_string();
		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, String _s)
	{
		String tmpResult0 = _s;
_out.write_string( tmpResult0 );
	}
}
