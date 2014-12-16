package demo.notification.office.PrinterPackage;

/**
 * Generated from IDL exception "AlreadyPrinted".
 *
 * @author JacORB IDL compiler V 3.5
 * @version generated at Dec 16, 2014 2:51:15 PM
 */

public final class AlreadyPrintedHolder
	implements org.omg.CORBA.portable.Streamable
{
	public AlreadyPrinted value;

	public AlreadyPrintedHolder ()
	{
	}
	public AlreadyPrintedHolder(final AlreadyPrinted initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return AlreadyPrintedHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = AlreadyPrintedHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		AlreadyPrintedHelper.write(_out, value);
	}
}
