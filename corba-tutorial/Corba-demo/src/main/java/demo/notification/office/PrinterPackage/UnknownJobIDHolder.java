package demo.notification.office.PrinterPackage;

/**
 * Generated from IDL exception "UnknownJobID".
 *
 * @author JacORB IDL compiler V 3.5
 * @version generated at Dec 16, 2014 2:51:15 PM
 */

public final class UnknownJobIDHolder
	implements org.omg.CORBA.portable.Streamable
{
	public UnknownJobID value;

	public UnknownJobIDHolder ()
	{
	}
	public UnknownJobIDHolder(final UnknownJobID initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return UnknownJobIDHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = UnknownJobIDHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		UnknownJobIDHelper.write(_out, value);
	}
}
