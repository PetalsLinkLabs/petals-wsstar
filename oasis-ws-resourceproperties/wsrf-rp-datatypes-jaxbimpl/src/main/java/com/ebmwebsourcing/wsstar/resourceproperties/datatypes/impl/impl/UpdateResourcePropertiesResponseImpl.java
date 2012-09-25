package com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.impl;

import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateResourcePropertiesResponse;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.WsrfrpJAXBContext;

public class UpdateResourcePropertiesResponseImpl implements UpdateResourcePropertiesResponse {
	
	private com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourcePropertiesResponse jaxbTypeObj;
	
	/**
	 * Default constructor
	 */
	protected UpdateResourcePropertiesResponseImpl() {		
		this.jaxbTypeObj = WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createUpdateResourcePropertiesResponse();		
	}

	protected UpdateResourcePropertiesResponseImpl(com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourcePropertiesResponse jaxbTypeObj){
		this.jaxbTypeObj = jaxbTypeObj;
	}
	
	protected final com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourcePropertiesResponse getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourcePropertiesResponse}
	 *  "Jaxb model type" object from a {@link UpdateResourcePropertiesResponse} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourcePropertiesResponse toJaxbModel(UpdateResourcePropertiesResponse apiTypeObj) {

		com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourcePropertiesResponse jaxbTypeObj = null;
		
		if (apiTypeObj instanceof UpdateResourcePropertiesResponseImpl) {
			jaxbTypeObj = ((UpdateResourcePropertiesResponseImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createUpdateResourcePropertiesResponse();
		}
		return jaxbTypeObj;
	}
}
