package demo.notification.office;

/**
 * Generated from IDL struct "Job".
 *
 * @author JacORB IDL compiler V 3.5
 * @version generated at Dec 16, 2014 2:51:15 PM
 */

public final class JobHolder
	implements org.omg.CORBA.portable.Streamable
{
	public Job value;

	public JobHolder ()
	{
	}
	public JobHolder(final Job initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return JobHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = JobHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		JobHelper.write(_out, value);
	}
}
