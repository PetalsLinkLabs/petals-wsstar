package com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.ResourceNotDestroyedFaultType;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.WsrfrlJAXBContext;

public class ResourceNotDestroyedFaultTypeImpl extends BaseFaultTypeImpl
		implements ResourceNotDestroyedFaultType {

	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link ResourceNotDestroyedFaultType) object that must
	 * 		  be {@link Date} representation of an Date xml type.
	 */
	protected ResourceNotDestroyedFaultTypeImpl(Date timestamp){
		super(Logger.getLogger(ResourceNotDestroyedFaultTypeImpl.class.getSimpleName()));
		
		com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.ResourceNotDestroyedFaultType jaxbTypeObj =
			new com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.ResourceNotDestroyedFaultType() ;
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp, this.getLogger()));
		
		this.setJaxbTypeObj(jaxbTypeObj);
	}
	
	protected ResourceNotDestroyedFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.ResourceNotDestroyedFaultType jaxbTypeObj) {
		super(jaxbTypeObj,Logger.getLogger(ResourceNotDestroyedFaultTypeImpl.class.getSimpleName()));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.ResourceNotDestroyedFaultType}
	 *  "Jaxb model type" object from a {@link ResourceNotDestroyedFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.ResourceNotDestroyedFaultType toJaxbModel(ResourceNotDestroyedFaultType apiTypeObj){

		com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.ResourceNotDestroyedFaultType jaxbTypeObj = null;
		if (apiTypeObj instanceof ResourceNotDestroyedFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.ResourceNotDestroyedFaultType)
				((ResourceNotDestroyedFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.ResourceNotDestroyedFaultType) 
				BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
						WsrfrlJAXBContext.WSRFRL_JAXB_FACTORY.createResourceNotDestroyedFaultType());
		}

		return jaxbTypeObj;
	}
}
