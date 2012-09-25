package com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.impl;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.UnableToSetTerminationTimeFaultType;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.WsrfrlJAXBContext;

public class UnableToSetTerminationTimeFaultTypeImpl extends BaseFaultTypeImpl
		implements UnableToSetTerminationTimeFaultType {
	
	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link UnableToSetTerminationTimeFaultType) object that must
	 * 		  be {@link GregorianCalendar} representation of an Date xml type.
	 */
	protected UnableToSetTerminationTimeFaultTypeImpl(Date timestamp){
		super(Logger.getLogger(UnableToSetTerminationTimeFaultTypeImpl.class.getSimpleName()));
		
		com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.UnableToSetTerminationTimeFaultType jaxbTypeObj =
			new com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.UnableToSetTerminationTimeFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp, this.getLogger()));
		
		this.setJaxbTypeObj(jaxbTypeObj);
	}
	
	protected UnableToSetTerminationTimeFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.UnableToSetTerminationTimeFaultType jaxbTypeObj) {
		super(jaxbTypeObj,Logger.getLogger(UnableToSetTerminationTimeFaultTypeImpl.class.getSimpleName()));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.UnableToSetTerminationTimeFaultType}
	 *  "Jaxb model type" object from a {@link UnableToSetTerminationTimeFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.UnableToSetTerminationTimeFaultType toJaxbModel(
			UnableToSetTerminationTimeFaultType apiTypeObj){
		com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.UnableToSetTerminationTimeFaultType jaxbTypeObj = null;
		if (apiTypeObj instanceof UnableToSetTerminationTimeFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.UnableToSetTerminationTimeFaultType)
			((UnableToSetTerminationTimeFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.UnableToSetTerminationTimeFaultType) 
			BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
					WsrfrlJAXBContext.WSRFRL_JAXB_FACTORY.createUnableToSetTerminationTimeFaultType());
		}

		return jaxbTypeObj;
	}
	
}
