package demo.notification.office;


/**
 * Generated from IDL interface "Printer".
 *
 * @author JacORB IDL compiler V 3.5
 * @version generated at Dec 16, 2014 2:51:15 PM
 */

public interface PrinterOperations
{
	/* constants */
	/* operations  */
	int print(String text, String uid) throws demo.notification.office.PrinterPackage.OffLine;
	void cancel(int id, String uid) throws demo.notification.office.PrinterPackage.UnknownJobID,demo.notification.office.PrinterPackage.AlreadyPrinted;
	void setOffLine(boolean flag);
}
