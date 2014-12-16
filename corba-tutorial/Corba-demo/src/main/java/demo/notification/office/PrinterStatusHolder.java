package demo.notification.office;
/**
 * Generated from IDL union "PrinterStatus".
 *
 * @author JacORB IDL compiler V 3.5
 * @version generated at Dec 16, 2014 2:51:15 PM
 */

public final class PrinterStatusHolder
	implements org.omg.CORBA.portable.Streamable
{
	public PrinterStatus value;

	public PrinterStatusHolder ()
	{
	}
	public PrinterStatusHolder (final PrinterStatus initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return PrinterStatusHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = PrinterStatusHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		PrinterStatusHelper.write (out, value);
	}
}
