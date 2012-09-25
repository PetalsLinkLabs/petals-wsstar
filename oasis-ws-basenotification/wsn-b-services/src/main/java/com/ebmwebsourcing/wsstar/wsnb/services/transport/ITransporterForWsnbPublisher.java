package com.ebmwebsourcing.wsstar.wsnb.services.transport;

import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Notify;

public interface ITransporterForWsnbPublisher {
	
	void sendNotifyRequest(final EndpointReferenceType consumerServiceEdp, final Notify payload); 
}
