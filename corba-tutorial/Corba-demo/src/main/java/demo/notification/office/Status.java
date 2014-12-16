package demo.notification.office;
/**
 * Generated from IDL enum "Status".
 *
 * @author JacORB IDL compiler V 3.5
 * @version generated at Dec 16, 2014 2:51:15 PM
 */

public final class Status
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _CANCELLED = 0;
	public static final Status CANCELLED = new Status(_CANCELLED);
	public static final int _PRINTED = 1;
	public static final Status PRINTED = new Status(_PRINTED);
	public static final int _ONLINE = 2;
	public static final Status ONLINE = new Status(_ONLINE);
	public static final int _OFFLINE = 3;
	public static final Status OFFLINE = new Status(_OFFLINE);
	public static final int _JAMMED = 4;
	public static final Status JAMMED = new Status(_JAMMED);
	public static final int _OUT_OF_PAPER = 5;
	public static final Status OUT_OF_PAPER = new Status(_OUT_OF_PAPER);
	public int value()
	{
		return value;
	}
	public static Status from_int(int value)
	{
		switch (value) {
			case _CANCELLED: return CANCELLED;
			case _PRINTED: return PRINTED;
			case _ONLINE: return ONLINE;
			case _OFFLINE: return OFFLINE;
			case _JAMMED: return JAMMED;
			case _OUT_OF_PAPER: return OUT_OF_PAPER;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _CANCELLED: return "CANCELLED";
			case _PRINTED: return "PRINTED";
			case _ONLINE: return "ONLINE";
			case _OFFLINE: return "OFFLINE";
			case _JAMMED: return "JAMMED";
			case _OUT_OF_PAPER: return "OUT_OF_PAPER";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected Status(int i)
	{
		value = i;
	}
	/**
	 * Designate replacement object when deserialized from stream. See
	 * http://www.omg.org/docs/ptc/02-01-03.htm#Issue4271
	 *
	 * @throws java.io.ObjectStreamException
	 */
	Object readResolve()
	throws java.io.ObjectStreamException
	{
		return from_int(value());
	}
}
