package com.micmiu.corba.jacorb.demo.notification.news;

import org.omg.CosNotification.EventType;
import org.omg.CosNotifyComm.InvalidEventType;
import org.omg.CosNotifyComm.StructuredPushSupplierPOA;

/**
 * Created
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 12/15/2014
 * Time: 16:29
 */
public class StructuredPushSupplierImpl extends StructuredPushSupplierPOA {

	public void disconnect_structured_push_supplier() {
		System.out.println("[PushSupplier] >>>> disconnect_structured_push_supplier");
	}

	public void subscription_change(EventType[] added, EventType[] removed)
			throws InvalidEventType {
		throw new UnsupportedOperationException();
	}

}  