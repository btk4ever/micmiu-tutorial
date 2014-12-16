package demo.notification.office;

/**
 * Generated from IDL union "PrinterStatus".
 *
 * @author JacORB IDL compiler V 3.5
 * @version generated at Dec 16, 2014 2:51:15 PM
 */

public abstract class PrinterStatusHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(PrinterStatusHelper.class)
			{
				if (_type == null)
				{
			org.omg.CORBA.UnionMember[] members = new org.omg.CORBA.UnionMember[4];
			org.omg.CORBA.Any label_any;
			label_any = org.omg.CORBA.ORB.init().create_any ();
			StatusHelper.insert(label_any, Status.PRINTED);
			members[0] = new org.omg.CORBA.UnionMember ("the_job", label_any, JobHelper.type(),null);
			label_any = org.omg.CORBA.ORB.init().create_any ();
			StatusHelper.insert(label_any, Status.CANCELLED);
			members[1] = new org.omg.CORBA.UnionMember ("the_job", label_any, JobHelper.type(),null);
			label_any = org.omg.CORBA.ORB.init().create_any ();
			StatusHelper.insert(label_any, Status.ONLINE);
			members[2] = new org.omg.CORBA.UnionMember ("dummy", label_any, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(8)),null);
			label_any = org.omg.CORBA.ORB.init().create_any ();
			StatusHelper.insert(label_any, Status.OFFLINE);
			members[3] = new org.omg.CORBA.UnionMember ("dummy", label_any, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(8)),null);
			 _type = org.omg.CORBA.ORB.init().create_union_tc(id(),"PrinterStatus",org.omg.CORBA.ORB.init().create_enum_tc(StatusHelper.id(),"Status",new String[]{"CANCELLED","PRINTED","ONLINE","OFFLINE","JAMMED","OUT_OF_PAPER"}), members);
				}
			}
		}
			return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final PrinterStatus s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static PrinterStatus extract (final org.omg.CORBA.Any any)
	{
		org.omg.CORBA.portable.InputStream in = any.create_input_stream();
		try
		{
			return read (in);
		}
		finally
		{
			try
			{
				in.close();
			}
			catch (java.io.IOException e)
			{
			throw new RuntimeException("Unexpected exception " + e.toString() );
			}
		}
	}

	public static String id()
	{
		return "IDL:demo/notification/office/PrinterStatus:1.0";
	}
	public static PrinterStatus read (org.omg.CORBA.portable.InputStream in)
	{
		PrinterStatus result = new PrinterStatus();
		Status disc;
		try
		{
			disc = Status.from_int(in.read_long());
			switch (disc.value ())
			{
				case Status._PRINTED:
				case Status._CANCELLED:
				{
					Job _var;
					_var= JobHelper.read(in);
					result.the_job (disc,_var);
					break;
				}
				case Status._ONLINE:
				case Status._OFFLINE:
				{
					boolean _var;
					_var=in.read_boolean();
					result.dummy (disc,_var);
					break;
				}
			default: result.__default (disc);
		}
		}
		catch (org.omg.CORBA.BAD_PARAM b)
		{
			// The default value was out-of-bounds for the Enum. Just use the default.
			result.__default ();
		}
		return result;
	}
	public static void write (org.omg.CORBA.portable.OutputStream out, PrinterStatus s)
	{
		out.write_long (s.discriminator().value ());
		switch (s.discriminator().value ())
		{
				case Status._PRINTED:
				case Status._CANCELLED:
				{
					JobHelper.write(out,s.the_job ());
					break;
				}
				case Status._ONLINE:
				case Status._OFFLINE:
				{
					out.write_boolean(s.dummy ());
					break;
				}
		}
	}
}
