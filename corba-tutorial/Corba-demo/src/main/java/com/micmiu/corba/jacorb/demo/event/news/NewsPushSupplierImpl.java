package com.micmiu.corba.jacorb.demo.event.news;

import org.omg.CosEventComm.PushSupplierPOA;

/**
 * Created Server侧的Supplier的实现类
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 12/15/2014
 * Time: 13:26
 */
public class NewsPushSupplierImpl extends PushSupplierPOA {

	public void disconnect_push_supplier() {
		System.out.println("[PushSupplier] >>>> disconnect_push_supplier");
	}
}
