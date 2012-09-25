package com.ebmwebsourcing.wsstar.wsrfrl.services.transport;

import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.Destroy;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.DestroyResponse;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.SetTerminationTime;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.SetTerminationTimeResponse;
import com.ebmwebsourcing.wsstar.wsrfbf.services.faults.AbsWSStarFault;

public interface ITransporterForWsrfrlRequestor {
	
	DestroyResponse sendDestroyRequest(final EndpointReferenceType wsrfrlServiceEdp, final Destroy payload) throws AbsWSStarFault;
	
	SetTerminationTimeResponse sendSetTerminationTimeRequest(final EndpointReferenceType wsrfrlServiceEdp, 
			final SetTerminationTime payload) throws AbsWSStarFault;
}
