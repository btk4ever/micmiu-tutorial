package demo.notification.office;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "Printer".
 *
 * @author JacORB IDL compiler V 3.5
 * @version generated at Dec 16, 2014 2:51:15 PM
 */

public class PrinterPOATie
	extends PrinterPOA
{
	private PrinterOperations _delegate;

	private POA _poa;
	public PrinterPOATie(PrinterOperations delegate)
	{
		_delegate = delegate;
	}
	public PrinterPOATie(PrinterOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public Printer _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		Printer __r = PrinterHelper.narrow(__o);
		return __r;
	}
	public Printer _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		Printer __r = PrinterHelper.narrow(__o);
		return __r;
	}
	public PrinterOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(PrinterOperations delegate)
	{
		_delegate = delegate;
	}
	public POA _default_POA()
	{
		if (_poa != null)
		{
			return _poa;
		}
		return super._default_POA();
	}
	public void setOffLine(boolean flag)
	{
_delegate.setOffLine(flag);
	}

	public void cancel(int id, String uid) throws demo.notification.office.PrinterPackage.UnknownJobID,demo.notification.office.PrinterPackage.AlreadyPrinted
	{
_delegate.cancel(id,uid);
	}

	public int print(String text, String uid) throws demo.notification.office.PrinterPackage.OffLine
	{
		return _delegate.print(text,uid);
	}

}
