package demo.notification.office;


/**
 * Generated from IDL interface "Printer".
 *
 * @author JacORB IDL compiler V 3.5
 * @version generated at Dec 16, 2014 2:51:15 PM
 */

public abstract class PrinterHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(PrinterHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:demo/notification/office/Printer:1.0", "Printer");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final Printer s)
	{
			any.insert_Object(s);
	}
	public static Printer extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:demo/notification/office/Printer:1.0";
	}
	public static Printer read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(_PrinterStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final Printer s)
	{
		_out.write_Object(s);
	}
	public static Printer narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof Printer)
		{
			return (Printer)obj;
		}
		else if (obj._is_a("IDL:demo/notification/office/Printer:1.0"))
		{
			_PrinterStub stub;
			stub = new _PrinterStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static Printer unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof Printer)
		{
			return (Printer)obj;
		}
		else
		{
			_PrinterStub stub;
			stub = new _PrinterStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
