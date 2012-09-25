package com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.impl;

import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.Destroy;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.WsrfrlJAXBContext;

public class DestroyImpl implements Destroy {

	private com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.Destroy jaxbTypeObj;
	
	/**
	 * Default constructor
	 */
	protected DestroyImpl() {
		this.jaxbTypeObj = WsrfrlJAXBContext.WSRFRL_JAXB_FACTORY.createDestroy();		
	}
	
	protected DestroyImpl(com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.Destroy jaxbTypeObj) {
		this.jaxbTypeObj = jaxbTypeObj;
	}	
	
	protected final com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.Destroy getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.Destroy}
	 *  "Jaxb model type" object from a {@link Destroy} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.Destroy toJaxbModel(Destroy apiTypeObj) {

		com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.Destroy jaxbTypeObj = null;
		
		if (apiTypeObj instanceof DestroyImpl){
			jaxbTypeObj = ((DestroyImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = WsrfrlJAXBContext.WSRFRL_JAXB_FACTORY.createDestroy();
		}
		return jaxbTypeObj;
	}
	
	
}
