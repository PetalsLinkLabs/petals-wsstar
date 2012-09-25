package com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction;

import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;

public interface CreatePullPointResponse {
	
	 EndpointReferenceType getPullPoint();
	
	 void setPullPoint(EndpointReferenceType endpoint);

}
