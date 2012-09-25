package com.ebmwebsourcing.wsstar.wsrfrp.services.transport;

import javax.xml.namespace.QName;

import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.GetResourcePropertyResponse;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateResourceProperties;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateResourcePropertiesResponse;
import com.ebmwebsourcing.wsstar.wsrfbf.services.faults.AbsWSStarFault;

public interface ITransporterForWsrfrpRequestor {
	
	GetResourcePropertyResponse sendGetResourcePropertyRequest(final EndpointReferenceType wsrfrpServiceEdp,
			final QName payload) throws AbsWSStarFault;
	
	UpdateResourcePropertiesResponse sendUpdateResourceProperties(final EndpointReferenceType wsrfrpServiceEdp,
			final UpdateResourceProperties payload) throws AbsWSStarFault;
	
}
