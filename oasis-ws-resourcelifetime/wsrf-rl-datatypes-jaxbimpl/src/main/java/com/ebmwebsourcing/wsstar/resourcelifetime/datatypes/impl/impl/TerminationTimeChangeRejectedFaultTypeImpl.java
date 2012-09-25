package com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.impl;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.TerminationTimeChangeRejectedFaultType;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.WsrfrlJAXBContext;

public class TerminationTimeChangeRejectedFaultTypeImpl extends
		BaseFaultTypeImpl implements TerminationTimeChangeRejectedFaultType {
	
	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link TerminationTimeChangeRejectedFaultType) object that must
	 * 		  be {@link GregorianCalendar} representation of an Date xml type.
	 */
	protected TerminationTimeChangeRejectedFaultTypeImpl(Date timestamp){
		super(Logger.getLogger(TerminationTimeChangeRejectedFaultTypeImpl.class.getSimpleName()));
		com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationTimeChangeRejectedFaultType jaxbTypeObj =
			new com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationTimeChangeRejectedFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp, this.getLogger()));
		
		this.setJaxbTypeObj(jaxbTypeObj);
	}
	
	protected TerminationTimeChangeRejectedFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationTimeChangeRejectedFaultType jaxbTypeObj) {
		super(jaxbTypeObj,Logger.getLogger(TerminationTimeChangeRejectedFaultTypeImpl.class.getSimpleName()));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationTimeChangeRejectedFaultType}
	 *  "Jaxb model type" object from a {@link TerminationTimeChangeRejectedFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationTimeChangeRejectedFaultType toJaxbModel(
			TerminationTimeChangeRejectedFaultType apiTypeObj){

		com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationTimeChangeRejectedFaultType jaxbTypeObj = null;
		if (apiTypeObj instanceof TerminationTimeChangeRejectedFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationTimeChangeRejectedFaultType)
				((TerminationTimeChangeRejectedFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationTimeChangeRejectedFaultType) 
				BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
						WsrfrlJAXBContext.WSRFRL_JAXB_FACTORY.createTerminationTimeChangeRejectedFaultType());
		}

		return jaxbTypeObj;
	}
}
