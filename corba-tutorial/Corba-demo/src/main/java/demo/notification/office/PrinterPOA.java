package demo.notification.office;


/**
 * Generated from IDL interface "Printer".
 *
 * @author JacORB IDL compiler V 3.5
 * @version generated at Dec 16, 2014 2:51:15 PM
 */

public abstract class PrinterPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, demo.notification.office.PrinterOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "setOffLine", Integer.valueOf(0));
		m_opsHash.put ( "cancel", Integer.valueOf(1));
		m_opsHash.put ( "print", Integer.valueOf(2));
	}
	private String[] ids = {"IDL:demo/notification/office/Printer:1.0"};
	public Printer _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		Printer __r = demo.notification.office.PrinterHelper.narrow(__o);
		return __r;
	}
	public Printer _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		Printer __r = demo.notification.office.PrinterHelper.narrow(__o);
		return __r;
	}
	public org.omg.CORBA.portable.OutputStream _invoke(String method, org.omg.CORBA.portable.InputStream _input, org.omg.CORBA.portable.ResponseHandler handler)
		throws org.omg.CORBA.SystemException
	{
		org.omg.CORBA.portable.OutputStream _out = null;
		// do something
		// quick lookup of operation
		Integer opsIndex = (Integer)m_opsHash.get ( method );
		if ( null == opsIndex )
			throw new org.omg.CORBA.BAD_OPERATION(method + " not found");
		switch ( opsIndex.intValue() )
		{
			case 0: // setOffLine
			{
				boolean _arg0=_input.read_boolean();
				_out = handler.createReply();
				setOffLine(_arg0);
				break;
			}
			case 1: // cancel
			{
			try
			{
				int _arg0=_input.read_long();
				String _arg1=_input.read_string();
				_out = handler.createReply();
				cancel(_arg0,_arg1);
			}
			catch(demo.notification.office.PrinterPackage.UnknownJobID _ex0)
			{
				_out = handler.createExceptionReply();
				demo.notification.office.PrinterPackage.UnknownJobIDHelper.write(_out, _ex0);
				if (handler instanceof org.jacorb.orb.dsi.ServerRequest && !demo.notification.office.PrinterPackage.UnknownJobIDHelper.id().equals(_ex0.getMessage()))
				{
					((org.jacorb.orb.giop.ReplyOutputStream)_out).addServiceContext (org.jacorb.orb.dsi.ServerRequest.createExceptionDetailMessage (_ex0.getMessage()));
				}
			}
			catch(demo.notification.office.PrinterPackage.AlreadyPrinted _ex1)
			{
				_out = handler.createExceptionReply();
				demo.notification.office.PrinterPackage.AlreadyPrintedHelper.write(_out, _ex1);
				if (handler instanceof org.jacorb.orb.dsi.ServerRequest && !demo.notification.office.PrinterPackage.AlreadyPrintedHelper.id().equals(_ex1.getMessage()))
				{
					((org.jacorb.orb.giop.ReplyOutputStream)_out).addServiceContext (org.jacorb.orb.dsi.ServerRequest.createExceptionDetailMessage (_ex1.getMessage()));
				}
			}
				break;
			}
			case 2: // print
			{
			try
			{
				String _arg0=_input.read_string();
				String _arg1=_input.read_string();
				_out = handler.createReply();
				_out.write_long(print(_arg0,_arg1));
			}
			catch(demo.notification.office.PrinterPackage.OffLine _ex0)
			{
				_out = handler.createExceptionReply();
				demo.notification.office.PrinterPackage.OffLineHelper.write(_out, _ex0);
				if (handler instanceof org.jacorb.orb.dsi.ServerRequest && !demo.notification.office.PrinterPackage.OffLineHelper.id().equals(_ex0.getMessage()))
				{
					((org.jacorb.orb.giop.ReplyOutputStream)_out).addServiceContext (org.jacorb.orb.dsi.ServerRequest.createExceptionDetailMessage (_ex0.getMessage()));
				}
			}
				break;
			}
		}
		return _out;
	}

	public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] obj_id)
	{
		return ids;
	}
}
