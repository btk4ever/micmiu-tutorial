package demo.notification.office.PrinterPackage;


/**
 * Generated from IDL exception "UnknownJobID".
 *
 * @author JacORB IDL compiler V 3.5
 * @version generated at Dec 16, 2014 2:51:15 PM
 */

public abstract class UnknownJobIDHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(UnknownJobIDHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(UnknownJobIDHelper.id(),"UnknownJobID",new org.omg.CORBA.StructMember[0]);
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final UnknownJobID s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static UnknownJobID extract (final org.omg.CORBA.Any any)
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
		return "IDL:demo/notification/office/Printer/UnknownJobID:1.0";
	}
	public static UnknownJobID read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		if (in instanceof org.jacorb.orb.giop.ReplyInputStream)
		{
			org.jacorb.orb.giop.ReplyInputStream reply = (org.jacorb.orb.giop.ReplyInputStream) in;
			org.omg.IOP.ServiceContext context = null;
			if ( (context = reply.getServiceContext(org.omg.IOP.ExceptionDetailMessage.value)) != null)
			{
				org.jacorb.orb.CDRInputStream details = new org.jacorb.orb.CDRInputStream(context.context_data);
				details.openEncapsulatedArray();
				id = details.read_wstring();
			}
		}
		final UnknownJobID result = new UnknownJobID(id);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final UnknownJobID s)
	{
		out.write_string(id());
	}
}
