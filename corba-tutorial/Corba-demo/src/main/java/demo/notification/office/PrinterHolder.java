package demo.notification.office;

/**
 * Generated from IDL interface "Printer".
 *
 * @author JacORB IDL compiler V 3.5
 * @version generated at Dec 16, 2014 2:51:15 PM
 */

public final class PrinterHolder	implements org.omg.CORBA.portable.Streamable{
	 public Printer value;
	public PrinterHolder()
	{
	}
	public PrinterHolder (final Printer initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return PrinterHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = PrinterHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		PrinterHelper.write (_out,value);
	}
}
