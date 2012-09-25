package com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.impl;

import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.DestroyResponse;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.WsrfrlJAXBContext;

public class DestroyResponseImpl implements DestroyResponse {

	private com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.DestroyResponse jaxbTypeObj;
	
	/**
	 * Default constructor
	 */
	protected DestroyResponseImpl() {
		this.jaxbTypeObj = WsrfrlJAXBContext.WSRFRL_JAXB_FACTORY.createDestroyResponse();		
	}
	
	protected DestroyResponseImpl(com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.DestroyResponse jaxbTypeObj) {
		this.jaxbTypeObj = jaxbTypeObj;
	}
	
	protected final com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.DestroyResponse getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.DestroyResponse}
	 *  "Jaxb model type" object from a {@link Destroy} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.DestroyResponse toJaxbModel(DestroyResponse apiTypeObj) {

		com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.DestroyResponse jaxbTypeObj = null;
		
		if (apiTypeObj instanceof DestroyResponseImpl){
			jaxbTypeObj = ((DestroyResponseImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = WsrfrlJAXBContext.WSRFRL_JAXB_FACTORY.createDestroyResponse();
		}
		return jaxbTypeObj;
	}
	
}
