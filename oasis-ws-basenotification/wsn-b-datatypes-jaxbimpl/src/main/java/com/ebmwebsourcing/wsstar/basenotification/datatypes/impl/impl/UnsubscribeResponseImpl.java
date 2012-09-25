package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;


import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnsubscribeResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class UnsubscribeResponseImpl implements UnsubscribeResponse {

	private com.ebmwebsourcing.wsstar.jaxb.notification.base.UnsubscribeResponse jaxbTypeObj;
	
	/**
	 * Default constructor
	 */
	protected UnsubscribeResponseImpl() {		
		this.jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createUnsubscribeResponse();		
	}

	protected UnsubscribeResponseImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.UnsubscribeResponse jaxbTypeObj){
		this.jaxbTypeObj = jaxbTypeObj;
	}
	
	protected final com.ebmwebsourcing.wsstar.jaxb.notification.base.UnsubscribeResponse getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.notification.base.UnsubscribeResponse}
	 *  "Jaxb model type" object from a {@link UnsubscribeResponse} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.UnsubscribeResponse toJaxbModel(UnsubscribeResponse apiTypeObj) {

		com.ebmwebsourcing.wsstar.jaxb.notification.base.UnsubscribeResponse jaxbTypeObj = null;
		
		if (apiTypeObj instanceof UnsubscribeResponseImpl) {
			jaxbTypeObj = ((UnsubscribeResponseImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createUnsubscribeResponse();
		}
		return jaxbTypeObj;
	}
	
}
