package demo.notification.office.PrinterPackage;

/**
 * Generated from IDL exception "OffLine".
 *
 * @author JacORB IDL compiler V 3.5
 * @version generated at Dec 16, 2014 2:51:15 PM
 */

public final class OffLineHolder
	implements org.omg.CORBA.portable.Streamable
{
	public OffLine value;

	public OffLineHolder ()
	{
	}
	public OffLineHolder(final OffLine initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return OffLineHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = OffLineHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		OffLineHelper.write(_out, value);
	}
}
