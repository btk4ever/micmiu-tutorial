package demo.notification.office;

/**
 * Generated from IDL struct "Job".
 *
 * @author JacORB IDL compiler V 3.5
 * @version generated at Dec 16, 2014 2:51:15 PM
 */

public final class Job
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public Job(){}
	public int job_id;
	public String user_id = "";
	public Job(int job_id, String user_id)
	{
		this.job_id = job_id;
		this.user_id = user_id;
	}
}
