package com.ebmwebsourcing.wsstar.resource.datatypes.impl.impl;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.abstraction.ResourceUnknownFaultType;
import com.ebmwebsourcing.wsstar.resource.datatypes.impl.WsrfrJAXBContext;

public class ResourceUnknownFaultTypeImpl extends BaseFaultTypeImpl	implements	ResourceUnknownFaultType {
	
	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link ResourceUnknownFaultType) object that must
	 * 		  be {@link GregorianCalendar} representation of an Date xml type.
	 */
	protected ResourceUnknownFaultTypeImpl(Date timestamp){
		super(Logger.getLogger(ResourceUnknownFaultTypeImpl.class.getSimpleName()));
		
		com.ebmwebsourcing.wsstar.jaxb.resource.resource.ResourceUnknownFaultType jaxbTypeObj = 
			 new com.ebmwebsourcing.wsstar.jaxb.resource.resource.ResourceUnknownFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp,this.getLogger()));
		
		this.setJaxbTypeObj(jaxbTypeObj);
		
	}
	
	protected ResourceUnknownFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.resource.resource.ResourceUnknownFaultType jaxbTypeObj) {
		super(jaxbTypeObj,Logger.getLogger(ResourceUnknownFaultTypeImpl.class.getSimpleName()));
	}
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.resource.ResourceUnknownFaultType}
	 *  "Jaxb model type" object from a {@link ResourceUavailableFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.resource.resource.ResourceUnknownFaultType toJaxbModel(ResourceUnknownFaultType apiTypeObj){
		
		com.ebmwebsourcing.wsstar.jaxb.resource.resource.ResourceUnknownFaultType jaxbTypeObj = null;

		if (apiTypeObj instanceof ResourceUnknownFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.resource.resource.ResourceUnknownFaultType)
				((ResourceUnknownFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.resource.resource.ResourceUnknownFaultType) 
				BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
						WsrfrJAXBContext.WSRFR_JAXB_FACTORY.createResourceUnknownFaultType());
		}

		return jaxbTypeObj;
		
		
	}
}
