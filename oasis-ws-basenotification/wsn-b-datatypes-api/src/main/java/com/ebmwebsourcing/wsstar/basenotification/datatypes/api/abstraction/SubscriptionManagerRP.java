package com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction;


import java.util.Date;

import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;

public interface SubscriptionManagerRP {
	
	FilterType getFilter();

	void setFilter(FilterType filter);

	EndpointReferenceType getConsumerReference();

	void setConsumerReference(EndpointReferenceType value);

	Date getCreationTime();

	void setCreationTime(Date value);

	SubscriptionPolicyType getSubscriptionPolicy();

	void setSubscriptionPolicy(SubscriptionPolicyType value);
}
