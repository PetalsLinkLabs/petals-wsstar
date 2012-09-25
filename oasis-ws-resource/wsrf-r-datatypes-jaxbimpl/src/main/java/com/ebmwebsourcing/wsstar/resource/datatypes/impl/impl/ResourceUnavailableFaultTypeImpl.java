package com.ebmwebsourcing.wsstar.resource.datatypes.impl.impl;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.abstraction.ResourceUnavailableFaultType;
import com.ebmwebsourcing.wsstar.resource.datatypes.impl.WsrfrJAXBContext;

public class ResourceUnavailableFaultTypeImpl extends BaseFaultTypeImpl implements ResourceUnavailableFaultType {

	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link ResourceUnavailableFaultType) object that must
	 * 		  be {@link GregorianCalendar} representation of an Date xml type.
	 */
	protected ResourceUnavailableFaultTypeImpl(Date timestamp){
		super(Logger.getLogger(ResourceUnavailableFaultTypeImpl.class.getSimpleName()));
		
		com.ebmwebsourcing.wsstar.jaxb.resource.resource.ResourceUnavailableFaultType jaxbTypeObj =
			new com.ebmwebsourcing.wsstar.jaxb.resource.resource.ResourceUnavailableFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp,this.getLogger()));		
		
		this.setJaxbTypeObj(jaxbTypeObj);
	}
	
	protected ResourceUnavailableFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.resource.resource.ResourceUnavailableFaultType jaxbTypeObj) {
		super(jaxbTypeObj,Logger.getLogger(ResourceUnavailableFaultTypeImpl.class.getSimpleName()));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.resource.ResourceUnavailableFaultType}
	 *  "Jaxb model type" object from a {@link ResourceUnavailableFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.resource.resource.ResourceUnavailableFaultType toJaxbModel(ResourceUnavailableFaultType apiTypeObj){

		com.ebmwebsourcing.wsstar.jaxb.resource.resource.ResourceUnavailableFaultType jaxbTypeObj = null;
		if (apiTypeObj instanceof ResourceUnavailableFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.resource.resource.ResourceUnavailableFaultType)
				((ResourceUnavailableFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.resource.resource.ResourceUnavailableFaultType) 
				BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
						WsrfrJAXBContext.WSRFR_JAXB_FACTORY.createResourceUnavailableFaultType());
		}

		return jaxbTypeObj;

	}
	
}
