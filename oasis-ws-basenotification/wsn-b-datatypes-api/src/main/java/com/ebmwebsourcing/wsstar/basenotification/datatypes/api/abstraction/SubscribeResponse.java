package com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction;


import java.util.Date;

import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;

public interface SubscribeResponse {

	 Date getCurrentTime();
	
	 void setCurrentTime(Date value);

	 EndpointReferenceType getSubscriptionReference();
	
	 void setSubscriptionReference(EndpointReferenceType value);

	 Date getTerminationTime();
	
	 void setTerminationTime(Date termTime);

}
