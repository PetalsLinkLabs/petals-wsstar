package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UseRaw;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class UseRawImpl implements UseRaw {

	private com.ebmwebsourcing.wsstar.jaxb.notification.base.UseRaw jaxbTypeObj;
//	private static Logger logger  = Logger.getLogger(UseRawImpl.class.getSimpleName());
	
	/**
	 * Default constructor
	 */
	protected UseRawImpl() {		
		this.jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createUseRaw();		
	}

	protected UseRawImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.UseRaw jaxbTypeObj){
		this.jaxbTypeObj = jaxbTypeObj;
	}
	
	protected final com.ebmwebsourcing.wsstar.jaxb.notification.base.UseRaw getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.notification.base.UseRaw}
	 *  "Jaxb model type" object from a {@link UseRaw} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.UseRaw toJaxbModel(UseRaw apiTypeObj) {

		com.ebmwebsourcing.wsstar.jaxb.notification.base.UseRaw jaxbTypeObj = null;
		
		if (apiTypeObj instanceof UseRawImpl){
			jaxbTypeObj = ((UseRawImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createUseRaw();
		}
		return jaxbTypeObj;
	}
}
