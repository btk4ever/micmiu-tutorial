package com.micmiu.corba.jacorb.demo.notification.news;

import org.omg.CORBA.TCKind;
import org.omg.CosEventComm.Disconnected;
import org.omg.CosNotification.EventType;
import org.omg.CosNotification.Property;
import org.omg.CosNotification.StructuredEvent;
import org.omg.CosNotifyComm.InvalidEventType;
import org.omg.CosNotifyComm.StructuredPushConsumerPOA;

/**
 * Created
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 12/15/2014
 * Time: 16:31
 */
public class StructuredPushConsumerImpl extends StructuredPushConsumerPOA {

	private String identifier;

	public StructuredPushConsumerImpl(String identifier) {
		this.identifier = identifier;
	}

	public void disconnect_structured_push_consumer() {
		System.out.println("[PushConsumer] >>>> disconnect_structured_push_consumer");

	}

	public void push_structured_event(StructuredEvent event) throws Disconnected {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(identifier);
		sb.append("]");
		sb.append(event.header.fixed_header.event_type.domain_name);
		sb.append(" ");
		sb.append(event.header.fixed_header.event_type.type_name);
		sb.append(",");
		sb.append(event.header.fixed_header.event_name);
		sb.append(",");
		for (Property prop : event.header.variable_header) {
			sb.append(prop.name);
			sb.append("=");
			if (prop.value.type().kind().value() == TCKind._tk_short) {
				sb.append(prop.value.extract_short());
			}
			sb.append(",");
		}

		if (event.remainder_of_body.type().kind().value() == TCKind._tk_string) {
			sb.append(event.remainder_of_body.extract_string());
		}
		System.out.println("[PushConsumer] >>>> " + sb.toString());
	}

	public void offer_change(EventType[] added, EventType[] removed) throws InvalidEventType {
		throw new UnsupportedOperationException();
	}

}
