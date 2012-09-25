package com.ebmwebsourcing.wsstar.wsnb.services.transport;

import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.GetCurrentMessage;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.GetCurrentMessageResponse;
import com.ebmwebsourcing.wsstar.wsrfbf.services.faults.AbsWSStarFault;

public interface ITransporterForWsnbRequestor {
	
	GetCurrentMessageResponse sendGetCurrentMessageRequest(final EndpointReferenceType producerServiceEdp, 
            final GetCurrentMessage payload) throws AbsWSStarFault;
}
