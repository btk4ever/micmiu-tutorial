package demo.notification.office;
/**
 * Generated from IDL enum "Status".
 *
 * @author JacORB IDL compiler V 3.5
 * @version generated at Dec 16, 2014 2:51:15 PM
 */

public final class StatusHolder
	implements org.omg.CORBA.portable.Streamable
{
	public Status value;

	public StatusHolder ()
	{
	}
	public StatusHolder (final Status initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return StatusHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = StatusHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		StatusHelper.write (out,value);
	}
}
