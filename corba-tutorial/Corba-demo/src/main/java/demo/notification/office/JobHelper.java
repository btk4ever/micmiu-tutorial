package demo.notification.office;


/**
 * Generated from IDL struct "Job".
 *
 * @author JacORB IDL compiler V 3.5
 * @version generated at Dec 16, 2014 2:51:15 PM
 */

public abstract class JobHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(JobHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(JobHelper.id(),"Job",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("job_id", org.omg.CORBA.ORB.init().create_alias_tc(demo.notification.office.JobIDHelper.id(), "JobID",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3))), null),new org.omg.CORBA.StructMember("user_id", org.omg.CORBA.ORB.init().create_alias_tc(demo.notification.office.UserIDHelper.id(), "UserID",org.omg.CORBA.ORB.init().create_string_tc(0)), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final Job s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static Job extract (final org.omg.CORBA.Any any)
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
		return "IDL:demo/notification/office/Job:1.0";
	}
	public static Job read (final org.omg.CORBA.portable.InputStream in)
	{
		Job result = new Job();
		result.job_id=in.read_long();
		result.user_id=in.read_string();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final Job s)
	{
		out.write_long(s.job_id);
		String tmpResult1 = s.user_id;
out.write_string( tmpResult1 );
	}
}
