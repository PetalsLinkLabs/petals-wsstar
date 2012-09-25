package com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction;


import java.util.Date;

import javax.xml.datatype.Duration;

import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;

public interface Subscribe {

	 FilterType getFilter();
	
	 void setFilter(FilterType filter);

	 EndpointReferenceType getConsumerReference();
	
	 void setConsumerReference(EndpointReferenceType value);

	 SubscriptionPolicyType getSubscriptionPolicy();
	
	 void setSubscriptionPolicy(SubscriptionPolicyType value);

	 Object getInitialTerminationTime();
	
	 void setInitialTerminationTime(Date value);
	
	 void setInitialTerminationTime(Duration value);
}
