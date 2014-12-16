package demo.notification.office;

/**
 * Generated from IDL union "PrinterStatus".
 *
 * @author JacORB IDL compiler V 3.5
 * @version generated at Dec 16, 2014 2:51:15 PM
 */

public final class PrinterStatus
	implements org.omg.CORBA.portable.IDLEntity
{
	private Status discriminator;
	private Job the_job;
	private boolean dummy;

	public PrinterStatus ()
	{
	}

	public Status discriminator ()
	{
		return discriminator;
	}

	public Job the_job ()
	{
		if (discriminator != Status.PRINTED && discriminator != Status.CANCELLED)
			throw new org.omg.CORBA.BAD_OPERATION();
		return the_job;
	}

	public void the_job (Job _x)
	{
		discriminator = Status.PRINTED;
		the_job = _x;
	}

	public void the_job (Status _discriminator, Job _x)
	{
		if (_discriminator != Status.PRINTED && _discriminator != Status.CANCELLED)
			throw new org.omg.CORBA.BAD_OPERATION();
		discriminator = _discriminator;
		the_job = _x;
	}

	public boolean dummy ()
	{
		if (discriminator != Status.ONLINE && discriminator != Status.OFFLINE)
			throw new org.omg.CORBA.BAD_OPERATION();
		return dummy;
	}

	public void dummy (boolean _x)
	{
		discriminator = Status.ONLINE;
		dummy = _x;
	}

	public void dummy (Status _discriminator, boolean _x)
	{
		if (_discriminator != Status.ONLINE && _discriminator != Status.OFFLINE)
			throw new org.omg.CORBA.BAD_OPERATION();
		discriminator = _discriminator;
		dummy = _x;
	}

	public void __default ()
	{
		discriminator = Status.JAMMED;
	}
	public void __default (Status _discriminator)
	{
		if(  _discriminator == Status.PRINTED || _discriminator == Status.CANCELLED || _discriminator == Status.ONLINE || _discriminator == Status.OFFLINE )
			throw new org.omg.CORBA.BAD_PARAM( "Illegal value is used in __default method", 34, org.omg.CORBA.CompletionStatus.COMPLETED_NO );

		discriminator = _discriminator;
	}
}
