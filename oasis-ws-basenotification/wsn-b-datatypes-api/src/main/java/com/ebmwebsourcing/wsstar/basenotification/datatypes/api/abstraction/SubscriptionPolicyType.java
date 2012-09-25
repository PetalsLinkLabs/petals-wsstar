package com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction;

import java.util.List;

import org.w3c.dom.Element;

public interface SubscriptionPolicyType {

	 List<Element> getPolicies();
	
	 void addPolicy(Element policy);
}
